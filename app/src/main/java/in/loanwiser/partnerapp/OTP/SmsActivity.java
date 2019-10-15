package in.loanwiser.partnerapp.OTP;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

public class SmsActivity extends AppCompatActivity {

    private Context mCon = this;
    private AwesomeValidation awesomeValidation;
    static  AppCompatEditText editTextOtp;
    private AppCompatTextView Smsauto,verfity;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = SmsActivity.class.getSimpleName();
    private AppCompatButton appCompatButtonSubmit;
    private AppCompatTextView textViewShowTime;
    private CountDownTimer countDownTimer;
    private long totalTimeCountInMilliseconds;
    private long timeBlinkInMilliseconds;
    private boolean blink;
    private AppCompatTextView verif,pls_enter,tvTimeCount;
    private String S_pinview;
    String OTP, opt_bundle,no_bundle;
    private AlertDialog progressDialog;
    private String JSON,new_otp;
    private String S_moblie,S_name,S_door,S_buliding,S_street,S_pincode,S_city,S_state,S_business_type;
    private ProgressDialog pDialog ,Sms_Dialog;
    InputMethodManager imm;
    public static final String OTP_REGEX = "[0-9]{1,5}";
    String  user_array,partner_code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Authenticating please wait...");
        Sms_Dialog = new ProgressDialog(this);
        Sms_Dialog.setMessage("Authenticating please wait...");
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        JSON =  Objs.a.getBundle(this, Params.JSON);
        opt_bundle =  Objs.a.getBundle(this, Params.otp);
        no_bundle =  Objs.a.getBundle(this, Params.mobile_no);

