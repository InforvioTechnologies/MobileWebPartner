package in.loanwiser.partnerapp.PartnerActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
import in.loanwiser.partnerapp.BankStamentUpload.Upload_Activity_Bank;
import in.loanwiser.partnerapp.Documents.Applicant_Details_Single;
import in.loanwiser.partnerapp.Documents.Applicant_Doc_Details_revamp;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.Partner_Statues.LeadeFragment;
import in.loanwiser.partnerapp.Payment.PaymentActivity;
import in.loanwiser.partnerapp.R;
//import in.loanwiser.partnerapp.Step_Changes_Screen.Applicant_fragment;
import in.loanwiser.partnerapp.Step_Changes_Screen.CRIF_Report_Activity_PDF_View;
import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklistActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklist_Fragment;
//import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklist_new;
import in.loanwiser.partnerapp.Step_Changes_Screen.Document_Checklist_Details_type;
import in.loanwiser.partnerapp.Step_Changes_Screen.FragmentApplicant;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Screen_revamp;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Screen_revamp_Pl_BL;



public class Home extends AppCompatActivity {

    String  email,username,user_id,mobileno,transaction_id, subtask_id,applicant_id,b2b_userid,sub_taskid,step_status,
            Applicant_Statues;
    private Context mCon = this;
    private String S1,S2,S3,S4,S5;
    Toolbar toolbar;
    private AlertDialog progressDialog;
    private CardView app,doc,offer;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private ImageView app_doc_img,app_info_img,app_info_img11,app_interview_img,app_offer_img,app_track_img,bank_statement_img,
            document_img1,eligibility_statues_img,payment_img1,document_check_list_status;
    private TextView customerinterview,offerdetails,app_doc_message,app_info_message;
    private LinearLayout lead_cr_statues,if_vaibility_faild;
    CardView Applicant_info_ly,Document_check_list,Document_Upload,offer_generation,
            Viability_Check,eligibility_check,viability_Report,Credit_REport_Generation,Paymet,
            CRIF_Check,step2_card,Bank_statement_Upload;

    FragmentApplicant fragmentApplicant=null;

    ImageView viability_check_img2,eligibility_check_img,viability_report_image,Credite_report_image,
            payment_img,crif_img,credite_report_img;

    String viability,eligibility,credit_request,payment,viability_report,viability_report_URL,
    document_checklist,document_upload,loan_type_id,loan_type,crif_status,submit_loanwiser,offer_Details,
            part_compstatus,part_subcompstatus, loanwiser_submit_str,loanwiser_submit_str1,loan_status,
            reject_status,bank_statement,payment_eligility,crif_report,eligibility_status,applicant_count,property_identified;
    AppCompatImageView call_phone;

    AppCompatTextView lead_name,mobile_no,Loan_amount,loan_type_,loan_submit_statues1,viability_statues,
            eligibility_check_cmp,payment_statues_comp,crif_report_cmp,viability_report_cmp,loan_statues,
            sub_to_loanwiser,crif_report_view,bank_stm,payment_statues_comp1,document_text,eligibility_Report,
            document_check_text,pending_ask_List;

    ImageView step2_viablitystatus,step2_paymentstatus,step2_reportstatus,step2crifstatus,bankstate_status,documentupload_status,step3payment_status,eligiblitity_status;
    Button step2viablity_button,step2payment_but,step2report_but,step2crif_but,
            bankstatment_but,document_upload_button,step3payment_but,eligibility_but,document_check_button;

        AppCompatButton view_ask;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    PermissionUtils permissionUtils;
    String applicant_empstates,coapplicant_empstates,property_empstates;
    String[] descriptionData = {"Send to Bank", "Sanctioned", "Disbursed"};
    StateProgressBar stateProgressBar,stateProgressBar1,stateProgressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setTitle("Lead Detail Dashboard");
        setSupportActionBar(toolbar);
      //  Objs.ac.ApplyFont(toolbar, mCon);

        Log.i("TAG", "onCreate:Home "+"Homeclass");

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
        ASK_Count_Display();
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

                            if(statues.equals("success"))
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

