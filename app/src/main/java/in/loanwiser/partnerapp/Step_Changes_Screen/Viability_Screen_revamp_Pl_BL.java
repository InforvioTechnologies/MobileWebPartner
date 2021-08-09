package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;
import com.thomashaertel.widget.MultiSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Documents.MyCommand;
import in.loanwiser.partnerapp.NumberTextWatcher;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.Payment.PaymentActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Viability_Screen_revamp_Pl_BL extends SimpleActivity implements NumberPicker.OnValueChangeListener {


    private Context context = this;
    String loan_type_id;
    Typeface font;
    AppCompatImageView prop_scroll,pan_scroll,salaried_scroll,res_scroll1_img,creditbank_scroll_img;
    LinearLayout residence_layout,pro_details,salaried,self_employeed,property_Scroll,Pan_scroll,salaried_Scroll,
            creditbanklay,avgbanklay,bankturnoverlay,accounttypelay,majortranslay,
    property_ly,pan_ly,salaried_ly,self_ly,res_scroll,res_ly,co_applicant_ly,applicant_ly,prop_details_identified,
            employement_selection_ly;

    List<Map<String,List<String>>> list = new ArrayList<Map<String,List<String>>>();//This is the final list you need
    Map<String, List<String>> map1 = new HashMap<String, List<String>>();//This is one instance of the  map you want to store in the above list.
    List<String> arraylist1 = new ArrayList<String>();

    String[] arr = { "Paries,France", "PA,United States","Parana,Brazil",
            "Padua,Italy", "Pasadena,CA,United States"};

    ArrayList<String> listarray=new ArrayList<String>();//creating new generic arraylist



    RadioGroup credit_radiogroup,legal_group,arrangepdf_group;

    String ave_month_income,no_of_years_in_works,office_res_pincode,other_income_amt1_self,closedloan_radio,legal_radio,arrangepdf_radio,
            residence_pincode1_;
    AppCompatButton property_btn,Pan_btn,salried_self_btn,residence_btn,co_applicant_yes,co_applicant_no,creditbankbtn;
    PopupWindow popupWindow;
    AppCompatEditText aboutbusinesstxt,annualprofittxt,annualturntxt,residencedoorno_txt,rentpaid_txt,
            per_residencepincode,liveincurrent_txt,residencedoorno_txt1,avgbankbaltxt,bankturnovertxt;
    LinearLayout rentpaidlay,per_respincodelay,permanentresidencetypelay,liveinresidence,officialmaillay;
    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    JSONArray Type_of_employement,have_pan_ar_self,vocaton_ar,Business_income_proof_ar,
            vocation_type_forming_ar,Residence_ownership_ar_self,Business_type_own_business,Business_Proof,Assets_own,
            office_shop,vehicle_Type,crop_type,sell_milk,franchise,property_identified,property_category,
            land_approval,building_approval,DA_approval, Employement,Property_Type,Property_title,Residence_ownership_ar,
            Salary_method_ar,employee_id_ar,other_earning_ar,have_pan_ar,Salary_proof_ar,Marital_Status,gender_status,educational_status,assetsown,
            Company_type,Office_residence,Type_of_self_employement,Other_income,epf_array,typeofemployarray,permanentres_array,
            currentres_array,creditscore_array,emilate_array,banksalary_array,autocompletearray,
            haveaddress_array,fileitr_array,haveotherincome_array,addr_proof_rent,bankactype_array,majortrans_array,employmentproof_array;

    Spinner spnr_Property_Identified,spnr_property_title,spnr_property_type,employment_type,maritial_status,
            spinn_salary_crt_mtd,spinner_company_type,spinner_office_shop_setup_ind,
            office_spinner_residence_type,office_spinner_area,spinn_area,
            res_spinn_area,spinner_residence_type_res,emp_type,spi_vocation_type_,
            other_income_source_salaried,other_income_source_self,gender_spinner,educational_qualificationspinner,epf_spinner,
            typeofemploye_spinner, permanentres_spinner,current_resspinner,
            banksalarycreditdropdown,creditscorespinner,emilatespinner,
            haveaddressproof_spinner,fileitr_spinner,accounttype_spinner,majortrans_spinner,employee_proofspinner;

    String[] Property_Type_SA,Property_Title,Property_Identified,EMPLOYEE_TYPE_SA,Marital_Statues_SA,Company_type_SA,
            SALARY_Method,Pincode_SA,Office_Shop_SA,Office_Shop_own_SA,Area,Area1,TYPE,
    Business_TYPE,Business_vintage_p,Vocation_SA;

    String[] Business_vintage_p_id;



    InputMethodManager imm;
    ArrayAdapter<String> Property_Type_Adapter,Property_Title_Adapter,Property_Identified_Adapter,Employee_Type_adapter,
            Marital_Statues_Adapter,Gender_statusadapter,educational_adapter,Company_type_Adapter,Salary_Adapter,Pincode_Adapter,Office_Shop__Adapter,
            Office_Shop_own_SA_Adapter,A_Area,A_Area1,companyproof_multiAdapter,assetowned_multiselect,
            Business_Proof_type_adapter,Business_vintage_Adapter,Vocation_SA_Adapter,epf_adapter,typeofemployeadapter,
            permanentrestypeadapter,currentrestypeadapter,creditscoreadapter,emilateadapter,banklistadapter,bankactypeadapter,
            haveaddres_adapter,fileitr_adapter,majortrans_adapter,autocompleteadapter,employeproofadapter;

    String  Property_Identified_ID,Property_Identified_Value,Property_Title_ID,Property_Title_Value,
            Propery_Type_ID,Propery_Type_Value,Employee_type_Id,Employee_type_Value,maritial_status_id,maritial_status_Value,educationalstatusid,educationalstatusvalue,
            pl_co_app_Company_id,pl_co_app_Company_Value,Salary_id,Salary_Value,
            pl_co_app_ind_Office_Shop__id,pl_co_app_ind_Office_Shop__value,pl_co_app_ind_Office_Shop_Own_id,
            pl_co_app_ind_Office_Shop_Own_value, residence_area,residence_area_district_id,residence_state_id,
            residence_area_res,residence_area_district_id_res,residence_state_id_res,
            residence_area_office,residence_area_district_id_office,residence_state_id_office,
            spinner_residence_type_res_id,spinner_residence_type_res_value,self_Employee_type_Id,self_Employee_type_Value,
            Emp_vocation_type_id,Emp_vocation_type_Value,company_area, Other_income_id,Other_income_Value,
            Other_income_id_self,Other_income_Value_self,viability_report_URL,company_area1,residence_area_office1,
            residence_area_res1,genderstatus_id,genderstatus_value,epfstatusid,epfstatusvalue,typeofemployeid,typeofemployevalue,
            permanentresid,permanentresvalue,currentresid,currentresvalue,
            creditscoreid,creditscorevalue,emilateid,emilatevalue,banklistid,banklistvalue,
            haveaddressid,haveaddressvalue,fileitrid,fileitrvalue,bankactypeid,bankactypevalue,
            majortransid,majortransvalue,employeproofid,employeproofvalue;

    AppCompatAutoCompleteTextView company_pincode_txt,office_residence_pincode_edite_txt,residence_pincode1_edit_txt,residence_pincode1_edit_txt_resi;

    AppCompatEditText experience_in_current_cmpy,total_experience_edit_txt,date_of_birt_txt,
            monthly_net_sal_edit_txt,Fathers_Name,PAN_Edit_text,
            avg_monthly_incom_edit_txt,no_of_years_work_ind_edit_txt,designation_edit_text,
            other_income_amt_txt_edit_txt_self,other_income_amt_txt_edit_txt,noof_dependenttxt,doorstreet_txt,officialmailidtxt;
    AppCompatTextView salarycredtextview;



    MultiSpinner spinner_salary_proof,BL_self_bussiness_proof,BL_self_bus_vintage_proof,assetsowned;
    SharedPreferences preferences;
    boolean[] Income_proof_selected;

    Calendar myCalendar;
    JSONObject Pan_Details,Employement_type,Residence,Creditbank_Details;
    String monthly_net_salary,company_name,designation_,experience_in_current,total_experience_,company_pincode,
    other_income_amt;
    List<String> list_income_proof_self,list_income_proof_self_value,list_vintage_proof,salry_proof,salry_proof_value,
            list_vintage_proof_value,assetownid,assetownvalue,assetproof,autocompletelist;
    JSONArray Other_income_id_,other_income_amt_;


    int total_experiance,ecperience,no_of_month_in_business;
    Button  closePopupBtn,close,view_report,sub_to_next,save_latter;
    PermissionUtils permissionUtils;
    String  DOB,Fathers_Name_str,Pan_number,noofdepent_str;

    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    LinearLayout name_ly,email_ly,other_income_amt_ly_,other_income_amt_ly_self,ofiice_res_details,
    BL_self_office_ownership_type_ly,other_income_amount_ly,creditbank_scroll;
    ArrayList<String> rule_message = new ArrayList<String>();
    private String blockCharacterSet = "~#^|$%&*!";

    AutoCompleteTextView company_name_edit_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this,R.layout.activity_viability__screen_revamp1_pl_bl);
        initTools(R.string.viy_check);
        Log.i("TAG", "onCreate: "+"plbl");

        loan_type_id = Pref.getLoanType(context);
       // autocompletetextview=findViewById(R.id.autocompletetextview);

        progressDialog = new SpotsDialog(Viability_Screen_revamp_Pl_BL.this, R.style.Custom);
        permissionUtils = new PermissionUtils();
        preferences = PreferenceManager.getDefaultSharedPreferences(Viability_Screen_revamp_Pl_BL.this);
        myCalendar = Calendar.getInstance();
        myCalendar.add(Calendar.YEAR, -26);
        salry_proof = new ArrayList<String>();
        salry_proof_value = new ArrayList<String>();
        assetownid=new ArrayList<String>();
        assetownvalue=new ArrayList<String>();
        list_income_proof_self = new ArrayList<String>();
        list_income_proof_self_value = new ArrayList<String>();
        autocompletelist = new ArrayList<String>();
        list_vintage_proof = new ArrayList<String>();

        list_vintage_proof_value = new ArrayList<String>();
        salry_proof_value = new ArrayList<String>();
        list_income_proof_self_value = new ArrayList<String>();

        name_ly = (LinearLayout) findViewById(R.id.name_ly);
        email_ly = (LinearLayout) findViewById(R.id.email_ly);

        name_ly.setVisibility(View.GONE);
        email_ly.setVisibility(View.GONE);


        UI();
        BankListAPI();
        AutoAPi();
        makeJsonObjReq1();
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        click();




    }
    private void UI()
    {


        pan_scroll = (AppCompatImageView) findViewById(R.id.pan_scroll);
        salaried_scroll = (AppCompatImageView) findViewById(R.id.salaried_scroll);
        res_scroll1_img = (AppCompatImageView) findViewById(R.id.res_scroll1_img);
        creditbank_scroll_img = (AppCompatImageView) findViewById(R.id.creditbank_scroll_img);

        creditbank_scroll=findViewById(R.id.creditbank_scroll);
        salarycredtextview=findViewById(R.id.salarycredtextview);

        property_Scroll = (LinearLayout) findViewById(R.id.property_Scroll);
        prop_details_identified = (LinearLayout) findViewById(R.id.prop_details_identified);

        creditbanklay=(LinearLayout)findViewById(R.id.creditbanklay);
        avgbanklay=(LinearLayout)findViewById(R.id.avgbanklay);
        bankturnoverlay=(LinearLayout)findViewById(R.id.bankturnoverlay);
        accounttypelay=(LinearLayout)findViewById(R.id.accounttypelay);
        majortranslay=(LinearLayout)findViewById(R.id.majortranslay);

        spnr_Property_Identified = (Spinner) findViewById(R.id.spnr_Property_Identified);
        spnr_property_title = (Spinner) findViewById(R.id.spnr_property_title);
        spnr_property_type = (Spinner) findViewById(R.id.spnr_property_type);
        employment_type = (Spinner) findViewById(R.id.employment_type);
        maritial_status = (Spinner) findViewById(R.id.maritial_status);
        spinn_area = (Spinner) findViewById(R.id.spinn_area);
        gender_spinner = (Spinner) findViewById(R.id.gender_spinner);
        educational_qualificationspinner=findViewById(R.id.educational_qualificationspinner);
        epf_spinner=findViewById(R.id.epf_spinner);
        typeofemploye_spinner=findViewById(R.id.typeofemploye_spinner);
        permanentres_spinner=findViewById(R.id.permanentres_spinner);
        current_resspinner=findViewById(R.id.current_resspinner);
        banksalarycreditdropdown=findViewById(R.id.banksalarycreditdropdown);
        creditscorespinner=findViewById(R.id.creditscorespinner);
        emilatespinner=findViewById(R.id.emilatespinner);
        haveaddressproof_spinner=findViewById(R.id.haveaddressproof_spinner);
        fileitr_spinner=findViewById(R.id.fileitr_spinner);
        accounttype_spinner=findViewById(R.id.accounttype_spinner);
        majortrans_spinner=findViewById(R.id.majortrans_spinner);
        employee_proofspinner=findViewById(R.id.employee_proofspinner);
        salarycredtextview = (AppCompatTextView)findViewById(R.id.salarycredtextview);


        company_pincode_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.company_pincode_txt);
        office_residence_pincode_edite_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.office_residence_pincode_edite_txt);

        residence_pincode1_edit_txt = (AppCompatAutoCompleteTextView) findViewById(R.id.residence_pincode1_edit_txt);
        residence_pincode1_edit_txt_resi = (AppCompatAutoCompleteTextView) findViewById(R.id.residence_pincode1_edit_txt_resi);
        date_of_birt_txt = (AppCompatEditText) findViewById(R.id.date_of_birt_txt);
        monthly_net_sal_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_net_sal_edit_txt);
        monthly_net_sal_edit_txt.addTextChangedListener(new NumberTextWatcher(monthly_net_sal_edit_txt));
        Fathers_Name = (AppCompatEditText) findViewById(R.id.Fathers_Name);
        Fathers_Name.setFilters(new InputFilter[] { filter });
        PAN_Edit_text = (AppCompatEditText) findViewById(R.id.PAN_Edit_text);
        PAN_Edit_text.setFilters(new InputFilter[]{new InputFilter.AllCaps()});



        spinn_salary_crt_mtd = (Spinner) findViewById(R.id.spinn_salary_crt_mtd);
        spi_vocation_type_ = (Spinner) findViewById(R.id.spi_vocation_type_);
        spinner_salary_proof = (MultiSpinner) findViewById(R.id.spinner_salary_proof);
        assetsowned = (MultiSpinner) findViewById(R.id.assetsowned);
        BL_self_bus_vintage_proof = (MultiSpinner) findViewById(R.id.BL_self_bus_vintage_proof);
        BL_self_bussiness_proof = (MultiSpinner) findViewById(R.id.BL_self_bussiness_proof);
        spinner_company_type = (Spinner) findViewById(R.id.spinner_company_type);

        spinner_office_shop_setup_ind = (Spinner) findViewById(R.id.spinner_office_shop_setup_ind);
        office_spinner_residence_type = (Spinner) findViewById(R.id.office_spinner_residence_type);
        office_spinner_area = (Spinner) findViewById(R.id.office_spinner_area);
        res_spinn_area = (Spinner) findViewById(R.id.res_spinn_area);
        spinner_residence_type_res = (Spinner) findViewById(R.id.spinner_residence_type_res);
        emp_type = (Spinner) findViewById(R.id.emp_type);
        other_income_source_salaried = (Spinner) findViewById(R.id.other_income_source_salaried);
        other_income_source_self = (Spinner) findViewById(R.id.other_income_source_self);

        experience_in_current_cmpy = (AppCompatEditText) findViewById(R.id.experience_in_current_cmpy);
        total_experience_edit_txt = (AppCompatEditText) findViewById(R.id.total_experience_edit_txt);
        no_of_years_work_ind_edit_txt = (AppCompatEditText) findViewById(R.id.no_of_years_work_ind_edit_txt);
        company_name_edit_text = (AutoCompleteTextView) findViewById(R.id.company_name_edit_text);
        designation_edit_text = (AppCompatEditText) findViewById(R.id.designation_edit_text);




      //  experience_in_current_cmpy = (AppCompatEditText) findViewById(R.id.experience_in_current_cmpy);

        avg_monthly_incom_edit_txt = (AppCompatEditText) findViewById(R.id.avg_monthly_incom_edit_txt);
        avg_monthly_incom_edit_txt.addTextChangedListener(new NumberTextWatcher(avg_monthly_incom_edit_txt));
        /*annualprofittxt.addTextChangedListener(new NumberTextWatcher(annualprofittxt));
        annualturntxt.addTextChangedListener(new NumberTextWatcher(annualturntxt));*/


        other_income_amt_txt_edit_txt = (AppCompatEditText) findViewById(R.id.other_income_amt_txt_edit_txt);
        noof_dependenttxt = (AppCompatEditText) findViewById(R.id.noof_dependenttxt);
        doorstreet_txt = (AppCompatEditText) findViewById(R.id.doorstreet_txt);
        officialmailidtxt = (AppCompatEditText) findViewById(R.id.officialmailidtxt);
        residencedoorno_txt1 = (AppCompatEditText) findViewById(R.id.residencedoorno_txt1);
        avgbankbaltxt = (AppCompatEditText) findViewById(R.id.avgbankbaltxt);
        avgbankbaltxt.addTextChangedListener(new NumberTextWatcher(avgbankbaltxt));

        bankturnovertxt = (AppCompatEditText) findViewById(R.id.bankturnovertxt);
        bankturnovertxt.addTextChangedListener(new NumberTextWatcher(bankturnovertxt));

        rentpaid_txt = (AppCompatEditText) findViewById(R.id.rentpaid_txt);
        rentpaid_txt.addTextChangedListener(new NumberTextWatcher(rentpaid_txt));
        per_residencepincode = (AppCompatEditText) findViewById(R.id.per_residencepincode);
        liveincurrent_txt = (AppCompatEditText) findViewById(R.id.liveincurrent_txt);

        other_income_amt_txt_edit_txt.addTextChangedListener(new NumberTextWatcher(other_income_amt_txt_edit_txt));
        other_income_amt_txt_edit_txt_self = (AppCompatEditText) findViewById(R.id.other_income_amt_txt_edit_txt_self);
        other_income_amt_txt_edit_txt_self.addTextChangedListener(new NumberTextWatcher(other_income_amt_txt_edit_txt_self));
        Pan_scroll = (LinearLayout) findViewById(R.id.Pan_scroll);
        res_scroll = (LinearLayout) findViewById(R.id.res_scroll);
        res_ly = (LinearLayout) findViewById(R.id.res_ly);
        salaried_Scroll = (LinearLayout) findViewById(R.id.salaried_Scroll);


        rentpaidlay = (LinearLayout) findViewById(R.id.rentpaidlay);
        per_respincodelay = (LinearLayout) findViewById(R.id.per_respincodelay);
        permanentresidencetypelay = (LinearLayout) findViewById(R.id.permanentresidencetypelay);
        liveinresidence = (LinearLayout) findViewById(R.id.liveinresidence);
        officialmaillay = (LinearLayout) findViewById(R.id.officialmaillay);

        aboutbusinesstxt=findViewById(R.id.aboutbusinesstxt);
        annualprofittxt=findViewById(R.id.annualprofittxt);
        annualturntxt=findViewById(R.id.annualturntxt);

        annualprofittxt.addTextChangedListener(new NumberTextWatcher(annualprofittxt));
        annualturntxt.addTextChangedListener(new NumberTextWatcher(annualturntxt));
        residencedoorno_txt=findViewById(R.id.residencedoorno_txt1);


        residence_layout = (LinearLayout) findViewById(R.id.residence_layout);
        pro_details = (LinearLayout) findViewById(R.id.pro_details);
        salaried = (LinearLayout) findViewById(R.id.salaried);
        self_employeed = (LinearLayout) findViewById(R.id.self_employeed);
        co_applicant_ly = (LinearLayout) findViewById(R.id.co_applicant_ly);
        applicant_ly = (LinearLayout) findViewById(R.id.applicant_ly);
        applicant_ly.setVisibility(View.VISIBLE);
        property_ly = (LinearLayout) findViewById(R.id.property_ly);
        pan_ly = (LinearLayout) findViewById(R.id.pan_ly);
        salaried_ly = (LinearLayout) findViewById(R.id.salaried_ly);
        self_ly = (LinearLayout) findViewById(R.id.self_ly);

        employement_selection_ly = (LinearLayout) findViewById(R.id.employement_selection_ly);

        credit_radiogroup=findViewById(R.id.credit_radiogroup);
        legal_group=findViewById(R.id.legal_group);
        arrangepdf_group=findViewById(R.id.arrangepdf_group);
        Log.i("TAG", "onCheckedChanged:notselected "+closedloan_radio);


        credit_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                //int mySelectedIndex = (int) rb.getTag();
                closedloan_radio= rb.getText().toString();

                if (closedloan_radio.equalsIgnoreCase("Yes")){
                    closedloan_radio="1";
                    Log.i("TAG", "closedloan radio onCheckedChanged: "+closedloan_radio);
                }else{
                    closedloan_radio="2";
                    Log.i("TAG", "closedloan radio onCheckedChanged: "+closedloan_radio);

                }
                Log.i("TAG", "onCheckedChanged:closedloan_radio "+closedloan_radio);
            }
        });

        arrangepdf_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                //int mySelectedIndex = (int) rb.getTag();
                arrangepdf_radio= rb.getText().toString();
                if (arrangepdf_radio.equalsIgnoreCase("Yes")){
                    arrangepdf_radio="1";
                }else{
                    arrangepdf_radio="2";
                }
                Log.i("TAG", "onCheckedChanged:closedloan_radio "+arrangepdf_radio);
            }
        });

        legal_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) findViewById(checkedId);
                //int mySelectedIndex = (int) rb.getTag();
                legal_radio= rb.getText().toString();
                if (legal_radio.equalsIgnoreCase("Yes")){
                    legal_radio="1";
                }else{
                    legal_radio="2";
                }
                Log.i("TAG", "onCheckedChanged:closedloan_radio "+legal_radio);
            }
        });

        other_income_amt_ly_ = (LinearLayout) findViewById(R.id.other_income_amt_ly);
        other_income_amt_ly_self = (LinearLayout) findViewById(R.id.other_income_amt_ly_self);



        ofiice_res_details = (LinearLayout) findViewById(R.id.ofiice_res_details);
        BL_self_office_ownership_type_ly = (LinearLayout) findViewById(R.id.BL_self_office_ownership_type_ly);


        property_btn = (AppCompatButton) findViewById(R.id.property_btn);
        Pan_btn = (AppCompatButton) findViewById(R.id.Pan_btn);
        salried_self_btn = (AppCompatButton) findViewById(R.id.salried_self_btn);
        residence_btn = (AppCompatButton) findViewById(R.id.residence_btn);
        creditbankbtn = (AppCompatButton) findViewById(R.id.creditbankbtn);

        if(loan_type_id.equals("21")){
            salarycredtextview.setText("Which Bank Name Your Salary is Credited to?");
            avgbanklay.setVisibility(View.GONE);
            bankturnoverlay.setVisibility(View.GONE);
            accounttypelay.setVisibility(View.GONE);
            majortranslay.setVisibility(View.GONE);


        }else{
            salarycredtextview.setText("Which Bank Business Transacation is Carried out?");

        }


        if(loan_type_id.equals("20")||loan_type_id.equals("21") )
        {
            //  residence_layout.setVisibility(View.VISIBLE);
            property_ly.setVisibility(View.GONE);
            property_Scroll.setVisibility(View.GONE);
            property_btn.setVisibility(View.GONE);

         Pan_btn.setVisibility(View.VISIBLE);
        //  creditbankbtn.setVisibility(View.VISIBLE);
            salried_self_btn.setVisibility(View.GONE);
           // residence_btn.setVisibility(View.VISIBLE);

            Pan_scroll.setVisibility(View.VISIBLE);
            salaried_Scroll.setVisibility(View.VISIBLE);
            res_scroll.setVisibility(View.VISIBLE);

            property_ly.setVisibility(View.GONE);
           pan_ly.setVisibility(View.VISIBLE);
         // creditbanklay.setVisibility(View.VISIBLE);
          //  res_ly.setVisibility(View.VISIBLE);
            //salaried_ly.setVisibility(View.VISIBLE);
            //self_ly.setVisibility(View.VISIBLE);

            pan_scroll.setBackgroundResource(R.drawable.capsul_button_icon);
            salaried_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
            res_scroll1_img.setBackgroundResource(R.drawable.capsul_button_icon1);
            creditbank_scroll_img.setBackgroundResource(R.drawable.capsul_button_icon1);
            employement_selection_ly.setVisibility(View.GONE);

        }

    }
    private void updateLabel()
    {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_of_birt_txt.setText(sdf.format(myCalendar.getTime()));
    }
    private InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };
    private void click()
    {

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };

        date_of_birt_txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    new DatePickerDialog(Viability_Screen_revamp_Pl_BL.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
                    // Hide your calender here
                }
            }
        });
        date_of_birt_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Viability_Screen_revamp_Pl_BL.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Pan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String DOB1 = date_of_birt_txt.getText().toString();
                Log.e("DOB",DOB1);
                String Pan = PAN_Edit_text.getText().toString();

                Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

                Matcher matcher = pattern .matcher(Pan);

                if (matcher .matches()) {

                    if (!validate_DOB()) {
                        return;
                    }
                    if (!validate_Fathers_Name()) {
                        return;
                    }
                    if(!validate_noofdependents()){
                        return;
                    }

                    if(maritial_status_id.equals("0"))
                    {
                        Toast.makeText(context,"Select marital Status",Toast.LENGTH_SHORT).show();
                    }
                    else if(educationalstatusid.equalsIgnoreCase("0")){
                        Toast.makeText(context,"Please Select Educational Qualication",Toast.LENGTH_SHORT).show();
                    }else if(genderstatus_id.equalsIgnoreCase("0")){
                        Toast.makeText(context,"Please Select Gender",Toast.LENGTH_SHORT).show();

                    }
                   else if(assetownid.size()==0)
                    {
                        Toast.makeText(context,"Select Asset own",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        Pan_Details = new JSONObject();

                         DOB = date_of_birt_txt.getText().toString();
                         Fathers_Name_str = Fathers_Name.getText().toString();
                         Pan_number = PAN_Edit_text.getText().toString().replace(" ","");
                         noofdepent_str=noof_dependenttxt.getText().toString();

                        try {
                            Pan_Details.put("member_dob",DOB);
                            Pan_Details.put("father_name",Fathers_Name_str);
                            Pan_Details.put("pan_no",Pan_number);
                            Pan_Details.put("marital_status",maritial_status_id);
                            Pan_Details.put("no_of_dependency",noofdepent_str);
                            Pan_Details.put("gender",genderstatus_id);
                            Pan_Details.put("assets",assetownid);
                            Pan_Details.put("qualification",educationalstatusid);
                           // Pan_Details.put("autocomplete",autocompletetextview.getText().toString());
                            Pan_Details.put("has_pancard","1");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Submit_PAN();
                        Log.e("the PAN",Pan_Details.toString());

                      /*  Pan_btn.setVisibility(View.GONE);
                        salried_self_btn.setVisibility(View.VISIBLE);
                        res_ly.setVisibility(View.GONE);
                        residence_btn.setVisibility(View.GONE);

                        pan_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                        salaried_scroll.setBackgroundResource(R.drawable.capsul_button_icon);
                        res_scroll1_img.setBackgroundResource(R.drawable.capsul_button_icon1);

                        property_ly.setVisibility(View.GONE);
                        pan_ly.setVisibility(View.GONE);

                        if(loan_type_id.equals("20"))
                        {
                            salaried_ly.setVisibility(View.GONE);
                            self_ly.setVisibility(View.VISIBLE);

                        }else if(loan_type_id.equals("21"))
                        {
                            salaried_ly.setVisibility(View.VISIBLE);
                            self_ly.setVisibility(View.GONE);
                        }*/
                    }

                }else
                {
                    PAN_Edit_text.setError(getText(R.string.pan_err));
                    PAN_Edit_text.requestFocus();
                }





            }
        });

        salried_self_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(loan_type_id.equals("21"))
                {
                    if (!validate_Monthly_net_salary()) {
                        return;
                    }
                    if(Salary_id.equals("0"))
                    {
                        Toast.makeText(context,"Select salary credit method",Toast.LENGTH_SHORT).show();
                    }
                    if (epfstatusid.equals("0")){
                        Toast.makeText(context,"Select EPF deduct in salary",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {

                        if(salry_proof.size()==0)
                        {
                            Toast.makeText(context,"Select Salary Proof",Toast.LENGTH_SHORT).show();
                        }else
                        {


                            if(typeofemployeid.equals("0")){
                                Toast.makeText(context,"Select Type of employee",Toast.LENGTH_SHORT).show();

                            }
                            if(employeproofid.equals("0")){
                                Toast.makeText(context,"Select Employee Proof",Toast.LENGTH_SHORT).show();
                            }
                            else if(employeproofid.equals("2")){
                                if (!validate_officialmailid()) {
                                    return;
                                }

                            }

                            if(pl_co_app_Company_id.equals("0"))
                        {
                            Toast.makeText(context,"Select company type",Toast.LENGTH_SHORT).show();
                        }else
                        {

                            if (!validate_company_name_edit_text()) {
                                return;
                            }
                            if (!validate_designation_edit_text()) {
                                return;
                            }
                            if (!validate_experience_in_current_cmpy()) {
                                return;
                            }
                            if (!validate_total_experience_edit_txt()) {
                                return;
                            }
                            if (!validate_company_pincode_txt()) {
                                return;
                            }
                            if (!validate_doornumber_edit_text()) {
                                return;
                            }
                            if(company_area.equals("0"))
                            {
                                Toast.makeText(context,"Select company area",Toast.LENGTH_SHORT).show();
                            }else
                            {
                                if (Other_income_id.equals("0")) {
                                    Toast.makeText(context, "Select Other income", Toast.LENGTH_SHORT).show();
                                } else {

                                    if (Other_income_id.equals("2")) {
                                        Employement_type = new JSONObject();
                                         monthly_net_salary = monthly_net_sal_edit_txt.getText().toString();
                                         company_name = company_name_edit_text.getText().toString();
                                         designation_ = designation_edit_text.getText().toString();
                                         experience_in_current = experience_in_current_cmpy.getText().toString();
                                         total_experience_ = total_experience_edit_txt.getText().toString();
                                         company_pincode = company_pincode_txt.getText().toString();
                                         other_income_amt = other_income_amt_txt_edit_txt.getText().toString();

                                         Other_income_id_ = new JSONArray(Arrays.asList(Other_income_id));
                                         other_income_amt_ = new JSONArray(Arrays.asList(other_income_amt));

                                        try {
                                            Employement_type.put("net_salary",monthly_net_salary);
                                            Employement_type.put("salary_mode",Salary_id);
                                            Employement_type.put("income_proof",salry_proof);
                                            Employement_type.put("company_type",pl_co_app_Company_id);
                                            Employement_type.put("company_name",company_name);
                                            Employement_type.put("designation",designation_);
                                            Employement_type.put("work_experience",ecperience);
                                            Employement_type.put("tot_work_experience",total_experiance);
                                            Employement_type.put("work_pincode",company_pincode);
                                            Employement_type.put("ofc_area",company_area);

                                            Employement_type.put("ofc_add_proof",employeproofid);
                                            Employement_type.put("office_email",officialmailidtxt.getText().toString());

                                            Employement_type.put("is_epf_deduct",epfstatusid);
                                            Employement_type.put("employee_type",typeofemployeid);
                                            Employement_type.put("ofc_street",doorstreet_txt.getText().toString());
                                            Employement_type.put("is_other_eranings", Other_income_id_);
                                            Employement_type.put("other_income", other_income_amt_);


                                            //  Employement_type.put("other_from", Other_income_id_);
                                          //  Employement_type.put("other_amount", other_income_amt_);
                                            Log.e("Employement_type",Employement_type.toString());

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        Submit_EMP_salried();
                                    } else {


                                        if (!validate_other_income_amt_txt_edit_txt()) {
                                            return;
                                        }

                                        Employement_type = new JSONObject();


                                         monthly_net_salary = monthly_net_sal_edit_txt.getText().toString();
                                         company_name = company_name_edit_text.getText().toString();
                                         designation_ = designation_edit_text.getText().toString();
                                         experience_in_current = experience_in_current_cmpy.getText().toString();
                                         total_experience_ = total_experience_edit_txt.getText().toString();
                                         company_pincode = company_pincode_txt.getText().toString();
                                         other_income_amt = other_income_amt_txt_edit_txt.getText().toString();

                                         Other_income_id_ = new JSONArray(Arrays.asList(Other_income_id));
                                         other_income_amt_ = new JSONArray(Arrays.asList(other_income_amt));

                                        try {
                                            Employement_type.put("net_salary", monthly_net_salary);
                                            Employement_type.put("salary_mode", Salary_id);
                                            Employement_type.put("income_proof", salry_proof);
                                            Employement_type.put("company_type", pl_co_app_Company_id);
                                            Employement_type.put("company_name", company_name);
                                            Employement_type.put("designation", designation_);
                                            Employement_type.put("work_experience", ecperience);
                                            Employement_type.put("tot_work_experience", total_experiance);
                                            Employement_type.put("work_pincode", company_pincode);
                                            Employement_type.put("ofc_area", company_area);
                                          /*  Employement_type.put("other_from", Other_income_id_);
                                            Employement_type.put("other_amount", other_income_amt_);*/

                                            Employement_type.put("ofc_add_proof",employeproofid);
                                            Employement_type.put("office_email",officialmailidtxt.getText().toString());


                                            Employement_type.put("is_epf_deduct",epfstatusid);
                                            Employement_type.put("employee_type",typeofemployeid);
                                            Employement_type.put("ofc_street",doorstreet_txt.getText().toString());
                                            Employement_type.put("is_other_eranings", Other_income_id_);
                                            Employement_type.put("other_income", other_income_amt_);
                                            Log.e("Employement_type", Employement_type.toString());

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        Submit_EMP_salried();

                                    }

                                }

                            }


                        }
                    }
                    }


                }else if(loan_type_id.equals("20"))
                {

                    /* salaried_ly.setVisibility(View.GONE);
                    self_ly.setVisibility(View.VISIBLE);*/
                   if(self_Employee_type_Id.equals("0"))
                   {
                       Toast.makeText(context,"Select Type of Self Employment",Toast.LENGTH_SHORT).show();
                   }else
                   {
                       if(Emp_vocation_type_id.equals("0"))
                       {
                           Toast.makeText(context,"Select Vocation Type",Toast.LENGTH_SHORT).show();
                       }else
                       {
                           if (!validate_about_business()) {
                               return;
                           }

                           if (!validate_annualprofit()) {
                               return;
                           }
                           if (!validate_annualturnover()) {
                               return;
                           }


                           if (!No_of_years_work()) {
                               return;
                           }

                           if(list_vintage_proof.size()==0)
                           {
                               Toast.makeText(context,"Select Business Vintage Proof",Toast.LENGTH_SHORT).show();

                           }else {
                               if (list_income_proof_self.size() == 0) {
                                   Toast.makeText(context, "Select Business Income Proof", Toast.LENGTH_SHORT).show();

                               }
                               if (haveaddressid.equals("0")) {
                                   Toast.makeText(context, "Select Address Proof for office", Toast.LENGTH_SHORT).show();
                               }

                               if (fileitrid.equals("0")) {
                                   Toast.makeText(context, "Select File Itr", Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   if(pl_co_app_ind_Office_Shop__id.equals("0"))
                                   {
                                       Toast.makeText(context,"Select Office Setup",Toast.LENGTH_SHORT).show();
                                   }else {

                                       if (pl_co_app_ind_Office_Shop__id.equals("1") || pl_co_app_ind_Office_Shop__id.equals("3")) {
                                           other_income_validat();
                                       } else {

                                           if (pl_co_app_ind_Office_Shop_Own_id.equals("0")) {
                                               Toast.makeText(context, "Select Office owned type", Toast.LENGTH_SHORT).show();
                                           } else {
                                               if (!validate_office_residence_pincode()) {
                                                   return;
                                               }

                                               if (residence_area_office.equals("0")) {
                                                   Toast.makeText(context, "Select office area", Toast.LENGTH_SHORT).show();
                                               }

                                               if (haveaddressid.equals("0")) {
                                                   Toast.makeText(context, "Select Address Proof for office", Toast.LENGTH_SHORT).show();
                                               }

                                               if (fileitrid.equals("0")) {
                                                   Toast.makeText(context, "Select File Itr", Toast.LENGTH_SHORT).show();
                                               }

                                               else {

                                                   other_income_validat();
                                                   ///
                                               }
                                           }

                                       }
                                   }
                               }
                           }

                           ///

                       }
                   }

                }

            }
        });

        residence_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(residencedoorno_txt.getText().toString().isEmpty()){
                    residencedoorno_txt.setError(getText(R.string.error_rise));
                    residencedoorno_txt.requestFocus();
                }else if(residence_pincode1_edit_txt_resi.getText().toString().isEmpty()|| residence_pincode1_edit_txt_resi.length() <6) {
                    residence_pincode1_edit_txt_resi.setError(getText(R.string.error_rise));
                    residence_pincode1_edit_txt_resi.requestFocus();
                }else if(residence_area_res.equals("0")){
                    Toast.makeText(context,"Select residence area",Toast.LENGTH_SHORT).show();
                }else if(spinner_residence_type_res_id.equals("0")){
                    Toast.makeText(context,"Select residence Ownership Type",Toast.LENGTH_SHORT).show();
                }else if(currentresid.equals("0")){
                    Toast.makeText(context,"Select Current Residence Address Proof",Toast.LENGTH_SHORT).show();
                }else if(spinner_residence_type_res_id.equals("1") || spinner_residence_type_res_id.equals("2")){
                    othervalidate_residence();
                }
                else if(spinner_residence_type_res_id.equals("3") || spinner_residence_type_res_id.equals("4") || spinner_residence_type_res_id.equals("5") || spinner_residence_type_res_id.equals("6"))
                {
                    if(rentpaid_txt.getText().toString().isEmpty()){
                        Toast.makeText(Viability_Screen_revamp_Pl_BL.this,"Please fill rent paid for house",Toast.LENGTH_SHORT).show();
                    }
                    else if(per_residencepincode.getText().toString().isEmpty()){
                        Toast.makeText(Viability_Screen_revamp_Pl_BL.this,"Please fill Permanent Residence Pincode",Toast.LENGTH_SHORT).show();
                    }else if(permanentresid.equals("0")){
                        Toast.makeText(Viability_Screen_revamp_Pl_BL.this,"Please Select Permanent Residence Type ",Toast.LENGTH_SHORT).show();

                    }else if(liveincurrent_txt.getText().toString().isEmpty()){
                        Toast.makeText(Viability_Screen_revamp_Pl_BL.this,"Please fill live in current residence",Toast.LENGTH_SHORT).show();
                    }else {
                        Residence = new JSONObject();
                        residence_pincode1_ = residence_pincode1_edit_txt_resi.getText().toString();

                        try {

                            Residence.put("res_pincode", residence_pincode1_);
                            Residence.put("per_area", residence_area_res);
                            Residence.put("res_type", spinner_residence_type_res_id);

                            Residence.put("street", residencedoorno_txt.getText().toString());
                            Residence.put("rent_beingpaid", rentpaid_txt.getText().toString());
                            Residence.put("perm_res_pincode", per_residencepincode.getText().toString());
                            Residence.put("perm_residence", permanentresid);
                            Residence.put("live_in_res", liveincurrent_txt.getText().toString());
                            Residence.put("cur_address_proof", currentresid);

                            Log.e("residence", Residence.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Submit_Residence();
                    }




                    }

                }

            private void othervalidate_residence() {
              /*  if(rentpaid_txt.getText().toString().isEmpty()){
                    Toast.makeText(Viability_Screen_revamp_Pl_BL.this,"Please fill rent paid details",Toast.LENGTH_SHORT).show();
                }else if(per_residencepincode.getText().toString().isEmpty()){
                    Toast.makeText(Viability_Screen_revamp_Pl_BL.this,"Please fill Permanent Residence Pincode",Toast.LENGTH_SHORT).show();
                }else if(permanentresid.equals("0")){
                    Toast.makeText(Viability_Screen_revamp_Pl_BL.this,"Please Select Permanent Residence Type ",Toast.LENGTH_SHORT).show();

                }else if(liveincurrent_txt.getText().toString().isEmpty()){
                    Toast.makeText(Viability_Screen_revamp_Pl_BL.this,"Please fill rent paid details",Toast.LENGTH_SHORT).show();
                }*/
                Residence = new JSONObject();
                residence_pincode1_ = residence_pincode1_edit_txt_resi.getText().toString();

                try {

                    Residence.put("res_pincode",residence_pincode1_);
                    Residence.put("per_area",residence_area_res);
                    Residence.put("res_type",spinner_residence_type_res_id);

                    Residence.put("street",residencedoorno_txt.getText().toString());
                 //   Residence.put("rent_beingpaid",rentpaid_txt.getText().toString());
                 //   Residence.put("perm_res_pincode",per_residencepincode.getText().toString());
                  //  Residence.put("perm_residence",permanentresid);
                 //   Residence.put("live_in_res",liveincurrent_txt.getText().toString());
                    Residence.put("cur_address_proof",currentresid);
                          /*  Residence.put("addr_proof_own","1");
                            Residence.put("addr_proof_rent","2");*/

                    Log.e("residence",Residence.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Submit_Residence();

            }


        });




        creditbankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(loan_type_id.equals("21")){
                    if(creditscoreid.equals("0"))
                    {
                        Toast.makeText(context,"Select Credit Score",Toast.LENGTH_SHORT).show();
                    }else if(emilateid.equalsIgnoreCase("0")){
                        Toast.makeText(context,"Select EMI Month late",Toast.LENGTH_SHORT).show();
                    }else if(banklistid.equalsIgnoreCase("0")){
                        Toast.makeText(context,"Select Bank Credited Salary",Toast.LENGTH_SHORT).show();
                    }else if(credit_radiogroup.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(context,"Please Select do you have running loan",Toast.LENGTH_SHORT).show();
                    }else if(legal_group.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(context,"Please Select legal in Past Loans",Toast.LENGTH_SHORT).show();
                    }else if(arrangepdf_group.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(context,"Please Select Bank Generated Pdf",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Creditbank_Details = new JSONObject();
                        try {

                            Creditbank_Details.put("is_existloan", closedloan_radio);
                            Creditbank_Details.put("is_write_off", legal_radio);
                            Creditbank_Details.put("entered_credit_score", creditscoreid);
                            Creditbank_Details.put("emi_late", emilateid);
                            Creditbank_Details.put("salary_bank", banklistid);
                            Creditbank_Details.put("arrange_bank_pdf",arrangepdf_radio);

                            //what is avargebankbalance
               /*             Creditbank_Details.put("entered_abb", avgbankbaltxt.getText().toString());
                            //banking turnover
                            Creditbank_Details.put("banking_turnover", bankturnovertxt.getText().toString());
                            //bank ac type
                            Creditbank_Details.put("bank_ac_type", "2");
                            //major transacation through
                            Creditbank_Details.put("major_transaction", "1");*/


                            Log.e("credit", Creditbank_Details.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Submit_Creditbankdetails();
                       // viability_Applicant();



                    }

                }else {


                    if(creditscoreid.equals("0"))
                    {
                        Toast.makeText(context,"Select Credit Score",Toast.LENGTH_SHORT).show();
                    }else if(emilateid.equals("0")){
                        Toast.makeText(context,"Select EMI Month late",Toast.LENGTH_SHORT).show();
                    }else if(banklistid.equalsIgnoreCase("0")) {
                        Toast.makeText(context, "Select Business Transcattion Carried out", Toast.LENGTH_SHORT).show();
                    } else if (!avgbankbalance()) {
                        return;
                    }else if (!bankturnover()) {
                        return;
                    }else if(bankactypeid.equals("0")) {
                        Toast.makeText(context, "Select Bank Account Type", Toast.LENGTH_SHORT).show();
                    } else if(majortransid.equals("0")) {
                        Toast.makeText(context, "Select Major transcation through?", Toast.LENGTH_SHORT).show();
                    }else if(credit_radiogroup.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(context,"Please Select do you have running loan",Toast.LENGTH_SHORT).show();
                    }else if(legal_group.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(context,"Please Select legal in Past Loans",Toast.LENGTH_SHORT).show();
                    }else if(arrangepdf_group.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(context,"Please Select Bank Generated Pdf",Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Creditbank_Details = new JSONObject();
                        try {

                            Creditbank_Details.put("is_existloan", closedloan_radio);
                            Creditbank_Details.put("is_write_off", legal_radio);
                            Creditbank_Details.put("entered_credit_score", creditscoreid);
                            Creditbank_Details.put("emi_late", emilateid);
                            Creditbank_Details.put("salary_bank", banklistid);
                            Creditbank_Details.put("arrange_bank_pdf", arrangepdf_radio);

                            //what is avargebankbalance
                            Creditbank_Details.put("entered_abb", avgbankbaltxt.getText().toString());
                            //banking turnover
                            Creditbank_Details.put("banking_turnover", bankturnovertxt.getText().toString());
                            //bank ac type
                            Creditbank_Details.put("bank_ac_type", bankactypeid);
                            //major transacation through
                            Creditbank_Details.put("major_transaction", majortransid);


                            Log.e("credit", Creditbank_Details.toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                            Submit_Creditbankdetails();
                        //viability_Applicant();

                    }
                }
            }
        });

      /*  creditbankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Creditbank_Details = new JSONObject();
                try {

                    Creditbank_Details.put("is_existloan","2");
                    Creditbank_Details.put("is_write_off","2");
                    Creditbank_Details.put("entered_credit_score","1");
                    Creditbank_Details.put("emi_late","2");
                    Creditbank_Details.put("salary_bank","2");
                    Creditbank_Details.put("arrange_bank_pdf","1");


                    Log.e("residence",Creditbank_Details.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                viability_Applicant();

            }
        });*/



        company_pincode_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("hi","hi11");
                String workpincode = company_pincode_txt.getText().toString();

                if(workpincode.length()==6){
                    GET_Pincode1(workpincode);
                }
               // imm.hideSoftInputFromWindow(company_pincode_txt.getWindowToken(), 0);
            }
        });

        office_residence_pincode_edite_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("hi","hi11");
                String workpincode = office_residence_pincode_edite_txt.getText().toString();

                if(workpincode.length()==6){
                    GET_AERA_POST_res(workpincode);
                }else {
                  //  Objs.a.showToast(context,"Please Select Pin code");
                }
               // imm.hideSoftInputFromWindow(office_residence_pincode_edite_txt.getWindowToken(), 0);
            }
        });

        residence_pincode1_edit_txt_resi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("hi","hi11");
                String workpincode = residence_pincode1_edit_txt_resi.getText().toString();

                if(workpincode.length()==6){
                    GET_AERA_POST_res1(workpincode);
                }else {
                  //  Objs.a.showToast(context,"Please Select Pin code");
                }
              //  imm.hideSoftInputFromWindow(residence_pincode1_edit_txt.getWindowToken(), 0);
            }
        });


        experience_in_current_cmpy.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    show();
                } else {
                    // Hide your calender here
                }


            }
        });

        no_of_years_work_ind_edit_txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    show2();
                } else {
                    // Hide your calender here
                }


            }
        });

        total_experience_edit_txt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus) {
                    show1();
                } else {
                    // Hide your calender here
                }


            }
        });
        total_experience_edit_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show1();
            }
        });

        no_of_years_work_ind_edit_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show2();
            }
        });

        experience_in_current_cmpy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });

    }


    private void other_income_validat()
    {
        if (Other_income_id_self.equals("0")) {
            Toast.makeText(context, "Select Other Income", Toast.LENGTH_SHORT).show();
        } else {

            if (Other_income_id_self.equals("2")) {
                Employement_type = new JSONObject();




                 ave_month_income = avg_monthly_incom_edit_txt.getText().toString();
                 no_of_years_in_works = no_of_years_work_ind_edit_txt.getText().toString();
                 office_res_pincode = office_residence_pincode_edite_txt.getText().toString();
                 other_income_amt1_self = other_income_amt_txt_edit_txt_self.getText().toString();


                JSONArray Other_income_id_self_ = new JSONArray(Arrays.asList(Other_income_id_self));
                JSONArray other_income_amt_self = new JSONArray(Arrays.asList(other_income_amt1_self));
                try {
                    Employement_type.put("bus_employment_type", self_Employee_type_Id);
                    Employement_type.put("business_vocation", Emp_vocation_type_id);
                    Employement_type.put("net_salary", ave_month_income);
                    Employement_type.put("work_experience", no_of_month_in_business);
                    Employement_type.put("income_proof", list_income_proof_self);
                    Employement_type.put("bus_proof", list_vintage_proof);
                    Employement_type.put("office_setup", pl_co_app_ind_Office_Shop__id);
                    Employement_type.put("office_res", pl_co_app_ind_Office_Shop_Own_id);
                    Employement_type.put("work_pincode", office_res_pincode);
                    Employement_type.put("ofc_area", residence_area_office);


                    //have itr file
                    Employement_type.put("is_itr_file", fileitrid);
                   // have address proof of office
                    Employement_type.put("ofc_add_proof", haveaddressid);
                    //annual profit
                  Employement_type.put("profit", annualprofittxt.getText().toString());
                  //annual turnover
                    Employement_type.put("turnover_curyr", annualturntxt.getText().toString());
                  Employement_type.put("about_company", aboutbusinesstxt.getText().toString());

                    Employement_type.put("other_from", Other_income_id_self_);
                    Employement_type.put("other_amount", other_income_amt_self);
                    Log.e("Employement_type", Employement_type.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Submit_EMP_self();

            } else {

                if (!validate_other_income_amt_txt_edit_txt_self()) {
                    return;
                }

                Employement_type = new JSONObject();
                 ave_month_income = avg_monthly_incom_edit_txt.getText().toString();
                 no_of_years_in_works = no_of_years_work_ind_edit_txt.getText().toString();
                 office_res_pincode = office_residence_pincode_edite_txt.getText().toString();
                 other_income_amt1_self = other_income_amt_txt_edit_txt_self.getText().toString();

                                     /*  JSONArray income_proof = new JSONArray();
                                       JSONArray vintage_proof = new JSONArray();
                                       income_proof = new JSONArray(Arrays.asList(list_income_proof_self));
                                       vintage_proof = new JSONArray(Arrays.asList(list_vintage_proof));*/

                JSONArray Other_income_id_self_ = new JSONArray(Arrays.asList(Other_income_id_self));
                JSONArray other_income_amt_self = new JSONArray(Arrays.asList(other_income_amt1_self));
                try {
                    Employement_type.put("bus_employment_type", self_Employee_type_Id);
                    Employement_type.put("business_vocation", Emp_vocation_type_id);
                    Employement_type.put("net_salary", ave_month_income);
                    Employement_type.put("work_experience", no_of_month_in_business);
                    Employement_type.put("income_proof", list_income_proof_self);
                    Employement_type.put("bus_proof", list_vintage_proof);
                    Employement_type.put("office_setup", pl_co_app_ind_Office_Shop__id);
                    Employement_type.put("office_res", pl_co_app_ind_Office_Shop_Own_id);
                    Employement_type.put("work_pincode", office_res_pincode);
                    Employement_type.put("ofc_area", residence_area_office);

                    Employement_type.put("other_from", Other_income_id_self_);
                    Employement_type.put("other_amount", other_income_amt_self);

                    //have itr file
                    Employement_type.put("is_itr_file", fileitrid);
                    // have address proof of office
                    Employement_type.put("ofc_add_proof", haveaddressid);
                    //annual profit
                    Employement_type.put("profit", annualprofittxt.getText().toString());
                    //annual turnover
                    Employement_type.put("turnover_curyr", annualturntxt.getText().toString());
                    Employement_type.put("about_company", aboutbusinesstxt.getText().toString());

                    Log.e("Employement_type", Employement_type.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Submit_EMP_self();

            }
        }
    }

    ////////////////////////VALIDATION////

    private void Submit_PAN(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.pan_sumbit_dialog);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn=(Button)dialog.findViewById(R.id.submitbtn);

        AppCompatTextView Pan_No_Show,dob_Show,father_name,marital_status,noofdependent_show,gendershow,
                edu_qualshow,assetshow,salarycredtextview;

        Pan_No_Show = (AppCompatTextView) dialog.findViewById(R.id.Pan_No_Show);
        dob_Show = (AppCompatTextView) dialog.findViewById(R.id.dob_Show);
        father_name = (AppCompatTextView) dialog.findViewById(R.id.father_name);
        marital_status = (AppCompatTextView) dialog.findViewById(R.id.marital_status);
        noofdependent_show = (AppCompatTextView) dialog.findViewById(R.id.noofdependent_show);
        gendershow = (AppCompatTextView) dialog.findViewById(R.id.gendershow);
        edu_qualshow = (AppCompatTextView) dialog.findViewById(R.id.edu_qualshow);
        assetshow = (AppCompatTextView) dialog.findViewById(R.id.assetshow);

        String Fathers_Name_str1 = Fathers_Name_str.substring(0, 1).toUpperCase() + Fathers_Name_str.substring(1);

        Pan_No_Show.setText(Pan_number);
        dob_Show.setText(DOB);
        father_name.setText(Fathers_Name_str1);
        marital_status.setText(maritial_status_Value);
        noofdependent_show.setText(noof_dependenttxt.getText().toString());
        gendershow.setText(genderstatus_value);
        edu_qualshow.setText(educationalstatusvalue);
        String list = Arrays.toString(assetownvalue.toArray()).replace("[", "").replace("]", "");
        assetshow.setText(list);


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Pan_btn.setVisibility(View.GONE);
                salried_self_btn.setVisibility(View.VISIBLE);
                res_ly.setVisibility(View.GONE);
                residence_btn.setVisibility(View.GONE);

                pan_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                salaried_scroll.setBackgroundResource(R.drawable.capsul_button_icon);
                res_scroll1_img.setBackgroundResource(R.drawable.capsul_button_icon1);
                creditbank_scroll_img.setBackgroundResource(R.drawable.capsul_button_icon1);

                property_ly.setVisibility(View.GONE);
                pan_ly.setVisibility(View.GONE);

                if(loan_type_id.equals("20"))
                {
                    salaried_ly.setVisibility(View.GONE);
                    self_ly.setVisibility(View.VISIBLE);

                }else if(loan_type_id.equals("21"))
                {
                    salaried_ly.setVisibility(View.VISIBLE);
                    self_ly.setVisibility(View.GONE);
                }

            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        if (!dialog.isShowing()) {
            dialog.show();
        }

    }

    private void Submit_EMP_salried(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.salaried_sumbit_dialog);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn=(Button)dialog.findViewById(R.id.submitbtn);

         other_income_amount_ly =   (LinearLayout)dialog.findViewById(R.id.other_income_amount_ly);
         LinearLayout officailmaildlay;

        AppCompatTextView Monthly_net_salry_Show,salary_Show,salary_proof_show,company_type_show,company_name_show,
                designation_show,current_show,total_experience_show,company_pincode_show,area_show,
                other_income_source_show,other_income_amount,epfdeductshow,typeofemploye_show,companydoor_show,employeeproofshow,officialmailshow;

        Monthly_net_salry_Show = (AppCompatTextView) dialog.findViewById(R.id.Monthly_net_salry_Show);
        officailmaildlay=(LinearLayout)dialog.findViewById(R.id.officailmaildlay);
        officialmailshow=(AppCompatTextView) dialog.findViewById(R.id.officialmailshow);
        salary_Show = (AppCompatTextView) dialog.findViewById(R.id.salary_Show);
        salary_proof_show = (AppCompatTextView) dialog.findViewById(R.id.salary_proof_show);
        company_type_show = (AppCompatTextView) dialog.findViewById(R.id.company_type_show);
        company_name_show = (AppCompatTextView) dialog.findViewById(R.id.company_name_show);
        designation_show = (AppCompatTextView) dialog.findViewById(R.id.designation_show);
        current_show = (AppCompatTextView) dialog.findViewById(R.id.current_show);
        total_experience_show = (AppCompatTextView) dialog.findViewById(R.id.total_experience_show);
        company_pincode_show = (AppCompatTextView) dialog.findViewById(R.id.company_pincode_show);
        area_show = (AppCompatTextView) dialog.findViewById(R.id.area_show);
        other_income_source_show = (AppCompatTextView) dialog.findViewById(R.id.other_income_source_show);
        other_income_amount = (AppCompatTextView) dialog.findViewById(R.id.other_income_amount);
        epfdeductshow = (AppCompatTextView) dialog.findViewById(R.id.epfdeductshow);
        typeofemploye_show = (AppCompatTextView) dialog.findViewById(R.id.typeofemploye_show);
        companydoor_show = (AppCompatTextView) dialog.findViewById(R.id.companydoor_show);
        employeeproofshow = (AppCompatTextView) dialog.findViewById(R.id.employeeproofshow);

        Monthly_net_salry_Show.setText(monthly_net_salary);
        salary_Show.setText(Salary_Value);
        employeeproofshow.setText(employeproofvalue);
      //  salary_proof_show.setText(salry_proof_value.toString());
        String list = Arrays.toString(salry_proof_value.toArray()).replace("[", "").replace("]", "");
        salary_proof_show.setText(list);
        company_type_show.setText(pl_co_app_Company_Value);
        company_name_show.setText(company_name);
        designation_show.setText(designation_);
        current_show.setText(experience_in_current_cmpy.getText().toString());
        total_experience_show.setText(total_experience_edit_txt.getText().toString());
        company_pincode_show.setText(company_pincode);
        area_show.setText(company_area1);
        epfdeductshow.setText(epfstatusvalue);
        typeofemploye_show.setText(typeofemployevalue);
        companydoor_show.setText(doorstreet_txt.getText().toString());



        other_income_source_show.setText(Other_income_Value);
        if(Other_income_id.equals("2"))
        {
            other_income_amount_ly.setVisibility(View.GONE);
        }else
        {
            other_income_amount_ly.setVisibility(View.VISIBLE);
            other_income_amount.setText(other_income_amt);
        }

        if(employeproofid.equals("2")){
            officailmaildlay.setVisibility(View.VISIBLE);
            officialmailshow.setText(officialmailidtxt.getText().toString());
        }else{
            officailmaildlay.setVisibility(View.GONE);
        }


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (loan_type_id.equals("20") || loan_type_id.equals("21")) {

                    Pan_btn.setVisibility(View.GONE);
                    salried_self_btn.setVisibility(View.GONE);
                    residence_btn.setVisibility(View.VISIBLE);

                    property_ly.setVisibility(View.GONE);
                    pan_ly.setVisibility(View.GONE);

                    pan_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                    salaried_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                    res_scroll1_img.setBackgroundResource(R.drawable.capsul_button_icon);
                    creditbank_scroll_img.setBackgroundResource(R.drawable.capsul_button_icon1);
                    //  residence_layout.setVisibility(View.GONE);
                    salaried_ly.setVisibility(View.GONE);
                    self_ly.setVisibility(View.GONE);
                    res_ly.setVisibility(View.VISIBLE);

                }

            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        if (!dialog.isShowing()) {
            dialog.show();
        }

    }

    private void Submit_EMP_self(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.self_sumbit_dialog);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn=(Button)dialog.findViewById(R.id.submitbtn);

        other_income_amount_ly =   (LinearLayout)dialog.findViewById(R.id.other_income_amount_ly);

      LinearLayout  office_res_type_ly =   (LinearLayout)dialog.findViewById(R.id.office_res_type_ly);
        LinearLayout office_pincode_ly =   (LinearLayout)dialog.findViewById(R.id.office_pincode_ly);
        LinearLayout  office_area_ly =   (LinearLayout)dialog.findViewById(R.id.office_area_ly);

        AppCompatTextView type_of_self_emp_show,vocation_Show,monthly_income_show,no_of_month_bus_show,bus_vintage_show,
                bus_income_proof_show,office_shop_show,office_residence_show,office_shop_pin_show,area_show,
                other_income_source_show,other_income_amount;

        AppCompatTextView abtbusiness_show,annualprofit_show,annualturn_show,haveaddress_show,itr_show;

        abtbusiness_show = (AppCompatTextView) dialog.findViewById(R.id.abtbusiness_show);
        annualprofit_show = (AppCompatTextView) dialog.findViewById(R.id.annualprofit_show);
        annualturn_show = (AppCompatTextView) dialog.findViewById(R.id.annualturn_show);
        haveaddress_show = (AppCompatTextView) dialog.findViewById(R.id.haveaddress_show);
        itr_show = (AppCompatTextView) dialog.findViewById(R.id.itr_show);


        type_of_self_emp_show = (AppCompatTextView) dialog.findViewById(R.id.type_of_self_emp_show);
        vocation_Show = (AppCompatTextView) dialog.findViewById(R.id.vocation_Show);
        monthly_income_show = (AppCompatTextView) dialog.findViewById(R.id.monthly_income_show);
        no_of_month_bus_show = (AppCompatTextView) dialog.findViewById(R.id.no_of_month_bus_show);
        bus_vintage_show = (AppCompatTextView) dialog.findViewById(R.id.bus_vintage_show);
        bus_income_proof_show = (AppCompatTextView) dialog.findViewById(R.id.bus_income_proof_show);

        office_shop_show = (AppCompatTextView) dialog.findViewById(R.id.office_shop_show);
        office_residence_show = (AppCompatTextView) dialog.findViewById(R.id.office_residence_show);
        office_shop_pin_show = (AppCompatTextView) dialog.findViewById(R.id.office_shop_pin_show);

        area_show = (AppCompatTextView) dialog.findViewById(R.id.area_show);
        other_income_source_show = (AppCompatTextView) dialog.findViewById(R.id.other_income_source_show);
        other_income_amount = (AppCompatTextView) dialog.findViewById(R.id.other_income_amount);


        abtbusiness_show.setText(aboutbusinesstxt.getText().toString());
        annualprofit_show.setText(annualprofittxt.getText().toString());
        annualturn_show.setText(annualturntxt.getText().toString());
        haveaddress_show.setText(haveaddressvalue);
        itr_show.setText(fileitrvalue);


        type_of_self_emp_show.setText(self_Employee_type_Value);
        vocation_Show.setText(Emp_vocation_type_Value);
        monthly_income_show.setText(ave_month_income);
        no_of_month_bus_show.setText(no_of_years_work_ind_edit_txt.getText().toString());
       // bus_income_proof_show.setText(list_income_proof_self_value.toString());
        String list1 = Arrays.toString(list_income_proof_self_value.toArray()).replace("[", "").replace("]", "");
// bus_income_proof_show.setText(list_income_proof_self_value.toString());
        bus_income_proof_show.setText(list1);
        String list = Arrays.toString(list_vintage_proof_value.toArray()).replace("[", "").replace("]", "");
        bus_vintage_show.setText(list);
      //  bus_vintage_show.setText(list_vintage_proof_value.toString());

        office_shop_show.setText(pl_co_app_ind_Office_Shop__value);

        if(pl_co_app_ind_Office_Shop__id.equals("1") || pl_co_app_ind_Office_Shop__id.equals("3"))
        {
            office_res_type_ly.setVisibility(View.GONE);
                    office_pincode_ly.setVisibility(View.GONE);
            office_area_ly.setVisibility(View.GONE);
        }else
        {

            office_res_type_ly.setVisibility(View.VISIBLE);
            office_pincode_ly.setVisibility(View.VISIBLE);
            office_area_ly.setVisibility(View.VISIBLE);

            office_residence_show.setText(pl_co_app_ind_Office_Shop_Own_value);
            office_shop_pin_show.setText(office_res_pincode);
            area_show.setText(residence_area_office1);
        }


        other_income_source_show.setText(Other_income_Value_self);
        if(Other_income_id_self.equals("2"))
        {
            other_income_amount_ly.setVisibility(View.GONE);
        }else
        {
            other_income_amount_ly.setVisibility(View.VISIBLE);
            other_income_amount.setText(other_income_amt1_self);
        }


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (loan_type_id.equals("20") || loan_type_id.equals("21")) {

                    Pan_btn.setVisibility(View.GONE);
                    salried_self_btn.setVisibility(View.GONE);
                    residence_btn.setVisibility(View.VISIBLE);

                    property_ly.setVisibility(View.GONE);
                    pan_ly.setVisibility(View.GONE);

                    pan_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                    salaried_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                    res_scroll1_img.setBackgroundResource(R.drawable.capsul_button_icon);
                    //  residence_layout.setVisibility(View.GONE);
                    salaried_ly.setVisibility(View.GONE);
                    self_ly.setVisibility(View.GONE);
                    res_ly.setVisibility(View.VISIBLE);


                }

            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        if (!dialog.isShowing()) {
            dialog.show();
        }

    }

    private void Submit_Residence(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.residence);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn=(Button)dialog.findViewById(R.id.submitbtn);

        AppCompatTextView residence_pincode_show,area_show,residence_ownership_show;

        AppCompatTextView resdoortxt_show,rentpaidshow,perrespinodeshow,perrestype,liveincurrentres,currentresaddress;

        LinearLayout liveincurlay,perres_typelay,perrespincodelay,rentpaidlay;

        liveincurlay = (LinearLayout) dialog.findViewById(R.id.liveincurlay);
        perres_typelay = (LinearLayout) dialog.findViewById(R.id.perres_typelay);
        perrespincodelay = (LinearLayout) dialog.findViewById(R.id.perrespincodelay);
        rentpaidlay = (LinearLayout) dialog.findViewById(R.id.rentpaidlay);


        resdoortxt_show = (AppCompatTextView) dialog.findViewById(R.id.doornumshow);
        rentpaidshow = (AppCompatTextView) dialog.findViewById(R.id.rentpaidshow);
        perrespinodeshow = (AppCompatTextView) dialog.findViewById(R.id.perrespinodeshow);
        perrestype = (AppCompatTextView) dialog.findViewById(R.id.perrestype);
        liveincurrentres = (AppCompatTextView) dialog.findViewById(R.id.liveincurrentres);
        currentresaddress = (AppCompatTextView) dialog.findViewById(R.id.currentresaddress);


        residence_pincode_show = (AppCompatTextView) dialog.findViewById(R.id.residence_pincode_show);
        area_show = (AppCompatTextView) dialog.findViewById(R.id.area_show);
        residence_ownership_show = (AppCompatTextView) dialog.findViewById(R.id.residence_ownership_show);

        if(spinner_residence_type_res_id.equals("1") || spinner_residence_type_res_id.equals("2")){
            liveincurlay.setVisibility(View.GONE);
            perres_typelay.setVisibility(View.GONE);
            perrespincodelay.setVisibility(View.GONE);
            rentpaidlay.setVisibility(View.GONE);
            residence_pincode_show.setText(residence_pincode1_);
            area_show.setText(residence_area_res1);
            residence_ownership_show.setText(spinner_residence_type_res_value);
            currentresaddress.setText(currentresvalue);
            resdoortxt_show.setText(residencedoorno_txt1.getText().toString());
        }else{
            residence_pincode_show.setText(residence_pincode1_);
            area_show.setText(residence_area_res1);
            residence_ownership_show.setText(spinner_residence_type_res_value);
            currentresaddress.setText(currentresvalue);
            resdoortxt_show.setText(residencedoorno_txt1.getText().toString());
            liveincurrentres.setText(liveincurrent_txt.getText().toString());
            perrestype.setText(permanentresvalue);
            perrespinodeshow.setText(per_residencepincode.getText().toString());
            rentpaidshow.setText(rentpaid_txt.getText().toString());

        }




        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //rule_message.clear();

                if (loan_type_id.equals("20")) {

                    Pan_btn.setVisibility(View.GONE);
                    salried_self_btn.setVisibility(View.GONE);
                    residence_btn.setVisibility(View.GONE);
                    creditbankbtn.setVisibility(View.VISIBLE);



                    property_ly.setVisibility(View.GONE);
                    pan_ly.setVisibility(View.GONE);
                    res_ly.setVisibility(View.GONE);

                    pan_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                    salaried_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                    res_scroll1_img.setBackgroundResource(R.drawable.capsul_button_icon1);
                    creditbank_scroll_img.setBackgroundResource(R.drawable.capsul_button_icon);
                    //  residence_layout.setVisibility(View.GONE);
                    salaried_ly.setVisibility(View.GONE);
                    self_ly.setVisibility(View.GONE);
                    creditbanklay.setVisibility(View.VISIBLE);


                    avgbanklay.setVisibility(View.VISIBLE);
                    bankturnoverlay.setVisibility(View.VISIBLE);
                    accounttypelay.setVisibility(View.VISIBLE);
                    majortranslay.setVisibility(View.VISIBLE);



                }else{
                    Pan_btn.setVisibility(View.GONE);
                    salried_self_btn.setVisibility(View.GONE);
                    residence_btn.setVisibility(View.GONE);
                    creditbankbtn.setVisibility(View.VISIBLE);


                    property_ly.setVisibility(View.GONE);
                    pan_ly.setVisibility(View.GONE);
                    res_ly.setVisibility(View.GONE);

                    pan_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                    salaried_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                    res_scroll1_img.setBackgroundResource(R.drawable.capsul_button_icon1);
                    creditbank_scroll_img.setBackgroundResource(R.drawable.capsul_button_icon);

                    //  residence_layout.setVisibility(View.GONE);
                    salaried_ly.setVisibility(View.GONE);
                    self_ly.setVisibility(View.GONE);
                    creditbanklay.setVisibility(View.VISIBLE);

                    avgbanklay.setVisibility(View.GONE);
                    bankturnoverlay.setVisibility(View.GONE);
                    accounttypelay.setVisibility(View.GONE);
                    majortranslay.setVisibility(View.GONE);


                }
               // viability_Applicant();

            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        if (!dialog.isShowing()) {
            dialog.show();
        }

    }



    private void Submit_Creditbankdetails1(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.creditbanklaysconform);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn=(Button)dialog.findViewById(R.id.submitbtn);

        AppCompatTextView residence_pincode_show,area_show,residence_ownership_show;

        residence_pincode_show = (AppCompatTextView) dialog.findViewById(R.id.residence_pincode_show);
        area_show = (AppCompatTextView) dialog.findViewById(R.id.area_show);
        residence_ownership_show = (AppCompatTextView) dialog.findViewById(R.id.residence_ownership_show);


        residence_pincode_show.setText(residence_pincode1_);
        area_show.setText(residence_area_res1);
        residence_ownership_show.setText(spinner_residence_type_res_value);



        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                rule_message.clear();

             /*   if (loan_type_id.equals("20") || loan_type_id.equals("21")) {

                    Pan_btn.setVisibility(View.GONE);
                    salried_self_btn.setVisibility(View.GONE);
                    residence_btn.setVisibility(View.GONE);
                    creditbankbtn.setVisibility(View.VISIBLE);


                    property_ly.setVisibility(View.GONE);
                    pan_ly.setVisibility(View.GONE);
                    res_ly.setVisibility(View.GONE);

                    pan_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                    salaried_scroll.setBackgroundResource(R.drawable.capsul_button_icon1);
                    res_scroll1_img.setBackgroundResource(R.drawable.capsul_button_icon1);
                    creditbank_scroll_img.setBackgroundResource(R.drawable.capsul_button_icon);
                    //  residence_layout.setVisibility(View.GONE);
                    salaried_ly.setVisibility(View.GONE);
                    self_ly.setVisibility(View.GONE);
                    creditbanklay.setVisibility(View.VISIBLE);

                }*/
                 viability_Applicant();

            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        if (!dialog.isShowing()) {
            dialog.show();
        }

    }



    private boolean validate_DOB(){
        if (date_of_birt_txt.getText().toString().isEmpty()) {
            date_of_birt_txt.setError(getText(R.string.error_rise));
            date_of_birt_txt.requestFocus();
            return false;
        } else {
            date_of_birt_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_Monthly_net_salary(){
        if (monthly_net_sal_edit_txt.getText().toString().isEmpty()) {
            monthly_net_sal_edit_txt.setError(getText(R.string.error_rise));
            monthly_net_sal_edit_txt.requestFocus();
            return false;
        } else {
            monthly_net_sal_edit_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_Fathers_Name(){
        String name = Fathers_Name.getText().toString();
        if (Fathers_Name.getText().toString().trim().isEmpty() || Fathers_Name.length() < 3 || !(Pattern.matches("^[\\p{L} .'-]+$", Fathers_Name.getText()))) {
            Fathers_Name.setError(getText(R.string.vali_name));
            Fathers_Name.requestFocus();
            return false;
        } else {
        }
        return true;
    }

    private boolean validate_noofdependents(){
        String name = noof_dependenttxt.getText().toString();
        if (noof_dependenttxt.getText().toString().trim().isEmpty()) {
            noof_dependenttxt.setError(getText(R.string.vali_names));
            noof_dependenttxt.requestFocus();
            return false;
        } else {
        }
        return true;
    }

    private boolean validate_avg_monthly_incom(){
        if (avg_monthly_incom_edit_txt.getText().toString().isEmpty()) {
            avg_monthly_incom_edit_txt.setError(getText(R.string.error_rise));
            avg_monthly_incom_edit_txt.requestFocus();
            return false;
        } else {
            avg_monthly_incom_edit_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_annualprofit(){
        if (annualprofittxt.getText().toString().isEmpty()) {
            annualprofittxt.setError(getText(R.string.error_rise));
            annualprofittxt.requestFocus();
            return false;
        } else {
            annualprofittxt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_annualturnover(){
        if (annualturntxt.getText().toString().isEmpty()) {
            annualturntxt.setError(getText(R.string.error_rise));
            annualturntxt.requestFocus();
            return false;
        } else {
            annualturntxt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_about_business(){
        if (aboutbusinesstxt.getText().toString().isEmpty()) {
            aboutbusinesstxt.setError(getText(R.string.error_rise));
            aboutbusinesstxt.requestFocus();
            return false;
        } else {
            aboutbusinesstxt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean No_of_years_work(){
        if (no_of_years_work_ind_edit_txt.getText().toString().isEmpty()) {
            no_of_years_work_ind_edit_txt.setError(getText(R.string.error_rise));
            no_of_years_work_ind_edit_txt.requestFocus();
            return false;
        } else {
            no_of_years_work_ind_edit_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_office_residence_pincode(){
        if (office_residence_pincode_edite_txt.getText().toString().isEmpty()) {
            office_residence_pincode_edite_txt.setError(getText(R.string.error_rise));
            office_residence_pincode_edite_txt.requestFocus();
            return false;
        } else {
            office_residence_pincode_edite_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_company_name_edit_text(){
        if (company_name_edit_text.getText().toString().isEmpty()) {
            company_name_edit_text.setError(getText(R.string.error_rise));
            company_name_edit_text.requestFocus();
            return false;
        } else {
            company_name_edit_text.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_officialmailid(){
        if (officialmailidtxt.getText().toString().isEmpty()) {
            officialmailidtxt.setError(getText(R.string.error_rise));
            officialmailidtxt.requestFocus();
            return false;
        } else {
            officialmailidtxt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validate_designation_edit_text(){
        if (designation_edit_text.getText().toString().isEmpty()) {
            designation_edit_text.setError(getText(R.string.error_rise));
            designation_edit_text.requestFocus();
            return false;
        } else {
            designation_edit_text.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_doornumber_edit_text(){
        if (doorstreet_txt.getText().toString().isEmpty()) {
            doorstreet_txt.setError(getText(R.string.error_rise));
            doorstreet_txt.requestFocus();
            return false;
        } else {
            doorstreet_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_resdoornumber_edit_text(){
        if (residencedoorno_txt1.getText().toString().isEmpty()) {
            residencedoorno_txt1.setError(getText(R.string.error_rise));
            residencedoorno_txt1.requestFocus();
            return false;
        } else {
            residencedoorno_txt1.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }




    private boolean validate_experience_in_current_cmpy(){
        if (experience_in_current_cmpy.getText().toString().isEmpty()) {
            experience_in_current_cmpy.setError(getText(R.string.error_rise));
            experience_in_current_cmpy.requestFocus();
            return false;
        } else {
            experience_in_current_cmpy.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validate_total_experience_edit_txt(){
        if (total_experience_edit_txt.getText().toString().isEmpty()) {
            total_experience_edit_txt.setError(getText(R.string.error_rise));
            total_experience_edit_txt.requestFocus();
            return false;
        } else {
            total_experience_edit_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_company_pincode_txt(){
        if (company_pincode_txt.getText().toString().isEmpty() || company_pincode_txt.length() <6) {
            company_pincode_txt.setError(getText(R.string.error_rise));
            company_pincode_txt.requestFocus();
            return false;
        } else {
            company_pincode_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_residence_pincode1_edit_txt(){
        if (residence_pincode1_edit_txt_resi.getText().toString().isEmpty()|| residence_pincode1_edit_txt_resi.length() <6) {
            residence_pincode1_edit_txt_resi.setError(getText(R.string.error_rise));
            residence_pincode1_edit_txt_resi.requestFocus();
            return false;
        } else {
            residence_pincode1_edit_txt_resi.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_perresidence_pincode1_edit_txt(){
        if (per_residencepincode.getText().toString().isEmpty()|| per_residencepincode.length() <6) {
            per_residencepincode.setError(getText(R.string.error_rise));
            per_residencepincode.requestFocus();
            return false;
        } else {
            per_residencepincode.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validate_liveincurrent_txt(){
        if (liveincurrent_txt.getText().toString().isEmpty()) {
            liveincurrent_txt.setError(getText(R.string.error_rise));
            liveincurrent_txt.requestFocus();
            return false;
        } else {
            liveincurrent_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validate_residence_address_edit_txt(){
        if (residencedoorno_txt.getText().toString().isEmpty()) {
            residencedoorno_txt.setError(getText(R.string.error_rise));
            residencedoorno_txt.requestFocus();
            return false;
        } else {
            residencedoorno_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_rentpaid_edit_txt(){
        if (rentpaid_txt.getText().toString().isEmpty()) {
            rentpaid_txt.setError(getText(R.string.error_rise));
            rentpaid_txt.requestFocus();
            return false;
        } else {
            rentpaid_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean avgbankbalance(){
        if (avgbankbaltxt.getText().toString().isEmpty()) {
            avgbankbaltxt.setError(getText(R.string.error_rise));
            avgbankbaltxt.requestFocus();
            return false;
        } else {
            avgbankbaltxt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean bankturnover(){
        if (bankturnovertxt.getText().toString().isEmpty()) {
            bankturnovertxt.setError(getText(R.string.error_rise));
            bankturnovertxt.requestFocus();
            return false;
        } else {
            bankturnovertxt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_other_income_amt_txt_edit_txt(){
        if (other_income_amt_txt_edit_txt.getText().toString().isEmpty()) {
            other_income_amt_txt_edit_txt.setError(getText(R.string.error_rise));
            other_income_amt_txt_edit_txt.requestFocus();
            return false;
        } else {
            other_income_amt_txt_edit_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validate_other_income_amt_txt_edit_txt_self(){
        if (other_income_amt_txt_edit_txt_self.getText().toString().isEmpty()) {
            other_income_amt_txt_edit_txt_self.setError(getText(R.string.error_rise));
            other_income_amt_txt_edit_txt_self.requestFocus();
            return false;
        } else {
            other_income_amt_txt_edit_txt_self.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    /////////////////////////////////////


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

                         Log.e("respose Dreopdown", object.toString());
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

                            Company_type =object.getJSONArray("Company_type");
                            Office_residence =object.getJSONArray("Office_residence");
                            property_category =object.getJSONArray("property_category");
                            land_approval =object.getJSONArray("land_approval");
                            building_approval =object.getJSONArray("building_approval");
                            DA_approval =object.getJSONArray("DA_approval");
                            Marital_Status =object.getJSONArray("Marital_Status");
                            gender_status =object.getJSONArray("gender");
                            epf_array =object.getJSONArray("EPF_deducted");
                            typeofemployarray =object.getJSONArray("Type_employee");
                            assetsown=object.getJSONArray("Assets_own");
                            educational_status=object.getJSONArray("Education Qualification");
                            permanentres_array=object.getJSONArray("permanent_adddress");
                            currentres_array=object.getJSONArray("Current_Residence");
                            addr_proof_rent=object.getJSONArray("addr_proof_rent");
                            creditscore_array=object.getJSONArray("entered_credit_score");
                            emilate_array=object.getJSONArray("emi_late");
                            haveaddress_array=object.getJSONArray("ofc_add_proof");
                            fileitr_array=object.getJSONArray("is_itr_file");
                            bankactype_array=object.getJSONArray("bankstacc_type");
                            majortrans_array=object.getJSONArray("major_transaction");
                            haveotherincome_array =object.getJSONArray("is_other_eranings");


                            Business_income_proof_ar =object.getJSONArray("Business_income_proof");
                            Business_Proof =object.getJSONArray("Business_Proof");

                            Property_Type =object.getJSONArray("Property_Type");
                            Property_title =object.getJSONArray("Property_title");
                            property_identified =object.getJSONArray("property_identified");
                            Type_of_self_employement  =object.getJSONArray("Type_of_self_employement");
                            Property_Identified_Spinner(property_identified);
                            Property_Title_Spinner(Property_title);

                            Property_Type(Property_Type);

                            Other_income =object.getJSONArray("Other_income");


                            Other_income(haveotherincome_array);
                            Other_income_self(haveotherincome_array);

                            ////SelfEmployes
                            Type_of_employement =object.getJSONArray("Employement");
                            employmentproof_array =object.getJSONArray("employment_proof");
                            EmploymentProof(employmentproof_array);


                            Type_of_Employeement(Type_of_employement);
                            Type_of_Employeement_self_employment(Type_of_self_employement);

                            Marital_Statues(Marital_Status);
                            Gender_Statues(gender_status);
                            Educationalstatus(educational_status);
                            EPFstatus(epf_array);
                            Typeofemployee(typeofemployarray);
                            PermenentresType(permanentres_array);
                        /*    CurrentresAddress(currentres_array);
                            Current_RentAddress(addr_proof_rent);*/
                            Creditscore(creditscore_array);
                            Emilate(emilate_array);
                            HaveAddressproof(haveaddress_array);
                            Fileitr(fileitr_array);
                            BankActype(bankactype_array);
                            MajorTranscation(majortrans_array);


                            Salry_method_Spinner(Salary_method_ar);
                          //  Salry_Proof(Salary_proof_ar);
                            Comapany_type(Company_type);
                            Multiselect_Companyproof(Salary_proof_ar);
                            Multiselect_Assetowned(assetsown);
                            office_shop =object.getJSONArray("office_shop");
                            pl_co_self_Office_Shop_(office_shop);
                            pl_co_self_Office_own_Rent(Office_residence);

                            have_pan_ar_self =object.getJSONArray("have_pan");
                            vocaton_ar =object.getJSONArray("vocaton");
                            Business_income_proof_ar =object.getJSONArray("Business_income_proof");
                            Residence_ownership_ar_self =object.getJSONArray("Residence_ownership");
                            pl_own_Rent(Residence_ownership_ar_self);
                            vocation_type_forming_ar =object.getJSONArray("vocation_type");


                            Business_type_own_business =object.getJSONArray("Business_income_proof");
                            Business_Proof =object.getJSONArray("Business_Proof");



                            Business_proof(Business_type_own_business);
                            Business_vintage_proof(Business_Proof);
                            Assets_own =object.getJSONArray("Assets_own");

                            vehicle_Type =object.getJSONArray("vehicle_Type");
                            crop_type =object.getJSONArray("crop_type");
                            sell_milk =object.getJSONArray("sell_milk");

                            franchise =object.getJSONArray("franchise");

                            Log.e("Business_type_own_busin",String.valueOf(Business_type_own_business));
                            Log.e("Business_Proof",String.valueOf(Business_Proof));
                            Log.e("Type_of_self",String.valueOf(Type_of_self_employement));

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
    private void CO_Applicant()
    {
        co_applicant_ly.setVisibility(View.VISIBLE);
        applicant_ly.setVisibility(View.GONE);
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

                        if(Property_Identified_ID.equals("0") ||Property_Identified_ID.equals("1") )
                        {
                            prop_details_identified.setVisibility(View.VISIBLE);
                        }else
                        {
                            prop_details_identified.setVisibility(View.GONE);
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
            employment_type.setAdapter(Employee_Type_adapter);
            employment_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Employee_type_Id = Type_of_employement_ar.getJSONObject(position).getString("id");
                        Employee_type_Value = Type_of_employement_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                       /* SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("Employement_type","Employee_type_Id");
                        editor.apply();

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                        String name = preferences.getString("Name", "");

                        */

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            employment_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Type_of_Employeement_self_employment(final JSONArray Type_of_employement_ar) throws JSONException {
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
                        self_Employee_type_Id = Type_of_employement_ar.getJSONObject(position).getString("id");
                        self_Employee_type_Value = Type_of_employement_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");



                        makeJsonObjReq1_vocation(self_Employee_type_Id);


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

            maritial_status.setAdapter(Marital_Statues_Adapter);
            maritial_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        maritial_status_id = Marital_Statues_ar.getJSONObject(position).getString("id");
                        maritial_status_Value = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            maritial_status.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }


    private void Educationalstatus(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            educational_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            educational_adapter.setDropDownViewResource(R.layout.view_spinner_item);

            educational_qualificationspinner.setAdapter(educational_adapter);
            educational_qualificationspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        educationalstatusid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        educationalstatusvalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            educational_qualificationspinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }


    private void EPFstatus(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            epf_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            epf_adapter.setDropDownViewResource(R.layout.view_spinner_item);

            epf_spinner.setAdapter(epf_adapter);
            epf_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        epfstatusid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        epfstatusvalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

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


    private void Typeofemployee(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            typeofemployeadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            typeofemployeadapter.setDropDownViewResource(R.layout.view_spinner_item);

            typeofemploye_spinner.setAdapter(typeofemployeadapter);
            typeofemploye_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        typeofemployeid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        typeofemployevalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            typeofemploye_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }



















    private void Emilate(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            emilateadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            emilateadapter.setDropDownViewResource(R.layout.view_spinner_item);

            emilatespinner.setAdapter(emilateadapter);
            emilatespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        emilateid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        emilatevalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            emilatespinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }



    private void HaveAddressproof(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            haveaddres_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            haveaddres_adapter.setDropDownViewResource(R.layout.view_spinner_item);

            haveaddressproof_spinner.setAdapter(haveaddres_adapter);
            haveaddressproof_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        haveaddressid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        haveaddressvalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            haveaddressproof_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }



    private void Fileitr(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            fileitr_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            fileitr_adapter.setDropDownViewResource(R.layout.view_spinner_item);

            fileitr_spinner.setAdapter(fileitr_adapter);
            fileitr_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        fileitrid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        fileitrvalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            fileitr_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }












    private void Banklist(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            banklistadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            banklistadapter.setDropDownViewResource(R.layout.view_spinner_item);

            banksalarycreditdropdown.setAdapter(banklistadapter);
            banksalarycreditdropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        banklistid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        banklistvalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            banksalarycreditdropdown.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void BankActype(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            bankactypeadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            bankactypeadapter.setDropDownViewResource(R.layout.view_spinner_item);

            accounttype_spinner.setAdapter(bankactypeadapter);
            accounttype_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        bankactypeid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        bankactypevalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            accounttype_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }


    private void MajorTranscation(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            majortrans_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            majortrans_adapter.setDropDownViewResource(R.layout.view_spinner_item);

            majortrans_spinner.setAdapter(majortrans_adapter);
            majortrans_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        majortransid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        majortransvalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            majortrans_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }
















    private void Creditscore(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            creditscoreadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            creditscoreadapter.setDropDownViewResource(R.layout.view_spinner_item);

            creditscorespinner.setAdapter(creditscoreadapter);
            creditscorespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        creditscoreid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        creditscorevalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.e("creditscorevalue",creditscorevalue);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            creditscorespinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }



    private void CurrentresAddress(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            currentrestypeadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            currentrestypeadapter.setDropDownViewResource(R.layout.view_spinner_item);

            current_resspinner.setAdapter(currentrestypeadapter);
            current_resspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        currentresid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        currentresvalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            current_resspinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }


    private void Current_RentAddress(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            currentrestypeadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            currentrestypeadapter.setDropDownViewResource(R.layout.view_spinner_item);

            current_resspinner.setAdapter(currentrestypeadapter);
            current_resspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        currentresid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        currentresvalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            current_resspinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }





    private void PermenentresType(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            permanentrestypeadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            permanentrestypeadapter.setDropDownViewResource(R.layout.view_spinner_item);

            permanentres_spinner.setAdapter(permanentrestypeadapter);
            permanentres_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        permanentresid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        permanentresvalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            permanentres_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }



    private void Gender_Statues(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            Gender_statusadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Gender_statusadapter.setDropDownViewResource(R.layout.view_spinner_item);

            gender_spinner.setAdapter(Gender_statusadapter);
            gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        genderstatus_id = Marital_Statues_ar.getJSONObject(position).getString("id");
                        genderstatus_value  = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            gender_spinner.setOnTouchListener(new View.OnTouchListener() {
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


            spinner_company_type.setAdapter(Company_type_Adapter);
            spinner_company_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            spinner_company_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

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
            office_spinner_residence_type.setAdapter(Office_Shop_own_SA_Adapter);
           /*
            spinner_office_shop_setup_far.setAdapter(Office_Shop__Adapter);*/

            office_spinner_residence_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            office_spinner_residence_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

        }

    }

    private void pl_own_Rent(final JSONArray office_shop_ar) throws JSONException {
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
            spinner_residence_type_res.setAdapter(Office_Shop_own_SA_Adapter);
           /*
            spinner_office_shop_setup_far.setAdapter(Office_Shop__Adapter);*/

            spinner_residence_type_res.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        spinner_residence_type_res_id = office_shop_ar.getJSONObject(position).getString("id");
                        spinner_residence_type_res_value = office_shop_ar.getJSONObject(position).getString("value");
                        Log.i("TAG", "spinner_residence_type_res_id: "+spinner_residence_type_res_id);

                        if(spinner_residence_type_res_id.equals("3")) {
                            rentpaidlay.setVisibility(View.VISIBLE);
                            per_respincodelay.setVisibility(View.VISIBLE);
                            permanentresidencetypelay.setVisibility(View.VISIBLE);
                            liveinresidence.setVisibility(View.VISIBLE);
                            Current_RentAddress(addr_proof_rent);
                        }else if(spinner_residence_type_res_id.equals("4")){
                            rentpaidlay.setVisibility(View.VISIBLE);
                            per_respincodelay.setVisibility(View.VISIBLE);
                            permanentresidencetypelay.setVisibility(View.VISIBLE);
                            liveinresidence.setVisibility(View.VISIBLE);
                            Current_RentAddress(addr_proof_rent);
                        }else if(spinner_residence_type_res_id.equals("5")){
                            rentpaidlay.setVisibility(View.VISIBLE);
                            per_respincodelay.setVisibility(View.VISIBLE);
                            permanentresidencetypelay.setVisibility(View.VISIBLE);
                            liveinresidence.setVisibility(View.VISIBLE);
                            Current_RentAddress(addr_proof_rent);
                        }else if(spinner_residence_type_res_id.equals("6")){
                            rentpaidlay.setVisibility(View.VISIBLE);
                            per_respincodelay.setVisibility(View.VISIBLE);
                            permanentresidencetypelay.setVisibility(View.VISIBLE);
                            liveinresidence.setVisibility(View.VISIBLE);
                            Current_RentAddress(addr_proof_rent);
                        }else if(spinner_residence_type_res_id.equals("1")){
                            rentpaidlay.setVisibility(View.GONE);
                            per_respincodelay.setVisibility(View.GONE);
                            permanentresidencetypelay.setVisibility(View.GONE);
                            liveinresidence.setVisibility(View.GONE);
                            CurrentresAddress(currentres_array);

                        }else if(spinner_residence_type_res_id.equals("2")){
                            rentpaidlay.setVisibility(View.GONE);
                            per_respincodelay.setVisibility(View.GONE);
                            permanentresidencetypelay.setVisibility(View.GONE);
                            liveinresidence.setVisibility(View.GONE);
                            CurrentresAddress(currentres_array);

                        }
                        else{
                            rentpaidlay.setVisibility(View.GONE);
                            per_respincodelay.setVisibility(View.GONE);
                            permanentresidencetypelay.setVisibility(View.GONE);
                            liveinresidence.setVisibility(View.GONE);
                            CurrentresAddress(currentres_array);

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

    private void Multiselect_Companyproof(final JSONArray jsonArray)throws JSONException{


        TYPE = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject J = jsonArray.getJSONObject(i);
            TYPE[i] = J.getString("value");
            final List<String> Profile_List = new ArrayList<>(Arrays.asList(TYPE));
            companyproof_multiAdapter = new ArrayAdapter<String>(Viability_Screen_revamp_Pl_BL.this, R.layout.view_spinner_item, Profile_List) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    // font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    //  v.setTypeface(font);
                    return v;
                }
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    //   v.setTypeface(font);
                    return v;
                }
            };
            spinner_salary_proof.setAdapter(companyproof_multiAdapter, false, onSelectedListener);
        }
    }

    private void Multiselect_Assetowned(final JSONArray jsonArray)throws JSONException{
        TYPE = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject J = jsonArray.getJSONObject(i);
            TYPE[i] = J.getString("value");
            final List<String> Profile_List = new ArrayList<>(Arrays.asList(TYPE));
            assetowned_multiselect = new ArrayAdapter<String>(Viability_Screen_revamp_Pl_BL.this, R.layout.view_spinner_item, Profile_List) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    // font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    //  v.setTypeface(font);
                    return v;
                }
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    //   v.setTypeface(font);
                    return v;
                }
            };
            assetsowned.setAdapter(assetowned_multiselect, false, onSelectedListenerassetowned);
        }
    }

    private void Business_proof(final JSONArray jsonArray)throws JSONException{


        Business_TYPE = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject J = jsonArray.getJSONObject(i);
            Business_TYPE[i] = J.getString("value");
            final List<String> Profile_List = new ArrayList<>(Arrays.asList(Business_TYPE));
            Business_Proof_type_adapter = new ArrayAdapter<String>(Viability_Screen_revamp_Pl_BL.this, R.layout.view_spinner_item, Profile_List) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    // font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    //  v.setTypeface(font);
                    return v;
                }
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    //   v.setTypeface(font);
                    return v;
                }
            };
            BL_self_bussiness_proof.setAdapter(Business_Proof_type_adapter, false, onSelectedListener1);
        }
    }
    private void Business_vintage_proof(final JSONArray jsonArray)throws JSONException{

        Business_vintage_p = new String[jsonArray.length()];
        Business_vintage_p_id = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject J = jsonArray.getJSONObject(i);
            Business_vintage_p[i] = J.getString("value");
            Business_vintage_p_id[i] = J.getString("id");
            final List<String> business_vintage_id = new ArrayList<>(Arrays.asList(Business_vintage_p));
            final List<String> Profile_List = new ArrayList<>(Arrays.asList(Business_vintage_p));
            Business_vintage_Adapter = new ArrayAdapter<String>(Viability_Screen_revamp_Pl_BL.this, R.layout.view_spinner_item, Profile_List) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    // font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    //  v.setTypeface(font);
                    return v;
                }
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    //   v.setTypeface(font);
                    return v;
                }
            };
            BL_self_bus_vintage_proof.setAdapter(Business_vintage_Adapter, false, onSelectedListener12);
        }
    }

   /* private void Salry_Proof(final JSONArray ja) throws JSONException {

        Salary_income_Proof = new ArrayList<IncomeProofPOJO>();
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO salary_proof = new IncomeProofPOJO(id,locality,false);
            Salary_income_Proof.add(salary_proof);
        }
        dataAdapter_Salaried_proof = new Viability_check_HL.MyCustomAdapter_Salary_Proof(context, 0,Salary_income_Proof);
        spinner_salary_proof.setAdapter(dataAdapter_Salaried_proof);
        dataAdapter_Salaried_proof.notifyDataSetChanged();

    }*/

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
            spinner_office_shop_setup_ind.setAdapter(Office_Shop__Adapter);
           /*
            spinner_office_shop_setup_far.setAdapter(Office_Shop__Adapter);*/

            spinner_office_shop_setup_ind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        pl_co_app_ind_Office_Shop__id = office_shop_ar.getJSONObject(position).getString("id");
                        pl_co_app_ind_Office_Shop__value = office_shop_ar.getJSONObject(position).getString("value");

                        if(pl_co_app_ind_Office_Shop__id.equals("1") || pl_co_app_ind_Office_Shop__id.equals("3"))
                        {
                            ofiice_res_details.setVisibility(View.GONE);
                            BL_self_office_ownership_type_ly.setVisibility(View.GONE);
                        }else
                        {
                            ofiice_res_details.setVisibility(View.VISIBLE);
                            BL_self_office_ownership_type_ly.setVisibility(View.VISIBLE);
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_office_shop_setup_ind.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

        }

    }

    private void GET_Pincode1(String code) {
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
                                 Log.e("Pincode", String.valueOf(response));

                                 if(response.length()>0)
                                 {
                                     setArea(response);
                                 }else
                                 {
                                     company_pincode_txt.setFocusable(true);
                                     company_pincode_txt.setEnabled(true);
                                 }

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
    private void setArea(final JSONArray ja) throws JSONException {

        Area1 = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            Area1[i] = J.getString("area");
            final List<String> area_list = new ArrayList<>(Arrays.asList(Area1));
            A_Area1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.view_spinner_item, area_list){
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
                A_Area1.setDropDownViewResource(R.layout.view_spinner_item);
                spinn_area.setAdapter(A_Area1);
                spinn_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        try {

                            //   work_pincode_area = ja.getJSONObject(position).getString("id");
                            company_area = ja.getJSONObject(position).getString("id");
                            company_area1 = ja.getJSONObject(position).getString("area");
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

    private void GET_AERA_POST_res(String code) {
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
                                if(response.length()>0)
                                {
                                    setArea_resi(response);
                                }else
                                {
                                    office_residence_pincode_edite_txt.setEnabled(true);
                                    office_residence_pincode_edite_txt.setFocusable(true);
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
    private void Other_income(final JSONArray Company_type_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Vocation_SA = new String[Company_type_ar.length()];
        for (int i=0;i<Company_type_ar.length();i++){
            JSONObject J =  Company_type_ar.getJSONObject(i);
            Vocation_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Vocation_SA));
            Vocation_SA_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Vocation_SA_Adapter.setDropDownViewResource(R.layout.view_spinner_item);


            other_income_source_salaried.setAdapter(Vocation_SA_Adapter);
            other_income_source_salaried.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Other_income_id = Company_type_ar.getJSONObject(position).getString("id");
                        Other_income_Value = Company_type_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        if( Other_income_id.equals("2"))
                        {
                            other_income_amt_ly_.setVisibility(View.GONE);
                        }else
                        {
                            other_income_amt_ly_.setVisibility(View.VISIBLE);


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            other_income_source_salaried.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

    }

    private void Other_income_self(final JSONArray Company_type_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Vocation_SA = new String[Company_type_ar.length()];
        for (int i=0;i<Company_type_ar.length();i++){
            JSONObject J =  Company_type_ar.getJSONObject(i);
            Vocation_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Vocation_SA));
            Vocation_SA_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Vocation_SA_Adapter.setDropDownViewResource(R.layout.view_spinner_item);


            other_income_source_self.setAdapter(Vocation_SA_Adapter);
            other_income_source_self.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Other_income_id_self = Company_type_ar.getJSONObject(position).getString("id");
                        Other_income_Value_self = Company_type_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                       if( Other_income_id_self.equals("2"))
                        {
                            other_income_amt_ly_self.setVisibility(View.GONE);
                        }else
                       {
                           other_income_amt_ly_self.setVisibility(View.VISIBLE);
                       }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            other_income_source_self.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

        }

    }

    private void setArea_resi(final JSONArray ja) throws JSONException {

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
                            residence_area_office = ja.getJSONObject(position).getString("id");
                            residence_area_office1 = ja.getJSONObject(position).getString("area");
                            residence_area_district_id_office = ja.getJSONObject(position).getString("district_id");
                            residence_state_id_office = ja.getJSONObject(position).getString("state_id");

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

    private void GET_AERA_POST_res1(String code) {
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

                                if(response.length()>0)
                                {
                                    setArea_resi1(response);
                                }else
                                {
                                    residence_pincode1_edit_txt_resi.setEnabled(true);
                                    residence_pincode1_edit_txt_resi.setFocusable(true);
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

    private void setArea_resi1(final JSONArray ja) throws JSONException {

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

            String workpincode = residence_pincode1_edit_txt_resi.getText().toString();
            //  String workpincode1 = residence_pincode1_edit_txt.getText().toString();

            if(workpincode.length()> 2){
                A_Area.setDropDownViewResource(R.layout.view_spinner_item);
                res_spinn_area.setAdapter(A_Area);
                res_spinn_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        try {

                            //   work_pincode_area = ja.getJSONObject(position).getString("id");
                            residence_area_res = ja.getJSONObject(position).getString("id");
                            residence_area_res1 = ja.getJSONObject(position).getString("area");
                            residence_area_district_id_res = ja.getJSONObject(position).getString("district_id");
                            residence_state_id_res = ja.getJSONObject(position).getString("state_id");

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

    }

    public void show() {
        final Dialog d = new Dialog(Viability_Screen_revamp_Pl_BL.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog_view);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker2);
        np.setMaxValue(12);
        np.setMinValue(0);
        np.setValue(0);
        np1.setMaxValue(30);
        np1.setMinValue(0);
        np1.setValue(0);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        np1.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String  exp_year = String.valueOf(np1.getValue());
              String  exp_month = String.valueOf(np.getValue());
              String a= "Year";
              String a1 = "Months";
                experience_in_current_cmpy.setText(exp_year + " " +a +","+ exp_month +" "+ a1);

                int e_year = Integer.parseInt(exp_year);
                int e_month = Integer.parseInt(exp_month);

                ecperience = e_year * 12 + e_month;
                Log.e("exp_year,exp_month", String.valueOf(ecperience));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
               // experience_in_current_cmpy.setText(String.valueOf(np1.getValue()) + "  " + "Year" + " , " + String.valueOf(np.getValue()) + "  " + "Months");
            }
        });
        d.show();
    }
    public void show1() {
        final Dialog d = new Dialog(Viability_Screen_revamp_Pl_BL.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog_view);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker2);
        np.setMaxValue(12);
        np.setMinValue(0);
        np.setValue(0);
        np1.setMaxValue(30);
        np1.setMinValue(0);
        np1.setValue(0);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        np1.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  exp_year = String.valueOf(np1.getValue());
                String  exp_month = String.valueOf(np.getValue());
                String a= "Year";
                String a1 = "Months";
                total_experience_edit_txt.setText(exp_year + " " +a +","+ exp_month +" "+ a1);


                int e_year = Integer.parseInt(exp_year);
                int e_month = Integer.parseInt(exp_month);

                total_experiance = e_year * 12 + e_month;

                Log.e("exp_year,exp_month", String.valueOf(total_experiance));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                // experience_in_current_cmpy.setText(String.valueOf(np1.getValue()) + "  " + "Year" + " , " + String.valueOf(np.getValue()) + "  " + "Months");
            }
        });
        d.show();
    }
    public void show2() {
        final Dialog d = new Dialog(Viability_Screen_revamp_Pl_BL.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog_view);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        final NumberPicker np1 = (NumberPicker) d.findViewById(R.id.numberPicker2);
        np.setMaxValue(12);
        np.setMinValue(0);
        np.setValue(0);
        np1.setMaxValue(30);
        np1.setMinValue(0);
        np1.setValue(0);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        np1.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  exp_year = String.valueOf(np1.getValue());
                String  exp_month = String.valueOf(np.getValue());
                String a= "Year";
                String a1 = "Months";
                no_of_years_work_ind_edit_txt.setText(exp_year + " " +a +","+ exp_month +" "+ a1);
                int e_year = Integer.parseInt(exp_year);
                int e_month = Integer.parseInt(exp_month);

                no_of_month_in_business = e_year * 12 + e_month;


                Log.e("exp_year,exp_month", String.valueOf(no_of_month_in_business));
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
                // experience_in_current_cmpy.setText(String.valueOf(np1.getValue()) + "  " + "Year" + " , " + String.valueOf(np.getValue()) + "  " + "Months");
            }
        });
        d.show();
    }
    private void makeJsonObjReq1_vocation(String id) {
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("employement_type",id);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        Log.e("state_id", String.valueOf(J));

        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.get_selfemployvocation, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {

                        progressDialog.dismiss();

                        try {
                            JSONArray jsonArray = object.getJSONArray("Type_of_employement");
                            vocation_type(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("respose Dreopdown", object.toString());

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

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        //  work_experience.setText("Selected Number : " + i1);
    }


    private void vocation_type(final JSONArray Company_type_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Vocation_SA = new String[Company_type_ar.length()];
        for (int i=0;i<Company_type_ar.length();i++){
            JSONObject J =  Company_type_ar.getJSONObject(i);
            Vocation_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Vocation_SA));
            Vocation_SA_Adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Vocation_SA_Adapter.setDropDownViewResource(R.layout.view_spinner_item);


            spi_vocation_type_.setAdapter(Vocation_SA_Adapter);
            spi_vocation_type_.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Emp_vocation_type_id = Company_type_ar.getJSONObject(position).getString("id");
                        Emp_vocation_type_Value = Company_type_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

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

    private MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener() {

        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
            Log.e("the value",selected.toString());
            spinner_salary_proof.setSelected(selected);

            StringBuilder builder = new StringBuilder();

            Income_proof_selected = selected;

            String selected_salary_proof;

            for (int i = 0; i <= selected.length-1; i++) {
                if (selected[i]) {
                    //  builder.append(companyproof_multiAdapter.getItem(i)).append(" ");
                    // salry_proof.add(companyproof_multiAdapter.getItem(i));

                    selected_salary_proof = companyproof_multiAdapter.getItem(i);
                    for (int j = 0; j < Salary_proof_ar.length(); j++) {
                        try {
                            JSONObject J = Salary_proof_ar.getJSONObject(i);
                            String salary_proof = J.getString("value");

                            if(selected_salary_proof.equals(salary_proof))
                            {
                                String vintage_proof_id = J.getString("id");
                                String salary_proof1 = J.getString("value");
                                // salry_proof.add(vintage_proof_id);
                                salry_proof.add(vintage_proof_id);
                                salry_proof_value.add(salary_proof1);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            Set<String> listWithoutDuplicates = new LinkedHashSet<String>(salry_proof);
            salry_proof.clear();
            salry_proof.addAll(listWithoutDuplicates);

            Set<String> listWithoutDuplicates1 = new LinkedHashSet<String>(salry_proof_value);
            salry_proof_value.clear();
            salry_proof_value.addAll(listWithoutDuplicates1);
            Log.e("the selected values",salry_proof.toString());
            // Toast.makeText(Viability_Screen_revamp_Pl_BL.this, builder.toString(), Toast.LENGTH_SHORT).show();
            // Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
        }
    };



    private MultiSpinner.MultiSpinnerListener onSelectedListenerassetowned = new MultiSpinner.MultiSpinnerListener() {

        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
            Log.e("the value",selected.toString());
            assetsowned.setSelected(selected);

            StringBuilder builder = new StringBuilder();

            Income_proof_selected = selected;

            String selected_salary_proof;

            for (int i = 0; i <= selected.length-1; i++) {
                if (selected[i]) {
                    //  builder.append(companyproof_multiAdapter.getItem(i)).append(" ");
                    // salry_proof.add(companyproof_multiAdapter.getItem(i));

                    selected_salary_proof = assetowned_multiselect.getItem(i);
                    for (int j = 0; j < assetsown.length(); j++) {
                        try {
                            JSONObject J = assetsown.getJSONObject(i);
                            String salary_proof = J.getString("value");

                            if (selected_salary_proof.equals(salary_proof)) {
                                String vintage_proof_id = J.getString("id");
                                String salary_proof1 = J.getString("value");
                                // salry_proof.add(vintage_proof_id);
                                assetownid.add(vintage_proof_id);
                                assetownvalue.add(salary_proof1);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            Set<String> listWithoutDuplicates = new LinkedHashSet<String>(assetownid);
            assetownid.clear();
            assetownid.addAll(listWithoutDuplicates);

            Set<String> listWithoutDuplicates1 = new LinkedHashSet<String>(assetownvalue);
            assetownvalue.clear();
            assetownvalue.addAll(listWithoutDuplicates1);
            Log.e("the selected valuesid",assetownid.toString());
            Log.e("the selected values",assetownvalue.toString());
            // Toast.makeText(Viability_Screen_revamp_Pl_BL.this, builder.toString(), Toast.LENGTH_SHORT).show();
            // Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
        }
    };





    private MultiSpinner.MultiSpinnerListener onSelectedListener1 = new MultiSpinner.MultiSpinnerListener() {


        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
            BL_self_bussiness_proof.setSelected(selected);
            StringBuilder builder = new StringBuilder();
            String business_proof_1;

            for (int i = 0; i <= selected.length-1; i++) {
               /* if (selected[i]) {
                  //  builder.append(companyproof_multiAdapter.getItem(i)).append(" ");
                    list_income_proof_self.add(Business_Proof_type_adapter.getItem(i));

                }*/
               business_proof_1 = Business_Proof_type_adapter.getItem(i);
                Log.e("the  values1111",business_proof_1);
                if (selected[i]) {
                    for (int j = 0; j < Business_type_own_business.length(); j++) {

                        try {
                            JSONObject J = Business_type_own_business.getJSONObject(j);
                            String business_proof = J.getString("value");

                            if (business_proof_1.equals(business_proof)) {
                                Log.e("the selected values", list_income_proof_self.toString());
                                String vintage_proof_id = J.getString("id");
                                String business_proof1 = J.getString("value");
                                list_income_proof_self.add(vintage_proof_id);
                                list_income_proof_self_value.add(business_proof1);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            Set<String> listWithoutDuplicates = new LinkedHashSet<String>(list_income_proof_self);
            list_income_proof_self.clear();
            list_income_proof_self.addAll(listWithoutDuplicates);

            Set<String> listWithoutDuplicates1 = new LinkedHashSet<String>(list_income_proof_self_value);
            list_income_proof_self_value.clear();
            list_income_proof_self_value.addAll(listWithoutDuplicates1);

            Log.e("the selected values",list_income_proof_self.toString());


            // Toast.makeText(Viability_Screen_revamp_Pl_BL.this, list_income_proof_self.toString(), Toast.LENGTH_SHORT).show();
            // Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
        }
    };
    private MultiSpinner.MultiSpinnerListener onSelectedListener12 = new MultiSpinner.MultiSpinnerListener() {

        public void onItemsSelected(boolean[] selected) {
            // Do something here with the selected items
            BL_self_bus_vintage_proof.setSelected(selected);
            StringBuilder builder = new StringBuilder();
            String Vintage_proof_1;
            for (int i = 0; i <= selected.length -1; i++) {
                /*if (selected[i]) {
                  //  builder.append(Business_vintage_Adapter.getItem(i)).append(" ");
                    list_vintage_proof.add(Business_vintage_Adapter.getItem(i));
                }*/
                if (selected[i]) {
                    Vintage_proof_1 = Business_vintage_Adapter.getItem(i);
                    for (int j = 0; j < Business_Proof.length(); j++) {

                        try {
                            JSONObject J = Business_Proof.getJSONObject(j);
                            String vintage_proof = J.getString("value");

                            if (Vintage_proof_1.equals(vintage_proof)) {
                                String vintage_proof_id = J.getString("id");
                                String vintage_proof_1 = J.getString("value");
                                list_vintage_proof.add(vintage_proof_id);
                                list_vintage_proof_value.add(vintage_proof_1);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            Set<String> listWithoutDuplicates = new LinkedHashSet<String>(list_vintage_proof);
            list_vintage_proof.clear();
            list_vintage_proof.addAll(listWithoutDuplicates);

            Set<String> listWithoutDuplicates1 = new LinkedHashSet<String>(list_vintage_proof_value);
            list_vintage_proof_value.clear();
            list_vintage_proof_value.addAll(listWithoutDuplicates1);

            Log.e("the selected values",list_vintage_proof.toString());

            //Toast.makeText(Viability_Screen_revamp_Pl_BL.this, builder.toString(), Toast.LENGTH_SHORT).show();
            // Toast.makeText(MainActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    private void viability_Applicant( ) {

        JSONObject J= null;
        JSONObject applicant1 = null;

        try {
            applicant1 =new JSONObject();
            applicant1.put("pan_details",Pan_Details);
            applicant1.put("employment_details",Employement_type);
            applicant1.put("residence",Residence);
            applicant1.put("creditdetails",Creditbank_Details);
            if(loan_type_id.equals("20"))
            {
                applicant1.put("emp_statues","3");

            }else if(loan_type_id.equals("21"))
            {
                applicant1.put("emp_statues","1");
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            J =new JSONObject();
            J.put("applicant_count","1");
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("applicant",applicant1);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("viability Applicant ", String.valueOf(J));
         progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.VIABILITY_CHECK_applicant1, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("viability response", String.valueOf(response));
                        String data = String.valueOf(response);
                        try {
                            //  String Status = response.getString("status");
                            JSONObject jsonObject1 = response.getJSONObject("response");
                            if(jsonObject1.getString("applicant_status").equals("success")) {

                                progressDialog.dismiss();
                               // viability_check_pass();
                                viability_check_rule();
                            }else{
                                Toast.makeText(Viability_Screen_revamp_Pl_BL.this,"Something went wrong",Toast.LENGTH_SHORT).show();
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



    private void Eligibility_check_doc_checklist_generate( ) {

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
                              /*  LayoutInflater layoutInflater = (LayoutInflater) Viability_Screen_revamp_Pl_BL.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View customView = layoutInflater.inflate(R.layout.popup_loading,null);
                                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                //display the popup window
                                popupWindow.dismiss();*/
                                Intent intent = new Intent(Viability_Screen_revamp_Pl_BL.this, PaymentActivity.class);
                                startActivity(intent);
                                finish();
                                Pref.putCoAPPAVAILABLE(context,"1");
                            }else if(response.getString("Generate_Document_check_list").equals("error"))
                            {
                                Toast.makeText(context,"Document Checklist Failed",Toast.LENGTH_SHORT).show();
                               /* String viability_array =jsonObject1.getString("eligibility_arr");
                                Intent intent = new Intent(Viability_Screen_revamp_Pl_BL.this, Loan_Viyability_Check_Activity.class);
                                intent.putExtra("viability_jsonArray", viability_array.toString());
                                startActivity(intent);
                                finish();*/
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
    }

    /*private void viability_check_rule() {

        JSONObject J= null;

        try {
            J =new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            J.put("b2b_id", Pref.getID(getApplicationContext()));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("rule rune request ", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.viability_eligibilitycheck, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("rule run response", String.valueOf(response));
                        String data = String.valueOf(response);
                        try {
                            String Status = response.getString("status");
                            JSONObject response_ = response.getJSONObject("response");

                            String eligible_status = response_.getString("eligible_status");
                            if(eligible_status.equals("1"))
                            {
                                progressDialog.dismiss();
                                // viability_check_pass();

                                Eligibility_check_doc_checklist_generate();
                            }else
                            {
                                String viability_status = response_.getString("viability_status");
                                if(viability_status.equals("1"))
                                {
                                    Toast.makeText(context,"Viability Failed",Toast.LENGTH_SHORT).show();
                                    String rule_desc = null;
                                    String age_vale = null;
                                    String ind_salary = null;
                                    String bank_failure = null;
                                    JSONArray jsonArray = response.getJSONArray("rule_desc");
                                    JSONArray jsonArray1 = response.getJSONArray("rule_message");

                                    for (int i=0; i<jsonArray.length();i++) {
                                        try {

                                            JSONObject J = jsonArray.getJSONObject(i);

                                            rule_desc = J.getString("rule_desc");
                                            if(rule_desc.equals("Age"))
                                            {
                                                age_vale="Sorry.! Age Criteria Not Met | You can apply for a loan in the name of - any one of your family members, whose Age is between 20 and 55.";
                                                rule_message.add(age_vale);
                                            }else if(rule_desc.equals("Individual Salary"))
                                            {
                                                ind_salary="Sorry.! Income should not be less than \u20B9 12,000 for applying loan with us.";
                                                rule_message.add(ind_salary);
                                            }else
                                            {
                                                rule_desc = J.getString("fail_message");
                                                bank_failure="Sorry.! Currently we have no partner banks available in applicants location" +" "+
                                                        "We are On-boarding as many new banks as possible. Stay tuned.! ";
                                                rule_message.add(rule_desc);
                                            }

                                            Log.e("rule_desc",rule_desc);

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }

                                    // Toast.makeText(context,"Viability Created Successfully",Toast.LENGTH_SHORT).show();
                                    LayoutInflater layoutInflater = (LayoutInflater) Viability_Screen_revamp_Pl_BL.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View customView = layoutInflater.inflate(R.layout.popup_rul_fail,null);
                                    Button godashboard = (Button) customView.findViewById(R.id.godashboard);
                                    TextView content_txt = (TextView) customView.findViewById(R.id.content_txt);


                                    String list = Arrays.toString(rule_message.toArray()).replace("[", "").replace("]", "");
                                    content_txt.setText(list);

                                    //instantiate popup window
                                    popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                                    //display the popup window
                                    popupWindow.showAtLocation(godashboard, Gravity.CENTER, 0, 0);
                                    //close the popup window on button click
                                    godashboard.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Intent intent = new Intent(Viability_Screen_revamp_Pl_BL.this, Dashboard_Activity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });
                                    progressDialog.dismiss();
                                }else
                                {
                                    LayoutInflater layoutInflater = (LayoutInflater) Viability_Screen_revamp_Pl_BL.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View customView = layoutInflater.inflate(R.layout.popup_rul_fail,null);
                                    Button godashboard = (Button) customView.findViewById(R.id.godashboard);
                                    TextView content_txt = (TextView) customView.findViewById(R.id.content_txt);


                                  //  String list = Arrays.toString(rule_message.toArray()).replace("[", "").replace("]", "");
                                    content_txt.setText("Bank Requirement Not Met");

                                    //instantiate popup window
                                    popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                                    //display the popup window
                                    popupWindow.showAtLocation(godashboard, Gravity.CENTER, 0, 0);
                                    //close the popup window on button click
                                    godashboard.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Intent intent = new Intent(Viability_Screen_revamp_Pl_BL.this, Dashboard_Activity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                                    progressDialog.dismiss();
                                }

                            }
                            //JSONObject jsonObject1 = response.getJSONObject("response");


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
*/






//New Updated Sourecode for Viablity Check Rule
    private void viability_check_rule() {
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            J.put("b2b_id", Pref.getID(getApplicationContext()));
            Log.i("TAG", "viability_check_rule: "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }Log.e("rule rune request ", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.viability_eligibilitycheck, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("rule run response", String.valueOf(response));
                        String data = String.valueOf(response);
                        GenerateDocverifyrule();
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























    private void viability_check_pass( ) {

        JSONObject J= null;

        try {
            J =new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            J.put("b2b_id", Pref.getID(mCon));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("viability pass ", String.valueOf(J));
        // progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.viabilitysave, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("viability response", String.valueOf(response));
                        String data = String.valueOf(response);
                        try {
                            //  String Status = response.getString("status");
                            JSONObject jsonObject1 = response.getJSONObject("response");

                            if(jsonObject1.getString("viablity_status").equals("success"))
                            {

                            }else if(jsonObject1.getString("viablity_status").equals("error"))
                            {

                            }
                            //  progressDialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Lead creation", String.valueOf(response));


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d(TAG, error.getMessage());
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
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

    private void Submitloandialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.submitloan_dialog2);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn=(Button)dialog.findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Documentdialog();

            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        if (!dialog.isShowing()) {
            dialog.show();
        }



    }

    public void Documentdialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialogsubmit);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView yes_documents = (AppCompatTextView) dialog.findViewById(R.id.yes_documents);
        AppCompatTextView nosubmit_document =(AppCompatTextView)dialog.findViewById(R.id.nosubmit_document);
        AppCompatTextView cancel=(AppCompatTextView)dialog.findViewById(R.id.cancel);
        yes_documents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        nosubmit_document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit_TO_Loanwiser();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent = new Intent(Viability_Screen_revamp_Pl_BL.this, PaymentActivity.class);
                startActivity(intent);
                finish();


            }
        });
        if(!dialog.isShowing()){
            dialog.show();
        }

    }

    private void Submit_TO_Loanwiser( ) {

        JSONObject J= null;

        try {
            J =new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Add Home Laoan", String.valueOf(J));
        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.SUBMIT_TO_LOANWIER, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        try {
                            String Status = response.getString("status");
                            if(Status.contains("success")){
                                Intent intent = new Intent(Viability_Screen_revamp_Pl_BL.this, Dashboard_Activity.class);
                                //  intent.putExtra("viability_jsonArray", viability_array.toString());
                                startActivity(intent);
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Lead creation", String.valueOf(response));

                        progressDialog.dismiss();
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




    private void BankListAPI() {
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("state_id","28");
            Log.i("TAG", "BankListAPI: "+J.toString());

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        Log.e("state_id", String.valueOf(J));

        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_BANKDROPDOWN_LIST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {

                        progressDialog.dismiss();

                        Log.e("respose Dreopdown", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));
                        try {
                            banksalary_array =object.getJSONArray("bank_list");
                            Banklist(banksalary_array);


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

    private void GenerateDocverifyrule(){
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            Log.i("TAG", "Docverifyrule "+J.toString());
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GENEARTEDOCVERIFYRULE, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        Log.e("respose docverifyrule", object.toString());
                        Status_Update();
                       /* Intent intent=new Intent(Viability_Screen_revamp_Pl_BL.this,ViableBankActivity.class);
                        startActivity(intent);*/
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



    private void Submit_Creditbankdetails(){

        Log.e("creditscorevalue1",creditscorevalue);
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.creditbanklaysconform);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn=(Button)dialog.findViewById(R.id.submitbtn);
        AppCompatTextView Pan_No_Show,dob_Show,father_name,marital_status,noofdependent_show,gendershow,
                edu_qualshow,assetshow,salarycredtextview;
        AppCompatTextView doyouhaveloan,legaltxt,creditscoreshow,didpaytxt,whatsavagtxt,bankturntxt,salatycredittxt,
                bankactxt,majortranstxt,bankpdftxt;
        LinearLayout avgbanklay,bankturnoverlays,salarycreditlay,bankaclay,majortranslay,pdfarranglay;

        if(loan_type_id.equals("21")){

            avgbanklay=(LinearLayout)dialog.findViewById(R.id.avgbanklay);
            bankturnoverlays=(LinearLayout)dialog.findViewById(R.id.bankturnoverlays);
            salarycreditlay=(LinearLayout)dialog.findViewById(R.id.salarycreditlay);
            bankaclay=(LinearLayout)dialog.findViewById(R.id.bankaclay);
            majortranslay=(LinearLayout)dialog.findViewById(R.id.majortranslay);
            pdfarranglay=(LinearLayout)dialog.findViewById(R.id.pdfarranglay);
            doyouhaveloan = (AppCompatTextView) dialog.findViewById(R.id.doyouhaveloan);
            legaltxt = (AppCompatTextView) dialog.findViewById(R.id.legaltxt);
            creditscoreshow = (AppCompatTextView) dialog.findViewById(R.id.creditscoreshow);
            didpaytxt = (AppCompatTextView) dialog.findViewById(R.id.didpaytxt);
            whatsavagtxt = (AppCompatTextView) dialog.findViewById(R.id.whatsavagtxt);
            bankturntxt = (AppCompatTextView) dialog.findViewById(R.id.bankturntxt);
            salatycredittxt = (AppCompatTextView) dialog.findViewById(R.id.salatycredittxt);
            bankactxt = (AppCompatTextView) dialog.findViewById(R.id.bankactxt);
            majortranstxt = (AppCompatTextView) dialog.findViewById(R.id.majortranstxt);
            bankpdftxt = (AppCompatTextView) dialog.findViewById(R.id.bankpdftxt);
            Log.e("creditscorevalue2",creditscorevalue);
            avgbanklay.setVisibility(View.GONE);
            bankturnoverlays.setVisibility(View.GONE);
            bankaclay.setVisibility(View.GONE);
            majortranslay.setVisibility(View.GONE);
            creditscoreshow.setText(creditscorevalue);
            didpaytxt.setText(emilatevalue);
            salatycredittxt.setText(banklistvalue);
            if (closedloan_radio.equals("1")){
                doyouhaveloan.setText("Yes");
            }else{
                doyouhaveloan.setText("No");
            }
            if(legal_radio.equals("1")){
                legaltxt.setText("Yes");
            }else{
                legaltxt.setText("No");
            }
            if(arrangepdf_radio.equals("1"))
            {
                bankpdftxt.setText("Yes");
            }else{
                bankpdftxt.setText("No");
            }
        }else{

            avgbanklay=(LinearLayout)dialog.findViewById(R.id.avgbanklay);
            bankturnoverlays=(LinearLayout)dialog.findViewById(R.id.bankturnoverlays);
            salarycreditlay=(LinearLayout)dialog.findViewById(R.id.salarycreditlay);
            bankaclay=(LinearLayout)dialog.findViewById(R.id.bankaclay);
            majortranslay=(LinearLayout)dialog.findViewById(R.id.majortranslay);
            pdfarranglay=(LinearLayout)dialog.findViewById(R.id.pdfarranglay);
            doyouhaveloan = (AppCompatTextView) dialog.findViewById(R.id.doyouhaveloan);
            legaltxt = (AppCompatTextView) dialog.findViewById(R.id.legaltxt);
            creditscoreshow = (AppCompatTextView) dialog.findViewById(R.id.creditscoreshow);
            didpaytxt = (AppCompatTextView) dialog.findViewById(R.id.didpaytxt);
            whatsavagtxt = (AppCompatTextView) dialog.findViewById(R.id.whatsavagtxt);
            bankturntxt = (AppCompatTextView) dialog.findViewById(R.id.bankturntxt);
            salatycredittxt = (AppCompatTextView) dialog.findViewById(R.id.salatycredittxt);
            bankactxt = (AppCompatTextView) dialog.findViewById(R.id.bankactxt);
            majortranstxt = (AppCompatTextView) dialog.findViewById(R.id.majortranstxt);
            bankpdftxt = (AppCompatTextView) dialog.findViewById(R.id.bankpdftxt);
            creditscoreshow.setText(creditscorevalue);
            didpaytxt.setText(emilatevalue);
            salatycredittxt.setText(banklistvalue);
            whatsavagtxt.setText(avgbankbaltxt.getText().toString());
            bankturntxt.setText(bankturnovertxt.getText().toString());
            bankactxt.setText(bankactypevalue);
            majortranstxt.setText(majortransvalue);
            if (closedloan_radio.equals("1")){
                doyouhaveloan.setText("Yes");
            }else{
                doyouhaveloan.setText("No");
            }
            if(legal_radio.equals("1")){
                legaltxt.setText("Yes");
            }else{
                legaltxt.setText("No");
            }
            if(arrangepdf_radio.equals("1"))
            {
                bankpdftxt.setText("Yes");
            }else{
                bankpdftxt.setText("No");
            }
        }
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                rule_message.clear();
                viability_Applicant();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }


    private void Status_Update(){
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("comp_status", "2");
            J.put("subcomp_status", "1");
            Log.i("TAG", "Statusupdate "+J.toString());
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.status_update, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        Intent intent=new Intent(Viability_Screen_revamp_Pl_BL.this, ViableBankActivity.class);
                        startActivity(intent);
                        finish();


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




    private void AutoAPi() {
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("term","info");
            Log.i("TAG", "autoapi: "+J.toString());

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, "https://cscapi.loanwiser.in/mobile/partner_loanapi_test_new.php?call=getCompanyList", J,
                new Response.Listener<JSONObject>() {
            @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        Log.e("resposeDreopdown", object.toString());
                        try {

                            autocompletearray =object.getJSONArray("company_arr");
                            Automenthod(autocompletearray);
/*
                            JSONArray jsonArray = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                                jsonArray = new JSONArray(object);
                                String ss=object.getString("")
                                autocompletearray =object.getJSONArray("company_arr");
                                for(int i=0;i<autocompletearray.length();i++){
                                    Log.i("TAG", "jsonlength"+autocompletearray.length());
                                    JSONObject e = autocompletearray.getJSONObject(i);
                                    String s=e.getString("employe_name");
                                    listarray.add(s);
                                    Log.i("TAG", "onResponse:list"+listarray.add(s));

                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                            (Viability_Screen_revamp_Pl_BL.this,android.R.layout.select_dialog_item, listarray);
                                    autocompletetextview.setThreshold(2);
                                    autocompletetextview.setAdapter(adapter);
                                }

                            }*/



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(Viability_Screen_revamp_Pl_BL.this,"Error",Toast.LENGTH_SHORT).show();
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


    private void Automenthod(final JSONArray Marital_Statues_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Marital_Statues_SA = new String[Marital_Statues_ar.length()];
        for (int i=0;i<Marital_Statues_ar.length();i++){
            JSONObject J =  Marital_Statues_ar.getJSONObject(i);
            Marital_Statues_SA[i] = J.getString("company_code");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList( Marital_Statues_SA));
            autocompleteadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            autocompleteadapter.setDropDownViewResource(R.layout.view_spinner_item);
            company_name_edit_text.setThreshold(2);
            company_name_edit_text.setAdapter(autocompleteadapter);

            //banksalarycreditdropdown.setAdapter(banklistadapter);
           /* banksalarycreditdropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        banklistid = Marital_Statues_ar.getJSONObject(position).getString("id");
                        banklistvalue = Marital_Statues_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/
         /*   banksalarycreditdropdown.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });*/
        }

    }


    private void EmploymentProof(final JSONArray Type_of_employement_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        EMPLOYEE_TYPE_SA = new String[Type_of_employement_ar.length()];
        for (int i=0;i<Type_of_employement_ar.length();i++){
            JSONObject J =  Type_of_employement_ar.getJSONObject(i);
            EMPLOYEE_TYPE_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(EMPLOYEE_TYPE_SA));
            employeproofadapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            employeproofadapter.setDropDownViewResource(R.layout.view_spinner_item);
            employee_proofspinner.setAdapter(employeproofadapter);
            employee_proofspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        employeproofid = Type_of_employement_ar.getJSONObject(position).getString("id");
                        employeproofvalue = Type_of_employement_ar.getJSONObject(position).getString("value");

                        if(employeproofid.equals("2")){
                            officialmaillay.setVisibility(View.VISIBLE);
                        }else{
                            officialmaillay.setVisibility(View.GONE);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            employee_proofspinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }











}