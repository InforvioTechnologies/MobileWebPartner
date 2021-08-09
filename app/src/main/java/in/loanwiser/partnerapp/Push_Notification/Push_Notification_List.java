package in.loanwiser.partnerapp.Push_Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import in.loanwiser.partnerapp.Infinite_Scrollview.InfiniteScrollProvider;
import in.loanwiser.partnerapp.Infinite_Scrollview.LeadListAdapter_Dashboard;
import in.loanwiser.partnerapp.Infinite_Scrollview.Lead_item;
import in.loanwiser.partnerapp.Infinite_Scrollview.Notification_Adapter_Dashboard;
import in.loanwiser.partnerapp.Infinite_Scrollview.Notification_item;
import in.loanwiser.partnerapp.Infinite_Scrollview.OnLoadMoreListener;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Lead_Crration_Activity_old;

import static java.sql.Types.TIMESTAMP;

public class Push_Notification_List extends SimpleActivity implements OnLoadMoreListener {

    RecyclerView recyclerView;
    public static final String b2b_user_id1 = "b2b_uer_id";
    String b2b_user_id;
    SharedPreferences pref;

    List<Notification_item> items;
    Notification_Adapter_Dashboard Notification_Adapter;
    private Context context = this;
    private ProgressBar progressBar;
    private int count12 = -1;
    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    String Loan_amount,sub_categoryid,transaction_id1,subtask_id,loan_type_id,loan_type,
            payment,applicant_id1, new_user,last_status;
    LinearLayout Notification_yes,no_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_push__notification__list);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_push__notification__list);
        initTools(R.string.notification);

        pref = getApplication().getSharedPreferences("MyPref", 0);

        items = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Notification_yes = (LinearLayout) findViewById(R.id.Notification_yes);
        no_notification = (LinearLayout) findViewById(R.id.no_notification);


        Account_Listings_Details();
        Notification_Adapter = new Notification_Adapter_Dashboard(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        recyclerView.setAdapter(Notification_Adapter);

        InfiniteScrollProvider infiniteScrollProvider=new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView,this);



    }

    private void Account_Listings_Details() {
        items.clear();

        count12 = count12 +1;
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(mCon));
            J.put("count", count12);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("count12 request", String.valueOf(J));

        if(count12 == 0)
        {
            progressDialog.show();
        }else
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.NOTIFICATION_LIST, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Dashboard response", String.valueOf(response));
                        try {

                            String status = response.getString("status");
                            if(status.contains("success")){
                                JSONArray ja = response.getJSONArray("data");

                                if (ja.length()>0){

                                    Notification_yes.setVisibility(View.VISIBLE);
                                    no_notification.setVisibility(View.GONE);

                                    for(int i = 0;i<ja.length();i++){
                                        JSONObject J = ja.getJSONObject(i);

                                        String id = J.getString("id");
                                        String title = J.getString("title");
                                        String message = J.getString("message");
                                        String created_at = J.getString("created_at");
                                        String user_id = J.getString("user_id");
                                        String btn_invoke = J.getString("btn_invoke");
                                        String status1 = J.getString("status");

                                        String content = J.getString("content");
                                        String app_content = J.getString("app_content");
                                        String imgurl = J.getString("imgurl");
                                        String post_title = J.getString("post_title");



                                        items.add(new Notification_item(title,message,created_at,user_id,btn_invoke,id,status1,
                                                content,app_content,imgurl,post_title));
                                        Notification_Adapter.notifyDataSetChanged();
                                    }
                                    //   Log.e("leadListA", String.valueOf(leadListAdapter_dashboard));
                                    Notification_Adapter.addPosts(items);
                                    // items.clear();
                                    // items = null;


                                }else {

                                    Notification_yes.setVisibility(View.GONE);
                                    no_notification.setVisibility(View.VISIBLE);
                                    Objs.a.ShowHideNoItems(mCon,true);
                                }



                            }else {


                                if(count12 == 0)
                                {
                                    progressDialog.dismiss();
                                }else
                                {
                                    progressBar.setVisibility(View.GONE);
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(count12 == 0)
                        {
                            progressDialog.dismiss();
                        }else
                        {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if(count12 == 0)
                {
                    progressDialog.show();
                }else
                {
                    progressBar.setVisibility(View.GONE);
                }
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        int x=2;// retry count
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                x, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

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
    @Override
    public void onLoadMore() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                items.clear();
                Account_Listings_Details();

            }
        },1500);
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, DashBoard_new.class);
        finish();
        super.onBackPressed();

    }

}