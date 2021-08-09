package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.BankStamentUpload.Upload_Activity_Bank;
import in.loanwiser.partnerapp.PDF_Viewer.MainActivity;
import in.loanwiser.partnerapp.PartnerActivitys.ViablityfailureActivity;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class ViablityReportActivity extends SimpleActivity {

    AppCompatButton view_viablity_report,nextahed,dash_but;
    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    String viable_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viablity_report);
        initTools(R.string.viablityreport);
        progressDialog = new SpotsDialog(ViablityReportActivity.this, R.style.Custom);

        view_viablity_report=findViewById(R.id.view_viablity_report);
        nextahed=findViewById(R.id.nextahed);
        dash_but=findViewById(R.id.dash_but);

        Viablityreport();

        view_viablity_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String report="Viability Report";
                String viability_report_URL="https://partner.loanwiser.in/viability_report_new.php?user_id=bGNVOXFmMWE2Y2JUaCtReGw4Y2pUZz09";
                Intent intent = new Intent(ViablityReportActivity.this, MainActivity.class);
                intent.putExtra("viability_report_URL", viable_url);
                intent.putExtra("report", report);
                startActivity(intent);
            }
        });

        nextahed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Status_Update();
               /* Intent intent = new Intent(ViablityReportActivity.this, Upload_Activity_Bank.class);
                startActivity(intent);*/
            }
        });

        dash_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViablityReportActivity.this, DashBoard_new.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void Viablityreport(){
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
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
                            JSONObject jsonObject=object.getJSONObject("viable");
                            int applicantstatus=jsonObject.getInt("applicant_status");
                            Log.i("TAG", "onResponse:applicant "+applicantstatus);
                            if(applicantstatus==1){
                                nextahed.setVisibility(View.VISIBLE);
                            }else{
                                nextahed.setVisibility(View.GONE);
                                dash_but.setVisibility(View.VISIBLE);

                            }
                            Log.i("TAG", "onResponse: "+viable_url);
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


    private void Status_Update(){
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("comp_status", "3");
            J.put("subcomp_status", "0");
            Log.i("TAG", "Statusupdate "+J.toString());
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.status_update, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                       Intent intent=new Intent(ViablityReportActivity.this,Upload_Activity_Bank.class);
                       startActivity(intent);
                       finish();


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



}