        try {
            JSONObject j =  new JSONObject(JSON);

            S_name =  j.getString(Params.contact_person);
            S_moblie = j.getString(Params.mobile_no);
            S_city =  j.getString(Params.district_id);
            S_state =  j.getString(Params.state_id);
//            S_pincode =  j.getString(Params.pincode);
            S_business_type = j.getString(Params.business_type);

            //    String all = opt_bundle +"\n"+ S_moblie +"\n"+S_name +"\n"+ S_pincode +"\n"+
            //    S_city +"\n"+ S_state +"\n"+ S_business_type;
            //    Objs.a.showToast(mCon, all );

        } catch (JSONException e) {
            e.printStackTrace();
        }
        editTextOtp =(AppCompatEditText)findViewById(R.id.editTextOtp);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        initCode();
        editTextOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int begin, int i1, int i2) {
                new_otp = editTextOtp.getText().toString();
                if(new_otp.length()==5){
                    imm.hideSoftInputFromWindow(editTextOtp.getWindowToken(), 0);
                   // appCompatButtonSubmit.setBackgroundColor(getResources().getColor(R.color.bc_green));
                    /*appCompatButtonSubmit.setEnabled(true);
                    if(new_otp.equals(opt_bundle)){
                        appCompatButtonSubmit.setBackgroundColor(getResources().getColor(R.color.bc_green));
                        appCompatButtonSubmit.setEnabled(true);
                       // Confirm_OTP_mob(opt_bundle);
                    }else {
                        appCompatButtonSubmit.setEnabled(false);
                    }*/

                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        setTimer();
        startTimer();

       smsReceiver();
    }

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

    private void initCode() {
        initUI();
        clicks();
        fonts();
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.doc.propwiser.documentupload", PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign= Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("MY KEY HASH:", sign);
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    private void initUI() {


        textViewShowTime = (AppCompatTextView) findViewById(R.id.tvTimeCount);
        appCompatButtonSubmit = (AppCompatButton) findViewById(R.id.appCompatButtonSubmit);
        verif = (AppCompatTextView) findViewById(R.id.verif);
        pls_enter = (AppCompatTextView) findViewById(R.id.pls_enter);
        tvTimeCount = (AppCompatTextView) findViewById(R.id.tvTimeCount);
        appCompatButtonSubmit =(AppCompatButton)findViewById(R.id.appCompatButtonSubmit);
        addValidationToViews();
    }
    private void fonts() {

        Objs.a.OutfitREgEditTextStyle(mCon, editTextOtp);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.verif);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.pls_enter);
        Objs.a.NormalFontStyle(mCon, R.id.tvTimeCount);
    }
    private void addValidationToViews() {
        awesomeValidation.addValidation(this, R.id.editTextOtp, "^[0-9]{5}$", R.string.mobileerror);
    }
    private void clicks() {
        findViewById(R.id.appCompatButtonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_otp = editTextOtp.getText().toString();
                if(new_otp.length()==5){
                    submitForm();
                }else {
                    Objs.a.showToast(mCon,"Please in enter the OTP");
                }
            }
        });
    }

   /* public void recivedSms(String message)
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

    private void submitForm() {
        new_otp = editTextOtp.getText().toString();
        Confirm_OTP_mob(new_otp);
        Log.d("OTP number", new_otp);
    }


    ///Confirmation Part
    private void Confirm_OTP_mob(String otp) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {


            J =new JSONObject();
            J.put(Params.otp_entered, otp);
            J.put(Params.flag, "1");
            J.put(Params.contact_person, S_name);
            J.put(Params.mobile_no,S_moblie);
            J.put(Params.district_id, S_city);
            J.put(Params.state_id, S_state);
        //    J.put(Params.pincode, S_pincode);
            J.put(Params.business_type, S_business_type);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String data  = String.valueOf(J);
        //   Objs.a.showToast(mCon, "Confirm Request JSON  "+data );
        Log.d("Request :", data);

        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(mCon);
        queue.getCache().clear();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Confirm_REG_OTP_POST1, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("Response :", String.valueOf(response));

                            if(response.getString(Params.status).equals(Params.ok)){

                                if(response.getString(Params.state_status).equals("0")){
                                   // Objs.a.showToast(mCon,"No Operation ");
                                     user_array = response.getString(Params.b2b_userid);
                                     partner_code = response.getString(Params.partner_code);

                                    ExitAlert(mCon,partner_code,user_array);

                                }
                                if(response.getString(Params.state_status).equals("1")) {
                                     user_array = response.getString(Params.b2b_userid);
                                     partner_code = response.getString(Params.partner_code);
                                    Pref.putPART(mCon, partner_code);
                                    Objs.a.showToast(mCon, "Welcome to Loanwiser, you have successfully logged in");
                                    Pref.putUID(mCon, no_bundle);
                                    Pref.putMobile(mCon, S_moblie);
                                    Pref.putName(mCon, S_name);
                                    Pref.putID(mCon, user_array);
                                    Objs.ac.StartActivity(mCon, Statues_Dashboard_Nav.class);
                                    finish();
                                }
                            }

                            if(response.getString(Params.status).equals(Params.error)){
                                Objs.a.showToast(mCon,"Already Verified");
                                if(response.getString(Params.state_status).equals("0")){
                                    Objs.a.showToast(mCon,"No Operation ");
                                }
                                if(response.getString(Params.state_status).equals("1")) {

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

        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    public void ExitAlert(Context context, final String pd_code,final String u_array ) {
        //http://android.support.v7.app/
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(R.string.attention));
        builder.setIcon(context.getResources().getDrawable(R.drawable.ic_info_outline_black_24dp));
        builder.setMessage("We are currently Not Operation in this Location. We will contact you when we are Operational.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Pref.putPART(mCon, pd_code);
                Objs.a.showToast(mCon, "Welcome to Loanwiser, you have successfully logged in");
                Pref.putUID(mCon, no_bundle);
                Pref.putMobile(mCon, S_moblie);
                Pref.putName(mCon, S_name);
                Pref.putID(mCon, u_array);
                Objs.ac.StartActivity(mCon, Statues_Dashboard_Nav.class);
                finish();
            }
        });
        //  builder.setPositiveButton("Yes", null);
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        Objs.a.DialogStyle(context, alert);
    }

    private void setTimer() {
        int time = 1;
        totalTimeCountInMilliseconds = 120 * time * 1000;
        timeBlinkInMilliseconds = 30 * 1000;
    }
    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
                    textViewShowTime.setTextAppearance(getApplicationContext(), R.style.blinkText);
                    Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
                    textViewShowTime.setTypeface(font);
                    textViewShowTime.setTextSize(16);
                    if (blink) {
                        textViewShowTime.setVisibility(View.VISIBLE);
                        // if blink is true, textview will be visible
                    } else {
                        textViewShowTime.setVisibility(View.INVISIBLE);
                    }
                    blink = !blink; // toggle the value of blink
                }
                textViewShowTime.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));
                // format the textview to show the easily readable format
            }
            @Override
            public void onFinish() {
                // this function will be called when the timecount is finished
                textViewShowTime.setText("Time up!");
                textViewShowTime.setVisibility(View.VISIBLE);
            }
        }.start();
    }

}