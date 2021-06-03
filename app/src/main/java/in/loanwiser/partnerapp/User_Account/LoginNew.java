package in.loanwiser.partnerapp.User_Account;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.OTP.SmsActivity2;
import in.loanwiser.partnerapp.R;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;


public class LoginNew extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    public static final String TAG = LoginNew.class.getSimpleName();
    private GoogleApiClient mCredentialsApiClient;
    private static final int RC_HINT = 1000;
    //  protected PhoneNumberUi ui;
    private AwesomeValidation awesomeValidation;
    private android.app.AlertDialog progressDialog;
    private FocusControl phoneFocus;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private TextView title;
    private AppCompatEditText phoneField;
    private AppCompatButton submit;
    private String Moblie;
    private Context mCon = this;
    private FloatingActionButton fab;
    private final String prefKey = "checkedInstallReferrer";
    String personId,utmSource,from_campaign;
    private final Executor backgroundExecutor = Executors.newSingleThreadExecutor();
    public static final String KEY_UTM_SOURCE = "utm_source";
    public static final String KEY_UTM_CONTENT = "utm_content";
    public static final String KEY_UTM_CAMPAIGN = "utm_campaign";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        phoneField = (AppCompatEditText) findViewById(R.id.mob);
        submit = (AppCompatButton)findViewById(R.id.appCompatButtonGen_OTP);
        phoneFocus = new FocusControl(phoneField);

        submit.setOnClickListener(this);
        phoneField.setOnClickListener(this);
        setSubmitEnabled(true);
        checkInstallReferrer();
        mCredentialsApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        Checking();
        //    requestHint();

