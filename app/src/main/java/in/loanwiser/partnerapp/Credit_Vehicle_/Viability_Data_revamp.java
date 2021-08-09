package in.loanwiser.partnerapp.Credit_Vehicle_;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Screen_revamp_Pl_BL;

public class Viability_Data_revamp extends SimpleActivity {

    CardView pancardview_lay;

    LinearLayout pandetails_txtlay,propert_title,propert_pincode,proprty_type,property_price;

    LinearLayout unsecure_personalloan, unsecure_businessloan,unsecure_residencelay,cost_estimation, office_residence_ly,
            rsidence_pincode,other_income_amount,other_income_amount_self,type_of_emploe,other_income_source_self,type_of_emploe1;

    LinearLayout secured_homeloanlay,securelaplay,secureplot_constructionlay,secureplot_loanlay,secure_btlay,secure_improvementlay,
            secure_extensionlay,vehicledetailslay;

    LinearLayout creditbanklay;
    String CoApplicant_Status;
    JSONObject Coapplicant_object;
    String employee_status1;

    String copancard,codob,cogender,cofather,gomartical,conoofdependes,coeducationqualification,coassets;

    AppCompatTextView copancardtxt,codateofbithtxt,cogendertxt,cofathernametxt,comaritaltxt,
            conooddependents,coeducationqualtxt,coassetstxt;

    String cobus_typeemploymentstr,cobus_typeselfstr,cobus_vocationtypestr,coaboutbustxtstr,cobus_avargeincomestr,
            coannualprofittxtstr,coannualturntxtstr,cobus_numof_monthstr,cobusiness_vintageproofstr,cobusinessincome_proofstr,
            coofficesetupstr,coofc_restypestr,cooffshoppincodestr,cohaveaddressprooftxtstr,codoyoufiletxtstr,cootherincome_selfstr,cootherincome_amount1_selfstr;


    String cosalarymode,comonthincome,cosalaryproof,coepfdeduct,cocompanytype,cocompanyname,
            codesignation,cotypeofemploye,cocompanydoor,cocmpnypin,cocompanyarea,cocurrentexp,cototalexp,cootherincome,cootheramount;


    AppCompatTextView cosalary_mode,comonth_incometxt,cosalaryproof_txt,coepfdeductsalarytxt,salaryprrof,
            cocompanytypetxt,cocompanynametxt,codesignationtxt,cotypeodemployetxt,cocompanydoortxt,
            cocmpny_pintxt,cocompanyarea_txt,cocurrent_exptxt,cototalexp_txt,cootherincometxt,cootherincome_amount1;


    LinearLayout co_other_income_amount;



    AppCompatTextView cobus_typeemployemnt,cobus_typeself,cobus_vocationtype,coaboutbustxt,cobus_avargeincome,
            coannualprofittxt,coannualturntxt,cobus_numof_month,cobusiness_vintageproof,cobusinessincome_proof,coofficesetup,
            coofc_restype,cooffshoppincode,cohaveaddressprooftxt,codoyoufiletxt,cootherincome_self,cootherincome_amount1_self;


    // private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private android.app.AlertDialog progressDialog;
    //Homeloan
    AppCompatTextView pancardtxt,maritaltxt,dateofbithtxt,fathernametxt;
    AppCompatTextView imp_prop_title,imp_prop_pincode,imp_prop_type,imp_prop_cost,imp_prop_price;
    AppCompatTextView prop_identxt,prop_titletxt,prop_pincode,prop_typetxt,prop_pricetxt;
    AppCompatTextView salary_mode,month_incometxt,companytypetxt,companynametxt,designationtxt,cmpny_pintxt,totalexp_txt,companyarea_txt,current_exptxt,salaryproof_txt;

    //Businessloan
    AppCompatTextView bus_typeself,bus_salrycredit,bus_vocationtype,bus_avargeincome,bus_numof_month,business_vintageproof,businessincome_proof, officesetup,
            ofc_restype,offshoppincode,bus_typeemployemnt;


    //Personalloan

    AppCompatTextView per_salary_mode,per_month_incometxt,per_salaryproof_txt,per_companytypetxt,per_companynametxt,
            per_designationtxt,per_cmpny_pintxt,per_companyarea_txt,per_current_exptxt,per_totalexp_txt;

    //Laptype
    AppCompatTextView lap_proptitle,lap_proppincode,lapprop_type,lapprop_price,
            otherincome_amount1,otherincome_amount1_self,rentpaidforhousetxt,liveincurrenttxt,perrestypecodetxt;



    AppCompatTextView gendertxt,nooddependents,educationqualtxt,assetstxt;

    AppCompatTextView epfdeductsalarytxt,curres_addressprofftxt,creditscoretext,emilatetxt,banksalary,
            runningloantxt,witeofftxt,bankpdftxt,doyoufiletxt,haveaddressprooftxt,annualturntxt,annualprofittxt,aboutbustxt;

    AppCompatTextView typeodemployetxt,companydoortxt,otherincometxt,perrespincodetxt,companydoor;




    //Extensionloan_propfields
    AppCompatTextView exten_proptitle, extension_proppincode, extension_prop_type, exten_cost_extement,exten_pro_price;

    // unsecure residence type
    AppCompatTextView unsecure_residence_pincode ,unsecure_resarea ,unsecure_restype;

    //plat+construction
    AppCompatTextView secureplot_cons,secure_ploatoincode,secureplot_prop_type,secureplo_value,secureplot_cost_estimate,
            prop_plot_title,prop_Pincode,prop_plot_identified,prop_plot_cost_of_plot,otherincome,otherincome_self;


    AppCompatTextView regnumtxt,brandtxt,modeltxt,totalkmtxt,agevehicletxt;




    final String url = "https://cscapi.propwiser.com/mobile/partner_loanapi_test.php?call=view_viability_revamp";


    private RequestQueue mRequestQueue;

    private String pancardnumber,dateofbirth,fathername,maritalstatus,gender,qualificationstr,assetsstr,noofdependstr,epfdeductstr;
    private String salarymodes,month_income, cmpny_type,cmpny_name, designation, cmpny_pincode, curr_exp,total_exp,
            income_proof_typestr,vintage_docstr,income_proof_typestr1,is_epf_deduct,rentpaid,perresstr,liveinstr,perrestypestr,currentaddresproof;

    private String typeofemp_str,haveotherincome,haveotheramount,
            ofcstreet,residenctstatus,currentrstypestr,residence_Street,employeproofstr,employeproofid,officailmailid_str;

    LinearLayout liveincurrentlay,permanentrestypelay,permanentrespinlay,rentpaidlay;

    String creditscorestr,emilatestr,banksalstr,runloanstr,witeoffstr,bankpdfstr,addressprof;

    AppCompatTextView employementprooftxtshow,officialmailidtxt;

    LinearLayout employementprooflay,officialmailidlay;




    // propertyvalue

    String prop_iden,prop_prices,prop_pincodes,prop_title, prop_type,cost_estimate,cost_of_land;

    String employee_status,office_setup,vocation,avgincome,typeemploy,office_residence,office_pincode, incprof,
            work_vocationstr,office_setupstr,bus_employment_type,office_setup1;

    String residence_pincode,residence_perarea,residence_type,Applicant_Status,loan_type;
    JSONObject Applicant_object,jsonobj,Property_object,otherincome_details;

    LinearLayout coapplicant_pandetails,coapplicant_employementdeatilslay,coapplicant_selfemployementdeatilslay;

    String area,res_area;

    //New declaration
    AppCompatTextView property_detailhead,property_identexthead,pandetailshead,pancardnumtxt,dateofborthhead,
            fathernametxthead,maritical_statushead,salarycredithead,monthlynethead,salaryprooftexthead,companytypetexthead,
            companynamehead,designationhead,companypincode_head,companyareahead,curenthead,totalworkhead,otherincomethead,other_sourcehead,empdethead,
            residence_detailshead,curreshead,curresareahead,restypehead,securelap_prophead,securelap_proptitle,securelap_proppincode,securelapproptype,
            securelap_price,secureplot_propertytitle,secureplot_pincode,secureplotcons_proptype,secureplot_plotvalue,secureplot_costhead,
            secureplot_propertyhead,secureplotpropiden,secureplotloan_proptitle,secureplot_proppincode,secureplot_proptype,secureplotvalie,
            securebt_propertydetails,securebt_title,securebt_pin,securebt_proptype,securebt_propertyprice,curr_resarea,curr_restype,
            prop_price,imp_propheads,imppin,impproptype,impcost,impprice,extern_prophead,exten_title,exten_pin,exten_proptype,costexten,exten_price,unsecureempdet,
            typeofemp_head,typeself,vochead,avghead,noofbushead,busvinhead,busincomehead,ofcsetuphead,ofcrestypehead,ofcpin,otherincomehead,otheramount;

    AppCompatTextView epfproof_txt;
    LinearLayout epflay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        // setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this,R.layout.activity_viability__data_revamp_vh_l);
        initTools(R.string.viy_check);

        progressDialog = new SpotsDialog(Viability_Data_revamp.this, R.style.Custom);
        pancardview_lay=findViewById(R.id.pancardview_lay);
        unsecure_businessloan=findViewById(R.id.unsecure_businessloan);
        unsecure_personalloan=findViewById(R.id.unsecure_personalloan);
        unsecure_residencelay=findViewById(R.id.unsecure_residencelay);

        epfproof_txt=findViewById(R.id.epfproof_txt);
        epflay=findViewById(R.id.epflay);

        liveincurrentlay=findViewById(R.id.liveincurrentlay);
        permanentrestypelay=findViewById(R.id.permanentrestypelay);
        permanentrespinlay=findViewById(R.id.permanentrespinlay);
        rentpaidlay=findViewById(R.id.rentpaidlay);

        employementprooftxtshow=findViewById(R.id.employementprooftxtshow);
        officialmailidtxt=findViewById(R.id.officialmailidtxt);

        employementprooflay=findViewById(R.id.employementprooflay);
        officialmailidlay=findViewById(R.id.officialmailidlay);

        propert_title=findViewById(R.id.propert_title);

        propert_pincode=findViewById(R.id.propert_pincode);
        proprty_type=findViewById(R.id.proprty_type);
        property_price=findViewById(R.id.property_price);

        //otherincome=findViewById(R.id.otherincome);
        otherincome_self=findViewById(R.id.otherincome_self);
        other_income_amount_self=findViewById(R.id.other_income_amount_self);
        otherincome_amount1=findViewById(R.id.otherincome_amount1);
        otherincome_amount1_self=findViewById(R.id.otherincome_amount1_self);
        other_income_source_self=findViewById(R.id.other_income_source_self);

        rentpaidforhousetxt=findViewById(R.id.rentpaidforhousetxt);
        liveincurrenttxt=findViewById(R.id.liveincurrenttxt);
        perrestypecodetxt=findViewById(R.id.perrestypecodetxt);

        secured_homeloanlay=findViewById(R.id.secured_homeloanlay);
        creditbanklay=findViewById(R.id.creditbanklay);
        secureplot_loanlay=findViewById(R.id.secureplot_loanlay);
        securelaplay=findViewById(R.id.securelaplay);
        secureplot_constructionlay=findViewById(R.id.secureplot_constructionlay);
        cost_estimation=findViewById(R.id.cost_estimation);
        secure_btlay=findViewById(R.id.secure_btlay);
        secure_improvementlay=findViewById(R.id.secure_improvementlay);
        secure_extensionlay=findViewById(R.id.secure_extensionlay);
        vehicledetailslay=findViewById(R.id.vehicledetailslay);


        copancardtxt=findViewById(R.id.copancardtxt);
        codateofbithtxt=findViewById(R.id.codateofbithtxt);
        cogendertxt=findViewById(R.id.cogendertxt);
        cofathernametxt=findViewById(R.id.cofathernametxt);
        comaritaltxt=findViewById(R.id.comaritaltxt);
        conooddependents=findViewById(R.id.conooddependents);
        coeducationqualtxt=findViewById(R.id.coeducationqualtxt);
        coassetstxt=findViewById(R.id.coassetstxt);

        cosalary_mode=findViewById(R.id.cosalary_mode);
        comonth_incometxt=findViewById(R.id.comonth_incometxt);
        cosalaryproof_txt=findViewById(R.id.cosalaryproof_txt);
        coepfdeductsalarytxt=findViewById(R.id.coepfdeductsalarytxt);
        cocompanytypetxt=findViewById(R.id.cocompanytypetxt);
        cocompanynametxt=findViewById(R.id.cocompanynametxt);
        codesignationtxt=findViewById(R.id.codesignationtxt);
        cotypeodemployetxt=findViewById(R.id.cotypeodemployetxt);
        cocompanydoortxt=findViewById(R.id.cocompanydoortxt);
        cocmpny_pintxt=findViewById(R.id.cocmpny_pintxt);
        cocompanyarea_txt=findViewById(R.id.cocompanyarea_txt);
        cocurrent_exptxt=findViewById(R.id.cocurrent_exptxt);
        cototalexp_txt=findViewById(R.id.cototalexp_txt);
        cootherincometxt=findViewById(R.id.cootherincometxt);
        cootherincome_amount1=findViewById(R.id.cootherincome_amount1);
        co_other_income_amount=findViewById(R.id.co_other_income_amount);

        cobus_typeemployemnt=findViewById(R.id.cobus_typeemployemnt);
        cobus_typeself=findViewById(R.id.cobus_typeself);
        cobus_vocationtype=findViewById(R.id.cobus_vocationtype);
        coaboutbustxt=findViewById(R.id.coaboutbustxt);
        cobus_avargeincome=findViewById(R.id.cobus_avargeincome);
        coaboutbustxt=findViewById(R.id.coaboutbustxt);
        coannualprofittxt=findViewById(R.id.coannualprofittxt);
        coannualturntxt=findViewById(R.id.coannualturntxt);
        cobus_numof_month=findViewById(R.id.cobus_numof_month);
        cobusiness_vintageproof=findViewById(R.id.cobusiness_vintageproof);
        cobusinessincome_proof=findViewById(R.id.cobusinessincome_proof);
        coofficesetup=findViewById(R.id.coofficesetup);
        coofc_restype=findViewById(R.id.coofc_restype);
        cooffshoppincode=findViewById(R.id.cooffshoppincode);
        cohaveaddressprooftxt=findViewById(R.id.cohaveaddressprooftxt);
        codoyoufiletxt=findViewById(R.id.codoyoufiletxt);
        cootherincome_self=findViewById(R.id.cootherincome_self);
        cootherincome_amount1_self=findViewById(R.id.cootherincome_amount1_self);




        coapplicant_pandetails=findViewById(R.id.coapplicant_pandetails);
        coapplicant_employementdeatilslay=findViewById(R.id.coapplicant_employementdeatilslay);
        coapplicant_selfemployementdeatilslay=findViewById(R.id.coapplicant_selfemployementdeatilslay);

        //vehicle details
        regnumtxt=findViewById(R.id.regnumtxt);
        brandtxt=findViewById(R.id.brandtxt);
        modeltxt=findViewById(R.id.modeltxt);
        totalkmtxt=findViewById(R.id.totalkmtxt);
        agevehicletxt=findViewById(R.id.agevehicletxt);







        office_residence_ly=findViewById(R.id.office_residence_ly);
        rsidence_pincode=findViewById(R.id.rsidence_pincode);
        other_income_amount=findViewById(R.id.other_income_amount);
        type_of_emploe=findViewById(R.id.type_of_emploe);
        type_of_emploe1=findViewById(R.id.type_of_emploe1);



        prop_plot_title=findViewById(R.id.prop_plot_title);
        prop_Pincode=findViewById(R.id.prop_Pincode);
        prop_plot_identified=findViewById(R.id.prop_plot_identified);
        prop_plot_cost_of_plot=findViewById(R.id.prop_plot_cost_of_plot);



        imp_prop_title=findViewById(R.id.imp_prop_title);
        imp_prop_pincode=findViewById(R.id.imp_prop_pincode);
        imp_prop_type=findViewById(R.id.imp_prop_type);
        imp_prop_cost=findViewById(R.id.imp_prop_cost);
        imp_prop_price=findViewById(R.id.imp_prop_price);

        pandetails_txtlay=findViewById(R.id.pandetails_txtlay);

        //Pandetails Id typecasting
        pancardtxt=findViewById(R.id.pancardtxt);
        dateofbithtxt=findViewById(R.id.dateofbithtxt);
        fathernametxt=findViewById(R.id.fathernametxt);
        maritaltxt=findViewById(R.id.maritaltxt);

        //propertyhomeloan typecasting
        prop_identxt=findViewById(R.id.prop_identxt);
        prop_titletxt=findViewById(R.id.prop_titletxt);
        prop_pincode=findViewById(R.id.prop_pincode);
        prop_pricetxt=findViewById(R.id.prop_pricetxt);
        prop_typetxt=findViewById(R.id.prop_typetxt);

        //Employmentdetails Type casting
        salary_mode=findViewById(R.id.salary_mode);
        month_incometxt=findViewById(R.id.month_incometxt);
        companytypetxt=findViewById(R.id.companytypetxt);
        companynametxt=findViewById(R.id.companynametxt);
        designationtxt=findViewById(R.id.designationtxt);
        cmpny_pintxt=findViewById(R.id.cmpny_pintxt);
        totalexp_txt=findViewById(R.id.totalexp_txt);
        companyarea_txt=findViewById(R.id.companyarea_txt);
        current_exptxt=findViewById(R.id.current_exptxt);
        salaryproof_txt=findViewById(R.id.salaryproof_txt);

        //Businessloan_typecasting

        bus_typeself=findViewById(R.id.bus_typeself);
        bus_salrycredit=findViewById(R.id.bus_salrycredit);
        bus_vocationtype=findViewById(R.id.bus_vocationtype);
        bus_avargeincome=findViewById(R.id.bus_avargeincome);
        bus_numof_month=findViewById(R.id.bus_numof_month);
        business_vintageproof=findViewById(R.id.business_vintageproof);
        businessincome_proof=findViewById(R.id.businessincome_proof);
        officesetup=findViewById(R.id.officesetup);
        ofc_restype=findViewById(R.id.ofc_restype);
        offshoppincode=findViewById(R.id.offshoppincode);
        bus_typeemployemnt=findViewById(R.id.bus_typeemployemnt);

