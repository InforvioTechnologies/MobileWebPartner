package in.loanwiser.partnerapp.PartnerActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Lead_Website.MainActivity_Add_Lead_Website;
import in.loanwiser.partnerapp.Partner_Statues.Statues_Dashboard_Nav;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.User_Account.Welcome_Page;

import static adhoc.app.applibrary.Config.AppUtils.Objs.a;

public class Dashboard_Activity extends AppCompatActivity {

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
    String email,username,mobileno,id,step_status,status,loan_type,loan_categoryid;
    String applicant_id,sub_taskid,transaction_id,Mobile,Mobile1,loan_typename,sub_categoryid;
    AppCompatButton logout1,leads_float_chat;
    AppCompatTextView no_leads_data,txt_bank,txt_profile,txt_get_callback,label_status;
    LinearLayout Ly_no_leads_data;
    String loancatagorey_id;
    String Loan_amount,S_status_id;
    private ImageView imageView_profile;

    /*@Override
    protected void onStart() {
        super.onStart();
        Account_Listings_Details();
    }*/

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
                getSupportActionBar().setTitle("Document Pending");
            }else if(S_status_id.equals("2")){
              //  initTools1("In Progress");
                getSupportActionBar().setTitle("In Progress");
            }else if(S_status_id.equals("3")){
              //  initTools1( "Declined");
                getSupportActionBar().setTitle( "Declined");
            }else if(S_status_id.equals("4")){
             //   initTools1( "Approved");
                getSupportActionBar().setTitle( "Approved");
            }else if(S_status_id.equals("5")){
             //   initTools1("Disbursed");
                getSupportActionBar().setTitle("Disbursed");
            }else if(S_status_id.equals("6")){
                //   initTools1("Disbursed");
                getSupportActionBar().setTitle("Closed");
            }
        }


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        //    setSupportActionBar(toolbar);
    //    toolbar.setNavigationIcon(R.drawable.ic_hamburger);
     //   Objs.ac.ApplyFont(toolbar, mCon);

      /*  int a = Integer.parseInt(S_status_id);
        switch(a) {
            case 0:
                toolbar.setTitle(R.string.list_lead);
                break;
            case 1:
                toolbar.setTitle(R.string.list_lead + "Document Pending");
                break;
            case 2:
                toolbar.setTitle(R.string.list_lead + "In Progress");
                break;
            case 3:
                toolbar.setTitle(R.string.list_lead + "Declined");
                break;
            case 4:
                toolbar.setTitle(R.string.list_lead + "Approved");
                break;
            case 5:
                toolbar.setTitle(R.string.list_lead + "Disbursed");
                break;
            default:
                return;
        }*/


     //   drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        Get_call_back= (LinearLayout) findViewById(R.id.get_callback);
        check_eligibility= (LinearLayout) findViewById(R.id.check_e);
        quick_apply= (LinearLayout) findViewById(R.id.quick_apply);
        chat= (LinearLayout) findViewById(R.id.Ly_chat);
      //  contact_person= (LinearLayout) findViewById(R.id.Ly_contact);
     //   Ly_bank= (LinearLayout) findViewById(R.id.Ly_bank);
     //   Ly_profile= (LinearLayout) findViewById(R.id.Ly_profile);
     //   logout= (LinearLayout) findViewById(R.id.logout1);
        no_leads_data= (AppCompatTextView) findViewById(R.id.no_leads_data);
        label_status= (AppCompatTextView) findViewById(R.id.label_status);
      //  txt_get_callback= (AppCompatTextView) findViewById(R.id.txt_get_callback);
     //   txt_bank= (AppCompatTextView) findViewById(R.id.txt_bank);
     //   txt_profile= (AppCompatTextView) findViewById(R.id.txt_profile);


/*
        drawerView = (View)findViewById(R.id.drawer);


        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                drawerLayout.openDrawer(drawerView);
            }});
        drawerLayout.setDrawerListener(myDrawerListener);


        Get_call_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
            }
        });

        contact_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.closeDrawer(drawerView);
                Objs.ac.StartActivity(mCon, CustomerCare.class);

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
                ExitAlert(mCon);
            }
        });


          Ly_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
                Objs.ac.StartActivity(mCon, ProfileSettings.class);
               // finish();
            }
        });

        Ly_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
                Objs.ac.StartActivity(mCon, BankDetails.class);
               // finish();

            }
        });
*/


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

     //   Objs.a.NormalFontStyle(mCon, R.id.txt_bank);
      //  Objs.a.NormalFontStyle(mCon, R.id.txt_get_callback);
     //   Objs.a.NormalFontStyle(mCon, R.id.txt_profile);
      //  Objs.a.NormalFontStyle(mCon, R.id.help_and_support);
      //  Objs.a.NormalFontStyle(mCon, R.id.logout2);
        a.NormalFontStyle(mCon, R.id.label_status);
        a.NormalFontStyle(mCon, R.id.no_leads_data);
    }

    public void ExitAlert(Context context) {
        android.support.v7.app.AlertDialog.Builder builder = new  android.support.v7.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
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
        android.support.v7.app.AlertDialog alert = builder.create();
        alert.show();
        a.DialogStyle(context, alert);
    }


