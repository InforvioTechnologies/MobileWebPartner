package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Viability_Activity_Data_View extends SimpleActivity {

    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    AppCompatTextView loan_type_newcat,loan_type_new_val_type,type_of_employee_new_val_type,Loan_amount_new_txt,
            loan_type_newcat_appl,loan_type_newcat_appl_total,loan_type_newcat_appl__pincoe,
            loan_type_newcat_appl__are,loan_type_newcat_appl__asets,loan_type_newcat_appl__res_pin,
            loan_type_newcat_appl__res_area,loan_type_newcat_appl__res_type,loan_type_newcat_appl__live,app_salaried_age,
            f_vocation_type,f_what_crop,f_anual_income, f_no_of_acres,f_daily_income,f_avg_monthly_income,
            d_no_of_animals,D_no_month_in_business,d_no_production_Daily,d_how_to_you_sell,
            D_avg_monthly_income, p_no_of_birds,p_supplied_by_which_cmp,p_selling_price,p_profit_affter_selling,
            p_average_monthly_incom,own_business_type_1,own_francies_deler,own_anual_turover,own_service_anual_turover,own_service_bisiness_setup,
            manufacture_anual_turover,manufacture_value_of_stock,manufacture_monthly_sales,
            value_of_machineries,manu_avg_monthly_income,manu_no_of_month_business,p_no_of_month_in_business,f_no_month,
            own_name_of_dealer;

    AppCompatTextView aaplicant_self_name_txt,aaplicant_self_toe_txt,aaplicant_self_age_txt,
            aaplicant_self_vtype_txt,aaplicant_self_vechiceletype_txt,aaplicant_self_no_f_vechtxt,
            aaplicant_self_vocation_txt,aaplicant_self_acres_txt,aaplicant_self_whatkinds_txt,
            aaplicant_self_annua_incm_txt,aaplicant_self_dailm_incm_txt,aaplicant_self_no_animals_txt,
            aplicant_self_no_litrals_txt,aplicant_self_sell_milk1_txt,aplicant_self_no_birds_txt,
            aplicant_self_supp_company_txt,aplicant_self__selling_birds_txt,aplicant_self__afetr_selling_birds_txt,
            aplicant_self_business_type_txt,aplicant_self_business_type_txt_franc,aplicant_self_business_type_txt_Delaership,
            aplicant_self_business_type_txt_annual,aplicant_self_business_type_txt_business_Setup1,
            aplicant_self_business_type_txt_value_f_stock,aplicant_self_business_type_txt_monthly_sales,
            aplicant_self_business_type_txt_value_f_machine,aplicant_self_nbusiness_txt,
            aplicant_self_busin_vintage_txt,aplicant_self_average_profit_txt,aplicant_self_income_txt,
            aplicant_self_office_shop_txt,aplicant_self_residen_tye_txt,aplicant_self_office_shop_pinc_txt,
            aplicant_self_office_shop_area_txt,aplicant_self_assets_own_txt;


    AppCompatTextView co_aaplicant_salried_name_txt,co_aaplicant_salried_age_txt,co_aaplicant_salried_age_Net,
            co_aaplicant_salried_salried_proof,co_aaplicant_salried_name_credit_method,co_aaplicant_salried_Haveing_txt,
            co_aaplicant_salried_exp_txt,co_aaplicant_salried_total_txt,co_aaplicant_salried_pincode_txt,
            co_aaplicant_salried_area_txt,co_aaplicant_salried_own_txt;



    AppCompatTextView  co_aaplicant_self_name_txt,co_aaplicant_self_toe_txt,co_aaplicant_self_age_txt,
            co_aaplicant_self_vtype_txt,co_aaplicant_self_vechiceletype_txt,co_aaplicant_self_no_f_vechtxt,
            co_aaplicant_self_vocation_txt,co_aaplicant_self_acres_txt,co_aaplicant_self_whatkinds_txt,
            co_aaplicant_self_annua_incm_txt,co_aaplicant_self_dailm_incm_txt,co_aaplicant_self_no_animals_txt,
            co_aaplicant_self_no_litrals_txt,co_aaplicant_self_sell_milk1_txt,co_aaplicant_self_no_birds_txt,
            co_aaplicant_self_supp_company_txt,co_aaplicant_self__selling_birds_txt,co_aaplicant_self__afetr_selling_birds_txt,
            co_aaplicant_self_business_type_txt,co_aaplicant_self_business_type_txt_franc,co_aaplicant_self_business_type_txt_Delaership,
            co_aaplicant_self_business_type_txt_annual,co_aaplicant_self_business_type_txt_business_Setup1,
            co_aaplicant_self_business_type_txt_value_f_stock,co_aaplicant_self_business_type_txt_monthly_sales,
            co_aaplicant_self_business_type_txt_value_f_machine,co_aaplicant_self_nbusiness_txt,
            co_aaplicant_self_busin_vintage_txt,co_aaplicant_self_average_profit_txt,
            co_aaplicant_self_income_txt,
            co_aaplicant_self_office_shop_txt,co_aaplicant_self_residen_tye_txt,
            co_aaplicant_self_office_shop_pinc_txt,co_aaplicant_self_office_shop_area_txt,
            co_aaplicant_self_assets_own_txt,self_employement_type,self_age_1,self_business_vintage,
            self_business_income,self_office_shopsetup, off_residence,off_residence_pincode,off_residence_area,assets_own,
            self_residence_pincode,self_residence_area,self_residence_type,self_curr_resi,
            selfvocation_type1,vehicle_type_1,no_vehicle,
            average_monthly_income,no_of_month_in_business,vehicle_type_1_;

    LinearLayout lay_out_applant_salaried,lay_out_applant_selfeployee,lay_out_co_applant_salaried,
            lay_out_co_applant_selfeployee,Ly_experience_appl_live,individual,formin_dairy,self_business,
            res_rented,office_ly,Driver_C_owner, forming,dairy,poultry,Retail_wholesale_business ,service_business,manufacturing;

    String [] area;
    String Employemnt_Status,Applicant_Status,Co_Applicant_status,CO_APPlicant_Employee_Status,bus_employee_type,
            own_business_type,applicant_own_business_type,salary_modestr,work_areaarr;
    JSONObject Viability_object,Applicant_object,Co_applicant_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_viability___data__view);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viability___data__view);
        initTools(R.string.Viability_Data);
     //   loan_type_newcat=(AppCompatTextView)findViewById(R.id.loan_type_newcat);
        loan_type_new_val_type=(AppCompatTextView)findViewById(R.id.loan_type_new_val_type);
        type_of_employee_new_val_type=(AppCompatTextView)findViewById(R.id.type_of_employee_new_val_type);
        Loan_amount_new_txt =(AppCompatTextView)findViewById(R.id.Loan_amount_new_txt);
        loan_type_newcat_appl =(AppCompatTextView)findViewById(R.id.loan_type_newcat_appl);
        loan_type_newcat_appl_total =(AppCompatTextView)findViewById(R.id.loan_type_newcat_appl_total);
        loan_type_newcat_appl__pincoe =(AppCompatTextView)findViewById(R.id.loan_type_newcat_appl__pincoe);
        loan_type_newcat_appl__are =(AppCompatTextView)findViewById(R.id.loan_type_newcat_appl__are);
        loan_type_newcat_appl__asets =(AppCompatTextView)findViewById(R.id.loan_type_newcat_appl__asets);
        loan_type_newcat_appl__res_pin =(AppCompatTextView)findViewById(R.id.loan_type_newcat_appl__res_pin);
        loan_type_newcat_appl__res_area =(AppCompatTextView)findViewById(R.id.loan_type_newcat_appl__res_area);
        loan_type_newcat_appl__res_type =(AppCompatTextView)findViewById(R.id.loan_type_newcat_appl__res_type);
        loan_type_newcat_appl__live =(AppCompatTextView)findViewById(R.id.loan_type_newcat_appl__live);
        app_salaried_age =(AppCompatTextView)findViewById(R.id.app_salaried_age);


        own_business_type_1 =(AppCompatTextView)findViewById(R.id.own_business_type_1);
        own_francies_deler =(AppCompatTextView)findViewById(R.id.own_francies_deler);
        own_name_of_dealer =(AppCompatTextView)findViewById(R.id.own_name_of_dealer);
        own_anual_turover =(AppCompatTextView)findViewById(R.id.own_anual_turover);
        own_service_anual_turover =(AppCompatTextView)findViewById(R.id.own_service_anual_turover);
        own_service_bisiness_setup =(AppCompatTextView)findViewById(R.id.own_service_bisiness_setup);
        manufacture_anual_turover =(AppCompatTextView)findViewById(R.id.manufacture_anual_turover);
        manufacture_value_of_stock =(AppCompatTextView)findViewById(R.id.manufacture_value_of_stock);
        manufacture_monthly_sales =(AppCompatTextView)findViewById(R.id.manufacture_monthly_sales);
        value_of_machineries =(AppCompatTextView)findViewById(R.id.value_of_machineries);
        manu_avg_monthly_income =(AppCompatTextView)findViewById(R.id.manu_avg_monthly_income);

      //  manu_no_of_month_business,p_no_of_month_in_business,f_no_month
        manu_no_of_month_business =(AppCompatTextView)findViewById(R.id.manu_no_of_month_business);
        p_no_of_month_in_business =(AppCompatTextView)findViewById(R.id.p_no_of_month_in_business);
        f_no_month =(AppCompatTextView)findViewById(R.id.f_no_month);

        self_employement_type =(AppCompatTextView)findViewById(R.id.self_employement_type);
        self_age_1 =(AppCompatTextView)findViewById(R.id.self_age_1);
        self_business_vintage =(AppCompatTextView)findViewById(R.id.self_business_vintage);
        self_business_income =(AppCompatTextView)findViewById(R.id.self_business_income);
        self_office_shopsetup =(AppCompatTextView)findViewById(R.id.self_office_shopsetup);


        f_vocation_type =(AppCompatTextView)findViewById(R.id.f_vocation_type);
        f_what_crop =(AppCompatTextView)findViewById(R.id.f_what_crop);
        f_anual_income =(AppCompatTextView)findViewById(R.id.f_anual_income);
        f_no_of_acres =(AppCompatTextView)findViewById(R.id.f_no_of_acres);
        f_daily_income =(AppCompatTextView)findViewById(R.id.f_daily_income);
        f_avg_monthly_income =(AppCompatTextView)findViewById(R.id.f_avg_monthly_income);

        d_no_of_animals =(AppCompatTextView)findViewById(R.id.d_no_of_animals);
        d_no_production_Daily =(AppCompatTextView)findViewById(R.id.d_no_production_Daily);
        d_how_to_you_sell =(AppCompatTextView)findViewById(R.id.d_how_to_you_sell);
        D_avg_monthly_income =(AppCompatTextView)findViewById(R.id.D_avg_monthly_income);
        D_no_month_in_business =(AppCompatTextView)findViewById(R.id.D_no_month_in_business);


        p_no_of_birds =(AppCompatTextView)findViewById(R.id.p_no_of_birds);
        p_supplied_by_which_cmp =(AppCompatTextView)findViewById(R.id.p_supplied_by_which_cmp);
        p_selling_price =(AppCompatTextView)findViewById(R.id.p_selling_price);
        p_profit_affter_selling =(AppCompatTextView)findViewById(R.id.p_profit_affter_selling);
        p_average_monthly_incom =(AppCompatTextView)findViewById(R.id.p_average_monthly_incom);



                selfvocation_type1 =(AppCompatTextView)findViewById(R.id.selfvocation_type1);
        vehicle_type_1 =(AppCompatTextView)findViewById(R.id.vehicle_type_1);
        no_vehicle =(AppCompatTextView)findViewById(R.id.no_vehicle);
        average_monthly_income =(AppCompatTextView)findViewById(R.id.average_monthly_income);
        no_of_month_in_business =(AppCompatTextView)findViewById(R.id.no_of_month_in_business);


        off_residence =(AppCompatTextView)findViewById(R.id.off_residence);
        off_residence_pincode =(AppCompatTextView)findViewById(R.id.off_residence_pincode);
        off_residence_area =(AppCompatTextView)findViewById(R.id.off_residence_area);
        assets_own =(AppCompatTextView)findViewById(R.id.assets_own);



        self_residence_pincode =(AppCompatTextView)findViewById(R.id.self_residence_pincode);
        self_residence_area =(AppCompatTextView)findViewById(R.id.self_residence_area);
        self_residence_type =(AppCompatTextView)findViewById(R.id.self_residence_type);
        self_curr_resi =(AppCompatTextView)findViewById(R.id.self_curr_resi);










        co_aaplicant_salried_name_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_name_txt);
        co_aaplicant_salried_age_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_age_txt);
        co_aaplicant_salried_age_Net =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_age_Net);
        co_aaplicant_salried_salried_proof =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_salried_proof);
        co_aaplicant_salried_name_credit_method =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_name_credit_method);
        co_aaplicant_salried_Haveing_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_Haveing_txt);
        co_aaplicant_salried_exp_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_exp_txt);
        co_aaplicant_salried_total_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_total_txt);
        co_aaplicant_salried_pincode_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_pincode_txt);
        co_aaplicant_salried_area_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_area_txt);
        co_aaplicant_salried_own_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_salried_own_txt);



        co_aaplicant_self_name_txt  =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_name_txt);
        co_aaplicant_self_toe_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_toe_txt);
        co_aaplicant_self_age_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_age_txt);
        co_aaplicant_self_vtype_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_vtype_txt);
        co_aaplicant_self_vechiceletype_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_vechiceletype_txt);
        co_aaplicant_self_no_f_vechtxt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_no_f_vechtxt);
        co_aaplicant_self_vocation_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_vocation_txt);
        co_aaplicant_self_acres_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_acres_txt);
        co_aaplicant_self_whatkinds_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_whatkinds_txt);
        co_aaplicant_self_annua_incm_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_annua_incm_txt);
        co_aaplicant_self_dailm_incm_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_dailm_incm_txt);
        co_aaplicant_self_no_animals_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_no_animals_txt);
        co_aaplicant_self_no_litrals_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_no_litrals_txt);
        co_aaplicant_self_sell_milk1_txt  =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_sell_milk1_txt);
        co_aaplicant_self_no_birds_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_no_birds_txt);
        co_aaplicant_self_supp_company_txt=(AppCompatTextView)findViewById(R.id.co_aaplicant_self_supp_company_txt);
        co_aaplicant_self__selling_birds_txt=(AppCompatTextView)findViewById(R.id.co_aaplicant_self__selling_birds_txt);
        co_aaplicant_self__afetr_selling_birds_txt=(AppCompatTextView)findViewById(R.id.co_aaplicant_self__afetr_selling_birds_txt);
        co_aaplicant_self_business_type_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_business_type_txt);
        co_aaplicant_self_business_type_txt_franc =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_business_type_txt_franc);
        co_aaplicant_self_business_type_txt_Delaership =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_business_type_txt_Delaership);
        co_aaplicant_self_business_type_txt_annual =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_business_type_txt_annual);
        co_aaplicant_self_business_type_txt_business_Setup1 =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_business_type_txt_business_Setup1);
        co_aaplicant_self_business_type_txt_value_f_stock =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_business_type_txt_value_f_stock);
        co_aaplicant_self_business_type_txt_monthly_sales =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_business_type_txt_monthly_sales);
        co_aaplicant_self_business_type_txt_value_f_machine =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_business_type_txt_value_f_machine);
        co_aaplicant_self_nbusiness_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_nbusiness_txt);
        co_aaplicant_self_busin_vintage_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_busin_vintage_txt);
        co_aaplicant_self_average_profit_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_average_profit_txt);
        co_aaplicant_self_income_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_income_txt);
        co_aaplicant_self_office_shop_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_office_shop_txt);
        co_aaplicant_self_residen_tye_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_residen_tye_txt);
        co_aaplicant_self_office_shop_pinc_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_office_shop_pinc_txt);
        co_aaplicant_self_office_shop_area_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_office_shop_area_txt);
        co_aaplicant_self_assets_own_txt =(AppCompatTextView)findViewById(R.id.co_aaplicant_self_assets_own_txt);

        res_rented =(LinearLayout)findViewById(R.id.res_rented);
        lay_out_applant_salaried =(LinearLayout)findViewById(R.id.lay_out_applant_salaried);

        forming =(LinearLayout)findViewById(R.id.forming);
        dairy =(LinearLayout)findViewById(R.id.dairy);
        poultry =(LinearLayout)findViewById(R.id.poultry);



        office_ly =(LinearLayout)findViewById(R.id.office_ly);

        lay_out_applant_selfeployee =(LinearLayout)findViewById(R.id.lay_out_applant_selfeployee) ;
        lay_out_co_applant_salaried =(LinearLayout)findViewById(R.id.lay_out_co_applant_salaried) ;
        lay_out_co_applant_selfeployee =(LinearLayout)findViewById(R.id.lay_out_co_applant_selfeployee) ;
        Ly_experience_appl_live =(LinearLayout)findViewById(R.id.Ly_experience_appl_live) ;



        individual =(LinearLayout)findViewById(R.id.individual);
        Driver_C_owner =(LinearLayout)findViewById(R.id.Driver_C_owner);
        formin_dairy =(LinearLayout)findViewById(R.id.formin_dairy);
        self_business =(LinearLayout)findViewById(R.id.self_business);


        Retail_wholesale_business =(LinearLayout)findViewById(R.id.Retail_wholesale_business);
        service_business =(LinearLayout)findViewById(R.id.service_business);
        manufacturing =(LinearLayout)findViewById(R.id.manufacturing);

        View_Viability_data();

    }

    private void View_Viability_data() {

        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }



        //   progressDialog.show();

        Log.e("Request vIew Viability", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.VIEW_VIABILITYDATA, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("respnse Viability data", object.toString());
                        try {
                            Viability_object = object.getJSONObject("response");
                            Applicant_Status =Viability_object.getString("applicant_status");
                            //  Employemnt_Status=Viability_object.getString("employe_status");
                            Applicant_object=Viability_object.getJSONObject("applicant_data");
                            Employemnt_Status=Applicant_object.getString("employe_status");

                            applicant_own_business_type=Applicant_object.getString("bus_vocation");
                            Co_Applicant_status=Viability_object.getString("coapplicant_status");

                            String residence_pincode = Applicant_object.getString("per_pincode");
                            String company_pincode = Applicant_object.getString("ofc_pincode");

                            String ofc_area = Applicant_object.getString("ofc_area");
                            String per_area = Applicant_object.getString("per_area");


                            if(Applicant_Status.contains("success"))
                            {
                                if (Employemnt_Status.contains("1"))
                                {
                                    lay_out_applant_salaried.setVisibility(View.VISIBLE);
                                    lay_out_applant_selfeployee.setVisibility(View.GONE);

                                    salary_modestr=Applicant_object.getString("salary_modestr");
                                 JSONArray work_areaarr_area =Applicant_object.getJSONArray("work_areaarr");
                                 JSONArray per_areaarr =Applicant_object.getJSONArray("per_areaarr");

                                    if(work_areaarr_area.length()>0)
                                        {

                                            for (int i = 0; i < work_areaarr_area.length(); i++) {
                                                try {

                                                    JSONObject J = work_areaarr_area.getJSONObject(i);
                                                    String area = J.getString("area");
                                                    String id = J.getString("id");
                                                    if (id.equals(ofc_area)) {
                                                        loan_type_newcat_appl__are.setText(area);
                                                    }

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }else
                                        {

                                        }

                                    if(per_areaarr.length()>0) {

                                        for (int i = 0; i < per_areaarr.length(); i++) {
                                            try {
                                                JSONObject J = per_areaarr.getJSONObject(i);
                                                String area13 = J.getString("area");
                                                String id_area = J.getString("id");
                                                if (id_area.equals(per_area)) {
                                                    loan_type_newcat_appl__res_area.setText(area13);
                                                }


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                    else {

                                    }

                                    JSONArray assetsstr1 =Applicant_object.getJSONArray("assetsstr");
                                    Log.e("work_areaarr_area",work_areaarr_area.toString());
                                    loan_type_newcat_appl__asets.setText(assetsstr1.toString());

                                    String age = Applicant_object.getString("member_age");
                                    app_salaried_age.setText(age);

                                    String net_salary = Applicant_object.getString("monthly_income");
                                    loan_type_new_val_type.setText(net_salary);

                                    String salry_credit_method = Applicant_object.getString("salary_mode");
                                    Loan_amount_new_txt.setText(salary_modestr);

                                    String salry_proof = Applicant_object.getString("income_proof_typestr");
                                    // type_of_employee_new_val_type.setText(salry_proof);
                                    StringBuffer buff3 = new StringBuffer();
                                    buff3.append(salry_proof);
                                    type_of_employee_new_val_type.setText(buff3.toString());


                                    String experience = Applicant_object.getString("working_experience");
                                    loan_type_newcat_appl.setText(experience);

                                    String total_experience = Applicant_object.getString("total_experience");
                                    loan_type_newcat_appl_total.setText(total_experience);


                                    loan_type_newcat_appl__pincoe.setText(company_pincode);


                                    loan_type_newcat_appl__res_pin.setText(residence_pincode);



                                    String residence_type = Applicant_object.getString("residence_type");
                                    String resident_statusstr1 = Applicant_object.getString("resident_statusstr");
                                    loan_type_newcat_appl__res_type.setText(resident_statusstr1);

                                    if(residence_type.equals("1"))
                                    {
                                        Ly_experience_appl_live.setVisibility(View.GONE);
                                    }else
                                    {
                                        Ly_experience_appl_live.setVisibility(View.VISIBLE);
                                        String current_home_duration = Applicant_object.getString("current_home_duration");
                                        loan_type_newcat_appl__live.setText(current_home_duration);
                                    }

                                } else {

                                    Applicant_object=Viability_object.getJSONObject("applicant_data");

                                    lay_out_applant_salaried.setVisibility(View.GONE);
                                    lay_out_applant_selfeployee.setVisibility(View.VISIBLE);

                                    String bus_employment_typestr = Applicant_object.getString("bus_employment_typestr");
                                    String bus_employment_type = Applicant_object.getString("bus_employment_type");
                                    String member_age = Applicant_object.getString("member_age");

                                    String vintagedoc = Applicant_object.getString("vintagedoc");
                                    String vintage_docstr = Applicant_object.getString("vintage_docstr");


                                    String income_proof_typestr = Applicant_object.getString("income_proof_typestr");

                                    String office_setup = Applicant_object.getString("office_setup");
                                    String office_setupstr = Applicant_object.getString("office_setupstr");

                                    String office_res = Applicant_object.getString("office_res");
                                    String ofc_pincode = Applicant_object.getString("ofc_pincode");
                                   // String ofc_area = Applicant_object.getString("work_areaarr ");


                                   if(office_setup.equals("2"))
                                   {
                                       office_ly.setVisibility(View.VISIBLE);
                                         JSONArray ofc_area1 =Applicant_object.getJSONArray("work_areaarr");

                                    if(ofc_area1.length()>0)
                                    {

                                        for(int i=0;i<ofc_area1.length();i++)
                                        {
                                            try {
                                                JSONObject J = ofc_area1.getJSONObject(i);

                                                String area = J.getString("area");
                                                String id_area11 = J.getString("id");

                                                if(id_area11.equals(ofc_area)) {
                                                    off_residence_area.setText(area);
                                                }


                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }


                                    }
                                   }else
                                   {
                                       office_ly.setVisibility(View.GONE);
                                   }


                                    String assetsstr = Applicant_object.getString("assetsstr");

                                    String per_pincode = Applicant_object.getString("per_pincode");



                                    String acres = Applicant_object.getString("acres");

                                    String crop_types = Applicant_object.getString("crop_types");
                                    String annual_income = Applicant_object.getString("annual_income");
                                    String daily_income = Applicant_object.getString("daily_income");
                                    String monthly_income_F = Applicant_object.getString("monthly_income");
                                    String monthly_income_D = Applicant_object.getString("monthly_income");
                                    String monthly_income_ow = Applicant_object.getString("monthly_income");


                                    String no_of_animals = Applicant_object.getString("no_of_animals");
                                    String no_of_litres = Applicant_object.getString("no_of_litres");
                                    String sell_milk_to = Applicant_object.getString("sell_milk_to");

                                    String is_franchise = Applicant_object.getString("is_franchise");
                                    String value_of_stock = Applicant_object.getString("value_of_stock");
                                    String setup_investment = Applicant_object.getString("setup_investment");
                                    String value_of_machineries1 = Applicant_object.getString("value_of_machineries");
                                    String monthly_sales = Applicant_object.getString("monthly_sales");
                                    String annual_income1 = Applicant_object.getString("annual_income");


                                    if(bus_employment_type.equals("1"))
                                    {

                                        individual.setVisibility(View.VISIBLE);
                                        formin_dairy.setVisibility(View.GONE);
                                        self_business.setVisibility(View.GONE);
                                        String vocation = Applicant_object.getString("vocation");
                                        String vehicle_type = Applicant_object.getString("vehicle_type");
                                        String vocation_str = Applicant_object.getString("vocation_str");
                                        String vehicle_typestr = Applicant_object.getString("vehicle_typestr");
                                        String no_of_vehicles = Applicant_object.getString("no_of_vehicles");
                                        String monthly_income = Applicant_object.getString("monthly_income");
                                        String working_experience = Applicant_object.getString("working_experience");

                                        selfvocation_type1.setText(vocation_str);
                                       // selfvocation_type1.setText(vehicle_type);
                                        //  no_vehicle.setText(no_of_vehicles);
                                        average_monthly_income.setText(monthly_income);
                                        no_of_month_in_business.setText(working_experience);
                                        if(vocation.equals("7"))
                                        {
                                            Driver_C_owner.setVisibility(View.VISIBLE);
                                            vehicle_type_1.setText(vehicle_typestr);
                                            no_vehicle.setText(no_of_vehicles);

                                        }else
                                        {
                                            Driver_C_owner.setVisibility(View.GONE);
                                        }





                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        individual.setVisibility(View.GONE);
                                        formin_dairy.setVisibility(View.VISIBLE);
                                        self_business.setVisibility(View.GONE);

                                        String vocation = Applicant_object.getString("work_vocation");
                                        String working_experience = Applicant_object.getString("working_experience");

                                        String no_of_birds = Applicant_object.getString("no_of_birds");
                                        String company_supplied = Applicant_object.getString("company_supplied");
                                        String selling_price = Applicant_object.getString("selling_price");
                                        String profit = Applicant_object.getString("profit");
                                        String monthly_profit = Applicant_object.getString("monthly_profit");


                                        if(vocation.equals("1"))
                                        {
                                            forming.setVisibility(View.VISIBLE);
                                            dairy.setVisibility(View.GONE);
                                            poultry.setVisibility(View.GONE);
                                            JSONArray jsonArray = Applicant_object.getJSONArray("crop_typesstr");
                                            f_vocation_type.setText(vocation);
                                            f_no_of_acres.setText(acres);
                                            f_what_crop.setText(jsonArray.toString());
                                            f_anual_income.setText(annual_income);
                                            f_daily_income.setText(daily_income);
                                            f_no_month.setText(working_experience);
                                            f_avg_monthly_income.setText(monthly_income_F);

                                        }else if(vocation.equals("2"))
                                        {
                                            forming.setVisibility(View.GONE);
                                            dairy.setVisibility(View.VISIBLE);
                                            poultry.setVisibility(View.GONE);

                                            d_no_of_animals.setText(no_of_animals);
                                            d_no_production_Daily.setText(no_of_litres);
                                            d_how_to_you_sell.setText(sell_milk_to);
                                            D_no_month_in_business.setText(monthly_income_F);
                                            D_avg_monthly_income.setText(monthly_income_D);

                                        } else if(vocation.equals("3"))
                                        {
                                            forming.setVisibility(View.GONE);
                                            dairy.setVisibility(View.GONE);
                                            poultry.setVisibility(View.VISIBLE);

                                            p_no_of_birds.setText(no_of_birds);
                                            p_supplied_by_which_cmp.setText(company_supplied);
                                            p_selling_price.setText(selling_price);
                                            p_profit_affter_selling.setText(profit);
                                            p_no_of_month_in_business.setText(working_experience);
                                            p_average_monthly_incom.setText(monthly_income_D);

                                        }






                                    }else if(bus_employment_type.equals("3"))
                                    {
                                        String vocation = Applicant_object.getString("bus_vocation");
                                        String working_experience = Applicant_object.getString("working_experience");
                                        individual.setVisibility(View.GONE);
                                        formin_dairy.setVisibility(View.GONE);
                                        self_business.setVisibility(View.VISIBLE);

                                        if(vocation.equals("1"))
                                        {
                                            Retail_wholesale_business.setVisibility(View.VISIBLE);
                                            service_business.setVisibility(View.GONE);
                                            manufacturing.setVisibility(View.GONE);

                                            own_francies_deler.setText(is_franchise);
                                            own_name_of_dealer.setText(monthly_income_D);
                                            own_anual_turover.setText(annual_income1);

                                        }else if(vocation.equals("2"))
                                        {
                                            Retail_wholesale_business.setVisibility(View.GONE);
                                            service_business.setVisibility(View.VISIBLE);
                                            manufacturing.setVisibility(View.GONE);

                                            own_service_anual_turover.setText(annual_income1);
                                            own_service_bisiness_setup.setText(setup_investment);

                                        }else if(vocation.equals("3"))
                                        {
                                            Retail_wholesale_business.setVisibility(View.GONE);
                                            service_business.setVisibility(View.GONE);
                                            manufacturing.setVisibility(View.VISIBLE);
                                            manufacture_anual_turover.setText(annual_income1);
                                            manufacture_value_of_stock.setText(value_of_stock);
                                            manufacture_monthly_sales.setText(monthly_sales);
                                            value_of_machineries.setText(value_of_machineries1);
                                        }

                                        manu_no_of_month_business.setText(working_experience);
                                        manu_avg_monthly_income.setText(monthly_income_ow);
                                    }

                                    own_business_type_1.setText(monthly_income_D);

                                    self_business_vintage.setText(vintage_docstr);
                                   self_employement_type.setText(bus_employment_typestr);
                                   self_age_1.setText(member_age);
                                    self_business_income.setText(income_proof_typestr);
                                    self_office_shopsetup.setText(office_setupstr);
                                    off_residence.setText(office_res);
                                    off_residence_pincode.setText(ofc_pincode);
                                   // off_residence_area.setText(ofc_area);
                                    assets_own.setText(assetsstr);

                                    self_residence_pincode.setText(per_pincode);

                                    JSONArray per_areaarr =Applicant_object.getJSONArray("per_areaarr");

                                    try {
                                        JSONObject J = per_areaarr.getJSONObject(0);
                                        String area = J.getString("area");
                                        String id_area1 = J.getString("id");
                                        self_residence_area.setText(area);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    String resident_status = Applicant_object.getString("resident_status");
                                    String resident_statusstr = Applicant_object.getString("resident_statusstr");
                                    String current_home_duration = Applicant_object.getString("current_home_duration");
                                    self_residence_type.setText(resident_statusstr);
                                    if(resident_status.equals(1))
                                    {
                                        res_rented.setVisibility(View.VISIBLE);
                                    }else
                                    {
                                        res_rented.setVisibility(View.GONE);
                                    }
                                   self_curr_resi.setText(current_home_duration);


                                }
                            }
                            else
                            {
                                lay_out_applant_salaried.setVisibility(View.GONE);
                                lay_out_applant_selfeployee.setVisibility(View.GONE);
                            }

                            if(Co_Applicant_status.contains("success"))
                            {

                                Co_applicant_object=Viability_object.getJSONObject("coapplicant_data");
                                CO_APPlicant_Employee_Status=Co_applicant_object.getString("employe_status");
                                bus_employee_type=Co_applicant_object.getString("bus_employment_type");
                                own_business_type=Co_applicant_object.getString("bus_vocation");

                                if(CO_APPlicant_Employee_Status.contains("1"))
                                {

                                    lay_out_co_applant_salaried.setVisibility(View.VISIBLE);
                                    lay_out_co_applant_selfeployee.setVisibility(View.GONE);

                                    String member_age=Co_applicant_object .getString("member_age");
                                    app_salaried_age.setText(member_age);

                                    String net_salary=Co_applicant_object .getString("monthly_income");
                                    co_aaplicant_salried_age_Net.setText(net_salary);

                                    String salar_credit=Co_applicant_object .getString("salary_mode");
                                    co_aaplicant_salried_name_credit_method.setText(salar_credit);

                                    String salary_multiselect=Co_applicant_object .getString("income_proof_type");
                                    //co_aaplicant_salried_salried_proof.setText(salary_multiselect);
                                    StringBuffer buff1 = new StringBuffer();
                                    buff1.append(salary_multiselect);
                                    co_aaplicant_salried_salried_proof.setText(buff1.toString());

                                    String wrk_experience=Co_applicant_object .getString("working_experience");
                                    co_aaplicant_salried_exp_txt.setText(wrk_experience);

                                    String total_Expr=Co_applicant_object .getString("total_experience");
                                    co_aaplicant_salried_total_txt.setText(total_Expr);

                                    String cmpy_pincode=Co_applicant_object .getString("ofc_pincode");
                                    co_aaplicant_salried_pincode_txt.setText(cmpy_pincode);



                                }
                                else
                                {

                                    lay_out_co_applant_salaried.setVisibility(View.GONE);
                                    lay_out_co_applant_selfeployee.setVisibility(View.VISIBLE);

                                    String name_co_applicant=Co_applicant_object .getString("member_name");
                                    co_aaplicant_self_name_txt.setText(name_co_applicant);

                                    String bus_employment_typestr=Co_applicant_object .getString("bus_employment_type");
                                    co_aaplicant_self_toe_txt.setText(bus_employment_typestr);

                                    if(bus_employment_typestr.contains("1")) {
                                        String vocation = Co_applicant_object.getString("vocation");
                                        co_aaplicant_self_vtype_txt.setText(vocation);

                                        String vehicle_type = Co_applicant_object.getString("vehicle_type");
                                        co_aaplicant_self_vechiceletype_txt.setText(vehicle_type);

                                        String noofvehi = Co_applicant_object.getString("no_of_vehicles");
                                        co_aaplicant_self_no_f_vechtxt.setText(noofvehi);
                                    }
                                    if(bus_employment_typestr.contains("2")) {

                                        String acres = Co_applicant_object.getString("acres");
                                        co_aaplicant_self_acres_txt.setText(acres);
                                        String crop_types = Co_applicant_object.getString("crop_types");
                                        co_aaplicant_self_whatkinds_txt.setText(crop_types);
                                        String annual_income = Co_applicant_object.getString("annual_income");
                                        co_aaplicant_self_annua_incm_txt.setText(annual_income);
                                        String daily_income = Co_applicant_object.getString("daily_income");
                                        co_aaplicant_self_dailm_incm_txt.setText(daily_income);
                                        String no_of_animals = Co_applicant_object.getString("no_of_animals");
                                        co_aaplicant_self_no_animals_txt.setText(no_of_animals);
                                        String no_of_litres = Co_applicant_object.getString("no_of_litres");
                                        co_aaplicant_self_no_litrals_txt.setText(no_of_litres);
                                        String sell_milk_to = Co_applicant_object.getString("sell_milk_to");
                                        co_aaplicant_self_sell_milk1_txt.setText(sell_milk_to);
                                        String no_of_birds = Co_applicant_object.getString("no_of_birds");
                                        co_aaplicant_self_no_birds_txt.setText(no_of_birds);
                                        String company_supplied = Co_applicant_object.getString("company_supplied");
                                        co_aaplicant_self_supp_company_txt.setText(company_supplied);
                                        String selling_price = Co_applicant_object.getString("selling_price");
                                        co_aaplicant_self__selling_birds_txt.setText(selling_price);
                                        String profit = Co_applicant_object.getString("profit");
                                        co_aaplicant_self__afetr_selling_birds_txt.setText(profit);
                                    }



                                    if(bus_employment_typestr.contains("3")) {

                                        if(own_business_type.contains("1"))

                                        {
                                            String franchise = Co_applicant_object.getString("setup_investment");
                                            co_aaplicant_self_business_type_txt_franc.setText(franchise);
                                        }


                                        if(own_business_type.contains("2")) {
                                            String setup_investment = Co_applicant_object.getString("setup_investment");
                                            co_aaplicant_self_business_type_txt_business_Setup1.setText(setup_investment);
                                        }

                                        if(own_business_type.contains("3")) {

                                            String value_of_stock = Co_applicant_object.getString("value_of_stock");
                                            co_aaplicant_self_business_type_txt_value_f_stock.setText(value_of_stock);
                                            String monthly_sales = Co_applicant_object.getString("monthly_sales");
                                            co_aaplicant_self_business_type_txt_monthly_sales.setText(monthly_sales);
                                            String value_of_machineries = Co_applicant_object.getString("value_of_machineries");
                                            co_aaplicant_self_business_type_txt_value_f_machine.setText(value_of_machineries);
                                        }
                                    }

                                    String monthly_profit=Co_applicant_object .getString("monthly_profit");
                                    co_aaplicant_self_average_profit_txt.setText(monthly_profit);
                                    String assets=Co_applicant_object .getString("assets");
                                    StringBuffer buff1 = new StringBuffer();
                                    buff1.append(assets);
                                    co_aaplicant_self_assets_own_txt.setText(buff1.toString());
                                    // co_aaplicant_self_assets_own_txt.setText(assets);

                                    String office_res=Co_applicant_object .getString("office_res");
                                    co_aaplicant_self_residen_tye_txt.setText(office_res);

                                }

                                   /* else
                                    {
                                        lay_out_co_applant_salaried.setVisibility(View.GONE);
                                        lay_out_co_applant_selfeployee.setVisibility(View.GONE);
                                    }*/



                            }else
                            {
                                lay_out_co_applant_salaried.setVisibility(View.GONE);
                                lay_out_co_applant_selfeployee.setVisibility(View.GONE);
                            }






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }







                        // progressDialog.dismiss();
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
