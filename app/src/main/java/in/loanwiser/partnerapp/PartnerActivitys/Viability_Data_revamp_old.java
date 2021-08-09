package in.loanwiser.partnerapp.PartnerActivitys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

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
import in.loanwiser.partnerapp.R;

public class Viability_Data_revamp_old extends SimpleActivity {

    CardView pancardview_lay;

    LinearLayout pandetails_txtlay,propert_title,propert_pincode,proprty_type,property_price;

    LinearLayout unsecure_personalloan, unsecure_businessloan,unsecure_residencelay,cost_estimation, office_residence_ly,
    rsidence_pincode,other_income_amount,other_income_amount_self;

    LinearLayout secured_homeloanlay,securelaplay,secureplot_constructionlay,secureplot_loanlay,secure_btlay,secure_improvementlay,secure_extensionlay;

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
    AppCompatTextView lap_proptitle,lap_proppincode,lapprop_type,lapprop_price,otherincome_amount1,otherincome_amount1_self;

    //Extensionloan_propfields
    AppCompatTextView exten_proptitle, extension_proppincode, extension_prop_type, exten_cost_extement,exten_pro_price;

    // unsecure residence type
    AppCompatTextView unsecure_residence_pincode ,unsecure_resarea ,unsecure_restype;

    //plat+construction
    AppCompatTextView secureplot_cons,secure_ploatoincode,secureplot_prop_type,secureplo_value,secureplot_cost_estimate,
            prop_plot_title,prop_Pincode,prop_plot_identified,prop_plot_cost_of_plot,otherincome,otherincome_self;




    final String url = "https://cscapi.propwiser.com/mobile/partner_loanapi_test.php?call=view_viability_revamp";


    private RequestQueue mRequestQueue;

    private String pancardnumber,dateofbirth,fathername,maritalstatus;
    private String salarymodes,month_income, cmpny_type,cmpny_name, designation, cmpny_pincode, curr_exp,total_exp,
            income_proof_typestr,vintage_docstr,income_proof_typestr1;

    // propertyvalue

    String prop_iden,prop_prices,prop_pincodes,prop_title, prop_type,cost_estimate,cost_of_land;

    String employee_status,office_setup,vocation,avgincome,typeemploy,office_residence,office_pincode, incprof,
            work_vocationstr,office_setupstr,bus_employment_type,office_setup1;

    String residence_pincode,residence_perarea,residence_type,Applicant_Status,loan_type;
    JSONObject Applicant_object,jsonobj,Property_object,otherincome_details;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

       // setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_viability__data_revamp_old);
        initTools(R.string.viy_check);

        progressDialog = new SpotsDialog(Viability_Data_revamp_old.this, R.style.Custom);
        pancardview_lay=findViewById(R.id.pancardview_lay);
        unsecure_businessloan=findViewById(R.id.unsecure_businessloan);
        unsecure_personalloan=findViewById(R.id.unsecure_personalloan);
        unsecure_residencelay=findViewById(R.id.unsecure_residencelay);

        propert_title=findViewById(R.id.propert_title);

        propert_pincode=findViewById(R.id.propert_pincode);
        proprty_type=findViewById(R.id.proprty_type);
        property_price=findViewById(R.id.property_price);

        otherincome=findViewById(R.id.otherincome);
        otherincome_self=findViewById(R.id.otherincome_self);
        other_income_amount_self=findViewById(R.id.other_income_amount_self);
        otherincome_amount1=findViewById(R.id.otherincome_amount1);
        otherincome_amount1_self=findViewById(R.id.otherincome_amount1_self);

        secured_homeloanlay=findViewById(R.id.secured_homeloanlay);
        secureplot_loanlay=findViewById(R.id.secureplot_loanlay);
        securelaplay=findViewById(R.id.securelaplay);
        secureplot_constructionlay=findViewById(R.id.secureplot_constructionlay);
        cost_estimation=findViewById(R.id.cost_estimation);
        secure_btlay=findViewById(R.id.secure_btlay);
        secure_improvementlay=findViewById(R.id.secure_improvementlay);
        secure_extensionlay=findViewById(R.id.secure_extensionlay);



