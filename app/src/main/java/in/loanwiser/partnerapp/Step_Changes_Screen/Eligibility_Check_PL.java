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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.NumberTextWatcher;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.Payment.PaymentActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Eligibility_Check_PL extends SimpleActivity {


    JSONObject Applicant,Co_Applicant1;
    AppCompatButton lead_viy_step2;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    Typeface font;
    private Context context = this;
    InputMethodManager imm;
    private AlertDialog progressDialog;
    JSONArray Company_type,Type_employee,Current_Residence,epf_deduct,
            Residence_ownership,permanent_adddress,relation_own,Other_income,gst_reflect,employee_id_ar,have_pan_ar,
            Marital_Status,Reflected_ITR,Education_Qualification;

    String[] Company_type_SA,Employee_type_SA,Current_res_SA,Epf_detected_SA,Permanent_SA,
            Own_house_relativ_SA,Pincode_SA,Other_income_SA,gst_reflect_SA,Employe_ID_SA,PAN_ID_SA,
            Marital_Statues_SA,Area,Permanent_Resi_SA;

    ArrayAdapter<String> Company_type_Adapter,Type_employee_Adapter,Current_residence_Adapter,
            Epf_detected_Adapter,permanent_Adapter,Own_house_Relative_Adapter,Pincode_Adapter,
            Other_income_Adapter,gst_reflect_Adapter,Employee_ID_Adapter,PAN_ID_Adapter,Marital_Statues_Adapter,
            A_Area_Adapter,Permanent_Resi_Adapter;

    String Company_id,Company_Value,Emp_type_id,Emp_type_Value,current_ress_id,current_ress_Value,
            epf_id,epf_Value,permanent_res_id,permanent_res_Value,Own_house_rela_id,Own_house_rela_Value,
            maried_res_spinner_id,maried_res_spinner_Value, permanent_residence__area_id,cpermanent_residence_spinn_district_id,
            permanent_residence_spinn_state_id, Permanent_Resi_id,Permanent_Resi_Value;

    AppCompatTextView cmp_typ_txt,cmp_typ_txt1,Company_name,Company_name1,desg_nation_txt,
            desg_nation_txt1,type_emp_txt,type_emp_txt1,no_of_Employee_txt,no_of_Employee_txt1,
            epf_txt,epf_txt1,current_res_proof,current_res_proof1,permanent_res_pincode_txt,
            permanent_res_pincode_txt1,permenant_res_txt,permenant_res_txt1,
            own_hose_relative_txt,own_hose_relative_txt1,rel_full_address_txt,
            rel_full_address_txt1,other_income_details_txt,other_income_txt,other_income_txt1,
            is_gst_reflect_txt,is_gst_reflect_txt1,other_incom_amt_txt,other_incom_amt_txt1,no_of_dependent,no_of_dependent1;


    AppCompatEditText company_name_edtxt,designation_in_company,no_of_emp_edtxt,
                     rel_full_address_edtxt,other_incom_amt_edtxt,no_of_dependent_edt_txt,
            emi_amount_edit_txt,education_qualification_edit_txt,pl_co_app_company_name_edtxt;

    AutoCompleteTextView permanent_residence_auto;

    Spinner spinner_cmp_type,employee_type_spnr,epf_spinner,current_res_spinner,perment_restype_spnr,
            own_hose_relative_spinner,spinn_other_income,spinn_is_gst_reflect,has_pan_card_spnr,spinner_employe_id,
            maried_res_spinner,permanent_res_type_res_spinner;

    String S_company_name_edtxt,S_no_of_emp_edtxt,S_permanent_res_pincode_edtxt,S_experience_in_current_cmpy,
            S_Designation_in_current_companny,Other_income_id,Other_income_Value,gst_reflect_id,gst_reflect_Value,S_rel_full_address_txt1,
            S_other_incom_amt_edtxt,Employee_id,PAN_id,PAN_Value,Employee_Value,
            S_no_of_dependent_edt_txt,S_education_qualification_edit_txt,S_emi_amount_edit_txt,S_permanent_residence_spinner,
            S_own_house_blood_address_edt_txt,S_rent_paid_for_house_edit_txt;

    LinearLayout relative_if_rented_ly,is_other_income,rented,co_app_is_other_income;

    //Co Applicant

        Spinner pl_co_self_has_pan_card_spnr,pl_co_app_spinner_employe_id,pl_co_app_spinner_cmp_type,pl_co_app_employee_type_spnr,
                pl_co_app_epf_spinner,pl_co_app_current_res_spinner,pl_co_app_maried_res_spinner,pl_co_app_spinn_other_income,
                pl_co_App_spinn_is_gst_reflect,permanent_residence_spinn_area,Educational_Spinner_pl,co_Educational_Spinner_pl;

        AppCompatEditText pl_co_app_designation_in_company,pl_co_App_education_qualification_edit_txt,pl_co_app_emi_amount_edit_txt,
                pl_co_app_no_of_dependent_edt_txt,pl_co_App_other_incom_amt_edtxt,own_house_blood_address_edt_txt,
                rent_paid_for_house_edit_txt,pl_co_App_no_of_emp_edtxt,co_business_ref_name_edt_txt,co_purchased_by_bank_edit_txt,
                co_purchased_by_GStbill_edit_txt,co_sales_by_GStbill_edit_txt,
                co_bank_cridit_by_edtxt,co_Avg_monthly_income,co_other_income_edite_txt;

        String Pl_Co_App_PAN_id,Pl_Co_App_PAN_Value,pl_co_app_Employee_id,pl_co_app_Employee_Value,
                pl_co_app_Company_id,pl_co_app_Company_Value, pl_co_app_Emp_type_id,pl_co_app_Emp_type_Value,
                pl_co_app_epf_id,pl_co_app_epf_Value, pl_co_app_maried_res_spinner_id,pl_co_app_maried_res_spinner_Value,
                pl_co_app_Other_income_id,pl_co_app_Other_income_Value,pl_co_App_gst_reflect_id,pl_co_App_gst_reflect_Value,
                Educational_Id,Educational_Value;

        String residence_id,Co_Applicant,S_pl_co_app_company_name_edtxt,S_pl_co_App_no_of_emp_edtxt,S_pl_co_app_designation_in_company,
                S_pl_co_app_no_of_dependent_edt_txt,S_pl_co_app_emi_amount_edit_txt,S_pl_co_App_education_qualification_edit_txt,
                S_co_business_ref_name_edt_txt,S_co_purchased_by_bank_edit_txt,S_co_purchased_by_GStbill_edit_txt,
                S_co_sales_by_GStbill_edit_txt,S_co_bank_cridit_by_edtxt,S_co_Avg_monthly_income,S_co_other_income_edite_txt,
                S_pl_co_App_other_incom_amt_edtxt;

        LinearLayout full_addres_of_relative,rented1,permanent_res_type_ly,permanent_residence_pincode_ly,permanent_res_area,
                rent_paid_for_house_ly,pl_co_app_cmp_name,pl_co_cmp_des_,co_applicant_pl_co_applicant,
                bl_co_eligibility_salaried,bl_co_eligibility_self, co_individual,co_self_business,no_of_Employee,
                co_no_of_Employee;
        String user_id,transaction_id,IS_CO_Salried_Self,CO_Employement_Type;
        int app_count;




      String[] Provide_guarente_SA,Gst_refelect_SA,Current_address_Proof_SA,Business_registration_SA;
      ArrayAdapter<String> Provide_gurenter_adapter,Gst_reflect_adapter,CureentAddress_proof_adapter,Other_income_adapter,
              Business_registration_adapter;
      Spinner co_having_bank_account,co_Educatio_qualification_Sppinner,co_spinner_res_proof,
              co_spinn_other_income,co_spinn_gst_other,co_business_registration_spinner;
      String co_having_bank_Id,co_Educational_Id,co_Spinner_res_proof_Id,co_Other_income_Id,co_Gstreflect_Id,
              co_having_bank_Value,co_Educational_Value,co_Spinner_res_proof_Value,co_Other_income_Value,co_Gstreflect_Value,
              co_Business_registration_Id,co_Business_registration_Value,co_salaried_Educational_Id,co_salaried_Educational_Value;
     LinearLayout co_other_income_ifany;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_eligibility__check);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_eligibility__check);
        initTools(R.string.eligi_check);

        progressDialog = new SpotsDialog(context, R.style.Custom);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

      //  Company_type,Type_employee,Current_Residence,


        residence_id = Pref.get_Residence_ID(context);
        Co_Applicant = Pref.getCoAPPAVAILABLE(context);

        IS_CO_Salried_Self = Pref.getCOAPPSALARYTYPE(context);
        CO_Employement_Type = Pref.getCOEMPTYPE(context);

        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        transaction_id = intent.getStringExtra("transaction_id");

        UISCREEN();
        Click();
        fonts();
        makeJsonObjReq1();

        if(residence_id.equals("1"))
        {
            full_addres_of_relative.setVisibility(View.GONE);
            permanent_res_type_ly.setVisibility(View.GONE);
            permanent_residence_pincode_ly.setVisibility(View.GONE);
            permanent_res_area.setVisibility(View.GONE);
            rented.setVisibility(View.GONE);
            rent_paid_for_house_ly.setVisibility(View.GONE);
        }else
        {
            full_addres_of_relative.setVisibility(View.VISIBLE);
            permanent_res_type_ly.setVisibility(View.VISIBLE);
            permanent_residence_pincode_ly.setVisibility(View.VISIBLE);
            permanent_res_area.setVisibility(View.VISIBLE);
            rented.setVisibility(View.VISIBLE);
            rent_paid_for_house_ly.setVisibility(View.VISIBLE);
        }

        if(Co_Applicant.equals("1"))
        {
            app_count=2;
            co_applicant_pl_co_applicant.setVisibility(View.VISIBLE);
        }else {
            app_count=1;
            co_applicant_pl_co_applicant.setVisibility(View.GONE);

        }

        if(Co_Applicant.equals("1"))
        {
            co_applicant_pl_co_applicant.setVisibility(View.VISIBLE);
            app_count = 2;

            if(IS_CO_Salried_Self.equals("1"))
            {
                bl_co_eligibility_salaried.setVisibility(View.VISIBLE);
                bl_co_eligibility_self.setVisibility(View.GONE);
            }else
            {
                bl_co_eligibility_salaried.setVisibility(View.GONE);
                bl_co_eligibility_self.setVisibility(View.VISIBLE);
            }
        }else
        {
            app_count = 1;
            co_applicant_pl_co_applicant.setVisibility(View.GONE);
        }

     /*   lead_viy_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Eligibility_Check_PL.this, Credite_report_details.class);
                startActivity(intent);
                finish();
            }
        });*/

    }

    private void UISCREEN()
    {
        lead_viy_step2 = (AppCompatButton) findViewById(R.id.lead_viy_step2);

        spinner_cmp_type = (Spinner) findViewById(R.id.spinner_cmp_type);
        employee_type_spnr = (Spinner) findViewById(R.id.employee_type_spnr);
        epf_spinner = (Spinner) findViewById(R.id.epf_spinner);
        current_res_spinner = (Spinner) findViewById(R.id.current_res_spinner);

      //  perment_restype_spnr = (Spinner) findViewById(R.id.perment_restype_spnr);
       own_hose_relative_spinner = (Spinner) findViewById(R.id.own_hose_relative_spinner);
        spinn_other_income = (Spinner) findViewById(R.id.spinn_other_income);
        spinn_is_gst_reflect = (Spinner) findViewById(R.id.spinn_is_gst_reflect);
        has_pan_card_spnr = (Spinner) findViewById(R.id.has_pan_card_spnr);
        spinner_employe_id = (Spinner) findViewById(R.id.spinner_employe_id);
        maried_res_spinner = (Spinner) findViewById(R.id.maried_res_spinner);
        permanent_residence_spinn_area = (Spinner) findViewById(R.id.permanent_residence_spinn_area);
        permanent_res_type_res_spinner = (Spinner) findViewById(R.id.permanent_res_type_res_spinner);

        is_other_income = (LinearLayout) findViewById(R.id.is_other_income);
        co_app_is_other_income = (LinearLayout) findViewById(R.id.co_app_is_other_income);
        rented = (LinearLayout) findViewById(R.id.rented);

        permanent_residence_auto = (AppCompatAutoCompleteTextView) findViewById(R.id.permanent_residence_auto);

        //Co apllicant

        pl_co_self_has_pan_card_spnr = (Spinner) findViewById(R.id.pl_co_self_has_pan_card_spnr);
        pl_co_app_spinner_employe_id = (Spinner) findViewById(R.id.pl_co_app_spinner_employe_id);
        pl_co_app_spinner_cmp_type = (Spinner) findViewById(R.id.pl_co_app_spinner_cmp_type);
        company_name_edtxt = (AppCompatEditText) findViewById(R.id.company_name_edtxt);
        pl_co_app_designation_in_company = (AppCompatEditText) findViewById(R.id.pl_co_app_designation_in_company);
        pl_co_app_employee_type_spnr = (Spinner) findViewById(R.id.pl_co_app_employee_type_spnr);
        no_of_emp_edtxt = (AppCompatEditText) findViewById(R.id.no_of_emp_edtxt);
        pl_co_app_epf_spinner = (Spinner) findViewById(R.id.pl_co_app_epf_spinner);
        pl_co_app_maried_res_spinner = (Spinner) findViewById(R.id.pl_co_app_maried_res_spinner);
        pl_co_app_spinn_other_income = (Spinner) findViewById(R.id.pl_co_app_spinn_other_income);
        pl_co_App_spinn_is_gst_reflect = (Spinner) findViewById(R.id.pl_co_App_spinn_is_gst_reflect);
        Educational_Spinner_pl = (Spinner) findViewById(R.id.Educational_Spinner_pl);
        co_Educational_Spinner_pl = (Spinner) findViewById(R.id.co_Educational_Spinner_pl);
        pl_co_app_no_of_dependent_edt_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_no_of_dependent_edt_txt);
        pl_co_App_education_qualification_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_App_education_qualification_edit_txt);
        pl_co_app_emi_amount_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_emi_amount_edit_txt);


        no_of_dependent = (AppCompatTextView) findViewById(R.id.no_of_dependent);
        no_of_dependent1 = (AppCompatTextView) findViewById(R.id.no_of_dependent1);
            cmp_typ_txt = (AppCompatTextView) findViewById(R.id.cmp_typ_txt);
        cmp_typ_txt1 = (AppCompatTextView) findViewById(R.id.cmp_typ_txt1);
        Company_name = (AppCompatTextView) findViewById(R.id.Company_name);
        Company_name1 = (AppCompatTextView) findViewById(R.id.Company_name1);
        desg_nation_txt = (AppCompatTextView) findViewById(R.id.desg_nation_txt);
        desg_nation_txt1 = (AppCompatTextView) findViewById(R.id.desg_nation_txt1);
        type_emp_txt = (AppCompatTextView) findViewById(R.id.type_emp_txt);
        type_emp_txt1 = (AppCompatTextView) findViewById(R.id.type_emp_txt1);
        no_of_Employee_txt = (AppCompatTextView) findViewById(R.id.no_of_Employee_txt);
        no_of_Employee_txt1 = (AppCompatTextView) findViewById(R.id.no_of_Employee_txt1);
        no_of_Employee_txt1 = (AppCompatTextView) findViewById(R.id.no_of_Employee_txt1);
        epf_txt = (AppCompatTextView) findViewById(R.id.epf_txt);
        epf_txt1 = (AppCompatTextView) findViewById(R.id.epf_txt1);
        current_res_proof = (AppCompatTextView) findViewById(R.id.current_res_proof);
        current_res_proof1 = (AppCompatTextView) findViewById(R.id.current_res_proof1);
      //  permanent_res_pincode_txt = (AppCompatTextView) findViewById(R.id.permanent_res_pincode_txt);
       // permanent_res_pincode_txt1 = (AppCompatTextView) findViewById(R.id.permanent_res_pincode_txt1);

       /* permenant_res_txt = (AppCompatTextView) findViewById(R.id.permenant_res_txt);
        permenant_res_txt1 = (AppCompatTextView) findViewById(R.id.permenant_res_txt1);
        own_hose_relative_txt = (AppCompatTextView) findViewById(R.id.own_hose_relative_txt);
        own_hose_relative_txt1 = (AppCompatTextView) findViewById(R.id.own_hose_relative_txt1);
        rel_full_address_txt = (AppCompatTextView) findViewById(R.id.rel_full_address_txt);
        rel_full_address_txt1 = (AppCompatTextView) findViewById(R.id.rel_full_address_txt1);*/
        other_income_details_txt = (AppCompatTextView) findViewById(R.id.other_income_details_txt);
        other_income_txt = (AppCompatTextView) findViewById(R.id.other_income_txt);
        other_income_txt1 = (AppCompatTextView) findViewById(R.id.other_income_txt1);
        other_incom_amt_txt = (AppCompatTextView) findViewById(R.id.other_incom_amt_txt);
        other_incom_amt_txt1 = (AppCompatTextView) findViewById(R.id.other_incom_amt_txt1);
        is_gst_reflect_txt = (AppCompatTextView) findViewById(R.id.is_gst_reflect_txt);
        is_gst_reflect_txt1 = (AppCompatTextView) findViewById(R.id.is_gst_reflect_txt1);

        no_of_dependent_edt_txt = (AppCompatEditText) findViewById(R.id.no_of_dependent_edt_txt);
        own_house_blood_address_edt_txt = (AppCompatEditText) findViewById(R.id.own_house_blood_address_edt_txt);
        education_qualification_edit_txt = (AppCompatEditText) findViewById(R.id.education_qualification_edit_txt);
        rent_paid_for_house_edit_txt = (AppCompatEditText) findViewById(R.id.rent_paid_for_house_edit_txt);

            company_name_edtxt = (AppCompatEditText) findViewById(R.id.company_name_edtxt);
        pl_co_app_company_name_edtxt = (AppCompatEditText) findViewById(R.id.pl_co_app_company_name_edtxt);
        pl_co_App_other_incom_amt_edtxt = (AppCompatEditText) findViewById(R.id.pl_co_App_other_incom_amt_edtxt);
        pl_co_App_other_incom_amt_edtxt.addTextChangedListener(new NumberTextWatcher(pl_co_App_other_incom_amt_edtxt));

        designation_in_company = (AppCompatEditText) findViewById(R.id.designation_in_company);
        no_of_emp_edtxt = (AppCompatEditText) findViewById(R.id.no_of_emp_edtxt);
        pl_co_App_no_of_emp_edtxt = (AppCompatEditText) findViewById(R.id.pl_co_App_no_of_emp_edtxt);
        permanent_residence_auto = (AutoCompleteTextView) findViewById(R.id.permanent_residence_auto);
        emi_amount_edit_txt = (AppCompatEditText) findViewById(R.id.emi_amount_edit_txt);
        emi_amount_edit_txt.addTextChangedListener(new NumberTextWatcher(emi_amount_edit_txt));
        other_incom_amt_edtxt = (AppCompatEditText) findViewById(R.id.other_incom_amt_edtxt);
        other_incom_amt_edtxt.addTextChangedListener(new NumberTextWatcher(other_incom_amt_edtxt));

        full_addres_of_relative = (LinearLayout) findViewById(R.id.full_addres_of_relative);
        permanent_res_type_ly = (LinearLayout) findViewById(R.id.permanent_res_type_ly);
        rent_paid_for_house_ly = (LinearLayout) findViewById(R.id.rent_paid_for_house_ly);
        pl_co_app_cmp_name = (LinearLayout) findViewById(R.id.pl_co_app_cmp_name);
        no_of_Employee = (LinearLayout) findViewById(R.id.no_of_Employee);
        co_no_of_Employee = (LinearLayout) findViewById(R.id.co_no_of_Employee);
        pl_co_cmp_des_ = (LinearLayout) findViewById(R.id.pl_co_cmp_des_);
        co_applicant_pl_co_applicant = (LinearLayout) findViewById(R.id.co_applicant_pl_co_applicant);
        permanent_residence_pincode_ly = (LinearLayout) findViewById(R.id.permanent_residence_pincode_ly);
        permanent_res_area = (LinearLayout) findViewById(R.id.permanent_res_area);

        //self

        bl_co_eligibility_salaried = (LinearLayout) findViewById(R.id.bl_co_eligibility_salaried);
        bl_co_eligibility_self = (LinearLayout) findViewById(R.id.bl_co_eligibility_self);




        co_having_bank_account = (Spinner) findViewById(R.id.co_having_bank_account);
        co_Educatio_qualification_Sppinner = (Spinner) findViewById(R.id.co_Educatio_qualification_Sppinner);
        co_spinner_res_proof = (Spinner) findViewById(R.id.co_spinner_res_proof);
        co_spinn_other_income = (Spinner) findViewById(R.id.co_spinn_other_income);
        co_spinn_gst_other = (Spinner) findViewById(R.id.co_spinn_gst_other);
        co_business_registration_spinner = (Spinner) findViewById(R.id.co_business_registration_spinner);

        co_individual = (LinearLayout) findViewById(R.id.co_individual);
        co_self_business = (LinearLayout) findViewById(R.id.co_self_business);
        co_other_income_ifany = (LinearLayout) findViewById(R.id.co_other_income_ifany);

        co_business_ref_name_edt_txt = (AppCompatEditText) findViewById(R.id.co_business_ref_name_edt_txt);
        co_purchased_by_bank_edit_txt = (AppCompatEditText) findViewById(R.id.co_purchased_by_bank_edit_txt);
        co_purchased_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.co_purchased_by_GStbill_edit_txt);
        co_sales_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.co_sales_by_GStbill_edit_txt);
        co_bank_cridit_by_edtxt = (AppCompatEditText) findViewById(R.id.co_bank_cridit_by_edtxt);
        co_Avg_monthly_income = (AppCompatEditText) findViewById(R.id.co_Avg_monthly_income);
        co_Avg_monthly_income.addTextChangedListener(new NumberTextWatcher(co_Avg_monthly_income));
        co_other_income_edite_txt = (AppCompatEditText) findViewById(R.id.co_other_income_edite_txt);
        co_other_income_edite_txt.addTextChangedListener(new NumberTextWatcher(co_other_income_edite_txt));

    }


    private void fonts()
    {

        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
        cmp_typ_txt.setTypeface(font);
        cmp_typ_txt1.setTypeface(font);
        Company_name.setTypeface(font);
        Company_name1.setTypeface(font);
        desg_nation_txt.setTypeface(font);
        desg_nation_txt1.setTypeface(font);
        type_emp_txt.setTypeface(font);
        type_emp_txt1.setTypeface(font);
        no_of_Employee_txt.setTypeface(font);
        no_of_Employee_txt1.setTypeface(font);
        epf_txt.setTypeface(font);
        epf_txt1.setTypeface(font);
        current_res_proof.setTypeface(font);
        current_res_proof1.setTypeface(font);

      /*  permanent_res_pincode_txt.setTypeface(font);
        permanent_res_pincode_txt1.setTypeface(font);*/
       // permenant_res_txt.setTypeface(font);
        no_of_dependent_edt_txt.setTypeface(font);
      /*  own_hose_relative_txt.setTypeface(font);

        own_hose_relative_txt1.setTypeface(font);
        rel_full_address_txt.setTypeface(font);
        rel_full_address_txt1.setTypeface(font);*/

        other_income_txt.setTypeface(font);
        other_income_txt1.setTypeface(font);
        other_incom_amt_txt.setTypeface(font);
        other_incom_amt_txt1.setTypeface(font);
        is_gst_reflect_txt.setTypeface(font);
        is_gst_reflect_txt1.setTypeface(font);
        company_name_edtxt.setTypeface(font);
        designation_in_company.setTypeface(font);
        no_of_emp_edtxt.setTypeface(font);
        permanent_residence_auto.setTypeface(font);
        education_qualification_edit_txt.setTypeface(font);
        other_incom_amt_edtxt.setTypeface(font);

    }

    private void Click ()
     {

         permanent_residence_auto.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /* Log.e("hi","hi11");
                 String permanent_pincode = permanent_residence_auto.getText().toString();

                 if(permanent_pincode.length()==2){
                    // GET_Pincode1(permanent_pincode);
                     GET_AERA_POST(permanent_pincode);
                 }*/

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void afterTextChanged(Editable editable) {
                Log.e("hi","hi11");
                 String permanent_pincode = permanent_residence_auto.getText().toString();

                 if(permanent_pincode.length()==6){
                    // GET_Pincode1(permanent_pincode);
                     GET_AERA_POST(permanent_pincode);
                 }
             }
         });

         lead_viy_step2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if(PAN_id.equals("0"))
                 {
                      Toast.makeText(mCon, "Please Select Has PAN ?", Toast.LENGTH_SHORT).show();
                 }else
                 {

                     if(Employee_id.equals("0"))
                     {
                         Toast.makeText(mCon, "Please Select Have Employee id ?", Toast.LENGTH_SHORT).show();

                     }else
                     {
                         if(Company_id.equals("0"))
                         {
                             Toast.makeText(mCon, "Please Select Company Type", Toast.LENGTH_SHORT).show();

                         }else
                         {

                             if(Company_id.equals("1") || Company_id.equals("2"))
                             {

                                 if (!Validate_Company_Name()) {
                                     return;
                                 }

                                 if (!Validate_Designation_in_company()) {
                                     return;
                                 }
                                 validate1();
                             }else {

                                 if (!Validate_Company_Name()) {
                                     return;
                                 }

                                 if (!Validate_Designation_in_company()) {
                                     return;
                                 }
                                 validate1();

                         }//
                         }
                     }

                 }

                 //
             }
             //
         });

     }


     private void validate1()
     {
         if(Emp_type_id.equals("0"))
         {
             Toast.makeText(mCon, "Please Select Type of Employee", Toast.LENGTH_SHORT).show();
         }else {

             if(Company_id.equals("1") || Company_id.equals("2") ||  Company_id.equals("3"))
             {

                 if (epf_id.equals("0")) {
                     Toast.makeText(mCon, "Please Select Epf detected", Toast.LENGTH_SHORT).show();

                 } else {
                     if (current_ress_id.equals("0")) {
                         Toast.makeText(mCon, "Select Current residence Proof", Toast.LENGTH_SHORT).show();
                     } else {


                         if(residence_id.equals("1"))
                         {
                             owned_house_Validation();
                         }else
                         {
                             rented_house_validation();
                         }

                     }
                 }

             }else {

                 if (!Validate_No_of_Employee()) {
                     return;
                 }
                 if (epf_id.equals("0")) {
                     Toast.makeText(mCon, "Please Select Epf detected", Toast.LENGTH_SHORT).show();

                 } else {
                     if (current_ress_id.equals("0")) {
                         Toast.makeText(mCon, "Select Current residence Proof", Toast.LENGTH_SHORT).show();
                     } else {


                         if(residence_id.equals("1"))
                         {
                             owned_house_Validation();
                         }else
                         {
                             rented_house_validation();
                         }

                     }
                 }
             }



         }
     }

     private void owned_house_Validation()
     {
         if(maried_res_spinner_id.equals("0"))
         {
             Toast.makeText(mCon, "Select Marital Status", Toast.LENGTH_SHORT).show();

         }else {
             if (!valiat_no_of_dependent()) {
                 return;
             }

             if(Educational_Id.equals("0"))
             {
                 Toast.makeText(context,"Please Select Educational Qualification",Toast.LENGTH_SHORT).show();

             }else
             {
                 if (!Validate_emi_amount()) {
                     return;
                 }

                 other_varidation();
             }

         }
     }
     private void rented_house_validation()
     {
         if (!Validate_permanent_residence_()) {
             return;
         }

         if(Permanent_Resi_id.equals("0"))
         {
             Toast.makeText(mCon, "Select Permanent Type", Toast.LENGTH_SHORT).show();

         }else if(Permanent_Resi_id.equals("2"))
         {
             validation2();
         }else
         {
             if(Own_house_rela_id.equals("0"))
             {
                 Toast.makeText(mCon, "select own house of blood relative", Toast.LENGTH_SHORT).show();

             }else
             {
                 if (!valiat_own_house_blood_address_edt_txt()) {
                     return;
                 }
                 validation2();
             }//

         }
     }

     private void validation2()
     {
         if(maried_res_spinner_id.equals("0"))
         {
             Toast.makeText(mCon, "Select Marital Status", Toast.LENGTH_SHORT).show();

         }else
         {
             if (!valiat_no_of_dependent()) {
                 return;
             }

             if (!valiat_rent_paid_for_house_edit_txt()) {
                 return;
             }

             if(Educational_Id.equals("0"))
             {
                 Toast.makeText(context,"Please Select Educational Qualification",Toast.LENGTH_SHORT).show();

             }else
             {
                 if (!Validate_emi_amount()) {
                     return;
                 }

                 other_varidation();
             }


         }
     }
     private void other_varidation()
     {
         if(Other_income_id.equals("0"))
         {
             Toast.makeText(mCon, "Please Select other income", Toast.LENGTH_SHORT).show();

         }else if(Other_income_id.equals("4"))
         {


                if(Co_Applicant.equals("1"))
                {
                    Co_Applicant();
                }else
                {
                    lead_Eligibility();
                }


         }else
         {
             if (!Validate_other_income()) {
                 return;
             }
             if(gst_reflect_id.equals("0"))
             {
                 Toast.makeText(mCon, "Please Select ITR Reflected", Toast.LENGTH_SHORT).show();

             }else
             {
                 if(Co_Applicant.equals("1"))
                 {
                     Co_Applicant();
                 }else
                 {
                     lead_Eligibility();
                 }

             }
         }
     }

     private void Co_Applicant()
     {


         if(Co_Applicant.equals("1"))
         {
             if(IS_CO_Salried_Self.equals("1"))
             {
                 co_salaried();
             }else
             {
                 co_self();
             }

         }else {


         }

     }

     private void co_salaried()
    {
        if(Pl_Co_App_PAN_id.equals("0"))
        {
            Toast.makeText(mCon, "Please Select Co applicant Has PAN ?", Toast.LENGTH_SHORT).show();
        }else
        {

            if(pl_co_app_Employee_id.equals("0"))
            {
                Toast.makeText(mCon, "Please Select Co applicant Have Employee id ?", Toast.LENGTH_SHORT).show();

            }else
            {
                if(pl_co_app_Company_id.equals("0"))
                {
                    Toast.makeText(mCon, "Please Select Co applicant Company Type", Toast.LENGTH_SHORT).show();

                }else
                {
                    if (!Co_App_Validate_Company_Name()) {
                        return;
                    }

                    if (!Co_App_Validate_Designation_in_company()) {
                        return;
                    }

                    if(pl_co_app_Emp_type_id.equals("0"))
                    {
                        Toast.makeText(mCon, "Please Select Co applicant Type of Employee", Toast.LENGTH_SHORT).show();
                    }else {

                        if(pl_co_app_Company_id.equals("1") || pl_co_app_Company_id.equals("2") ||  pl_co_app_Company_id.equals("2")) {
                            if (pl_co_app_epf_id.equals("0")) {
                                Toast.makeText(mCon, "Please Select Co applicant Epf detected", Toast.LENGTH_SHORT).show();

                            } else {


                                if(pl_co_app_maried_res_spinner_id.equals("0"))
                                {
                                    Toast.makeText(mCon, "Select Co applicant Marital Status", Toast.LENGTH_SHORT).show();

                                }else
                                {
                                    if (!Pl_Co_valiat_no_of_dependent()) {
                                        return;
                                    }

                                    if(co_salaried_Educational_Id.equals("0"))
                                    {
                                        Toast.makeText(context,"Please Select Educational Qualification",Toast.LENGTH_SHORT).show();

                                    }else
                                    {
                                        if (!pl_co_App_Validate_emi_amount()) {
                                            return;
                                        }

                                        Co_App_other_varidation();
                                    }


                                }


                            }
                        }else {
                            if (!Pl_Co_Validate_No_of_Employee()) {
                                return;
                            }
                            if (pl_co_app_epf_id.equals("0")) {
                                Toast.makeText(mCon, "Please Select Co applicant Epf detected", Toast.LENGTH_SHORT).show();

                            } else {


                                if(pl_co_app_maried_res_spinner_id.equals("0"))
                                {
                                    Toast.makeText(mCon, "Select Co applicant Marital Status", Toast.LENGTH_SHORT).show();

                                }else
                                {
                                    if (!Pl_Co_valiat_no_of_dependent()) {
                                        return;
                                    }

                                    if(co_salaried_Educational_Id.equals("0"))
                                    {
                                        Toast.makeText(context,"Please Select Educational Qualification",Toast.LENGTH_SHORT).show();

                                    }else
                                    {
                                        if (!pl_co_App_Validate_emi_amount()) {
                                            return;
                                        }

                                        Co_App_other_varidation();
                                    }


                                }


                            }
                        }

                      /*  if (!Pl_Co_Validate_No_of_Employee()) {
                            return;
                        }*/


                        //
                    }
                }

            }

        }
    }
    private void Co_App_other_varidation()
    {
        if(pl_co_app_Other_income_id.equals("0"))
        {
            Toast.makeText(mCon, "Please Select other income", Toast.LENGTH_SHORT).show();

        }else if(!pl_co_app_Other_income_id.equals("4"))
        {
            lead_Eligibility();

        }else
        {
            if (!pl_Co_App_Validate_other_income()) {
                return;
            }
            if(pl_co_App_gst_reflect_id.equals("0"))
            {
                Toast.makeText(mCon, "Please Select ITR Reflected", Toast.LENGTH_SHORT).show();

            }else
            {
                lead_Eligibility();
            }
        }
    }
    //end salaried

    private void co_self()
    {
        if(CO_Employement_Type.equals("1"))
        {
            co_individual.setVisibility(View.VISIBLE);
            co_self_business.setVisibility(View.GONE);
            if(co_having_bank_Id.equals("0"))
            {
                Toast.makeText(context,"Please Select Bank A/C Type",Toast.LENGTH_SHORT).show();
            }else
            {
                if(co_Educational_Id.equals("0"))
                {
                    Toast.makeText(context,"Please Select Educational Qualification",Toast.LENGTH_SHORT).show();

                }else
                {
                    if (!Validate_co_business_ref_name_edt_txt()) {
                        return;
                    }

                    co_validation();

                }

            }

        }else if(CO_Employement_Type.equals("2"))
        {

            co_individual.setVisibility(View.GONE);
            co_self_business.setVisibility(View.GONE);
            if(co_Spinner_res_proof_Id.equals("0"))
            {
                Toast.makeText(context,"Please Select Current Residence Addres Proof",Toast.LENGTH_SHORT).show();
            }else
            {
                co_validation();
            }
        }else if(CO_Employement_Type.equals("3"))
        {
            co_individual.setVisibility(View.GONE);
            co_self_business.setVisibility(View.VISIBLE);
            if(co_Business_registration_Id.equals("0"))
            {
                Toast.makeText(context,"Please Select Business Registration",Toast.LENGTH_SHORT).show();
            }else
            {

                if (!Validate_co_purchased_by_bank_edit_txt()) {
                    return;
                }
                if (!Validate_co_purchased_by_GStbill_edit_txt()) {
                    return;
                }
                if (!Validate_co_sales_by_GStbill_edit_txt()) {
                    return;
                }

                if (!Validate_co_bank_cridit_by_edtxt()) {
                    return;
                }

                if (!Validate_co_Avg_monthly_income()) {
                    return;
                }
                co_validation();

            }
        }

    }

    private void co_validation()
    {
        if(co_Spinner_res_proof_Id.equals("0"))
        {
            Toast.makeText(context,"Please Select Co Applicant Current Residence Addres Proof",Toast.LENGTH_SHORT).show();
        }else
        {
            if(co_Other_income_Id.equals("0"))
            {
                Toast.makeText(context,"Please Select Co Applicant Other Income",Toast.LENGTH_SHORT).show();

            }else if(co_Other_income_Id.equals("4"))
            {
                lead_Eligibility();
            }else
            {

                if (!Validate_co_other_income_edite_txt()) {
                    return;
                }

                if(co_Gstreflect_Id.equals("0"))
                {
                    Toast.makeText(context,"Please Select Co Applicant ITR Reflected",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    lead_Eligibility();
                    //
                }
            }
        }

    }

    //end self eligibility

    private boolean Validate_Company_Name(){
        if (company_name_edtxt.getText().toString().trim().isEmpty()) {
            company_name_edtxt.setError(getText(R.string.error_company_name));
            company_name_edtxt.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_No_of_Employee(){
        if (no_of_emp_edtxt.getText().toString().trim().isEmpty()) {
            no_of_emp_edtxt.setError(getText(R.string.error_no_emp));
            no_of_emp_edtxt.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean valiat_no_of_dependent(){
        if (no_of_dependent_edt_txt.getText().toString().trim().isEmpty()) {
            no_of_dependent_edt_txt.setError(getText(R.string.err_curent));
            no_of_dependent_edt_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean valiat_own_house_blood_address_edt_txt(){
        if (own_house_blood_address_edt_txt.getText().toString().trim().isEmpty()) {
            own_house_blood_address_edt_txt.setError(getText(R.string.err_curent));
            own_house_blood_address_edt_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean valiat_education_qualification(){
        if (education_qualification_edit_txt.getText().toString().trim().isEmpty()) {
            education_qualification_edit_txt.setError(getText(R.string.err_curent));
            education_qualification_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean valiat_rent_paid_for_house_edit_txt(){
        if (rent_paid_for_house_edit_txt.getText().toString().trim().isEmpty()) {
            rent_paid_for_house_edit_txt.setError(getText(R.string.err_curent));
            rent_paid_for_house_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_other_income(){
        if (other_incom_amt_edtxt.getText().toString().trim().isEmpty()) {
            other_incom_amt_edtxt.setError(getText(R.string.oth_income));
            other_incom_amt_edtxt.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_Designation_in_company(){
        if (designation_in_company.getText().toString().trim().isEmpty()) {
            designation_in_company.setError(getText(R.string.error_company_name));
            designation_in_company.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_emi_amount(){
        if (emi_amount_edit_txt.getText().toString().trim().isEmpty()) {
            emi_amount_edit_txt.setError(getText(R.string.err_curent));
            emi_amount_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_permanent_residence_(){
        if (permanent_residence_auto.getText().toString().trim().isEmpty()) {
            permanent_residence_auto.setError(getText(R.string.err_curent));
            permanent_residence_auto.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Co_App_Validate_Company_Name(){
        if (pl_co_app_company_name_edtxt.getText().toString().trim().isEmpty()) {
            pl_co_app_company_name_edtxt.setError(getText(R.string.error_company_name));
            pl_co_app_company_name_edtxt.requestFocus();
            return false;
        } else {

        }

        return true;
    }






    private boolean Pl_Co_Validate_No_of_Employee(){
        if (pl_co_App_no_of_emp_edtxt.getText().toString().trim().isEmpty()) {
            pl_co_App_no_of_emp_edtxt.setError(getText(R.string.error_no_emp));
            pl_co_App_no_of_emp_edtxt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Pl_Co_valiat_no_of_dependent(){
        if (pl_co_app_no_of_dependent_edt_txt.getText().toString().trim().isEmpty()) {
            pl_co_app_no_of_dependent_edt_txt.setError(getText(R.string.err_curent));
            pl_co_app_no_of_dependent_edt_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }





    private boolean Validate_pl_Co_education_qualification_edit_txt(){
        if (pl_co_App_education_qualification_edit_txt.getText().toString().trim().isEmpty()) {
            pl_co_App_education_qualification_edit_txt.setError(getText(R.string.err_curent));
            pl_co_App_education_qualification_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }





    private boolean pl_Co_App_Validate_other_income(){
        if (pl_co_App_other_incom_amt_edtxt.getText().toString().trim().isEmpty()) {
            pl_co_App_other_incom_amt_edtxt.setError(getText(R.string.oth_income));
            pl_co_App_other_incom_amt_edtxt.requestFocus();
            return false;
        } else {

        }

        return true;
    }




    private boolean Co_App_Validate_Designation_in_company(){
        if (pl_co_app_designation_in_company.getText().toString().trim().isEmpty()) {
            pl_co_app_designation_in_company.setError(getText(R.string.error_company_name));
            pl_co_app_designation_in_company.requestFocus();
            return false;
        } else {

        }

        return true;
    }






    private boolean pl_co_App_Validate_emi_amount(){
        if (pl_co_app_emi_amount_edit_txt.getText().toString().trim().isEmpty()) {
            pl_co_app_emi_amount_edit_txt.setError(getText(R.string.err_curent));
            pl_co_app_emi_amount_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }




    private boolean Validate_co_business_ref_name_edt_txt(){
        if (co_business_ref_name_edt_txt.getText().toString().isEmpty()) {
            co_business_ref_name_edt_txt.setError(getText(R.string.err_curent));
            co_business_ref_name_edt_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }

    private boolean Validate_co_purchased_by_bank_edit_txt(){
        if (co_purchased_by_bank_edit_txt.getText().toString().isEmpty()) {
            co_purchased_by_bank_edit_txt.setError(getText(R.string.err_curent));
            co_purchased_by_bank_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }

    private boolean Validate_co_purchased_by_GStbill_edit_txt(){
        if (co_purchased_by_GStbill_edit_txt.getText().toString().isEmpty()) {
            co_purchased_by_GStbill_edit_txt.setError(getText(R.string.err_curent));
            co_purchased_by_GStbill_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }

    private boolean Validate_co_sales_by_GStbill_edit_txt(){
        if (co_sales_by_GStbill_edit_txt.getText().toString().isEmpty()) {
            co_sales_by_GStbill_edit_txt.setError(getText(R.string.err_curent));
            co_sales_by_GStbill_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }

    private boolean Validate_co_bank_cridit_by_edtxt(){
        if (co_bank_cridit_by_edtxt.getText().toString().isEmpty()) {
            co_bank_cridit_by_edtxt.setError(getText(R.string.err_curent));
            co_bank_cridit_by_edtxt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }

    private boolean Validate_co_Avg_monthly_income(){
        if (co_Avg_monthly_income.getText().toString().isEmpty()) {
            co_Avg_monthly_income.setError(getText(R.string.err_curent));
            co_Avg_monthly_income.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }
    private boolean Validate_co_other_income_edite_txt(){
        if (co_other_income_edite_txt.getText().toString().isEmpty()) {
            co_other_income_edite_txt.setError(getText(R.string.error_sales_by_bank_crdt));
            co_other_income_edite_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }


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

            permanent_residence_auto.setThreshold(2);
            permanent_residence_auto.setAdapter(Pincode_Adapter);

        }

        permanent_residence_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);
            }
        });

        permanent_residence_auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);

                if(code.length()==6){
                    GET_AERA_POST(code);
                }else {
                    Objs.a.showToast(context,"Please Select Pin code");
                }

                imm.hideSoftInputFromWindow(permanent_residence_auto.getWindowToken(), 0);
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
            A_Area_Adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.view_spinner_item, area_list){
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

            String workpincode = permanent_residence_auto.getText().toString();
            //  String workpincode1 = residence_pincode1_edit_txt.getText().toString();

            if(workpincode.length()> 2){
                A_Area_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
                permanent_residence_spinn_area.setAdapter(A_Area_Adapter);
                permanent_residence_spinn_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        try {


                            //   work_pincode_area = ja.getJSONObject(position).getString("id");
                            permanent_residence__area_id = ja.getJSONObject(position).getString("id");
                            cpermanent_residence_spinn_district_id = ja.getJSONObject(position).getString("district_id");
                            permanent_residence_spinn_state_id = ja.getJSONObject(position).getString("state_id");
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
                permanent_residence_spinn_area.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                        return false;
                    }
                });
            }

        }

    }

    private void makeJsonObjReq1() {
        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_DROPDOWN_LIST, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("respose Dreopdown", object.toString());
                        try {

                            Company_type =object.getJSONArray("Company_type");
                            Type_employee =object.getJSONArray("Type_employee");
                            Current_Residence =object.getJSONArray("Current_Residence");
                            epf_deduct =object.getJSONArray("epf_deduct");
                            permanent_adddress =object.getJSONArray("permanent_adddress");
                            relation_own =object.getJSONArray("relation_own");

                            Other_income =object.getJSONArray("Other_income");
                            gst_reflect =object.getJSONArray("gst_reflect");
                            employee_id_ar =object.getJSONArray("employee_id");
                            have_pan_ar =object.getJSONArray("have_pan");
                            Marital_Status =object.getJSONArray("Marital_Status");
                            Reflected_ITR =object.getJSONArray("Reflected_ITR");

                            Education_Qualification =object.getJSONArray("Education Qualification");

                            Log.e("gst_reflect",String.valueOf(gst_reflect));
                            Employee_ID_Array(employee_id_ar);
                            HAVE_PAN_Card(have_pan_ar);
                            Comapany_type(Company_type);
                            Type_of_Employee(Type_employee);
                            Current_res_Type(Current_Residence);
                            Epf_detedcted(epf_deduct);

                            Other_income_f(Other_income);
                            itr_reflect_f(Reflected_ITR);
                            Marital_Statues(Marital_Status);
                            Permanent_res_Type(permanent_adddress);
                            Own_hous_relative(relation_own);
                            Education_Qualification_(Education_Qualification);
                            Co_Education_Qualification_(Education_Qualification);


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

    private void Comapany_type(final JSONArray Company_type_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Company_type_SA = new String[Company_type_ar.length()];
        for (int i=0;i<Company_type_ar.length();i++){
            JSONObject J =  Company_type_ar.getJSONObject(i);
            Company_type_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Company_type_SA));
            Company_type_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Company_type_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_cmp_type.setAdapter(Company_type_Adapter);
            spinner_cmp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Company_id = Company_type_ar.getJSONObject(position).getString("id");
                        Company_Value = Company_type_ar.getJSONObject(position).getString("value");

                        if(Company_id.equals("1") || Company_id. equals("2") ||Company_id. equals("3"))
                        {
                            no_of_Employee.setVisibility(View.GONE);
                            pl_co_app_cmp_name.setVisibility(View.VISIBLE);
                            pl_co_cmp_des_.setVisibility(View.VISIBLE);
                        }else
                        {
                            no_of_Employee.setVisibility(View.VISIBLE);
                            pl_co_app_cmp_name.setVisibility(View.VISIBLE);
                            pl_co_cmp_des_.setVisibility(View.VISIBLE);
                        }

                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Company_id", Company_id);
                        Log.d("Company_Value", Company_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_cmp_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

            pl_co_app_spinner_cmp_type.setAdapter(Company_type_Adapter);
            pl_co_app_spinner_cmp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        pl_co_app_Company_id = Company_type_ar.getJSONObject(position).getString("id");
                        pl_co_app_Company_Value = Company_type_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

                        if(pl_co_app_Company_id.equals("1") || pl_co_app_Company_id.equals("2") || pl_co_app_Company_id.equals("3"))
                        {
                            co_no_of_Employee.setVisibility(View.GONE);
                        }else
                        {
                            co_no_of_Employee.setVisibility(View.VISIBLE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_spinner_cmp_type.setOnTouchListener(new View.OnTouchListener() {
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



            pl_co_app_spinner_employe_id.setAdapter(Employee_ID_Adapter);
            pl_co_app_spinner_employe_id.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {



                        pl_co_app_Employee_id = employee_id_ar.getJSONObject(position).getString("id");
                        pl_co_app_Employee_Value = employee_id_ar.getJSONObject(position).getString("value");





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_spinner_employe_id.setOnTouchListener(new View.OnTouchListener() {
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
            pl_co_self_has_pan_card_spnr.setAdapter(PAN_ID_Adapter);
            pl_co_self_has_pan_card_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        Pl_Co_App_PAN_id = has_pancard_ar.getJSONObject(position).getString("id");
                        Pl_Co_App_PAN_Value = has_pancard_ar.getJSONObject(position).getString("value");




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_self_has_pan_card_spnr.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }
    private void Type_of_Employee(final JSONArray Type_employee_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

        Employee_type_SA = new String[Type_employee_ar.length()];
        for (int i=0;i<Type_employee_ar.length();i++){
            JSONObject J =  Type_employee_ar.getJSONObject(i);
            Employee_type_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Employee_type_SA));
            Type_employee_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Type_employee_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            employee_type_spnr.setAdapter(Type_employee_Adapter);
            employee_type_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        Emp_type_id = Type_employee_ar.getJSONObject(position).getString("id");
                        Emp_type_Value = Type_employee_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Company_id", Emp_type_id);
                        Log.d("Company_Value", Emp_type_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            employee_type_spnr.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


            pl_co_app_employee_type_spnr.setAdapter(Type_employee_Adapter);
            pl_co_app_employee_type_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        pl_co_app_Emp_type_id = Type_employee_ar.getJSONObject(position).getString("id");
                        pl_co_app_Emp_type_Value = Type_employee_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_employee_type_spnr.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

        }

    }

    private void Current_res_Type(final JSONArray Current_Residence_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Current_res_SA = new String[Current_Residence_ar.length()];
        for (int i=0;i<Current_Residence_ar.length();i++){
            JSONObject J =  Current_Residence_ar.getJSONObject(i);
            Current_res_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Current_res_SA));
            Current_residence_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Current_residence_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            current_res_spinner.setAdapter(Current_residence_Adapter);
            current_res_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        current_ress_id = Current_Residence_ar.getJSONObject(position).getString("id");
                        current_ress_Value = Current_Residence_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            current_res_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Marital_Statues(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            Marital_Statues_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Marital_Statues_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            maried_res_spinner.setAdapter(Marital_Statues_Adapter);
            maried_res_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        maried_res_spinner_id = Marital_Statues_ar.getJSONObject(position).getString("id");
                        maried_res_spinner_Value = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            maried_res_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


            pl_co_app_maried_res_spinner.setAdapter(Marital_Statues_Adapter);
            pl_co_app_maried_res_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        pl_co_app_maried_res_spinner_id = Marital_Statues_ar.getJSONObject(position).getString("id");
                        pl_co_app_maried_res_spinner_Value = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_maried_res_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }



    private void Epf_detedcted(final JSONArray epf_jarray) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Epf_detected_SA = new String[epf_jarray.length()];
        for (int i=0;i<epf_jarray.length();i++){
            JSONObject J =  epf_jarray.getJSONObject(i);
            Epf_detected_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Epf_detected_SA));
            Epf_detected_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Epf_detected_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            epf_spinner.setAdapter(Epf_detected_Adapter);
            epf_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        epf_id = epf_jarray.getJSONObject(position).getString("id");
                        epf_Value = epf_jarray.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("epf_id", epf_id);
                        Log.d("epf_Value", epf_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            epf_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


            pl_co_app_epf_spinner.setAdapter(Epf_detected_Adapter);
            pl_co_app_epf_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        pl_co_app_epf_id = epf_jarray.getJSONObject(position).getString("id");
                        pl_co_app_epf_Value = epf_jarray.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("epf_id", epf_id);
                        Log.d("epf_Value", epf_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_epf_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }


    private void Own_hous_relative(final JSONArray Own_house_rela_Ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Own_house_relativ_SA = new String[Own_house_rela_Ar.length()];
        for (int i=0;i<Own_house_rela_Ar.length();i++){
            JSONObject J =  Own_house_rela_Ar.getJSONObject(i);
            Own_house_relativ_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Own_house_relativ_SA));
            Own_house_Relative_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Own_house_Relative_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            own_hose_relative_spinner.setAdapter(Own_house_Relative_Adapter);
            own_hose_relative_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");


                        Own_house_rela_id = Own_house_rela_Ar.getJSONObject(position).getString("id");
                        Own_house_rela_Value = Own_house_rela_Ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            own_hose_relative_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Education_Qualification_(final JSONArray GST_reflected_ar) throws JSONException {

        Gst_refelect_SA = new String[GST_reflected_ar.length()];
        for (int i=0;i<GST_reflected_ar.length();i++){
            JSONObject J =  GST_reflected_ar.getJSONObject(i);
            Gst_refelect_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Gst_refelect_SA));
            Gst_reflect_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Gst_reflect_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            Educational_Spinner_pl.setAdapter(Gst_reflect_adapter);
            Educational_Spinner_pl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        Educational_Id = GST_reflected_ar.getJSONObject(position).getString("id");
                        Educational_Value = GST_reflected_ar.getJSONObject(position).getString("value");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Educational_Spinner_pl.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });



            co_Educational_Spinner_pl.setAdapter(Gst_reflect_adapter);
            co_Educational_Spinner_pl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        co_salaried_Educational_Id = GST_reflected_ar.getJSONObject(position).getString("id");
                        co_salaried_Educational_Value = GST_reflected_ar.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_Educational_Spinner_pl.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Permanent_res_Type(final JSONArray Permanent_Resi_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Permanent_Resi_SA = new String[Permanent_Resi_ar.length()];
        for (int i=0;i<Permanent_Resi_ar.length();i++){
            JSONObject J =  Permanent_Resi_ar.getJSONObject(i);
            Permanent_Resi_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Permanent_Resi_SA));
            Permanent_Resi_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Permanent_Resi_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            permanent_res_type_res_spinner.setAdapter(Permanent_Resi_Adapter);
            permanent_res_type_res_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");


                        Permanent_Resi_id = Permanent_Resi_ar.getJSONObject(position).getString("id");
                        Permanent_Resi_Value = Permanent_Resi_ar.getJSONObject(position).getString("value");

                        if(Permanent_Resi_id.equals("1"))
                        {
                            rented.setVisibility(View.VISIBLE);
                        }else if(Permanent_Resi_id.equals("2"))
                        {
                            rented.setVisibility(View.GONE);
                        }else
                        {
                            rented.setVisibility(View.GONE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            permanent_res_type_res_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }


    private void Other_income_f(final JSONArray Other_income_Ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Other_income_SA = new String[Other_income_Ar.length()];
        for (int i=0;i<Other_income_Ar.length();i++){
            JSONObject J =  Other_income_Ar.getJSONObject(i);
            Other_income_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Other_income_SA));
            Other_income_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Other_income_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spinn_other_income.setAdapter( Other_income_Adapter);
            spinn_other_income.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Other_income_id = Other_income_Ar.getJSONObject(position).getString("id");
                        Other_income_Value = Other_income_Ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                         if(Other_income_id.equals("4"))
                        {
                            is_other_income.setVisibility(View.GONE);
                        }else
                        {
                            is_other_income.setVisibility(View.VISIBLE);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinn_other_income.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

            pl_co_app_spinn_other_income.setAdapter( Other_income_Adapter);
            pl_co_app_spinn_other_income.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        pl_co_app_Other_income_id = Other_income_Ar.getJSONObject(position).getString("id");
                        pl_co_app_Other_income_Value = Other_income_Ar.getJSONObject(position).getString("value");


                        if(pl_co_app_Other_income_id.equals("4"))
                        {
                            co_app_is_other_income.setVisibility(View.GONE);
                        }else
                        {
                            co_app_is_other_income.setVisibility(View.VISIBLE);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_spinn_other_income.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

    }



    private void itr_reflect_f(final JSONArray gst_reflect_Ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        gst_reflect_SA = new String[gst_reflect_Ar.length()];
        for (int i=0;i<gst_reflect_Ar.length();i++){
            JSONObject J =  gst_reflect_Ar.getJSONObject(i);
            gst_reflect_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(gst_reflect_SA));
            gst_reflect_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            gst_reflect_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spinn_is_gst_reflect.setAdapter(gst_reflect_Adapter);
            spinn_is_gst_reflect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");


                        gst_reflect_id = gst_reflect_Ar.getJSONObject(position).getString("id");
                        gst_reflect_Value = gst_reflect_Ar.getJSONObject(position).getString("value");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinn_is_gst_reflect.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

            pl_co_App_spinn_is_gst_reflect.setAdapter(gst_reflect_Adapter);
            pl_co_App_spinn_is_gst_reflect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        pl_co_App_gst_reflect_id = gst_reflect_Ar.getJSONObject(position).getString("id");
                        pl_co_App_gst_reflect_Value = gst_reflect_Ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_App_spinn_is_gst_reflect.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

    }


    ///self


    private void Bank_account(final JSONArray Provide_Guarantor) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Provide_guarente_SA = new String[Provide_Guarantor.length()];
        for (int i=0;i<Provide_Guarantor.length();i++){
            JSONObject J =  Provide_Guarantor.getJSONObject(i);
            Provide_guarente_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Provide_guarente_SA));
            Provide_gurenter_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Provide_gurenter_adapter.setDropDownViewResource(R.layout.view_spinner_item);

            co_having_bank_account.setAdapter(Provide_gurenter_adapter);
            co_having_bank_account.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        co_having_bank_Id = Provide_Guarantor.getJSONObject(position).getString("id");
                        co_having_bank_Value = Provide_Guarantor.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_having_bank_account.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Co_Education_Qualification_(final JSONArray GST_reflected_ar) throws JSONException {

        Gst_refelect_SA = new String[GST_reflected_ar.length()];
        for (int i=0;i<GST_reflected_ar.length();i++){
            JSONObject J =  GST_reflected_ar.getJSONObject(i);
            Gst_refelect_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Gst_refelect_SA));
            Gst_reflect_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Gst_reflect_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            co_Educatio_qualification_Sppinner.setAdapter(Gst_reflect_adapter);
            co_Educatio_qualification_Sppinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        co_Educational_Id = GST_reflected_ar.getJSONObject(position).getString("id");
                        co_Educational_Value = GST_reflected_ar.getJSONObject(position).getString("value");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_Educatio_qualification_Sppinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Current_res_proof(final JSONArray Current_res_proof_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Current_address_Proof_SA = new String[Current_res_proof_ar.length()];
        for (int i=0;i<Current_res_proof_ar.length();i++){
            JSONObject J =  Current_res_proof_ar.getJSONObject(i);
            Current_address_Proof_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Current_address_Proof_SA));
            CureentAddress_proof_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            CureentAddress_proof_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            co_spinner_res_proof.setAdapter(CureentAddress_proof_adapter);
            co_spinner_res_proof.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        co_Spinner_res_proof_Id = Current_res_proof_ar.getJSONObject(position).getString("id");
                        co_Spinner_res_proof_Value = Current_res_proof_ar.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_spinner_res_proof.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Other_income(final JSONArray Other_income_ar) throws JSONException {

        Other_income_SA = new String[Other_income_ar.length()];
        for (int i=0;i<Other_income_ar.length();i++){
            JSONObject J =  Other_income_ar.getJSONObject(i);
            Other_income_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Other_income_SA));
            Other_income_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Other_income_adapter.setDropDownViewResource(R.layout.view_spinner_item);

            co_spinn_other_income.setAdapter(Other_income_adapter);
            co_spinn_other_income.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        co_Other_income_Id = Other_income_ar.getJSONObject(position).getString("id");
                        co_Other_income_Value = Other_income_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

                        if(co_Other_income_Id.equals("0"))
                        {
                            co_other_income_ifany.setVisibility(View.VISIBLE);

                        }else if(co_Other_income_Id.equals("4"))
                        {
                            co_other_income_ifany.setVisibility(View.GONE);
                        }else
                        {
                            co_other_income_ifany.setVisibility(View.VISIBLE);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_spinn_other_income.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

        }

    }

    private void GST_reflected(final JSONArray GST_reflected_ar) throws JSONException {

        Gst_refelect_SA = new String[GST_reflected_ar.length()];
        for (int i=0;i<GST_reflected_ar.length();i++){
            JSONObject J =  GST_reflected_ar.getJSONObject(i);
            Gst_refelect_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Gst_refelect_SA));
            Gst_reflect_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Gst_reflect_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            co_spinn_gst_other.setAdapter(Gst_reflect_adapter);
            co_spinn_gst_other.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        co_Gstreflect_Id = GST_reflected_ar.getJSONObject(position).getString("id");
                        co_Gstreflect_Value = GST_reflected_ar.getJSONObject(position).getString("value");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_spinn_gst_other.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

    }


    private void Business_registration_fun(final JSONArray Business_registration_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Business_registration_SA = new String[Business_registration_ar.length()];
        for (int i=0;i<Business_registration_ar.length();i++){
            JSONObject J =  Business_registration_ar.getJSONObject(i);
            Business_registration_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Business_registration_SA));
            Business_registration_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Business_registration_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            co_business_registration_spinner.setAdapter(Business_registration_adapter);
            co_business_registration_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {



                        co_Business_registration_Id = Business_registration_ar.getJSONObject(position).getString("id");
                        co_Business_registration_Value = Business_registration_ar.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_business_registration_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }


    private void lead_Eligibility() {


        S_company_name_edtxt = company_name_edtxt.getText().toString();
        S_Designation_in_current_companny = designation_in_company.getText().toString();
        S_no_of_dependent_edt_txt = no_of_dependent_edt_txt.getText().toString();
      //  S_education_qualification_edit_txt = education_qualification_edit_txt.getText().toString();
        S_emi_amount_edit_txt = emi_amount_edit_txt.getText().toString();
        S_other_incom_amt_edtxt = other_incom_amt_edtxt.getText().toString();
        S_no_of_emp_edtxt = no_of_emp_edtxt.getText().toString();
        S_permanent_residence_spinner = permanent_residence_auto.getText().toString();
        S_own_house_blood_address_edt_txt = own_house_blood_address_edt_txt.getText().toString();
        S_rent_paid_for_house_edit_txt = rent_paid_for_house_edit_txt.getText().toString();

        S_pl_co_app_company_name_edtxt = pl_co_app_company_name_edtxt.getText().toString();
        S_pl_co_App_no_of_emp_edtxt = pl_co_App_no_of_emp_edtxt.getText().toString();
        S_pl_co_app_designation_in_company = pl_co_app_designation_in_company.getText().toString();
        S_pl_co_app_no_of_dependent_edt_txt = pl_co_app_no_of_dependent_edt_txt.getText().toString();
        S_pl_co_app_emi_amount_edit_txt = pl_co_app_emi_amount_edit_txt.getText().toString();
      //  S_pl_co_App_education_qualification_edit_txt = pl_co_App_education_qualification_edit_txt.getText().toString();
        S_pl_co_App_other_incom_amt_edtxt = pl_co_App_other_incom_amt_edtxt.getText().toString();


        S_co_business_ref_name_edt_txt = co_business_ref_name_edt_txt.getText().toString();
        S_co_purchased_by_bank_edit_txt = co_purchased_by_bank_edit_txt.getText().toString();
        S_co_purchased_by_GStbill_edit_txt = co_purchased_by_GStbill_edit_txt.getText().toString();
        S_co_sales_by_GStbill_edit_txt = co_sales_by_GStbill_edit_txt.getText().toString();
        S_co_bank_cridit_by_edtxt = co_bank_cridit_by_edtxt.getText().toString();
        S_co_Avg_monthly_income = co_Avg_monthly_income.getText().toString();
        S_co_other_income_edite_txt = co_other_income_edite_txt.getText().toString();


        JSONObject jsonObject =new JSONObject();
         Applicant =new JSONObject();
         Co_Applicant1 =new JSONObject();


        JSONArray other_income = new JSONArray();
        JSONArray other_amount = new JSONArray();
        JSONArray itr_reflected = new JSONArray();

        other_income = new JSONArray(Arrays.asList(Other_income_id));
        other_amount = new JSONArray(Arrays.asList(S_other_incom_amt_edtxt));
        itr_reflected = new JSONArray(Arrays.asList(gst_reflect_id));

        JSONObject J= null;
        try {

            J =new JSONObject();

            Applicant.put("has_pancardHave ",PAN_id);
            Applicant.put("has_emp_id ",Employee_id);
            Applicant.put("company_type",Company_id);
            Applicant.put("designation",S_Designation_in_current_companny);
            Applicant.put("company_name",S_company_name_edtxt);
           Applicant.put("employees_count",S_no_of_emp_edtxt);
            Applicant.put("is_epf_deduct",epf_id);
            Applicant.put("addr_proof_rentCurrent",current_ress_id);
            Applicant.put("marital_status",maried_res_spinner_id);
            Applicant.put("no_of_dependency",S_no_of_dependent_edt_txt);
            Applicant.put("affordable_pay",S_emi_amount_edit_txt);
            Applicant.put("other_from",other_income);
            Applicant.put("other_amount",other_amount);
            Applicant.put("other_reflected",itr_reflected);
            Applicant.put("perm_res_pincode",S_permanent_residence_spinner);
            Applicant.put("perm_res_area ",permanent_residence__area_id);
            Applicant.put("perm_residence",Permanent_Resi_id);
            Applicant.put("own_house_relative",Own_house_rela_id);
            Applicant.put("rel_address",S_own_house_blood_address_edt_txt);
            Applicant.put("rent_beingpaid",S_rent_paid_for_house_edit_txt);
            Applicant.put("qualification",Educational_Id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(Co_Applicant.equals("1"))
        {
            try {

                if(IS_CO_Salried_Self.equals("1"))
                {
                    Co_Applicant1.put("has_pancardHave",Pl_Co_App_PAN_id);
                    Co_Applicant1.put("has_emp_id",pl_co_app_Employee_id);
                    Co_Applicant1.put("company_type",pl_co_app_Company_id);
                    Co_Applicant1.put("pl_co_app_Emp_type_id",pl_co_app_Emp_type_id);
                    Co_Applicant1.put("is_epf_deduct",pl_co_app_epf_id);
                    Co_Applicant1.put("marital_status",pl_co_app_maried_res_spinner_id);
                    Co_Applicant1.put("company_name",S_pl_co_app_company_name_edtxt);
                    Co_Applicant1.put("employees_count",S_pl_co_App_no_of_emp_edtxt);
                    Co_Applicant1.put("designation",S_pl_co_app_designation_in_company);
                    Co_Applicant1.put("no_of_dependency",S_pl_co_app_no_of_dependent_edt_txt);
                    Co_Applicant1.put("affordable_pay",S_pl_co_app_emi_amount_edit_txt);
                    Co_Applicant1.put("qualification",co_salaried_Educational_Id);

                    JSONArray co_Other_income_Id_ar = new JSONArray(Arrays.asList(pl_co_app_Other_income_id));
                    JSONArray S_co_other_income_edite_txt_ar = new JSONArray(Arrays.asList(S_pl_co_App_other_incom_amt_edtxt));
                    JSONArray S_co_Gstreflect_Id_ar = new JSONArray(Arrays.asList(pl_co_App_gst_reflect_id));

                    Co_Applicant1.put("other_from",co_Other_income_Id_ar);
                    Co_Applicant1.put("other_amount",S_co_other_income_edite_txt_ar);
                    Co_Applicant1.put("other_reflected",S_co_Gstreflect_Id_ar);

                }else if(IS_CO_Salried_Self.equals("2"))
                {
                    Co_Applicant1.put("has_sb_account",co_having_bank_Id);
                    Co_Applicant1.put("qualification",co_Educational_Id);
                    Co_Applicant1.put("reference_name",co_business_ref_name_edt_txt);
                    Co_Applicant1.put("addr_proof_own",co_Spinner_res_proof_Id);

                    JSONArray co_Other_income_Id_ar = new JSONArray(Arrays.asList(co_Other_income_Id));
                    JSONArray S_co_other_income_edite_txt_ar = new JSONArray(Arrays.asList(S_co_other_income_edite_txt));
                    JSONArray S_co_Gstreflect_Id_ar = new JSONArray(Arrays.asList(co_Gstreflect_Id));

                    Co_Applicant1.put("other_from",co_Other_income_Id_ar);
                    Co_Applicant1.put("other_amount",S_co_other_income_edite_txt_ar);
                    Co_Applicant1.put("other_reflected",S_co_Gstreflect_Id_ar);
                    Co_Applicant1.put("bussiness_registeration",co_Business_registration_Id);

                    Co_Applicant1.put("purpercent_bankacc",S_co_purchased_by_bank_edit_txt);
                    Co_Applicant1.put("purpercent_gstbill",S_co_purchased_by_GStbill_edit_txt);
                    Co_Applicant1.put("salepercent_gstbill",S_co_sales_by_GStbill_edit_txt);
                    Co_Applicant1.put("incomepercent_bank",S_co_bank_cridit_by_edtxt);
                    Co_Applicant1.put("avg_bankbalance",S_co_Avg_monthly_income);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {

        }

        try {
            J =new JSONObject();
            //  J.put(Params.email_id,email);
            J.put("applicant_count","1");
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("applicant",Applicant);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Add Home Laoan", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Eligibility_Check_applicant, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        Log.e("Add_Home_loan Partner", String.valueOf(response));
                        try {
                            JSONObject jsonObject1 = response.getJSONObject("response");
                            if(jsonObject1.getString("applicant_status").equals("success")) {

                                progressDialog.dismiss();
                                LayoutInflater layoutInflater = (LayoutInflater) Eligibility_Check_PL.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View customView = layoutInflater.inflate(R.layout.popup_loading,null);
                                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                //display the popup window
                                popupWindow.showAtLocation(lead_viy_step2, Gravity.CENTER, 0, 0);

                                if(Co_Applicant.equals("1"))
                                {
                                    Eligibility_Co_Applicant();
                                }else
                                {
                                    Eligibility_check_pass();
                                }


                            }else
                            {
                                Toast.makeText(mCon, "error, try after some time",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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


    private void Eligibility_Co_Applicant( ) {

        JSONObject J= null;

        try {
            J =new JSONObject();
            J.put("applicant_count","2");
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("applicant",Co_Applicant1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("viability co_Applicant ", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Eligibility_Check_co_applicant, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("viability response", String.valueOf(response));
                        String data = String.valueOf(response);
                        try {
                            //  String Status = response.getString("status");
                            JSONObject jsonObject1 = response.getJSONObject("response");
                            if(jsonObject1.getString("applicant_status").equals("success")) {


                                Eligibility_check_pass();

                            }
                            ///
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Lead creation", String.valueOf(response));


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

    private void Eligibility_check_pass( ) {

        JSONObject J= null;

        try {
            J =new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            J.put("b2b_id", Pref.getID(mCon));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("viability", String.valueOf(J));
       // progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Eligibility_Check_eligibilitysavet, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("viability response", String.valueOf(response));
                        String data = String.valueOf(response);
                        try {
                            //  String Status = response.getString("status");
                            JSONObject jsonObject1 = response.getJSONObject("response");

                               if(jsonObject1.getString("eligibility_status").equals("success"))
                                {
                                 //   Eligibility_check_doc_checklist_generate();
                                }else if(jsonObject1.getString("eligibility_status").equals("error"))
                                {
                                    Toast.makeText(context,"Eligibility Failed",Toast.LENGTH_SHORT).show();
                                    String viability_array =jsonObject1.getString("eligibility_arr");
                                    Intent intent = new Intent(Eligibility_Check_PL.this, Loan_Viyability_Check_Activity.class);
                                    intent.putExtra("viability_jsonArray", viability_array.toString());
                                    startActivity(intent);
                                    finish();
                                }
                            ///
                          //  progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Lead creation", String.valueOf(response));


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

  /*  private void Eligibility_check_doc_checklist_generate( ) {

        JSONObject J= null;

        try {
            J =new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("viability", String.valueOf(J));
      //  progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.generate_doccklist, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("viability response", String.valueOf(response));
                        String data = String.valueOf(response);
                        try {
                             String Generate_Document_check_list = response.getString("Generate_Document_check_list");
                            JSONObject jsonObject1 = response.getJSONObject("response");


                            if(response.getString("Generate_Document_check_list").equals("success"))
                            {
                                LayoutInflater layoutInflater = (LayoutInflater) Eligibility_Check_PL.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View customView = layoutInflater.inflate(R.layout.popup_loading,null);
                                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                //display the popup window
                                popupWindow.dismiss();
                                Toast.makeText(context,"Eligibility Created Successfully",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Eligibility_Check_PL.this, PaymentActivity.class);
                                startActivity(intent);
                                finish();
                            }else if(response.getString("Generate_Document_check_list").equals("error"))
                            {
                                Toast.makeText(context,"Eligibility Failed",Toast.LENGTH_SHORT).show();
                                String viability_array =jsonObject1.getString("eligibility_arr");
                                Intent intent = new Intent(Eligibility_Check_PL.this, Loan_Viyability_Check_Activity.class);
                                intent.putExtra("viability_jsonArray", viability_array.toString());
                                startActivity(intent);
                                finish();
                            }

                            ///
                           // progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Lead creation", String.valueOf(response));


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
    }*/

  /*  @Override
    public void onBackPressed() {
        Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
        finish();
        super.onBackPressed();
    }*/

}
