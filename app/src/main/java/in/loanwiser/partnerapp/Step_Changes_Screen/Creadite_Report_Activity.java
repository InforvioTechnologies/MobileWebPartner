package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Creadite_Report_Activity extends SimpleActivity {

    AppCompatTextView name_txt,name_txt1,email_id_Txt,email_id_Txt1,mobiles_txt,mobiles_txt1,
            co_applicant_name_txt,co_applicant_name_txt1,Co_applicant_email_id,Co_applicant_email_id1,
            co_applicant_mobile,accept_terms_condition;
    AppCompatButton credit_det_cap_button;

    AppCompatEditText Pan_No_Edite_text,Email_Id_Edite_text,Co_App_Pan_No_Edite_text,Co_Email_Id_No_Edite_text,
            Co_Mobile_No_Edite_text,Mobile_No_Edite_text;
    AppCompatCheckBox check_complete;

    AppCompatEditText first_name_Edite_text,Last_name_Edite_text,father_name_edt_txt,
            dob_Edite_text,pincode_edt_txt,pl_co_app_first_name_Edite_text,pl_co_app_Last_name_Edite_text,pl_co_app_Pan_No_Edite_text,
            pl_co_app_father_name_edt_txt,pl_co_app_Email_Id_Edite_text,pl_co_app_dob_Edite_text,
            pl_co_app_Mobile_No_Edite_text,pl_co_app_pincode_edt_txt;

    Typeface font;
    private Context context = this;
    InputMethodManager imm;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;
    String Co_Applicant, S_first_name_edtxt,S_last_name_edtxt,S_father_name_edtxt,S_dob_edtxt,S_pincode_edtxt,S_Pan_No_edtxt,
            S_email_edtxt,S_Mobile_No_edtxt,S_pl_co_app_first_name_Edite_text,S_pl_co_app_Last_name_Edite_text,S_pl_co_app_Pan_No_Edite_text,
            S_pl_co_app_father_name_edt_txt,S_pl_co_app_Email_Id_Edite_text,
            S_pl_co_app_dob_Edite_text,S_pl_co_app_Mobile_No_Edite_text,S_pl_co_app_pincode_edt_txt;

    Calendar myCalendar;
    LinearLayout co_applicant_crif;

    int applicant_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_creadite__report_);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_creadite__report_);
        initTools(R.string.credit_report);


        Co_Applicant = Pref.getCoAPPAVAILABLE(getApplicationContext());
        progressDialog = new SpotsDialog(context, R.style.Custom);
        myCalendar = Calendar.getInstance();

        UISCREEN();
        Font();
        Click();
        GET_Credite_Data();

        if(Co_Applicant.equals("1"))
        {
            co_applicant_crif.setVisibility(View.VISIBLE);
        }else
        {
            co_applicant_crif.setVisibility(View.GONE);

        }
    }

    private void UISCREEN()
    {

        name_txt = (AppCompatTextView) findViewById(R.id.name_txt);
        name_txt1 = (AppCompatTextView) findViewById(R.id.name_txt1);
        email_id_Txt = (AppCompatTextView) findViewById(R.id.email_id_Txt);
        email_id_Txt1 = (AppCompatTextView) findViewById(R.id.email_id_Txt1);
        mobiles_txt = (AppCompatTextView) findViewById(R.id.mobiles_txt);
        mobiles_txt1 = (AppCompatTextView) findViewById(R.id.mobiles_txt1);
        //Pan_No_Edite_text,Email_Id_Edite_text,Co_App_Pan_No_Edite_text;

        first_name_Edite_text = (AppCompatEditText) findViewById(R.id.first_name_Edite_text);
        Last_name_Edite_text = (AppCompatEditText) findViewById(R.id.Last_name_Edite_text);
        father_name_edt_txt = (AppCompatEditText) findViewById(R.id.father_name_edt_txt);
        dob_Edite_text = (AppCompatEditText) findViewById(R.id.dob_Edite_text);
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob_Edite_text.setText(sdf.format(myCalendar.getTime()));
        pincode_edt_txt = (AppCompatEditText) findViewById(R.id.pincode_edt_txt);
        Pan_No_Edite_text = (AppCompatEditText) findViewById(R.id.Pan_No_Edite_text);
        Email_Id_Edite_text = (AppCompatEditText) findViewById(R.id.Email_Id_Edite_text);
        Mobile_No_Edite_text = (AppCompatEditText) findViewById(R.id.Mobile_No_Edite_text);


        pl_co_app_first_name_Edite_text = (AppCompatEditText) findViewById(R.id.pl_co_app_first_name_Edite_text);
        pl_co_app_Last_name_Edite_text = (AppCompatEditText) findViewById(R.id.pl_co_app_Last_name_Edite_text);
        pl_co_app_Pan_No_Edite_text = (AppCompatEditText) findViewById(R.id.pl_co_app_Pan_No_Edite_text);
        pl_co_app_father_name_edt_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_father_name_edt_txt);
        pl_co_app_Email_Id_Edite_text = (AppCompatEditText) findViewById(R.id.pl_co_app_Email_Id_Edite_text);
        pl_co_app_dob_Edite_text = (AppCompatEditText) findViewById(R.id.pl_co_app_dob_Edite_text);
        pl_co_app_dob_Edite_text.setText(sdf.format(myCalendar.getTime()));
        pl_co_app_Mobile_No_Edite_text = (AppCompatEditText) findViewById(R.id.pl_co_app_Mobile_No_Edite_text);
        pl_co_app_pincode_edt_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_pincode_edt_txt);
        credit_det_cap_button = (AppCompatButton) findViewById(R.id.credit_det_cap_button);

        co_applicant_crif = (LinearLayout) findViewById(R.id.co_applicant_crif);

    }


    private void Font() {

        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");

        name_txt.setTypeface(font);
        name_txt1.setTypeface(font);
        email_id_Txt.setTypeface(font);
        email_id_Txt1.setTypeface(font);
        mobiles_txt.setTypeface(font);
        mobiles_txt1.setTypeface(font);

    }

    private void updateLabel()
    {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob_Edite_text.setText(sdf.format(myCalendar.getTime()));
        pl_co_app_dob_Edite_text.setText(sdf.format(myCalendar.getTime()));
    }
    private void Click() {

        credit_det_cap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Creadite_Report_Activity.this, Payment_Details_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

         dob_Edite_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(Creadite_Report_Activity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }


        });
         pl_co_app_dob_Edite_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(Creadite_Report_Activity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }


        });

        credit_det_cap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validate_name()) {
                    return;
                }

                if (!validate_Last_name()) {
                    return;
                }
                if (!CR_PAn_Validation()) {
                    return;
                }

                if (!Email_ID_Validation()) {
                    return;
                }

                if (!validate_father_name()) {
                    return;
                }

                if (!validate_DOB()) {
                    return;
                }

                if (!Maobile_validation()) {
                    return;
                }

                if (!validate_Applicant_pincode()) {
                    return;
                }

                if(Co_Applicant.equals(1))
                {
                    Co_Applicant_validation();
                }else
                {
                    CRIF_Report();
                }


            }
        });

    }


    private void Co_Applicant_validation()
    {
        if (!Co_pl_App_name_Validation()) {
            return;
        }
        if (!CO_pl_app_Last_name_Edite_text()) {
            return;
        }

        if (!Co_pl_Pan_validation()) {
            return;
        }
        if (!Co_pl_father_name_validation()) {
            return;
        }
        if (!Co_pl_Email_Id_validation()) {
            return;
        }
        if (!Co_pl_DOB_validation()) {
            return;
        }

        if (!Co_pl_Mobilr_no_validation()) {
            return;
        }

        if (!Co_pl_Pincode_validation()) {
            return;
        }


    }

    private boolean validate_name(){

        if (first_name_Edite_text.getText().toString().isEmpty()) {
            first_name_Edite_text.setError(getText(R.string.error_rise));
            first_name_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_Last_name(){

        if (Last_name_Edite_text.getText().toString().isEmpty()) {
            Last_name_Edite_text.setError(getText(R.string.error_rise));
            Last_name_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_father_name(){

        if (father_name_edt_txt.getText().toString().isEmpty()) {
            father_name_edt_txt.setError(getText(R.string.error_rise));
            father_name_edt_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_DOB(){
        if (dob_Edite_text.getText().toString().isEmpty()) {
            dob_Edite_text.setError(getText(R.string.error_rise));
            dob_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_Applicant_pincode(){
        if (pincode_edt_txt.getText().toString().isEmpty()) {
            pincode_edt_txt.setError(getText(R.string.error_rise));
            pincode_edt_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean CR_PAn_Validation(){

        if (Pan_No_Edite_text.getText().toString().isEmpty()) {
            Pan_No_Edite_text.setError(getText(R.string.error_rise));
            Pan_No_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Email_ID_Validation(){

        if (Email_Id_Edite_text.getText().toString().isEmpty()) {
            Email_Id_Edite_text.setError(getText(R.string.error_rise));
            Email_Id_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Maobile_validation(){

        if (Mobile_No_Edite_text.getText().toString().isEmpty()) {
            Mobile_No_Edite_text.setError(getText(R.string.error_rise));
            Mobile_No_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }


    //co

    private boolean Co_pl_App_name_Validation(){

        if (pl_co_app_first_name_Edite_text.getText().toString().isEmpty()) {
            pl_co_app_first_name_Edite_text.setError(getText(R.string.error_rise));
            pl_co_app_first_name_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean CO_pl_app_Last_name_Edite_text(){

        if (pl_co_app_Last_name_Edite_text.getText().toString().isEmpty()) {
            pl_co_app_Last_name_Edite_text.setError(getText(R.string.error_rise));
            pl_co_app_Last_name_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Co_pl_Pan_validation(){

        if (pl_co_app_Pan_No_Edite_text.getText().toString().isEmpty()) {
            pl_co_app_Pan_No_Edite_text.setError(getText(R.string.error_rise));
            pl_co_app_Pan_No_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }



    private boolean Co_pl_father_name_validation(){

        if (pl_co_app_father_name_edt_txt.getText().toString().isEmpty()) {
            pl_co_app_father_name_edt_txt.setError(getText(R.string.error_rise));
            pl_co_app_father_name_edt_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Co_pl_Email_Id_validation(){

        if (pl_co_app_Email_Id_Edite_text.getText().toString().isEmpty()) {
            pl_co_app_Email_Id_Edite_text.setError(getText(R.string.error_rise));
            pl_co_app_Email_Id_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Co_pl_DOB_validation(){

        if (pl_co_app_dob_Edite_text.getText().toString().isEmpty()) {
            pl_co_app_dob_Edite_text.setError(getText(R.string.error_rise));
            pl_co_app_dob_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Co_pl_Mobilr_no_validation(){

        if (pl_co_app_Mobile_No_Edite_text.getText().toString().isEmpty()) {
            pl_co_app_Mobile_No_Edite_text.setError(getText(R.string.error_rise));
            pl_co_app_Mobile_No_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Co_pl_Pincode_validation(){

        if (pl_co_app_pincode_edt_txt.getText().toString().isEmpty()) {
            pl_co_app_pincode_edt_txt.setError(getText(R.string.error_rise));
            pl_co_app_pincode_edt_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private void GET_Credite_Data( ) {
        // progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("The Credit request", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CRIF_DATA_Populate, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {

                        Log.e("The Credit request", String.valueOf(object));

                        try {
                            String applicant_status= object.getString("applicant_status");
                            String coapplicant_status= object.getString("coapplicant_status");

                            if(applicant_status.contains("success"))
                            {

                                JSONObject applicant = object.getJSONObject("applicant");
                                String name = applicant.getString("username");
                                first_name_Edite_text.setText(name);
                                String email = applicant.getString("email");
                                Email_Id_Edite_text.setText(email);
                                String mobileno = applicant.getString("mobileno");
                                Mobile_No_Edite_text.setText(mobileno);

                            }
                            if(coapplicant_status.contains("success"))
                            {
                                JSONObject coapplicant = object.getJSONObject("coapplicant");

                                String name = coapplicant.getString("username");
                                pl_co_app_first_name_Edite_text.setText(name);
                                String email = coapplicant.getString("email");
                                pl_co_app_Email_Id_Edite_text.setText(email);
                                String mobileno = coapplicant.getString("mobileno");
                                pl_co_app_Mobile_No_Edite_text.setText(mobileno);

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

    private void CRIF_Report()
    {

        S_first_name_edtxt = first_name_Edite_text.getText().toString();
        S_last_name_edtxt = Last_name_Edite_text.getText().toString();
        S_father_name_edtxt = father_name_edt_txt.getText().toString();
        S_dob_edtxt = dob_Edite_text.getText().toString();
        S_pincode_edtxt = pincode_edt_txt.getText().toString();
        S_Pan_No_edtxt = Pan_No_Edite_text.getText().toString();
        S_email_edtxt = Email_Id_Edite_text.getText().toString();
        S_Mobile_No_edtxt = Mobile_No_Edite_text.getText().toString();


        S_pl_co_app_first_name_Edite_text = pl_co_app_first_name_Edite_text.getText().toString();
        S_pl_co_app_Last_name_Edite_text = pl_co_app_Last_name_Edite_text.getText().toString();
        S_pl_co_app_Pan_No_Edite_text = pl_co_app_Pan_No_Edite_text.getText().toString();
        S_pl_co_app_father_name_edt_txt = pl_co_app_father_name_edt_txt.getText().toString();
        S_pl_co_app_Email_Id_Edite_text = pl_co_app_Email_Id_Edite_text.getText().toString();
        S_pl_co_app_dob_Edite_text = pl_co_app_dob_Edite_text.getText().toString();
        S_pl_co_app_Mobile_No_Edite_text = pl_co_app_Mobile_No_Edite_text.getText().toString();
        S_pl_co_app_pincode_edt_txt = pl_co_app_pincode_edt_txt.getText().toString();



        JSONObject Applicant =new JSONObject();
        JSONObject Co_Applicant =new JSONObject();
        JSONObject J= null;


        try {
            Applicant.put("first_name",S_first_name_edtxt);
            Applicant.put("is_creditreport",1);
            Applicant.put("last_name",S_last_name_edtxt);
            Applicant.put("father_name",S_father_name_edtxt);
            Applicant.put("member_dob",S_dob_edtxt);
            Applicant.put("crif_pincode",S_pincode_edtxt);
            Applicant.put("pan_no",S_Pan_No_edtxt);
            Applicant.put("member_email",S_email_edtxt);
            Applicant.put("member_mobile",S_Mobile_No_edtxt);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(Co_Applicant.equals("1"))
        {
            applicant_count = 2;
            co_applicant_crif.setVisibility(View.VISIBLE);
            try {
                Co_Applicant.put("first_name",S_pl_co_app_first_name_Edite_text);
                Co_Applicant.put("is_creditreport",1);
                Co_Applicant.put("last_name",S_pl_co_app_Last_name_Edite_text);
                Co_Applicant.put("father_name",S_pl_co_app_father_name_edt_txt);
                Co_Applicant.put("member_dob",S_pl_co_app_dob_Edite_text);
                Co_Applicant.put("crif_pincode",S_pl_co_app_pincode_edt_txt);
                Co_Applicant.put("pan_no",S_pl_co_app_Pan_No_Edite_text);
                Co_Applicant.put("member_email",S_pl_co_app_Email_Id_Edite_text);
                Co_Applicant.put("member_mobile",S_pl_co_app_Mobile_No_Edite_text);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else
        {
            applicant_count = 1;
            co_applicant_crif.setVisibility(View.GONE);

        }

        try {
            J =new JSONObject();
            //  J.put(Params.email_id,email);
            J.put("applicant_count",applicant_count);
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("applicant",Applicant);
            J.put("co_applicant",Co_Applicant);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Add Home Laoan", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.NOCRIFREPORT, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        Log.e("Add_Home_loan Partner", String.valueOf(response));
                        try {
                            JSONObject jsonObject1 = response.getJSONObject("response");
                            if(jsonObject1.getString("applicant_status").equals("success")) {
                                if(jsonObject1.getString("pay_status").equals("success"))
                                {
                                    Toast.makeText(context,"Eligibility Created Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Creadite_Report_Activity.this, Payment_Details_Activity.class);
                                    startActivity(intent);
                                    finish();

                                }else if(jsonObject1.getString("pay_status").equals("error"))
                                {
                                    Toast.makeText(context,"Eligibility Failed",Toast.LENGTH_SHORT).show();
                                    String viability_array =jsonObject1.getString("pay_status");
                                    Intent intent = new Intent(Creadite_Report_Activity.this, Home.class);
                                    intent.putExtra("viability_jsonArray", viability_array.toString());
                                    startActivity(intent);
                                    finish();

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
                //Log.d(TAG, error.getMessage());
                VolleyLog.d("TAG", "Error: " + error.getMessage());
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

        private void Payment_Option()
    {
        Intent intent = new Intent(Creadite_Report_Activity.this, Payment_Details_Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Credite_report_details.class);
        finish();
        super.onBackPressed();
    }
}
