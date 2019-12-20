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

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
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
            paymentstatus,r4,payment_status;

    AppCompatButton btn1,btn2;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;

    String order_id,order_amt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_payment__details_);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_payment__details_);
        initTools(R.string.payment);


        progressDialog = new SpotsDialog(getApplicationContext(), R.style.Custom);

        details=(AppCompatTextView)findViewById(R.id.payment_details);

        paymentname=(AppCompatTextView)findViewById(R.id.payment_name);
        paymentamount=(AppCompatTextView)findViewById(R.id.payment_amount);


        paymentlead=(AppCompatTextView)findViewById(R.id.payment_lead);
        r1=(AppCompatTextView)findViewById(R.id.r1);
        lead=(AppCompatTextView)findViewById(R.id.lcc);


        paymentcrif=(AppCompatTextView)findViewById(R.id.payment_crif);
        r2=(AppCompatTextView)findViewById(R.id.r2);
        crif=(AppCompatTextView)findViewById(R.id.crc);


        paymenttotal=(AppCompatTextView)findViewById(R.id.payment_total);
        r3=(AppCompatTextView)findViewById(R.id.r3);
        totalamount =(AppCompatTextView)findViewById(R.id.payemnt_totalamount);

        paymentstatus=(AppCompatTextView)findViewById(R.id.payment_status);
        r4=(AppCompatTextView)findViewById(R.id.r4);
        payment_status=(AppCompatTextView)findViewById(R.id.status);


        btn1=(AppCompatButton)findViewById(R.id.back_side);
        btn2=(AppCompatButton)findViewById(R.id.next_side);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Do_payment_method();
            }
        });



    }


    private void Do_payment_method() {
        // progressDialog.show();
        order_id = "9596-0";
        order_amt = "1";
        JSONObject J =new JSONObject();
        try {
            J.put("order_id", order_id);
            J.put("order_amount",order_amt);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_TOCKEN, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            if (object.getString(Params.status).equals("OK")) {

                                String msg = object.getString("message");
                                String cftoken = object.getString("cftoken");

                                Log.e("message",msg);
                                Log.e("cftoken",cftoken);
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
        params.put(PARAM_ORDER_AMOUNT, order_amt);
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
        Log.e("TAG", "ReqCode : " + CFPaymentService.REQ_CODE);
        Log.e("TAG", "API Response : ");
        //Prints all extras. Replace with app logic.
        if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null)
                for (String key : bundle.keySet()) {
                    if (bundle.getString(key) != null) {
                        Log.e("TAG", key + " : " + bundle.getString(key));

                        if( key.contains("txStatus"))
                        {
                            String statues = bundle.getString(key);
                            Log.e("transaction","Transaction Faild");
                            Log.e("transaction1",statues);
                        }else
                        {
                            Log.e("transaction","Transaction Sucess");
                        }
                    }
                }
        }
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Eligibility_BL.class);
        finish();
        super.onBackPressed();
    }
}
