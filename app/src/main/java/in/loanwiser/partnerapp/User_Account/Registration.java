package in.loanwiser.partnerapp.User_Account;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
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
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
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
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import in.loanwiser.partnerapp.OTP.SmsActivity;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.app.Config;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class Registration extends SimpleActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,View.OnClickListener{

    private Context mCon = this;
    private GoogleApiClient mCredentialsApiClient;
    private static final int RC_HINT = 1000;
    private AppCompatEditText door,buliding,street,pincode,city,state;
    private AppCompatEditText editTextName,phoneField;
    private AwesomeValidation awesomeValidation;
    private AppCompatButton appCompatButtonGen_OTP;
    InputMethodManager imm;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Registration.class.getSimpleName();
    String[] SPINNERLIST;
    String[] SPINNERLIST2;
    String[] TYPE;
    @NotEmpty(messageId = R.string.city_type)
    private Spinner spinner_city;
    @NotEmpty(messageId = R.string.state_type)
    private Spinner spinner_state;
    private AppCompatTextView txt_bussiness,state_you,city_you;
    private Spinner spinner_business_type;
    private String S_moblie,S_name,S_door,S_buliding,S_street,S_pincode,S_city,S_city_name,S_state,S_business_type,S_business_type1;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private android.app.AlertDialog progressDialog;
    private String Sdatanew;
    private int offSet = 1;
    MySpinnerAdapter property;
    Typeface font;
    private AppCompatButton submit;
    private String Moblie;
    AppCompatEditText name,mob,pincode1;
    private FocusControl phoneFocus;
    String State_Loc_uniqueID,City_loc_uniqueID,Bussiness_uniqueID;

    JSONArray ja= new JSONArray();
    ArrayAdapter<String> state_profile;
    ArrayAdapter<String> type_business;
    ArrayAdapter<String> city_profile;


    private static final int RC_SIGN_IN = 007;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private Button btnSignOut, btnRevokeAccess;
    private LinearLayout llProfileLayout;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    Boolean alreadyLoggedIn=false;
    String PhotoUrl;
    String email;
    String personId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_registration);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_registration);
        initTools(R.string.app_sign);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        phoneField = (AppCompatEditText) findViewById(R.id.mob);
        submit = (AppCompatButton)findViewById(R.id.appCompatButtonGen_OTP);
        phoneFocus = new FocusControl(phoneField);

        submit.setOnClickListener(this);
        phoneField.setOnClickListener(this);
        setSubmitEnabled(true);


        mCredentialsApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

       // updateUI(false, null);

        initCode();

        Checking();
        Select_Bussiness_Type();
        Select_State();



        smsreciver();
      //  displayFirebaseRegId();
    }

    private void initCode() {
        initUI();
        fonts();

        clicks();

    }

    public void Checking(){
        if(checkPermission()== true) {
        }else{
            requestPermission();
        }
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

    public static boolean isValid(String s) {
        Pattern p = Pattern.compile("^([6-7-8-9]{1})([0-9]{9})");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }


    protected void doSubmit(String phoneValue) {
        if(phoneValue.contains("+91")){
            String a =    phoneValue.replace("+91", "");
            Moblie = a;
            int mob_leng = Moblie.length();
            if (Moblie.length() < 10 || Moblie.length() > 10) {
                phoneField.setError(getText(R.string.error_empty_mobile));
                phoneField.requestFocus();
            }else {
                Log.e("Condtion",a);
             //   submitForm(Moblie);
                if (isValid(Moblie)) {
                    //   Objs.a.showToast(mCon,"Vaild Number");
                    submitForm(Moblie);
                } else {
                    //  System.out.println("Invalid Number");
                    Objs.a.showToast(mCon, "Invalid Number");
                }
            }
        }else {
            Moblie = phoneValue;
            if (Moblie.length() < 10 || Moblie.length() > 10) {
                phoneField.setError(getText(R.string.error_empty_mobile));
                phoneField.requestFocus();
            }else {
                Log.e("phoneValue",phoneValue);
               // submitForm(Moblie);
                if (isValid(Moblie)) {
                    //   Objs.a.showToast(mCon,"Vaild Number");
                    submitForm(Moblie);
                } else {
                    //  System.out.println("Invalid Number");
                    Objs.a.showToast(mCon, "Invalid Number");
                }
            }
        }
    }

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



    private void initUI() {
        // txt_bussiness,state_you,city_you;
        txt_bussiness = (AppCompatTextView) findViewById(R.id.txt_bussiness);
        state_you = (AppCompatTextView) findViewById(R.id.state_you);
        city_you = (AppCompatTextView) findViewById(R.id.city_you);

        city_you = (AppCompatTextView) findViewById(R.id.city_you);

        name = (AppCompatEditText) findViewById(R.id.name);
        mob = (AppCompatEditText) findViewById(R.id.mob);
        pincode1 = (MaterialEditText) findViewById(R.id.pincode);

        editTextName = (AppCompatEditText) findViewById(R.id.name);


        spinner_city = (Spinner) findViewById(R.id.spinner_city);
        spinner_state = (Spinner) findViewById(R.id.spinner_state);
        pincode = (AppCompatEditText)findViewById(R.id.pincode);
        spinner_business_type =(Spinner) findViewById(R.id.spinner_business_type);

        addValidationToViews();
    }

    private void fonts() {
      //  Objs.a.EditTextStyle(mCon, editTextName);
      //  Objs.a.EditTextStyle(mCon, phoneField);
      //  Objs.a.EditTextStyle(mCon, pincode);
        // txt_bussiness,state_you,city_you;
       // Objs.a.OutfitNormalFontStyle(mCon,R.id.verif);
        Objs.a.OutfitREgEditTextStyle(mCon, name);
        Objs.a.OutfitREgEditTextStyle(mCon, mob);
        Objs.a.OutfitREgEditTextStyle(mCon, pincode1);
        Objs.a.NormalFontStyle(mCon, txt_bussiness);
       Objs.a.NormalFontStyle(mCon, state_you);
       Objs.a.NormalFontStyle(mCon, city_you);
    }

    private void addValidationToViews() {
     //   awesomeValidation.addValidation(this, R.id.pincode, "^[0-9]{6}$", R.string.error_postal);
   //     awesomeValidation.addValidation(this, R.id.mob, "^[0-9]{10}$", R.string.error_empty_mobile);
        awesomeValidation.addValidation(this, R.id.name, "^[0-9a-zA-Z\\s\\r\\n@!#\\$\\^%&*()+=\\-\\[\\]\\\\\\';,\\.\\/\\{\\}\\|\\\":<>\\?]+$", R.string.error_field_required);
    }




    private void clicks() {
        findViewById(R.id.Gmail_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Objs.ac.StartActivity(mCon,AppGoogleLogin.class);
                  finish();

            }
        });


    }




    private void Select_Bussiness_Type() {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.BUSS_TYPE_POST, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            JSONArray ja = object.getJSONArray("business_type");
                            setBusinessTupe(ja);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void setBusinessTupe(final JSONArray ja) throws JSONException {
        TYPE = new String[ja.length()];
        for (int i=0;i<ja.length();i++) {
            JSONObject J = ja.getJSONObject(i);
            TYPE[i] = J.getString("value");

            final List<String> Profile_List = new ArrayList<>(Arrays.asList(TYPE));
            //BUYER
            type_business = new ArrayAdapter<String>(mCon, R.layout.view_spinner_item, Profile_List) {
                public View getView(int position, View convertView, android.view.ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            type_business.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_business_type.setAdapter(type_business);
            spinner_business_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        Bussiness_uniqueID = ja.getJSONObject(position).getString("value");
                        S_business_type = ja.getJSONObject(position).getString("id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner_business_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    imm.hideSoftInputFromWindow(pincode.getWindowToken(), 0);
                    return false;
                }
            });


        }
    }

    private void Select_State() {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_STATE_POST, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            ja = object.getJSONArray(Params.state);
                            setMainSpinner(ja);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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

    private void setMainSpinner(final JSONArray ja) throws JSONException {
        SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[i] = J.getString(Params.state_name);

            final List<String> Profile_List = new ArrayList<>(Arrays.asList(SPINNERLIST));
            //BUYER
            state_profile = new ArrayAdapter<String>(mCon, R.layout.view_spinner_item,Profile_List){
                public View getView(int position, View convertView, android.view.ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            state_profile.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_state.setAdapter(state_profile);
            spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    try {
                        State_Loc_uniqueID = ja.getJSONObject(position).getString("state_id");
                        Select_City(State_Loc_uniqueID);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner_state.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    imm.hideSoftInputFromWindow(pincode.getWindowToken(), 0);
                    return false;
                }
            });
        }
    }




    private void Select_City(String id) {

        //  Log.d("Add_Home_loan1111", id);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_CITY_POST+id, null,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject object) {
                        //  Objs.a.showToast(getContext(), String.valueOf(object));
                        Log.d("Cityvalues11111111111111111 ", String.valueOf(object));
                        try {
                            JSONObject jobj = object.getJSONObject(Params.city);

                            JSONArray ja = jobj.getJSONArray("district_arr");
                            setMainSpinner_City(ja);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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


        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }
    private void setMainSpinner_City(final JSONArray ja) throws JSONException {
     /*   SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[0]="Select your City";
            SPINNERLIST[i] = J.getString("district_name");
        }*/

        SPINNERLIST2 = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST2[i] = J.getString("district_name");
            final List<String> city_buyer_list = new ArrayList<>(Arrays.asList(SPINNERLIST2));
            city_profile = new ArrayAdapter<String>(mCon.getApplicationContext(), R.layout.view_spinner_item, city_buyer_list){
                public View getView(int position, View convertView, android.view.ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, android.view.ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            city_profile.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_city.setAdapter(city_profile);
            spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        City_loc_uniqueID = ja.getJSONObject(position).getString("district_id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_city.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }


    /*    address1.setItems(SPINNERLIST);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Lato-Regular.ttf");
        address1.setTypeface(font);
        address1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                try {
                    S_city = ja.getJSONObject(position).getString("district_id");
                    //   S_state = ja.getJSONObject(position).getString("state_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        address1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(mob.getWindowToken(), 0);
                return false;
            }
        });*/
    }



    private void submitForm(String moblie) {
        if (awesomeValidation.validate()) {

                S_name =  name.getText().toString();
                S_moblie =  moblie;
                S_state = State_Loc_uniqueID;
                S_city = City_loc_uniqueID;
                S_business_type1 = Bussiness_uniqueID;


            if(S_business_type!="0" && S_business_type != null &&
                    !S_business_type.isEmpty() && !S_business_type.equals("null") && !S_business_type.equals("0")){


                if ( S_state!="0" && S_state != null && !S_state.isEmpty() && !S_state.equals("null")&& !S_state.equals("0") &&
                        S_city!="0" && S_city != null && !S_city.isEmpty() && !S_city.equals("null") &&!S_city.equals("0")){

                    reg_POST();
                    //Objs.a.showToast(mCon,"Sucess");
                }else {
                  //  Toast.makeText(Registration.this, "Select your State and City", Toast.LENGTH_SHORT).show();
                    Objs.a.showToast(mCon,"Select your State and City");
                }
            }else{
              //  Toast.makeText(Registration.this, "Select your Business Type", Toast.LENGTH_SHORT).show();
                Objs.a.showToast(mCon,"Select your Business Type");

            }

            }

    }



    public void main_function(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                reg_POST();
            }
        }, 3000);
    }





    private void reg_POST() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.contact_person, S_name);
            J.put(Params.mobile_no,S_moblie);
           // J.put(Params.comp_building_no, "");
          //  J.put(Params.comp_building_name, "");
           // J.put(Params.comp_building_street, "");
            J.put(Params.state_id, S_state);
            J.put(Params.district_id, S_city);
         //   J.put(Params.pincode, S_pincode);
            J.put(Params.business_type, S_business_type1);
            Log.d("Request :", String.valueOf(J));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String data  = String.valueOf(J);
        Sdatanew  = offSet + "\n" + data;


        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.BUSINESS_reg_POST1, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();

                        Log.e("Response :", String.valueOf(response));



                        try {
                            if(response.getString(Params.status).equals(Params.ok)){

                                //  Objs.a.showToast(mCon,"Registered");
                                String otp_new = response.getString(Params.otp);

                             //   Objs.a.showToast(mCon,"OTP will be sent to the mobile number");
                                Toast.makeText(mCon,"OTP will be sent to the mobile number",Toast.LENGTH_SHORT).show();

                                JSONObject jsonObject1 = response.getJSONObject(Params.inputs);
                                String JSON = String.valueOf(jsonObject1);
                                Objs.ac.StartActivityPutExtra(mCon, SmsActivity.class, Params.otp,otp_new
                                        , Params.mobile_no,S_moblie, Params.JSON,JSON);
                                finish();

                            }

                            if(response.getString(Params.status).equals(Params.error)){
                                Toast.makeText(mCon,"Mobile number is already registered. Please login",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Registration.this,LoginNew.class);
                                startActivity(intent);
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
                 //   boolean smsAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean externalAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && externalAccepted)
                        Snackbar.make(findViewById(R.id.fab), "Permission Granted, Now you can access location data and camera.", Snackbar.LENGTH_LONG).show();
                    else {

                        Snackbar.make(findViewById(R.id.fab), "Permission Denied, You cannot access location data and camera.", Snackbar.LENGTH_LONG).show();

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

    private static class MySpinnerAdapter extends ArrayAdapter<String> {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),
                "AlegreyaSans-Regular.ttf");
        private MySpinnerAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Welcome_Page.class);
        finish();
        super.onBackPressed();
    }
}

