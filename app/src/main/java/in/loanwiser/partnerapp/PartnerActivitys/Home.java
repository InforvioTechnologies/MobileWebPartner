package in.loanwiser.partnerapp.PartnerActivitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
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
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Document_Check_List;

public class Home extends AppCompatActivity {

    String  email,username,user_id,mobileno,transaction_id, subtask_id,applicant_id,b2b_userid,sub_taskid,step_status;
    private Context mCon = this;
    private String S1,S2,S3,S4,S5;
    Toolbar toolbar;
    private AlertDialog progressDialog;
    private CardView app,doc,offer;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private ImageView app_doc_img,app_info_img,app_info_img11,app_interview_img,app_offer_img,app_track_img;
    private TextView customerinterview,offerdetails,app_doc_message,app_info_message;
    private LinearLayout lead_cr_statues;
    CardView Applicant_info_ly,Document_check_list,Document_Upload;
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
        step_status =  Objs.a.getBundle(this, Params.step_status);


        Log.e("step_status",step_status);

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
      //  Work_flow_status(transaction_id);
    }

    private void initUI()
    {
        Applicant_info_ly = (CardView) findViewById(R.id.Applicant_info_ly);



        Document_check_list = (CardView) findViewById(R.id.Document_check_list);
        Document_Upload = (CardView) findViewById(R.id.Document_Upload);
        offer = (CardView) findViewById(R.id.Blo);
        lead_cr_statues = (LinearLayout) findViewById(R.id.lead_cr_statues);

        app_doc_img = (ImageView) findViewById(R.id.app_doc_img);
        app_info_img = (ImageView) findViewById(R.id.app_info_img);
        app_info_img11 = (ImageView) findViewById(R.id.app_info_img11);
        app_interview_img = (ImageView) findViewById(R.id.app_interview_img);
        app_offer_img = (ImageView) findViewById(R.id.app_offer_img);

        customerinterview = (TextView) findViewById(R.id.customerinterview_offer) ;
        app_info_message = (TextView) findViewById(R.id.app_info_message) ;
        app_doc_message = (TextView) findViewById(R.id.app_doc_message) ;
        offerdetails = (TextView) findViewById(R.id.callcenter_offer) ;
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
            J.put(Params.transaction_id, transaction_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  Objs.a.showToast(mCon,"Empty " + id);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.WORKFLOW_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                             Log.e("Home List",String.valueOf(response));
                            //    Objs.a.showToast(mCon, String.valueOf(response));
                            if(response.getBoolean(Params.status)){

                                S1 = response.getString(Params.s1);
                                S2 = response.getString(Params.s2);
                                S3 = response.getString(Params.s3);
                                S4 = response.getString(Params.s4);
                                S5 = response.getString(Params.s5);

                                //CD_app_info,CD_app_doc,CD_app_interview,CD_app_offer,CD_app_offer,CD_app_track
                                //app_doc_img,app_info_img,app_interview_img,app_offer_img,app_track_img;
                                if(S2.equals("1")){
                                 //   app.setEnabled(true);
                                    app_info_message.setTextColor(ContextCompat.getColor(mCon, R.color.colorAccent));
                                  //  app_info_img.setImageDrawable(getResources().getDrawable(R.drawable.don));
                                }else{
                                   // app.setEnabled(true);
                                    app_info_message.setTextColor(ContextCompat.getColor(mCon, R.color.gray));
                                  //  app_info_img.setImageDrawable(getResources().getDrawable(R.drawable.notdon));
                                }

                                if(S3.equals("1")){
                                    doc.setEnabled(true);
                                    app_doc_message.setText("Thankyou for Uploading the Documents");
                                    Pref.putDOC_Status(mCon,"1");
                                    app_doc_message.setTextColor(ContextCompat.getColor(mCon, R.color.colorAccent));
                                    app_doc_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));
                                }else{
                                  //  doc.setEnabled(true);
                                    Pref.putDOC_Status(mCon,"0");
                                    app_doc_message.setTextColor(ContextCompat.getColor(mCon, R.color.gray));
                                    app_doc_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                                }

                                if(S4.equals("1")){
                                    Document_Upload.setEnabled(true);
                                    customerinterview.setTextColor(ContextCompat.getColor(mCon, R.color.colorAccent));
                                    customerinterview.setText("Thank you for sharing more info about you with Customer care");
                                    app_interview_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));
                                }else{
                                    Document_Upload.setEnabled(false);
                                    customerinterview.setVisibility(View.VISIBLE);
                                    customerinterview.setTextColor(ContextCompat.getColor(mCon, R.color.gray));
                                    app_interview_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                                }


                               if(S5.equals("1")){
                                   offer.setEnabled(true);
                               //    offerdetails.setVisibility(View.GONE);
                                   offerdetails.setText(step_status);
                                   offerdetails.setTextColor(ContextCompat.getColor(mCon, R.color.colorAccent));
                                   app_offer_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_tick_icon));
                               }else{
                                   offer.setEnabled(false);
                                   offerdetails.setVisibility(View.VISIBLE);
                                   offerdetails.setTextColor(ContextCompat.getColor(mCon, R.color.gray));
                                   app_offer_img.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_tick));
                               }

                            }else {

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
                Intent intent = new Intent(Home.this, Document_Check_List.class);
                startActivity(intent);
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
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.APP_ID_OTP_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject jsonObject1 = response.getJSONObject(Params.application_form);
                            String user_id  = response.getString(Params.user_id);
                            String jsonStringObj  = String.valueOf(jsonObject1);
                            JSONArray jsonArray = response.getJSONArray(Params.emp_states);

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

                            JSONArray ja = response.getJSONArray(Params.emp_states);

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
        Pref.removeDOC_Status(mCon);
        super.onBackPressed();
    }
}