        //lap property type
        lap_proptitle=findViewById(R.id.lap_proptitle);
        lap_proppincode=findViewById(R.id.lap_proppincode);
        lapprop_type=findViewById(R.id.lapprop_type);
        lapprop_price=findViewById(R.id.lapprop_price);

        gendertxt=findViewById(R.id.gendertxt);
        educationqualtxt=findViewById(R.id.educationqualtxt);
        assetstxt=findViewById(R.id.assetstxt);
        nooddependents=findViewById(R.id.nooddependents);

        typeodemployetxt=findViewById(R.id.typeodemployetxt);
        companydoortxt=findViewById(R.id.companydoortxt);
        companydoor=findViewById(R.id.companydoor);
        otherincometxt=findViewById(R.id.otherincometxt);
        perrespincodetxt=findViewById(R.id.perrespincodetxt);

        epfdeductsalarytxt=findViewById(R.id.epfdeductsalarytxt);
        curres_addressprofftxt=findViewById(R.id.curres_addressprofftxt);
        creditscoretext=findViewById(R.id.creditscoretext);
        emilatetxt=findViewById(R.id.emilatetxt);
        banksalary=findViewById(R.id.banksalary);
        runningloantxt=findViewById(R.id.runningloantxt);
        witeofftxt=findViewById(R.id.witeofftxt);
        bankpdftxt=findViewById(R.id.bankpdftxt);

        //extensionloan_property type
        exten_proptitle=findViewById(R.id.exten_proptitle);
        extension_proppincode=findViewById(R.id.extension_proppincode);
        extension_prop_type=findViewById(R.id.extension_prop_type);
        exten_cost_extement=findViewById(R.id.exten_cost_extement);
        exten_pro_price=findViewById(R.id.exten_pro_price);

        //unsecures residence type
        unsecure_residence_pincode=findViewById(R.id.unsecure_residence_pincode);
        unsecure_resarea=findViewById(R.id.unsecure_resarea);
        unsecure_restype=findViewById(R.id.unsecure_restype);

        //Plat +Constuction loan
        secureplo_value=findViewById(R.id.secureplo_value);
        secureplot_cons=findViewById(R.id.secureplot_cons);
        secure_ploatoincode=findViewById(R.id.secure_ploatoincode);
        secureplot_prop_type=findViewById(R.id.secureplot_prop_type);
        secureplot_cost_estimate=findViewById(R.id.secureplot_cost_estimate);


        //New added textview
        property_detailhead=findViewById(R.id.property_detailhead);
        property_identexthead=findViewById(R.id.property_identexthead);

        pandetailshead=findViewById(R.id.pandetailshead);
        pancardnumtxt=findViewById(R.id.pancardnumtxt);
        dateofborthhead=findViewById(R.id.dateofborthhead);
        fathernametxthead=findViewById(R.id.fathernametxthead);
        maritical_statushead=findViewById(R.id.maritical_statushead);

        salarycredithead=findViewById(R.id.salarycredithead);
        monthlynethead=findViewById(R.id.monthlynethead);
        salaryprooftexthead=findViewById(R.id.salaryprooftexthead);
        companytypetexthead=findViewById(R.id.companytypetexthead);
        companynamehead=findViewById(R.id.companynamehead);
        designationhead=findViewById(R.id.designationhead);
        companypincode_head=findViewById(R.id.companypincode_head);
        companyareahead=findViewById(R.id.companyareahead);
        curenthead=findViewById(R.id.curenthead);
        totalworkhead=findViewById(R.id.totalworkhead);
        otherincomethead=findViewById(R.id.otherincomethead);
        other_sourcehead=findViewById(R.id.other_sourcehead);
        empdethead=findViewById(R.id.empdethead);
        residence_detailshead=findViewById(R.id.residence_detailshead);
        curreshead=findViewById(R.id.curreshead);
        curresareahead=findViewById(R.id.curresareahead);
        restypehead=findViewById(R.id.restypehead);
        securelap_prophead=findViewById(R.id.securelap_prophead);
        securelap_proptitle=findViewById(R.id.securelap_proptitle);
        securelap_proppincode=findViewById(R.id.securelap_proppincode);
        securelapproptype=findViewById(R.id.securelapproptype);
        securelap_price=findViewById(R.id.securelap_price);
        secureplot_propertytitle=findViewById(R.id.secureplot_propertytitle);
        secureplot_pincode=findViewById(R.id.secureplot_pincode);
        secureplotcons_proptype=findViewById(R.id.secureplotcons_proptype);
        secureplot_plotvalue=findViewById(R.id.secureplot_plotvalue);
        secureplot_costhead=findViewById(R.id.secureplot_costhead);
        secureplot_propertyhead=findViewById(R.id.secureplot_propertyhead);
        secureplotpropiden=findViewById(R.id.secureplotpropiden);
        secureplotloan_proptitle=findViewById(R.id.secureplotloan_proptitle);
        secureplot_proppincode=findViewById(R.id.secureplot_proppincode);
        secureplot_proptype=findViewById(R.id.secureplot_proptype);
        secureplotvalie=findViewById(R.id.secureplotvalie);
        prop_plot_cost_of_plot=findViewById(R.id.prop_plot_cost_of_plot);
        securebt_propertydetails=findViewById(R.id.securebt_propertydetails);
        securebt_title=findViewById(R.id.securebt_title);
        securebt_pin=findViewById(R.id.securebt_pin);
        securebt_proptype=findViewById(R.id.securebt_proptype);
        securebt_propertyprice=findViewById(R.id.securebt_propertyprice);
        curr_resarea=findViewById(R.id.curr_resarea);
        curr_restype=findViewById(R.id.curr_restype);
        prop_price=findViewById(R.id.prop_price);

        imp_propheads=findViewById(R.id.imp_propheads);
        imppin=findViewById(R.id.imppin);
        impproptype=findViewById(R.id.impproptype);
        impcost=findViewById(R.id.impcost);
        impprice=findViewById(R.id.impprice);

        extern_prophead=findViewById(R.id.extern_prophead);
        exten_title=findViewById(R.id.exten_titles);
        exten_pin=findViewById(R.id.exten_pin);
        exten_proptype=findViewById(R.id.exten_proptype);
        costexten=findViewById(R.id.costexten);
        exten_price=findViewById(R.id.exten_price);
        unsecureempdet=findViewById(R.id.unsecureempdet);
        typeofemp_head=findViewById(R.id.typeofemp_head);
        typeself=findViewById(R.id.typeself);
        vochead=findViewById(R.id.vochead);
        avghead=findViewById(R.id.avghead);
        noofbushead=findViewById(R.id.noofbushead);
        busvinhead=findViewById(R.id.busvinhead);
        busincomehead=findViewById(R.id.busincomehead);
        ofcsetuphead=findViewById(R.id.ofcsetuphead);
        ofcrestypehead=findViewById(R.id.ofcrestypehead);
        ofcpin=findViewById(R.id.ofcpin);
        otherincomehead=findViewById(R.id.otherincomehead);
        otheramount=findViewById(R.id.otheramount);










       /* Typeface font = Typeface.createFromAsset(Viability_Data_revamp.this.getAssets(), "segoe_ui.ttf");
        property_detailhead.setTypeface(font);
        property_identexthead.setTypeface(font);
        prop_identxt.setTypeface(font);
        prop_titletxt.setTypeface(font);
        prop_pincode.setTypeface(font);
        prop_pricetxt.setTypeface(font);
        prop_typetxt.setTypeface(font);
        pandetailshead.setTypeface(font);
        pancardnumtxt.setTypeface(font);
        pancardtxt.setTypeface(font);
        dateofborthhead.setTypeface(font);
        dateofbithtxt.setTypeface(font);
        fathernametxthead.setTypeface(font);
        fathernametxt.setTypeface(font);
        maritical_statushead.setTypeface(font);
        maritaltxt.setTypeface(font);
        salarycredithead.setTypeface(font);
        salary_mode.setTypeface(font);
        monthlynethead.setTypeface(font);
        month_incometxt.setTypeface(font);
        salaryprooftexthead.setTypeface(font);
        salaryproof_txt.setTypeface(font);
        companytypetexthead.setTypeface(font);
        companytypetxt.setTypeface(font);
        companynamehead.setTypeface(font);
        companynametxt.setTypeface(font);
        designationhead.setTypeface(font);
        designationtxt.setTypeface(font);
        companypincode_head.setTypeface(font);
        cmpny_pintxt.setTypeface(font);
        companyareahead.setTypeface(font);
        companyarea_txt.setTypeface(font);
        curenthead.setTypeface(font);
        current_exptxt.setTypeface(font);
        totalworkhead.setTypeface(font);
        totalexp_txt.setTypeface(font);
        otherincomethead.setTypeface(font);
        otherincome.setTypeface(font);
        other_sourcehead.setTypeface(font);
        otherincome_amount1.setTypeface(font);
        empdethead.setTypeface(font);
        residence_detailshead.setTypeface(font);
        curreshead.setTypeface(font);
        unsecure_residence_pincode.setTypeface(font);
        curresareahead.setTypeface(font);
        unsecure_resarea.setTypeface(font);
        restypehead.setTypeface(font);
        unsecure_restype.setTypeface(font);
        securelap_prophead.setTypeface(font);
        securelap_proptitle.setTypeface(font);
        lap_proptitle.setTypeface(font);
        securelap_proppincode.setTypeface(font);
        lap_proppincode.setTypeface(font);
        securelapproptype.setTypeface(font);
        lapprop_type.setTypeface(font);
        securelap_price.setTypeface(font);
        lapprop_price.setTypeface(font);
        secureplot_propertytitle.setTypeface(font);
        secureplot_cons.setTypeface(font);
        secureplot_pincode.setTypeface(font);
        secure_ploatoincode.setTypeface(font);
        secureplotcons_proptype.setTypeface(font);
        secureplot_prop_type.setTypeface(font);
        secureplot_plotvalue.setTypeface(font);
        secureplo_value.setTypeface(font);
        secureplot_costhead.setTypeface(font);
        secureplot_cost_estimate.setTypeface(font);
        secureplot_propertyhead.setTypeface(font);
        secureplotpropiden.setTypeface(font);
        secureplotloan_proptitle.setTypeface(font);
        prop_plot_title.setTypeface(font);
        secureplot_proppincode.setTypeface(font);
        prop_Pincode.setTypeface(font);
        secureplot_proptype.setTypeface(font);
        prop_plot_identified.setTypeface(font);
        secureplotvalie.setTypeface(font);
        prop_plot_cost_of_plot.setTypeface(font);
        securebt_propertydetails.setTypeface(font);
        securebt_title.setTypeface(font);
        curr_resarea.setTypeface(font);
        securebt_pin.setTypeface(font);
        curr_restype.setTypeface(font);
        securebt_proptype.setTypeface(font);
        securebt_propertyprice.setTypeface(font);
        prop_price.setTypeface(font);
        imp_propheads.setTypeface(font);
        imp_prop_title.setTypeface(font);
        imppin.setTypeface(font);
        imp_prop_pincode.setTypeface(font);
        imp_prop_type.setTypeface(font);
        impproptype.setTypeface(font);
        impcost.setTypeface(font);
        imp_prop_cost.setTypeface(font);
        impprice.setTypeface(font);
        imp_prop_price.setTypeface(font);
        extern_prophead.setTypeface(font);
        exten_title.setTypeface(font);
        exten_proptitle.setTypeface(font);
        exten_pin.setTypeface(font);
        extension_proppincode.setTypeface(font);
        exten_proptype.setTypeface(font);
        extension_prop_type.setTypeface(font);
        costexten.setTypeface(font);
        exten_cost_extement.setTypeface(font);
        exten_price.setTypeface(font);
        exten_pro_price.setTypeface(font);
        exten_title.setTypeface(font);
        unsecureempdet.setTypeface(font);
        typeofemp_head.setTypeface(font);
        bus_typeemployemnt.setTypeface(font);
        typeself.setTypeface(font);
        bus_typeself.setTypeface(font);
        vochead.setTypeface(font);
        bus_vocationtype.setTypeface(font);
        avghead.setTypeface(font);
        bus_avargeincome.setTypeface(font);
        noofbushead.setTypeface(font);
        bus_numof_month.setTypeface(font);
        busvinhead.setTypeface(font);
        business_vintageproof.setTypeface(font);
        busincomehead.setTypeface(font);
        businessincome_proof.setTypeface(font);
        ofcsetuphead.setTypeface(font);
        officesetup.setTypeface(font);
        ofcrestypehead.setTypeface(font);
        ofc_restype.setTypeface(font);
        ofcpin.setTypeface(font);
        offshoppincode.setTypeface(font);
        otherincomehead.setTypeface(font);
        otherincome_self.setTypeface(font);
        otheramount.setTypeface(font);
        otherincome_amount1_self.setTypeface(font);*/












