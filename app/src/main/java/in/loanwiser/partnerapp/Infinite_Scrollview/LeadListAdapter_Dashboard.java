package in.loanwiser.partnerapp.Infinite_Scrollview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
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
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ly_track_status, parent, false));
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
        ImageView v_Image;
        ProgressBar progressBar;
        AppCompatButton appCompatButtonSelect,add_notes,pipline,archive;
        AppCompatImageView loan_type_image;

        LinearLayout Over_all,ly_question;
        View view;
        String loantype1,statues12,step_status,transaction_id,id;

        public CustomViewHolder(View view) {
            super(view);
            type  = (AppCompatTextView) itemView.findViewById(R.id.doc_typename_all);
            doc_steps  = (AppCompatTextView) itemView.findViewById(R.id.doc_steps);
            loantype  = (AppCompatTextView) itemView.findViewById(R.id.loantype);

            loan_type_image  = (AppCompatImageView) itemView.findViewById(R.id.loan_type_image);
            // font1  = (AppCompatTextView) itemView.findViewById(R.id.doc_typename_all);
            //  font2 = (AppCompatTextView) itemView.findViewById(R.id.image_Product);
            // progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);
            // cardView  = (CardView) itemView.findViewById(R.id.card_view);
            Over_all = (LinearLayout) itemView.findViewById(R.id.Over_all);

        }

        public void bindPost(Lead_item post) {

            type.setText(a.capitalize(post.getid()+" "+ "( "+post.getusername()+" "+ post.getmobileno()+" )"));
            loantype.setText(post.getloan_typename());
            doc_steps.setText(post.getstep_status());

             loantype1 = post.getloan_typename();
          //  field_status = post.getfield_status();
            step_status = post.getstep_status();

            step_status = post.getstep_status();
            id = post.getid();
            transaction_id = post.gettransaction_id();


            if(loantype1.contains("null")) {
                loantype.setVisibility(View.GONE);
            }


            if(loantype1.contains("Business Loan [Unsecured]")){
                loan_type_image.setImageResource(R.drawable.business);
            }else if(loantype1.contains("Personal Loan [Unsecured]")) {

                loan_type_image.setImageResource(R.drawable.personal);

            }else if(loantype1.contains("Two Wheeler Loan")) {
                loan_type_image.setImageResource(R.drawable.car1);
            }else if(loantype1.contains("Car Loan")) {
                loan_type_image.setImageResource(R.drawable.car1);
            }else if(loantype1.contains("Commercial Vehicle Loan")) {
                loan_type_image.setImageResource(R.drawable.car1);

            }else if(loantype1.contains("Loan Against Property")) {

                loan_type_image.setImageResource(R.drawable.loanaganst_property);

            }else
            {
                loan_type_image.setImageResource(R.drawable.home21);
            }

            Over_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        if(step_status.contains("Rejected"))
                        {
                            Objs.a.showToast(context, "This Lead is Rejected");

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
            a.NewNormalFontStyle(context,type);
            a.NewNormalFontStyle(context,doc_steps);
            a.NewNormalFontStyle(context,loantype);
          //  a.NewNormalFontStyle(context,assigned);

        }




    }

}