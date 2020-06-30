package in.loanwiser.partnerapp.Infinite_Scrollview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;

public class Payout_ListAdapter extends RecyclerView.Adapter<Payout_ListAdapter.CustomViewHolder> {

    private Context context;
    private List<PayoutList_item> posts = new ArrayList<>();
    JSONObject J;
    private AlertDialog progressDialog;
    public Payout_ListAdapter(Context context){
        this.context = context;
    }
    String date1,lead_detail1,loan_details1,purpose,coins1;
    String date_disp, user_name, app_id, loan_amount, loan_type,name_of_source,transaction_mode_disp,coins_disp,
            commision,current_step,payment_plan;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public void addPosts(List<PayoutList_item> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ly_paypout, parent, false));
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


        AppCompatTextView date,lead_detail,loan_details,amount_id,coins;
        ImageView v_Image;
        ProgressBar progressBar;
        AppCompatButton appCompatButtonSelect,add_notes,pipline,archive;
        AppCompatImageView loan_type_image;

        LinearLayout Over_all,ly_question;
        View view;
        String loantype1,statues12,step_status,transaction_id,id,id1;

        public CustomViewHolder(View view) {
            super(view);
            date  = (AppCompatTextView) itemView.findViewById(R.id.date_id);
            lead_detail  = (AppCompatTextView) itemView.findViewById(R.id.lead_details_id);
            loan_details  = (AppCompatTextView) itemView.findViewById(R.id.loan_details_id);
            amount_id  = (AppCompatTextView) itemView.findViewById(R.id.amount_id);

            Over_all = (LinearLayout) itemView.findViewById(R.id.Over_all);
            progressDialog = new SpotsDialog(context, R.style.Custom);
        }

        @SuppressLint("ResourceAsColor")
        public void bindPost(PayoutList_item post) {

         /*   type.setText(a.capitalize(post.getid()+" "+ "( "+post.getusername()+" "+ post.getmobileno()+" )"));
            loantype.setText(post.getloan_typename()); */

            //doc_steps.setText(post.getstep_status());

            date_disp = post.getdate_disp();
            user_name = post.getuser_name();
            app_id = post.getapp_id();
            loan_amount = post.getloan_amount();
            loan_type = post.getloan_type();
            commision = post.getcommision();

            date.setText(date_disp);
            lead_detail.setText(date_disp+ "\n"+ app_id);
            loan_details.setText(loan_amount+ "\n"+ loan_type);
            amount_id.setText(commision);

            Over_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

    }

}
