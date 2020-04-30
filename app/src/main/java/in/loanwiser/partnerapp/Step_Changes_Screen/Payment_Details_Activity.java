package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gocashfree.cashfreesdk.CFPaymentService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_APP_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_NOTE;

public class Payment_Details_Activity extends SimpleActivity {


    AppCompatTextView details,
            paymentname,paymentamount,
            paymentlead,r1,lead,
            paymentcrif,r2,crif,
            paymenttotal,r3,totalamount,
            paymentstatus,r4,payment_status, Lead_charge,CRIE_charge;

    AppCompatButton btn1,btn2,done,get_link;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;
    List<String> result1;

    String order_id,order_amt,pay_for,pay_id,amount,Payment_Amount;
    int araycount=0;
    LinearLayout lead_charge,crif_charge;

    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_payment__details_);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_payment__details_);
        initTools(R.string.payment);

        result1 = new ArrayList<>();
        progressDialog = new SpotsDialog(context, R.style.Custom);


        UISCREEN();
        click();
        get_pay_shedule();



    }

    private void click()
    {
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Do_payment_method();

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment_Details_Activity.this, Dashboard_Activity.class);
                startActivity(intent);
                finish();
            }
        });

      /*  btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment_Details_Activity.this, Send_Payment_Link_Activity.class);
                startActivity(intent);
                finish();
            }
        });*/
    }

    private void UISCREEN()
    {
        details=(AppCompatTextView)findViewById(R.id.payment_details);

        paymentname=(AppCompatTextView)findViewById(R.id.payment_name);
        paymentamount=(AppCompatTextView)findViewById(R.id.payment_amount);

        lead_charge = (LinearLayout) findViewById(R.id.lead_charge);
        crif_charge = (LinearLayout) findViewById(R.id.crif_charge);

        paymentlead=(AppCompatTextView)findViewById(R.id.payment_lead);
        r1=(AppCompatTextView)findViewById(R.id.r1);

        Lead_charge=(AppCompatTextView)findViewById(R.id.Lead_charge);


        paymentcrif=(AppCompatTextView)findViewById(R.id.payment_crif);
        r2=(AppCompatTextView)findViewById(R.id.r2);
       CRIE_charge=(AppCompatTextView)findViewById(R.id.CRIE_charge);


        paymenttotal=(AppCompatTextView)findViewById(R.id.payment_total);
        r3=(AppCompatTextView)findViewById(R.id.r3);
        totalamount =(AppCompatTextView)findViewById(R.id.payemnt_totalamount);

        paymentstatus=(AppCompatTextView)findViewById(R.id.payment_status);
        r4=(AppCompatTextView)findViewById(R.id.r4);
        payment_status=(AppCompatTextView)findViewById(R.id.status);


        btn1=(AppCompatButton)findViewById(R.id.get_link);
        btn2=(AppCompatButton)findViewById(R.id.next_side);
        done=(AppCompatButton)findViewById(R.id.skip_payment);
    }

    private void get_pay_shedule() {
         progressDialog.show();

        String Order_Id = Pref.getUSERID(getApplicationContext()) + "-0";
      //  String Order_Id = "10043" + "-0";
        JSONObject J =new JSONObject();
        try {
            J.put("order_id", Order_Id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Payment schedule", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PAYMENT_SCHEDULE, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {

                        Log.e("Payment schedule", String.valueOf(object));
                        try {
                            if (object.getString("Payment schedule").equals("Success")) {


                                JSONObject payment_reponse= object.getJSONObject("Response");
                                Payment_Amount = payment_reponse.getString("payment_amount");
                                JSONArray payment_arr = payment_reponse.getJSONArray("payment_arr");

                                totalamount.setText(Payment_Amount);

                                araycount=payment_arr.length();

                                if(araycount >1)
                                {
                                    lead_charge .setVisibility(View.VISIBLE);
                                    crif_charge .setVisibility(View.VISIBLE);

                                }else
                                {

                                    lead_charge .setVisibility(View.VISIBLE);
                                    crif_charge .setVisibility(View.GONE);

                                }

                                for (int i = 0; i < araycount; i++) {
                                    JSONObject rec = null;
                                    try {
                                        rec = payment_arr.getJSONObject(i);
                                        //  int id = rec.getInt("id");
                                      order_id = rec.getString("order_id");
                                      pay_for = rec.getString("pay_for");
                                      pay_id = rec.getString("pay_id");
                                      amount = rec.getString("amount");

                                        Log.e("Payment schedule", order_id);
                                        Log.e("amount ", amount);

                                      if(i>1)
                                      {
                                          CRIE_charge.setText(amount);
                                          Lead_charge.setText(amount);
                                      }else
                                      {
                                          Lead_charge.setText(amount);

                                      }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }else
                            {
                                Toast.makeText(mCon, "payment Schedule is Faild",Toast.LENGTH_SHORT).show();

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

    private void Payment_initialize() {
        // progressDialog.show();
        //  order_id = "95914-0";
        ///  order_amt = "1";
        JSONObject J =new JSONObject();
        try {
            J.put("order_id", order_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Payment initialize", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Payment_Initialize, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment", String.valueOf(object));
                        try {

                            JSONObject jsonObject = object.getJSONObject("status");
                            if (jsonObject.getString("status").equals("1")) {



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

    private void Do_payment_method() {
        // progressDialog.show();
      //  order_id = "95914-0";
      ///  order_amt = "1";
        JSONObject J =new JSONObject();
        try {
            J.put("order_id", order_id);
            J.put("order_amount",Payment_Amount);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Payment tocken", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_TOCKEN, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment tocken", String.valueOf(object));
                        try {
                            if (object.getString(Params.status).equals("OK")) {

                                String msg = object.getString("message");
                                String cftoken = object.getString("cftoken");
                              //  Log.e("message",msg);
                              //  Log.e("cftoken",cftoken);
                                Do_Cashfree_Payment(cftoken);

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

    private void Do_Cashfree_Payment(String cftoken)
    {
        String stage = "TEST";
        String appId = "100221d5f45db701fd6552fc722001";
        String orderNote = "Test Order";

        String customerName = "mathayan";
        String customerPhone = "8883072458";
        String customerEmail = "arivindh321@gmail.com";

        Map<String, String> params = new HashMap<>();

        for(Map.Entry entry : params.entrySet()) {
            Log.d("CFSKDSample", entry.getKey() + " " + entry.getValue());
        }

        params.put(PARAM_APP_ID, appId);
        params.put(PARAM_ORDER_ID, order_id);
        params.put(PARAM_ORDER_AMOUNT, Payment_Amount);
        params.put(PARAM_ORDER_NOTE, orderNote);
        params.put(PARAM_CUSTOMER_NAME, customerName);
        params.put(PARAM_CUSTOMER_PHONE, customerPhone);
        params.put(PARAM_CUSTOMER_EMAIL,customerEmail);

        CFPaymentService cfPaymentService = CFPaymentService.getCFPaymentServiceInstance();
       // cfPaymentService.setOrientation(0);

       // cfPaymentService.doPayment(this, params, cftoken, stage, "#000000", "#FFFFFF");
        cfPaymentService.doPayment(this, params, cftoken, stage);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Same request code for all payment APIs.
      //  Log.e("TAG", "ReqCode : " + CFPaymentService.REQ_CODE);
      //  Log.e("TAG", "API Response : ",  data);
        //Prints all extras. Replace with app logic.
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        Log.e("Response", key + " : " + bundle.getString(key));
                        result1.add(key + " : " + bundle.getString(key));

                       /* if( key.contains("txStatus"))
                        {
                            String statues = bundle.getString(key);
                            Log.e("transaction1",statues);
                        }else
                        {
                            Log.e("transaction","Transaction Sucess");
                        }*/

                    }

                }
            Log.e("Result", String.valueOf(result1));
                send_data();
        }

    }
    private void send_data()
    {
        JSONArray jsonArray = new JSONArray();
          jsonArray = new JSONArray(result1);
        Log.e("jsonArray", String.valueOf(jsonArray));


        JSONObject J =new JSONObject();
        try {
            J.put("request", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("jsonArray", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PAYMENT_CONFIRMATION, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            if (object.getString("status").equals("success")) {

                                Toast.makeText(mCon, "Sucessfully Completed",Toast.LENGTH_SHORT).show();
                                Update_Payment_Statues();

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

    private void Update_Payment_Statues() {
        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
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
                                Intent intent = new Intent(Payment_Details_Activity.this, Creadite_Report_Activity.class);
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
    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
        finish();
        super.onBackPressed();
    }
}
