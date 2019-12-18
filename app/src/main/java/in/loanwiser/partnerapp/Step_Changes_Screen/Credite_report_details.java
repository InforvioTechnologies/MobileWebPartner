package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

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
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Credite_report_details extends SimpleActivity {

    AppCompatButton cr_yes,cr_no;
    LinearLayout credite_report_yes_No,having_existing_loan;

    AppCompatTextView dcomplete,Credit_report_des,need_cr_report,existing_loan,
                     do_you_need_credit_rep_txt,existing_Loan_txt,emi_amt_txt,emi_amt_txt1,
            bank_name_txt,bank_name_txt1,loan_type_txt,loan_type_txt1;

    AppCompatEditText EMI_Amount,bank_name_Edite_txt,loan_type_Edite_txt,
                      remaning_tenor_Edite_txt;

    AppCompatButton next_step4;
    Spinner have_existing_Loan;
    JSONArray having_Loan_array;

    Typeface font;
    private Context context = this;
    InputMethodManager imm;

    ArrayAdapter<String> Existing_loan_adapter;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;

    String[] Do_you_have_Existing_Loan;
    String  Existing_Loan_ID,Existing_Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_credite_report_details);
        initTools(R.string.credit_report1);

        progressDialog = new SpotsDialog(context, R.style.Custom);






        makeJsonObjReq1();
        SCREEN_UI();
        Font();
        Click();

    }

    private void SCREEN_UI()
    {

       /* AppCompatTextView dcomplete,des,need_cr_report,existing_loan,
                do_you_need_credit_rep_txt,existing_Loan_txt,emi_amt_txt,emi_amt_txt1,
                bank_name_txt,bank_name_txt1,loan_type_txt,loan_type_txt1;

        AppCompatEditText EMI_Amount,bank_name_Edite_txt,loan_type_Edite_txt,
                remaning_tenor_Edite_txt;*/

        cr_yes = (AppCompatButton) findViewById(R.id.cr_yes);
        cr_no = (AppCompatButton) findViewById(R.id.cr_no);

        credite_report_yes_No = (LinearLayout) findViewById(R.id.credite_report_yes_No);
        having_existing_loan = (LinearLayout) findViewById(R.id.having_existing_loan);

        dcomplete = (AppCompatTextView) findViewById(R.id.dcomplete);
        Credit_report_des = (AppCompatTextView) findViewById(R.id.Credit_report_des);
        do_you_need_credit_rep_txt = (AppCompatTextView) findViewById(R.id.do_you_need_credit_rep_txt);
        existing_Loan_txt = (AppCompatTextView) findViewById(R.id.existing_Loan_txt);
        emi_amt_txt = (AppCompatTextView) findViewById(R.id.emi_amt_txt);
        emi_amt_txt1 = (AppCompatTextView) findViewById(R.id.emi_amt_txt1);
        bank_name_txt = (AppCompatTextView) findViewById(R.id.bank_name_txt);
        bank_name_txt1 = (AppCompatTextView) findViewById(R.id.bank_name_txt1);
        loan_type_txt = (AppCompatTextView) findViewById(R.id.loan_type_txt);
        loan_type_txt1 = (AppCompatTextView) findViewById(R.id.loan_type_txt1);

        EMI_Amount = (AppCompatEditText) findViewById(R.id.EMI_Amount);
        bank_name_Edite_txt = (AppCompatEditText) findViewById(R.id.bank_name_Edite_txt);
        loan_type_Edite_txt = (AppCompatEditText) findViewById(R.id.loan_type_Edite_txt);
        remaning_tenor_Edite_txt = (AppCompatEditText) findViewById(R.id.remaning_tenor_Edite_txt);
        have_existing_Loan = (Spinner) findViewById(R.id.have_existing_Loan);

        next_step4 = (AppCompatButton) findViewById(R.id.next_step4);


    }

    private void Font(){

        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");

        dcomplete.setTypeface(font);
        Credit_report_des.setTypeface(font);
        do_you_need_credit_rep_txt.setTypeface(font);
        existing_Loan_txt.setTypeface(font);
        emi_amt_txt.setTypeface(font);
        emi_amt_txt1.setTypeface(font);
        bank_name_txt.setTypeface(font);
        bank_name_txt1.setTypeface(font);
        loan_type_txt.setTypeface(font);
        loan_type_txt1.setTypeface(font);
        EMI_Amount.setTypeface(font);
        bank_name_Edite_txt.setTypeface(font);
        loan_type_Edite_txt.setTypeface(font);
        remaning_tenor_Edite_txt.setTypeface(font);
    }

      private void Click()
     {

         next_step4 = (AppCompatButton) findViewById(R.id.next_step4);

         cr_yes.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 credite_report_yes_No.setVisibility(View.GONE);
                 Intent intent = new Intent(Credite_report_details.this, Creadite_Report_Activity.class);
                 startActivity(intent);
                 finish();
             }
         });

         cr_no.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 credite_report_yes_No.setVisibility(View.VISIBLE);
             }
         });

         next_step4.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if(Existing_Loan_ID.equals("0"))
                 {
                     Toast.makeText(context, "plese Select Existing Loan",Toast.LENGTH_SHORT).show();
                 }else if(Existing_Loan_ID.equals("1"))
                 {
                     if (!EMI_Amount()) {
                         return;
                     }

                     if (!Bank_Name_edite_Txt()) {
                         return;
                     }

                     if (!Loan_Type_Fun()) {
                         return;
                     }

                     if (!Remaning_Tenor()) {
                         return;
                     }

                     Payment_Option();
                 }else if(Existing_Loan_ID.equals("2"))
                 {
                     Payment_Option();
                 }
             }
         });


      }

    private boolean EMI_Amount(){

        if (EMI_Amount.getText().toString().isEmpty()) {
            EMI_Amount.setError(getText(R.string.error_emi));
            EMI_Amount.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Bank_Name_edite_Txt(){

        if (bank_name_Edite_txt.getText().toString().isEmpty()) {
            bank_name_Edite_txt.setError(getText(R.string.error_bank_name));
            bank_name_Edite_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Loan_Type_Fun(){

        if (loan_type_Edite_txt.getText().toString().isEmpty()) {
            loan_type_Edite_txt.setError(getText(R.string.error_Loan_Type));
            loan_type_Edite_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Remaning_Tenor(){

        if (remaning_tenor_Edite_txt.getText().toString().isEmpty()) {
            remaning_tenor_Edite_txt.setError(getText(R.string.error_Loan_Type));
            remaning_tenor_Edite_txt.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

        private void Payment_Option()
        {
            Intent intent = new Intent(Credite_report_details.this, Payment_Details_Activity.class);
            startActivity(intent);
            finish();
        }

    private void makeJsonObjReq1() {
        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_DROPDOWN_LIST, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("respose Dreopdown", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        try {

                            having_Loan_array =object.getJSONArray("existing_loan");

                            Current_res_proof(having_Loan_array);

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

    private void Current_res_proof(final JSONArray Current_res_proof_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Do_you_have_Existing_Loan = new String[Current_res_proof_ar.length()];
        for (int i=0;i<Current_res_proof_ar.length();i++){
            JSONObject J =  Current_res_proof_ar.getJSONObject(i);
            Do_you_have_Existing_Loan[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Do_you_have_Existing_Loan));
            Existing_loan_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
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

            Existing_loan_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            have_existing_Loan.setAdapter(Existing_loan_adapter);
            have_existing_Loan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        Existing_Loan_ID = Current_res_proof_ar.getJSONObject(position).getString("id");
                        Existing_Value = Current_res_proof_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Existing_Loan_ID", Existing_Loan_ID);
                        Log.d("Existing_Value", Existing_Value);

                        if(Existing_Loan_ID.equals("1"))
                        {
                            having_existing_loan.setVisibility(View.VISIBLE);
                        }else
                        {
                            having_existing_loan.setVisibility(View.GONE);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            have_existing_Loan.setOnTouchListener(new View.OnTouchListener() {
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

        Objs.ac.StartActivity(mCon, Eligibility_Check_PL.class);
        finish();
        super.onBackPressed();
    }
}
