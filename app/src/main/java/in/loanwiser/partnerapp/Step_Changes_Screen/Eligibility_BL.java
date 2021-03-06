package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
import in.loanwiser.partnerapp.NumberTextWatcher;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.Payment.PaymentActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Eligibility_BL extends SimpleActivity {

    private LinearLayout self_business,individual,co_individual,
            co_self_business;
    private String Lontype ;
    JSONObject Applicant,Co_Applicant;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;
    PopupWindow popupWindow;
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
           ,sales_by_GStbill_edit_txt,bank_cridit_by_edtxt,business_ref_name_edt_txt, business_ref_mobile_edt_txt,
            about_the_business;

    AppCompatButton lead_Elegibility_Bank;

    LinearLayout other_income_ifany,co_applicant_pl_co_applicant,residence_rented,
            about_businesss_ly_forming,co_other_income_ifany;
    String user_id,transaction_id,IS_CO_Applicant_Id,CO_Type_of_employement_ID,Educational_Id,Educational_Value,
            Employement_Type,Rsidence_Type,purchased_by_bank_edit_txt1,purchased_by_GStbill_edit_txt1,sales_by_GStbill_edit_txt1,bank_cridit_by_edtxt1,
            Avg_monthly_income1,Business_refernce_name1,Business_reference_mobile1,other_income_edite_txt1,
            S_business_ref_name_edt_txt,S_business_ref_mobile_edt_txt,S_about_the_business,S_about_the_business_own,
            S_business_Name_edt_txt,S_about_the_business_forming;

    AppCompatEditText permanent_res_pincode_edt_txt, business_Name_edt_txt,about_the_business_own,
            about_the_business_forming,business_Name_edt_txt_ind;

    int app_count;

    // Co Applicant Self

    JSONArray Other_income,epf_deduct,Marital_Status,Type_employee,Company_type,
            employee_id_ar,have_pan_ar;

    String [] PAN_ID_SA,Employe_ID_SA,Company_type_SA,Employee_type_SA,Marital_Statues_SA,Epf_detected_SA,
            gst_reflect_SA;

    ArrayAdapter<String> PAN_ID_Adapter,Employee_ID_Adapter,Company_type_Adapter,Type_employee_Adapter,Marital_Statues_Adapter,
            Epf_detected_Adapter,Other_income_Adapter,gst_reflect_Adapter;
        String  co_having_bank_Id,co_having_bank_Value, co_Educational_Id,co_Educational_Value,
                co_Business_registration_Id,co_Business_registration_Value, co_Spinner_res_proof_Id,co_Spinner_res_proof_Value,
                co_Other_income_Id,co_Other_income_Value, co_Gstreflect_Id,co_Gstreflect_Value,CO_Employement_Type,
                IS_CO_Salried_Self,  co_salaried_Educational_Id,co_salaried_Educational_Value;

        Spinner co_having_bank_account,co_Educatio_qualification_Sppinner,co_business_registration_spinner,
            co_spinner_res_proof,co_spinn_other_income,co_spinn_gst_other,co_Educational_Spinner_pl;

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

        LinearLayout co_app_is_other_income,bl_co_eligibility_salaried,bl_co_eligibility_self,
                co_no_of_Employee;

        AppCompatEditText  pl_co_app_company_name_edtxt,pl_co_app_designation_in_company,pl_co_App_no_of_emp_edtxt,pl_co_app_no_of_dependent_edt_txt,
                pl_co_App_education_qualification_edit_txt, pl_co_app_emi_amount_edit_txt,pl_co_App_other_incom_amt_edtxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_eligibility__bl);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_eligibility__bl);
        initTools(R.string.eligi_check);

        Lontype = Pref.getLoanType(getApplicationContext());

        self_business = (LinearLayout) findViewById(R.id.self_business);

        progressDialog = new SpotsDialog(context, R.style.Custom);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        transaction_id = intent.getStringExtra("transaction_id");


        IS_CO_Applicant_Id = Pref.getCoAPPAVAILABLE(context);
        IS_CO_Salried_Self = Pref.getCOAPPSALARYTYPE(context);


        Employement_Type = Pref.getCOSALARYTYPE(context);

      //  CO_Employement_Type = Pref.getCOEMPTYPE(getApplicationContext());


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        CO_Employement_Type=prefs.getString("co_applicant","defaultStringIfNothingFound");
        Log.i("TAG", "onCreate:CO_Employement_Type "+CO_Employement_Type);

        Log.i("TAG", "onCreate:CO_Employement_Type "+CO_Employement_Type);
       // Log.e("the co",CO_Employement_Type);
        Rsidence_Type = Pref.getResidenceType(getApplicationContext());

        makeJsonObjReq1();
        UISCREENS();
        Font();
        Click();

        if(Employement_Type.equals("1"))
        {
            individual.setVisibility(View.VISIBLE);
            self_business.setVisibility(View.GONE);
            about_businesss_ly_forming.setVisibility(View.GONE);

        }else if(Employement_Type.equals("2"))
        {
            individual.setVisibility(View.GONE);
            self_business.setVisibility(View.GONE);
            about_businesss_ly_forming.setVisibility(View.VISIBLE);

        }else if(Employement_Type.equals("3"))
        {
            individual.setVisibility(View.GONE);
            self_business.setVisibility(View.VISIBLE);
            about_businesss_ly_forming.setVisibility(View.GONE);
        }


        if(CO_Employement_Type.equals("1"))
        {
            co_individual.setVisibility(View.VISIBLE);
            co_self_business.setVisibility(View.GONE);

        }else if(CO_Employement_Type.equals("2"))
        {
            co_individual.setVisibility(View.GONE);
            co_self_business.setVisibility(View.GONE);

        }else if(CO_Employement_Type.equals("3"))
        {
            co_individual.setVisibility(View.GONE);
            co_self_business.setVisibility(View.VISIBLE);
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


        if(Rsidence_Type.equals("2"))
        {
            residence_rented.setVisibility(View.VISIBLE);
        }else
        {
            residence_rented.setVisibility(View.GONE);
        }

    }

    private void UISCREENS()
    {

        spinner_res_proof = (Spinner) findViewById(R.id.spinner_res_proof);

        spinn_gst_other = (Spinner) findViewById(R.id.spinn_gst_other);
        spinn_other_income = (Spinner) findViewById(R.id.spinn_other_income);
        business_registration_spinner = (Spinner) findViewById(R.id.business_registration_spinner);
        Educatio_qualification_Sppinner = (Spinner) findViewById(R.id.Educatio_qualification_Sppinner);
        co_Educational_Spinner_pl = (Spinner) findViewById(R.id.co_Educational_Spinner_pl);

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
        other_income_edite_txt.addTextChangedListener(new NumberTextWatcher(other_income_edite_txt));
        purchased_by_bank_edit_txt = (AppCompatEditText) findViewById(R.id.purchased_by_bank_edit_txt);
        purchased_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.purchased_by_GStbill_edit_txt);
        sales_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.sales_by_GStbill_edit_txt);
        bank_cridit_by_edtxt = (AppCompatEditText) findViewById(R.id.bank_cridit_by_edtxt);
        business_ref_name_edt_txt = (AppCompatEditText) findViewById(R.id.business_ref_name_edt_txt);
        business_ref_mobile_edt_txt = (AppCompatEditText) findViewById(R.id.business_ref_mobile_edt_txt);
        about_the_business = (AppCompatEditText) findViewById(R.id.about_the_business);
        about_the_business_forming = (AppCompatEditText) findViewById(R.id.about_the_business_forming);
        about_the_business_own = (AppCompatEditText) findViewById(R.id.about_the_business_own);
        business_Name_edt_txt = (AppCompatEditText) findViewById(R.id.business_Name_edt_txt);
        business_Name_edt_txt_ind = (AppCompatEditText) findViewById(R.id.business_Name_edt_txt_ind);

        co_business_ref_name_edt_txt = (AppCompatEditText) findViewById(R.id.co_business_ref_name_edt_txt);
        co_purchased_by_bank_edit_txt = (AppCompatEditText) findViewById(R.id.co_purchased_by_bank_edit_txt);
        co_purchased_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.co_purchased_by_GStbill_edit_txt);
        co_sales_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.co_sales_by_GStbill_edit_txt);
        co_bank_cridit_by_edtxt = (AppCompatEditText) findViewById(R.id.co_bank_cridit_by_edtxt);

        co_Avg_monthly_income = (AppCompatEditText) findViewById(R.id.co_Avg_monthly_income);

        co_Avg_monthly_income.addTextChangedListener(new NumberTextWatcher(co_Avg_monthly_income));

        Avg_monthly_income = (AppCompatEditText) findViewById(R.id.Avg_monthly_income);

        Avg_monthly_income.addTextChangedListener(new NumberTextWatcher(Avg_monthly_income));

        co_other_income_edite_txt = (AppCompatEditText) findViewById(R.id.co_other_income_edite_txt);
        co_other_income_edite_txt.addTextChangedListener(new NumberTextWatcher(co_other_income_edite_txt));

        permanent_res_pincode_edt_txt = (AppCompatEditText) findViewById(R.id.permanent_res_pincode_edt_txt);

        lead_Elegibility_Bank = (AppCompatButton) findViewById(R.id.lead_Elegibility_Bank);

        other_income_ifany = (LinearLayout) findViewById(R.id.other_income_ifany);
        co_other_income_ifany = (LinearLayout) findViewById(R.id.co_other_income_ifany);

        co_applicant_pl_co_applicant = (LinearLayout) findViewById(R.id.co_applicant_pl_co_applicant);

        bl_co_eligibility_salaried = (LinearLayout) findViewById(R.id.bl_co_eligibility_salaried);
        bl_co_eligibility_self = (LinearLayout) findViewById(R.id.bl_co_eligibility_self);
        co_no_of_Employee = (LinearLayout) findViewById(R.id.co_no_of_Employee);

        individual = (LinearLayout) findViewById(R.id.individual);
        co_individual = (LinearLayout) findViewById(R.id.co_individual);
        self_business = (LinearLayout) findViewById(R.id.self_business);
        co_self_business = (LinearLayout) findViewById(R.id.co_self_business);
        residence_rented = (LinearLayout) findViewById(R.id.residence_rented);
        about_businesss_ly_forming = (LinearLayout) findViewById(R.id.about_businesss_ly_forming);



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
        pl_co_app_emi_amount_edit_txt.addTextChangedListener(new NumberTextWatcher(pl_co_app_emi_amount_edit_txt));
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
        pl_co_app_emi_amount_edit_txt.addTextChangedListener(new NumberTextWatcher(pl_co_app_emi_amount_edit_txt));

        pl_co_App_other_incom_amt_edtxt = (AppCompatEditText) findViewById(R.id.pl_co_App_other_incom_amt_edtxt);
        pl_co_App_other_incom_amt_edtxt.addTextChangedListener(new NumberTextWatcher(pl_co_App_other_incom_amt_edtxt));
    }

    private void Font() {

        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");

        is_gst_reflect_txt.setTypeface(font);

    }
    private void Click ()
    {


     /*   lead_Elegibility_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Eligibility_BL.this, Credite_report_details.class);
                startActivity(intent);
                finish();
            }
        });*/

        lead_Elegibility_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(Employement_Type.equals("1"))
                {

                    if (!validate_business_Name_edt_txt_ind()) {
                        return;
                    }
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

                          if (!Sales_Bank_ref_mobile()) {
                              return;
                          }
                          if (!Sales_about_the_business()) {
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

                    if (!validateabout_the_business_forming()) {
                        return;
                    }

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
                    if (!validate_Sales_business_Name_edt_txt()) {
                        return;
                    }

                    if(Business_registration_Id.equals("0"))
                    {
                        Toast.makeText(context,"Please Select Business Registration",Toast.LENGTH_SHORT).show();
                    }else
                    {


                            if (!validate_Sales_about_the_business_own()) {
                                return;
                            }
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
//////
            }
        });

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
                    lead_Eligibility();
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

                                if(pl_co_app_Company_id.equals("1")||pl_co_app_Company_id.equals("2") || pl_co_app_Company_id.equals("3") )
                                {
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
                                                Toast.makeText(context,"Please Select Co Applicant Educational Qualification",Toast.LENGTH_SHORT).show();

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
                                                Toast.makeText(context,"Please Select Co Applicant Educational Qualification",Toast.LENGTH_SHORT).show();

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

                            }
                        }
                    }
                }

        }

    private void Co_App_other_varidation()
    {
        if(pl_co_app_Other_income_id.equals("0"))
        {
            Toast.makeText(mCon, "Please Select Co Applicant other income", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(mCon, "Please Co Applicant Select ITR Reflected", Toast.LENGTH_SHORT).show();

            }else
            {
                lead_Eligibility();
            }
        }
    }

    ///end salarird eligibility
        private void co_self()
        {
            if(CO_Employement_Type.equals("1"))
            {

                if(co_having_bank_Id.equals("0"))
                {
                    Toast.makeText(context,"Please Select Co Applicant Bank A/C Type",Toast.LENGTH_SHORT).show();
                }else
                {
                    if(co_Educational_Id.equals("0"))
                    {
                        Toast.makeText(context,"Please Select Co Applicant Educational Qualification",Toast.LENGTH_SHORT).show();

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
                if(co_Spinner_res_proof_Id.equals("0"))
                {
                    Toast.makeText(context,"Please Select Co Applicant Current Residence Addres Proof",Toast.LENGTH_SHORT).show();
                }else
                {
                    co_validation();
                }
            }else if(CO_Employement_Type.equals("3"))
            {

                if(co_Business_registration_Id.equals("0"))
                {
                    Toast.makeText(context,"Please Select Co Applicant Business Registration",Toast.LENGTH_SHORT).show();
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

    private boolean Sales_Bank_ref_mobile(){
        if (business_ref_mobile_edt_txt.getText().toString().isEmpty()) {
            business_ref_mobile_edt_txt.setError(getText(R.string.err_curent));
            business_ref_mobile_edt_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validate_Sales_business_Name_edt_txt(){
        if (business_Name_edt_txt.getText().toString().isEmpty()) {
            business_Name_edt_txt.setError(getText(R.string.err_curent));
            business_Name_edt_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }

    private boolean validate_business_Name_edt_txt_ind(){
        if (business_Name_edt_txt_ind.getText().toString().isEmpty()) {
            business_Name_edt_txt_ind.setError(getText(R.string.err_curent));
            business_Name_edt_txt_ind.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }


    private boolean validateabout_the_business_forming(){
        if (about_the_business_forming.getText().toString().isEmpty()) {
            about_the_business_forming.setError(getText(R.string.err_curent));
            about_the_business_forming.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }



    private boolean validate_Sales_about_the_business_own(){
        if (about_the_business_own.getText().toString().isEmpty()) {
            about_the_business_own.setError(getText(R.string.err_curent));
            about_the_business_own.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }


    private boolean Sales_about_the_business(){
        if (about_the_business.getText().toString().isEmpty()) {
            about_the_business.setError(getText(R.string.err_curent));
            about_the_business.requestFocus();
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



                            Other_income =object.getJSONArray("Other_income");
                            epf_deduct =object.getJSONArray("epf_deduct");
                            Marital_Status =object.getJSONArray("Marital_Status");
                            Type_employee =object.getJSONArray("Type_employee");
                            Company_type =object.getJSONArray("Company_type");
                            employee_id_ar =object.getJSONArray("employee_id");
                            have_pan_ar =object.getJSONArray("have_pan");

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


          //  pl_co_App_education_qualification_edit_txt


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
                        if(pl_co_app_Company_id.equals("1")||pl_co_app_Company_id.equals("2") || pl_co_app_Company_id.equals("3") )
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

    private void lead_Eligibility() {
         S_purchased_by_bank_edit_txt1 = purchased_by_bank_edit_txt.getText().toString();
         S_purchased_by_GStbill_edit_txt1 = purchased_by_GStbill_edit_txt.getText().toString();
         S_sales_by_GStbill_edit_txt1 = sales_by_GStbill_edit_txt.getText().toString();
         S_bank_cridit_by_edtxt1 = bank_cridit_by_edtxt.getText().toString();
         S_Avg_monthly_income1 = Avg_monthly_income.getText().toString();
         S_other_income_edite_txt1 = other_income_edite_txt.getText().toString();
         S_business_ref_name_edt_txt = business_ref_name_edt_txt.getText().toString();


         S_business_ref_mobile_edt_txt = business_ref_mobile_edt_txt.getText().toString();
         S_about_the_business = about_the_business.getText().toString();


         S_about_the_business_own = about_the_business_own.getText().toString();
         S_business_Name_edt_txt = business_Name_edt_txt.getText().toString();

         S_about_the_business_forming = about_the_business_forming.getText().toString();
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
         Applicant =new JSONObject();
         Co_Applicant =new JSONObject();


        JSONArray other_income = new JSONArray();
        JSONArray other_amount = new JSONArray();
        JSONArray itr_reflected = new JSONArray();

        JSONArray other_income1 = new JSONArray();
        JSONArray other_amount1 = new JSONArray();
        JSONArray itr_reflected1 = new JSONArray();

        JSONArray other_income2 = new JSONArray();
        JSONArray other_amount2 = new JSONArray();
        JSONArray itr_reflected2 = new JSONArray();

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


            Applicant.put("has_sb_account",having_bank_Id);
            Applicant.put("qualification",Educational_Id);
            Applicant.put("reference_name",S_business_ref_name_edt_txt);

            Applicant.put("reference_num",S_business_ref_mobile_edt_txt);
            Applicant.put("company_name",S_business_Name_edt_txt);
            Applicant.put("addr_proof_own",Spinner_res_proof_Id);

            if(Employement_Type.equals("1"))
            {
                Applicant.put("about_company",S_about_the_business);
            }else  if(Employement_Type.equals("2"))
            {
                Applicant.put("about_company",about_the_business_forming);
            }else
            {
                Applicant.put("about_company",S_about_the_business_own);
            }

            Applicant.put("other_from",other_income);
            Applicant.put("other_amount",other_amount);
            Applicant.put("other_reflected",other_amount);

            Applicant.put("bussiness_registeration",Business_registration_Id);
            Applicant.put("purchased_by_bank_edit_txt1",S_purchased_by_bank_edit_txt1);
            Applicant.put("purchased_by_GStbill_edit_txt1",S_purchased_by_GStbill_edit_txt1);
            Applicant.put("sales_by_GStbill_edit_txt1",S_sales_by_GStbill_edit_txt1);
            Applicant.put("bank_cridit_by_edtxt1",S_bank_cridit_by_edtxt1);
            Applicant.put("Avg_monthly_income1",S_Avg_monthly_income1);

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
                    Co_Applicant.put("qualification",co_salaried_Educational_Id);


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
                                LayoutInflater layoutInflater = (LayoutInflater) Eligibility_BL.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View customView = layoutInflater.inflate(R.layout.popup_loading,null);
                                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                //display the popup window
                                popupWindow.showAtLocation(lead_Elegibility_Bank, Gravity.CENTER, 0, 0);

                                if(IS_CO_Applicant_Id.equals("1"))
                                {
                                    Eligibility_Co_Applicant();
                                }else
                                {
                                    Eligibility_check_pass();
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

    private void Eligibility_Co_Applicant( ) {

        JSONObject J= null;

        try {
            J =new JSONObject();
            J.put("applicant_count","2");
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("applicant",Co_Applicant);

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
       //// progressDialog.show();
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
                               // Eligibility_check_doc_checklist_generate();
                            }else if(jsonObject1.getString("eligibility_status").equals("error"))
                            {
                                Toast.makeText(context,"Eligibility Failed",Toast.LENGTH_SHORT).show();
                                String viability_array =jsonObject1.getString("eligibility_arr");
                                Intent intent = new Intent(Eligibility_BL.this, Loan_Viyability_Check_Activity.class);
                                intent.putExtra("viability_jsonArray", viability_array.toString());
                                startActivity(intent);
                                finish();
                            }
                            ///
                         //   progressDialog.dismiss();

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
   /* private void Eligibility_check_doc_checklist_generate( ) {

        JSONObject J= null;

        try {
            J =new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("viability", String.valueOf(J));
       // progressDialog.show();
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
                                LayoutInflater layoutInflater = (LayoutInflater) Eligibility_BL.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View customView = layoutInflater.inflate(R.layout.popup_loading,null);
                                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                //display the popup window
                                popupWindow.dismiss();
                                Toast.makeText(context,"Eligibility Created Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Eligibility_BL.this, PaymentActivity.class);
                                startActivity(intent);
                                finish();
                            }else if(response.getString("Generate_Document_check_list").equals("error"))
                            {
                                Toast.makeText(context,"Eligibility Failed",Toast.LENGTH_SHORT).show();
                                String viability_array =jsonObject1.getString("eligibility_arr");
                                Intent intent = new Intent(Eligibility_BL.this, Loan_Viyability_Check_Activity.class);
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
