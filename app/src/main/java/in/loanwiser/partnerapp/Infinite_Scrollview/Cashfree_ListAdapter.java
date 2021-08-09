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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;

public class Cashfree_ListAdapter extends RecyclerView.Adapter<Cashfree_ListAdapter.CustomViewHolder> {

    private Context context;
    private List<CashfreeList_item> posts = new ArrayList<>();
    JSONObject J;
    private AlertDialog progressDialog;
    public Cashfree_ListAdapter(Context context){
        this.context = context;
    }
    String date1,lead_detail1,loan_details1,purpose,coins1;
    String date_disp, user_name, app_id, loan_amount, loan_type,name_of_source,transaction_mode_disp,coins_disp,
     status_disp,current_step,payment_plan;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public void addPosts(List<CashfreeList_item> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ly_credit_coins, parent, false));
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


        AppCompatTextView date,lead_detail,loan_details,purpose,coins;
        ImageView v_Image;
        ProgressBar progressBar;
        AppCompatButton appCompatButtonSelect,add_notes,pipline,archive;
        AppCompatImageView loan_type_image;

        LinearLayout Over_all,ly_question;
        View view;
        String loantype1,statues12,step_status,transaction_id,id,id1;

        public CustomViewHolder(View view) {
            super(view);
            date  = (AppCompatTextView) itemView.findViewById(R.id.date_txt);
            lead_detail  = (AppCompatTextView) itemView.findViewById(R.id.lead_detail_txt);
            loan_details  = (AppCompatTextView) itemView.findViewById(R.id.loan_details_txt);
            purpose  = (AppCompatTextView) itemView.findViewById(R.id.purpose_txt);
            coins  = (AppCompatTextView) itemView.findViewById(R.id.coins_txt);


            Over_all = (LinearLayout) itemView.findViewById(R.id.Over_all);
            progressDialog = new SpotsDialog(context, R.style.Custom);
        }

        @SuppressLint("ResourceAsColor")
        public void bindPost(CashfreeList_item post) {

         /*   type.setText(a.capitalize(post.getid()+" "+ "( "+post.getusername()+" "+ post.getmobileno()+" )"));
            loantype.setText(post.getloan_typename()); */

            //doc_steps.setText(post.getstep_status());

            date_disp = post.getdate_disp();
            user_name = post.getuser_name();
            app_id = post.getapp_id();
            loan_amount = post.getloan_amount();
            loan_type = post.getloan_type();
            name_of_source = post.getname_of_source();
            transaction_mode_disp = post.gettransaction_mode_disp();
            coins_disp = post.getcoins_disp();

            current_step = post.getcurrent_step();
            payment_plan = post.getpayment_plan();

            date.setText(date_disp);
           // lead_detail.setText(user_name + "\n"+ app_id+"" +current_step);
            lead_detail.setText(user_name + "\n"+ app_id);
            loan_details.setText(loan_amount + "\n"+ loan_type);
            purpose.setText(name_of_source + "\n"+ payment_plan);



            Log.e("coins",coins_disp);
            if(transaction_mode_disp.contains("Debit"))
            {
                coins.setText("-"+coins_disp);
                coins.setTextColor(Color.RED);
            }else if(transaction_mode_disp.contains("Credit"))
            {
                coins.setText("+"+coins_disp);
                coins.setTextColor(Color.GREEN);
            }


           /* Over_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                        if(step_status.contains("Rejected"))
                        {
                            Log.e("the lead List","intiated ");
                            Objs.a.showToast(context, "This Lead is Rejected");
                          ///  Applicant_Status(id1,step_status);
                        }
                        else
                        {
                            Log.e("the lead List","intiated ");
                          //  Applicant_Status(id1,step_status);
                           // Applicant_Status(id);
                        }

                }
            });*/

        }

    }

}
