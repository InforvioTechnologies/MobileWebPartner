package in.loanwiser.partnerapp.PartnerActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
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
import in.loanwiser.partnerapp.Infinite_Scrollview.OnLoadMoreListener;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Lead_Crration_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Lead_Crration_Activity_old;
import in.loanwiser.partnerapp.Step_Changes_Screen.Step_Completion_Activity;
import in.loanwiser.partnerapp.User_Account.Welcome_Page;

import static adhoc.app.applibrary.Config.AppUtils.Objs.a;

public class Dashboard_Activity extends AppCompatActivity implements OnLoadMoreListener {

    Toolbar toolbar;
  //  DrawerLayout drawerLayout;
//    View drawerView;
    private Context mCon = this;
    LinearLayout Get_call_back,check_eligibility,quick_apply,chat,contact_person,logout,Ly_profile,Ly_bank;
    private CardView homeloan,loanaganstpropert,personal_loan,Businessloan,vehicle_loan;
    // String selected_loantype;
    Animation animFadein;
    MenuItem chat1;
    private FloatingActionButton float_chat;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Dashboard_Activity.class.getSimpleName();
    private AlertDialog progressDialog;
    String email,username,mobileno,id,step_status,status,loan_type,loan_categoryid,payment;

    String applicant_id,sub_taskid,transaction_id,Mobile,Mobile1,loan_typename,sub_categoryid,
            transaction_id1,subtask_id,applicant_id1,loan_type_id,new_user,last_status;

    AppCompatButton logout1,leads_float_chat;
    AppCompatTextView no_leads_data,txt_bank,txt_profile,txt_get_callback,label_status;
    LinearLayout Ly_no_leads_data;
    String loancatagorey_id;
    String Loan_amount,S_status_id,viability_report_URL;
    private ImageView imageView_profile;
    private int count12 = -1;

