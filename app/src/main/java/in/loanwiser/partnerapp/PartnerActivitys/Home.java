package in.loanwiser.partnerapp.PartnerActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kofigyan.stateprogressbar.StateProgressBar;

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
import in.loanwiser.partnerapp.Documents.Applicant_Details_Single;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.Partner_Statues.LeadeFragment;
import in.loanwiser.partnerapp.Payment.PaymentActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.CRIF_Report_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.CRIF_Report_Activity_PDF_View;
import in.loanwiser.partnerapp.Step_Changes_Screen.Creadite_Report_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Credite_report_details;
import in.loanwiser.partnerapp.Step_Changes_Screen.Document_Check_List;
import in.loanwiser.partnerapp.Step_Changes_Screen.Document_Checklist_Details_type;
import in.loanwiser.partnerapp.Step_Changes_Screen.Eligibility_BL;
import in.loanwiser.partnerapp.Step_Changes_Screen.Eligibility_Check_PL;
import in.loanwiser.partnerapp.Step_Changes_Screen.Eligibility_HL_New;
import in.loanwiser.partnerapp.Step_Changes_Screen.Lead_Crration_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Payment_Details_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Activity_Data_View;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Check_BL;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Check_HL_new;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Check_PL;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Report_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Report_Activity_New;

public class Home extends AppCompatActivity {

    String  email,username,user_id,mobileno,transaction_id, subtask_id,applicant_id,b2b_userid,sub_taskid,step_status,
            Applicant_Statues;
    private Context mCon = this;
    private String S1,S2,S3,S4,S5;
    Toolbar toolbar;
    private AlertDialog progressDialog;
    private CardView app,doc,offer;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private ImageView app_doc_img,app_info_img,app_info_img11,app_interview_img,app_offer_img,app_track_img;
    private TextView customerinterview,offerdetails,app_doc_message,app_info_message;
    private LinearLayout lead_cr_statues;
    CardView Applicant_info_ly,Document_check_list,Document_Upload,offer_generation,
            Viability_Check,eligibility_check,viability_Report,Credit_REport_Generation,Paymet,
            CRIF_Check,step2_card;

    ImageView viability_check_img2,eligibility_check_img,viability_report_image,Credite_report_image,
            payment_img,crif_img,credite_report_img;

    String viability,eligibility,credit_request,payment,viability_report,viability_report_URL,
    document_checklist,document_upload,loan_type_id,loan_type,crif_status,submit_loanwiser,offer_Details,
            part_compstatus,part_subcompstatus, loanwiser_submit_str,loanwiser_submit_str1,loan_status,
            reject_status;
    AppCompatImageView call_phone;

    AppCompatTextView lead_name,mobile_no,Loan_amount,loan_type_,loan_submit_statues1,viability_statues,
            eligibility_check_cmp,payment_statues_comp,crif_report_cmp,viability_report_cmp,loan_statues,
            sub_to_loanwiser,crif_report_view;


    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    PermissionUtils permissionUtils;

    String[] descriptionData = {"Send to Bank", "Sanctioned", "Disbursed"};
    StateProgressBar stateProgressBar,stateProgressBar1,stateProgressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setTitle("Lead Detail Dashboard");
        setSupportActionBar(toolbar);
        Objs.ac.ApplyFont(toolbar, mCon);


        getSupportActionBar().setTitle(R.string.dashboard_new);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //  toolbar.setNavigationIcon(R.drawable.ic_hamburger);
        progressDialog = new SpotsDialog(this, R.style.Custom);

    //    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
      //  user_id=prefs.getString("user_id","defaultStringIfNothingFound");
        user_id= Pref.getUSERID(getApplicationContext());
        Log.e("TAG", "onCreate:CO_Employement_Type "+user_id);
        initCode();
        Applicant_Status();