         office_residence_ly=findViewById(R.id.office_residence_ly);
        rsidence_pincode=findViewById(R.id.rsidence_pincode);
        other_income_amount=findViewById(R.id.other_income_amount);



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
                            Applicant_Status =jsonobj.getString("applicant_status");
                            Applicant_object = jsonobj.getJSONObject("applicant_data");
                            loan_type=jsonobj.getString("loan_type");
                            Log.i("TAG", "Loantype: "+loan_type);
                            if (loan_type.equals("21")) {
                                Applicant_object = jsonobj.getJSONObject("applicant_data");
                                otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.getString("pan_no");
                                dateofbirth = Applicant_object.getString("member_dob");
                                fathername = Applicant_object.getString("father_name");
                                maritalstatus = Applicant_object.getString("marital_statusstr");

                                //Employement Details
                                month_income = Applicant_object.getString("monthly_income");
                                salarymodes = Applicant_object.getString("salary_modestr");
                                cmpny_type = Applicant_object.getString("company_typestr");
                                cmpny_name = Applicant_object.getString("company_name");
                                designation = Applicant_object.getString("designation");
                                cmpny_pincode = Applicant_object.getString("ofc_pincode");
                                curr_exp = Applicant_object.getString("working_experience");
                                total_exp = Applicant_object.getString("total_experience");
                                income_proof_typestr = Applicant_object.getString("income_proof_typestr");


                                //working Area array
                                JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                if (area_array.length() > 0) {
                                    try {
                                        JSONObject J = area_array.getJSONObject(0);
                                        area = J.getString("area");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                //Residenence Pincode
                                residence_pincode = Applicant_object.getString("per_pincode");
                                //residence_perarea=Applicant_object.getString("per_area");
                                residence_type = Applicant_object.optString("resident_statusstr");
                                //residence area array value

                                JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                if (res_array.length() > 0) {
                                    try {
                                        JSONObject J = res_array.getJSONObject(0);
                                        res_area = J.getString("area");
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
                                String income_type = otherincome_details.getString("income_type");
                                String income_typestr = otherincome_details.getString("income_typestr");
                                if( income_type.equals("4"))
                                {
                                    otherincome.setText(income_typestr);
                                    other_income_amount.setVisibility(View.GONE);

                                }else
                                {
                                    String otherincome_details1 = otherincome_details.getString("income_amount");
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
                                pancardnumber = Applicant_object.getString("pan_no");
                                dateofbirth = Applicant_object.getString("member_dob");
                                fathername = Applicant_object.getString("father_name");
                                maritalstatus = Applicant_object.getString("marital_statusstr");
                                curr_exp = Applicant_object.getString("working_experience");

                                vintage_docstr = Applicant_object.getString("vintage_docstr");
                                income_proof_typestr1=Applicant_object.getString("income_proof_typestr");

                                //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                //String text = income.toString().replace("[", "").replace("]", "");
                                JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                if (res_array.length() > 0) {
                                    try {
                                        JSONObject J = res_array.getJSONObject(0);
                                        res_area = J.getString("area");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                employee_status=Applicant_object.getString("employe_status");
                                office_setup1=Applicant_object.getString("office_setup");

                                if(office_setup1.equals("1") || office_setup1.equals("3"))
                                {
                                    office_residence_ly.setVisibility(View.GONE);
                                            rsidence_pincode.setVisibility(View.GONE);
                                }else
                                {
                                    office_residence_ly.setVisibility(View.VISIBLE);
                                    rsidence_pincode.setVisibility(View.VISIBLE);
                                }

                                office_setup=Applicant_object.getString("office_setupstr");
                                month_income = Applicant_object.getString("monthly_income");
                                //  salarymodes = Applicant_object.getString("salary_modestr");
                                vocation=Applicant_object.getString("vocation");
                                bus_employment_type=Applicant_object.getString("bus_employment_type");


                                typeemploy=Applicant_object.getString("bus_employment_typestr");
                                Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                office_residence=Applicant_object.getString("office_res");
                                office_pincode=Applicant_object.getString("ofc_pincode");
                                residence_pincode=Applicant_object.getString("per_pincode");
                                residence_perarea=Applicant_object.getString("per_area");
                                residence_type=Applicant_object.getString("resident_statusstr");

                                if(bus_employment_type.equals("1"))
                                {
                                    work_vocationstr=Applicant_object.getString("bus_vocationstr");
                                }else if(bus_employment_type.equals("2"))
                                {
                                    work_vocationstr=Applicant_object.getString("work_vocationstr");
                                }else {

                                    work_vocationstr=Applicant_object.getString("work_vocationstr");
                                }

                                otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                String income_type = otherincome_details.getString("income_type");
                                String income_typestr = otherincome_details.getString("income_typestr");
                                if( income_type.equals("4"))
                                {
                                    otherincome_self.setText(income_typestr);
                                    other_income_amount_self.setVisibility(View.GONE);

                                }else
                                {
                                    String otherincome_details1 = otherincome_details.getString("income_amount");
                                    otherincome_self.setText(income_typestr);
                                    otherincome_amount1_self.setText(otherincome_details1);
                                    other_income_amount_self.setVisibility(View.VISIBLE);


                                }

                                office_setupstr=Applicant_object.getString("resident_statusstr");
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
                                employee_status=Applicant_object.getString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.getString("pan_no");
                                dateofbirth = Applicant_object.getString("member_dob");
                                fathername = Applicant_object.getString("father_name");
                                maritalstatus = Applicant_object.getString("marital_statusstr");
                                //Salaried type employee_status 1
                                if (employee_status.equals("1")){

                                    secured_homeloanlay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    prop_iden = Property_object.getString("prop_identifiedstr");

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

                                        prop_iden = Property_object.getString("prop_identifiedstr");
                                        prop_prices = Property_object.getString("property_value");
                                        prop_pincodes = Property_object.getString("pincode");
                                        prop_title = Property_object.getString("prop_titlestr");
                                        prop_type = Property_object.getString("prop_typestr");
                                        cost_estimate=Property_object.getString("cost_of_construction");

                                        prop_identxt.setText(prop_iden);
                                        prop_pincode.setText(prop_pincodes);
                                        prop_pricetxt.setText(prop_prices);
                                        prop_typetxt.setText(prop_type);
                                        prop_titletxt.setText(prop_title);
                                    }



                                    //Employement Details
                                    month_income = Applicant_object.getString("monthly_income");
                                    salarymodes = Applicant_object.getString("salary_modestr");
                                    cmpny_type = Applicant_object.getString("company_typestr");
                                    cmpny_name = Applicant_object.getString("company_name");
                                    designation = Applicant_object.getString("designation");
                                    cmpny_pincode = Applicant_object.getString("ofc_pincode");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    total_exp = Applicant_object.getString("total_experience");


                                    income_proof_typestr = Applicant_object.getString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                  //  salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);

                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");



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
                                            String workarea = J.getString("area");
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

                                    residence_pincode = Applicant_object.getString("per_pincode");
                                    //residence_perarea=Applicant_object.getString("per_area");

                                    //residence area array value
                                   // companyarea_txt.setText(area);
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
                                        otherincome.setText(income_typestr);
                                        otherincome_amount1.setText(otherincome_details1);
                                        other_income_amount.setVisibility(View.VISIBLE);


                                    }

                                    unsecure_resarea.setText(res_area);
                                    unsecure_residence_pincode.setText(residence_pincode);
                                    residence_type = Applicant_object.getString("resident_statusstr");
                                    unsecure_restype.setText(residence_type);

                                }
                                //self employedd type
                                else{
                                    secured_homeloanlay.setVisibility(View.VISIBLE);
                                    unsecure_businessloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);

                                    prop_iden = Property_object.getString("prop_identifiedstr");

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

                                        prop_prices = Property_object.getString("property_value");
                                        prop_pincodes = Property_object.getString("pincode");
                                        prop_title = Property_object.getString("prop_titlestr");
                                        prop_type = Property_object.getString("prop_typestr");
                                        cost_estimate=Property_object.getString("cost_of_construction");

                                        prop_identxt.setText(prop_iden);
                                        prop_pincode.setText(prop_pincodes);
                                        prop_pricetxt.setText(prop_prices);
                                        prop_typetxt.setText(prop_type);
                                        prop_titletxt.setText(prop_title);
                                    }


                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    vintage_docstr = Applicant_object.getString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.getString("employe_status");
                                    office_setup=Applicant_object.getString("office_setupstr");
                                    month_income = Applicant_object.getString("monthly_income");
                                    //  salarymodes = Applicant_object.getString("salary_modestr");
                                    vocation=Applicant_object.getString("vocation");
                                    typeemploy=Applicant_object.getString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.getString("office_res");
                                    office_pincode=Applicant_object.getString("ofc_pincode");
                                    residence_pincode=Applicant_object.getString("per_pincode");
                                    residence_perarea=Applicant_object.getString("per_area");
                                    residence_type=Applicant_object.getString("resident_statusstr");
                                    work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    office_setupstr=Applicant_object.getString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.getString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.getString("vocation_str");
                                    }

                                    office_setup1=Applicant_object.getString("office_setup");

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
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");

                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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
                                employee_status=Applicant_object.getString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.getString("pan_no");
                                dateofbirth = Applicant_object.getString("member_dob");
                                fathername = Applicant_object.getString("father_name");
                                maritalstatus = Applicant_object.getString("marital_statusstr");

                                //Salaried mode

                                if (employee_status.equals("1")){
                                    securelaplay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    prop_prices = Property_object.getString("property_value");
                                    prop_pincodes = Property_object.getString("pincode");
                                    prop_title = Property_object.getString("prop_titlestr");
                                    prop_type = Property_object.getString("prop_typestr");


                                    lap_proppincode.setText(prop_pincodes);
                                    lapprop_price.setText(prop_prices);
                                    lapprop_type.setText(prop_type);
                                    lap_proptitle.setText(prop_title);

                                    //Employement Details
                                    month_income = Applicant_object.getString("monthly_income");
                                    salarymodes = Applicant_object.getString("salary_modestr");
                                    cmpny_type = Applicant_object.getString("company_typestr");
                                    cmpny_name = Applicant_object.getString("company_name");
                                    designation = Applicant_object.getString("designation");
                                    cmpny_pincode = Applicant_object.getString("ofc_pincode");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    total_exp = Applicant_object.getString("total_experience");
                                    income_proof_typestr = Applicant_object.getString("income_proof_typestr");
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
                                            String workarea = J.getString("area");
                                            companyarea_txt.setText(workarea);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    /*income_proof_typestr = Applicant_object.getString("income_proof_typestr");
                                    salaryproof_txt.setText(income_proof_typestr);*/

                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");

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

                                    residence_pincode = Applicant_object.getString("per_pincode");
                                    //residence_perarea=Applicant_object.getString("per_area");
                                    residence_type = Applicant_object.getString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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

                                    prop_prices = Property_object.getString("property_value");
                                    prop_pincodes = Property_object.getString("pincode");
                                    prop_title = Property_object.getString("prop_titlestr");
                                    prop_type = Property_object.getString("prop_typestr");


                                    lap_proppincode.setText(prop_pincodes);
                                    lapprop_price.setText(prop_prices);
                                    lapprop_type.setText(prop_type);
                                    lap_proptitle.setText(prop_title);

                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    vintage_docstr = Applicant_object.getString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.getString("employe_status");
                                    office_setup=Applicant_object.getString("office_setupstr");
                                    month_income = Applicant_object.getString("monthly_income");
                                    //  salarymodes = Applicant_object.getString("salary_modestr");
                                    vocation=Applicant_object.getString("vocation");
                                    typeemploy=Applicant_object.getString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.getString("office_res");
                                    office_pincode=Applicant_object.getString("ofc_pincode");
                                    residence_pincode=Applicant_object.getString("per_pincode");
                                    residence_perarea=Applicant_object.getString("per_area");
                                    residence_type=Applicant_object.getString("resident_statusstr");
                                    work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    office_setupstr=Applicant_object.getString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.getString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.getString("vocation_str");
                                    }

                                    office_setup1=Applicant_object.getString("office_setup");

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
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");

                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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
                                employee_status=Applicant_object.getString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.getString("pan_no");
                                dateofbirth = Applicant_object.getString("member_dob");
                                fathername = Applicant_object.getString("father_name");
                                maritalstatus = Applicant_object.getString("marital_statusstr");

                                //Salaried mode

                                if (employee_status.equals("1")){

                                    secureplot_constructionlay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    //prop_prices = Property_object.getString("property_value");
                                    prop_pincodes = Property_object.getString("pincode");
                                    prop_title = Property_object.getString("prop_titlestr");
                                    prop_type = Property_object.getString("prop_typestr");

                                    cost_estimate=Property_object.getString("cost_of_construction");
                                    cost_of_land =Property_object.getString("cost_of_land");

                                    secureplot_cons.setText(prop_title);
                                    secure_ploatoincode.setText(prop_pincodes);
                                    secureplot_prop_type.setText(prop_type);
                                    secureplo_value.setText(cost_of_land);
                                    secureplot_cost_estimate.setText(cost_estimate);

                                    //Employement Details
                                    month_income = Applicant_object.getString("monthly_income");
                                    salarymodes = Applicant_object.getString("salary_modestr");
                                    cmpny_type = Applicant_object.getString("company_typestr");
                                    cmpny_name = Applicant_object.getString("company_name");
                                    designation = Applicant_object.getString("designation");
                                    cmpny_pincode = Applicant_object.getString("ofc_pincode");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    total_exp = Applicant_object.getString("total_experience");

                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            String workarea = J.getString("area");
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


                                    income_proof_typestr = Applicant_object.getString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                   // salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);

                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");



                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_salrycredit.setText(salarymodes);
                                    // companyarea_txt.setText(area);

                                    //Residenence Pincode

                                    residence_pincode = Applicant_object.getString("per_pincode");
                                    //residence_perarea=Applicant_object.getString("per_area");
                                    residence_type = Applicant_object.getString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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


                                    //prop_prices = Property_object.getString("property_value");
                                    prop_pincodes = Property_object.getString("pincode");
                                    prop_title = Property_object.getString("prop_titlestr");
                                    prop_type = Property_object.getString("prop_typestr");

                                    cost_estimate=Property_object.getString("cost_of_construction");
                                    cost_of_land =Property_object.getString("cost_of_land");

                                    secureplot_cons.setText(prop_title);
                                    secure_ploatoincode.setText(prop_pincodes);
                                    secureplot_prop_type.setText(prop_type);
                                    secureplo_value.setText(cost_of_land);
                                    secureplot_cost_estimate.setText(cost_estimate);

                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    vintage_docstr = Applicant_object.getString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.getString("employe_status");
                                    office_setup=Applicant_object.getString("office_setupstr");
                                    month_income = Applicant_object.getString("monthly_income");
                                    //  salarymodes = Applicant_object.getString("salary_modestr");
                                    vocation=Applicant_object.getString("vocation");
                                    typeemploy=Applicant_object.getString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.getString("office_res");
                                    office_pincode=Applicant_object.getString("ofc_pincode");
                                    residence_pincode=Applicant_object.getString("per_pincode");
                                    residence_perarea=Applicant_object.getString("per_area");
                                    residence_type=Applicant_object.getString("resident_statusstr");
                                    work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    office_setupstr=Applicant_object.getString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.getString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.getString("vocation_str");
                                    }

                                    office_setup1=Applicant_object.getString("office_setup");

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
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");

                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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
                                employee_status=Applicant_object.getString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.getString("pan_no");
                                dateofbirth = Applicant_object.getString("member_dob");
                                fathername = Applicant_object.getString("father_name");
                                maritalstatus = Applicant_object.getString("marital_statusstr");

                                //Salaried mode

                                if (employee_status.equals("1")){

                                    secureplot_loanlay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);

                                  //  prop_prices = Property_object.getString("property_value");
                                    prop_pincodes = Property_object.getString("pincode");
                                    prop_title = Property_object.getString("prop_titlestr");
                                    prop_type = Property_object.getString("prop_typestr");

                                   // cost_estimate=Property_object.getString("cost_of_construction");
                                    cost_of_land =Property_object.getString("cost_of_land");

                                    prop_plot_title.setText(prop_title);
                                    prop_Pincode.setText(prop_pincodes);
                                    prop_plot_identified.setText(prop_type);
                                    prop_plot_cost_of_plot.setText(cost_of_land);



                                    //Employement Details
                                    month_income = Applicant_object.getString("monthly_income");
                                    salarymodes = Applicant_object.getString("salary_modestr");
                                    cmpny_type = Applicant_object.getString("company_typestr");
                                    cmpny_name = Applicant_object.getString("company_name");
                                    designation = Applicant_object.getString("designation");
                                    cmpny_pincode = Applicant_object.getString("ofc_pincode");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    total_exp = Applicant_object.getString("total_experience");

                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            String workarea = J.getString("area");
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


                                    income_proof_typestr = Applicant_object.getString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                   // salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);

                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");

                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_salrycredit.setText(salarymodes);
                                    // companyarea_txt.setText(area);

                                    //Residenence Pincode

                                    residence_pincode = Applicant_object.getString("per_pincode");
                                    //residence_perarea=Applicant_object.getString("per_area");
                                    residence_type = Applicant_object.getString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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


                                   // prop_prices = Property_object.getString("property_value");
                                    prop_pincodes = Property_object.getString("pincode");
                                    prop_title = Property_object.getString("prop_titlestr");
                                    prop_type = Property_object.getString("prop_typestr");

                                    // cost_estimate=Property_object.getString("cost_of_construction");
                                    cost_of_land =Property_object.getString("cost_of_land");

                                    prop_plot_title.setText(prop_title);
                                    prop_Pincode.setText(prop_pincodes);
                                    prop_plot_identified.setText(prop_type);
                                    prop_plot_cost_of_plot.setText(cost_of_land);

                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    vintage_docstr = Applicant_object.getString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.getString("employe_status");
                                    office_setup=Applicant_object.getString("office_setupstr");
                                    month_income = Applicant_object.getString("monthly_income");
                                    //  salarymodes = Applicant_object.getString("salary_modestr");
                                    vocation=Applicant_object.getString("vocation");
                                    typeemploy=Applicant_object.getString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.getString("office_res");
                                    office_pincode=Applicant_object.getString("ofc_pincode");
                                    residence_pincode=Applicant_object.getString("per_pincode");
                                    residence_perarea=Applicant_object.getString("per_area");
                                    residence_type=Applicant_object.getString("resident_statusstr");
                                    work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    office_setupstr=Applicant_object.getString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.getString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.getString("vocation_str");
                                    }
                                    office_setup1=Applicant_object.getString("office_setup");

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
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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
                                employee_status=Applicant_object.getString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.getString("pan_no");
                                dateofbirth = Applicant_object.getString("member_dob");
                                fathername = Applicant_object.getString("father_name");
                                maritalstatus = Applicant_object.getString("marital_statusstr");



                                if (employee_status.equals("1")){

                                    secure_improvementlay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);

                                    //Salaried mode
                                    prop_prices = Property_object.getString("property_value");
                                    prop_pincodes = Property_object.getString("pincode");
                                    prop_title = Property_object.getString("prop_titlestr");
                                    prop_type = Property_object.getString("prop_typestr");

                                    cost_estimate=Property_object.getString("cost_of_construction");
                                    //  cost_of_land =Property_object.getString("cost_of_land");

                                    imp_prop_title.setText(prop_title);
                                    imp_prop_pincode.setText(prop_pincodes);
                                    imp_prop_type.setText(prop_type);
                                    imp_prop_cost.setText(cost_estimate);
                                    imp_prop_price.setText(prop_prices);

                                    //Employement Details
                                    month_income = Applicant_object.getString("monthly_income");
                                    salarymodes = Applicant_object.getString("salary_modestr");
                                    cmpny_type = Applicant_object.getString("company_typestr");
                                    cmpny_name = Applicant_object.getString("company_name");
                                    designation = Applicant_object.getString("designation");
                                    cmpny_pincode = Applicant_object.getString("ofc_pincode");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    total_exp = Applicant_object.getString("total_experience");

                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            String workarea = J.getString("area");
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


                                    income_proof_typestr = Applicant_object.getString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                   // salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);

                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");



                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_salrycredit.setText(salarymodes);
                                    // companyarea_txt.setText(area);

                                    //Residenence Pincode

                                    residence_pincode = Applicant_object.getString("per_pincode");
                                    //residence_perarea=Applicant_object.getString("per_area");
                                    residence_type = Applicant_object.getString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    otherincome_details = Applicant_object.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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
                                    prop_prices = Property_object.getString("property_value");
                                    prop_pincodes = Property_object.getString("pincode");
                                    prop_title = Property_object.getString("prop_titlestr");
                                    prop_type = Property_object.getString("prop_typestr");

                                    cost_estimate=Property_object.getString("cost_of_construction");
                                    //  cost_of_land =Property_object.getString("cost_of_land");

                                    imp_prop_title.setText(prop_title);
                                    imp_prop_pincode.setText(prop_pincodes);
                                    imp_prop_type.setText(prop_type);
                                    imp_prop_cost.setText(cost_estimate);
                                    imp_prop_price.setText(prop_prices);

                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    vintage_docstr = Applicant_object.getString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.getString("employe_status");
                                    office_setup=Applicant_object.getString("office_setupstr");
                                    month_income = Applicant_object.getString("monthly_income");
                                    //  salarymodes = Applicant_object.getString("salary_modestr");
                                    vocation=Applicant_object.getString("vocation");
                                    typeemploy=Applicant_object.getString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.getString("office_res");
                                    office_pincode=Applicant_object.getString("ofc_pincode");
                                    residence_pincode=Applicant_object.getString("per_pincode");
                                    residence_perarea=Applicant_object.getString("per_area");
                                    residence_type=Applicant_object.getString("resident_statusstr");
                                    work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    office_setupstr=Applicant_object.getString("resident_statusstr");
                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);
                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.getString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.getString("vocation_str");
                                    }
                                    office_setup1=Applicant_object.getString("office_setup");

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
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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
                                employee_status=Applicant_object.getString("employe_status");
                                Log.i("TAG", "employee status: "+employee_status);
                                //Showing Pancard Details
                                pancardnumber = Applicant_object.getString("pan_no");
                                dateofbirth = Applicant_object.getString("member_dob");
                                fathername = Applicant_object.getString("father_name");
                                maritalstatus = Applicant_object.getString("marital_statusstr");

                                //Salaried mode

                                if (employee_status.equals("1")){

                                    secureplot_constructionlay.setVisibility(View.VISIBLE);
                                    unsecure_personalloan.setVisibility(View.VISIBLE);
                                    unsecure_residencelay.setVisibility(View.VISIBLE);


                                    //prop_prices = Property_object.getString("property_value");
                                    prop_pincodes = Property_object.getString("pincode");
                                    prop_title = Property_object.getString("prop_titlestr");
                                    prop_type = Property_object.getString("prop_typestr");

                                    cost_estimate=Property_object.getString("cost_of_construction");
                                    //cost_of_land =Property_object.getString("cost_of_land");

                                    secureplot_cons.setText(prop_title);
                                    secure_ploatoincode.setText(prop_pincodes);
                                    secureplot_prop_type.setText(prop_type);
                                    secureplo_value.setText(cost_estimate);

                                    cost_estimation.setVisibility(View.GONE);

                                    //Pan details show
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");

                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);

                                    //Employement Details
                                    month_income = Applicant_object.getString("monthly_income");
                                    salarymodes = Applicant_object.getString("salary_modestr");
                                    cmpny_type = Applicant_object.getString("company_typestr");
                                    cmpny_name = Applicant_object.getString("company_name");
                                    designation = Applicant_object.getString("designation");
                                    cmpny_pincode = Applicant_object.getString("ofc_pincode");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    total_exp = Applicant_object.getString("total_experience");

                                    //working Area array
                                    JSONArray area_array = Applicant_object.getJSONArray("work_areaarr");
                                    if (area_array.length() > 0) {
                                        try {
                                            JSONObject J = area_array.getJSONObject(0);
                                            String workarea = J.getString("area");
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


                                    income_proof_typestr = Applicant_object.getString("income_proof_typestr");
                                    String formattedString = income_proof_typestr.toString()
                                            .replace("[", "")  //remove the right bracket
                                            .replace("]", "")
                                            .replaceAll("\"", "")
                                            .trim();
                                  //  salaryproof_txt.setText(income_proof_typestr);
                                    salaryproof_txt.setText(formattedString);

                                    //Residenence Pincode

                                    residence_pincode = Applicant_object.getString("per_pincode");
                                    //residence_perarea=Applicant_object.getString("per_area");
                                    residence_type = Applicant_object.getString("resident_statusstr");
                                    //residence area array value

                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    otherincome_details = jsonobj.getJSONObject("otherincome_details");
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome.setText(income_typestr);
                                        other_income_amount.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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

                                    //prop_prices = Property_object.getString("property_value");
                                    prop_pincodes = Property_object.getString("pincode");
                                    prop_title = Property_object.getString("prop_titlestr");
                                    prop_type = Property_object.getString("prop_typestr");

                                    // cost_estimate=Property_object.getString("cost_of_construction");
                                    cost_of_land =Property_object.getString("cost_of_land");

                                    secureplot_cons.setText(prop_title);
                                    secure_ploatoincode.setText(prop_pincodes);
                                    secureplot_prop_type.setText(prop_type);
                                    secureplo_value.setText(cost_of_land);

                                    cost_estimation.setVisibility(View.GONE);

                                    Applicant_object = jsonobj.getJSONObject("applicant_data");
                                    //Showing Pancard Details
                                    pancardnumber = Applicant_object.getString("pan_no");
                                    dateofbirth = Applicant_object.getString("member_dob");
                                    fathername = Applicant_object.getString("father_name");
                                    maritalstatus = Applicant_object.getString("marital_statusstr");
                                    curr_exp = Applicant_object.getString("working_experience");
                                    vintage_docstr = Applicant_object.getString("vintage_docstr");
                                    //JSONArray jsonArray=Applicant_object.getJSONArray("income_proof_typestr");
                                    String income= String.valueOf(Applicant_object.getJSONArray("income_proof_typestr"));
                                    //String text = income.toString().replace("[", "").replace("]", "");
                                    JSONArray res_array = Applicant_object.getJSONArray("per_areaarr");
                                    if (res_array.length() > 0) {
                                        try {
                                            JSONObject J = res_array.getJSONObject(0);
                                            res_area = J.getString("area");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    employee_status=Applicant_object.getString("employe_status");
                                    office_setup=Applicant_object.getString("office_setupstr");
                                    month_income = Applicant_object.getString("monthly_income");
                                    //  salarymodes = Applicant_object.getString("salary_modestr");
                                    vocation=Applicant_object.getString("vocation");
                                    typeemploy=Applicant_object.getString("bus_employment_typestr");
                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    Log.i("TAG", "Business_employetypestr: "+typeemploy);
                                    office_residence=Applicant_object.getString("office_res");
                                    office_pincode=Applicant_object.getString("ofc_pincode");
                                    residence_pincode=Applicant_object.getString("per_pincode");
                                    residence_perarea=Applicant_object.getString("per_area");
                                    residence_type=Applicant_object.getString("resident_statusstr");
                                    work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    office_setupstr=Applicant_object.getString("resident_statusstr");

                                    pancardtxt.setText(pancardnumber);
                                    dateofbithtxt.setText(dateofbirth);
                                    fathernametxt.setText(fathername);
                                    maritaltxt.setText(maritalstatus);

                                    bus_typeemployemnt.setText("Business Self Employed");
                                    bus_salrycredit.setText(salarymodes);
                                    bus_typeself.setText(typeemploy);
                                    officesetup.setText(office_setup);

                                    bus_employment_type=Applicant_object.getString("bus_employment_type");
                                    if(bus_employment_type.equals("1"))
                                    {
                                        work_vocationstr=Applicant_object.getString("bus_vocationstr");
                                    }else if(bus_employment_type.equals("2"))
                                    {
                                        work_vocationstr=Applicant_object.getString("work_vocationstr");
                                    }else {

                                        work_vocationstr=Applicant_object.getString("vocation_str");
                                    }
                                    office_setup1=Applicant_object.getString("office_setup");

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
                                    String income_type = otherincome_details.getString("income_type");
                                    String income_typestr = otherincome_details.getString("income_typestr");
                                    if( income_type.equals("4"))
                                    {
                                        otherincome_self.setText(income_typestr);
                                        other_income_amount_self.setVisibility(View.GONE);

                                    }else
                                    {
                                        String otherincome_details1 = otherincome_details.getString("income_amount");
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