//       AppSignatureHelper signatureHelper = new AppSignatureHelper(LoginNew.this);
//
//       String appSignatures = String.valueOf(signatureHelper.getAppSignatures());
//       Log.e("AppSign", appSignatures);
        fonts();
        smsreciver();
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

    void checkInstallReferrer() {
        Log.e("invockedInstallRef","checkInstallReferrer");
        if (getPreferences(MODE_PRIVATE).getBoolean(prefKey, false)) {
            return;
        }

        InstallReferrerClient referrerClient = InstallReferrerClient.newBuilder(this).build();
        backgroundExecutor.execute(() -> getInstallReferrerFromClient(referrerClient));
    }

    void getInstallReferrerFromClient(InstallReferrerClient referrerClient) {

        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        ReferrerDetails response = null;
                        try {
                            response = referrerClient.getInstallReferrer();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            return;
                        }
                        final String referrerUrl = response.getInstallReferrer();

                        if (referrerUrl != null && !referrerUrl.equals("")) {
                            // Utils.log("Referral Received - " + referrer);
                            String[] referrerParts = referrerUrl.split("&");
                            utmSource = getData(KEY_UTM_SOURCE, referrerParts);
                            String utmContent = getData(KEY_UTM_CONTENT, referrerParts);
                            String utmCampaign = getData(KEY_UTM_CAMPAIGN, referrerParts);
                            from_campaign = "1";
                            if (utmSource != null && utmSource.equals("google")) {
                                //  sendLogToMobisocServer(context, utmContent);

                                Log.e("the utm",utmContent);
                            } else if (utmSource != null && utmSource.equals("app_share")) {
                                // RawStorageProvider.getInstance(context).dumpDataToStorage(RaghuKakaConstants.REFFERAL_FOR, utmContent);
                            }
                            // updateRKServerForReferral(context, utmSource, utmCampaign, utmContent);
                        }else
                        {
                            from_campaign = "0";
                        }

                        //   trackInstallReferrerforGTM(referrerUrl);
                        Log.e("the referat url ",referrerUrl);

                        // TODO: If you're using GTM, call trackInstallReferrerforGTM instead.
                        //  trackInstallReferrer(referrerUrl);


                        // Only check this once.
                        getPreferences(MODE_PRIVATE).edit().putBoolean(prefKey, true).commit();

                        // End the connection
                        referrerClient.endConnection();

                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        // API not available on the current Play Store app.
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        // Connection couldn't be established.
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {

            }
        });
    }

    // Tracker for Classic GA (call this if you are using Classic GA only)
    private void trackInstallReferrer(final String referrerUrl) {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                CampaignTrackingReceiver receiver = new CampaignTrackingReceiver();
                Intent intent = new Intent("com.android.vending.INSTALL_REFERRER");
                intent.putExtra("referrer", referrerUrl);
                receiver.onReceive(getApplicationContext(), intent);
            }
        });
    }

    // Tracker for GTM + Classic GA (call this if you are using GTM + Classic GA only)
    private void trackInstallReferrerforGTM(final String referrerUrl) {
        Log.e("referat url1 ",referrerUrl);
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                InstallReferrerReceiver receiver = new InstallReferrerReceiver();
                Intent intent = new Intent("com.android.vending.INSTALL_REFERRER");
                intent.putExtra("referrer", referrerUrl);
                receiver.onReceive(getApplicationContext(), intent);
            }
        });
    }
    private String getData(String key, String[] allData) {
        for (String selected : allData)
            if (selected.contains(key)) {
                return selected.split("=")[1];
            }
        return "";
    }
    public void Checking(){
        if(checkPermission()== true) {
        }else{
            requestPermission();
        }
    }
    private void fonts() {
        Objs.a.OutfitNormalFontStyle(mCon, R.id.pls_enter);
        Objs.a.OutfitEditTextStyle(mCon, phoneField);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.pls_enter);

    }


    @Override
    public void onBackPressed() {
        Objs.ac.StartActivity(mCon, Welcome_Page.class);
        finish();
        super.onBackPressed();
    }

    // Construct a request for phone numbers and show the picker
    private void showHint() {
        clearKeyboard();
        HintRequest hintRequest = new HintRequest.Builder()
                .setHintPickerConfig(new CredentialPickerConfig.Builder()
                        .setShowCancelButton(true)
                        .build())
                .setPhoneNumberIdentifierSupported(true)
                .build();

        PendingIntent intent =
                Auth.CredentialsApi.getHintPickerIntent(mCredentialsApiClient, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(), RC_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            Log.e(TAG, "Could not start hint picker Intent", e);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "Connected" + bundle);
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "GoogleApiClient is suspended with cause code: " + cause);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "GoogleApiClient failed to connect: " + connectionResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_HINT) {
            if (resultCode == RESULT_OK) {
                Credential cred = data.getParcelableExtra(Credential.EXTRA_KEY);
                setPhoneNumber(cred.getId());
                // Log.e("Credential",  cred.getId());
            } else {
                focusPhoneNumber();
            }

        }
    }
    private boolean checkPermission() {
        // int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        //int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), RECEIVE_SMS);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return  result1 == PackageManager.PERMISSION_GRANTED
                && result3 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA,
                        READ_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    //  boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    //      boolean smsAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean externalAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && externalAccepted)
                        Snackbar.make(findViewById(R.id.fab), "Permission Granted", Snackbar.LENGTH_LONG).show();
                    else {

                        Snackbar.make(findViewById(R.id.fab), "Permission Denied, You cannot access SMS and camera.", Snackbar.LENGTH_LONG).show();

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
    public void onClick(View view) {
        if (view.equals(submit)) {
            doSubmit(getPhoneNumber());
        }
        if (view.equals(phoneField)) {
            phoneField.setEnabled(true);
            phoneField.requestFocus();
            if (TextUtils.isEmpty(getPhoneNumber())) {
                showHint();
            }
        }
    }


//^([8-9]{1})([0-9]{7})

    protected void doSubmit(String phoneValue) {
        if(phoneValue.contains("+91")){
            String a = phoneValue.replace("+91", "");
            Moblie = a;
            int mob_leng = Moblie.length();
            if (Moblie.length() < 10 || Moblie.length() > 10) {
                phoneField.setError(getText(R.string.error_empty_mobile));
                phoneField.requestFocus();

            }else {

                if (isValid(Moblie)) {
                    //   Objs.a.showToast(mCon,"Vaild Number");
                    Login_POST(a);
                } else {
                    //  System.out.println("Invalid Number");
                    Objs.a.showToast(mCon, "Invalid Number");
                }
                Log.e("Condtion",a);
              //  Login_POST(a);
            }
        }else {
            Moblie = phoneValue;
            if (Moblie.length() < 10 || Moblie.length() > 10) {
                phoneField.setError(getText(R.string.error_empty_mobile));
                phoneField.requestFocus();
            }else {

                if (isValid(Moblie)) {
                    //System.out.println("Valid Number");
                  //  Objs.a.showToast(mCon, "Vaild Number");
                    Login_POST(phoneValue);
                }else {
                    // System.out.println("Invalid Number");
                    Objs.a.showToast(mCon, "Invalid Number");
                }
               // Log.e("phoneValue",phoneValue);
             //   Login_POST(phoneValue);
            }
        }

    }

    public static boolean isValid(String s) {
        Pattern p = Pattern.compile("^([6-7-8-9]{1})([0-9]{9})");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }


    /*  private boolean validateMobile() {
        if (phoneField.length() < 10 || phoneField.length() > 10) {
            phoneField.setError(getText(R.string.error_empty_mobile));
            phoneField.requestFocus();
            return false;
        } else if (phoneField.length() < 13 || phoneField.length() > 13) {
            phoneField.setError(getText(R.string.error_empty_mobile));
            phoneField.requestFocus();
            return false;
        }else {

        }
        return true;
    }*/
    String getPhoneNumber() {
        return phoneField.getText().toString();
    }

    void setPhoneNumber(String phoneNumber) {
        phoneField.setText(phoneNumber);
    }

    void focusPhoneNumber() {
        phoneFocus.showKeyboard();
    }

    void clearKeyboard() {
        phoneFocus.hideKeyboard();
    }

    void setSubmitEnabled(boolean enabled) {
        submit.setEnabled(enabled);
    }




    class FocusControl {
        static final int POST_DELAY = 250;
        private Handler handler;
        private InputMethodManager manager;
        private View focus;

        /**
         * Keyboard focus controller
         *
         * Shows and hides the keyboard. Uses a runnable to do the showing as there are race
         * conditions with hiding the keyboard that this solves.
         *
         * @param focus The view element to focus and hide the keyboard from
         */
        public FocusControl(View focus) {
            handler = new Handler();
            manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            this.focus = focus;
        }

        /**
         * Focus the view and show the keyboard.
         */
        public void showKeyboard() {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    focus.requestFocus();
                    manager.showSoftInput(focus, InputMethodManager.SHOW_IMPLICIT);
                }
            }, POST_DELAY);
        }

        /**
         * Hide the keyboard.
         */
        public void hideKeyboard() {
            View currentView = getCurrentFocus();
            if (currentView.equals(focus)) {
                manager.hideSoftInputFromWindow(currentView.getWindowToken(), 0);
            }
        }
    }

    private void Login_POST(String no) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.mobile_no,no);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data  = String.valueOf(J);
        Log.d("Request :", data);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.BUSINESS_login_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        String JO_data  = String.valueOf(response);
                        Log.d("Request :", JO_data.toString());
                        try {
                            if (response.getString(Params.status).equals(Params.ok)){
                                String otp_new =  response.getString(Params.otp);

                                Toast.makeText(mCon,"OTP will be sent to the mobile number",Toast.LENGTH_SHORT).show();
                               // Objs.a.showToast(mCon,"OTP will be sent to the mobile number");
                                Objs.ac.StartActivityPutExtra(mCon, SmsActivity2.class, Params.otp,otp_new,
                                        Params.mobile_no,Moblie,Params.from_campaign,from_campaign,Params.utmSource,utmSource);
                                finish();

                            }
                            if (response.getString(Params.status).equals(Params.error)){
                              //  Objs.a.showToast(mCon,"Mobile number is not registered. Please register it");
                                Toast.makeText(mCon,"Mobile number is not registered. Please register it",Toast.LENGTH_SHORT).show();
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

}