      /*  user_id =  Objs.a.getBundle(this, Params.user_id);
        transaction_id =  Objs.a.getBundle(this, Params.transaction_id);
        applicant_id =  Objs.a.getBundle(this, Params.applicant_id);
        sub_taskid =  Objs.a.getBundle(this, Params.sub_taskid);
        Applicant_Statues =  Objs.a.getBundle(this, Params.Applicant_status);

        loan_type_id =  Objs.a.getBundle(this, Params.loan_type_id);
        loan_type =  Objs.a.getBundle(this, Params.loan_type);
        Log.e("Applicant_Statues",Applicant_Statues);*/

         stateProgressBar = (StateProgressBar) findViewById(R.id.state_progressbar);
         stateProgressBar1 = (StateProgressBar) findViewById(R.id.state_progressbar1);
         stateProgressBar2 = (StateProgressBar) findViewById(R.id.state_progressbar2);
        stateProgressBar.setStateDescriptionData(descriptionData);
        stateProgressBar1.setStateDescriptionData(descriptionData);
        stateProgressBar2.setStateDescriptionData(descriptionData);

        call_phone = findViewById(R.id.call_phone);
      /*  call_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + mobileno));
                startActivity(intent);
            }
        });*/

        call_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                Log.i("TAG", "onClick: "+mobileno);
                callIntent.setData(Uri.parse("tel:" +mobileno));
                try {
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Home.this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }
            }
        });




      //  step_status =  Objs.a.getBundle(this, Params.step_status);

        permissionUtils = new PermissionUtils();




    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initCode() {

        initUI();
        fonts();
        clicks();

    }


    public void Applicant_Status() {

        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("user_id",Pref.getUSERID(getApplicationContext()));

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
                               String Loan_amount = jsonObject2.getString("loan_amount");

                                transaction_id =  jsonObject2.getString("transaction_id");
                                subtask_id =  jsonObject2.getString("subtask_id");
                                loan_type_id =  jsonObject2.getString("loan_type_id");
                                loan_type =  jsonObject2.getString("loan_type");
                                payment =  jsonObject2.getString("payment");
                              //  applicant_id =  "APP-"+user_id;

                                // String statues2 = "3";
                              //  Pref.putUSERID(mCon,user_id);
                                String _Emp_staus_jsonArray = jsonArray.toString();
                                Pref.putTRANSACTIONID(mCon,transaction_id);
                                Pref.putUSERID(mCon,user_id);
                                Applicant_Statues =  _Emp_staus_jsonArray;
                                try {
                                    JSONArray array = new JSONArray(_Emp_staus_jsonArray);
                                    for (int i=0;i<array.length();i++) {
                                        JSONObject J = null;
                                        try {
                                            J = array.getJSONObject(i);

                                            applicant_id = J.getString("user_type");


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Report_View_Fu();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Applicant Entry request", String.valueOf(error));
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
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

    private void initUI()
    {

        Applicant_info_ly = (CardView) findViewById(R.id.Applicant_info_ly);
        Document_check_list = (CardView) findViewById(R.id.Document_check_list);
        Document_Upload = (CardView) findViewById(R.id.Document_Upload);
        offer_generation = (CardView) findViewById(R.id.offer_generation);
        offer = (CardView) findViewById(R.id.offer_generation);
        Paymet = (CardView) findViewById(R.id.Paymet);

        Viability_Check = (CardView) findViewById(R.id.Viability_Check);
        eligibility_check = (CardView) findViewById(R.id.eligibility_check);
        viability_Report = (CardView) findViewById(R.id.viability_Report);
        Credit_REport_Generation = (CardView) findViewById(R.id.Credit_REport_Generation);
        CRIF_Check = (CardView) findViewById(R.id.CRIF_Check);
        step2_card = (CardView) findViewById(R.id.step2_card);



        lead_name = (AppCompatTextView) findViewById(R.id.lead_name);
        mobile_no = (AppCompatTextView) findViewById(R.id.mobile_no);
        Loan_amount = (AppCompatTextView) findViewById(R.id.Loan_amount);
        loan_type_ = (AppCompatTextView) findViewById(R.id.loan_type_);
        loan_submit_statues1 = (AppCompatTextView) findViewById(R.id.loan_submit_statues1);
        loan_statues = (AppCompatTextView) findViewById(R.id.loan_statues);
        sub_to_loanwiser = (AppCompatTextView) findViewById(R.id.sub_to_loanwiser);

        sub_to_loanwiser.setTextColor(Color.parseColor("#F9F338"));

        viability_statues = (AppCompatTextView) findViewById(R.id.viability_statues);
        eligibility_check_cmp = (AppCompatTextView) findViewById(R.id.eligibility_check_cmp);
        payment_statues_comp = (AppCompatTextView) findViewById(R.id.payment_statues_comp);
        crif_report_cmp = (AppCompatTextView) findViewById(R.id.crif_report_cmp);
        viability_report_cmp = (AppCompatTextView) findViewById(R.id.viability_report_cmp);
        crif_report_view = (AppCompatTextView) findViewById(R.id.crif_report_view);

        lead_cr_statues = (LinearLayout) findViewById(R.id.lead_cr_statues);

        app_doc_img = (ImageView) findViewById(R.id.app_doc_img);
        app_info_img = (ImageView) findViewById(R.id.app_info_img);
        app_info_img11 = (ImageView) findViewById(R.id.app_info_img11);
        app_interview_img = (ImageView) findViewById(R.id.app_interview_img);


        app_offer_img = (ImageView) findViewById(R.id.app_offer_img);

        //viability_check_img2,eligibility_check_img,viability_report_image,Credite_report_image;

        viability_check_img2 = (ImageView) findViewById(R.id.viability_check_img2);
        eligibility_check_img = (ImageView) findViewById(R.id.eligibility_check_img);
        crif_img = (ImageView) findViewById(R.id.crif_img);
        viability_report_image = (ImageView) findViewById(R.id.viability_report_image);
       // Credite_report_image = (ImageView) findViewById(R.id.Credite_report_image);
        credite_report_img = (ImageView) findViewById(R.id.credite_report_img);
        payment_img = (ImageView) findViewById(R.id.payment_img);

        customerinterview = (TextView) findViewById(R.id.customerinterview_offer) ;
        app_info_message = (TextView) findViewById(R.id.app_info_message) ;
        app_doc_message = (TextView) findViewById(R.id.app_doc_message) ;
        offerdetails = (TextView) findViewById(R.id.callcenter_offer) ;

        Viability_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(viability.contains("completed"))
                {
                    Intent intent = new Intent(Home.this, Viability_Activity_Data_View.class);
                    intent.putExtra("user_id", user_id);
                    intent.putExtra("transaction_id", transaction_id);
                    startActivity(intent);
                   // finish();
                }else
                {

                    if(loan_type_id.equals("21"))
                    {

                        Intent intent = new Intent(Home.this, Viability_Check_PL.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                       // finish();

                    }else if(loan_type_id.equals("20"))
                    {
                        Intent intent = new Intent(Home.this, Viability_Check_BL.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                      //  finish();

                    }else
                    {
                        Intent intent = new Intent(Home.this, Viability_Check_HL_new.class);
                        startActivity(intent);
                       // finish();
                    }


                }

            }
        });

        eligibility_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(viability.contains("completed"))
                {
                    if(eligibility.contains("completed"))
                    {

                    }else
                    {
                        if(loan_type_id.equals("21"))
                        {

                            Intent intent = new Intent(Home.this, Eligibility_Check_PL.class);
                            intent.putExtra("user_id", user_id);
                            intent.putExtra("transaction_id", transaction_id);
                            startActivity(intent);
                           // finish();

                        }else if(loan_type_id.equals("20"))
                        {
                            Intent intent = new Intent(Home.this, Eligibility_BL.class);
                            intent.putExtra("user_id", user_id);
                            intent.putExtra("transaction_id", transaction_id);
                            startActivity(intent);
                           // finish();

                        }else
                        {
                            Intent intent = new Intent(Home.this, Eligibility_HL_New.class);
                            startActivity(intent);
                           // finish();
                        }
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(),"Please Complete the Viavility Check", Toast.LENGTH_SHORT).show();

                }


            }
        });

        CRIF_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(viability.contains("completed")) {

                    if (eligibility.contains("completed")) {

                        if (payment.contains("completed")) {

                            if(credit_request.contains("completed"))
                            {

                            }else
                            {
                                Intent intent = new Intent(Home.this, Creadite_Report_Activity.class);
                                startActivity(intent);
                                finish();
                            }

                        }else
                        {
                            Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                        }
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                    }
                        }else {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }

            }
        });


        Paymet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(viability.contains("completed")) {
                    if (eligibility.contains("completed")) {
                        if(payment.contains("completed"))
                        {

                        }else  if(payment.contains("pending"))
                        {
                            Intent intent = new Intent(Home.this, PaymentActivity.class);
                            startActivity(intent);
                            // finish();
                        }
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                    }

                }else

                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        viability_Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(reject_status.equals("1"))
                {
                    if (permissionUtils.checkPermission(Home.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                        if (viability_report_URL.length() > 0) {
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL)));
                            } catch (Exception e) {
                                e.getStackTrace();
                            }
                        }

                    }
                }else
                {
                    if(viability.contains("completed")) {

                        if (eligibility.contains("completed")) {

                            if (payment.contains("completed")) {

                                if(credit_request.contains("completed"))
                                {
                                    if (permissionUtils.checkPermission(Home.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                        if (viability_report_URL.length() > 0) {
                                            try {
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL)));
                                            } catch (Exception e) {
                                                e.getStackTrace();
                                            }
                                        }

                                    }
                                }else
                                {
                                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                                }

                            }else
                            {
                                Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                            }
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                        }
                    }else {

                        Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();


                    }
                }




              /*  if(viability_report.contains("completed"))
                {
                    if (permissionUtils.checkPermission(Home.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                        if (viability_report_URL.length() > 0) {
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL)));
                            } catch (Exception e) {
                                e.getStackTrace();
                            }
                        }

                    }
                }else
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }
*/

            }
        });

        Credit_REport_Generation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(viability.contains("completed")) {

                    if (eligibility.contains("completed")) {

                        if (payment.contains("completed")) {

                            if(credit_request.contains("completed"))
                            {
                                Intent intent = new Intent(Home.this, CRIF_Report_Activity_PDF_View.class);
                                intent.putExtra("user_id", applicant_id);
                                startActivity(intent);
                            }else
                            {
                                Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                            }

                        }else
                        {
                            Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                        }
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                    }
                }else {


                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }




            }
        });


    }
    private void fonts() {
        Objs.a.OutfitNormalFontStyle(mCon, R.id.step1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.step21);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_info);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.step2);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_doc);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.step3);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.step4);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_offer);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.callcenter_offer);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.customerinterview_offer);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_doc_message);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_info_message);
    }


    private void Step_copletions() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("user_id",Pref.getUSERID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Statues Request ",String.valueOf(J));
      //  progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Lead_Details_statues, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.e("Statues response",String.valueOf(response));
                            String report_statues = response.getString("status");
                            if(report_statues.equals("success"))
                            {
                                JSONObject jsonObject1 = response.getJSONObject("data");
                                String user_name = jsonObject1.getString("user_name");
                                 mobileno = jsonObject1.getString("mobileno");
                                String loan_type = jsonObject1.getString("loan_type");
                                String loan_amount = jsonObject1.getString("loan_amount");
                                 loan_status = jsonObject1.getString("loan_status");
                                String curr_status = jsonObject1.getString("curr_status");
                                 submit_loanwiser = jsonObject1.getString("submit_loanwiser");
                                part_compstatus = jsonObject1.getString("part_compstatus");
                                part_subcompstatus = jsonObject1.getString("part_subcompstatus");

                                lead_name.setText(user_name);
                                mobile_no.setText(mobileno);
                                Loan_amount.setText("\u20B9"+loan_amount);
                                loan_type_.setText(loan_type);
                                loan_submit_statues1.setText(loan_status);


                                if(curr_status.equals("6"))
                                {
                                    loan_statues.setVisibility(View.VISIBLE);
                                    stateProgressBar.setVisibility(View.VISIBLE);
                                    stateProgressBar1.setVisibility(View.GONE);
                                    stateProgressBar2.setVisibility(View.GONE);
                                }else if(curr_status.equals("7"))
                                {
                                    loan_statues.setVisibility(View.VISIBLE);
                                    stateProgressBar.setVisibility(View.GONE);
                                    stateProgressBar1.setVisibility(View.VISIBLE);
                                    stateProgressBar2.setVisibility(View.GONE);
                                }else if(curr_status.equals("8"))
                                {
                                    loan_statues.setVisibility(View.VISIBLE);
                                    stateProgressBar.setVisibility(View.GONE);
                                    stateProgressBar1.setVisibility(View.GONE);
                                    stateProgressBar2.setVisibility(View.VISIBLE);
                                }else
                                {
                                    loan_statues.setVisibility(View.GONE);
                                    stateProgressBar.setVisibility(View.GONE);
                                    stateProgressBar1.setVisibility(View.GONE);
                                    stateProgressBar2.setVisibility(View.GONE);
                                }

                                Work_flow_status();
                            }else
                            {
                                Toast.makeText(getApplicationContext(),"error please check!!!", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
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

    private void Work_flow_status() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("trans_id", transaction_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Request List",String.valueOf(J));
       // progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PARTNER_STATUES, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                             Log.e("Home List",String.valueOf(response));

                             JSONObject jsonObject1 = response.getJSONObject("response");
                             JSONObject jsonObject2 = jsonObject1.getJSONObject("step2");
                             JSONObject jsonObject3 = jsonObject1.getJSONObject("step3");
                             JSONObject jsonObject4 = jsonObject1.getJSONObject("step4");
                             JSONObject loanwiser_submit = jsonObject1.getJSONObject("loanwiser_submit");
                              reject_status = jsonObject1.getString("reject_status");

                             String step2_statues = jsonObject2.getString("status");
                             JSONObject step2_sub_statues = jsonObject2.getJSONObject("sub_status");

                             JSONObject step3_sub_statues = jsonObject3.getJSONObject("sub_status");
                             JSONObject step4_sub_statues = jsonObject4.getJSONObject("sub_status");

                             viability = step2_sub_statues.getString("viability");
                             eligibility = step2_sub_statues.getString("eligibility");
                             credit_request = step2_sub_statues.getString("credit_request");
                             payment = step2_sub_statues.getString("payment");
                             viability_report = step2_sub_statues.getString("viability_report");
                            crif_status = step2_sub_statues.getString("crif_status");

                            offer_Details = step4_sub_statues.getString("offer_generate");

                             document_checklist = step3_sub_statues.getString("document_checklist");
                             document_upload = step3_sub_statues.getString("document_upload");


                            loanwiser_submit_str = loanwiser_submit.getString("submit");


                            if(reject_status.equals("1"))
                            {
                                Viability_Check.setVisibility(View.VISIBLE);
                                eligibility_check.setVisibility(View.GONE);
                                CRIF_Check.setVisibility(View.GONE);
                                Paymet.setVisibility(View.GONE);
                                Document_check_list.setVisibility(View.GONE);
                                Document_Upload.setVisibility(View.GONE);
                                offer.setVisibility(View.GONE);
                                Credit_REport_Generation.setVisibility(View.GONE);

                            }else
                            {
                                Viability_Check.setVisibility(View.VISIBLE);
                                eligibility_check.setVisibility(View.VISIBLE);
                                CRIF_Check.setVisibility(View.VISIBLE);
                                Paymet.setVisibility(View.VISIBLE);
                                Document_check_list.setVisibility(View.GONE);
                                Document_Upload.setVisibility(View.VISIBLE);
                                offer.setVisibility(View.VISIBLE);


                               if( loanwiser_submit_str.contains("yes"))
                               {
                                   loanwiser_submit_str1 = loanwiser_submit.getString("step");
                                   loan_submit_statues1.setText("Submit to Loanwiser");

                                   if(loanwiser_submit_str1.contains("step-1") || loanwiser_submit_str1.contains("step-2") )
                                   {

                                      if(loanwiser_submit_str1.contains("step-2"))
                                      {
                                          Viability_Check.setVisibility(View.VISIBLE);
                                      }else
                                      {
                                          Viability_Check.setVisibility(View.GONE);
                                      }

                                       eligibility_check.setVisibility(View.GONE);
                                       CRIF_Check.setVisibility(View.GONE);
                                       Paymet.setVisibility(View.GONE);
                                       Document_check_list.setVisibility(View.GONE);
                                       viability_Report.setVisibility(View.GONE);
                                       Document_Upload.setVisibility(View.GONE);
                                       offer.setVisibility(View.GONE);
                                       step2_card.setVisibility(View.GONE);
                                       Credit_REport_Generation.setVisibility(View.GONE);

                                   }

                               }else {

                                   loan_submit_statues1.setText(loan_status);
                                   Viability_Check.setVisibility(View.VISIBLE);
                                   eligibility_check.setVisibility(View.VISIBLE);
                                   CRIF_Check.setVisibility(View.VISIBLE);
                                   Paymet.setVisibility(View.VISIBLE);
                                   Document_check_list.setVisibility(View.GONE);
                                   Document_Upload.setVisibility(View.VISIBLE);
                                   viability_Report.setVisibility(View.VISIBLE);
                                   offer.setVisibility(View.VISIBLE);
                                   step2_card.setVisibility(View.VISIBLE);
                                   Credit_REport_Generation.setVisibility(View.VISIBLE);
                                   //
                               }

                            }

                            if(document_checklist.contains("pending"))
                            {
                                app_doc_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));

                            }else
                            {
                                app_doc_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));

                            }
                            if(document_upload.contains("pending"))
                            {

                                app_interview_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));

                            }else
                            {
                                app_interview_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));

                            }


                            if(viability.contains("completed"))
                            {

                                viability_check_img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                viability_statues.setText("completed");
                            }else
                            {
                                viability_check_img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                viability_statues.setText("Pending under you");
                            }

                            if(eligibility.contains("completed"))
                            {

                                eligibility_check_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                eligibility_check_cmp.setText("completed");
                            }else
                            {
                                eligibility_check_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                eligibility_check_cmp.setText("Pending under you");
                            }

                            if(credit_request.contains("completed"))
                            {
                                CRIF_Check.setEnabled(false);
                                crif_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));

                                crif_report_cmp.setText("completed");
                            }else
                            {
                                CRIF_Check.setEnabled(true);
                                crif_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                crif_report_cmp.setText("Pending under you");
                            }

                            if(payment.contains("completed"))
                            {
                                Paymet.setEnabled(false);
                                payment_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                payment_statues_comp.setText("completed");
                            }else
                            {
                                Paymet.setEnabled(true);
                                payment_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                payment_statues_comp.setText("Pending under you");
                            }

                            if(viability_report.contains("completed"))
                            {
                                viability_Report.setEnabled(true);
                                viability_report_image.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                viability_report_cmp.setText("completed");
                            }else
                            {
                                if(reject_status.equals("1"))
                                {
                                    viability_report_cmp.setText("click to view reject status");
                                    viability_Report.setEnabled(true);
                                }else
                                {
                                    viability_Report.setEnabled(true);
                                    viability_report_image.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    viability_report_cmp.setText("Pending under you");
                                }

                            }

                            if(offer_Details.contains("completed"))
                            {
                                app_offer_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));

                            }else
                            {
                                app_offer_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                            }


                            if( loanwiser_submit_str.contains("yes"))
                            {
                                Credit_REport_Generation.setVisibility(View.GONE);
                            }else
                            {
                                if(reject_status.equals("1"))
                                {
                                    Credit_REport_Generation.setVisibility(View.GONE);
                                }else {
                                    if(crif_status.contains("completed"))
                                    {
                                        Credit_REport_Generation.setVisibility(View.VISIBLE);
                                        Credit_REport_Generation.setEnabled(true);
                                        credite_report_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                        crif_report_view.setText("completed");

                                    }else if(crif_status.contains("pending"))
                                    {
                                        Credit_REport_Generation.setVisibility(View.VISIBLE);
                                        Credit_REport_Generation.setEnabled(true);
                                        credite_report_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                        crif_report_view.setText("Pending under you");
                                    }else if(crif_status.contains("not_wanted"))
                                    {
                                        Credit_REport_Generation.setVisibility(View.GONE);
                                    }
                                }

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
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
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

    private void Report_View_Fu() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("trans_id", transaction_id);
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Report Request ",String.valueOf(J));
      //  progressDialog.show();
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

                                Step_copletions();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                     //   progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
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

    private void clicks() {

       /* findViewById(R.id.hl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  lead_cr_statues.setVisibility(View.VISIBLE);

            }
        });
*/
        Applicant_info_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Applicant_Info_new.class);
                startActivity(intent);
            }
        });

        app_info_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lead_cr_statues.setVisibility(View.VISIBLE);
                app_info_img.setVisibility(View.GONE);
                app_info_img11.setVisibility(View.VISIBLE);
            }
        });

        app_info_img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lead_cr_statues.setVisibility(View.GONE);
                app_info_img.setVisibility(View.VISIBLE);
                app_info_img11.setVisibility(View.GONE);
            }
        });

        findViewById(R.id.Document_check_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      Toast.makeText(mCon, "CD_app_info", Toast.LENGTH_LONG).show();
              //  Account_Listings_Details(user_id);


            }
        });

        findViewById(R.id.Document_Upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      Toast.makeText(mCon, "CD_app_info", Toast.LENGTH_LONG).show();

                if(document_checklist.contains("pending"))
                {
                    Intent intent = new Intent(Home.this, Document_Checklist_Details_type.class);
                    intent.putExtra("jsonArray", Applicant_Statues.toString());
                    startActivity(intent);

                }else
                {
                    Account_Listings_Details(user_id);

                }



            }
        });


      /*  findViewById(R.id.PL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Objs.ac.StartActivityPutExtra(mCon,
                        Customer_Info.class,
                        Params.id,user_id);
                //finish();
                //Objs.ac.StartActivity(mCon, Customer_Info.class);
                //        Toast.makeText(mCon, "CD_app_interview", Toast.LENGTH_LONG).show();
            }
        });*/

        findViewById(R.id.offer_generation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(offer_Details.contains("pending"))
                {


                }else
                {
                    Objs.ac.StartActivityPutExtra(mCon, Offers_list.class,
                            Params.transaction_id,transaction_id);

                }

               // finish();
            }
        });

    }
    private void Account_Listings_Details(String id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.user_id, id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PARTNER_STATUES_IDs, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("the reponse",response.toString());
                        try {

                            JSONObject Response = response.getJSONObject("reponse");
                            JSONObject jsonObject1 = Response.getJSONObject(Params.application_form);
                            String user_id  = Response.getString(Params.user_id);
                            String jsonStringObj  = String.valueOf(jsonObject1);
                            JSONArray jsonArray = Response.getJSONArray(Params.emp_states);

                            if (jsonArray.length()>0){
                                String jsonString  = String.valueOf(jsonArray);
                             //   Log.e("ApplicantDetails_Single",jsonString);

                                Objs.ac.StartActivityPutExtra(mCon,
                                        Applicant_Details_Single.class,
                                        Params.JSON, jsonString,
                                        Params.id,user_id,
                                        Params.transaction_id,transaction_id,
                                        Params.JSONObj,jsonStringObj);

                                 //finish();
                            }else {
                                Objs.a.ShowHideNoItems(mCon,true);
                            }

                         //   JSONArray ja = response.getJSONArray(Params.emp_states);

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

    @Override
    public void onBackPressed() {
        Objs.ac.StartActivity(mCon, LeadeFragment.class);
        finish();
        super.onBackPressed();

    }
}





