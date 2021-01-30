package in.loanwiser.partnerapp.Infinite_Scrollview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
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
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;

public class LeadListAdapter_Dashboard1 extends RecyclerView.Adapter<LeadListAdapter_Dashboard1.CustomViewHolder> {

    private Context context;
    private List<Lead_item> posts = new ArrayList<>();
    JSONObject J;
    private AlertDialog progressDialog;
    public LeadListAdapter_Dashboard1(Context context){
        this.context = context;
    }
    String Loan_amount,sub_categoryid,transaction_id1,subtask_id,loan_type_id,loan_type,
            payment,applicant_id1;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
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
                Lead_Name,loan_amount,app_id,loan_type,payment_plane,step_com,statues_new,
                status_Ask,Statues_update_view,Statues_update_view1;
        ImageView v_Image;
        ProgressBar progressBar;
        AppCompatButton appCompatButtonSelect,add_notes,pipline,archive;
        AppCompatImageView loan_type_image;

        LinearLayout Over_all,ly_question;
        View view;
        String loantype1,statues12,step_status,transaction_id,id,id1;

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
            status_Ask  = (AppCompatTextView) itemView.findViewById(R.id.status_Ask);
            Over_all = (LinearLayout) itemView.findViewById(R.id.Over_all);
            Statues_update_view  = (AppCompatTextView) itemView.findViewById(R.id.Statues_update_view);
            Statues_update_view1  = (AppCompatTextView) itemView.findViewById(R.id.Statues_update_view1);
            progressDialog = new SpotsDialog(context, R.style.Custom);
        }

        public void bindPost(Lead_item post) {

         /*   type.setText(a.capitalize(post.getid()+" "+ "( "+post.getusername()+" "+ post.getmobileno()+" )"));
            loantype.setText(post.getloan_typename()); */

            //doc_steps.setText(post.getstep_status());
            String statues = post.getstatus_disp();
            Lead_Name.setText(post.getusername());
            loan_amount.setText("\u20B9"+post.getloan_amount());
            app_id.setText(post.getid());
            loan_type.setText(post.getloan_typename());
            step_com.setText(post.getcomp_step());
            statues_new.setText(post.getstatus_disp());
            payment_plane.setText(post.getpayment_plan());
            String  pending_asks_count = post.getpending_asks_count();
          //
            //  Statues_update_dot.setText(post.getcolor_code());
          //  payment_plane.setText(post.getloan_typename());

            String color_code = post.getcolor_code();
            // loantype1 = post.getloan_typename();
          //  field_status = post.getfield_status();
            step_status = post.getstep_status();

            step_status = post.getstep_status();
            id = post.getid();
            id1 = post.getid1();
            transaction_id = post.gettransaction_id();
            status_Ask.setText("Pending Ask"+"("+pending_asks_count+")");

            if(statues.equals("Pending under you"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#FF9200"));
                statues_new.setTextColor(Color.parseColor("#FF9200"));

                Statues_update_view.setVisibility(View.VISIBLE);
                Statues_update_view.setText("Complete Now");
                Statues_update_view1.setVisibility(View.GONE);

            } else if(statues.equals("Submitted to Loanwiser"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#F9F338"));
                statues_new.setTextColor(Color.parseColor("#F9F338"));
                Statues_update_view.setVisibility(View.GONE);
                Statues_update_view1.setText("View");
                Statues_update_view1.setVisibility(View.VISIBLE);

            }else if(statues.equals("Sanctioned"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#1592E6"));
                statues_new.setTextColor(Color.parseColor("#1592E6"));
                Statues_update_view.setVisibility(View.GONE);
                Statues_update_view1.setText("View");
                Statues_update_view1.setVisibility(View.VISIBLE);
            }else if(statues.equals("Disbursed"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#15CE00"));
                statues_new.setTextColor(Color.parseColor("#15CE00"));
                Statues_update_view.setVisibility(View.GONE);
                Statues_update_view1.setText("View");
                Statues_update_view1.setVisibility(View.VISIBLE);

            }else if(statues.equals("Sent to bank"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#012B5D"));
                statues_new.setTextColor(Color.parseColor("#012B5D"));
                Statues_update_view.setVisibility(View.GONE);
                Statues_update_view1.setText("View");
                Statues_update_view1.setVisibility(View.VISIBLE);

            }else
            {
                Statues_update_dot.setTextColor(Color.parseColor("#E3434A"));
                statues_new.setTextColor(Color.parseColor("#E3434A"));
                Statues_update_view.setVisibility(View.GONE);
                Statues_update_view1.setText("View");
                Statues_update_view1.setVisibility(View.VISIBLE);
            }

            Over_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                        if(step_status.contains("Rejected"))
                        {
                            Log.e("the lead List","intiated ");
                            Objs.a.showToast(context, "This Lead is Rejected");
                            Applicant_Status(id1,step_status);
                        }
                        else
                        {
                            Log.e("the lead List","intiated ");
                            Applicant_Status(id1,step_status);
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


    public void Applicant_Status(final String id, final String step_status1) {

        final String step_status11 = step_status1;
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.user_id, id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("Applicant Entry request", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PARTNER_STATUES_IDs, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant Entry", String.valueOf(response));
                        JSONObject jsonObject1 = new JSONObject();

                        try {
                            String statues = response.getString("status");

                            if(statues.contains("success"))
                            {
                                JSONObject jsonObject2 = response.getJSONObject("reponse");

                                JSONArray jsonArray = jsonObject2.getJSONArray("emp_states");

                                String user_id = jsonObject2.getString("user_id");
                                Loan_amount = jsonObject2.getString("loan_amount");
                                sub_categoryid =   jsonObject2.getString("sub_categoryid");
                                transaction_id1 =  jsonObject2.getString("transaction_id");
                                subtask_id =  jsonObject2.getString("subtask_id");
                                loan_type_id =  jsonObject2.getString("loan_type_id");
                                loan_type =  jsonObject2.getString("loan_type");
                                payment =  jsonObject2.getString("payment");
                                applicant_id1 =  "APP-"+user_id;


                                // String statues2 = "3";
                                Pref.putUSERID(context,user_id);
                                String _Emp_staus_jsonArray = jsonArray.toString();

                                SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                                prefEditor.putString("user_id", user_id);
                                prefEditor.apply();

                                Objs.ac.StartActivityPutExtra(context, Home.class,
                                        Params.user_id,user_id,
                                        Params.transaction_id,transaction_id1,
                                        Params.applicant_id,applicant_id1,
                                        Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                        Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);


                              /*  if(payment.equals("error"))
                                {
                                    Intent intent = new Intent(Dashboard_Activity.this, Payment_Details_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }else
                                {
                                    Log.d("applicant_id1",loan_type);
                                    Objs.ac.StartActivityPutExtra(mCon, Home.class,
                                            Params.user_id,user_id,
                                            Params.transaction_id,transaction_id1,
                                            Params.applicant_id,applicant_id1,
                                            Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                            Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);
                                    finish();

                                }*/


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Applicant Entry request", String.valueOf(error));
                Toast.makeText(context,error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
