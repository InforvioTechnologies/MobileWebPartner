package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
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
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Eligibility_HL_New extends SimpleActivity {

    private LinearLayout self_business,individual,co_individual,
            co_self_business;
    private String Lontype ;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;

    Typeface font;
    private Context context = this;
    InputMethodManager imm;
    JSONArray Provide_Guarantor,Current_address_proof,GST_reflected,other_income,
            Business_registration,Bank_account,permanent_adddress,Reflected_ITR,Education_Qualification;

    Spinner spinner_res_proof,spinner_guaranter,spinner_existing_loan,spinn_other_income,
            spinn_gst_other,business_registration_spinner,having_bank_account,permanent_res_type_spinner,
            Educatio_qualification_Sppinner;

    String[] Current_address_Proof_SA,Provide_guarente_SA,Other_income_SA,
            Gst_refelect_SA,Business_registration_SA;
    ArrayAdapter<String> CureentAddress_proof_adapter,Provide_gurenter_adapter,
                         Other_income_adapter,Gst_reflect_adapter,
            Business_registration_adapter;
    String Spinner_res_proof_Id,Spinner_res_proof_Value,Guarenter_Id,Guarenter_Value,
            Gstreflect_Id,Gstreflect_Value,Business_registration_Id,Business_registration_Value,
            Other_income_Id,Other_income_Value,having_bank_Id,having_bank_Value,permanent_res_Id,
            permanent_res_Value;

    AppCompatTextView business_registration_txt,business_registration_txt1,
            avg_bank_balence_txt,avg_bank_balence_txt1,res_proof,res_proof1,guaranter_txt,guaranter_txt1,
            business_r_name_txt,business_r_name_txt1,business_refernce_txt,business_refernce_txt1,
            other_income_txt,other_income_txt1,other_incom_amt_txt,other_incom_amt_txt1,
            is_gst_reflect_txt,is_gst_reflect_txt1,purchased_by_bank1,purchased_by_bank2,purchased_by_gst,purchased_by_gst1,
            bank_credit1_txt,bank_credit1_txt1, gst_sales1_txt,gst_sales1_txt1;

    AppCompatEditText Avg_monthly_income,Business_reference_mobile,Business_refernce_name,
            other_income_edite_txt,purchased_by_bank_edit_txt,purchased_by_GStbill_edit_txt
           ,sales_by_GStbill_edit_txt,bank_cridit_by_edtxt,business_ref_name_edt_txt;
    AppCompatButton lead_Elegibility_Bank;

    LinearLayout other_income_ifany,co_applicant_pl_co_applicant,residence_rented,co_other_income_ifany;
    String user_id,transaction_id,IS_CO_Applicant_Id,CO_Type_of_employement_ID,Educational_Id,Educational_Value,
            Employement_Type,Rsidence_Type,purchased_by_bank_edit_txt1,purchased_by_GStbill_edit_txt1,sales_by_GStbill_edit_txt1,bank_cridit_by_edtxt1,
            Avg_monthly_income1,Business_refernce_name1,Business_reference_mobile1,other_income_edite_txt1,
            S_business_ref_name_edt_txt;

    AppCompatEditText permanent_res_pincode_edt_txt;

    int app_count;

    // Co Applicant Self

    JSONArray Other_income,epf_deduct,Marital_Status,Type_employee,Company_type,
            employee_id_ar,have_pan_ar,Current_Residence,relation_own,Property_Status,transaction_type,Construction_Status;;

    String [] PAN_ID_SA,Employe_ID_SA,Company_type_SA,Employee_type_SA,Marital_Statues_SA,Epf_detected_SA,
            gst_reflect_SA,Current_res_SA,Own_house_relativ_SA,Permanent_Resi_SA,Pincode_SA,Area;

    ArrayAdapter<String> PAN_ID_Adapter,Employee_ID_Adapter,Company_type_Adapter,Type_employee_Adapter,Marital_Statues_Adapter,
            Epf_detected_Adapter,Other_income_Adapter,gst_reflect_Adapter,Current_residence_Adapter,
            Own_house_Relative_Adapter,Permanent_Resi_Adapter,Pincode_Adapter,A_Area_Adapter;

        String  co_having_bank_Id,co_having_bank_Value, co_Educational_Id,co_Educational_Value,
                co_Business_registration_Id,co_Business_registration_Value, co_Spinner_res_proof_Id,co_Spinner_res_proof_Value,
                co_Other_income_Id,co_Other_income_Value, co_Gstreflect_Id,co_Gstreflect_Value,CO_Employement_Type,
                IS_CO_Salried_Self;

        Spinner co_having_bank_account,co_Educatio_qualification_Sppinner,co_business_registration_spinner,
            co_spinner_res_proof,co_spinn_other_income,co_spinn_gst_other;

        AppCompatEditText co_business_ref_name_edt_txt,co_purchased_by_bank_edit_txt,co_purchased_by_GStbill_edit_txt,
                co_sales_by_GStbill_edit_txt,co_bank_cridit_by_edtxt,co_Avg_monthly_income,co_other_income_edite_txt;

        Spinner  pl_co_self_has_pan_card_spnr,pl_co_app_spinner_employe_id,pl_co_app_spinner_cmp_type,
                pl_co_app_employee_type_spnr,pl_co_app_epf_spinner,
                pl_co_app_spinn_other_income,pl_co_App_spinn_is_gst_reflect,pl_co_app_maried_res_spinner;

        String   Pl_Co_App_PAN_id,Pl_Co_App_PAN_Value,pl_co_app_Employee_id,pl_co_app_Employee_Value,
                pl_co_app_Company_id,pl_co_app_Company_Value,pl_co_app_Emp_type_id,pl_co_app_Other_income_Value,
                pl_co_App_gst_reflect_id,pl_co_App_gst_reflect_Value, pl_co_app_epf_id,pl_co_app_epf_Value,
                pl_co_app_maried_res_spinner_id,pl_co_app_maried_res_spinner_Value,pl_co_app_Other_income_id,
                pl_co_app_Emp_type_Value;

        String S_pl_co_app_company_name_edtxt,S_pl_co_App_no_of_emp_edtxt,S_pl_co_app_designation_in_company,
                S_pl_co_app_no_of_dependent_edt_txt,S_pl_co_app_emi_amount_edit_txt,S_pl_co_App_education_qualification_edit_txt,
                S_co_business_ref_name_edt_txt,S_co_purchased_by_bank_edit_txt,S_co_purchased_by_GStbill_edit_txt,
                S_co_sales_by_GStbill_edit_txt,S_co_bank_cridit_by_edtxt,S_co_Avg_monthly_income,S_co_other_income_edite_txt,
                S_purchased_by_bank_edit_txt1,S_purchased_by_GStbill_edit_txt1,S_sales_by_GStbill_edit_txt1,S_bank_cridit_by_edtxt1,
                S_Avg_monthly_income1,S_other_income_edite_txt1,S_pl_co_App_other_incom_amt_edtxt;

        String salary_type,residence_id;

        LinearLayout co_app_is_other_income,bl_co_eligibility_salaried,bl_co_eligibility_self,
                app_eligibility_salaried_hl,app_eligibility_self_hl,age_of_Property_lap;
        LinearLayout full_addres_of_relative,permanent_res_type_ly,rent_paid_for_house_ly,pl_co_app_cmp_name,pl_co_cmp_des_,
                permanent_residence_pincode_ly,permanent_res_area;

        AppCompatEditText  pl_co_app_company_name_edtxt,pl_co_app_designation_in_company,pl_co_App_no_of_emp_edtxt,pl_co_app_no_of_dependent_edt_txt,
                pl_co_App_education_qualification_edit_txt, pl_co_app_emi_amount_edit_txt,pl_co_App_other_incom_amt_edtxt;


        //Applicant
    Spinner spinner_cmp_type, has_pan_card_spnr,spinner_employe_id,employee_type_spnr,epf_spinner,
    current_res_spinner,maried_res_spinner,applicant_salaried_spinn_other_income,spinn_is_gst_reflect,
    own_hose_relative_spinner,permanent_res_type_res_spinner,permanent_residence_spinn_area;

    String PAN_id,PAN_Value,Employee_id,Employee_Value,Company_id,Company_Value,
    Emp_type_id,Emp_type_Value,epf_id,epf_Value,current_ress_id,current_ress_Value,
    maried_res_spinner_id,maried_res_spinner_Value,Other_income_id,gst_reflect_id,
    gst_reflect_Value,Own_house_rela_id,Own_house_rela_Value,Permanent_Resi_id,Permanent_Resi_Value,App_salaried_Other_income_id;

      LinearLayout hl_eligi_app_cmp_name,hl_eligibility_cmp_des_,is_other_income,rented_applicant_salaried_hl,
              property_field,property_field_bt_TOP_up;

      AppCompatEditText  company_name_edtxt,no_of_emp_edtxt,no_of_dependent_edt_txt,own_house_blood_address_edt_txt,
              education_qualification_edit_txt,rent_paid_for_house_edit_txt,other_incom_amt_edtxt,
              designation_in_company,emi_amount_edit_txt;

      String S_company_name_edtxt,S_Designation_in_current_companny,S_no_of_dependent_edt_txt,S_education_qualification_edit_txt,
              S_emi_amount_edit_txt,S_other_incom_amt_edtxt,S_no_of_emp_edtxt,S_permanent_residence_spinner,
              S_own_house_blood_address_edt_txt,S_rent_paid_for_house_edit_txt,
              permanent_residence__area_id,cpermanent_residence_spinn_district_id,permanent_residence_spinn_state_id,property_identified;

    AppCompatAutoCompleteTextView permanent_residence_auto;




    Spinner propert_statues_spinner,transaction_type_spi,construction_statues_spinner;

    String transaction_type_spi_id,transaction_type_spi_value,propert_statues_spinner_id,
            propert_statues_spinner_value,S_plot_area_edit_txt,S_build_up_area_edit_txt,S_property_price_edt_txt,
            loan_type_id,S_age_Of_Property_Edit_Text,construction_statues_spinner_id,construction_statues_spinner_value,
            S_cost_of_plot_edite_text,S_plot_area_edit_txt_plot_construction,S_build_up_area_edit_txt_plot_construction,
            S_plot_property_price_edt_txt,S_plot_area_edit_txt_plot_;

    AppCompatEditText plot_area_edit_txt,build_up_area_edit_txt,property_price_edt_txt,age_Of_Property_Edit_Text,
            cost_of_plot_edite_text,plot_area_edit_txt_plot_construction,build_up_area_edit_txt_plot_construction,
            plot_property_price_edt_txt,plot_area_edit_txt_plot_;


    AppCompatEditText plot_area_edit_txt_bt,build_up_area_edit_txt_bt,property_price_edt_txt_bt,Existing_bank_edit_txt,
            Loan_varient_edit_txt,loan_amount_edit_txt,original_tenure_edit_txt,remaining_tenure_edit_txt,
            interest_rate_edit_txt,plot_area_edit_txt_improment,ploat_area_edit_txt_improment,cost_estimation_edit_txt_improment,
            property_ownership_edt_txt_improment;

    String  S_plot_area_edit_txt_bt,S_build_up_area_edit_txt_bt,S_property_price_edt_txt_bt,S_Existing_bank_edit_txt,S_Loan_varient_edit_txt,
            S_loan_amount_edit_txt,S_original_tenure_edit_txt,S_remaining_tenure_edit_txt,
            S_interest_rate_edit_txt;

    String S_plot_area_edit_txt_improment,S_ploat_area_edit_txt_improment,S_cost_estimation_edit_txt_improment,
            S_property_ownership_edt_txt_improment,R_state;

    LinearLayout plot_construction_loan,identified_yes_or_no,plot_loan,improvment_extention;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_eligibility__bl);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_eligibility__hl_new);
        initTools(R.string.eligi_check);

        Lontype = Pref.getLoanType(getApplicationContext());

        self_business = (LinearLayout) findViewById(R.id.self_business);

        progressDialog = new SpotsDialog(context, R.style.Custom);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        transaction_id = intent.getStringExtra("transaction_id");


        IS_CO_Applicant_Id = Pref.getCoAPPAVAILABLE(getApplicationContext());
        IS_CO_Salried_Self = Pref.getCOAPPSALARYTYPE(getApplicationContext());
        salary_type = Pref.getSALARYTYPE(context);

        loan_type_id = Pref.getLoanType(context);

        Employement_Type = Pref.getCOSALARYTYPE(getApplicationContext());
        CO_Employement_Type = Pref.getCOEMPTYPE(getApplicationContext());
        Rsidence_Type = Pref.getResidenceType(getApplicationContext());
        residence_id = Pref.get_Residence_ID(getApplicationContext());
        property_identified = Pref.getPROPERTYIDENTIFIED(getApplicationContext());

        makeJsonObjReq1();
        UISCREENS();
        Font();
        Click();

       // Log.e("the salried type",salary_type);
       // Log.e("the CO_Employ",CO_Employement_Type);

        if(salary_type.equals("1"))
        {
            app_eligibility_salaried_hl.setVisibility(View.VISIBLE);
            app_eligibility_self_hl.setVisibility(View.GONE);
            if(residence_id.equals("1"))
            {
                full_addres_of_relative.setVisibility(View.GONE);
                permanent_res_type_ly.setVisibility(View.GONE);
                permanent_residence_pincode_ly.setVisibility(View.GONE);
                permanent_res_area.setVisibility(View.GONE);
                rented_applicant_salaried_hl.setVisibility(View.GONE);
                rent_paid_for_house_ly.setVisibility(View.GONE);
            }else
            {
                full_addres_of_relative.setVisibility(View.VISIBLE);
                permanent_res_type_ly.setVisibility(View.VISIBLE);
                permanent_residence_pincode_ly.setVisibility(View.VISIBLE);
                permanent_res_area.setVisibility(View.VISIBLE);
                rented_applicant_salaried_hl.setVisibility(View.VISIBLE);
                rent_paid_for_house_ly.setVisibility(View.VISIBLE);
            }

        }else if(salary_type.equals("2"))
        {
            app_eligibility_salaried_hl.setVisibility(View.GONE);
            app_eligibility_self_hl.setVisibility(View.VISIBLE);

            if(Rsidence_Type.equals("2"))
            {
                residence_rented.setVisibility(View.VISIBLE);
            }else
            {
                residence_rented.setVisibility(View.GONE);
            }

        }

        if(property_identified.equals("1"))
        {
            property_field.setVisibility(View.VISIBLE);
        }else
        {
            property_field.setVisibility(View.VISIBLE);
        }

        if(Employement_Type.equals("1"))
        {
            individual.setVisibility(View.VISIBLE);
            self_business.setVisibility(View.GONE);

        }else if(Employement_Type.equals("2"))
        {
            individual.setVisibility(View.GONE);
            self_business.setVisibility(View.GONE);

        }else if(Employement_Type.equals("3"))
        {
            individual.setVisibility(View.GONE);
            self_business.setVisibility(View.VISIBLE);
        }

        if(IS_CO_Applicant_Id.equals("1"))
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


        if(loan_type_id.equals("1"))
        {
            identified_yes_or_no.setVisibility(View.VISIBLE);
             age_of_Property_lap.setVisibility(View.VISIBLE);
            plot_construction_loan.setVisibility(View.GONE);

            plot_loan.setVisibility(View.GONE);
        }else if(loan_type_id.equals("2"))
        {
            identified_yes_or_no.setVisibility(View.VISIBLE);
            age_of_Property_lap.setVisibility(View.GONE);
            plot_construction_loan.setVisibility(View.GONE);
            plot_loan.setVisibility(View.GONE);

         }else if(loan_type_id.equals("3") || loan_type_id.equals("8"))
             {
                 identified_yes_or_no.setVisibility(View.GONE);
                 plot_construction_loan.setVisibility(View.VISIBLE);
                 plot_loan.setVisibility(View.GONE);

        }else if(loan_type_id.equals("4"))
        {
            identified_yes_or_no.setVisibility(View.GONE);
            plot_construction_loan.setVisibility(View.GONE);
            plot_loan.setVisibility(View.VISIBLE);
        }
        else if(loan_type_id.equals("5") || loan_type_id.equals("10"))
        {
            identified_yes_or_no.setVisibility(View.GONE);
            plot_construction_loan.setVisibility(View.GONE);
            plot_loan.setVisibility(View.GONE);
            property_field_bt_TOP_up.setVisibility(View.VISIBLE);

            improvment_extention.setVisibility(View.GONE);
        }
        if(loan_type_id.equals("6") || loan_type_id.equals("9") )
        {
            identified_yes_or_no.setVisibility(View.GONE);
            plot_construction_loan.setVisibility(View.GONE);
            plot_loan.setVisibility(View.GONE);
            property_field_bt_TOP_up.setVisibility(View.GONE);
            improvment_extention.setVisibility(View.VISIBLE);
        }

    }

    private void UISCREENS()
    {

        spinner_res_proof = (Spinner) findViewById(R.id.spinner_res_proof);

        spinn_gst_other = (Spinner) findViewById(R.id.spinn_gst_other);
        spinn_other_income = (Spinner) findViewById(R.id.spinn_other_income);
        business_registration_spinner = (Spinner) findViewById(R.id.business_registration_spinner);
        Educatio_qualification_Sppinner = (Spinner) findViewById(R.id.Educatio_qualification_Sppinner);

        having_bank_account = (Spinner) findViewById(R.id.having_bank_account);
        permanent_res_type_spinner = (Spinner) findViewById(R.id.permanent_res_type_spinner);


        business_registration_txt = (AppCompatTextView) findViewById(R.id.business_registration_txt);
        business_registration_txt1 = (AppCompatTextView) findViewById(R.id.business_registration_txt1);
        avg_bank_balence_txt = (AppCompatTextView) findViewById(R.id.avg_bank_balence_txt);
        avg_bank_balence_txt1 = (AppCompatTextView) findViewById(R.id.avg_bank_balence_txt1);
        res_proof = (AppCompatTextView) findViewById(R.id.res_proof);
        res_proof1 = (AppCompatTextView) findViewById(R.id.res_proof1);

        other_income_txt = (AppCompatTextView) findViewById(R.id.other_income_txt);
        other_income_txt1 = (AppCompatTextView) findViewById(R.id.other_income_txt1);
        other_incom_amt_txt = (AppCompatTextView) findViewById(R.id.other_incom_amt_txt);
        other_incom_amt_txt1 = (AppCompatTextView) findViewById(R.id.other_incom_amt_txt1);
        is_gst_reflect_txt = (AppCompatTextView) findViewById(R.id.is_gst_reflect_txt);
        is_gst_reflect_txt1 = (AppCompatTextView) findViewById(R.id.is_gst_reflect_txt1);

        /*,purchased_by_bank1,purchased_by_bank2,purchased_by_gst,purchased_by_gst1,
            bank_credit1_txt,bank_credit1_txt1;*/

        purchased_by_bank1 = (AppCompatTextView) findViewById(R.id.purchased_by_bank1);
        purchased_by_bank2 = (AppCompatTextView) findViewById(R.id.purchased_by_bank2);
        purchased_by_gst = (AppCompatTextView) findViewById(R.id.purchased_by_gst);
        purchased_by_gst1 = (AppCompatTextView) findViewById(R.id.purchased_by_gst1);
        gst_sales1_txt = (AppCompatTextView) findViewById(R.id.gst_sales1_txt);
        gst_sales1_txt1 = (AppCompatTextView) findViewById(R.id.gst_sales1_txt1);
        bank_credit1_txt = (AppCompatTextView) findViewById(R.id.bank_credit1_txt);
        bank_credit1_txt1 = (AppCompatTextView) findViewById(R.id.bank_credit1_txt1);


        other_income_edite_txt = (AppCompatEditText) findViewById(R.id.other_income_edite_txt);

        purchased_by_bank_edit_txt = (AppCompatEditText) findViewById(R.id.purchased_by_bank_edit_txt);
        purchased_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.purchased_by_GStbill_edit_txt);
        sales_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.sales_by_GStbill_edit_txt);
        bank_cridit_by_edtxt = (AppCompatEditText) findViewById(R.id.bank_cridit_by_edtxt);
        business_ref_name_edt_txt = (AppCompatEditText) findViewById(R.id.business_ref_name_edt_txt);

        co_business_ref_name_edt_txt = (AppCompatEditText) findViewById(R.id.co_business_ref_name_edt_txt);
        co_purchased_by_bank_edit_txt = (AppCompatEditText) findViewById(R.id.co_purchased_by_bank_edit_txt);
        co_purchased_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.co_purchased_by_GStbill_edit_txt);
        co_sales_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.co_sales_by_GStbill_edit_txt);
        co_bank_cridit_by_edtxt = (AppCompatEditText) findViewById(R.id.co_bank_cridit_by_edtxt);
        co_Avg_monthly_income = (AppCompatEditText) findViewById(R.id.co_Avg_monthly_income);
        Avg_monthly_income = (AppCompatEditText) findViewById(R.id.Avg_monthly_income);
        co_other_income_edite_txt = (AppCompatEditText) findViewById(R.id.co_other_income_edite_txt);

        permanent_res_pincode_edt_txt = (AppCompatEditText) findViewById(R.id.permanent_res_pincode_edt_txt);

        lead_Elegibility_Bank = (AppCompatButton) findViewById(R.id.lead_Elegibility_Bank);

        other_income_ifany = (LinearLayout) findViewById(R.id.other_income_ifany);
        co_other_income_ifany = (LinearLayout) findViewById(R.id.co_other_income_ifany);

        co_applicant_pl_co_applicant = (LinearLayout) findViewById(R.id.co_applicant_pl_co_applicant);

        bl_co_eligibility_salaried = (LinearLayout) findViewById(R.id.bl_co_eligibility_salaried);
        bl_co_eligibility_self = (LinearLayout) findViewById(R.id.bl_co_eligibility_self);

        individual = (LinearLayout) findViewById(R.id.individual);
        co_individual = (LinearLayout) findViewById(R.id.co_individual);
        self_business = (LinearLayout) findViewById(R.id.self_business);
        co_self_business = (LinearLayout) findViewById(R.id.co_self_business);
        residence_rented = (LinearLayout) findViewById(R.id.residence_rented);

        app_eligibility_salaried_hl = (LinearLayout) findViewById(R.id.app_eligibility_salaried_hl);
        app_eligibility_self_hl = (LinearLayout) findViewById(R.id.app_eligibility_self_hl);
        age_of_Property_lap = (LinearLayout) findViewById(R.id.age_of_Property_lap);

        plot_construction_loan = (LinearLayout) findViewById(R.id.plot_construction_loan);
        identified_yes_or_no = (LinearLayout) findViewById(R.id.identified_yes_or_no);




        full_addres_of_relative = (LinearLayout) findViewById(R.id.full_addres_of_relative);
        permanent_res_type_ly = (LinearLayout) findViewById(R.id.permanent_res_type_ly);
        rent_paid_for_house_ly = (LinearLayout) findViewById(R.id.rent_paid_for_house_ly);
       // rented = (LinearLayout) findViewById(R.id.rented);
        pl_co_app_cmp_name = (LinearLayout) findViewById(R.id.pl_co_app_cmp_name);
        pl_co_cmp_des_ = (LinearLayout) findViewById(R.id.pl_co_cmp_des_);
        co_applicant_pl_co_applicant = (LinearLayout) findViewById(R.id.co_applicant_pl_co_applicant);
        permanent_residence_pincode_ly = (LinearLayout) findViewById(R.id.permanent_residence_pincode_ly);
        permanent_res_area = (LinearLayout) findViewById(R.id.permanent_res_area);
        plot_loan = (LinearLayout) findViewById(R.id.plot_loan);
        improvment_extention = (LinearLayout) findViewById(R.id.improvment_extention);

        ///co Applicnt
        pl_co_self_has_pan_card_spnr = (Spinner) findViewById(R.id.pl_co_self_has_pan_card_spnr);
        pl_co_app_spinner_employe_id = (Spinner) findViewById(R.id.pl_co_app_spinner_employe_id);
        pl_co_app_spinner_cmp_type = (Spinner) findViewById(R.id.pl_co_app_spinner_cmp_type);

        pl_co_app_designation_in_company = (AppCompatEditText) findViewById(R.id.pl_co_app_designation_in_company);
        pl_co_app_employee_type_spnr = (Spinner) findViewById(R.id.pl_co_app_employee_type_spnr);
             pl_co_app_epf_spinner = (Spinner) findViewById(R.id.pl_co_app_epf_spinner);
        pl_co_app_maried_res_spinner = (Spinner) findViewById(R.id.pl_co_app_maried_res_spinner);
        pl_co_app_spinn_other_income = (Spinner) findViewById(R.id.pl_co_app_spinn_other_income);
        pl_co_App_spinn_is_gst_reflect = (Spinner) findViewById(R.id.pl_co_App_spinn_is_gst_reflect);
        pl_co_app_no_of_dependent_edt_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_no_of_dependent_edt_txt);
        pl_co_App_education_qualification_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_App_education_qualification_edit_txt);
        pl_co_app_emi_amount_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_emi_amount_edit_txt);
        co_app_is_other_income = (LinearLayout) findViewById(R.id.co_app_is_other_income);


        //self
        co_having_bank_account = (Spinner) findViewById(R.id.co_having_bank_account);
        co_Educatio_qualification_Sppinner = (Spinner) findViewById(R.id.co_Educatio_qualification_Sppinner);
        co_business_registration_spinner = (Spinner) findViewById(R.id.co_business_registration_spinner);
        co_spinner_res_proof = (Spinner) findViewById(R.id.co_spinner_res_proof);
        co_spinn_other_income = (Spinner) findViewById(R.id.co_spinn_other_income);
        co_spinn_gst_other = (Spinner) findViewById(R.id.co_spinn_gst_other);



        pl_co_app_company_name_edtxt = (AppCompatEditText) findViewById(R.id.pl_co_app_company_name_edtxt);
        pl_co_app_designation_in_company = (AppCompatEditText) findViewById(R.id.pl_co_app_designation_in_company);
        pl_co_App_no_of_emp_edtxt = (AppCompatEditText) findViewById(R.id.pl_co_App_no_of_emp_edtxt);
        pl_co_app_no_of_dependent_edt_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_no_of_dependent_edt_txt);
        pl_co_App_education_qualification_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_App_education_qualification_edit_txt);
        pl_co_app_emi_amount_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_emi_amount_edit_txt);
        pl_co_App_other_incom_amt_edtxt = (AppCompatEditText) findViewById(R.id.pl_co_App_other_incom_amt_edtxt);
        pl_co_App_other_incom_amt_edtxt.addTextChangedListener(new NumberTextWatcher(pl_co_App_other_incom_amt_edtxt));





        ///
        propert_statues_spinner = (Spinner) findViewById(R.id.propert_statues_spinner);
        transaction_type_spi = (Spinner) findViewById(R.id.transaction_type_spi);
        //plot_area_edit_txt,build_up_area_edit_txt,carpet_area_edit_txt;


        //property
        plot_area_edit_txt = (AppCompatEditText) findViewById(R.id.plot_area_edit_txt);
        build_up_area_edit_txt = (AppCompatEditText) findViewById(R.id.build_up_area_edit_txt);
        property_price_edt_txt = (AppCompatEditText) findViewById(R.id.property_price_edt_txt);
        age_Of_Property_Edit_Text = (AppCompatEditText) findViewById(R.id.age_Of_Property_Edit_Text);

        property_price_edt_txt.addTextChangedListener(new NumberTextWatcher(property_price_edt_txt));



        plot_area_edit_txt_improment = (AppCompatEditText) findViewById(R.id.plot_area_edit_txt_improment);
        ploat_area_edit_txt_improment = (AppCompatEditText) findViewById(R.id.ploat_area_edit_txt_improment);
        cost_estimation_edit_txt_improment = (AppCompatEditText) findViewById(R.id.cost_estimation_edit_txt_improment);
        property_ownership_edt_txt_improment = (AppCompatEditText) findViewById(R.id.property_ownership_edt_txt_improment);


        cost_of_plot_edite_text = (AppCompatEditText) findViewById(R.id.cost_of_plot_edite_text);
        plot_area_edit_txt_plot_construction = (AppCompatEditText) findViewById(R.id.plot_area_edit_txt_plot_construction);
        build_up_area_edit_txt_plot_construction = (AppCompatEditText) findViewById(R.id.build_up_area_edit_txt_plot_construction);
        plot_property_price_edt_txt = (AppCompatEditText) findViewById(R.id.plot_property_price_edt_txt);

        plot_property_price_edt_txt.addTextChangedListener(new NumberTextWatcher(plot_property_price_edt_txt));

        plot_area_edit_txt_plot_ = (AppCompatEditText) findViewById(R.id.plot_area_edit_txt_plot_);


