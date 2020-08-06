package in.loanwiser.partnerapp.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Eligibility_Check_PL;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Check_PL;

public class PaymentActivity extends SimpleActivity implements CompoundButton.OnCheckedChangeListener {

    AppCompatTextView skip_payment,cusben_textviewone,cusben_textviewsecond,partner_textviewone,partner_textviewtwo,loancredit_textview;
    Button proceed_button;
    AppCompatSpinner customplan_amount;
    RadioButton custom_radio, standard_radio,paymentcustomer_radio,loancredit_radio,paycollect_radio;
    LinearLayout customben_laysecond,customben_layone,partnerben_laysecond,partnerben_layone,skip_payment1;
    private Context context = this;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    String[] SALARY_Method;
    ArrayAdapter<String> Salary_Adapter;

    private AlertDialog progressDialog;
    Typeface font;

    String  payment_id,Salary_Value,paymentamoubt,Payment_value,payment_key,
            applicant_count,co_app;
    JSONArray payment_values;

    String STAND="0",CUST="0",PAY_OPTION="0",Chose_plan="0";

    String pay_plan;
    PopupWindow popupWindow;
    AppCompatTextView proceedany,back;
    Button  closePopupBtn,close,view_report;
    ImageView closebtn;
    AppCompatTextView standard_amount;

    SharedPreferences pref;
    public static final String IS_CO_Applicant_Id = "IS_CO_Applicant_Id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_payment);
        initTools(R.string.pay_ment);

        progressDialog = new SpotsDialog(context, R.style.Custom);
        pref = getApplication().getSharedPreferences("MyPref", 0);

