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
            loan_type_newcat_appl__res_area,loan_type_newcat_appl__res_type,loan_type_newcat_appl__live,app_salaried_age;

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
            co_aaplicant_self_assets_own_txt;

    LinearLayout lay_out_applant_salaried,lay_out_applant_selfeployee,lay_out_co_applant_salaried,lay_out_co_applant_selfeployee;


    String Employemnt_Status,Applicant_Status,Co_Applicant_status,CO_APPlicant_Employee_Status,bus_employee_type,
            own_business_type,applicant_own_business_type;
    JSONObject Viability_object,Applicant_object,Co_applicant_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_viability___data__view);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viability___data__view);
        initTools(R.string.Viability_Data);
        loan_type_newcat=(AppCompatTextView)findViewById(R.id.loan_type_newcat);
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




        aaplicant_self_name_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_name_txt);
        aaplicant_self_toe_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_toe_txt);
        aaplicant_self_age_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_age_txt);
        aaplicant_self_vtype_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_vtype_txt);
        aaplicant_self_vechiceletype_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_vechiceletype_txt);
        aaplicant_self_no_f_vechtxt =(AppCompatTextView)findViewById(R.id.aaplicant_self_no_f_vechtxt);
        aaplicant_self_vocation_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_vocation_txt);
        aaplicant_self_acres_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_acres_txt);
        aaplicant_self_whatkinds_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_whatkinds_txt);
        aaplicant_self_annua_incm_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_annua_incm_txt);
        aaplicant_self_dailm_incm_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_dailm_incm_txt);
        aaplicant_self_no_animals_txt =(AppCompatTextView)findViewById(R.id.aaplicant_self_no_animals_txt);
        aplicant_self_no_litrals_txt =(AppCompatTextView)findViewById(R.id.aplicant_self_no_litrals_txt);
        aplicant_self_sell_milk1_txt =(AppCompatTextView)findViewById(R.id.aplicant_self_sell_milk1_txt);
        aplicant_self_no_birds_txt =(AppCompatTextView)findViewById(R.id.aplicant_self_no_birds_txt);
        aplicant_self_supp_company_txt =(AppCompatTextView)findViewById(R.id.aplicant_self_supp_company_txt);
        aplicant_self__selling_birds_txt =(AppCompatTextView)findViewById(R.id.aplicant_self__selling_birds_txt);
        aplicant_self__afetr_selling_birds_txt =(AppCompatTextView)findViewById(R.id.aplicant_self__afetr_selling_birds_txt);
        aplicant_self_business_type_txt  =(AppCompatTextView)findViewById(R.id.aplicant_self_business_type_txt);
        aplicant_self_business_type_txt_franc  =(AppCompatTextView)findViewById(R.id.aplicant_self_business_type_txt_franc);
        aplicant_self_business_type_txt_Delaership  =(AppCompatTextView)findViewById(R.id.aplicant_self_business_type_txt_Delaership);
        aplicant_self_business_type_txt_annual  =(AppCompatTextView)findViewById(R.id.aplicant_self_business_type_txt_annual);
        aplicant_self_business_type_txt_business_Setup1  =(AppCompatTextView)findViewById(R.id.aplicant_self_business_type_txt_business_Setup1);
        aplicant_self_business_type_txt_value_f_stock  =(AppCompatTextView)findViewById(R.id.aplicant_self_business_type_txt_value_f_stock);
        aplicant_self_business_type_txt_monthly_sales  =(AppCompatTextView)findViewById(R.id.aplicant_self_business_type_txt_monthly_sales);
        aplicant_self_business_type_txt_value_f_machine  =(AppCompatTextView)findViewById(R.id.aplicant_self_business_type_txt_value_f_machine);
        aplicant_self_nbusiness_txt   =(AppCompatTextView)findViewById(R.id.aplicant_self_nbusiness_txt);
        aplicant_self_busin_vintage_txt  =(AppCompatTextView)findViewById(R.id.aplicant_self_busin_vintage_txt);
        aplicant_self_average_profit_txt  =(AppCompatTextView)findViewById(R.id.aplicant_self_average_profit_txt);
        aplicant_self_income_txt  =(AppCompatTextView)findViewById(R.id.aplicant_self_income_txt);
        aplicant_self_office_shop_txt  =(AppCompatTextView)findViewById(R.id.aplicant_self_office_shop_txt);
        aplicant_self_residen_tye_txt  =(AppCompatTextView)findViewById(R.id.aplicant_self_residen_tye_txt);
        aplicant_self_office_shop_pinc_txt  =(AppCompatTextView)findViewById(R.id.aplicant_self_office_shop_pinc_txt);
        aplicant_self_office_shop_area_txt  =(AppCompatTextView)findViewById(R.id.aplicant_self_office_shop_area_txt);
        aplicant_self_assets_own_txt  =(AppCompatTextView)findViewById(R.id.aplicant_self_assets_own_txt);





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

        lay_out_applant_salaried =(LinearLayout)findViewById(R.id.lay_out_applant_salaried) ;
        lay_out_applant_selfeployee =(LinearLayout)findViewById(R.id.lay_out_applant_selfeployee) ;
        lay_out_co_applant_salaried =(LinearLayout)findViewById(R.id.lay_out_co_applant_salaried) ;
        lay_out_co_applant_selfeployee =(LinearLayout)findViewById(R.id.lay_out_co_applant_selfeployee) ;




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

        Log.e("Request vIew Viability", "called");
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




                            if(Applicant_Status.contains("success"))
                            {
                                if (Employemnt_Status.contains("1"))
                                {
                                    lay_out_applant_salaried.setVisibility(View.VISIBLE);
                                    lay_out_applant_selfeployee.setVisibility(View.GONE);


                                    String age = Applicant_object.getString("member_age");
                                    loan_type_newcat.setText(age);

                                    String net_salary = Applicant_object.getString("monthly_income");
                                    loan_type_new_val_type.setText(net_salary);

                                    String salry_credit_method = Applicant_object.getString("salary_mode");
                                    Loan_amount_new_txt.setText(salry_credit_method);

                                    String salry_proof = Applicant_object.getString("income_proof_type");
                                    // type_of_employee_new_val_type.setText(salry_proof);
                                    StringBuffer buff3 = new StringBuffer();
                                    buff3.append(salry_proof);
                                    type_of_employee_new_val_type.setText(buff3.toString());


                                    String experience = Applicant_object.getString("working_experience");
                                    loan_type_newcat_appl.setText(experience);

                                    String total_experience = Applicant_object.getString("total_experience");
                                    loan_type_newcat_appl_total.setText(total_experience);


                                    String company_pincode = Applicant_object.getString("ofc_pincode");
                                    loan_type_newcat_appl__pincoe.setText(company_pincode);

                                    String residence_pincode = Applicant_object.getString("per_pincode");
                                    loan_type_newcat_appl__res_pin.setText(residence_pincode);

                                    String residence_type = Applicant_object.getString("residence_type");
                                    loan_type_newcat_appl__res_type.setText(residence_type);

                                } else {
                                    lay_out_applant_salaried.setVisibility(View.GONE);
                                    lay_out_applant_selfeployee.setVisibility(View.VISIBLE);


                                    String Type_of_employement = Applicant_object.getString("bus_employment_type");
                                    aaplicant_self_toe_txt.setText(Type_of_employement);


                                    if (Type_of_employement.contains("1")) {

                                        String vocatrion = Applicant_object.getString("vocation");
                                        aaplicant_self_vtype_txt.setText(vocatrion);


                                        String vehicle_type = Applicant_object.getString("vehicle_type");
                                        aaplicant_self_vechiceletype_txt.setText(vehicle_type);

                                        String no_f_veh = Applicant_object.getString("no_of_vehicles");
                                        aaplicant_self_no_f_vechtxt.setText(no_f_veh);


                                    }
                                    if (Type_of_employement.contains("2"))
                                    {


                                        String no_f_aceres = Applicant_object.getString("acres");
                                        aaplicant_self_acres_txt.setText(no_f_aceres);
                                        String crops = Applicant_object.getString("crop_types");
                                        aaplicant_self_whatkinds_txt.setText(crops);
                                        String anuualincome = Applicant_object.getString("annual_income");
                                        aaplicant_self_annua_incm_txt.setText(anuualincome);
                                        String dailyincome = Applicant_object.getString("daily_income");
                                        aaplicant_self_dailm_incm_txt.setText(dailyincome);


                                        String no_f_animals = Applicant_object.getString("no_of_animals");
                                        aaplicant_self_no_animals_txt.setText(no_f_animals);
                                        String no_f_liters = Applicant_object.getString("no_of_litres");
                                        aplicant_self_no_litrals_txt.setText(no_f_liters);


                                        String sell_milk = Applicant_object.getString("sell_milk_to");
                                        aplicant_self_sell_milk1_txt.setText(sell_milk);
                                        String no_of_birds = Applicant_object.getString("no_of_birds");
                                        aplicant_self_no_birds_txt.setText(no_of_birds);
                                        String company_supplied = Applicant_object.getString("company_supplied");
                                        aplicant_self_supp_company_txt.setText(company_supplied);
                                        String selling_price = Applicant_object.getString("selling_price");
                                        aplicant_self__selling_birds_txt.setText(selling_price);
                                        String profit = Applicant_object.getString("profit");
                                        aplicant_self__afetr_selling_birds_txt.setText(profit);
                                    }

                                    if (Type_of_employement.contains("3")) {
                                        if (applicant_own_business_type.contains("1")) {
                                            String is_franchise = Applicant_object.getString("is_franchise");
                                            aplicant_self_business_type_txt_franc.setText(is_franchise);
                                        }
                                        if (applicant_own_business_type.contains("2")) {
                                            String setup_investment = Applicant_object.getString("setup_investment");
                                            aplicant_self_business_type_txt_business_Setup1.setText(setup_investment);

                                        }

                                        if (applicant_own_business_type.contains("3")) {
                                            String value_of_stock = Applicant_object.getString("value_of_stock");
                                            aplicant_self_business_type_txt_value_f_stock.setText(value_of_stock);

                                            String monthly_sales = Applicant_object.getString("monthly_sales");
                                            aplicant_self_business_type_txt_monthly_sales.setText(monthly_sales);
                                            String value_of_machineries = Applicant_object.getString("value_of_machineries");
                                            aplicant_self_business_type_txt_value_f_machine.setText(value_of_machineries);
                                        }

                                    }

                                    String monthly_profit = Applicant_object.getString("monthly_profit");
                                    aplicant_self_average_profit_txt.setText(monthly_profit);
                                    String office_res = Applicant_object.getString("office_res");
                                    aplicant_self_residen_tye_txt.setText(office_res);
                                    String member_name = Applicant_object.getString("member_name");
                                    aaplicant_self_name_txt.setText(member_name);
                                    String assets_own = Applicant_object.getString("assets");
                                    //   aplicant_self_assets_own_txt.setText(assets_own);

                                    StringBuffer buff = new StringBuffer();
                                    //  for (int i = 0; i < assets_own.length(); i++) {
                                    buff.append(assets_own);


                                    // }
                                    aplicant_self_assets_own_txt.setText(buff.toString());


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
