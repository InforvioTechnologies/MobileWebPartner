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
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Eligibility_BL extends SimpleActivity {

    private LinearLayout self_business;
    private String Lontype ;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;

    Typeface font;
    private Context context = this;
    InputMethodManager imm;
    JSONArray Provide_Guarantor,Current_address_proof,GST_reflected,other_income,
            Business_registration;
    Spinner spinner_res_proof,spinner_guaranter,spinner_existing_loan,spinn_other_income,
            spinn_gst_other,business_registration_spinner;

    String[] Current_address_Proof_SA,Provide_guarente_SA,Other_income_SA,
            Gst_refelect_SA,Business_registration_SA;
    ArrayAdapter<String> CureentAddress_proof_adapter,Provide_gurenter_adapter,
                         Other_income_adapter,Gst_reflect_adapter,
            Business_registration_adapter;
    String Spinner_res_proof_Id,Spinner_res_proof_Value,Guarenter_Id,Guarenter_Value,
            Gstreflect_Id,Gstreflect_Value,Business_registration_Id,Business_registration_Value,
            Other_income_Id,Other_income_Value;

    AppCompatTextView business_registration_txt,business_registration_txt1,
            avg_bank_balence_txt,avg_bank_balence_txt1,res_proof,res_proof1,guaranter_txt,guaranter_txt1,
            business_r_name_txt,business_r_name_txt1,business_refernce_txt,business_refernce_txt1,
            other_income_txt,other_income_txt1,other_incom_amt_txt,other_incom_amt_txt1,
            is_gst_reflect_txt,is_gst_reflect_txt1,purchased_by_bank1,purchased_by_bank2,purchased_by_gst,purchased_by_gst1,
            bank_credit1_txt,bank_credit1_txt1, gst_sales1_txt,gst_sales1_txt1;

    AppCompatEditText Avg_monthly_income,Business_reference_mobile,Business_refernce_name,
            other_income_edite_txt,purchased_by_bank_edit_txt,purchased_by_GStbill_edit_txt
           ,sales_by_GStbill_edit_txt,bank_cridit_by_edtxt;
    AppCompatButton lead_Elegibility_Bank;

    LinearLayout other_income_ifany;

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



        makeJsonObjReq1();
        UISCREENS();
        Font();
        Click();


    }

    private void UISCREENS()
    {

        spinner_res_proof = (Spinner) findViewById(R.id.spinner_res_proof);
        spinner_guaranter = (Spinner) findViewById(R.id.spinner_guaranter);
        spinn_gst_other = (Spinner) findViewById(R.id.spinn_gst_other);
        spinn_other_income = (Spinner) findViewById(R.id.spinn_other_income);
        business_registration_spinner = (Spinner) findViewById(R.id.business_registration_spinner);
        business_registration_txt = (AppCompatTextView) findViewById(R.id.business_registration_txt);
        business_registration_txt1 = (AppCompatTextView) findViewById(R.id.business_registration_txt1);
        avg_bank_balence_txt = (AppCompatTextView) findViewById(R.id.avg_bank_balence_txt);
        avg_bank_balence_txt1 = (AppCompatTextView) findViewById(R.id.avg_bank_balence_txt1);
        res_proof = (AppCompatTextView) findViewById(R.id.res_proof);
        res_proof1 = (AppCompatTextView) findViewById(R.id.res_proof1);
        guaranter_txt = (AppCompatTextView) findViewById(R.id.guaranter_txt);
        guaranter_txt1 = (AppCompatTextView) findViewById(R.id.guaranter_txt1);
        business_r_name_txt = (AppCompatTextView) findViewById(R.id.business_r_name_txt);
        business_r_name_txt1 = (AppCompatTextView) findViewById(R.id.business_r_name_txt1);
        business_refernce_txt = (AppCompatTextView) findViewById(R.id.business_refernce_txt);
        business_refernce_txt1 = (AppCompatTextView) findViewById(R.id.business_refernce_txt1);
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

        Avg_monthly_income = (AppCompatEditText) findViewById(R.id.Avg_monthly_income);
        Business_reference_mobile = (AppCompatEditText) findViewById(R.id.Business_reference_mobile);
        Business_refernce_name = (AppCompatEditText) findViewById(R.id.Business_refernce_name);
        other_income_edite_txt = (AppCompatEditText) findViewById(R.id.other_income_edite_txt);

        purchased_by_bank_edit_txt = (AppCompatEditText) findViewById(R.id.purchased_by_bank_edit_txt);
        purchased_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.purchased_by_GStbill_edit_txt);
        sales_by_GStbill_edit_txt = (AppCompatEditText) findViewById(R.id.sales_by_GStbill_edit_txt);
        bank_cridit_by_edtxt = (AppCompatEditText) findViewById(R.id.bank_cridit_by_edtxt);

        lead_Elegibility_Bank = (AppCompatButton) findViewById(R.id.lead_Elegibility_Bank);

        other_income_ifany = (LinearLayout) findViewById(R.id.other_income_ifany);
        if(Lontype.equals("3"))
        {
            self_business.setVisibility(View.VISIBLE);
            business_registration_txt.setText("1");
            purchased_by_bank1.setText("2");
            purchased_by_gst.setText("3");
            gst_sales1_txt.setText("4");
            bank_credit1_txt.setText("5");

            avg_bank_balence_txt.setText("5");
            res_proof.setText("6");
            guaranter_txt.setText("7");
            business_r_name_txt.setText("8");
            business_refernce_txt.setText("9");
            other_income_txt.setText("10");
            other_incom_amt_txt.setText("11");
            is_gst_reflect_txt.setText("12");

        }else
        {
            self_business.setVisibility(View.GONE);
            business_registration_txt.setText("1");
            avg_bank_balence_txt.setText("2");
            res_proof.setText("3");
            guaranter_txt.setText("4");
            business_r_name_txt.setText("5");
            business_refernce_txt.setText("6");
            other_income_txt.setText("7");
            other_incom_amt_txt.setText("8");
            is_gst_reflect_txt.setText("9");
        }

    }

    private void Font() {

        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");

        is_gst_reflect_txt.setTypeface(font);
        is_gst_reflect_txt1.setTypeface(font);
        purchased_by_bank1.setTypeface(font);
        purchased_by_bank2.setTypeface(font);
        purchased_by_gst.setTypeface(font);
        purchased_by_gst1.setTypeface(font);
        bank_credit1_txt.setTypeface(font);
        bank_credit1_txt1.setTypeface(font);
        gst_sales1_txt.setTypeface(font);
        gst_sales1_txt1.setTypeface(font);

        business_registration_txt.setTypeface(font);
        business_registration_txt1.setTypeface(font);
        avg_bank_balence_txt.setTypeface(font);
        avg_bank_balence_txt1.setTypeface(font);
        res_proof.setTypeface(font);
        res_proof1.setTypeface(font);
        guaranter_txt.setTypeface(font);
        guaranter_txt1.setTypeface(font);
        business_r_name_txt.setTypeface(font);
        business_r_name_txt1.setTypeface(font);
        business_refernce_txt.setTypeface(font);
        business_refernce_txt1.setTypeface(font);
        other_income_txt.setTypeface(font);
        other_income_txt1.setTypeface(font);
        other_incom_amt_txt.setTypeface(font);
        other_incom_amt_txt1.setTypeface(font);
        is_gst_reflect_txt.setTypeface(font);
        is_gst_reflect_txt1.setTypeface(font);
        Avg_monthly_income.setTypeface(font);
        Business_reference_mobile.setTypeface(font);
        Business_refernce_name.setTypeface(font);
        other_income_edite_txt.setTypeface(font);
    }
    private void Click ()
    {


        lead_Elegibility_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Eligibility_BL.this, Credite_report_details.class);
                startActivity(intent);
                finish();
            }
        });

        /*lead_Elegibility_Bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Business_registration_Id.equals("0"))
                {
                    Toast.makeText(context,"Please Select Business Registration",Toast.LENGTH_SHORT).show();
                }else
                {
                    if(Lontype.equals("3"))
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

                        validation();

                    }else
                    {
                        validation();
                    }

                }
            }
        });*/

    }

    private void validation()
    {

        if (!Average_monthly_income()) {
            return;
        }
        if(Spinner_res_proof_Id.equals("0"))
        {
            Toast.makeText(context,"Please Select Current Residence Addres Proof",Toast.LENGTH_SHORT).show();
        }else
        {
          /*  if(Guarenter_Id.equals("0"))
            {
                Toast.makeText(context,"Please Select can you provide Guarenter",Toast.LENGTH_SHORT).show();

            }else
            {*/

             /*   if (!residence_Business_reference_name()) {
                    return;
                }

                if (!residence_Business_reference_phone()) {
                    return;
                }*/

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
                        Toast.makeText(context,"Please Select Gst Reflected",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                    }

                }

           /* }*/
        }

    }

    private boolean Purchased_by_Bank(){

        if (purchased_by_bank_edit_txt.getText().toString().isEmpty()) {
            purchased_by_bank_edit_txt.setError(getText(R.string.error_purchased_by_Bank));
            purchased_by_bank_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Purchased_by_Gst_bill(){
        if (purchased_by_GStbill_edit_txt.getText().toString().isEmpty()) {
            purchased_by_GStbill_edit_txt.setError(getText(R.string.error_purchased_by_gst));
            purchased_by_GStbill_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }


    private boolean sales_by_Gst_bill(){
        if (sales_by_GStbill_edit_txt.getText().toString().isEmpty()) {
            sales_by_GStbill_edit_txt.setError(getText(R.string.error_sales_by_gst_crdt));
            sales_by_GStbill_edit_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }


    private boolean Sales_bank_cridit_(){
        if (bank_cridit_by_edtxt.getText().toString().isEmpty()) {
            bank_cridit_by_edtxt.setError(getText(R.string.error_sales_by_bank_crdt));
            bank_cridit_by_edtxt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);

        }

        return true;
    }

    private boolean Average_monthly_income(){
        if (Avg_monthly_income.getText().toString().isEmpty()) {
            Avg_monthly_income.setError(getText(R.string.error_avg));
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
            other_income_edite_txt.setError(getText(R.string.error_other_income_amt));
            other_income_edite_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
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

                            Provide_Guarantor =object.getJSONArray("Provide_Guarantor");
                            Current_address_proof =object.getJSONArray("Current_address");
                            GST_reflected =object.getJSONArray("GST_reflected");
                            other_income =object.getJSONArray("other_income");
                            Business_registration =object.getJSONArray("Business_registration");
                            // Business_Proof =object.getJSONArray("Business_Proof");
                            Log.e("Type_of_employement",String.valueOf(Provide_Guarantor));
                            Current_res_proof(Current_address_proof);
                            Provie_Guarenter(Provide_Guarantor);
                            Other_income(other_income);
                            GST_reflected(GST_reflected);
                            Business_registration_fun(Business_registration);


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
        }

    }

    private void Provie_Guarenter(final JSONArray Provide_Guarantor) throws JSONException {
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
            spinner_guaranter.setAdapter(Provide_gurenter_adapter);
            spinner_guaranter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        Guarenter_Id = Provide_Guarantor.getJSONObject(position).getString("id");
                        Guarenter_Value = Provide_Guarantor.getJSONObject(position).getString("value");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_guaranter.setOnTouchListener(new View.OnTouchListener() {
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
                        Log.d("Salary_id", Spinner_res_proof_Id);
                        Log.d("Salary_Value", Spinner_res_proof_Value);


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
        }

    }


    private void lead_Eligibility() {



        String purchased_by_bank_edit_txt1 = purchased_by_bank_edit_txt.getText().toString();
        String purchased_by_GStbill_edit_txt1 = purchased_by_GStbill_edit_txt.getText().toString();
        String sales_by_GStbill_edit_txt1 = sales_by_GStbill_edit_txt.getText().toString();
        String bank_cridit_by_edtxt1 = bank_cridit_by_edtxt.getText().toString();

        String Avg_monthly_income1 = Avg_monthly_income.getText().toString();
        String Business_refernce_name1 = Business_refernce_name.getText().toString();
        String Business_reference_mobile1 = Business_reference_mobile.getText().toString();
        String other_income_edite_txt1 = other_income_edite_txt.getText().toString();

        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();


            J.put("Business_registration_Id",Business_registration_Id);

            J.put("purchased_by_bank_edit_txt1",purchased_by_bank_edit_txt1);
            J.put("purchased_by_GStbill_edit_txt1",purchased_by_GStbill_edit_txt1);
            J.put("sales_by_GStbill_edit_txt1",sales_by_GStbill_edit_txt1);
            J.put("bank_cridit_by_edtxt1",bank_cridit_by_edtxt1);
            J.put("Avg_monthly_income1",Avg_monthly_income1);
            J.put("Business_refernce_name1",Business_refernce_name1);
            J.put("Business_reference_mobile1",Business_reference_mobile1);
            J.put("other_income_edite_txt1",other_income_edite_txt1);

            J.put("Spinner_res_proof_Id",Spinner_res_proof_Id);
            J.put("Guarenter_Id",Guarenter_Id);
            J.put("Other_income_Id",Other_income_Id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Add Home Laoan", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.ADD_LEAD_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        Log.e("Add_Home_loan Partner", String.valueOf(response));


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

        Objs.ac.StartActivity(mCon, Viability_Check_BL.class);
        finish();
        super.onBackPressed();

    }
}
