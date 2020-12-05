package in.loanwiser.partnerapp.Infinite_Scrollview;

import android.content.Context;
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
import in.loanwiser.partnerapp.R;

public class Ask_user_LeadListAdapter_Dashboard extends RecyclerView.Adapter<Ask_user_LeadListAdapter_Dashboard.CustomViewHolder> {

    private Context context;
    private List<Ask_Lead_item> posts = new ArrayList<>();
    JSONObject J;

    private String ask_id;

    private String doc_classname,doc_typeid,transaction_id;

    public Ask_user_LeadListAdapter_Dashboard(Context context){
        this.context = context;
    }

  //  private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public void addPosts(List<Ask_Lead_item> posts) {
        this.posts.clear();
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ly_ask_user_new_lead_status, parent, false));
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

        AppCompatTextView App_id,name_applicant,name_callcenter,status_Ask,ask_crated_at,documnet_name,ask_Tack_Action,
                notes1;

        ImageView v_Image;
        ProgressBar progressBar;
        AppCompatButton add_notes,pipline,archive;
        AppCompatImageView loan_type_image;

        LinearLayout Over_all,ly_question;
        View view;
        String loantype1,statues12,step_status,transaction_id,id,id1;

        public CustomViewHolder(View view) {
            super(view);


            App_id  = (AppCompatTextView) itemView.findViewById(R.id.App_id);
            name_applicant  = (AppCompatTextView) itemView.findViewById(R.id.name_applicant);
            name_callcenter  = (AppCompatTextView) itemView.findViewById(R.id.name_callcenter);
            status_Ask  = (AppCompatTextView) itemView.findViewById(R.id.status_Ask);
            ask_crated_at  = (AppCompatTextView) itemView.findViewById(R.id.ask_crated_at);
            documnet_name  = (AppCompatTextView) itemView.findViewById(R.id.documnet_name);
            ask_Tack_Action  = (AppCompatTextView) itemView.findViewById(R.id.ask_Tack_Action);
            notes1  = (AppCompatTextView) itemView.findViewById(R.id.notes);


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

            App_id.setText(App_id1);
         //   name_applicant.setText("\u20B9"+post.getloan_amount());
            name_applicant.setText(name_applicant1);
            name_callcenter.setText(name_callcenter1);
            status_Ask.setText(status_disp);
            ask_crated_at.setText(created_at);
            notes1.setText(notes);
            documnet_name.setText(ask_applicant+ " "+ "Need"+ " "+ doc_typename);



/*
            if(loantype1.contains("null")) {
                loantype.setVisibility(View.GONE);
            }*/



            Over_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    id1 = post.getiuser_id();
                    String doc_typename = post.getdoc_typename();
                    ask_id = post.getask_id();
                    doc_typeid = post.getdoc_typeid();
                    transaction_id = post.gettransaction_id();
                    doc_classname = post.getdoc_classname();

                    Objs.ac.StartActivityPutExtra(context, ASK_ManiActivity_Image2.class, Params.doc_typename,doc_typename,
                            Params.docid,doc_typeid,Params.transaction_id,transaction_id,Params.ask_id,ask_id,Params.doc_classname,doc_classname);

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
