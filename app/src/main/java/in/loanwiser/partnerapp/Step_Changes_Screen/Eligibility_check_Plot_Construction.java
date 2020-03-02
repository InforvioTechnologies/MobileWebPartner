package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Eligibility_check_Plot_Construction extends SimpleActivity {

    LinearLayout self,former,salaried,identified_yes_or_no,property_field;
    String salary_type,loan_type;
    private AlertDialog progressDialog;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    Typeface font;
    private Context context = this;
    JSONArray property_size_A,property_purpose_A,transaction_type_A,property_ownership_A,purchased_from_A,
            company_type_A,is_epf_deduct_A,employee_type_A,Other_income_A,gst_reflect_A,
            Current_Residence_A,Self_purchased_from_A,guarantor_provide_A;

    String[] Property_Size_SA,property_Purpose_SA,Transaction_Type_SA,property_Ownership_SA,Purchased_from_SA,
            Company_Type_SA,Employee_Type_SA,EPF_Deducted_SA,Other_income_SA,IS_GST_Reflected,
            Current_residence_SA,Guarenter_SA;

    ArrayAdapter Propert_size_Adapter,Property_Purpose_Adapter,Transaction_Type_Adapter,property_Ownership_Adapter,
            Purchase_from_Adapter,Company_Type_Adapter,Employee_Type_Adapter,EPF_Deducted_Adapter,
            Other_Income_Adapter,IS_GST_Reflact_Adapter,Current_residence_Adapter,
            Guarenter_Adapter;

    String Property_Size_ID,Property_Size_Value,Property_Purpose_Id,Property_Purpose_value,
            Transactio_Type_Id,Transactio_Type_value,Select_Purchased_from_Id,Select_Purchased_from_Value,
            Company_Type_Id,Company_Type_Value,Employee_Type_Id,Employee_Type_Value,
            EPF_Deducted_ID,EPF_Deducted_Value,Other_Income_ID,Other_Income_Value,GST_Reflact_ID,GST_Reflact__Value,
            Current_Residence_ID,Current_Residence_Value, Self_Select_Purchased_from_Id,
            Self_Select_Purchased_from_Value,Self_Current_Residence_ID,Self_Current_Residence_Value,
            Guarenter_Id,Guarenter_Value,Self_Other_Income_ID,Self_Other_Income_Value,
            Self_GST_Reflact_ID,Self_GST_Reflact__Value,Property_ownership_Id,Property_ownership_Value;

    Spinner spnr_Property_Identified;

    //HL salaried

    AppCompatTextView property_Size,property_Size1,plot_area_txt,plot_area_txt1,build_up_area_txt,
            age_of_propert_txt,age_of_propert_txt1,required_loan_txt,required_loan_txt1,
            build_up_area_txt1,carpet_area_txt1,carpet_area_txt,property_price_txt,property_price_txt1,cmp_typ_txt,
            cmp_typ_txt1,Cmp_name,Cmp_name1,property_guidence_value,property_guidence_value1,
            select_purchase_txt,select_purchase_txt1,select_construction_cost_txt,select_construction_cost_txt1,
            prop_market_value,prop_market_value1,contruction_plan_txt,contruction_plan_txt1,
            designation_in_company_txt,designation_in_company_txt1,type_emp_txt,type_emp_txt1,no_ofEmployees_txt
            ,no_ofEmployees_txt1,epf_deducted_in_salary_txt,epf_deducted_in_salary_txt1,
            current_residence_address_proof,current_residence_address_proof1,other_income_details_txt,
            other_income_txt,other_income_txt1,other_incom_amt_txt,other_incom_amt_txt1,is_gst_reflect_txt,is_gst_reflect_txt1;

    Spinner propert_Size,Purpose_spinner_,transaction_type_spi,property_ownerShip,
            spinner_cmp_type,select_purchased_spinner_,epf_deducted_spinner,other_income_spinner,current_recidence_spinner,
            spinn_is_gst_reflect,type_of_employement;

    AppCompatEditText prop_purpose,proposed_comp_list_date,property_ownership_edt_txt,plot_area_edit_txt,
            build_up_area_edit_txt,carpet_area_edit_txt,property_price_edt_txt,company_name_edt_txt,
            property_guidence_value_edt_txt,prop_market_value1_edit_text1,construction_cost_edit_text1,
            construction_plan_edit_txt,designation_in_company,no_of_Employee_edit_txt,
            other_incom_amt_edtxt;


    // HL Self
    AppCompatTextView self_property_guidence_value,self_property_guidence_value1,self_property_market_value_txt,
            self_property_market_value_txt1,self_select_purchase_txt,self_select_purchase_txt1,
            self_land_value_txt,self_land_value_txt1,self_average_bank_txt,self_average_bank_txt1,
            self_current_res_proof_txt,self_current_res_proof_txt1,self_can_you_provide_gure_txt,
            self_can_you_provide_gure_txt1,self_business_reference_name,self_business_reference_name1,
            self_business_refer_mobile_no,self_business_refer_mobile_no1,self_permanent_res_pincode,
            self_permanent_res_pincode1,S_permanent_res_type,S_permanent_res_type1,
            self_other_income_details_txt,self_other_income_txt,self_other_income_txt1,
            self_other_incom_amt_txt,self_other_incom_amt_txt1,self_is_gst_reflect_txt,self_is_gst_reflect_txt1;

    Spinner self_select_purchase_spinner,self_spinner_land_value,self_average_bank_spinner,
            self_current_res_proof_spinner,self_can_you_provide_guarante,self_business_reference_name_spinner,
            self_business_reference_mob_spin,self_other_income_spinner,self_spinn_is_gst_reflect;

    AppCompatEditText self_property_guidence_value_edit_text,self_construction_cost,self_market_value_edit_txt,
            self_permanent_res_type_spinner,self_other_incom_amt_edtxt,
            self_business_reference_name_edit,self_business_reference_mob_edit,
            Average_bank_edt_text,land_value_Edit_txt,self_construction_plan,
            age_of_propert_edt_txt,required_loan_edit_txt;

    AppCompatButton lead_Eligibility_button;

    LinearLayout salaried_other_income_YN,self_other_income_YN;

    AppCompatAutoCompleteTextView self_permanent_res_edit_txt;
    String property_identified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_eligibility_check__lap);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_eligibility_check__plot_construction_loan);
        initTools(R.string.eligi_check);


        salary_type = Pref.getSALARYTYPE(getApplicationContext());
        loan_type = Pref.getLoanType(getApplicationContext());
      //  property_identified = Pref.getPROPERTYIDENTIFIED(getApplicationContext());

        progressDialog = new SpotsDialog(context, R.style.Custom);
        self =(LinearLayout) findViewById(R.id.self);
        former =(LinearLayout) findViewById(R.id.former);
        salaried =(LinearLayout) findViewById(R.id.salaried);
        identified_yes_or_no =(LinearLayout) findViewById(R.id.identified_yes_or_no);
        property_field =(LinearLayout) findViewById(R.id.property_field);

        salaried_other_income_YN = (LinearLayout) findViewById(R.id.salaried_other_income_YN);
        self_other_income_YN = (LinearLayout) findViewById(R.id.self_other_income_YN);



        if(salary_type.equals("2"))
        {
            self.setVisibility(View.GONE);
            former.setVisibility(View.VISIBLE);
            salaried.setVisibility(View.GONE);

        }else if(salary_type.equals("1"))
        {
            salaried.setVisibility(View.VISIBLE);
            self.setVisibility(View.GONE);
            former.setVisibility(View.GONE);

        }else if(salary_type.equals("0"))
        {
            self.setVisibility(View.GONE);
            former.setVisibility(View.GONE);
            salaried.setVisibility(View.GONE);
        }

        makeJsonObjReq1();
        UISCREEN();
        fonts();
        Click();
    }

    private void UISCREEN() {

        lead_Eligibility_button = (AppCompatButton) findViewById(R.id.lead_Eligibility_button);

       // property_Size = (AppCompatTextView) findViewById(R.id.property_Size);
       // property_Size1 = (AppCompatTextView) findViewById(R.id.property_Size1);

      //  age_of_propert_txt = (AppCompatTextView) findViewById(R.id.age_of_propert_txt);
      //  age_of_propert_txt1 = (AppCompatTextView) findViewById(R.id.age_of_propert_txt1);
      //  required_loan_txt = (AppCompatTextView) findViewById(R.id.required_loan_txt);
       // required_loan_txt1 = (AppCompatTextView) findViewById(R.id.required_loan_txt1);

        plot_area_txt = (AppCompatTextView) findViewById(R.id.plot_area_txt);
        plot_area_txt1 = (AppCompatTextView) findViewById(R.id.plot_area_txt1);
        build_up_area_txt = (AppCompatTextView) findViewById(R.id.build_up_area_txt);
        build_up_area_txt1 = (AppCompatTextView) findViewById(R.id.build_up_area_txt1);
        carpet_area_txt1 = (AppCompatTextView) findViewById(R.id.carpet_area_txt1);
        carpet_area_txt = (AppCompatTextView) findViewById(R.id.carpet_area_txt);
        property_price_txt = (AppCompatTextView) findViewById(R.id.property_price_txt);
        property_price_txt1 = (AppCompatTextView) findViewById(R.id.property_price_txt1);
        cmp_typ_txt = (AppCompatTextView) findViewById(R.id.cmp_typ_txt);
        cmp_typ_txt1 = (AppCompatTextView) findViewById(R.id.cmp_typ_txt1);
        Cmp_name = (AppCompatTextView) findViewById(R.id.Cmp_name);
        Cmp_name1 = (AppCompatTextView) findViewById(R.id.Cmp_name1);
        property_guidence_value = (AppCompatTextView) findViewById(R.id.property_guidence_value);
        property_guidence_value1 = (AppCompatTextView) findViewById(R.id.property_guidence_value1);
        select_purchase_txt = (AppCompatTextView) findViewById(R.id.select_purchase_txt);
        select_purchase_txt1 = (AppCompatTextView) findViewById(R.id.select_purchase_txt1);
        select_construction_cost_txt = (AppCompatTextView) findViewById(R.id.select_construction_cost_txt);
        select_construction_cost_txt1 = (AppCompatTextView) findViewById(R.id.select_construction_cost_txt1);
        prop_market_value = (AppCompatTextView) findViewById(R.id.prop_market_value);
        prop_market_value1 = (AppCompatTextView) findViewById(R.id.prop_market_value1);
        contruction_plan_txt = (AppCompatTextView) findViewById(R.id.contruction_plan_txt);
        contruction_plan_txt1 = (AppCompatTextView) findViewById(R.id.contruction_plan_txt1);
        designation_in_company_txt = (AppCompatTextView) findViewById(R.id.designation_in_company_txt);
        designation_in_company_txt1 = (AppCompatTextView) findViewById(R.id.designation_in_company_txt1);
        type_emp_txt = (AppCompatTextView) findViewById(R.id.type_emp_txt);
        type_emp_txt1 = (AppCompatTextView) findViewById(R.id.type_emp_txt1);
        no_ofEmployees_txt = (AppCompatTextView) findViewById(R.id.no_ofEmployees_txt);
        no_ofEmployees_txt1 = (AppCompatTextView) findViewById(R.id.no_ofEmployees_txt1);
        epf_deducted_in_salary_txt = (AppCompatTextView) findViewById(R.id.epf_deducted_in_salary_txt);
        epf_deducted_in_salary_txt1 = (AppCompatTextView) findViewById(R.id.epf_deducted_in_salary_txt1);
        current_residence_address_proof = (AppCompatTextView) findViewById(R.id.current_residence_address_proof);
        current_residence_address_proof1 = (AppCompatTextView) findViewById(R.id.current_residence_address_proof1);
        other_income_details_txt = (AppCompatTextView) findViewById(R.id.other_income_details_txt);
        other_income_txt = (AppCompatTextView) findViewById(R.id.other_income_txt);
        other_income_txt1 = (AppCompatTextView) findViewById(R.id.other_income_txt1);
        other_incom_amt_txt = (AppCompatTextView) findViewById(R.id.other_incom_amt_txt);
        other_incom_amt_txt1 = (AppCompatTextView) findViewById(R.id.other_incom_amt_txt1);
        is_gst_reflect_txt = (AppCompatTextView) findViewById(R.id.is_gst_reflect_txt);
        is_gst_reflect_txt1 = (AppCompatTextView) findViewById(R.id.is_gst_reflect_txt1);



        propert_Size = (Spinner) findViewById(R.id.propert_Size);
        transaction_type_spi = (Spinner) findViewById(R.id.transaction_type_spi);
        spinner_cmp_type = (Spinner) findViewById(R.id.spinner_cmp_type);
        select_purchased_spinner_ = (Spinner) findViewById(R.id.select_purchased_spinner_);
        epf_deducted_spinner = (Spinner) findViewById(R.id.epf_deducted_spinner);
        other_income_spinner = (Spinner) findViewById(R.id.other_income_spinner);
        current_recidence_spinner = (Spinner) findViewById(R.id.current_recidence_spinner);
        spinn_is_gst_reflect = (Spinner) findViewById(R.id.spinn_is_gst_reflect);
        type_of_employement = (Spinner) findViewById(R.id.type_of_employement);


        plot_area_edit_txt = (AppCompatEditText) findViewById(R.id.plot_area_edit_txt);
      //  build_up_area_edit_txt = (AppCompatEditText) findViewById(R.id.build_up_area_edit_txt);
       // proposed_comp_list_date = (AppCompatEditText) findViewById(R.id.proposed_comp_list_date);
        carpet_area_edit_txt = (AppCompatEditText) findViewById(R.id.carpet_area_edit_txt);
        property_price_edt_txt = (AppCompatEditText) findViewById(R.id.property_price_edt_txt);
        company_name_edt_txt = (AppCompatEditText) findViewById(R.id.company_name_edt_txt);
        property_guidence_value_edt_txt = (AppCompatEditText) findViewById(R.id.property_guidence_value_edt_txt);
        prop_market_value1_edit_text1 = (AppCompatEditText) findViewById(R.id.prop_market_value1_edit_text1);
        construction_cost_edit_text1 = (AppCompatEditText) findViewById(R.id.construction_cost_edit_text1);
        construction_plan_edit_txt = (AppCompatEditText) findViewById(R.id.construction_plan_edit_txt);
        designation_in_company = (AppCompatEditText) findViewById(R.id.designation_in_company);

        no_of_Employee_edit_txt = (AppCompatEditText) findViewById(R.id.no_of_Employee_edit_txt);
        other_incom_amt_edtxt = (AppCompatEditText) findViewById(R.id.other_incom_amt_edtxt);


        age_of_propert_edt_txt = (AppCompatEditText) findViewById(R.id.age_of_propert_edt_txt);
        required_loan_edit_txt = (AppCompatEditText) findViewById(R.id.required_loan_edit_txt);



        self_business_reference_name_edit = (AppCompatEditText) findViewById(R.id.self_business_reference_name_edit);
        self_business_reference_mob_edit = (AppCompatEditText) findViewById(R.id.self_business_reference_mob_edit);
        // HL Self

        self_property_guidence_value = (AppCompatTextView) findViewById(R.id.self_property_guidence_value);
        self_property_guidence_value1 = (AppCompatTextView) findViewById(R.id.self_property_guidence_value1);
        self_property_market_value_txt = (AppCompatTextView) findViewById(R.id.self_property_market_value_txt);
        self_property_market_value_txt1 = (AppCompatTextView) findViewById(R.id.self_property_market_value_txt1);
        self_property_market_value_txt1 = (AppCompatTextView) findViewById(R.id.self_property_market_value_txt1);
        self_select_purchase_txt = (AppCompatTextView) findViewById(R.id.self_select_purchase_txt);
        self_select_purchase_txt1 = (AppCompatTextView) findViewById(R.id.self_select_purchase_txt1);
        self_land_value_txt = (AppCompatTextView) findViewById(R.id.self_land_value_txt);
        self_land_value_txt1 = (AppCompatTextView) findViewById(R.id.self_land_value_txt1);
        self_average_bank_txt = (AppCompatTextView) findViewById(R.id.self_average_bank_txt);
        self_average_bank_txt1 = (AppCompatTextView) findViewById(R.id.self_average_bank_txt1);
        self_current_res_proof_txt = (AppCompatTextView) findViewById(R.id.self_current_res_proof_txt);
        self_current_res_proof_txt1 = (AppCompatTextView) findViewById(R.id.self_current_res_proof_txt1);
        self_can_you_provide_gure_txt = (AppCompatTextView) findViewById(R.id.self_can_you_provide_gure_txt);
        self_business_reference_name = (AppCompatTextView) findViewById(R.id.self_business_reference_name);
        self_business_reference_name1 = (AppCompatTextView) findViewById(R.id.self_business_reference_name1);
        self_business_refer_mobile_no = (AppCompatTextView) findViewById(R.id.self_business_refer_mobile_no);
        self_business_refer_mobile_no1 = (AppCompatTextView) findViewById(R.id.self_business_refer_mobile_no1);


        self_other_income_details_txt = (AppCompatTextView) findViewById(R.id.self_other_income_details_txt);
        self_other_income_txt = (AppCompatTextView) findViewById(R.id.self_other_income_txt);
        self_other_income_txt1 = (AppCompatTextView) findViewById(R.id.self_other_income_txt1);
        self_other_incom_amt_txt = (AppCompatTextView) findViewById(R.id.self_other_incom_amt_txt);
        self_other_incom_amt_txt1 = (AppCompatTextView) findViewById(R.id.self_other_incom_amt_txt1);
        self_is_gst_reflect_txt = (AppCompatTextView) findViewById(R.id.self_is_gst_reflect_txt);

        self_select_purchase_spinner = (Spinner) findViewById(R.id.self_select_purchase_spinner);
        self_current_res_proof_spinner = (Spinner) findViewById(R.id.self_current_res_proof_spinner);
        self_can_you_provide_guarante = (Spinner) findViewById(R.id.self_can_you_provide_guarante);



        self_other_income_spinner = (Spinner) findViewById(R.id.self_other_income_spinner);
        self_spinn_is_gst_reflect = (Spinner) findViewById(R.id.self_spinn_is_gst_reflect);


        self_property_guidence_value_edit_text = (AppCompatEditText) findViewById(R.id.self_property_guidence_value_edit_text);
        self_construction_cost = (AppCompatEditText) findViewById(R.id.self_construction_cost);
        self_construction_plan = (AppCompatEditText) findViewById(R.id.self_construction_plan);
        self_market_value_edit_txt = (AppCompatEditText) findViewById(R.id.self_market_value_edit_txt);
        Average_bank_edt_text = (AppCompatEditText) findViewById(R.id.Average_bank_edt_text);
        land_value_Edit_txt = (AppCompatEditText) findViewById(R.id.land_value_Edit_txt);

        self_other_incom_amt_edtxt = (AppCompatEditText) findViewById(R.id.self_other_incom_amt_edtxt);


    }

    private void fonts() {

        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
      //  property_Size.setTypeface(font);
      //  property_Size1.setTypeface(font);
        plot_area_txt.setTypeface(font);
        plot_area_txt1.setTypeface(font);
        build_up_area_txt.setTypeface(font);
        build_up_area_txt1.setTypeface(font);
        carpet_area_txt1.setTypeface(font);
     //   age_of_propert_txt.setTypeface(font);
      //  age_of_propert_txt1.setTypeface(font);
        carpet_area_txt.setTypeface(font);
        property_price_txt.setTypeface(font);
        property_price_txt1.setTypeface(font);
        cmp_typ_txt.setTypeface(font);
        cmp_typ_txt1.setTypeface(font);
        Cmp_name.setTypeface(font);
        Cmp_name1.setTypeface(font);
        property_guidence_value.setTypeface(font);
        property_guidence_value1.setTypeface(font);
        select_purchase_txt.setTypeface(font);
        select_purchase_txt1.setTypeface(font);
        select_construction_cost_txt.setTypeface(font);
        select_construction_cost_txt1.setTypeface(font);
        prop_market_value.setTypeface(font);
        prop_market_value1.setTypeface(font);
        contruction_plan_txt.setTypeface(font);
        contruction_plan_txt1.setTypeface(font);
        designation_in_company_txt.setTypeface(font);
        designation_in_company_txt1.setTypeface(font);
        type_emp_txt.setTypeface(font);
        type_emp_txt1.setTypeface(font);
        no_ofEmployees_txt.setTypeface(font);
        no_ofEmployees_txt1.setTypeface(font);
        epf_deducted_in_salary_txt.setTypeface(font);
        epf_deducted_in_salary_txt1.setTypeface(font);
        current_residence_address_proof.setTypeface(font);
        current_residence_address_proof1.setTypeface(font);
        other_income_details_txt.setTypeface(font);
        other_income_txt.setTypeface(font);
        other_income_txt1.setTypeface(font);
        other_incom_amt_txt.setTypeface(font);
        other_incom_amt_txt1.setTypeface(font);
        is_gst_reflect_txt.setTypeface(font);
        is_gst_reflect_txt1.setTypeface(font);
        //  prop_purpose.setTypeface(font);
        // property_ownership_edt_txt.setTypeface(font);
        plot_area_edit_txt.setTypeface(font);
      //  build_up_area_edit_txt.setTypeface(font);
      //  proposed_comp_list_date.setTypeface(font);
        carpet_area_edit_txt.setTypeface(font);
        property_price_edt_txt.setTypeface(font);
        company_name_edt_txt.setTypeface(font);
        property_guidence_value_edt_txt.setTypeface(font);
        prop_market_value1_edit_text1.setTypeface(font);
        construction_cost_edit_text1.setTypeface(font);
        construction_plan_edit_txt.setTypeface(font);
        designation_in_company.setTypeface(font);
        no_of_Employee_edit_txt.setTypeface(font);
        other_incom_amt_edtxt.setTypeface(font);

        //self
        self_property_guidence_value.setTypeface(font);
        self_property_guidence_value1.setTypeface(font);
        self_property_market_value_txt.setTypeface(font);
        self_property_market_value_txt1.setTypeface(font);
        self_select_purchase_txt.setTypeface(font);
        self_select_purchase_txt1.setTypeface(font);
        self_land_value_txt.setTypeface(font);
        self_land_value_txt1.setTypeface(font);
        self_average_bank_txt.setTypeface(font);
        self_average_bank_txt1.setTypeface(font);
        self_current_res_proof_txt.setTypeface(font);
        self_current_res_proof_txt1.setTypeface(font);
        self_can_you_provide_gure_txt.setTypeface(font);
        self_business_reference_name.setTypeface(font);
        self_business_reference_name1.setTypeface(font);
        self_business_reference_name_edit.setTypeface(font);
        self_business_reference_mob_edit.setTypeface(font);
        self_business_refer_mobile_no.setTypeface(font);
        self_business_refer_mobile_no1.setTypeface(font);


        self_other_income_details_txt.setTypeface(font);
        self_other_income_txt.setTypeface(font);
        self_other_income_txt1.setTypeface(font);
        self_other_incom_amt_txt.setTypeface(font);
        self_other_incom_amt_txt1.setTypeface(font);
        self_is_gst_reflect_txt.setTypeface(font);
        self_property_guidence_value_edit_text.setTypeface(font);
        self_construction_cost.setTypeface(font);
        self_construction_plan.setTypeface(font);
        self_market_value_edit_txt.setTypeface(font);
        self_other_incom_amt_edtxt.setTypeface(font);
        land_value_Edit_txt.setTypeface(font);

    }

    private void Click()
    {

        lead_Eligibility_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Property_Validation();

            }
        });

    }

    private void Property_Validation()
    {


            if (!Ploat_area_()) {
                return;
            }
            if (!Build_up_area()) {
                return;
            }

            if (!age_of_property()) {
                return;
            }

            if (!Carpet_Area_Val()) {
                return;
            }

            if (!Property_Price_Val()) {
                return;
            }
            if (salary_type.equals("1")) {
                Salaried_Validation();

            } else if (salary_type.equals("2")) {
                Self_Validation();

            }

    }


    private void Salaried_Validation()
    {
        if (!Property_Guidence_Value()) {
            return;
        }
        if (!Property_Market_Value()) {
            return;
        }
        if(Select_Purchased_from_Id.equals("0"))
        {
            Toast.makeText(context, "Please Select Purchased From",Toast.LENGTH_SHORT).show();
        }
        else {

            if (!Construction_valdation()) {
                return;
            }

            if (!Construction_Plane_Validation()) {
                return;
            }
            if(Company_Type_Id.equals("0"))
            {
                Toast.makeText(context, "Please Select Company Type",Toast.LENGTH_SHORT).show();
            }else
            {
                if (!Company_Name_Vali()) {
                    return;
                }
                if (!Designation_in_Company()) {
                    return;
                }
                if(Employee_Type_Id.equals("0"))
                {
                    Toast.makeText(context, "Please Select Employee Type",Toast.LENGTH_SHORT).show();

                }else
                {
                    if (!No_off_Employee_Validation()) {
                        return;
                    }
                    if(EPF_Deducted_ID.equals("0"))
                    {
                        Toast.makeText(context, "Please Select EPF Deducted",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        if(Current_Residence_ID.equals("0"))
                        {
                            Toast.makeText(context, "Please Select Current Residence",Toast.LENGTH_SHORT).show();
                        }else
                        {
                            if(Other_Income_ID.equals("0"))
                            {
                                Toast.makeText(context, "Please Select Other Income",Toast.LENGTH_SHORT).show();
                            }else if( ! Other_Income_ID.equals("4"))
                            {
                                salaried_other();
                            }
                            else {

                            }
                        }

                    }


                }

            }

        }
    }

    private void Self_Validation()
    {
        if (!self_prop_gui_Validation()) {
            return;
        }
        if (!self_Market_Value()) {
            return;
        }
        if(Self_Select_Purchased_from_Id.equals("0"))
        {
            Toast.makeText(context,"please Select Purcased From",Toast.LENGTH_SHORT).show();
        }else
        {
            if (!self_land_Value()) {
                return;
            }
            if (!self_construction_cost_Validation()) {
                return;
            }
            if (!self_cons_plane()) {
                return;
            }
            if (!Average_bak_valid()) {
                return;
            }
            if(Self_Current_Residence_ID.equals("0"))
            {
                Toast.makeText(context,"please Select Current residence",Toast.LENGTH_SHORT).show();

            }else
            {
                if(Guarenter_Id.equals("0"))
                {
                    Toast.makeText(context,"please Select Guarenter Id",Toast.LENGTH_SHORT).show();
                }
                if (!self_busi_ref_Name()) {
                    return;
                }
                if (!self_busi_ref_mob_no()) {
                    return;
                }
                if(Self_Other_Income_ID.equals("0"))
                {
                    Toast.makeText(context,"please Select Other Income",Toast.LENGTH_SHORT).show();

                }else if(!Self_Other_Income_ID.equals("4"))
                {
                    self_other();
                }else
                {

                }

            }


        }
    }


    private void self_other()
    {
        if (!self_other_inc_amount()) {
            return;
        }
        if(GST_Reflact_ID.equals("0"))
        {
            Toast.makeText(context,"please Select GST Reflected",Toast.LENGTH_SHORT).show();
        }else
        {

        }

    }

    private void salaried_other()
    {
        if (!Other_Income_Validation()) {
            return;
        }
        if(GST_Reflact_ID.equals("0"))
        {
            Toast.makeText(context, "Please Select GST reflected",Toast.LENGTH_SHORT).show();

        }else
        {

        }
    }
    private boolean Proposed_Completion_Date(){
        if (proposed_comp_list_date.getText().toString().trim().isEmpty() || proposed_comp_list_date.length() < 3) {
            proposed_comp_list_date.setError(getText(R.string.error_rise));
            proposed_comp_list_date.requestFocus();
            return false;
        } else {

        }

        return true;
    }



    private boolean Ploat_area_(){
        if (plot_area_edit_txt.getText().toString().trim().isEmpty() || plot_area_edit_txt.length() < 3) {
            plot_area_edit_txt.setError(getText(R.string.error_rise));
            plot_area_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Build_up_area(){
        if (build_up_area_edit_txt.getText().toString().trim().isEmpty() || build_up_area_edit_txt.length() < 3) {
            build_up_area_edit_txt.setError(getText(R.string.error_rise));
            build_up_area_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean age_of_property(){
        if (age_of_propert_edt_txt.getText().toString().trim().isEmpty() || age_of_propert_edt_txt.length() < 3) {
            age_of_propert_edt_txt.setError(getText(R.string.error_rise));
            age_of_propert_edt_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Carpet_Area_Val(){
        if (carpet_area_edit_txt.getText().toString().trim().isEmpty() || carpet_area_edit_txt.length() < 3) {
            carpet_area_edit_txt.setError(getText(R.string.error_rise));
            carpet_area_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }


    private boolean Property_Price_Val(){
        if (property_price_edt_txt.getText().toString().trim().isEmpty() || property_price_edt_txt.length() < 3) {
            property_price_edt_txt.setError(getText(R.string.error_rise));
            property_price_edt_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    //salaried
    private boolean Property_Guidence_Value(){
        if (property_guidence_value_edt_txt.getText().toString().trim().isEmpty() || property_guidence_value_edt_txt.length() < 3) {
            property_guidence_value_edt_txt.setError(getText(R.string.error_rise));
            property_guidence_value_edt_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }


    private boolean Property_Market_Value(){
        if (prop_market_value1_edit_text1.getText().toString().trim().isEmpty() || prop_market_value1_edit_text1.length() < 3) {
            prop_market_value1_edit_text1.setError(getText(R.string.error_rise));
            prop_market_value1_edit_text1.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Construction_valdation(){
        if (construction_cost_edit_text1.getText().toString().trim().isEmpty() || construction_cost_edit_text1.length() < 3) {
            construction_cost_edit_text1.setError(getText(R.string.error_rise));
            construction_cost_edit_text1.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Construction_Plane_Validation(){
        if (construction_plan_edit_txt.getText().toString().trim().isEmpty() || construction_plan_edit_txt.length() < 3) {
            construction_plan_edit_txt.setError(getText(R.string.error_rise));
            construction_plan_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Company_Name_Vali(){
        if (company_name_edt_txt.getText().toString().trim().isEmpty() || company_name_edt_txt.length() < 3) {
            company_name_edt_txt.setError(getText(R.string.error_rise));
            company_name_edt_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Designation_in_Company(){
        if (designation_in_company.getText().toString().trim().isEmpty() || designation_in_company.length() < 3) {
            designation_in_company.setError(getText(R.string.error_rise));
            designation_in_company.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean No_off_Employee_Validation(){
        if (no_of_Employee_edit_txt.getText().toString().trim().isEmpty() || no_of_Employee_edit_txt.length() < 3) {
            no_of_Employee_edit_txt.setError(getText(R.string.error_rise));
            no_of_Employee_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Other_Income_Validation(){
        if (other_incom_amt_edtxt.getText().toString().trim().isEmpty() || other_incom_amt_edtxt.length() < 3) {
            other_incom_amt_edtxt.setError(getText(R.string.error_rise));
            other_incom_amt_edtxt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    ///self Employee

    private boolean self_prop_gui_Validation(){
        if (self_property_guidence_value_edit_text.getText().toString().trim().isEmpty() || self_property_guidence_value_edit_text.length() < 3) {
            self_property_guidence_value_edit_text.setError(getText(R.string.error_rise));
            self_property_guidence_value_edit_text.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean self_Market_Value(){
        if (self_market_value_edit_txt.getText().toString().trim().isEmpty() || self_market_value_edit_txt.length() < 3) {
            self_market_value_edit_txt.setError(getText(R.string.error_rise));
            self_market_value_edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean self_land_Value(){
        if (land_value_Edit_txt.getText().toString().trim().isEmpty() || land_value_Edit_txt.length() < 3) {
            land_value_Edit_txt.setError(getText(R.string.error_rise));
            land_value_Edit_txt.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean self_construction_cost_Validation(){
        if (self_construction_cost.getText().toString().trim().isEmpty() || self_construction_cost.length() < 3) {
            self_construction_cost.setError(getText(R.string.error_rise));
            self_construction_cost.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean self_cons_plane(){
        if (self_construction_plan.getText().toString().trim().isEmpty() || self_construction_plan.length() < 3) {
            self_construction_plan.setError(getText(R.string.error_rise));
            self_construction_plan.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean Average_bak_valid(){
        if (Average_bank_edt_text.getText().toString().trim().isEmpty() || Average_bank_edt_text.length() < 3) {
            Average_bank_edt_text.setError(getText(R.string.error_rise));
            Average_bank_edt_text.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean self_busi_ref_Name(){
        if (self_business_reference_name_edit.getText().toString().trim().isEmpty() || self_business_reference_name_edit.length() < 3) {
            self_business_reference_name_edit.setError(getText(R.string.error_rise));
            self_business_reference_name_edit.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean self_busi_ref_mob_no(){
        if (self_business_reference_mob_edit.getText().toString().trim().isEmpty() || self_business_reference_mob_edit.length() < 3) {
            self_business_reference_mob_edit.setError(getText(R.string.error_rise));
            self_business_reference_mob_edit.requestFocus();
            return false;
        } else {

        }

        return true;
    }

    private boolean self_other_inc_amount(){
        if (self_other_incom_amt_edtxt.getText().toString().trim().isEmpty() || self_other_incom_amt_edtxt.length() < 3) {
            self_other_incom_amt_edtxt.setError(getText(R.string.error_rise));
            self_other_incom_amt_edtxt.requestFocus();
            return false;
        } else {

        }

        return true;
    }





    private void makeJsonObjReq1() {

        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_DROPDOWN_LIST, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {

                        try {

                            property_size_A =object.getJSONArray("property_size");
                            property_purpose_A =object.getJSONArray("property_purpose");
                            transaction_type_A =object.getJSONArray("transaction_type");
                            property_ownership_A =object.getJSONArray("property_ownership");
                            purchased_from_A =object.getJSONArray("purchased_from");
                            company_type_A =object.getJSONArray("company_type");
                            employee_type_A =object.getJSONArray("employee_type");
                            is_epf_deduct_A =object.getJSONArray("is_epf_deduct");


                            Other_income_A =object.getJSONArray("Other_income");
                            gst_reflect_A =object.getJSONArray("gst_reflect");
                            Current_Residence_A =object.getJSONArray("Current_Residence");
                            Self_purchased_from_A =object.getJSONArray("purchased_from");
                            guarantor_provide_A =object.getJSONArray("guarantor_provide");

                            Log.e("employee_type_A",String.valueOf(employee_type_A));

                          //  Property_Size(property_size_A);

                            Select_Purchased_From(purchased_from_A);
                            Select_Company_Type(company_type_A);
                            Select_Employement_type(employee_type_A);
                            EPF_Deduted(is_epf_deduct_A);
                            Other_Income(Other_income_A);
                            GST_Reflacted(gst_reflect_A);
                            Current_residence_Proof(Current_Residence_A);
                            Self_Guarenter(guarantor_provide_A);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(context, response.toString(),Toast.LENGTH_SHORT).show();
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

    private void Property_Size(final JSONArray Property_size) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Property_Size_SA = new String[Property_size.length()];
        for (int i=0;i<Property_size.length();i++){
            JSONObject J =  Property_size.getJSONObject(i);
            Property_Size_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Property_Size_SA));
            Propert_size_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Propert_size_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            propert_Size.setAdapter(Propert_size_Adapter);
            propert_Size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        Property_Size_ID = Property_size.getJSONObject(position).getString("id");
                        Property_Size_Value = Property_size.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Property_Size_ID", Property_Size_ID);
                        Log.d("Property_Size_Value", Property_Size_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            propert_Size.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }


    private void Select_Purchased_From(final JSONArray select_purcased_from) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Purchased_from_SA = new String[select_purcased_from.length()];
        for (int i=0;i<select_purcased_from.length();i++){
            JSONObject J =  select_purcased_from.getJSONObject(i);
            Purchased_from_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Purchased_from_SA));
            Purchase_from_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Purchase_from_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            select_purchased_spinner_.setAdapter(Purchase_from_Adapter);
            self_select_purchase_spinner.setAdapter(Purchase_from_Adapter);
            select_purchased_spinner_.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        Select_Purchased_from_Id = select_purcased_from.getJSONObject(position).getString("id");
                        Select_Purchased_from_Value = select_purcased_from.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Property_Size_ID", Select_Purchased_from_Id);
                        Log.d("Property_Size_Value", Select_Purchased_from_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            select_purchased_spinner_.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

            self_select_purchase_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        Self_Select_Purchased_from_Id = select_purcased_from.getJSONObject(position).getString("id");
                        Self_Select_Purchased_from_Value = select_purcased_from.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Property_Size_ID", Self_Select_Purchased_from_Id);
                        Log.d("Property_Size_Value", Self_Select_Purchased_from_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            self_select_purchase_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

    }



    private void Select_Company_Type(final JSONArray select_company_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Company_Type_SA = new String[select_company_ar.length()];
        for (int i=0;i<select_company_ar.length();i++){
            JSONObject J =  select_company_ar.getJSONObject(i);
            Company_Type_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Company_Type_SA));
            Company_Type_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Company_Type_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_cmp_type.setAdapter(Company_Type_Adapter);
            spinner_cmp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        Company_Type_Id = select_company_ar.getJSONObject(position).getString("id");
                        Company_Type_Value = select_company_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Company_Type_Id", Company_Type_Id);
                        Log.d("Company_Type_Value", Company_Type_Value);

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

    private void Select_Employement_type(final JSONArray employee_type_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Employee_Type_SA = new String[employee_type_ar.length()];
        for (int i=0;i<employee_type_ar.length();i++){
            JSONObject J =  employee_type_ar.getJSONObject(i);
            Employee_Type_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Employee_Type_SA));
            Employee_Type_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Employee_Type_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            type_of_employement.setAdapter(Employee_Type_Adapter);
            type_of_employement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        Employee_Type_Id = employee_type_ar.getJSONObject(position).getString("id");
                        Employee_Type_Value = employee_type_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Company_Type_Id", Employee_Type_Id);
                        Log.d("Company_Type_Value", Employee_Type_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            type_of_employement.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void EPF_Deduted(final JSONArray employee_type_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        EPF_Deducted_SA = new String[employee_type_ar.length()];
        for (int i=0;i<employee_type_ar.length();i++){
            JSONObject J =  employee_type_ar.getJSONObject(i);
            EPF_Deducted_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(EPF_Deducted_SA));
            EPF_Deducted_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            EPF_Deducted_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            epf_deducted_spinner.setAdapter( EPF_Deducted_Adapter);
            epf_deducted_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {



                        EPF_Deducted_ID = employee_type_ar.getJSONObject(position).getString("id");
                        EPF_Deducted_Value = employee_type_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("EPF_Deducted_ID", EPF_Deducted_ID);
                        Log.d("EPF_Deducted_Value", EPF_Deducted_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            epf_deducted_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Other_Income(final JSONArray other_income_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Other_income_SA = new String[other_income_ar.length()];
        for (int i=0;i<other_income_ar.length();i++){
            JSONObject J =  other_income_ar.getJSONObject(i);
            Other_income_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Other_income_SA));
            Other_Income_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Other_Income_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            other_income_spinner.setAdapter(Other_Income_Adapter);
            self_other_income_spinner.setAdapter(Other_Income_Adapter);
            other_income_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        Other_Income_ID = other_income_ar.getJSONObject(position).getString("id");
                        Other_Income_Value = other_income_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("EPF_Deducted_ID", Other_Income_ID);
                        Log.d("EPF_Deducted_Value", Other_Income_Value);

                        if(Other_Income_ID.equals("4"))
                        {
                            salaried_other_income_YN.setVisibility(View.GONE);
                        }else
                        {
                            salaried_other_income_YN.setVisibility(View.VISIBLE);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            other_income_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

            self_other_income_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        Self_Other_Income_ID = other_income_ar.getJSONObject(position).getString("id");
                        Self_Other_Income_Value = other_income_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Self_Other_Income_ID", Self_Other_Income_ID);
                        Log.d("Self_Other_Income_Value", Self_Other_Income_Value);

                        if(Self_Other_Income_ID.equals("4"))
                        {
                            self_other_income_YN.setVisibility(View.GONE);
                        }else
                        {
                            self_other_income_YN.setVisibility(View.VISIBLE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            self_other_income_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void GST_Reflacted(final JSONArray gst_reflected) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        IS_GST_Reflected = new String[gst_reflected.length()];
        for (int i=0;i<gst_reflected.length();i++){
            JSONObject J =  gst_reflected.getJSONObject(i);
            IS_GST_Reflected[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(IS_GST_Reflected));
            IS_GST_Reflact_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            IS_GST_Reflact_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            spinn_is_gst_reflect.setAdapter(IS_GST_Reflact_Adapter);
            self_spinn_is_gst_reflect.setAdapter(IS_GST_Reflact_Adapter);
            spinn_is_gst_reflect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        GST_Reflact_ID = gst_reflected.getJSONObject(position).getString("id");
                        GST_Reflact__Value = gst_reflected.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("GST_Reflact_ID", GST_Reflact_ID);
                        Log.d("GST_Reflact__Value", GST_Reflact__Value);

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

            self_spinn_is_gst_reflect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        Self_GST_Reflact_ID = gst_reflected.getJSONObject(position).getString("id");
                        Self_GST_Reflact__Value = gst_reflected.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("GST_Reflact_ID", Self_GST_Reflact_ID);
                        Log.d("GST_Reflact__Value", Self_GST_Reflact__Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            self_spinn_is_gst_reflect.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }
    private void Current_residence_Proof(final JSONArray current_residence_proof) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Current_residence_SA = new String[current_residence_proof.length()];
        for (int i=0;i<current_residence_proof.length();i++){
            JSONObject J =  current_residence_proof.getJSONObject(i);
            Current_residence_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Current_residence_SA));
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
            current_recidence_spinner.setAdapter(Current_residence_Adapter);
            self_current_res_proof_spinner.setAdapter(Current_residence_Adapter);

            current_recidence_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        Current_Residence_ID = current_residence_proof.getJSONObject(position).getString("id");
                        Current_Residence_Value = current_residence_proof.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Current_Residence_ID", Current_Residence_ID);
                        Log.d("Current_Residence_Value", Current_Residence_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            current_recidence_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

            self_current_res_proof_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {


                        Self_Current_Residence_ID = current_residence_proof.getJSONObject(position).getString("id");
                        Self_Current_Residence_Value = current_residence_proof.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Self_Current_ID", Self_Current_Residence_ID);
                        Log.d("Self_Current__Value", Self_Current_Residence_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            self_current_res_proof_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    //Self
    private void Self_Guarenter(final JSONArray select_gurenter) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Guarenter_SA = new String[select_gurenter.length()];
        for (int i=0;i<select_gurenter.length();i++){
            JSONObject J =  select_gurenter.getJSONObject(i);
            Guarenter_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Guarenter_SA));
            Guarenter_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Guarenter_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            self_can_you_provide_guarante.setAdapter(Guarenter_Adapter);
            self_can_you_provide_guarante.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        Guarenter_Id = select_gurenter.getJSONObject(position).getString("id");
                        Guarenter_Value = select_gurenter.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Property_Size_ID", Guarenter_Id);
                        Log.d("Property_Size_Value", Guarenter_Value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            self_can_you_provide_guarante.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(context, Viability_check_HL.class);
        finish();
        super.onBackPressed();

    }
}
