package in.loanwiser.partnerapp.PartnerActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kofigyan.stateprogressbar.StateProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.BankStamentUpload.Doc_ImageView_Bank;
import in.loanwiser.partnerapp.BankStamentUpload.Upload_Activity_Bank;
import in.loanwiser.partnerapp.Documents.ActivityStreamActivity;
import in.loanwiser.partnerapp.Documents.Applicant_Details_Single;
import in.loanwiser.partnerapp.Documents.Applicant_Doc_Details_revamp;
import in.loanwiser.partnerapp.Documents.Document_Availability_Check;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.PDF_Viewer.MainActivity;
import in.loanwiser.partnerapp.Partner_Statues.BusinessCardActivity;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.Payment.PaymentActivity;
import in.loanwiser.partnerapp.Payment.Payment_Sucess_Screen;
import in.loanwiser.partnerapp.R;
//import in.loanwiser.partnerapp.Step_Changes_Screen.Applicant_fragment;
import in.loanwiser.partnerapp.Step3_Changes.Automater_Under_write;
import in.loanwiser.partnerapp.Step3_Changes.Elegibility_Report;
import in.loanwiser.partnerapp.Step_Changes_Screen.CRIF_Report_Activity_PDF_View;
import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklist_Fragment;
//import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklist_new;
import in.loanwiser.partnerapp.Step_Changes_Screen.FragmentApplicant;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Screen_revamp;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Screen_revamp_Pl_BL;
import in.loanwiser.partnerapp.Step_Changes_Screen.ViableBankActivity;


