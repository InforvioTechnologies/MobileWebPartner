package in.loanwiser.partnerapp.User_Account;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Partner_Statues.Statues_Dashboard_Nav;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SMSRetrieverAPI.MySMSBroadcastReceiver;
import in.loanwiser.partnerapp.SMSRetrieverAPI.SmsListener;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MobileNoUpdated extends AppCompatActivity {

    private Context mCon = this;
    private AppCompatTextView verif,pls_enter,txt_mob;
    private AppCompatEditText edittext_mobile,editTextOtp;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = MobileNoUpdated.class.getSimpleName();
    private AwesomeValidation awesomeValidation;
    private AppCompatButton appCompatButtonGen_OTP,appCompatVerifty;
    InputMethodManager imm;
    private String Moblie;
    private String new_otp;
    private android.app.AlertDialog progressDialog;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private FloatingActionButton fab;
    private String mobile_no,OTP;
    private View view;
    public static final String OTP_REGEX = "[0-9]{1,5}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_no_updated);

       // setContentView(R.layout.activity_simple);
       // Objs.a.setStubId(this,R.layout.activity_login);
       // initTools(R.string.signin);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        mobile_no =  Objs.a.getBundle(this, Params.mobile_no);


        edittext_mobile = (AppCompatEditText) findViewById(R.id.mob);
        editTextOtp = (AppCompatEditText) findViewById(R.id.editTextOtp);
        appCompatButtonGen_OTP = (AppCompatButton) findViewById(R.id.appCompatButtonGen_OTP);
        appCompatVerifty = (AppCompatButton) findViewById(R.id.appCompatVerifty);


        initCode();
        Checking();

        edittext_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int begin, int i1, int i2) {
                new_otp = edittext_mobile.getText().toString();
                if(new_otp.length()==10){
                    imm.hideSoftInputFromWindow(edittext_mobile.getWindowToken(), 0);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        smsreciver();
        smsReceiver();
    }

    private void initCode() {
        initUI();
        fonts();
        clicks();
        verif.setText(mobile_no);
        edittext_mobile.setVisibility(View.VISIBLE);
        appCompatButtonGen_OTP.setVisibility(View.VISIBLE);
    }

    public void Checking(){
        if(checkPermission()== true) {
        }else{
            requestPermission();
        }
    }
    private void initUI() {


        verif = (AppCompatTextView) findViewById(R.id.verif);
        pls_enter = (AppCompatTextView) findViewById(R.id.pls_enter);
        //txt_mob = (AppCompatTextView) findViewById(R.id.txt_mob);

        addValidationToViews();


    }

    public void smsreciver(){
        SmsRetrieverClient client = SmsRetriever.getClient(this /* context */);
        Task<Void> task = client.startSmsRetriever();
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // Successfully started retriever, expect broadcast intent
                // ...
                Log.e("VerifedOTP", "SmsRetrievalResult status: Success");
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Failed to start retriever, inspect Exception for more details
                // ...
                Log.e("VerifedOTP", "SmsRetrievalResult start failed.", e);
            }
        });
    }

   /* public void recivedSmsUpdate(String message)
    {
        try
        {
            String str = message;
            String a=    str.replace("<#>", "");
            String a1=    a.replace("58662k2uEQl", "");
            String a2 = a1.replaceAll("[^\\d.]", "");
            String OTP1 =   a2.replace("\"", "");
            OTP =   OTP1.replaceAll("\\.", "");
            editTextOtp.setText(OTP);
        }
        catch (Exception e)
        {
        }
    }*/


    private void smsReceiver(){
        MySMSBroadcastReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                //From the received text string you may do string operations to get the required OTP
                //It depends on your SMS format
                Log.e("Message",messageText);
                // If your OTP is six digits number, you may use the below code
                Pattern pattern = Pattern.compile(OTP_REGEX);
                Matcher matcher = pattern.matcher(messageText);
                String otp = null;

                while (matcher.find()) {
                    otp = matcher.group();
                }

                if(otp != null && editTextOtp != null) {
                    editTextOtp.setText(otp);
                }
            }
        });
    }
    private void fonts() {

         Objs.a.EditTextStyle(mCon, editTextOtp);
        //  Objs.a.OutfitNormalFontStyle(mCon,R.id.verif);
       // Objs.a.OutfitNormalFontStyle(mCon,R.id.pls_enter);
        Objs.a.OutfitREgEditTextStyle(mCon, edittext_mobile);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.pls_enter);
        //  Objs.a.OutfitREgEditTextStyle(mCon, mob);
        // Objs.a.OutfitNormalFontStyle(mCon,R.id.txt_mob);
    }

    private void addValidationToViews() {
        awesomeValidation.addValidation(this, R.id.mob, "^[0-9]{10}$", R.string.error_empty_mobile);
    }



    private void clicks() {

        findViewById(R.id.appCompatButtonGen_OTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Moblie = edittext_mobile.getText().toString();

                Log.d("Mobile number", Moblie);
                submitForm();
            }
        });

        findViewById(R.id.appCompatVerifty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm_out();
            }
        });

    }
    private void submitForm() {
        if (awesomeValidation.validate()) {

            Moblie = edittext_mobile.getText().toString();
            Login_POST(Moblie);
            // ExitAlert(mCon);
        }
    }


    private void submitForm_out() {
        Moblie = edittext_mobile.getText().toString();
        new_otp = editTextOtp.getText().toString();
        Confirm_OTP_mob(new_otp,Moblie);
        Log.d("OTP number", new_otp);
    }

    private void Confirm_OTP_mob(String otp,String mob) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.mobile_no, mob);
            J.put(Params.otp_entered, otp);
            J.put(Params.b2b_userid, Pref.getID(mCon));


            Log.d("OTP number", String.valueOf(J));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Confirm_Upadte_OTP_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("OTP number", String.valueOf(response));
                        String JO_data  = String.valueOf(response);
                        try {
                            if(response.getString(Params.status).equals(Params.success)){

                                Objs.ac.StartActivity(mCon, Statues_Dashboard_Nav.class);
                                finish();
                            }
                            if(response.getString(Params.status).equals(Params.error)){
                                Objs.a.showToast(mCon,"Already Verified");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        };
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d(TAG, error.getMessage());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
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
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void Login_POST(String no) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.mobile_no,no);
            J.put(Params.b2b_userid, Pref.getID(mCon));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data  = String.valueOf(J);
        Log.d("Request :", data);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.MOBILE_UPDATE_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        String JO_data  = String.valueOf(response);
                        //   Objs.a.showToast(mCon,"BUSINESS_login_POST  "+"\n"+  JO_data );
                        Log.d("Response :", JO_data);
                        try {
                            if (response.getString(Params.status).equals(Params.success)){
                               if(response.getString(Params.otp_status).equals("0")){
                                   Objs.a.showToast(mCon,"Mobile number is not registered");

                               }
                                if(response.getString(Params.otp_status).equals("1")){
                                    Objs.a.showToast(mCon,"OTP will be sent to the mobile number");
                                    edittext_mobile.setVisibility(View.GONE);
                                    appCompatButtonGen_OTP.setVisibility(View.GONE);
                                    editTextOtp.setVisibility(View.VISIBLE);
                                    appCompatVerifty.setVisibility(View.VISIBLE);
                                }
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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

    private boolean checkPermission() {
        // int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
      //  int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), RECEIVE_SMS);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return  result1 == PackageManager.PERMISSION_GRANTED
                && result3 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA,READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    //  boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                  //  boolean smsAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean externalAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && externalAccepted)
                        Snackbar.make(findViewById(R.id.fab), "Permission Granted", Snackbar.LENGTH_LONG).show();
                    else {

                        Snackbar.make(findViewById(R.id.fab), "Permission Denied, You cannot access.", Snackbar.LENGTH_LONG).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to all the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                                                    requestPermissions(new String[]{ CAMERA,READ_EXTERNAL_STORAGE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }

                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(mCon)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Statues_Dashboard_Nav.class);
        finish();
        super.onBackPressed();
    }
}