        Viabilityshow();

    }

    private void Viabilityshow() {
        progressDialog.show();
        JSONObject jsonObject =new JSONObject();

        mRequestQueue = Volley.newRequestQueue(this);
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            Log.i("TAG", "Viabilityshow: "+J.toString());
            //  Log.e("TAG", "viability_transcation: "+Pref.getTRANSACTIONID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.URL_Viability_Detail_Show, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("viablity_response", object.toString());
                        progressDialog.dismiss();

                        try {
                            jsonobj = object.getJSONObject("response");
                            Applicant_Status =jsonobj.optString("applicant_status");
                            Applicant_object = jsonobj.getJSONObject("applicant_data");
                            loan_type=jsonobj.optString("loan_type");
                            Log.i("TAG", "Loantype: "+loan_type);
                            if (loan_type.equals("21")) {
                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.optString("pan_no");
                                dateofbirth = Applicant_object.optString("member_dob");
                                fathername = Applicant_object.optString("father_name");
                                maritalstatus = Applicant_object.optString("marital_statusstr");

                                //Employement Details
                                month_income = Applicant_object.optString("monthly_income");
                                salarymodes = Applicant_object.optString("salary_modestr");
                                cmpny_type = Applicant_object.optString("company_typestr");
                                cmpny_name = Applicant_object.optString("company_name");
                                designation = Applicant_object.optString("designation");
                                cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                curr_exp = Applicant_object.optString("working_experience");
                                total_exp = Applicant_object.optString("total_experience");
                                income_proof_typestr = Applicant_object.optString("income_proof_typestr");


                                //working Area array
                                JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                if (area_array.length() > 0) {
                                    try {
                                        JSONObject J = area_array.getJSONObject(0);
                                        area = J.optString("area");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                //Residenence Pincode
                                residence_pincode = Applicant_object.optString("per_pincode");
                                //residence_perarea=Applicant_object.optString("per_area");
                                residence_type = Applicant_object.optString("resident_statusstr");
                                //residence area array value

                                JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                if (res_array.length() > 0) {
                                    try {
                                        JSONObject J = res_array.getJSONObject(0);
                                        res_area = J.optString("area");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                unsecure_personalloan.setVisibility(View.VISIBLE);
                                unsecure_residencelay.setVisibility(View.VISIBLE);
                                salary_mode.setText(salarymodes);
                                month_incometxt.setText(month_income);
                                companytypetxt.setText(cmpny_type);
                                companynametxt.setText(cmpny_name);
                                designationtxt.setText(designation);
                                cmpny_pintxt.setText(cmpny_pincode);


                                String year,month,year1,month1;

                                year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                month = String.valueOf(Integer.parseInt(total_exp)  % 12);

                                // totalexp_txt.setText(total_exp);
                                totalexp_txt.setText(year +" year ,"+ month+" month ");

                                year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                month1 = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                //current_exptxt.setText(curr_exp);
                                current_exptxt.setText(year1 +" year ,"+ month1+" month ");

                                companyarea_txt.setText(area);


                                otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                String income_type = otherincome_details.optString("income_type");
                                String income_typestr = otherincome_details.optString("income_typestr");
                                if( income_type.equals("4"))
                                {
                                    otherincome.setText(income_typestr);
                                    other_income_amount.setVisibility(View.GONE);

                                }else
                                {
                                    String otherincome_details1 = otherincome_details.optString("income_amount");
                                    otherincome.setText(income_typestr);
                                    otherincome_amount1.setText(otherincome_details1);
                                    other_income_amount.setVisibility(View.VISIBLE);

                                }

                                pancardtxt.setText(pancardnumber);
                                dateofbithtxt.setText(dateofbirth);
                                fathernametxt.setText(fathername);
                                maritaltxt.setText(maritalstatus);
                                bus_salrycredit.setText(salarymodes);
                                String formattedString = income_proof_typestr.toString()
                                        .replace("[", "")  //remove the right bracket
                                        .replace("]", "")
                                        .replaceAll("\"", "")
                                        .trim();
                                salaryproof_txt.setText(formattedString);

                                unsecure_residence_pincode.setText(residence_pincode);
                                unsecure_resarea.setText(res_area);
                                unsecure_restype.setText(residence_type);
                            }
                            //Business loan Setup
                            else if (loan_type.equals("20")) {

                                unsecure_businessloan.setVisibility(View.VISIBLE);
                                unsecure_residencelay.setVisibility(View.VISIBLE);
                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.optString("pan_no");
                                dateofbirth = Applicant_object.optString("member_dob");
                                fathername = Applicant_object.optString("father_name");
                                maritalstatus = Applicant_object.optString("marital_statusstr");
                                curr_exp = Applicant_object.optString("working_experience");

                                vintage_docstr = Applicant_object.optString("vintage_docstr");
                                income_proof_typestr1=Applicant_object.optString("income_proof_typestr");

                                //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                //String text = income.toString().replace("[", "").replace("]", "");
                                JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                if (res_array.length() > 0) {
                                    try {
                                        JSONObject J = res_array.getJSONObject(0);
                                        res_area = J.optString("area");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                employee_status=Applicant_object.optString("employe_status");
                                office_setup1=Applicant_object.optString("office_setup");

                                if(office_setup1.equals("1") || office_setup1.equals("3"))
                                {
                                    office_residence_ly.setVisibility(View.GONE);
                                    rsidence_pincode.setVisibility(View.GONE);
                                }else
                                {
                                    office_residence_ly.setVisibility(View.VISIBLE);
                                    rsidence_pincode.setVisibility(View.VISIBLE);
                                }

                                office_setup=Applicant_object.optString("office_setupstr");
                                month_income = Applicant_object.optString("monthly_income");
                                //  salarymodes = Applicant_object.optString("salary_modestr");
                                vocation=Applicant_object.optString("vocation");
                                bus_employment_type=Applicant_object.optString("bus_employment_type");


                                typeemploy=Applicant_object.optString("bus_employment_typestr");
                                Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                office_residence=Applicant_object.optString("office_res");
                                office_pincode=Applicant_object.optString("ofc_pincode");
                                residence_pincode=Applicant_object.optString("per_pincode");
                                residence_perarea=Applicant_object.optString("per_area");
                                residence_type=Applicant_object.optString("resident_statusstr");

                                if(bus_employment_type.equals("1"))
                                {
                                    work_vocationstr=Applicant_object.optString("bus_vocationstr");
                                }else if(bus_employment_type.equals("2"))
                                {
                                    work_vocationstr=Applicant_object.optString("work_vocationstr");
                                }else {

                                    work_vocationstr=Applicant_object.optString("work_vocationstr");
                                }

                                otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                String income_type = otherincome_details.optString("income_type");
                                String income_typestr = otherincome_details.optString("income_typestr");
                                if( income_type.equals("4"))
                                {
                                    otherincome_self.setText(income_typestr);
                                    other_income_amount_self.setVisibility(View.GONE);

                                }else
                                {
                                    String otherincome_details1 = otherincome_details.optString("income_amount");
                                    otherincome_self.setText(income_typestr);
                                    otherincome_amount1_self.setText(otherincome_details1);
                                    other_income_amount_self.setVisibility(View.VISIBLE);


                                }

                                office_setupstr=Applicant_object.optString("resident_statusstr");
                                pancardtxt.setText(pancardnumber);
                                dateofbithtxt.setText(dateofbirth);
                                fathernametxt.setText(fathername);
                                maritaltxt.setText(maritalstatus);
                                bus_typeemployemnt.setText("Business Self Employed");
                                bus_salrycredit.setText(salarymodes);
                                bus_typeself.setText(typeemploy);
                                officesetup.setText(office_setup);
                                bus_vocationtype.setText(work_vocationstr);
                                bus_avargeincome.setText(month_income);

                                String year,month,year1,month1;

                                year = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                month = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                bus_numof_month.setText(year +" year ,"+ month+" month ");
                                // bus_numof_month.setText(curr_exp);
                                ofc_restype.setText(office_setupstr);
                                offshoppincode.setText(office_pincode);
                                String formattedString = vintage_docstr.toString()
                                        .replace("[", "")  //remove the right bracket
                                        .replace("]", "")
                                        .replaceAll("\"", "")
                                        .trim();

                                //business_vintageproof.setText(vintage_docstr);
                                business_vintageproof.setText(formattedString);
                                String formattedStrings = income_proof_typestr1.toString()
                                        .replace("[", "")  //remove the right bracket
                                        .replace("]", "")
                                        .replaceAll("\"", "")
                                        .trim();

                                businessincome_proof.setText(formattedStrings);
                                // businessincome_proof.setText(income);
                                unsecure_resarea.setText(res_area);
                                unsecure_restype.setText(residence_type);
                                unsecure_residence_pincode.setText(residence_pincode);

                            }
                            //Home loan type
                            else if (loan_type.equals("1")){
                                Log.i("TAG", "Home loan: "+loan_type);
                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                Property_object=Applicant_object.getJSONObject("Property_details");
                                employee_status=Applicant_object.optString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.optString("pan_no");
                                dateofbirth = Applicant_object.optString("member_dob");
                                fathername = Applicant_object.optString("father_name");
                                maritalstatus = Applicant_object.optString("marital_statusstr");
                                //Salaried type employee_status 1
                                if (employee_status.equals("1")){

                                    secured_homeloanlay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    prop_iden = Property_object.optString("prop_identifiedstr");

                                    if(prop_iden.equals("No"))
                                    {
                                        prop_identxt.setText(prop_iden);

                                        propert_title.setVisibility(View.GONE);
                                        propert_pincode.setVisibility(View.GONE);
                                        proprty_type.setVisibility(View.GONE);
                                        property_price.setVisibility(View.GONE);


                                    }else
                                    {

                                        propert_title.setVisibility(View.VISIBLE);
                                        propert_pincode.setVisibility(View.VISIBLE);
                                        proprty_type.setVisibility(View.VISIBLE);
                                        property_price.setVisibility(View.VISIBLE);

                                        prop_iden = Property_object.optString("prop_identifiedstr");
                                        prop_prices = Property_object.optString("property_value");
                                        prop_pincodes = Property_object.optString("pincode");
                                        prop_title = Property_object.optString("prop_titlestr");
                                        prop_type = Property_object.optString("prop_typestr");
                                        cost_estimate=Property_object.optString("cost_of_construction");

                                        prop_identxt.setText(prop_iden);
                                        prop_pincode.setText(prop_pincodes);
                                        prop_pricetxt.setText(prop_prices);
                                        prop_typetxt.setText(prop_type);
                                        prop_titletxt.setText(prop_title);
                                    }



                                    //Employement Details
                                    month_income = Applicant_object.optString("monthly_income");
                                    salarymodes = Applicant_object.optString("salary_modestr");
                                    cmpny_type = Applicant_object.optString("company_typestr");
                                    cmpny_name = Applicant_object.optString("company_name");
                                    designation = Applicant_object.optString("designation");
                                    cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    total_exp = Applicant_object.optString("total_experience");


                                    income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    //  salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);

                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");



                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_salrycredit.setText(salarymodes);
                                    // companyarea_txt.setText(area);

                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            String workarea = J.optString("area");
                                            companyarea_txt.setText(workarea);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    salary_mode.setText(salarymodes);
                                    month_incometxt.setText(month_income);
                                    companytypetxt.setText(cmpny_type);
                                    companynametxt.setText(cmpny_name);
                                    designationtxt.setText(designation);
                                    cmpny_pintxt.setText(cmpny_pincode);



                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(total_exp)  % 12);

                                    // totalexp_txt.setText(total_exp);
                                    totalexp_txt.setText(year +" year ,"+ month+" month ");

                                    year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month1 = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    //current_exptxt.setText(curr_exp);
                                    current_exptxt.setText(year1 +" year ,"+ month1+" month ");

                                    // companyarea_txt.setText(area);

                                    //Residenence Pincode

                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    //residence_perarea=Applicant_object.optString("per_area");

                                    //residence area array value
                                    // companyarea_txt.setText(area);
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome.setText(income_typestr);
                                        otherincome_amount1.setText(otherincome_details1);
                                        other_income_amount.setVisibility(View.VISIBLE);


                                    }

                                    unsecure_resarea.setText(res_area);
                                    unsecure_residence_pincode.setText(residence_pincode);
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    unsecure_restype.setText(residence_type);

                                }
                                //self employedd type
                                else{
                                    secured_homeloanlay.setVisibility(View.VISIBLE);
                                    unsecure_businessloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);

                                    prop_iden = Property_object.optString("prop_identifiedstr");

                                    if(prop_iden.equals("No"))
                                    {
                                        prop_identxt.setText(prop_iden);

                                        propert_title.setVisibility(View.GONE);
                                        propert_pincode.setVisibility(View.GONE);
                                        proprty_type.setVisibility(View.GONE);
                                        property_price.setVisibility(View.GONE);

                                    }else
                                    {

                                        propert_title.setVisibility(View.VISIBLE);
                                        propert_pincode.setVisibility(View.VISIBLE);
                                        proprty_type.setVisibility(View.VISIBLE);
                                        property_price.setVisibility(View.VISIBLE);

                                        prop_prices = Property_object.optString("property_value");
                                        prop_pincodes = Property_object.optString("pincode");
                                        prop_title = Property_object.optString("prop_titlestr");
                                        prop_type = Property_object.optString("prop_typestr");
                                        cost_estimate=Property_object.optString("cost_of_construction");

                                        prop_identxt.setText(prop_iden);
                                        prop_pincode.setText(prop_pincodes);
                                        prop_pricetxt.setText(prop_prices);
                                        prop_typetxt.setText(prop_type);
                                        prop_titletxt.setText(prop_title);
                                    }


                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    vintage_docstr = Applicant_object.optString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.optString("employe_status");
                                    office_setup=Applicant_object.optString("office_setupstr");
                                    month_income = Applicant_object.optString("monthly_income");
                                    //  salarymodes = Applicant_object.optString("salary_modestr");
                                    vocation=Applicant_object.optString("vocation");
                                    typeemploy=Applicant_object.optString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.optString("office_res");
                                    office_pincode=Applicant_object.optString("ofc_pincode");
                                    residence_pincode=Applicant_object.optString("per_pincode");
                                    residence_perarea=Applicant_object.optString("per_area");
                                    residence_type=Applicant_object.optString("resident_statusstr");
                                    work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    office_setupstr=Applicant_object.optString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.optString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.optString("vocation_str");
                                    }

                                    office_setup1=Applicant_object.optString("office_setup");

                                    if(office_setup1.equals("1") || office_setup1.equals("3"))
                                    {
                                        office_residence_ly.setVisibility(View.GONE);
                                        rsidence_pincode.setVisibility(View.GONE);
                                    }else
                                    {
                                        office_residence_ly.setVisibility(View.VISIBLE);
                                        rsidence_pincode.setVisibility(View.VISIBLE);
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");

                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome_self.setText(income_typestr);
                                        otherincome_amount1_self.setText(otherincome_details1);
                                        other_income_amount_self.setVisibility(View.VISIBLE);


                                    }

                                    bus_vocationtype.setText(work_vocationstr);
                                    bus_avargeincome.setText(month_income);
                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    bus_numof_month.setText(year +" year ,"+ month+" month ");
                                    ofc_restype.setText(office_setupstr);
                                    offshoppincode.setText(office_pincode);
                                    business_vintageproof.setText(vintage_docstr);
                                    businessincome_proof.setText(income);

                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }

                            }

                            else if (loan_type.equals("2") ||loan_type.equals("5") ||loan_type.equals("10")){
                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                Property_object=Applicant_object.getJSONObject("Property_details");
                                employee_status=Applicant_object.optString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.optString("pan_no");
                                dateofbirth = Applicant_object.optString("member_dob");
                                fathername = Applicant_object.optString("father_name");
                                maritalstatus = Applicant_object.optString("marital_statusstr");

                                //Salaried mode

                                if (employee_status.equals("1")){
                                    securelaplay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    prop_prices = Property_object.optString("property_value");
                                    prop_pincodes = Property_object.optString("pincode");
                                    prop_title = Property_object.optString("prop_titlestr");
                                    prop_type = Property_object.optString("prop_typestr");


                                    lap_proppincode.setText(prop_pincodes);
                                    lapprop_price.setText(prop_prices);
                                    lapprop_type.setText(prop_type);
                                    lap_proptitle.setText(prop_title);

                                    //Employement Details
                                    month_income = Applicant_object.optString("monthly_income");
                                    salarymodes = Applicant_object.optString("salary_modestr");
                                    cmpny_type = Applicant_object.optString("company_typestr");
                                    cmpny_name = Applicant_object.optString("company_name");
                                    designation = Applicant_object.optString("designation");
                                    cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    total_exp = Applicant_object.optString("total_experience");
                                    income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    // salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);
                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            String workarea = J.optString("area");
                                            companyarea_txt.setText(workarea);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    /*income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                    salaryproof_txt.setText(income_proof_typestr);*/

                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");

                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_salrycredit.setText(salarymodes);
                                    // companyarea_txt.setText(area);


                                    salary_mode.setText(salarymodes);
                                    month_incometxt.setText(month_income);
                                    companytypetxt.setText(cmpny_type);
                                    companynametxt.setText(cmpny_name);
                                    designationtxt.setText(designation);
                                    cmpny_pintxt.setText(cmpny_pincode);

                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(total_exp)  % 12);

                                    // totalexp_txt.setText(total_exp);
                                    totalexp_txt.setText(year +" year ,"+ month+" month ");

                                    year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month1 = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    //current_exptxt.setText(curr_exp);
                                    current_exptxt.setText(year1 +" year ,"+ month1+" month ");

                                    //  companyarea_txt.setText(area);


                                    //Residenence Pincode

                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    //residence_perarea=Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome.setText(income_typestr);
                                        otherincome_amount1.setText(otherincome_details1);
                                        other_income_amount.setVisibility(View.VISIBLE);

                                    }

                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }else{
                                    securelaplay.setVisibility(View.VISIBLE);
                                    unsecure_businessloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);

                                    prop_prices = Property_object.optString("property_value");
                                    prop_pincodes = Property_object.optString("pincode");
                                    prop_title = Property_object.optString("prop_titlestr");
                                    prop_type = Property_object.optString("prop_typestr");


                                    lap_proppincode.setText(prop_pincodes);
                                    lapprop_price.setText(prop_prices);
                                    lapprop_type.setText(prop_type);
                                    lap_proptitle.setText(prop_title);

                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    vintage_docstr = Applicant_object.optString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.optString("employe_status");
                                    office_setup=Applicant_object.optString("office_setupstr");
                                    month_income = Applicant_object.optString("monthly_income");
                                    //  salarymodes = Applicant_object.optString("salary_modestr");
                                    vocation=Applicant_object.optString("vocation");
                                    typeemploy=Applicant_object.optString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.optString("office_res");
                                    office_pincode=Applicant_object.optString("ofc_pincode");
                                    residence_pincode=Applicant_object.optString("per_pincode");
                                    residence_perarea=Applicant_object.optString("per_area");
                                    residence_type=Applicant_object.optString("resident_statusstr");
                                    work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    office_setupstr=Applicant_object.optString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.optString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.optString("vocation_str");
                                    }

                                    office_setup1=Applicant_object.optString("office_setup");

                                    if(office_setup1.equals("1") || office_setup1.equals("3"))
                                    {
                                        office_residence_ly.setVisibility(View.GONE);
                                        rsidence_pincode.setVisibility(View.GONE);
                                    }else
                                    {
                                        office_residence_ly.setVisibility(View.VISIBLE);
                                        rsidence_pincode.setVisibility(View.VISIBLE);
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");

                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome_self.setText(income_typestr);
                                        otherincome_amount1_self.setText(otherincome_details1);
                                        other_income_amount_self.setVisibility(View.VISIBLE);


                                    }

                                    bus_vocationtype.setText(work_vocationstr);
                                    bus_avargeincome.setText(month_income);
                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    bus_numof_month.setText(year +" year ,"+ month+" month ");
                                    ofc_restype.setText(office_setupstr);
                                    offshoppincode.setText(office_pincode);
                                    business_vintageproof.setText(vintage_docstr);
                                    businessincome_proof.setText(income);
                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }

                            }

                            //Plat +constuctionloan

                            else if (loan_type.equals("3")){

                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                Property_object=Applicant_object.getJSONObject("Property_details");
                                employee_status=Applicant_object.optString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.optString("pan_no");
                                dateofbirth = Applicant_object.optString("member_dob");
                                fathername = Applicant_object.optString("father_name");
                                maritalstatus = Applicant_object.optString("marital_statusstr");

                                //Salaried mode

                                if (employee_status.equals("1")){

                                    secureplot_constructionlay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    //prop_prices = Property_object.optString("property_value");
                                    prop_pincodes = Property_object.optString("pincode");
                                    prop_title = Property_object.optString("prop_titlestr");
                                    prop_type = Property_object.optString("prop_typestr");

                                    cost_estimate=Property_object.optString("cost_of_construction");
                                    cost_of_land =Property_object.optString("cost_of_land");

                                    secureplot_cons.setText(prop_title);
                                    secure_ploatoincode.setText(prop_pincodes);
                                    secureplot_prop_type.setText(prop_type);
                                    secureplo_value.setText(cost_of_land);
                                    secureplot_cost_estimate.setText(cost_estimate);

                                    //Employement Details
                                    month_income = Applicant_object.optString("monthly_income");
                                    salarymodes = Applicant_object.optString("salary_modestr");
                                    cmpny_type = Applicant_object.optString("company_typestr");
                                    cmpny_name = Applicant_object.optString("company_name");
                                    designation = Applicant_object.optString("designation");
                                    cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    total_exp = Applicant_object.optString("total_experience");

                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            String workarea = J.optString("area");
                                            companyarea_txt.setText(workarea);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    salary_mode.setText(salarymodes);
                                    month_incometxt.setText(month_income);
                                    companytypetxt.setText(cmpny_type);
                                    companynametxt.setText(cmpny_name);
                                    designationtxt.setText(designation);
                                    cmpny_pintxt.setText(cmpny_pincode);
                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(total_exp)  % 12);

                                    // totalexp_txt.setText(total_exp);
                                    totalexp_txt.setText(year +" year ,"+ month+" month ");

                                    year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month1 = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    //current_exptxt.setText(curr_exp);
                                    current_exptxt.setText(year1 +" year ,"+ month1+" month ");

                                    //  companyarea_txt.setText(area);


                                    income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    // salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);

                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");



                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_salrycredit.setText(salarymodes);
                                    // companyarea_txt.setText(area);

                                    //Residenence Pincode

                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    //residence_perarea=Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome.setText(income_typestr);
                                        otherincome_amount1.setText(otherincome_details1);
                                        other_income_amount.setVisibility(View.VISIBLE);


                                    }

                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }else{
                                    secureplot_constructionlay.setVisibility(View.VISIBLE);
                                    unsecure_businessloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    //prop_prices = Property_object.optString("property_value");
                                    prop_pincodes = Property_object.optString("pincode");
                                    prop_title = Property_object.optString("prop_titlestr");
                                    prop_type = Property_object.optString("prop_typestr");

                                    cost_estimate=Property_object.optString("cost_of_construction");
                                    cost_of_land =Property_object.optString("cost_of_land");

                                    secureplot_cons.setText(prop_title);
                                    secure_ploatoincode.setText(prop_pincodes);
                                    secureplot_prop_type.setText(prop_type);
                                    secureplo_value.setText(cost_of_land);
                                    secureplot_cost_estimate.setText(cost_estimate);

                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    vintage_docstr = Applicant_object.optString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.optString("employe_status");
                                    office_setup=Applicant_object.optString("office_setupstr");
                                    month_income = Applicant_object.optString("monthly_income");
                                    //  salarymodes = Applicant_object.optString("salary_modestr");
                                    vocation=Applicant_object.optString("vocation");
                                    typeemploy=Applicant_object.optString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.optString("office_res");
                                    office_pincode=Applicant_object.optString("ofc_pincode");
                                    residence_pincode=Applicant_object.optString("per_pincode");
                                    residence_perarea=Applicant_object.optString("per_area");
                                    residence_type=Applicant_object.optString("resident_statusstr");
                                    work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    office_setupstr=Applicant_object.optString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.optString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.optString("vocation_str");
                                    }

                                    office_setup1=Applicant_object.optString("office_setup");

                                    if(office_setup1.equals("1") || office_setup1.equals("3"))
                                    {
                                        office_residence_ly.setVisibility(View.GONE);
                                        rsidence_pincode.setVisibility(View.GONE);
                                    }else
                                    {
                                        office_residence_ly.setVisibility(View.VISIBLE);
                                        rsidence_pincode.setVisibility(View.VISIBLE);
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");

                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome_self.setText(income_typestr);
                                        otherincome_amount1_self.setText(otherincome_details1);
                                        other_income_amount_self.setVisibility(View.VISIBLE);


                                    }

                                    bus_vocationtype.setText(work_vocationstr);
                                    bus_avargeincome.setText(month_income);
                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    bus_numof_month.setText(year +" year ,"+ month+" month ");
                                    ofc_restype.setText(office_setupstr);
                                    offshoppincode.setText(office_pincode);
                                    business_vintageproof.setText(vintage_docstr);
                                    businessincome_proof.setText(income);
                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }

                            }


                            //Plot Loan - 4
                            else if (loan_type.equals("4")){
                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                Property_object=Applicant_object.getJSONObject("Property_details");
                                employee_status=Applicant_object.optString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.optString("pan_no");
                                dateofbirth = Applicant_object.optString("member_dob");
                                fathername = Applicant_object.optString("father_name");
                                maritalstatus = Applicant_object.optString("marital_statusstr");

                                //Salaried mode

                                if (employee_status.equals("1")){

                                    secureplot_loanlay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);

                                    //  prop_prices = Property_object.optString("property_value");
                                    prop_pincodes = Property_object.optString("pincode");
                                    prop_title = Property_object.optString("prop_titlestr");
                                    prop_type = Property_object.optString("prop_typestr");

                                    // cost_estimate=Property_object.optString("cost_of_construction");
                                    cost_of_land =Property_object.optString("cost_of_land");

                                    prop_plot_title.setText(prop_title);
                                    prop_Pincode.setText(prop_pincodes);
                                    prop_plot_identified.setText(prop_type);
                                    prop_plot_cost_of_plot.setText(cost_of_land);



                                    //Employement Details
                                    month_income = Applicant_object.optString("monthly_income");
                                    salarymodes = Applicant_object.optString("salary_modestr");
                                    cmpny_type = Applicant_object.optString("company_typestr");
                                    cmpny_name = Applicant_object.optString("company_name");
                                    designation = Applicant_object.optString("designation");
                                    cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    total_exp = Applicant_object.optString("total_experience");

                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            String workarea = J.optString("area");
                                            companyarea_txt.setText(workarea);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    salary_mode.setText(salarymodes);
                                    month_incometxt.setText(month_income);
                                    companytypetxt.setText(cmpny_type);
                                    companynametxt.setText(cmpny_name);
                                    designationtxt.setText(designation);
                                    cmpny_pintxt.setText(cmpny_pincode);
                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(total_exp)  % 12);

                                    // totalexp_txt.setText(total_exp);
                                    totalexp_txt.setText(year +" year ,"+ month+" month ");

                                    year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month1 = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    //current_exptxt.setText(curr_exp);
                                    current_exptxt.setText(year1 +" year ,"+ month1+" month ");

                                    // companyarea_txt.setText(area);


                                    income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    // salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);

                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");

                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_salrycredit.setText(salarymodes);
                                    // companyarea_txt.setText(area);

                                    //Residenence Pincode

                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    //residence_perarea=Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome.setText(income_typestr);
                                        otherincome_amount1.setText(otherincome_details1);
                                        other_income_amount.setVisibility(View.VISIBLE);


                                    }

                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }else{
                                    secureplot_loanlay.setVisibility(View.VISIBLE);
                                    unsecure_businessloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    // prop_prices = Property_object.optString("property_value");
                                    prop_pincodes = Property_object.optString("pincode");
                                    prop_title = Property_object.optString("prop_titlestr");
                                    prop_type = Property_object.optString("prop_typestr");

                                    // cost_estimate=Property_object.optString("cost_of_construction");
                                    cost_of_land =Property_object.optString("cost_of_land");

                                    prop_plot_title.setText(prop_title);
                                    prop_Pincode.setText(prop_pincodes);
                                    prop_plot_identified.setText(prop_type);
                                    prop_plot_cost_of_plot.setText(cost_of_land);

                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    vintage_docstr = Applicant_object.optString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.optString("employe_status");
                                    office_setup=Applicant_object.optString("office_setupstr");
                                    month_income = Applicant_object.optString("monthly_income");
                                    //  salarymodes = Applicant_object.optString("salary_modestr");
                                    vocation=Applicant_object.optString("vocation");
                                    typeemploy=Applicant_object.optString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.optString("office_res");
                                    office_pincode=Applicant_object.optString("ofc_pincode");
                                    residence_pincode=Applicant_object.optString("per_pincode");
                                    residence_perarea=Applicant_object.optString("per_area");
                                    residence_type=Applicant_object.optString("resident_statusstr");
                                    work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    office_setupstr=Applicant_object.optString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.optString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.optString("vocation_str");
                                    }
                                    office_setup1=Applicant_object.optString("office_setup");

                                    if(office_setup1.equals("1") || office_setup1.equals("3"))
                                    {
                                        office_residence_ly.setVisibility(View.GONE);
                                        rsidence_pincode.setVisibility(View.GONE);
                                    }else
                                    {
                                        office_residence_ly.setVisibility(View.VISIBLE);
                                        rsidence_pincode.setVisibility(View.VISIBLE);
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome_self.setText(income_typestr);
                                        otherincome_amount1_self.setText(otherincome_details1);
                                        other_income_amount_self.setVisibility(View.VISIBLE);


                                    }

                                    bus_vocationtype.setText(work_vocationstr);
                                    bus_avargeincome.setText(month_income);
                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    bus_numof_month.setText(year +" year ,"+ month+" month ");
                                    ofc_restype.setText(office_setupstr);
                                    offshoppincode.setText(office_pincode);
                                    business_vintageproof.setText(vintage_docstr);
                                    businessincome_proof.setText(income);
                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }

                            }


                            /*  //BT + Topup Loan - 5
                             */
                            // Improvement Loan - 6

                            else if (loan_type.equals("6")||loan_type.equals("9") ){
                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                Property_object=Applicant_object.getJSONObject("Property_details");
                                employee_status=Applicant_object.optString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.optString("pan_no");
                                dateofbirth = Applicant_object.optString("member_dob");
                                fathername = Applicant_object.optString("father_name");
                                maritalstatus = Applicant_object.optString("marital_statusstr");



                                if (employee_status.equals("1")){

                                    secure_improvementlay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);

                                    //Salaried mode
                                    prop_prices = Property_object.optString("property_value");
                                    prop_pincodes = Property_object.optString("pincode");
                                    prop_title = Property_object.optString("prop_titlestr");
                                    prop_type = Property_object.optString("prop_typestr");

                                    cost_estimate=Property_object.optString("cost_of_construction");
                                    //  cost_of_land =Property_object.optString("cost_of_land");

                                    imp_prop_title.setText(prop_title);
                                    imp_prop_pincode.setText(prop_pincodes);
                                    imp_prop_type.setText(prop_type);
                                    imp_prop_cost.setText(cost_estimate);
                                    imp_prop_price.setText(prop_prices);

                                    //Employement Details
                                    month_income = Applicant_object.optString("monthly_income");
                                    salarymodes = Applicant_object.optString("salary_modestr");
                                    cmpny_type = Applicant_object.optString("company_typestr");
                                    cmpny_name = Applicant_object.optString("company_name");
                                    designation = Applicant_object.optString("designation");
                                    cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    total_exp = Applicant_object.optString("total_experience");

                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            String workarea = J.optString("area");
                                            companyarea_txt.setText(workarea);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    salary_mode.setText(salarymodes);
                                    month_incometxt.setText(month_income);
                                    companytypetxt.setText(cmpny_type);
                                    companynametxt.setText(cmpny_name);
                                    designationtxt.setText(designation);
                                    cmpny_pintxt.setText(cmpny_pincode);
                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(total_exp)  % 12);

                                    // totalexp_txt.setText(total_exp);
                                    totalexp_txt.setText(year +" year ,"+ month+" month ");

                                    year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month1 = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    //current_exptxt.setText(curr_exp);
                                    current_exptxt.setText(year1 +" year ,"+ month1+" month ");

                                    // companyarea_txt.setText(area);


                                    income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    // salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);

                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");



                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_salrycredit.setText(salarymodes);
                                    // companyarea_txt.setText(area);

                                    //Residenence Pincode

                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    //residence_perarea=Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome.setText(income_typestr);
                                        otherincome_amount1.setText(otherincome_details1);
                                        other_income_amount.setVisibility(View.VISIBLE);


                                    }

                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }else{
                                    secure_improvementlay.setVisibility(View.VISIBLE);
                                    unsecure_businessloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);

                                    //Salaried mode
                                    prop_prices = Property_object.optString("property_value");
                                    prop_pincodes = Property_object.optString("pincode");
                                    prop_title = Property_object.optString("prop_titlestr");
                                    prop_type = Property_object.optString("prop_typestr");

                                    cost_estimate=Property_object.optString("cost_of_construction");
                                    //  cost_of_land =Property_object.optString("cost_of_land");

                                    imp_prop_title.setText(prop_title);
                                    imp_prop_pincode.setText(prop_pincodes);
                                    imp_prop_type.setText(prop_type);
                                    imp_prop_cost.setText(cost_estimate);
                                    imp_prop_price.setText(prop_prices);

                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    vintage_docstr = Applicant_object.optString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.optString("employe_status");
                                    office_setup=Applicant_object.optString("office_setupstr");
                                    month_income = Applicant_object.optString("monthly_income");
                                    //  salarymodes = Applicant_object.optString("salary_modestr");
                                    vocation=Applicant_object.optString("vocation");
                                    typeemploy=Applicant_object.optString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.optString("office_res");
                                    office_pincode=Applicant_object.optString("ofc_pincode");
                                    residence_pincode=Applicant_object.optString("per_pincode");
                                    residence_perarea=Applicant_object.optString("per_area");
                                    residence_type=Applicant_object.optString("resident_statusstr");
                                    work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    office_setupstr=Applicant_object.optString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.optString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.optString("vocation_str");
                                    }
                                    office_setup1=Applicant_object.optString("office_setup");

                                    if(office_setup1.equals("1") || office_setup1.equals("3"))
                                    {
                                        office_residence_ly.setVisibility(View.GONE);
                                        rsidence_pincode.setVisibility(View.GONE);
                                    }else
                                    {
                                        office_residence_ly.setVisibility(View.VISIBLE);
                                        rsidence_pincode.setVisibility(View.VISIBLE);
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome_self.setText(income_typestr);
                                        otherincome_amount1_self.setText(otherincome_details1);
                                        other_income_amount_self.setVisibility(View.VISIBLE);


                                    }

                                    bus_vocationtype.setText(work_vocationstr);
                                    bus_avargeincome.setText(month_income);
                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    bus_numof_month.setText(year +" year ,"+ month+" month ");
                                    ofc_restype.setText(office_setupstr);
                                    offshoppincode.setText(office_pincode);
                                    business_vintageproof.setText(vintage_docstr);
                                    businessincome_proof.setText(income);
                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }

                            }



                            //Construction Loan - 8

                            else if (loan_type.equals("8")){
                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                Property_object=Applicant_object.getJSONObject("Property_details");
                                employee_status=Applicant_object.optString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.optString("pan_no");
                                dateofbirth = Applicant_object.optString("member_dob");
                                fathername = Applicant_object.optString("father_name");
                                maritalstatus = Applicant_object.optString("marital_statusstr");

                                //Salaried mode

                                if (employee_status.equals("1")){

                                    secureplot_constructionlay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    //prop_prices = Property_object.optString("property_value");
                                    prop_pincodes = Property_object.optString("pincode");
                                    prop_title = Property_object.optString("prop_titlestr");
                                    prop_type = Property_object.optString("prop_typestr");

                                    cost_estimate=Property_object.optString("cost_of_construction");
                                    //cost_of_land =Property_object.optString("cost_of_land");

                                    secureplot_cons.setText(prop_title);
                                    secure_ploatoincode.setText(prop_pincodes);
                                    secureplot_prop_type.setText(prop_type);
                                    secureplo_value.setText(cost_estimate);

                                    cost_estimation.setVisibility(View.GONE);

                                    //Pan details show
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");

                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);

                                    //Employement Details
                                    month_income = Applicant_object.optString("monthly_income");
                                    salarymodes = Applicant_object.optString("salary_modestr");
                                    cmpny_type = Applicant_object.optString("company_typestr");
                                    cmpny_name = Applicant_object.optString("company_name");
                                    designation = Applicant_object.optString("designation");
                                    cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    total_exp = Applicant_object.optString("total_experience");

                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            String workarea = J.optString("area");
                                            companyarea_txt.setText(workarea);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    salary_mode.setText(salarymodes);
                                    month_incometxt.setText(month_income);
                                    companytypetxt.setText(cmpny_type);
                                    companynametxt.setText(cmpny_name);
                                    designationtxt.setText(designation);
                                    cmpny_pintxt.setText(cmpny_pincode);
                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(total_exp)  % 12);

                                    // totalexp_txt.setText(total_exp);
                                    totalexp_txt.setText(year +" year ,"+ month+" month ");

                                    year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month1 = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    //current_exptxt.setText(curr_exp);
                                    current_exptxt.setText(year1 +" year ,"+ month1+" month ");

                                    // companyarea_txt.setText(area);


                                    income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    //  salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);

                                    //Residenence Pincode

                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    //residence_perarea=Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    otherincome_details = jsonobj.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome.setText(income_typestr);
                                        otherincome_amount1.setText(otherincome_details1);
                                        other_income_amount.setVisibility(View.VISIBLE);


                                    }

                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }else{
                                    secureplot_constructionlay.setVisibility(View.VISIBLE);
                                    unsecure_businessloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);

                                    //prop_prices = Property_object.optString("property_value");
                                    prop_pincodes = Property_object.optString("pincode");
                                    prop_title = Property_object.optString("prop_titlestr");
                                    prop_type = Property_object.optString("prop_typestr");

                                    // cost_estimate=Property_object.optString("cost_of_construction");
                                    cost_of_land =Property_object.optString("cost_of_land");

                                    secureplot_cons.setText(prop_title);
                                    secure_ploatoincode.setText(prop_pincodes);
                                    secureplot_prop_type.setText(prop_type);
                                    secureplo_value.setText(cost_of_land);

                                    cost_estimation.setVisibility(View.GONE);

                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    vintage_docstr = Applicant_object.optString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.optString("employe_status");
                                    office_setup=Applicant_object.optString("office_setupstr");
                                    month_income = Applicant_object.optString("monthly_income");
                                    //  salarymodes = Applicant_object.optString("salary_modestr");
                                    vocation=Applicant_object.optString("vocation");
                                    typeemploy=Applicant_object.optString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.optString("office_res");
                                    office_pincode=Applicant_object.optString("ofc_pincode");
                                    residence_pincode=Applicant_object.optString("per_pincode");
                                    residence_perarea=Applicant_object.optString("per_area");
                                    residence_type=Applicant_object.optString("resident_statusstr");
                                    work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    office_setupstr=Applicant_object.optString("resident_statusstr");

                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);

                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.optString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.optString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.optString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.optString("vocation_str");
                                    }
                                    office_setup1=Applicant_object.optString("office_setup");

                                    if(office_setup1.equals("1") || office_setup1.equals("3"))
                                    {
                                        office_residence_ly.setVisibility(View.GONE);
                                        rsidence_pincode.setVisibility(View.GONE);
                                    }else
                                    {
                                        office_residence_ly.setVisibility(View.VISIBLE);
                                        rsidence_pincode.setVisibility(View.VISIBLE);
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome_self.setText(income_typestr);
                                        otherincome_amount1_self.setText(otherincome_details1);
                                        other_income_amount_self.setVisibility(View.VISIBLE);


                                    }

                                    bus_vocationtype.setText(work_vocationstr);
                                    bus_avargeincome.setText(month_income);
                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    bus_numof_month.setText(year +" year ,"+ month+" month ");
                                    ofc_restype.setText(office_setupstr);
                                    offshoppincode.setText(office_pincode);
                                    business_vintageproof.setText(vintage_docstr);
                                    businessincome_proof.setText(income);

                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                }

                            }

                            //Vehicle loan type
                            else if(loan_type.equals("31")) {
                                other_income_amount.setVisibility(View.GONE);
                                type_of_emploe.setVisibility(View.GONE);
                                // type_of_emploe1.setVisibility(View.GONE);
                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                Property_object = Applicant_object.getJSONObject("Property_details");
                                employee_status = Applicant_object.optString("employe_status");
                                Log.i("TAG", "employee status: " + employee_status);
                                //Showing Pancard Details
                                if (employee_status.equals("3")) {
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    Log.i("TAG", "pancardnumber_onResponse: " + pancardnumber);
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");

                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);


                                    unsecure_businessloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    vintage_docstr = Applicant_object.optString("vintage_docstr");
                                    String formattedString = vintage_docstr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();

                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income = String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    String formattedStrings = income.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status = Applicant_object.optString("employe_status");
                                    office_setup = Applicant_object.optString("office_setupstr");
                                    month_income = Applicant_object.optString("monthly_income");
                                    //  salarymodes = Applicant_object.optString("salary_modestr");
                                    vocation = Applicant_object.optString("vocation");
                                    typeemploy = Applicant_object.optString("bus_employment_typestr");
                                    bus_employment_type = Applicant_object.optString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: " + typeemploy);
                                    office_residence = Applicant_object.optString("office_res");
                                    office_pincode = Applicant_object.optString("ofc_pincode");
                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    residence_perarea = Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    work_vocationstr = Applicant_object.optString("work_vocationstr");
                                    office_setupstr = Applicant_object.optString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type = Applicant_object.optString("bus_employment_type");
                                    if (bus_employment_type.equals("1")) {
                                        work_vocationstr = Applicant_object.optString("bus_vocationstr");
                                    } else if (bus_employment_type.equals("2")) {
                                        work_vocationstr = Applicant_object.optString("work_vocationstr");
                                    } else {

                                        work_vocationstr = Applicant_object.optString("vocation_str");
                                    }

                                    office_setup1 = Applicant_object.optString("office_setup");

                                    if (office_setup1.equals("1") || office_setup1.equals("3")) {
                                        office_residence_ly.setVisibility(View.GONE);
                                        rsidence_pincode.setVisibility(View.GONE);
                                    } else {
                                        office_residence_ly.setVisibility(View.VISIBLE);
                                        rsidence_pincode.setVisibility(View.VISIBLE);
                                    }

                               /*     otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");


                                    if (income_type.equals("4")) {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    } else {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome_self.setText(income_typestr);
                                        otherincome_amount1_self.setText(otherincome_details1);
                                        other_income_amount_self.setVisibility(View.VISIBLE);


                                    }*/

                                    bus_vocationtype.setText(work_vocationstr);
                                    bus_avargeincome.setText(month_income);
                                    String year, month, year1, month1;

                                    year = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(curr_exp) % 12);
                                    bus_numof_month.setText(year + " year ," + month + " month ");
                                    ofc_restype.setText(office_setupstr);
                                    offshoppincode.setText(office_pincode);
                                    //   business_vintageproof.setText(vintage_docstr);
                                    business_vintageproof.setText(formattedString);
                                    businessincome_proof.setText(formattedStrings);
                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                    vehicledetailslay.setVisibility(View.VISIBLE);

                                    prop_prices = Property_object.optString("vehicle_reg_no");
                                    prop_pincodes = Property_object.optString("vh_makeby_text");
                                    prop_title = Property_object.optString("vh_model_text");
                                    prop_type = Property_object.optString("age_of_vehicle");
                                    prop_iden = Property_object.optString("vh_kmdriven");
                                    Log.i("TAG", "onResponse:prop_iden " + prop_iden);


                                    regnumtxt.setText(prop_prices);
                                    brandtxt.setText(prop_pincodes);
                                    modeltxt.setText(prop_title);
                                    agevehicletxt.setText(prop_type);
                                    totalkmtxt.setText(prop_iden);


                                }else if(employee_status.equals("1")){


                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    Property_object = Applicant_object.getJSONObject("Property_details");

                                    //    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");
                                    gender=Applicant_object.optString("gender_str");

                                    qualificationstr = Applicant_object.optString("qualification_str");
                                    assetsstr=Applicant_object.optString("assetsstr");
                                    noofdependstr=Applicant_object.optString("no_of_dependency");
                                    epfdeductstr=Applicant_object.optString("is_epf_deduct_str");
                                    typeofemp_str=Applicant_object.optString("employee_type_str");
                                    haveotherincome=Applicant_object.optString("is_other_eranings_str");
                                    haveotheramount=Applicant_object.optString("other_income");

                                    //Employement Details
                                    month_income = Applicant_object.optString("monthly_income");
                                    salarymodes = Applicant_object.optString("salary_modestr");
                                    cmpny_type = Applicant_object.optString("company_typestr");
                                    cmpny_name = Applicant_object.optString("company_name");
                                    designation = Applicant_object.optString("designation");
                                    cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    total_exp = Applicant_object.optString("total_experience");
                                    typeofemp_str=Applicant_object.optString("employee_type_str");
                                    haveotherincome=Applicant_object.optString("is_other_eranings_str");
                                    haveotheramount=Applicant_object.optString("other_income");
                                    ofcstreet=Applicant_object.optString("ofc_street");
                                    income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                    Log.i("TAG", "onResponse:income_proof_typestr "+income_proof_typestr);

                                    currentaddresproof=Applicant_object.optString("address_proof_str");
                                    residence_Street=Applicant_object.optString("street");
                                    curres_addressprofftxt.setText(currentaddresproof);

                                    employeproofstr=Applicant_object.optString("employee_proof_str");
                                    employeproofid=Applicant_object.optString("ofc_add_proof");
                                    officailmailid_str=Applicant_object.optString("office_email");
                                    if (employeproofid.equalsIgnoreCase("2")){
                                        officialmailidlay.setVisibility(View.VISIBLE);
                                        officialmailidtxt.setText(officailmailid_str);
                                    }else{
                                        officialmailidlay.setVisibility(View.GONE);

                                    }
                                    employementprooftxtshow.setText(employeproofstr);

                                    companydoor.setText(residence_Street);



                                    creditscorestr=Applicant_object.optString("entered_credit_score_str");
                                    emilatestr=Applicant_object.optString("emi_late_str");
                                    banksalstr=Applicant_object.optString("salary_bank_str");
                                    runloanstr=Applicant_object.optString("is_existloan_str");
                                    witeoffstr=Applicant_object.optString("is_write_off_str");
                                    bankpdfstr=Applicant_object.optString("arrange_bank_pdf_str");
                                    creditbanklay.setVisibility(View.VISIBLE);

                                    typeodemployetxt.setText(typeofemp_str);
                                    if(haveotherincome.equalsIgnoreCase("No")){
                                        other_income_amount.setVisibility(View.GONE);
                                    }else{
                                        otherincome_amount1.setText(haveotheramount);

                                    }
                                    otherincometxt.setText(haveotherincome);
                                    companydoortxt.setText(ofcstreet);
                                    otherincome_amount1.setText(haveotheramount);
                                    creditscoretext.setText(creditscorestr);
                                    emilatetxt.setText(emilatestr);
                                    banksalary.setText(banksalstr);
                                    runningloantxt.setText(runloanstr);
                                    witeofftxt.setText(witeoffstr);
                                    bankpdftxt.setText(bankpdfstr);

                                    vehicledetailslay.setVisibility(View.VISIBLE);

                                    prop_prices = Property_object.optString("vehicle_reg_no");
                                    prop_pincodes = Property_object.optString("vh_makeby_text");
                                    prop_title = Property_object.optString("vh_model_text");
                                    prop_type = Property_object.optString("age_of_vehicle");
                                    prop_iden = Property_object.optString("vh_kmdriven");
                                    Log.i("TAG", "onResponse:prop_iden " + prop_iden);


                                    regnumtxt.setText(prop_prices);
                                    brandtxt.setText(prop_pincodes);
                                    modeltxt.setText(prop_title);
                                    agevehicletxt.setText(prop_type);
                                    totalkmtxt.setText(prop_iden);


                                    residenctstatus=Applicant_object.optString("resident_status");


                                    if(residenctstatus.equals("1") || residenctstatus.equals("2")){

                                        rentpaidlay.setVisibility(View.GONE);
                                        permanentrespinlay.setVisibility(View.GONE);
                                        permanentrestypelay.setVisibility(View.GONE);
                                        liveincurrentlay.setVisibility(View.GONE);

                                    }else{
                                        perresstr=Applicant_object.optString("perm_res_pincode");
                                        liveinstr=Applicant_object.optString("current_home_duration");
                                        perrestypestr=Applicant_object.optString("perm_residence_str");
                                        rentpaid=Applicant_object.optString("rent_beingpaid");

                                        rentpaidlay.setVisibility(View.VISIBLE);
                                        permanentrespinlay.setVisibility(View.VISIBLE);
                                        permanentrestypelay.setVisibility(View.VISIBLE);
                                        liveincurrentlay.setVisibility(View.VISIBLE);
                                    }


                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    //Residenence Pincode
                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    //residence_perarea=Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    type_of_emploe.setVisibility(View.GONE);
                                    // type_of_emploe1.setVisibility(View.GONE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);
                                    salary_mode.setText(salarymodes);
                                    month_incometxt.setText(month_income);
                                    companytypetxt.setText(cmpny_type);
                                    companynametxt.setText(cmpny_name);
                                    designationtxt.setText(designation);
                                    cmpny_pintxt.setText(cmpny_pincode);


                                    String year,month,year1,month1;

                                    year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(total_exp)  % 12);

                                    // totalexp_txt.setText(total_exp);
                                    totalexp_txt.setText(year +" year ,"+ month+" month ");

                                    year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month1 = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                    //current_exptxt.setText(curr_exp);
                                    current_exptxt.setText(year1 +" year ,"+ month1+" month ");

                                    companyarea_txt.setText(area);
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    String formattedString1 = assetsstr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();

                                    gendertxt.setText(gender);
                                    nooddependents.setText(noofdependstr);
                                    educationqualtxt.setText(qualificationstr);
                                    assetstxt.setText(formattedString1);
                                    epfdeductsalarytxt.setText(epfdeductstr);



                                    unsecure_residence_pincode.setText(residence_pincode);
                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);


                                  /*  otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome.setText(income_typestr);
                                        otherincome_amount1.setText(otherincome_details1);
                                        other_income_amount.setVisibility(View.VISIBLE);

                                    }*/


                                    bus_salrycredit.setText(salarymodes);
                                    String formattedString2 = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    salaryproof_txt.setText(formattedString2);

                                    CoApplicant_Status =jsonobj.optString("coapplicant_status");
                                    if(CoApplicant_Status.equalsIgnoreCase("success")) {
                                        coapplicant_pandetails.setVisibility(View.VISIBLE);
                                        coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                        Coapplicant_object = jsonobj.getJSONObject("coapplicant_data");
                                        employee_status1 = Coapplicant_object.optString("employe_status");
                                        if(employee_status.equalsIgnoreCase("1")){
                                            coapplicant_pandetails.setVisibility(View.VISIBLE);
                                           // coapplicant_selfemployementdeatilslay.setVisibility(View.VISIBLE);
                                            coapplicant_pandetails.setVisibility(View.VISIBLE);
                                            coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                            //  coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                            Coapplicant_object=jsonobj.getJSONObject("coapplicant_data");
                                            copancard = Coapplicant_object.optString("pan_no");
                                            codob = Coapplicant_object.optString("member_dob");
                                            cofather = Coapplicant_object.optString("father_name");
                                            gomartical = Coapplicant_object.optString("marital_statusstr");

                                            coeducationqualification = Coapplicant_object.optString("qualification_str");
                                            coassets=Coapplicant_object.optString("assetsstr");
                                            cogender=Coapplicant_object.optString("gender_str");
                                            conoofdependes=Coapplicant_object.optString("no_of_dependency");


                                            copancardtxt.setText(copancard);
                                            cofathernametxt.setText(cofather);
                                            codateofbithtxt.setText(codob);
                                            comaritaltxt.setText(gomartical);
                                            conooddependents.setText(conoofdependes);
                                            cogendertxt.setText(cogender);
                                            coassetstxt.setText(formattedString1);
                                            coeducationqualtxt.setText(coeducationqualification);


                                            comonthincome = Coapplicant_object.optString("monthly_income");
                                            cosalarymode = Coapplicant_object.optString("salary_modestr");
                                            cocompanytype = Coapplicant_object.optString("company_typestr");
                                            cocompanyname = Coapplicant_object.optString("company_name");
                                            codesignation = Coapplicant_object.optString("designation");
                                            cocmpnypin = Coapplicant_object.optString("ofc_pincode");
                                            cocurrentexp = Coapplicant_object.optString("working_experience");
                                            cototalexp = Coapplicant_object.optString("total_experience");
                                            cootherincome = Coapplicant_object.optString("is_other_eranings_str");
                                            cootheramount = Coapplicant_object.optString("other_income");

                                            cosalaryproof = Coapplicant_object.optString("income_proof_typestr");
                                            coepfdeduct = Coapplicant_object.optString("is_epf_deduct_str");
                                            cotypeofemploye = Coapplicant_object.optString("employee_type_str");
                                            cocmpnypin = Coapplicant_object.optString("ofc_pincode");
                                            cocompanydoor = Coapplicant_object.optString("ofc_street");


                                            if (cootherincome.equalsIgnoreCase("No")) {
                                                co_other_income_amount.setVisibility(View.GONE);

                                            }

                                            String formattedString = cosalaryproof.toString()
                                                    .replace("[", "")  //remove the right bracket
                                                    .replace("]", "")
                                                    .replaceAll("\"", "")
                                                    .trim();

                                            //working Area array
                                            JSONArray area_array1 = Coapplicant_object.getJSONArray("work_areaarr");
                                            if (area_array1.length() > 0) {
                                                try {
                                                    JSONObject J = area_array1.getJSONObject(0);
                                                    area = J.optString("area");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }


                                            cosalary_mode.setText(cosalarymode);
                                            comonth_incometxt.setText(comonthincome);
                                            cosalaryproof_txt.setText(formattedString);
                                            coepfdeductsalarytxt.setText(coepfdeduct);
                                            cocompanytypetxt.setText(cocompanytype);
                                            cocompanynametxt.setText(cocompanyname);
                                            codesignationtxt.setText(codesignation);
                                            cotypeodemployetxt.setText(cotypeofemploye);
                                            cocompanydoortxt.setText(cocompanydoor);
                                            cocmpny_pintxt.setText(cocmpnypin);
                                            cocompanyarea_txt.setText(area);
                                            cocurrent_exptxt.setText(cocurrentexp);
                                            cototalexp_txt.setText(cototalexp);
                                            cootherincometxt.setText(cootherincome);
                                            cootherincome_amount1.setText(cootheramount);


                                        }

                                        else{

                                            if(CoApplicant_Status.equalsIgnoreCase("success")){
                                                coapplicant_pandetails.setVisibility(View.VISIBLE);
                                                coapplicant_employementdeatilslay.setVisibility(View.GONE);
                                                coapplicant_selfemployementdeatilslay.setVisibility(View.VISIBLE);
                                                coapplicant_pandetails.setVisibility(View.VISIBLE);
                                                //  coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                                Coapplicant_object=jsonobj.getJSONObject("coapplicant_data");
                                                copancard = Coapplicant_object.optString("pan_no");
                                                codob = Coapplicant_object.optString("member_dob");
                                                cofather = Coapplicant_object.optString("father_name");
                                                gomartical = Coapplicant_object.optString("marital_statusstr");

                                                coeducationqualification = Coapplicant_object.optString("qualification_str");
                                                coassets=Coapplicant_object.optString("assetsstr");
                                                cogender=Coapplicant_object.optString("gender_str");
                                                conoofdependes=Coapplicant_object.optString("no_of_dependency");




                                                String formattedString3 = coassets.toString()
                                                        .replace("[", "")  //remove the right bracket
                                                        .replace("]", "")
                                                        .replaceAll("\"", "")
                                                        .trim();
                                                //Co APPLicant Personal details

                                                copancardtxt.setText(copancard);
                                                cofathernametxt.setText(cofather);
                                                codateofbithtxt.setText(codob);
                                                comaritaltxt.setText(gomartical);
                                                conooddependents.setText(conoofdependes);
                                                cogendertxt.setText(cogender);
                                                coassetstxt.setText(formattedString3);
                                                coeducationqualtxt.setText(coeducationqualification);



                                                codoyoufiletxtstr=Coapplicant_object.optString("is_itr_file_str");
                                                coaboutbustxtstr=Coapplicant_object.optString("about_company");
                                                cohaveaddressprooftxtstr=Coapplicant_object.optString("ofc_add_proof_str");
                                                coannualturntxtstr=Coapplicant_object.optString("turnover_curyr");
                                                coannualprofittxtstr=Coapplicant_object.optString("profit");

                                                cobus_numof_monthstr = Coapplicant_object.optString("working_experience");
                                                cobusiness_vintageproofstr = Coapplicant_object.optString("vintage_docstr");

                                                coofficesetupstr=Coapplicant_object.optString("office_setupstr");
                                                comonthincome = Coapplicant_object.optString("monthly_income");
                                                // cosalarymode = Coapplicant_object.optString("salary_modestr");
                                                cobus_vocationtypestr=Coapplicant_object.optString("vocation");
                                                cotypeofemploye=Coapplicant_object.optString("bus_employment_typestr");

                                                comonthincome = Coapplicant_object.optString("monthly_income");


                                                coofc_restypestr=Coapplicant_object.optString("office_res");
                                                cooffshoppincodestr=Coapplicant_object.optString("ofc_pincode");
                                                comonthincome = Coapplicant_object.optString("monthly_income");
                                                cobusinessincome_proofstr=Coapplicant_object.optString("income_proof_typestr");




                                                String year2 = String.valueOf(Integer.parseInt(cobus_numof_monthstr) / 12);
                                                String month2 = String.valueOf(Integer.parseInt(cobus_numof_monthstr)  % 12);
                                                cobus_numof_month.setText(year2 +" year ,"+ month2+" month ");
                                                cobusiness_vintageproof.setText(cobusiness_vintageproofstr);

                                                cotypeofemploye=Coapplicant_object.optString("bus_employment_typestr");


                                                coannualprofittxt.setText(coannualprofittxtstr);
                                                coannualturntxt.setText(coannualturntxtstr);
                                                cohaveaddressprooftxt.setText(cohaveaddressprooftxtstr);
                                                coaboutbustxt.setText(coaboutbustxtstr);
                                                codoyoufiletxt.setText(codoyoufiletxtstr);
                                                coofficesetup.setText(coofficesetupstr);
                                                comonth_incometxt.setText(comonthincome);
                                                cobus_typeemployemnt.setText("Self employed");
                                                // cosalary_mode.setText(cosalarymode);
                                                cobus_vocationtype.setText(cobus_vocationtypestr);
                                                cotypeodemployetxt.setText(cotypeofemploye);
                                                coofc_restype.setText(coofc_restypestr);
                                                cooffshoppincode.setText(cooffshoppincodestr);
                                                comonth_incometxt.setText(comonthincome);
                                                cobusinessincome_proof.setText(cobusinessincome_proofstr);
                                                cobus_typeself.setText(cotypeofemploye);
                                                cobus_avargeincome.setText(month_income);









                                            }



                                        }

                                    }





                                    }
                            }




                            else if(loan_type.equals("22")) {


                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                Property_object = Applicant_object.getJSONObject("Property_details");
                                employee_status = Applicant_object.optString("employe_status");
                                Log.i("TAG", "employee status: " + employee_status);
                                //Showing Pancard Details
                                if (employee_status.equals("3")) {
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    Log.i("TAG", "pancardnumber_onResponse: " + pancardnumber);
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");

                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);


                                    unsecure_businessloan.setVisibility(View.VISIBLE);
                                    other_income_source_self.setVisibility(View.GONE);
                                    other_income_amount_self.setVisibility(View.GONE);
                                    other_income_amount.setVisibility(View.GONE);
                                    type_of_emploe.setVisibility(View.GONE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    vintage_docstr = Applicant_object.optString("vintage_docstr");
                                    String formattedString = vintage_docstr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();

                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income = String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    String formattedStrings = income.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status = Applicant_object.optString("employe_status");
                                    office_setup = Applicant_object.optString("office_setupstr");
                                    month_income = Applicant_object.optString("monthly_income");
                                    //  salarymodes = Applicant_object.optString("salary_modestr");
                                    vocation = Applicant_object.optString("vocation");
                                    typeemploy = Applicant_object.optString("bus_employment_typestr");
                                    bus_employment_type = Applicant_object.optString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: " + typeemploy);
                                    office_residence = Applicant_object.optString("office_res");
                                    office_pincode = Applicant_object.optString("ofc_pincode");
                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    residence_perarea = Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    work_vocationstr = Applicant_object.optString("work_vocationstr");
                                    office_setupstr = Applicant_object.optString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type = Applicant_object.optString("bus_employment_type");
                                    if (bus_employment_type.equals("1")) {
                                        work_vocationstr = Applicant_object.optString("bus_vocationstr");
                                    } else if (bus_employment_type.equals("2")) {
                                        work_vocationstr = Applicant_object.optString("work_vocationstr");
                                    } else {

                                        work_vocationstr = Applicant_object.optString("vocation_str");
                                    }

                                    office_setup1 = Applicant_object.optString("office_setup");

                                    if (office_setup1.equals("1") || office_setup1.equals("3")) {
                                        office_residence_ly.setVisibility(View.GONE);
                                        rsidence_pincode.setVisibility(View.GONE);
                                    } else {
                                        office_residence_ly.setVisibility(View.VISIBLE);
                                        rsidence_pincode.setVisibility(View.VISIBLE);
                                    }

                                    //  otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                              /*      String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");


                                    if (income_type.equals("4")) {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    } else {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome_self.setText(income_typestr);
                                        otherincome_amount1_self.setText(otherincome_details1);
                                        other_income_amount_self.setVisibility(View.VISIBLE);


                                    }*/

                                    bus_vocationtype.setText(work_vocationstr);
                                    bus_avargeincome.setText(month_income);
                                    String year, month, year1, month1;

                                    year = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(curr_exp) % 12);
                                    bus_numof_month.setText(year + " year ," + month + " month ");
                                    ofc_restype.setText(office_setupstr);
                                    offshoppincode.setText(office_pincode);
                                    //   business_vintageproof.setText(vintage_docstr);
                                    business_vintageproof.setText(formattedString);
                                    businessincome_proof.setText(formattedStrings);
                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                    vehicledetailslay.setVisibility(View.VISIBLE);

                                    prop_prices = Property_object.optString("vehicle_reg_no");
                                    prop_pincodes = Property_object.optString("vh_makeby_text");
                                    prop_title = Property_object.optString("vh_model_text");
                                    prop_type = Property_object.optString("age_of_vehicle");
                                    prop_iden = Property_object.optString("vh_kmdriven");
                                    Log.i("TAG", "onResponse:prop_iden " + prop_iden);


                                    regnumtxt.setText(prop_prices);
                                    brandtxt.setText(prop_pincodes);
                                    modeltxt.setText(prop_title);
                                    agevehicletxt.setText(prop_type);
                                    totalkmtxt.setText(prop_iden);

                                    CoApplicant_Status =jsonobj.optString("coapplicant_status");
                                    if(CoApplicant_Status.equalsIgnoreCase("success")) {
                                        coapplicant_pandetails.setVisibility(View.VISIBLE);
                                        coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                        Coapplicant_object = jsonobj.getJSONObject("coapplicant_data");
                                        employee_status1 = Coapplicant_object.optString("employe_status");
                                        if (employee_status.equalsIgnoreCase("1")) {
                                            coapplicant_pandetails.setVisibility(View.VISIBLE);
                                            // coapplicant_selfemployementdeatilslay.setVisibility(View.VISIBLE);
                                            coapplicant_pandetails.setVisibility(View.VISIBLE);
                                            coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                            //  coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                            Coapplicant_object = jsonobj.getJSONObject("coapplicant_data");
                                            copancard = Coapplicant_object.optString("pan_no");
                                            codob = Coapplicant_object.optString("member_dob");
                                            cofather = Coapplicant_object.optString("father_name");
                                            gomartical = Coapplicant_object.optString("marital_statusstr");

                                            coeducationqualification = Coapplicant_object.optString("qualification_str");
                                            coassets = Coapplicant_object.optString("assetsstr");
                                            cogender = Coapplicant_object.optString("gender_str");
                                            conoofdependes = Coapplicant_object.optString("no_of_dependency");


                                            copancardtxt.setText(copancard);
                                            cofathernametxt.setText(cofather);
                                            codateofbithtxt.setText(codob);
                                            comaritaltxt.setText(gomartical);
                                            conooddependents.setText(conoofdependes);
                                            cogendertxt.setText(cogender);
                                            coassetstxt.setText(formattedString);
                                            coeducationqualtxt.setText(coeducationqualification);


                                            comonthincome = Coapplicant_object.optString("monthly_income");
                                            cosalarymode = Coapplicant_object.optString("salary_modestr");
                                            cocompanytype = Coapplicant_object.optString("company_typestr");
                                            cocompanyname = Coapplicant_object.optString("company_name");
                                            codesignation = Coapplicant_object.optString("designation");
                                            cocmpnypin = Coapplicant_object.optString("ofc_pincode");
                                            cocurrentexp = Coapplicant_object.optString("working_experience");
                                            cototalexp = Coapplicant_object.optString("total_experience");
                                            cootherincome = Coapplicant_object.optString("is_other_eranings_str");
                                            cootheramount = Coapplicant_object.optString("other_income");

                                            cosalaryproof = Coapplicant_object.optString("income_proof_typestr");
                                            coepfdeduct = Coapplicant_object.optString("is_epf_deduct_str");
                                            cotypeofemploye = Coapplicant_object.optString("employee_type_str");
                                            cocmpnypin = Coapplicant_object.optString("ofc_pincode");
                                            cocompanydoor = Coapplicant_object.optString("ofc_street");


                                            if (cootherincome.equalsIgnoreCase("No")) {
                                                co_other_income_amount.setVisibility(View.GONE);

                                            }

                                            String formattedString3= cosalaryproof.toString()
                                                    .replace("[", "")  //remove the right bracket
                                                    .replace("]", "")
                                                    .replaceAll("\"", "")
                                                    .trim();

                                            //working Area array
                                            JSONArray area_array1 = Coapplicant_object.getJSONArray("work_areaarr");
                                            if (area_array1.length() > 0) {
                                                try {
                                                    JSONObject J = area_array1.getJSONObject(0);
                                                    area = J.optString("area");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }


                                            cosalary_mode.setText(cosalarymode);
                                            comonth_incometxt.setText(comonthincome);
                                            cosalaryproof_txt.setText(formattedString3);
                                            coepfdeductsalarytxt.setText(coepfdeduct);
                                            cocompanytypetxt.setText(cocompanytype);
                                            cocompanynametxt.setText(cocompanyname);
                                            codesignationtxt.setText(codesignation);
                                            cotypeodemployetxt.setText(cotypeofemploye);
                                            cocompanydoortxt.setText(cocompanydoor);
                                            cocmpny_pintxt.setText(cocmpnypin);
                                            cocompanyarea_txt.setText(area);
                                            cocurrent_exptxt.setText(cocurrentexp);
                                            cototalexp_txt.setText(cototalexp);
                                            cootherincometxt.setText(cootherincome);
                                            cootherincome_amount1.setText(cootheramount);


                                        } else {

                                            if (CoApplicant_Status.equalsIgnoreCase("success")) {
                                                coapplicant_pandetails.setVisibility(View.VISIBLE);
                                                coapplicant_employementdeatilslay.setVisibility(View.GONE);
                                                coapplicant_selfemployementdeatilslay.setVisibility(View.VISIBLE);
                                                coapplicant_pandetails.setVisibility(View.VISIBLE);
                                                //  coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                                Coapplicant_object = jsonobj.getJSONObject("coapplicant_data");
                                                copancard = Coapplicant_object.optString("pan_no");
                                                codob = Coapplicant_object.optString("member_dob");
                                                cofather = Coapplicant_object.optString("father_name");
                                                gomartical = Coapplicant_object.optString("marital_statusstr");

                                                coeducationqualification = Coapplicant_object.optString("qualification_str");
                                                coassets = Coapplicant_object.optString("assetsstr");
                                                cogender = Coapplicant_object.optString("gender_str");
                                                conoofdependes = Coapplicant_object.optString("no_of_dependency");


                                                String formattedString3 = coassets.toString()
                                                        .replace("[", "")  //remove the right bracket
                                                        .replace("]", "")
                                                        .replaceAll("\"", "")
                                                        .trim();
                                                //Co APPLicant Personal details

                                                copancardtxt.setText(copancard);
                                                cofathernametxt.setText(cofather);
                                                codateofbithtxt.setText(codob);
                                                comaritaltxt.setText(gomartical);
                                                conooddependents.setText(conoofdependes);
                                                cogendertxt.setText(cogender);
                                                coassetstxt.setText(formattedString3);
                                                coeducationqualtxt.setText(coeducationqualification);


                                                codoyoufiletxtstr = Coapplicant_object.optString("is_itr_file_str");
                                                coaboutbustxtstr = Coapplicant_object.optString("about_company");
                                                cohaveaddressprooftxtstr = Coapplicant_object.optString("ofc_add_proof_str");
                                                coannualturntxtstr = Coapplicant_object.optString("turnover_curyr");
                                                coannualprofittxtstr = Coapplicant_object.optString("profit");

                                                cobus_numof_monthstr = Coapplicant_object.optString("working_experience");
                                                cobusiness_vintageproofstr = Coapplicant_object.optString("vintage_docstr");

                                                coofficesetupstr = Coapplicant_object.optString("office_setupstr");
                                                comonthincome = Coapplicant_object.optString("monthly_income");
                                                // cosalarymode = Coapplicant_object.optString("salary_modestr");
                                                cobus_vocationtypestr = Coapplicant_object.optString("vocation");
                                                cotypeofemploye = Coapplicant_object.optString("bus_employment_typestr");

                                                comonthincome = Coapplicant_object.optString("monthly_income");


                                                coofc_restypestr = Coapplicant_object.optString("office_res");
                                                cooffshoppincodestr = Coapplicant_object.optString("ofc_pincode");
                                                comonthincome = Coapplicant_object.optString("monthly_income");
                                                cobusinessincome_proofstr = Coapplicant_object.optString("income_proof_typestr");


                                                String year2 = String.valueOf(Integer.parseInt(cobus_numof_monthstr) / 12);
                                                String month2 = String.valueOf(Integer.parseInt(cobus_numof_monthstr) % 12);
                                                cobus_numof_month.setText(year2 + " year ," + month2 + " month ");
                                                cobusiness_vintageproof.setText(cobusiness_vintageproofstr);

                                                cotypeofemploye = Coapplicant_object.optString("bus_employment_typestr");


                                                coannualprofittxt.setText(coannualprofittxtstr);
                                                coannualturntxt.setText(coannualturntxtstr);
                                                cohaveaddressprooftxt.setText(cohaveaddressprooftxtstr);
                                                coaboutbustxt.setText(coaboutbustxtstr);
                                                codoyoufiletxt.setText(codoyoufiletxtstr);
                                                coofficesetup.setText(coofficesetupstr);
                                                comonth_incometxt.setText(comonthincome);
                                                cobus_typeemployemnt.setText("Self employed");
                                                // cosalary_mode.setText(cosalarymode);
                                                cobus_vocationtype.setText(cobus_vocationtypestr);
                                                cotypeodemployetxt.setText(cotypeofemploye);
                                                coofc_restype.setText(coofc_restypestr);
                                                cooffshoppincode.setText(cooffshoppincodestr);
                                                comonth_incometxt.setText(comonthincome);
                                                cobusinessincome_proof.setText(cobusinessincome_proofstr);
                                                cobus_typeself.setText(cotypeofemploye);
                                                cobus_avargeincome.setText(month_income);


                                            }


                                        }
                                    }


                                } else if (employee_status.equals("1")) {

                                    creditbanklay.setVisibility(View.VISIBLE);
                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");



                                    gender=Applicant_object.optString("gender_str");
                                    typeofemp_str=Applicant_object.optString("employee_type_str");
                                    epfdeductstr=Applicant_object.optString("is_epf_deduct_str");
                                    haveotherincome=Applicant_object.optString("is_other_eranings_str");
                                    haveotheramount=Applicant_object.optString("other_income");
                                    ofcstreet=Applicant_object.optString("ofc_street");

                                    residenctstatus=Applicant_object.optString("resident_status");


                                    if(residenctstatus.equals("1") || residenctstatus.equals("2")){

                                        rentpaidlay.setVisibility(View.GONE);
                                        permanentrespinlay.setVisibility(View.GONE);
                                        permanentrestypelay.setVisibility(View.GONE);
                                        liveincurrentlay.setVisibility(View.GONE);

                                    }else{
                                        perresstr=Applicant_object.optString("perm_res_pincode");
                                        liveinstr=Applicant_object.optString("current_home_duration");
                                        perrestypestr=Applicant_object.optString("perm_residence_str");
                                        rentpaid=Applicant_object.optString("rent_beingpaid");
                                        currentrstypestr=Applicant_object.optString("address_proof_str");


                                        rentpaidlay.setVisibility(View.VISIBLE);
                                        permanentrespinlay.setVisibility(View.VISIBLE);
                                        permanentrestypelay.setVisibility(View.VISIBLE);
                                        liveincurrentlay.setVisibility(View.VISIBLE);
                                    }






                                    qualificationstr = Applicant_object.optString("qualification_str");
                                    assetsstr=Applicant_object.optString("assetsstr");
                                    noofdependstr=Applicant_object.optString("no_of_dependency");
                                    creditscorestr=Applicant_object.optString("entered_credit_score_str");
                                    emilatestr=Applicant_object.optString("emi_late_str");
                                    banksalstr=Applicant_object.optString("salary_bank_str");
                                    runloanstr=Applicant_object.optString("is_existloan_str");
                                    witeoffstr=Applicant_object.optString("is_write_off_str");
                                    residence_Street=Applicant_object.optString("street");

                                    employeproofstr=Applicant_object.optString("employee_proof_str");
                                    employeproofid=Applicant_object.optString("ofc_add_proof");
                                    officailmailid_str=Applicant_object.optString("office_email");
                                    if (employeproofid.equalsIgnoreCase("2")){
                                        officialmailidlay.setVisibility(View.VISIBLE);
                                        officialmailidtxt.setText(officailmailid_str);
                                    }else{
                                        officialmailidlay.setVisibility(View.GONE);

                                    }
                                    employementprooftxtshow.setText(employeproofstr);

                                    bankpdfstr=Applicant_object.optString("arrange_bank_pdf_str");
                                    currentaddresproof=Applicant_object.optString("address_proof_str");



                                    //Employement Details
                                    month_income = Applicant_object.optString("monthly_income");
                                    salarymodes = Applicant_object.optString("salary_modestr");
                                    cmpny_type = Applicant_object.optString("company_typestr");
                                    cmpny_name = Applicant_object.optString("company_name");
                                    designation = Applicant_object.optString("designation");
                                    cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    total_exp = Applicant_object.optString("total_experience");
                                    income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                    Log.i("TAG", "onResponse:income_proof_typestr " + income_proof_typestr);


                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    //Residenence Pincode
                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    //residence_perarea=Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    other_income_amount.setVisibility(View.GONE);
                                    type_of_emploe.setVisibility(View.GONE);
                                    // type_of_emploe1.setVisibility(View.GONE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);
                                    salary_mode.setText(salarymodes);
                                    month_incometxt.setText(month_income);
                                    companytypetxt.setText(cmpny_type);
                                    companynametxt.setText(cmpny_name);
                                    designationtxt.setText(designation);
                                    companydoortxt.setText(ofcstreet);

                                    curres_addressprofftxt.setText(currentaddresproof);

                                    cmpny_pintxt.setText(cmpny_pincode);
                                    creditscoretext.setText(creditscorestr);
                                    emilatetxt.setText(emilatestr);
                                    banksalary.setText(banksalstr);
                                    runningloantxt.setText(runloanstr);
                                    witeofftxt.setText(witeoffstr);
                                    bankpdftxt.setText(bankpdfstr);
                                    companydoor.setText(residence_Street);



                                    String year, month, year1, month1;

                                    year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(total_exp) % 12);

                                    // totalexp_txt.setText(total_exp);
                                    totalexp_txt.setText(year + " year ," + month + " month ");

                                    year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month1 = String.valueOf(Integer.parseInt(curr_exp) % 12);
                                    //current_exptxt.setText(curr_exp);
                                    current_exptxt.setText(year1 + " year ," + month1 + " month ");

                                    companyarea_txt.setText(area);
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    String formattedString1 = assetsstr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();

                                    gendertxt.setText(gender);
                                    nooddependents.setText(noofdependstr);
                                    educationqualtxt.setText(qualificationstr);
                                    epfdeductsalarytxt.setText(epfdeductstr);
                                    typeodemployetxt.setText(typeofemp_str);
                                    companydoor.setText(residence_Street);
                                    otherincometxt.setText(haveotherincome);

                                    perrespincodetxt.setText(perresstr);
                                    // otherincome_amount1.setText(haveotheramount);
                                    rentpaidforhousetxt.setText(rentpaid);
                                    liveincurrenttxt.setText(liveinstr);
                                    perrestypecodetxt.setText(perrestypestr);
                                    curres_addressprofftxt.setText(currentaddresproof);

                                    assetstxt.setText(formattedString1);
                                    unsecure_residence_pincode.setText(residence_pincode);
                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);


                                  /*  otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome.setText(income_typestr);
                                        otherincome_amount1.setText(otherincome_details1);
                                        other_income_amount.setVisibility(View.VISIBLE);

                                    }*/


                                    bus_salrycredit.setText(salarymodes);
                                    String formattedString2 = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    salaryproof_txt.setText(formattedString2);

                                    vehicledetailslay.setVisibility(View.VISIBLE);

                                    prop_prices = Property_object.optString("vehicle_reg_no");
                                    prop_pincodes = Property_object.optString("vh_makeby_text");
                                    prop_title = Property_object.optString("vh_model_text");
                                    prop_type = Property_object.optString("age_of_vehicle");
                                    prop_iden = Property_object.optString("vh_kmdriven");
                                    Log.i("TAG", "onResponse:prop_iden " + prop_iden);


                                    regnumtxt.setText(prop_prices);
                                    brandtxt.setText(prop_pincodes);
                                    modeltxt.setText(prop_title);
                                    agevehicletxt.setText(prop_type);
                                    totalkmtxt.setText(prop_iden);


                                    CoApplicant_Status =jsonobj.optString("coapplicant_status");
                                    if(CoApplicant_Status.equalsIgnoreCase("success")) {
                                        coapplicant_pandetails.setVisibility(View.VISIBLE);
                                        coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                        Coapplicant_object = jsonobj.getJSONObject("coapplicant_data");
                                        employee_status1 = Coapplicant_object.optString("employe_status");
                                        if (employee_status.equalsIgnoreCase("1")) {
                                            coapplicant_pandetails.setVisibility(View.VISIBLE);
                                            // coapplicant_selfemployementdeatilslay.setVisibility(View.VISIBLE);
                                            coapplicant_pandetails.setVisibility(View.VISIBLE);
                                            coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                            //  coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                            Coapplicant_object = jsonobj.getJSONObject("coapplicant_data");
                                            copancard = Coapplicant_object.optString("pan_no");
                                            codob = Coapplicant_object.optString("member_dob");
                                            cofather = Coapplicant_object.optString("father_name");
                                            gomartical = Coapplicant_object.optString("marital_statusstr");

                                            coeducationqualification = Coapplicant_object.optString("qualification_str");
                                            coassets = Coapplicant_object.optString("assetsstr");
                                            cogender = Coapplicant_object.optString("gender_str");
                                            conoofdependes = Coapplicant_object.optString("no_of_dependency");


                                            copancardtxt.setText(copancard);
                                            cofathernametxt.setText(cofather);
                                            codateofbithtxt.setText(codob);
                                            comaritaltxt.setText(gomartical);
                                            conooddependents.setText(conoofdependes);
                                            cogendertxt.setText(cogender);
                                            coassetstxt.setText(formattedString1);
                                            coeducationqualtxt.setText(coeducationqualification);


                                            comonthincome = Coapplicant_object.optString("monthly_income");
                                            cosalarymode = Coapplicant_object.optString("salary_modestr");
                                            cocompanytype = Coapplicant_object.optString("company_typestr");
                                            cocompanyname = Coapplicant_object.optString("company_name");
                                            codesignation = Coapplicant_object.optString("designation");
                                            cocmpnypin = Coapplicant_object.optString("ofc_pincode");
                                            cocurrentexp = Coapplicant_object.optString("working_experience");
                                            cototalexp = Coapplicant_object.optString("total_experience");
                                            cootherincome = Coapplicant_object.optString("is_other_eranings_str");
                                            cootheramount = Coapplicant_object.optString("other_income");

                                            cosalaryproof = Coapplicant_object.optString("income_proof_typestr");
                                            coepfdeduct = Coapplicant_object.optString("is_epf_deduct_str");
                                            cotypeofemploye = Coapplicant_object.optString("employee_type_str");
                                            cocmpnypin = Coapplicant_object.optString("ofc_pincode");
                                            cocompanydoor = Coapplicant_object.optString("ofc_street");


                                            if (cootherincome.equalsIgnoreCase("No")) {
                                                co_other_income_amount.setVisibility(View.GONE);

                                            }

                                            String formattedString = cosalaryproof.toString()
                                                    .replace("[", "")  //remove the right bracket
                                                    .replace("]", "")
                                                    .replaceAll("\"", "")
                                                    .trim();

                                            //working Area array
                                            JSONArray area_array1 = Coapplicant_object.getJSONArray("work_areaarr");
                                            if (area_array1.length() > 0) {
                                                try {
                                                    JSONObject J = area_array1.getJSONObject(0);
                                                    area = J.optString("area");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }


                                            cosalary_mode.setText(cosalarymode);
                                            comonth_incometxt.setText(comonthincome);
                                            cosalaryproof_txt.setText(formattedString);
                                            coepfdeductsalarytxt.setText(coepfdeduct);
                                            cocompanytypetxt.setText(cocompanytype);
                                            cocompanynametxt.setText(cocompanyname);
                                            codesignationtxt.setText(codesignation);
                                            cotypeodemployetxt.setText(cotypeofemploye);
                                            cocompanydoortxt.setText(cocompanydoor);
                                            cocmpny_pintxt.setText(cocmpnypin);
                                            cocompanyarea_txt.setText(area);
                                            cocurrent_exptxt.setText(cocurrentexp);
                                            cototalexp_txt.setText(cototalexp);
                                            cootherincometxt.setText(cootherincome);
                                            cootherincome_amount1.setText(cootheramount);


                                        } else {

                                            if (CoApplicant_Status.equalsIgnoreCase("success")) {
                                                coapplicant_pandetails.setVisibility(View.VISIBLE);
                                                coapplicant_employementdeatilslay.setVisibility(View.GONE);
                                                coapplicant_selfemployementdeatilslay.setVisibility(View.VISIBLE);
                                                coapplicant_pandetails.setVisibility(View.VISIBLE);
                                                //  coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                                Coapplicant_object = jsonobj.getJSONObject("coapplicant_data");
                                                copancard = Coapplicant_object.optString("pan_no");
                                                codob = Coapplicant_object.optString("member_dob");
                                                cofather = Coapplicant_object.optString("father_name");
                                                gomartical = Coapplicant_object.optString("marital_statusstr");

                                                coeducationqualification = Coapplicant_object.optString("qualification_str");
                                                coassets = Coapplicant_object.optString("assetsstr");
                                                cogender = Coapplicant_object.optString("gender_str");
                                                conoofdependes = Coapplicant_object.optString("no_of_dependency");


                                                String formattedString3 = coassets.toString()
                                                        .replace("[", "")  //remove the right bracket
                                                        .replace("]", "")
                                                        .replaceAll("\"", "")
                                                        .trim();
                                                //Co APPLicant Personal details

                                                copancardtxt.setText(copancard);
                                                cofathernametxt.setText(cofather);
                                                codateofbithtxt.setText(codob);
                                                comaritaltxt.setText(gomartical);
                                                conooddependents.setText(conoofdependes);
                                                cogendertxt.setText(cogender);
                                                coassetstxt.setText(formattedString3);
                                                coeducationqualtxt.setText(coeducationqualification);


                                                codoyoufiletxtstr = Coapplicant_object.optString("is_itr_file_str");
                                                coaboutbustxtstr = Coapplicant_object.optString("about_company");
                                                cohaveaddressprooftxtstr = Coapplicant_object.optString("ofc_add_proof_str");
                                                coannualturntxtstr = Coapplicant_object.optString("turnover_curyr");
                                                coannualprofittxtstr = Coapplicant_object.optString("profit");

                                                cobus_numof_monthstr = Coapplicant_object.optString("working_experience");
                                                cobusiness_vintageproofstr = Coapplicant_object.optString("vintage_docstr");

                                                coofficesetupstr = Coapplicant_object.optString("office_setupstr");
                                                comonthincome = Coapplicant_object.optString("monthly_income");
                                                // cosalarymode = Coapplicant_object.optString("salary_modestr");
                                                cobus_vocationtypestr = Coapplicant_object.optString("vocation");
                                                cotypeofemploye = Coapplicant_object.optString("bus_employment_typestr");

                                                comonthincome = Coapplicant_object.optString("monthly_income");


                                                coofc_restypestr = Coapplicant_object.optString("office_res");
                                                cooffshoppincodestr = Coapplicant_object.optString("ofc_pincode");
                                                comonthincome = Coapplicant_object.optString("monthly_income");
                                                cobusinessincome_proofstr = Coapplicant_object.optString("income_proof_typestr");


                                                String year2 = String.valueOf(Integer.parseInt(cobus_numof_monthstr) / 12);
                                                String month2 = String.valueOf(Integer.parseInt(cobus_numof_monthstr) % 12);
                                                cobus_numof_month.setText(year2 + " year ," + month2 + " month ");
                                                cobusiness_vintageproof.setText(cobusiness_vintageproofstr);

                                                cotypeofemploye = Coapplicant_object.optString("bus_employment_typestr");


                                                coannualprofittxt.setText(coannualprofittxtstr);
                                                coannualturntxt.setText(coannualturntxtstr);
                                                cohaveaddressprooftxt.setText(cohaveaddressprooftxtstr);
                                                coaboutbustxt.setText(coaboutbustxtstr);
                                                codoyoufiletxt.setText(codoyoufiletxtstr);
                                                coofficesetup.setText(coofficesetupstr);
                                                comonth_incometxt.setText(comonthincome);
                                                cobus_typeemployemnt.setText("Self employed");
                                                // cosalary_mode.setText(cosalarymode);
                                                cobus_vocationtype.setText(cobus_vocationtypestr);
                                                cotypeodemployetxt.setText(cotypeofemploye);
                                                coofc_restype.setText(coofc_restypestr);
                                                cooffshoppincode.setText(cooffshoppincodestr);
                                                comonth_incometxt.setText(comonthincome);
                                                cobusinessincome_proof.setText(cobusinessincome_proofstr);
                                                cobus_typeself.setText(cotypeofemploye);
                                                cobus_avargeincome.setText(month_income);


                                            }


                                        }
                                    }


                                }
                            }





                            else if(loan_type.equals("32")) {


                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                Property_object = Applicant_object.getJSONObject("Property_details");
                                employee_status = Applicant_object.optString("employe_status");
                                Log.i("TAG", "employee status: " + employee_status);
                                //Showing Pancard Details
                                if (employee_status.equals("3")) {
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    Log.i("TAG", "pancardnumber_onResponse: " + pancardnumber);
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");

                                    gender=Applicant_object.optString("gender_str");

                                    qualificationstr = Applicant_object.optString("qualification_str");
                                    assetsstr=Applicant_object.optString("assetsstr");
                                    noofdependstr=Applicant_object.optString("no_of_dependency");

                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);


                                    unsecure_businessloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    vintage_docstr = Applicant_object.optString("vintage_docstr");
                                    String formattedString = vintage_docstr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();

                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income = String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    String formattedStrings = income.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status = Applicant_object.optString("employe_status");
                                    office_setup = Applicant_object.optString("office_setupstr");
                                    month_income = Applicant_object.optString("monthly_income");
                                    //  salarymodes = Applicant_object.optString("salary_modestr");
                                    vocation = Applicant_object.optString("vocation");
                                    typeemploy = Applicant_object.optString("bus_employment_typestr");
                                    bus_employment_type = Applicant_object.optString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: " + typeemploy);
                                    office_residence = Applicant_object.optString("office_res");
                                    office_pincode = Applicant_object.optString("ofc_pincode");
                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    residence_perarea = Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    work_vocationstr = Applicant_object.optString("work_vocationstr");
                                    office_setupstr = Applicant_object.optString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    String formattedString1 = assetsstr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();

                                    gendertxt.setText(gender);
                                    nooddependents.setText(noofdependstr);
                                    educationqualtxt.setText(qualificationstr);
                                    assetstxt.setText(formattedString1);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type = Applicant_object.optString("bus_employment_type");
                                    if (bus_employment_type.equals("1")) {
                                        work_vocationstr = Applicant_object.optString("bus_vocationstr");
                                    } else if (bus_employment_type.equals("2")) {
                                        work_vocationstr = Applicant_object.optString("work_vocationstr");
                                    } else {

                                        work_vocationstr = Applicant_object.optString("vocation_str");
                                    }

                                    office_setup1 = Applicant_object.optString("office_setup");

                                    if (office_setup1.equals("1") || office_setup1.equals("3")) {
                                        office_residence_ly.setVisibility(View.GONE);
                                        rsidence_pincode.setVisibility(View.GONE);
                                    } else {
                                        office_residence_ly.setVisibility(View.VISIBLE);
                                        rsidence_pincode.setVisibility(View.VISIBLE);
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");


                                    if (income_type.equals("2")) {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    } else {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome_self.setText(income_typestr);
                                        otherincome_amount1_self.setText(otherincome_details1);
                                        other_income_amount_self.setVisibility(View.VISIBLE);


                                    }

                                    bus_vocationtype.setText(work_vocationstr);
                                    bus_avargeincome.setText(month_income);
                                    String year, month, year1, month1;

                                    year = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(curr_exp) % 12);
                                    bus_numof_month.setText(year + " year ," + month + " month ");
                                    ofc_restype.setText(office_setupstr);
                                    offshoppincode.setText(office_pincode);
                                    //   business_vintageproof.setText(vintage_docstr);
                                    business_vintageproof.setText(formattedString);
                                    businessincome_proof.setText(formattedStrings);
                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);
                                    unsecure_residence_pincode.setText(residence_pincode);

                                    vehicledetailslay.setVisibility(View.VISIBLE);

                                    prop_prices = Property_object.optString("vehicle_reg_no");
                                    prop_pincodes = Property_object.optString("vh_makeby_text");
                                    prop_title = Property_object.optString("vh_model_text");
                                    prop_type = Property_object.optString("age_of_vehicle");
                                    prop_iden = Property_object.optString("vh_kmdriven");
                                    Log.i("TAG", "onResponse:prop_iden " + prop_iden);


                                    regnumtxt.setText(prop_prices);
                                    brandtxt.setText(prop_pincodes);
                                    modeltxt.setText(prop_title);
                                    agevehicletxt.setText(prop_type);
                                    totalkmtxt.setText(prop_iden);


                                } else if (employee_status.equals("1")) {


                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.optString("pan_no");
                                    dateofbirth = Applicant_object.optString("member_dob");
                                    fathername = Applicant_object.optString("father_name");
                                    maritalstatus = Applicant_object.optString("marital_statusstr");

                                    //Employement Details
                                    month_income = Applicant_object.optString("monthly_income");
                                    salarymodes = Applicant_object.optString("salary_modestr");
                                    cmpny_type = Applicant_object.optString("company_typestr");
                                    cmpny_name = Applicant_object.optString("company_name");
                                    designation = Applicant_object.optString("designation");
                                    cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                    curr_exp = Applicant_object.optString("working_experience");
                                    total_exp = Applicant_object.optString("total_experience");
                                    income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                    Log.i("TAG", "onResponse:income_proof_typestr " + income_proof_typestr);


                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    //Residenence Pincode
                                    residence_pincode = Applicant_object.optString("per_pincode");
                                    //residence_perarea=Applicant_object.optString("per_area");
                                    residence_type = Applicant_object.optString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.optString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    residenctstatus=Applicant_object.optString("resident_status");


                                    if(residenctstatus.equals("1") || residenctstatus.equals("2")){

                                        rentpaidlay.setVisibility(View.GONE);
                                        permanentrespinlay.setVisibility(View.GONE);
                                        permanentrestypelay.setVisibility(View.GONE);
                                        liveincurrentlay.setVisibility(View.GONE);

                                    }else{
                                        perresstr=Applicant_object.optString("perm_res_pincode");
                                        liveinstr=Applicant_object.optString("current_home_duration");
                                        perrestypestr=Applicant_object.optString("perm_residence_str");
                                        rentpaid=Applicant_object.optString("rent_beingpaid");
                                        currentrstypestr=Applicant_object.optString("address_proof_str");


                                        rentpaidlay.setVisibility(View.VISIBLE);
                                        permanentrespinlay.setVisibility(View.VISIBLE);
                                        permanentrestypelay.setVisibility(View.VISIBLE);
                                        liveincurrentlay.setVisibility(View.VISIBLE);
                                    }


                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    other_income_amount.setVisibility(View.GONE);
                                    type_of_emploe.setVisibility(View.GONE);
                                    //  type_of_emploe1.setVisibility(View.GONE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);
                                    salary_mode.setText(salarymodes);
                                    month_incometxt.setText(month_income);
                                    companytypetxt.setText(cmpny_type);
                                    companynametxt.setText(cmpny_name);
                                    designationtxt.setText(designation);
                                    cmpny_pintxt.setText(cmpny_pincode);


                                    String year, month, year1, month1;

                                    year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                    month = String.valueOf(Integer.parseInt(total_exp) % 12);

                                    // totalexp_txt.setText(total_exp);
                                    totalexp_txt.setText(year + " year ," + month + " month ");

                                    year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                    month1 = String.valueOf(Integer.parseInt(curr_exp) % 12);
                                    //current_exptxt.setText(curr_exp);
                                    current_exptxt.setText(year1 + " year ," + month1 + " month ");

                                    companyarea_txt.setText(area);
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    unsecure_residence_pincode.setText(residence_pincode);
                                    unsecure_resarea.setText(res_area);
                                    unsecure_restype.setText(residence_type);

                                    perrespincodetxt.setText(perresstr);
                                    // otherincome_amount1.setText(haveotheramount);
                                    rentpaidforhousetxt.setText(rentpaid);
                                    liveincurrenttxt.setText(liveinstr);
                                    //   perrestypecodetxt.setText(currentrstypestr);
                                    perrestypecodetxt.setText(perrestypestr);
                                    curres_addressprofftxt.setText(currentaddresproof);

                                    employeproofstr=Applicant_object.optString("employee_proof_str");
                                    employeproofid=Applicant_object.optString("ofc_add_proof");
                                    officailmailid_str=Applicant_object.optString("office_email");
                                    if (employeproofid.equalsIgnoreCase("2")){
                                        officialmailidlay.setVisibility(View.VISIBLE);
                                        officialmailidtxt.setText(officailmailid_str);
                                    }else{
                                        officialmailidlay.setVisibility(View.GONE);

                                    }
                                    employementprooftxtshow.setText(employeproofstr);


                                  /*  otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.optString("income_type");
                                    String income_typestr = otherincome_details.optString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.optString("income_amount");
                                        otherincome.setText(income_typestr);
                                        otherincome_amount1.setText(otherincome_details1);
                                        other_income_amount.setVisibility(View.VISIBLE);

                                    }*/


                                    bus_salrycredit.setText(salarymodes);
                                    String formattedString2 = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                    salaryproof_txt.setText(formattedString2);

                                    vehicledetailslay.setVisibility(View.VISIBLE);

                                    prop_prices = Property_object.optString("vehicle_reg_no");
                                    prop_pincodes = Property_object.optString("vh_makeby_text");
                                    prop_title = Property_object.optString("vh_model_text");
                                    prop_type = Property_object.optString("age_of_vehicle");
                                    prop_iden = Property_object.optString("vh_kmdriven");
                                    Log.i("TAG", "onResponse:prop_iden " + prop_iden);


                                    regnumtxt.setText(prop_prices);
                                    brandtxt.setText(prop_pincodes);
                                    modeltxt.setText(prop_title);
                                    agevehicletxt.setText(prop_type);
                                    totalkmtxt.setText(prop_iden);

                                    CoApplicant_Status =jsonobj.optString("coapplicant_status");
                                    if(CoApplicant_Status.equalsIgnoreCase("success")) {
                                        coapplicant_pandetails.setVisibility(View.VISIBLE);
                                        coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                        Coapplicant_object = jsonobj.getJSONObject("coapplicant_data");
                                        employee_status1 = Coapplicant_object.optString("employe_status");
                                        if (employee_status.equalsIgnoreCase("1")) {
                                            coapplicant_pandetails.setVisibility(View.VISIBLE);
                                            // coapplicant_selfemployementdeatilslay.setVisibility(View.VISIBLE);
                                            coapplicant_pandetails.setVisibility(View.VISIBLE);
                                            coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                            //  coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                            Coapplicant_object = jsonobj.getJSONObject("coapplicant_data");
                                            copancard = Coapplicant_object.optString("pan_no");
                                            codob = Coapplicant_object.optString("member_dob");
                                            cofather = Coapplicant_object.optString("father_name");
                                            gomartical = Coapplicant_object.optString("marital_statusstr");

                                            coeducationqualification = Coapplicant_object.optString("qualification_str");
                                            coassets = Coapplicant_object.optString("assetsstr");
                                            cogender = Coapplicant_object.optString("gender_str");
                                            conoofdependes = Coapplicant_object.optString("no_of_dependency");
                                            String formattedString32 = coassets.toString()
                                                    .replace("[", "")  //remove the right bracket
                                                    .replace("]", "")
                                                    .replaceAll("\"", "")
                                                    .trim();


                                            copancardtxt.setText(copancard);
                                            cofathernametxt.setText(cofather);
                                            codateofbithtxt.setText(codob);
                                            comaritaltxt.setText(gomartical);
                                            conooddependents.setText(conoofdependes);
                                            cogendertxt.setText(cogender);
                                            coassetstxt.setText(formattedString32);
                                            coeducationqualtxt.setText(coeducationqualification);


                                            comonthincome = Coapplicant_object.optString("monthly_income");
                                            cosalarymode = Coapplicant_object.optString("salary_modestr");
                                            cocompanytype = Coapplicant_object.optString("company_typestr");
                                            cocompanyname = Coapplicant_object.optString("company_name");
                                            codesignation = Coapplicant_object.optString("designation");
                                            cocmpnypin = Coapplicant_object.optString("ofc_pincode");
                                            cocurrentexp = Coapplicant_object.optString("working_experience");
                                            cototalexp = Coapplicant_object.optString("total_experience");
                                            cootherincome = Coapplicant_object.optString("is_other_eranings_str");
                                            cootheramount = Coapplicant_object.optString("other_income");

                                            cosalaryproof = Coapplicant_object.optString("income_proof_typestr");
                                            coepfdeduct = Coapplicant_object.optString("is_epf_deduct_str");
                                            cotypeofemploye = Coapplicant_object.optString("employee_type_str");
                                            cocmpnypin = Coapplicant_object.optString("ofc_pincode");
                                            cocompanydoor = Coapplicant_object.optString("ofc_street");


                                            if (cootherincome.equalsIgnoreCase("No")) {
                                                co_other_income_amount.setVisibility(View.GONE);

                                            }

                                            String formattedString = cosalaryproof.toString()
                                                    .replace("[", "")  //remove the right bracket
                                                    .replace("]", "")
                                                    .replaceAll("\"", "")
                                                    .trim();

                                            //working Area array
                                            JSONArray area_array1 = Coapplicant_object.getJSONArray("work_areaarr");
                                            if (area_array1.length() > 0) {
                                                try {
                                                    JSONObject J = area_array1.getJSONObject(0);
                                                    area = J.optString("area");
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }


                                            cosalary_mode.setText(cosalarymode);
                                            comonth_incometxt.setText(comonthincome);
                                            cosalaryproof_txt.setText(formattedString);
                                            coepfdeductsalarytxt.setText(coepfdeduct);
                                            cocompanytypetxt.setText(cocompanytype);
                                            cocompanynametxt.setText(cocompanyname);
                                            codesignationtxt.setText(codesignation);
                                            cotypeodemployetxt.setText(cotypeofemploye);
                                            cocompanydoortxt.setText(cocompanydoor);
                                            cocmpny_pintxt.setText(cocmpnypin);
                                            cocompanyarea_txt.setText(area);
                                            cocurrent_exptxt.setText(cocurrentexp);
                                            cototalexp_txt.setText(cototalexp);
                                            cootherincometxt.setText(cootherincome);
                                            cootherincome_amount1.setText(cootheramount);


                                        }else{

                                            if(CoApplicant_Status.equalsIgnoreCase("success")) {
                                                coapplicant_pandetails.setVisibility(View.VISIBLE);
                                                coapplicant_employementdeatilslay.setVisibility(View.GONE);
                                                coapplicant_selfemployementdeatilslay.setVisibility(View.VISIBLE);
                                                coapplicant_pandetails.setVisibility(View.VISIBLE);
                                                //  coapplicant_employementdeatilslay.setVisibility(View.VISIBLE);
                                                Coapplicant_object = jsonobj.getJSONObject("coapplicant_data");
                                                copancard = Coapplicant_object.optString("pan_no");
                                                codob = Coapplicant_object.optString("member_dob");
                                                cofather = Coapplicant_object.optString("father_name");
                                                gomartical = Coapplicant_object.optString("marital_statusstr");

                                                coeducationqualification = Coapplicant_object.optString("qualification_str");
                                                coassets = Coapplicant_object.optString("assetsstr");
                                                cogender = Coapplicant_object.optString("gender_str");
                                                conoofdependes = Coapplicant_object.optString("no_of_dependency");


                                                String formattedString3 = coassets.toString()
                                                        .replace("[", "")  //remove the right bracket
                                                        .replace("]", "")
                                                        .replaceAll("\"", "")
                                                        .trim();
                                                //Co APPLicant Personal details

                                                copancardtxt.setText(copancard);
                                                cofathernametxt.setText(cofather);
                                                codateofbithtxt.setText(codob);
                                                comaritaltxt.setText(gomartical);
                                                conooddependents.setText(conoofdependes);
                                                cogendertxt.setText(cogender);
                                                coassetstxt.setText(formattedString3);
                                                coeducationqualtxt.setText(coeducationqualification);


                                                codoyoufiletxtstr = Coapplicant_object.optString("is_itr_file_str");
                                                coaboutbustxtstr = Coapplicant_object.optString("about_company");
                                                cohaveaddressprooftxtstr = Coapplicant_object.optString("ofc_add_proof_str");
                                                coannualturntxtstr = Coapplicant_object.optString("turnover_curyr");
                                                coannualprofittxtstr = Coapplicant_object.optString("profit");

                                                cobus_numof_monthstr = Coapplicant_object.optString("working_experience");
                                                cobusiness_vintageproofstr = Coapplicant_object.optString("vintage_docstr");

                                                coofficesetupstr = Coapplicant_object.optString("office_setupstr");
                                                comonthincome = Coapplicant_object.optString("monthly_income");
                                                // cosalarymode = Coapplicant_object.optString("salary_modestr");
                                                cobus_vocationtypestr = Coapplicant_object.optString("vocation");
                                                cotypeofemploye = Coapplicant_object.optString("bus_employment_typestr");

                                                comonthincome = Coapplicant_object.optString("monthly_income");


                                                coofc_restypestr = Coapplicant_object.optString("office_res");
                                                cooffshoppincodestr = Coapplicant_object.optString("ofc_pincode");
                                                comonthincome = Coapplicant_object.optString("monthly_income");
                                                cobusinessincome_proofstr = Coapplicant_object.optString("income_proof_typestr");


                                                String year2 = String.valueOf(Integer.parseInt(cobus_numof_monthstr) / 12);
                                                String month2 = String.valueOf(Integer.parseInt(cobus_numof_monthstr) % 12);
                                                cobus_numof_month.setText(year2 + " year ," + month2 + " month ");
                                                cobusiness_vintageproof.setText(cobusiness_vintageproofstr);

                                                cotypeofemploye = Coapplicant_object.optString("bus_employment_typestr");


                                                coannualprofittxt.setText(coannualprofittxtstr);
                                                coannualturntxt.setText(coannualturntxtstr);
                                                cohaveaddressprooftxt.setText(cohaveaddressprooftxtstr);
                                                coaboutbustxt.setText(coaboutbustxtstr);
                                                codoyoufiletxt.setText(codoyoufiletxtstr);
                                                coofficesetup.setText(coofficesetupstr);
                                                comonth_incometxt.setText(comonthincome);
                                                cobus_typeemployemnt.setText("Self employed");
                                                // cosalary_mode.setText(cosalarymode);
                                                cobus_vocationtype.setText(cobus_vocationtypestr);
                                                cotypeodemployetxt.setText(cotypeofemploye);
                                                coofc_restype.setText(coofc_restypestr);
                                                cooffshoppincode.setText(cooffshoppincodestr);
                                                comonth_incometxt.setText(comonthincome);
                                                cobusinessincome_proof.setText(cobusinessincome_proofstr);
                                                cobus_typeself.setText(cotypeofemploye);
                                                cobus_avargeincome.setText(month_income);
                                            }
                                            }
                                    }



                                }
                            }



















                            else if(loan_type.equals("33")){
                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                //    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.optString("pan_no");
                                dateofbirth = Applicant_object.optString("member_dob");
                                fathername = Applicant_object.optString("father_name");
                                maritalstatus = Applicant_object.optString("marital_statusstr");


                                gender=Applicant_object.optString("gender_str");
                                qualificationstr = Applicant_object.optString("qualification_str");
                                assetsstr=Applicant_object.optString("assetsstr");
                                noofdependstr=Applicant_object.optString("no_of_dependency");
                                epfdeductstr=Applicant_object.optString("is_epf_deduct_str");
                                typeofemp_str=Applicant_object.optString("employee_type_str");
                                haveotherincome=Applicant_object.optString("is_other_eranings_str");
                                haveotheramount=Applicant_object.optString("other_income");
                                ofcstreet=Applicant_object.optString("ofc_street");

                                employeproofstr=Applicant_object.optString("employee_proof_str");
                                employeproofid=Applicant_object.optString("ofc_add_proof");
                                officailmailid_str=Applicant_object.optString("office_email");
                                if (employeproofid.equalsIgnoreCase("2")){
                                    officialmailidlay.setVisibility(View.VISIBLE);
                                    officialmailidtxt.setText(officailmailid_str);
                                }else{
                                    officialmailidlay.setVisibility(View.GONE);

                                }
                                employementprooftxtshow.setText(employeproofstr);

                                currentrstypestr=Applicant_object.optString("address_proof_str");

                                residenctstatus=Applicant_object.optString("resident_status");


                                if(residenctstatus.equals("1") || residenctstatus.equals("2")){

                                    rentpaidlay.setVisibility(View.GONE);
                                    permanentrespinlay.setVisibility(View.GONE);
                                    permanentrestypelay.setVisibility(View.GONE);
                                    liveincurrentlay.setVisibility(View.GONE);

                                }else{
                                    perresstr=Applicant_object.optString("perm_res_pincode");
                                    liveinstr=Applicant_object.optString("current_home_duration");
                                    perrestypestr=Applicant_object.optString("perm_residence_str");
                                    rentpaid=Applicant_object.optString("rent_beingpaid");

                                    rentpaidlay.setVisibility(View.VISIBLE);
                                    permanentrespinlay.setVisibility(View.VISIBLE);
                                    permanentrestypelay.setVisibility(View.VISIBLE);
                                    liveincurrentlay.setVisibility(View.VISIBLE);
                                }



                                //Employement Details
                                month_income = Applicant_object.optString("monthly_income");
                                salarymodes = Applicant_object.optString("salary_modestr");
                                cmpny_type = Applicant_object.optString("company_typestr");
                                cmpny_name = Applicant_object.optString("company_name");
                                is_epf_deduct = Applicant_object.optString("is_epf_deduct");
                                designation = Applicant_object.optString("designation");
                                cmpny_pincode = Applicant_object.optString("ofc_pincode");
                                curr_exp = Applicant_object.optString("working_experience");
                                total_exp = Applicant_object.optString("total_experience");
                                income_proof_typestr = Applicant_object.optString("income_proof_typestr");
                                residence_Street=Applicant_object.optString("street");
                                creditscorestr=Applicant_object.optString("entered_credit_score_str");
                                emilatestr=Applicant_object.optString("emi_late_str");
                                banksalstr=Applicant_object.optString("salary_bank_str");
                                runloanstr=Applicant_object.optString("is_existloan_str");
                                witeoffstr=Applicant_object.optString("is_write_off_str");
                                bankpdfstr=Applicant_object.optString("arrange_bank_pdf_str");
                                currentrstypestr=Applicant_object.optString("address_proof_str");
                                currentaddresproof=Applicant_object.optString("address_proof_str");





                                //working Area array
                                JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                if (area_array.length() > 0) {
                                    try {
                                        JSONObject J = area_array.getJSONObject(0);
                                        area = J.optString("area");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                //Residenence Pincode
                                residence_pincode = Applicant_object.optString("per_pincode");
                                //residence_perarea=Applicant_object.optString("per_area");
                                residence_type = Applicant_object.optString("resident_statusstr");
                                //residence area array value

                                JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                if (res_array.length() > 0) {
                                    try {
                                        JSONObject J = res_array.getJSONObject(0);
                                        res_area = J.optString("area");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                unsecure_personalloan.setVisibility(View.VISIBLE);
                                //  epflay.setVisibility(View.VISIBLE);
                                unsecure_residencelay.setVisibility(View.VISIBLE);
                                creditbanklay.setVisibility(View.VISIBLE);

                                salary_mode.setText(salarymodes);
                                month_incometxt.setText(month_income);
                                companytypetxt.setText(cmpny_type);
                                companynametxt.setText(cmpny_name);
                                designationtxt.setText(designation);
                                cmpny_pintxt.setText(cmpny_pincode);



                                String year,month,year1,month1;

                                year = String.valueOf(Integer.parseInt(total_exp) / 12);
                                month = String.valueOf(Integer.parseInt(total_exp)  % 12);

                                // totalexp_txt.setText(total_exp);
                                totalexp_txt.setText(year +" year ,"+ month+" month ");

                                year1 = String.valueOf(Integer.parseInt(curr_exp) / 12);
                                month1 = String.valueOf(Integer.parseInt(curr_exp)  % 12);
                                //current_exptxt.setText(curr_exp);
                                current_exptxt.setText(year1 +" year ,"+ month1+" month ");

                                companyarea_txt.setText(area);
                                pancardtxt.setText(pancardnumber);
                                dateofbithtxt.setText(dateofbirth);
                                fathernametxt.setText(fathername);
                                maritaltxt.setText(maritalstatus);
                                unsecure_residence_pincode.setText(residence_pincode);
                                unsecure_resarea.setText(res_area);
                                unsecure_restype.setText(residence_type);


                                /*otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                String income_type = otherincome_details.optString("income_type");
                                String income_typestr = otherincome_details.optString("income_typestr");
                                if( income_type.equals("2"))
                                {
                                    otherincome.setText(income_typestr);
                                    other_income_amount.setVisibility(View.GONE);

                                }else
                                {
                                    String otherincome_details1 = otherincome_details.optString("income_amount");
                                    otherincome.setText(income_typestr);
                                    otherincome_amount1.setText(otherincome_details1);
                                    other_income_amount.setVisibility(View.VISIBLE);

                                }
*/
                                String formattedString1 = assetsstr.toString()
                                        .replace("[", "")  //remove the right bracket
                                        .replace("]", "")
                                        .replaceAll("\"", "")
                                        .trim();

                                gendertxt.setText(gender);
                                nooddependents.setText(noofdependstr);
                                educationqualtxt.setText(qualificationstr);
                                assetstxt.setText(formattedString1);
                                epfdeductsalarytxt.setText(epfdeductstr);
                                typeodemployetxt.setText(typeofemp_str);
                                companydoor.setText(residence_Street);
                                otherincometxt.setText(haveotherincome);
                                perrespincodetxt.setText(perresstr);
                                otherincome_amount1.setText(haveotheramount);
                                rentpaidforhousetxt.setText(rentpaid);
                                liveincurrenttxt.setText(liveinstr);
                                perrestypecodetxt.setText(perrestypestr);
                                curres_addressprofftxt.setText(currentaddresproof);
                                companydoortxt.setText(ofcstreet);
                                creditscoretext.setText(creditscorestr);
                                emilatetxt.setText(emilatestr);
                                banksalary.setText(banksalstr);
                                runningloantxt.setText(runloanstr);
                                witeofftxt.setText(witeoffstr);
                                bankpdftxt.setText(bankpdfstr);




                                bus_salrycredit.setText(salarymodes);
                                String formattedString = income_proof_typestr.toString()
                                        .replace("[", "")  //remove the right bracket
                                        .replace("]", "")
                                        .replaceAll("\"", "")
                                        .trim();
                                salaryproof_txt.setText(formattedString);
                                if(is_epf_deduct.equals("1")){
                                    epfproof_txt.setText("Yes");
                                }else if (is_epf_deduct.equals("2")){
                                    epfproof_txt.setText("No");
                                }else{
                                    epfproof_txt.setText("");
                                }



                            }

                            /*  //Extension Loan - 9
                             */

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        // progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "APIError: " + error.getMessage());
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
        mRequestQueue.add(jsonObjReq);
    }
}