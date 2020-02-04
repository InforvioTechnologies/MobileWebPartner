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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Multi_select_checkbox.Multi_Select_checkbox;
import in.loanwiser.partnerapp.NumberTextWatcher;
import in.loanwiser.partnerapp.PartnerActivitys.Add_Applicant;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.IncomeProofPOJO;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Viability_Check_PL extends SimpleActivity {

    AppCompatButton lead_viy_step2;

    private Spinner spinner_residence_type,spinner_employe_id,spinn_salary_crt_mtd,
            spinner_salary_proof,has_pan_card_spnr,Other_family_income_spnr,spinn_area,res_spinn_area,
            co_applicant_spinner,co_applicant_emp_spinner;



    LinearLayout residence_type,residence_live,pan_card_available,other_earning_avbl,other_family_mem,asstes_own,
            co_applicant_ly,co_applicant_emp_type,co_applicant_salaried_employed,co_applicant_self_employed;
    RadioGroup has_pan_card,applicant_family_OEM;
    RadioButton yes_pan,no_pan,other__OEM_family_yes,other_OEM_family_no;
    AppCompatTextView age,age1,pan_number_txt,pan_number_txt1,Pan_number_txt,Pan_number1_txt,
                     occupation_txt,occupation_txt1,monthly_sal_txt,monthly_sal_txt1,emp_id,emp_id1,
                     salery_credite_method_txt,salery_credite_method_txt1,salary_proof_txt,salary_proof_txt1,Exp_in_current_txt,
                     Exp_in_current_txt1,total_workexperiecnce_txt,total_workexperiecnce_txt1,
                     cmp_pincode_txt,cmp_pincode_txt1,txt_residence_pincode,txt_residence_pincode1,txt_residence_type,
                    txt_residence_type1,Lives_in_current_txt,Lives_in_current_txt1,any_other_family_member_txt,
                      any_other_family_member_txt1,family_member_name_txt,family_member_name_txt1,family_member_income_txt,
            family_member_income_txt1,monthly_afr_emi_txt,monthly_afr_emi_txt1,salary_proof_edit_txt_pl,assets_owned_BL,
            assets_owned_txt,area_txt,area_txt1,res_area_txt,res_area_txt1,do_you_have_coApp_txt,coApp_txt_emp_type1,
            do_you_have_coApp_txt1,coApp_txt_emp_type2;

    AppCompatEditText age_edite_txt,pan_number_edit_txt,occupation_edit_txt,monthly_net_sal_edit_txt,
                         experience_in_current_cmpy,total_experience_edit_txt
                         ,current_residence_edit_txt,family_member_name_edit_txt,
            family_member_income_edit_txt,monthly_afr_emi_amt_edit_txt,
            pl_co_app_slrd_name_edite_txt,pl_co_app_slrd_age_edite_txt,pl_co_app_slrd_month_net_slrd_edite_txt,pl_co_app_slrd_experience_in_current_cmpy,
            pl_co_app_slrd_total_experience_edit_txt;

    //Co Applicant
    private Spinner pl_co_app_slrd_spinn_salary_crt_mtd,pl_co_app_slrd_res_spinn_area;

    AppCompatAutoCompleteTextView company_pincode_txt,residence_pincode1_edit_txt,pl_co_app_slrd_company_pincode_txt;
    String pl_co_app_slrd_Salary_id,pl_co_app_slrd_Salary_Value,pl_co_app_slrd_res_spinn_area_id,pl_co_app_slrd_res_spinn_area_district_id,
            pl_co_app_slrd_res_spinn_area_state_id;

    AppCompatTextView pl_co_app_slrd_salary_proof_edit_txt,pl_co_app_slrd_assets_owned_BL;

    ////////

    JSONArray Employement,is_coapplicant;
    Typeface font;
    private Context context = this;
    InputMethodManager imm;
    JSONArray ja= new JSONArray();
    JSONArray Residence_ownership_ar,Salary_method_ar,Salary_proof_ar,employee_id_ar,have_pan_ar,
                    other_earning_ar,Property_Type,Assets_own;
    String[] SPINNERLIST;
    String[] SALARY_Method,Salary_Proof,Residence_Type_SA,Employe_ID_SA,PAN_ID_SA,
            Other_Earning_SA,Pincode_SA,Area,CO_Type_Of_Emp_SA,Have_Co_Applicant;

    ArrayAdapter<String> Salary_Adapter,Salary_proof_Adapter,Residence_Adapter,Employee_ID_Adapter,
            PAN_ID_Adapter,Other_Earning_Adapter,Pincode_Adapter,A_Area,CO_Type_Of_Emp_Adapter,
            Have_Co_Adapter;

    String String_value_Age,ST_occupation_edit_txt,St_monthly_net_sal_edit_txt,
            ST_experience_in_current_cmpy,ST_total_experience_edit_txt,
            ST_company_pincode_txt,ST_residence_pincode1_edit_txt,
            ST_current_residence_edit_txt,ST_monthly_afr_emi_amt_edit_txt,result,
            company_area,company_area_district_id,company_area_state_id,
            res_company_area,res_company_area_district_id,res_company_area_state_id;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;

    String Salary_id,Salary_Value,Salary_proof_id,Salary_proof_Value,residence_id,residence_Value,
                 Employee_id,Employee_Value,PAN_id,PAN_Value, other_earning_id,other_earning_value,
            CO_Type_of_employement_ID,CO_Type_of_employement_Value, IS_CO_Applicant_Id,IS_CO_Applicant_Value;

    ArrayList<IncomeProofPOJO> IncomeProof_Salaried,Salary_income_Proof;
    MyCustomAdapter_Salary_Proof dataAdapter_Salaried_proof = null;
    private ChipsView cv_salary_income_proof;
    StringBuffer salary_proof_list;
    ArrayList<String> myList_values;
    ArrayList<String> Assets_myList_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_viability__check);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viability__check);
        initTools(R.string.viy_check);

        lead_viy_step2 = (AppCompatButton) findViewById(R.id.lead_viy_step2);
        assets_owned_BL = (AppCompatTextView) findViewById(R.id.assets_owned_BL);
        progressDialog = new SpotsDialog(context, R.style.Custom);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        salary_proof_edit_txt_pl = (AppCompatTextView) findViewById(R.id.salary_proof_edit_txt_pl);
        pl_co_app_slrd_salary_proof_edit_txt = (AppCompatTextView) findViewById(R.id.pl_co_app_slrd_salary_proof_edit_txt);
        pl_co_app_slrd_assets_owned_BL = (AppCompatTextView) findViewById(R.id.pl_co_app_slrd_assets_owned_BL);

        Assets_myList_values = (ArrayList<String>) getIntent().getSerializableExtra("select_lid_id");

      /*  lead_viy_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_PL.this, Eligibility_Check_PL.class);
                startActivity(intent);
                finish();
            }
        });*/

        Log.e("viability check Pl ","Personal Loan");
      /* monthly_sal_txt,salery_credite_method_txt,Exp_in_current_txt,total_workexperiecnce_txt,cmp_pincode_txt,
        txt_residence_pincode,txt_residence_type,Lives_in_current_txt,any_other_family_member_txt,family_member_name_txt
        assets_owned_txt*/

        myList_values = (ArrayList<String>) getIntent().getSerializableExtra("select_lid_id");

        salary_proof_edit_txt_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_PL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Salary_proof_ar.toString());
                intent.putExtra("select_lid_id", (Serializable) myList_values);
                startActivity(intent);

            }
        });


        pl_co_app_slrd_salary_proof_edit_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_PL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Salary_proof_ar.toString());
                intent.putExtra("select_lid_id", (Serializable) myList_values);
                startActivity(intent);

            }
        });

        assets_owned_BL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_PL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Assets_own.toString());
                intent.putExtra("select_lid_id", (Serializable) Assets_myList_values);
                startActivity(intent);

            }
        });

        pl_co_app_slrd_assets_owned_BL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_PL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Assets_own.toString());
                intent.putExtra("select_lid_id", (Serializable) Assets_myList_values);
                startActivity(intent);

            }
        });


        UISCREEN();
        Click();
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
        spinn_area = (Spinner) findViewById(R.id.spinn_area);
        res_spinn_area = (Spinner) findViewById(R.id.res_spinn_area);

        residence_type = (LinearLayout) findViewById(R.id.residence_type);
        residence_live = (LinearLayout) findViewById(R.id.residence_live);
        pan_card_available = (LinearLayout) findViewById(R.id.pan_card_available);
        other_earning_avbl = (LinearLayout) findViewById(R.id.other_earning_avbl);
        other_family_mem = (LinearLayout) findViewById(R.id.other_family_mem);
        asstes_own = (LinearLayout) findViewById(R.id.asstes_own);
        residence_type.setVisibility(View.VISIBLE);


        co_applicant_salaried_employed = (LinearLayout) findViewById(R.id.co_applicant_salaried_employed);
        co_applicant_self_employed = (LinearLayout) findViewById(R.id.co_applicant_self_employed);

        co_applicant_ly = (LinearLayout) findViewById(R.id.co_applicant_ly);
        co_applicant_emp_type = (LinearLayout) findViewById(R.id.co_applicant_emp_type);

        age = (AppCompatTextView) findViewById(R.id.age);
        age1 = (AppCompatTextView) findViewById(R.id.age1);

        area_txt = (AppCompatTextView) findViewById(R.id.area_txt);
        area_txt1 = (AppCompatTextView) findViewById(R.id.area_txt1);
        res_area_txt = (AppCompatTextView) findViewById(R.id.res_area_txt);
        res_area_txt1 = (AppCompatTextView) findViewById(R.id.res_area_txt1);
        do_you_have_coApp_txt = (AppCompatTextView) findViewById(R.id.do_you_have_coApp_txt);
        do_you_have_coApp_txt1 = (AppCompatTextView) findViewById(R.id.do_you_have_coApp_txt1);
        coApp_txt_emp_type1 = (AppCompatTextView) findViewById(R.id.coApp_txt_emp_type1);
        coApp_txt_emp_type2 = (AppCompatTextView) findViewById(R.id.coApp_txt_emp_type2);

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
        assets_owned_txt = (AppCompatTextView) findViewById(R.id.assets_owned_txt);
        family_member_income_txt = (AppCompatTextView) findViewById(R.id.family_member_income_txt);
        family_member_income_txt1 = (AppCompatTextView) findViewById(R.id.family_member_income_txt1);
        monthly_afr_emi_txt = (AppCompatTextView) findViewById(R.id.monthly_afr_emi_txt);
        monthly_afr_emi_txt1 = (AppCompatTextView) findViewById(R.id.monthly_afr_emi_txt1);


        co_applicant_spinner = (Spinner) findViewById(R.id.co_applicant_spinner);
        co_applicant_emp_spinner = (Spinner) findViewById(R.id.co_applicant_emp_spinner);

        age_edite_txt = (AppCompatEditText) findViewById(R.id.age_edite_txt);
        pan_number_edit_txt = (AppCompatEditText) findViewById(R.id.pan_number_edit_txt);
        occupation_edit_txt = (AppCompatEditText) findViewById(R.id.occupation_edit_txt);
        monthly_net_sal_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_net_sal_edit_txt);
        monthly_net_sal_edit_txt.addTextChangedListener(new NumberTextWatcher(monthly_net_sal_edit_txt));
        experience_in_current_cmpy = (AppCompatEditText) findViewById(R.id.experience_in_current_cmpy);
        total_experience_edit_txt = (AppCompatEditText) findViewById(R.id.total_experience_edit_txt);
        company_pincode_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.company_pincode_txt);

        residence_pincode1_edit_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.residence_pincode1_edit_txt);
        pl_co_app_slrd_company_pincode_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.pl_co_app_slrd_company_pincode_txt);

        current_residence_edit_txt = (AppCompatEditText) findViewById(R.id.current_residence_edit_txt);

        family_member_name_edit_txt = (AppCompatEditText) findViewById(R.id.family_member_name_edit_txt);
        family_member_income_edit_txt = (AppCompatEditText) findViewById(R.id.family_member_income_edit_txt);
        monthly_afr_emi_amt_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_afr_emi_amt_edit_txt);
        monthly_afr_emi_amt_edit_txt.addTextChangedListener(new NumberTextWatcher(monthly_net_sal_edit_txt));
        cv_salary_income_proof = (ChipsView) findViewById(R.id.cv_salary_income_proof);

        //pl_co_app_slrd_age_edite_txt,pl_co_app_slrd_month_net_slrd_edite_txt,pl_co_app_slrd_experience_in_current_cmpy,
        //            pl_co_app_slrd_total_experience_edit_txt

        pl_co_app_slrd_name_edite_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_slrd_name_edite_txt);
        pl_co_app_slrd_age_edite_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_slrd_age_edite_txt);
        pl_co_app_slrd_month_net_slrd_edite_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_slrd_month_net_slrd_edite_txt);
        pl_co_app_slrd_experience_in_current_cmpy = (AppCompatEditText) findViewById(R.id.pl_co_app_slrd_experience_in_current_cmpy);
        pl_co_app_slrd_total_experience_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_slrd_total_experience_edit_txt);
        pl_co_app_slrd_company_pincode_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.pl_co_app_slrd_company_pincode_txt);

        pl_co_app_slrd_spinn_salary_crt_mtd = (Spinner) findViewById(R.id.pl_co_app_slrd_spinn_salary_crt_mtd);
        pl_co_app_slrd_res_spinn_area = (Spinner) findViewById(R.id.pl_co_app_slrd_res_spinn_area);

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
        assets_owned_txt.setTypeface(font);
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
        res_area_txt.setTypeface(font);
        res_area_txt1.setTypeface(font);
        do_you_have_coApp_txt.setTypeface(font);
        do_you_have_coApp_txt1.setTypeface(font);
        coApp_txt_emp_type1.setTypeface(font);
        coApp_txt_emp_type2.setTypeface(font);

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


        pl_co_app_slrd_company_pincode_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("hi","hi11");
                String workpincode = pl_co_app_slrd_company_pincode_txt.getText().toString();

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

              /*  if(PAN_id.equals("0"))
                {
                    Toast.makeText(context,"Please Select Having PAN Card",Toast.LENGTH_SHORT).show();

                }else
                {*/
                   /* if (!Validate_Occupation()) {
                        return;
                    }*/
                    if (!Validate_net_income()) {
                        return;
                    }
                   /* if(Employee_id.equals("0"))
                    {
                        Toast.makeText(context,"Please Select Employee id",Toast.LENGTH_SHORT).show();
                    }else
                    {*/
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


                                    co_applicant_validation();
                                   /* Intent intent = new Intent(Viability_Check_PL.this, Eligibility_Check_PL.class);
                                    startActivity(intent);
                                    finish();
                                   */
                                }else if(residence_id.equals("2"))
                                {

                                    if (!curennt_resi_v()) {
                                        return;
                                    }

                                    co_applicant_validation();
                                 /*   Intent intent = new Intent(Viability_Check_PL.this, Eligibility_Check_PL.class);
                                    startActivity(intent);
                                    finish();*/


                                }

                            }
                        }

                  /*  }*/
                    ////
               /* }*/

            }
        });

    }


    private void co_applicant_validation()
    {
        if(IS_CO_Applicant_Id.equals("0"))
        {
            Toast.makeText(context,"Select Do you haveing Co Applicant",Toast.LENGTH_SHORT).show();

        }else if(IS_CO_Applicant_Id.equals("1"))
        {
            if(CO_Type_of_employement_ID.equals("0"))
            {
                Toast.makeText(context,"Select Co Applicant Employement type",Toast.LENGTH_SHORT).show();

            }else  if(CO_Type_of_employement_ID.equals("1"))
            {

                if (!Validate_pl_co_app_slrd_name_edite_txt()) {
                    return;
                }

                if (!Validate_pl_co_app_slrd_age_edite_txt()) {
                    return;
                }

                if (!Validate_pl_co_app_slrd_month_net_slrd_edite_txt()) {
                    return;
                }
                if(pl_co_app_slrd_Salary_id.equals("0"))
                {
                    Toast.makeText(context,"Select salary credit method",Toast.LENGTH_SHORT).show();

                }else
                {
                    if (!Validate_pl_co_app_slrd_experience_in_current_cmpy()) {
                        return;
                    }
                    if (!Validate_pl_co_app_slrd_total_experience_edit_txt()) {
                        return;
                    }

                    if (!Validate_pl_co_app_slrd_company_pincode_txt()) {
                        return;
                    }

                }

            }else
            {

            }

        } else if(IS_CO_Applicant_Id.equals("2")) {

            Intent intent = new Intent(Viability_Check_PL.this, Eligibility_Check_PL.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean Validate_age() {

        if (age_edite_txt.length() < 1 || age_edite_txt.length() > 3) {
            age_edite_txt.setError(getText(R.string.err_curent));
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
            monthly_net_sal_edit_txt.setError(getText(R.string.err_curent));
            monthly_net_sal_edit_txt.requestFocus();
            return false;
        } else {
            //     inputLayoutNumber.setErrorEnabled(false);
        }
        return true;
    }

    private boolean Validate_experience(){
        if (experience_in_current_cmpy.getText().toString().trim().isEmpty()) {
            experience_in_current_cmpy.setError(getText(R.string.err_curent));
            experience_in_current_cmpy.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_pl_co_app_slrd_name_edite_txt(){
        if (pl_co_app_slrd_name_edite_txt.getText().toString().trim().isEmpty()) {
            pl_co_app_slrd_name_edite_txt.setError(getText(R.string.err_curent));
            pl_co_app_slrd_name_edite_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_pl_co_app_slrd_age_edite_txt(){
        if (pl_co_app_slrd_age_edite_txt.getText().toString().trim().isEmpty()) {
            pl_co_app_slrd_age_edite_txt.setError(getText(R.string.err_curent));
            pl_co_app_slrd_age_edite_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_pl_co_app_slrd_month_net_slrd_edite_txt(){
        if (pl_co_app_slrd_month_net_slrd_edite_txt.getText().toString().trim().isEmpty()) {
            pl_co_app_slrd_month_net_slrd_edite_txt.setError(getText(R.string.err_curent));
            pl_co_app_slrd_month_net_slrd_edite_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_pl_co_app_slrd_experience_in_current_cmpy(){
        if (pl_co_app_slrd_experience_in_current_cmpy.getText().toString().trim().isEmpty()) {
            pl_co_app_slrd_experience_in_current_cmpy.setError(getText(R.string.err_curent));
            pl_co_app_slrd_experience_in_current_cmpy.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_pl_co_app_slrd_total_experience_edit_txt(){
        if (pl_co_app_slrd_total_experience_edit_txt.getText().toString().trim().isEmpty()) {
            pl_co_app_slrd_total_experience_edit_txt.setError(getText(R.string.err_curent));
            pl_co_app_slrd_total_experience_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_pl_co_app_slrd_company_pincode_txt(){
        if (pl_co_app_slrd_company_pincode_txt.getText().toString().trim().isEmpty()) {
            pl_co_app_slrd_company_pincode_txt.setError(getText(R.string.err_curent));
            pl_co_app_slrd_company_pincode_txt.requestFocus();
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
            total_experience_edit_txt.setError(getText(R.string.err_curent));
            total_experience_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Company_locationpincode1(){
        if (company_pincode_txt.getText().toString().isEmpty()) {
            company_pincode_txt.setError(getText(R.string.err_curent));
            company_pincode_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean family_member(){
        if (family_member_name_edit_txt.getText().toString().isEmpty()) {
            family_member_name_edit_txt.setError(getText(R.string.err_curent));
            family_member_name_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean family_member_income(){
        if (family_member_income_edit_txt.getText().toString().isEmpty()) {
            family_member_income_edit_txt.setError(getText(R.string.err_curent));
            family_member_income_edit_txt.requestFocus();
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
                      //  Log.e("respose Dreopdown", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        try {

                             Residence_ownership_ar =object.getJSONArray("Residence_ownership");
                             Salary_method_ar =object.getJSONArray("Salary_method");
                             Salary_proof_ar =object.getJSONArray("Salary_proof");

                            other_earning_ar =object.getJSONArray("other_earning");
                            employee_id_ar =object.getJSONArray("employee_id");
                            have_pan_ar =object.getJSONArray("have_pan");
                            Assets_own =object.getJSONArray("Assets_own");


                            Salry_method_Spinner(Salary_method_ar);
                            Salry_Proof(Salary_proof_ar);
                            Residence_Array(Residence_ownership_ar);
                            Employee_ID_Array(employee_id_ar);
                            HAVE_PAN_Card(have_pan_ar);
                            Employement =object.getJSONArray("Employement");
                            is_coapplicant =object.getJSONArray("is_coapplicant");
                            Co_Type_Of_Employement_Spinner(Employement);
                            DO_Have_Co_Applicant(is_coapplicant);
                           // Other_Earning(other_earning_ar);


                            Log.e("Property_Type",String.valueOf(other_earning_ar));

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

            //Co Applicant Salary cr method
            pl_co_app_slrd_spinn_salary_crt_mtd.setAdapter(Salary_Adapter);
            pl_co_app_slrd_spinn_salary_crt_mtd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");


                        pl_co_app_slrd_Salary_id = Salary_method_ar.getJSONObject(position).getString("id");
                        pl_co_app_slrd_Salary_Value = Salary_method_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", pl_co_app_slrd_Salary_id);
                        Log.d("Salary_Value", pl_co_app_slrd_Salary_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_slrd_spinn_salary_crt_mtd.setOnTouchListener(new View.OnTouchListener() {
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

                            other_family_mem.setVisibility(View.GONE);
                            asstes_own.setVisibility(View.GONE);

                            other_earning_avbl.setVisibility(View.GONE);

                            age.setText("1");
                            monthly_sal_txt.setText("2");
                            salery_credite_method_txt.setText("3");
                            Exp_in_current_txt.setText("4");
                            total_workexperiecnce_txt.setText("5");
                            cmp_pincode_txt.setText("6");
                            area_txt.setText("7");
                            txt_residence_pincode.setText("8");
                            res_area_txt.setText("9");
                            txt_residence_type.setText("10");


                            Lives_in_current_txt.setText("11");
                            do_you_have_coApp_txt.setText("12");
                            coApp_txt_emp_type1.setText("13");


                        }else
                        {

                            residence_live.setVisibility(View.GONE);

                            other_family_mem.setVisibility(View.GONE);
                            asstes_own.setVisibility(View.GONE);

                            other_earning_avbl.setVisibility(View.GONE);

                            age.setText("1");
                            monthly_sal_txt.setText("2");
                            salery_credite_method_txt.setText("3");
                            Exp_in_current_txt.setText("4");
                            total_workexperiecnce_txt.setText("5");
                            cmp_pincode_txt.setText("6");
                            area_txt.setText("7");
                            txt_residence_pincode.setText("8");
                            res_area_txt.setText("9");
                            txt_residence_type.setText("10");
                            do_you_have_coApp_txt.setText("11");
                            coApp_txt_emp_type1.setText("12");

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

                        monthly_sal_txt.setText("1");
                        salery_credite_method_txt.setText("2");
                        Exp_in_current_txt.setText("3");
                        total_workexperiecnce_txt.setText("4");
                        cmp_pincode_txt.setText("5");
                        txt_residence_pincode.setText("6");
                        txt_residence_type.setText("7");
                        Lives_in_current_txt.setText("8");
                        any_other_family_member_txt.setText("9");
                        family_member_name_txt.setText("10");
                        family_member_income_txt.setText("11");
                        assets_owned_txt.setText("12");

                        if(other_earning_id.equals("1"))
                        {
                            other_family_mem.setVisibility(View.GONE);
                            asstes_own.setVisibility(View.GONE);

                            other_earning_avbl.setVisibility(View.GONE);

                            monthly_sal_txt.setText("1");
                            salery_credite_method_txt.setText("2");
                            Exp_in_current_txt.setText("3");
                            total_workexperiecnce_txt.setText("4");
                            cmp_pincode_txt.setText("5");
                            txt_residence_pincode.setText("6");
                            txt_residence_type.setText("7");
                            Lives_in_current_txt.setText("8");
                            any_other_family_member_txt.setText("9");
                            family_member_name_txt.setText("10");
                            family_member_income_txt.setText("11");
                            assets_owned_txt.setText("12");
                        }else
                        {
                            other_family_mem.setVisibility(View.GONE);
                            other_earning_avbl.setVisibility(View.GONE);
                            asstes_own.setVisibility(View.GONE);
                            monthly_sal_txt.setText("1");
                            salery_credite_method_txt.setText("2");
                            Exp_in_current_txt.setText("3");
                            total_workexperiecnce_txt.setText("4");
                            cmp_pincode_txt.setText("5");
                            txt_residence_pincode.setText("6");
                            txt_residence_type.setText("7");
                            Lives_in_current_txt.setText("8");

                            if(residence_id.equals("0"))
                            {
                                any_other_family_member_txt.setText("8");
                                assets_owned_txt.setText("9");
                            }else
                            {
                                any_other_family_member_txt.setText("9");
                                assets_owned_txt.setText("10");
                            }
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

                        if(IS_CO_Applicant_Id.equals("1"))
                        {

                            if(CO_Type_of_employement_ID.equals("1"))
                            {
                                co_applicant_salaried_employed.setVisibility(View.VISIBLE);
                                co_applicant_self_employed.setVisibility(View.GONE);

                            }else if(CO_Type_of_employement_ID.equals("2"))
                            {
                                co_applicant_salaried_employed.setVisibility(View.GONE);
                                co_applicant_self_employed.setVisibility(View.VISIBLE);
                            }

                        }else
                        {
                            co_applicant_salaried_employed.setVisibility(View.GONE);
                            co_applicant_self_employed.setVisibility(View.GONE);
                        }

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
/*
                            do_you_have_coApp_txt.setText("8");
                            coApp_txt_emp_type1.setText("9");*/


                        }else if(IS_CO_Applicant_Id.equals("2"))
                        {
                           co_applicant_emp_type.setVisibility(View.GONE);
                           // do_you_have_coApp_txt.setText("8");

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

            String workpincode = company_pincode_txt.getText().toString();
            String workpincode1 = residence_pincode1_edit_txt.getText().toString();
            String workpincode3 = pl_co_app_slrd_company_pincode_txt.getText().toString();

            if(workpincode.length()> 2){
                company_pincode_txt.setThreshold(2);
                company_pincode_txt.setAdapter(Pincode_Adapter);
            }

            if(workpincode1.length()> 2){
                residence_pincode1_edit_txt.setThreshold(2);
                residence_pincode1_edit_txt.setAdapter(Pincode_Adapter);
            }

            if(workpincode3.length()> 2){
                pl_co_app_slrd_company_pincode_txt.setThreshold(2);
                pl_co_app_slrd_company_pincode_txt.setAdapter(Pincode_Adapter);
            }

        }

        company_pincode_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);

                if(code.length()==6){
                    GET_AERA_POST(code);
                }else {
                    Objs.a.showToast(context,"Please Select Pin code");
                }

                imm.hideSoftInputFromWindow(company_pincode_txt.getWindowToken(), 0);
            }
        });

        residence_pincode1_edit_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);

                if(code.length()==6){
                    GET_AERA_POST1(code);
                }else {
                    Objs.a.showToast(context,"Please Select Pin code");
                }

                imm.hideSoftInputFromWindow(company_pincode_txt.getWindowToken(), 0);

            }
        });

        pl_co_app_slrd_company_pincode_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);

                if(code.length()==6){
                    GET_AERA_POST2(code);
                }else {
                    Objs.a.showToast(context,"Please Select Pin code");
                }

                imm.hideSoftInputFromWindow(company_pincode_txt.getWindowToken(), 0);

            }
        });


    }

    private void GET_AERA_POST(String code) {
        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("pincode", code);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_AERA_POST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {

                            if (object.getString(Params.status).equals("success")) {
                                JSONArray response = object.getJSONArray("response");
                                //    Log.e("Pincode", String.valueOf(response));
                                setArea(response);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    private void setArea(final JSONArray ja) throws JSONException {

        Area = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            Area[i] = J.getString("area");
            final List<String> area_list = new ArrayList<>(Arrays.asList(Area));
            A_Area = new ArrayAdapter<String>(getApplicationContext(), R.layout.view_spinner_item, area_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(getApplicationContext().getAssets(),"Lato-Regular.ttf");
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

            String workpincode = company_pincode_txt.getText().toString();
            String workpincode1 = residence_pincode1_edit_txt.getText().toString();

            if(workpincode.length()> 2){
                A_Area.setDropDownViewResource(R.layout.view_spinner_item);
                spinn_area.setAdapter(A_Area);
                spinn_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        try {
                            //   work_pincode_area = ja.getJSONObject(position).getString("id");
                            company_area = ja.getJSONObject(position).getString("id");
                            company_area_district_id = ja.getJSONObject(position).getString("district_id");
                            company_area_state_id = ja.getJSONObject(position).getString("state_id");
                            // Objs.a.showToast(getContext(),work_pincode_area);
                            ///  String a = work_pincode_area +"   "+work_pincode_district_id +"   "+work_pincode_state_id;

                            //   Log.e("Drop Down",a);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinn_area.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                        return false;
                    }
                });
            }
            if(workpincode1.length()> 2){

            } A_Area.setDropDownViewResource(R.layout.view_spinner_item);
            res_spinn_area.setAdapter(A_Area);
            res_spinn_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        res_company_area = ja.getJSONObject(position).getString("id");
                        res_company_area_district_id = ja.getJSONObject(position).getString("district_id");
                        res_company_area_state_id = ja.getJSONObject(position).getString("state_id");
                        // Objs.a.showToast(getContext(),work_pincode_area);
                        ///  String a = work_pincode_area +"   "+work_pincode_district_id +"   "+work_pincode_state_id;

                        //   Log.e("Drop Down",a);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            res_spinn_area.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

    }

    private void GET_AERA_POST1(String code) {
        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("pincode", code);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_AERA_POST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {

                            if (object.getString(Params.status).equals("success")) {
                                JSONArray response = object.getJSONArray("response");
                                //    Log.e("Pincode", String.valueOf(response));
                                setArea1(response);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    private void setArea1(final JSONArray ja) throws JSONException {

        Area = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            Area[i] = J.getString("area");
            final List<String> area_list = new ArrayList<>(Arrays.asList(Area));
            A_Area = new ArrayAdapter<String>(getApplicationContext(), R.layout.view_spinner_item, area_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(getApplicationContext().getAssets(),"Lato-Regular.ttf");
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


            String workpincode1 = residence_pincode1_edit_txt.getText().toString();

            A_Area.setDropDownViewResource(R.layout.view_spinner_item);
            res_spinn_area.setAdapter(A_Area);
            res_spinn_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        res_company_area = ja.getJSONObject(position).getString("id");
                        res_company_area_district_id = ja.getJSONObject(position).getString("district_id");
                        res_company_area_state_id = ja.getJSONObject(position).getString("state_id");
                        // Objs.a.showToast(getContext(),work_pincode_area);
                        ///  String a = work_pincode_area +"   "+work_pincode_district_id +"   "+work_pincode_state_id;

                        //   Log.e("Drop Down",a);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            res_spinn_area.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

    }

    private void GET_AERA_POST2(String code) {
        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("pincode", code);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_AERA_POST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {

                            if (object.getString(Params.status).equals("success")) {
                                JSONArray response = object.getJSONArray("response");
                                //    Log.e("Pincode", String.valueOf(response));
                                setArea2(response);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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

    private void setArea2(final JSONArray ja) throws JSONException {

        Area = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            Area[i] = J.getString("area");
            final List<String> area_list = new ArrayList<>(Arrays.asList(Area));
            A_Area = new ArrayAdapter<String>(getApplicationContext(), R.layout.view_spinner_item, area_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(getApplicationContext().getAssets(),"Lato-Regular.ttf");
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


            String workpincode2 = pl_co_app_slrd_company_pincode_txt.getText().toString();

            A_Area.setDropDownViewResource(R.layout.view_spinner_item);
            pl_co_app_slrd_res_spinn_area.setAdapter(A_Area);
            pl_co_app_slrd_res_spinn_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        pl_co_app_slrd_res_spinn_area_id = ja.getJSONObject(position).getString("id");
                        pl_co_app_slrd_res_spinn_area_district_id = ja.getJSONObject(position).getString("district_id");
                        pl_co_app_slrd_res_spinn_area_state_id = ja.getJSONObject(position).getString("state_id");

                        //   Log.e("Drop Down",a);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_slrd_res_spinn_area.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

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

        Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
        finish();
        super.onBackPressed();

    }
}
