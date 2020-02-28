package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
            employee_id_ar,have_pan_ar;

    String [] PAN_ID_SA,Employe_ID_SA,Company_type_SA,Employee_type_SA,Marital_Statues_SA,Epf_detected_SA,
            gst_reflect_SA;

    ArrayAdapter<String> PAN_ID_Adapter,Employee_ID_Adapter,Company_type_Adapter,Type_employee_Adapter,Marital_Statues_Adapter,
            Epf_detected_Adapter,Other_income_Adapter,gst_reflect_Adapter;
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
                app_eligibility_salaried_hl,app_eligibility_self_hl;
        LinearLayout full_addres_of_relative,permanent_res_type_ly,rent_paid_for_house_ly,pl_co_app_cmp_name,pl_co_cmp_des_,
                permanent_residence_pincode_ly,permanent_res_area,rented;

        AppCompatEditText  pl_co_app_company_name_edtxt,pl_co_app_designation_in_company,pl_co_App_no_of_emp_edtxt,pl_co_app_no_of_dependent_edt_txt,
                pl_co_App_education_qualification_edit_txt, pl_co_app_emi_amount_edit_txt,pl_co_App_other_incom_amt_edtxt;


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

        Employement_Type = Pref.getCOSALARYTYPE(getApplicationContext());
        CO_Employement_Type = Pref.getCOEMPTYPE(getApplicationContext());
        Rsidence_Type = Pref.getResidenceType(getApplicationContext());
        residence_id = Pref.get_Residence_ID(getApplicationContext());

        makeJsonObjReq1();
        UISCREENS();
        Font();
        Click();

        if(salary_type.equals("1"))
        {
            app_eligibility_salaried_hl.setVisibility(View.VISIBLE);
            app_eligibility_self_hl.setVisibility(View.GONE);
        }else if(salary_type.equals("2"))
        {
            app_eligibility_salaried_hl.setVisibility(View.GONE);
            app_eligibility_self_hl.setVisibility(View.VISIBLE);
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
        app_eligibility_self_hl = (LinearLayout) findViewById(R.id.app_eligibility_salaried_hl);




        full_addres_of_relative = (LinearLayout) findViewById(R.id.full_addres_of_relative);
        permanent_res_type_ly = (LinearLayout) findViewById(R.id.permanent_res_type_ly);
        rent_paid_for_house_ly = (LinearLayout) findViewById(R.id.rent_paid_for_house_ly);
        rented = (LinearLayout) findViewById(R.id.rented);
        pl_co_app_cmp_name = (LinearLayout) findViewById(R.id.pl_co_app_cmp_name);
        pl_co_cmp_des_ = (LinearLayout) findViewById(R.id.pl_co_cmp_des_);
        co_applicant_pl_co_applicant = (LinearLayout) findViewById(R.id.co_applicant_pl_co_applicant);
        permanent_residence_pincode_ly = (LinearLayout) findViewById(R.id.permanent_residence_pincode_ly);
        permanent_res_area = (LinearLayout) findViewById(R.id.permanent_res_area);

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

    }

    private void Font() {

        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");

        is_gst_reflect_txt.setTypeface(font);

    }
    private void Click ()
    {


        lead_Elegibility_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Eligibility_HL_New.this, Credite_report_details.class);
                startActivity(intent);
                finish();
            }
        });

        lead_Elegibility_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


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



    private void lead_Eligibility() {
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
