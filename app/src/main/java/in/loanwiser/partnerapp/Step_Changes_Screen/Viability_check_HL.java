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
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
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
import in.loanwiser.partnerapp.PartnerActivitys.IncomeProofPOJO;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Viability_check_HL extends SimpleActivity {

    Spinner spnr_type_of_empmnt;
    LinearLayout salaried,self_employed;

    private Spinner spinner1,spnr_property_title,spnr_Property_Identified;

    private AppCompatTextView property_identified_typ_txt,property_identified_typ_txt1,property_title_txt,
                                property_title2,type_of_empmnt_txt,type_of_empmnt_txt1;

    private AppCompatEditText Property_identified;

    AppCompatButton lead_viy_step2;
    private AlertDialog progressDialog;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    Typeface font;
    private Context context = this;
    JSONArray Residence_ownership_ar,Property_Type,Employement,Property_title;

    String[] Property_Identified,Property_Title,Type_Of_Emp_SA;
    ArrayAdapter Property_Identified_Adapter,Property_Title_Adapter,Type_Of_Emp_Adapter;
    String  Property_Identified_ID,Property_Identified_Value,Property_Title_ID,Property_Title_Value,
            Type_of_employement_ID,Type_of_employement_Value;

    ///Salaried

    private Spinner spinner_employe_id,spinn_salary_crt_mtd,
            spinner_salary_proof,has_pan_card_spnr,Other_family_income_spnr,
            spnr_property_category,spnr_property_type,spnr_approval_of_land,spnr_bulding_approval,
            spnr_da_bulding_approval;

    LinearLayout residence_type,residence_live,pan_card_available,other_earning_avbl,
            propert_identified_ly,property_identified_Ly1,pro_details;

    RadioGroup has_pan_card,applicant_family_OEM;
    RadioButton yes_pan,no_pan,other__OEM_family_yes,other_OEM_family_no;
    AppCompatTextView age,age1,pan_number_txt,pan_number_txt1,Pan_number_txt,Pan_number1_txt,
            occupation_txt,occupation_txt1,monthly_sal_txt,monthly_sal_txt1,emp_id,emp_id1,
            salery_credite_method_txt,salery_credite_method_txt1,salary_proof_txt,salary_proof_txt1,Exp_in_current_txt,
            Exp_in_current_txt1,total_workexperiecnce_txt,total_workexperiecnce_txt1,
            cmp_pincode_txt,cmp_pincode_txt1,txt_residence_pincode,txt_residence_pincode1,txt_residence_type,
            txt_residence_type1,Lives_in_current_txt,Lives_in_current_txt1,any_other_family_member_txt,
            any_other_family_member_txt1,family_member_name_txt,family_member_name_txt1,family_member_income_txt,
            family_member_income_txt1,monthly_afr_emi_txt,monthly_afr_emi_txt1,property_category_txt,property_category_txt1,
            property_type_txt,property_type_txt1,approval_of_land_txt,approval_of_land_txt1,
            bulding_approval_txt,bulding_approval_txt1,da_bulding_approval_txt,da_bulding_approval_txt1,
            txt_property_pincode,txt_property_pincode1;

    AppCompatEditText age_edite_txt,pan_number_edit_txt,occupation_edit_txt,monthly_net_sal_edit_txt,
            experience_in_current_cmpy,total_experience_edit_txt
            ,current_residence_edit_txt,family_member_name_edit_txt,
            family_member_income_edit_txt,monthly_afr_emi_amt_edit_txt;
    AppCompatAutoCompleteTextView company_pincode_txt,residence_pincode1_edit_txt;

    InputMethodManager imm;
    JSONArray ja= new JSONArray();
    JSONArray Salary_method_ar,Salary_proof_ar,employee_id_ar,have_pan_ar,
            other_earning_ar;
    String[] SPINNERLIST;
    String[] SALARY_Method,Salary_Proof,Residence_Type_SA,Employe_ID_SA,PAN_ID_SA,Other_Earning_SA,Pincode_SA;

    ArrayAdapter<String> Salary_Adapter,Salary_proof_Adapter,Residence_Adapter,Employee_ID_Adapter,
            PAN_ID_Adapter,Other_Earning_Adapter,Pincode_Adapter,Property_Category_Adapter,Property_Type_Adapter,
            Approval_of_Land_Adapter,Bulding_Approval_Adapter,DA_Bulding_Adapter;

    String String_value_Age,ST_occupation_edit_txt,St_monthly_net_sal_edit_txt,
            ST_experience_in_current_cmpy,ST_total_experience_edit_txt,
            ST_company_pincode_txt,ST_residence_pincode1_edit_txt,
            ST_current_residence_edit_txt,ST_monthly_afr_emi_amt_edit_txt,result;

    String Salary_id,Salary_Value,Salary_proof_id,Salary_proof_Value,residence_id,residence_Value,
            Employee_id,Employee_Value,PAN_id,PAN_Value, other_earning_id,other_earning_value;

    ArrayList<IncomeProofPOJO> IncomeProof_Salaried,Salary_income_Proof;
    MyCustomAdapter_Salary_Proof dataAdapter_Salaried_proof = null;
    private ChipsView cv_salary_income_proof;
    StringBuffer salary_proof_list;

    ///selfEmployeed

    private Spinner emp_type,spi_vocation_type_,spinner_residence_type,office_spinner_residence_type,
            spi_vocation_forming,spinner_busines_type,
            has_pan_card_self,business_incom_proof,business_incom_proof_forming,
            spinner_busines_type_own_business,spi_busproof_individual,
            spinner_assets_owned,spinner_business_proof_txt_F,spi_busproof_own_business,
            spinner_office_shop_setup,business_proof_own_business_spinner,spp_vehicle_type,
            what_crop_spinne,business_incom_proof_D,spinner_business_proof_txt_D,
            spinner_how_do_sell_milk,business_incom_proof_p,spinner_business_proof_txt_P,
            spinner_frenc_deler_sub,spinner_residence_type_self;

    private LinearLayout individual,self_business,formin_dairy,Driver_C_owner,
            res_rented,forming,dairy,poultry,
            Retail_wholesale_business,service_business,manufacturing,ofiice_res_details;


    JSONArray Type_of_employement,have_pan_ar_self,vocaton_ar,Business_income_proof_ar,
            vocation_type_forming_ar,Residence_ownership_ar_self,Business_type_own_business,Business_Proof,Assets_own,
            office_shop,vehicle_Type,crop_type,sell_milk,franchise,property_identified,property_category,
            land_approval,building_approval,DA_approval;

    String[] EMPLOYEE_TYPE_SA,PAN_ID_SA_self,Vocation_SA,Business_income_proof_SA,vocation_type_forming__SA,
            Residence_Type_SA_self,Own_business_type_SA,Selling_Milk_SA,
            Office_Shop_SA,franchise_SA,Property_Category_SA,Property_Type_SA,Approval_of_Land_SA,
            Bulding_Approval_SA,DA_Bulding_Approval;

    ArrayAdapter<String> Employee_Type_adapter,PAN_ID_Adapter_self,Vocation_Adapter,Business_income_proof_Adapter,
            vocation_type_forming_Adapter,Residence_Adapter_Self,Own_business_type_Adapter,
            Office_Shop__Adapter,Selling_Milk_Adapter,franchise__Adapter;

    ChipsView cv_vusiness_proof_individual,cv_Assets_Owns,cv_business_proof_multiselect_forming,cv_business_proof_own,
            cv_vehicle_type,cv_what_kindof_crop,cv_Business_proof_dairy,
            cv_Business_proof_poultry;

    AppCompatTextView business_details_txt,emp_type1,emp_type2,age_txt,age_txt1,pan_number_txt_self,
            pan_number_txt1_self,residence_details,txt_residence_pincode_self,txt_residence_pincode1_self,txt_residence_type_self,
            txt_residence_type1_self,current_recidence_txt,current_recidence_txt1,assets_owned_txt,assets_owned_txt1,
            vocation_indiviual_txt,vocation_indiviual_txt1,busines_inco_proof_individua_txt,busines_inco_proof_individua_txt1,
            vehicle_individual_txt,vehicle_individual_txt1,number_of_vehicle_ind_txt,number_of_vehicle_ind_txt1,no_of_year_ind_txt,
            no_of_year_ind_txt1,monthly_incom_txt,monthly_incom_txt1,busproof_ind_txt,busproof_ind_txt1;

    AppCompatEditText age_edit_txt,residence_edite_txt,live_curentres_edite_txt,no_of_vehicle_edit_txt,
            no_of_years_ind_edit_txt,avg_monthly_incom_edit_txt,
            actual_business_ind_edit_txt,no_of_years_work_ind_edit_txt,actual_business_edit_forming_txt,
            no_of_acres_edit_txt,anual_income_edit_txt,daily_income_f,number_of_years_in_work,average_monthly_income,
            no_of_animals,no_of_liters_edit_txt,no_of_years_in_works,avg_monthly_income,
            no_of_birds_edit_txt,supply_by_who,Selling_Price,Profit_affter_selling,no_of_years_in_work_P,
            avg_monthly_income_Poultry,actual_business_edit_own_edt_txt,
            delership_company_edit_txt,monthly_profit_edit_txt,number_of_years_in_work_retails,
            monthly_income_own_ser_bus_edit_txt,no_of_employee_own_ser_bus_edit_txt,business_investment_own_ser_bus_edit_txt,
            value_of_stock_raw_material,monthly_sales_manufa,monthly_profit_manufa,
            average_monthly_income_own_business,value_of_machineries,salary_proof_edit_txt;



    AutoCompleteTextView residence_pincode_edite_txt,office_residence_pincode_edite_txt,
            property_pincode1_edit_txt;


    String Employee_type_Id,Employee_type_Value,PAN_id_self,PAN_Value_self,
            vocaton_id,vocaton_value, Business_income_proof_id,Business_income_proof_value,
            vocation_type_forming_id, vocation_type_forming_value,business_incom_proof_forming_id,
            business_incom_proof_forming_value,  residence_id_self,residence_Value_self,
            business_own_type_id,business_own_type_Value,
            Office_Shop__id,Office_Shop__value,off_residence_id,off_residence_Value,
            business_incom_proof_own_business_id,business_incom_proof_own_business_value,
            business_incom_proof_Dairy_id,business_incom_proof_Dairy_value,
            selling_milk_id,selling_milk_value,business_incom_proof_Poultry_id,business_incom_proof_Poultry_value,
            franchise__id,franchise__value;

    String Propery_Category_ID,Propery_Category_Value,Propery_Type_ID,Propery_Type_Value,
            Approval_of_Land_ID,Approval_of_Land_Value,Bulding_Approval_Id,Bulding_Approval_Value,
            DA_Bulding_ID,DA_Bulding_Value;

    ArrayList<IncomeProofPOJO> Business_proof_individual,Assets_own_array_list,Business_proof_individual_forming_array_list,
            Business_proof_own_array_list,Vehicle_type_individual,crop_type_array_list,
            Business_proof_forming_dairy_array_list,Business_proof_forming_poultry_array_list;

    MyCustomAdapter_Business_proof_Individual business_proof_individual_adapter = null;
    MyCustomAdapter_Vehicle_Type_Adapter vehicle_type_adapter = null;
    MyCustomAdapter_Business_proof_Forming business_proof_Forming_adapter = null;
    MyCustomAdapter_Business_proof_Forming_dairy business_proof_Forming_dairy_adapter = null;
    MyCustomAdapter_Business_proof_Forming_poultry business_proof_Forming_poultry_adapter = null;
    MyCustomAdapter_Business_proof_Own business_proof_Own_Business = null;
    MyCustomAdapter_Assets_own_adapter Assets_own_adapter = null;

    MyCustomAdapter_Crop_Type crop_type_adapter = null;

    String salary_type,loan_type_id,Lontype,Co_salary_type,is_co_available;

    LinearLayout salary_mul_select;
    AppCompatTextView salary_proof_edit_txt1,self_proof_edit_txt,business_proof_type_text,vehicle_type_text,
            self_frm_what_crop_textview,self_frm_business_proof_txt,self_own_business_proof_txt;

    LinearLayout co_applicant_salaried_employed,co_applicant_self_employed;

    ArrayList<String> myList_values,Assets_myList_values,business_proof_self_list,vehicle_proof_self_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_viability_check__hl);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viability_check__hl);
        initTools(R.string.viy_check);

        progressDialog = new SpotsDialog(Viability_check_HL.this, R.style.Custom);
        lead_viy_step2 = (AppCompatButton) findViewById(R.id.lead_viy_step2);

        salary_mul_select = (LinearLayout) findViewById(R.id.salary_mul_select);
        salary_proof_edit_txt1 = (AppCompatTextView) findViewById(R.id.salary_proof_edit_txt);
        Log.e("viability check Hl ","Secure Loan");



        vehicle_type_text = (AppCompatTextView) findViewById(R.id.vehicle_type_text);
        self_proof_edit_txt = (AppCompatTextView) findViewById(R.id.self_proof_edit_txt);
        self_frm_what_crop_textview = (AppCompatTextView) findViewById(R.id.self_frm_what_crop_textview);
        self_frm_business_proof_txt = (AppCompatTextView) findViewById(R.id.self_frm_business_proof_txt);
        self_own_business_proof_txt = (AppCompatTextView) findViewById(R.id.self_own_business_proof_txt);

        co_applicant_salaried_employed = (LinearLayout) findViewById(R.id.co_applicant_salaried_employed);
        co_applicant_self_employed = (LinearLayout) findViewById(R.id.co_applicant_self_employed);

        loan_type_id = Pref.getLoanType(context);
        salary_type = Pref.getSALARYTYPE(context);
        Co_salary_type = Pref.getCOSALARYTYPE(context);
        is_co_available = Pref.getCoAPPAVAILABLE(context);

      //  salary_proof_edit_txt = (AppCompatEditText) findViewById(R.id.salary_proof_edit_txt);

        if(is_co_available.equals("1"))
        {

            if(Co_salary_type.equals("1"))
            {
                co_applicant_salaried_employed.setVisibility(View.VISIBLE);
                co_applicant_self_employed.setVisibility(View.GONE);

            }else if(Co_salary_type.equals("2"))
            {
                co_applicant_salaried_employed.setVisibility(View.GONE);
                co_applicant_self_employed.setVisibility(View.VISIBLE);
            }

        }else
        {
            co_applicant_salaried_employed.setVisibility(View.GONE);
            co_applicant_self_employed.setVisibility(View.GONE);
        }


        myList_values = (ArrayList<String>) getIntent().getSerializableExtra("select_lid_id");
        Assets_myList_values = (ArrayList<String>) getIntent().getSerializableExtra("select_lid_id");
        business_proof_self_list = (ArrayList<String>) getIntent().getSerializableExtra("select_lid_id");
        vehicle_proof_self_list = (ArrayList<String>) getIntent().getSerializableExtra("select_lid_id");


        UISCREEN();
        fonts();
     //  Click();
        makeJsonObjReq1();

        lead_viy_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Viability_check_HL.this, Eligibility_HL.class);
                startActivity(intent2);
            }
        });

        //multiselect functionality


      /*  vehicle_type_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_check_HL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", vehicle_Type.toString());
                intent.putExtra("select_lid_id", (Serializable) vehicle_proof_self_list);
                startActivity(intent);
            }
        });

        business_proof_type_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_check_HL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Business_Proof.toString());
                intent.putExtra("select_lid_id", (Serializable) business_proof_self_list);
                startActivity(intent);

            }
        });

        self_proof_edit_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_check_HL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Assets_own.toString());
                intent.putExtra("select_lid_id", (Serializable) Assets_myList_values);
                startActivity(intent);

            }
        });

        salary_proof_edit_txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_check_HL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Salary_proof_ar.toString());
                intent.putExtra("select_lid_id", (Serializable) myList_values);
                startActivity(intent);


            }
        });

        self_frm_what_crop_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_check_HL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", crop_type.toString());
                intent.putExtra("select_lid_id", (Serializable) business_proof_self_list);
                startActivity(intent);

            }
        });
        self_frm_business_proof_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_check_HL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Business_Proof.toString());
                intent.putExtra("select_lid_id", (Serializable) business_proof_self_list);
                startActivity(intent);

            }
        });

        self_own_business_proof_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_check_HL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Business_Proof.toString());
                intent.putExtra("select_lid_id", (Serializable) business_proof_self_list);
                startActivity(intent);

            }
        });*/

        //
        if(loan_type_id.equals("1") || loan_type_id.equals("3") || loan_type_id.equals("4"))
        {
            pro_details.setVisibility(View.VISIBLE);
            propert_identified_ly.setVisibility(View.VISIBLE);
        }else
        {
            pro_details.setVisibility(View.VISIBLE);
            propert_identified_ly.setVisibility(View.GONE);
            property_identified_Ly1.setVisibility(View.VISIBLE);
        }

        if(salary_type.equals("0"))
        {
            salaried.setVisibility(View.GONE);
            self_employed.setVisibility(View.GONE);

        }else if(salary_type.equals("1"))
        {
            salaried.setVisibility(View.VISIBLE);
            self_employed.setVisibility(View.GONE);
        }else if(salary_type.equals("2"))
        {
            salaried.setVisibility(View.GONE);
            self_employed.setVisibility(View.VISIBLE);
        }

    }

    private void UISCREEN() {

        spnr_property_title =(Spinner) findViewById(R.id.spnr_property_title);
        spnr_Property_Identified =(Spinner) findViewById(R.id.spnr_Property_Identified);
        salaried = (LinearLayout) findViewById(R.id.salaried);
        self_employed = (LinearLayout) findViewById(R.id.self_employed);
        pro_details = (LinearLayout) findViewById(R.id.pro_details);

        propert_identified_ly = (LinearLayout) findViewById(R.id.propert_identified_ly);
        property_identified_Ly1 = (LinearLayout) findViewById(R.id.property_identified_Ly1);

        spnr_type_of_empmnt = (Spinner) findViewById(R.id.spnr_type_of_empmnt);

        property_identified_typ_txt = (AppCompatTextView) findViewById(R.id.property_identified_typ_txt);
        property_identified_typ_txt1 = (AppCompatTextView) findViewById(R.id.property_identified_typ_txt1);
        property_title_txt = (AppCompatTextView) findViewById(R.id.property_title_txt);
        property_title2 = (AppCompatTextView) findViewById(R.id.property_title2);

        property_category_txt = (AppCompatTextView) findViewById(R.id.property_category_txt);
        property_category_txt1 = (AppCompatTextView) findViewById(R.id.property_category_txt1);
        property_type_txt = (AppCompatTextView) findViewById(R.id.property_type_txt);
        property_type_txt1 = (AppCompatTextView) findViewById(R.id.property_type_txt1);
        approval_of_land_txt = (AppCompatTextView) findViewById(R.id.approval_of_land_txt);
        approval_of_land_txt1 = (AppCompatTextView) findViewById(R.id.approval_of_land_txt1);
        bulding_approval_txt = (AppCompatTextView) findViewById(R.id.bulding_approval_txt);
        bulding_approval_txt1 = (AppCompatTextView) findViewById(R.id.bulding_approval_txt1);
        da_bulding_approval_txt = (AppCompatTextView) findViewById(R.id.da_bulding_approval_txt);
        da_bulding_approval_txt1 = (AppCompatTextView) findViewById(R.id.da_bulding_approval_txt1);
        txt_property_pincode = (AppCompatTextView) findViewById(R.id.txt_property_pincode);
        txt_property_pincode1 = (AppCompatTextView) findViewById(R.id.txt_property_pincode1);
        ///Salaried PL

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
        pan_number_txt_self = (AppCompatTextView) findViewById(R.id.pan_number_txt_self);
        pan_number_txt1_self = (AppCompatTextView) findViewById(R.id.pan_number_txt1_self);
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
        txt_residence_type_self = (AppCompatTextView) findViewById(R.id.txt_residence_type_self);
        txt_residence_type1_self = (AppCompatTextView) findViewById(R.id.txt_residence_type1_self);
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
        ///////////////////////////////////////////////////////////////////////////////////////////


        ////////////////SELF EMPLOYEED/////////////////////////////////////////////////////

        lead_viy_step2 = (AppCompatButton) findViewById(R.id.lead_viy_step2);
        emp_type =(Spinner) findViewById(R.id.emp_type);
        has_pan_card_self =(Spinner) findViewById(R.id.has_pan_card_self);
        spnr_property_category =(Spinner) findViewById(R.id.spnr_property_category);
        spi_vocation_type_ =(Spinner) findViewById(R.id.spi_vocation_type_);
        spinner_residence_type_self =(Spinner) findViewById(R.id.spinner_residence_type_self);
        office_spinner_residence_type =(Spinner) findViewById(R.id.office_spinner_residence_type);
        spi_vocation_forming =(Spinner) findViewById(R.id.spi_vocation_forming);
        spinner_how_do_sell_milk =(Spinner) findViewById(R.id.spinner_how_do_sell_milk);
        //  spinner_busines_type =(Spinner) findViewById(R.id.spinner_busines_type);
        spinner_busines_type_own_business =(Spinner) findViewById(R.id.spinner_busines_type_own_business);

        what_crop_spinne =(Spinner) findViewById(R.id.what_crop_spinne);
        spinner_business_proof_txt_F =(Spinner) findViewById(R.id.spinner_business_proof_txt_F);
        spinner_business_proof_txt_D =(Spinner) findViewById(R.id.spinner_business_proof_txt_D);
        spinner_business_proof_txt_P =(Spinner) findViewById(R.id.spinner_business_proof_txt_P);
        spinner_assets_owned =(Spinner) findViewById(R.id.spinner_assets_owned);
        spp_vehicle_type =(Spinner) findViewById(R.id.spp_vehicle_type);

        spinner_frenc_deler_sub =(Spinner) findViewById(R.id.spinner_frenc_deler_sub);

        spnr_property_category = (Spinner) findViewById(R.id.spnr_property_category);
        spnr_property_type = (Spinner) findViewById(R.id.spnr_property_type);
        spnr_approval_of_land = (Spinner) findViewById(R.id.spnr_approval_of_land);
        spnr_bulding_approval = (Spinner) findViewById(R.id.spnr_bulding_approval);
        spnr_da_bulding_approval = (Spinner) findViewById(R.id.spnr_da_bulding_approval);

        spi_busproof_own_business =(Spinner) findViewById(R.id.spi_busproof_own_business);


        individual = (LinearLayout) findViewById(R.id.individual);
        formin_dairy = (LinearLayout) findViewById(R.id.formin_dairy);
        self_business = (LinearLayout) findViewById(R.id.self_business);
        Driver_C_owner = (LinearLayout) findViewById(R.id.Driver_C_owner);
        res_rented = (LinearLayout) findViewById(R.id.res_rented);
        forming = (LinearLayout) findViewById(R.id.forming);
        dairy = (LinearLayout) findViewById(R.id.dairy);
        poultry = (LinearLayout) findViewById(R.id.poultry);


        cv_Assets_Owns =(ChipsView) findViewById(R.id.cv_Assets_Owns);
        cv_business_proof_multiselect_forming =(ChipsView) findViewById(R.id.cv_business_proof_multiselect_forming);

        cv_business_proof_own =(ChipsView) findViewById(R.id.cv_business_proof_own);
        cv_vehicle_type =(ChipsView) findViewById(R.id.cv_vehicle_type);
        cv_what_kindof_crop =(ChipsView) findViewById(R.id.cv_what_kindof_crop);

        residence_pincode_edite_txt = (AutoCompleteTextView) findViewById(R.id.residence_pincode_edite_txt);
        office_residence_pincode_edite_txt = (AutoCompleteTextView) findViewById(R.id.office_residence_pincode_edite_txt);
        property_pincode1_edit_txt = (AutoCompleteTextView) findViewById(R.id.property_pincode1_edit_txt);
        Retail_wholesale_business = (LinearLayout) findViewById(R.id.Retail_wholesale_business);
        service_business = (LinearLayout) findViewById(R.id.service_business);
        manufacturing = (LinearLayout) findViewById(R.id.manufacturing);
        ofiice_res_details = (LinearLayout) findViewById(R.id.ofiice_res_details);

        business_details_txt = (AppCompatTextView) findViewById(R.id.business_details_txt);
        emp_type1 = (AppCompatTextView) findViewById(R.id.emp_type1);
        emp_type2 = (AppCompatTextView) findViewById(R.id.emp_type2);
        age_txt = (AppCompatTextView) findViewById(R.id.age_txt);
        age_txt1 = (AppCompatTextView) findViewById(R.id.age_txt1);
        pan_number_txt = (AppCompatTextView) findViewById(R.id.pan_number_txt);
        pan_number_txt1 = (AppCompatTextView) findViewById(R.id.pan_number_txt1);
        residence_details = (AppCompatTextView) findViewById(R.id.residence_details);
        txt_residence_pincode = (AppCompatTextView) findViewById(R.id.txt_residence_pincode);
        txt_residence_pincode1 = (AppCompatTextView) findViewById(R.id.txt_residence_pincode1);
        txt_residence_type = (AppCompatTextView) findViewById(R.id.txt_residence_type);
        txt_residence_type1 = (AppCompatTextView) findViewById(R.id.txt_residence_type1);
        current_recidence_txt = (AppCompatTextView) findViewById(R.id.current_recidence_txt);
        current_recidence_txt1 = (AppCompatTextView) findViewById(R.id.current_recidence_txt1);
        assets_owned_txt = (AppCompatTextView) findViewById(R.id.assets_owned_txt);
        assets_owned_txt1 = (AppCompatTextView) findViewById(R.id.assets_owned_txt1);
        vocation_indiviual_txt = (AppCompatTextView) findViewById(R.id.vocation_indiviual_txt);
        vocation_indiviual_txt1 = (AppCompatTextView) findViewById(R.id.vocation_indiviual_txt1);
        busines_inco_proof_individua_txt = (AppCompatTextView) findViewById(R.id.busines_inco_proof_individua_txt);
        busines_inco_proof_individua_txt1 = (AppCompatTextView) findViewById(R.id.busines_inco_proof_individua_txt1);
        vehicle_individual_txt = (AppCompatTextView) findViewById(R.id.vehicle_individual_txt);
        vehicle_individual_txt1 = (AppCompatTextView) findViewById(R.id.vehicle_individual_txt1);
        number_of_vehicle_ind_txt = (AppCompatTextView) findViewById(R.id.number_of_vehicle_ind_txt);
        number_of_vehicle_ind_txt1 = (AppCompatTextView) findViewById(R.id.number_of_vehicle_ind_txt1);
        no_of_year_ind_txt = (AppCompatTextView) findViewById(R.id.no_of_year_ind_txt);
        no_of_year_ind_txt1 = (AppCompatTextView) findViewById(R.id.no_of_year_ind_txt1);
        monthly_incom_txt = (AppCompatTextView) findViewById(R.id.monthly_incom_txt);
        monthly_incom_txt1 = (AppCompatTextView) findViewById(R.id.monthly_incom_txt1);

        age_edit_txt = (AppCompatEditText) findViewById(R.id.age_edit_txt);
        // residence_edite_txt = (AppCompatEditText) findViewById(R.id.residence_edite_txt);
        live_curentres_edite_txt = (AppCompatEditText) findViewById(R.id.live_curentres_edite_txt);
        no_of_vehicle_edit_txt = (AppCompatEditText) findViewById(R.id.no_of_vehicle_edit_txt);
        no_of_years_work_ind_edit_txt = (AppCompatEditText) findViewById(R.id.no_of_years_work_ind_edit_txt);

        no_of_acres_edit_txt = (AppCompatEditText) findViewById(R.id.no_of_acres_edit_txt);
        anual_income_edit_txt = (AppCompatEditText) findViewById(R.id.anual_income_edit_txt);
        daily_income_f = (AppCompatEditText) findViewById(R.id.daily_income_f);
        number_of_years_in_work = (AppCompatEditText) findViewById(R.id.number_of_years_in_work);
        average_monthly_income = (AppCompatEditText) findViewById(R.id.average_monthly_income);

        no_of_birds_edit_txt = (AppCompatEditText) findViewById(R.id.no_of_birds_edit_txt);
        supply_by_who = (AppCompatEditText) findViewById(R.id.supply_by_who);


        avg_monthly_incom_edit_txt = (AppCompatEditText) findViewById(R.id.avg_monthly_incom_edit_txt);
        actual_business_ind_edit_txt = (AppCompatEditText) findViewById(R.id.actual_business_ind_edit_txt);
        actual_business_edit_forming_txt = (AppCompatEditText) findViewById(R.id.actual_business_edit_forming_txt);
        no_of_animals = (AppCompatEditText) findViewById(R.id.no_of_animals);
        residence_pincode_edite_txt = (AutoCompleteTextView) findViewById(R.id.residence_pincode_edite_txt);
        no_of_liters_edit_txt = (AppCompatEditText) findViewById(R.id.no_of_liters_edit_txt);
        no_of_years_in_works = (AppCompatEditText) findViewById(R.id.no_of_years_in_works);
        avg_monthly_income = (AppCompatEditText) findViewById(R.id.avg_monthly_income);
        Selling_Price = (AppCompatEditText) findViewById(R.id.Selling_Price);
        Profit_affter_selling = (AppCompatEditText) findViewById(R.id.Profit_affter_selling);
        no_of_years_in_work_P = (AppCompatEditText) findViewById(R.id.no_of_years_in_work_P);
        avg_monthly_income_Poultry = (AppCompatEditText) findViewById(R.id.avg_monthly_income_Poultry);


        delership_company_edit_txt = (AppCompatEditText) findViewById(R.id.delership_company_edit_txt);
        monthly_profit_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_profit_edit_txt);

        monthly_income_own_ser_bus_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_income_own_ser_bus_edit_txt);
        no_of_employee_own_ser_bus_edit_txt = (AppCompatEditText) findViewById(R.id.no_of_employee_own_ser_bus_edit_txt);
        business_investment_own_ser_bus_edit_txt = (AppCompatEditText) findViewById(R.id.business_investment_own_ser_bus_edit_txt);



        value_of_stock_raw_material = (AppCompatEditText) findViewById(R.id.value_of_stock_raw_material);
        monthly_sales_manufa = (AppCompatEditText) findViewById(R.id.monthly_sales_manufa);
        monthly_profit_manufa = (AppCompatEditText) findViewById(R.id.monthly_profit_manufa);
        value_of_machineries = (AppCompatEditText) findViewById(R.id.value_of_machineries);

        number_of_years_in_work_retails = (AppCompatEditText) findViewById(R.id.number_of_years_in_work_retails);
        average_monthly_income_own_business = (AppCompatEditText) findViewById(R.id.average_monthly_income_own_business);

        ///////////////////////////////////////////////////

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

        ///////////////Propert Values
        property_category_txt.setTypeface(font);
        property_category_txt1.setTypeface(font);
        property_type_txt.setTypeface(font);
        property_type_txt1.setTypeface(font);
        approval_of_land_txt.setTypeface(font);
        approval_of_land_txt1.setTypeface(font);
        bulding_approval_txt.setTypeface(font);
        bulding_approval_txt1.setTypeface(font);
        da_bulding_approval_txt.setTypeface(font);
        da_bulding_approval_txt1.setTypeface(font);
        txt_property_pincode.setTypeface(font);
        txt_property_pincode1.setTypeface(font);

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

        business_details_txt.setTypeface(font);
        emp_type1.setTypeface(font);
        emp_type2.setTypeface(font);
        age_txt.setTypeface(font);
        age_txt1.setTypeface(font);
        pan_number_txt.setTypeface(font);
        pan_number_txt1.setTypeface(font);
        residence_details.setTypeface(font);
        txt_residence_pincode.setTypeface(font);
        txt_residence_pincode1.setTypeface(font);
        txt_residence_type.setTypeface(font);
        txt_residence_type1.setTypeface(font);
        current_recidence_txt.setTypeface(font);
        current_recidence_txt1.setTypeface(font);
        assets_owned_txt.setTypeface(font);
        assets_owned_txt1.setTypeface(font);
        vocation_indiviual_txt.setTypeface(font);
        vocation_indiviual_txt1.setTypeface(font);
      //  busines_inco_proof_individua_txt.setTypeface(font);
      //  busines_inco_proof_individua_txt1.setTypeface(font);
        vehicle_individual_txt.setTypeface(font);
        vehicle_individual_txt1.setTypeface(font);
        number_of_vehicle_ind_txt.setTypeface(font);
        number_of_vehicle_ind_txt1.setTypeface(font);
        no_of_year_ind_txt.setTypeface(font);
        no_of_year_ind_txt1.setTypeface(font);
        monthly_incom_txt.setTypeface(font);
        monthly_incom_txt1.setTypeface(font);

         ////////Self Employeed
        age_edit_txt.setTypeface(font);
        //  residence_edite_txt.setTypeface(font);
        live_curentres_edite_txt.setTypeface(font);
        no_of_vehicle_edit_txt.setTypeface(font);
        //  no_of_years_ind_edit_txt.setTypeface(font);
      //  busproof_ind_txt.setTypeface(font);
      //  busproof_ind_txt1.setTypeface(font);
        avg_monthly_incom_edit_txt.setTypeface(font);
        actual_business_ind_edit_txt.setTypeface(font);
        actual_business_edit_forming_txt.setTypeface(font);
        no_of_animals.setTypeface(font);
        daily_income_f.setTypeface(font);
        number_of_years_in_work.setTypeface(font);
        average_monthly_income.setTypeface(font);
        no_of_liters_edit_txt.setTypeface(font);
        no_of_years_in_works.setTypeface(font);
        avg_monthly_income.setTypeface(font);
        no_of_birds_edit_txt.setTypeface(font);
        supply_by_who.setTypeface(font);
        Selling_Price.setTypeface(font);
        Profit_affter_selling.setTypeface(font);
        no_of_years_in_work_P.setTypeface(font);
        avg_monthly_income_Poultry.setTypeface(font);

     //   actual_business_edit_own_edt_txt.setTypeface(font);
        delership_company_edit_txt.setTypeface(font);
        monthly_profit_edit_txt.setTypeface(font);

        monthly_income_own_ser_bus_edit_txt.setTypeface(font);
        no_of_employee_own_ser_bus_edit_txt.setTypeface(font);
        business_investment_own_ser_bus_edit_txt.setTypeface(font);

        value_of_stock_raw_material.setTypeface(font);
        monthly_sales_manufa.setTypeface(font);
        monthly_profit_manufa.setTypeface(font);
        value_of_machineries.setTypeface(font);

        number_of_years_in_work_retails.setTypeface(font);
        average_monthly_income_own_business.setTypeface(font);
        property_pincode1_edit_txt.setTypeface(font);
     ///   salary_proof_edit_txt.setTypeface(font);

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

                        progressDialog.dismiss();

                      //  Log.e("respose Dreopdown", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));
                        try {
                            Residence_ownership_ar =object.getJSONArray("Residence_ownership");
                            Salary_method_ar =object.getJSONArray("Salary_method");
                            Salary_proof_ar =object.getJSONArray("Salary_proof");
                            employee_id_ar =object.getJSONArray("employee_id");
                            other_earning_ar =object.getJSONArray("other_earning");
                            have_pan_ar =object.getJSONArray("have_pan");

                            Employement =object.getJSONArray("Employement");

                            Property_Type =object.getJSONArray("Property_Type");
                            Property_title =object.getJSONArray("Property_title");
                            property_identified =object.getJSONArray("property_identified");
                            property_category =object.getJSONArray("property_category");
                            land_approval =object.getJSONArray("land_approval");
                            building_approval =object.getJSONArray("building_approval");
                            DA_approval =object.getJSONArray("DA_approval");


                            Salry_method_Spinner(Salary_method_ar);
                           Salry_Proof(Salary_proof_ar);
                            Residence_Array(Residence_ownership_ar);
                            Employee_ID_Array(employee_id_ar);
                            HAVE_PAN_Card(have_pan_ar);
                            Other_Earning(other_earning_ar);

                            Other_Earning(other_earning_ar);

                            Log.e("Property_Type",String.valueOf(Property_Type));
                            Log.e("Employement",String.valueOf(Employement));
                            Log.e("Property_title",String.valueOf(Property_title));
                          //  Salry_method_Spinner(Residence_ownership_ar);

                            Property_Identified_Spinner(property_identified);
                            Property_Title_Spinner(Property_title);

                            Property_Type(Property_Type);
                            Property_Category(property_category);
                            Approval_of_Land(land_approval);
                            Building_Approval(building_approval);
                            DA_Building_Approval(DA_approval);

                            ////SelfEmployes
                            Type_of_employement =object.getJSONArray("Type_of_employement");
                            have_pan_ar_self =object.getJSONArray("have_pan");
                            vocaton_ar =object.getJSONArray("vocaton");
                            Business_income_proof_ar =object.getJSONArray("Business_income_proof");
                            Residence_ownership_ar_self =object.getJSONArray("Residence_ownership");

                            vocation_type_forming_ar =object.getJSONArray("vocation_type");

                            Business_type_own_business =object.getJSONArray("Business_type");
                            Business_Proof =object.getJSONArray("Business_Proof");

                            Assets_own =object.getJSONArray("Assets_own");
                            office_shop =object.getJSONArray("office_shop");
                            vehicle_Type =object.getJSONArray("vehicle_Type");
                            crop_type =object.getJSONArray("crop_type");
                            sell_milk =object.getJSONArray("sell_milk");

                            franchise =object.getJSONArray("franchise");

                            Log.e("Type_of_employement",String.valueOf(Type_of_employement));

                            Type_of_Employeement(Type_of_employement);
                            HAVE_PAN_Card_self(have_pan_ar_self);
                            Vocation(vocaton_ar);
                            Business_income_proof(Business_income_proof_ar);

                            vocation_type_forming(vocation_type_forming_ar);
                            Residence_Array_Self(Residence_ownership_ar_self);
                          //  Business_type_own_business_Array(Business_type_own_business);
                          //  Business_Proof_individual(Business_Proof);
                          //  Business_Proof_individual_forming(Business_Proof);
                          //  Business_Proof_Own_Business(Business_Proof);
                          //  Business_Proof_forming_Dairy(Business_Proof);
                          //  Business_Proof_forming_Poultry(Business_Proof);
                           // Assets_own_fun(Assets_own);
                            Office_Shop_(office_shop);
                            Vehicle_Type_(vehicle_Type);
                            Crop_type_function(crop_type);
                            Selling_milk(sell_milk);
                            Selling_milk(sell_milk);
                            Runs_own_business_franchise(franchise);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();

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

    private void Property_Identified_Spinner(final JSONArray Property_Identified_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Property_Identified = new String[Property_Identified_ar.length()];

        for (int i=0;i<Property_Identified_ar.length();i++){
            JSONObject J =  Property_Identified_ar.getJSONObject(i);
            Property_Identified[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Property_Identified));

            Property_Identified_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Property_Identified_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spnr_Property_Identified.setAdapter(Property_Identified_Adapter);
            spnr_Property_Identified.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Property_Identified_ID = Property_Identified_ar.getJSONObject(position).getString("id");
                        Property_Identified_Value = Property_Identified_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Property_Identified_ID", Property_Identified_ID);
                        Log.d("Property_Ident_Value", Property_Identified_Value);

                        Pref.putPROPERTYIDENTIFIED(context,Property_Identified_ID);

                        if(Property_Identified_ID.equals("0") ||Property_Identified_ID.equals("2") )
                        {
                            property_identified_Ly1.setVisibility(View.GONE);
                        }else
                        {
                            property_identified_Ly1.setVisibility(View.VISIBLE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spnr_Property_Identified.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Property_Title_Spinner(final JSONArray Property_Title_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Property_Title = new String[Property_Title_ar.length()];
        for (int i=0;i<Property_Title_ar.length();i++){
            JSONObject J =  Property_Title_ar.getJSONObject(i);
            Property_Title[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Property_Title));
            Property_Title_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Property_Title_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spnr_property_title.setAdapter(Property_Title_Adapter);
            spnr_property_title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        Property_Title_ID = Property_Title_ar.getJSONObject(position).getString("id");
                        Property_Title_Value = Property_Title_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", Property_Title_ID);
                        Log.d("Salary_Value", Property_Title_Value);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spnr_property_title.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    //////Salaried

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

        property_pincode1_edit_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("hi","hi11");
                String workpincode = property_pincode1_edit_txt.getText().toString();

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

        //Self
        residence_pincode_edite_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("hi", "hi11");
                String workpincode = residence_pincode_edite_txt.getText().toString();

                if (workpincode.length() == 2) {
                    GET_Pincode1_self(workpincode);
                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        office_residence_pincode_edite_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("hi", "hi11");
                String workpincode = office_residence_pincode_edite_txt.getText().toString();

                if (workpincode.length() == 2) {
                    GET_Pincode1_self(workpincode);
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

                if(loan_type_id.equals("1") || loan_type_id.equals("3") || loan_type_id.equals("4"))
                {
                    if(Property_Identified_ID.equals("0"))
                    {
                        Toast.makeText(context,"Please Select Property Identified",Toast.LENGTH_SHORT).show();

                    }else
                    {
                        if(Property_Title_ID.equals("0"))
                        {
                            Toast.makeText(context,"Please Select Property Title",Toast.LENGTH_SHORT).show();

                        }else
                        {
                            if(salary_type.equals("1"))
                            {
                                Salaried_Validation();
                            }else if(salary_type.equals("2"))
                            {
                                Self_Emp_Validation();
                            }

                        }

                    }

                }else
                {
                    if(Property_Title_ID.equals("0"))
                    {
                        Toast.makeText(context,"Please Select Property Title",Toast.LENGTH_SHORT).show();

                    }else
                    {
                        if(salary_type.equals("1"))
                        {
                            Salaried_Validation();
                        }else if(salary_type.equals("2"))
                        {
                            Self_Emp_Validation();
                        }

                    }
                }


            }
        });

    }


    private  void Salaried_Validation()
    {
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

                           // lead_viability();
                            nexteligibility();


                        }else if(residence_id.equals("2"))
                        {

                            if (!curennt_resi_v()) {
                                return;
                            }

                            if (!Validate_Monthly_Emi()) {
                                return;
                            }

                            //lead_viability();
                            nexteligibility();
                        }

                    }
                }
            }
        }
    }

    private void Self_Emp_Validation()
    {

        if(Employee_type_Id.equals("0"))
        {
            Toast.makeText(context,"please Select the Employee type",Toast.LENGTH_SHORT).show();
        }else
        {
            if (!Age_validation()) {
                return;
            }

            if(PAN_id_self.equals("0"))
            {
                Toast.makeText(context,"please Select the Pan Card",Toast.LENGTH_SHORT).show();

            }else
            {

                if(Employee_type_Id.equals("1"))
                {
                    validation_individual();


                }else if(Employee_type_Id.equals("2")) {

                    if(vocation_type_forming_id.equals("0"))
                    {
                        Toast.makeText(context,"please Select the vocation Type",Toast.LENGTH_SHORT).show();
                    }else  if(vocation_type_forming_id.equals("1"))
                    {
                        if (!Actual_business_forming()) {
                            return;
                        }
                        validation_forming();

                    }else if(vocation_type_forming_id.equals("2"))
                    {
                        if (!Actual_business_forming()) {
                            return;
                        }
                        validate_dairy();

                    }else if(vocation_type_forming_id.equals("3"))
                    {
                        if (!Actual_business_forming()) {
                            return;
                        }
                        validation_poultry();
                    }

                }else if(Employee_type_Id.equals("3"))
                {
                    Validate_own_Business();

                }

            }

        }


    }

    private void nexteligibility()
    {

        int i = Integer.parseInt(loan_type_id);
        switch (i) {
            case 1:
                Intent intent = new Intent(Viability_check_HL.this, Eligibility_HL.class);
                startActivity(intent);
                intent.putExtra("property_identified",Property_Identified_ID);
                finish();
                break;
            case 2:
                Intent intent1 = new Intent(Viability_check_HL.this, Eligibility_check_LAP.class);
                startActivity(intent1);
                finish();
                break;
            case 3:

                Intent intent2 = new Intent(Viability_check_HL.this, Eligibility_check_Plot_Construction.class);
                startActivity(intent2);
                finish();
                break;
            case 4:
                Intent intent3 = new Intent(Viability_check_HL.this, Eligibility_check_PlotLoan.class);
                startActivity(intent3);
                finish();

                break;
            case 5:
                Intent intent4 = new Intent(Viability_check_HL.this, Eligibility_check_BT_TopUp_Loan.class);
                startActivity(intent4);
                finish();
                break;
            case 6:

                Intent intent5 = new Intent(Viability_check_HL.this, Eligibility_check_Improment_Loan.class);
                startActivity(intent5);
                finish();
                break;
            case 8:
                Intent intent6 = new Intent(Viability_check_HL.this, Eligibility_check_Extention_Loan.class);
                startActivity(intent6);
                finish();
                break;
            case 9:
                Intent intent7 = new Intent(Viability_check_HL.this, Eligibility_check_Extention_Loan.class);
                startActivity(intent7);
                finish();
                break;
        }

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

    private void HAVE_PAN_Card_self(final JSONArray has_pancard_ar) throws JSONException {
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
            String workpincode2 = property_pincode1_edit_txt.getText().toString();

            if(workpincode.length()> 2){
                company_pincode_txt.setThreshold(2);
                company_pincode_txt.setAdapter(Pincode_Adapter);
            }

            if(workpincode1.length()> 2){
                residence_pincode1_edit_txt.setThreshold(2);
                residence_pincode1_edit_txt.setAdapter(Pincode_Adapter);
            }

            if(workpincode2.length()> 2){
                property_pincode1_edit_txt.setThreshold(2);
                property_pincode1_edit_txt.setAdapter(Pincode_Adapter);
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

        property_pincode1_edit_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);

            }
        });


    }


    /////Self Employeed


   // validation
    private void validation_individual()
    {
        if(vocaton_id.equals("0"))
        {
            Toast.makeText(context,"please Select the vocation Type",Toast.LENGTH_SHORT).show();
        }else
        {

            if (!Actual_business()) {
                return;
            }

            if(Business_income_proof_id.equals("0"))
            {
                Toast.makeText(context,"please Select the Business income proof",Toast.LENGTH_SHORT).show();

            }else if(Business_income_proof_id.equals("7"))
            {

                if (!Number_of_vehicle()) {
                    return;
                }

                if (!no_of_years_wrk()) {
                    return;
                }

                if (!Avg_Monthly_income()) {
                    return;
                }

                Residence_Details_Validation();
            }else
            {


                if (!no_of_years_wrk()) {
                    return;
                }

                if (!Avg_Monthly_income()) {
                    return;
                }
                Residence_Details_Validation();

            }
        }
    }

    private void validation_forming()
    {

        if(business_incom_proof_forming_id.equals("0"))
        {
            Toast.makeText(context,"please Select the Business income proof",Toast.LENGTH_SHORT).show();

        }else
        {
            if (!No_acress_edit_txt()) {
                return;
            }
            if (!Annual_income()) {
                return;
            }
            if (!Daily_income_f()) {
                return;
            }
            if (!No_of_Years_in_work()) {
                return;
            }

            if (!average_monthly_income()) {
                return;
            }


        }
    }

    private void validate_dairy()
    {

        if(business_incom_proof_Dairy_id.equals("0"))
        {
            Toast.makeText(context,"please Select the Business Income Proof",Toast.LENGTH_SHORT).show();

        }else
        {
            if (!No_animals_Proof()) {
                return;
            }
            if (!No_Liters()) {
                return;
            }

            if(selling_milk_id.equals("0"))
            {
                Toast.makeText(context,"please Select Milk selling type",Toast.LENGTH_SHORT).show();

            }else
            {
                if (!No_of_years_in_work()) {
                    return;
                }

                if (!Avg_monthly_Income_D()) {
                    return;
                }

                nexteligibility();
            }



        }
    }

    private void validation_poultry()
    {
        if(business_incom_proof_Poultry_id.equals("0"))
        {
            Toast.makeText(context,"please Select the business income proof",Toast.LENGTH_SHORT).show();
        }else
        {
            if (!No_Of_Birds()) {
                return;
            }
            if (!Supplied_by_who()) {
                return;
            }

            if (!Selling_Price()) {
                return;
            }

            if (!Profit_after_Selling()) {
                return;
            }

            if (!No_years_in_work_p()) {
                return;
            }

            if (!Avg_monthly_income_p()) {
                return;
            }

            nexteligibility();

        }
    }

    private void Validate_own_Business()
    {
        if(business_own_type_id.equals("0"))
        {
            Toast.makeText(context,"please Select the business type",Toast.LENGTH_SHORT).show();

        }else
        {
            if (!Actual_Business()) {
                return;
            }

            if(business_incom_proof_own_business_id.equals("0"))
            {
                Toast.makeText(context,"please Select the business income proof",Toast.LENGTH_SHORT).show();

            }else
            {
                if(Office_Shop__id.equals("0"))
                {
                    Toast.makeText(context,"please Select the office/shop typef",Toast.LENGTH_SHORT).show();

                }else
                {

                    if(business_own_type_id.equals("1"))
                    {
                        if(franchise__id.equals("0"))
                        {
                            Toast.makeText(context,"please Select Franchise/dealer/sub dealer type",Toast.LENGTH_SHORT).show();

                        }else
                        {
                            if (!Delership_Company()) {
                                return;
                            }
                            if (!Monthly_Profit()) {
                                return;
                            }

                            Own_Bus_No_year_com_validation();
                        }

                    }else if(business_own_type_id.equals("2"))
                    {

                        if (!monthly_income_own_ser_bus_Work()) {
                            return;
                        }
                        if (!no_of_employee_own_ser_bus_Work()) {
                            return;
                        }
                        if (!business_investment_own_ser_bus_Work()) {
                            return;
                        }
                        Own_Bus_No_year_com_validation();

                    }else if(business_own_type_id.equals("3"))
                    {

                        if (!value_of_stock_raw_material_Work()) {
                            return;
                        }
                        if (!monthly_sales_manufa_Work()) {
                            return;
                        }
                        if (!monthly_profit_manufa_Work()) {
                            return;
                        }

                        if (!Value_of_Machinaries()) {
                            return;
                        }
                        Own_Bus_No_year_com_validation();

                    }

                }
            }


        }
    }

    private void Own_Bus_No_year_com_validation()
    {
        if (!No_years_in_Work()) {
            return;
        }

        if (!Avg_monthly_income_own_Bus()) {
            return;
        }
        nexteligibility();

    }

    private void Residence_Details_Validation()
    {
        if(Employee_type_Id.equals("3"))
        {

            if(Office_Shop__id.equals("2"))
            {
                if (!office_pincode()) {
                    return;
                }
                if(off_residence_id.equals("0"))
                {
                    Toast.makeText(context,"please Select office ownership type",Toast.LENGTH_SHORT).show();

                }else
                {
                    resi_val();
                }
            }else
            {
                resi_val();
            }
        }else
        {
            resi_val();

        }


    }

    private void resi_val()
    {
        if (!Residence_pincode()) {
            return;
        }
        if(residence_id_self.equals("0"))
        {
            Toast.makeText(context,"please Select residence type",Toast.LENGTH_SHORT).show();
        }else
        {
            nexteligibility();
        }
    }

    private boolean Age_validation(){

        if (age_edit_txt.getText().toString().isEmpty()) {
            age_edit_txt.setError(getText(R.string.error_age));
            age_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Actual_business(){

        if (actual_business_ind_edit_txt.getText().toString().isEmpty()) {
            actual_business_ind_edit_txt.setError(getText(R.string.error_actual_business));
            actual_business_ind_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Actual_business_forming(){

        if (actual_business_edit_forming_txt.getText().toString().isEmpty()) {
            actual_business_edit_forming_txt.setError(getText(R.string.error_actual_business));
            actual_business_edit_forming_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean no_of_years_wrk(){

        if (no_of_years_work_ind_edit_txt.getText().toString().isEmpty()) {
            no_of_years_work_ind_edit_txt.setError(getText(R.string.error_no_of_years_work));
            no_of_years_work_ind_edit_txt.requestFocus();
            return false;
        }else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Number_of_vehicle(){

        if (no_of_vehicle_edit_txt.getText().toString().isEmpty()) {
            no_of_vehicle_edit_txt.setError(getText(R.string.error_no_of_vehicle));
            no_of_vehicle_edit_txt.requestFocus();
            return false;

        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Avg_Monthly_income(){

        if (avg_monthly_incom_edit_txt.getText().toString().isEmpty()) {
            avg_monthly_incom_edit_txt.setError(getText(R.string.error_no_of_years_work));
            avg_monthly_incom_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }



    private boolean No_acress_edit_txt(){

        if (no_of_acres_edit_txt.getText().toString().isEmpty()) {
            no_of_acres_edit_txt.setError(getText(R.string.error_no_of_acress));
            no_of_acres_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Annual_income(){

        if (anual_income_edit_txt.getText().toString().isEmpty()) {
            anual_income_edit_txt.setError(getText(R.string.error_anual_income));
            anual_income_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Daily_income_f(){

        if (daily_income_f.getText().toString().isEmpty()) {
            daily_income_f.setError(getText(R.string.error_daily_income));
            daily_income_f.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean No_of_Years_in_work(){

        if (number_of_years_in_work.getText().toString().isEmpty()) {
            number_of_years_in_work.setError(getText(R.string.error_no_of_years_in_work));
            number_of_years_in_work.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean average_monthly_income(){

        if (average_monthly_income.getText().toString().isEmpty()) {
            average_monthly_income.setError(getText(R.string.error_avg_monthly_income));
            average_monthly_income.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean No_animals_Proof(){

        if (no_of_animals.getText().toString().isEmpty()) {
            no_of_animals.setError(getText(R.string.error_no_of_animals));
            no_of_animals.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean No_Liters(){

        if (no_of_liters_edit_txt.getText().toString().isEmpty()) {
            no_of_liters_edit_txt.setError(getText(R.string.error_no_of_liters));
            no_of_liters_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean No_of_years_in_work(){

        if (no_of_years_in_works.getText().toString().isEmpty()) {
            no_of_years_in_works.setError(getText(R.string.error_no_years_in_work));
            no_of_years_in_works.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Avg_monthly_Income_D(){

        if (avg_monthly_income.getText().toString().isEmpty()) {
            avg_monthly_income.setError(getText(R.string.error_avg_monthly_income));
            avg_monthly_income.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean No_Of_Birds(){

        if (no_of_birds_edit_txt.getText().toString().isEmpty()) {
            no_of_birds_edit_txt.setError(getText(R.string.error_no_of_birds));
            no_of_birds_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }


    private boolean Supplied_by_who(){

        if (supply_by_who.getText().toString().isEmpty()) {
            supply_by_who.setError(getText(R.string.error_no_of_birds));
            supply_by_who.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Selling_Price(){

        if (Selling_Price.getText().toString().isEmpty()) {
            Selling_Price.setError(getText(R.string.error_selling_price));
            Selling_Price.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Profit_after_Selling(){

        if (Profit_affter_selling.getText().toString().isEmpty()) {
            Profit_affter_selling.setError(getText(R.string.error_profit_af_sel));
            Profit_affter_selling.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }



    private boolean No_years_in_work_p(){

        if (no_of_years_in_work_P.getText().toString().isEmpty()) {
            no_of_years_in_work_P.setError(getText(R.string.error_no_of_years_in_work));
            no_of_years_in_work_P.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Avg_monthly_income_p(){

        if (avg_monthly_income_Poultry.getText().toString().isEmpty()) {
            avg_monthly_income_Poultry.setError(getText(R.string.error_avg_monthly_income_d));
            avg_monthly_income_Poultry.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }


    private boolean Actual_Business(){

        if (actual_business_edit_own_edt_txt.getText().toString().isEmpty()) {
            actual_business_edit_own_edt_txt.setError(getText(R.string.error_actual_business));
            actual_business_edit_own_edt_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Delership_Company(){

        if (delership_company_edit_txt.getText().toString().isEmpty()) {
            delership_company_edit_txt.setError(getText(R.string.error_delership_company));
            delership_company_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean Monthly_Profit(){

        if (monthly_profit_edit_txt.getText().toString().isEmpty()) {
            monthly_profit_edit_txt.setError(getText(R.string.error_profit_af_sel));
            monthly_profit_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }



    private boolean monthly_income_own_ser_bus_Work(){

        if (monthly_income_own_ser_bus_edit_txt.getText().toString().isEmpty()) {
            monthly_income_own_ser_bus_edit_txt.setError(getText(R.string.error_monthly_income));
            monthly_income_own_ser_bus_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean no_of_employee_own_ser_bus_Work(){

        if (no_of_employee_own_ser_bus_edit_txt.getText().toString().isEmpty()) {
            no_of_employee_own_ser_bus_edit_txt.setError(getText(R.string.error_no_emp_in_work));
            no_of_employee_own_ser_bus_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean business_investment_own_ser_bus_Work(){

        if (business_investment_own_ser_bus_edit_txt.getText().toString().isEmpty()) {
            business_investment_own_ser_bus_edit_txt.setError(getText(R.string.error_bus_setup_invest));
            business_investment_own_ser_bus_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }




    private boolean value_of_stock_raw_material_Work(){

        if (value_of_stock_raw_material.getText().toString().isEmpty()) {
            value_of_stock_raw_material.setError(getText(R.string.error_bus_setup_invest));
            value_of_stock_raw_material.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }
    private boolean monthly_sales_manufa_Work(){

        if (monthly_sales_manufa.getText().toString().isEmpty()) {
            monthly_sales_manufa.setError(getText(R.string.error_bus_setup_invest));
            monthly_sales_manufa.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean monthly_profit_manufa_Work(){

        if (monthly_profit_manufa.getText().toString().isEmpty()) {
            monthly_profit_manufa.setError(getText(R.string.error_bus_setup_invest));
            monthly_profit_manufa.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean Value_of_Machinaries(){

        if (value_of_machineries.getText().toString().isEmpty()) {
            value_of_machineries.setError(getText(R.string.error_value_of_machinaries));
            value_of_machineries.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }


    private boolean No_years_in_Work(){

        if (number_of_years_in_work_retails.getText().toString().isEmpty()) {
            number_of_years_in_work_retails.setError(getText(R.string.error_no_years_in_work));
            number_of_years_in_work_retails.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }
    private boolean Avg_monthly_income_own_Bus(){

        if (average_monthly_income_own_business.getText().toString().isEmpty()) {
            average_monthly_income_own_business.setError(getText(R.string.error_avg_monthly_income));
            average_monthly_income_own_business.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }

    private boolean Residence_pincode(){

        if (residence_pincode_edite_txt.getText().toString().isEmpty()) {
            residence_pincode_edite_txt.setError(getText(R.string.error_residence_pincode));
            residence_pincode_edite_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }
    private boolean office_pincode(){

        if (office_residence_pincode_edite_txt.getText().toString().isEmpty()) {
            office_residence_pincode_edite_txt.setError(getText(R.string.error_office_pincode));
            office_residence_pincode_edite_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }
        return true;
    }


    //auto completed
    private void GET_Pincode1_self(String code) {
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

                                setMain_Area1_self(response);
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

    private void setMain_Area1_self(final JSONArray ja) throws JSONException {

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

            //  residence_pincode_edite_txt.setThreshold(2);
            //   residence_pincode_edite_txt.setAdapter(Pincode_Adapter);
            String workpincode = residence_pincode_edite_txt.getText().toString();
            String workpincode1 = office_residence_pincode_edite_txt.getText().toString();

            if(workpincode.length()> 2){
                residence_pincode_edite_txt.setThreshold(2);
                residence_pincode_edite_txt.setAdapter(Pincode_Adapter);
            }

            if(workpincode1.length()> 2){
                office_residence_pincode_edite_txt.setThreshold(2);
                office_residence_pincode_edite_txt.setAdapter(Pincode_Adapter);
            }
        }

        residence_pincode_edite_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);
            }
        });

        office_residence_pincode_edite_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code1 = (String)adapterView.getItemAtPosition(i);
            }
        });

    }


    private void Type_of_Employeement(final JSONArray Type_of_employement_ar) throws JSONException {
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
            emp_type.setAdapter(Employee_Type_adapter);
            emp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Employee_type_Id = Type_of_employement_ar.getJSONObject(position).getString("id");
                        Employee_type_Value = Type_of_employement_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", Employee_type_Id);
                        Log.d("Salary_Value", Employee_type_Value);

                        int b = Integer.parseInt(Employee_type_Id);

                        switch(b) {
                            case 1:
                                individual.setVisibility(View.VISIBLE);
                                formin_dairy.setVisibility(View.GONE);
                                self_business.setVisibility(View.GONE);
                                ofiice_res_details.setVisibility(View.GONE);
                                break;
                            case 2:
                                individual.setVisibility(View.GONE);
                                formin_dairy.setVisibility(View.VISIBLE);
                                self_business.setVisibility(View.GONE);
                                ofiice_res_details.setVisibility(View.GONE);
                                break;
                            case 3:
                                individual.setVisibility(View.GONE);
                                formin_dairy.setVisibility(View.GONE);
                                self_business.setVisibility(View.VISIBLE);
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
            emp_type.setOnTouchListener(new View.OnTouchListener() {
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
        PAN_ID_SA_self = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            PAN_ID_SA_self[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(PAN_ID_SA_self));
            PAN_ID_Adapter_self = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            PAN_ID_Adapter_self.setDropDownViewResource(R.layout.view_spinner_item);
            has_pan_card_self.setAdapter(PAN_ID_Adapter_self);
            has_pan_card_self.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        PAN_id_self = has_pancard_ar.getJSONObject(position).getString("id");
                        PAN_Value_self = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", PAN_id_self);
                        Log.d("Salary_Value", PAN_Value_self);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            has_pan_card_self.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    /*Property_Category_Adapter,Property_Type_Adapter,
            Approval_of_Land_Adapter,Bulding_Approval_Adapter,DA_Bulding_Adapter*/

    private void Property_Category(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Property_Category_SA = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            Property_Category_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Property_Category_SA));
            Property_Category_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Property_Category_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spnr_property_category.setAdapter(Property_Category_Adapter);
            spnr_property_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Propery_Category_ID = has_pancard_ar.getJSONObject(position).getString("id");
                        Propery_Category_Value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", Propery_Category_ID);
                        Log.d("Salary_Value", Propery_Category_Value);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spnr_property_category.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Property_Type(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Property_Type_SA = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            Property_Type_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Property_Type_SA));
            Property_Type_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Property_Type_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spnr_property_type.setAdapter(Property_Type_Adapter);
            spnr_property_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Propery_Type_ID = has_pancard_ar.getJSONObject(position).getString("id");
                        Propery_Type_Value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", Propery_Type_ID);
                        Log.d("Salary_Value", Propery_Type_Value);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spnr_property_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Approval_of_Land(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Approval_of_Land_SA = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            Approval_of_Land_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Approval_of_Land_SA));
            Approval_of_Land_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Approval_of_Land_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spnr_approval_of_land.setAdapter(Approval_of_Land_Adapter);
            spnr_approval_of_land.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Approval_of_Land_ID = has_pancard_ar.getJSONObject(position).getString("id");
                        Approval_of_Land_Value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Approval_of_Land_ID", Approval_of_Land_ID);
                        Log.d("Approval_of_Land_Value", Approval_of_Land_Value);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spnr_approval_of_land.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Building_Approval(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Bulding_Approval_SA = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            Bulding_Approval_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Bulding_Approval_SA));
            Bulding_Approval_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Bulding_Approval_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spnr_bulding_approval.setAdapter(Bulding_Approval_Adapter);
            spnr_bulding_approval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Bulding_Approval_Id = has_pancard_ar.getJSONObject(position).getString("id");
                        Bulding_Approval_Value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", Bulding_Approval_Id);
                        Log.d("Salary_Value", Bulding_Approval_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spnr_bulding_approval.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void DA_Building_Approval(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        DA_Bulding_Approval = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            DA_Bulding_Approval[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(DA_Bulding_Approval));
            DA_Bulding_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            DA_Bulding_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spnr_da_bulding_approval.setAdapter(DA_Bulding_Adapter);
            spnr_da_bulding_approval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        DA_Bulding_ID = has_pancard_ar.getJSONObject(position).getString("id");
                        DA_Bulding_Value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("DA_Bulding_ID", DA_Bulding_ID);
                        Log.d("DA_Bulding_Value", DA_Bulding_Value);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spnr_da_bulding_approval.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Vocation(final JSONArray vocaton_ar) throws JSONException {
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
            spi_vocation_type_.setAdapter(Vocation_Adapter);
            spi_vocation_type_.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        vocaton_id = vocaton_ar.getJSONObject(position).getString("id");
                        vocaton_value = vocaton_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("vocaton_id", vocaton_id);
                        Log.d("vocaton_value", vocaton_value);

                        if(vocaton_id.equals("7"))
                        {
                            Driver_C_owner.setVisibility(View.VISIBLE);

                        }else
                        {
                            Driver_C_owner.setVisibility(View.GONE);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spi_vocation_type_.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Business_income_proof(final JSONArray Business_income_proof_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

        Business_income_proof_SA = new String[Business_income_proof_ar.length()];
        for (int i=0;i<Business_income_proof_ar.length();i++){
            JSONObject J =  Business_income_proof_ar.getJSONObject(i);
            Business_income_proof_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Business_income_proof_SA));
            Business_income_proof_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Business_income_proof_Adapter.setDropDownViewResource(R.layout.view_spinner_item);

            ///Buiness Income proof Dairy

        }



    }


    private void vocation_type_forming(final JSONArray vocation_type_forming_ar) throws JSONException {
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
            spi_vocation_forming.setAdapter(vocation_type_forming_Adapter);
            spi_vocation_forming.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        vocation_type_forming_id = vocation_type_forming_ar.getJSONObject(position).getString("id");
                        vocation_type_forming_value = vocation_type_forming_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("vocaton_id", vocation_type_forming_id);
                        Log.d("vocaton_value", vocation_type_forming_value);

                        if(vocation_type_forming_id.equals("1"))
                        {
                            forming.setVisibility(View.VISIBLE);
                            dairy.setVisibility(View.GONE);
                            poultry.setVisibility(View.GONE);

                        }else if(vocation_type_forming_id.equals("2"))
                        {
                            forming.setVisibility(View.GONE);
                            dairy.setVisibility(View.VISIBLE);
                            poultry.setVisibility(View.GONE);
                        }else if(vocation_type_forming_id.equals("3"))
                        {
                            forming.setVisibility(View.GONE);
                            dairy.setVisibility(View.GONE);
                            poultry.setVisibility(View.VISIBLE);
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spi_vocation_forming.setOnTouchListener(new View.OnTouchListener() {
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
            spinner_how_do_sell_milk.setAdapter(Selling_Milk_Adapter);
            spinner_how_do_sell_milk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        selling_milk_id = selling_milk_ar.getJSONObject(position).getString("id");
                        selling_milk_value = selling_milk_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("vocaton_id", selling_milk_id);
                        Log.d("vocaton_value", selling_milk_value);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spi_vocation_forming.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }
    }

    private void Office_Shop_(final JSONArray office_shop_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

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
            spinner_office_shop_setup.setAdapter(Office_Shop__Adapter);
            spinner_office_shop_setup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Office_Shop__id = office_shop_ar.getJSONObject(position).getString("id");
                        Office_Shop__value = office_shop_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("vocaton_id", Office_Shop__id);
                        Log.d("vocaton_value", Office_Shop__value);

                        if(Office_Shop__id.equals("2"))
                        {
                            ofiice_res_details.setVisibility(View.VISIBLE);
                        }else
                        {
                            ofiice_res_details.setVisibility(View.GONE);

                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_office_shop_setup.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Runs_own_business_franchise(final JSONArray franchise_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];

        franchise_SA = new String[franchise_ar.length()];
        for (int i=0;i<franchise_ar.length();i++){
            JSONObject J =  franchise.getJSONObject(i);
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
            spinner_frenc_deler_sub.setAdapter(franchise__Adapter);
            spinner_frenc_deler_sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        franchise__id = franchise.getJSONObject(position).getString("id");
                        franchise__value = franchise.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("vocaton_id", franchise__id);
                        Log.d("vocaton_value", franchise__value);






                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_frenc_deler_sub.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Residence_Array_Self(final JSONArray Residence_ownership_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Residence_Type_SA_self = new String[Residence_ownership_ar.length()];
        for (int i=0;i<Residence_ownership_ar.length();i++){
            JSONObject J =  Residence_ownership_ar.getJSONObject(i);
            Residence_Type_SA_self[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Residence_Type_SA_self));
            Residence_Adapter_Self = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Residence_Adapter_Self.setDropDownViewResource(R.layout.view_spinner_item);

            spinner_residence_type_self.setAdapter(Residence_Adapter_Self);
            office_spinner_residence_type.setAdapter(Residence_Adapter_Self);

            spinner_residence_type_self.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        residence_id_self = Residence_ownership_ar.getJSONObject(position).getString("id");
                        residence_Value_self = Residence_ownership_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", residence_id_self);
                        Log.d("Salary_Value", residence_Value_self);

                        if(residence_id_self.equals("2"))
                        {
                            res_rented.setVisibility(View.VISIBLE);

                        }else
                        {
                            res_rented.setVisibility(View.GONE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_residence_type_self.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


            office_spinner_residence_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        off_residence_id = Residence_ownership_ar.getJSONObject(position).getString("id");
                        off_residence_Value = Residence_ownership_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("off_residence_id", off_residence_id);
                        Log.d("off_residence_Value", off_residence_Value);

                        if(off_residence_id.equals("2"))
                        {
                            res_rented.setVisibility(View.VISIBLE);

                        }else
                        {
                            res_rented.setVisibility(View.GONE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            office_spinner_residence_type.setOnTouchListener(new View.OnTouchListener() {
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
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        business_own_type_id = Business_type_own_business_ar.getJSONObject(position).getString("id");
                        business_own_type_Value = Business_type_own_business_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", business_own_type_id);
                        Log.d("Salary_Value", business_own_type_Value);


                        if(business_own_type_id.equals("1"))
                        {
                            Retail_wholesale_business.setVisibility(View.VISIBLE);
                            service_business.setVisibility(View.GONE);
                            manufacturing.setVisibility(View.GONE);

                        }else if(business_own_type_id.equals("2"))
                        {
                            Retail_wholesale_business.setVisibility(View.GONE);
                            service_business.setVisibility(View.VISIBLE);
                            manufacturing.setVisibility(View.GONE);
                        }else if(business_own_type_id.equals("3"))
                        {
                            Retail_wholesale_business.setVisibility(View.GONE);
                            service_business.setVisibility(View.GONE);
                            manufacturing.setVisibility(View.VISIBLE);
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

    private void Business_Proof_individual(final JSONArray ja) throws JSONException {

        Business_proof_individual = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            Business_proof_individual.add(salary_proof);
        }
        business_proof_individual_adapter = new MyCustomAdapter_Business_proof_Individual(context, 0,Business_proof_individual);
        spi_busproof_individual.setAdapter(business_proof_individual_adapter);
        business_proof_individual_adapter.notifyDataSetChanged();

    }

    private void Business_Proof_individual_forming(final JSONArray ja) throws JSONException {

        Business_proof_individual_forming_array_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            Business_proof_individual_forming_array_list.add(salary_proof);
        }
        business_proof_Forming_adapter = new MyCustomAdapter_Business_proof_Forming(context, 0,Business_proof_individual_forming_array_list);
        spinner_business_proof_txt_F.setAdapter(business_proof_Forming_adapter);
        business_proof_Forming_adapter.notifyDataSetChanged();

    }


    private void Business_Proof_forming_Dairy(final JSONArray ja) throws JSONException {

        Business_proof_forming_dairy_array_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            Business_proof_forming_dairy_array_list.add(salary_proof);
        }
        business_proof_Forming_dairy_adapter = new MyCustomAdapter_Business_proof_Forming_dairy(context, 0,Business_proof_forming_dairy_array_list);
        spinner_business_proof_txt_D.setAdapter(business_proof_Forming_dairy_adapter);
        business_proof_Forming_dairy_adapter.notifyDataSetChanged();

    }

    private void Business_Proof_forming_Poultry(final JSONArray ja) throws JSONException {

        Business_proof_forming_poultry_array_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            Business_proof_forming_poultry_array_list.add(salary_proof);
        }
        business_proof_Forming_poultry_adapter = new MyCustomAdapter_Business_proof_Forming_poultry(context, 0,Business_proof_forming_poultry_array_list);
        spinner_business_proof_txt_P.setAdapter(business_proof_Forming_poultry_adapter);
        business_proof_Forming_poultry_adapter.notifyDataSetChanged();

    }

    private void Business_Proof_Own_Business(final JSONArray ja) throws JSONException {

        Business_proof_own_array_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            Business_proof_own_array_list.add(salary_proof);
        }
        business_proof_Own_Business = new MyCustomAdapter_Business_proof_Own(context, 0,Business_proof_own_array_list);
        spi_busproof_own_business.setAdapter(business_proof_Own_Business);
        business_proof_Own_Business.notifyDataSetChanged();

    }

    private void Assets_own_fun(final JSONArray ja) throws JSONException {

        Assets_own_array_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            Assets_own_array_list.add(salary_proof);
        }
        Assets_own_adapter = new MyCustomAdapter_Assets_own_adapter(context, 0,Assets_own_array_list);
        spinner_assets_owned.setAdapter(Assets_own_adapter);
        Assets_own_adapter.notifyDataSetChanged();

    }

    private void Vehicle_Type_(final JSONArray ja) throws JSONException {

        Vehicle_type_individual = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            Vehicle_type_individual.add(salary_proof);
        }
        vehicle_type_adapter = new MyCustomAdapter_Vehicle_Type_Adapter(context, 0,Vehicle_type_individual);
        spp_vehicle_type.setAdapter(vehicle_type_adapter);
        vehicle_type_adapter.notifyDataSetChanged();
    }


    private void Crop_type_function(final JSONArray ja) throws JSONException {

        crop_type_array_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            crop_type_array_list.add(salary_proof);
        }
        crop_type_adapter = new MyCustomAdapter_Crop_Type(context, 0,crop_type_array_list);
        what_crop_spinne.setAdapter(crop_type_adapter);
        crop_type_adapter.notifyDataSetChanged();
    }

    //Dairy


    //////ADApter Multi Select

    private class MyCustomAdapter_Business_proof_Individual extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Business_proof;
        IncomeProofPOJO business_proof;
        public MyCustomAdapter_Business_proof_Individual(Context context, int textViewResourceId,
                                                         ArrayList<IncomeProofPOJO> Business_proof) {
            super(context, textViewResourceId, Business_proof);
            this.Business_proof = new ArrayList<IncomeProofPOJO>();
            this.Business_proof.addAll(Business_proof);
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

            MyCustomAdapter_Business_proof_Individual.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Business_proof_Individual.ViewHolder();
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
                        IncomeProofPOJO business_proof = (IncomeProofPOJO) cb.getTag();
                        business_proof.setIP_selected(cb.isChecked());

                        String email = business_proof.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_vusiness_proof_individual.addChip(email, imgUrl, contact);
                        } else {
                            cv_vusiness_proof_individual.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Business_proof_Individual.ViewHolder) convertView.getTag();
            }

            business_proof = Business_proof.get(position);
            holder.name.setText(business_proof.getIP_name());
            holder.name.setChecked(business_proof.isIP_selected());
            holder.name.setTag(business_proof);

            if(business_proof.getIP_name().contains("Business Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Business Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }


    private class MyCustomAdapter_Business_proof_Forming_poultry extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Business_proof_poultry;
        IncomeProofPOJO business_proof_poultry;
        public MyCustomAdapter_Business_proof_Forming_poultry(Context context, int textViewResourceId,
                                                              ArrayList<IncomeProofPOJO> Business_proof_poultry) {
            super(context, textViewResourceId, Business_proof_poultry);
            this.Business_proof_poultry = new ArrayList<IncomeProofPOJO>();
            this.Business_proof_poultry.addAll(Business_proof_poultry);
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

            MyCustomAdapter_Business_proof_Forming_poultry.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Business_proof_Forming_poultry.ViewHolder();
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
                        IncomeProofPOJO business_proof = (IncomeProofPOJO) cb.getTag();
                        business_proof.setIP_selected(cb.isChecked());

                        String email = business_proof.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_Business_proof_poultry.addChip(email, imgUrl, contact);
                        } else {
                            cv_Business_proof_poultry.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Business_proof_Forming_poultry.ViewHolder) convertView.getTag();
            }

            business_proof_poultry = Business_proof_poultry.get(position);
            holder.name.setText(business_proof_poultry.getIP_name());
            holder.name.setChecked(business_proof_poultry.isIP_selected());
            holder.name.setTag(business_proof_poultry);

            if(business_proof_poultry.getIP_name().contains("Business Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Business Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }

    private class MyCustomAdapter_Assets_own_adapter extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Assets_own_;
        IncomeProofPOJO assets_own;
        public MyCustomAdapter_Assets_own_adapter(Context context, int textViewResourceId,
                                                  ArrayList<IncomeProofPOJO> Assets_own_) {
            super(context, textViewResourceId, Assets_own_);
            this.Assets_own_ = new ArrayList<IncomeProofPOJO>();
            this.Assets_own_.addAll(Assets_own_);
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

            MyCustomAdapter_Assets_own_adapter.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Assets_own_adapter.ViewHolder();
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
                        IncomeProofPOJO assets_own = (IncomeProofPOJO) cb.getTag();
                        assets_own.setIP_selected(cb.isChecked());

                        String email = assets_own.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_Assets_Owns.addChip(email, imgUrl, contact);
                        } else {
                            cv_Assets_Owns.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Assets_own_adapter.ViewHolder) convertView.getTag();
            }

            assets_own = Assets_own_.get(position);
            holder.name.setText(assets_own.getIP_name());
            holder.name.setChecked(assets_own.isIP_selected());
            holder.name.setTag(assets_own);

            if(assets_own.getIP_name().contains("Assets Own")){
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

    private class MyCustomAdapter_Business_proof_Forming extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Assets_own_;
        IncomeProofPOJO assets_own;
        public MyCustomAdapter_Business_proof_Forming(Context context, int textViewResourceId,
                                                      ArrayList<IncomeProofPOJO> Assets_own_) {
            super(context, textViewResourceId, Assets_own_);
            this.Assets_own_ = new ArrayList<IncomeProofPOJO>();
            this.Assets_own_.addAll(Assets_own_);
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

            MyCustomAdapter_Business_proof_Forming.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Business_proof_Forming.ViewHolder();
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
                        IncomeProofPOJO assets_own = (IncomeProofPOJO) cb.getTag();
                        assets_own.setIP_selected(cb.isChecked());

                        String email = assets_own.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_business_proof_multiselect_forming.addChip(email, imgUrl, contact);
                        } else {
                            cv_business_proof_multiselect_forming.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Business_proof_Forming.ViewHolder) convertView.getTag();
            }

            assets_own = Assets_own_.get(position);
            holder.name.setText(assets_own.getIP_name());
            holder.name.setChecked(assets_own.isIP_selected());
            holder.name.setTag(assets_own);

            if(assets_own.getIP_name().contains("Business Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Business Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }


    private class MyCustomAdapter_Business_proof_Forming_dairy extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> BP_FD;
        IncomeProofPOJO bp_fd_model;
        public MyCustomAdapter_Business_proof_Forming_dairy(Context context, int textViewResourceId,
                                                            ArrayList<IncomeProofPOJO> BP_FD) {
            super(context, textViewResourceId, BP_FD);
            this.BP_FD = new ArrayList<IncomeProofPOJO>();
            this.BP_FD.addAll(BP_FD);
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

            MyCustomAdapter_Business_proof_Forming_dairy.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Business_proof_Forming_dairy.ViewHolder();
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
                        IncomeProofPOJO bp_fd_model = (IncomeProofPOJO) cb.getTag();
                        bp_fd_model.setIP_selected(cb.isChecked());

                        String email = bp_fd_model.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_Business_proof_dairy.addChip(email, imgUrl, contact);
                        } else {
                            cv_business_proof_multiselect_forming.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Business_proof_Forming_dairy.ViewHolder) convertView.getTag();
            }

            bp_fd_model = BP_FD.get(position);
            holder.name.setText(bp_fd_model.getIP_name());
            holder.name.setChecked(bp_fd_model.isIP_selected());
            holder.name.setTag(bp_fd_model);

            if(bp_fd_model.getIP_name().contains("Business Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Business Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }

    private class MyCustomAdapter_Business_proof_Own extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Assets_own_;
        IncomeProofPOJO assets_own;
        public MyCustomAdapter_Business_proof_Own(Context context, int textViewResourceId,
                                                  ArrayList<IncomeProofPOJO> Assets_own_) {
            super(context, textViewResourceId, Assets_own_);
            this.Assets_own_ = new ArrayList<IncomeProofPOJO>();
            this.Assets_own_.addAll(Assets_own_);
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

            MyCustomAdapter_Business_proof_Own.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Business_proof_Own.ViewHolder();
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
                        IncomeProofPOJO assets_own = (IncomeProofPOJO) cb.getTag();
                        assets_own.setIP_selected(cb.isChecked());

                        String email = assets_own.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_business_proof_own.addChip(email, imgUrl, contact);
                        } else {
                            cv_business_proof_own.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Business_proof_Own.ViewHolder) convertView.getTag();
            }

            assets_own = Assets_own_.get(position);
            holder.name.setText(assets_own.getIP_name());
            holder.name.setChecked(assets_own.isIP_selected());
            holder.name.setTag(assets_own);

            if(assets_own.getIP_name().contains("Business Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Business Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }

    private class MyCustomAdapter_Vehicle_Type_Adapter extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> vahicle_type_list1;
        IncomeProofPOJO vahicletype;
        public MyCustomAdapter_Vehicle_Type_Adapter(Context context, int textViewResourceId,
                                                    ArrayList<IncomeProofPOJO> vahicle_type_list1) {
            super(context, textViewResourceId, vahicle_type_list1);
            this.vahicle_type_list1 = new ArrayList<IncomeProofPOJO>();
            this.vahicle_type_list1.addAll(vahicle_type_list1);
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

            MyCustomAdapter_Vehicle_Type_Adapter.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Vehicle_Type_Adapter.ViewHolder();
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
                        IncomeProofPOJO assets_own = (IncomeProofPOJO) cb.getTag();
                        assets_own.setIP_selected(cb.isChecked());

                        String email = assets_own.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_vehicle_type.addChip(email, imgUrl, contact);
                        } else {
                            cv_vehicle_type.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Vehicle_Type_Adapter.ViewHolder) convertView.getTag();
            }

            vahicletype = vahicle_type_list1.get(position);
            holder.name.setText(vahicletype.getIP_name());
            holder.name.setChecked(vahicletype.isIP_selected());
            holder.name.setTag(vahicletype);

            if(vahicletype.getIP_name().contains("-Select vehicle Type-")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("-Select vehicle Type-");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

    }

    private class MyCustomAdapter_Crop_Type extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Crop_type_list1;
        IncomeProofPOJO croptype;
        public MyCustomAdapter_Crop_Type(Context context, int textViewResourceId,
                                         ArrayList<IncomeProofPOJO> vahicle_type_list1) {
            super(context, textViewResourceId, vahicle_type_list1);
            this.Crop_type_list1 = new ArrayList<IncomeProofPOJO>();
            this.Crop_type_list1.addAll(vahicle_type_list1);
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

            MyCustomAdapter_Crop_Type.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Crop_Type.ViewHolder();
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
                        IncomeProofPOJO assets_own = (IncomeProofPOJO) cb.getTag();
                        assets_own.setIP_selected(cb.isChecked());

                        String email = assets_own.getIP_name();
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
                holder = (MyCustomAdapter_Crop_Type.ViewHolder) convertView.getTag();
            }

            croptype = Crop_type_list1.get(position);
            holder.name.setText(croptype.getIP_name());
            holder.name.setChecked(croptype.isIP_selected());
            holder.name.setTag(croptype);

            if(croptype.getIP_name().contains("-What Kind of Crops (Multiselect)-")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("-Select Crops Type-");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
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

        Objs.ac.StartActivity(mCon, Lead_Crration_Activity.class);
        finish();
        Pref.removeSALARYTYPE(context);
        super.onBackPressed();

    }
}
