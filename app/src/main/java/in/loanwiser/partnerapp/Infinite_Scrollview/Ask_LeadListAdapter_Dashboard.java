package in.loanwiser.partnerapp.Infinite_Scrollview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import in.loanwiser.partnerapp.CameraActivity.ASK_ManiActivity_Image2;
import in.loanwiser.partnerapp.CameraActivity.Ask_DocGridView_List;
import in.loanwiser.partnerapp.CameraActivity.DocGridView_List;
import in.loanwiser.partnerapp.Documents.Applicant_Doc_Details_revamp;
import in.loanwiser.partnerapp.PartnerActivitys.Ask_user_Dashboard_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;

public class Ask_LeadListAdapter_Dashboard extends RecyclerView.Adapter<Ask_LeadListAdapter_Dashboard.CustomViewHolder> {

    private Context context;
    private List<Ask_Lead_item> posts = new ArrayList<>();
    JSONObject J;
    public Ask_LeadListAdapter_Dashboard(Context context){
        this.context = context;
    }

    private String ask_id;

    private String doc_classname,doc_typeid,transaction_id;
  //  private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public void addPosts(List<Ask_Lead_item> posts) {
        this.posts.clear();
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ly_ask_new_lead_status, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

         holder.bindPost(posts.get(position));

    }

    @Override
    public int getItemCount() {
        return posts.size();
       // return 6;
    }

    public void clear() {
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView App_id,name_applicant,name_callcenter,status_Ask,ask_crated_at,documnet_name,
                notes1,raised_on_resolved,ask_completed;

        ImageView v_Image;
        ProgressBar progressBar;
        AppCompatButton add_notes,pipline,archive,ask_Tack_Action,ask_View_Action;
        AppCompatImageView loan_type_image;

        LinearLayout Over_all,ly_question,resolve_ly,raised_on_ly;
        View view;
        String loantype1,statues12,step_status,transaction_id,id,id1,
                relationship_type;

        public CustomViewHolder(View view) {
            super(view);


            App_id  = (AppCompatTextView) itemView.findViewById(R.id.App_id);
            name_applicant  = (AppCompatTextView) itemView.findViewById(R.id.name_applicant);
            name_callcenter  = (AppCompatTextView) itemView.findViewById(R.id.name_callcenter);
            status_Ask  = (AppCompatTextView) itemView.findViewById(R.id.status_Ask);
            ask_crated_at  = (AppCompatTextView) itemView.findViewById(R.id.ask_crated_at);
            documnet_name  = (AppCompatTextView) itemView.findViewById(R.id.documnet_name);
            ask_Tack_Action  = (AppCompatButton) itemView.findViewById(R.id.ask_Tack_Action);
            ask_View_Action  = (AppCompatButton) itemView.findViewById(R.id.ask_View_Action);
            ask_completed  = (AppCompatTextView) itemView.findViewById(R.id.ask_completed);
            raised_on_resolved  = (AppCompatTextView) itemView.findViewById(R.id.raised_on_resolved);
            notes1  = (AppCompatTextView) itemView.findViewById(R.id.notes);

            resolve_ly = (LinearLayout) itemView.findViewById(R.id.resolve_ly);
            raised_on_ly = (LinearLayout) itemView.findViewById(R.id.raised_on_ly);

            Over_all = (LinearLayout) itemView.findViewById(R.id.Over_all);

        }

        public void bindPost(final Ask_Lead_item post) {

         /*   type.setText(a.capitalize(post.getid()+" "+ "( "+post.getusername()+" "+ post.getmobileno()+" )"));
            loantype.setText(post.getloan_typename()); */

            //doc_steps.setText(post.getstep_status());


            String App_id1 = post.getapp_id();
            String name_applicant1 = post.getname();
            String name_callcenter1 = post.getrequest_by();
            String ask_applicant = post.getapplicant();
            String created_at = post.getcreated_at();
            String status_disp = post.getstatus_disp();
            String doc_typename = post.getdoc_typename();
            String notes = post.getnotes();

            String partner_resolved_date_org = post.getpartner_resolved_date_org();
            String close_date = post.getclose_date();

            String input= "sentence";
            String output = input.substring(0, 1).toUpperCase() + name_applicant1.substring(1);
            String output1 = input.substring(0, 1).toUpperCase() + name_callcenter1.substring(1);
            //textview.setText(output);

            App_id.setText(App_id1);
         //   name_applicant.setText("\u20B9"+post.getloan_amount());
            name_applicant.setText(output);
            name_callcenter.setText(output1);
            status_Ask.setText(status_disp);
            ask_crated_at.setText(created_at);
            notes1.setText(notes);
            documnet_name.setText(ask_applicant+","+ " "+ "Need"+ " "+ doc_typename);

            if(status_disp.equals("Accepted by Loanwiser"))
            {
                status_Ask.setTextColor(Color.parseColor("#4CAF50"));
                resolve_ly.setVisibility(View.VISIBLE);
                raised_on_resolved.setText("Accepted on");
                ask_completed.setText(close_date);
                ask_Tack_Action.setVisibility(View.GONE);
                raised_on_ly.setVisibility(View.GONE);
                ask_View_Action.setVisibility(View.VISIBLE);



            }else if(status_disp.equals("Resolved by GSK"))
            {
                status_Ask.setTextColor(Color.parseColor("#FF9800"));
                resolve_ly.setVisibility(View.VISIBLE);
                raised_on_resolved.setText("Resolved on");
                ask_completed.setText(partner_resolved_date_org);
                ask_Tack_Action.setVisibility(View.GONE);
                raised_on_ly.setVisibility(View.GONE);
                ask_View_Action.setVisibility(View.VISIBLE);
            }else
            {
                resolve_ly.setVisibility(View.GONE);
                ask_Tack_Action.setVisibility(View.VISIBLE);
                ask_View_Action.setVisibility(View.GONE);
            }

/*
            if(loantype1.contains("null")) {
                loantype.setVisibility(View.GONE);
            }*/



            ask_Tack_Action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    id1 = post.getiuser_id();
                    String doc_typename = post.getdoc_typename();
                    ask_id = post.getask_id();
                    doc_typeid = post.getdoc_typeid();
                    transaction_id = post.gettransaction_id();
                    doc_classname = post.getdoc_classname();
                    relationship_type = post.getrelationship_type();

                    Objs.ac.StartActivityPutExtra(context, ASK_ManiActivity_Image2.class, Params.doc_typename,doc_typename,
                            Params.docid,doc_typeid,Params.transaction_id,transaction_id,Params.ask_id,ask_id,Params.doc_classname,doc_classname);

                   /* Intent intent = new Intent(context, Ask_user_Dashboard_Activity.class);
                    intent.putExtra("user_id", id1);
                    context.startActivity(intent);*/
                }
            });

            ask_View_Action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    id1 = post.getiuser_id();
                    String doc_typename = post.getdoc_typename();
                    ask_id = post.getask_id();
                    doc_typeid = post.getdoc_typeid1();
                    transaction_id = post.gettransaction_id();
                    doc_classname = post.getdoc_classname();
                    relationship_type = post.getrelationship_type();
                    String doc_classid = post.getdoc_classid();

                    Objs.ac.StartActivityPutExtra(context, Ask_DocGridView_List.class,
                            Params.class_id,doc_classid,
                            Params.user_type,relationship_type,
                            Params.transaction_id,transaction_id,Params.docid1,doc_typeid,
                            Params.doc_typename,doc_typename);

                   /* Intent intent = new Intent(context, Ask_user_Dashboard_Activity.class);
                    intent.putExtra("user_id", id1);
                    context.startActivity(intent);*/
                }
            });

            // Objs.a.OutfitNormalFontStyle(mCon, R.id.doc_typename_all);
            // Objs.a.OutfitNormalFontStyle(mCon, R.id.doc_steps);
        //    a.NewNormalFontStyle(context,type);
         //   a.NewNormalFontStyle(context,doc_steps);
         //   a.NewNormalFontStyle(context,loantype);
          //  a.NewNormalFontStyle(context,assigned);

        }




    }

}
