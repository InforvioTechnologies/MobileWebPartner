package in.loanwiser.partnerapp.PartnerActivitys;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
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
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import in.loanwiser.partnerapp.NumberTextWatcher;
import in.loanwiser.partnerapp.R;

public class Applicant_Entry extends SimpleActivity {

    public static final String TITLE = "Applicant Details";
    String[] SPINNERLIST;
    private Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner_loan_category;
    private Spinner spinner_property,applicant_type1;
    LinearLayout linearLayout,linearLayout1,ap,ap1,ap2,ap3,ap4,appl;
    private RadioGroup radioGroup,rg;
    private RadioButton radioButton,nca,ocp,two_co_applicant,three_co_applicant,four_co_applicant;
    private AppCompatTextView loan_type,property_type,add_co_app, txt_bussiness1,txt_bussiness,txt_bussiness101,txt_bussiness121,txt_loan_category;
    private AppCompatTextView applicant_txt,coapplicant1,coapplicant2,coapplicant3,coapplicant4,
            txt_bussiness11,txt_bussiness112,txt_bussiness131,txt_bussiness1311,txt_bussiness10;
    private AppCompatButton updated_applicant;
    private String status;
    String id,step_status;
    int index;
    Typeface font;
    ArrayAdapter<String> Loantype,Loantype1;
    ArrayAdapter<String> C_Loantype;
    JSONArray ja= new JSONArray();
    private String App,Co_app1,Co_app2,Co_app3,Co_app4,Co_app5,string_property,CAT_ID;
    @NotEmpty(messageId = R.string.validation_state)
    private Spinner spinner;
    private AlertDialog progressDialog;
    String sub_categoryid;
    LinearLayout Ly_spinner_loan_category;
    private String TAG = Applicant_Entry.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String loan_category_id;

    String user_id,applicant_id,sub_taskid,transaction_id,Loan_amount;
    private static final String[] Category_TYPE = {
            "Select Loan Type",
            "Home Loan",
            "Loan Against Property",
            "Personal Loan [Unsecured]",
            "Business Loan [Unsecured]",
            "Vehicle Loan"
    };


    AppCompatEditText work_pincode;
    Spinner business_registration1,business_proof1,income_proof,income_proof12;
    
    Spinner co1_income_proof,co1_business_registration1,co1_business_proof1,co1_income_proof12;
    StringBuffer responseID;
    String applicant_select;
    LinearLayout salariy_details,salariy_details1,
                 co1_salariy_details,co1_salariy_details1;

    String[] Business_Reg_type,Business_proof,Income_salarid,Income_self;
    String[] Co_Business_Reg_type,Co_Business_proof,Co_Income_salarid,Co_Income_self;

    ArrayList<IncomeProofPOJO> IncomeProof_Salaried,Co_IncomeProof_Salaried;
    ArrayList<IncomeProofPOJO> IncomeProof_Self,Co_IncomeProof_Self;

    StringBuffer SB_co_self,SB_self,SB_co_salaried,SB_salaried;
    String VSB_co_self,VSB_self,VSB_co_salaried,VSB_salaried;
    String [] aVSB_co_self,aVSB_self,aVSB_co_salaried,aVSB_salaried;

    MyCustomAdapter_Salaried dataAdapter_Salaried = null;
    MyCustomAdapter_Co_Salaried dataAdapter_Co_Salaried = null;
    MyCustomAdapter_Self dataAdapter_Self = null;
    MyCustomAdapter_Co_Self dataAdapter_Co_Self = null;

    RemoveCommas removeClass;

    ArrayAdapter<String>  A_Business_Reg_type, A_Business_proof, A_Income_salarid, A_Income_self;
    ArrayAdapter<String>  Co_A_Business_Reg_type, Co_A_Business_proof, Co_A_Income_salarid, Co_A_Income_self;

    String[] Occupation,Occupation1;
    String[] Co_Occupation,Co_Occupation1;
    AutoCompleteTextView occupation1,occupation12;
    AutoCompleteTextView co1_occupation1,co1_occupation12;
    ArrayAdapter<String> A_occupation1,Co_A_occupation1;
    ArrayAdapter<String> A_occupation2,Co_A_occupation2;

    String  S_Business_Reg_type, S_Business_proof, S_Income_salarid, S_Income_self;
    String  Co_S_Business_Reg_type, Co_S_Business_proof, Co_S_Income_salarid, Co_S_Income_self;
    String Lontypename;
    String Applicant_type,Co_Applicant_type;
    AppCompatEditText Monthly_income1,Monthly_income12,co1_Monthly_income1,co1_Monthly_income12;



    //text_income_proof,text_income_proof12,text_co1_income_proof,text_co1_income_proof12
    String  LW_Property_code,LW_Work_code;
    String  LW_Monthly_income_sala,LW_Monthly_income_self;
    String  LW_Co_Monthly_income_sala,LW_Co_Monthly_income_self;

    String LW_Occup_sala,LW_Occup_self;
    String LW_Co_Occup_sala,LW_Co_Occup_self;

    String LW_Income_sala,LW_Income_self;
    String LW_Co_Income_sala,LW_Co_Income_self;

    String LW_Busniess_Reg_sala,LW_Busniess_Reg_self;
    String LW_Co_Busniess_Reg_sala,LW_Co_Busniess_Reg_self;

    String LW_Busniess_Proof_sala,LW_Busniess_Proof_self;
    String LW_Co_Busniess_Proof_sala,LW_Co_Busniess_Proof_self;

    //hided work_pincode;
    Spinner SP_work_pincode_area,SP_property_pincode_area;
    AutoCompleteTextView W_locationpincode,property_pincode;
    String[] Pincode,Area;
    String[] pro_Pincode,pro_Area;

    String work_pincode_area,property_pincode_area;
    String work_pincode_district_id,work_pincode_state_id;
    String property_pincode_district_id,property_pincode_state_id;


    ArrayAdapter<String>  A_Pincode,A_Area;

    JSONArray mothincome = new JSONArray();
    JSONArray occupation = new JSONArray();
    JSONArray businesseg = new JSONArray();
    JSONArray businessproo = new JSONArray();
    JSONArray residence = new JSONArray();
    JSONArray incomeproofsal = new JSONArray();
    JSONArray incomeproofsal1 = new JSONArray();
    JSONArray incomeproofsal2 = new JSONArray();
    JSONArray incomeproofself = new JSONArray();
    JSONArray incomeproofself1 = new JSONArray();
    JSONArray incomeproofself2 = new JSONArray();
    JSONArray incomeproofself_all = new JSONArray();
    JSONArray incomeproofsal_all = new JSONArray();
    ArrayAdapter<String>  pro_A_Pincode,pro_A_Area;

    JSONArray businessproo1 = new JSONArray();
    JSONArray businessproo2 = new JSONArray();
    ArrayList<IncomeProofPOJO> BusinessProof_Self,BusinessProof_Self1;
    StringBuffer SB_co_self_business_proof,SB_co_self_business_proof1;
    MyCustomAdapter_Business_Proofself dataAdapter_Business_Proofself = null;
    MyCustomAdapter_Business_Proofself1 dataAdapter_Business_Proofself1 = null;
    JSONArray businessproof = new JSONArray();
    String VSB_co_self_business_proof,VSB_co_self_business_proof1;
    String [] aVSB_co_self_business_proof,aVSB_co_self_business_proof1;

    JSONArray  incomeproof_business;
    AppCompatTextView applicant_type,txt_work_pincode_area,txt_property_pincode_area;


    private ChipsView cv_income_proof,cv_business_proof1,cv_income_proof12;
    private ChipsView  cv_co1_income_proof,cv_co1_business_proof1,cv_co1_income_proof12;
    LinearLayout P_Location_pin,Ly_loan_typ;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_applicant__entry);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_applicant__entry);
        initTools(R.string.app_details);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        imm = (InputMethodManager)mCon.getSystemService(Context.INPUT_METHOD_SERVICE);
        font = Typeface.createFromAsset(getApplicationContext().getAssets(), "Lato-Regular.ttf");
        String homeloan= getIntent().getStringExtra("EXTRA_SESSION_ID");
        removeClass = new RemoveCommas();
       // sub_categoryid = "0";