public class Home extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    String  email,username,user_id,mobileno,transaction_id, subtask_id,applicant_id,b2b_userid,sub_taskid,step_status,
            Applicant_Statues,new_user,payment;
    private Context mCon = this;
    private String S1,S2,S3,S4,S5;
    Toolbar toolbar;
    private AlertDialog progressDialog;
    private CardView app,doc,offer;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private ImageView app_doc_img,app_info_img,app_info_img11,app_interview_img,app_offer_img,app_track_img,bank_statement_img,
            document_img1,eligibility_statues_img,payment_img1,step2_paymentstatus;
    private TextView customerinterview,offerdetails,app_doc_message,app_info_message;
    private LinearLayout lead_cr_statues,if_vaibility_faild,bank_is_yes;
    CardView Applicant_info_ly,Document_check_list,Document_Upload,offer_generation,
            Viability_Check,eligibility_check,viability_Report,Credit_REport_Generation,Paymet,
            CRIF_Check,step2_card,Bank_statement_Upload,Submiteed_to_Loanwiser,document_check__Upload,
            document_Upload,ban_available;

    FragmentApplicant fragmentApplicant=null;

    ImageView viability_check_img2,eligibility_check_img,viability_report_image,Credite_report_image,
            payment_img,crif_img,credite_report_img;

    String viability,eligibility,credit_request,payment_STEP2,viability_report,viability_report_URL,
    document_checklist,document_upload,loan_type_id,loan_type,crif_status,submit_loanwiser,offer_Details,
            part_compstatus,part_subcompstatus, loanwiser_submit_str,loanwiser_submit_str1,loan_status,
            reject_status,bank_statement,payment_eligility,crif_report,
            eligibility_status,applicant_count,property_identified,crif_report_gen,auto_reject_status,
            viablebank_str;
    AppCompatImageView call_phone;

    AppCompatTextView lead_name,mobile_no,Loan_amount,loan_type_,loan_submit_statues1,viability_statues,
            eligibility_check_cmp,payment_statues_comp,crif_report_cmp,viability_report_cmp,loan_statues,
            sub_to_loanwiser,crif_report_view,bank_stm,step3_bank_availability_txt1,document_text,eligibility_Report,
            document_check_text,pending_ask_List,lead_name_id,payment_statues_comp1;

    ImageView step2_viablitystatus,step3_paymentstatus_img,step2_reportstatus,step2crifstatus,bankstate_status,documentupload_status,
            step3_Bank_Avalability_img,eligiblitity_status,step3_Automated_img,step3_elegibility_img;
    AppCompatButton step2viablity_button,step3payment_but,step2report_but,step2crif_but,
            bankstatment_but,document_upload_button,
            step3_Bank_Availability,eligibility_but,document_check_button,step2payment_but;

        AppCompatButton view_ask,step3Automated_under_write_btn,step3Elegibility_write_btn;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;
    private ImageView loan_statues_,loan_statues_1,step3_statue,step3_statue1,step2_image_completed,
            step3_image_completed,step4_image_completed;
    private LinearLayout Loan_Submit_tracStatues,step3_sub_staus,Loan_Statue_submit_layout;

    PermissionUtils permissionUtils;
    String applicant_empstates,coapplicant_empstates,property_empstates;
    String[] descriptionData = {"Submit to Loanwiser","Send to Bank", "Sanctioned", "Disbursed"};
    StateProgressBar stateProgressBar,stateProgressBar1,stateProgressBar2,state_progressbar_submit;

    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    AppCompatTextView In_Progress,paymenttxt,Doc_status,Approved,bank_statenent,document_checklist_revamp,
            document_upload_revamp,payment1,step_1detail,ask_txt,
            loanamounttxt,loantypetext,loan_statustxt,step_comp,askthis,app_interview,automate_under_W_txt1,Elegibility_W_txt1;
    AppCompatTextView   paymentstatus,loan_submit_statues_txt1,Document_verification_txt1,Applied_to_Bank_txt1;
    private ExpandableListView exp_leaseoffer;
    private ExpandableListAdapter1 expandableListAdapter;
    String all,bank_eligible;
    ImageView loan_submit_statues_img,Document_verification_img,applied_to_bank_img;
    AppCompatButton view_activity;

    String step2_statues,step3_statues;
    String step_3_document_upload,step_3_bankwise_availability_check,step_3_payment,
    step_3_automated_underwriting,step_3_eligibility_status;
    String viable_url;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setTitle("Lead Detail Dashboard");
        setSupportActionBar(toolbar);
      //  Objs.ac.ApplyFont(toolbar, mCon);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swife);
        mSwipeRefreshLayout.setOnRefreshListener(Home.this);
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
        Viablityreport();
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

        loan_submit_statues_img = (ImageView) findViewById(R.id.loan_submit_statues_img);
        step2_image_completed = (ImageView) findViewById(R.id.step2_image_completed);
        step3_image_completed = (ImageView) findViewById(R.id.step3_image_completed);
        step4_image_completed = (ImageView) findViewById(R.id.step4_image_completed);
        Document_verification_img = (ImageView) findViewById(R.id.Document_verification_img);
        applied_to_bank_img = (ImageView) findViewById(R.id.applied_to_bank_img);
        loan_submit_statues_txt1 = (AppCompatTextView) findViewById(R.id.loan_submit_statues_txt1);
        Document_verification_txt1 = (AppCompatTextView) findViewById(R.id.Document_verification_txt1);
        Applied_to_Bank_txt1 = (AppCompatTextView) findViewById(R.id.Applied_to_Bank_txt1);
        view_activity = (AppCompatButton) findViewById(R.id.view_activity);
        bank_is_yes = (LinearLayout) findViewById(R.id.bank_is_yes);


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

        view_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, ActivityStreamActivity.class);
                startActivity(intent);

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
                                applicant_id =  jsonObject2.getString("applicant_count");
                               // payment =  jsonObject2.getString("payment");
                                new_user =  jsonObject2.getString("new_user");
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

                                           // applicant_id = J.getString("user_type");


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
       // Credit_REport_Generation = (CardView) findViewById(R.id.Credit_REport_Generation);
        Bank_statement_Upload = (CardView) findViewById(R.id.Bank_statement_Upload);

        step2_card = (CardView) findViewById(R.id.step2_card);

       /* DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;*/
        exp_leaseoffer = (ExpandableListView) findViewById(R.id.lvExp);
      //  exp_leaseoffer.setIndicatorBounds(140, 150);
       // exp_leaseoffer.setIndicatorBounds();

        lead_name = (AppCompatTextView) findViewById(R.id.lead_name);
        lead_name_id = (AppCompatTextView) findViewById(R.id.lead_name_id);
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
        payment_statues_comp1 = (AppCompatTextView) findViewById(R.id.payment_statues_comp1);
        crif_report_cmp = (AppCompatTextView) findViewById(R.id.crif_report_cmp);
        viability_report_cmp = (AppCompatTextView) findViewById(R.id.viability_report_cmp);
        bank_stm = (AppCompatTextView) findViewById(R.id.bank_stm);
        step3_bank_availability_txt1 = (AppCompatTextView) findViewById(R.id.step3_bank_availability_txt1);
        document_text = (AppCompatTextView) findViewById(R.id.document_text);
        document_check_text = (AppCompatTextView) findViewById(R.id.document_check_text);
      //  crif_report_view = (AppCompatTextView) findViewById(R.id.crif_report_view);
        eligibility_Report = (AppCompatTextView) findViewById(R.id.eligibility_Report);

        lead_cr_statues = (LinearLayout) findViewById(R.id.lead_cr_statues);
        if_vaibility_faild = (LinearLayout) findViewById(R.id.if_vaibility_faild);

        app_doc_img = (ImageView) findViewById(R.id.app_doc_img);
        app_info_img = (ImageView) findViewById(R.id.app_info_img);
        app_info_img11 = (ImageView) findViewById(R.id.app_info_img11);
        app_interview_img = (ImageView) findViewById(R.id.app_interview_img);
        document_img1 = (ImageView) findViewById(R.id.document_img1);
      //  document_check_list_status = (ImageView) findViewById(R.id.document_check_list_status);
        payment_img1 = (ImageView) findViewById(R.id.payment_img1);
        step2_paymentstatus = (ImageView) findViewById(R.id.step2_paymentstatus);

        automate_under_W_txt1 = (AppCompatTextView) findViewById(R.id.automate_under_W_txt1);


        Elegibility_W_txt1 = (AppCompatTextView) findViewById(R.id.Elegibility_W_txt1);


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
        step3_paymentstatus_img=findViewById(R.id.step3_paymentstatus_img);
        step3_Automated_img=findViewById(R.id.step3_Automated_img);
        step3_elegibility_img=findViewById(R.id.step3_elegibility_img);
        step2_reportstatus=findViewById(R.id.step2_reportstatus);
      //  step2crifstatus=findViewById(R.id.step2crifstatus);
        bankstate_status=findViewById(R.id.bankstate_status);
        documentupload_status=findViewById(R.id.documentupload_status);
        step3_Bank_Avalability_img=findViewById(R.id.step3_Bank_Avalability_img);
        eligiblitity_status=findViewById(R.id.eligiblitity_status);


        step2viablity_button=findViewById(R.id.step2viablity_button);
      
      
     
        step2report_but=findViewById(R.id.step2report_but);
      //  step2crif_but=findViewById(R.id.step2crif_but);
      
       
        Loan_Statue_submit_layout=findViewById(R.id.Loan_Statue_submit_layout);
        document_check__Upload=findViewById(R.id.document_check__Upload);
        document_Upload=findViewById(R.id.document_Upload);
        ban_available=findViewById(R.id.ban_available);
        Submiteed_to_Loanwiser=findViewById(R.id.Submiteed_to_Loanwiser);
      
        eligibility_but=findViewById(R.id.eligibility_but);
        document_check_button=findViewById(R.id.document_check_button);

        loan_statues_=findViewById(R.id.loan_statues_);
        loan_statues_1=findViewById(R.id.loan_statues_1);
        Loan_Submit_tracStatues=findViewById(R.id.Loan_Submit_tracStatues);

        step3_statue=findViewById(R.id.step3_statue);
        step3_statue1=findViewById(R.id.step3_statue1);
        step3_sub_staus=findViewById(R.id.step3_sub_staus);
        
       // STEP2

        step2payment_but=findViewById(R.id.step2payment_but);
        //Step 3 button action
        bankstatment_but=findViewById(R.id.bankstatment_but);
        document_upload_button=findViewById(R.id.document_upload_button);
        step3_Bank_Availability=findViewById(R.id.step3_Bank_Availability);
        step3payment_but=findViewById(R.id.step3payment_but);
        step3Automated_under_write_btn=findViewById(R.id.step3Automated_under_write_btn);
        step3Elegibility_write_btn=findViewById(R.id.step3Elegibility_write_btn);
        

        loan_statues_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loan_statues_.setVisibility(View.GONE);
                loan_statues_1.setVisibility(View.VISIBLE);
                Loan_Submit_tracStatues.setVisibility(View.VISIBLE);
            }
        });
        loan_statues_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loan_statues_1.setVisibility(View.GONE);
                loan_statues_.setVisibility(View.VISIBLE);
                Loan_Submit_tracStatues.setVisibility(View.GONE);
            }
        });

        step3_statue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                step3_statue.setVisibility(View.GONE);
                step3_statue1.setVisibility(View.VISIBLE);
                step3_sub_staus.setVisibility(View.VISIBLE);
            }
        });




        step3_statue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                step3_statue1.setVisibility(View.GONE);
                step3_statue.setVisibility(View.VISIBLE);
                step3_sub_staus.setVisibility(View.GONE);
            }
        });

        //new
        In_Progress=findViewById(R.id.In_Progress);
        paymenttxt=findViewById(R.id.paymenttxt);
        Doc_status=findViewById(R.id.Doc_status);

        bank_statenent=findViewById(R.id.bank_statenent);
        document_checklist_revamp=findViewById(R.id.document_checklist_revamp);
        document_upload_revamp=findViewById(R.id.document_upload_revamp);
        payment1=findViewById(R.id.payment1);
        step_1detail=findViewById(R.id.step_1detail);
        ask_txt=findViewById(R.id.ask_txt);
        loanamounttxt=findViewById(R.id.loanamounttxt);
        loantypetext=findViewById(R.id.loantypetext);
        loan_statustxt=findViewById(R.id.loan_statustxt);
        paymentstatus=findViewById(R.id.paymentstatus);
        step_comp=findViewById(R.id.step_comp);
        askthis=findViewById(R.id.askthis);
        app_interview=findViewById(R.id.app_interview);



        view_ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this,Ask_user_Dashboard_Activity.class);
                startActivity(intent);
                finish();
            }
        });


        step2viablity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(viability.equals("completed"))
                {
                    //  Intent intent = new Intent(Home.this, Viability_Activity_Data_View.class);
                    //vehicle loan type
                 if(loan_type_id.equals("22"))
                {
                    if(new_user.equals("0"))
                    {
                        Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Data_revamp_old.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                    }else
                    {
                        Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Data_revamp.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                    }

                    //  finish();
                }
                else if (loan_type_id.equals("31")){
                     if(new_user.equals("0"))
                     {
                         Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Data_revamp_old.class);
                         intent.putExtra("user_id", user_id);
                         intent.putExtra("transaction_id", transaction_id);
                         startActivity(intent);
                     }else
                     {
                         Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Data_revamp.class);
                         intent.putExtra("user_id", user_id);
                         intent.putExtra("transaction_id", transaction_id);
                         startActivity(intent);
                     }
                }
                else if (loan_type_id.equals("32")){
                     if(new_user.equals("0"))
                     {
                         Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Data_revamp_old.class);
                         intent.putExtra("user_id", user_id);
                         intent.putExtra("transaction_id", transaction_id);
                         startActivity(intent);
                     }else
                     {
                         Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Data_revamp.class);
                         intent.putExtra("user_id", user_id);
                         intent.putExtra("transaction_id", transaction_id);
                         startActivity(intent);
                     }
                }
                //CreditCard loan type
                else if(loan_type_id.equals("33"))
                {
                    if(new_user.equals("0"))
                    {
                        Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Data_revamp_old.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                    }else
                    {
                        Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Data_revamp.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                    }
                    //  finish();
                }
                else
                {
                    if(new_user.equals("0"))
                    {
                        Intent intent = new Intent(Home.this, Viability_Data_revamp_old.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                    }else
                    {
                        Intent intent = new Intent(Home.this, Viability_Data_revamp.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                    }

                }
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

                    }  //vehicle loan type
                    else if(loan_type_id.equals("22"))
                    {
                        Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Screen_revamp.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                        //  finish();
                    }
                    else if (loan_type_id.equals("31")){
                        Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Screen_revamp.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                    }
                    else if (loan_type_id.equals("32")){
                        Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Screen_revamp.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                    }
                    //CreditCard loan type
                    else if(loan_type_id.equals("33"))
                    {
                        Intent intent = new Intent(Home.this, in.loanwiser.partnerapp.Credit_Vehicle_.Viability_Screen_revamp_Pl_BL.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                        //  finish();
                    }
                    else
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
                    if(viablebank_str.equals("completed"))
                    {
                        Intent intent = new Intent(Home.this, ViableBankActivity.class);
                        startActivity(intent);
                    }else  if(viablebank_str.equals("pending"))
                    {
                        Intent intent = new Intent(Home.this, ViableBankActivity.class);
                        startActivity(intent);
                        // finish();
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        step2report_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viablebank_str.equals("completed"))
                {
                    if((viability.equals("completed") && viablebank_str.equals("completed")) || (viability.equals("completed")))  {
                        if(reject_status.equals("1"))
                        {
                            String report="Viability Report";
                            Intent intent = new Intent(Home.this, MainActivity.class);
                            intent.putExtra("viability_report_URL", viable_url);
                            intent.putExtra("report", report);
                            startActivity(intent);
                        }else
                        {
                            String report="Viability Report";
                            Intent intent = new Intent(Home.this, MainActivity.class);
                            intent.putExtra("viability_report_URL", viable_url);
                            intent.putExtra("report", report);
                            startActivity(intent);
                        }
                    }else
                    {
                        if(crif_report_gen.equals("completed") && crif_report.equals("completed") )
                        {
                            String report="Viability Report";
                            Intent intent = new Intent(Home.this, MainActivity.class);
                            intent.putExtra("viability_report_URL", viability_report_URL);
                            intent.putExtra("report", report);
                            startActivity(intent);
                        }else
                        {
                            Intent intent = new Intent(Home.this, Payment_Sucess_Screen.class);
                            startActivity(intent);
                        }
                    }
                }else  if(viablebank_str.equals("pending"))
                {
                    if (reject_status.equals("1")) {
                        String report="Viability Report";
                        Intent intent = new Intent(Home.this, MainActivity.class);
                        intent.putExtra("viability_report_URL", viability_report_URL);
                        intent.putExtra("report", report);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        bankstatment_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(step2_statues.equals("completed"))
                {
                    Intent intent = new Intent(Home.this, Upload_Activity_Bank.class);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }


            }
        });
        document_upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(step2_statues.equals("completed"))
                {
                    Intent intent = new Intent(Home.this, Applicant_Doc_Details_revamp.class);
                    intent.putExtra("jsonArray", Applicant_Statues.toString());
                    startActivity(intent);
                }else
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }


            }
        });
        step3_Bank_Availability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(step_3_document_upload.equals("pending"))
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }else
                {
                    Document_generate_checklist_rule();

                }

            }
        });
        step3payment_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(step_3_document_upload.equals("pending"))
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }else
                {
                    if(step_3_bankwise_availability_check.equals("pending"))
                    {
                        Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                    }else
                    {
                        if(step_3_payment.equals("pending")|| payment.equals("error"))
                        {
                            Intent intent = new Intent(Home.this, PaymentActivity.class);
                            startActivity(intent);
                        }else
                        {
                           // Paymentplanshow();
                            Intent intent = new Intent(Home.this, Payment_Sucess_Screen.class);
                            startActivity(intent);
                        }

                    }
                }
            }
        });

        step3Automated_under_write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(step_3_document_upload.equals("pending"))
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }else
                {
                    if(step_3_bankwise_availability_check.equals("pending"))
                    {
                        Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                    }else
                    {
                        if(step_3_payment.equals("pending") || payment.equals("error"))
                        {
                            Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                        }else
                        {
                            Intent intent = new Intent(Home.this, Automater_Under_write.class);
                            startActivity(intent);
                        }
                    }
                }


            }
        });
        step3Elegibility_write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(step_3_document_upload.equals("pending"))
                {
                    Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                }else
                {
                    if(step_3_bankwise_availability_check.equals("pending"))
                    {
                        Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                    }else
                    {
                        if(step_3_payment.equals("pending"))
                        {
                            Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                        }else
                        {
                            if(step_3_automated_underwriting.equals("pending"))
                            {
                                Toast.makeText(getApplicationContext(),"Please Complete The Previous Steps To Proceed!!!", Toast.LENGTH_SHORT).show();

                            }else
                            {
                                Intent intent = new Intent(Home.this, Elegibility_Report.class);
                                startActivity(intent);
                            }
                        }
                    }
                }


            }
        });

    }


    private void Viablityreport(){
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            Log.i("TAG", "Docverifyrule "+J.toString());
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.report_links, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        Log.e("respose docverifyrule", object.toString());
                        try {
                            viable_url=object.getString("viable_url");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
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
    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
    private void Criffail() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.criffail);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        ImageView closebtn1=(ImageView) dialog.findViewById(R.id.closebtn1);
        LinearLayout background=(LinearLayout) dialog.findViewById(R.id.background);
        background.setBackground(getResources().getDrawable(R.drawable.capsul_button_rect_viability));
        closebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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

        Objs.a.OutfitNormalFontStyle(mCon, R.id.In_Progress);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.viability_statues);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.paymenttxt);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.payment_statues_comp);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.payment_statues_comp1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Doc_status);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.viability_report_cmp);

      //  Objs.a.OutfitNormalFontStyle(mCon, R.id.crif_report_view);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.bank_statenent);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.bank_stm);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.document_checklist_revamp);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.document_check_text);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.document_upload_revamp);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.document_text);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.payment1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.step3_bank_availability_txt1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.eligibility_report);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.step_1detail);
      //  Objs.a.OutfitNormalFontStyle(mCon, R.id.view_ask);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.ask_txt);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.ask_txt1_pending);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.lead_name);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.lead_name_id);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.mobile_no);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.loanamounttxt);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.loantypetext);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.loan_statustxt);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.paymentstatus);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Loan_amount);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.loan_type_);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.loan_submit_statues1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.step_comp);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.askthis);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_interview);
    }
    private void Document_generate_checklist_rule() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            // J.put("transaction_id", "61359");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("submit_loanwiser", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.generate_docuverifyrule, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Documnet_upload_Status1", String.valueOf(response));
                        //{"request":{"transaction_id":"10194"},"response":true,"status":"success"}
                        try {

                            if(response.getString("status").equals("success")){

                                Intent intent = new Intent(Home.this, Document_Availability_Check.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(getApplicationContext(),"Something went wrong, Please check!!!", Toast.LENGTH_SHORT).show();

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
                               String paymentplan_show=jsonObject1.getString("payment_plan");
                               String pending_status=jsonObject1.getString("pending_status");
                               String pending_status_code=jsonObject1.getString("pending_status_code");
                               String curr_status_name=jsonObject1.getString("curr_status_name");
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
                               String user_id = Pref.getUSERID(getApplicationContext());
                               String App_id= "APP-"+user_id;
                               lead_name_id.setText(App_id);
                               Log.e("App_id",App_id);
                               Loan_amount.setText("\u20B9"+loan_amount);
                               Loan_amount.setTextColor(Color.parseColor("#484848"));
                               loan_type_.setText(loan_type);
                               loan_type_.setTextColor(Color.parseColor("#484848"));
                               loan_submit_statues1.setText(loan_status);
                               loan_submit_statues1.setTextColor(Color.parseColor("#484848"));
                               paymentstatus.setText(paymentplan_show);
                               if (pending_status_code.equals("1")){
                                   sub_to_loanwiser.setText("\u25CF"+" "+"Pending under you");
                                   sub_to_loanwiser.setTextColor(Color.parseColor("#FF9201"));
                               }else if (pending_status_code.equals("2")){
                                   sub_to_loanwiser.setText("Pending with Loanwiser");
                                   sub_to_loanwiser.setTextColor(Color.parseColor("#FF9201"));
                               }else {

                                   sub_to_loanwiser.setText(curr_status_name);
                                   sub_to_loanwiser.setTextColor(Color.parseColor("#FF9201"));
                               }
                               Pref.putProperty_id(mCon,property_identified);
                               Pref.putCoAPPAVAILABLE(mCon,applicant_count);
                               String applicant = Pref.getCoAPPAVAILABLE(mCon);
                               Log.e("the Co", applicant);
                               if(curr_status.equals("6"))
                               {
                                 /*  loan_statues.setVisibility(View.VISIBLE);
                                   stateProgressBar.setVisibility(View.VISIBLE);
                                   stateProgressBar1.setVisibility(View.GONE);
                                   stateProgressBar2.setVisibility(View.GONE);*/
                                   loan_statues.setVisibility(View.GONE);
                                   stateProgressBar.setVisibility(View.GONE);
                                   stateProgressBar1.setVisibility(View.GONE);
                                   stateProgressBar2.setVisibility(View.GONE);
                               }else if(curr_status.equals("7"))
                               {
                                 /*  loan_statues.setVisibility(View.VISIBLE);
                                   stateProgressBar.setVisibility(View.GONE);
                                   stateProgressBar1.setVisibility(View.VISIBLE);
                                   stateProgressBar2.setVisibility(View.GONE);*/
                                   loan_statues.setVisibility(View.GONE);
                                   stateProgressBar.setVisibility(View.GONE);
                                   stateProgressBar1.setVisibility(View.GONE);
                                   stateProgressBar2.setVisibility(View.GONE);
                               }else if(curr_status.equals("8"))
                               {
                                  /* loan_statues.setVisibility(View.VISIBLE);
                                   stateProgressBar.setVisibility(View.GONE);
                                   stateProgressBar1.setVisibility(View.GONE);
                                   stateProgressBar2.setVisibility(View.VISIBLE);*/
                                   loan_statues.setVisibility(View.GONE);
                                   stateProgressBar.setVisibility(View.GONE);
                                   stateProgressBar1.setVisibility(View.GONE);
                                   stateProgressBar2.setVisibility(View.GONE);
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
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
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
                              auto_reject_status = jsonObject1.getString("auto_reject_status");

                              step2_statues = jsonObject2.getString("status");

                              step3_statues = jsonObject3.getString("status");





                             if(step2_statues.equals("completed"))
                             {

                                 step2_image_completed.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                 lead_cr_statues.setVisibility(View.GONE);
                                 app_info_img.setVisibility(View.VISIBLE);
                                 app_info_img11.setVisibility(View.GONE);
                             }else
                             {
                                 step2_image_completed.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                             }

                            if(step3_statues.equals("completed"))
                            {
                                step3_image_completed.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                            }else
                            {
                                step3_image_completed.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                            }



                             JSONObject step2_sub_statues = jsonObject2.getJSONObject("sub_status");

                             JSONObject step3_sub_statues = jsonObject3.getJSONObject("sub_status");
                             JSONObject step4_sub_statues = jsonObject4.getJSONObject("sub_status");

                            viablebank_str = step2_sub_statues.getString("viabile_bank");
                             viability = step2_sub_statues.getString("viability");
                            step_3_automated_underwriting = step3_sub_statues.getString("automated_underwriting");

                            payment_STEP2 = step2_sub_statues.getString("payment");

                            if(new_user.equals("0"))
                            {

                                if(step_3_automated_underwriting.equals("pending"))
                                {
                                    if(!payment.equals("error"))
                                    {
                                        if(!reject_status.equals("1"))
                                        {
                                             //   upload_Status();
                                            Bank_analysis_ErrorStatus();
                                        }

                                    }else
                                    {
                                        step3_paymentstatus_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                        payment_statues_comp1.setText("Pending under you");
                                        payment_statues_comp1.setTextColor(Color.parseColor("#FF9201"));
                                        step3payment_but.setBackgroundResource(R.drawable.but_shape);
                                        step3payment_but.setText("Complete now");
                                    }
                                }
                            }

                             viability_report = step2_sub_statues.getString("viability_report");

                          //  crif_status = step2_sub_statues.getString("crif_status");
                            crif_report = step2_sub_statues.getString("crif_report");
                            crif_report_gen = step2_sub_statues.getString("crif_report_gen");

                            bank_statement = step3_sub_statues.getString("bank_statement");
                            bank_eligible = step3_sub_statues.getString("bank_eligible");
                            payment_eligility = step3_sub_statues.getString("payment");
                            eligibility_status = step3_sub_statues.getString("eligibility_status");

                            offer_Details = step4_sub_statues.getString("offer_generate");

                             document_checklist = step3_sub_statues.getString("document_checklist");
                            Log.i("TAG", "onResponse:document_checklist "+document_checklist);


                            document_upload = step3_sub_statues.getString("document_upload");

                             step_3_document_upload = step3_sub_statues.getString("document_upload");

                            step_3_bankwise_availability_check = step3_sub_statues.getString("bankwise_availability_check");
                            step_3_payment = step3_sub_statues.getString("payment");
                            step_3_automated_underwriting = step3_sub_statues.getString("automated_underwriting");
                            step_3_eligibility_status = step3_sub_statues.getString("eligibility_status");


                            loanwiser_submit_str = loanwiser_submit.getString("submit");


                            ///Step2 statues


                            if(viability.equals("completed"))
                            {
                                viability_check_img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                step2_viablitystatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                viability_statues.setText("Completed");
                                viability_statues.setTextColor(Color.parseColor("#00CEB4"));
                                step2viablity_button.setBackgroundResource(R.drawable.but_shape_blue);
                                step2viablity_button.setText("View");
                            }else
                            {
                                viability_check_img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                step2_viablitystatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                viability_statues.setText("Pending under you");
                                viability_statues.setTextColor(Color.parseColor("#FF9201"));
                                step2viablity_button.setBackgroundResource(R.drawable.but_shape);
                                step2viablity_button.setText("Complete Now");
                                bankstatment_but.setBackgroundResource(R.drawable.but_shape_gray);
                                document_upload_button.setBackgroundResource(R.drawable.but_shape_gray);
                            }

                            if(viablebank_str.equals("completed"))
                            {
                                if (viability.equals("completed")) {
                                    payment_statues_comp.setText("Completed");
                                    payment_statues_comp.setTextColor(Color.parseColor("#00CEB4"));
                                    step2payment_but.setBackgroundResource(R.drawable.but_shape_blue);
                                    step2_paymentstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                    step2payment_but.setText("View");
                                    step2payment_but.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            //Paymentplanshow();
                                            Intent in=new Intent(Home.this,ViableBankActivity.class);
                                            startActivity(in);
                                        }
                                    });
                                }
                                else{
                                    Paymet.setEnabled(true);
                                    // payment_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    payment_statues_comp.setText("Pending under you");
                                    payment_statues_comp.setTextColor(Color.parseColor("#FF9201"));
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
                                    payment_statues_comp.setTextColor(Color.parseColor("#FF9201"));
                                    step2_paymentstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    step2payment_but.setBackgroundResource(R.drawable.but_shape_gray);
                                    step2payment_but.setText("Complete Now");
                                    step2payment_but.setFocusable(false);
                                }
                            }


                            if(viability_report.equals("completed"))
                            {
                                if (viablebank_str.equals("completed")) {
                                    if (reject_status.equals("1")) {
                                        viability_report_cmp.setText("click to view reject status");
                                        viability_Report.setEnabled(true);
                                        if_vaibility_faild.setVisibility(View.VISIBLE);
                                        step2_reportstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                        if(auto_reject_status.equals("1"))
                                        {
                                            loan_submit_statues1.setText("Auto Rejected");
                                            sub_to_loanwiser.setText("\u25CF"+" "+"Auto Rejected");
                                        }else
                                        {
                                            loan_submit_statues1.setText("Loan Rejected");
                                            sub_to_loanwiser.setText("\u25CF"+" "+"Loan Rejected");
                                        }
                                        loan_submit_statues1.setTextColor(Color.parseColor("#FF9201"));
                                        step2report_but.setBackgroundResource(R.drawable.but_shape_reject);
                                        step2report_but.setText("View");
                                    } else {
                                        if_vaibility_faild.setVisibility(View.VISIBLE);
                                        viability_Report.setEnabled(true);
                                        viability_report_image.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                        step2_reportstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                        viability_report_cmp.setText("Completed");
                                        viability_report_cmp.setTextColor(Color.parseColor("#00CEB4"));
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
                                    if(auto_reject_status.equals("1"))
                                    {
                                        loan_submit_statues1.setText("Auto Rejected");
                                        sub_to_loanwiser.setText("\u25CF"+" "+"Auto Rejected");
                                    }else
                                    {
                                        loan_submit_statues1.setText("Loan Rejected");
                                        sub_to_loanwiser.setText("\u25CF"+" "+"Loan Rejected");
                                    }
                                    loan_submit_statues1.setTextColor(Color.parseColor("#FF9201"));
                                    step2_reportstatus.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    step2report_but.setBackgroundResource(R.drawable.but_shape_reject);
                                    step2report_but.setText("View");
                                    step2report_but.setVisibility(View.VISIBLE);
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
                                    viability_report_cmp.setTextColor(Color.parseColor("#FF9201"));
                                    step2report_but.setText("Complete Now");
                                }
                            }



                            Loan_submit_statues();

                            ///STEP 3 Statues Statrs/////
                            if(step_3_document_upload.equals("pending"))
                            {

                                bankstate_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                bank_stm.setText("Pending under you");
                                bank_stm.setTextColor(Color.parseColor("#FF9201"));
                                bankstatment_but.setBackgroundResource(R.drawable.but_shape);
                                bankstatment_but.setText("Complete now");

                                documentupload_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                document_text.setText("Pending under you");
                                document_text.setTextColor(Color.parseColor("#FF9201"));
                                document_upload_button.setBackgroundResource(R.drawable.but_shape);
                                document_upload_button.setText("Complete now");

                                Submiteed_to_Loanwiser.setVisibility(View.VISIBLE);

                            }else
                            {

                                bankstate_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                bank_stm.setText("Completed");
                                bank_stm.setTextColor(Color.parseColor("#00CEB4"));
                                bankstatment_but.setBackgroundResource(R.drawable.but_shape_blue);
                                bankstatment_but.setText("View");

                                documentupload_status.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                document_text.setText("Completed");
                                document_text.setTextColor(Color.parseColor("#00CEB4"));
                                document_upload_button.setBackgroundResource(R.drawable.but_shape_blue);
                                document_upload_button.setText("View");
                              //
                                Loan_Statue_submit_layout.setVisibility(View.VISIBLE);
                            }

                            if(step_3_bankwise_availability_check.equals("pending"))
                            {
                                step3_Bank_Avalability_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                step3_bank_availability_txt1.setText("Pending under you");
                                step3_bank_availability_txt1.setTextColor(Color.parseColor("#FF9201"));
                                step3_Bank_Availability.setBackgroundResource(R.drawable.but_shape);
                                step3_Bank_Availability.setText("Complete now");
                            }else
                            {
                                step3_Bank_Avalability_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                step3_bank_availability_txt1.setText("Completed");
                                step3_bank_availability_txt1.setTextColor(Color.parseColor("#00CEB4"));
                                step3_Bank_Availability.setBackgroundResource(R.drawable.but_shape_blue);
                                step3_Bank_Availability.setText("View");
                            }

                            if(step_3_payment.equals("pending"))
                            {
                                step3_paymentstatus_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                payment_statues_comp1.setText("Pending under you");
                                payment_statues_comp1.setTextColor(Color.parseColor("#FF9201"));
                                step3payment_but.setBackgroundResource(R.drawable.but_shape);
                                step3payment_but.setText("Complete now");
                            }else
                            {
                                if(!payment.equals("error"))
                                {
                                    step3_paymentstatus_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                    payment_statues_comp1.setText("Completed");
                                    payment_statues_comp1.setTextColor(Color.parseColor("#00CEB4"));
                                    step3payment_but.setBackgroundResource(R.drawable.but_shape_blue);
                                    step3payment_but.setText("View");
                                }else
                                {
                                    step3_paymentstatus_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                    payment_statues_comp1.setText("Pending under you");
                                    payment_statues_comp1.setTextColor(Color.parseColor("#FF9201"));
                                    step3payment_but.setBackgroundResource(R.drawable.but_shape);
                                    step3payment_but.setText("Complete now");
                                }

                            }

                            if(step_3_automated_underwriting.equals("pending"))
                            {
                                step3_Automated_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                automate_under_W_txt1.setText("Pending under you");
                                automate_under_W_txt1.setTextColor(Color.parseColor("#FF9201"));
                                step3Automated_under_write_btn.setBackgroundResource(R.drawable.but_shape);
                                step3Automated_under_write_btn.setText("Complete now");
                            }else
                            {
                                step3_Automated_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                automate_under_W_txt1.setText("Completed");
                                automate_under_W_txt1.setTextColor(Color.parseColor("#00CEB4"));
                                step3Automated_under_write_btn.setBackgroundResource(R.drawable.but_shape_blue);
                                step3Automated_under_write_btn.setText("View");
                            }

                            if(step_3_eligibility_status.equals("pending"))
                            {
                                step3_elegibility_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                Elegibility_W_txt1.setText("Pending under you");
                                Elegibility_W_txt1.setTextColor(Color.parseColor("#FF9201"));
                                step3Elegibility_write_btn.setBackgroundResource(R.drawable.but_shape);
                                step3Elegibility_write_btn.setText("Complete now");
                            }else
                            {
                                step3_elegibility_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                Elegibility_W_txt1.setText("Completed");
                                Elegibility_W_txt1.setTextColor(Color.parseColor("#00CEB4"));
                                step3Elegibility_write_btn.setBackgroundResource(R.drawable.but_shape_blue);
                                step3Elegibility_write_btn.setText("View");
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

    private void upload_Status() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.transaction_id, Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("comp_status", "3");
            J.put("subcomp_status", "3");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("submit_loanwiser", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.status_update, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Documnet_upload_Status1", String.valueOf(response));
                        //{"request":{"transaction_id":"10194"},"response":true,"status":"success"}
                      /*  Intent intent = new Intent(Home.this, Home.class);
                        startActivity(intent);
                        finish();*/

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
    private void Bank_analysis_ErrorStatus() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.bank_statement_analysis_error);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView bankstatement_message=(AppCompatTextView) dialog.findViewById(R.id.bankstatement_message);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

               /* Intent intent = new Intent(Home.this,Upload_Activity_Bank.class);
                startActivity(intent);
                finish();*/

            }
        });



        if (!dialog.isShowing()) {
            dialog.show();
        }

    }
    public void ASK_Count_Display() {
        final JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            //  J.put("b2b_id", Pref.getID(mCon));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            //J.put("user_id", "51647");
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

                                if(pending_count.equals("0")){
                                    view_ask.setVisibility(View.GONE);
                                    pending_ask_List.setText("Pending Ask" +"("+pending_count+")");
                                }else{
                                    view_ask.setVisibility(View.VISIBLE);
                                    pending_ask_List.setText("Pending Ask" +"("+pending_count+")");
                                }

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
       /* Typeface font = Typeface.createFromAsset(Home.this.getAssets(), "segoe_ui.ttf");
        planchoose.setTypeface(font);
        transferdby.setTypeface(font);
        benefitcoin.setTypeface(font);
        paydate.setTypeface(font);
        textplande.setTypeface(font);
        texttransfer.setTypeface(font);
        textamount.setTypeface(font);
        textbene.setTypeface(font);
        paydates.setTypeface(font);
        paydettext.setTypeface(font);*/
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



    private void Loan_submit_statues() {
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", transaction_id);
           // J.put("transaction_id", 53277);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Request _statues ", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.bank_status_fetch, J,
                new Response.Listener<JSONObject>() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Loawiser_Submit", object.toString());

                        listDataHeader = new ArrayList<String>();
                        listDataChild = new HashMap<String, List<String>>();
                        try
                        {
                           JSONObject result=object.getJSONObject("result");


                            submit_loanwiser = result.getString("submit_loanwiser");
                           String doc_verification = result.getString("doc_verification");
                           String apply_completion_status = result.getString("apply_completion_status");



                           if(submit_loanwiser.equals("1"))
                            {
                                String statues = "0";
                                Pref.putSTATUES_FLAG(getApplicationContext(),statues);

                                String submit_date = result.getString("submit_date");
                                loan_submit_statues_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                loan_submit_statues_txt1.setText("Completed "+"("+ submit_date+")");
                                loan_submit_statues_txt1.setTextColor(Color.parseColor("#00ceb4"));

                            }else
                            {
                                String statues = "1";
                                Pref.putSTATUES_FLAG(getApplicationContext(),statues);

                                loan_submit_statues_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                loan_submit_statues_txt1.setText("Pending under you");
                                loan_submit_statues_txt1.setTextColor(Color.parseColor("#FF9201"));
                            }
                            if(doc_verification.equals("1"))
                            {
                                String doc_verification_date = result.getString("doc_verification_date");
                                Document_verification_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                Document_verification_txt1.setText("Completed "+"("+ doc_verification_date+")");
                                Document_verification_txt1.setTextColor(Color.parseColor("#00ceb4"));
                            }else
                            {
                                Document_verification_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                Document_verification_txt1.setText("Not Started");
                                Document_verification_txt1.setTextColor(Color.parseColor("#FF9201"));
                            }
                            step4_image_completed.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));

                            if(apply_completion_status.equals("1"))
                            {
                                String apply_completion_date = result.getString("apply_completion_date");

                                applied_to_bank_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));
                                Applied_to_Bank_txt1.setText("Completed "+"("+ apply_completion_date+")");
                                Applied_to_Bank_txt1.setTextColor(Color.parseColor("#00ceb4"));
                                bank_is_yes.setVisibility(View.VISIBLE);

                                if(apply_completion_status.equals("completed_"))
                                {
                                    step4_image_completed.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));
                                }else
                                {
                                    step4_image_completed.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                                }
                            }else
                            {
                                applied_to_bank_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                Applied_to_Bank_txt1.setText("Not Started");
                                Applied_to_Bank_txt1.setTextColor(Color.parseColor("#FF9201"));
                                bank_is_yes.setVisibility(View.GONE);
                            }

                           JSONObject response_doc=result.getJSONObject("apply_bank");
                           JSONObject banks =response_doc.getJSONObject("banks");

                            //Log.i("TAG", "Keyarray "+key_value);
                            Log.e("KEY_ARR_VALUE", banks.toString());



                                Iterator iterator = banks.keys();
                                int i =0;
                                while (iterator.hasNext()) {

                                    String key = (String) iterator.next();
                                    //  JSONObject req_type=doc_ar.getJSONObject("document_req");

                                    listDataHeader.add(key);
                                    List<String> lease_offer = new ArrayList<String>();
                                    //  Log.e("the value",list_key.toString());
                                    JSONArray Bank_details = banks.getJSONArray(key);
                                    Log.e("the value",Bank_details.toString());
                                    for (int j = 0; j < Bank_details.length(); j++) {
                                        JSONObject J = null;
                                        try {

                                            J = Bank_details.getJSONObject(j);
                                            String stage_name = J.getString("stage_name");
                                            String completion_date = J.getString("completion_date");
                                            String is_reject = J.getString("is_reject");
                                            all = stage_name+"," + completion_date+","+is_reject;
                                            lease_offer.add(all);
                                            Log.e("the i value", String.valueOf(i));
                                            listDataChild.put(listDataHeader.get(i), lease_offer);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    i =i+1;
                            }

                            exp_leaseoffer.setFocusable(false);
                            expandableListAdapter = new ExpandableListAdapter1(mCon,listDataHeader, listDataChild);
                            exp_leaseoffer.setAdapter(expandableListAdapter);
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
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



        document_check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(bank_statement.equals("completed"))
                {
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
                       // Toast.makeText(getApplicationContext(),"Please Completed the Previous Step", Toast.LENGTH_SHORT).show();

                    }
                }else
                {
                    Toast.makeText(getApplicationContext(),"Please Completed the Previous Step", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onRefresh() {
        // Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Applicant_Status();
                ASK_Count_Display();
                Viablityreport();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}





