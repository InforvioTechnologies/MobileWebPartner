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
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Eligibility_Check_PL extends SimpleActivity {

    AppCompatButton lead_viy_step2;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    Typeface font;
    private Context context = this;
    InputMethodManager imm;
    private AlertDialog progressDialog;
    JSONArray Company_type,Type_employee,Current_Residence,epf_deduct,
            Residence_ownership,permanent_adddress,relation_own,Other_income,gst_reflect,employee_id_ar,have_pan_ar,
            Marital_Status,Reflected_ITR;

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
            emi_amount_edit_txt,education_qualification_edit_txt;
    AutoCompleteTextView permanent_res_pincode_edtxt;

    Spinner spinner_cmp_type,employee_type_spnr,epf_spinner,current_res_spinner,perment_restype_spnr,
            own_hose_relative_spinner,spinn_other_income,spinn_is_gst_reflect,has_pan_card_spnr,spinner_employe_id,
            maried_res_spinner,permanent_res_type_res_spinner;

    String S_company_name_edtxt,S_no_of_emp_edtxt,S_permanent_res_pincode_edtxt,S_experience_in_current_cmpy,
            S_Designation_in_current_companny,Other_income_id,Other_income_Value,gst_reflect_id,gst_reflect_Value,S_rel_full_address_txt1,
            S_other_incom_amt_edtxt,Employee_id,PAN_id,PAN_Value,Employee_Value,
            S_no_of_dependent_edt_txt,S_education_qualification_edit_txt,S_emi_amount_edit_txt,S_permanent_residence_spinner,
            S_own_house_blood_address_edt_txt,S_rent_paid_for_house_edit_txt;

    LinearLayout relative_if_rented_ly,is_other_income,rented,co_app_is_other_income;

    AutoCompleteTextView permanent_residence_spinner;

    //Co Applicant

        Spinner pl_co_self_has_pan_card_spnr,pl_co_app_spinner_employe_id,pl_co_app_spinner_cmp_type,pl_co_app_employee_type_spnr,
                pl_co_app_epf_spinner,pl_co_app_current_res_spinner,pl_co_app_maried_res_spinner,pl_co_app_spinn_other_income,
                pl_co_App_spinn_is_gst_reflect,permanent_residence_spinn_area;

        AppCompatEditText pl_co_app_designation_in_company,pl_co_App_education_qualification_edit_txt,pl_co_app_emi_amount_edit_txt,
                pl_co_app_no_of_dependent_edt_txt,pl_co_App_other_incom_amt_edtxt,own_house_blood_address_edt_txt,
                rent_paid_for_house_edit_txt;

        String Pl_Co_App_PAN_id,Pl_Co_App_PAN_Value,pl_co_app_Employee_id,pl_co_app_Employee_Value,
                pl_co_app_Company_id,pl_co_app_Company_Value, pl_co_app_Emp_type_id,pl_co_app_Emp_type_Value,
                pl_co_app_epf_id,pl_co_app_epf_Value, pl_co_app_maried_res_spinner_id,pl_co_app_maried_res_spinner_Value,
                pl_co_app_Other_income_id,pl_co_app_Other_income_Value,pl_co_App_gst_reflect_id,pl_co_App_gst_reflect_Value;

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



        UISCREEN();
        Click();
        fonts();
        makeJsonObjReq1();
    /*

        lead_viy_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Eligibility_Check_PL.this, Credite_report_details.class);
                startActivity(intent);
                finish();
            }
        });
    */

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

        permanent_residence_spinner = (AppCompatAutoCompleteTextView) findViewById(R.id.permanent_residence_spinner);

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
        designation_in_company = (AppCompatEditText) findViewById(R.id.designation_in_company);
        no_of_emp_edtxt = (AppCompatEditText) findViewById(R.id.no_of_emp_edtxt);
      //  permanent_res_pincode_edtxt = (AutoCompleteTextView) findViewById(R.id.permanent_res_pincode_edtxt);
        emi_amount_edit_txt = (AppCompatEditText) findViewById(R.id.emi_amount_edit_txt);
        other_incom_amt_edtxt = (AppCompatEditText) findViewById(R.id.other_incom_amt_edtxt);

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
        other_income_details_txt.setTypeface(font);
        other_income_txt.setTypeface(font);
        other_income_txt1.setTypeface(font);
        other_incom_amt_txt.setTypeface(font);
        other_incom_amt_txt1.setTypeface(font);
        is_gst_reflect_txt.setTypeface(font);
        is_gst_reflect_txt1.setTypeface(font);
        company_name_edtxt.setTypeface(font);
        designation_in_company.setTypeface(font);
        no_of_emp_edtxt.setTypeface(font);
      //  permanent_res_pincode_edtxt.setTypeface(font);
        education_qualification_edit_txt.setTypeface(font);
        other_incom_amt_edtxt.setTypeface(font);

    }

    private void Click ()
     {


         permanent_residence_spinner.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 Log.e("hi","hi11");
                 String permanent_pincode = permanent_residence_spinner.getText().toString();

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
                             if (!Validate_Company_Name()) {
                                 return;
                             }

                             if (!Validate_Designation_in_company()) {
                                 return;
                             }

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


                                         if (!Validate_permanent_residence_()) {
                                             return;
                                         }

                                         if(Permanent_Resi_id.equals("0"))
                                         {
                                             Toast.makeText(mCon, "Select Permanent Type", Toast.LENGTH_SHORT).show();

                                         }else if(Permanent_Resi_id.equals("2"))
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

                                         }

                                     }
                                 }
                             }
                         }

                     }

                 }

             }
             //
         });

     }


     private void other_varidation()
     {
         if(Other_income_id.equals("0"))
         {
             Toast.makeText(mCon, "Please Select other income", Toast.LENGTH_SHORT).show();

         }else if(!Other_income_id.equals("4"))
         {
                 lead_Eligibility();

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
                 lead_Eligibility();
             }
         }
     }

     private void Co_Applicant()
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
                     if (!Validate_Company_Name()) {
                         return;
                     }

                     if (!Validate_Designation_in_company()) {
                         return;
                     }

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

                                 if(Permanent_Resi_id.equals("0"))
                                 {
                                     Toast.makeText(mCon, "Select Permanent Type", Toast.LENGTH_SHORT).show();

                                 }else
                                 {
                                     if(maried_res_spinner_id.equals("0"))
                                     {
                                         Toast.makeText(mCon, "Select Marital Status", Toast.LENGTH_SHORT).show();

                                     }else
                                     {
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

                         }
                     }
                 }

             }

         }

     }


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
        if (permanent_residence_spinner.getText().toString().trim().isEmpty()) {
            permanent_residence_spinner.setError(getText(R.string.err_curent));
            permanent_residence_spinner.requestFocus();
            return false;
        } else {

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

            permanent_residence_spinner.setThreshold(2);
            permanent_residence_spinner.setAdapter(Pincode_Adapter);

        }

        permanent_res_pincode_edtxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);
            }
        });

        permanent_res_pincode_edtxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String code = (String)adapterView.getItemAtPosition(i);

                if(code.length()==6){
                    GET_AERA_POST(code);
                }else {
                    Objs.a.showToast(context,"Please Select Pin code");
                }

                imm.hideSoftInputFromWindow(permanent_res_pincode_edtxt.getWindowToken(), 0);
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

            String workpincode = permanent_res_pincode_edtxt.getText().toString();
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

                            Log.e("gst_reflect",String.valueOf(gst_reflect));
                            Employee_ID_Array(employee_id_ar);
                            HAVE_PAN_Card(have_pan_ar);
                            Salry_method_Spinner(Company_type);
                            Type_of_Employee(Type_employee);
                            Current_res_Type(Current_Residence);
                            Epf_detedcted(epf_deduct);

                            Other_income_f(Other_income);
                            itr_reflect_f(Reflected_ITR);
                            Marital_Statues(Marital_Status);
                            Permanent_res_Type(permanent_adddress);
                            Own_hous_relative(relation_own);


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

    private void Salry_method_Spinner(final JSONArray Company_type_ar) throws JSONException {
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


                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        pl_co_app_Employee_id = employee_id_ar.getJSONObject(position).getString("id");
                        pl_co_app_Employee_Value = employee_id_ar.getJSONObject(position).getString("value");
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

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Pl_Co_App_PAN_id = has_pancard_ar.getJSONObject(position).getString("id");
                        Pl_Co_App_PAN_Value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", Pl_Co_App_PAN_id);
                        Log.d("Salary_Value", Pl_Co_App_PAN_id);



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
                        Log.d("Company_id", current_ress_id);
                        Log.d("Company_Value", current_ress_Value);

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
                        Log.d("Company_id", maried_res_spinner_id);
                        Log.d("Company_Value", maried_res_spinner_Value);

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
                        Log.d("Company_id", maried_res_spinner_id);
                        Log.d("Company_Value", maried_res_spinner_Value);

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
                        Log.d("Company_id", current_ress_id);
                        Log.d("Company_Value", current_ress_Value);

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
                        Log.d("Company_id", current_ress_id);
                        Log.d("Company_Value", current_ress_Value);

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
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Company_id", current_ress_id);
                        Log.d("Company_Value", current_ress_Value);

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
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Company_id", current_ress_id);
                        Log.d("Company_Value", current_ress_Value);

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

    private void lead_Eligibility() {


        S_company_name_edtxt = company_name_edtxt.getText().toString();

      //  S_permanent_res_pincode_edtxt = permanent_res_pincode_edtxt.getText().toString();
       // S_rel_full_address_txt1 = rel_full_address_txt1.getText().toString();

        S_Designation_in_current_companny = designation_in_company.getText().toString();
        S_no_of_dependent_edt_txt = no_of_dependent_edt_txt.getText().toString();
        S_education_qualification_edit_txt = education_qualification_edit_txt.getText().toString();
        S_emi_amount_edit_txt = emi_amount_edit_txt.getText().toString();
        S_other_incom_amt_edtxt = other_incom_amt_edtxt.getText().toString();
        S_no_of_emp_edtxt = no_of_emp_edtxt.getText().toString();
        S_permanent_residence_spinner = permanent_residence_spinner.getText().toString();
        S_own_house_blood_address_edt_txt = own_house_blood_address_edt_txt.getText().toString();
        S_rent_paid_for_house_edit_txt = rent_paid_for_house_edit_txt.getText().toString();

        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {

            J =new JSONObject();

            J.put("has_pancardHave ",PAN_id);
            J.put("has_pancardHave ",Employee_id);
            J.put("Company_id",Company_id);
            J.put("S_Designation_in_current_companny",S_Designation_in_current_companny);
            J.put("S_company_name_edtxt",S_company_name_edtxt);
            J.put("S_no_of_emp_edtxt",S_no_of_emp_edtxt);
            J.put("epf_id",epf_id);
            J.put("current_ress_id",current_ress_id);
            J.put("maried_res_spinner_id",maried_res_spinner_id);
            J.put("S_no_of_dependent_edt_txt",S_no_of_dependent_edt_txt);
            J.put("S_emi_amount_edit_txt",S_emi_amount_edit_txt);
            J.put("Other_income_id",Other_income_id);
            J.put("S_other_incom_amt_edtxt",S_other_incom_amt_edtxt);
            J.put("gst_reflect_id",gst_reflect_id);
            J.put("S_permanent_residence_spinner",S_permanent_residence_spinner);
            J.put("permanent_residence__area_id",permanent_residence__area_id);
            J.put("Permanent_Resi_id",Permanent_Resi_id);
            J.put("Own_house_rela_id",Own_house_rela_id);
            J.put("S_own_house_blood_address_edt_txt",S_own_house_blood_address_edt_txt);
            J.put("S_rent_paid_for_house_edit_txt",S_rent_paid_for_house_edit_txt);


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

        Objs.ac.StartActivity(mCon, Viability_Check_PL.class);
        finish();
        super.onBackPressed();
    }

}
