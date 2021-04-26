package in.loanwiser.partnerapp.Partner_Statues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class BusinesscardEditActivity extends SimpleActivity {

    AppCompatButton updatebutton;
    AppCompatEditText nameedittxt,emailedittext,mobileedittext,locationedittext;
    AppCompatTextView nametxt,emailtxt,webtxt,phntxt,locationtxt;
    String Value,Value1,Value2,Value3,Value4;
    private android.app.AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    public static final String b2b_user_id1 = "b2b_uer_id";
    String b2b_user_id;
    SharedPreferences pref; // 0 - for private mode
    String email,mobilenumber,contactperson,location,url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_businesscard_edit);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_businesscard_edit);
        initTools(R.string.businessedit);

        progressDialog = new SpotsDialog(this, R.style.Custom);

        updatebutton=findViewById(R.id.updatebutton);
        nameedittxt=findViewById(R.id.nameedittxt);
        emailedittext=findViewById(R.id.emailedittext);
        mobileedittext=findViewById(R.id.mobileedittext);
        locationedittext=findViewById(R.id.locationedittext);

        nametxt=findViewById(R.id.nametxt);
        emailtxt=findViewById(R.id.emailtxt);
        webtxt=findViewById(R.id.webtxt);
        phntxt=findViewById(R.id.phntxt);
        locationtxt=findViewById(R.id.locationtxt);


        pref = this.getSharedPreferences("MyPref", 0);
        b2b_user_id =  pref.getString(b2b_user_id1, null);
        Log.i("TAG", "b2b_userid_onCreate: "+b2b_user_id);

        nameedittxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Value = nameedittxt.getText().toString();
                nametxt.setText(Value);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(nameedittxt.getText().toString())){
                    Toast.makeText(BusinesscardEditActivity.this, "Empty Name field not allowed!", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(emailedittext.getText().toString())){
                    Toast.makeText(BusinesscardEditActivity.this, "Empty Email field not allowed!", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(mobileedittext.getText().toString())){
                    Toast.makeText(BusinesscardEditActivity.this, "Empty Mobile field not allowed!", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(locationedittext.getText().toString())){
                    Toast.makeText(BusinesscardEditActivity.this, "Empty Location field not allowed!", Toast.LENGTH_SHORT).show();
                }else{
                    Bscardupdate();
                }

            }
        });
        mobileedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Value1 = mobileedittext.getText().toString();
                phntxt.setText(Value1);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        emailedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Value2=emailedittext.getText().toString();
                emailtxt.setText(Value2);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        locationedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Value3=locationedittext.getText().toString();
                locationtxt.setText(Value3);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Businesscarddetails();


    }


    private void Businesscarddetails() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2b_userid", b2b_user_id);
            Log.i("TAG", "Request "+J.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data  = String.valueOf(J);
        Log.d("Request :", data);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GETBUSINESSCARD, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        String JO_data  = String.valueOf(response);
                        Log.d("Request :", JO_data.toString());
                        try {
                            email=response.getString("email_id");
                            mobilenumber=response.getString("mobile_no");
                            contactperson=response.getString("contact_person");
                            location=response.getString("location");
                            url=response.getString("url");

                            nametxt.setText(contactperson);
                            emailtxt.setText(email);
                            phntxt.setText(mobilenumber);
                            locationtxt.setText(location);
                            webtxt.setText(url);

                            nameedittxt.setText(contactperson);
                            emailedittext.setText(email);
                            mobileedittext.setText(mobilenumber);
                            locationedittext.setText(location);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
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

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    private void Bscardupdate() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2b_userid", b2b_user_id);
            J.put("email_id", emailedittext.getText().toString());
            J.put("mobile_no", mobileedittext.getText().toString());
            J.put("contact_person", nameedittxt.getText().toString());
            Log.i("TAG", "Requestupdate "+J.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data  = String.valueOf(J);
        Log.d("Request :", data);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.UPDATEBSCARD, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        String JO_data  = String.valueOf(response);
                        Log.d("Request :", JO_data.toString());
                        try {
                        String result=response.getString("result");
                        if (result.equalsIgnoreCase("true")){
                            Toast.makeText(getApplicationContext(),"Suceesfully Updated",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(BusinesscardEditActivity.this,BusinessCardActivity.class);
                            startActivity(intent);

                        }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
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

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}