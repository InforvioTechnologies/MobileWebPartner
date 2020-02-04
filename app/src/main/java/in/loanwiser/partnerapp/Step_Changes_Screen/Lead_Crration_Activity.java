package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

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

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.NumberTextWatcher;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Lead_Crration_Activity extends SimpleActivity {

    AppCompatButton lead_cr_step1;
    private Spinner spinner_loan_category,spinner_loan_type,spnr_type_of_empmnt;
    private Toolbar toolbar;
    private AlertDialog progressDialog;
    private Context context = this;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    JSONArray ja= new JSONArray();
    JSONArray ja1= new JSONArray();
    Typeface font;
    String[] SPINNERLIST,Type_Of_Emp_SA,CO_Type_Of_Emp_SA,Have_Co_Applicant;
    String[] SPINNERLIST_CAT;
    ArrayAdapter<String> Loantype_cat,Loantype1,Type_Of_Emp_Adapter,CO_Type_Of_Emp_Adapter,Have_Co_Adapter;
    private String App,CAT_ID;
    String Lontypename,Lontype,Loan_Cat_id,result,C_loan_amount_ext,
            C_mobile_no_txt,C_name_txt,C_whats_app_no,LoanCat_Name,
            Type_of_employement_ID,Type_of_employement_Value, CO_Type_of_employement_ID,CO_Type_of_employement_Value,
            IS_CO_Applicant_Id,IS_CO_Applicant_Value,Mobile,Name,C_email_edite_txt;

    InputMethodManager imm;
    JSONArray Employement,is_coapplicant;
    AppCompatEditText loan_amount_ext,name_txt,mobile_no_txt,whats_app_no,age_edite_txt,email_edite_txt;
    AppCompatTextView txt_loan_category,txt_loan_category1,loan_type,loan_type1,
                        Loan_amount,Loan_amount1,name,name1,mobile,mobile1,wt_mobile,wt_mobile11,terms_and_condition,
            type_of_empmnt_txt,type_of_empmnt_txt1,do_you_have_coApp_txt,do_you_have_coApp_txt1,coApp_txt_emp_type1
            ,coApp_txt_emp_type2,age,email,email1;

    CheckBox check_complete;
    Spinner co_applicant_spinner,co_applicant_emp_spinner;
    LinearLayout type_of_empmnt,co_applicant_ly,co_applicant_emp_type;
    TextView v;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_lead__crration_);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_lead__crration_);
        initTools(R.string.lead_creation);

        Lontype = Pref.getLoanType(getApplicationContext());
        Lontypename = Pref.getLoanTypename(context);

       // LoanCat_Name = Pref.getLoanCat_Name(context);

        Log.e("Loantype_Name",Lontypename);

        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
        progressDialog = new SpotsDialog(context, R.style.Custom);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        makeJsonObjReq_loancat();
        UI_FIELDS();
        fonts();
        makeJsonObjReq1();
      // Click();

        lead_cr_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_PL.class);
                startActivity(intent);
                finish();

            }
        });





     if(Lontypename.contains("Personal Loan [Unsecured]") || Lontypename.contains("Business Loan [Unsecured]"))
     {

         type_of_empmnt.setVisibility(View.GONE);
         Type_of_employement_ID = "0";
         co_applicant_ly.setVisibility(View.GONE);
         co_applicant_emp_type.setVisibility(View.GONE);
         //txt_loan_category,loan_type,type_of_empmnt_txt,Loan_amount,name,age,mobile,do_you_have_coApp_txt,coApp_txt_emp_type1
         //wt_mobile

         txt_loan_category.setText("1");
         loan_type.setText("2");

         Loan_amount.setText("3");
         name.setText("4");
         email.setText("5");
         mobile.setText("6");
         wt_mobile.setText("7");
     }else
     {

         txt_loan_category.setText("1");
         loan_type.setText("2");
         type_of_empmnt_txt.setText("3");
         Loan_amount.setText("4");
         name.setText("5");
         email.setText("6");
         mobile.setText("7");
         do_you_have_coApp_txt.setText("8");
         wt_mobile.setText("9");
     }

    }

    private void UI_FIELDS()
    {
        lead_cr_step1 = (AppCompatButton) findViewById(R.id.lead_cr_step1);

        loan_amount_ext = (AppCompatEditText) findViewById(R.id.loan_amount_ext);
        loan_amount_ext.addTextChangedListener(new NumberTextWatcher(loan_amount_ext));
        name_txt = (AppCompatEditText) findViewById(R.id.name_txt);
        email = (AppCompatTextView) findViewById(R.id.email);
        email1 = (AppCompatTextView) findViewById(R.id.email1);
        mobile_no_txt = (AppCompatEditText) findViewById(R.id.mobile_no_txt);
        whats_app_no = (AppCompatEditText) findViewById(R.id.whats_app_no);


        //TextView
        txt_loan_category = (AppCompatTextView) findViewById(R.id.txt_loan_category);
        coApp_txt_emp_type1 = (AppCompatTextView) findViewById(R.id.coApp_txt_emp_type1);
        do_you_have_coApp_txt = (AppCompatTextView) findViewById(R.id.do_you_have_coApp_txt);
        txt_loan_category1 = (AppCompatTextView) findViewById(R.id.txt_loan_category1);
        type_of_empmnt_txt = (AppCompatTextView) findViewById(R.id.type_of_empmnt_txt);
        type_of_empmnt_txt1 = (AppCompatTextView) findViewById(R.id.type_of_empmnt_txt1);

        spinner_loan_category =(Spinner) findViewById(R.id.spinner_loan_category);
        spinner_loan_type =(Spinner) findViewById(R.id.spinner_loan_type);

        spnr_type_of_empmnt = (Spinner) findViewById(R.id.spnr_type_of_empmnt);

        co_applicant_spinner = (Spinner) findViewById(R.id.co_applicant_spinner);
        co_applicant_emp_spinner = (Spinner) findViewById(R.id.co_applicant_emp_spinner);

        type_of_empmnt = (LinearLayout) findViewById(R.id.type_of_empmnt);
        co_applicant_ly = (LinearLayout) findViewById(R.id.co_applicant_ly);
        co_applicant_emp_type = (LinearLayout) findViewById(R.id.co_applicant_emp_type);

        loan_type = (AppCompatTextView) findViewById(R.id.loan_type);
        loan_type1 = (AppCompatTextView) findViewById(R.id.loan_type1);
        Loan_amount = (AppCompatTextView) findViewById(R.id.Loan_amount);
        Loan_amount1 = (AppCompatTextView) findViewById(R.id.Loan_amount1);
        name = (AppCompatTextView) findViewById(R.id.name);
        name1 = (AppCompatTextView) findViewById(R.id.name1);
        mobile = (AppCompatTextView) findViewById(R.id.mobile);
        mobile1 = (AppCompatTextView) findViewById(R.id.mobile1);
        wt_mobile = (AppCompatTextView) findViewById(R.id.wt_mobile);
        wt_mobile11 = (AppCompatTextView) findViewById(R.id.wt_mobile1);
        email_edite_txt = (AppCompatEditText) findViewById(R.id.email_edite_txt);
        terms_and_condition = (AppCompatTextView) findViewById(R.id.terms_and_condition);
        check_complete = (CheckBox) findViewById(R.id.check_complete);

        Mobile = Pref.getMobileLead(getApplicationContext());
        Name = Pref.getName(getApplicationContext());

        name_txt.setText(Name);
        mobile_no_txt.setText(Mobile);

    }
    private void fonts() {

        font = Typeface.createFromAsset(getApplicationContext().getAssets(), "Lato-Regular.ttf");
        loan_amount_ext.setTypeface(font);
        name_txt.setTypeface(font);
        mobile_no_txt.setTypeface(font);
        whats_app_no.setTypeface(font);
        email_edite_txt.setTypeface(font);

        txt_loan_category.setTypeface(font);
        txt_loan_category1.setTypeface(font);
        loan_type.setTypeface(font);
        loan_type1.setTypeface(font);
        Loan_amount.setTypeface(font);
        Loan_amount1.setTypeface(font);
        name.setTypeface(font);
        email.setTypeface(font);
        name1.setTypeface(font);
        mobile.setTypeface(font);
        mobile1.setTypeface(font);
        wt_mobile.setTypeface(font);
        wt_mobile11.setTypeface(font);
        terms_and_condition.setTypeface(font);
        type_of_empmnt_txt.setTypeface(font);
        type_of_empmnt_txt1.setTypeface(font);

    }

  private void Click()
    {
        lead_cr_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Loan_Cat_id.equals("0"))
                {
                    Toast.makeText(context, "Please Select Loan Category", Toast.LENGTH_SHORT).show();
                }else
                {
                        if(App.equals("0"))
                        {
                            Toast.makeText(context, "Please Select Loan Type", Toast.LENGTH_SHORT).show();
                        }
                        else if((App.equals("20")) || (App.equals("21")))
                        {
                            validation_lead();

                        }else
                        {
                            if(Type_of_employement_ID.equals("0"))
                            {
                                Toast.makeText(context, "Please Select Employement Type", Toast.LENGTH_SHORT).show();

                            }else
                            {
                                validation_lead();
                            }
                        }
                }

            }
        });
    }


    private  void validation_lead()
    {
        if (!validateLoanamount()) {
            return;
        }
        if (!validateName()) {
            return;
        }
        if (!validate_email()) {
            return;
        }
        if (!validateMobile()) {
            return;
        }

            validate_wats_App();


    }

    private void validate_wats_App()
    {
        if (!validate_wt_Mobile()) {
            return;
        }
        if(check_complete.isChecked())
        {

            lead_cr();

            Log.e("App",App);
           // click_action();

        }else
        {
            Toast.makeText(context, "Please accept the Terms and condition", Toast.LENGTH_SHORT).show();
        }
    }

    private void click_action()
    {

        if(App.equals("1")|| App.equals("2"))
        {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_check_HL.class);
                    startActivity(intent);
                    finish();

        }else if(App.equals("20"))
        {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_BL.class);
                    startActivity(intent);
                    finish();

        } else if(App.equals("21"))
        {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_PL.class);
                    startActivity(intent);
                    finish();
        }else
        {
            Intent intent = new Intent(Lead_Crration_Activity.this, Viability_check_HL.class);
            startActivity(intent);
            finish();
        }
    }

    private void makeJsonObjReq1() {
        JSONObject J= null;
        try {

            J = new JSONObject();
            J.put("state_id","28");

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        Log.e("state_id", String.valueOf(J));

        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_DROPDOWN_LIST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {

                        try {

                            Employement =object.getJSONArray("Employement");
                            is_coapplicant =object.getJSONArray("is_coapplicant");
                            Log.e("Property_title",String.valueOf(Employement));

                            Type_Of_Employement_Spinner(Employement);
                            Co_Type_Of_Employement_Spinner(Employement);
                            DO_Have_Co_Applicant(is_coapplicant);

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

    private void Type_Of_Employement_Spinner(final JSONArray Type_Of_Employement) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Type_Of_Emp_SA = new String[Type_Of_Employement.length()];
        for (int i=0;i<Type_Of_Employement.length();i++){
            JSONObject J =  Type_Of_Employement.getJSONObject(i);
            Type_Of_Emp_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Type_Of_Emp_SA));
            Type_Of_Emp_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
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

            Type_Of_Emp_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spnr_type_of_empmnt.setAdapter(Type_Of_Emp_Adapter);
            spnr_type_of_empmnt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        Type_of_employement_ID = Type_Of_Employement.getJSONObject(position).getString("id");
                        Type_of_employement_Value = Type_Of_Employement.getJSONObject(position).getString("value");

                        Pref.putSALARYTYPE(context,Type_of_employement_ID);

                        Log.e("The salary Type",Type_of_employement_ID);
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spnr_type_of_empmnt.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Co_Type_Of_Employement_Spinner(final JSONArray Type_Of_emp_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        CO_Type_Of_Emp_SA = new String[Type_Of_emp_ar.length()];
        for (int i=0;i<Type_Of_emp_ar.length();i++){
            JSONObject J =  Type_Of_emp_ar.getJSONObject(i);
            CO_Type_Of_Emp_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(CO_Type_Of_Emp_SA));
            CO_Type_Of_Emp_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
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

            CO_Type_Of_Emp_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            co_applicant_emp_spinner.setAdapter(CO_Type_Of_Emp_Adapter);
            co_applicant_emp_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        CO_Type_of_employement_ID = Type_Of_emp_ar.getJSONObject(position).getString("id");
                        CO_Type_of_employement_Value = Type_Of_emp_ar.getJSONObject(position).getString("value");

                        Pref.putCOSALARYTYPE(context,CO_Type_of_employement_ID);

                        Log.e("The salary Type",CO_Type_of_employement_Value);
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_applicant_emp_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void DO_Have_Co_Applicant(final JSONArray do_u_have_co_) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Have_Co_Applicant = new String[do_u_have_co_.length()];
        for (int i=0;i<do_u_have_co_.length();i++){
            JSONObject J =  do_u_have_co_.getJSONObject(i);
            Have_Co_Applicant[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Have_Co_Applicant));
            Have_Co_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
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

            Have_Co_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            co_applicant_spinner.setAdapter(Have_Co_Adapter);
            co_applicant_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        IS_CO_Applicant_Id = do_u_have_co_.getJSONObject(position).getString("id");
                        IS_CO_Applicant_Value = do_u_have_co_.getJSONObject(position).getString("value");

                        Pref.putCoAPPAVAILABLE(context,IS_CO_Applicant_Id);

                        if(IS_CO_Applicant_Id.equals("1"))
                        {
                            co_applicant_emp_type.setVisibility(View.VISIBLE);

                            do_you_have_coApp_txt.setText("8");
                            coApp_txt_emp_type1.setText("9");
                            wt_mobile.setText("10");

                        }else if(IS_CO_Applicant_Id.equals("2"))
                        {
                            co_applicant_emp_type.setVisibility(View.GONE);
                            do_you_have_coApp_txt.setText("8");
                            wt_mobile.setText("9");


                        }else
                        {
                            co_applicant_emp_type.setVisibility(View.GONE);
                        }

                        Log.e("The salary Type",IS_CO_Applicant_Id);
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_applicant_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private boolean validateLoanamount() {
        if (loan_amount_ext.length() < 6 || loan_amount_ext.length() > 12) {
            loan_amount_ext.setError(getText(R.string.error_empty_loan));
            loan_amount_ext.requestFocus();
            return false;
        } else {
            //     inputLayoutNumber.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateName(){
        if (name_txt.getText().toString().trim().isEmpty() || name_txt.length() < 3) {
            name_txt.setError(getText(R.string.err_curent));
            name_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean validate_email(){
       /* if (email_edite_txt.getText().toString().trim().isEmpty() || email_edite_txt.length() < 3) {

            email_edite_txt.requestFocus();
            return false;
        }*/
        if(email_edite_txt.getText().toString().isEmpty()) {
            email_edite_txt.setError(getText(R.string.err_curent));
            email_edite_txt.requestFocus();
            return false;
        }else {
            if (email_edite_txt.getText().toString().trim().matches(emailPattern)) {
                //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                return true;
            }else {

                Toast.makeText(getApplicationContext(),"", Toast.LENGTH_SHORT).show();
                email_edite_txt.setError("Invalid email address");
                email_edite_txt.requestFocus();
                return false;
            }
        }

    }


    private boolean validateMobile() {
        if (mobile_no_txt.length() < 10 || mobile_no_txt.length() > 10) {
            mobile_no_txt.setError(getText(R.string.err_curent));
            mobile_no_txt.requestFocus();
            return false;
        } else {

        }
        return true;
    }
    private boolean validate_wt_Mobile() {
        if (whats_app_no.length() < 10 || whats_app_no.length() > 10) {
            whats_app_no.setError(getText(R.string.err_curent));
            whats_app_no.requestFocus();
            return false;
        } else {

        }
        return true;
    }

    private void makeJsonObjReq_loancat() {
        progressDialog.show();
        JSONObject J =new JSONObject();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LOAN_CATAGORY, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Loan catgory", object.toString());
                        try {
                            ja1 = object.getJSONArray("loancatlist_arr");
                            if (ja1.length()>0)
                            {
                                setMainSpinner_loancat(ja1);
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

    private void setMainSpinner_loancat( final JSONArray ja1) throws JSONException {

        //   SPINNERLIST = new String[ja.length()];
        SPINNERLIST_CAT = new String[ja1.length()];
        Log.e("the lenth", String.valueOf(ja1.length()));
        for (int i=0; i < ja1.length(); i++){
            JSONObject J =  ja1.getJSONObject(i);
            SPINNERLIST_CAT[i] = J.getString("category_type");
          //  Log.e("catgory list",SPINNERLIST_CAT.toString());
            final List<String> Loan_cat_list = new ArrayList<>(Arrays.asList(SPINNERLIST_CAT));
            Loantype_cat = new ArrayAdapter<String>(context, R.layout.view_spinner_item, Loan_cat_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
                     v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                     v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            Loantype_cat.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_loan_category.setAdapter(Loantype_cat);
            spinner_loan_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        Loan_Cat_id = ja1.getJSONObject(position).getString("id");
                        makeJsonObjReq1(Loan_Cat_id);
                        Log.e("Selected_cat_id", String.valueOf(Loan_Cat_id));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_loan_category.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

        int loancat_name1 = Integer.parseInt(Lontype);
        Log.e("loancat_name1", String.valueOf(loancat_name1));
        if(loancat_name1 == -1)
        {
            String message = Lontypename + " : Item not found.";

        }
        else
        {
            spinner_loan_category.setSelection(loancat_name1);
            String message = Lontypename + " : Item found and selected.";
            //  Objs.a.showToast(getActivity(), message);
        }
    }

    private void makeJsonObjReq1(String Loan_Cat_id) {
        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("category_id", Loan_Cat_id);
            Log.e("category_id111", String.valueOf(J));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LOAN_TYPE_POST11, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("respose L_type", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        try {
                            ja = object.getJSONArray("loantypelist_arr");
                            setMainSpinner1(ja);
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

    private void setMainSpinner1(final JSONArray ja) throws JSONException {

        //   SPINNERLIST = new String[ja.length()];
        SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[i] = J.getString("loan_type");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(SPINNERLIST));
            Loantype1 = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
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

            Loantype1.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_loan_type.setAdapter(Loantype1);
            spinner_loan_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        App = ja.getJSONObject(position).getString("id");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.e("Add Applicant Info", String.valueOf(App));

                        Pref.putLoanType(mCon,App);

                        int a = Integer.parseInt(App);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_loan_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

        int loantypename1 = Loantype1.getPosition(Lontypename);
        if(loantypename1 == -1)
        {
            String message = Lontypename + " : Item not found.";
            // Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        }
        else
        {
            spinner_loan_type.setSelection(loantypename1);
            String message = Lontypename + " : Item found and selected.";
           // Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        }
        /// loadSubLocations(ja.getJSONObject(0).getString("countryid"));
    }

    private void lead_cr( ) {
        C_loan_amount_ext = loan_amount_ext.getText().toString();
        String stringNumber = C_loan_amount_ext;
        result = stringNumber.replace(",","");
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;

        int m = 1;

        C_mobile_no_txt = mobile_no_txt.getText().toString();
        C_whats_app_no = whats_app_no.getText().toString();
        C_name_txt = name_txt.getText().toString();
        C_email_edite_txt = email_edite_txt.getText().toString();


        try {
            J =new JSONObject();
             J.put("b2b_userid",Pref.getID(getApplicationContext()));
            J.put("user_name",C_name_txt);
            J.put("email_id",C_email_edite_txt);
            J.put("mobileno",C_mobile_no_txt);
            J.put("loan_amount",result);
            J.put("whatsapp",C_whats_app_no);
            J.put("employment_type",Type_of_employement_ID);
            J.put("loan_type",App);
            J.put("loan_cat",Loan_Cat_id);
            J.put("terms_cond",m);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Add Home Laoan", String.valueOf(J));
        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Lead_Creation, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        Log.e("Lead creation", String.valueOf(response));
                        try {

                            JSONObject jsonObject1 = response.getJSONObject("reponse");

                            if(jsonObject1.getString("status").equals("success")) {

                                if(App.equals("1"))
                                {
                                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_check_HL.class);
                                    startActivity(intent);
                                    finish();
                                }else if(App.equals("2"))
                                {
                                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_check_HL.class);
                                    startActivity(intent);
                                    finish();

                                } else if(App.equals("20"))
                                {
                                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_BL.class);
                                    startActivity(intent);
                                    finish();

                                } else if(App.equals("21"))
                                {
                                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_PL.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            if(jsonObject1.getString(Params.status).equals("error")) {
                                Objs.a.showToast(context, "Already Registered with Propwiser");
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

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
        finish();
        super.onBackPressed();
    }

}
