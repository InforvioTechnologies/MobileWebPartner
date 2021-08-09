package in.loanwiser.partnerapp.PartnerActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.NumberTextWatcher;
import in.loanwiser.partnerapp.R;

public class Applicant_Info_new extends SimpleActivity {

    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    AppCompatTextView loan_type_newcat,loan_type_new_val_type,type_of_employee_new_val_type,Loan_amount_new_txt,
            app_name1_pan_txt,app_Email_txt,app_mobile1_new_txt,app_mobile1_co_applicant,
            type_of_employee_new_val_type_co_applica,app_mobile1_new_txt_whats_app,lastnametxt;

    JSONObject applicant_info_object,applicant_info_object2;

    Typeface font;
    String CAT_ID, loan_cat_id,loan_type, employement_type;
    JSONArray Employement;
    String loantype_id;
    LinearLayout type_of_emploe,loan_amount_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_applicant_new);
        initTools(R.string.app_info);

        loan_type_newcat  = (AppCompatTextView)findViewById(R.id.loan_type_newcat) ;
        loan_type_new_val_type  = (AppCompatTextView)findViewById(R.id.loan_type_new_val_type) ;
        type_of_employee_new_val_type  = (AppCompatTextView)findViewById(R.id.type_of_employee_new_val_type) ;
        Loan_amount_new_txt  = (AppCompatTextView)findViewById(R.id.Loan_amount_new_txt) ;
        app_name1_pan_txt  = (AppCompatTextView)findViewById(R.id.app_name1_pan_txt) ;
        app_Email_txt = (AppCompatTextView)findViewById(R.id.app_Email_txt) ;
        app_mobile1_new_txt = (AppCompatTextView)findViewById(R.id.app_mobile1_new_txt) ;
        app_mobile1_co_applicant = (AppCompatTextView)findViewById(R.id.app_mobile1_co_applicant) ;
        type_of_employee_new_val_type_co_applica = (AppCompatTextView)findViewById(R.id.type_of_employee_new_val_type_co_applica);
        app_mobile1_new_txt_whats_app = (AppCompatTextView)findViewById(R.id.app_mobile1_new_txt_whats_app) ;
        lastnametxt = (AppCompatTextView)findViewById(R.id.lastnametxt) ;
        loan_amount_new = (LinearLayout) findViewById(R.id.loan_amount_new) ;
        type_of_emploe=findViewById(R.id.type_of_emploe);

        applicant_response();

    }

    private void applicant_response() {

        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            Log.i("TAG", "applicant_response: "+Pref.getUSERID(getApplicationContext()));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Request Applicant_info", "called");

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.APPLICANT_INFO, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("RESPONSE Applicant_info", String.valueOf(object));


                        try {
                            applicant_info_object=object.getJSONObject("appinfo");
                           // employement_type=applicant_info_object.getString("employement_type");
                            employement_type=applicant_info_object.getString("employment_typestr");
                           // type_of_employement( employement_type);

                            String user_name=applicant_info_object.getString("username");
                            app_name1_pan_txt.setText(user_name);
                            String email=applicant_info_object.getString("email");
                            app_Email_txt.setText(email);
                            String mobile_no=applicant_info_object.getString("mobileno");
                            app_mobile1_new_txt.setText(mobile_no);
                            String whats_app=applicant_info_object.getString("whatsapp_mobile");
                            app_mobile1_new_txt_whats_app.setText(whats_app);
                            String lastname=applicant_info_object.getString("last_name");
                            lastnametxt.setText(lastname);

                            applicant_info_object2=object.getJSONObject("appinfo2");
                            loan_cat_id=applicant_info_object2.getString("loan_categoryid");
                            loan_type=applicant_info_object2.getString("loan_type");

                            makeJsonObjReq_loancat();
                            makeJsonObjReq1_loan_type(loan_cat_id, loan_type);

                            String amount=applicant_info_object2.getString("loan_amount");
                            Loan_amount_new_txt.setText(amount);
                            if (loan_type.equals("33")){
                                type_of_emploe.setVisibility(View.GONE);
                                loan_amount_new.setVisibility(View.GONE);
                            }else {
                                type_of_employee_new_val_type.setText(employement_type);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        //  progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                //     progressDialog.dismiss();
            }
        }) {

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
    private void type_of_employement(final String employement_type) {


        JSONObject J =new JSONObject();
        try {
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //  progressDialog.show();
        Log.e("Request Type f emplo", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_DROPDOWN_LIST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {

                        try {



                            Employement =object.getJSONArray("Employement");

                            for (int i=0;i<Employement.length();i++){

                                JSONObject object3 = Employement.getJSONObject(i);
                                String id_type3 = object3.getString("id");
                                String loan_cat3 = object3.getString("value");
                                if(id_type3.equals(employement_type)){
                                    // Objs.a.showToast(mCom, loan_type);
                                    type_of_employee_new_val_type.setText(loan_cat3);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        //  progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                //  progressDialog.dismiss();
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
    private void makeJsonObjReq1_loan_type(String loan_cat_id, final String loan_type) {
//        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("category_id", loan_cat_id);
            Log.e("category_id111", String.valueOf(J));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LOAN_TYPE_POST11, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("respose Loan_type", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));


                        try {
                            JSONArray lonty_1= object.getJSONArray("loantypelist_arr");

                            for (int k=0;k<lonty_1.length();k++){

                                JSONObject object2 = lonty_1.getJSONObject(k);

                                String id_type1 = object2.getString("id");
                                String loan_type1 = object2.getString("loan_type");

                                if(id_type1.equals(loan_type)){
                                    // Objs.a.showToast(mCom, loan_type);
                                    loan_type_new_val_type.setText(loan_type1);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        // progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                //   progressDialog.dismiss();
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
    private void makeJsonObjReq_loancat() {
        //  progressDialog.show();
        JSONObject J =new JSONObject();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LOAN_CATAGORY, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Loan catgory", object.toString());
                        try {
                            JSONArray ja = object.getJSONArray("loancatlist_arr");
                            for (int i=0;i<ja.length();i++){

                                JSONObject object1 = ja.getJSONObject(i);

                                String id_type = object1.getString("id");
                                String loan_cat = object1.getString("category_type");

                                if(id_type.equals(loan_cat_id)){
                                    // Objs.a.showToast(mCom, loan_type);
                                    loan_type_newcat.setText(loan_cat);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        //  progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                //  progressDialog.dismiss();
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
