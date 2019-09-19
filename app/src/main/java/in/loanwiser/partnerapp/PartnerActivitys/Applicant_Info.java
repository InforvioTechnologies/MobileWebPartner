package in.loanwiser.partnerapp.PartnerActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.NumberTextWatcher;
import in.loanwiser.partnerapp.R;

public class Applicant_Info extends SimpleActivity {

    public static final String TITLE = "Building Details";

    String[] SPINNERLIST,SPINNERLIST2;

    String[] APPLICANT = {" -Select Salary Details- ","Salaried Individual","Self Employed Profesional","Self Employed Non Profesional","Home Maker","Retired"};
    // APPLICANT = new String[]{"- Select Co-Applicant-","Co-Applicant 1","Co-Applicant 2","Co-Applicant 3","Co-Applicant 4"};
    JSONArray ja= new JSONArray();
    String[] Locality;


    JSONObject json = null;



    private Spinner address,address1,loan_type11;
    private Spinner spinner_property_type,spinner_property_size;
    private String S_property_type_value,S_property_size_value,statename;
    private String ed_door,ed_buliding,ed_street,ed_pincode,ed_state,ed_city,ed_Ptype,ed_Psize,ed_sqft;
    private String ed_address1,ed_address2,ed_ed_buliding_name,ed_unit_no;
    String prop_pincode,locality_id,property_desc,prop_typeid;
    String city_id,get_city_id,city_name,get_city_name;
    String state_id,get_state_id,state_name,get_state_name;

    private String searched_State,searched_City,loan_type_select;


    ArrayAdapter<String> state_profile,city_profile,Loantype,applicant_pdt;

    Typeface font;

    //////
    private String TAG = Applicant_Info.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private FloatingActionButton edite,edite1;
    private Spinner spinner1,spinner2,spinner3,spinner4,spinner5;
    private LinearLayout ap1,ap2,ap3,ap4,appl,Businer_registration,Business_proof,
            co_Businer_registration,co_Business_proof,co_income_proof,co11,residence;
    private AlertDialog progressDialog;
    private Context mCom = this;

    String user_id;
    private  String applicant_cnt,loan_type,applicant,co1,co2,co3,co4,prop_identified,
            app_name11,app_mobile11,app_Loanamt11,app_State11,app_city11,pin11,Loan_cat;
    private LinearLayout Ly_no_applicant,Ly_loan_type_new,Ly_applicant_type,Ly_co1_applicant,
            Ly_co2_applicant,Ly_co3_applicant,Ly_co4_applicant,Ly_pro_idfied,applicant_info,applicant_editinfo, CO1_Ly_applicant_mi,CO1_Ly_applicant_occupation;
    private AppCompatTextView no_applicant,loan_type_new,applicant_type,co1_applicant,
            co2_applicant,co3_applicant,co4_applicant,  pro_idfied;
    private AppCompatTextView no_applicant_val,loan_type_new_val,applicant_type_val,co1_applicant_val,
            co2_applicant_val,co3_applicant_val,co4_applicant_val,pro_idfied_val,
            app_name1,app_mobile1,app_Loanamt1,app_State1,app_city1,pin1,
            applicant_monthly_income,applicant_monthly_income1,applicant_occu,applicant_occu1,
            CO1_applicant_monthly_income,CO1_applicant_monthly_income1,CO1_applicant_occu,CO1_applicant_occu1;
    private AppCompatTextView  propert_pin,prop_area,P_app_State,P_app_State1,
            P_app_city,P_app_city1,work_pin,work_area,W_app_State,W_app_State1,W_app_city,W_app_city1,
            applicant_business_reg,applicant_business_reg1,
            applicant_business_proof,applicant_business_proof1,
            co_applicant_business_reg,co_applicant_business_reg1,co_applicant_business_proof,co_applicant_business_proof1,
            applicant_income_proof,applicant_income_proof1,
            co_applicant_income_proof,co_applicant_income_proof1, applicant_residence,applicant_residence1;
    private AppCompatButton update;

    private String S_proprty_loan;

    String S_city,applicant_id,transaction_id,sub_taskid;
    Applicant_Info.MySpinnerAdapter applicant1;
    InputMethodManager imm;
    private MaterialEditText name,loanamount;
    //   String[] SPINNERLIST1;
    //   String[] SPINNERLIST;
    // MaterialSpinner address,address1;
    private String  S_name,S_loanamount,App,CAT_ID,State_Loc_uniqueID,City_loc_uniqueID,Applicant_salerytype,
            Applicant_salerytype1,Applicant_salerytype2,Applicant_salerytype3,Applicant_salerytype4;
    String S_businessproo,R_businessproo;
    String S_co_businessproo,R_co_businessproo;
    String S_incomeprofcoapp,R_incomeprofcoapp;
    String S_incomeprofapp,R_incomeprofapp;
    RemoveCommas removeClass;
    LinearLayout Ly_proprty_loan;
    private DecimalFormat dfnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_applicant__info);
        initTools(R.string.app_info);
        progressDialog = new SpotsDialog(this, R.style.Custom);

        user_id =  Objs.a.getBundle(this, Params.id);
        transaction_id =  Objs.a.getBundle(this, Params.transaction_id);
        dfnd = new DecimalFormat("#,##,###");
        applicant_id =  Objs.a.getBundle(this, Params.applicant_id);
        sub_taskid =  Objs.a.getBundle(this, Params.sub_taskid);
        removeClass = new RemoveCommas();
        String all = user_id +"\n"+ applicant_id +"\n"+sub_taskid+"\n"+transaction_id +"\n"+ Pref.getID(mCon);
        // loan_id= Pref.getLoanType(mCon);

        Log.e("the loan type id", all);

        initCode();
        imm = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        applicant_info = (LinearLayout) findViewById(R.id.applicant_info);
        Ly_proprty_loan = (LinearLayout) findViewById(R.id.Ly_proprty_loan);
        applicant_editinfo = (LinearLayout) findViewById(R.id.applicant_editinfo);
        edite = (FloatingActionButton)findViewById(R.id.edite);
        edite1 = (FloatingActionButton)findViewById(R.id.edite1);
        update = (AppCompatButton) findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applicant_editinfo.setVisibility(View.GONE);
                applicant_info.setVisibility(View.VISIBLE);


                S_name = name.getText().toString();

                S_loanamount = loanamount.getText().toString();

                // S_name,S_loanamount,App,CAT_ID,State_Loc_uniqueID,City_loc_uniqueID,Applicant_salerytype;
                // Log.e("name",S_name);

                //  updateApplicant();
