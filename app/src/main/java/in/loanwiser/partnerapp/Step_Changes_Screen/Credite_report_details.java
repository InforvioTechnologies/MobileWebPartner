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
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Credite_report_details extends SimpleActivity {

    AppCompatButton cr_yes,cr_no;
    LinearLayout credite_report_yes_No,having_existing_loan,co_having_existing_loan;

    AppCompatTextView dcomplete,Credit_report_des,need_cr_report,existing_loan,
                     do_you_need_credit_rep_txt,existing_Loan_txt,emi_amt_txt,emi_amt_txt1,
            bank_name_txt,bank_name_txt1,loan_type_txt,loan_type_txt1;

    AppCompatEditText EMI_Amount,bank_name_Edite_txt,loan_type_Edite_txt,
                      remaning_tenor_Edite_txt,co_EMI_Amount,co_bank_name_Edite_txt,co_loan_type_Edite_txt,co_remaning_tenor_Edite_txt;

    AppCompatButton next_step4;
    Spinner have_existing_Loan,co_have_existing_Loan;
    JSONArray having_Loan_array;

    Typeface font;
    private Context context = this;
    InputMethodManager imm;

    ArrayAdapter<String> Existing_loan_adapter;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;

    String[] Do_you_have_Existing_Loan;
    String  Existing_Loan_ID,Existing_Value,S_EMI_Amount,S_bank_name_Edite_txt,S_loan_type_Edite_txt,
            S_remaning_tenor_Edite_txt,IS_CO_Applicant_Id,co_Existing_Loan_ID,co_Existing_Value,
            S_co_EMI_Amount,S_co_bank_name_Edite_txt,S_co_loan_type_Edite_txt,S_co_remaning_tenor_Edite_txt;
    int app_count;
    String Co_Applicant;
    LinearLayout co_applicant_have;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_credite_report_details);
        initTools(R.string.credit_report1);

        progressDialog = new SpotsDialog(context, R.style.Custom);
       // Co_Applicant = Pref.getCoAPPAVAILABLE(getApplicationContext());


        makeJsonObjReq1();
        SCREEN_UI();
        Font();
        Click();
        IS_CO_Applicant_Id = Pref.getCoAPPAVAILABLE(getApplicationContext());
        if(IS_CO_Applicant_Id.equals("1"))
        {
            co_applicant_have.setVisibility(View.VISIBLE);
        }else
        {
            co_applicant_have.setVisibility(View.GONE);

        }

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
        co_having_existing_loan = (LinearLayout) findViewById(R.id.co_having_existing_loan);

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



        co_EMI_Amount = (AppCompatEditText) findViewById(R.id.co_EMI_Amount);
        co_bank_name_Edite_txt = (AppCompatEditText) findViewById(R.id.co_bank_name_Edite_txt);
        co_loan_type_Edite_txt = (AppCompatEditText) findViewById(R.id.co_loan_type_Edite_txt);
        co_remaning_tenor_Edite_txt = (AppCompatEditText) findViewById(R.id.co_remaning_tenor_Edite_txt);

        have_existing_Loan = (Spinner) findViewById(R.id.have_existing_Loan);
        co_have_existing_Loan = (Spinner) findViewById(R.id.co_have_existing_Loan);

        next_step4 = (AppCompatButton) findViewById(R.id.next_step4);
        co_applicant_have = (LinearLayout) findViewById(R.id.co_applicant_have);


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

                     validate_applicant();


                 }else if(Existing_Loan_ID.equals("2"))
                 {
                     if(IS_CO_Applicant_Id.equals("1"))
                     {
                         if(co_Existing_Loan_ID.equals("0"))
                         {
                             Toast.makeText(context, "plese Select Existing Loan",Toast.LENGTH_SHORT).show();
                         }else if(co_Existing_Loan_ID.equals("1"))
                         {

                             validate_co_applicant();


                         }else if(co_Existing_Loan_ID.equals("2"))
                         {
                             no_credite_report();
                         }

                     }else
                     {
                         no_credite_report();

                     }

                 }
             }
         });
      }

     private void validate_applicant()
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

          if(IS_CO_Applicant_Id.equals("1"))
          {
              if(co_Existing_Loan_ID.equals("0"))
              {
                  Toast.makeText(context, "plese Select Existing Loan",Toast.LENGTH_SHORT).show();
              }else if(co_Existing_Loan_ID.equals("1"))
              {

                  validate_co_applicant();


              }else if(co_Existing_Loan_ID.equals("2"))
              {
                  no_credite_report();
              }
          }else
          {
              no_credite_report();

          }

      }

      private  void validate_co_applicant()
      {
          if (!co_EMI_Amount()) {
              return;
          }

          if (!co_Bank_Name_edite_Txt()) {
              return;
          }

          if (!co_Loan_Type_Fun()) {
              return;
          }

          if (!co_Remaning_Tenor()) {
              return;
          }
          no_credite_report();

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


    private boolean co_EMI_Amount(){

        if (co_EMI_Amount.getText().toString().isEmpty()) {
            co_EMI_Amount.setError(getText(R.string.error_emi));
            co_EMI_Amount.requestFocus();
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

    private boolean co_Bank_Name_edite_Txt(){

        if (co_bank_name_Edite_txt.getText().toString().isEmpty()) {
            co_bank_name_Edite_txt.setError(getText(R.string.error_bank_name));
            co_bank_name_Edite_txt.requestFocus();
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

    private boolean co_Loan_Type_Fun(){

        if (co_loan_type_Edite_txt.getText().toString().isEmpty()) {
            co_loan_type_Edite_txt.setError(getText(R.string.error_Loan_Type));
            co_loan_type_Edite_txt.requestFocus();
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

    private boolean co_Remaning_Tenor(){

        if (co_remaning_tenor_Edite_txt.getText().toString().isEmpty()) {
            co_remaning_tenor_Edite_txt.setError(getText(R.string.error_Loan_Type));
            co_remaning_tenor_Edite_txt.requestFocus();
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

            co_have_existing_Loan.setAdapter(Existing_loan_adapter);
            co_have_existing_Loan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");

                        co_Existing_Loan_ID = Current_res_proof_ar.getJSONObject(position).getString("id");
                        co_Existing_Value = Current_res_proof_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Existing_Loan_ID", Existing_Loan_ID);
                        Log.d("Existing_Value", Existing_Value);

                        if(co_Existing_Loan_ID.equals("1"))
                        {
                            co_having_existing_loan.setVisibility(View.VISIBLE);
                        }else
                        {
                            co_having_existing_loan.setVisibility(View.GONE);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            co_have_existing_Loan.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });

        }

    }

    private void no_credite_report()
    {



        if(IS_CO_Applicant_Id.equals("1"))
        {
            app_count = 2;
        }else
        {
            app_count = 1;
        }

        S_EMI_Amount = EMI_Amount.getText().toString();
        S_bank_name_Edite_txt = bank_name_Edite_txt.getText().toString();
        S_loan_type_Edite_txt = loan_type_Edite_txt.getText().toString();
        S_remaning_tenor_Edite_txt = remaning_tenor_Edite_txt.getText().toString();



        S_co_EMI_Amount = co_EMI_Amount.getText().toString();
        S_co_bank_name_Edite_txt = co_bank_name_Edite_txt.getText().toString();
        S_co_loan_type_Edite_txt = co_loan_type_Edite_txt.getText().toString();
        S_co_remaning_tenor_Edite_txt = co_remaning_tenor_Edite_txt.getText().toString();

        JSONArray EMI_Amount1 = new JSONArray();
        JSONArray bank_name_Edite_txt1 = new JSONArray();
        JSONArray loan_type_Edite_txt = new JSONArray();
        JSONArray remaning_tenor_Edite_txt = new JSONArray();

        JSONArray EMI_Amount2 = new JSONArray();
        JSONArray bank_name_Edite_txt2 = new JSONArray();
        JSONArray loan_type_Edite_txt2 = new JSONArray();
        JSONArray remaning_tenor_Edite_txt2 = new JSONArray();


        EMI_Amount1 = new JSONArray(Arrays.asList(S_EMI_Amount));
        bank_name_Edite_txt1 = new JSONArray(Arrays.asList(S_bank_name_Edite_txt));
        loan_type_Edite_txt = new JSONArray(Arrays.asList(S_loan_type_Edite_txt));
        remaning_tenor_Edite_txt = new JSONArray(Arrays.asList(S_remaning_tenor_Edite_txt));

        EMI_Amount2 = new JSONArray(Arrays.asList(S_co_EMI_Amount));
        bank_name_Edite_txt2 = new JSONArray(Arrays.asList(S_co_bank_name_Edite_txt));
        loan_type_Edite_txt2 = new JSONArray(Arrays.asList(S_co_loan_type_Edite_txt));
        remaning_tenor_Edite_txt2 = new JSONArray(Arrays.asList(S_co_remaning_tenor_Edite_txt));

        JSONObject Applicant =new JSONObject();
        JSONObject Co_Applicant =new JSONObject();

        try {
            Applicant.put("is_existloan",Existing_Loan_ID);
            Applicant.put("emi_amount",EMI_Amount1);
            Applicant.put("bank_name",bank_name_Edite_txt1);
            Applicant.put("loan_type",loan_type_Edite_txt);
            Applicant.put("remaining_tenor",remaning_tenor_Edite_txt);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(IS_CO_Applicant_Id.equals("1"))
        {
            try {
                Co_Applicant.put("is_existloan",co_Existing_Loan_ID);
                Co_Applicant.put("emi_amount",EMI_Amount2);
                Co_Applicant.put("bank_name",bank_name_Edite_txt2);
                Co_Applicant.put("loan_type",loan_type_Edite_txt2);
                Co_Applicant.put("remaining_tenor",remaning_tenor_Edite_txt2);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else
        {

        }

        JSONObject J= null;
        try {
            J =new JSONObject();
            //  J.put(Params.email_id,email);
            J.put("applicant_count",app_count);
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
             J.put("user_id",Pref.getUSERID(getApplicationContext()));
            /*J.put("transaction_id","11502");
            J.put("user_id","10043");*/
            J.put("applicant",Applicant);
            J.put("co_applicant",Co_Applicant);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("Add Home Laoan", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.NOCRIFREPORT, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);

                        Log.e("Add Home Laoan", String.valueOf(data));
                        try {
                            JSONObject jsonObject1 = response.getJSONObject("response");
                            if(jsonObject1.getString("applicant_status").equals("success")) {
                                if(jsonObject1.getString("pay_status").equals("success"))
                                {
                                    Toast.makeText(context,"Eligibility Created Successfully",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Credite_report_details.this, Payment_Details_Activity.class);
                                    startActivity(intent);
                                    finish();

                                }else if(jsonObject1.getString("pay_status").equals("error"))
                                {
                                    Toast.makeText(context,"Eligibility Failed",Toast.LENGTH_SHORT).show();

                                    String viability_array =jsonObject1.getString("pay_status");
                                    Intent intent = new Intent(Credite_report_details.this, Home.class);
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

        Objs.ac.StartActivity(mCon, Eligibility_Check_PL.class);
        finish();
        super.onBackPressed();
    }
}