//       user_id,applicant_id,sub_taskid,transaction_id;
        user_id =  Objs.a.getBundle(this, Params.user_id);
        applicant_id =  Objs.a.getBundle(this, Params.applicant_id);
        sub_taskid =  Objs.a.getBundle(this, Params.sub_taskid);
        transaction_id =  Objs.a.getBundle(this, Params.transaction_id);
        Loan_amount =  Objs.a.getBundle(this, Params.loan_amount);
        sub_categoryid =  Objs.a.getBundle(this, Params.sub_categoryid);

        String all = user_id +"\n"+ applicant_id +"\n"+sub_taskid+"\n"+transaction_id +"\n"+ Pref.getID(mCon);
        initCode();

        makeJsonObjReq(sub_categoryid);




        cv_income_proof = (ChipsView) findViewById(R.id.cv_income_proof);
        cv_income_proof12 = (ChipsView) findViewById(R.id.cv_income_proof12);
        cv_business_proof1 = (ChipsView) findViewById(R.id.cv_business_proof1);
      //cv_co1_income_proof,cv_co1_business_proof1,cv_co1_income_proof12
        cv_co1_income_proof = (ChipsView) findViewById(R.id.cv_co1_income_proof);
        cv_co1_business_proof1 = (ChipsView) findViewById(R.id.cv_co1_business_proof1);
        cv_co1_income_proof12 = (ChipsView) findViewById(R.id.cv_co1_income_proof12);

        // change EditText config
        cv_income_proof.getEditText().setCursorVisible(true);

        cv_income_proof.setChipsValidator(new ChipsView.ChipValidator() {
            @Override
            public boolean isValid(Contact contact) {
                if (contact.getDisplayName().equals("asd@qwe.de")) {
                    return false;
                }
                return true;
            }
        });

        cv_income_proof.setChipsListener(new ChipsView.ChipsListener() {
            @Override
            public void onChipAdded(ChipsView.Chip chip) {
                for (ChipsView.Chip chipItem : cv_income_proof.getChips()) {
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

        cv_income_proof12.getEditText().setCursorVisible(true);

        cv_income_proof12.setChipsValidator(new ChipsView.ChipValidator() {
            @Override
            public boolean isValid(Contact contact) {
                if (contact.getDisplayName().equals("asd@qwe.de")) {
                    return false;
                }
                return true;
            }
        });

        cv_income_proof12.setChipsListener(new ChipsView.ChipsListener() {
            @Override
            public void onChipAdded(ChipsView.Chip chip) {
                for (ChipsView.Chip chipItem : cv_income_proof12.getChips()) {
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


        cv_business_proof1.getEditText().setCursorVisible(true);

        cv_business_proof1.setChipsValidator(new ChipsView.ChipValidator() {
            @Override
            public boolean isValid(Contact contact) {
                if (contact.getDisplayName().equals("asd@qwe.de")) {
                    return false;
                }
                return true;
            }
        });

        cv_business_proof1.setChipsListener(new ChipsView.ChipsListener() {
            @Override
            public void onChipAdded(ChipsView.Chip chip) {
                for (ChipsView.Chip chipItem : cv_business_proof1.getChips()) {
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


        //cv_co1_income_proof

        cv_co1_income_proof.getEditText().setCursorVisible(true);

        cv_co1_income_proof.setChipsValidator(new ChipsView.ChipValidator() {
            @Override
            public boolean isValid(Contact contact) {
                if (contact.getDisplayName().equals("asd@qwe.de")) {
                    return false;
                }
                return true;
            }
        });

        cv_co1_income_proof.setChipsListener(new ChipsView.ChipsListener() {
            @Override
            public void onChipAdded(ChipsView.Chip chip) {
                for (ChipsView.Chip chipItem : cv_co1_income_proof.getChips()) {
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


        ///cv_co1_business_proof1
        cv_co1_business_proof1.getEditText().setCursorVisible(true);

        cv_co1_business_proof1.setChipsValidator(new ChipsView.ChipValidator() {
            @Override
            public boolean isValid(Contact contact) {
                if (contact.getDisplayName().equals("asd@qwe.de")) {
                    return false;
                }
                return true;
            }
        });


        cv_co1_business_proof1.setChipsListener(new ChipsView.ChipsListener() {
            @Override
            public void onChipAdded(ChipsView.Chip chip) {
                for (ChipsView.Chip chipItem : cv_co1_business_proof1.getChips()) {
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


        ///cv_co1_income_proof12
        cv_co1_income_proof12.getEditText().setCursorVisible(true);

        cv_co1_income_proof12.setChipsValidator(new ChipsView.ChipValidator() {
            @Override
            public boolean isValid(Contact contact) {
                if (contact.getDisplayName().equals("asd@qwe.de")) {
                    return false;
                }
                return true;
            }
        });

        cv_co1_income_proof12.setChipsListener(new ChipsView.ChipsListener() {
            @Override
            public void onChipAdded(ChipsView.Chip chip) {
                for (ChipsView.Chip chipItem : cv_co1_income_proof12.getChips()) {
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




    }

    private void initCode(){
        initUI();
        clicks();
        fonts();
        Adapter_function();
        Categrty_loan();
     //   addListenerOnButton();
        GET_FIELDS();
        GET_occupation();
        if(sub_categoryid.equals("0")){
            Ly_spinner_loan_category.setVisibility(View.VISIBLE);
        }else {
            Ly_spinner_loan_category.setVisibility(View.GONE);
        }


    }

    private void fonts() {


       // loan_type.setTypeface(font);
        property_type.setTypeface(font);
     //   add_co_app.setTypeface(font);
        coapplicant1.setTypeface(font);
        coapplicant2.setTypeface(font);
        coapplicant3.setTypeface(font);
        coapplicant4.setTypeface(font);
        applicant_txt.setTypeface(font);
        txt_bussiness101.setTypeface(font);
        txt_bussiness1.setTypeface(font);
        txt_bussiness.setTypeface(font);
        txt_bussiness121.setTypeface(font);
        txt_loan_category.setTypeface(font);

        txt_bussiness11.setTypeface(font);
        txt_bussiness10.setTypeface(font);
        txt_bussiness112.setTypeface(font);
        txt_bussiness131.setTypeface(font);
        txt_bussiness1311.setTypeface(font);

     //   W_locationpincode.setTypeface(font);
     //   W_locationpincode.setTextSize(13);
        co1_occupation1.setTypeface(font);
        co1_occupation12.setTypeface(font);
        occupation1.setTypeface(font);
        occupation12.setTypeface(font);
        co1_occupation1.setTextSize(13);
        co1_occupation12.setTextSize(13);
        occupation1.setTextSize(13);
        occupation12.setTextSize(13);
        property_pincode.setTextSize(13);
        property_pincode.setTypeface(font);

        applicant_type.setTypeface(font);
        //txt_work_pincode_area.setTypeface(font);
        txt_property_pincode_area.setTypeface(font);


    }
    private void initUI() {

        //text_income_proof,text_income_proof12,text_co1_income_proof,text_co1_income_proof12

        //Monthly_income1,Monthly_income12,co1_Monthly_income1,co1_Monthly_income12
        Monthly_income1 = (AppCompatEditText)findViewById(R.id.Monthly_income1);
        Monthly_income12 = (AppCompatEditText)findViewById(R.id.Monthly_income12);
        co1_Monthly_income1 = (AppCompatEditText)findViewById(R.id.co1_Monthly_income1);
        co1_Monthly_income12 = (AppCompatEditText)findViewById(R.id.co1_Monthly_income12);


        Monthly_income12.addTextChangedListener(new NumberTextWatcher(Monthly_income12));
        Monthly_income1.addTextChangedListener(new NumberTextWatcher(Monthly_income1));
        co1_Monthly_income12.addTextChangedListener(new NumberTextWatcher(co1_Monthly_income12));
        co1_Monthly_income1.addTextChangedListener(new NumberTextWatcher(co1_Monthly_income1));

        property_pincode = (AutoCompleteTextView) findViewById(R.id.p_locationpincode);

     ///   work_pincode = (AppCompatEditText)findViewById(R.id.W_locationpincode);
        SP_property_pincode_area =  (Spinner)findViewById(R.id.SP_property_pincode_area);
        applicant_type = (AppCompatTextView) findViewById(R.id.applicant_type);
      //  txt_work_pincode_area = (AppCompatTextView)findViewById(R.id.txt_work_pincode_area);
        txt_property_pincode_area = (AppCompatTextView)findViewById(R.id.txt_property_pincode_area);

       // W_locationpincode =  (AutoCompleteTextView)findViewById(R.id.W_locationpincode);
      //  SP_work_pincode_area =  (Spinner)findViewById(R.id.SP_work_pincode_area);

        salariy_details = (LinearLayout) findViewById(R.id.salariy_details);
        salariy_details1 = (LinearLayout) findViewById(R.id.salariy_details1);
        occupation1 =  (AutoCompleteTextView)findViewById(R.id.occupation1);
        occupation12 =  (AutoCompleteTextView)findViewById(R.id.occupation12);
        co1_occupation1 =  (AutoCompleteTextView)findViewById(R.id.co1_occupation1);
        co1_occupation12 =  (AutoCompleteTextView)findViewById(R.id.co1_occupation12);
        //   co1_salariy_details,co1_salariy_details1
        co1_salariy_details = (LinearLayout) findViewById(R.id.co1_salariy_details);
        co1_salariy_details1 = (LinearLayout) findViewById(R.id.co1_salariy_details1);
        Ly_loan_typ = (LinearLayout) findViewById(R.id.Ly_loan_typ);

        P_Location_pin = (LinearLayout) findViewById(R.id.P_Location_pin);
        txt_bussiness101 = (AppCompatTextView) findViewById(R.id.txt_bussiness101);

        //  business_registration1,business_proof1
        income_proof =(Spinner) findViewById(R.id.income_proof);
        income_proof12 =(Spinner) findViewById(R.id.income_proof12);
        business_registration1 =(Spinner) findViewById(R.id.business_registration1);
        business_proof1 =(Spinner) findViewById(R.id.business_proof1);
        //  co1_income_proof,co1_business_registration1,co1_business_proof1,co1_income_proof12
        co1_income_proof =(Spinner) findViewById(R.id.co1_income_proof);
        co1_business_registration1 =(Spinner) findViewById(R.id.co1_business_registration1);
        co1_business_proof1 =(Spinner) findViewById(R.id.co1_business_proof1);
        co1_income_proof12 =(Spinner) findViewById(R.id.co1_income_proof12);


        // loan_type = (AppCompatTextView) findViewById(R.id.loan_type);
        property_type = (AppCompatTextView) findViewById(R.id.property_type);
        add_co_app = (AppCompatTextView) findViewById(R.id.add_co_app);
        updated_applicant = (AppCompatButton) findViewById(R.id.updated_applicant);
        applicant_type1 =(Spinner) findViewById(R.id.applicant_type1);
        txt_bussiness1 = (AppCompatTextView) findViewById(R.id.txt_bussiness1);
        txt_bussiness = (AppCompatTextView) findViewById(R.id.txt_bussiness);
        txt_bussiness121 = (AppCompatTextView) findViewById(R.id.txt_bussiness121);
        txt_loan_category = (AppCompatTextView) findViewById(R.id.txt_loan_category);
        txt_bussiness101 = (AppCompatTextView) findViewById(R.id.txt_bussiness101);
        txt_bussiness11 = (AppCompatTextView) findViewById(R.id.txt_bussiness11);
        txt_bussiness112 = (AppCompatTextView) findViewById(R.id.txt_bussiness112);
        txt_bussiness131 = (AppCompatTextView) findViewById(R.id.txt_bussiness131);
        txt_bussiness1311 = (AppCompatTextView) findViewById(R.id.txt_bussiness1311);
        txt_bussiness10 = (AppCompatTextView) findViewById(R.id.txt_bussiness10);

        applicant_txt = (AppCompatTextView) findViewById(R.id.applicant_txt);
        coapplicant1 = (AppCompatTextView) findViewById(R.id.coapplicant1);
        coapplicant2 = (AppCompatTextView) findViewById(R.id.coapplicant2);
        coapplicant3 = (AppCompatTextView)findViewById(R.id.coapplicant3);
        coapplicant4 = (AppCompatTextView) findViewById(R.id.coapplicant4);

        spinner = (Spinner) findViewById(R.id.spinner);
        Ly_spinner_loan_category = (LinearLayout) findViewById(R.id.Ly_spinner_loan_category);

        // spinner = (Spinner)view.findViewById(R.id.spinner);
        spinner1 =(Spinner) findViewById(R.id.spinner_11);
        spinner2 =(Spinner) findViewById(R.id.spinner12);
        spinner3 =(Spinner) findViewById(R.id.spinner13);
        spinner4 =(Spinner)findViewById(R.id.spinner14);
        spinner5 =(Spinner) findViewById(R.id.spinner15);
        spinner_loan_category =(Spinner) findViewById(R.id.spinner_loan_category);

        spinner_property =(Spinner) findViewById(R.id.spinner_property);
        ap =(LinearLayout)findViewById(R.id.appl);
        ap1 =(LinearLayout) findViewById(R.id.appl1);
        ap2 =(LinearLayout) findViewById(R.id.appl2);
        ap3 =(LinearLayout) findViewById(R.id.appl3);
        ap4 =(LinearLayout) findViewById(R.id.appl4);
        radioGroup = (RadioGroup) findViewById(R.id.radio);

        //nca,ocp,two_co_applicant,three_co_applicant,four_co_applicant
  /*      ocp = (RadioButton) findViewById(R.id.ocp);
        nca = (RadioButton) findViewById(R.id.nca);
        two_co_applicant = (RadioButton) findViewById(R.id.two_co_applicant);
        three_co_applicant = (RadioButton)findViewById(R.id.three_co_applicant);
        four_co_applicant = (RadioButton) findViewById(R.id.four_co_applicant);
        rg = (RadioGroup)findViewById(R.id.applicant);*/

        linearLayout1 = (LinearLayout) findViewById(R.id.Ly_support1);
        linearLayout1.setVisibility(View.VISIBLE);
        appl = (LinearLayout) findViewById(R.id.appl);
        P_Location_pin.setVisibility(View.GONE);


     
        

        property_pincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String new_otp = property_pincode.getText().toString();
                if(new_otp.length()==2){
                    GET_Pincode2(new_otp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private void Adapter_function(){

        MySpinnerAdapter property = new MySpinnerAdapter(mCon, R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.Property_Type)));
        spinner_property.setAdapter(property);
        MySpinnerAdapter applicant1 = new MySpinnerAdapter(mCon, R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.select_applicant)));
        applicant_type1.setAdapter(applicant1);
        MySpinnerAdapter _applicant = new MySpinnerAdapter(mCon, R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.applicant)));
        spinner1.setAdapter(_applicant);

        MySpinnerAdapter Co_applicant2 = new MySpinnerAdapter(mCon, R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.applicant)));
        spinner2.setAdapter(Co_applicant2);

        MySpinnerAdapter Co_applicant3 = new MySpinnerAdapter(mCon, R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.applicant)));
        spinner3.setAdapter(Co_applicant3);

        MySpinnerAdapter Co_applicant4 = new MySpinnerAdapter(mCon, R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.applicant)));
        spinner4.setAdapter(Co_applicant4);

        MySpinnerAdapter Co_applicant5 = new MySpinnerAdapter(mCon, R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.applicant)));
        spinner5.setAdapter(Co_applicant5);

//

    }


  
    
    private void GET_Pincode2(String code) {
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
                                //    Log.e("Pincode", String.valueOf(response));

                                setMain_Area2(response);
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
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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

    

    private void setMain_Area2(final JSONArray ja) throws JSONException {


        pro_Pincode = new String[ja.length()];

        for (int i=0;i<ja.length();i++) {


            JSONObject J = ja.getJSONObject(i);
            pro_Pincode[i] = J.getString("pincode");
            final List<String> pro_Pincode_list = new ArrayList<>(Arrays.asList(pro_Pincode));
            HashSet<String> hashSet = new HashSet<String>();

            hashSet.addAll(pro_Pincode_list);
            pro_Pincode_list.clear();
            pro_Pincode_list.addAll(hashSet);
            //ArrayList<Integer> newList = removeDuplicates(Pincode_list);
            pro_A_Pincode = new ArrayAdapter<String>(mCon.getApplicationContext(),
                    R.layout.view_spinner_item, pro_Pincode_list) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
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
            property_pincode.setThreshold(4);
            property_pincode.setAdapter(pro_A_Pincode);


        }

        property_pincode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String code = (String)adapterView.getItemAtPosition(i);
                //    Objs.a.showToast(getContext(),selection);
                if(code.length()==6){
                    GET_AERA_POST2(code);
                }else {
                    Objs.a.showToast(mCon,"Please Select Pin code");
                }


            }
        });
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
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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

        pro_Area = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            pro_Area[i] = J.getString("area");
            final List<String> pro_area_list = new ArrayList<>(Arrays.asList(pro_Area));
            pro_A_Area = new ArrayAdapter<String>(mCon, R.layout.view_spinner_item, pro_area_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
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

            pro_A_Area.setDropDownViewResource(R.layout.view_spinner_item);
            SP_property_pincode_area.setAdapter(pro_A_Area);
            SP_property_pincode_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //   work_pincode_area = ja.getJSONObject(position).getString("id");
                        property_pincode_area = ja.getJSONObject(position).getString("id");
                        property_pincode_state_id = ja.getJSONObject(position).getString("state_id");
                        property_pincode_district_id = ja.getJSONObject(position).getString("district_id");

                        String a = property_pincode_area +"   "+property_pincode_state_id +"   "+property_pincode_district_id;

                        Log.e("Drop Down",a);
                        // Objs.a.showToast(getContext(),work_pincode_area);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            SP_property_pincode_area.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }


    private void GET_FIELDS() {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_FIELDS_POST,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            JSONArray R_type = object.getJSONArray(Params.business_registration_type);
                            JSONArray B_proof = object.getJSONArray(Params.business_proof);
                            JSONArray IC_proof_sal = object.getJSONArray(Params.business_proofsalaried);
                            JSONArray IC_proof_self = object.getJSONArray(Params.business_proofself);

                            setMain_Business_Reg_type(R_type);
                            //  setMain_Business_proof(B_proof);
                            setBusiness_Proof(B_proof);
                            setBusiness_Proof1(B_proof);
                            setMain_Salaried(IC_proof_sal);
                            Co_setMain_Salaried(IC_proof_sal);
                            setMain_Self(IC_proof_self);
                            Co_setMain_Self(IC_proof_self);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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

    private void GET_occupation() {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_OCC_POST,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {

                            if (object.getString(Params.status).equals("success")) {

                                JSONArray response = object.getJSONArray("response");

                                setMain_Occupation(response);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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




    private void setBusiness_Proof (final JSONArray ja) throws JSONException {

        //  ArrayList<IncomeProofPOJO> BusinessProof_Self,BusinessProof_Self1;
        // business_proof1,co1_business_proof1
        BusinessProof_Self = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO country = new IncomeProofPOJO(id,locality,false);
            BusinessProof_Self.add(country);
        }
        dataAdapter_Business_Proofself = new MyCustomAdapter_Business_Proofself(mCon, 0,BusinessProof_Self);
        business_proof1.setAdapter(dataAdapter_Business_Proofself);
        dataAdapter_Business_Proofself.notifyDataSetChanged();
    }


    private void setBusiness_Proof1 (final JSONArray ja) throws JSONException {

        //  ArrayList<IncomeProofPOJO> BusinessProof_Self,BusinessProof_Self1;
        BusinessProof_Self1 = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO country = new IncomeProofPOJO(id,locality,false);
            BusinessProof_Self1.add(country);
        }
        dataAdapter_Business_Proofself1 = new MyCustomAdapter_Business_Proofself1(mCon, 0,BusinessProof_Self1);
        co1_business_proof1.setAdapter(dataAdapter_Business_Proofself1);
        dataAdapter_Business_Proofself1.notifyDataSetChanged();
    }

    //Business_Reg_type,Business_proof,Income_salarid,Income_self
    private void setMain_Business_Reg_type(final JSONArray ja) throws JSONException {

        Business_Reg_type = new String[ja.length()];
        Co_Business_Reg_type = new String[ja.length()];

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            Business_Reg_type[i] = J.getString("value");
            final List<String> Business_Reg_type_list = new ArrayList<>(Arrays.asList(Business_Reg_type));
            A_Business_Reg_type = new ArrayAdapter<String>(mCon.getApplicationContext(),
                    R.layout.view_spinner_item, Business_Reg_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
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

            A_Business_Reg_type.setDropDownViewResource(R.layout.view_spinner_item);
            business_registration1.setAdapter(A_Business_Reg_type);
            business_registration1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        S_Business_Reg_type = ja.getJSONObject(position).getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            });
            business_registration1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    imm.hideSoftInputFromWindow(occupation12.getWindowToken(), 0);
                    return false;
                }
            });
        }


        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            Co_Business_Reg_type[i] = J.getString("value");
            final List<String> Co_Business_Reg_type_list = new ArrayList<>(Arrays.asList(Co_Business_Reg_type));
            Co_A_Business_Reg_type = new ArrayAdapter<String>(mCon.getApplicationContext(),
                    R.layout.view_spinner_item, Co_Business_Reg_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
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

            Co_A_Business_Reg_type.setDropDownViewResource(R.layout.view_spinner_item);
            co1_business_registration1.setAdapter(Co_A_Business_Reg_type);
            co1_business_registration1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        Co_S_Business_Reg_type = ja.getJSONObject(position).getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            });
            co1_business_registration1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    imm.hideSoftInputFromWindow(co1_occupation12.getWindowToken(), 0);
                    return false;
                }
            });
        }



    }

    //business_proof1,co1_business_proof1

    private void setMain_Business_proof(final JSONArray ja) throws JSONException {

        Business_proof = new String[ja.length()];
        Co_Business_proof = new String[ja.length()];

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            Business_proof[i] = J.getString("value");
            final List<String> Business_proof_type_list = new ArrayList<>(Arrays.asList(Business_proof));
            A_Business_proof = new ArrayAdapter<String>(mCon.getApplicationContext(),
                    R.layout.view_spinner_item, Business_proof_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
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

            A_Business_proof.setDropDownViewResource(R.layout.view_spinner_item);
            business_proof1.setAdapter(A_Business_proof);
            business_proof1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        S_Business_proof = ja.getJSONObject(position).getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            });
            business_proof1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
        }


        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            Co_Business_proof[i] = J.getString("value");
            final List<String> Co_Business_proof_list = new ArrayList<>(Arrays.asList(Co_Business_proof));
            Co_A_Business_proof = new ArrayAdapter<String>(mCon.getApplicationContext(),
                    R.layout.view_spinner_item, Co_Business_proof_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
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

            Co_A_Business_proof.setDropDownViewResource(R.layout.view_spinner_item);
            co1_business_proof1.setAdapter(Co_A_Business_proof);
            co1_business_proof1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        Co_S_Business_proof= ja.getJSONObject(position).getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            });
            co1_business_proof1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
        }



    }


    //IncomeProof_Self,Co_IncomeProof_Self
    private void setMain_Self (final JSONArray ja) throws JSONException {
        IncomeProof_Self = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO country = new IncomeProofPOJO(id,locality,false);
            IncomeProof_Self.add(country);
        }
        dataAdapter_Self = new MyCustomAdapter_Self(mCon, 0,IncomeProof_Self);
        income_proof12.setAdapter(dataAdapter_Self);
        dataAdapter_Self.notifyDataSetChanged();
    }

    private void Co_setMain_Self (final JSONArray ja) throws JSONException {
        Co_IncomeProof_Self = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO country = new IncomeProofPOJO(id,locality,false);
            Co_IncomeProof_Self.add(country);
        }
        dataAdapter_Co_Self = new MyCustomAdapter_Co_Self(mCon, 0,Co_IncomeProof_Self);
        co1_income_proof12.setAdapter(dataAdapter_Co_Self);
        dataAdapter_Co_Self.notifyDataSetChanged();
    }


    ////////////////////////////////////////////
    private void setMain_Salaried(final JSONArray ja) throws JSONException {

        IncomeProof_Salaried = new ArrayList<IncomeProofPOJO>();

        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);

            String id = J.getString("id");
            String locality = J.getString("value");

            IncomeProofPOJO country = new IncomeProofPOJO(id,locality,false);
            IncomeProof_Salaried.add(country);
        }
        dataAdapter_Salaried = new MyCustomAdapter_Salaried(mCon, 0,IncomeProof_Salaried);
        income_proof.setAdapter(dataAdapter_Salaried);
        dataAdapter_Salaried.notifyDataSetChanged();


    }

    private void Co_setMain_Salaried(final JSONArray ja) throws JSONException {

        Co_IncomeProof_Salaried = new ArrayList<IncomeProofPOJO>();
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            String id = J.getString("id");
            String locality = J.getString("value");
            IncomeProofPOJO country = new IncomeProofPOJO(id,locality,false);
            Co_IncomeProof_Salaried.add(country);
        }
        dataAdapter_Co_Salaried = new MyCustomAdapter_Co_Salaried(mCon, 0,Co_IncomeProof_Salaried);
        co1_income_proof.setAdapter(dataAdapter_Co_Salaried);
        dataAdapter_Co_Salaried.notifyDataSetChanged();
    }

    
    private void setMain_Occupation(final JSONArray ja) throws JSONException {

        // A_occupation,Co_A_occupation
        Occupation = new String[ja.length()];
        Occupation1 = new String[ja.length()];
        Co_Occupation1 = new String[ja.length()];
        Co_Occupation = new String[ja.length()];
        //Co_Business_Reg_type = new String[ja.length()];

        for (int i=0;i<ja.length();i++) {
            JSONObject J = ja.getJSONObject(i);
            Occupation[i] = J.getString("occupation_type");
            final List<String> Business_Reg_type_list = new ArrayList<>(Arrays.asList(Occupation));
            A_occupation1 = new ArrayAdapter<String>(mCon.getApplicationContext(),
                    R.layout.view_spinner_item, Business_Reg_type_list) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
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
            occupation1.setThreshold(4);
            occupation1.setAdapter(A_occupation1);
            occupation1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    imm.hideSoftInputFromWindow(occupation1.getWindowToken(), 0);

                }
            });
        }


        for (int i=0;i<ja.length();i++) {
            JSONObject J = ja.getJSONObject(i);
            Occupation1[i] = J.getString("occupation_type");
            final List<String> Business_Reg_type_list = new ArrayList<>(Arrays.asList(Occupation1));
            A_occupation2 = new ArrayAdapter<String>(mCon.getApplicationContext(),
                    R.layout.view_spinner_item, Business_Reg_type_list) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
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
            occupation12.setThreshold(4);
            occupation12.setAdapter(A_occupation2);
            occupation12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    imm.hideSoftInputFromWindow(occupation12.getWindowToken(), 0);

                }
            });
        }


        //Co_Occupation,Co_Occupation1;
        for (int i=0;i<ja.length();i++) {
            JSONObject J = ja.getJSONObject(i);
            Co_Occupation[i] = J.getString("occupation_type");
            final List<String> Business_Reg_type_list = new ArrayList<>(Arrays.asList(Co_Occupation));
            Co_A_occupation1 = new ArrayAdapter<String>(mCon.getApplicationContext(),
                    R.layout.view_spinner_item, Business_Reg_type_list) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
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
            co1_occupation1.setThreshold(4);
            co1_occupation1.setAdapter(Co_A_occupation1);
            co1_occupation1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    imm.hideSoftInputFromWindow(co1_occupation1.getWindowToken(), 0);

                }
            });
        }

        for (int i=0;i<ja.length();i++) {
            JSONObject J = ja.getJSONObject(i);
            Co_Occupation1[i] = J.getString("occupation_type");
            final List<String> Business_Reg_type_list = new ArrayList<>(Arrays.asList(Co_Occupation1));
            Co_A_occupation2 = new ArrayAdapter<String>(mCon.getApplicationContext(),
                    R.layout.view_spinner_item, Business_Reg_type_list) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
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
            co1_occupation12.setThreshold(4);
            co1_occupation12.setAdapter(Co_A_occupation2);
            co1_occupation12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    imm.hideSoftInputFromWindow(co1_occupation12.getWindowToken(), 0);

                }
            });
        }


    }


    ///////Adpaters
    //Salaried,Co_Salaried,self,Co_self
    private class MyCustomAdapter_Salaried extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Salaried;
        IncomeProofPOJO country;
        public MyCustomAdapter_Salaried(Context context, int textViewResourceId,
                               ArrayList<IncomeProofPOJO> Salaried) {
            super(context, textViewResourceId, Salaried);
            this.Salaried = new ArrayList<IncomeProofPOJO>();
            this.Salaried.addAll(Salaried);
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

            MyCustomAdapter_Salaried.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Salaried.ViewHolder();
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
                        IncomeProofPOJO country = (IncomeProofPOJO) cb.getTag();
                        country.setIP_selected(cb.isChecked());

                        String email = country.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_income_proof.addChip(email, imgUrl, contact);
                        } else {
                            cv_income_proof.removeChipBy(contact);
                        }
                    }

                });
            }
            else {
                holder = (MyCustomAdapter_Salaried.ViewHolder) convertView.getTag();
            }

            country = Salaried.get(position);
            holder.name.setText(country.getIP_name());
            holder.name.setChecked(country.isIP_selected());
            holder.name.setTag(country);

            if(country.getIP_name().contains("Select Income Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Income Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }



            return convertView;
        }




    }

    private class MyCustomAdapter_Co_Salaried extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Co_Salaried;

        public MyCustomAdapter_Co_Salaried(Context context, int textViewResourceId,
                               ArrayList<IncomeProofPOJO> Co_Salaried) {
            super(context, textViewResourceId, Co_Salaried);
            this.Co_Salaried = new ArrayList<IncomeProofPOJO>();
            this.Co_Salaried.addAll(Co_Salaried);
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

            MyCustomAdapter_Co_Salaried.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Co_Salaried.ViewHolder();
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
                        IncomeProofPOJO country = (IncomeProofPOJO) cb.getTag();
                        country.setIP_selected(cb.isChecked());
                        String email = country.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_co1_income_proof.addChip(email, imgUrl, contact);
                        } else {
                            cv_co1_income_proof.removeChipBy(contact);
                        }
                    }
                });
            }
            else {
                holder = (MyCustomAdapter_Co_Salaried.ViewHolder) convertView.getTag();
            }

            IncomeProofPOJO country = Co_Salaried.get(position);
            holder.name.setText(country.getIP_name());
            holder.name.setChecked(country.isIP_selected());
            holder.name.setTag(country);
            if(country.getIP_name().contains("Select Income Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Income Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }
    }

    private class MyCustomAdapter_Self extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> self;

        public MyCustomAdapter_Self(Context context, int textViewResourceId,
                               ArrayList<IncomeProofPOJO> self) {
            super(context, textViewResourceId, self);
            this.self = new ArrayList<IncomeProofPOJO>();
            this.self.addAll(self);
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

            MyCustomAdapter_Self.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Self.ViewHolder();
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
                        IncomeProofPOJO country = (IncomeProofPOJO) cb.getTag();
                        country.setIP_selected(cb.isChecked());
                        String email = country.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_income_proof12.addChip(email, imgUrl, contact);
                        } else {
                            cv_income_proof12.removeChipBy(contact);
                        }
                    }
                });
            }
            else {
                holder = (MyCustomAdapter_Self.ViewHolder) convertView.getTag();
            }

            IncomeProofPOJO country = self.get(position);
            holder.name.setText(country.getIP_name());
            holder.name.setChecked(country.isIP_selected());
            holder.name.setTag(country);

            if(country.getIP_name().contains("Select Income Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Income Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }
            return convertView;
        }
    }

    private class MyCustomAdapter_Co_Self extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Co_self;

        public MyCustomAdapter_Co_Self(Context context, int textViewResourceId,
                               ArrayList<IncomeProofPOJO> Co_self) {
            super(context, textViewResourceId, Co_self);
            this.Co_self = new ArrayList<IncomeProofPOJO>();
            this.Co_self.addAll(Co_self);
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

            MyCustomAdapter_Co_Self.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Co_Self.ViewHolder();
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
                        IncomeProofPOJO country = (IncomeProofPOJO) cb.getTag();
                        country.setIP_selected(cb.isChecked());
                        String email = country.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_co1_income_proof12.addChip(email, imgUrl, contact);
                        } else {
                            cv_co1_income_proof12.removeChipBy(contact);
                        }
                    }
                });
            }
            else {
                holder = (MyCustomAdapter_Co_Self.ViewHolder) convertView.getTag();
            }

            IncomeProofPOJO country = Co_self.get(position);
            holder.name.setText(country.getIP_name());
            holder.name.setChecked(country.isIP_selected());
            holder.name.setTag(country);

            if(country.getIP_name().contains("Select Income Proof")){
                holder.name.setVisibility(View.GONE);
                holder.code.setVisibility(View.VISIBLE);
                holder.code.setText("Select Income Proof");

            }else {
                holder.code.setVisibility(View.GONE);
                holder.name.setVisibility(View.VISIBLE);
            }


            return convertView;
        }
    }

    //business Proof
    private class MyCustomAdapter_Business_Proofself extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Business_proff_self;

        public MyCustomAdapter_Business_Proofself(Context context, int textViewResourceId,
                                                  ArrayList<IncomeProofPOJO> Co_self) {
            super(context, textViewResourceId, Co_self);
            this.Business_proff_self = new ArrayList<IncomeProofPOJO>();
            this.Business_proff_self.addAll(Co_self);
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

            MyCustomAdapter_Business_Proofself.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Business_Proofself.ViewHolder();
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
                        IncomeProofPOJO country = (IncomeProofPOJO) cb.getTag();
                        country.setIP_selected(cb.isChecked());

                        String email = country.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_business_proof1.addChip(email, imgUrl, contact);
                        } else {
                            cv_business_proof1.removeChipBy(contact);
                        }
                    }
                });
            }
            else {
                holder = (MyCustomAdapter_Business_Proofself.ViewHolder) convertView.getTag();
            }

            IncomeProofPOJO country = Business_proff_self.get(position);
            holder.name.setText(country.getIP_name());
            holder.name.setChecked(country.isIP_selected());
            holder.name.setTag(country);

            if(country.getIP_name().contains("Select Business Proof")){
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
    //  MyCustomAdapter_Business_Proofself1

    private class MyCustomAdapter_Business_Proofself1 extends ArrayAdapter<IncomeProofPOJO> {

        private ArrayList<IncomeProofPOJO> Business_proff_self1;

        public MyCustomAdapter_Business_Proofself1(Context context, int textViewResourceId,
                                                   ArrayList<IncomeProofPOJO> Co_self) {
            super(context, textViewResourceId, Co_self);
            this.Business_proff_self1 = new ArrayList<IncomeProofPOJO>();
            this.Business_proff_self1.addAll(Co_self);
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

            MyCustomAdapter_Business_Proofself1.ViewHolder holder = null;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.income_proof_info, null);
                holder = new MyCustomAdapter_Business_Proofself1.ViewHolder();
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
                        IncomeProofPOJO country = (IncomeProofPOJO) cb.getTag();
                        country.setIP_selected(cb.isChecked());
                        String email = country.getIP_name();
                        Uri imgUrl = Uri.parse("https://image.shutterstock.com/image-vector/green-proof-icon-check-concept-260nw-596401601.jpg");
                        Contact contact = new Contact(null, null, null, email, imgUrl);
                        if (cb.isChecked()) {
                            cv_co1_business_proof1.addChip(email, imgUrl, contact);
                        } else {
                            cv_co1_business_proof1.removeChipBy(contact);
                        }
                    }
                });
            }
            else {
                holder = (MyCustomAdapter_Business_Proofself1.ViewHolder) convertView.getTag();
            }

            IncomeProofPOJO country = Business_proff_self1.get(position);
            holder.name.setText(country.getIP_name());
            holder.name.setChecked(country.isIP_selected());
            holder.name.setTag(country);

            if(country.getIP_name().contains("Select Business Proof")){
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



    private static class MySpinnerAdapter extends ArrayAdapter<String> {
        // Initialise custom font, for example:
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),
                "Lato-Regular.ttf");

        // (In reality I used a manager which caches the Typeface objects)
        // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

        private MySpinnerAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        // Affects default (closed) state of the spinner
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }

        // Affects opened state of the spinner
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }
    }
   

    private void Categrty_loan(){
            final List<String> loan_list = new ArrayList<>(Arrays.asList(Category_TYPE));
            C_Loantype = new ArrayAdapter<String>(mCon, R.layout.view_spinner_item, loan_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
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

            C_Loantype.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_loan_category.setAdapter(C_Loantype);
            spinner_loan_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    loan_category_id = String.valueOf(position);

                    if(loan_category_id.equals("0")){

                    }else {
                        Lontypename = String.valueOf(parent.getItemAtPosition(position));
                        if(loan_category_id.equals("2")||loan_category_id.equals("3")||loan_category_id.equals("4")){
                            Ly_loan_typ.setVisibility(View.GONE);


                            if(loan_category_id.equals("2")){
                                if (Ly_loan_typ.getVisibility() == View.GONE) {
                                    appl.setVisibility(View.GONE);
                                    P_Location_pin.setVisibility(View.VISIBLE);
                                }
                            }else {
                                if (Ly_loan_typ.getVisibility() == View.GONE) {
                                    appl.setVisibility(View.GONE);
                                    P_Location_pin.setVisibility(View.GONE);
                                }
                            }


                        }else {
                            Ly_loan_typ.setVisibility(View.VISIBLE);
                        }
                        Log.e("Loan Type",Lontypename);
                        makeJsonObjReq(loan_category_id);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_loan_category.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });

    }


    private boolean validateMI1(){
        if (Monthly_income1.getText().toString().trim().isEmpty()) {
            Monthly_income1.setError(getText(R.string.error_income_monthly));
            Monthly_income1.requestFocus();
            return false;
        } else {
        }
        return true;
    }

    private boolean validateMI2(){
        if (Monthly_income12.getText().toString().trim().isEmpty()) {
            Monthly_income12.setError(getText(R.string.error_income_monthly));
            Monthly_income12.requestFocus();
            return false;
        } else {
        }
        return true;
    }

    private boolean validateCO_MI1(){
        if (co1_Monthly_income1.getText().toString().trim().isEmpty()) {
            co1_Monthly_income1.setError(getText(R.string.error_income_monthly));
            co1_Monthly_income1.requestFocus();
            return false;
        } else {
        }
        return true;
    }
    private boolean validateCO_MI2(){
        if (co1_Monthly_income12.getText().toString().trim().isEmpty()) {
            co1_Monthly_income12.setError(getText(R.string.error_income_monthly));
            co1_Monthly_income12.requestFocus();
            return false;
        } else {
        }
        return true;
    }


    private boolean validateOCC1(){
        if (occupation1.getText().toString().trim().isEmpty()) {
           occupation1.setError(getText(R.string.error_occupation));
            occupation1.requestFocus();
            return false;
        } else {
        }
        return true;
    }


    private boolean validateOCC2(){
        if (occupation12.getText().toString().trim().isEmpty()) {
            occupation12.setError(getText(R.string.error_occupation));
            occupation12.requestFocus();
            return false;
        } else {
        }
        return true;
    }

    private boolean validateCO_OCC2(){
        if (co1_occupation12.getText().toString().trim().isEmpty()) {
            co1_occupation12.setError(getText(R.string.error_occupation));

            co1_occupation12.requestFocus();
            return false;
        } else {
        }
        return true;
    }
    private boolean validateCO_OCC1(){
        if (co1_occupation1.getText().toString().trim().isEmpty()) {
            co1_occupation1.setError(getText(R.string.error_occupation));
            co1_occupation1.requestFocus();
            return false;
        } else {
        }
        return true;
    }
