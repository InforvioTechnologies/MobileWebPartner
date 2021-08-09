package in.loanwiser.partnerapp.Infinite_Scrollview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import in.loanwiser.Old_Partner.Home_Old;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.Partner_Statues.Loanwiser_Academy_webinar;
import in.loanwiser.partnerapp.Partner_Statues.ShareActivity;
import in.loanwiser.partnerapp.Push_Notification.Push_Notification_List;
import in.loanwiser.partnerapp.Push_Notification.Timeago;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Lead_Crration_Activity_old;

import static java.sql.Types.TIMESTAMP;

public class Notification_Adapter_Dashboard extends RecyclerView.Adapter<Notification_Adapter_Dashboard.CustomViewHolder> {

    private Context context;
    private List<Notification_item> posts = new ArrayList<>();
    JSONObject J;
    public Notification_Adapter_Dashboard(Context context){
        this.context = context;
    }
    String user_id,btn_invoke,step_status,Notification_id,
            status,content,app_content,imgurl,post_title;
    String Loan_amount,sub_categoryid,transaction_id1,subtask_id,loan_type_id,loan_type,
            payment,applicant_id1, new_user,last_status;
    Timeago timeAgo2 = new Timeago();
    private ProgressBar progressBar;
    private AlertDialog progressDialog;
  //  private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public void addPosts(List<Notification_item> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ly_notification_message, parent, false));
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

        AppCompatTextView title,message,time_ago,font1,font2,loantype,assigned;
        AppCompatTextView Statues_update_dot,
                Lead_Name,loan_amount,app_id,loan_type,payment_plane,step_com,statues_new;
        ImageView v_Image;
        AppCompatImageView  read_msg;
        LinearLayout Over_all;


        public CustomViewHolder(View view) {
            super(view);
            title  = (AppCompatTextView) itemView.findViewById(R.id.title);
            message  = (AppCompatTextView) itemView.findViewById(R.id.message);
            time_ago  = (AppCompatTextView) itemView.findViewById(R.id.time_ago);
            Over_all  = (LinearLayout) itemView.findViewById(R.id.Over_all);
            read_msg  = (AppCompatImageView) itemView.findViewById(R.id.read_msg);
            progressDialog = new SpotsDialog(context, R.style.Custom);
        }

        public void bindPost(Notification_item post) {


            title.setText(post.gettitle());
            message.setText(post.getmessage());

            user_id = post.getuser_id();
            btn_invoke = post.getbtn_invoke();
            status = post.getstatus();
            Log.e("status",status);

            Notification_id = post.getnotification_id();


           // String date = "2011-10-06T12:00:00-08:00";
            String MyFinalValue = timeAgo2.covertTimeToText(post.getcreated_at());
          //  String MyFinalValue = timeAgo2.covertTimeToText(date);
            time_ago.setText(MyFinalValue);
           // long timeInMillis = System.currentTimeMillis();

            if (status.equals("1"))
            {
                read_msg.setImageResource(R.drawable.ic_right_done);
            }else
            {
                read_msg.setImageResource(R.drawable.ic_right_notdone);
            }

            Over_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Notification_id = post.getnotification_id();
                    user_id = post.getuser_id();



                    Log.e("this is working fine","works fine");
                   if(btn_invoke.equals("1"))
                    {
                        Log.e("this is working fine","works fine");

                        Applicant_Status_new(user_id,step_status);

                    }else if( btn_invoke.equals("2"))
                   {
                       content = post.getcontent();
                       app_content = post.getapp_content();
                       imgurl = post.getimgurl();
                       post_title = post.getpost_title();

                       Intent intent=new Intent(context, ShareActivity.class);
                       intent.putExtra("content",content);
                       intent.putExtra("app_content",app_content);
                       intent.putExtra("imgurl",imgurl);
                       intent.putExtra("title",post_title);
                       context.startActivity(intent);
                   } else if( btn_invoke.equals("3"))
                   {
                       Intent intent = new Intent(context, Loanwiser_Academy_webinar.class);
                       context.startActivity(intent);
                   }

                    Applicant_Status();
                }
            });

        }




    }

    public void Applicant_Status() {


        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("notification_id", Notification_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

      //  progressDialog.show();
     //   Log.e("Notification_id", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Notification_Update, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Notification_id", String.valueOf(response));
                        try {

                            String status = response.getString("status");

                            if(status.contains("success"))
                            {
                                if(user_id.equals("0"))
                                {
                                   // Toast.makeText(context,"share this poster with someone...", Toast.LENGTH_SHORT).show();

                                }else
                                {
                                   /* if (context instanceof Push_Notification_List) {
                                        ((Push_Notification_List)context).Applicant_Status(user_id,step_status);
                                    }*/
                                }


                            }else {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                      //  progressDialog.dismiss();
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
    public void Applicant_Status_new(final String id, final String step_status1) {

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


                                new_user =  jsonObject2.getString("new_user");
                                last_status =  jsonObject2.getString("last_status");
                                applicant_id1 =  "APP-"+user_id;
                                // Toast.makeText(getApplicationContext(),new_user, Toast.LENGTH_SHORT).show();

                                // String statues2 = "3";
                                Pref.putUSERID(context,user_id);

                                SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                                prefEditor.putString("user_id", user_id);
                                prefEditor.apply();
                                String _Emp_staus_jsonArray = jsonArray.toString();


                               /* if(new_user.equals("0"))
                                {
                                    Objs.ac.StartActivityPutExtra(context, Home.class,
                                            Params.user_id,user_id,
                                            Params.transaction_id,transaction_id1,
                                            Params.applicant_id,applicant_id1,
                                            Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                            Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);

                                }else
                                {
                                    Objs.ac.StartActivityPutExtra(context, Home.class,
                                            Params.user_id,user_id,
                                            Params.transaction_id,transaction_id1,
                                            Params.applicant_id,applicant_id1,
                                            Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                            Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);
                                }*/

                                if(new_user.equals("0"))
                                {
                                    if(last_status.equals("1")&& payment.equals("error"))
                                    {

                                        Pref.putLoanType(context,loan_type_id);
                                        //String Loantype_name = "Loan Against Property";
                                        Pref.putLoanTypename(context,loan_type);
                                        Pref.putnew_user(context,new_user);
                                        Intent intent=new Intent(context, Lead_Crration_Activity_old.class);
                                        context.startActivity(intent);
                                    }else
                                    {
                                        Objs.ac.StartActivityPutExtra(context, Home_Old.class,
                                                Params.user_id,user_id,
                                                Params.transaction_id,transaction_id1,
                                                Params.applicant_id,applicant_id1,
                                                Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                                Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);

                                    }

                                }else
                                {
                                    Objs.ac.StartActivityPutExtra(context, Home.class,
                                            Params.user_id,user_id,
                                            Params.transaction_id,transaction_id1,
                                            Params.applicant_id,applicant_id1,
                                            Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                            Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);

                                }


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
