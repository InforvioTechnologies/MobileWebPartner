package in.loanwiser.partnerapp.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.BankStamentUpload.Upload_Activity_Bank;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Creadite_Report_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Eligibility_HL_New;
import in.loanwiser.partnerapp.Step_Changes_Screen.Lead_Crration_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Screen_revamp;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Screen_revamp_Pl_BL;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Screen_revamp_co;

public class Payment_Sucess_Screen extends AppCompatActivity {

    Button payment_button;
    LinearLayout save_latter;
    PopupWindow popupWindow,popupWindow1;
    private AlertDialog progressDialog;
    private Context mCon = this;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    String  credit_issued_id,credit_issued_value,Report_ID,Order_ID,viability_report_URL;

    Spinner applicant_credite_card_spinner,co_applicant_Spinner;
    Typeface font;
    String[] Other_Earning_SA;
    ArrayAdapter<String> Other_Earning_Adapter;
    AppCompatTextView cr_app1,cr_app2;
    LinearLayout credit_card_app,co_applicant_,applicant_question,standard;

    AppCompatButton view_report,view_report_fail;

    String Statues_vability;

    PermissionUtils permissionUtils;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment__sucess__screen);


        payment_button = findViewById(R.id.payment_button);

        save_latter = findViewById(R.id.save_latter);
        progressDialog = new SpotsDialog(mCon, R.style.Custom);
        permissionUtils = new PermissionUtils();
        applicant_credite_card_spinner = (Spinner) findViewById(R.id.applicant_credite_card_spinner);
        co_applicant_Spinner = (Spinner) findViewById(R.id.co_applicant_Spinner);

        cr_app1 = (AppCompatTextView) findViewById(R.id.cr_app1);
        cr_app2 = (AppCompatTextView) findViewById(R.id.cr_app2);
        credit_card_app = (LinearLayout) findViewById(R.id.credit_card_app);
        co_applicant_ = (LinearLayout) findViewById(R.id.co_applicant_);
        standard = (LinearLayout) findViewById(R.id.standard);

        applicant_question = (LinearLayout) findViewById(R.id.applicant_question);
        payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Crif_Generation();

            }
        });

        save_latter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment_Sucess_Screen.this, Dashboard_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        viability_check_pass();
    }

    private void viability_check_pass( ) {

        JSONObject J= null;

        try {
            J =new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            J.put("b2b_id", Pref.getID(mCon));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("viability ", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.viabilitysave, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("viability response", String.valueOf(response));
                        String data = String.valueOf(response);
                        try {
                            //  String Status = response.getString("status");
                            JSONObject jsonObject1 = response.getJSONObject("response");

                            if(jsonObject1.getString("viablity_status").equals("success"))
                            {
                                Statues_vability= jsonObject1.getString("viablity_status");

                            }else if(jsonObject1.getString("viablity_status").equals("error"))
                            {
                                Statues_vability= jsonObject1.getString("viablity_status");
                               /* view_report.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                            if (viability_report_URL.length() > 0) {
                                                try {
                                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL)));
                                                } catch (Exception e) {
                                                    e.getStackTrace();
                                                }
                                            }

                                        }
                                    }
                                });*/

                                progressDialog.dismiss();
                            }
                            progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Log.e("Lead creation", String.valueOf(response));


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d(TAG, error.getMessage());
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        // AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void Crif_Generation() {

        JSONObject J =new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("relationship_type",Pref.getCoAPPAVAILABLE(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Crif Generation", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CRIF_Generation, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment", String.valueOf(object));
                        try {

                            String Statues = object.getString("status");

                            if (Statues.contains("success")) {

                                JSONObject data = object.getJSONObject("data");

                                String rule_status = data.getString("rule_status");
                                String cibil_score = data.getString("cibil_score");

                                if(Statues_vability.equals("success"))
                                {
                                    LayoutInflater layoutInflater1 = (LayoutInflater) Payment_Sucess_Screen.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View customView1 = layoutInflater1.inflate(R.layout.popup_viability_scrore,null);

                                    view_report = (AppCompatButton) customView1.findViewById(R.id.view_report);

                                    TextView score= (TextView) customView1.findViewById(R.id.score);

                                    score.setText(cibil_score);
                                    Log.e("crif_Score",cibil_score);
                                    //instantiate popup window
                                    popupWindow1 = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                    //display the popup window
                                    popupWindow1.showAtLocation(view_report, Gravity.CENTER, 0, 0);

                                    //close the popup window on button click
                                    view_report.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Intent intent = new Intent(Payment_Sucess_Screen.this, Upload_Activity_Bank.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }else
                                {
                                    LayoutInflater layoutInflater1 = (LayoutInflater) Payment_Sucess_Screen.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View customView1 = layoutInflater1.inflate(R.layout.popup_viability_scrore_fail,null);

                                    view_report_fail = (AppCompatButton) customView1.findViewById(R.id.view_report_fail);

                                    TextView faild_score= (TextView) customView1.findViewById(R.id.faild_score);

                                    faild_score.setText(cibil_score);
                                    Log.e("crif_Score",cibil_score);
                                    //instantiate popup window
                                    popupWindow1 = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                    //display the popup window
                                    popupWindow1.showAtLocation(view_report_fail, Gravity.CENTER, 0, 0);

                                    //close the popup window on button click
                                    view_report_fail.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Intent intent = new Intent(Payment_Sucess_Screen.this, DashBoard_new.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                }
                                standard.setVisibility(View.GONE);
                                applicant_question.setVisibility(View.GONE);
                                progressDialog.dismiss();
                               /* LayoutInflater layoutInflater = (LayoutInflater) Payment_Sucess_Screen.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View customView = layoutInflater.inflate(R.layout.popup_loading,null);
                                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                //display the popup window
                                popupWindow.showAtLocation(payment_button, Gravity.CENTER, 0, 0);
                                STEP2_COMPLETE();*/

                            }else if (Statues.contains("question"))
                            {
                                standard.setVisibility(View.GONE);
                                JSONObject data1 = object.getJSONObject("data");
                                Report_ID  = data1.getString("reportId");
                                Order_ID  = data1.getString("orderId");
                                String question = object.getString("question");
                                JSONArray qus_dropdown = object.getJSONArray("qus_dropdown");
                                applicant_question.setVisibility(View.VISIBLE);

                                cr_app1.setText(question);
                                credit_card_app.setVisibility(View.VISIBLE);
                                Other_Earning(qus_dropdown);
                                progressDialog.dismiss();
                                // co_applicant_.setVisibility(View.VISIBLE);
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
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }
    private void STEP2_COMPLETE() {
    //    progressDialog.show();
        JSONObject J =new JSONObject();
        try {

            J.put("trans_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("mode","view");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Step2 Complete request",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.step2_complete, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Step2 complete response",object.toString());
                        try {

                            String Staues_step2_complete = object.getString("status");

                            if(Staues_step2_complete.contains("success")) {

                                viability_CRIF_Score();
                                //  Document_Details();
                            }else
                            {
                                Toast.makeText(mCon, "CRIF Statues Failed",Toast.LENGTH_SHORT).show();
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
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }
    private void Other_Earning(final JSONArray other_earning_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Other_Earning_SA = new String[other_earning_ar.length()];
        for (int i=0;i<other_earning_ar.length();i++){
            JSONObject J =  other_earning_ar.getJSONObject(i);
            Other_Earning_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Other_Earning_SA));
            Other_Earning_Adapter = new ArrayAdapter<String>(mCon, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            Other_Earning_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            applicant_credite_card_spinner.setAdapter(Other_Earning_Adapter);
            applicant_credite_card_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        credit_issued_id = other_earning_ar.getJSONObject(position).getString("id");
                        credit_issued_value = other_earning_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", credit_issued_id);
                        Log.d("Salary_Value", credit_issued_value);

                        CRIF_Question_Testing();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            applicant_credite_card_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }
    private void CRIF_Question_Testing() {

        JSONObject J =new JSONObject();
        try {
            J.put("report_id",Report_ID);
            J.put("order_id",Order_ID);
            J.put("crif_question",credit_issued_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Crif Generation", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CRIF_Generation, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment", String.valueOf(object));
                        try {

                            String Statues = object.getString("status");
                            JSONObject data1 = object.getJSONObject("data");
                            Report_ID  = data1.getString("reportId");
                            Order_ID  = data1.getString("orderId");


                            if (Statues.equals("success")) {
                                viability_CRIF_Score();
                            }else if (Statues.equals("question"))
                            {
                                String question = object.getString("question");
                                JSONArray qus_dropdown = object.getJSONArray("qus_dropdown");
                                cr_app1.setText(question);
                                credit_card_app.setVisibility(View.VISIBLE);
                                Other_Earning(qus_dropdown);

                                // co_applicant_.setVisibility(View.VISIBLE);
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
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void viability_CRIF_Score( ) {

        JSONObject J= null;

        try {
            J =new JSONObject();
           // J.put("applicant_count",getCoAPPAVAILABLE);
            J.put("relationship_type", Pref.getCoAPPAVAILABLE(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("viability Applicant ", String.valueOf(J));
        // progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.view_crifscore, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Log.e("viability response", String.valueOf(response));
                        String data = String.valueOf(response);
                        try {
                             String Status = response.getString("status");
                           // JSONObject jsonObject1 = response.getJSONObject("response");
                            if(Status.equals("success")) {

                                LayoutInflater layoutInflater = (LayoutInflater) Payment_Sucess_Screen.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View customView = layoutInflater.inflate(R.layout.popup_loading,null);
                                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                //display the popup window
                                popupWindow.dismiss();

                                JSONObject applicant = response.getJSONObject("applicant");
                               // JSONObject coapplicant = response.getJSONObject("coapplicant");

                                String crif_Score = applicant.getString("crif_score");
                                LayoutInflater layoutInflater1 = (LayoutInflater) Payment_Sucess_Screen.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View customView1 = layoutInflater.inflate(R.layout.popup_viability_scrore,null);

                                view_report = (AppCompatButton) customView1.findViewById(R.id.view_report);

                                TextView score= (TextView) customView1.findViewById(R.id.score);

                                score.setText(crif_Score);
                                Log.e("crif_Score",crif_Score);
                                //instantiate popup window
                                popupWindow1 = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                //display the popup window
                                popupWindow1.showAtLocation(view_report, Gravity.CENTER, 0, 0);

                                //close the popup window on button click
                                view_report.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent intent = new Intent(Payment_Sucess_Screen.this, Upload_Activity_Bank.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });



                            }
                            ///
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                       // Log.e("Lead creation", String.valueOf(response));


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d(TAG, error.getMessage());
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Network error, try after some time",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        // AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