//    DrawerLayout.DrawerListener myDrawerListener = new DrawerLayout.DrawerListener(){
//
//        @Override
//        public void onDrawerClosed(View drawerView) {
//        }
//
//        @Override
//        public void onDrawerOpened(View drawerView) {
//        }
//
//        @Override
//        public void onDrawerSlide(View drawerView, float slideOffset) {
//        }
//
//        @Override
//        public void onDrawerStateChanged(int newState) {
//            String state;
//            switch(newState){
//                case DrawerLayout.STATE_IDLE:
//                    state = "STATE_IDLE";
//                    break;
//                case DrawerLayout.STATE_DRAGGING:
//                    state = "STATE_DRAGGING";
//                    break;
//                case DrawerLayout.STATE_SETTLING:
//                    state = "STATE_SETTLING";
//                    break;
//                default:
//                    state = "unknown!";
//            }
//        }};
//

    private void Account_Listings_Details(String id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
           J.put(Params.b2b_userid, Pref.getID(mCon));
          //  J.put(Params.b2b_userid, "20407");
            J.put(Params.status_id, id);
        //    Log.d("response b2b_userid", String.valueOf(J));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

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

                                }
                            }else {
                                label_status.setVisibility(View.GONE);
                                float_chat.setVisibility(View.GONE);
                                Ly_no_leads_data.setVisibility(View.VISIBLE);

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

    private void setAdapter(JSONArray ja) {
        Dashboard_Activity.ListItemAdapter adapter = new Dashboard_Activity.ListItemAdapter(mCon,ja);
        a.getRecyleview(this).setAdapter(adapter);
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
                    .inflate(R.layout.ly_track_status, parent, false);
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

                // Get_Loan_Type(loan_categoryid,loan_type);

                if(J.getString("loan_typename").contains("null")) {
                    holder.loantype.setVisibility(View.GONE);
                }

                holder.type.setText(  applicant_id +" "+ "( "+username+" "+ mobileno+" )");
                //  holder.doc_status.setText(status);
                holder.doc_steps.setText(step_status);
                holder.loantype.setText(loan_typename);


                if(J.getString("loan_typename").contains("Business Loan [Unsecured]")){
                    holder.loan_type_image.setImageDrawable(getResources().getDrawable(R.drawable.business));
                }else if(J.getString("loan_typename").contains("Personal Loan [Unsecured]")) {
                    holder.loan_type_image.setImageDrawable(getResources().getDrawable(R.drawable.personal));
                }else if(J.getString("loan_typename").contains("Two Wheeler Loan")) {
                    holder.loan_type_image.setImageDrawable(getResources().getDrawable(R.drawable.car1));
                }else if(J.getString("loan_typename").contains("Car Loan")) {
                    holder.loan_type_image.setImageDrawable(getResources().getDrawable(R.drawable.car1));
                }else if(J.getString("loan_typename").contains("Commercial Vehicle Loan")) {
                    holder.loan_type_image.setImageDrawable(getResources().getDrawable(R.drawable.car1));
                }else if(J.getString("loan_typename").contains("Loan Against Property")) {
                    holder.loan_type_image.setImageDrawable(getResources().getDrawable(R.drawable.loanaganst_property));
                }else
                {
                    holder.loan_type_image.setImageDrawable(getResources().getDrawable(R.drawable.home21));
                }
                // Objs.a.OutfitNormalFontStyle(mCon, R.id.doc_typename_all);
                // Objs.a.OutfitNormalFontStyle(mCon, R.id.doc_steps);
                a.NewNormalFontStyle(mCon,holder.type);
                a.NewNormalFontStyle(mCon,holder.doc_steps);
                a.NewNormalFontStyle(mCon,holder.loantype);
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
                                Applicant_Status(id);
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

            AppCompatTextView type,doc_steps,doc_status,font1,font2,loantype;
            ImageView v_Image;
            ProgressBar progressBar;
            AppCompatButton appCompatButtonSelect;
            AppCompatImageView loan_type_image;
            CardView cardView;
            LinearLayout Over_all;
            View view;

            public ViewHolder(View itemView) {
                super(itemView);

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
        }
    }

    private void Applicant_Status(final String id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.user_id, id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.APP_ID_OTP_POST, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant Entry", String.valueOf(response));
                        JSONObject jsonObject1 = new JSONObject();

                       /* try {
                            if(response.getBoolean(Params.status)){
                                Objs.ac.StartActivityPutExtra(mCon, Home.class,
                                        Params.mobileno,mobileno,
                                        Params.user_id,id,
                                        Params.transaction_id,transaction_id,
                                        Params.username,username,
                                        Params.email,email);
                            }else if(0){
                                // Objs.a.showToast(mCon, "Update Your Applicant Details");
                                Objs.a.showToast(mCon, "Please Contact Call center to Update the Details");
                               *//* Objs.ac.StartActivityPutExtra(mCon, Applicant_Entry.class,
                                        Params.user_id,id,
                                        Params.applicant_id,applicant_id,
                                        Params.transaction_id,transaction_id,
                                        Params.sub_taskid,sub_taskid);
                                finish();*//*
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/

                        try {
                            String statues = response.getString("status");
                            String statues2 = response.getString("loan_categoryid");
                             Loan_amount = response.getString("loan_amount");
                             sub_categoryid =   response.getString("sub_categoryid");
                           // username =   response.getString("username");

                           // String statues2 = "3";
                            Log.d("loan_categoryid",statues2);
                            if (statues == "true")
                            {
                                Log.d(" applicant Entry true", step_status);
                                Objs.ac.StartActivityPutExtra(mCon, Home.class,
                                        Params.user_id,id,
                                        Params.step_status,step_status,
                                        Params.transaction_id,transaction_id,
                                        Params.applicant_id,applicant_id,
                                        Params.sub_taskid,sub_taskid);
                                        finish();
                            }
                            else
                            {
                                int a = Integer.parseInt(statues2);
                                Log.d("loan_categoryid123", String.valueOf(a));
                                switch(a) {
                                    case 1:

                                        if(sub_categoryid.equals("0")){

                                            String all = id + "," + applicant_id+ "," +transaction_id + ","
                                                    + sub_categoryid + "," + sub_taskid + "," + username
                                                    + "," +mobileno ;
                                            Pref.putALL(mCon, all);


                                            Objs.ac.StartActivity(mCon, MainActivity_Add_Lead_Website.class);



                                        }else {
                                            Log.d(" applicant Entry 1", statues);
                                            Objs.ac.StartActivityPutExtra(mCon, Applicant_Entry.class,
                                                    Params.user_id, id,
                                                    Params.applicant_id, applicant_id,
                                                    Params.transaction_id, transaction_id,
                                                    Params.loan_amount, Loan_amount,
                                                    Params.sub_categoryid, sub_categoryid,
                                                    Params.sub_taskid, sub_taskid);
                                            finish();
                                        }
                                        break;
                                    case 2:
                                        Log.d(" applicant Entry 2", statues);
                                        Objs.ac.StartActivityPutExtra(mCon, Applicant_Entry1.class,
                                                Params.user_id,id,
                                                Params.applicant_id,applicant_id,
                                                Params.transaction_id,transaction_id,
                                                Params.loan_amount,Loan_amount,
                                                Params.sub_categoryid,sub_categoryid,
                                                Params.sub_taskid,sub_taskid);
                                        finish();
                                        break;
                                    case 3:
                                        Log.d(" applicant Entry 3", statues);
                                        Objs.ac.StartActivityPutExtra(mCon, Applicant_Entry2.class,
                                                Params.user_id,id,
                                                Params.applicant_id,applicant_id,
                                                Params.transaction_id,transaction_id,
                                                Params.loan_amount,Loan_amount,
                                                Params.sub_categoryid,sub_categoryid,
                                                Params.sub_taskid,sub_taskid);
                                        finish();
                                        break;
                                    default:
                                        Objs.a.showToast(mCon, "Please Contact Call center to Update the Details");
                                        return;
                                }
                            }
                           /* else if(statues1 == "1" && statues == "false")
                            {
                                Log.d(" applicant Entry 1", statues);
                                Objs.ac.StartActivityPutExtra(mCon, Applicant_Entry.class,
                                        Params.user_id,id,
                                        Params.applicant_id,applicant_id,
                                        Params.transaction_id,transaction_id,
                                        Params.sub_taskid,sub_taskid);
                                finish();
                            }
                            else if(statues1 == "2" && statues == "false")
                            {
                                Log.d(" applicant Entry 2", statues);
                                Objs.ac.StartActivityPutExtra(mCon, Applicant_Entry1.class,
                                        Params.user_id,id,
                                        Params.applicant_id,applicant_id,
                                        Params.transaction_id,transaction_id,
                                        Params.sub_taskid,sub_taskid);
                                finish();
                            }
                            else if(statues1 == "3" && statues == "false")
                            {
                                Log.d(" applicant Entry 3", statues);
                                Objs.ac.StartActivityPutExtra(mCon, Applicant_Entry2.class,
                                        Params.user_id,id,
                                        Params.applicant_id,applicant_id,
                                        Params.transaction_id,transaction_id,
                                        Params.sub_taskid,sub_taskid);
                                finish();
                            }
                            else
                            {
                                Objs.a.showToast(mCon, "Please Contact Call center to Update the Details");
                            }
*/

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
        Objs.ac.StartActivity(mCon, Statues_Dashboard_Nav.class);
        Pref.removeStatus_id(mCon);
        Pref.removeStatus_Count(mCon);
        finish();

        super.onBackPressed();
    }
}