/* Log.e("name",S_loanamount);
                Log.e("name",App);
                Log.e("name",CAT_ID);
                // Log.e("name",State_Loc_uniqueID);
                // Log.e("name",City_loc_uniqueID);
                Log.e("applicant1",Applicant_salerytype);
                Log.e("applicant2",Applicant_salerytype1);
                Log.e("applicant3",Applicant_salerytype2);
                Log.e("applicant4",Applicant_salerytype3);
                Log.e("applicant5",Applicant_salerytype4);*/
                Applicant_Status(S_loanamount,App,CAT_ID,Applicant_salerytype,Applicant_salerytype1,Applicant_salerytype2,Applicant_salerytype3,Applicant_salerytype4);

            }
        });


    }

    private void initCode(){
        initUI();
        fonts();
        Applicant_Info(user_id);
        click();
        //  Select_State1();
        makeJsonObjReq();
        makeJsonObjReq1(Loan_cat);
        Adapter_function();
    }

    private void click(){
        findViewById(R.id.edite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applicant_editinfo.setVisibility(View.VISIBLE);
                applicant_info.setVisibility(View.GONE);

                //applicant details
                int appli = Integer.parseInt(applicant);
                Log.e("salary Id", String.valueOf(appli));
                String app_name= APPLICANT[appli];
                Log.e("salary Id", String.valueOf(app_name));
                //int aa = Integer.parseInt(appli);
                int applicant_dt1 = applicant_pdt.getPosition(app_name);

                if(applicant_dt1 == -1)
                {

                }
                else
                {
                    spinner1.setSelection(applicant_dt1);

                }

                int loantype = Loantype.getPosition(loan_type_select);
                if(loantype == -1)
                {
                    String message = loan_type_select + " : Item not found.";
                    //   Objs.a.showToast(getActivity(), message);
                }
                else
                {
                    loan_type11.setSelection(loantype);
                    String message = loan_type_select + " : Item found and selected.";
                    //  Objs.a.showToast(getActivity(), message);
                }

                int id = Integer.parseInt(applicant_cnt);
                switch(id) {
                    case 1:
                        ap1.setVisibility(View.GONE);
                        ap2.setVisibility(View.GONE);
                        ap3.setVisibility(View.GONE);
                        ap4.setVisibility(View.GONE);
                        break;
                    case 2:
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.GONE);
                        ap3.setVisibility(View.GONE);
                        ap4.setVisibility(View.GONE);

                        int appli12 = Integer.parseInt(co1);
                        Log.e("salary Idco1", String.valueOf(appli12));
                        String app_name12= APPLICANT[appli12];
                        Log.e("salary Id", String.valueOf(app_name12));
                        //int aa = Integer.parseInt(appli);
                        int applicant_dt112 = applicant_pdt.getPosition(app_name12);

                        if(applicant_dt112 == -1)
                        {

                        }
                        else
                        {
                            spinner2.setSelection(applicant_dt112);

                        }

                        break;
                    case 3:
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.VISIBLE);
                        ap3.setVisibility(View.GONE);
                        ap4.setVisibility(View.GONE);

                        int appli13 = Integer.parseInt(co1);
                        Log.e("salary Idco1", String.valueOf(appli13));
                        String app_name13= APPLICANT[appli13];
                        Log.e("salary Id", String.valueOf(app_name1));
                        //int aa = Integer.parseInt(appli);
                        int applicant_dt113 = applicant_pdt.getPosition(app_name13);

                        if(applicant_dt113 == -1)
                        {

                        }
                        else
                        {
                            spinner2.setSelection(applicant_dt113);

                        }

                        int appli23 = Integer.parseInt(co2);
                        Log.e("salary Idco2", String.valueOf(appli23));
                        String app_name23= APPLICANT[appli23];
                        Log.e("salary Id", String.valueOf(app_name23));
                        //int aa = Integer.parseInt(appli);
                        int applicant_dt123 = applicant_pdt.getPosition(app_name23);

                        if(applicant_dt123 == -1)
                        {

                        }
                        else
                        {
                            spinner3.setSelection(applicant_dt123);

                        }
                        break;
                    case 4:
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.VISIBLE);
                        ap3.setVisibility(View.VISIBLE);
                        ap4.setVisibility(View.GONE);

                        int appli14 = Integer.parseInt(co1);
                        Log.e("salary Idco1", String.valueOf(appli14));
                        String app_name14= APPLICANT[appli14];
                        Log.e("salary Id", String.valueOf(app_name14));
                        //int aa = Integer.parseInt(appli);
                        int applicant_dt114 = applicant_pdt.getPosition(app_name14);

                        if(applicant_dt114 == -1)
                        {

                        }
                        else
                        {
                            spinner2.setSelection(applicant_dt114);

                        }

                        int appli24 = Integer.parseInt(co2);
                        Log.e("salary Idco2", String.valueOf(appli24));
                        String app_name24= APPLICANT[appli24];
                        Log.e("salary Id", String.valueOf(app_name24));
                        //int aa = Integer.parseInt(appli);
                        int applicant_dt124 = applicant_pdt.getPosition(app_name24);

                        if(applicant_dt124 == -1)
                        {

                        }
                        else
                        {
                            spinner3.setSelection(applicant_dt124);

                        }

                        int appli34 = Integer.parseInt(co3);
                        Log.e("salary Idco3", String.valueOf(appli34));
                        String app_name34= APPLICANT[appli34];
                        Log.e("salary Id", String.valueOf(app_name34));
                        //int aa = Integer.parseInt(appli);
                        int applicant_dt134 = applicant_pdt.getPosition(app_name34);

                        if(applicant_dt134 == -1)
                        {

                        }
                        else
                        {
                            spinner4.setSelection(applicant_dt134);

                        }

                        break;
                    case 5:
                        ap1.setVisibility(View.VISIBLE);
                        ap2.setVisibility(View.VISIBLE);
                        ap3.setVisibility(View.VISIBLE);
                        ap4.setVisibility(View.VISIBLE);

                        int appli1 = Integer.parseInt(co1);
                        Log.e("salary Idco1", String.valueOf(appli1));
                        String app_name1= APPLICANT[appli1];
                        Log.e("salary Id", String.valueOf(app_name1));
                        //int aa = Integer.parseInt(appli);
                        int applicant_dt11 = applicant_pdt.getPosition(app_name1);

                        if(applicant_dt11 == -1)
                        {

                        }
                        else
                        {
                            spinner2.setSelection(applicant_dt11);

                        }

                        int appli2 = Integer.parseInt(co2);
                        Log.e("salary Idco2", String.valueOf(appli2));
                        String app_name2= APPLICANT[appli2];
                        Log.e("salary Id", String.valueOf(app_name2));
                        //int aa = Integer.parseInt(appli);
                        int applicant_dt12 = applicant_pdt.getPosition(app_name2);

                        if(applicant_dt12 == -1)
                        {

                        }
                        else
                        {
                            spinner3.setSelection(applicant_dt12);

                        }

                        int appli3 = Integer.parseInt(co3);
                        Log.e("salary Idco3", String.valueOf(appli3));
                        String app_name3= APPLICANT[appli3];
                        Log.e("salary Id", String.valueOf(app_name3));
                        //int aa = Integer.parseInt(appli);
                        int applicant_dt13 = applicant_pdt.getPosition(app_name3);

                        if(applicant_dt13 == -1)
                        {

                        }
                        else
                        {
                            spinner4.setSelection(applicant_dt13);

                        }

                        int appli4 = Integer.parseInt(co4);
                        Log.e("salary Idco4", String.valueOf(appli4));
                        String app_name4= APPLICANT[appli4];
                        Log.e("salary Id", String.valueOf(app_name4));
                        //int aa = Integer.parseInt(appli);
                        int applicant_dt14 = applicant_pdt.getPosition(app_name4);

                        if(applicant_dt14 == -1)
                        {

                        }
                        else
                        {
                            spinner5.setSelection(applicant_dt14);

                        }


                        return;
                }
            }
        });
        findViewById(R.id.edite1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                applicant_editinfo.setVisibility(View.GONE);
                applicant_info.setVisibility(View.VISIBLE);
            }
        });
    }
    private void fonts() {
        Objs.a.OutfitNormalFontStyle(mCon, R.id.no_applicant);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.loan_type_new);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_type);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co1_applicant);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co2_applicant);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co3_applicant);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co4_applicant);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.pro_idfied);

        Objs.a.OutfitNormalFontStyle(mCon, R.id.no_applicant_val);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.loan_type_new_val);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_type_val);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co1_applicant_val);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co2_applicant_val);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co3_applicant_val);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co4_applicant_val);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.pro_idfied_val);

        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_name);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_name1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_mobile);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_mobile1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_Loanamt);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_Loanamt1);
       /* propert_pin,prop_area,P_app_State,P_app_State1,
                P_app_city,P_app_city1,work_pin,work_area,W_app_State,W_app_State1,W_app_city,W_app_city1*/
        Objs.a.OutfitNormalFontStyle(mCon, R.id.propert_pin);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.prop_area);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.P_app_State);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.P_app_State1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.P_app_city);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.P_app_city1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.work_pin);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.work_area);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.W_app_State);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.W_app_State1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.W_app_city1);



        //
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_State);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_State1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_city);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.app_city1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.pin);

        Objs.a.OutfitNormalFontStyle(mCon, R.id.pin1);

        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_monthly_income);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_monthly_income1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_occu);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_occu1);
        //CO1_applicant_monthly_income,CO1_applicant_monthly_income1,CO1_applicant_occu,CO1_applicant_occu1
        Objs.a.OutfitNormalFontStyle(mCon, R.id.CO1_applicant_monthly_income);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.CO1_applicant_monthly_income1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.CO1_applicant_occu);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.CO1_applicant_occu1);
        /*applicant_business_reg,applicant_business_reg1,
            applicant_business_proof,applicant_business_proof1,
            co_applicant_business_reg,co_applicant_business_reg1,co_applicant_business_proof,co_applicant_business_proof1*/

        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_business_reg);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_business_reg1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_business_proof);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_business_proof1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co_applicant_business_reg1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co_applicant_business_proof);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co_applicant_business_proof1);
        /* applicant_income_proof,applicant_income_proof1,
            co_applicant_income_proof,co_applicant_income_proof1*/
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_income_proof);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_income_proof1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co_applicant_income_proof);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.co_applicant_income_proof1);
        // residence,applicant_residence,applicant_residence1
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_residence);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.applicant_residence1);


    }
    private void initUI() {

        no_applicant = (AppCompatTextView) findViewById(R.id.no_applicant);
        loan_type_new = (AppCompatTextView) findViewById(R.id.loan_type_new);
        applicant_type = (AppCompatTextView) findViewById(R.id.applicant_type);
        co1_applicant = (AppCompatTextView) findViewById(R.id.co1_applicant);
        co2_applicant = (AppCompatTextView) findViewById(R.id.co2_applicant);
        co3_applicant = (AppCompatTextView) findViewById(R.id.co3_applicant);
        co4_applicant = (AppCompatTextView) findViewById(R.id.co4_applicant);
        pro_idfied = (AppCompatTextView) findViewById(R.id.pro_idfied);

        //names


        app_name1 = (AppCompatTextView) findViewById(R.id.app_name1);
        app_mobile1 = (AppCompatTextView) findViewById(R.id.app_mobile1);
        app_Loanamt1 = (AppCompatTextView) findViewById(R.id.app_Loanamt1);
        app_State1 = (AppCompatTextView) findViewById(R.id.app_State1);
        app_city1 = (AppCompatTextView) findViewById(R.id.app_city1);
        pin1 = (AppCompatTextView) findViewById(R.id.pin1);

        //
        no_applicant_val = (AppCompatTextView) findViewById(R.id.no_applicant_val);
        loan_type_new_val = (AppCompatTextView) findViewById(R.id.loan_type_new_val);
        applicant_type_val = (AppCompatTextView) findViewById(R.id.applicant_type_val);
        co1_applicant_val = (AppCompatTextView) findViewById(R.id.co1_applicant_val);
        co2_applicant_val = (AppCompatTextView) findViewById(R.id.co2_applicant_val);
        co3_applicant_val = (AppCompatTextView) findViewById(R.id.co3_applicant_val);
        co4_applicant_val = (AppCompatTextView) findViewById(R.id.co4_applicant_val);
        pro_idfied_val = (AppCompatTextView) findViewById(R.id.pro_idfied_val);
         /* propert_pin,prop_area,P_app_State,P_app_State1,
                P_app_city,P_app_city1,work_pin,work_area,W_app_State,W_app_State1,W_app_city,W_app_city1*/
        propert_pin = (AppCompatTextView) findViewById(R.id.propert_pin);
        prop_area = (AppCompatTextView) findViewById(R.id.prop_area);
        P_app_State = (AppCompatTextView) findViewById(R.id.P_app_State);
        P_app_State1 = (AppCompatTextView) findViewById(R.id.P_app_State1);
        P_app_city = (AppCompatTextView) findViewById(R.id.P_app_city);
        P_app_city1 = (AppCompatTextView) findViewById(R.id.P_app_city1);
        work_pin = (AppCompatTextView) findViewById(R.id.work_pin);
        work_area = (AppCompatTextView) findViewById(R.id.work_area);
        W_app_State = (AppCompatTextView) findViewById(R.id.W_app_State);
        W_app_State1 = (AppCompatTextView) findViewById(R.id.W_app_State1);
        W_app_city = (AppCompatTextView) findViewById(R.id.W_app_city);
        W_app_city1 = (AppCompatTextView) findViewById(R.id.W_app_city1);
        // applicant_monthly_income,applicant_monthly_income1,  applicant_occu,applicant_occu1
        applicant_monthly_income = (AppCompatTextView) findViewById(R.id.applicant_monthly_income);
        applicant_monthly_income1 = (AppCompatTextView) findViewById(R.id.applicant_monthly_income1);

     //   applicant_monthly_income1.addTextChangedListener(new NumberTextWatcher(applicant_monthly_income1));

        applicant_occu = (AppCompatTextView) findViewById(R.id.applicant_occu);
        applicant_occu1 = (AppCompatTextView) findViewById(R.id.applicant_occu1);
        //CO1_applicant_monthly_income,CO1_applicant_monthly_income1,CO1_applicant_occu,CO1_applicant_occu1
        CO1_applicant_monthly_income = (AppCompatTextView) findViewById(R.id.CO1_applicant_monthly_income);
        CO1_applicant_monthly_income1 = (AppCompatTextView) findViewById(R.id.CO1_applicant_monthly_income1);
        CO1_applicant_occu = (AppCompatTextView) findViewById(R.id.CO1_applicant_occu);
        CO1_applicant_occu1 = (AppCompatTextView) findViewById(R.id.CO1_applicant_occu1);

        /*applicant_business_reg,applicant_business_reg1,
            applicant_business_proof,applicant_business_proof1,
            co_applicant_business_reg,co_applicant_business_reg1,co_applicant_business_proof,co_applicant_business_proof1*/

        applicant_business_reg = (AppCompatTextView) findViewById(R.id.applicant_business_reg);
        applicant_business_reg1 = (AppCompatTextView) findViewById(R.id.applicant_business_reg1);
        applicant_business_proof = (AppCompatTextView) findViewById(R.id.applicant_business_proof);
        applicant_business_proof1 = (AppCompatTextView) findViewById(R.id.applicant_business_proof1);
        co_applicant_business_reg = (AppCompatTextView) findViewById(R.id.co_applicant_business_reg);
        co_applicant_business_reg1 = (AppCompatTextView) findViewById(R.id.co_applicant_business_reg1);
        co_applicant_business_proof = (AppCompatTextView) findViewById(R.id.co_applicant_business_proof);
        co_applicant_business_proof1 = (AppCompatTextView) findViewById(R.id.co_applicant_business_proof1);
        co_applicant_business_proof1 = (AppCompatTextView) findViewById(R.id.co_applicant_business_proof1);
        /*applicant_income_proof,applicant_income_proof1,
            co_applicant_income_proof,co_applicant_income_proof1*/
        applicant_income_proof = (AppCompatTextView) findViewById(R.id.applicant_income_proof);
        applicant_income_proof1 = (AppCompatTextView) findViewById(R.id.applicant_income_proof1);
        co_applicant_income_proof = (AppCompatTextView) findViewById(R.id.co_applicant_income_proof);
        co_applicant_income_proof1 = (AppCompatTextView) findViewById(R.id.co_applicant_income_proof1);
        // residence,applicant_residence,applicant_residence1
        applicant_residence = (AppCompatTextView) findViewById(R.id.applicant_residence);
        applicant_residence1 = (AppCompatTextView) findViewById(R.id.applicant_residence1);


        spinner1 =(Spinner) findViewById(R.id.spinner_11);

        spinner2 =(Spinner) findViewById(R.id.spinner12);
        spinner3 =(Spinner) findViewById(R.id.spinner13);
        spinner4 =(Spinner) findViewById(R.id.spinner14);
        spinner5 =(Spinner) findViewById(R.id.spinner15);
        residence =(LinearLayout) findViewById(R.id.residence);

        ap1 =(LinearLayout) findViewById(R.id.appl1);
        ap2 =(LinearLayout) findViewById(R.id.appl2);
        ap3 =(LinearLayout) findViewById(R.id.appl3);
        ap4 =(LinearLayout) findViewById(R.id.appl4);

        name = (MaterialEditText) findViewById(R.id.name);
        loanamount = (MaterialEditText) findViewById(R.id.loanamount);
        loanamount.addTextChangedListener(new NumberTextWatcher(loanamount));
        Ly_no_applicant =(LinearLayout)findViewById(R.id.Ly_no_applicant);
        Ly_loan_type_new =(LinearLayout) findViewById(R.id.Ly_loan_type_new);
        Ly_applicant_type =(LinearLayout) findViewById(R.id.Ly_applicant_type);
        Ly_co1_applicant =(LinearLayout) findViewById(R.id.Ly_co1_applicant);
        Ly_co2_applicant =(LinearLayout) findViewById(R.id.Ly_co2_applicant);
        Ly_co3_applicant = (LinearLayout) findViewById(R.id.Ly_co3_applicant);
        Ly_co4_applicant = (LinearLayout) findViewById(R.id.Ly_co4_applicant);
        Ly_pro_idfied = (LinearLayout) findViewById(R.id.Ly_pro_idfied);

        Businer_registration = (LinearLayout) findViewById(R.id.Businer_registration);
        Business_proof = (LinearLayout) findViewById(R.id.Business_proof);
        co_Businer_registration = (LinearLayout) findViewById(R.id.co_Businer_registration);
        co_Business_proof = (LinearLayout) findViewById(R.id.co_Business_proof);

        // CO1_Ly_applicant_mi,CO1_Ly_applicant_occupation,
        CO1_Ly_applicant_mi = (LinearLayout) findViewById(R.id.CO1_Ly_applicant_mi);
        CO1_Ly_applicant_occupation = (LinearLayout) findViewById(R.id.CO1_Ly_applicant_occupation);
        co_income_proof = (LinearLayout) findViewById(R.id.co_income_proof);
        co11 = (LinearLayout) findViewById(R.id.co1);

        address = (Spinner) findViewById(R.id.address);
        address1 = (Spinner) findViewById(R.id.address1);
        loan_type11 = (Spinner) findViewById(R.id.loan_type11);


    }
    // Applicant_Status(S_loanamount,App,CAT_ID,Applicant_salerytype,Applicant_salerytype1,Applicant_salerytype2,Applicant_salerytype3,Applicant_salerytype4);

    private void Applicant_Status(String S_loanamount, String App, String CAT_ID, String Applicant_salerytype,
                                  String Applicant_salerytype1, String Applicant_salerytype2, String Applicant_salerytype3, String Applicant_salerytype4) {

        String stringNumber = S_loanamount;
        String result = null;
        result = stringNumber.replace(",","");

        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {

            // user_id +"\n"+ applicant_id +"\n"+sub_taskid+"\n"+transaction_id +"\n"+ Pref.getID(mCon);
            J =new JSONObject();
            J.put(Params.user_id, user_id);
            J.put(Params.b2b_userid, Pref.getID(getApplicationContext()));
            J.put(Params.applicant_id, applicant_id);
            J.put(Params.subtask_id, sub_taskid);
            J.put(Params.formloan_cat, CAT_ID);
            J.put(Params.applicant_value, applicant_cnt);
            J.put(Params.loan_amount,result);
            J.put(Params.transaction_id, transaction_id);
            J.put(Params.loantype_frmgen, App);
            // J.put(Params.propidenti_frmgen, s_pro);
            J.put(Params.applicant_nustatus, Applicant_salerytype);
            J.put(Params.coapplicant1_nustatus, Applicant_salerytype1);
            J.put(Params.coapplicant2_nustatus, Applicant_salerytype2);
            J.put(Params.coapplicant3_nustatus, Applicant_salerytype3);
            J.put(Params.coapplicant4_nustatus, Applicant_salerytype4);
            Log.d("Add Applicant Info", String.valueOf(J));
            Log.d("Add Applicant Info", String.valueOf(CAT_ID));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.APP_DOWNLOAD_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        /// Objs.a.showToast(getActivity(), String.valueOf(response));

                        Log.e("Add Applicant Info", String.valueOf(response));

                        try {
                            if(response.getString(Params.status).equals("ok")){
                                Objs.a.showToast(mCon, "Successfully Updated");
                                Pref.removeALL(mCon);

                                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
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
    private void Adapter_function(){

        final List<String> city_buyer_list = new ArrayList<>(Arrays.asList(APPLICANT));
        applicant_pdt = new ArrayAdapter<String>(getApplicationContext().getApplicationContext(),
                R.layout.view_spinner_item, city_buyer_list){
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

        applicant_pdt.setDropDownViewResource(R.layout.view_spinner_item);
        spinner1.setAdapter(applicant_pdt);
        spinner2.setAdapter(applicant_pdt);
        spinner3.setAdapter(applicant_pdt);
        spinner4.setAdapter(applicant_pdt);
        spinner5.setAdapter(applicant_pdt);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Applicant_salerytype = String.valueOf(spinner1.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                return false;
            }
        });



        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Applicant_salerytype1 = String.valueOf(spinner2.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                return false;
            }
        });


        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Applicant_salerytype2 = String.valueOf(spinner3.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                return false;
            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Applicant_salerytype3 = String.valueOf(spinner4.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                return false;
            }
        });

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Applicant_salerytype4 = String.valueOf(spinner5.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                return false;
            }
        });






    }


    private static class MySpinnerAdapter extends ArrayAdapter<String> {
        // Initialise custom font, for example:
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Lato-Regular.ttf");

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

    private void Applicant_Info(String id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        final JSONObject[] applicant_details = new JSONObject[1];
        try {
            J =new JSONObject();
            J.put(Params.user_id, id);
            Log.e("Applicant Statues", String.valueOf(J));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.APPLICANT_STATUS_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Applicant Statues ", String.valueOf(response));
                        try {
                            String[] residence11 = getResources().getStringArray(R.array.reci);
                            String[] some_array = getResources().getStringArray(R.array.applicant_list);
                            String[] property_array = getResources().getStringArray(R.array.Property_Type_list);
                            String[] businerreg = getResources().getStringArray(R.array.busines_reg);
                            // app_name11,app_mobile11,app_Loanamt11,app_State11,app_city11,pin11,Loan_cat

                            //  Objs.a.showToast(mCon, String.valueOf(response));
                            app_name11 = response.getString("username");
                            app_name1.setText(app_name11);

                            String location_state = response.getString("location_state");
                            if (response.getString("location_state") != null) {
                                W_app_State1.setText(location_state);
                            } else {
                                Log.e("the value", "the value");
                            }

                            String property_state = response.getString("property_state");
                            String location_district = response.getString("location_district");
                            String property_district = response.getString("property_district");
                            String pincode_area_name = response.getString("pincode_area_name");
                            String property_area_name = response.getString("property_area_name");
                            // applicant_details = response.getJSONObject("applicant_details");
//applicant_monthly_income,applicant_monthly_income1,  applicant_occu,applicant_occu1



 /* propert_pin,prop_area,P_app_State,P_app_State1,
                P_app_city,P_app_city1,work_pin,work_area,W_app_State,W_app_State1,W_app_city,W_app_city1*/
                            prop_area.setText(property_area_name);
                            P_app_State1.setText(property_state);
                            P_app_city1.setText(property_district);

                            work_area.setText(pincode_area_name);

                            W_app_city1.setText(location_district);


                            statename = response.getString("state_name");
                            name.setText(app_name11);

                            app_mobile11 = response.getString("mobileno");
                            app_mobile1.setText(app_mobile11);
                            app_Loanamt11 = response.getString("loan_amount");
                            S_proprty_loan = response.getString("category_id");

                            if(S_proprty_loan.equals("1")){
                                Ly_proprty_loan.setVisibility(View.VISIBLE);
                            }else {
                                Ly_proprty_loan.setVisibility(View.GONE);
                            }


                           // app_Loanamt1.setText(app_Loanamt11);
                            Integer int_Loanamt = (Integer.parseInt(app_Loanamt11));
                            app_Loanamt1.setText(dfnd.format(int_Loanamt));



                            app_State11 = response.getString("state_id");
                            app_city11 = response.getString("city_id");
                            pin11 = response.getString("pincode");
                            pin1.setText(app_Loanamt11);
                            Loan_cat = response.getString("category_id");

                            applicant_cnt = response.getString(Params.applicant_cnt);
                            no_applicant_val.setText(applicant_cnt);
                            loan_type = response.getString(Params.loan_type);


                            Get_Loan_Type(loan_type, Loan_cat);

                            Select_State(app_State11, app_city11);
                            Select_City(app_State11, app_city11);

                            makeJsonObjReq();
                            makeJsonObjReq1(Loan_cat);
                            Adapter_function();
                            GetStateDetails(app_State11);


                            applicant = response.getString(Params.applicant);


                            applicant_type_val.setText(some_array[Integer.parseInt(applicant)]);

                            //  applicant_id = some_array[Integer.parseInt(applicant)];
                            //  Log.e("Applicant Id",applicant_id);
                            co1 = response.getString(Params.co1);
                            co2 = response.getString(Params.co2);
                            co3 = response.getString(Params.co3);
                            co4 = response.getString(Params.co4);
                            prop_identified = response.getString(Params.prop_identified);

                            if(!prop_identified.equals("1"))
                            {
                                Ly_proprty_loan.setVisibility(View.GONE);
                            }

                            JSONObject js = response.getJSONObject("applicant_details");

                            JSONObject app = js.getJSONObject("app");
                            String occuption = app.getString("occuption");
                            String mothincome = app.getString("mothincome");

                           // applicant_monthly_income1.setText(mothincome);
                           // applicant_monthly_income1.setText((Integer.parseInt(mothincome)));
                            Integer int_mothincome = (Integer.parseInt(mothincome));
                            applicant_monthly_income1.setText(dfnd.format(int_mothincome));


                            applicant_occu1.setText(occuption);
                            String residence1 = app.getString("residence");
                            String businesseg = app.getString("businesseg");
                            String type = app.getString("type");

                            // residence,applicant_residence,applicant_residence1
                            if(Loan_cat.equals("2"))
                            {
                                residence.setVisibility(View.VISIBLE);
                               // applicant_residence1.setText(residence1);
                                applicant_residence1.setText(residence11[Integer.parseInt(residence1)]);
                            }else
                            {
                                residence.setVisibility(View.GONE);
                            }


                            JSONArray businessproo = app.getJSONArray("businessproo");
                            StringBuffer businessproo_result = new StringBuffer();

                            //Validation of Array
                            if (businessproo != null && businessproo.length() > 0) {

                                for (int i = 0; i < businessproo.length(); i++) {
                                    JSONObject object = businessproo.getJSONObject(i);
                                    businessproo_result.append(object.getString("name") + ",");
                                }
                                S_businessproo = businessproo_result.toString();
                                R_businessproo = removeClass.cleanUpCommas(S_businessproo);
                                applicant_business_proof1.setText(R_businessproo);
                                //set text value R_businessproo
                            } else {
                                //  Toast.makeText(mCon, "businessproo Its Empty", Toast.LENGTH_SHORT).show();
                            }
                            //  Businer_registration,Business_proof,co_Businer_registration,co_Business_proof
                            if (type.contains("Salaried")) {
                                Businer_registration.setVisibility(View.GONE);
                                Business_proof.setVisibility(View.GONE);

                            } else {
                                if (businesseg != null && !businesseg.isEmpty() && !businesseg.equals("null")) {
                                    applicant_business_reg1.setText(businerreg[Integer.parseInt(businesseg)]);
                                }

                                Businer_registration.setVisibility(View.VISIBLE);
                                Business_proof.setVisibility(View.VISIBLE);
                            }

                            //Income Proof for Applicant
                            /* applicant_income_proof,applicant_income_proof1,
            co_applicant_income_proof,co_applicant_income_proof1*/

                            JSONArray incomeprofapp = response.getJSONArray("incomeprofapp");
                            if (incomeprofapp != null && incomeprofapp.length() > 0) {
                                StringBuffer incomeprofapp_result = new StringBuffer();
                                for (int i = 0; i < incomeprofapp.length(); i++) {
                                    JSONObject aa = incomeprofapp.getJSONObject(i);
                                    incomeprofapp_result.append(aa.getString("name") + ",");
                                }
                                S_incomeprofapp = incomeprofapp_result.toString();
                                R_incomeprofapp = removeClass.cleanUpCommas(S_incomeprofapp);
                                //set text value R_incomeprofapp
                                applicant_income_proof1.setText(R_incomeprofapp);

                            }
                            if (applicant_cnt.equals("1")) {
                                Log.e("count","count 1.");
                                co11.setVisibility(View.GONE);
                            }else{

                                co11.setVisibility(View.VISIBLE);
                                JSONObject co_applicant =js.getJSONObject("coapp");
                                String co_occuption = co_applicant.getString("occuption");
                                String co_mothincome = co_applicant.getString("mothincome");
                                String co_residence = co_applicant.getString("residence");
                                String co_businesseg = co_applicant.getString("businesseg");
                                String co_type = co_applicant.getString("type");
                                JSONArray co_businessproo = co_applicant.getJSONArray("businessproo");

                                StringBuffer co_applicant_result = new StringBuffer();
                                //Validation of Array
                                if(co_businessproo != null && co_businessproo.length() > 0 ){

                                    for (int i = 0; i < co_businessproo.length(); i++) {
                                        JSONObject object =  co_businessproo.getJSONObject(i);
                                        co_applicant_result.append(object.getString("name")+ ",");
                                    }
                                    S_co_businessproo = co_applicant_result.toString();
                                    R_co_businessproo  = removeClass.cleanUpCommas(S_co_businessproo);
                                    //set text value R_co_businessproo
                                    co_applicant_business_proof1.setText(R_co_businessproo);
                                }else {
                                    //  Toast.makeText(mCon,"co_businessproo Its Empty",Toast.LENGTH_SHORT).show();
                                }

                                if(co_type.contains("Salaried"))
                                {
                                    co_Businer_registration.setVisibility(View.GONE);
                                    co_Business_proof.setVisibility(View.GONE);
                                }else
                                {
                                    if (co_businesseg != null && !co_businesseg.isEmpty() && !co_businesseg.equals("null"))
                                    {
                                        co_applicant_business_reg1.setText(businerreg[Integer.parseInt(co_businesseg)]);
                                    }
                                    co_Businer_registration.setVisibility(View.VISIBLE);
                                    co_Business_proof.setVisibility(View.VISIBLE);
                                }

                                //Income Proof for Co-Applicant
                                JSONArray incomeprofcoapp = response.getJSONArray("incomeprofcoapp");
                                if(incomeprofcoapp != null && incomeprofcoapp.length() > 0 ) {
                                    StringBuffer incomeprofcoapp_result = new StringBuffer();
                                    for (int i = 0; i < incomeprofcoapp.length(); i++) {
                                        JSONObject aa = incomeprofcoapp.getJSONObject(i);
                                        incomeprofcoapp_result.append(aa.getString("name") + ",");
                                    }
                                    S_incomeprofcoapp= incomeprofcoapp_result.toString();
                                    R_incomeprofcoapp = removeClass.cleanUpCommas(S_incomeprofcoapp);
                                    //set text value R_incomeprofcoapp
                                    co_applicant_income_proof1.setText(R_incomeprofcoapp);
                                }

                                if (co1 != null && !co1.isEmpty() && !co1.equals("null")){

                                    co1_applicant_val.setText(some_array[Integer.parseInt(co1)]);
                                    Ly_co1_applicant.setVisibility(View.VISIBLE);

//CO1_applicant_monthly_income,CO1_applicant_monthly_income1,CO1_applicant_occu,CO1_applicant_occu1
                                    CO1_Ly_applicant_mi.setVisibility(View.VISIBLE);
                                    CO1_Ly_applicant_occupation.setVisibility(View.VISIBLE);
                                    co_income_proof.setVisibility(View.VISIBLE);
                                 ///   CO1_applicant_monthly_income1.setText(co_mothincome);
                                    Integer int_Co_mothincome = (Integer.parseInt(co_mothincome));
                                    CO1_applicant_monthly_income1.setText(dfnd.format(int_Co_mothincome));
                                    CO1_applicant_occu1.setText(co_occuption);

                                }else {
                                    Ly_co1_applicant.setVisibility(View.GONE);
                                    CO1_Ly_applicant_mi.setVisibility(View.GONE);
                                    CO1_Ly_applicant_occupation.setVisibility(View.GONE);
                                }

                            }


                            ///  pro_idfied_val.setText(property_array[Integer.parseInt(prop_identified)]);


                            if (co2 != null && !co2.isEmpty() && !co2.equals("null")){
                                co2_applicant_val.setText(some_array[Integer.parseInt(co2)]);
                                Ly_co2_applicant.setVisibility(View.VISIBLE);
                            }else {
                                Ly_co2_applicant.setVisibility(View.GONE);
                            }

                            if (co3 != null && !co3.isEmpty() && !co3.equals("null")){
                                Ly_co3_applicant.setVisibility(View.VISIBLE);
                                co3_applicant_val.setText(some_array[Integer.parseInt(co3)]);
                            }else {
                                Ly_co3_applicant.setVisibility(View.GONE);
                            }

                            if (co4 != null && !co4.isEmpty() && !co4.equals("null")){
                                Ly_co4_applicant.setVisibility(View.VISIBLE);
                                co4_applicant_val.setText(some_array[Integer.parseInt(co4)]);
                            }else {
                                Ly_co4_applicant.setVisibility(View.GONE);
                            }

                           /* if (prop_identified != null && !prop_identified.isEmpty() && !prop_identified.equals("null")){
                                Ly_pro_idfied.setVisibility(View.VISIBLE);
                                pro_idfied_val.setText(property_array[Integer.parseInt(prop_identified)]);
                            }else {
                                Ly_pro_idfied.setVisibility(View.GONE);
                            }*/

                            int a = Integer.parseInt(prop_identified);
                            switch(a) {
                                case 1:

                                    Ly_pro_idfied.setVisibility(View.GONE);
                                    pro_idfied_val.setText(property_array[Integer.parseInt(prop_identified)]);
                                    break;
                                case 2:
                                    Ly_pro_idfied.setVisibility(View.GONE);
                                    break;

                                default:
                                    Ly_pro_idfied.setVisibility(View.GONE);
                                    return;
                            }

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

    private void Get_Loan_Type(final String id,String cat_id) {

        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("category_id", cat_id);


            //  Log.d("Applicant category_id", cat_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  progressDialog.show();
        // Toast.makeText(mCon,"Its running",Toast.LENGTH_SHORT).show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LOAN_TYPE_POST11, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.d("Applicant Info", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));
                        Log.d("Applicant Info", String.valueOf(object));
                        try {
                            JSONArray ja = object.getJSONArray(Params.loantypelist_arr);

                            for (int i=0;i<ja.length();i++){

                                JSONObject object1 = ja.getJSONObject(i);

                                String id_type = object1.getString(Params.id);
                                String loan_type = object1.getString(Params.loan_type);

                                if(id_type.equals(id)){
                                    // Objs.a.showToast(mCom, loan_type);
                                    loan_type_new_val.setText(loan_type);

                                    loan_type_select = loan_type;
                                    //  Objs.a.showToast(mCom, loan_type);

                                    // int loantype_new = Loantype.getPosition(loan_type_select);


                                   /* if(loantype_new == -1)
                                    {
                                        String message = loan_type_select + " : Item not found.";
                                        //   Objs.a.showToast(getActivity(), message);
                                    }
                                    else
                                    {
                                        loan_type11.setSelection(loantype_new);
                                        String message = loan_type_select + " : Item found and selected.";
                                        //  Objs.a.showToast(getActivity(), message);
                                    }*/

                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        //   progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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

    private void Select_State(final String state_id,String city_id) {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_STATE_POST, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        //   Objs.a.showToast(getContext(), String.valueOf(object));
                        try {
                            JSONArray ja = object.getJSONArray("state");
                            //  setState_Spinner(ja);
                            String id_type,state_name;
                            for (int i=0;i<ja.length();i++){

                                JSONObject object1 = ja.getJSONObject(i);

                                id_type = object1.getString("state_id");
                                state_name = object1.getString("state_name");

                                if(id_type.equals(state_id)){
                                    // Objs.a.showToast(mCom, loan_type);
                                    app_State1.setText(state_name);
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



    private void Select_City(final String id,final String city_id) {

        Log.d("Add_Home_loan1111", id);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_CITY_POST+id, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject object) {
                        //  Objs.a.showToast(getContext(), String.valueOf(object));
                        Log.d("Cityvalues11111111111111111 ", String.valueOf(object));
                        try {
                            JSONObject jobj = object.getJSONObject(Params.city);

                            JSONArray ja = jobj.getJSONArray("district_arr");
                            // setMainSpinner(ja);
                            String id_type,district_name;
                            for (int i=0;i<ja.length();i++){

                                JSONObject object1 = ja.getJSONObject(i);

                                id_type = object1.getString("district_id");
                                district_name = object1.getString("district_name");

                                if(id_type.equals(city_id)){
                                    // Objs.a.showToast(mCom, loan_type);
                                    app_city1.setText(district_name);
                                    searched_City = district_name;

                                    Log.e("searched city name","searched_City");

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

    private void makeJsonObjReq1(String loan_cat) {
        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("category_id", loan_cat);
            Log.e(" Appli makeJsonObjReq1", String.valueOf(J));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LOAN_TYPE_POST11, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Add Applican ", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        try {
                            ja = object.getJSONArray("loantypelist_arr");
                            setMainSpinner1(ja);
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

    private void setMainSpinner1(final JSONArray ja) throws JSONException {

        //   SPINNERLIST = new String[ja.length()];


        SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[i] = J.getString("loan_type");

            final List<String> city_buyer_list = new ArrayList<>(Arrays.asList(SPINNERLIST));
            Loantype = new ArrayAdapter<String>(getApplicationContext().getApplicationContext(),
                    R.layout.view_spinner_item, city_buyer_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(getApplicationContext().getAssets(),"AlegreyaSans-Regular.ttf");
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

            Loantype.setDropDownViewResource(R.layout.view_spinner_item);
            loan_type11.setAdapter(Loantype);
            loan_type11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        App = ja.getJSONObject(position).getString("id");
                        CAT_ID = ja.getJSONObject(position).getString("category_id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            loan_type11.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

      /*  for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[i] = J.getString("loan_type");
           *//* String Loan_catagorey = J.getString("category_id");
            if(Loan_catagorey != "2" || Loan_catagorey != "3") {
                SPINNERLIST[i] = J.getString("loan_type");
            }*//*
            //  SPINNERLIST2[i]= J.getString("countryid");

        }


        loan_type1.setItems(SPINNERLIST);*/


      /*  loan_type1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                //Objs.a.showToast(getContext(), item);
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

*/


        /// loadSubLocations(ja.getJSONObject(0).getString("countryid"));
    }


    private void makeJsonObjReq() {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_STATE_POST, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        Log.d("State", object.toString());
                        try {
                            ja = object.getJSONArray("state");
                            setMainSpinner(ja);
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

    private void setMainSpinner(final JSONArray ja) throws JSONException {
        SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[i] = J.getString("state_name");
            final List<String> Profile_List = new ArrayList<>(Arrays.asList(SPINNERLIST));
            //BUYER
            state_profile = new ArrayAdapter<String>(getApplicationContext(), R.layout.view_spinner_item,Profile_List){
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

            state_profile.setDropDownViewResource(R.layout.view_spinner_item);
            address.setAdapter(state_profile);
            address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {

                        State_Loc_uniqueID = ja.getJSONObject(position).getString("state_id");
                        GetStateDetails(State_Loc_uniqueID);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            address.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    imm.hideSoftInputFromWindow(loanamount.getWindowToken(), 0);
                    return false;
                }
            });
        }
    }

    private void GetStateDetails(final String a) {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_CITY_POST+a, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("City", object.toString());

                        try {
                            JSONObject jobj = object.getJSONObject(Params.city);

                            JSONArray ja = jobj.getJSONArray("district_arr");
                            setMainSpinner_State(ja);
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

    private void setMainSpinner_State(final JSONArray ja) throws JSONException {

        Locality = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            Locality[i] = J.getString("district_name");
            final List<String> city_buyer_list = new ArrayList<>(Arrays.asList(Locality));
            city_profile = new ArrayAdapter<String>(
                    getApplicationContext(), R.layout.view_spinner_item, city_buyer_list){
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

            city_profile.setDropDownViewResource(R.layout.view_spinner_item);
            address1.setAdapter(city_profile);
            address1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        City_loc_uniqueID = ja.getJSONObject(position).getString("district_id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            address1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }
        /// loadSubLocations(ja.getJSONObject(0).getString("countryid"));
    }
    /*private void Select_City1(String id) {

        Log.d("Add_Home_loan1111", id);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_CITY_POST+id, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject object) {
                        //  Objs.a.showToast(getContext(), String.valueOf(object));
                        Log.d("Cityvalues11111111111111111 ", String.valueOf(object));
                        try {
                            JSONObject jobj = object.getJSONObject(Params.city);

                            JSONArray ja = jobj.getJSONArray("district_arr");
                            setMainSpinner(ja);
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
    private void setMainSpinner(final JSONArray ja) throws JSONException {
        SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[0]="Select your City";
            SPINNERLIST[i] = J.getString("district_name");
        }

       *//* SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[0]="Select your City";
            SPINNERLIST[i] = J.getString("district_name");
            SPINNERLIST[i] = J.getString("district_name");
            final List<String> city_buyer_list = new ArrayList<>(Arrays.asList(SPINNERLIST));
            city_profile = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item, city_buyer_list){
                public View getView(int position, View convertView, android.view.ViewGroup parent) {
                   // font = Typeface.createFromAsset(getActivity().getAssets(),"AlegreyaSans-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                   // v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                   // v.setTypeface(font);
                    return v;
                }
            };

            city_profile.setDropDownViewResource(R.layout.spinner_item);
            address1.setAdapter(city_profile);
            address1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                      //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        S_city = ja.getJSONObject(position).getString("district_id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            address1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }*//*


        address1.setItems(SPINNERLIST);
        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "Lato-Regular.ttf");
        address1.setTypeface(font);
        address1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                try {
                    S_city = ja.getJSONObject(position).getString("district_id");
                    //   S_state = ja.getJSONObject(position).getString("state_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        address1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(loanamount.getWindowToken(), 0);
                return false;
            }
        });
    }*/



}
