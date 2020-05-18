package in.loanwiser.partnerapp.Infinite_Scrollview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;



import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.R;


import static adhoc.app.applibrary.Config.AppUtils.Objs.a;

public class LeadListAdapter_Dashboard extends RecyclerView.Adapter<LeadListAdapter_Dashboard.CustomViewHolder> {

    private Context context;
    private List<Lead_item> posts = new ArrayList<>();
    JSONObject J;
    public LeadListAdapter_Dashboard(Context context){
        this.context = context;
    }

  //  private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public void addPosts(List<Lead_item> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ly_new_lead_status, parent, false));
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

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView type,doc_steps,doc_status,font1,font2,loantype,assigned;
        AppCompatTextView Statues_update_dot,
                Lead_Name,loan_amount,app_id,loan_type,payment_plane,step_com,statues_new;
        ImageView v_Image;
        ProgressBar progressBar;
        AppCompatButton appCompatButtonSelect,add_notes,pipline,archive;
        AppCompatImageView loan_type_image;

        LinearLayout Over_all,ly_question;
        View view;
        String loantype1,statues12,step_status,transaction_id,id;

        public CustomViewHolder(View view) {
            super(view);
            Lead_Name  = (AppCompatTextView) itemView.findViewById(R.id.Lead_Name);
            loan_amount  = (AppCompatTextView) itemView.findViewById(R.id.loan_amount);
            app_id  = (AppCompatTextView) itemView.findViewById(R.id.app_id);
            loan_type  = (AppCompatTextView) itemView.findViewById(R.id.loan_type);
            payment_plane  = (AppCompatTextView) itemView.findViewById(R.id.payment_plane);
            step_com  = (AppCompatTextView) itemView.findViewById(R.id.step_com);
            Statues_update_dot  = (AppCompatTextView) itemView.findViewById(R.id.Statues_update_dot);
            statues_new  = (AppCompatTextView) itemView.findViewById(R.id.statues_new);

            Over_all = (LinearLayout) itemView.findViewById(R.id.Over_all);

        }

        public void bindPost(Lead_item post) {

         /*   type.setText(a.capitalize(post.getid()+" "+ "( "+post.getusername()+" "+ post.getmobileno()+" )"));
            loantype.setText(post.getloan_typename());*/

            //doc_steps.setText(post.getstep_status());

            Lead_Name.setText(post.getusername());
            loan_amount.setText(post.getloan_amount());
            app_id.setText(post.getid());
            loan_type.setText(post.getloan_typename());
            step_com.setText(post.getcomp_step());
            statues_new.setText(post.getstatus_disp());
          //  Statues_update_dot.setText(post.getcolor_code());
          //  payment_plane.setText(post.getloan_typename());

            String color_code = post.getcolor_code();
            // loantype1 = post.getloan_typename();
          //  field_status = post.getfield_status();
            step_status = post.getstep_status();

            step_status = post.getstep_status();
            id = post.getid();
            transaction_id = post.gettransaction_id();

/*
            if(loantype1.contains("null")) {
                loantype.setVisibility(View.GONE);
            }*/


            if(color_code.equals("1"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#FF9200"));
            } else if(color_code.equals("2"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#F9F338"));
            }else if(color_code.equals("3"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#1592E6"));
            }else if(color_code.equals("4"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#15CE00"));
            }else if(color_code.equals("5"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#012B5D"));
            }else
            {
                Statues_update_dot.setTextColor(Color.parseColor("#E3434A"));
            }

            Over_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        if(step_status.contains("Rejected"))
                        {
                            Objs.a.showToast(context, "This Lead is Rejected");
                            if (context instanceof Dashboard_Activity) {
                                ((Dashboard_Activity)context).Applicant_Status(id,step_status);
                            }
                        }
                        else
                        {
                            if (context instanceof Dashboard_Activity) {
                                ((Dashboard_Activity)context).Applicant_Status(id,step_status);
                            }
                           // Applicant_Status(id);
                        }


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
