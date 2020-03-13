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
import in.loanwiser.partnerapp.PartnerActivitys.RemoveCommas;
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
            pl_co_app_slrd_total_experience_edit_txt,pl_co_app_f_no_of_acres_edit_txt,pl_co_app_F_anual_income_edit_txt,
            pl_co_app_f_daily_income_f,pl_co_app_F_number_of_years_in_work,pl_co_app_F_average_monthly_income,
            pl_co_D_no_of_animals,pl_co_D_no_of_liters_edit_txt,pl_co_app_self_D_no_of_years_in_works,
            pl_co_app_D_avg_monthly_income,pl_co_P_no_of_birds_edit_txt,pl_co_P_supply_by_who,pl_co_P_Selling_Price,
            pl_co_p_Profit_affter_selling,pl_co_P_no_of_years_in_work_P,pl_co_p_avg_monthly_income_Poultry,
            pl_co_own_self_delership_company_edit_txt,
            pl_co_own_self_monthly_profit_edit_txt,pl_co_monthly_income_own_ser_bus_edit_txt,
            pl_co_no_of_employee_own_ser_bus_edit_txt,pl_co_business_investment_own_ser_bus_edit_txt,
            pl_co_value_of_stock_raw_material,pl_co_monthly_sales_manufa,pl_co_value_of_machineries,
            pl_co_Own_number_of_years_in_work_retails,pl_co_own_average_monthly_income_own_business;

    //Co Applicant
    private Spinner pl_co_app_slrd_spinn_salary_crt_mtd,pl_co_app_slrd_res_spinn_area,
            pl_co_app_ind_business_incom_proof,pl_Ly_co_app_self_emp_type,pl_co_app_self_spi_vocation_type_,
            pl_co_app_ind_spinner_office_shop_setup_ind,pl_co_self_office_spinner_residence_type,
            co_self_bus_vintage_proof,co_self_bussiness_proof,co_self_asstes_owned;

    AppCompatEditText pl_Ly_co_app_self_edit_txt_name,pl_Ly_co_app_self_age_edit_txt,pl_co_app_ind_no_of_vehicle_edit_txt,
            pl_co_app_ind_no_of_years_work_ind_edit_txt,pl_co_app_ind_avg_monthly_incom_edit_txt;

    AppCompatAutoCompleteTextView company_pincode_txt,residence_pincode1_edit_txt,pl_co_app_slrd_company_pincode_txt,
            pl_co_app_office_residence_pincode_edite_txt;

    Spinner pl_co_self_spi_vocation_forming,co_self_D_spinner_how_do_sell_milk,pl_co_app_spinner_office_shop_setup_ind,
            spinner_busines_type_own_business,co_self_fran_spinner_frenc_deler_sub,pl_co_own_spinner_office_shop_setup,
            assets_owned_sppiner,salaried_assets_owned_sppiner,salaried_salary_proof_sppiner,co_self_ind_vehicle_type,
            pl_co_app_what_crop_spinne,pl_co_app_ind_spinner_office_ownership_Type;

    String pl_co_app_slrd_Salary_id,pl_co_app_slrd_Salary_Value,pl_co_app_slrd_res_spinn_area_id,pl_co_app_slrd_res_spinn_area_district_id,
            pl_co_app_slrd_res_spinn_area_state_id,pl_co_s_forming_vocation_type_forming_id,pl_co_s_forming_vocation_type_forming_value;

    AppCompatTextView pl_co_app_slrd_salary_proof_edit_txt,pl_co_app_slrd_assets_owned_BL,app_assets_owned;

    String  pl_self_ind_vocaton_id,pl_self_ind_vocaton_value,pl_self_ind_Employee_type_Id,pl_co_app_ind_Office_Shop__id,pl_co_app_ind_Office_Shop__value
            ,pl_self_ind_Employee_type_Value,pl_co_app_self_res_spinn_area_id,pl_co_app_self_res_spinn_area_district_id,
            pl_co_app_self_res_spinn_area_state_id,pl_co_f_Office_Shop__id,pl_co_f_Office_Shop__value,
            pl_co_own_Office_Shop__id,pl_co_own_Office_Shop__value;

    LinearLayout pl_self_ind_Driver_C_owner,pl_self_individual,pl_formin_dairy,pl_self_business,pl_co_self_ofiice_res_details,
            pl_forming,pl_dairy,pl_poultry,pl_co_Retail_wholesale_business,pl_service_business,pl_manufacturing;
    AppCompatTextView pl_co_app_ind_vehicle_type_text;
    ////////

    String assets_owned_str,assets_owned_salaried_str,salary_proof_salaried_str,vehilce_str,
            what_kind_crop_str,self_own_bus_str,self_own_vintage_bus_str,self_co_assets_owned_str;
    String [] assets_owned_SA,assets_owned_salaried_SA,salary_proof_salaried_SA,vehilce_SA,
            what_kind_crop_SA,self_own_bus_SA,self_own_vintage_bus_SA,self_co_assets_owned_SA;
    RemoveCommas removeClass;

    JSONArray Employement,is_coapplicant;
    Typeface font;
    private Context context = this;
    InputMethodManager imm;
    JSONArray ja= new JSONArray();
    JSONArray Residence_ownership_ar,Salary_method_ar,Salary_proof_ar,employee_id_ar,have_pan_ar,
                    other_earning_ar,Property_Type,Assets_own,vocaton_ar,Pl_self_ind_Type_of_employement,
            Business_Proof,office_shop,vehicle_Type,vocation_type_forming_ar,sell_milk,franchise,Business_type_own_business,
            Office_residence,
            crop_type,Business_income_proof;
    String[] SPINNERLIST,salary_proof_salaried_app_SA;
    String[] SALARY_Method,Salary_Proof,Residence_Type_SA,Employe_ID_SA,PAN_ID_SA,
            Other_Earning_SA,Pincode_SA,Area,CO_Type_Of_Emp_SA,Have_Co_Applicant,Vocation_SA,EMPLOYEE_TYPE_SA,
            vocation_type_forming__SA,Office_Shop_SA,Selling_Milk_SA,Own_business_type_SA,
            franchise_SA,Office_Shop_own_SA;

    ArrayAdapter<String> Salary_Adapter,Salary_proof_Adapter,Residence_Adapter,Employee_ID_Adapter,
            PAN_ID_Adapter,Other_Earning_Adapter,Pincode_Adapter,A_Area,CO_Type_Of_Emp_Adapter,
            Have_Co_Adapter,Vocation_Adapter,Employee_Type_adapter,Office_Shop__Adapter,vocation_type_forming_Adapter,
            Selling_Milk_Adapter,Own_business_type_Adapter,franchise__Adapter,Office_Shop_own_SA_Adapter;

    String String_value_Age,ST_occupation_edit_txt,St_monthly_net_sal_edit_txt,
            ST_experience_in_current_cmpy,ST_total_experience_edit_txt,
            ST_company_pincode_txt,ST_residence_pincode1_edit_txt,
            ST_current_residence_edit_txt,ST_monthly_afr_emi_amt_edit_txt,result,
            company_area,company_area_district_id,company_area_state_id,
            res_company_area,res_company_area_district_id,res_company_area_state_id,
            co_self_D_selling_milk_id,co_self_D_selling_milk_value,pl_co_own_business_own_type_id,
            pl_co_own_business_own_type_Value,pl_co_self_franchise__id,pl_co_self_franchise__value,
            ST_pl_co_app_slrd_name_edite_txt,ST_pl_co_app_slrd_age_edite_txt,ST_pl_co_app_slrd_month_net_slrd_edite_txt,
            ST_pl_co_app_slrd_experience_in_current_cmpy,ST_pl_co_app_slrd_total_experience_edit_txt,
            ST_pl_co_app_slrd_company_pincode_txt,ST_pl_Ly_co_app_self_edit_txt_name,ST_pl_Ly_co_app_self_age_edit_txt,
            ST_pl_co_app_ind_no_of_vehicle_edit_txt,ST_pl_co_app_ind_no_of_years_work_ind_edit_txt,
            ST_pl_co_app_ind_avg_monthly_incom_edit_txt;

    String ST_pl_co_app_f_no_of_acres_edit_txt,ST_pl_co_app_F_anual_income_edit_txt,
            ST_pl_co_app_f_daily_income_f,ST_pl_co_app_F_number_of_years_in_work,ST_pl_co_app_F_average_monthly_income,
            ST_pl_co_D_no_of_animals,ST_pl_co_D_no_of_liters_edit_txt,ST_pl_co_app_self_D_no_of_years_in_works,ST_pl_co_app_D_avg_monthly_income,
            ST_pl_co_P_no_of_birds_edit_txt,ST_pl_co_P_supply_by_who,ST_pl_co_P_Selling_Price,ST_pl_co_p_Profit_affter_selling,
            ST_pl_co_P_no_of_years_in_work_P,ST_pl_co_p_avg_monthly_income_Poultry, ST_pl_co_own_self_delership_company_edit_txt,ST_pl_co_own_self_monthly_profit_edit_txt,
            ST_pl_co_monthly_income_own_ser_bus_edit_txt,ST_pl_co_no_of_employee_own_ser_bus_edit_txt,ST_pl_co_business_investment_own_ser_bus_edit_txt,
            ST_pl_co_value_of_stock_raw_material,ST_pl_co_monthly_sales_manufa,ST_pl_co_value_of_machineries,ST_pl_co_Own_number_of_years_in_work_retails,
            ST_pl_co_own_average_monthly_income_own_business;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;

    StringBuffer assets_buff,assets_salaried_buff,salaried_salaried_buff,salaried_salaried_buff_app,self_ind_vehilce_type,
            self_F_what_crop,Vintage_business_proof,co_own_bus_proof,co_self_assets_owned_buff;

    String Salary_id,Salary_Value,Salary_proof_id,Salary_proof_Value,residence_id,residence_Value,
                 Employee_id,Employee_Value,PAN_id,PAN_Value, other_earning_id,other_earning_value,salary_proof_salaried_str_App,
            CO_Type_of_employement_ID,CO_Type_of_employement_Value, IS_CO_Applicant_Id,IS_CO_Applicant_Value;

    ArrayList<IncomeProofPOJO> IncomeProof_Salaried,Salary_income_Proof,assets_owned_proof,
                                assets_owned_salaried,salary_proof_arr_list,salary_proof_arr_list_app,vehicle_Type_arr_list,what_kind_of_crope_list,
            co_self_bus_vintage_proof_list,co_self_assets_owned,co_self_Assets_owned_list,co_self_bus_own_proof_list;
  //  MyCustomAdapter_Salary_Proof dataAdapter_Salaried_proof = null;
    MyCustomAdapter_Assets_owned dataAdapter_assets_owned = null;
    MyCustomAdapter_assets_owned_Salaried assets_owned_salaried_Adapter = null;
    MyCustomAdapter_salary_proof_Salaried salary_proof_salaried_Adapter = null;
    MyCustomAdapter_salary_proof_Salaried_App salary_proof_salaried_Adapter_APP = null;
    MyCustomAdapter_vehicle_type_self self_vehicle_adapter = null;
    MyCustomAdapter_what_crope_self what_crop_adapter = null;
    MyCustomAdapter_Business_Vintage_proof business_vintage_Adapter = null;
    MyCustomAdapter_Self_Assets_owned Co_my_self_Assets_Owned_adapter = null;
    MyCustomAdapter_Business_proof business_Adapter = null;
    private ChipsView cv_salary_income_proof;
    StringBuffer salary_proof_list;
    ArrayList<String> myList_values;
    ArrayList<String> Assets_myList_values;

    ChipsView cv_assets_ownned,salaried_cv_assets_ownned,salaried_cv_salary_proof,
            self_cv_vehicle_type,cv_what_kindof_crop,self_cv_bus_vintage_proof,self_cv_business_proof,
            co_self_asstes_owned_CV;

    JSONArray assets_owned_array = new JSONArray();
    JSONArray assets_owned_salary_array = new JSONArray();
    JSONArray salary_proof_salary_array = new JSONArray();
    JSONArray vehicle_type_array = new JSONArray();
    JSONArray what_crop_array = new JSONArray();
    JSONArray business_vintage_self = new JSONArray();
    JSONArray business_proof_self = new JSONArray();
    JSONArray self_co_assets_ = new JSONArray();
    JSONArray salary_proof_salary_array_App = new JSONArray();

    String user_id,transaction_id,pl_co_app_ind_Office_Shop_Own_id,pl_co_app_ind_Office_Shop_Own_value;
    int applicant_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_viability__check);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viability__check);
        initTools(R.string.viy_check);

        lead_viy_step2 = (AppCompatButton) findViewById(R.id.lead_viy_step2);
     //   assets_owned_BL = (AppCompatTextView) findViewById(R.id.assets_owned_BL);
        progressDialog = new SpotsDialog(context, R.style.Custom);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        salary_proof_edit_txt_pl = (AppCompatTextView) findViewById(R.id.salary_proof_edit_txt_pl);
        app_assets_owned = (AppCompatTextView) findViewById(R.id.app_assets_owned);
        pl_co_app_slrd_salary_proof_edit_txt = (AppCompatTextView) findViewById(R.id.pl_co_app_slrd_salary_proof_edit_txt);
        pl_co_app_slrd_assets_owned_BL = (AppCompatTextView) findViewById(R.id.pl_co_app_slrd_assets_owned_BL);

        Intent intent = getIntent();
         user_id = intent.getStringExtra("user_id");
         transaction_id = intent.getStringExtra("transaction_id");

        Assets_myList_values = (ArrayList<String>) getIntent().getSerializableExtra("select_lid_id");
        removeClass = new RemoveCommas();

     /*   lead_viy_step2.setOnClickListener(new View.OnClickListener() {
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

      /*  assets_owned_BL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_PL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Assets_own.toString());
                intent.putExtra("select_lid_id", (Serializable) Assets_myList_values);
                startActivity(intent);

            }
        });*/
        app_assets_owned.setOnClickListener(new View.OnClickListener() {
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

      //  spinner_residence_type = (Spinner) findViewById(R.id.spinner_residence_type);
        spinner_employe_id = (Spinner) findViewById(R.id.spinner_employe_id);
        spinn_salary_crt_mtd = (Spinner) findViewById(R.id.spinn_salary_crt_mtd);
        spinner_residence_type = (Spinner) findViewById(R.id.spinner_residence_type);
        spinner_salary_proof = (Spinner) findViewById(R.id.spinner_salary_proof);
        has_pan_card_spnr = (Spinner) findViewById(R.id.has_pan_card_spnr);
        Other_family_income_spnr = (Spinner) findViewById(R.id.Other_family_income_spnr);
        pl_co_self_spi_vocation_forming = (Spinner) findViewById(R.id.pl_co_self_spi_vocation_forming);
        spinn_area = (Spinner) findViewById(R.id.spinn_area);
        res_spinn_area = (Spinner) findViewById(R.id.res_spinn_area);

        residence_type = (LinearLayout) findViewById(R.id.residence_type);
        residence_live = (LinearLayout) findViewById(R.id.residence_live);
        pan_card_available = (LinearLayout) findViewById(R.id.pan_card_available);
        other_earning_avbl = (LinearLayout) findViewById(R.id.other_earning_avbl);
        other_family_mem = (LinearLayout) findViewById(R.id.other_family_mem);
       // asstes_own = (LinearLayout) findViewById(R.id.asstes_own);
        residence_type.setVisibility(View.VISIBLE);


        co_applicant_salaried_employed = (LinearLayout) findViewById(R.id.co_applicant_salaried_employed);
        co_applicant_self_employed = (LinearLayout) findViewById(R.id.co_applicant_self_employed);
        pl_self_ind_Driver_C_owner = (LinearLayout) findViewById(R.id.pl_self_ind_Driver_C_owner);


        pl_self_individual = (LinearLayout) findViewById(R.id.pl_self_individual);
        pl_formin_dairy = (LinearLayout) findViewById(R.id.pl_formin_dairy);
        pl_self_business = (LinearLayout) findViewById(R.id.pl_self_business);

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
        co_self_D_spinner_how_do_sell_milk = (Spinner) findViewById(R.id.co_self_D_spinner_how_do_sell_milk);

        spinner_busines_type_own_business = (Spinner) findViewById(R.id.spinner_busines_type_own_business);
        co_self_fran_spinner_frenc_deler_sub = (Spinner) findViewById(R.id.co_self_fran_spinner_frenc_deler_sub);
        assets_owned_sppiner = (Spinner) findViewById(R.id.assets_owned_sppiner);
        salaried_assets_owned_sppiner = (Spinner) findViewById(R.id.salaried_assets_owned_sppiner);
        salaried_salary_proof_sppiner = (Spinner) findViewById(R.id.salaried_salary_proof_sppiner);
        co_self_ind_vehicle_type = (Spinner) findViewById(R.id.co_self_ind_vehicle_type);
        pl_co_app_what_crop_spinne = (Spinner) findViewById(R.id.pl_co_app_what_crop_spinne);
        pl_co_app_ind_spinner_office_ownership_Type = (Spinner) findViewById(R.id.pl_co_app_ind_spinner_office_ownership_Type);


        age_edite_txt = (AppCompatEditText) findViewById(R.id.age_edite_txt);
        pan_number_edit_txt = (AppCompatEditText) findViewById(R.id.pan_number_edit_txt);
        occupation_edit_txt = (AppCompatEditText) findViewById(R.id.occupation_edit_txt);
        pl_co_own_self_delership_company_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_own_self_delership_company_edit_txt);
        monthly_net_sal_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_net_sal_edit_txt);
        monthly_net_sal_edit_txt.addTextChangedListener(new NumberTextWatcher(monthly_net_sal_edit_txt));
        experience_in_current_cmpy = (AppCompatEditText) findViewById(R.id.experience_in_current_cmpy);
        total_experience_edit_txt = (AppCompatEditText) findViewById(R.id.total_experience_edit_txt);
        company_pincode_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.company_pincode_txt);

        residence_pincode1_edit_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.residence_pincode1_edit_txt);
      //  pl_co_app_slrd_company_pincode_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.pl_co_app_slrd_company_pincode_txt);

        current_residence_edit_txt = (AppCompatEditText) findViewById(R.id.current_residence_edit_txt);

        family_member_name_edit_txt = (AppCompatEditText) findViewById(R.id.family_member_name_edit_txt);
        family_member_income_edit_txt = (AppCompatEditText) findViewById(R.id.family_member_income_edit_txt);
        monthly_afr_emi_amt_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_afr_emi_amt_edit_txt);
        monthly_afr_emi_amt_edit_txt.addTextChangedListener(new NumberTextWatcher(monthly_net_sal_edit_txt));
        cv_salary_income_proof = (ChipsView) findViewById(R.id.cv_salary_income_proof);

     //Co Applicant--

        pl_co_app_ind_vehicle_type_text = (AppCompatTextView) findViewById(R.id.pl_co_app_ind_vehicle_type_text);
        cv_assets_ownned = (ChipsView) findViewById(R.id.cv_assets_ownned);
        salaried_cv_assets_ownned = (ChipsView) findViewById(R.id.salaried_cv_assets_ownned);
        salaried_cv_salary_proof = (ChipsView) findViewById(R.id.salaried_cv_salary_proof);
        self_cv_vehicle_type = (ChipsView) findViewById(R.id.self_cv_vehicle_type);
        cv_what_kindof_crop = (ChipsView) findViewById(R.id.cv_what_kindof_crop);
        self_cv_bus_vintage_proof = (ChipsView) findViewById(R.id.self_cv_bus_vintage_proof);
        self_cv_business_proof = (ChipsView) findViewById(R.id.self_cv_business_proof);
        co_self_asstes_owned_CV = (ChipsView) findViewById(R.id.co_self_asstes_owned_CV);

        pl_Ly_co_app_self_edit_txt_name = (AppCompatEditText) findViewById(R.id.pl_Ly_co_app_self_edit_txt_name);
        pl_Ly_co_app_self_age_edit_txt = (AppCompatEditText) findViewById(R.id.pl_Ly_co_app_self_age_edit_txt);
        pl_co_app_ind_no_of_vehicle_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_ind_no_of_vehicle_edit_txt);
        pl_co_app_ind_avg_monthly_incom_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_ind_avg_monthly_incom_edit_txt);
        pl_co_app_ind_no_of_years_work_ind_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_ind_no_of_years_work_ind_edit_txt);
        pl_Ly_co_app_self_emp_type = (Spinner) findViewById(R.id.pl_Ly_co_app_self_emp_type);

        pl_co_app_slrd_name_edite_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_slrd_name_edite_txt);
        pl_co_app_slrd_age_edite_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_slrd_age_edite_txt);
        pl_co_app_slrd_month_net_slrd_edite_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_slrd_month_net_slrd_edite_txt);
        pl_co_app_slrd_experience_in_current_cmpy = (AppCompatEditText) findViewById(R.id.pl_co_app_slrd_experience_in_current_cmpy);
        pl_co_app_slrd_total_experience_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_slrd_total_experience_edit_txt);
        pl_co_app_f_no_of_acres_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_f_no_of_acres_edit_txt);
        pl_co_app_F_anual_income_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_app_F_anual_income_edit_txt);
        pl_co_app_f_daily_income_f = (AppCompatEditText) findViewById(R.id.pl_co_app_f_daily_income_f);
        pl_co_app_F_number_of_years_in_work = (AppCompatEditText) findViewById(R.id.pl_co_app_F_number_of_years_in_work);
        pl_co_app_F_average_monthly_income = (AppCompatEditText) findViewById(R.id.pl_co_app_F_average_monthly_income);
        pl_co_D_no_of_animals = (AppCompatEditText) findViewById(R.id.pl_co_D_no_of_animals);
        pl_co_D_no_of_liters_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_D_no_of_liters_edit_txt);
        pl_co_app_self_D_no_of_years_in_works = (AppCompatEditText) findViewById(R.id.pl_co_app_self_D_no_of_years_in_works);
        pl_co_app_D_avg_monthly_income = (AppCompatEditText) findViewById(R.id.pl_co_app_D_avg_monthly_income);
        pl_co_P_no_of_birds_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_P_no_of_birds_edit_txt);
        pl_co_P_supply_by_who = (AppCompatEditText) findViewById(R.id.pl_co_P_supply_by_who);
        pl_co_P_Selling_Price = (AppCompatEditText) findViewById(R.id.pl_co_P_Selling_Price);
        pl_co_p_Profit_affter_selling = (AppCompatEditText) findViewById(R.id.pl_co_p_Profit_affter_selling);
        pl_co_P_no_of_years_in_work_P = (AppCompatEditText) findViewById(R.id.pl_co_P_no_of_years_in_work_P);
        pl_co_p_avg_monthly_income_Poultry = (AppCompatEditText) findViewById(R.id.pl_co_p_avg_monthly_income_Poultry);
        pl_co_own_self_monthly_profit_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_own_self_monthly_profit_edit_txt);
        pl_co_monthly_income_own_ser_bus_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_monthly_income_own_ser_bus_edit_txt);
        pl_co_no_of_employee_own_ser_bus_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_no_of_employee_own_ser_bus_edit_txt);
        pl_co_business_investment_own_ser_bus_edit_txt = (AppCompatEditText) findViewById(R.id.pl_co_business_investment_own_ser_bus_edit_txt);
        pl_co_value_of_stock_raw_material = (AppCompatEditText) findViewById(R.id.pl_co_value_of_stock_raw_material);
        pl_co_monthly_sales_manufa = (AppCompatEditText) findViewById(R.id.pl_co_monthly_sales_manufa);
        pl_co_value_of_machineries = (AppCompatEditText) findViewById(R.id.pl_co_value_of_machineries);
        pl_co_Own_number_of_years_in_work_retails = (AppCompatEditText) findViewById(R.id.pl_co_Own_number_of_years_in_work_retails);
        pl_co_own_average_monthly_income_own_business = (AppCompatEditText) findViewById(R.id.pl_co_own_average_monthly_income_own_business);

        pl_co_app_slrd_company_pincode_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.pl_co_app_slrd_company_pincode_txt);
        pl_co_app_office_residence_pincode_edite_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.pl_co_app_office_residence_pincode_edite_txt);

        pl_co_app_slrd_spinn_salary_crt_mtd = (Spinner) findViewById(R.id.pl_co_app_slrd_spinn_salary_crt_mtd);
        pl_co_app_slrd_res_spinn_area = (Spinner) findViewById(R.id.pl_co_app_slrd_res_spinn_area);
        pl_co_app_self_spi_vocation_type_ = (Spinner) findViewById(R.id.pl_co_app_self_spi_vocation_type_);
        pl_co_app_ind_business_incom_proof = (Spinner) findViewById(R.id.pl_co_app_ind_business_incom_proof);
        pl_co_self_office_spinner_residence_type = (Spinner) findViewById(R.id.pl_co_self_office_spinner_residence_type);
        co_self_bus_vintage_proof = (Spinner) findViewById(R.id.co_self_bus_vintage_proof);
        co_self_bussiness_proof = (Spinner) findViewById(R.id.co_self_bussiness_proof);
        co_self_asstes_owned = (Spinner) findViewById(R.id.co_self_asstes_owned);

        pl_co_app_ind_spinner_office_shop_setup_ind = (Spinner) findViewById(R.id.pl_co_app_ind_spinner_office_shop_setup_ind);

        pl_co_self_ofiice_res_details =( LinearLayout) findViewById(R.id.pl_co_self_ofiice_res_details);


        pl_forming =( LinearLayout) findViewById(R.id.pl_forming);
        pl_dairy =( LinearLayout) findViewById(R.id.pl_dairy);
        pl_poultry =( LinearLayout) findViewById(R.id.pl_poultry);

        pl_co_Retail_wholesale_business =( LinearLayout) findViewById(R.id.pl_co_Retail_wholesale_business);
        pl_service_business =( LinearLayout) findViewById(R.id.pl_service_business);
        pl_manufacturing =( LinearLayout) findViewById(R.id.pl_manufacturing);

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
       // assets_owned_txt.setTypeface(font);
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

        cv_assets_ownned.getEditText().setCursorVisible(true);

        cv_assets_ownned.setChipsValidator(new ChipsView.ChipValidator() {
            @Override
            public boolean isValid(Contact contact) {
                if (contact.getDisplayName().equals("asd@qwe.de")) {
                    return false;
                }
                return true;
            }
        });

        cv_assets_ownned.setChipsListener(new ChipsView.ChipsListener() {
            @Override
            public void onChipAdded(ChipsView.Chip chip) {
                for (ChipsView.Chip chipItem : cv_assets_ownned.getChips()) {
                    Log.d("ChipList", "chip: " + chipItem.toString());
                }
            }

            @Override
            public void onChipDeleted(ChipsView.Chip chip) {

            }

            @Override
            public void onTextChanged(CharSequence text) {
                //   mAdapter.filterItems(text);
            }
        });

        salaried_cv_salary_proof.getEditText().setCursorVisible(true);

        salaried_cv_salary_proof.setChipsValidator(new ChipsView.ChipValidator() {
            @Override
            public boolean isValid(Contact contact) {
                if (contact.getDisplayName().equals("asd@qwe.de")) {
                    return false;
                }
                return true;
            }
        });

        salaried_cv_salary_proof.setChipsListener(new ChipsView.ChipsListener() {
            @Override
            public void onChipAdded(ChipsView.Chip chip) {
                for (ChipsView.Chip chipItem : salaried_cv_salary_proof.getChips()) {
                    Log.d("ChipList", "chip: " + chipItem.toString());
                }
            }

            @Override
            public void onChipDeleted(ChipsView.Chip chip) {

            }

            @Override
            public void onTextChanged(CharSequence text) {
                //   mAdapter.filterItems(text);
            }
        });

        //salaried_assets-owned
        salaried_cv_assets_ownned.getEditText().setCursorVisible(true);

        salaried_cv_assets_ownned.setChipsValidator(new ChipsView.ChipValidator() {
            @Override
            public boolean isValid(Contact contact) {
                if (contact.getDisplayName().equals("asd@qwe.de")) {
                    return false;
                }
                return true;
            }
        });

        salaried_cv_assets_ownned.setChipsListener(new ChipsView.ChipsListener() {
            @Override
            public void onChipAdded(ChipsView.Chip chip) {
                for (ChipsView.Chip chipItem : salaried_cv_assets_ownned.getChips()) {
                    Log.d("ChipList", "chip: " + chipItem.toString());
                }
            }

            @Override
            public void onChipDeleted(ChipsView.Chip chip) {

            }

            @Override
            public void onTextChanged(CharSequence text) {
                //   mAdapter.filterItems(text);
            }
        });



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


        pl_co_app_office_residence_pincode_edite_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String workpincode = pl_co_app_office_residence_pincode_edite_txt.getText().toString();

                if (workpincode.length() == 2) {
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
                            Toast.makeText(context,"Please Select Co applicant Salary Credit Method",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            salaried_salaried_buff_app = new StringBuffer();
                            ArrayList<IncomeProofPOJO> assests_list_v1 = salary_proof_salaried_Adapter_APP.SALARIED_PROOF_SALARIED_App;
                            for(int i=0;i<assests_list_v1.size();i++){
                                IncomeProofPOJO country = assests_list_v1.get(i);
                                if(country.isIP_selected()){
                                    salaried_salaried_buff_app.append(country.getIP_id()+ ",");
                                    String responseID1 = String.valueOf(salaried_salaried_buff_app);
                                }
                            }
                            if (salaried_salaried_buff_app.length()> 0) {

                                String responseID1 = String.valueOf(salaried_salaried_buff_app);
                                salary_proof_salaried_str_App = removeClass.cleanUpCommas(responseID1);
                                salary_proof_salaried_app_SA = salary_proof_salaried_str_App.split(",");

                                salary_proof_salary_array_App = new JSONArray();
                                salary_proof_salary_array_App = new JSONArray(Arrays.asList(salary_proof_salaried_app_SA));
                            }

                            if (!Validate_experience()) {
                                return;
                            }

                            if (!Validate_total_experience()) {
                                return;
                            }

                            if (!Company_locationpincode1()) {
                                return;
                            }

                            assets_buff = new StringBuffer();
                            ArrayList<IncomeProofPOJO> assests_list_v = dataAdapter_assets_owned.ASSETS_OWNED;
                            for(int i=0;i<assests_list_v.size();i++){
                                IncomeProofPOJO country = assests_list_v.get(i);
                                if(country.isIP_selected()){
                                    assets_buff.append(country.getIP_id()+ ",");
                                    String responseID1 = String.valueOf(assets_buff);
                                }
                            }
                            if (assets_buff.length()> 0) {

                                String responseID1 = String.valueOf(assets_buff);
                                assets_owned_str = removeClass.cleanUpCommas(responseID1);
                                assets_owned_SA = assets_owned_str.split(",");

                                assets_owned_array = new JSONArray();
                                assets_owned_array = new JSONArray(Arrays.asList(assets_owned_SA));
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
                    salaried_salaried_buff = new StringBuffer();
                    ArrayList<IncomeProofPOJO> assests_list_v = salary_proof_salaried_Adapter.SALARIED_PROOF_SALARIED;
                    for(int i=0;i<assests_list_v.size();i++){
                        IncomeProofPOJO country = assests_list_v.get(i);
                        if(country.isIP_selected()){
                            salaried_salaried_buff.append(country.getIP_id()+ ",");
                            String responseID1 = String.valueOf(salaried_salaried_buff);
                        }
                    }
                    if (salaried_salaried_buff.length()> 0) {

                        String responseID1 = String.valueOf(salaried_salaried_buff);
                        salary_proof_salaried_str = removeClass.cleanUpCommas(responseID1);
                        salary_proof_salaried_SA = salary_proof_salaried_str.split(",");

                        salary_proof_salary_array = new JSONArray();
                        salary_proof_salary_array = new JSONArray(Arrays.asList(salary_proof_salaried_SA));
                    }


                    if (!Validate_pl_co_app_slrd_experience_in_current_cmpy()) {
                        return;
                    }
                    if (!Validate_pl_co_app_slrd_total_experience_edit_txt()) {
                        return;
                    }

                    if (!Validate_pl_co_app_slrd_company_pincode_txt()) {
                        return;
                    }

                    assets_salaried_buff = new StringBuffer();
                    ArrayList<IncomeProofPOJO> assests_list_A = assets_owned_salaried_Adapter.ASSETS_OWNED_SALARIED;
                    for(int i=0;i<assests_list_A.size();i++){
                        IncomeProofPOJO country = assests_list_A.get(i);
                        if(country.isIP_selected()){
                            assets_salaried_buff.append(country.getIP_id()+ ",");
                            String responseID1 = String.valueOf(assets_owned_salaried);
                        }
                    }
                    if (assets_salaried_buff.length()> 0) {

                        String responseID1 = String.valueOf(assets_salaried_buff);
                        assets_owned_salaried_str = removeClass.cleanUpCommas(responseID1);
                        assets_owned_salaried_SA = assets_owned_salaried_str.split(",");

                        assets_owned_salary_array = new JSONArray();
                        assets_owned_salary_array = new JSONArray(Arrays.asList(assets_owned_salaried_SA));
                    }

                    lead_viability();
                }


            }else
            {

                if (!Validate_pl_Ly_co_app_self_edit_txt_name()) {
                    return;
                }

                if(pl_self_ind_Employee_type_Id.equals("0"))
                {

                }else if(pl_self_ind_Employee_type_Id.equals("1"))
                {
                    if (!Validate_pl_Ly_co_app_self_age_edit_txt()) {
                        return;
                    }
                    validation_individual();
                }
                else if(pl_self_ind_Employee_type_Id.equals("1"))
                {
                    if (!Validate_pl_Ly_co_app_self_age_edit_txt()) {
                        return;
                    }
                    if(pl_co_s_forming_vocation_type_forming_id.equals("0"))
                    {
                        Toast.makeText(context,"please Select the vocation Type",Toast.LENGTH_SHORT).show();
                    }else  if(pl_co_s_forming_vocation_type_forming_id.equals("1"))
                    {

                        validation_forming();

                    }else if(pl_co_s_forming_vocation_type_forming_id.equals("2"))
                    {

                        validate_dairy();

                    }else if(pl_co_s_forming_vocation_type_forming_id.equals("3"))
                    {

                        validation_poultry();
                    }
                }  else if(pl_self_ind_Employee_type_Id.equals("2"))
                {

                    Validate_own_Business();
                }

            }

        } else if(IS_CO_Applicant_Id.equals("2")) {
            lead_viability();

           /* Intent intent = new Intent(Viability_Check_PL.this, Eligibility_Check_PL.class);
            startActivity(intent);
            finish();*/
        }
    }

    private void validation_individual()
    {
        if(pl_self_ind_vocaton_id.equals("0"))
        {
            Toast.makeText(context,"please Select the vocation Type",Toast.LENGTH_SHORT).show();
        }else
        {

            if(pl_self_ind_vocaton_id.equals("7"))
            {

                self_ind_vehilce_type = new StringBuffer();
                ArrayList<IncomeProofPOJO> vehicle_list = self_vehicle_adapter.SELF_VEHICLE_ARR_LIST;
                for(int i=0;i<vehicle_list.size();i++){
                    IncomeProofPOJO country = vehicle_list.get(i);
                    if(country.isIP_selected()){
                        self_ind_vehilce_type.append(country.getIP_id()+ ",");
                        String responseID1 = String.valueOf(vehicle_Type_arr_list);
                    }
                }
                if (self_ind_vehilce_type.length()> 0) {

                    String responseID1 = String.valueOf(self_ind_vehilce_type);
                   vehilce_str = removeClass.cleanUpCommas(responseID1);
                    vehilce_SA = vehilce_str.split(",");

                    vehicle_type_array = new JSONArray();
                    vehicle_type_array = new JSONArray(Arrays.asList(vehilce_SA));
                }

                if (!Validate_pl_co_app_ind_no_of_vehicle_edit_txt()) {
                    return;
                }

                if (!Validate_pl_co_app_ind_no_of_years_work_ind_edit_txt()) {
                    return;
                }

                if (!Validate_pl_co_app_ind_avg_monthly_incom_edit_txt()) {
                    return;
                }

                common_validation();

            }else
            {

                if (!Validate_pl_co_app_ind_no_of_years_work_ind_edit_txt()) {
                    return;
                }

                if (!Validate_pl_co_app_ind_avg_monthly_incom_edit_txt()) {
                    return;
                }

                common_validation();

            }

        }
    }

    private void validation_forming()
    {

            if (!Validate_pl_co_app_f_no_of_acres_edit_txt()) {
                return;
            }

        self_F_what_crop = new StringBuffer();

        ArrayList<IncomeProofPOJO> vehicle_list = what_crop_adapter.SELF_F_WHAT_CROP_ARR_LIST;
        for(int i=0;i<vehicle_list.size();i++){
            IncomeProofPOJO country = vehicle_list.get(i);
            if(country.isIP_selected()){
                self_F_what_crop.append(country.getIP_id()+ ",");
                String responseID1 = String.valueOf(vehicle_Type_arr_list);
            }
        }
        if (self_F_what_crop.length()> 0) {

            String responseID1 = String.valueOf(self_F_what_crop);
            what_kind_crop_str = removeClass.cleanUpCommas(responseID1);
            what_kind_crop_SA = what_kind_crop_str.split(",");

            what_crop_array = new JSONArray();
            what_crop_array = new JSONArray(Arrays.asList(what_kind_crop_SA));
        }

            if (!Validate_pl_co_app_F_anual_income_edit_txt()) {
                return;
            }
            if (!Validate_pl_co_app_f_daily_income_f()) {
                return;
            }
            if (!Validate_pl_co_app_F_number_of_years_in_work()) {
                return;
            }
            if (!Validate_pl_co_app_F_average_monthly_income()) {
                return;
            }

        common_validation();

    }

    private void validate_dairy()
    {
            if (!Validate_pl_co_D_no_of_animals()) {
                return;
            }
            if (!Validate_pl_co_D_no_of_liters_edit_txt()) {
                return;
            }

            if(co_self_D_selling_milk_id.equals("0"))
            {
                Toast.makeText(context,"please Select Milk selling type",Toast.LENGTH_SHORT).show();

            }else
            {
                if (!Validate_pl_co_app_self_D_no_of_years_in_works()) {
                    return;
                }

                if (!Validate_pl_co_app_D_avg_monthly_income()) {
                    return;
                }
            }

        common_validation();
    }

    private void validation_poultry()
    {

            if (!Validate_pl_co_P_no_of_birds_edit_txt()) {
                return;
            }
            if (!Validate_pl_co_P_supply_by_who()) {
                return;
            }

            if (!Validate_pl_co_P_Selling_Price()) {
                return;
            }

            if (!Validate_pl_co_p_Profit_affter_selling()) {
                return;
            }

            if (!Validate_pl_co_P_no_of_years_in_work_P()) {
                return;
            }

            if (!Validate_pl_co_p_avg_monthly_income_Poultry()) {
                return;
            }

        common_validation();
        }

        ///////////////////////////////// Co Applicant Own business.

    private void Validate_own_Business()
    {
        if(pl_co_own_business_own_type_id.equals("0"))
        {
            Toast.makeText(context,"please Select the business type",Toast.LENGTH_SHORT).show();

        }else
        {

           if(pl_co_own_business_own_type_id.equals("1"))
           {
                     if(pl_co_self_franchise__id.equals("0"))
                        {
                            Toast.makeText(context,"please Select Franchise/dealer/sub dealer type",Toast.LENGTH_SHORT).show();

                        }else if(pl_co_self_franchise__id.equals("1"))
                        {
                            if (!Validate_pl_co_own_self_delership_company_edit_txt()) {
                                return;
                            }
                            if (!Validate_pl_co_own_self_monthly_profit_edit_txt()) {
                                return;
                            }

                            Own_Bus_No_year_com_validation();

                        }else {

                         if (!Validate_pl_co_own_self_monthly_profit_edit_txt()) {
                             return;
                         }
                         Own_Bus_No_year_com_validation();

                     }

                    }else if(pl_co_own_business_own_type_id.equals("2"))
                    {

                        if (!Validate_pl_co_monthly_income_own_ser_bus_edit_txt()) {
                            return;
                        }
                        if (!Validate_pl_co_no_of_employee_own_ser_bus_edit_txt()) {
                            return;
                        }
                        if (!Validate_pl_co_business_investment_own_ser_bus_edit_txt()) {
                            return;
                        }
                        Own_Bus_No_year_com_validation();

                    }else if(pl_co_own_business_own_type_id.equals("3"))
                    {

                        if (!Validate_pl_co_value_of_stock_raw_material()) {
                            return;
                        }
                        if (!Validate_pl_co_monthly_sales_manufa()) {
                            return;
                        }
                        if (!Validate_pl_co_value_of_machineries()) {
                            return;
                        }

                        Own_Bus_No_year_com_validation();

                    }

        }
    }


    private void Own_Bus_No_year_com_validation()
    {
        if (!Validate_pl_co_Own_number_of_years_in_work_retails()) {
            return;
        }

        if (!Validate_pl_co_own_average_monthly_income_own_business()) {
            return;
        }
        common_validation();

    }
    /////////////////////////////comm validation

    private void common_validation()
    {
        Vintage_business_proof = new StringBuffer();

        ArrayList<IncomeProofPOJO> vehicle_list = business_vintage_Adapter.VINTAGE_PROOF_LIST;
        for(int i=0;i<vehicle_list.size();i++){
            IncomeProofPOJO country = vehicle_list.get(i);
            if(country.isIP_selected()){
                Vintage_business_proof.append(country.getIP_id()+ ",");
                String responseID1 = String.valueOf(co_self_bus_vintage_proof_list);
            }
        }
        if (Vintage_business_proof.length()> 0) {

            String responseID1 = String.valueOf(Vintage_business_proof);
            self_own_vintage_bus_str = removeClass.cleanUpCommas(responseID1);
            self_own_vintage_bus_SA = self_own_vintage_bus_str.split(",");

            business_vintage_self = new JSONArray();
            business_vintage_self = new JSONArray(Arrays.asList(self_own_vintage_bus_SA));
        }

        co_own_bus_proof = new StringBuffer();

        ArrayList<IncomeProofPOJO>  business_proof = business_Adapter.SELF_BUSINESS_PROOF_LIST;
        for(int i=0;i<business_proof.size();i++){
            IncomeProofPOJO country = business_proof.get(i);
            if(country.isIP_selected()){
                co_own_bus_proof.append(country.getIP_id()+ ",");
                String responseID1 = String.valueOf(co_self_bus_own_proof_list);
            }
        }
        if (co_own_bus_proof.length()> 0) {

            String responseID1 = String.valueOf(co_own_bus_proof);
            self_own_bus_str = removeClass.cleanUpCommas(responseID1);
            self_own_bus_SA = self_own_bus_str.split(",");

            business_proof_self = new JSONArray();
            business_proof_self = new JSONArray(Arrays.asList(self_own_bus_SA));
        }


        if(pl_co_app_ind_Office_Shop__id.equals("0"))
        {
            Toast.makeText(context,"please Select office/shop setup",Toast.LENGTH_SHORT).show();

        }else
        {
            co_self_assets_owned_buff = new StringBuffer();

            ArrayList<IncomeProofPOJO>  self_assets_ownr = Co_my_self_Assets_Owned_adapter.SELF_CO_ASSETS_OWNED_LIST;
            for(int i=0;i<self_assets_ownr.size();i++){
                IncomeProofPOJO country = self_assets_ownr.get(i);
                if(country.isIP_selected()){
                    co_self_assets_owned_buff.append(country.getIP_id()+ ",");
                    String responseID1 = String.valueOf(co_self_Assets_owned_list);
                }
            }
            if (co_self_assets_owned_buff.length()> 0) {

                String responseID1 = String.valueOf(co_self_assets_owned_buff);
                self_co_assets_owned_str = removeClass.cleanUpCommas(responseID1);
                self_co_assets_owned_SA = self_co_assets_owned_str.split(",");

                self_co_assets_ = new JSONArray();
                self_co_assets_ = new JSONArray(Arrays.asList(self_co_assets_owned_SA));
            }

        }
        lead_viability();

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


    private boolean Validate_pl_Ly_co_app_self_edit_txt_name(){
        if (pl_Ly_co_app_self_edit_txt_name.getText().toString().isEmpty()) {
            pl_Ly_co_app_self_edit_txt_name.setError(getText(R.string.err_curent));
            pl_Ly_co_app_self_edit_txt_name.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_Ly_co_app_self_age_edit_txt(){
        if (pl_Ly_co_app_self_age_edit_txt.getText().toString().isEmpty()) {
            pl_Ly_co_app_self_age_edit_txt.setError(getText(R.string.err_curent));
            pl_Ly_co_app_self_age_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_app_ind_no_of_vehicle_edit_txt(){
        if (pl_co_app_ind_no_of_vehicle_edit_txt.getText().toString().isEmpty()) {
            pl_co_app_ind_no_of_vehicle_edit_txt.setError(getText(R.string.err_curent));
            pl_co_app_ind_no_of_vehicle_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_app_ind_no_of_years_work_ind_edit_txt(){
        if (pl_co_app_ind_no_of_years_work_ind_edit_txt.getText().toString().isEmpty()) {
            pl_co_app_ind_no_of_years_work_ind_edit_txt.setError(getText(R.string.err_curent));
            pl_co_app_ind_no_of_years_work_ind_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_app_ind_avg_monthly_incom_edit_txt(){
        if (pl_co_app_ind_avg_monthly_incom_edit_txt.getText().toString().isEmpty()) {
            pl_co_app_ind_avg_monthly_incom_edit_txt.setError(getText(R.string.err_curent));
            pl_co_app_ind_avg_monthly_incom_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_app_f_no_of_acres_edit_txt(){
        if (pl_co_app_f_no_of_acres_edit_txt.getText().toString().isEmpty()) {
            pl_co_app_f_no_of_acres_edit_txt.setError(getText(R.string.err_curent));
            pl_co_app_f_no_of_acres_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_app_F_anual_income_edit_txt(){
        if (pl_co_app_F_anual_income_edit_txt.getText().toString().isEmpty()) {
            pl_co_app_F_anual_income_edit_txt.setError(getText(R.string.err_curent));
            pl_co_app_F_anual_income_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_app_f_daily_income_f(){
        if (pl_co_app_f_daily_income_f.getText().toString().isEmpty()) {
            pl_co_app_f_daily_income_f.setError(getText(R.string.err_curent));
            pl_co_app_f_daily_income_f.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_app_F_number_of_years_in_work(){
        if (pl_co_app_F_number_of_years_in_work.getText().toString().isEmpty()) {
            pl_co_app_F_number_of_years_in_work.setError(getText(R.string.err_curent));
            pl_co_app_F_number_of_years_in_work.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_app_F_average_monthly_income(){
        if (pl_co_app_F_average_monthly_income.getText().toString().isEmpty()) {
            pl_co_app_F_average_monthly_income.setError(getText(R.string.err_curent));
            pl_co_app_F_average_monthly_income.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_D_no_of_animals(){
        if (pl_co_D_no_of_animals.getText().toString().isEmpty()) {
            pl_co_D_no_of_animals.setError(getText(R.string.err_curent));
            pl_co_D_no_of_animals.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_D_no_of_liters_edit_txt(){
        if (pl_co_D_no_of_liters_edit_txt.getText().toString().isEmpty()) {
            pl_co_D_no_of_liters_edit_txt.setError(getText(R.string.err_curent));
            pl_co_D_no_of_liters_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_app_self_D_no_of_years_in_works(){
        if (pl_co_app_self_D_no_of_years_in_works.getText().toString().isEmpty()) {
            pl_co_app_self_D_no_of_years_in_works.setError(getText(R.string.err_curent));
            pl_co_app_self_D_no_of_years_in_works.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_app_D_avg_monthly_income(){
        if (pl_co_app_D_avg_monthly_income.getText().toString().isEmpty()) {
            pl_co_app_D_avg_monthly_income.setError(getText(R.string.err_curent));
            pl_co_app_D_avg_monthly_income.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_P_no_of_birds_edit_txt(){
        if (pl_co_P_no_of_birds_edit_txt.getText().toString().isEmpty()) {
            pl_co_P_no_of_birds_edit_txt.setError(getText(R.string.err_curent));
            pl_co_P_no_of_birds_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_P_supply_by_who(){
        if (pl_co_P_supply_by_who.getText().toString().isEmpty()) {
            pl_co_P_supply_by_who.setError(getText(R.string.err_curent));
            pl_co_P_supply_by_who.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_P_Selling_Price(){
        if (pl_co_P_Selling_Price.getText().toString().isEmpty()) {
            pl_co_P_Selling_Price.setError(getText(R.string.err_curent));
            pl_co_P_Selling_Price.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_p_Profit_affter_selling(){
        if (pl_co_p_Profit_affter_selling.getText().toString().isEmpty()) {
            pl_co_p_Profit_affter_selling.setError(getText(R.string.err_curent));
            pl_co_p_Profit_affter_selling.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_P_no_of_years_in_work_P(){
        if (pl_co_P_no_of_years_in_work_P.getText().toString().isEmpty()) {
            pl_co_P_no_of_years_in_work_P.setError(getText(R.string.err_curent));
            pl_co_P_no_of_years_in_work_P.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_p_avg_monthly_income_Poultry(){
        if (pl_co_p_avg_monthly_income_Poultry.getText().toString().isEmpty()) {
            pl_co_p_avg_monthly_income_Poultry.setError(getText(R.string.err_curent));
            pl_co_p_avg_monthly_income_Poultry.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_own_self_delership_company_edit_txt(){
        if (pl_co_own_self_delership_company_edit_txt.getText().toString().isEmpty()) {
            pl_co_own_self_delership_company_edit_txt.setError(getText(R.string.err_curent));
            pl_co_own_self_delership_company_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_own_self_monthly_profit_edit_txt(){
        if (pl_co_own_self_monthly_profit_edit_txt.getText().toString().isEmpty()) {
            pl_co_own_self_monthly_profit_edit_txt.setError(getText(R.string.err_curent));
            pl_co_own_self_monthly_profit_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_monthly_income_own_ser_bus_edit_txt(){
        if (pl_co_monthly_income_own_ser_bus_edit_txt.getText().toString().isEmpty()) {
            pl_co_monthly_income_own_ser_bus_edit_txt.setError(getText(R.string.err_curent));
            pl_co_monthly_income_own_ser_bus_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_no_of_employee_own_ser_bus_edit_txt(){
        if (pl_co_no_of_employee_own_ser_bus_edit_txt.getText().toString().isEmpty()) {
            pl_co_no_of_employee_own_ser_bus_edit_txt.setError(getText(R.string.err_curent));
            pl_co_no_of_employee_own_ser_bus_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_business_investment_own_ser_bus_edit_txt(){
        if (pl_co_business_investment_own_ser_bus_edit_txt.getText().toString().isEmpty()) {
            pl_co_business_investment_own_ser_bus_edit_txt.setError(getText(R.string.err_curent));
            pl_co_business_investment_own_ser_bus_edit_txt.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_value_of_stock_raw_material(){
        if (pl_co_value_of_stock_raw_material.getText().toString().isEmpty()) {
            pl_co_value_of_stock_raw_material.setError(getText(R.string.err_curent));
            pl_co_value_of_stock_raw_material.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_monthly_sales_manufa(){
        if (pl_co_monthly_sales_manufa.getText().toString().isEmpty()) {
            pl_co_monthly_sales_manufa.setError(getText(R.string.err_curent));
            pl_co_monthly_sales_manufa.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_value_of_machineries(){
        if (pl_co_value_of_machineries.getText().toString().isEmpty()) {
            pl_co_value_of_machineries.setError(getText(R.string.err_curent));
            pl_co_value_of_machineries.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Validate_pl_co_Own_number_of_years_in_work_retails(){
        if (pl_co_Own_number_of_years_in_work_retails.getText().toString().isEmpty()) {
            pl_co_Own_number_of_years_in_work_retails.setError(getText(R.string.err_curent));
            pl_co_Own_number_of_years_in_work_retails.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Validate_pl_co_own_average_monthly_income_own_business(){
        if (pl_co_own_average_monthly_income_own_business.getText().toString().isEmpty()) {
            pl_co_own_average_monthly_income_own_business.setError(getText(R.string.err_curent));
            pl_co_own_average_monthly_income_own_business.requestFocus();
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
                            Pl_self_ind_Type_of_employement =object.getJSONArray("Type_of_employement");
                            other_earning_ar =object.getJSONArray("other_earning");
                            employee_id_ar =object.getJSONArray("employee_id");
                            have_pan_ar =object.getJSONArray("have_pan");
                            Assets_own =object.getJSONArray("Assets_own");
                            crop_type =object.getJSONArray("crop_type");

                            vocaton_ar =object.getJSONArray("vocaton");
                            franchise =object.getJSONArray("franchise");

                            vocation_type_forming_ar =object.getJSONArray("vocation_type");
                            Business_Proof =object.getJSONArray("Business_Proof");
                            Business_income_proof =object.getJSONArray("Business_income_proof");
                            office_shop =object.getJSONArray("office_shop");
                            Office_residence =object.getJSONArray("Office_residence");
                            vehicle_Type =object.getJSONArray("vehicle_Type");
                            sell_milk =object.getJSONArray("sell_milk");
                            Business_type_own_business =object.getJSONArray("Business_type");

                            Salry_method_Spinner(Salary_method_ar);
                            pl_wt_kind_of_crope(crop_type);
                          //  Salry_Proof(Salary_proof_ar);
                            assets_owner(Assets_own);
                            assets_owner_salaried(Assets_own);
                            Self_Assets_Owned(Assets_own);
                            salary_proof_salaried(Salary_proof_ar);
                            salary_proof_salaried_app1(Salary_proof_ar);
                            self_ind_vehicle_type(vehicle_Type);

                            Residence_Array(Residence_ownership_ar);
                            Employee_ID_Array(employee_id_ar);
                            HAVE_PAN_Card(have_pan_ar);
                            Employement =object.getJSONArray("Employement");
                            is_coapplicant =object.getJSONArray("is_coapplicant");
                            Co_Type_Of_Employement_Spinner(Employement);
                            DO_Have_Co_Applicant(is_coapplicant);
                           // Other_Earning(other_earning_ar);
                            pl_self_ind_Vocation(vocaton_ar);
                            Pl_self_ind_Type_of_employement_(Pl_self_ind_Type_of_employement);
                            pl_co_self_Office_Shop_(office_shop);
                            pl_co_self_Office_own_Rent(Office_residence);
                            pl_vocation_type_forming(vocation_type_forming_ar);

                            Selling_milk(sell_milk);
                            Runs_own_business_franchise(franchise);
                            Runs_own_business_Vintage_Proof(Business_Proof);
                            Runs_own_business_Proof(Business_income_proof);
                            Business_type_own_business_Array(Business_type_own_business);

                            Log.e("Salary_proof_ar",String.valueOf(Salary_proof_ar));

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

                        Pref.put_Residence_ID(mCon,residence_id);

                        if(residence_id.equals("2") )
                        {
                            monthly_afr_emi_txt.setText("14");

                            residence_live.setVisibility(View.VISIBLE);

                            other_family_mem.setVisibility(View.GONE);
                          //  asstes_own.setVisibility(View.GONE);

                            other_earning_avbl.setVisibility(View.GONE);

                            age.setText("1");
                            monthly_sal_txt.setText("2");
                            salery_credite_method_txt.setText("3");
                            Exp_in_current_txt.setText("4");
                            total_workexperiecnce_txt.setText("5");
                            cmp_pincode_txt.setText("6");
                            area_txt.setText("7");
                            txt_residence_pincode.setText("9");
                            res_area_txt.setText("10");
                            txt_residence_type.setText("11");


                            Lives_in_current_txt.setText("12");
                            do_you_have_coApp_txt.setText("13");
                            coApp_txt_emp_type1.setText("14");


                        }else
                        {

                            residence_live.setVisibility(View.GONE);

                            other_family_mem.setVisibility(View.GONE);
                          //  asstes_own.setVisibility(View.GONE);

                            other_earning_avbl.setVisibility(View.GONE);

                            age.setText("1");
                            monthly_sal_txt.setText("2");
                            salery_credite_method_txt.setText("3");
                            Exp_in_current_txt.setText("4");
                            total_workexperiecnce_txt.setText("5");
                            cmp_pincode_txt.setText("6");
                            area_txt.setText("7");
                            txt_residence_pincode.setText("9");
                            res_area_txt.setText("10");
                            txt_residence_type.setText("11");
                            do_you_have_coApp_txt.setText("12");
                            coApp_txt_emp_type1.setText("13");

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

    private void pl_vocation_type_forming(final JSONArray vocation_type_forming_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

        vocation_type_forming__SA = new String[vocation_type_forming_ar.length()];
        for (int i=0;i<vocation_type_forming_ar.length();i++){
            JSONObject J =  vocation_type_forming_ar.getJSONObject(i);
            vocation_type_forming__SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(vocation_type_forming__SA));
            vocation_type_forming_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            vocation_type_forming_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            pl_co_self_spi_vocation_forming.setAdapter(vocation_type_forming_Adapter);
            pl_co_self_spi_vocation_forming.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {



                        pl_co_s_forming_vocation_type_forming_id = vocation_type_forming_ar.getJSONObject(position).getString("id");
                        pl_co_s_forming_vocation_type_forming_value = vocation_type_forming_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("vocaton_id", pl_co_s_forming_vocation_type_forming_id);
                        Log.d("vocaton_value", pl_co_s_forming_vocation_type_forming_value);

                        if(pl_co_s_forming_vocation_type_forming_id.equals("1"))
                        {
                            pl_forming.setVisibility(View.VISIBLE);
                            pl_dairy.setVisibility(View.GONE);
                            pl_poultry.setVisibility(View.GONE);

                        }else if(pl_co_s_forming_vocation_type_forming_id.equals("2"))
                        {
                            pl_forming.setVisibility(View.GONE);
                            pl_dairy.setVisibility(View.VISIBLE);
                            pl_poultry.setVisibility(View.GONE);
                        }else if(pl_co_s_forming_vocation_type_forming_id.equals("3"))
                        {
                            pl_forming.setVisibility(View.GONE);
                            pl_dairy.setVisibility(View.GONE);
                            pl_poultry.setVisibility(View.VISIBLE);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_self_spi_vocation_forming.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void assets_owner(final JSONArray ja) throws JSONException {

        assets_owned_proof = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            assets_owned_proof.add(salary_proof);
        }
        dataAdapter_assets_owned = new MyCustomAdapter_Assets_owned(context, 0,assets_owned_proof);
        assets_owned_sppiner.setAdapter(dataAdapter_assets_owned);
        dataAdapter_assets_owned.notifyDataSetChanged();
    }

    private void assets_owner_salaried(final JSONArray ja) throws JSONException {

        assets_owned_salaried = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            assets_owned_salaried.add(salary_proof);
        }
        assets_owned_salaried_Adapter = new MyCustomAdapter_assets_owned_Salaried(context, 0,assets_owned_salaried);
        salaried_assets_owned_sppiner.setAdapter(assets_owned_salaried_Adapter);
        assets_owned_salaried_Adapter.notifyDataSetChanged();
    }

    private void salary_proof_salaried_app1(final JSONArray ja) throws JSONException {

        salary_proof_arr_list_app = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            salary_proof_arr_list_app.add(salary_proof);
        }
        salary_proof_salaried_Adapter_APP = new MyCustomAdapter_salary_proof_Salaried_App(context, 0,salary_proof_arr_list);
        spinner_salary_proof.setAdapter(salary_proof_salaried_Adapter_APP);
        salary_proof_salaried_Adapter_APP.notifyDataSetChanged();
    }

    private void salary_proof_salaried(final JSONArray ja) throws JSONException {

        salary_proof_arr_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            salary_proof_arr_list.add(salary_proof);
        }
        salary_proof_salaried_Adapter = new MyCustomAdapter_salary_proof_Salaried(context, 0,salary_proof_arr_list);
        salaried_salary_proof_sppiner.setAdapter(salary_proof_salaried_Adapter);
        salary_proof_salaried_Adapter.notifyDataSetChanged();
    }

    private void self_ind_vehicle_type(final JSONArray ja) throws JSONException {

        vehicle_Type_arr_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            vehicle_Type_arr_list.add(salary_proof);
        }
        self_vehicle_adapter = new MyCustomAdapter_vehicle_type_self(context, 0,vehicle_Type_arr_list);
        co_self_ind_vehicle_type.setAdapter(self_vehicle_adapter);
        self_vehicle_adapter.notifyDataSetChanged();
    }

    private void pl_wt_kind_of_crope(final JSONArray ja) throws JSONException {

        what_kind_of_crope_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            what_kind_of_crope_list.add(salary_proof);
        }
        what_crop_adapter = new MyCustomAdapter_what_crope_self(context, 0,what_kind_of_crope_list);
        pl_co_app_what_crop_spinne.setAdapter(what_crop_adapter);
        what_crop_adapter.notifyDataSetChanged();
    }



    private void Runs_own_business_Vintage_Proof(final JSONArray ja) throws JSONException {

        co_self_bus_vintage_proof_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            co_self_bus_vintage_proof_list.add(salary_proof);
        }
        business_vintage_Adapter = new MyCustomAdapter_Business_Vintage_proof(context, 0,co_self_bus_vintage_proof_list);
        co_self_bus_vintage_proof.setAdapter(business_vintage_Adapter);
        business_vintage_Adapter.notifyDataSetChanged();
    }


    private void Runs_own_business_Proof(final JSONArray ja) throws JSONException {

        co_self_bus_own_proof_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            co_self_bus_own_proof_list.add(salary_proof);
        }
        business_Adapter = new MyCustomAdapter_Business_proof(context, 0,co_self_bus_own_proof_list);
        co_self_bussiness_proof.setAdapter(business_Adapter);
        business_Adapter.notifyDataSetChanged();
    }

    private void Self_Assets_Owned(final JSONArray ja) throws JSONException {

        co_self_Assets_owned_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            co_self_Assets_owned_list.add(salary_proof);
        }
        Co_my_self_Assets_Owned_adapter = new MyCustomAdapter_Self_Assets_owned(context, 0,co_self_Assets_owned_list);
        co_self_asstes_owned.setAdapter(Co_my_self_Assets_Owned_adapter);
        Co_my_self_Assets_Owned_adapter.notifyDataSetChanged();
    }


    private class MyCustomAdapter_Assets_owned extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> ASSETS_OWNED;
        IncomeProofPOJO assets_owned;
        public MyCustomAdapter_Assets_owned(Context context, int textViewResourceId,
                                        ArrayList<IncomeProofPOJO> ASSETS_OWNED) {
            super(context, textViewResourceId, ASSETS_OWNED);
            this.ASSETS_OWNED = new ArrayList<IncomeProofPOJO>();
            this.ASSETS_OWNED.addAll(ASSETS_OWNED);
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

            MyCustomAdapter_Assets_owned.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Assets_owned.ViewHolder();
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
                            cv_assets_ownned.addChip(email, imgUrl, contact);
                        } else {
                            cv_assets_ownned.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Assets_owned.ViewHolder) convertView.getTag();
            }

            assets_owned = ASSETS_OWNED.get(position);
            holder.name.setText(assets_owned.getIP_name());
            holder.name.setChecked(assets_owned.isIP_selected());
            holder.name.setTag(assets_owned);

            if(assets_owned.getIP_name().contains("Assets Own")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Assets Own");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }
    private class MyCustomAdapter_assets_owned_Salaried extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> ASSETS_OWNED_SALARIED;
        IncomeProofPOJO assets_owned_salaried;
        public MyCustomAdapter_assets_owned_Salaried(Context context, int textViewResourceId,
                                            ArrayList<IncomeProofPOJO> ASSETS_OWNED_SALARIED) {
            super(context, textViewResourceId, ASSETS_OWNED_SALARIED);
            this.ASSETS_OWNED_SALARIED = new ArrayList<IncomeProofPOJO>();
            this.ASSETS_OWNED_SALARIED.addAll(ASSETS_OWNED_SALARIED);
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

            MyCustomAdapter_assets_owned_Salaried.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_assets_owned_Salaried.ViewHolder();
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
                            salaried_cv_assets_ownned.addChip(email, imgUrl, contact);
                        } else {
                            salaried_cv_assets_ownned.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_assets_owned_Salaried.ViewHolder) convertView.getTag();
            }

            assets_owned_salaried = ASSETS_OWNED_SALARIED.get(position);
            holder.name.setText(assets_owned_salaried.getIP_name());
            holder.name.setChecked(assets_owned_salaried.isIP_selected());
            holder.name.setTag(assets_owned_salaried);

            if(assets_owned_salaried.getIP_name().contains("Assets Own")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Assets Own");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }

    private class MyCustomAdapter_salary_proof_Salaried_App extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> SALARIED_PROOF_SALARIED_App;
        IncomeProofPOJO salary_proof_salaried_App;
        public MyCustomAdapter_salary_proof_Salaried_App(Context context, int textViewResourceId,
                                                         ArrayList<IncomeProofPOJO> SALARIED_PROOF_SALARIED_App) {
            super(context, textViewResourceId, SALARIED_PROOF_SALARIED_App);
            this.SALARIED_PROOF_SALARIED_App = new ArrayList<IncomeProofPOJO>();
            this.SALARIED_PROOF_SALARIED_App.addAll(SALARIED_PROOF_SALARIED_App);
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

            MyCustomAdapter_salary_proof_Salaried_App.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_salary_proof_Salaried_App.ViewHolder();
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
                holder = (MyCustomAdapter_salary_proof_Salaried_App.ViewHolder) convertView.getTag();
            }

            salary_proof_salaried_App = SALARIED_PROOF_SALARIED_App.get(position);
            holder.name.setText(salary_proof_salaried_App.getIP_name());
            holder.name.setChecked(salary_proof_salaried_App.isIP_selected());
            holder.name.setTag(salary_proof_salaried_App);

            if(salary_proof_salaried_App.getIP_name().contains("Salary Proof")){
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

    private class MyCustomAdapter_salary_proof_Salaried extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> SALARIED_PROOF_SALARIED;
        IncomeProofPOJO salary_proof_salaried;
        public MyCustomAdapter_salary_proof_Salaried(Context context, int textViewResourceId,
                                                     ArrayList<IncomeProofPOJO> SALARIED_PROOF_SALARIED) {
            super(context, textViewResourceId, SALARIED_PROOF_SALARIED);
            this.SALARIED_PROOF_SALARIED = new ArrayList<IncomeProofPOJO>();
            this.SALARIED_PROOF_SALARIED.addAll(SALARIED_PROOF_SALARIED);
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

            MyCustomAdapter_salary_proof_Salaried.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_salary_proof_Salaried.ViewHolder();
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
                            salaried_cv_salary_proof.addChip(email, imgUrl, contact);
                        } else {
                            salaried_cv_salary_proof.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_salary_proof_Salaried.ViewHolder) convertView.getTag();
            }

            salary_proof_salaried = SALARIED_PROOF_SALARIED.get(position);
            holder.name.setText(salary_proof_salaried.getIP_name());
            holder.name.setChecked(salary_proof_salaried.isIP_selected());
            holder.name.setTag(salary_proof_salaried);

            if(salary_proof_salaried.getIP_name().contains("Salary Proof")){
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

    private class MyCustomAdapter_vehicle_type_self extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> SELF_VEHICLE_ARR_LIST;
        IncomeProofPOJO vehicle_type_self;
        public MyCustomAdapter_vehicle_type_self(Context context, int textViewResourceId,
                                                     ArrayList<IncomeProofPOJO> SELF_VEHICLE_ARR_LIST) {
            super(context, textViewResourceId, SELF_VEHICLE_ARR_LIST);
            this.SELF_VEHICLE_ARR_LIST = new ArrayList<IncomeProofPOJO>();
            this.SELF_VEHICLE_ARR_LIST.addAll(SELF_VEHICLE_ARR_LIST);
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

            MyCustomAdapter_vehicle_type_self.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_vehicle_type_self.ViewHolder();
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
                            self_cv_vehicle_type.addChip(email, imgUrl, contact);
                        } else {
                            self_cv_vehicle_type.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_vehicle_type_self.ViewHolder) convertView.getTag();
            }

            vehicle_type_self = SELF_VEHICLE_ARR_LIST.get(position);
            holder.name.setText(vehicle_type_self.getIP_name());
            holder.name.setChecked(vehicle_type_self.isIP_selected());
            holder.name.setTag(vehicle_type_self);

            if(vehicle_type_self.getIP_name().contains("-Select vehicle Type-")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Select vehicle Type");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }

    private class MyCustomAdapter_what_crope_self extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> SELF_F_WHAT_CROP_ARR_LIST;
        IncomeProofPOJO what_crop_pojo;
        public MyCustomAdapter_what_crope_self(Context context, int textViewResourceId,
                                                 ArrayList<IncomeProofPOJO> SELF_F_WHAT_CROP_ARR_LIST) {
            super(context, textViewResourceId, SELF_F_WHAT_CROP_ARR_LIST);
            this.SELF_F_WHAT_CROP_ARR_LIST = new ArrayList<IncomeProofPOJO>();
            this.SELF_F_WHAT_CROP_ARR_LIST.addAll(SELF_F_WHAT_CROP_ARR_LIST);
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

            MyCustomAdapter_what_crope_self.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_what_crope_self.ViewHolder();
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
                            cv_what_kindof_crop.addChip(email, imgUrl, contact);
                        } else {
                            cv_what_kindof_crop.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_what_crope_self.ViewHolder) convertView.getTag();
            }

            what_crop_pojo = SELF_F_WHAT_CROP_ARR_LIST.get(position);
            holder.name.setText(what_crop_pojo.getIP_name());
            holder.name.setChecked(what_crop_pojo.isIP_selected());
            holder.name.setTag(what_crop_pojo);

            if(what_crop_pojo.getIP_name().contains("What Kind of Crops (Multiselect)")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select What Kind of Crops");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }

    private class MyCustomAdapter_Business_Vintage_proof extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> VINTAGE_PROOF_LIST ;
        IncomeProofPOJO business_vintage_proof_pojo;

        public MyCustomAdapter_Business_Vintage_proof(Context context, int textViewResourceId,
                                               ArrayList<IncomeProofPOJO> VINTAGE_PROOF_LIST) {
            super(context, textViewResourceId, VINTAGE_PROOF_LIST);
            this.VINTAGE_PROOF_LIST = new ArrayList<IncomeProofPOJO>();
            this.VINTAGE_PROOF_LIST.addAll(VINTAGE_PROOF_LIST);
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

            MyCustomAdapter_Business_Vintage_proof.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Business_Vintage_proof.ViewHolder();
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
                            self_cv_bus_vintage_proof.addChip(email, imgUrl, contact);
                        } else {
                            self_cv_bus_vintage_proof.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Business_Vintage_proof.ViewHolder) convertView.getTag();
            }

            business_vintage_proof_pojo = VINTAGE_PROOF_LIST.get(position);
            holder.name.setText(business_vintage_proof_pojo.getIP_name());
            holder.name.setChecked(business_vintage_proof_pojo.isIP_selected());
            holder.name.setTag(business_vintage_proof_pojo);

            if(business_vintage_proof_pojo.getIP_name().contains("Business Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Business Vintage Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }

    private class MyCustomAdapter_Business_proof extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> SELF_BUSINESS_PROOF_LIST;
        IncomeProofPOJO Self_business_proof_pojo;

        public MyCustomAdapter_Business_proof(Context context, int textViewResourceId,
                                                      ArrayList<IncomeProofPOJO> VINTAGE_PROOF_LIST) {
            super(context, textViewResourceId, VINTAGE_PROOF_LIST);
            this.SELF_BUSINESS_PROOF_LIST = new ArrayList<IncomeProofPOJO>();
            this.SELF_BUSINESS_PROOF_LIST.addAll(VINTAGE_PROOF_LIST);
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

            MyCustomAdapter_Business_proof.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Business_proof.ViewHolder();
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
                            self_cv_business_proof.addChip(email, imgUrl, contact);
                        } else {
                            self_cv_business_proof.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Business_proof.ViewHolder) convertView.getTag();
            }

            Self_business_proof_pojo = SELF_BUSINESS_PROOF_LIST.get(position);
            holder.name.setText(Self_business_proof_pojo.getIP_name());
            holder.name.setChecked(Self_business_proof_pojo.isIP_selected());
            holder.name.setTag(Self_business_proof_pojo);

            if(Self_business_proof_pojo.getIP_name().contains("Business Income Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Business Income Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }


    private class MyCustomAdapter_Self_Assets_owned extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> SELF_CO_ASSETS_OWNED_LIST;
        IncomeProofPOJO Co_Business_Self_Assets_pojo;

        public MyCustomAdapter_Self_Assets_owned(Context context, int textViewResourceId,
                                              ArrayList<IncomeProofPOJO> SELF_CO_ASSETS_OWNED_LIST) {
            super(context, textViewResourceId, SELF_CO_ASSETS_OWNED_LIST);
            this.SELF_CO_ASSETS_OWNED_LIST = new ArrayList<IncomeProofPOJO>();
            this.SELF_CO_ASSETS_OWNED_LIST.addAll(SELF_CO_ASSETS_OWNED_LIST);
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

            MyCustomAdapter_Self_Assets_owned.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Self_Assets_owned.ViewHolder();
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
                            co_self_asstes_owned_CV.addChip(email, imgUrl, contact);
                        } else {
                            co_self_asstes_owned_CV.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Self_Assets_owned.ViewHolder) convertView.getTag();
            }

            Co_Business_Self_Assets_pojo = SELF_CO_ASSETS_OWNED_LIST.get(position);
            holder.name.setText(Co_Business_Self_Assets_pojo.getIP_name());
            holder.name.setChecked(Co_Business_Self_Assets_pojo.isIP_selected());
            holder.name.setTag(Co_Business_Self_Assets_pojo);

            if(Co_Business_Self_Assets_pojo.getIP_name().contains("Assets Own")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Assets Own");

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
                        Pref.putCOAPPSALARYTYPE(context,CO_Type_of_employement_ID);
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
                            co_applicant_salaried_employed.setVisibility(View.GONE);
                            co_applicant_self_employed.setVisibility(View.GONE);
                           // do_you_have_coApp_txt.setText("8");

                        }else
                        {
                            co_applicant_emp_type.setVisibility(View.GONE);
                            co_applicant_salaried_employed.setVisibility(View.GONE);
                            co_applicant_self_employed.setVisibility(View.GONE);
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

    ///Co Apllicant Auto Completer Pin Code

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
            String workpincode4 = pl_co_app_office_residence_pincode_edite_txt.getText().toString();

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
            if(workpincode4.length()> 2){
                pl_co_app_office_residence_pincode_edite_txt.setThreshold(2);
                pl_co_app_office_residence_pincode_edite_txt.setAdapter(Pincode_Adapter);
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

        pl_co_app_office_residence_pincode_edite_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);

                if(code.length()==6){
                    GET_AERA_POST3(code);
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
          //  String workpincode1 = residence_pincode1_edit_txt.getText().toString();

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

    private void GET_AERA_POST3(String code) {
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
                                setArea3(response);
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

    private void setArea3(final JSONArray ja) throws JSONException {

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
            pl_co_self_office_spinner_residence_type.setAdapter(A_Area);
            pl_co_self_office_spinner_residence_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        pl_co_app_self_res_spinn_area_id = ja.getJSONObject(position).getString("id");
                        pl_co_app_self_res_spinn_area_district_id = ja.getJSONObject(position).getString("district_id");
                        pl_co_app_self_res_spinn_area_state_id = ja.getJSONObject(position).getString("state_id");

                        //   Log.e("Drop Down",a);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_self_office_spinner_residence_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

    }

    private void pl_self_ind_Vocation(final JSONArray vocaton_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

        Vocation_SA = new String[vocaton_ar.length()];
        for (int i=0;i<vocaton_ar.length();i++){
            JSONObject J =  vocaton_ar.getJSONObject(i);
            Vocation_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Vocation_SA));
            Vocation_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Vocation_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            pl_co_app_self_spi_vocation_type_.setAdapter(Vocation_Adapter);
            pl_co_app_self_spi_vocation_type_.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        pl_self_ind_vocaton_id = vocaton_ar.getJSONObject(position).getString("id");
                        pl_self_ind_vocaton_value = vocaton_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("vocaton_id", pl_self_ind_vocaton_id);
                        Log.d("vocaton_value", pl_self_ind_vocaton_value);

                        if(pl_self_ind_vocaton_id.equals("7"))
                        {
                            pl_self_ind_Driver_C_owner.setVisibility(View.VISIBLE);

                        }else
                        {
                            pl_self_ind_Driver_C_owner.setVisibility(View.GONE);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_self_spi_vocation_type_.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Pl_self_ind_Type_of_employement_(final JSONArray Type_of_employement_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        EMPLOYEE_TYPE_SA = new String[Type_of_employement_ar.length()];
        for (int i=0;i<Type_of_employement_ar.length();i++){
            JSONObject J =  Type_of_employement_ar.getJSONObject(i);
            EMPLOYEE_TYPE_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(EMPLOYEE_TYPE_SA));
            Employee_Type_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Employee_Type_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            pl_Ly_co_app_self_emp_type.setAdapter(Employee_Type_adapter);
            pl_Ly_co_app_self_emp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        pl_self_ind_Employee_type_Id = Type_of_employement_ar.getJSONObject(position).getString("id");
                        pl_self_ind_Employee_type_Value = Type_of_employement_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("pl_Employee_type_Id", pl_self_ind_Employee_type_Id);
                        Log.d("pl_self_ind", pl_self_ind_Employee_type_Value);

                        Pref.putCOEMPTYPE(context,pl_self_ind_Employee_type_Id);

                        int b = Integer.parseInt(pl_self_ind_Employee_type_Id);
                        // pl_self_individual,pl_formin_dairy,pl_self_business
                        switch(b) {
                            case 1:
                                pl_self_individual.setVisibility(View.VISIBLE);
                                pl_formin_dairy.setVisibility(View.GONE);
                                pl_self_business.setVisibility(View.GONE);
                                break;
                            case 2:
                                pl_self_individual.setVisibility(View.GONE);
                                pl_formin_dairy.setVisibility(View.VISIBLE);
                                pl_self_business.setVisibility(View.GONE);
                                break;
                            case 3:

                                String Loantype = "3";
                                //  Pref.putEMPLOYMENT(mCon,Loantype);
                                Pref.putLoanType(mCon,Loantype);
                                pl_self_individual.setVisibility(View.GONE);
                                pl_formin_dairy.setVisibility(View.GONE);
                                pl_self_business.setVisibility(View.VISIBLE);
                                break;
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_Ly_co_app_self_emp_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void pl_co_self_Office_own_Rent(final JSONArray office_shop_ar) throws JSONException {



        Office_Shop_own_SA = new String[office_shop_ar.length()];
        for (int i=0;i<office_shop_ar.length();i++){
            JSONObject J =  office_shop_ar.getJSONObject(i);
            Office_Shop_own_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Office_Shop_own_SA));
            Office_Shop_own_SA_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Office_Shop_own_SA_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            pl_co_app_ind_spinner_office_ownership_Type.setAdapter(Office_Shop_own_SA_Adapter);
           /*
            spinner_office_shop_setup_far.setAdapter(Office_Shop__Adapter);*/

            pl_co_app_ind_spinner_office_ownership_Type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        pl_co_app_ind_Office_Shop_Own_id = office_shop_ar.getJSONObject(position).getString("id");
                        pl_co_app_ind_Office_Shop_Own_value = office_shop_ar.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_ind_spinner_office_ownership_Type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

        }

    }

    private void pl_co_self_Office_Shop_(final JSONArray office_shop_ar) throws JSONException {

        Office_Shop_SA = new String[office_shop_ar.length()];
        for (int i=0;i<office_shop_ar.length();i++){
            JSONObject J =  office_shop_ar.getJSONObject(i);
            Office_Shop_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Office_Shop_SA));
            Office_Shop__Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Office_Shop__Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            pl_co_app_ind_spinner_office_shop_setup_ind.setAdapter(Office_Shop__Adapter);
           /*
            spinner_office_shop_setup_far.setAdapter(Office_Shop__Adapter);*/

            pl_co_app_ind_spinner_office_shop_setup_ind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        pl_co_app_ind_Office_Shop__id = office_shop_ar.getJSONObject(position).getString("id");
                        pl_co_app_ind_Office_Shop__value = office_shop_ar.getJSONObject(position).getString("value");

                        if(pl_co_app_ind_Office_Shop__id.equals("2"))
                        {
                            pl_co_self_ofiice_res_details.setVisibility(View.VISIBLE);
                        }else
                        {
                            pl_co_self_ofiice_res_details.setVisibility(View.GONE);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            pl_co_app_ind_spinner_office_shop_setup_ind.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

        }

    }

    private void Selling_milk(final JSONArray selling_milk_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

        Selling_Milk_SA = new String[selling_milk_ar.length()];
        for (int i=0;i<selling_milk_ar.length();i++){
            JSONObject J =  selling_milk_ar.getJSONObject(i);
            Selling_Milk_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Selling_Milk_SA));
            Selling_Milk_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Selling_Milk_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            co_self_D_spinner_how_do_sell_milk.setAdapter(Selling_Milk_Adapter);

            co_self_D_spinner_how_do_sell_milk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        co_self_D_selling_milk_id = selling_milk_ar.getJSONObject(position).getString("id");
                        co_self_D_selling_milk_value = selling_milk_ar.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_self_D_spinner_how_do_sell_milk.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    // InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    return false;
                }
            });
        }

    }

    private void Runs_own_business_franchise(final JSONArray franchise_ar) throws JSONException {

        franchise_SA = new String[franchise_ar.length()];
        for (int i=0;i<franchise_ar.length();i++){
            JSONObject J =  franchise_ar.getJSONObject(i);
            franchise_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(franchise_SA));
            franchise__Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            franchise__Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            co_self_fran_spinner_frenc_deler_sub.setAdapter(franchise__Adapter);
            co_self_fran_spinner_frenc_deler_sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        pl_co_self_franchise__id = franchise_ar.getJSONObject(position).getString("id");
                        pl_co_self_franchise__value = franchise_ar.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_self_fran_spinner_frenc_deler_sub.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Business_type_own_business_Array(final JSONArray Business_type_own_business_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

        Own_business_type_SA = new String[Business_type_own_business_ar.length()];
        for (int i=0;i<Business_type_own_business_ar.length();i++){
            JSONObject J =  Business_type_own_business_ar.getJSONObject(i);
            Own_business_type_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Own_business_type_SA));
            Own_business_type_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Own_business_type_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_busines_type_own_business.setAdapter(Own_business_type_Adapter);
            spinner_busines_type_own_business.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        pl_co_own_business_own_type_id = Business_type_own_business_ar.getJSONObject(position).getString("id");
                        pl_co_own_business_own_type_Value = Business_type_own_business_ar.getJSONObject(position).getString("value");

                        if(pl_co_own_business_own_type_id.equals("1"))
                        {
                            pl_co_Retail_wholesale_business.setVisibility(View.VISIBLE);
                            pl_service_business.setVisibility(View.GONE);
                            pl_manufacturing.setVisibility(View.GONE);

                        }else if(pl_co_own_business_own_type_id.equals("2"))
                        {
                            pl_co_Retail_wholesale_business.setVisibility(View.GONE);
                            pl_service_business.setVisibility(View.VISIBLE);
                            pl_manufacturing.setVisibility(View.GONE);
                        }else if(pl_co_own_business_own_type_id.equals("3"))
                        {
                            pl_co_Retail_wholesale_business.setVisibility(View.GONE);
                            pl_service_business.setVisibility(View.GONE);
                            pl_manufacturing.setVisibility(View.VISIBLE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_busines_type_own_business.setOnTouchListener(new View.OnTouchListener() {
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
        St_monthly_net_sal_edit_txt = monthly_net_sal_edit_txt.getText().toString();

        ST_experience_in_current_cmpy = experience_in_current_cmpy.getText().toString();
        ST_total_experience_edit_txt = total_experience_edit_txt.getText().toString();
        ST_company_pincode_txt = company_pincode_txt.getText().toString();
        ST_residence_pincode1_edit_txt = residence_pincode1_edit_txt.getText().toString();
        ST_current_residence_edit_txt = current_residence_edit_txt.getText().toString();
        ST_monthly_afr_emi_amt_edit_txt = monthly_afr_emi_amt_edit_txt.getText().toString();


        //co_applicant_salaried
        ST_pl_co_app_slrd_name_edite_txt = pl_co_app_slrd_name_edite_txt.getText().toString();
        ST_pl_co_app_slrd_age_edite_txt = pl_co_app_slrd_age_edite_txt.getText().toString();
        ST_pl_co_app_slrd_month_net_slrd_edite_txt = pl_co_app_slrd_month_net_slrd_edite_txt.getText().toString();
        ST_pl_co_app_slrd_experience_in_current_cmpy = pl_co_app_slrd_experience_in_current_cmpy.getText().toString();
        ST_pl_co_app_slrd_total_experience_edit_txt = pl_co_app_slrd_total_experience_edit_txt.getText().toString();
        ST_pl_co_app_slrd_company_pincode_txt = pl_co_app_slrd_company_pincode_txt.getText().toString();

        //co_applicant_self
        ST_pl_Ly_co_app_self_edit_txt_name = pl_Ly_co_app_self_edit_txt_name.getText().toString();
        ST_pl_Ly_co_app_self_age_edit_txt = pl_Ly_co_app_self_age_edit_txt.getText().toString();
        ST_pl_co_app_ind_no_of_vehicle_edit_txt = pl_co_app_ind_no_of_vehicle_edit_txt.getText().toString();
        ST_pl_co_app_ind_no_of_years_work_ind_edit_txt = pl_co_app_ind_no_of_years_work_ind_edit_txt.getText().toString();
        ST_pl_co_app_ind_avg_monthly_incom_edit_txt = pl_co_app_ind_avg_monthly_incom_edit_txt.getText().toString();

        //forming
        ST_pl_co_app_f_no_of_acres_edit_txt = pl_co_app_f_no_of_acres_edit_txt.getText().toString();
        ST_pl_co_app_F_anual_income_edit_txt = pl_co_app_F_anual_income_edit_txt.getText().toString();
        ST_pl_co_app_f_daily_income_f = pl_co_app_f_daily_income_f.getText().toString();
        ST_pl_co_app_F_number_of_years_in_work = pl_co_app_F_number_of_years_in_work.getText().toString();
        ST_pl_co_app_F_average_monthly_income = pl_co_app_F_average_monthly_income.getText().toString();

        //dairy
        ST_pl_co_D_no_of_animals = pl_co_D_no_of_animals.getText().toString();
        ST_pl_co_D_no_of_liters_edit_txt = pl_co_D_no_of_liters_edit_txt.getText().toString();
        ST_pl_co_app_self_D_no_of_years_in_works = pl_co_app_self_D_no_of_years_in_works.getText().toString();
        ST_pl_co_app_D_avg_monthly_income = pl_co_app_D_avg_monthly_income.getText().toString();

        //poulty
        ST_pl_co_P_no_of_birds_edit_txt = pl_co_P_no_of_birds_edit_txt.getText().toString();
        ST_pl_co_P_supply_by_who = pl_co_P_supply_by_who.getText().toString();
        ST_pl_co_P_Selling_Price = pl_co_P_Selling_Price.getText().toString();
        ST_pl_co_p_Profit_affter_selling = pl_co_p_Profit_affter_selling.getText().toString();
        ST_pl_co_P_no_of_years_in_work_P = pl_co_P_no_of_years_in_work_P.getText().toString();




        //own business fran
        ST_pl_co_own_self_delership_company_edit_txt = pl_co_own_self_delership_company_edit_txt.getText().toString();
        ST_pl_co_own_self_monthly_profit_edit_txt = pl_co_own_self_monthly_profit_edit_txt.getText().toString();
        //
        ST_pl_co_monthly_income_own_ser_bus_edit_txt = pl_co_monthly_income_own_ser_bus_edit_txt.getText().toString();
        ST_pl_co_no_of_employee_own_ser_bus_edit_txt = pl_co_no_of_employee_own_ser_bus_edit_txt.getText().toString();
        ST_pl_co_business_investment_own_ser_bus_edit_txt = pl_co_business_investment_own_ser_bus_edit_txt.getText().toString();
        //

        ST_pl_co_value_of_stock_raw_material = pl_co_value_of_stock_raw_material.getText().toString();
        ST_pl_co_monthly_sales_manufa = pl_co_monthly_sales_manufa.getText().toString();
        ST_pl_co_value_of_machineries = pl_co_value_of_machineries.getText().toString();

        /////

         ST_pl_co_Own_number_of_years_in_work_retails = pl_co_Own_number_of_years_in_work_retails.getText().toString();
         ST_pl_co_own_average_monthly_income_own_business = pl_co_own_average_monthly_income_own_business.getText().toString();


         ///comm for self


        String stringNumber = St_monthly_net_sal_edit_txt;
        result = stringNumber.replace(",","");
        JSONObject jsonObject =new JSONObject();
        JSONObject applicant =new JSONObject();
        JSONObject applicant1 =new JSONObject();
        JSONObject applicant_salaried =new JSONObject();
        JSONObject applicant_self_individual =new JSONObject();
        try {
            applicant.put("age",String_value_Age);
            applicant.put("net_salary",St_monthly_net_sal_edit_txt);
            applicant.put("salary_mode",Salary_id);
            applicant1.put("income_proof",salary_proof_salary_array_App);
            applicant.put("work_experiance",ST_experience_in_current_cmpy);
            applicant.put("tot_work_experiance",ST_total_experience_edit_txt);
            applicant.put("work_pincode",ST_company_pincode_txt);
            applicant.put("ofc_area",company_area);
            applicant.put("assets",assets_owned_array);
            applicant.put("res_pincode",ST_residence_pincode1_edit_txt);
            applicant.put("per_area",res_company_area);
            applicant.put("res_type",residence_id);
            applicant.put("live_in_res",ST_current_residence_edit_txt);
            applicant.put("emp_statues",1);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            if(IS_CO_Applicant_Id.equals("1"))
            {
                applicant_count =2;
                if(CO_Type_of_employement_ID.equals("1"))
                {
                    applicant1.put("member_name",ST_pl_co_app_slrd_name_edite_txt);
                    applicant1.put("age",ST_pl_co_app_slrd_age_edite_txt);
                    applicant1.put("net_salary",ST_pl_co_app_slrd_month_net_slrd_edite_txt);
                    applicant1.put("work_experiance",ST_pl_co_app_slrd_experience_in_current_cmpy);
                    applicant1.put("tot_work_experiance",ST_pl_co_app_slrd_total_experience_edit_txt);
                    applicant1.put("work_pincode",ST_pl_co_app_slrd_company_pincode_txt);
                    applicant1.put("salary_mode",pl_co_app_slrd_Salary_id);
                    applicant1.put("income_proof",salary_proof_salary_array);
                    applicant1.put("ofc_area",pl_co_app_slrd_res_spinn_area_id);
                    applicant1.put("assets",assets_owned_salary_array);
                    applicant1.put("emp_statues",1);

                }else if(CO_Type_of_employement_ID.equals("2"))
                {
                    co_applicant_salaried_employed.setVisibility(View.GONE);
                    co_applicant_self_employed.setVisibility(View.VISIBLE);
                    applicant1.put("emp_statues",3);
                    applicant1.put("bus_employment_type",pl_self_ind_Employee_type_Id);
                    applicant1.put("member_name",ST_pl_Ly_co_app_self_edit_txt_name);
                    applicant1.put("age",ST_pl_Ly_co_app_self_age_edit_txt);

                    int b = Integer.parseInt(pl_self_ind_Employee_type_Id);
                    // pl_self_individual,pl_formin_dairy,pl_self_business
                    switch(b) {
                            case 1:
                                applicant1.put("business_vocation",pl_self_ind_vocaton_id);
                                applicant1.put("work_experiance",ST_pl_co_app_ind_no_of_years_work_ind_edit_txt);
                                applicant1.put("net_salary",ST_pl_co_app_ind_avg_monthly_incom_edit_txt);
                                break;
                        case 2:
                                applicant1.put("business_vocation",pl_co_s_forming_vocation_type_forming_id);
                                if(pl_co_s_forming_vocation_type_forming_id.equals("1"))
                                {
                                    applicant1.put("work_experiance",ST_pl_co_app_F_number_of_years_in_work);
                                    applicant1.put("net_salary",ST_pl_co_app_F_average_monthly_income);

                                }else if(pl_co_s_forming_vocation_type_forming_id.equals("2"))
                                {
                                    applicant1.put("work_experiance",ST_pl_co_app_self_D_no_of_years_in_works);
                                    applicant1.put("net_salary",ST_pl_co_app_D_avg_monthly_income);

                                }else if(pl_co_s_forming_vocation_type_forming_id.equals("3"))
                                {
                                    applicant1.put("work_experiance",ST_pl_co_P_no_of_years_in_work_P);
                                    applicant1.put("net_salary",pl_co_p_avg_monthly_income_Poultry);
                                }
                                break;
                        case 3:
                                applicant1.put("business_vocation",pl_co_own_business_own_type_id);
                                if(pl_co_own_business_own_type_id.equals("1"))
                                {
                                    applicant1.put("rel_income",ST_pl_co_own_self_monthly_profit_edit_txt);
                                }else if(pl_co_own_business_own_type_id.equals("2"))
                                {
                                    applicant1.put("rel_income",ST_pl_co_monthly_income_own_ser_bus_edit_txt);
                                }else if(pl_co_own_business_own_type_id.equals("3"))
                                {

                                }
                                applicant1.put("work_experiance",ST_pl_co_Own_number_of_years_in_work_retails);
                                applicant1.put("net_salary",ST_pl_co_own_average_monthly_income_own_business);
                                break;
                             }
                        //ind
                            applicant1.put("vehicle_type",vehicle_type_array);
                            applicant1.put("no_of_vehicles",ST_pl_co_app_ind_no_of_vehicle_edit_txt);
                         //f
                            applicant1.put("crop_types",what_crop_array);
                            applicant1.put("acres",ST_pl_co_app_f_no_of_acres_edit_txt);
                            applicant1.put("annual_income",ST_pl_co_app_F_anual_income_edit_txt);
                            applicant1.put("daily_income",ST_pl_co_app_f_daily_income_f);
                            applicant1.put("no_of_animals",ST_pl_co_D_no_of_animals);
                            applicant1.put("no_of_litres",ST_pl_co_D_no_of_liters_edit_txt);
                            applicant1.put("sell_milk_to",co_self_D_selling_milk_id);
                            applicant1.put("no_of_birds",ST_pl_co_P_no_of_birds_edit_txt);
                            applicant1.put("company_supplied",ST_pl_co_P_supply_by_who);
                            applicant1.put("selling_price",ST_pl_co_P_Selling_Price);
                            applicant1.put("profit",ST_pl_co_p_Profit_affter_selling);
                            //own
                            applicant1.put("dealership_name",ST_pl_co_own_self_delership_company_edit_txt);
                            applicant1.put("is_franchise",pl_co_self_franchise__id);
                            applicant1.put("bus_employee_count",ST_pl_co_no_of_employee_own_ser_bus_edit_txt);
                            applicant1.put("setup_investment",ST_pl_co_business_investment_own_ser_bus_edit_txt);
                            applicant1.put("value_of_stock",ST_pl_co_value_of_stock_raw_material);
                            applicant1.put("monthly_sales",ST_pl_co_monthly_sales_manufa);
                            applicant1.put("value_of_machineries",ST_pl_co_value_of_machineries);
                            applicant1.put("bus_proof",business_vintage_self);
                            applicant1.put("income_proof",business_proof_self);
                            applicant1.put("office_setup",pl_co_app_ind_Office_Shop__id);
                            applicant1.put("assets",self_co_assets_);
                            applicant1.put("emp_statues",IS_CO_Applicant_Id);
                }

            }else {
                applicant_count =1;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject J= null;
        try {
            J =new JSONObject();
            //  J.put(Params.email_id,email);
            J.put("applicant_count",applicant_count);
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("applicant",applicant);
            J.put("co_applicant",applicant1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Add Home Laoan", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.VIABILITY_CHECK, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);

                        Log.e("Add_Home_loan Partner", String.valueOf(response));
                        try {
                            JSONObject jsonObject1 = response.getJSONObject("response");
                            if(jsonObject1.getString("applicant_status").equals("success")) {

                                 if(jsonObject1.getString("viablity_status").equals("success"))
                                 {
                                     Toast.makeText(context,"Viability Created Successfully",Toast.LENGTH_SHORT).show();

                                     Intent intent = new Intent(Viability_Check_PL.this, Eligibility_Check_PL.class);
                                     intent.putExtra("user_id", user_id);
                                     intent.putExtra("transaction_id", transaction_id);
                                     startActivity(intent);
                                     finish();
                                 }else if(jsonObject1.getString("viablity_status").equals("error"))
                                 {
                                     Toast.makeText(context,"Viability Failed",Toast.LENGTH_SHORT).show();

                                     String viability_array =jsonObject1.getString("viability_arr");
                                     Intent intent = new Intent(Viability_Check_PL.this, Loan_Viyability_Check_Activity.class);
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

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
        finish();
        super.onBackPressed();

    }
}
