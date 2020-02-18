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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import in.loanwiser.partnerapp.PartnerActivitys.IncomeProofPOJO;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Viability_Check_BL extends SimpleActivity {

    private Spinner emp_type,spi_vocation_type_,spinner_residence_type,office_spinner_residence_type,
                    spi_vocation_forming,spinner_busines_type,
                    has_pan_card,business_incom_proof,business_incom_proof_forming,
            spinner_busines_type_own_business,spi_busproof_individual,
            spinner_assets_owned,spinner_business_proof_txt_F,spi_busproof_own_business,
            spinner_office_shop_setup,business_proof_own_business_spinner,spp_vehicle_type,
            what_crop_spinne,business_incom_proof_D,spinner_business_proof_txt_D,
            spinner_how_do_sell_milk,business_incom_proof_p,spinner_business_proof_txt_P,
            spinner_frenc_deler_sub,spinner_office_shop_setup_ind,spinner_office_shop_setup_far;

    private LinearLayout individual,self_business,formin_dairy,Driver_C_owner,
             res_rented,forming,dairy,poultry,
            Retail_wholesale_business,service_business,manufacturing,ofiice_res_details;
    private AppCompatButton lead_viy_step2;

    Typeface font;
    private Context context = this;
    InputMethodManager imm;
    JSONArray Type_of_employement,have_pan_ar,vocaton_ar,Business_income_proof_ar,
            vocation_type_forming_ar,Residence_ownership_ar,Business_type_own_business,Business_Proof,Assets_own,
            office_shop,vehicle_Type,crop_type,sell_milk,franchise,is_coapplicant,Employement,Business_income_proof;

    String[] EMPLOYEE_TYPE_SA,PAN_ID_SA,Vocation_SA,Business_income_proof_SA,vocation_type_forming__SA,
            Residence_Type_SA,Own_business_type_SA,Selling_Milk_SA,
            Pincode_SA,Office_Shop_SA,franchise_SA,CO_Type_Of_Emp_SA;

    ArrayAdapter<String> Employee_Type_adapter,PAN_ID_Adapter,Vocation_Adapter,Business_income_proof_Adapter,
            vocation_type_forming_Adapter,Residence_Adapter,Own_business_type_Adapter,
            Pincode_Adapter,Office_Shop__Adapter,Selling_Milk_Adapter,franchise__Adapter,CO_Type_Of_Emp_Adapter;

    ChipsView cv_vusiness_proof_individual,cv_Assets_Owns,cv_business_proof_multiselect_forming,cv_business_proof_own,
                 cv_vehicle_type,cv_what_kindof_crop,cv_Business_proof_dairy,
            cv_Business_proof_poultry;

    AppCompatTextView business_details_txt,emp_type1,emp_type2,age_txt,age_txt1,pan_number_txt,
                    pan_number_txt1,residence_details,txt_residence_pincode,txt_residence_pincode1,txt_residence_type,
            txt_residence_type1,current_recidence_txt,current_recidence_txt1,assets_owned_txt,assets_owned_txt1,
            vocation_indiviual_txt,vocation_indiviual_txt1,busines_inco_proof_individua_txt,busines_inco_proof_individua_txt1,
    vehicle_individual_txt,vehicle_individual_txt1,number_of_vehicle_ind_txt,number_of_vehicle_ind_txt1,no_of_year_ind_txt,
    no_of_year_ind_txt1,monthly_incom_txt,monthly_incom_txt1,busproof_ind_txt,busproof_ind_txt1,assets_owned_BL,
            office_setup_txt_ind,office_setup_txt_ind1,vehicle_type_text,office_setup_txt_far,office_setup_txt_far1;

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
            average_monthly_income_own_business,value_of_machineries;



    AutoCompleteTextView residence_pincode_edite_txt,office_residence_pincode_edite_txt;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;
    String  Employee_type_Id,Employee_type_Value,PAN_id,PAN_Value,
            vocaton_id,vocaton_value, Business_income_proof_id,Business_income_proof_value,
            vocation_type_forming_id, vocation_type_forming_value,business_incom_proof_forming_id,
            business_incom_proof_forming_value, residence_id,residence_Value,
            business_own_type_id,business_own_type_Value,
            Office_Shop__id,Office_Shop__value,off_residence_id,off_residence_Value,
            business_incom_proof_own_business_id,business_incom_proof_own_business_value,
            business_incom_proof_Dairy_id,business_incom_proof_Dairy_value,
            selling_milk_id,selling_milk_value,business_incom_proof_Poultry_id,business_incom_proof_Poultry_value,
            franchise__id,franchise__value;

    ArrayList<IncomeProofPOJO> Business_proof_individual,Assets_own_array_list,Business_proof_individual_forming_array_list,
                                Business_proof_own_array_list,Vehicle_type_individual,crop_type_array_list,
                                Business_proof_forming_dairy_array_list,Business_proof_forming_poultry_array_list,bl_self_bus_vintage_proof_list;

    MyCustomAdapter_Business_proof_Individual business_proof_individual_adapter = null;
    MyCustomAdapter_Vehicle_Type_Adapter vehicle_type_adapter = null;

    MyCustomAdapter_Assets_own_adapter Assets_own_adapter = null;

    MyCustomAdapter_Business_Vintage_proof business_vintage_Adapter = null;

    MyCustomAdapter_Crop_Type crop_type_adapter = null;

    ArrayList<String> Assets_myList_values,business_proof_self_list,vehicle_proof_self_list;

    AppCompatTextView self_frm_what_crop_textview,self_frm_business_proof_txt,
            self_own_business_proof_txt,business_proof_type_text;

    // Co Applicant
    String[] Have_Co_Applicant,Area;
    ArrayAdapter<String> Have_Co_Adapter,A_Area;
    Spinner co_applicant_spinner,co_applicant_emp_spinner,pl_Ly_co_app_self_emp_type,spinner_residence_area;
    String IS_CO_Applicant_Id,IS_CO_Applicant_Value,CO_Type_of_employement_ID,CO_Type_of_employement_Value,
            pl_self_ind_Employee_type_Id,pl_self_ind_Employee_type_Value,office_id,office_value,
            residence_area,residence_area_district_id,residence_state_id,
            office_residence_area,office_residence_area_district_id,office_residence_state_id,BL_ind_vocaton_id,
            BL_ind_vocaton_value;

    LinearLayout co_applicant_emp_type,co_applicant_salaried_employed,co_applicant_self_employed,
            pl_self_individual,pl_formin_dairy,pl_self_business,BL_self_office_ownership_type_ly;

    Spinner BL_self_bus_vintage_proof,BL_self_bussiness_proof,BL_self_asstes_owned_spinner,BL_spinner_office_shop_setup_ind,
            office_spinner_area;
    ChipsView BL_self_cv_bus_vintage_proof,BL_self_cv_business_proof,BL_self_asstes_owned_CV;
    String  V_age,V_no_of_vehicle_edit_txt,V_no_of_years_work_ind_edit_txt,V_avg_monthly_incom_edit_txt,V_no_of_acres_edit_txt,
            V_anual_income_edit_txt,V_daily_income_f,V_number_of_years_in_work_F,V_average_monthly_income_F,V_no_of_animals,
            V_no_of_liters_edit_txt,V_no_of_years_in_works_D,V_avg_monthly_income_D,V_no_of_birds_edit_txt,V_supply_by_who,
            V_Selling_Price,V_Profit_affter_selling,V_no_of_years_in_work_P,V_avg_monthly_income_Poultry,
            V_delership_company_edit_txt,V_monthly_profit_edit_txt,V_business_investment_own_ser_bus_edit_txt,V_value_of_stock_raw_material,
            V_monthly_sales_manufa,V_value_of_machineries,V_number_of_years_in_work_retails,V_average_monthly_income_own_business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_viability__check__bl);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viability__check__bl);
        initTools(R.string.viy_check);


        progressDialog = new SpotsDialog(context, R.style.Custom);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

       // assets_owned_BL = (AppCompatTextView) findViewById(R.id.assets_owned_BL);

        Log.e("viability check BL ","Businss Loan");
        UISCREEN();
        makeJsonObjReq1();
        Font();
        Click();

        vehicle_type_text = (AppCompatTextView) findViewById(R.id.vehicle_type_text);

        self_frm_what_crop_textview = (AppCompatTextView) findViewById(R.id.self_frm_what_crop_textview);
        self_frm_business_proof_txt = (AppCompatTextView) findViewById(R.id.self_frm_business_proof_txt);
        self_own_business_proof_txt = (AppCompatTextView) findViewById(R.id.self_own_business_proof_txt);


        Assets_myList_values = (ArrayList<String>) getIntent().getSerializableExtra("select_lid_id");
        business_proof_self_list = (ArrayList<String>) getIntent().getSerializableExtra("select_lid_id");
        vehicle_proof_self_list = (ArrayList<String>) getIntent().getSerializableExtra("select_lid_id");



        vehicle_type_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_BL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", vehicle_Type.toString());
                intent.putExtra("select_lid_id", (Serializable) vehicle_proof_self_list);
                startActivity(intent);
            }
        });


        self_frm_what_crop_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_BL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", crop_type.toString());
                intent.putExtra("select_lid_id", (Serializable) business_proof_self_list);
                startActivity(intent);

            }
        });

        self_frm_business_proof_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_BL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Business_Proof.toString());
                intent.putExtra("select_lid_id", (Serializable) business_proof_self_list);
                startActivity(intent);

            }
        });

        self_own_business_proof_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_BL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Business_Proof.toString());
                intent.putExtra("select_lid_id", (Serializable) business_proof_self_list);
                startActivity(intent);

            }
        });

     /*   business_proof_type_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_BL.this, Multi_Select_checkbox.class);
                intent.putExtra("jsonArray", Business_Proof.toString());
                intent.putExtra("select_lid_id", (Serializable) business_proof_self_list);
                startActivity(intent);

            }
        });*/

    }

    private void UISCREEN()
    {
        lead_viy_step2 = (AppCompatButton) findViewById(R.id.lead_viy_step2);
        emp_type =(Spinner) findViewById(R.id.emp_type);

        spi_vocation_type_ =(Spinner) findViewById(R.id.spi_vocation_type_);
        spinner_residence_type =(Spinner) findViewById(R.id.spinner_residence_type);
        office_spinner_residence_type =(Spinner) findViewById(R.id.office_spinner_residence_type);
        office_spinner_area =(Spinner) findViewById(R.id.office_spinner_area);
        spi_vocation_forming =(Spinner) findViewById(R.id.spi_vocation_forming);
        spinner_how_do_sell_milk =(Spinner) findViewById(R.id.spinner_how_do_sell_milk);
      //  spinner_busines_type =(Spinner) findViewById(R.id.spinner_busines_type);
        spinner_busines_type_own_business =(Spinner) findViewById(R.id.spinner_busines_type_own_business);

        what_crop_spinne =(Spinner) findViewById(R.id.what_crop_spinne);

        co_applicant_spinner = (Spinner) findViewById(R.id.co_applicant_spinner);
        spinner_residence_area = (Spinner) findViewById(R.id.spinner_residence_area);
        co_applicant_emp_spinner = (Spinner) findViewById(R.id.co_applicant_emp_spinner);
        pl_Ly_co_app_self_emp_type = (Spinner) findViewById(R.id.pl_Ly_co_app_self_emp_type);


        spinner_business_proof_txt_F =(Spinner) findViewById(R.id.spinner_business_proof_txt_F);
        spinner_business_proof_txt_D =(Spinner) findViewById(R.id.spinner_business_proof_txt_D);
        spinner_business_proof_txt_P =(Spinner) findViewById(R.id.spinner_business_proof_txt_P);
        spinner_assets_owned =(Spinner) findViewById(R.id.spinner_assets_owned);
        spp_vehicle_type =(Spinner) findViewById(R.id.spp_vehicle_type);
        spinner_frenc_deler_sub =(Spinner) findViewById(R.id.spinner_frenc_deler_sub);


        spi_busproof_own_business =(Spinner) findViewById(R.id.spi_busproof_own_business);

        //BL_self_bus_vintage_proof,BL_self_bussiness_proof,BL_self_asstes_owned_spinner
        BL_self_bus_vintage_proof =(Spinner) findViewById(R.id.BL_self_bus_vintage_proof);
        BL_self_bussiness_proof =(Spinner) findViewById(R.id.BL_self_bussiness_proof);
        BL_self_asstes_owned_spinner =(Spinner) findViewById(R.id.BL_self_asstes_owned_spinner);
        BL_spinner_office_shop_setup_ind =(Spinner) findViewById(R.id.BL_spinner_office_shop_setup_ind);


        individual = (LinearLayout) findViewById(R.id.individual);
        formin_dairy = (LinearLayout) findViewById(R.id.formin_dairy);
        self_business = (LinearLayout) findViewById(R.id.self_business);
        Driver_C_owner = (LinearLayout) findViewById(R.id.Driver_C_owner);
        res_rented = (LinearLayout) findViewById(R.id.res_rented);
        forming = (LinearLayout) findViewById(R.id.forming);
        dairy = (LinearLayout) findViewById(R.id.dairy);
        poultry = (LinearLayout) findViewById(R.id.poultry);

        BL_self_cv_bus_vintage_proof =(ChipsView) findViewById(R.id.BL_self_cv_bus_vintage_proof);


        cv_Assets_Owns =(ChipsView) findViewById(R.id.cv_Assets_Owns);
        cv_business_proof_multiselect_forming =(ChipsView) findViewById(R.id.cv_business_proof_multiselect_forming);
               cv_business_proof_own =(ChipsView) findViewById(R.id.cv_business_proof_own);
        cv_vehicle_type =(ChipsView) findViewById(R.id.cv_vehicle_type);
        cv_what_kindof_crop =(ChipsView) findViewById(R.id.cv_what_kindof_crop);

        residence_pincode_edite_txt = (AutoCompleteTextView) findViewById(R.id.residence_pincode_edite_txt);
        office_residence_pincode_edite_txt = (AutoCompleteTextView) findViewById(R.id.office_residence_pincode_edite_txt);
        Retail_wholesale_business = (LinearLayout) findViewById(R.id.Retail_wholesale_business);
        service_business = (LinearLayout) findViewById(R.id.service_business);
        manufacturing = (LinearLayout) findViewById(R.id.manufacturing);
        ofiice_res_details = (LinearLayout) findViewById(R.id.ofiice_res_details);
        BL_self_office_ownership_type_ly = (LinearLayout) findViewById(R.id.BL_self_office_ownership_type_ly);

        //co_applicant_emp_type,co_applicant_salaried_employed,co_applicant_self_employed;
        co_applicant_emp_type = (LinearLayout) findViewById(R.id.co_applicant_emp_type);
        co_applicant_salaried_employed = (LinearLayout) findViewById(R.id.co_applicant_salaried_employed);
        co_applicant_self_employed = (LinearLayout) findViewById(R.id.co_applicant_self_employed);

        pl_self_individual = (LinearLayout) findViewById(R.id.pl_self_individual);
        pl_formin_dairy = (LinearLayout) findViewById(R.id.pl_formin_dairy);
        pl_self_business = (LinearLayout) findViewById(R.id.pl_self_business);

        business_details_txt = (AppCompatTextView) findViewById(R.id.business_details_txt);
        office_setup_txt_ind = (AppCompatTextView) findViewById(R.id.office_setup_txt_ind);


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

    }

    private void Font()
    {

       font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");

      //  business_details_txt.setTypeface(font);
    }
    private void Click() {

        residence_pincode_edite_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String workpincode = residence_pincode_edite_txt.getText().toString();

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

        office_residence_pincode_edite_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String workpincode = office_residence_pincode_edite_txt.getText().toString();

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



      /*  lead_viy_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_BL.this, Eligibility_BL.class);
                startActivity(intent);
                finish();
            }
        });*/

        lead_viy_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Employee_type_Id.equals("0"))
                {
                    Toast.makeText(context,"please Select the Employee type",Toast.LENGTH_SHORT).show();
                }else
                {
                    if (!Age_validation()) {
                        return;
                    }
                    if(Employee_type_Id.equals("1"))
                    {
                        validation_individual();


                    }else if(Employee_type_Id.equals("2")) {

                        if(vocation_type_forming_id.equals("0"))
                        {
                            Toast.makeText(context,"please Select the vocation Type",Toast.LENGTH_SHORT).show();
                        }else  if(vocation_type_forming_id.equals("1"))
                        {

                            validation_forming();

                        }else if(vocation_type_forming_id.equals("2"))
                        {

                            validate_dairy();

                        }else if(vocation_type_forming_id.equals("3"))
                        {

                            validation_poultry();
                        }

                    }else if(Employee_type_Id.equals("3"))
                    {
                        Validate_own_Business();

                    }

                }
            ////////////////////////

            }
        });

    }


    private void validation_individual()
    {
        if(vocaton_id.equals("0"))
        {
            Toast.makeText(context,"please Select the vocation Type",Toast.LENGTH_SHORT).show();
        }else
        {
            if(vocaton_id.equals("7"))
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



                commValidation();
            }else
            {


                if (!no_of_years_wrk()) {
                    return;
                }

                if (!Avg_Monthly_income()) {
                    return;
                }
                commValidation();

            }

        }
    }

    private void validation_forming()
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

    private void validate_dairy()
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
            }

    }

    private void validation_poultry()
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


    }

    private void Validate_own_Business()
    {
        if(business_own_type_id.equals("0"))
        {
            Toast.makeText(context,"please Select the business type",Toast.LENGTH_SHORT).show();

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

                        if (!Value_of_Machinaries()) {
                            return;
                        }

                        Own_Bus_No_year_com_validation();

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

    }

    private void commValidation()
    {
        if(office_id.equals("0"))
        {
            Toast.makeText(context,"please Select office Setup",Toast.LENGTH_SHORT).show();

        }else
        {
            if(residence_id.equals("0"))
            {
                Toast.makeText(context,"please Select office ownership type",Toast.LENGTH_SHORT).show();

            }else
            {
                if (!office_pincode()) {
                    return;
                }
                resi_val();

            }
        }


    }


    private void resi_val()
    {
        if (!Residence_pincode()) {
            return;
        }
        if(residence_id.equals("0"))
        {
            Toast.makeText(context,"please Select residence type",Toast.LENGTH_SHORT).show();
        }else
        {

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
        } else {

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
            office_residence_pincode_edite_txt.setError(getText(R.string.err_curent));
            office_residence_pincode_edite_txt.requestFocus();
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

                if(code.length()==6){
                    GET_AERA_POST(code);
                }else {
                    Objs.a.showToast(context,"Please Select Pin code");
                }

                imm.hideSoftInputFromWindow(residence_pincode_edite_txt.getWindowToken(), 0);
            }
        });



        office_residence_pincode_edite_txt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code1 = (String)adapterView.getItemAtPosition(i);

                if(code1.length()==6){
                    GET_AERA_POST1(code1);
                }else {
                    Objs.a.showToast(context,"Please Select Pin code");
                }

                imm.hideSoftInputFromWindow(residence_pincode_edite_txt.getWindowToken(), 0);
            }
        });

    }

    private void makeJsonObjReq1() {
        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_DROPDOWN_LIST, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("respose Dreopdown", object.toString());

                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        try {

                            Type_of_employement =object.getJSONArray("Type_of_employement");
                            have_pan_ar =object.getJSONArray("have_pan");
                            vocaton_ar =object.getJSONArray("vocaton");
                            Business_income_proof_ar =object.getJSONArray("Business_income_proof");
                            Residence_ownership_ar =object.getJSONArray("Residence_ownership");

                            vocation_type_forming_ar =object.getJSONArray("vocation_type");

                            Business_type_own_business =object.getJSONArray("Business_type");
                            Business_Proof =object.getJSONArray("Business_Proof");

                            Assets_own =object.getJSONArray("Assets_own");
                            office_shop =object.getJSONArray("office_shop");
                            vehicle_Type =object.getJSONArray("vehicle_Type");
                            crop_type =object.getJSONArray("crop_type");
                            sell_milk =object.getJSONArray("sell_milk");


                            franchise =object.getJSONArray("franchise");
                            is_coapplicant =object.getJSONArray("is_coapplicant");
                           // Business_Proof =object.getJSONArray("Business_Proof");
                            Employement =object.getJSONArray("Employement");
                            Co_Type_Of_Employement_Spinner(Employement);
                            Type_of_Employeement(Type_of_employement);
                            HAVE_PAN_Card(have_pan_ar);
                            Vocation(vocaton_ar);
                            Business_income_proof(Business_income_proof_ar);
                            DO_Have_Co_Applicant(is_coapplicant);

                            vocation_type_forming(vocation_type_forming_ar);
                            Residence_Array(Residence_ownership_ar);
                            Business_type_own_business_Array(Business_type_own_business);
                            Business_Proof_individual(Business_income_proof_ar);

                            Runs_own_business_Vintage_Proof(Business_Proof);

                            Assets_own_fun(Assets_own);
                            Office_Shop_(office_shop);
                            Vehicle_Type_(vehicle_Type);
                            Crop_type_function(crop_type);
                           // Selling_milk(sell_milk);

                            Selling_milk(sell_milk);

                            Runs_own_business_franchise(franchise);



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

            String workpincode = residence_pincode_edite_txt.getText().toString();
            //  String workpincode1 = residence_pincode1_edit_txt.getText().toString();

            if(workpincode.length()> 2){
                A_Area.setDropDownViewResource(R.layout.view_spinner_item);
                spinner_residence_area.setAdapter(A_Area);
                spinner_residence_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        try {

                            //   work_pincode_area = ja.getJSONObject(position).getString("id");
                            residence_area = ja.getJSONObject(position).getString("id");
                            residence_area_district_id = ja.getJSONObject(position).getString("district_id");
                            residence_state_id = ja.getJSONObject(position).getString("state_id");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner_residence_area.setOnTouchListener(new View.OnTouchListener() {
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

            String workpincode = office_residence_pincode_edite_txt.getText().toString();
            //  String workpincode1 = residence_pincode1_edit_txt.getText().toString();

            if(workpincode.length()> 2){
                A_Area.setDropDownViewResource(R.layout.view_spinner_item);
                office_spinner_area.setAdapter(A_Area);
                office_spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        try {

                            //   work_pincode_area = ja.getJSONObject(position).getString("id");
                            office_residence_area = ja.getJSONObject(position).getString("id");
                            office_residence_area_district_id = ja.getJSONObject(position).getString("district_id");
                            office_residence_state_id = ja.getJSONObject(position).getString("state_id");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                office_spinner_area.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                        return false;
                    }
                });
            }

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
                              //  ofiice_res_details.setVisibility(View.GONE);
                                break;
                            case 2:
                                individual.setVisibility(View.GONE);
                                formin_dairy.setVisibility(View.VISIBLE);
                                self_business.setVisibility(View.GONE);
                               // ofiice_res_details.setVisibility(View.GONE);
                                break;
                            case 3:

                                String Loantype = "3";
                              //  Pref.putEMPLOYMENT(mCon,Loantype);
                                Pref.putLoanType(mCon,Loantype);
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

            pl_Ly_co_app_self_emp_type.setAdapter(Employee_Type_adapter);
            pl_Ly_co_app_self_emp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        pl_self_ind_Employee_type_Id = Type_of_employement_ar.getJSONObject(position).getString("id");
                        pl_self_ind_Employee_type_Value = Type_of_employement_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

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
            has_pan_card.setAdapter(PAN_ID_Adapter);
            has_pan_card.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            has_pan_card.setOnTouchListener(new View.OnTouchListener() {
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
                        BL_ind_vocaton_id = vocaton_ar.getJSONObject(position).getString("id");
                        BL_ind_vocaton_value = vocaton_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                        if(BL_ind_vocaton_id.equals("7"))
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

                        selling_milk_id = selling_milk_ar.getJSONObject(position).getString("id");
                        selling_milk_value = selling_milk_ar.getJSONObject(position).getString("value");

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
                   // InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    return false;
                }
            });
        }

    }

    private void Office_Shop_(final JSONArray office_shop_ar) throws JSONException {

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
            BL_spinner_office_shop_setup_ind.setAdapter(Office_Shop__Adapter);
            BL_spinner_office_shop_setup_ind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        office_id = office_shop_ar.getJSONObject(position).getString("id");
                        office_value = office_shop_ar.getJSONObject(position).getString("value");

                        if(office_id.equals("2"))
                        {
                            BL_self_office_ownership_type_ly.setVisibility(View.VISIBLE);
                            ofiice_res_details.setVisibility(View.VISIBLE);
                        }else
                        {
                            BL_self_office_ownership_type_ly.setVisibility(View.GONE);
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
            BL_spinner_office_shop_setup_ind.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
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
            spinner_frenc_deler_sub.setAdapter(franchise__Adapter);
            spinner_frenc_deler_sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        franchise__id = franchise_ar.getJSONObject(position).getString("id");
                        franchise__value = franchise_ar.getJSONObject(position).getString("value");

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
            office_spinner_residence_type.setAdapter(Residence_Adapter);

            spinner_residence_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        residence_id = Residence_ownership_ar.getJSONObject(position).getString("id");
                        residence_Value = Residence_ownership_ar.getJSONObject(position).getString("value");

                        if(residence_id.equals("2"))
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
            spinner_residence_type.setOnTouchListener(new View.OnTouchListener() {
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

                       off_residence_id = Residence_ownership_ar.getJSONObject(position).getString("id");
                        off_residence_Value = Residence_ownership_ar.getJSONObject(position).getString("value");

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

                        business_own_type_id = Business_type_own_business_ar.getJSONObject(position).getString("id");
                        business_own_type_Value = Business_type_own_business_ar.getJSONObject(position).getString("value");

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


    private void Runs_own_business_Vintage_Proof(final JSONArray ja) throws JSONException {

        bl_self_bus_vintage_proof_list = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            bl_self_bus_vintage_proof_list.add(salary_proof);
        }
        business_vintage_Adapter = new MyCustomAdapter_Business_Vintage_proof(context, 0,bl_self_bus_vintage_proof_list);
        BL_self_bus_vintage_proof.setAdapter(business_vintage_Adapter);
        business_vintage_Adapter.notifyDataSetChanged();
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
        BL_self_bussiness_proof.setAdapter(business_proof_individual_adapter);
        business_proof_individual_adapter.notifyDataSetChanged();

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
        BL_self_asstes_owned_spinner.setAdapter(Assets_own_adapter);
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
                            BL_self_cv_business_proof.addChip(email, imgUrl, contact);
                        } else {
                            BL_self_cv_business_proof.removeChipBy(contact);
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

            if(business_proof.getIP_name().contains("Business Income Proof")){
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
                            BL_self_asstes_owned_CV.addChip(email, imgUrl, contact);
                        } else {
                            BL_self_asstes_owned_CV.removeChipBy(contact);
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
                            BL_self_cv_bus_vintage_proof.addChip(email, imgUrl, contact);
                        } else {
                            BL_self_cv_bus_vintage_proof.removeChipBy(contact);
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
//ind
        V_age = age_edit_txt.getText().toString();
        V_no_of_vehicle_edit_txt = no_of_vehicle_edit_txt.getText().toString();
        V_no_of_years_work_ind_edit_txt = no_of_years_work_ind_edit_txt.getText().toString();
        V_avg_monthly_incom_edit_txt = avg_monthly_incom_edit_txt.getText().toString();
//fr1
        V_no_of_acres_edit_txt = no_of_acres_edit_txt.getText().toString();
        V_anual_income_edit_txt = anual_income_edit_txt.getText().toString();
        V_daily_income_f = daily_income_f.getText().toString();
        V_number_of_years_in_work_F = number_of_years_in_work.getText().toString();
        V_average_monthly_income_F = average_monthly_income.getText().toString();

//fr2
        V_no_of_animals = no_of_animals.getText().toString();
        V_no_of_liters_edit_txt = no_of_liters_edit_txt.getText().toString();
        V_no_of_years_in_works_D = no_of_years_in_works.getText().toString();
        V_avg_monthly_income_D = avg_monthly_income.getText().toString();

//fr3

                V_no_of_birds_edit_txt = no_of_birds_edit_txt.getText().toString();
                V_supply_by_who = supply_by_who.getText().toString();
                V_Selling_Price = Selling_Price.getText().toString();
                V_Profit_affter_selling = Profit_affter_selling.getText().toString();
                V_no_of_years_in_work_P = no_of_years_in_work_P.getText().toString();
                V_avg_monthly_income_Poultry = avg_monthly_income_Poultry.getText().toString();


  //own_business

        V_delership_company_edit_txt = delership_company_edit_txt.getText().toString();
        V_monthly_profit_edit_txt = monthly_profit_edit_txt.getText().toString();

        //
        V_business_investment_own_ser_bus_edit_txt = business_investment_own_ser_bus_edit_txt.getText().toString();

        //
        V_value_of_stock_raw_material = value_of_stock_raw_material.getText().toString();
        V_monthly_sales_manufa = monthly_sales_manufa.getText().toString();
        V_value_of_machineries = value_of_machineries.getText().toString();

        V_number_of_years_in_work_retails = number_of_years_in_work_retails.getText().toString();
        V_average_monthly_income_own_business = average_monthly_income_own_business.getText().toString();



        JSONObject applicant1 =new JSONObject();

        try {
                applicant1.put("bus_employment_type",Employee_type_Id);
                applicant1.put("age",V_age);

                int b = Integer.parseInt(Employee_type_Id);
                // pl_self_individual,pl_formin_dairy,pl_self_business
                switch(b) {
                    case 1:

                        applicant1.put("work_experiance",V_no_of_years_work_ind_edit_txt);
                        applicant1.put("net_salary",V_avg_monthly_incom_edit_txt);

                        break;
                    case 2:
                        applicant1.put("ind_vocation",vocation_type_forming_id);
                        if(vocation_type_forming_id.equals("1"))
                        {
                            applicant1.put("work_experiance",V_number_of_years_in_work_F);
                            applicant1.put("net_salary",V_average_monthly_income_F);

                        }else if(vocation_type_forming_id.equals("2"))
                        {
                            applicant1.put("work_experiance",V_no_of_years_in_works_D);
                            applicant1.put("net_salary",V_avg_monthly_income_D);

                        }else if(vocation_type_forming_id.equals("3"))
                        {
                            applicant1.put("work_experiance",V_no_of_years_in_work_P);
                            applicant1.put("net_salary",V_avg_monthly_income_Poultry);
                        }
                        break;
                    case 3:

                        applicant1.put("work_experiance",V_number_of_years_in_work_retails);
                        applicant1.put("net_salary",V_average_monthly_income_own_business);
                        break;
                }
                //ind

                applicant1.put("ind_vocation",BL_ind_vocaton_id);
                applicant1.put("no_of_vehicles",V_no_of_vehicle_edit_txt);
             //   applicant1.put("vehicle_type",vehicle_type_array);

                //f
                applicant1.put("crop_types",V_no_of_acres_edit_txt);
                applicant1.put("crop_types",V_anual_income_edit_txt);
                applicant1.put("crop_types",V_daily_income_f);

                applicant1.put("crop_types",V_no_of_animals);
                applicant1.put("crop_types",V_no_of_liters_edit_txt);

                applicant1.put("crop_types",V_no_of_birds_edit_txt);
                applicant1.put("crop_types",V_supply_by_who);
                applicant1.put("crop_types",V_Selling_Price);
                applicant1.put("crop_types",V_Profit_affter_selling);


                applicant1.put("crop_types",selling_milk_id);

                //bbusiness_own_type_id
                 applicant1.put("crop_types",business_own_type_id);
                 applicant1.put("crop_types",V_delership_company_edit_txt);
                 applicant1.put("crop_types",V_monthly_profit_edit_txt);

                 applicant1.put("crop_types",V_business_investment_own_ser_bus_edit_txt);

                 applicant1.put("crop_types",V_monthly_sales_manufa);
                 applicant1.put("crop_types",V_value_of_machineries);

                 applicant1.put("crop_types",franchise__id);



        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            //  J.put(Params.email_id,email);
            J.put("residence_id",residence_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Add Home Laoan", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.ADD_LEAD_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        Log.e("Add_Home_loan Partner", String.valueOf(response));
                        try {

                            if(response.getString(Params.status).equals("Ok")) {


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

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Lead_Crration_Activity.class);
        finish();
        super.onBackPressed();

    }
}
