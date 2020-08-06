package in.loanwiser.partnerapp.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
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
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Creadite_Report_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Payment_Details_Activity;

import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_APP_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_EMAIL;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_NAME;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_CUSTOMER_PHONE;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_AMOUNT;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_ID;
import static com.gocashfree.cashfreesdk.CFPaymentService.PARAM_ORDER_NOTE;

public class PaymentDetails extends SimpleActivity {

    Button payment_button,cust_payment_button,send_payment_link1;

    private PopupWindow mPopupWindow;
    PopupWindow popupWindow;
    LinearLayout linearLayout1;
    AppCompatTextView back_button,back_button_b;
    ImageView closebtn;
    AppCompatTextView proceedany,back;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    String payment_option,paymentamoubt,payment_id,Order_Id,Name,mobile_no,email,payment_plane,
            payment_url;
    private AlertDialog progressDialog;
    private Context context = this;
    LinearLayout  standard,Custome,send_payment_link;

    AppCompatTextView payment_amount_stand,payment_amout_cust,payment_amont_link;

    AppCompatEditText mobilenumber_edittext;

    List<String> result1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_payment_details);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_payment_details);
        initTools(R.string.pay_ment);
        Intent intent = getIntent();
        payment_option = intent.getStringExtra("payment_option");
        paymentamoubt = intent.getStringExtra("paymentamoubt");
        payment_id = intent.getStringExtra("payment_id");
        payment_plane = intent.getStringExtra("payment_plane");

        Log.e("payment_plane",payment_plane);

        result1 = new ArrayList<>();
        payment_button=findViewById(R.id.payment_button);
        cust_payment_button=findViewById(R.id.cust_payment_button);
        send_payment_link1=findViewById(R.id.send_payment_link1);
        linearLayout1=findViewById(R.id.linearLayout1);
        back_button=findViewById(R.id.back_button);
        back_button_b=findViewById(R.id.back_button_b);

        standard=findViewById(R.id.standard);
        Custome=findViewById(R.id.Custome);

        payment_amount_stand=findViewById(R.id.payment_amount_stand);
        payment_amout_cust=findViewById(R.id.payment_amout_cust);
        payment_amont_link=findViewById(R.id.payment_amont_link);

        mobilenumber_edittext=findViewById(R.id.mobilenumber_edittext);

        payment_amount_stand.setText(payment_id);
        payment_amout_cust.setText(payment_id);
        payment_amont_link.setText(payment_id);

        send_payment_link=findViewById(R.id.send_payment_link);
        progressDialog = new SpotsDialog(context, R.style.Custom);

        if(payment_option.contains("2"))
        {
            standard.setVisibility(View.VISIBLE);
            Custome.setVisibility(View.GONE);
            send_payment_link.setVisibility(View.GONE);

        }else if(payment_option.contains("3"))
        {
            standard.setVisibility(View.GONE);
            Custome.setVisibility(View.VISIBLE);
            send_payment_link.setVisibility(View.GONE);
        }else  if(payment_option.contains("1"))
        {
            standard.setVisibility(View.GONE);
            Custome.setVisibility(View.GONE);
            send_payment_link.setVisibility(View.VISIBLE);
            get_payment_Link();

        }

        Step_copletions();

        payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pay_Credit_Coins();
               /* Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                startActivity(intent);*/
            }
               /* linearLayout1.setVisibility(View.GONE);
                LayoutInflater layoutInflater = (LayoutInflater) PaymentDetails.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup2,null);
                closebtn = (ImageView) customView.findViewById(R.id.closebtn);
                proceedany=(AppCompatTextView)customView.findViewById(R.id.proceedany);
                back=(AppCompatTextView)customView.findViewById(R.id.back);



                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //display the popup window
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        linearLayout1.setVisibility(View.VISIBLE);

                    }
                });

                proceedany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                        startActivity(intent);
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }*/
            //
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Pay_Credit_Coins();
                Intent intent = new Intent(PaymentDetails.this, PaymentActivity.class);
                startActivity(intent);
            }
               /* linearLayout1.setVisibility(View.GONE);
                LayoutInflater layoutInflater = (LayoutInflater) PaymentDetails.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup2,null);
                closebtn = (ImageView) customView.findViewById(R.id.closebtn);
                proceedany=(AppCompatTextView)customView.findViewById(R.id.proceedany);
                back=(AppCompatTextView)customView.findViewById(R.id.back);



                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //display the popup window
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        linearLayout1.setVisibility(View.VISIBLE);

                    }
                });

                proceedany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                        startActivity(intent);
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }*/
            //
        });
        back_button_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Pay_Credit_Coins();
                Intent intent = new Intent(PaymentDetails.this, PaymentActivity.class);
                startActivity(intent);
            }
               /* linearLayout1.setVisibility(View.GONE);
                LayoutInflater layoutInflater = (LayoutInflater) PaymentDetails.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup2,null);
                closebtn = (ImageView) customView.findViewById(R.id.closebtn);
                proceedany=(AppCompatTextView)customView.findViewById(R.id.proceedany);
                back=(AppCompatTextView)customView.findViewById(R.id.back);



                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //display the popup window
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        linearLayout1.setVisibility(View.VISIBLE);

                    }
                });

                proceedany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                        startActivity(intent);
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }*/
            //
        });

        cust_payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Do_payment_method();
               /* Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                startActivity(intent);*/
            }
               /* linearLayout1.setVisibility(View.GONE);
                LayoutInflater layoutInflater = (LayoutInflater) PaymentDetails.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup2,null);
                closebtn = (ImageView) customView.findViewById(R.id.closebtn);
                proceedany=(AppCompatTextView)customView.findViewById(R.id.proceedany);
                back=(AppCompatTextView)customView.findViewById(R.id.back);



                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //display the popup window
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        linearLayout1.setVisibility(View.VISIBLE);

                    }
                });

                proceedany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                        startActivity(intent);
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }*/
            //
        });

        send_payment_link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateMobile()) {
                    return;
                }
                Send_payment_Link();
               /* Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                startActivity(intent);*/
            }
               /* linearLayout1.setVisibility(View.GONE);
                LayoutInflater layoutInflater = (LayoutInflater) PaymentDetails.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup2,null);
                closebtn = (ImageView) customView.findViewById(R.id.closebtn);
                proceedany=(AppCompatTextView)customView.findViewById(R.id.proceedany);
                back=(AppCompatTextView)customView.findViewById(R.id.back);



                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //display the popup window
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        linearLayout1.setVisibility(View.VISIBLE);

                    }
                });

                proceedany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                        startActivity(intent);
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }*/
            //
        });

      //  get_pay_shedule();

    }

    private boolean validateMobile() {
        if (mobilenumber_edittext.length() < 10 || mobilenumber_edittext.length() > 10) {
            mobilenumber_edittext.setError(getText(R.string.err_curent));
            mobilenumber_edittext.requestFocus();
            return false;
        } else {

        }
        return true;
    }
    private void get_payment_Link() {

         Order_Id = Pref.getUSERID(getApplicationContext()) + "-0";
        JSONObject J =new JSONObject();
        try {
            J.put("order_id", Order_Id);
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            J.put("plan_choose", payment_plane);
            J.put("order_amount", payment_id);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Payment schedule", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_PAYLINK, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {

                        Log.e("Payment response", String.valueOf(object));

                        try {
                            String statues = object.getString("payint_status");
                            if(statues.contains("success"))
                            {
                                 payment_url = object.getString("payment_url");
                            }else
                            {
                                Toast.makeText(mCon, "you have already paid the Amount",Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());

                Log.e("error",error.getMessage());
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

    private void Step_copletions() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("user_id", Pref.getUSERID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Statues Request ",String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Lead_Details_statues, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.e("Statues response",String.valueOf(response));
                            String report_statues = response.getString("status");
                            if(report_statues.equals("success"))
                            {
                                JSONObject jsonObject1 = response.getJSONObject("data");
                                String user_name = jsonObject1.getString("user_name");
                                String mobileno = jsonObject1.getString("mobileno");
                                mobilenumber_edittext.setText(mobileno);
                            }else
                            {
                                Toast.makeText(getApplicationContext(),"error please check!!!", Toast.LENGTH_SHORT).show();

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

    private void Send_payment_Link() {

        String mob_no = mobilenumber_edittext.getText().toString();

        Order_Id = Pref.getUSERID(getApplicationContext()) + "-0";
        JSONObject J =new JSONObject();
        try {
            J.put("mobileno", mob_no);
            J.put("pay_content", payment_url);
          //  J.put("payment_url", payment_url);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Payment schedule", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.send_paymentlink, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {

                        Log.e("Payment response", String.valueOf(object));

                        try {
                            String statues = object.getString("type");
                            if(statues.contains("success"))
                            {

                                Toast.makeText(mCon, "Payment Link is Sucessfully Send to your Mobile No",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PaymentDetails.this,Dashboard_Activity.class);
                                startActivity(intent);


                            }else
                            {
                                Toast.makeText(mCon, "you have already paid the Amount",Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());

                Log.e("error",error.getMessage());
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
          Order_Id = Pref.getUSERID(getApplicationContext()) + "-0";
         //  Order_Id = "9556" + "-0";
        JSONObject J =new JSONObject();
        try {
            J.put("order_id", Order_Id);
            J.put("order_amount",payment_id);

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
                            if (object.getString("status").equals("OK")) {

                                String msg = object.getString("message");
                                String cftoken = object.getString("cftoken");

                                JSONObject user_data = object.getJSONObject("user_data");

                                 Name = user_data.getString("user_name");
                                 mobile_no = user_data.getString("mobile_no");
                                 email = user_data.getString("email");

                                Log.e("Payment Name", String.valueOf(Name));
                                Log.e("Payment mobile_no", String.valueOf(mobile_no));
                                Log.e("Payment email", String.valueOf(email));
                                Do_Cashfree_Payment(cftoken);

                                //  Log.e("message",msg);
                                //  Log.e("cftoken",cftoken);
                               // Do_Cashfree_Payment(cftoken);

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

    private void Pay_Credit_Coins( ) {

        JSONObject J= null;

        try {
            J =new JSONObject();
            J.put("b2buser_id", Pref.getID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("coins",paymentamoubt);
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("request", String.valueOf(J));
        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.COIN_Transaction, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        Log.e("Response", String.valueOf(response));

                        try {
                            String statues = response.getString("status");

                            if(statues.equals("1"))
                            {
                                Intent intent = new Intent(PaymentDetails.this, Payment_Sucess_Screen.class);
                                startActivity(intent);
                                finish();
                            }else
                            {
                                Toast.makeText(mCon, "Low Balance Payment Not Completed",Toast.LENGTH_SHORT).show();

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

        // AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);


    }
    private void send_data()
    {
        JSONArray jsonArray = new JSONArray();
        jsonArray = new JSONArray(result1);
        Log.e("jsonArray", String.valueOf(jsonArray));


        JSONObject J =new JSONObject();
        try {
            J.put("request", jsonArray);
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            J.put("b2buser_id", Pref.getID(getApplicationContext()));
            J.put("plan_choose", payment_plane);
            J.put("basicAmount", paymentamoubt);
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("jsonArray", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PAYMENT_CONFIRMATION, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {

                        Log.e("Payment Statues", String.valueOf(object));
                        try {
                            if (object.getString("status").equals("success")) {

                                Toast.makeText(mCon, "Sucessfully Completed",Toast.LENGTH_SHORT).show();
                                Update_Payment_Statues();
                            }else if (object.getString("status").equals("cancelled"))
                            {
                                Toast.makeText(mCon, "Payment is Canceled",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PaymentDetails.this, DashBoard_new.class);
                                startActivity(intent);
                            }else {

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
                                Intent intent = new Intent(PaymentDetails.this, Payment_Sucess_Screen.class);
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

    private void Do_Cashfree_Payment(String cftoken)
    {
        String stage = "TEST";
        String appId = "100221d5f45db701fd6552fc722001";
        String orderNote = "Test Order";

      /*  String customerName = "mathayan";
        String customerPhone = "8883072458";
        String customerEmail = "arivindh321@gmail.com";*/

        Map<String, String> params = new HashMap<>();

        for(Map.Entry entry : params.entrySet()) {
            Log.d("CFSKDSample", entry.getKey() + " " + entry.getValue());
        }

        params.put(PARAM_APP_ID, appId);
        params.put(PARAM_ORDER_ID, Order_Id);
        params.put(PARAM_ORDER_AMOUNT, payment_id);
        params.put(PARAM_ORDER_NOTE, orderNote);
        params.put(PARAM_CUSTOMER_NAME, Name);
        params.put(PARAM_CUSTOMER_PHONE, mobile_no);
        params.put(PARAM_CUSTOMER_EMAIL,email);


        Log.e("params",params.toString());
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

   /*@Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
        finish();
        super.onBackPressed();
    }*/

}
