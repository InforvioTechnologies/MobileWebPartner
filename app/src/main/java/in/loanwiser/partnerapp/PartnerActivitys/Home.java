package in.loanwiser.partnerapp.PartnerActivitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
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
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.CRIF_Report_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.CRIF_Report_Activity_PDF_View;
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
    CardView Applicant_info_ly,Document_check_list,Document_Upload,
            Viability_Check,eligibility_check,viability_Report,Credit_REport_Generation,Paymet,
            CRIF_Check;

    ImageView viability_check_img2,eligibility_check_img,viability_report_image,Credite_report_image,
            payment_img,crif_img,credite_report_img;

    String viability,eligibility,credit_request,payment,viability_report,viability_report_URL,
    document_checklist,document_upload,loan_type_id,loan_type,crif_status;


    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    PermissionUtils permissionUtils;

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

        user_id =  Objs.a.getBundle(this, Params.user_id);
        transaction_id =  Objs.a.getBundle(this, Params.transaction_id);
        applicant_id =  Objs.a.getBundle(this, Params.applicant_id);
        sub_taskid =  Objs.a.getBundle(this, Params.sub_taskid);
        Applicant_Statues =  Objs.a.getBundle(this, Params.Applicant_status);

        loan_type_id =  Objs.a.getBundle(this, Params.loan_type_id);
        loan_type =  Objs.a.getBundle(this, Params.loan_type);
        Log.e("Applicant_Statues",Applicant_Statues);

        try {
            JSONArray array = new JSONArray(Applicant_Statues);
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

        Pref.putTRANSACTIONID(mCon,transaction_id);
        Pref.putUSERID(mCon,user_id);
      //  step_status =  Objs.a.getBundle(this, Params.step_status);

        permissionUtils = new PermissionUtils();
        Log.e("applicant_id",applicant_id);
        Report_View_Fu();
        initCode();


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
        Work_flow_status(transaction_id);
    }

    private void initUI()
    {

        Applicant_info_ly = (CardView) findViewById(R.id.Applicant_info_ly);
        Document_check_list = (CardView) findViewById(R.id.Document_check_list);
        Document_Upload = (CardView) findViewById(R.id.Document_Upload);
        offer = (CardView) findViewById(R.id.Blo);
        Paymet = (CardView) findViewById(R.id.Paymet);

        Viability_Check = (CardView) findViewById(R.id.Viability_Check);
        eligibility_check = (CardView) findViewById(R.id.eligibility_check);
        viability_Report = (CardView) findViewById(R.id.viability_Report);
        Credit_REport_Generation = (CardView) findViewById(R.id.Credit_REport_Generation);
        CRIF_Check = (CardView) findViewById(R.id.CRIF_Check);

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
                   /* Intent intent = new Intent(Home.this, Viability_Activity_Data_View.class);
                    intent.putExtra("user_id", user_id);
                    intent.putExtra("transaction_id", transaction_id);
                    startActivity(intent);
                    finish();*/
                }else
                {

                    if(loan_type_id.equals("21"))
                    {

                        Intent intent = new Intent(Home.this, Viability_Check_PL.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                        finish();

                    }else if(loan_type_id.equals("20"))
                    {
                        Intent intent = new Intent(Home.this, Viability_Check_BL.class);
                        intent.putExtra("user_id", user_id);
                        intent.putExtra("transaction_id", transaction_id);
                        startActivity(intent);
                        finish();

                    }else
                    {
                        Intent intent = new Intent(Home.this, Viability_Check_HL_new.class);
                        startActivity(intent);
                        finish();
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
                            finish();

                        }else if(loan_type_id.equals("20"))
                        {
                            Intent intent = new Intent(Home.this, Eligibility_BL.class);
                            intent.putExtra("user_id", user_id);
                            intent.putExtra("transaction_id", transaction_id);
                            startActivity(intent);
                            finish();

                        }else
                        {
                            Intent intent = new Intent(Home.this, Eligibility_HL_New.class);
                            startActivity(intent);
                            finish();
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
                if(credit_request.contains("completed"))
                {

                }else
                {
                    Intent intent = new Intent(Home.this, Credite_report_details.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        Paymet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(payment.contains("completed"))
                {

                }else
                {
                    Intent intent = new Intent(Home.this, Payment_Details_Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        viability_Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (permissionUtils.checkPermission(Home.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                    if (viability_report_URL.length() > 0) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL)));
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }

                }
            }
        });
        Credit_REport_Generation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Home.this, CRIF_Report_Activity_PDF_View.class);
                intent.putExtra("user_id", applicant_id);
                startActivity(intent);


            }
        });


    }
    private void fonts() {
        Objs.a.OutfitNormalFontStyle(mCon, R.id.step1);
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

    private void Work_flow_status(String transaction_id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("trans_id", transaction_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Request List",String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PARTNER_STATUES, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                             Log.e("Home List",String.valueOf(response));

                             JSONObject jsonObject1 = response.getJSONObject("response");
                             JSONObject jsonObject2 = jsonObject1.getJSONObject("step2");
                             JSONObject jsonObject3 = jsonObject1.getJSONObject("step3");

                             String step2_statues = jsonObject2.getString("status");
                             JSONObject step2_sub_statues = jsonObject2.getJSONObject("sub_status");

                             JSONObject step3_sub_statues = jsonObject3.getJSONObject("sub_status");

                             viability = step2_sub_statues.getString("viability");
                             eligibility = step2_sub_statues.getString("eligibility");
                             credit_request = step2_sub_statues.getString("credit_request");
                             payment = step2_sub_statues.getString("payment");
                             viability_report = step2_sub_statues.getString("viability_report");
                            crif_status = step2_sub_statues.getString("crif_status");

                             document_checklist = step3_sub_statues.getString("document_checklist");
                             document_upload = step3_sub_statues.getString("document_upload");



                            if(document_checklist.contains("pending"))
                            {
                                app_doc_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));

                            }else
                            {
                                app_doc_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));

                            }
                            if(document_upload.contains("pending"))
                            {

                                app_interview_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));

                            }else
                            {
                                app_interview_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));

                            }


                            if(viability.contains("completed"))
                            {

                                viability_check_img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));
                            }else
                            {
                                viability_check_img2.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                            }

                            if(eligibility.contains("completed"))
                            {

                                eligibility_check_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));
                            }else
                            {
                                eligibility_check_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                            }

                            if(credit_request.contains("completed"))
                            {
                                CRIF_Check.setEnabled(false);
                                crif_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));
                            }else
                            {
                                CRIF_Check.setEnabled(true);
                                crif_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                            }

                            if(payment.contains("completed"))
                            {
                                Paymet.setEnabled(false);
                                payment_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));
                            }else
                            {
                                Paymet.setEnabled(true);
                                payment_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                            }

                            if(viability_report.contains("completed"))
                            {
                                viability_Report.setEnabled(true);
                                viability_report_image.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));
                            }else
                            {
                                viability_Report.setEnabled(true);
                                viability_report_image.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                            }

                            if(crif_status.contains("completed"))
                            {
                                Credit_REport_Generation.setEnabled(true);
                                credite_report_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));
                            }else if(crif_status.contains("pending"))
                            {
                                Credit_REport_Generation.setEnabled(true);
                                credite_report_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                            }else if(crif_status.contains("not_wanted"))
                            {
                                Credit_REport_Generation.setVisibility(View.GONE);
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
                Intent intent = new Intent(Home.this, Document_Checklist_Details_type.class);
                intent.putExtra("jsonArray", Applicant_Statues.toString());
                startActivity(intent);
            }
        });





        findViewById(R.id.Document_Upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //      Toast.makeText(mCon, "CD_app_info", Toast.LENGTH_LONG).show();
                 Account_Listings_Details(user_id);

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

        findViewById(R.id.Blo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Objs.ac.StartActivityPutExtra(mCon, Offers_list.class,
                        Params.transaction_id,transaction_id);
                finish();
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
        Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
        finish();

        super.onBackPressed();
    }
}
