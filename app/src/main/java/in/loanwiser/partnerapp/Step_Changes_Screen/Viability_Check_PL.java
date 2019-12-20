package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.doodle.android.chips.ChipsView;
import com.doodle.android.chips.model.Contact;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.NumberTextWatcher;
import in.loanwiser.partnerapp.PartnerActivitys.Add_Applicant;
import in.loanwiser.partnerapp.PartnerActivitys.IncomeProofPOJO;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Viability_Check_PL extends SimpleActivity {

    AppCompatButton lead_viy_step2;
    private Spinner spinner_residence_type,spinner_employe_id,spinn_salary_crt_mtd,
            spinner_salary_proof,has_pan_card_spnr,Other_family_income_spnr;
    LinearLayout residence_type,residence_live,pan_card_available,other_earning_avbl;
    RadioGroup has_pan_card,applicant_family_OEM;
    RadioButton yes_pan,no_pan,other__OEM_family_yes,other_OEM_family_no;
    AppCompatTextView age,age1,pan_number_txt,pan_number_txt1,Pan_number_txt,Pan_number1_txt,
                     occupation_txt,occupation_txt1,monthly_sal_txt,monthly_sal_txt1,emp_id,emp_id1,
                     salery_credite_method_txt,salery_credite_method_txt1,salary_proof_txt,salary_proof_txt1,Exp_in_current_txt,
                     Exp_in_current_txt1,total_workexperiecnce_txt,total_workexperiecnce_txt1,
                     cmp_pincode_txt,cmp_pincode_txt1,txt_residence_pincode,txt_residence_pincode1,txt_residence_type,
                    txt_residence_type1,Lives_in_current_txt,Lives_in_current_txt1,any_other_family_member_txt,
                      any_other_family_member_txt1,family_member_name_txt,family_member_name_txt1,family_member_income_txt,
            family_member_income_txt1,monthly_afr_emi_txt,monthly_afr_emi_txt1;

    AppCompatEditText age_edite_txt,pan_number_edit_txt,occupation_edit_txt,monthly_net_sal_edit_txt,
                         experience_in_current_cmpy,total_experience_edit_txt
                         ,current_residence_edit_txt,family_member_name_edit_txt,
            family_member_income_edit_txt,monthly_afr_emi_amt_edit_txt;
    AppCompatAutoCompleteTextView company_pincode_txt,residence_pincode1_edit_txt;
    Typeface font;
    private Context context = this;
    InputMethodManager imm;
    JSONArray ja= new JSONArray();
    JSONArray Residence_ownership_ar,Salary_method_ar,Salary_proof_ar,employee_id_ar,have_pan_ar,
                    other_earning_ar;
    String[] SPINNERLIST;
    String[] SALARY_Method,Salary_Proof,Residence_Type_SA,Employe_ID_SA,PAN_ID_SA,Other_Earning_SA,Pincode_SA;

    ArrayAdapter<String> Salary_Adapter,Salary_proof_Adapter,Residence_Adapter,Employee_ID_Adapter,
            PAN_ID_Adapter,Other_Earning_Adapter,Pincode_Adapter;

    String String_value_Age,ST_occupation_edit_txt,St_monthly_net_sal_edit_txt,
            ST_experience_in_current_cmpy,ST_total_experience_edit_txt,
            ST_company_pincode_txt,ST_residence_pincode1_edit_txt,
            ST_current_residence_edit_txt,ST_monthly_afr_emi_amt_edit_txt,result;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;

    String Salary_id,Salary_Value,Salary_proof_id,Salary_proof_Value,residence_id,residence_Value,
                 Employee_id,Employee_Value,PAN_id,PAN_Value, other_earning_id,other_earning_value;

    ArrayList<IncomeProofPOJO> IncomeProof_Salaried,Salary_income_Proof;
    MyCustomAdapter_Salary_Proof dataAdapter_Salaried_proof = null;
    private ChipsView cv_salary_income_proof;
    StringBuffer salary_proof_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_viability__check);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viability__check);
        initTools(R.string.viy_check);

        lead_viy_step2 = (AppCompatButton) findViewById(R.id.lead_viy_step2);

        progressDialog = new SpotsDialog(context, R.style.Custom);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);


        lead_viy_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_PL.this, Eligibility_Check_PL.class);
                startActivity(intent);
                finish();
            }
        });


        UISCREEN();
    //   Click();
        fonts();
        makeJsonObjReq1();
    }

    private void UISCREEN() {

        spinner_residence_type = (Spinner) findViewById(R.id.spinner_residence_type);
        spinner_employe_id = (Spinner) findViewById(R.id.spinner_employe_id);
        spinn_salary_crt_mtd = (Spinner) findViewById(R.id.spinn_salary_crt_mtd);
        spinner_residence_type = (Spinner) findViewById(R.id.spinner_residence_type);
        spinner_salary_proof = (Spinner) findViewById(R.id.spinner_salary_proof);
        has_pan_card_spnr = (Spinner) findViewById(R.id.has_pan_card_spnr);
        Other_family_income_spnr = (Spinner) findViewById(R.id.Other_family_income_spnr);

        residence_type = (LinearLayout) findViewById(R.id.residence_type);
        residence_live = (LinearLayout) findViewById(R.id.residence_live);

        pan_card_available = (LinearLayout) findViewById(R.id.pan_card_available);
        other_earning_avbl = (LinearLayout) findViewById(R.id.other_earning_avbl);

        residence_type.setVisibility(View.VISIBLE);


        age = (AppCompatTextView) findViewById(R.id.age);
        age1 = (AppCompatTextView) findViewById(R.id.age1);
        pan_number_txt = (AppCompatTextView) findViewById(R.id.pan_number_txt);
        pan_number_txt1 = (AppCompatTextView) findViewById(R.id.pan_number_txt1);
        occupation_txt = (AppCompatTextView) findViewById(R.id.occupation_txt);
        occupation_txt1 = (AppCompatTextView) findViewById(R.id.occupation_txt1);
        monthly_sal_txt = (AppCompatTextView) findViewById(R.id.monthly_sal_txt);
        monthly_sal_txt1 = (AppCompatTextView) findViewById(R.id.monthly_sal_txt1);
        emp_id = (AppCompatTextView) findViewById(R.id.emp_id);
        emp_id1 = (AppCompatTextView) findViewById(R.id.emp_id1);
        salery_credite_method_txt = (AppCompatTextView) findViewById(R.id.salery_credite_method_txt);
        salery_credite_method_txt1 = (AppCompatTextView) findViewById(R.id.salery_credite_method_txt1);
        salary_proof_txt = (AppCompatTextView) findViewById(R.id.salary_proof_txt);
        salary_proof_txt1 = (AppCompatTextView) findViewById(R.id.salary_proof_txt1);
        Exp_in_current_txt = (AppCompatTextView) findViewById(R.id.Exp_in_current_txt);
        Exp_in_current_txt1 = (AppCompatTextView) findViewById(R.id.Exp_in_current_txt1);
        total_workexperiecnce_txt = (AppCompatTextView) findViewById(R.id.total_workexperiecnce_txt);
        total_workexperiecnce_txt1 = (AppCompatTextView) findViewById(R.id.total_workexperiecnce_txt1);
        cmp_pincode_txt = (AppCompatTextView) findViewById(R.id.cmp_pincode_txt);
        cmp_pincode_txt1 = (AppCompatTextView) findViewById(R.id.cmp_pincode_txt1);
        txt_residence_pincode = (AppCompatTextView) findViewById(R.id.txt_residence_pincode);
        txt_residence_pincode1 = (AppCompatTextView) findViewById(R.id.txt_residence_pincode1);
        txt_residence_type = (AppCompatTextView) findViewById(R.id.txt_residence_type);
        txt_residence_type1 = (AppCompatTextView) findViewById(R.id.txt_residence_type1);
        Lives_in_current_txt = (AppCompatTextView) findViewById(R.id.Lives_in_current_txt);
        Lives_in_current_txt1 = (AppCompatTextView) findViewById(R.id.Lives_in_current_txt1);
        any_other_family_member_txt = (AppCompatTextView) findViewById(R.id.any_other_family_member_txt);
        any_other_family_member_txt1 = (AppCompatTextView) findViewById(R.id.any_other_family_member_txt1);
        family_member_name_txt = (AppCompatTextView) findViewById(R.id.family_member_name_txt);
        family_member_name_txt1 = (AppCompatTextView) findViewById(R.id.family_member_name_txt1);
        family_member_income_txt = (AppCompatTextView) findViewById(R.id.family_member_income_txt);
        family_member_income_txt1 = (AppCompatTextView) findViewById(R.id.family_member_income_txt1);
        monthly_afr_emi_txt = (AppCompatTextView) findViewById(R.id.monthly_afr_emi_txt);
        monthly_afr_emi_txt1 = (AppCompatTextView) findViewById(R.id.monthly_afr_emi_txt1);


        age_edite_txt = (AppCompatEditText) findViewById(R.id.age_edite_txt);
        pan_number_edit_txt = (AppCompatEditText) findViewById(R.id.pan_number_edit_txt);
        occupation_edit_txt = (AppCompatEditText) findViewById(R.id.occupation_edit_txt);
        monthly_net_sal_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_net_sal_edit_txt);
        monthly_net_sal_edit_txt.addTextChangedListener(new NumberTextWatcher(monthly_net_sal_edit_txt));
        experience_in_current_cmpy = (AppCompatEditText) findViewById(R.id.experience_in_current_cmpy);
        total_experience_edit_txt = (AppCompatEditText) findViewById(R.id.total_experience_edit_txt);
        company_pincode_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.company_pincode_txt);

        residence_pincode1_edit_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.residence_pincode1_edit_txt);

        current_residence_edit_txt = (AppCompatEditText) findViewById(R.id.current_residence_edit_txt);
        family_member_name_edit_txt = (AppCompatEditText) findViewById(R.id.family_member_name_edit_txt);
        family_member_income_edit_txt = (AppCompatEditText) findViewById(R.id.family_member_income_edit_txt);
        monthly_afr_emi_amt_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_afr_emi_amt_edit_txt);
        monthly_afr_emi_amt_edit_txt.addTextChangedListener(new NumberTextWatcher(monthly_net_sal_edit_txt));
        cv_salary_income_proof = (ChipsView) findViewById(R.id.cv_salary_income_proof);


    }

    private void fonts()
    {


        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
        age.setTypeface(font);
        age1.setTypeface(font);
        pan_number_txt.setTypeface(font);
        pan_number_txt1.setTypeface(font);
        occupation_txt.setTypeface(font);
        occupation_txt1.setTypeface(font);
        monthly_sal_txt.setTypeface(font);
        monthly_sal_txt1.setTypeface(font);
        emp_id.setTypeface(font);
        salery_credite_method_txt.setTypeface(font);
        salery_credite_method_txt1.setTypeface(font);
        salary_proof_txt.setTypeface(font);
        salary_proof_txt1.setTypeface(font);
        Exp_in_current_txt.setTypeface(font);
        Exp_in_current_txt1.setTypeface(font);
        total_workexperiecnce_txt.setTypeface(font);
        total_workexperiecnce_txt1.setTypeface(font);
        cmp_pincode_txt.setTypeface(font);
        cmp_pincode_txt1.setTypeface(font);

        txt_residence_pincode.setTypeface(font);
        txt_residence_pincode1.setTypeface(font);
        txt_residence_type.setTypeface(font);
        txt_residence_type1.setTypeface(font);
        Lives_in_current_txt.setTypeface(font);
        Lives_in_current_txt1.setTypeface(font);
        any_other_family_member_txt.setTypeface(font);
        any_other_family_member_txt1.setTypeface(font);
        family_member_name_txt.setTypeface(font);
        family_member_name_txt1.setTypeface(font);
        family_member_income_txt.setTypeface(font);
        family_member_income_txt1.setTypeface(font);
        monthly_afr_emi_txt.setTypeface(font);
        monthly_afr_emi_txt1.setTypeface(font);


        age_edite_txt.setTypeface(font);
        pan_number_edit_txt.setTypeface(font);
        occupation_edit_txt.setTypeface(font);
        monthly_net_sal_edit_txt.setTypeface(font);
        experience_in_current_cmpy.setTypeface(font);
        total_experience_edit_txt.setTypeface(font);
        company_pincode_txt.setTypeface(font);
        residence_pincode1_edit_txt.setTypeface(font);
        current_residence_edit_txt.setTypeface(font);
        family_member_name_edit_txt.setTypeface(font);
        family_member_income_edit_txt.setTypeface(font);
        monthly_afr_emi_amt_edit_txt.setTypeface(font);

    }

    private void Click()
    {

        company_pincode_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("hi","hi11");
                String workpincode = company_pincode_txt.getText().toString();

                if(workpincode.length()==2){
                    GET_Pincode1(workpincode);
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        residence_pincode1_edit_txt.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.e("hi","hi11");
            String workpincode = residence_pincode1_edit_txt.getText().toString();

            if(workpincode.length()==2){
                GET_Pincode1(workpincode);
            }

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

        lead_viy_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                salary_proof_list = new StringBuffer();

                if (!Validate_age()) {
                    return;
                }

                if(PAN_id.equals("0"))
                {
                    Toast.makeText(context,"Please Select Having PAN Card",Toast.LENGTH_SHORT).show();

                }else
                {
                    if (!Validate_Occupation()) {
                        return;
                    }
                    if (!Validate_net_income()) {
                        return;
                    }
                    if(Employee_id.equals("0"))
                    {
                        Toast.makeText(context,"Please Select Employee id",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        if(Salary_id.equals("0"))
                        {
                            Toast.makeText(context,"Please Select Salary Credit Method",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            /*if(salary_proof_list.length() < 0 )
                            {

                            } else
                            {
                                Toast.makeText(context,"Please Select Salary Proof",Toast.LENGTH_SHORT).show();
                            }*/

                            if (!Validate_experience()) {
                                return;
                            }

                            if (!Validate_total_experience()) {
                                return;
                            }

                            if (!Company_locationpincode1()) {
                                return;
                            }

                            if (!residence_pincode1()) {
                                return;
                            }
                            if(residence_id.equals("0"))
                            {
                                Toast.makeText(context,"Please Select Residence Type",Toast.LENGTH_SHORT).show();

                            }else
                            {

                                if(residence_id.equals("1"))
                                {
                                    if (!Validate_Monthly_Emi()) {
                                        return;
                                    }

                                    lead_viability();

                                }else if(residence_id.equals("2"))
                                {

                                    if (!curennt_resi_v()) {
                                        return;
                                    }

                                    if (!Validate_Monthly_Emi()) {
                                        return;
                                    }

                                    lead_viability();
                                }

                            }
                        }
                    }
                }

            }
        });

    }

    private boolean Validate_age() {

        if (age_edite_txt.length() < 1 || age_edite_txt.length() > 3) {
            age_edite_txt.setError(getText(R.string.age_vali));
            age_edite_txt.requestFocus();
            return false;
        } else {
            //     inputLayoutNumber.setErrorEnabled(false);
        }
        return true;
    }

    private boolean Validate_Occupation(){
        if (occupation_edit_txt.getText().toString().trim().isEmpty() || occupation_edit_txt.length() < 3) {
            occupation_edit_txt.setError(getText(R.string.error_occupation));
            occupation_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_net_income() {

        if (monthly_net_sal_edit_txt.length() < 5) {
            monthly_net_sal_edit_txt.setError(getText(R.string.net_salary));
            monthly_net_sal_edit_txt.requestFocus();
            return false;
        } else {
            //     inputLayoutNumber.setErrorEnabled(false);
        }
        return true;
    }

    private boolean Validate_experience(){
        if (experience_in_current_cmpy.getText().toString().trim().isEmpty()) {
            experience_in_current_cmpy.setError(getText(R.string.error_experience));
            experience_in_current_cmpy.requestFocus();
            return false;
        } else {

        }

        return true;
    }



    private boolean Validate_Monthly_Emi(){

        if (monthly_afr_emi_amt_edit_txt.getText().toString().trim().isEmpty()) {
            monthly_afr_emi_amt_edit_txt.setError(getText(R.string.net_salary));
            monthly_afr_emi_amt_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_total_experience(){
        if (total_experience_edit_txt.getText().toString().trim().isEmpty()) {
            total_experience_edit_txt.setError(getText(R.string.error_total_experience));
            total_experience_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Company_locationpincode1(){
        if (company_pincode_txt.getText().toString().isEmpty()) {
            company_pincode_txt.setError(getText(R.string.error_pincode));
            company_pincode_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean curennt_resi_v(){
        if (current_residence_edit_txt.getText().toString().isEmpty()) {
            current_residence_edit_txt.setError(getText(R.string.err_curent));
            current_residence_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean residence_pincode1(){
        if (residence_pincode1_edit_txt.getText().toString().isEmpty()) {
            residence_pincode1_edit_txt.setError(getText(R.string.error_pincode));
            residence_pincode1_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }


    private void makeJsonObjReq1() {
        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_DROPDOWN_LIST, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("respose Dreopdown", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        try {

                             Residence_ownership_ar =object.getJSONArray("Residence_ownership");
                             Salary_method_ar =object.getJSONArray("Salary_method");
                             Salary_proof_ar =object.getJSONArray("Salary_proof");
                            employee_id_ar =object.getJSONArray("employee_id");
                            other_earning_ar =object.getJSONArray("other_earning");
                            have_pan_ar =object.getJSONArray("have_pan");
                            Salry_method_Spinner(Salary_method_ar);
                            Salry_Proof(Salary_proof_ar);
                            Residence_Array(Residence_ownership_ar);
                            Employee_ID_Array(employee_id_ar);
                            HAVE_PAN_Card(have_pan_ar);
                            Other_Earning(other_earning_ar);


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

    private void Salry_method_Spinner(final JSONArray Salary_method_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        SALARY_Method = new String[Salary_method_ar.length()];
        for (int i=0;i<Salary_method_ar.length();i++){
            JSONObject J =  Salary_method_ar.getJSONObject(i);
            SALARY_Method[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(SALARY_Method));
            Salary_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Salary_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spinn_salary_crt_mtd.setAdapter(Salary_Adapter);
            spinn_salary_crt_mtd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");


                        Salary_id = Salary_method_ar.getJSONObject(position).getString("id");
                        Salary_Value = Salary_method_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", Salary_id);
                        Log.d("Salary_Value", Salary_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinn_salary_crt_mtd.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Residence_Array(final JSONArray Residence_ownership_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Residence_Type_SA = new String[Residence_ownership_ar.length()];
        for (int i=0;i<Residence_ownership_ar.length();i++){
            JSONObject J =  Residence_ownership_ar.getJSONObject(i);
            Residence_Type_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Residence_Type_SA));
            Residence_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Residence_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_residence_type.setAdapter(Residence_Adapter);
            spinner_residence_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        residence_id = Residence_ownership_ar.getJSONObject(position).getString("id");
                        residence_Value = Residence_ownership_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", residence_id);
                        Log.d("Salary_Value", residence_Value);

                        if(residence_id.equals("2") )
                        {
                            monthly_afr_emi_txt.setText("14");
                            residence_live.setVisibility(View.VISIBLE);

                        }else
                        {
                            monthly_afr_emi_txt.setText("13");
                            residence_live.setVisibility(View.GONE);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_residence_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Employee_ID_Array(final JSONArray employee_id_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Employe_ID_SA = new String[employee_id_ar.length()];
        for (int i=0;i<employee_id_ar.length();i++){
            JSONObject J =  employee_id_ar.getJSONObject(i);
            Employe_ID_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Employe_ID_SA));
            Employee_ID_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Employee_ID_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_employe_id.setAdapter(Employee_ID_Adapter);
            spinner_employe_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Employee_id = employee_id_ar.getJSONObject(position).getString("id");
                        Employee_Value = employee_id_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", Employee_id);
                        Log.d("Salary_Value", Employee_Value);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_employe_id.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void HAVE_PAN_Card(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        PAN_ID_SA = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            PAN_ID_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(PAN_ID_SA));
            PAN_ID_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            PAN_ID_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            has_pan_card_spnr.setAdapter(PAN_ID_Adapter);
            has_pan_card_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        PAN_id = has_pancard_ar.getJSONObject(position).getString("id");
                        PAN_Value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", PAN_id);
                        Log.d("Salary_Value", PAN_Value);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            has_pan_card_spnr.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Other_Earning(final JSONArray other_earning_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Other_Earning_SA = new String[other_earning_ar.length()];
        for (int i=0;i<other_earning_ar.length();i++){
            JSONObject J =  other_earning_ar.getJSONObject(i);
            Other_Earning_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Other_Earning_SA));
            Other_Earning_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Other_Earning_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            Other_family_income_spnr.setAdapter(Other_Earning_Adapter);
            Other_family_income_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        other_earning_id = other_earning_ar.getJSONObject(position).getString("id");
                        other_earning_value = other_earning_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", other_earning_id);
                        Log.d("Salary_Value", other_earning_value);

                        if(other_earning_id.equals("1"))
                        {
                            other_earning_avbl.setVisibility(View.VISIBLE);
                        }else
                        {
                            other_earning_avbl.setVisibility(View.GONE);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Other_family_income_spnr.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Salry_Proof(final JSONArray ja) throws JSONException {

        Salary_income_Proof = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            Salary_income_Proof.add(salary_proof);
        }
        dataAdapter_Salaried_proof = new MyCustomAdapter_Salary_Proof(context, 0,Salary_income_Proof);
        spinner_salary_proof.setAdapter(dataAdapter_Salaried_proof);
        dataAdapter_Salaried_proof.notifyDataSetChanged();




    }

    private class MyCustomAdapter_Salary_Proof extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Salary_proof;
        IncomeProofPOJO salary_proof;
        public MyCustomAdapter_Salary_Proof(Context context, int textViewResourceId,
                                        ArrayList<IncomeProofPOJO> Salary_proof) {
            super(context, textViewResourceId, Salary_proof);
            this.Salary_proof = new ArrayList<IncomeProofPOJO>();
            this.Salary_proof.addAll(Salary_proof);
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            MyCustomAdapter_Salary_Proof.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Salary_Proof.ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.code);
                holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);


                holder.name.setTypeface(font);
                holder.name.setTextSize(13);
                holder.code.setTypeface(font);
                holder.code.setTextSize(13);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;
                        IncomeProofPOJO salary_proof = (IncomeProofPOJO) cb.getTag();
                        salary_proof.setIP_selected(cb.isChecked());

                        String email = salary_proof.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_salary_income_proof.addChip(email, imgUrl, contact);
                        } else {
                            cv_salary_income_proof.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Salary_Proof.ViewHolder) convertView.getTag();
            }

            salary_proof = Salary_proof.get(position);
            holder.name.setText(salary_proof.getIP_name());
            holder.name.setChecked(salary_proof.isIP_selected());
            holder.name.setTag(salary_proof);

            if(salary_proof.getIP_name().contains("Salary Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Salary Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }



    ///Auto Completer Pin Code

    private void GET_Pincode1(String code) {
        // progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("pincode", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_PINCODE_POST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            if (object.getString(Params.status).equals("success")) {
                                JSONArray response = object.getJSONArray("response");
                                // Log.e("Pincode", String.valueOf(response));

                                setMain_Area1(response);
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

    private void setMain_Area1(final JSONArray ja) throws JSONException {


        Pincode_SA = new String[ja.length()];


        /*for (int i =occupation.length() - 1;i >= 0; i--) {
                   occupation.remove(i);
               }*/
        for (int i=0;i<ja.length();i++) {


            JSONObject J = ja.getJSONObject(i);
            Pincode_SA[i] = J.getString("pincode");
            final List<String> Pincode_list = new ArrayList<>(Arrays.asList(Pincode_SA));
            HashSet<String> hashSet = new HashSet<String>();

            hashSet.addAll(Pincode_list);
            Pincode_list.clear();
            Pincode_list.addAll(hashSet);
            //ArrayList<Integer> newList = removeDuplicates(Pincode_list);
            Pincode_Adapter = new ArrayAdapter<String>(context.getApplicationContext(),
                    R.layout.view_spinner_item, Pincode_list) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
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

           // company_pincode_txt.setThreshold(2);
          //  company_pincode_txt.setAdapter(Pincode_Adapter);

            String workpincode = residence_pincode1_edit_txt.getText().toString();
            String workpincode1 = residence_pincode1_edit_txt.getText().toString();

            if(workpincode.length()> 2){
                company_pincode_txt.setThreshold(2);
                company_pincode_txt.setAdapter(Pincode_Adapter);
            }

            if(workpincode1.length()> 2){
                residence_pincode1_edit_txt.setThreshold(2);
                residence_pincode1_edit_txt.setAdapter(Pincode_Adapter);
            }



        }

        company_pincode_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);
            }
        });

        residence_pincode1_edit_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);

            }
        });


    }

    private void lead_viability() {

        String_value_Age = age_edite_txt.getText().toString();
        ST_occupation_edit_txt = occupation_edit_txt.getText().toString();
        St_monthly_net_sal_edit_txt = monthly_net_sal_edit_txt.getText().toString();

        ST_experience_in_current_cmpy = experience_in_current_cmpy.getText().toString();
        ST_total_experience_edit_txt = total_experience_edit_txt.getText().toString();
        ST_company_pincode_txt = company_pincode_txt.getText().toString();
        ST_residence_pincode1_edit_txt = residence_pincode1_edit_txt.getText().toString();
        ST_current_residence_edit_txt = current_residence_edit_txt.getText().toString();
        ST_monthly_afr_emi_amt_edit_txt = monthly_afr_emi_amt_edit_txt.getText().toString();


        String stringNumber = St_monthly_net_sal_edit_txt;
        result = stringNumber.replace(",","");
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            //  J.put(Params.email_id,email);
            J.put("String_value_Age",String_value_Age);
            J.put("ST_occupation_edit_txt",ST_occupation_edit_txt);
            J.put("St_monthly_net_sal_edit_txt",St_monthly_net_sal_edit_txt);
            J.put("ST_experience_in_current_cmpy",ST_experience_in_current_cmpy);
            J.put("ST_total_experience_edit_txt",ST_total_experience_edit_txt);
            J.put("ST_company_pincode_txt",ST_company_pincode_txt);
            J.put("ST_residence_pincode1_edit_txt",ST_residence_pincode1_edit_txt);
            J.put("ST_current_residence_edit_txt",ST_current_residence_edit_txt);
            J.put("ST_monthly_afr_emi_amt_edit_txt",ST_monthly_afr_emi_amt_edit_txt);
            J.put("PAN_id",PAN_id);
            J.put("Employee_id",Employee_id);
            J.put("Salary_id",Salary_id);
            J.put("residence_id",residence_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Add Home Laoan", String.valueOf(J));
        progressDialog.show();
      /*  JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.ADD_LEAD_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        Log.e("Add_Home_loan Partner", String.valueOf(response));
                        try {

                            if(response.getString(Params.status).equals("Ok")) {

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
                                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_PL.class);
                                    startActivity(intent);
                                    finish();

                                } else if(App.equals("21"))
                                {
                                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_BL.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            if(response.getString(Params.status).equals("error")) {
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

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);*/
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Lead_Crration_Activity.class);
        finish();
        super.onBackPressed();

    }
}