//  occupation1,occupation12;
//   co1_occupation1,co1_occupation12;



    private void clicks() {
        applicant_type1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                applicant_select = String.valueOf(applicant_type1.getSelectedItemPosition());
                Log.e("the Applicant select is",applicant_select);
                int a = Integer.parseInt(applicant_select);
                switch(a) {
                    case 0:
                        linearLayout1.setVisibility(View.VISIBLE);
                        ap1.setVisibility(View.GONE);
                        ap2.setVisibility(View.GONE);
                        ap3.setVisibility(View.GONE);
                        ap4.setVisibility(View.GONE);
                        break;

                    case 1:
                        linearLayout1.setVisibility(View.VISIBLE);
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.GONE);
                        ap3.setVisibility(View.GONE);
                        ap4.setVisibility(View.GONE);
                        break;
                    case 2:
                        linearLayout1.setVisibility(View.VISIBLE);
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.VISIBLE);
                        ap3.setVisibility(View.GONE);
                        ap4.setVisibility(View.GONE);
                        break;
                    case 3:
                        linearLayout1.setVisibility(View.VISIBLE);
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.VISIBLE);
                        ap3.setVisibility(View.VISIBLE);
                        ap4.setVisibility(View.GONE);
                        break;
                    case 4:
                        linearLayout1.setVisibility(View.VISIBLE);
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.VISIBLE);
                        ap3.setVisibility(View.VISIBLE);
                        ap4.setVisibility(View.VISIBLE);
                        return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        findViewById(R.id.updated_applicant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Co_app1 = String.valueOf(spinner1.getSelectedItemPosition());
                Log.e("the Applicant select is",Co_app1);
                int b = Integer.parseInt(Co_app1);
                Log.e("propert value",String.valueOf(Co_app1));
                switch(b) {
                    case 0:
                        Applicant_type = "0";
                        salariy_details.setVisibility(View.VISIBLE);
                        salariy_details1.setVisibility(View.GONE);
                        break;
                    case 1:
                        Applicant_type = "1";
                        salariy_details1.setVisibility(View.VISIBLE);
                        salariy_details.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinner1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(property_pincode.getWindowToken(), 0);
                return false;
            }
        });


        income_proof.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(occupation1.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(Monthly_income1.getWindowToken(), 0);
                return false;
            }
        });

        income_proof12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(occupation12.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(Monthly_income12.getWindowToken(), 0);
                return false;
            }
        });

        business_proof1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(occupation12.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(Monthly_income12.getWindowToken(), 0);
                return false;
            }
        });

        co1_income_proof.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(co1_occupation1.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(co1_Monthly_income1.getWindowToken(), 0);
                return false;
            }
        });


        co1_income_proof12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(co1_occupation12.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(co1_Monthly_income12.getWindowToken(), 0);
                return false;
            }
        });



        co1_business_proof1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(occupation12.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(Monthly_income12.getWindowToken(), 0);
                return false;
            }
        });


        //Applicant_type,Co_Applicant_type;


        spinner_property.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // string_property = String.valueOf(spinner_property.getSelectedItemPosition()+ 1);
                string_property = String.valueOf(spinner_property.getSelectedItemPosition());
                Log.e("the Applicant select is",string_property);
                int b = Integer.parseInt(string_property);
                Log.e("propert value",String.valueOf(string_property));
                switch(b) {
                    case 0:
                        P_Location_pin.setVisibility(View.VISIBLE);
                        break;

                    case 1:
                        P_Location_pin.setVisibility(View.GONE);
                        break;
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Co_app2 = String.valueOf(spinner2.getSelectedItemPosition());
                Log.e("the Applicant select is",Co_app2);
                int b = Integer.parseInt(Co_app2);
                Log.e("propert value",String.valueOf(Co_app2));
                switch(b) {
                    case 0:
                        Co_Applicant_type = "0";
                        co1_salariy_details.setVisibility(View.VISIBLE);
                        co1_salariy_details1.setVisibility(View.GONE);

                        break;
                    case 1:
                        Co_Applicant_type = "1";
                        co1_salariy_details1.setVisibility(View.VISIBLE);
                        co1_salariy_details.setVisibility(View.GONE);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    private void Validation_Functions(String s_status, String s_loan_ty, String s_pro, String nustatus,
                                      String co1){

        if(applicant_select.equals("1")){
            Log.e("Selected Applicant", "1" );
            if(Applicant_type.equals("1") && Co_Applicant_type.equals("1")){

                Log.e("Applicant", "1 to 1" );


                incomeproofsal_all = new JSONArray();


                if (!S_Business_Reg_type.equals("0") && S_Business_Reg_type != null && !S_Business_Reg_type.isEmpty()){

                    //Applicant Self
                    SB_co_self_business_proof = new StringBuffer();
                    ArrayList<IncomeProofPOJO> SB_selfList_business = dataAdapter_Business_Proofself.Business_proff_self;
                    for(int i=0;i<SB_selfList_business.size();i++){
                        IncomeProofPOJO country = SB_selfList_business.get(i);
                        if(country.isIP_selected()){
                            SB_co_self_business_proof.append(country.getIP_id()+",");
                            String responseID11 = String.valueOf(SB_co_self_business_proof);
                        }
                    }
                    if (SB_co_self_business_proof.length()> 0) {
                        String responseID11 = String.valueOf(SB_co_self_business_proof);
                        Log.e("SBbusiness_proof 1,1", responseID11);

                        VSB_co_self_business_proof = removeClass.cleanUpCommas(responseID11);
                        aVSB_co_self_business_proof = VSB_co_self_business_proof.split(",");
                        businessproo1 = new JSONArray(Arrays.asList(aVSB_co_self_business_proof));
                    }


                    SB_self = new StringBuffer();
                    ArrayList<IncomeProofPOJO> SB_selfList = dataAdapter_Self.self;
                    for(int i=0;i<SB_selfList.size();i++){
                        IncomeProofPOJO country = SB_selfList.get(i);
                        if(country.isIP_selected()){
                            SB_self.append(country.getIP_id()+ ",");
                            String responseID1 = String.valueOf(SB_self);
                        }
                    }
                    if (SB_self.length()> 0) {

                        String responseID1 = String.valueOf(SB_self);
                        VSB_self = removeClass.cleanUpCommas(responseID1);
                        aVSB_self = VSB_self.split(",");

                        // Log.e("SB_self 1,1",VSB_self);
                        //income proof Self
                        incomeproofsal1 = new JSONArray();
                        incomeproofself1 = new JSONArray(Arrays.asList(aVSB_self));

                    }




                        if ( !Co_S_Business_Reg_type.equals("0") && Co_S_Business_Reg_type != null && !Co_S_Business_Reg_type.isEmpty()) {

                        }
                            SB_co_self_business_proof1 = new StringBuffer();
                            ArrayList<IncomeProofPOJO> SB_selfList_business1 = dataAdapter_Business_Proofself1.Business_proff_self1;
                            for(int i=0;i<SB_selfList_business1.size();i++){
                                IncomeProofPOJO country = SB_selfList_business1.get(i);
                                if(country.isIP_selected()){
                                    SB_co_self_business_proof1.append(country.getIP_id()+",");
                                    String responseID11 = String.valueOf(SB_co_self_business_proof1);
                                }
                            }
                            if (SB_co_self_business_proof1.length()> 0) {
                                String responseID11 = String.valueOf(SB_co_self_business_proof1);
                                Log.e("SBbusiness_proof1 1,1", responseID11);

                                VSB_co_self_business_proof1 = removeClass.cleanUpCommas(responseID11);
                                aVSB_co_self_business_proof1 = VSB_co_self_business_proof1.split(",");

                                businessproo2 = new JSONArray(Arrays.asList(aVSB_co_self_business_proof1));


                            }


                            SB_co_self = new StringBuffer();
                            ArrayList<IncomeProofPOJO> SB_co_self_List = dataAdapter_Co_Self.Co_self;
                            for(int i=0;i<SB_co_self_List.size();i++){
                                IncomeProofPOJO country = SB_co_self_List.get(i);
                                if(country.isIP_selected()){
                                    SB_co_self.append(country.getIP_id()+ ",");
                                    String responseID2 = String.valueOf(SB_co_self);
                                }
                            }
                            if (SB_co_self.length()> 0){
                                String responseID2 = String.valueOf(SB_co_self);
                                VSB_co_self = removeClass.cleanUpCommas(responseID2);
                                aVSB_co_self = VSB_co_self.split(",");

                                Log.e("SB_co_self 1,1",VSB_co_self);
                                incomeproofsal2 = new JSONArray();
                                incomeproofself2 = new JSONArray(Arrays.asList(aVSB_co_self));

                            }

                            LW_Property_code = property_pincode.getText().toString();
                            //  LW_Work_code = W_locationpincode.getText().toString();

                            //   LW_prop_stateid,LW_prop_districtid,LW_loaction_stateid,LW_loaction_districtid;

                            LW_Monthly_income_self =  Monthly_income12.getText().toString();
                            LW_Occup_self =  occupation12.getText().toString();
                            LW_Busniess_Reg_self = S_Business_Reg_type;
                            // LW_Busniess_Proof_self = S_Business_proof;


                            LW_Co_Busniess_Reg_self= Co_S_Business_Reg_type;
                            // LW_Co_Busniess_Proof_self = Co_S_Business_proof;
                            LW_Co_Monthly_income_self =  co1_Monthly_income12.getText().toString();
                            LW_Co_Occup_self =  co1_occupation12.getText().toString();

                            //LW_Monthly_income_sala =  Monthly_income1.getText().toString();
                            //LW_Occup_sala =  occupation1.getText().toString();

                            mothincome = new JSONArray();
                            mothincome.put(LW_Monthly_income_self);
                            mothincome.put(LW_Co_Monthly_income_self);

                            occupation = new JSONArray();
                            occupation.put(LW_Occup_self);
                            occupation.put(LW_Co_Occup_self);


                            businesseg = new JSONArray();
                            businesseg.put(LW_Busniess_Reg_self);
                            businesseg.put(LW_Co_Busniess_Reg_self);


                            businessproo = new JSONArray();
                            businessproo.put(businessproo1);
                            businessproo.put(businessproo2);

                            //income proof Sal

                            //not hava
                            incomeproofsal_all.put(incomeproofsal1);
                            incomeproofsal_all.put(incomeproofsal2);

                            //have
                            incomeproofself_all = new JSONArray();
                            incomeproofself_all.put(incomeproofself1);
                            incomeproofself_all.put(incomeproofself2);

                           /* Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1, LW_Property_code,
                                    occupation,mothincome,null,businesseg,businessproo,
                                    incomeproofsal_all,incomeproofself_all);*/



                    if(SB_co_self_business_proof.length()> 0){


                        if (!validateOCC2()) {
                            return;
                        }

                        if (!validateMI2()) {
                            return;
                        }
                        if (SB_self.length()> 0){

                                if ( !Co_S_Business_Reg_type.equals("0") && Co_S_Business_Reg_type != null && !Co_S_Business_Reg_type.isEmpty()){


                                    if(SB_co_self_business_proof1.length()> 0){

                                        if (SB_co_self.length()> 0){


                                            if (!validateCO_OCC2()) {
                                                return;
                                            }

                                            if (!validateCO_MI2()) {
                                                return;
                                            }


                                            Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1, LW_Property_code,
                                                    occupation,mothincome,null,businesseg,businessproo,
                                                    incomeproofsal_all,incomeproofself_all);

                                        }else {

                                            Objs.a.showToast(mCon,"Please Select the Income proof for Co-Applicant");
                                        }

                                    }else {
                                        Objs.a.showToast(mCon,"Please Select the Business proof Co-Applicant");
                                    }


                                }else {
                                    Objs.a.showToast(mCon,"Please Select Business Registration Type for Co-Applicant");
                                }

                        }else {

                            Objs.a.showToast(mCon,"Please Select the Income proof for Applicant");
                        }

                    }else {
                        Objs.a.showToast(mCon,"Please Select the Business proof Applicant");
                    }


                }else {
                    Objs.a.showToast(mCon,"Please Select Business Registration Type for Applicant");
                }


            }else  if(Applicant_type.equals("1") && Co_Applicant_type.equals("0")){
                Log.e("Applicant", "1 to 0" );

                if (!validateOCC2()) {
                    return;
                }

                if (!validateMI2()) {
                    return;
                }


                if (!S_Business_Reg_type.equals("0") && S_Business_Reg_type != null && !S_Business_Reg_type.isEmpty()){

                    Log.e("BusinessReg 1,0",S_Business_Reg_type );


                    SB_self = new StringBuffer();
                    ArrayList<IncomeProofPOJO> SB_selfList = dataAdapter_Self.self;
                    for(int i=0;i<SB_selfList.size();i++){
                        IncomeProofPOJO country = SB_selfList.get(i);
                        if(country.isIP_selected()){
                            SB_self.append(country.getIP_id()+ ",");
                            String responseID1 = String.valueOf(SB_self);
                        }
                    }
                    if (SB_self.length()> 0) {
                        String responseID1 = String.valueOf(SB_self);
                        VSB_self = removeClass.cleanUpCommas(responseID1);
                        Log.e("SB_self 1,0", VSB_self);
                        aVSB_self = VSB_self.split(",");

                        incomeproofself1 = new JSONArray(Arrays.asList(aVSB_self));
                        incomeproofself2 = new JSONArray();
                        incomeproofself_all = new JSONArray();
                        incomeproofself_all.put(incomeproofself1);
                        incomeproofself_all.put(incomeproofself2);

                    }


                        SB_co_salaried = new StringBuffer();
                        ArrayList<IncomeProofPOJO> SB_co_salaried_List = dataAdapter_Co_Salaried.Co_Salaried;
                        for(int i=0;i<SB_co_salaried_List.size();i++){
                            IncomeProofPOJO country = SB_co_salaried_List.get(i);
                            if(country.isIP_selected()){
                                SB_co_salaried.append(country.getIP_id()+ ",");
                                String responseID12 = String.valueOf(SB_co_salaried);
                            }
                        }
                        if (SB_co_salaried.length()> 0){
                            String responseID12 = String.valueOf(SB_co_salaried);
                            VSB_co_salaried = removeClass.cleanUpCommas(responseID12);
                            Log.e("SB_co_salaried 1,0",VSB_co_salaried);
                            aVSB_co_salaried = VSB_co_salaried.split(",");

                            incomeproofsal1 = new JSONArray();
                            incomeproofsal2 = new JSONArray(Arrays.asList(aVSB_co_salaried));
                            incomeproofsal_all = new JSONArray();
                            incomeproofsal_all.put(incomeproofsal1);
                            incomeproofsal_all.put(incomeproofsal2);

                        }






                    SB_co_self_business_proof = new StringBuffer();
                    ArrayList<IncomeProofPOJO> SB_selfList_business = dataAdapter_Business_Proofself.Business_proff_self;
                    for(int i=0;i<SB_selfList_business.size();i++){
                        IncomeProofPOJO country = SB_selfList_business.get(i);
                        if(country.isIP_selected()){
                            SB_co_self_business_proof.append(country.getIP_id()+",");
                            String responseID1 = String.valueOf(SB_co_self_business_proof);
                        }
                    }

                    if (SB_co_self_business_proof.length()> 0) {
                        String responseID1 = String.valueOf(SB_co_self_business_proof);
                        Log.e("SBbusiness_proof 1,1", responseID1);

                        VSB_co_self_business_proof = removeClass.cleanUpCommas(responseID1);
                        aVSB_co_self_business_proof = VSB_co_self_business_proof.split(",");

                        businessproo1 = new JSONArray(Arrays.asList(aVSB_co_self_business_proof));
                        businessproo2 = new JSONArray();

                        businessproo = new JSONArray();
                        businessproo.put(businessproo1);
                        businessproo.put(businessproo2);

                    }


                    LW_Property_code = property_pincode.getText().toString();
                    // LW_Work_code = W_locationpincode.getText().toString();

                    LW_Monthly_income_self =  Monthly_income12.getText().toString();
                    LW_Occup_self =  occupation12.getText().toString();
                    LW_Busniess_Reg_self = S_Business_Reg_type;
                    //  LW_Busniess_Proof_self = S_Business_proof;

                    LW_Co_Monthly_income_sala =  co1_Monthly_income1.getText().toString();
                    LW_Co_Occup_sala =  co1_occupation1.getText().toString();


                    mothincome = new JSONArray();
                    mothincome.put(LW_Monthly_income_self);
                    mothincome.put(LW_Co_Monthly_income_sala);

                    occupation = new JSONArray();
                    occupation.put(LW_Occup_self);
                    occupation.put(LW_Co_Occup_sala);

                    businesseg = new JSONArray();
                    businesseg.put(LW_Busniess_Reg_self);
                    businesseg.put("");


                   /* Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1,
                            LW_Property_code,occupation,mothincome,null,businesseg,businessproo,
                            incomeproofsal_all,incomeproofself_all);
*/

                    if(SB_co_self_business_proof.length()> 0){


                        if (!validateOCC2()) {
                            return;
                        }

                        if (!validateMI2()) {
                            return;
                        }
                        if (SB_self.length()> 0){


                                        if (!validateCO_OCC1()) {
                                            return;
                                        }

                                        if (!validateCO_MI1()) {
                                            return;
                                        }

                            if (SB_co_salaried.length()> 0){


                                        Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1, LW_Property_code,
                                                occupation,mothincome,null,businesseg,businessproo,
                                                incomeproofsal_all,incomeproofself_all);

                            }else {

                                Objs.a.showToast(mCon,"Please Select the Income proof for Co-Applicant");
                            }

                        }else {

                            Objs.a.showToast(mCon,"Please Select the Income proof for Applicant");
                        }

                    }else {
                        Objs.a.showToast(mCon,"Please Select the Business proof Applicant");
                    }

                }else {
                    Objs.a.showToast(mCon,"Please Select Business Registration Type for Applicant");
                }

            }else  if(Applicant_type.equals("0") && Co_Applicant_type.equals("1")){
                Log.e("Applicant", "0 to 1" );

                if (!validateOCC1()) {
                    return;
                }
                if (!validateMI1()) {
                    return;
                }


                SB_salaried = new StringBuffer();
                ArrayList<IncomeProofPOJO> SB_salaried_List = dataAdapter_Salaried.Salaried;
                for(int i=0;i<SB_salaried_List.size();i++){
                    IncomeProofPOJO country = SB_salaried_List.get(i);
                    if(country.isIP_selected()){
                        SB_salaried.append(country.getIP_id()+ ",");
                        String responseID1 = String.valueOf(SB_salaried);
                    }
                }
                if (SB_salaried.length()> 0) {
                    String responseID1 = String.valueOf(SB_salaried);
                    VSB_salaried = removeClass.cleanUpCommas(responseID1);
                    Log.e("SB_co_self 0,1", VSB_salaried);
                    aVSB_salaried = VSB_salaried.split(",");

                    incomeproofsal1 = new JSONArray(Arrays.asList(aVSB_salaried));
                    incomeproofsal2 = new JSONArray();

                    incomeproofsal_all = new JSONArray();
                    incomeproofsal_all.put(incomeproofsal1);
                    incomeproofsal_all.put(incomeproofsal2);


                }


                    if (!Co_S_Business_Reg_type.equals("0") && Co_S_Business_Reg_type != null &&
                            !Co_S_Business_Reg_type.isEmpty()) {
                    }
                        Log.e("Business Reg 0,1",Co_S_Business_Reg_type );



                        SB_co_self = new StringBuffer();
                        ArrayList<IncomeProofPOJO> SB_co_self_List = dataAdapter_Co_Self.Co_self;
                        for(int i=0;i<SB_co_self_List.size();i++){
                            IncomeProofPOJO country = SB_co_self_List.get(i);
                            if(country.isIP_selected()){
                                SB_co_self.append(country.getIP_id()+ ",");
                                String responseID3 = String.valueOf(SB_co_self);
                            }
                        }
                        if (SB_co_self.length()> 0){
                            String responseID3 = String.valueOf(SB_co_self);
                            VSB_co_self = removeClass.cleanUpCommas(responseID3);
                            Log.e("SB_co_self 0,1",VSB_co_self);
                            aVSB_co_self = VSB_co_self.split(",");

                            incomeproofself1 = new JSONArray();
                            incomeproofself2 = new JSONArray(Arrays.asList(aVSB_co_self));
                            incomeproofself_all = new JSONArray();
                            incomeproofself_all.put(incomeproofself1);
                            incomeproofself_all.put(incomeproofself2);
                        }


                        //Co-applicant
                        SB_co_self_business_proof1 = new StringBuffer();
                        ArrayList<IncomeProofPOJO> SB_selfList_business1 = dataAdapter_Business_Proofself1.Business_proff_self1;
                        for(int i=0;i<SB_selfList_business1.size();i++){
                            IncomeProofPOJO country = SB_selfList_business1.get(i);
                            if(country.isIP_selected()){
                                SB_co_self_business_proof1.append(country.getIP_id()+",");
                                String responseID2 = String.valueOf(SB_co_self_business_proof1);
                            }
                        }
                        if (SB_co_self_business_proof1.length()> 0) {
                            String responseID2 = String.valueOf(SB_co_self_business_proof1);
                            Log.e("SBbusiness_proof1 1,1", responseID2);

                            VSB_co_self_business_proof1 = removeClass.cleanUpCommas(responseID2);
                            aVSB_co_self_business_proof1 = VSB_co_self_business_proof1.split(",");

                            businessproo1 = new JSONArray();
                            businessproo2 = new JSONArray(Arrays.asList(aVSB_co_self_business_proof1));

                            businessproo = new JSONArray();
                            businessproo.put(businessproo1);
                            businessproo.put(businessproo2);
                        }




                        LW_Property_code = property_pincode.getText().toString();
                        //  LW_Work_code = W_locationpincode.getText().toString();

                        LW_Monthly_income_sala =  Monthly_income1.getText().toString();
                        LW_Occup_sala =  occupation1.getText().toString();

                        LW_Co_Busniess_Reg_self= Co_S_Business_Reg_type;
                        //  LW_Co_Busniess_Proof_self = Co_S_Business_proof;
                        LW_Co_Monthly_income_self =  co1_Monthly_income12.getText().toString();
                        LW_Co_Occup_self =  co1_occupation12.getText().toString();


                        mothincome = new JSONArray();
                        mothincome.put(LW_Monthly_income_sala);
                        mothincome.put(LW_Co_Monthly_income_self);

                        occupation = new JSONArray();
                        occupation.put(LW_Occup_sala);
                        occupation.put(LW_Co_Occup_self);

                        businesseg = new JSONArray();
                        businesseg.put("");
                        businesseg.put(LW_Co_Busniess_Reg_self);


                       /* Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1,
                                LW_Property_code,occupation,mothincome,null,businesseg,businessproo,
                                incomeproofsal_all,incomeproofself_all);*/

                        if(SB_salaried.length()> 0){

                            if (!Co_S_Business_Reg_type.equals("0") && Co_S_Business_Reg_type != null &&
                                    !Co_S_Business_Reg_type.isEmpty()){

                                if (SB_co_self_business_proof1.length()> 0) {

                                    if (!validateCO_OCC2()) {
                                        return;
                                    }
                                    if (!validateCO_MI2()) {
                                        return;
                                    }

                                    if (SB_co_self.length()> 0){

                                        Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1,
                                                LW_Property_code,occupation,mothincome,null,businesseg,businessproo,
                                                incomeproofsal_all,incomeproofself_all);
                                    }else {

                                        Objs.a.showToast(mCon,"Please Select the Income proof for Co-Applicant");
                                    }

                                } else {
                                    Objs.a.showToast(mCon,"Please Select the Business proof Co-Applicant");
                                }

                            }else {
                                Objs.a.showToast(mCon,"Please Select Business Registration Type for Co-Applicant");
                            }

                        }else {
                            Objs.a.showToast(mCon,"Please Select the Income proof for Applicant");
                        }



            }else  if(Applicant_type.equals("0") && Co_Applicant_type.equals("0")){
                Log.e("Applicant", "0 to 0" );

                if (!validateOCC1()) {
                    return;
                }

                if (!validateMI1()) {
                    return;
                }


                incomeproofsal_all = new JSONArray();


                SB_co_salaried = new StringBuffer();
                ArrayList<IncomeProofPOJO> SB_co_salaried_List = dataAdapter_Co_Salaried.Co_Salaried;
                for(int i=0;i<SB_co_salaried_List.size();i++){
                    IncomeProofPOJO country = SB_co_salaried_List.get(i);
                    if(country.isIP_selected()){
                        SB_co_salaried.append(country.getIP_id()+ ",");
                        String responseID1 = String.valueOf(SB_co_salaried);
                    }
                }
                if (SB_co_salaried.length()> 0){
                    String responseID1 = String.valueOf(SB_co_salaried);
                    VSB_co_salaried= removeClass.cleanUpCommas(responseID1);
                    Log.e("SB_co_salaried 0,0",VSB_co_salaried);
                    aVSB_co_salaried = VSB_co_salaried.split(",");

                    incomeproofsal2 = new JSONArray(Arrays.asList(aVSB_co_salaried));
                    incomeproofsal_all.put(incomeproofsal2);


                }


                SB_salaried = new StringBuffer();
                ArrayList<IncomeProofPOJO> SB_salaried_List = dataAdapter_Salaried.Salaried;
                for(int i=0;i<SB_salaried_List.size();i++){
                    IncomeProofPOJO country = SB_salaried_List.get(i);
                    if(country.isIP_selected()){
                        SB_salaried.append(country.getIP_id()+ ",");
                        String responseID1 = String.valueOf(SB_salaried);
                    }
                }
                if (SB_salaried.length()> 0){
                    String responseID1 = String.valueOf(SB_salaried);
                    VSB_salaried = removeClass.cleanUpCommas(responseID1);
                    Log.e("SB_co_self 0,0",VSB_salaried);
                    aVSB_salaried = VSB_salaried.split(",");

                    incomeproofsal1 = new JSONArray(Arrays.asList(aVSB_salaried));
                    incomeproofsal_all.put(incomeproofsal1);





                }


                LW_Property_code = property_pincode.getText().toString();
                //  LW_Work_code = W_locationpincode.getText().toString();

                LW_Monthly_income_sala =  Monthly_income1.getText().toString();
                LW_Occup_sala =  occupation1.getText().toString();

                LW_Co_Monthly_income_sala =  co1_Monthly_income1.getText().toString();
                LW_Co_Occup_sala =  co1_occupation1.getText().toString();



                mothincome = new JSONArray();
                mothincome.put(LW_Monthly_income_sala);
                mothincome.put(LW_Co_Monthly_income_sala);

                occupation = new JSONArray();
                occupation.put(LW_Occup_sala);
                occupation.put(LW_Co_Occup_sala);

                incomeproofself1 = new JSONArray();
                incomeproofself2 = new JSONArray();

                incomeproofself_all = new JSONArray();
                incomeproofself_all.put(incomeproofself1);
                incomeproofself_all.put(incomeproofself2);


               /* Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1,
                        LW_Property_code,
                        occupation,mothincome,null,businesseg,businessproo,
                        incomeproofsal_all,incomeproofself_all);
*/

                if(SB_salaried.length()> 0){

                    if (!validateCO_OCC1()) {
                        return;
                    }

                    if (!validateCO_MI1()) {
                        return;
                    }

                    if (SB_co_salaried.length()> 0){

                        Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1,
                                LW_Property_code,
                                occupation,mothincome,null,businesseg,businessproo,
                                incomeproofsal_all,incomeproofself_all);

                    }else {

                        Objs.a.showToast(mCon,"Please Select the Income proof for Co-Applicant");
                    }

                }else {
                    Objs.a.showToast(mCon,"Please Select the Income proof for Applicant");
                }

            }


        }else if(applicant_select.equals("0")){
            Log.e("Selected Applicant", "0" );
            if(Applicant_type.equals("1")) {
                Log.e("Applicant", "1" );



                if (!S_Business_Reg_type.equals("0") && S_Business_Reg_type != null && !S_Business_Reg_type.isEmpty()){


                    if (!validateOCC2()) {
                        return;
                    }

                    if (!validateMI2()) {
                        return;
                    }



                    SB_self = new StringBuffer();
                    ArrayList<IncomeProofPOJO> SB_selfList = dataAdapter_Self.self;
                    for(int i=0;i<SB_selfList.size();i++){
                        IncomeProofPOJO country = SB_selfList.get(i);
                        if(country.isIP_selected()){
                            SB_self.append( country.getIP_id()+ ",");
                            String responseID1 = String.valueOf(SB_self);
                        }
                    }
                    if (SB_self.length()> 0){
                        String responseID1 = String.valueOf(SB_self);
                        VSB_self= removeClass.cleanUpCommas(responseID1);
                        Log.e("SB_self 1,1",VSB_self);
                        aVSB_self = VSB_self.split(",");

                        incomeproofself1 = new JSONArray(Arrays.asList(aVSB_self));
                        incomeproofself2 = new JSONArray();
                        incomeproofself_all = new JSONArray();
                        incomeproofself_all.put(incomeproofself1);
                        incomeproofself_all.put(incomeproofself2);

                    }else {
                        Objs.a.showToast(mCon,"Please Select the Income proof for Applicant");
                    }

                    //Applicant Self
                    SB_co_self_business_proof = new StringBuffer();
                    ArrayList<IncomeProofPOJO> SB_selfList_business = dataAdapter_Business_Proofself.Business_proff_self;
                    for(int i=0;i<SB_selfList_business.size();i++){
                        IncomeProofPOJO country = SB_selfList_business.get(i);
                        if(country.isIP_selected()){
                            SB_co_self_business_proof.append(country.getIP_id()+",");
                            String responseID1 = String.valueOf(SB_co_self_business_proof);
                        }
                    }
                    if (SB_co_self_business_proof.length()> 0) {
                        String responseID1 = String.valueOf(SB_co_self_business_proof);
                        Log.e("SBbusiness_proof 1,1", responseID1);

                        VSB_co_self_business_proof = removeClass.cleanUpCommas(responseID1);
                        aVSB_co_self_business_proof = VSB_co_self_business_proof.split(",");
                        businessproo1 = new JSONArray(Arrays.asList(aVSB_co_self_business_proof));
                        businessproo2 = new JSONArray();

                        businessproo = new JSONArray();
                        businessproo.put(businessproo1);
                        businessproo.put(businessproo2);
                    }
                    else {

                        Objs.a.showToast(mCon,"Please Select the Business proof Applicant");
                    }



                    LW_Property_code = property_pincode.getText().toString();
                    // LW_Work_code = W_locationpincode.getText().toString();

                    LW_Monthly_income_self =  Monthly_income12.getText().toString();
                    LW_Occup_self =  occupation12.getText().toString();

                    LW_Busniess_Reg_self = S_Business_Reg_type;
                    LW_Busniess_Proof_self = S_Business_proof;

                    mothincome = new JSONArray();
                    mothincome.put(LW_Monthly_income_self);
                    mothincome.put("");

                    occupation = new JSONArray();
                    occupation.put(LW_Occup_self);
                    occupation.put("");

                    businesseg = new JSONArray();
                    businesseg.put(LW_Busniess_Reg_self);
                    businesseg.put("");

                    incomeproofsal1 = new JSONArray();
                    incomeproofsal2 = new JSONArray();
                    incomeproofsal_all = new JSONArray();
                    incomeproofsal_all.put(incomeproofsal1);
                    incomeproofsal_all.put(incomeproofsal2);

                   /* Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1,
                            LW_Property_code,
                            occupation,mothincome,null,businesseg,businessproo,
                            incomeproofsal_all,incomeproofself_all);*/


                    if(SB_co_self_business_proof.length()> 0){

                        if (!validateOCC2()) {
                            return;
                        }

                        if (!validateMI2()) {
                            return;
                        }

                        if (SB_self.length()> 0){



                            Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1,
                                    LW_Property_code,
                                    occupation,mothincome,null,businesseg,businessproo,
                                    incomeproofsal_all,incomeproofself_all);


                        }else {

                            Objs.a.showToast(mCon,"Please Select the Income proof for Applicant");
                        }

                    }else {
                        Objs.a.showToast(mCon,"Please Select the Business proof Applicant");
                    }

                }else {
                    Objs.a.showToast(mCon,"Please Select Business Registration Type for Applicant");
                }
            }else {
                Log.e("Applicant", "0" );

                if (!validateOCC1()) {
                    return;
                }

                if (!validateMI1()) {
                    return;
                }


                SB_salaried = new StringBuffer();
                ArrayList<IncomeProofPOJO> SB_salaried_List = dataAdapter_Salaried.Salaried;
                for(int i=0;i<SB_salaried_List.size();i++){
                    IncomeProofPOJO country = SB_salaried_List.get(i);
                    if(country.isIP_selected()){
                        SB_salaried.append(country.getIP_id()+ ",");
                        String responseID1 = String.valueOf(SB_salaried);
                    }
                }
                if (SB_salaried.length()> 0){
                    String responseID1 = String.valueOf(SB_salaried);
                    VSB_salaried= removeClass.cleanUpCommas(responseID1);
                    //  Log.e("SB_co_self 0,0",VSB_salaried);
                    aVSB_salaried = VSB_salaried.split(",");

                    incomeproofsal1 = new JSONArray(Arrays.asList(aVSB_salaried));
                    incomeproofsal2 = new JSONArray();

                    incomeproofsal_all = new JSONArray();
                    incomeproofsal_all.put(incomeproofsal1);
                    incomeproofsal_all.put(incomeproofsal2);


                }


                LW_Property_code = property_pincode.getText().toString();
                // LW_Work_code = W_locationpincode.getText().toString();
                LW_Monthly_income_sala =  Monthly_income1.getText().toString();
                LW_Occup_sala =  occupation1.getText().toString();


                mothincome = new JSONArray();
                mothincome.put(LW_Monthly_income_sala);
                mothincome.put("");

                occupation = new JSONArray();
                occupation.put(LW_Occup_sala);
                occupation.put("");


                incomeproofself1 = new JSONArray();
                incomeproofself2 = new JSONArray();

                incomeproofself_all = new JSONArray();
                incomeproofself_all.put(incomeproofself1);
                incomeproofself_all.put(incomeproofself2);

//                Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1, LW_Property_code,
//                        occupation,mothincome,null,null,null,
//                        incomeproofsal_all,incomeproofself_all);

                if (SB_salaried.length()> 0){

                        Collection_Datas(s_status,s_loan_ty,s_pro,nustatus,co1, LW_Property_code,
                                occupation,mothincome,null,null,null,
                                incomeproofsal_all,incomeproofself_all);


                }else {

                    Objs.a.showToast(mCon,"Please Select the Income proof for Applicant");
                }

            }

        }
    }

    private boolean validateProperty() {
        if (property_pincode.getText().toString().trim().isEmpty()) {
            property_pincode.setError(getText(R.string.error_property));
            property_pincode.requestFocus();
            return false;
        } else {
            //     inputLayoutNumber.setErrorEnabled(false);
        }
        return true;
    }

  /*  private boolean validateWork() {
        if (work_pincode.length() < 6 || work_pincode.length() > 6) {
            work_pincode.setError(getText(R.string.error_work));
            work_pincode.requestFocus();
            return false;
        } else {
            //     inputLayoutNumber.setErrorEnabled(false);
        }
        return true;
    }*/


    private void Collection_Datas1(String s_status, String s_loan_ty, String s_pro, String nustatus,
                                  String co1,String p_pin,
                                  JSONArray occ,JSONArray month_in,JSONArray resd,JSONArray b_reg,
                                  JSONArray b_proof,JSONArray ic_sal,JSONArray ic_self) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {

            J =new JSONObject();
            J.put(Params.user_id, user_id);
            J.put(Params.b2b_userid, Pref.getID(mCon));
            J.put(Params.applicant_id, applicant_id);
            J.put(Params.subtask_id, sub_taskid);
            J.put(Params.formloan_cat, CAT_ID);
            J.put(Params.loan_amount,Loan_amount);
            J.put(Params.applicant_value, s_status);
            J.put(Params.transaction_id, transaction_id);
            J.put(Params.loantype_frmgen, s_loan_ty);
            J.put(Params.propidenti_frmgen, s_pro);
            J.put(Params.applicant_nustatus, nustatus);
            J.put(Params.coapplicant1_nustatus, co1);
            J.put(Params.prop_location_pincode, p_pin);
            J.put(Params.prop_pincode_area, property_pincode_area);
            J.put(Params.prop_stateid,property_pincode_state_id );
            J.put(Params.prop_districtid, property_pincode_district_id);
            J.put(Params.occupation, occ);
            J.put(Params.mothincome, month_in);
            J.put(Params.residence, resd);
            J.put(Params.businesseg, b_reg);
            J.put(Params.businessproo, b_proof);
            J.put(Params.incomeproofsal, ic_sal);
            J.put(Params.incomeproofself, ic_self);

            Log.e("All Package", String.valueOf(J));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*Log.e("Json one and one", String.valueOf(obj));*/
  //  private void Collection_Datas1(String s_status, String s_loan_ty, String s_pro, String nustatus,String co1,
  //                                String p_pin,String w_pin,String occ,String month_in,String resd,String b_reg,String b_proof,String ic_sal,String ic_self) {
        private void Collection_Datas(String s_status, String s_loan_ty, String s_pro, String nustatus,
                                      String co1,String p_pin,
                                      JSONArray occ,JSONArray month_in,JSONArray resd,JSONArray b_reg,
                                      JSONArray b_proof,JSONArray ic_sal,JSONArray ic_self) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {

           J =new JSONObject();
             J.put(Params.user_id, user_id);
            J.put(Params.b2b_userid, Pref.getID(mCon));
            J.put(Params.applicant_id, applicant_id);
            J.put(Params.subtask_id, sub_taskid);
            J.put(Params.formloan_cat, CAT_ID);
            J.put(Params.loan_amount,Loan_amount);
            J.put(Params.applicant_value, s_status);
            J.put(Params.transaction_id, transaction_id);
            J.put(Params.loantype_frmgen, s_loan_ty);
            J.put(Params.propidenti_frmgen, string_property);
            J.put(Params.applicant_nustatus, nustatus);
            J.put(Params.coapplicant1_nustatus, co1);
            J.put(Params.prop_location_pincode, p_pin);
            J.put(Params.prop_pincode_area, property_pincode_area);
            J.put(Params.prop_stateid,property_pincode_state_id );
            J.put(Params.prop_districtid, property_pincode_district_id);
            J.put(Params.occupation, occ);
            J.put(Params.mothincome, month_in);
            J.put(Params.residence, resd);
            J.put(Params.businesseg, b_reg);
            J.put(Params.businessproo, b_proof);
            J.put(Params.incomeproofsal, ic_sal);
            J.put(Params.incomeproofself, ic_self);

            Log.e("All Package", String.valueOf(J));

        } catch (JSONException e) {
            e.printStackTrace();
        }

            progressDialog.show();

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.APP_DOWNLOAD_POST, J,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject J) {

                            //  Objs.a.showToast(getActivity(), String.valueOf(response));

                            Log.e("Add Applicant Info", String.valueOf(J));

                            try {
                                if(J.getString(Params.status).equals("ok")){
                                    Objs.a.showToast(mCon, "Successfully Added");
                                    //    Objs.ac.StartActivity(getActivity(), Dashboard_Activity.class);
                                    //    getActivity().finish();


                                    id =   J.getString(Params.user_id);
                                    applicant_id =   J.getString(Params.applicant_id);
                                    transaction_id =   J.getString(Params.transaction_id);
                                    sub_taskid =   J.getString(Params.subtask_id);
                                    step_status =   J.getString(Params.step_status);


                                    Objs.ac.StartActivityPutExtra(mCon, Home.class,
                                            Params.user_id,id,
                                            Params.step_status,step_status,
                                            Params.transaction_id,transaction_id,
                                            Params.applicant_id,applicant_id,
                                            Params.sub_taskid,sub_taskid);
                                    finish();


                                }else{

                                }

                                progressDialog.dismiss();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(mCon,error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("content-type", "application/json");
                    return headers;
                }
            };
            AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        }


    private void submit(){
            if(sub_categoryid.equals("0")){
                if (!loan_category_id.equals("0") && loan_category_id != null && !loan_category_id.isEmpty()){

                    if (!App.equals("0") && App != null && !App.isEmpty()){

                        string_property = String.valueOf(spinner_property.getSelectedItemPosition());
                        Log.e("the Applicant select is",string_property);
                        int b = Integer.parseInt(string_property);
                        Log.e("propert value",String.valueOf(string_property));
                        if( App.equals("16") || App.equals("18")|| App.equals("19") || App.equals("20")|| App.equals("21"))
                        {

                        }else{
                            switch(b) {
                                case 0:
                                    if (!validateProperty()) {
                                        return;
                                    }
                                    string_property = "1";
                                    break;
                                case 1:
                                    string_property = "2";
                                    break;
                            }
                        }
                       /* if (!validateWork()) {
                            return;
                        }*/
                        Applicant_Count();

                    }else {
                        Objs.a.showToast(mCon,"Please Select Loan type");
                    }

                }else {
                    Objs.a.showToast(mCon,"Please Select Loan Category and your Loan type");
                }
            }else {

                if (!App.equals("0") && App != null && !App.isEmpty()) {

                    string_property = String.valueOf(spinner_property.getSelectedItemPosition());
                    Log.e("the Applicant select is", string_property);
                    int b = Integer.parseInt(string_property);
                    Log.e("propert value", String.valueOf(string_property));
                    if (App.equals("16") || App.equals("18") || App.equals("19") || App.equals("20") || App.equals("21")) {
                        Log.e("No validation", "no validation");
                        if( App.equals("20")|| App.equals("21"))
                        {
                            string_property = "2";
                        }else
                        {
                            string_property = "1";
                        }
                    } else {
                        switch (b) {
                            case 0:
                                if (!validateProperty()) {
                                    return;
                                }
                                string_property = "1";
                                break;
                            case 1:
                                string_property = "2";
                                break;
                        }
                    }
                    Applicant_Count();
                }else {
                    Objs.a.showToast(mCon,"Please Select Loan type");
                }
            }

    }

    private void Applicant_Count(){

   //     status = String.valueOf(index + 1);
        status = String.valueOf(applicant_type1.getSelectedItemPosition()+1);
        if (!App.equals("1") || !App.equals("3")|| !App.equals("4") )
        {
            string_property = "1";
        }

        if(status.equals("1")){
            //  App = spinner.getSelectedItem().toString();
            if(!App.equals("0") && App != null && !App.isEmpty()&&!App.equals("null")){
                Co_app1 = String.valueOf(spinner1.getSelectedItemPosition()+ 1);
              //  string_property = String.valueOf(spinner_property.getSelectedItemPosition()+ 1);
                String all = status+"\n"+App +"\n"+ Co_app1;
                //  Applicant_Status(status,App,string_property,Co_app1,"","","","");
                //  Objs.a.showToast(mCon, "Select Loan Type 1" + all);
                if(Co_app1.equals("2")){

                    int a = Integer.parseInt(Co_app1);
                    int b = 1;
                    Co_app1 = String.valueOf(a+b);
                }


                  Validation_Functions(status,App,string_property,Co_app1,null);
            }else{
                Toast.makeText(mCon,"Select Loan Type 1", Toast.LENGTH_SHORT).show();
            }

        }else if(status.equals("2")){
            if(!App.equals("0") && App != null && !App.isEmpty()&&!App.equals("null")){
             //   string_property = String.valueOf(spinner_property.getSelectedItemPosition()+ 1);
                Co_app1 = String.valueOf(spinner1.getSelectedItemPosition()+ 1);
                Co_app2 = String.valueOf(spinner2.getSelectedItemPosition()+ 1);
                String all = status+"\n"+App +"\n"+ Co_app1 +"\n"+ Co_app2 ;
             //   Applicant_Status(status,App,string_property,Co_app1,Co_app2,"","","");
            //    Objs.a.showToast(mCon, "Select Loan Type 2" + all);
                if(Co_app1.equals("2")){

                    int a = Integer.parseInt(Co_app1);
                    int b = 1;
                    Co_app1 = String.valueOf(a+b);
                }
                if(Co_app2.equals("2")){

                    int a = Integer.parseInt(Co_app2);
                    int b = 1;
                    Co_app2 = String.valueOf(a+b);
                }
                Validation_Functions(status,App,string_property,Co_app1,Co_app2);
            }else{
                Toast.makeText(mCon,"Select Loan Type 2", Toast.LENGTH_SHORT).show();
            }

        }else if(status.equals("3")){
            if(!App.equals("0") && App != null && !App.isEmpty()&&!App.equals("null")){
                string_property = String.valueOf(spinner_property.getSelectedItemPosition()+ 1);
                Co_app1 = String.valueOf(spinner1.getSelectedItemPosition()+ 1);
                Co_app2 = String.valueOf(spinner2.getSelectedItemPosition()+ 1);
                Co_app3 = String.valueOf(spinner3.getSelectedItemPosition()+ 1);
                String all = status+"\n"+ App +"\n"+ Co_app1 +"\n"+ Co_app2 +"\n"+ Co_app3;
                //Applicant_Status(status,App,string_property,Co_app1,Co_app2,Co_app3,"","");
                    //Objs.a.showToast(mCon,all);
                Objs.a.showToast(mCon, "Select Loan Type 3" + all);
            }else{
                Toast.makeText(mCon,"Select Loan Type 3", Toast.LENGTH_SHORT).show();
            }
        }else if(status.equals("4")){
            if(!App.equals("0") && App != null && !App.isEmpty()&&!App.equals("null")){
                string_property = String.valueOf(spinner_property.getSelectedItemPosition()+ 1);
                Co_app1 = String.valueOf(spinner1.getSelectedItemPosition()+ 1);
                Co_app2 = String.valueOf(spinner2.getSelectedItemPosition()+ 1);
                Co_app3 = String.valueOf(spinner3.getSelectedItemPosition()+ 1);
                Co_app4 = String.valueOf(spinner4.getSelectedItemPosition()+ 1);
                String all = status+"\n"+ App +"\n"+ Co_app1 +"\n"+ Co_app2 +"\n"+ Co_app3 +"\n" + Co_app4;
                Applicant_Status(status,App,string_property,Co_app1,Co_app2,Co_app3,Co_app4,"");
                //  Objs.a.showToast(mCon,all);
            }else{
                Toast.makeText(mCon,"Select Loan Type", Toast.LENGTH_SHORT).show();
            }
        }else if(status.equals("5")){
            if(!App.equals("0") && App != null && !App.isEmpty()&&!App.equals("null")){
                string_property = String.valueOf(spinner_property.getSelectedItemPosition()+1);
                Co_app1 = String.valueOf(spinner1.getSelectedItemPosition()+ 1);
                Co_app2 = String.valueOf(spinner2.getSelectedItemPosition()+ 1);
                Co_app3 = String.valueOf(spinner3.getSelectedItemPosition()+ 1);
                Co_app4 = String.valueOf(spinner4.getSelectedItemPosition()+ 1);
                Co_app5 = String.valueOf(spinner5.getSelectedItemPosition()+ 1);
                String all = status+"\n"+App +"\n"+ Co_app1 +"\n"+ Co_app2 +"\n"+ Co_app3 +"\n" + Co_app4+"\n" + Co_app5;
                //   Objs.a.showToast(mCon,all);
                Applicant_Status(status,App,string_property,Co_app1,Co_app2,Co_app3,Co_app4,Co_app5);

            }else{
                Toast.makeText(mCon,"Select Loan Type", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void Applicant_Status(String s_status, String s_loan_ty, String s_pro, String nustatus,
                                  String co1, String co2, String co3, String co4) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {

            // user_id +"\n"+ applicant_id +"\n"+sub_taskid+"\n"+transaction_id +"\n"+ Pref.getID(mCon);
            J =new JSONObject();
            J.put(Params.user_id, user_id);
            J.put(Params.b2b_userid, Pref.getID(mCon));
            J.put(Params.applicant_id, applicant_id);
            J.put(Params.subtask_id, sub_taskid);
            J.put(Params.formloan_cat, CAT_ID);
            J.put(Params.loan_amount,Loan_amount);
            J.put(Params.applicant_value, s_status);
            J.put(Params.transaction_id, transaction_id);
            J.put(Params.loantype_frmgen, s_loan_ty);
            J.put(Params.propidenti_frmgen, s_pro);
            J.put(Params.applicant_nustatus, nustatus);
            J.put(Params.coapplicant1_nustatus, co1);


            J.put(Params.coapplicant2_nustatus, co2);
            J.put(Params.coapplicant3_nustatus, co3);
            J.put(Params.coapplicant4_nustatus, co4);
            Log.d("Add Applicant Info", String.valueOf(J));
            Log.d("Add Applicant Info", String.valueOf(CAT_ID));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.APP_DOWNLOAD_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject J) {

                        // Objs.a.showToast(mCon, String.valueOf(response));
                        try {
                            if(J.getString(Params.status).equals("ok")){
                                Objs.a.showToast(mCon, "Successfully Added");
                             //   Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                             //   finish();
                                id =   J.getString(Params.user_id);
                                applicant_id =   J.getString(Params.applicant_id);
                                transaction_id =   J.getString(Params.transaction_id);
                                sub_taskid =   J.getString(Params.subtask_id);
                                step_status =   J.getString(Params.step_status);


                                Objs.ac.StartActivityPutExtra(mCon, Home.class,
                                        Params.user_id,id,
                                        Params.step_status,step_status,
                                        Params.transaction_id,transaction_id,
                                        Params.applicant_id,applicant_id,
                                        Params.sub_taskid,sub_taskid);
                                    finish();
                            }else{

                            }
                            progressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    private void makeJsonObjReq(String id) {
        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("category_id", "1");
            J.put("sub_categoryid", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LOAN_TYPE_POST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            ja = object.getJSONArray("loantypelist_arr");
                            setMainSpinner(ja);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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

    private void setMainSpinner(final JSONArray ja) throws JSONException {

        SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[i] = J.getString("loan_type");
            final List<String> city_buyer_list = new ArrayList<>(Arrays.asList(SPINNERLIST));
            Loantype1 = new ArrayAdapter<String>(mCon, R.layout.view_spinner_item, city_buyer_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
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

            Loantype1.setDropDownViewResource(R.layout.view_spinner_item);
            spinner.setAdapter(Loantype1);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        App = ja.getJSONObject(position).getString("id");
                        CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Add Applicant Info", String.valueOf(App));
                        int a = Integer.parseInt(App);
                        switch(a) {
                            case 0:
                                appl.setVisibility(View.GONE);
                                P_Location_pin.setVisibility(View.GONE);
                                break;
                            case 1:
                                appl.setVisibility(View.VISIBLE);
                                P_Location_pin.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                appl.setVisibility(View.VISIBLE);
                                P_Location_pin.setVisibility(View.VISIBLE);
                                break;
                            case 4:
                                appl.setVisibility(View.VISIBLE);
                                P_Location_pin.setVisibility(View.VISIBLE);
                                break;
                            case 16:
                                appl.setVisibility(View.GONE);
                                P_Location_pin.setVisibility(View.GONE);
                                break;
                            case 18:
                                appl.setVisibility(View.GONE);
                                P_Location_pin.setVisibility(View.GONE);
                                break;
                            case 19:
                                appl.setVisibility(View.GONE);
                                P_Location_pin.setVisibility(View.GONE);
                                break;
                            case 20:
                                appl.setVisibility(View.GONE);
                                P_Location_pin.setVisibility(View.GONE);
                                break;
                            case 21:
                                appl.setVisibility(View.GONE);
                                P_Location_pin.setVisibility(View.GONE);
                                break;
                            default:
                                appl.setVisibility(View.GONE);
                                P_Location_pin.setVisibility(View.VISIBLE);
                                return;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

        int loantypename1 = Loantype1.getPosition(Lontypename);
        if(loantypename1 == -1)
        {
            String message = Lontypename + " : Item not found.";
            //   Objs.a.showToast(getActivity(), message);
        }
        else
        {
            spinner.setSelection(loantypename1);
            String message = Lontypename + " : Item found and selected.";
            //  Objs.a.showToast(getActivity(), message);
        }


    }
    /*private void setMainSpinner(final JSONArray ja) throws JSONException {
        SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[0]="--Select your Loan Type--";
            SPINNERLIST[i] = J.getString("loan_type");
        }

        spinner.setItems(SPINNERLIST);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
        spinner.setTypeface(font);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                try {
                    App = ja.getJSONObject(position).getString("id");
                    CAT_ID = ja.getJSONObject(position).getString("category_id");
                    Log.d("Add Applicant Info", String.valueOf(App));
                    int a = Integer.parseInt(App);
                    switch(a) {
                        case 1:
                            appl.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            appl.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            appl.setVisibility(View.VISIBLE);
                            break;
                        default:
                            appl.setVisibility(View.GONE);
                            return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
*/
   /* public void addListenerOnButton() {

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = group.findViewById(checkedId);
                index = group.indexOfChild(radioButton);

                switch(checkedId){
                    case R.id.nca:
                        linearLayout1.setVisibility(View.VISIBLE);
                        ap1.setVisibility(View.GONE);
                        ap2.setVisibility(View.GONE);
                        ap3.setVisibility(View.GONE);
                        ap4.setVisibility(View.GONE);

                        break;
                    case R.id.ocp:
                        linearLayout1.setVisibility(View.VISIBLE);
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.GONE);
                        ap3.setVisibility(View.GONE);
                        ap4.setVisibility(View.GONE);

                        break;
                    case R.id.two_co_applicant:
                        linearLayout1.setVisibility(View.VISIBLE);
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.VISIBLE);
                        ap3.setVisibility(View.GONE);
                        ap4.setVisibility(View.GONE);
                        break;

                    case R.id.three_co_applicant:
                        linearLayout1.setVisibility(View.VISIBLE);
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.VISIBLE);
                        ap3.setVisibility(View.VISIBLE);
                        ap4.setVisibility(View.GONE);

                        break;
                    case R.id.four_co_applicant:
                        linearLayout1.setVisibility(View.VISIBLE);
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.VISIBLE);
                        ap3.setVisibility(View.VISIBLE);
                        ap4.setVisibility(View.VISIBLE);

                        break;
                }
            }
        });

    }*/
    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
        finish();
        super.onBackPressed();
    }

}