    List<Lead_item> items;
    LeadListAdapter_Dashboard leadListAdapter_dashboard;
    RecyclerView recyclerView;
    private ProgressBar progressBar;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    PermissionUtils permissionUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_2);
      //  setContentView(R.layout.activity_simple);
       // Objs.a.setStubId(this,R.layout.activity_dashboard_2);

       // toolbar = (Toolbar) findViewById(R.id.toolbar);

      /*  homeloan = findViewById(R.id.hl);
        loanaganstpropert = findViewById(R.id.lap);
        personal_loan = findViewById(R.id.PL);
        Businessloan = findViewById(R.id.Bl);
        vehicle_loan = findViewById(R.id.vl);*/
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objs.ac.ApplyFont(toolbar, mCon);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     //   Objs.ac.BackButton(mCon, toolbar);

        Mobile = Pref.getUID(mCon);
       // Mobile1 = Pref.getMobile(mCon);
     //   S_status_id =  Objs.a.getBundle(this, Params.status_id);

   //     S_status_id = "0";
        if (Pref.getStatus_id(mCon)==null) {
           // initTools1("List of Leads");
            getSupportActionBar().setTitle("List of Leads");
            S_status_id = "0";
        }else {
            S_status_id =  Pref.getStatus_id(mCon);
            if(S_status_id.equals("0")){
              //  initTools1("List of Leads");
                getSupportActionBar().setTitle("List of Leads");
            }else if(S_status_id.equals("1")){
              //  initTools1("Document Pending");
                getSupportActionBar().setTitle("Pending with you");
            }else if(S_status_id.equals("2")){
              //  initTools1("In Progress");
                getSupportActionBar().setTitle("In Progress");
            }else if(S_status_id.equals("3")){
              //  initTools1( "Declined");
                getSupportActionBar().setTitle( "Declined");
            }else if(S_status_id.equals("4")){
             //   initTools1( "Approved");
                getSupportActionBar().setTitle( "Sanctioned");
            }else if(S_status_id.equals("5")){
             //   initTools1("Disbursed");
                getSupportActionBar().setTitle("Disbursed");
            }else if(S_status_id.equals("6")){
                //   initTools1("Disbursed");
                getSupportActionBar().setTitle("Pending with you");
            }else if(S_status_id.equals("7"))
            {
                    getSupportActionBar().setTitle("Rejected");
            }
        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });




        items = new ArrayList<>();

     //   drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        Get_call_back= (LinearLayout) findViewById(R.id.get_callback);
        check_eligibility= (LinearLayout) findViewById(R.id.check_e);
        quick_apply= (LinearLayout) findViewById(R.id.quick_apply);
        chat= (LinearLayout) findViewById(R.id.Ly_chat);

        no_leads_data= (AppCompatTextView) findViewById(R.id.no_leads_data);
        label_status= (AppCompatTextView) findViewById(R.id.label_status);


        fonts();
        //Applicant Details Activity
        progressDialog = new SpotsDialog(this, R.style.Custom);
        float_chat = (FloatingActionButton)findViewById(R.id.float_chat);
        Ly_no_leads_data = (LinearLayout)findViewById(R.id.Ly_no_leads_data);
        leads_float_chat = (AppCompatButton)findViewById(R.id.leads_float_chat);

        float_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
                finish();
            }
        });
        leads_float_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
                finish();
            }
        });

        Account_Listings_Details(S_status_id);


        leadListAdapter_dashboard = new LeadListAdapter_Dashboard(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        recyclerView.setAdapter(leadListAdapter_dashboard);

        InfiniteScrollProvider infiniteScrollProvider=new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView,this);

        label_status.setVisibility(View.GONE);
       /* if(S_status_id.equals("0")){
            label_status.setText("Total No. of Leads: " + Pref.getStatus_Count(mCon));
        }else {
            label_status.setText("No. of Leads in this Queue: " + Pref.getStatus_Count(mCon));
        }*/

      /*  imageView_profile = (ImageView) findViewById(adhoc.app.applibrary.R.id.imageProfile);
        imageView_profile.setImageDrawable(mCon.getResources().getDrawable(adhoc.app.applibrary.R.drawable.default_placeholder));
        String url ="http://cscapi.propwiser.com/mobile/images/loanwiser-app-logo.png";
        Objs.a.loadPicasso(mCon,url, imageView_profile,(ProgressBar)findViewById(adhoc.app.applibrary.R.id.progressBarMaterial));
        final AppCompatTextView tvUserName = (AppCompatTextView)findViewById(R.id.tvUserName);
        final AppCompatTextView tvUserEmail = (AppCompatTextView) findViewById(adhoc.app.applibrary.R.id.tvUserEmail);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(),  "Lato-Regular.ttf");
        tvUserName.setText(mCon.getResources().getString(R.string.app_name));
        tvUserEmail.setText(mCon.getResources().getString(R.string.app_web));
        tvUserEmail.setTypeface(font);
        tvUserName.setTypeface(font);*/

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat, menu);
        chat1 = menu.findItem(R.id.action_chat);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_chat) {
            Objs.ac.StartActivity(mCon, Chat.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fonts() {

        a.NormalFontStyle(mCon, R.id.label_status);
        a.NormalFontStyle(mCon, R.id.no_leads_data);
    }

    public void ExitAlert(Context context) {
        androidx.appcompat.app.AlertDialog.Builder builder = new  androidx.appcompat.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setMessage("Do you want to Logout..?");
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Pref.removeLogin(mCon);
                Pref.removeID(mCon);
                Pref.removeMOB(mCon);
                Pref.removeMobile(mCon);
                Intent i = new Intent(Dashboard_Activity.this, Welcome_Page.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                finish();
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        a.DialogStyle(context, alert);
    }

    private void Account_Listings_Details(String id) {
        items.clear();

        count12 = count12 +1;
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(mCon));
            J.put(Params.status_id, id);
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

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LEAD_LIST_POST, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Dashboard response", String.valueOf(response));
                        try {
                            if(response.getBoolean(Params.status)){
                                JSONArray ja = response.getJSONArray(Params.applicationusers_arr);

                                if (ja.length()>0){

                                    for(int i = 0;i<ja.length();i++){
                                        JSONObject J = ja.getJSONObject(i);

                                        String id = J.getString("id");
                                        String from_cobrand = J.getString("from_cobrand");
                                        String cobrand_mobile = J.getString("cobrand_mobile");
                                        String loan_type = J.getString("loan_type");
                                        String loan_typename = J.getString("loan_typename");
                                        String step_status = J.getString("step_status");
                                        String username = J.getString("username");
                                        String mobileno = J.getString("mobileno");
                                        String transaction_id = J.getString("transaction_id");
                                        String created_at = J.getString("created_at");

                                        String loan_amount = J.getString("loan_amount");
                                        String comp_step = J.getString("comp_step");
                                        String status_disp = J.getString("status_disp");
                                        String color_code = J.getString("color_code");
                                        String payment_plan = J.getString("payment_plan");
                                        String pending_asks_count = J.getString("pending_asks_count");
                                        applicant_id =   J.getString(Params.applicant_id);
                                      //  String id1 =   J.getString("id");
                                       // String field_status = J.getString("field_status");

                                       // Log.e("mobile no",mobileno);

                                        items.add(new Lead_item(applicant_id,loan_typename, step_status,username,
                                                mobileno,transaction_id,loan_amount,comp_step,status_disp,color_code,payment_plan,id,pending_asks_count,from_cobrand,
                                                loan_type,cobrand_mobile,created_at));
                                        leadListAdapter_dashboard.notifyDataSetChanged();
                                    }
                                 //   Log.e("leadListA", String.valueOf(leadListAdapter_dashboard));
                                    leadListAdapter_dashboard.addPosts(items);
                                   // items.clear();
                                   // items = null;


                                }else {
                                    Objs.a.ShowHideNoItems(mCon,true);
                                }

                               /* if (ja.length()>0){
                                   // Log.e("Length", String.valueOf(ja.length()));
                                    Ly_no_leads_data.setVisibility(View.GONE);
                                    float_chat.setVisibility(View.VISIBLE);
                                    label_status.setVisibility(View.VISIBLE);
                                    setAdapter(ja);

                                        if (Pref.getStatus_Count(mCon) != null && !Pref.getStatus_Count(mCon).isEmpty() && !Pref.getStatus_Count(mCon).equals("null")){

                                            label_status.setText("No. of Leads in this Queue: " + Pref.getStatus_Count(mCon));
                                         }else {
                                            label_status.setText("Total No. of Leads: " + String.valueOf(ja.length()));
                                        }

                                }*/


                            }else {

                                label_status.setVisibility(View.GONE);
                                float_chat.setVisibility(View.VISIBLE);
                                if(count12 == 0)
                                {
                                    float_chat.setVisibility(View.GONE);
                                    Ly_no_leads_data.setVisibility(View.VISIBLE);
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



   /* private void setAdapter(JSONArray ja) {
        Dashboard_Activity.ListItemAdapter adapter = new Dashboard_Activity.ListItemAdapter(mCon,ja);
        a.getRecyleview(this).setAdapter(adapter);
    }*/

    @Override
    public void onLoadMore() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                items.clear();
                Account_Listings_Details(S_status_id);

            }
        },1500);
    }


    public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {

        JSONArray list = new JSONArray();
        Context mCon;
        JSONObject J;

        public ListItemAdapter(Context mCon, JSONArray list) {
            this.list = list;
            this.mCon = mCon;
        }

        @Override
        public int getItemCount() {
            return list.length();
        }

        public JSONObject getItem(int i) {
            try {
                return list.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public Dashboard_Activity.ListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_new_lead_status, parent, false);
            return new Dashboard_Activity.ListItemAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final Dashboard_Activity.ListItemAdapter.ViewHolder holder, final int position) {
            try {

                J = getItem(position);
                mobileno =   J.getString(Params.mobileno);
                username =   J.getString(Params.username);
                applicant_id =   J.getString(Params.applicant_id);
                status =   J.getString(Params.status);
                step_status =   J.getString(Params.step_status);
                loan_categoryid =   J.getString("loan_categoryid");
                loan_type =   J.getString("loan_type");
                loan_typename =   J.getString("loan_typename");

                loan_typename =   J.getString("loan_typename");
                String  loan_amount =   J.getString("loan_amount");
                String  step_com =   J.getString("comp_step");
                String statues_new =   J.getString("status_disp");
                String color_code =   J.getString("color_code");


                // Get_Loan_Type(loan_categoryid,loan_type);

                if(J.getString("loan_typename").contains("null")) {
                    holder.loantype.setVisibility(View.GONE);
                }

              /*  holder.type.setText(  applicant_id +" "+ "( "+username+" "+ mobileno+" )");
                //  holder.doc_status.setText(status);
                holder.doc_steps.setText(step_status);
                holder.loantype.setText(loan_typename);*/

                holder.Lead_Name.setText(username);
                holder.loan_amount.setText(loan_amount);
                holder.app_id.setText(applicant_id);
                holder.loan_type.setText(loan_typename);
                holder.step_com.setText(step_com);
                holder.statues_new.setText(statues_new);

                if(color_code.equals("1"))
                {
                    holder.Statues_update_dot.setTextColor(Color.parseColor("#FF9200"));
                } else if(color_code.equals("2"))
                {
                    holder.Statues_update_dot.setTextColor(Color.parseColor("#F9F338"));
                }else if(color_code.equals("3"))
                {
                    holder.Statues_update_dot.setTextColor(Color.parseColor("#1592E6"));
                }else if(color_code.equals("4"))
                {
                    holder.Statues_update_dot.setTextColor(Color.parseColor("#15CE00"));
                }else if(color_code.equals("5"))
                {
                    holder.Statues_update_dot.setTextColor(Color.parseColor("#012B5D"));
                }else
                {
                    holder.Statues_update_dot.setTextColor(Color.parseColor("#E3434A"));
                }

               // holder.payment_plane.setText(loan_typename);

                // Objs.a.OutfitNormalFontStyle(mCon, R.id.doc_typename_all);
                // Objs.a.OutfitNormalFontStyle(mCon, R.id.doc_steps);

                //  Objs.a.NormalFontStyle(mCon,holder.doc_steps);

                holder.Over_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);
                        try {

                            email = J.getString(Params.email);
                            username =   J.getString(Params.username);
                            mobileno =   J.getString(Params.mobileno);
                            id =   J.getString(Params.id);
                            applicant_id =   J.getString(Params.applicant_id);
                            transaction_id =   J.getString(Params.transaction_id);
                            sub_taskid =   J.getString(Params.sub_taskid);
                            loancatagorey_id =   J.getString("loan_categoryid");
                            step_status =   J.getString(Params.step_status);

                            // String all = id +"\n"+ applicant_id +"\n"+sub_taskid+"\n"+transaction_id +"\n"+ Pref.getID(mCon);
                            // Objs.a.showToast(mCon,all);
                            //  Objs.a.showToast(mCon, id);


                            if(step_status.contains("Rejected"))
                            {
                                Objs.a.showToast(mCon, "This Lead is Rejected");
                            }
                            else
                            {
                                Applicant_Status(id,step_status);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            } catch (NullPointerException e) {
                a.showToast(mCon, e.toString());
            } catch (Exception e) {
                a.showToast(mCon, e.toString());
            }
        }

        private void update(JSONArray list1) {
            list = list1;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            AppCompatTextView type,doc_steps,doc_status,font1,font2,loantype,Statues_update_dot,
                    Lead_Name,loan_amount,app_id,loan_type,payment_plane,step_com,statues_new;
            ImageView v_Image;
            ProgressBar progressBar;
            AppCompatButton appCompatButtonSelect,go_leads;
            AppCompatImageView loan_type_image;
            CardView cardView;
            LinearLayout Over_all;
            View view;

            public ViewHolder(View itemView) {
                super(itemView);

                Lead_Name  = (AppCompatTextView) itemView.findViewById(R.id.Lead_Name);
                loan_amount  = (AppCompatTextView) itemView.findViewById(R.id.loan_amount);
                app_id  = (AppCompatTextView) itemView.findViewById(R.id.app_id);
                loan_type  = (AppCompatTextView) itemView.findViewById(R.id.loan_type);
                payment_plane  = (AppCompatTextView) itemView.findViewById(R.id.payment_plane);
                step_com  = (AppCompatTextView) itemView.findViewById(R.id.step_com);
                Statues_update_dot  = (AppCompatTextView) itemView.findViewById(R.id.Statues_update_dot);
                statues_new  = (AppCompatTextView) itemView.findViewById(R.id.statues_new);

              //  go_leads  = (AppCompatButton) itemView.findViewById(R.id.go_leads);
                // font1  = (AppCompatTextView) itemView.findViewById(R.id.doc_typename_all);
                //  font2 = (AppCompatTextView) itemView.findViewById(R.id.image_Product);
                // progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);
                // cardView  = (CardView) itemView.findViewById(R.id.card_view);
                Over_all = (LinearLayout) itemView.findViewById(R.id.Over_all);

            }
        }
    }

    private void Report_View_Fu(String user_id,String transaction_id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("trans_id", transaction_id);
            J.put("user_id", user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Report Request ",String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Report_Activity, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.e("Report response",String.valueOf(response));
                            String report_statues = response.getString("status");
                            if(report_statues.equals("success"))
                            {
                                viability_report_URL = response.getString("viability_report");

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
                                new_user =  jsonObject2.getString("new_user");
                                last_status =  jsonObject2.getString("last_status");
                                applicant_id1 =  "APP-"+user_id;
                                Toast.makeText(getApplicationContext(),new_user, Toast.LENGTH_SHORT).show();

                                // String statues2 = "3";
                                Pref.putUSERID(mCon,user_id);

                                SharedPreferences.Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                                prefEditor.putString("user_id", user_id);
                                prefEditor.apply();
                                String _Emp_staus_jsonArray = jsonArray.toString();


                                if(new_user.equals("0"))
                                {
                                    if(last_status.equals("1")&& payment.equals("error"))
                                    {

                                        Pref.putLoanType(mCon,loan_type_id);
                                        //String Loantype_name = "Loan Against Property";
                                        Pref.putLoanTypename(mCon,loan_type);
                                        Pref.putnew_user(mCon,new_user);
                                        Intent intent=new Intent(Dashboard_Activity.this, Lead_Crration_Activity_old.class);
                                        startActivity(intent);
                                    }else
                                    {
                                        Objs.ac.StartActivityPutExtra(mCon, Home_Old.class,
                                                Params.user_id,user_id,
                                                Params.transaction_id,transaction_id1,
                                                Params.applicant_id,applicant_id1,
                                                Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                                Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);
                                        finish();
                                    }

                                }else
                                {
                                    Objs.ac.StartActivityPutExtra(mCon, Home.class,
                                            Params.user_id,user_id,
                                            Params.transaction_id,transaction_id1,
                                            Params.applicant_id,applicant_id1,
                                            Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                            Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);
                                    finish();
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
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void Get_Loan_Type(final String loan_categoryid,final String loan_type) {

        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("category_id", loan_categoryid);
            Log.d("loan_categoryid", loan_categoryid);
            Log.d("loan_type_id", loan_type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  progressDialog.show();
        // Toast.makeText(mCon,"Its running",Toast.LENGTH_SHORT).show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LOAN_TYPE_POST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.d("Applicant Info", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        try {
                            JSONArray ja = object.getJSONArray(Params.loantypelist_arr);
                            Log.d("Applicant Info", String.valueOf(ja));
                            for (int i=0;i<ja.length();i++){

                                JSONObject object1 = ja.getJSONObject(i);

                                String id_type1 = object1.getString(Params.id);
                                String loan_type1 = object1.getString(Params.loan_type);
                                // Log.d("loan_categoryid112", id_type1);
                                // Log.d("loan_categoryid113", loan_type);
                                if(id_type1.equals(loan_type)){
                                    // Objs.a.showToast(mCom, loan_type);
                                    String selected_loantype = loan_type1;
                                    Log.d("loan_categoryid111", selected_loantype);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    @Override
    public void onBackPressed() {
        Objs.ac.StartActivity(mCon, DashBoard_new.class);
        Pref.removeStatus_id(mCon);
        Pref.removeStatus_Count(mCon);
        finish();
        super.onBackPressed();

    }
}