//bt
      /*  plot_area_edit_txt_bt,ploat_area_edit_txt,property_price_edt_txt_bt,Existing_bank_edit_txt,
                Loan_varient_edit_txt,loan_amount_edit_txt,original_tenure_edit_txt,remaining_tenure_edit_txt,
                interest_rate_edit_txt;*/
        plot_area_edit_txt_bt = (AppCompatEditText) findViewById(R.id.plot_area_edit_txt_bt);
        Existing_bank_edit_txt = (AppCompatEditText) findViewById(R.id.Existing_bank_edit_txt);
        build_up_area_edit_txt_bt = (AppCompatEditText) findViewById(R.id.build_up_area_edit_txt_bt);

        property_price_edt_txt_bt = (AppCompatEditText) findViewById(R.id.property_price_edt_txt_bt);
        property_price_edt_txt_bt.addTextChangedListener(new NumberTextWatcher(property_price_edt_txt_bt));

        Loan_varient_edit_txt = (AppCompatEditText) findViewById(R.id.Loan_varient_edit_txt);
        loan_amount_edit_txt = (AppCompatEditText) findViewById(R.id.loan_amount_edit_txt);
        original_tenure_edit_txt = (AppCompatEditText) findViewById(R.id.original_tenure_edit_txt);
        remaining_tenure_edit_txt = (AppCompatEditText) findViewById(R.id.remaining_tenure_edit_txt);
        interest_rate_edit_txt = (AppCompatEditText) findViewById(R.id.interest_rate_edit_txt);

        property_field_bt_TOP_up = (LinearLayout) findViewById(R.id.property_field_bt_TOP_up);



        cost_of_plot_edite_text = (AppCompatEditText) findViewById(R.id.cost_of_plot_edite_text);
        plot_area_edit_txt_plot_construction = (AppCompatEditText) findViewById(R.id.plot_area_edit_txt_plot_construction);
        build_up_area_edit_txt_plot_construction = (AppCompatEditText) findViewById(R.id.build_up_area_edit_txt_plot_construction);

        construction_statues_spinner = (Spinner) findViewById(R.id.construction_statues_spinner);

        //applicant Salaried
        spinner_cmp_type = (Spinner) findViewById(R.id.spinner_cmp_type);
        has_pan_card_spnr = (Spinner) findViewById(R.id.has_pan_card_spnr);
        spinner_employe_id = (Spinner) findViewById(R.id.spinner_employe_id);
        spinner_cmp_type = (Spinner) findViewById(R.id.spinner_cmp_type);
        employee_type_spnr = (Spinner) findViewById(R.id.employee_type_spnr);
        epf_spinner = (Spinner) findViewById(R.id.epf_spinner);
        current_res_spinner = (Spinner) findViewById(R.id.current_res_spinner);
        maried_res_spinner = (Spinner) findViewById(R.id.maried_res_spinner);
        applicant_salaried_spinn_other_income = (Spinner) findViewById(R.id.applicant_salaried_spinn_other_income);
        spinn_is_gst_reflect = (Spinner) findViewById(R.id.spinn_is_gst_reflect);
        own_hose_relative_spinner = (Spinner) findViewById(R.id.own_hose_relative_spinner);
        permanent_res_type_res_spinner = (Spinner) findViewById(R.id.permanent_res_type_res_spinner);
        permanent_residence_spinn_area = (Spinner) findViewById(R.id.permanent_residence_spinn_area);

        hl_eligi_app_cmp_name = (LinearLayout) findViewById(R.id.hl_eligi_app_cmp_name);
        hl_eligibility_cmp_des_ = (LinearLayout) findViewById(R.id.hl_eligibility_cmp_des_);
        is_other_income = (LinearLayout) findViewById(R.id.is_other_income);
        rented_applicant_salaried_hl = (LinearLayout) findViewById(R.id.rented_applicant_salaried_hl);
        property_field = (LinearLayout) findViewById(R.id.property_field);

        company_name_edtxt = (AppCompatEditText) findViewById(R.id.company_name_edtxt);
        no_of_emp_edtxt = (AppCompatEditText) findViewById(R.id.no_of_emp_edtxt);
        no_of_dependent_edt_txt = (AppCompatEditText) findViewById(R.id.no_of_dependent_edt_txt);
        own_house_blood_address_edt_txt = (AppCompatEditText) findViewById(R.id.own_house_blood_address_edt_txt);
        education_qualification_edit_txt = (AppCompatEditText) findViewById(R.id.education_qualification_edit_txt);
        rent_paid_for_house_edit_txt = (AppCompatEditText) findViewById(R.id.rent_paid_for_house_edit_txt);
        other_incom_amt_edtxt = (AppCompatEditText) findViewById(R.id.other_incom_amt_edtxt);
        other_incom_amt_edtxt.addTextChangedListener(new NumberTextWatcher(other_incom_amt_edtxt));

        designation_in_company = (AppCompatEditText) findViewById(R.id.designation_in_company);
        emi_amount_edit_txt = (AppCompatEditText) findViewById(R.id.emi_amount_edit_txt);
        permanent_residence_auto = (AppCompatAutoCompleteTextView) findViewById(R.id.permanent_residence_auto);

    }

    private void Font() {

        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");

        is_gst_reflect_txt.setTypeface(font);

    }
    private void Click ()
    {

        permanent_residence_auto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("hi","hi11");
                String permanent_pincode = permanent_residence_auto.getText().toString();

                if(permanent_pincode.length()==2){
                    GET_Pincode1(permanent_pincode);
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

/*
        lead_Elegibility_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Eligibility_HL_New.this, Credite_report_details.class);
                startActivity(intent);
                finish();
            }
        });*/

        lead_Elegibility_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(loan_type_id.equals("1") || loan_type_id.equals("2"))
                {
                    if(transaction_type_spi_id.equals("0"))
                    {
                        Toast.makeText(mCon, "Please Select Transaction Type", Toast.LENGTH_SHORT).show();

                    }else {
                        if(propert_statues_spinner_id.equals("0"))
                        {
                            Toast.makeText(mCon, "Please Select Property Status", Toast.LENGTH_SHORT).show();

                        }else {


                            if(loan_type_id.equals("1"))
                            {

                                if (!Validate_age_Of_Property_Edit_Text()) {
                                    return;
                                }
                                if (!Validate_plot_area_edit_txt()) {
                                    return;
                                }
                                if (!Validate_build_up_area_edit_txt()) {
                                    return;
                                }
                                if (!Validate_carpet_area_edit_txt()) {
                                    return;
                                }

                                if(salary_type.equals("1"))
                                {
                                    applicant_elig_salaried();
                                }else if(salary_type.equals("2"))
                                {
                                    applicant_elig_Self();
                                }

                            }else  if(loan_type_id.equals("2"))
                            {
                                if (!Validate_plot_area_edit_txt()) {
                                    return;
                                }
                                if (!Validate_build_up_area_edit_txt()) {
                                    return;
                                }
                                if (!Validate_carpet_area_edit_txt()) {
                                    return;
                                }

                                if(salary_type.equals("1"))
                                {
                                    applicant_elig_salaried();
                                }else if(salary_type.equals("2"))
                                {
                                    applicant_elig_Self();
                                }
                            }
                        }
                    }

                }else {
                    if(loan_type_id.equals("3")|| loan_type_id.equals("8"))
                    {

                        if (!Validate_cost_of_plot_edite_text()) {
                            return;
                        }
                        if (!Validate_plot_area_edit_txt_plot_construction()) {
                            return;
                        }
                        if (!Validate_build_up_area_edit_txt_plot_construction()) {
                            return;
                        }

                        if(construction_statues_spinner_id.equals("0"))
                        {
                            Toast.makeText(mCon, "Please Construction Statues ?", Toast.LENGTH_SHORT).show();

                        }else {

                            if(salary_type.equals("1"))
                            {
                                applicant_elig_salaried();
                            }else if(salary_type.equals("2"))
                            {
                                applicant_elig_Self();
                            }
                        }


                    } else if(loan_type_id.equals("4"))
                    {

                        if (!Validate_plot_property_price_edt_txt()) {
                            return;
                        }
                        if (!Validate_plot_area_edit_txt_plot_()) {
                            return;
                        }
                        if(salary_type.equals("1"))
                        {
                            applicant_elig_salaried();
                        }else if(salary_type.equals("2"))
                        {
                            applicant_elig_Self();
                        }

                    }else if(loan_type_id.equals("5"))
                    {
                        if (!Validate_plot_area_edit_txt_bt()) {
                            return;
                        }
                        if (!Validate_build_up_area_edit_txt_bt()) {
                            return;
                        }
                        if (!Validate_property_price_edt_txt_bt()) {
                            return;
                        }

                        if (!Validate_Existing_bank_edit_txt()) {
                            return;
                        }
                        if (!Validate_Loan_varient_edit_txt()) {
                            return;
                        }
                        if (!Validate_loan_amount_edit_txt()) {
                            return;
                        }
                        if (!Validate_original_tenure_edit_txt()) {
                            return;
                        }
                        if (!Validate_remaining_tenure_edit_txt()) {
                            return;
                        }
                        if (!Validate_interest_rate_edit_txt()) {
                            return;
                        }

                    }else if(loan_type_id.equals("6") || loan_type_id.equals("9") )
                    {
                        if (!Validate_plot_area_edit_txt_improment()) {
                            return;
                        }

                        if (!Validate_ploat_area_edit_txt_improment()) {
                            return;
                        }
                        if (!Validate_ploat_area_edit_txt_improment()) {
                            return;
                        }
                        if (!Validate_cost_estimation_edit_txt_improment()) {
                            return;
                        }

                    }


                }
//////
            }
        });

    }

    private void applicant_elig_salaried()
    {
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
    }

    private void validate1()
    {
        if(Emp_type_id.equals("0"))
        {
            Toast.makeText(mCon, "Please Select Type of Employee", Toast.LENGTH_SHORT).show();
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

    private void owned_house_Validation()
    {
        if(maried_res_spinner_id.equals("0"))
        {
            Toast.makeText(mCon, "Select Marital Status", Toast.LENGTH_SHORT).show();

        }else {
            if (!valiat_no_of_dependent()) {
                return;
            }

            if (!valiat_education_qualification()) {
                return;
            }
            if (!Validate_emi_amount()) {
                return;
            }

            other_varidation();
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

        }else if(Permanent_Resi_id.equals("1"))
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

            if (!valiat_education_qualification()) {
                return;
            }
            if (!Validate_emi_amount()) {
                return;
            }

            other_varidation();
        }
    }
    private void other_varidation()
    {
        if(App_salaried_Other_income_id.equals("0"))
        {
            Toast.makeText(mCon, "Please Select other income", Toast.LENGTH_SHORT).show();

        }else if(App_salaried_Other_income_id.equals("4"))
        {


            if(IS_CO_Applicant_Id.equals("1"))
            {
                CO_APPLICANT_VALIDATION();
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
                if(IS_CO_Applicant_Id.equals("1"))
                {
                    CO_APPLICANT_VALIDATION();
                }else
                {
                    lead_Eligibility();
                }

            }
        }
    }

    private void applicant_elig_Self()
    {
        if(Employement_Type.equals("1"))
        {
            if(having_bank_Id.equals("0"))
            {
                Toast.makeText(context,"Please Select Bank A/C Type",Toast.LENGTH_SHORT).show();
            }else
            {
                if(Educational_Id.equals("0"))
                {
                    Toast.makeText(context,"Please Select Educational Qualification",Toast.LENGTH_SHORT).show();

                }else
                {
                    if (!Sales_Bank_ref_Name()) {
                        return;
                    }

                    if(Rsidence_Type.equals("2"))
                    {
                        rented();
                    }else
                    {
                        validation();
                    }


                }

            }

        }else if(Employement_Type.equals("2"))
        {
            if(Spinner_res_proof_Id.equals("0"))
            {
                Toast.makeText(context,"Please Select Current Residence Addres Proof",Toast.LENGTH_SHORT).show();
            }else
            {
                if(Rsidence_Type.equals("2"))
                {
                    rented();
                }else
                {
                    validation();
                }
            }
        }else if(Employement_Type.equals("3"))
        {
            if(Business_registration_Id.equals("0"))
            {
                Toast.makeText(context,"Please Select Business Registration",Toast.LENGTH_SHORT).show();
            }else
            {

                if (!Purchased_by_Bank()) {
                    return;
                }
                if (!Purchased_by_Gst_bill()) {
                    return;
                }
                if (!sales_by_Gst_bill()) {
                    return;
                }

                if (!Sales_bank_cridit_()) {
                    return;
                }

                if (!Average_monthly_income()) {
                    return;
                }
                if(Rsidence_Type.equals("2"))
                {
                    rented();
                }else
                {
                    validation();
                }

            }
        }
    }

    private void rented(){
        if(Spinner_res_proof_Id.equals("0"))
        {
            Toast.makeText(context,"Please Select Current Residence Addres Proof",Toast.LENGTH_SHORT).show();
        }else
        {
            if (!Validation_Permanent_Resi()) {
                return;
            }
            if(permanent_res_Id.equals("0"))
            {
                Toast.makeText(context,"Please Select  Residence Type",Toast.LENGTH_SHORT).show();
            }else
            {
                validation();
            }


        }
    }
    private void validation()
    {
        if(Spinner_res_proof_Id.equals("0"))
        {
            Toast.makeText(context,"Please Select Current Residence Addres Proof",Toast.LENGTH_SHORT).show();
        }else
        {
                if(Other_income_Id.equals("0"))
                {
                    Toast.makeText(context,"Please Select Other Income",Toast.LENGTH_SHORT).show();

                }else if(Other_income_Id.equals("4"))
                {

                }else
                {

                    if (!other_income_amount()) {
                        return;
                    }

                    if(Gstreflect_Id.equals("0"))
                    {
                        Toast.makeText(context,"Please Select ITR Reflected",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {


                       CO_APPLICANT_VALIDATION();
                        //
                    }
                }
        }

    }



        private void CO_APPLICANT_VALIDATION()
        {
            if(IS_CO_Applicant_Id.equals("1")) {

                if(IS_CO_Salried_Self.equals("1"))
                {
                    salaried();
                }else
                {
                    co_self();
                }

            }else
            {
              lead_Eligibility();
            }
        }


        private void salaried()
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

                                        if (!Validate_pl_Co_education_qualification_edit_txt()) {
                                            return;
                                        }
                                        if (!pl_co_App_Validate_emi_amount()) {
                                            return;
                                        }

                                        Co_App_other_varidation();
                                    }


                                }
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

        }else if(pl_co_app_Other_income_id.equals("4"))
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

    ///end salarird eligibility
        private void co_self()
        {

            Log.e("the self","self_co_is called");
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

    private boolean Purchased_by_Bank(){

        if (purchased_by_bank_edit_txt.getText().toString().isEmpty()) {
            purchased_by_bank_edit_txt.setError(getText(R.string.err_curent));
            purchased_by_bank_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Purchased_by_Gst_bill(){
        if (purchased_by_GStbill_edit_txt.getText().toString().isEmpty()) {
            purchased_by_GStbill_edit_txt.setError(getText(R.string.err_curent));
            purchased_by_GStbill_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validation_Permanent_Resi(){
        if (permanent_res_pincode_edt_txt.getText().toString().isEmpty()) {
            permanent_res_pincode_edt_txt.setError(getText(R.string.err_curent));
            permanent_res_pincode_edt_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }


    private boolean sales_by_Gst_bill(){
        if (sales_by_GStbill_edit_txt.getText().toString().isEmpty()) {
            sales_by_GStbill_edit_txt.setError(getText(R.string.err_curent));
            sales_by_GStbill_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }


    private boolean Sales_bank_cridit_(){
        if (bank_cridit_by_edtxt.getText().toString().isEmpty()) {
            bank_cridit_by_edtxt.setError(getText(R.string.err_curent));
            bank_cridit_by_edtxt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }

    private boolean Sales_Bank_ref_Name(){
        if (business_ref_name_edt_txt.getText().toString().isEmpty()) {
            business_ref_name_edt_txt.setError(getText(R.string.err_curent));
            business_ref_name_edt_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

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
            co_other_income_edite_txt.setError(getText(R.string.err_curent));
            co_other_income_edite_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }



    private boolean Average_monthly_income(){
        if (Avg_monthly_income.getText().toString().isEmpty()) {
            Avg_monthly_income.setError(getText(R.string.err_curent));
            Avg_monthly_income.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }

    private boolean residence_Business_reference_name(){
        if (Business_refernce_name.getText().toString().isEmpty()) {
            Business_refernce_name.setError(getText(R.string.error_bus_ref_name));
            Business_refernce_name.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);Business_reference_mobile

        }

        return true;
    }

    private boolean residence_Business_reference_phone(){
        if (Business_reference_mobile.getText().toString().isEmpty()) {
            Business_reference_mobile.setError(getText(R.string.error_bus_ref_mobile));
            Business_reference_mobile.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean other_income_amount(){
        if (other_income_edite_txt.getText().toString().isEmpty()) {
            other_income_edite_txt.setError(getText(R.string.err_curent));
            other_income_edite_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    ///co Salaried



    private boolean Co_App_Validate_Company_Name(){
        if (pl_co_app_company_name_edtxt.getText().toString().trim().isEmpty()) {
            pl_co_app_company_name_edtxt.setError(getText(R.string.err_curent));
            pl_co_app_company_name_edtxt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Co_App_Validate_Designation_in_company(){
        if (pl_co_app_designation_in_company.getText().toString().trim().isEmpty()) {
            pl_co_app_designation_in_company.setError(getText(R.string.err_curent));
            pl_co_app_designation_in_company.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Pl_Co_Validate_No_of_Employee(){
        if (pl_co_App_no_of_emp_edtxt.getText().toString().trim().isEmpty()) {
            pl_co_App_no_of_emp_edtxt.setError(getText(R.string.err_curent));
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

    private boolean pl_co_App_Validate_emi_amount(){
        if (pl_co_app_emi_amount_edit_txt.getText().toString().trim().isEmpty()) {
            pl_co_app_emi_amount_edit_txt.setError(getText(R.string.err_curent));
            pl_co_app_emi_amount_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean pl_Co_App_Validate_other_income(){
        if (pl_co_App_other_incom_amt_edtxt.getText().toString().trim().isEmpty()) {
            pl_co_App_other_incom_amt_edtxt.setError(getText(R.string.err_curent));
            pl_co_App_other_incom_amt_edtxt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    ///Applicant eligibility validation



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

    private boolean Validate_plot_area_edit_txt(){
        if (plot_area_edit_txt.getText().toString().trim().isEmpty()) {
            plot_area_edit_txt.setError(getText(R.string.err_curent));
            plot_area_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_build_up_area_edit_txt(){
        if (build_up_area_edit_txt.getText().toString().trim().isEmpty()) {
            build_up_area_edit_txt.setError(getText(R.string.err_curent));
            build_up_area_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_carpet_area_edit_txt(){
        if (property_price_edt_txt.getText().toString().trim().isEmpty()) {
            property_price_edt_txt.setError(getText(R.string.err_curent));
            property_price_edt_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_age_Of_Property_Edit_Text(){
        if (age_Of_Property_Edit_Text.getText().toString().trim().isEmpty()) {
            age_Of_Property_Edit_Text.setError(getText(R.string.err_curent));
            age_Of_Property_Edit_Text.requestFocus();
            return false;
        } else {

        }

        return true;
    }

 /*
       cost_of_plot_edite_text,plot_area_edit_txt_plot_construction,build_up_area_edit_txt_plot_construction,
            plot_construction_property_price_edt_txt,plot_area_edit_txt_plot_;*/

    private boolean Validate_cost_of_plot_edite_text(){
        if (cost_of_plot_edite_text.getText().toString().trim().isEmpty()) {
            cost_of_plot_edite_text.setError(getText(R.string.err_curent));
            cost_of_plot_edite_text.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_plot_area_edit_txt_plot_construction(){
        if (plot_area_edit_txt_plot_construction.getText().toString().trim().isEmpty()) {
            plot_area_edit_txt_plot_construction.setError(getText(R.string.err_curent));
            plot_area_edit_txt_plot_construction.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_build_up_area_edit_txt_plot_construction(){
        if (build_up_area_edit_txt_plot_construction.getText().toString().trim().isEmpty()) {
            build_up_area_edit_txt_plot_construction.setError(getText(R.string.err_curent));
            build_up_area_edit_txt_plot_construction.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_plot_property_price_edt_txt(){
        if (plot_property_price_edt_txt.getText().toString().trim().isEmpty()) {
            plot_property_price_edt_txt.setError(getText(R.string.err_curent));
            plot_property_price_edt_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_plot_area_edit_txt_plot_(){
        if (plot_area_edit_txt_plot_.getText().toString().trim().isEmpty()) {
            plot_area_edit_txt_plot_.setError(getText(R.string.err_curent));
            plot_area_edit_txt_plot_.requestFocus();
            return false;
        } else {

        }

        return true;
    }



    private boolean Validate_plot_area_edit_txt_bt(){
        if (plot_area_edit_txt_bt.getText().toString().trim().isEmpty()) {
            plot_area_edit_txt_bt.setError(getText(R.string.err_curent));
            plot_area_edit_txt_bt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_build_up_area_edit_txt_bt(){
        if (build_up_area_edit_txt_bt.getText().toString().trim().isEmpty()) {
            build_up_area_edit_txt_bt.setError(getText(R.string.err_curent));
            build_up_area_edit_txt_bt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_property_price_edt_txt_bt(){
        if (property_price_edt_txt_bt.getText().toString().trim().isEmpty()) {
            property_price_edt_txt_bt.setError(getText(R.string.err_curent));
            property_price_edt_txt_bt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_Existing_bank_edit_txt(){
        if (Existing_bank_edit_txt.getText().toString().trim().isEmpty()) {
            Existing_bank_edit_txt.setError(getText(R.string.err_curent));
            Existing_bank_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_Loan_varient_edit_txt(){
        if (Loan_varient_edit_txt.getText().toString().trim().isEmpty()) {
            Loan_varient_edit_txt.setError(getText(R.string.err_curent));
            Loan_varient_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_loan_amount_edit_txt(){
        if (loan_amount_edit_txt.getText().toString().trim().isEmpty()) {
            loan_amount_edit_txt.setError(getText(R.string.err_curent));
            loan_amount_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_original_tenure_edit_txt(){
        if (original_tenure_edit_txt.getText().toString().trim().isEmpty()) {
            original_tenure_edit_txt.setError(getText(R.string.err_curent));
            original_tenure_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_remaining_tenure_edit_txt(){
        if (remaining_tenure_edit_txt.getText().toString().trim().isEmpty()) {
            remaining_tenure_edit_txt.setError(getText(R.string.err_curent));
            remaining_tenure_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Validate_interest_rate_edit_txt(){
        if (interest_rate_edit_txt.getText().toString().trim().isEmpty()) {
            interest_rate_edit_txt.setError(getText(R.string.err_curent));
            interest_rate_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }



    private boolean Validate_plot_area_edit_txt_improment(){
        if (plot_area_edit_txt_improment.getText().toString().trim().isEmpty()) {
            plot_area_edit_txt_improment.setError(getText(R.string.err_curent));
            plot_area_edit_txt_improment.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_ploat_area_edit_txt_improment(){
        if (ploat_area_edit_txt_improment.getText().toString().trim().isEmpty()) {
            ploat_area_edit_txt_improment.setError(getText(R.string.err_curent));
            ploat_area_edit_txt_improment.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_cost_estimation_edit_txt_improment(){
        if (cost_estimation_edit_txt_improment.getText().toString().trim().isEmpty()) {
            cost_estimation_edit_txt_improment.setError(getText(R.string.err_curent));
            cost_estimation_edit_txt_improment.requestFocus();
            return false;
        } else {

        }

        return true;
    }
    private boolean Validate_property_ownership_edt_txt_improment(){
        if (property_ownership_edt_txt_improment.getText().toString().trim().isEmpty()) {
            property_ownership_edt_txt_improment.setError(getText(R.string.err_curent));
            property_ownership_edt_txt_improment.requestFocus();
            return false;
        } else {

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

                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        try {


                            Current_address_proof =object.getJSONArray("addr_proof_rent");
                            GST_reflected =object.getJSONArray("GST_reflected");
                            other_income =object.getJSONArray("other_income");
                            Business_registration =object.getJSONArray("Business_registration");
                            permanent_adddress =object.getJSONArray("permanent_adddress");
                            Bank_account =object.getJSONArray("Bank_account");
                            Reflected_ITR =object.getJSONArray("Reflected_ITR");
                           // Business_registration =object.getJSONArray("Business_registration");

                            Education_Qualification =object.getJSONArray("Education Qualification");
                            // Business_Proof =object.getJSONArray("Business_Proof");

                            Property_Status =object.getJSONArray("Property Status");
                            transaction_type =object.getJSONArray("transaction_type");
                            Construction_Status =object.getJSONArray("Construction Status");

                            Other_income =object.getJSONArray("Other_income");
                            epf_deduct =object.getJSONArray("epf_deduct");
                            Marital_Status =object.getJSONArray("Marital_Status");
                            Type_employee =object.getJSONArray("Type_employee");
                            Company_type =object.getJSONArray("Company_type");
                            employee_id_ar =object.getJSONArray("employee_id");
                            have_pan_ar =object.getJSONArray("have_pan");
                            Current_Residence =object.getJSONArray("Current_Residence");
                            relation_own =object.getJSONArray("relation_own");



                            Current_res_proof(Current_address_proof);
                            Bank_account(Bank_account);
                            Other_income(other_income);
                            GST_reflected(Reflected_ITR);
                            Business_registration_fun(Business_registration);
                            Permanent_Adddress_(permanent_adddress);
                            Education_Qualification_(Education_Qualification);


                            //co salried
                            HAVE_PAN_Card(have_pan_ar);
                            Employee_ID_Array(employee_id_ar);
                            Comapany_type(Company_type);
                            Type_of_Employee(Type_employee);
                            Marital_Statues(Marital_Status);
                            Epf_detedcted(epf_deduct);

                            Other_income_f(Other_income);
                            itr_reflect_f(Reflected_ITR);

                            //property
                            hl_Property_Status(Property_Status);
                            hl_transaction_type(transaction_type);
                            plot_construction_transaction_type(Construction_Status);


                            ///applicant salaried APP_Salaried_HAVE_PAN_Card
                            APP_Salaried_Employee_ID_Array(employee_id_ar);
                            APP_Salaried_HAVE_PAN_Card(have_pan_ar);
                            APP_Salaried_Comapany_type(Company_type);
                            APP_Salaried_Type_of_Employee(Type_employee);
                            APP_Salaried_Current_res_Type(Current_Residence);
                            APP_Salaried_Epf_detedcted(epf_deduct);

                            APP_Salaried_Other_income_f(Other_income);
                            APP_Salaried_itr_reflect_f(Reflected_ITR);
                            APP_Salaried_Marital_Statues(Marital_Status);
                            APP_Salaried_Permanent_res_Type(permanent_adddress);
                            APP_Salaried_Own_hous_relative(relation_own);


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

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

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
            spinner_res_proof.setAdapter(CureentAddress_proof_adapter);
            spinner_res_proof.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        Spinner_res_proof_Id = Current_res_proof_ar.getJSONObject(position).getString("id");
                        Spinner_res_proof_Value = Current_res_proof_ar.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_res_proof.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

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

    private void Permanent_Adddress_(final JSONArray Current_res_proof_ar) throws JSONException {
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
            permanent_res_type_spinner.setAdapter(CureentAddress_proof_adapter);
            permanent_res_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        permanent_res_Id = Current_res_proof_ar.getJSONObject(position).getString("id");
                        permanent_res_Value = Current_res_proof_ar.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            permanent_res_type_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

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
            having_bank_account.setAdapter(Provide_gurenter_adapter);
            having_bank_account.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        having_bank_Id = Provide_Guarantor.getJSONObject(position).getString("id");
                        having_bank_Value = Provide_Guarantor.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            having_bank_account.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


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
            spinn_other_income.setAdapter(Other_income_adapter);
            spinn_other_income.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        Other_income_Id = Other_income_ar.getJSONObject(position).getString("id");
                        Other_income_Value = Other_income_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");



                        if(Other_income_Id.equals("0"))
                        {
                            other_income_ifany.setVisibility(View.VISIBLE);

                        }else if(Other_income_Id.equals("4"))
                        {
                            other_income_ifany.setVisibility(View.GONE);
                        }else
                        {
                            other_income_ifany.setVisibility(View.VISIBLE);
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
            spinn_gst_other.setAdapter(Gst_reflect_adapter);
            spinn_gst_other.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        Gstreflect_Id = GST_reflected_ar.getJSONObject(position).getString("id");
                        Gstreflect_Value = GST_reflected_ar.getJSONObject(position).getString("value");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinn_gst_other.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

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
            Educatio_qualification_Sppinner.setAdapter(Gst_reflect_adapter);
            Educatio_qualification_Sppinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            Educatio_qualification_Sppinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });



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
            business_registration_spinner.setAdapter(Business_registration_adapter);
            business_registration_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        Business_registration_Id = Business_registration_ar.getJSONObject(position).getString("id");
                        Business_registration_Value = Business_registration_ar.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            business_registration_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


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







    //CO Applicant sppiner values
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


            pl_co_app_spinner_cmp_type.setAdapter(Company_type_Adapter);
            pl_co_app_spinner_cmp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        pl_co_app_Company_id = Company_type_ar.getJSONObject(position).getString("id");
                        pl_co_app_Company_Value = Company_type_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


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
            pl_co_app_epf_spinner.setAdapter(Epf_detected_Adapter);
            pl_co_app_epf_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        pl_co_app_epf_id = epf_jarray.getJSONObject(position).getString("id");
                        pl_co_app_epf_Value = epf_jarray.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


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


    ///Apllicant Employee salaried
    private void APP_Salaried_HAVE_PAN_Card(final JSONArray has_pancard_ar) throws JSONException {
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

    private void APP_Salaried_Employee_ID_Array(final JSONArray employee_id_ar) throws JSONException {
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

    private void APP_Salaried_Comapany_type(final JSONArray Company_type_ar) throws JSONException {
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

                        if(Company_id.equals("1") || Company_id. equals("2"))
                        {
                            hl_eligi_app_cmp_name.setVisibility(View.GONE);
                            hl_eligibility_cmp_des_.setVisibility(View.GONE);
                        }else
                        {
                            hl_eligi_app_cmp_name.setVisibility(View.VISIBLE);
                            hl_eligibility_cmp_des_.setVisibility(View.VISIBLE);
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


        }

    }

    private void APP_Salaried_Type_of_Employee(final JSONArray Type_employee_ar) throws JSONException {
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

        }

    }

    private void APP_Salaried_Epf_detedcted(final JSONArray epf_jarray) throws JSONException {
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



        }

    }

    private void APP_Salaried_Current_res_Type(final JSONArray Current_Residence_ar) throws JSONException {
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
    private void APP_Salaried_Marital_Statues(final JSONArray Marital_Statues_ar) throws JSONException {
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


        }

    }

    private void APP_Salaried_Other_income_f(final JSONArray Other_income_Ar) throws JSONException {
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



            applicant_salaried_spinn_other_income.setAdapter( Other_income_Adapter);
            applicant_salaried_spinn_other_income.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        App_salaried_Other_income_id = Other_income_Ar.getJSONObject(position).getString("id");
                       // App_salaried_Other_income_Value = Other_income_Ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                        if(App_salaried_Other_income_id.equals("4"))
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




        }

    }

    private void APP_Salaried_itr_reflect_f(final JSONArray gst_reflect_Ar) throws JSONException {
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



        }

    }

    private void APP_Salaried_Own_hous_relative(final JSONArray Own_house_rela_Ar) throws JSONException {
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
    private void APP_Salaried_Permanent_res_Type(final JSONArray Permanent_Resi_ar) throws JSONException {
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
                            rented_applicant_salaried_hl.setVisibility(View.GONE);
                        }else if(Permanent_Resi_id.equals("2"))
                        {
                            rented_applicant_salaried_hl.setVisibility(View.VISIBLE);
                        }else
                        {
                            rented_applicant_salaried_hl.setVisibility(View.GONE);
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

    private void hl_Property_Status(final JSONArray vocaton_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

        Permanent_Resi_SA = new String[vocaton_ar.length()];
        for (int i=0;i<vocaton_ar.length();i++){
            JSONObject J =  vocaton_ar.getJSONObject(i);
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
            propert_statues_spinner.setAdapter(Permanent_Resi_Adapter);
            propert_statues_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        propert_statues_spinner_id = vocaton_ar.getJSONObject(position).getString("id");
                        propert_statues_spinner_value = vocaton_ar.getJSONObject(position).getString("value");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            propert_statues_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void hl_transaction_type(final JSONArray vocaton_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

        Permanent_Resi_SA = new String[vocaton_ar.length()];
        for (int i=0;i<vocaton_ar.length();i++){
            JSONObject J =  vocaton_ar.getJSONObject(i);
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
            transaction_type_spi.setAdapter(Permanent_Resi_Adapter);
            transaction_type_spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        transaction_type_spi_id = vocaton_ar.getJSONObject(position).getString("id");
                        transaction_type_spi_value = vocaton_ar.getJSONObject(position).getString("value");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            transaction_type_spi.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void plot_construction_transaction_type(final JSONArray vocaton_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

        Permanent_Resi_SA = new String[vocaton_ar.length()];
        for (int i=0;i<vocaton_ar.length();i++){
            JSONObject J =  vocaton_ar.getJSONObject(i);
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
            construction_statues_spinner.setAdapter(Permanent_Resi_Adapter);
            construction_statues_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        construction_statues_spinner_id = vocaton_ar.getJSONObject(position).getString("id");
                        construction_statues_spinner_value = vocaton_ar.getJSONObject(position).getString("value");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            construction_statues_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

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

    private void lead_Eligibility() {


        //Property Values
        S_plot_area_edit_txt = plot_area_edit_txt.getText().toString();
        S_build_up_area_edit_txt = build_up_area_edit_txt.getText().toString();
        S_property_price_edt_txt = property_price_edt_txt.getText().toString();
        S_age_Of_Property_Edit_Text = age_Of_Property_Edit_Text.getText().toString();

        S_cost_of_plot_edite_text = cost_of_plot_edite_text.getText().toString();
        S_plot_area_edit_txt_plot_construction = plot_area_edit_txt_plot_construction.getText().toString();
        S_build_up_area_edit_txt_plot_construction = build_up_area_edit_txt_plot_construction.getText().toString();

        S_plot_property_price_edt_txt = plot_property_price_edt_txt.getText().toString();
        S_plot_area_edit_txt_plot_ = plot_area_edit_txt_plot_.getText().toString();




        S_plot_area_edit_txt_bt = plot_area_edit_txt_bt.getText().toString();
        S_build_up_area_edit_txt_bt = build_up_area_edit_txt_bt.getText().toString();
        S_property_price_edt_txt_bt = property_price_edt_txt_bt.getText().toString();
        S_Existing_bank_edit_txt = Existing_bank_edit_txt.getText().toString();
        S_Loan_varient_edit_txt = Loan_varient_edit_txt.getText().toString();
        S_loan_amount_edit_txt = loan_amount_edit_txt.getText().toString();
        S_original_tenure_edit_txt = original_tenure_edit_txt.getText().toString();
        S_remaining_tenure_edit_txt = remaining_tenure_edit_txt.getText().toString();
        S_interest_rate_edit_txt = interest_rate_edit_txt.getText().toString();


        S_plot_area_edit_txt_improment = plot_area_edit_txt_improment.getText().toString();
        S_ploat_area_edit_txt_improment = ploat_area_edit_txt_improment.getText().toString();
        S_cost_estimation_edit_txt_improment = cost_estimation_edit_txt_improment.getText().toString();
        S_property_ownership_edt_txt_improment = property_ownership_edt_txt_improment.getText().toString();
        ///applicant salaried
        S_company_name_edtxt = company_name_edtxt.getText().toString();

        S_Designation_in_current_companny = designation_in_company.getText().toString();
        S_no_of_dependent_edt_txt = no_of_dependent_edt_txt.getText().toString();
        S_education_qualification_edit_txt = education_qualification_edit_txt.getText().toString();
        S_emi_amount_edit_txt = emi_amount_edit_txt.getText().toString();
        S_other_incom_amt_edtxt = other_incom_amt_edtxt.getText().toString();
        S_no_of_emp_edtxt = no_of_emp_edtxt.getText().toString();
        S_permanent_residence_spinner = permanent_residence_auto.getText().toString();
        S_own_house_blood_address_edt_txt = own_house_blood_address_edt_txt.getText().toString();
        S_rent_paid_for_house_edit_txt = rent_paid_for_house_edit_txt.getText().toString();

        ////

         S_purchased_by_bank_edit_txt1 = purchased_by_bank_edit_txt.getText().toString();
         S_purchased_by_GStbill_edit_txt1 = purchased_by_GStbill_edit_txt.getText().toString();
         S_sales_by_GStbill_edit_txt1 = sales_by_GStbill_edit_txt.getText().toString();
         S_bank_cridit_by_edtxt1 = bank_cridit_by_edtxt.getText().toString();
         S_Avg_monthly_income1 = Avg_monthly_income.getText().toString();
         S_other_income_edite_txt1 = other_income_edite_txt.getText().toString();
         S_business_ref_name_edt_txt = business_ref_name_edt_txt.getText().toString();
         S_business_ref_name_edt_txt = business_ref_name_edt_txt.getText().toString();
//salried
        S_pl_co_app_company_name_edtxt = pl_co_app_company_name_edtxt.getText().toString();
        S_pl_co_App_no_of_emp_edtxt = pl_co_App_no_of_emp_edtxt.getText().toString();
        S_pl_co_app_designation_in_company = pl_co_app_designation_in_company.getText().toString();
        S_pl_co_app_no_of_dependent_edt_txt = pl_co_app_no_of_dependent_edt_txt.getText().toString();
        S_pl_co_app_emi_amount_edit_txt = pl_co_app_emi_amount_edit_txt.getText().toString();
        S_pl_co_App_education_qualification_edit_txt = pl_co_App_education_qualification_edit_txt.getText().toString();
        S_pl_co_App_other_incom_amt_edtxt = pl_co_App_other_incom_amt_edtxt.getText().toString();

        S_co_business_ref_name_edt_txt = co_business_ref_name_edt_txt.getText().toString();
        S_co_purchased_by_bank_edit_txt = co_purchased_by_bank_edit_txt.getText().toString();
        S_co_purchased_by_GStbill_edit_txt = co_purchased_by_GStbill_edit_txt.getText().toString();
        S_co_sales_by_GStbill_edit_txt = co_sales_by_GStbill_edit_txt.getText().toString();
        S_co_bank_cridit_by_edtxt = co_bank_cridit_by_edtxt.getText().toString();
        S_co_Avg_monthly_income = co_Avg_monthly_income.getText().toString();
        S_co_other_income_edite_txt = co_other_income_edite_txt.getText().toString();

        JSONObject jsonObject =new JSONObject();
        JSONObject Applicant =new JSONObject();
        JSONObject Co_Applicant =new JSONObject();

        JSONArray other_income = new JSONArray();
        JSONArray other_amount = new JSONArray();
        JSONArray itr_reflected = new JSONArray();

        JSONArray other_income1 = new JSONArray();
        JSONArray other_amount1 = new JSONArray();
        JSONArray itr_reflected1 = new JSONArray();

        JSONArray other_income2 = new JSONArray();
        JSONArray other_amount2 = new JSONArray();
        JSONArray itr_reflected2 = new JSONArray();




        JSONArray APP_salaried_other_income = new JSONArray();
        JSONArray APP_salaried_other_amount = new JSONArray();
        JSONArray APP_salaried_itr_reflected = new JSONArray();

        APP_salaried_other_income = new JSONArray(Arrays.asList(App_salaried_Other_income_id));
        APP_salaried_other_amount = new JSONArray(Arrays.asList(S_other_incom_amt_edtxt));
        APP_salaried_itr_reflected = new JSONArray(Arrays.asList(gst_reflect_id));


        other_income = new JSONArray(Arrays.asList(Other_income_Id));
        other_amount = new JSONArray(Arrays.asList(S_other_income_edite_txt1));
        itr_reflected = new JSONArray(Arrays.asList(Gstreflect_Id));

        other_income1 = new JSONArray(Arrays.asList(pl_co_app_Other_income_id));
        other_amount1 = new JSONArray(Arrays.asList(S_pl_co_App_other_incom_amt_edtxt));
        itr_reflected1 = new JSONArray(Arrays.asList(pl_co_App_gst_reflect_id));

        other_income2 = new JSONArray(Arrays.asList(co_Other_income_Id));
        other_amount2 = new JSONArray(Arrays.asList(S_co_other_income_edite_txt));
        itr_reflected2 = new JSONArray(Arrays.asList(co_Gstreflect_Id));

     //   Applicant.put("")

        JSONObject J= null;
        try {
            J =new JSONObject();


            if(loan_type_id.equals("1") ||loan_type_id.equals("2") )
            {
                Applicant.put("transaction_type",transaction_type_spi_id);
                Applicant.put("property_status",propert_statues_spinner_id);
                Applicant.put("age_of_property",S_age_Of_Property_Edit_Text);
                Applicant.put("plot_area",S_plot_area_edit_txt);
                Applicant.put("builtup_area",S_build_up_area_edit_txt);
                Applicant.put("property_price",S_property_price_edt_txt);

            }else if(loan_type_id.equals("3") ||loan_type_id.equals("8") )
            {
                Applicant.put("cost_of_land",S_cost_of_plot_edite_text);
                Applicant.put("plot_area",S_plot_area_edit_txt_plot_construction);
                Applicant.put("builtup_area",S_build_up_area_edit_txt_plot_construction);
                Applicant.put("construction_status",construction_statues_spinner_id);

            }else if(loan_type_id.equals("4"))
            {
                Applicant.put("plot_area",S_plot_property_price_edt_txt);
                Applicant.put("property_price",S_plot_area_edit_txt_plot_);
            }
            else if(loan_type_id.equals("5") || loan_type_id.equals("10"))
            {
                Applicant.put("plot_area",S_plot_area_edit_txt_bt);
                Applicant.put("built_up_area",build_up_area_edit_txt_bt);
                Applicant.put("property_value",S_property_price_edt_txt_bt);
                Applicant.put("exit_bank_name",S_Existing_bank_edit_txt);
                Applicant.put("exist_loan_varient",S_Loan_varient_edit_txt);
                Applicant.put("original_loan_amount",S_loan_amount_edit_txt);
                Applicant.put("original_tenure",S_original_tenure_edit_txt);
                Applicant.put("remaining_tenure",S_remaining_tenure_edit_txt);
                Applicant.put("interest_rate",S_interest_rate_edit_txt);

            } else if(loan_type_id.equals("6") || loan_type_id.equals("9") ) {

                Applicant.put("plot_area", S_plot_area_edit_txt_improment);
                Applicant.put("plot_area", S_ploat_area_edit_txt_improment);
                Applicant.put("plot_area", S_cost_estimation_edit_txt_improment);
                Applicant.put("plot_area", S_property_ownership_edt_txt_improment);
            }

            if(salary_type.equals("1"))
            {
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
                Applicant.put("other_from",APP_salaried_other_income);
                Applicant.put("other_amount",APP_salaried_other_amount);
                Applicant.put("other_reflected",APP_salaried_itr_reflected);
                Applicant.put("perm_res_pincode",S_permanent_residence_spinner);
                Applicant.put("perm_res_area ",permanent_residence__area_id);
                Applicant.put("perm_residence",Permanent_Resi_id);
                Applicant.put("own_house_relative",Own_house_rela_id);
                Applicant.put("rel_address",S_own_house_blood_address_edt_txt);
                Applicant.put("rent_beingpaid",S_rent_paid_for_house_edit_txt);
                Applicant.put("qualification",S_education_qualification_edit_txt);

            }else if(salary_type.equals("2"))
            {
                Applicant.put("has_sb_account",having_bank_Id);
                Applicant.put("qualification",Educational_Id);
                Applicant.put("reference_name",S_business_ref_name_edt_txt);
                Applicant.put("addr_proof_own",Spinner_res_proof_Id);

                Applicant.put("other_from",other_income);
                Applicant.put("other_amount",other_amount);
                Applicant.put("other_reflected",other_amount);

                Applicant.put("bussiness_registeration",Business_registration_Id);
                Applicant.put("purchased_by_bank_edit_txt1",S_purchased_by_bank_edit_txt1);
                Applicant.put("purchased_by_GStbill_edit_txt1",S_purchased_by_GStbill_edit_txt1);
                Applicant.put("sales_by_GStbill_edit_txt1",S_sales_by_GStbill_edit_txt1);
                Applicant.put("bank_cridit_by_edtxt1",S_bank_cridit_by_edtxt1);
                Applicant.put("Avg_monthly_income1",S_Avg_monthly_income1);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(IS_CO_Applicant_Id.equals("1"))
        {
            try {


                if(IS_CO_Salried_Self.equals("1"))
                {
                    Co_Applicant.put("has_pancard",Pl_Co_App_PAN_id);
                    Co_Applicant.put("has_emp_id",pl_co_app_Employee_id);
                    Co_Applicant.put("company_type",pl_co_app_Company_id);
                    Co_Applicant.put("employee_type",pl_co_app_Emp_type_id);
                    Co_Applicant.put("is_epf_deduct",pl_co_app_epf_id);
                    Co_Applicant.put("marital_status",pl_co_app_maried_res_spinner_id);
                    Co_Applicant.put("company_name",S_pl_co_app_company_name_edtxt);
                    Co_Applicant.put("employees_count",S_pl_co_App_no_of_emp_edtxt);
                    Co_Applicant.put("designation",S_pl_co_app_designation_in_company);
                    Co_Applicant.put("no_of_dependency",S_pl_co_app_no_of_dependent_edt_txt);
                    Co_Applicant.put("affordable_pay",S_pl_co_app_emi_amount_edit_txt);
                    Co_Applicant.put("qualification",S_pl_co_App_education_qualification_edit_txt);


                    Co_Applicant.put("other_from",other_income1);
                    Co_Applicant.put("other_amount",other_amount1);
                    Co_Applicant.put("other_reflected",itr_reflected1);

                }else
                {
                    Co_Applicant.put("has_sb_account",co_having_bank_Id);
                    Co_Applicant.put("qualification",co_Educational_Id);
                    Co_Applicant.put("reference_name",co_business_ref_name_edt_txt);
                    Co_Applicant.put("addr_proof_own",co_Spinner_res_proof_Id);

                    Co_Applicant.put("other_from",other_income2);
                    Co_Applicant.put("other_amount",other_amount2);
                    Co_Applicant.put("other_reflected",itr_reflected2);

                    Co_Applicant.put("bussiness_registeration",co_Business_registration_Id);
                    Co_Applicant.put("purpercent_bankacc",S_co_purchased_by_bank_edit_txt);
                    Co_Applicant.put("purpercent_gstbill",S_co_purchased_by_GStbill_edit_txt);
                    Co_Applicant.put("salepercent_gstbill",S_co_sales_by_GStbill_edit_txt);
                    Co_Applicant.put("incomepercent_bank",S_co_bank_cridit_by_edtxt);
                    Co_Applicant.put("avg_bankbalance",S_co_Avg_monthly_income);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {

        }



        try {
            J =new JSONObject();
            //  J.put(Params.email_id,email);
            J.put("applicant_count",app_count);


            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("applicant",Applicant);
            J.put("co_applicant",Co_Applicant);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("Add Home Laoan", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Eligibility_Check, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        Log.e("Add_Home_loan Partner", String.valueOf(response));
                        try {
                            JSONObject jsonObject1 = response.getJSONObject("response");
                            if(jsonObject1.getString("applicant_status").equals("success")) {
                                if(jsonObject1.getString("eligibility_status").equals("success"))
                                {
                                    Toast.makeText(context,"Eligibility Created Successfully",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Eligibility_HL_New.this, Credite_report_details.class);
                                    intent.putExtra("user_id", user_id);
                                    intent.putExtra("transaction_id", transaction_id);
                                    startActivity(intent);
                                    finish();
                                }else if(jsonObject1.getString("eligibility_status").equals("error"))
                                {
                                    Toast.makeText(context,"Eligibility Failed",Toast.LENGTH_SHORT).show();

                                    String viability_array =jsonObject1.getString("eligibility_arr");
                                    Intent intent = new Intent(Eligibility_HL_New.this, Loan_Viyability_Check_Activity.class);
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