        UI_Fields();
        Click();
        makeJsonObjReq1();
       // get_pay_shedule();
    }


   private void UI_Fields()
    {
        skip_payment = findViewById(R.id.skip_payment);
        proceed_button=findViewById(R.id.proceed_button);
        customplan_amount=findViewById(R.id.customplan_amount);
        skip_payment=findViewById(R.id.skip_payment);

        custom_radio=(RadioButton) findViewById(R.id.custom_radio);
        standard_radio=(RadioButton) findViewById(R.id.standard_radio);
        paymentcustomer_radio=(RadioButton)findViewById(R.id.paymentcustomer_radio);
        loancredit_radio=(RadioButton)findViewById(R.id.loancredit_radio);
        paycollect_radio=(RadioButton)findViewById(R.id.paycollect_radio);

        //Linearlayouts
        customben_laysecond=findViewById(R.id.customben_laysecond);
        customben_layone=findViewById(R.id.customben_layone);
        partnerben_layone=findViewById(R.id.partnerben_layone);
        partnerben_laysecond=findViewById(R.id.partnerben_laysecond);

        skip_payment1=findViewById(R.id.skip_payment1);

        //Textviews

        cusben_textviewone=findViewById(R.id.cusben_textviewone);
        cusben_textviewsecond=findViewById(R.id.cusben_textviewsecond);
        partner_textviewone=findViewById(R.id.partner_textviewone);
        partner_textviewtwo=findViewById(R.id.partner_textviewtwo);
        loancredit_textview=findViewById(R.id.loancredit_textview);
        standard_amount=findViewById(R.id.standard_amount);
    }

    private void Click()
    {
        custom_radio.setOnCheckedChangeListener(this);
        standard_radio.setOnCheckedChangeListener(this);
        paymentcustomer_radio.setOnCheckedChangeListener(this);
        loancredit_radio.setOnCheckedChangeListener(this);
        paycollect_radio.setOnCheckedChangeListener(this);

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate();
            }
        });

        skip_payment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Toast.makeText(context,"Viability Created Successfully",Toast.LENGTH_SHORT).show();
                LayoutInflater layoutInflater = (LayoutInflater) PaymentActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup2,null);

                proceedany = (AppCompatTextView) customView.findViewById(R.id.proceedany);
                back = (AppCompatTextView) customView.findViewById(R.id.back);
                closebtn = (ImageView) customView.findViewById(R.id.closebtn);



                //instantiate popup window
                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                //display the popup window
                popupWindow.showAtLocation(proceed_button, Gravity.CENTER, 0, 0);

                //close the popup window on button click
                proceedany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(PaymentActivity.this, Dashboard_Activity.class);
                        startActivity(intent);
                       // popupWindow.dismiss();
                        finish();
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });


              /*  */
            }
        });


        skip_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update_Payment_Statues();
            }
        });

    }

    private void Update_Payment_Statues() {
        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("pay_skip","1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("payment_st_request",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.UPDATE_PAYMENT_STATUES, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("payment_st_request",object.toString());
                        try {
                            JSONObject response = object.getJSONObject("response");

                            String Staues_pay = response.getString("status");
                            if(Staues_pay.contains("success"))
                            {
                                Intent intent = new Intent(PaymentActivity.this, Dashboard_Activity.class);
                                startActivity(intent);
                                finish();
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

/*    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.standard_radio:
                if (checked)
              *//*      standard_radio.setSelected(true);
                    custom_radio.setSelected(false);*//*
                    Toast.makeText(this, "standard", Toast.LENGTH_SHORT).show();
                break;
            case R.id.custom_radio:
                if (checked)
                    *//*custom_radio.setSelected(true);
                    standard_radio.setSelected(false);*//*
                    Toast.makeText(this, "Cusstom", Toast.LENGTH_SHORT).show();
                break;
        }
    }*/

    @SuppressLint("LongLogTag")
    private void Validate()
    {

        Log.e("STAND",STAND);
        Log.e("CUST",CUST);
        Log.e("PAY_OPTION",PAY_OPTION);

        if(Chose_plan.contains("0"))
        {
            Toast.makeText(this, "Select the choose Plane Option", Toast.LENGTH_SHORT).show();

        }else
        {
            if(STAND.contains("1"))
            {
                if(PAY_OPTION.contains("0"))
                {
                    Toast.makeText(this, "Select the Payment Option to Proceed", Toast.LENGTH_SHORT).show();
                }else
                {

                    Log.e("the value to fine STAND",STAND);
                     pay_plan = "1";
                    Log.e("the value to fine pay_plan pay_plan",pay_plan);
                    Intent intent = new Intent(PaymentActivity.this, PaymentDetails.class);
                    intent.putExtra("payment_option", PAY_OPTION);
                    intent.putExtra("paymentamoubt", paymentamoubt);
                    intent.putExtra("payment_id", payment_id);
                    intent.putExtra("payment_plane", pay_plan);
                    startActivity(intent);
                    // finish();
                }

            }else if(CUST.contains("2"))
            {
                Log.e("the value to fine CUST",CUST);
                if(PAY_OPTION.contains("0"))
                {
                    Toast.makeText(this, "Select the Payment Option to Proceed", Toast.LENGTH_SHORT).show();

                }else
                {
                     pay_plan = "2";
                    Log.e("the value to fine ",pay_plan);
                    Intent intent = new Intent(PaymentActivity.this, PaymentDetails.class);
                    intent.putExtra("payment_option", PAY_OPTION);
                    intent.putExtra("paymentamoubt", paymentamoubt);
                    intent.putExtra("payment_id", payment_id);
                    intent.putExtra("payment_plane", pay_plan);
                    startActivity(intent);
                    // finish();

                }
            }

        }





    }
    private void makeJsonObjReq1() {
        progressDialog.show();
        JSONObject J= null;

       //  co_app = Pref.getCoAPPAVAILABLE(getApplicationContext());

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        co_app=prefs.getString("co_applicant","defaultStringIfNothingFound");
        Log.i("TAG", "onCreate:CO_Employement_Type "+co_app);
       // String co_app =  pref.getString(IS_CO_Applicant_Id, null);
       // b2b_user_id =  pref.getString(b2b_user_id1, null);

        if(co_app.equals("2"))
        {
            applicant_count = "1";
        }else {
             applicant_count = "2";
        }

        try {
            J =new JSONObject();

         //   J.put("app_count","1");
           J.put("app_count",applicant_count);
            J.put("b2buser_id", Pref.getID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("request Dreopdown", J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Payable_Amount, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                          Log.e("respose Dreopdown", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        try {

                            String statues = object.getString("status");
                            if(statues.contains("success"))
                            {
                                paymentamoubt = object.getString("standard_amount");
                                standard_amount.setText(paymentamoubt);
                                payment_values =object.getJSONArray("custom_plan");
                                Log.e("Salary_proof_ar",String.valueOf(payment_values));

                                Salry_method_Spinner(payment_values);
                                standard_amount.setText(paymentamoubt);
                            }else
                            {

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
            customplan_amount.setAdapter(Salary_Adapter);

            customplan_amount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");


                        Payment_value = Salary_method_ar.getJSONObject(position).getString("value");
                        payment_key = Salary_method_ar.getJSONObject(position).getString("key");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        payment_id = Payment_value;
                        Log.d("payment_id", Payment_value);
                        Log.d("Salary_Value", payment_key);

                        get_credit_coins_points(Payment_value);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            customplan_amount.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });


        }

    }

    private void get_credit_coins_points(String Payment_value ) {
        progressDialog.show();

        String Order_Id = Pref.getUSERID(getApplicationContext()) + "-0";
        // String Order_Id = "9556" + "-0";
        JSONObject J =new JSONObject();
        try {
            J.put("order_amount", Payment_value);
            J.put("basic_amount", paymentamoubt);

        } catch (JSONException e) {
            e.printStackTrace();
        }
       // Log.e("Payment schedule", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.customplan_msg, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment schedule", String.valueOf(object));

                        try {
                            String statues = object.getString("status");
                            JSONObject message = object.getJSONObject("message");

                            String str_msg1 = message.getString("msg1");
                            String str_msg2 = message.getString("msg2");
                            partner_textviewone.setText(str_msg1);
                            partner_textviewtwo.setText(str_msg2);
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

    @SuppressLint("LongLogTag")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            if (buttonView.getId() == R.id.standard_radio) {
                custom_radio.setChecked(false);
                customben_laysecond.setVisibility(View.GONE);
                cusben_textviewone.setText(getString(R.string.cusben_desc));
                partner_textviewone.setText(getString(R.string.par_desc));
                partnerben_laysecond.setVisibility(View.GONE);
                loancredit_radio.setEnabled(true);
                loancredit_textview.setTextColor(Color.parseColor("#002B5D"));
                STAND= "1";
                Chose_plan= "1";
                payment_id = paymentamoubt;
                pay_plan = "1";
                Log.e("the value to fine pay_plan",pay_plan);
            }
            if (buttonView.getId() == R.id.custom_radio) {
                standard_radio.setChecked(false);
                customben_laysecond.setVisibility(View.VISIBLE);
                partnerben_laysecond.setVisibility(View.VISIBLE);
                cusben_textviewone.setText(getString(R.string.instantloan));
                cusben_textviewsecond.setText(getString(R.string.fasttrack_loan));

                //partner_textviewone.setText(getString(R.string.partner_loan_one));
              //  partner_textviewtwo.setText(getString(R.string.partner_loan_second));

                loancredit_radio.setChecked(false);
                loancredit_radio.setEnabled(false);
                loancredit_textview.setTextColor(Color.parseColor("#B6B6B6"));
                payment_id = Payment_value;
                CUST = "2";
                Chose_plan = "1";
                pay_plan = "2";
                Log.e("the value to fine pay_plan",pay_plan);
            }

            if (buttonView.getId()==R.id.paymentcustomer_radio){
                paycollect_radio.setChecked(false);
                loancredit_radio.setChecked(false);
                PAY_OPTION = "1";

            }

            if (buttonView.getId()==R.id.loancredit_radio){
                paymentcustomer_radio.setChecked(false);
                paycollect_radio.setChecked(false);
                PAY_OPTION = "2";
            }

            if (buttonView.getId()==R.id.paycollect_radio){
                paymentcustomer_radio.setChecked(false);
                loancredit_radio.setChecked(false);
                PAY_OPTION = "3";
            }
        }

    }
}