                                applicant_empstates=response.getString("app_emp");
                                coapplicant_empstates=response.getString("coapp_emp");
                                property_empstates=response.getString("prop_emp");
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Home.this);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("app_emp", applicant_empstates);
                                editor.putString("coapp_emp", coapplicant_empstates);
                                editor.putString("prop_emp",property_empstates);
                                editor.apply();
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
        Bank_statement_Upload = (CardView) findViewById(R.id.Bank_statement_Upload);
        CRIF_Check = (CardView) findViewById(R.id.CRIF_Check);
        step2_card = (CardView) findViewById(R.id.step2_card);



        lead_name = (AppCompatTextView) findViewById(R.id.lead_name);
        pending_ask_List = (AppCompatTextView) findViewById(R.id.ask_txt1_pending);
        view_ask = (AppCompatButton) findViewById(R.id.view_ask);
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
        bank_stm = (AppCompatTextView) findViewById(R.id.bank_stm);
        payment_statues_comp1 = (AppCompatTextView) findViewById(R.id.payment_statues_comp1);
        document_text = (AppCompatTextView) findViewById(R.id.document_text);
        document_check_text = (AppCompatTextView) findViewById(R.id.document_check_text);
        crif_report_view = (AppCompatTextView) findViewById(R.id.crif_report_view);
        eligibility_Report = (AppCompatTextView) findViewById(R.id.eligibility_Report);

        lead_cr_statues = (LinearLayout) findViewById(R.id.lead_cr_statues);
        if_vaibility_faild = (LinearLayout) findViewById(R.id.if_vaibility_faild);

        app_doc_img = (ImageView) findViewById(R.id.app_doc_img);
        app_info_img = (ImageView) findViewById(R.id.app_info_img);
        app_info_img11 = (ImageView) findViewById(R.id.app_info_img11);
        app_interview_img = (ImageView) findViewById(R.id.app_interview_img);
        document_img1 = (ImageView) findViewById(R.id.document_img1);
        document_check_list_status = (ImageView) findViewById(R.id.document_check_list_status);
        payment_img1 = (ImageView) findViewById(R.id.payment_img1);


        app_offer_img = (ImageView) findViewById(R.id.app_offer_img);
        bank_statement_img = (ImageView) findViewById(R.id.bank_statement_img);
        eligibility_statues_img = (ImageView) findViewById(R.id.eligibility_statues_img);

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

        //new
        step2_viablitystatus=findViewById(R.id.step2_viablitystatus);
        step2_paymentstatus=findViewById(R.id.step2_paymentstatus);
        step2_reportstatus=findViewById(R.id.step2_reportstatus);
        step2crifstatus=findViewById(R.id.step2crifstatus);
        bankstate_status=findViewById(R.id.bankstate_status);
        documentupload_status=findViewById(R.id.documentupload_status);
        step3payment_status=findViewById(R.id.step3payment_status);
        eligiblitity_status=findViewById(R.id.eligiblitity_status);


        step2viablity_button=findViewById(R.id.step2viablity_button);
        step2payment_but=findViewById(R.id.step2payment_but);
        step2report_but=findViewById(R.id.step2report_but);
        step2crif_but=findViewById(R.id.step2crif_but);
        bankstatment_but=findViewById(R.id.bankstatment_but);
        document_upload_button=findViewById(R.id.document_upload_button);
        step3payment_but=findViewById(R.id.step3payment_but);
        eligibility_but=findViewById(R.id.eligibility_but);
        document_check_button=findViewById(R.id.document_check_button);



        view_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this,Ask_user_Dashboard_Activity.class);
                startActivity(intent);
                finish();
            }
        });
       /* Viability_Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(viability.equals("completed"))
                {
                  //  Intent intent = new Intent(Home.this, Viability_Activity_Data_View.class);
                    Intent intent = new Intent(Home.this, Viability_Data_revamp.class);
                    intent.putExtra("user_id", user_id);
                    intent.putExtra("transaction_id", transaction_id);
                    startActivity(intent);
                   // finish();
                }else
                {
                    Pref.putLoanType(mCon,loan_type_id);

                    if(loan_type_id.equals("21"))
                    {

                        Intent intent = new Intent(Home.this, Viability_Screen_revamp_Pl_BL.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                       // finish();

                    }else if(loan_type_id.equals("20"))
                    {
                        Intent intent = new Intent(Home.this, Viability_Screen_revamp_Pl_BL.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                      //  finish();

                    }else
                    {
                        Intent intent = new Intent(Home.this, Viability_Screen_revamp.class);
                        startActivity(intent);
                       // finish();
                    }


                }

            }
        });*/

        step2viablity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(viability.equals("completed"))
                {
                    //  Intent intent = new Intent(Home.this, Viability_Activity_Data_View.class);
                    Intent intent = new Intent(Home.this, Viability_Data_revamp.class);
                    intent.putExtra("user_id", user_id);
                    intent.putExtra("transaction_id", transaction_id);
                    startActivity(intent);
                    // finish();
                }else
                {
                    Pref.putLoanType(mCon,loan_type_id);

                    if(loan_type_id.equals("21"))
                    {

                        Intent intent = new Intent(Home.this, Viability_Screen_revamp_Pl_BL.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                        // finish();

                    }else if(loan_type_id.equals("20"))
                    {
                        Intent intent = new Intent(Home.this, Viability_Screen_revamp_Pl_BL.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                        //  finish();

                    }else
                    {
                        Intent intent = new Intent(Home.this, Viability_Screen_revamp.class);
                        startActivity(intent);
                        // finish();
                    }


                }

            }
        });

        step2payment_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(viability.equals("completed")) {
                    if(payment.equals("completed"))
                    {

                    }else  if(payment.equals("pending"))
                    {
                        Intent intent = new Intent(Home.this, PaymentActivity.class);
                        startActivity(intent);
                        // finish();
                    }

                }else
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    /*    Paymet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(viability.equals("completed")) {
                        if(payment.equals("completed"))
                        {

                        }else  if(payment.equals("pending"))
                        {
                            Intent intent = new Intent(Home.this, PaymentActivity.class);
                            startActivity(intent);
                            // finish();
                        }

                }else
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }

            }
        });*/


        step2report_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(payment.equals("completed"))
                {
                    if(reject_status.equals("1"))
                    {
                        Objs.ac.StartActivityPutExtra(Home.this, Doc_ImageView_Viability.class,
                                Params.document,viability_report_URL);

                    }else
                    {
                        Objs.ac.StartActivityPutExtra(Home.this, Doc_ImageView_Viability.class,
                                Params.document,viability_report_URL);
                    }
                }else  if(payment.equals("pending"))
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }

            }
        });

       /* viability_Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(payment.equals("completed"))
                {
                    if(reject_status.equals("1"))
                    {
                   *//* if (permissionUtils.checkPermission(Home.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                        if (viability_report_URL.length() > 0) {
                            try {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL)));
                            } catch (Exception e) {
                                e.getStackTrace();
                            }
                        }

                    }*//*
                        Objs.ac.StartActivityPutExtra(Home.this, Doc_ImageView_Viability.class,
                                Params.document,viability_report_URL);

                    }else
                    {
                   *//* if(viability.contains("completed")) {
                            if (payment.contains("completed")) {

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
                    }else {

                        Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();
                    }*//*
                        //
                        Objs.ac.StartActivityPutExtra(Home.this, Doc_ImageView_Viability.class,
                                Params.document,viability_report_URL);
                    }
                }else  if(payment.equals("pending"))
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }



            }
        });*/

        step2crif_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viability.equals("completed")) {
                    if (payment.equals("completed")) {
                        Intent intent = new Intent(Home.this, CRIF_Report_Activity_PDF_View.class);
                        intent.putExtra("user_id", applicant_id);
                        startActivity(intent);

                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }
            }
        });

      /*  Credit_REport_Generation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viability.equals("completed")) {
                        if (payment.equals("completed")) {
                                Intent intent = new Intent(Home.this, CRIF_Report_Activity_PDF_View.class);
                                intent.putExtra("user_id", applicant_id);
                                startActivity(intent);

                        }else
                        {
                            Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();
                        }
                }else {

                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }




            }
        });*/

        bankstatment_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viability.equals("completed")) {
                    if (payment.equals("completed")) {

                        if(bank_statement.equals("completed"))
                        {

                        }else
                        {
                            Intent intent = new Intent(Home.this, Upload_Activity_Bank.class);
                            startActivity(intent);
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

        /*Bank_statement_Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viability.equals("completed")) {
                        if (payment.equals("completed")) {

                                if(bank_statement.equals("completed"))
                                {

                                }else
                                {
                                    Intent intent = new Intent(Home.this, Upload_Activity_Bank.class);
                                    startActivity(intent);
                                }

                        }else
                        {
                            Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();
                        }
                }else {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }
            }
        });*/

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
            Log.i("TAG", "Checkuserid: "+Pref.getUSERID(getApplicationContext()));

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
                                applicant_count = jsonObject1.getString("applicant_count");
                                property_identified=jsonObject1.getString("property_identified");
                                Log.i("TAG", "onResponse:applicant_count "+applicant_count);

                                lead_name.setText(user_name);
                                mobile_no.setText(mobileno);
                                Loan_amount.setText("\u20B9"+loan_amount);
                                loan_type_.setText(loan_type);
                                loan_submit_statues1.setText(loan_status);

                                Pref.putCoAPPAVAILABLE(mCon,applicant_count);
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
                    @SuppressLint("ResourceAsColor")
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
                             String step3_statues = jsonObject3.getString("status");

                             JSONObject step2_sub_statues = jsonObject2.getJSONObject("sub_status");

                             JSONObject step3_sub_statues = jsonObject3.getJSONObject("sub_status");
                             JSONObject step4_sub_statues = jsonObject4.getJSONObject("sub_status");

                             viability = step2_sub_statues.getString("viability");
                           //  eligibility = step2_sub_statues.getString("eligibility");
                           //  credit_request = step2_sub_statues.getString("credit_request");
                             payment = step2_sub_statues.getString("payment");
                             viability_report = step2_sub_statues.getString("viability_report");
                          //  crif_status = step2_sub_statues.getString("crif_status");
                            crif_report = step2_sub_statues.getString("crif_report");

                            bank_statement = step3_sub_statues.getString("bank_statement");
                            payment_eligility = step3_sub_statues.getString("payment");
                            eligibility_status = step3_sub_statues.getString("eligibility_status");

                            offer_Details = step4_sub_statues.getString("offer_generate");

                             document_checklist = step3_sub_statues.getString("document_checklist");
                            Log.i("TAG", "onResponse:document_checklist "+document_checklist);
                             document_upload = step3_sub_statues.getString("document_upload");


                            loanwiser_submit_str = loanwiser_submit.getString("submit");
                            loanwiser_submit_str1 = loanwiser_submit.getString("submit");


                            if(payment.equals("completed"))
                            {
                                if (viability.equals("completed")) {
                                    payment_statues_comp.setText("Completed");
                                    step2payment_but.setBackgroundResource(R.drawable.but_shape_blue);
                                    step2_paymentstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                    step2payment_but.setText("View");
                                    step2payment_but.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Paymentplanshow();
                                        }
                                    });
                                }
                                else{
                                    Paymet.setEnabled(true);
                                    // payment_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    payment_statues_comp.setText("Pending under you");
                                    step2_paymentstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    step2payment_but.setBackgroundResource(R.drawable.but_shape_gray);
                                    step2payment_but.setText("Complete Now");
                                    step2payment_but.setFocusable(false);

                                }

                            }
                            else
                            {
                                if (viability.equals("completed")){
                                    Paymet.setEnabled(true);
                                    // payment_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    payment_statues_comp.setText("Pending under you");
                                    step2_paymentstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    step2payment_but.setBackgroundResource(R.drawable.but_shape);
                                    step2payment_but.setText("Complete Now");
                                    step2payment_but.setFocusable(false);
                                }
                                else{
                                    Paymet.setEnabled(true);
                                    // payment_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    payment_statues_comp.setText("Pending under you");
                                    step2_paymentstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    step2payment_but.setBackgroundResource(R.drawable.but_shape_gray);
                                    step2payment_but.setText("Complete Now");
                                    step2payment_but.setFocusable(false);

                                }


                            }

                            if(viability.equals("completed"))
                            {

                                viability_check_img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                step2_viablitystatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                viability_statues.setText("completed");
                                step2viablity_button.setBackgroundResource(R.drawable.but_shape_blue);
                                step2viablity_button.setText("View");
                            }else
                            {
                                viability_check_img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                step2_viablitystatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                viability_statues.setText("Pending under you");
                                step2viablity_button.setBackgroundResource(R.drawable.but_shape);
                                step2viablity_button.setText("Complete Now");
                                bankstatment_but.setBackgroundResource(R.drawable.but_shape_gray);
                                document_upload_button.setBackgroundResource(R.drawable.but_shape_gray);
                            }


                            if(viability_report.equals("completed"))
                            {
                                if (payment.equals("completed")) {

                                    if (reject_status.equals("1")) {
                                        viability_report_cmp.setText("click to view reject status");
                                        viability_Report.setEnabled(true);
                                        if_vaibility_faild.setVisibility(View.GONE);
                                        step2_reportstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                        loan_submit_statues1.setText("Loan Rejected");
                                        step2report_but.setVisibility(View.GONE);
                                    } else {
                                        if_vaibility_faild.setVisibility(View.VISIBLE);
                                        viability_Report.setEnabled(true);
                                        viability_report_image.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                        step2_reportstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                        viability_report_cmp.setText("completed");
                                        step2report_but.setBackgroundResource(R.drawable.but_shape_blue);
                                        step2report_but.setText("View");

                                    }
                                }

                            }else
                            {
                                if(reject_status.equals("1"))
                                {
                                    viability_report_cmp.setText("click to view reject status");
                                    viability_Report.setEnabled(true);
                                    step2report_but.setVisibility(View.GONE);

                                }else
                                {
                                    viability_Report.setEnabled(true);
                                    viability_report_image.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    step2_reportstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    step2report_but.setBackgroundResource(R.drawable.but_shape_gray);
                                    bankstatment_but.setBackgroundResource(R.drawable.but_shape_gray);
                                    document_upload_button.setBackgroundResource(R.drawable.but_shape_gray);
                                    step2report_but.setFocusable(false);
                                    viability_report_cmp.setText("Pending under you");
                                    step2report_but.setText("Complete Now");
                                }

                            }


                            if(loanwiser_submit_str.equals("yes"))
                            {
                                if(loanwiser_submit_str1.equals("step-3"))
                                {
                                    Credit_REport_Generation.setVisibility(View.VISIBLE);
                                    Bank_statement_Upload.setVisibility(View.VISIBLE);
                                }else
                                {
                                    Credit_REport_Generation.setVisibility(View.GONE);
                                }

                            }else
                            {
                                if(reject_status.equals("1"))
                                {
                                    Credit_REport_Generation.setVisibility(View.VISIBLE);
                                    crif_report_view.setText("View CRIF Report");
                                    credite_report_img.setVisibility(View.GONE);
                                    Bank_statement_Upload.setVisibility(View.GONE);

                                }else {

                                    if(crif_report.equals("completed"))
                                    {
                                        if (payment.equals("completed")) {
                                            Credit_REport_Generation.setVisibility(View.VISIBLE);
                                            Credit_REport_Generation.setEnabled(true);
                                            credite_report_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                            step2crifstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                            crif_report_view.setText("completed");
                                            step2crif_but.setBackgroundResource(R.drawable.but_shape_blue);
                                            step2crif_but.setText("View");
                                        }

                                    }else if(crif_report.equals("pending"))
                                    {
                                        Credit_REport_Generation.setVisibility(View.VISIBLE);
                                        Credit_REport_Generation.setEnabled(true);
                                        credite_report_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                        crif_report_view.setText("Pending under you");
                                        step2crif_but.setBackgroundResource(R.drawable.but_shape_gray);
                                        step2crif_but.setText("Complete Now");

                                    }else if(crif_report.equals("not_wanted"))
                                    {
                                        Credit_REport_Generation.setVisibility(View.GONE);
                                    }
                                }

                            }
                            if(bank_statement.equals("completed"))
                            {
                                bank_statement_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                bankstate_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                bank_stm.setText("completed");
                                bankstatment_but.setBackgroundResource(R.drawable.but_shape_blue);
                                document_check_button.setBackgroundResource(R.drawable.but_shape);
                                bankstatment_but.setText("View");
                            }else
                            {
                                bank_statement_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                bankstate_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));

                                bank_stm.setText("Pending under you");

                                if(viability.equals("completed"))
                                {
                                    bankstatment_but.setBackgroundResource(R.drawable.but_shape);
                                }else
                                {
                                    bankstatment_but.setBackgroundResource(R.drawable.but_shape_gray);
                                    document_check_button.setBackgroundResource(R.drawable.but_shape_gray);
                                }


                                bankstatment_but.setText("Complete Now");

                            }

                            if(payment_eligility.equals("completed"))
                            {
                                payment_img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                step3payment_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                payment_statues_comp1.setText("completed");
                                step3payment_but.setBackgroundResource(R.drawable.but_shape_blue);
                                step3payment_but.setText("View");
                            }else
                            {
                                payment_img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                step3payment_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                payment_statues_comp1.setText("Pending under you");
                                step3payment_but.setBackgroundResource(R.drawable.but_shape_gray);
                                step3payment_but.setText("Complete Now");
                                step3payment_but.setFocusable(false);
                            }
                            if(eligibility_status.equals("completed"))
                            {
                                eligibility_statues_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                eligiblitity_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                eligibility_Report.setText("completed");
                                eligibility_but.setBackgroundResource(R.drawable.but_shape_blue);
                                eligibility_but.setText("View");

                            }else
                            {
                                eligibility_statues_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                eligiblitity_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                eligibility_Report.setText("Pending under you");
                                eligibility_but.setBackgroundResource(R.drawable.but_shape_gray);
                                eligibility_but.setText("Complete Now");
                                eligibility_but.setFocusable(false);
                            }

                           /* if(document_checklist.contains("pending"))
                            {
                                app_doc_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));

                            }else
                            {
                                app_doc_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));

                            }*/

                            if(document_upload.equals("pending"))
                            {
                                document_text.setText("Pending under you");
                                document_img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                documentupload_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));

                                if(viability.equals("completed"))
                                {
                                    document_upload_button.setBackgroundResource(R.drawable.but_shape);
                                }else
                                {
                                    document_upload_button.setBackgroundResource(R.drawable.but_shape_gray);
                                }


                                document_upload_button.setText("Complete now");

                            }else
                            {
                                document_img1.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                document_text.setText("completed");
                                documentupload_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                document_upload_button.setBackgroundResource(R.drawable.but_shape_blue);
                                document_upload_button.setText("View");

                            }

                            if(document_checklist.equals("pending"))
                            {
                                document_check_text.setText("Pending under you");
                                document_check_list_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));

                                if(viability.equals("completed"))
                                {
                                    document_check_button.setBackgroundResource(R.drawable.but_shape);
                                }else
                                {
                                    document_check_button.setBackgroundResource(R.drawable.but_shape_gray);
                                }
                                document_upload_button.setBackgroundResource(R.drawable.but_shape_gray);
                            }else
                            {
                                document_check_text.setText("completed");
                                document_check_list_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                document_check_button.setBackgroundResource(R.drawable.but_shape_blue);
                                document_upload_button.setBackgroundResource(R.drawable.but_shape);
                                document_check_button.setText("View");
                            }

                            if(offer_Details.equals("completed"))
                            {
                                app_offer_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));

                            }else
                            {
                                app_offer_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
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
    public void ASK_Count_Display() {
        final JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            //  J.put("b2b_id", Pref.getID(mCon));
            //  J.put("user_id", user_id);
            J.put("user_id", "51647");
            // User : {"user_id":"51647"}
            Log.e("ASK Count response", String.valueOf(J));
        } catch (JSONException e) {
            e.printStackTrace();
        }

       // progressDialog.show();
        Log.e("Applicant Entry request", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.ask_countdisp, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("ASK Count response", String.valueOf(response));
                        JSONObject jsonObject1 = new JSONObject();

                        try {
                            String statues = response.getString("status");

                            if(statues.equals("success"))
                            {
                                JSONObject jsonObject2 = response.getJSONObject("data");

                                String pending_count= jsonObject2.getString("pending_count");
                                String resolve_count= jsonObject2.getString("resolve_count");
                                String accept_loanwiser= jsonObject2.getString("accept_loanwiser");

                                pending_ask_List.setText("Pending Ask" +"("+pending_count+")");




                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
              //  progressDialog.dismiss();
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

    private void Paymentplanshow() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.payment_layout);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        final LinearLayout benefitcoinlay=(LinearLayout) dialog.findViewById(R.id.benefitcoinlay);
        ImageView closebutton=(ImageView)dialog.findViewById(R.id.closebtn);
        final AppCompatTextView planchoose=(AppCompatTextView) dialog.findViewById(R.id.planchoose);
        final AppCompatTextView transferdby=(AppCompatTextView) dialog.findViewById(R.id.transferdby);
        final AppCompatTextView planamt=(AppCompatTextView) dialog.findViewById(R.id.planamt);
        final AppCompatTextView benefitcoin=(AppCompatTextView) dialog.findViewById(R.id.benefitcoin);
        final AppCompatTextView paydate=(AppCompatTextView) dialog.findViewById(R.id.paydate);
        final AppCompatTextView textplande=(AppCompatTextView) dialog.findViewById(R.id.textplande);
        final AppCompatTextView texttransfer=(AppCompatTextView) dialog.findViewById(R.id.texttransfer);
        final AppCompatTextView textamount=(AppCompatTextView) dialog.findViewById(R.id.textamount);
        final AppCompatTextView textbene=(AppCompatTextView) dialog.findViewById(R.id.textbene);
        final AppCompatTextView paydates=(AppCompatTextView) dialog.findViewById(R.id.paydates);
        final AppCompatTextView paydettext=(AppCompatTextView) dialog.findViewById(R.id.paydettext);
        Typeface font = Typeface.createFromAsset(Home.this.getAssets(), "segoe_ui.ttf");
        planchoose.setTypeface(font);
        transferdby.setTypeface(font);
        benefitcoin.setTypeface(font);
        paydate.setTypeface(font);
        textplande.setTypeface(font);
        texttransfer.setTypeface(font);
        textamount.setTypeface(font);
        textbene.setTypeface(font);
        paydates.setTypeface(font);
        paydettext.setTypeface(font);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.user_id,user_id );
            J.put(Params.transaction_id,transaction_id);
            Log.i("TAG", "Paymentplanshow: "+J);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Payment_Plan_Show, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("the reponse",response.toString());
                        try {
                            dialog.show();
                            String plan_choose  = response.getString("plan_choose");
                            if (plan_choose.equalsIgnoreCase("Custom Plan")){
                                String benefitcoins=response.getString("benifit_coin");
                                benefitcoin.setText(benefitcoins);
                                String transfer_by  = response.getString("transfer_by");
                                String amount  = response.getString("amount");
                                String payment_date  = response.getString("payment_date");
                                planchoose.setText(plan_choose);
                                transferdby.setText(transfer_by);
                                planamt.setText("\u20B9"+amount);
                                paydate.setText(payment_date);
                            }
                            else if (plan_choose.equalsIgnoreCase("Standard Plan")){
                                benefitcoinlay.setVisibility(View.GONE);
                                String plan_chooses  = response.getString("plan_choose");
                                String transfer_by  = response.getString("transfer_by");
                                String amount  = response.getString("amount");
                                String payment_date  = response.getString("payment_date");
                                planchoose.setText(plan_chooses);
                                transferdby.setText(transfer_by);
                                planamt.setText("\u20B9"+amount);
                                paydate.setText(payment_date);
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
                            Log.e("viability_report_",String.valueOf(viability_report_URL));
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

        document_upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(document_upload.equals("pending"))
                {
                    // Account_Listings_Details(user_id);
                    Intent intent = new Intent(Home.this, Applicant_Doc_Details_revamp.class);
                    intent.putExtra("jsonArray", Applicant_Statues.toString());
                    startActivity(intent);
                }else
                {

                    Toast.makeText(getApplicationContext(),"This Step is Completed", Toast.LENGTH_SHORT).show();

                }

            }
        });

        document_check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(document_checklist.equals("pending"))
                {

                    Intent intent=new Intent(Home.this, DocumentChecklist_Fragment.class);
                    intent.putExtra("jsonArray", Applicant_Statues.toString());
                    intent.putExtra("applicantcount",applicant_count);
                    intent.putExtra("propertyidentify",property_identified);
                    startActivity(intent);

                }else
                {
                    Intent intent=new Intent(Home.this, DocumentChecklist_Fragment.class);
                    intent.putExtra("jsonArray", Applicant_Statues.toString());
                    intent.putExtra("applicantcount",applicant_count);
                    intent.putExtra("propertyidentify",property_identified);
                    startActivity(intent);

                }

            }
        });

/*        findViewById(R.id.document_Upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      Toast.makeText(mCon, "CD_app_info", Toast.LENGTH_LONG).show();

                if(document_checklist.equals("pending"))
                {

                 Intent intent=new Intent(Home.this, DocumentChecklistActivity.class);
                 intent.putExtra("jsonArray", Applicant_Statues.toString());
                 intent.putExtra("applicantcount",applicant_count);
                 intent.putExtra("propertyidentify",property_identified);
                 startActivity(intent);

                }else
                {
                    Account_Listings_Details(user_id);

                }



            }
        });*/

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


                if(offer_Details.equals("pending"))
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

        Intent intent = new Intent(mCon, DashBoard_new.class);
        startActivity(intent);
       // Objs.ac.StartActivity(mCon, LeadeFragment.class);
        finish();
        super.onBackPressed();

    }
}





