package in.loanwiser.partnerapp.User_Account;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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

import in.loanwiser.partnerapp.OTP.SmsActivity2;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECEIVE_SMS;

public class Login extends SimpleActivity {

    private Context mCon = this;
    private AppCompatTextView verif,pls_enter,txt_mob;
    private MaterialEditText edittext_mobile;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Login.class.getSimpleName();
    private AwesomeValidation awesomeValidation;
    private AppCompatButton appCompatButtonGen_OTP;
    InputMethodManager imm;
    private String Moblie;
    private String new_otp;
    private android.app.AlertDialog progressDialog;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private FloatingActionButton fab;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  setContentView(R.layout.activity_login);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_login);
        initTools(R.string.signin);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        edittext_mobile = (MaterialEditText) findViewById(R.id.mob);
        initCode();
     //   Checking();

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
    private void initUI() {


        verif = (AppCompatTextView) findViewById(R.id.verif);
        pls_enter = (AppCompatTextView) findViewById(R.id.pls_enter);
        //txt_mob = (AppCompatTextView) findViewById(R.id.txt_mob);

        addValidationToViews();
    }

    private void fonts() {

        // Objs.a.EditTextStyle(mCon, edittext_mobile);
        //  Objs.a.OutfitNormalFontStyle(mCon,R.id.verif);
        Objs.a.OutfitNormalFontStyle(mCon,R.id.pls_enter);

        Objs.a.OutfitREgEditTextStyle(mCon, edittext_mobile);
        Objs.a.OutfitNormalFontStyle(mCon,R.id.pls_enter);
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

    }
    private void submitForm() {
        if (awesomeValidation.validate()) {
            Moblie = edittext_mobile.getText().toString();
            Login_POST(Moblie);
            // ExitAlert(mCon);
        }
    }



    public void ExitAlert(Context context) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setTitle("Are you sure this number is valid ?");
        builder.setMessage(Moblie);
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d("Alert :", Moblie);
                Login_POST(Moblie);
            }
        });
        android.app.AlertDialog alert = builder.create();
        alert.show();
        DialogStyle(context, alert);
    }


    public void DialogStyle(Context context, android.app.AlertDialog alert) {
        Button BUTTON_NEGATIVE = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        BUTTON_NEGATIVE.setTextColor(context.getResources().getColor(adhoc.app.applibrary.R.color.colorAccent));
        BUTTON_NEGATIVE.setTransformationMethod(null);
        Typeface cancel_font = Typeface.createFromAsset(context.getAssets(), "RobotoSlab-Light.ttf");
        BUTTON_NEGATIVE.setTypeface(cancel_font);

        Button BUTTON_POSITIVE = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        BUTTON_POSITIVE.setTextColor(context.getResources().getColor(adhoc.app.applibrary.R.color.colorPrimaryDark));
        BUTTON_POSITIVE.setTransformationMethod(null);
        Typeface ok_font = Typeface.createFromAsset(context.getAssets(), "RobotoSlab-Light.ttf");
        BUTTON_POSITIVE.setTypeface(ok_font);

        /*TextView title = (TextView) alert.findViewById(adhoc.app.applibrary.R.id.alertTitle);
        title.setTextColor(context.getResources().getColor(adhoc.app.applibrary.R.color.colorPrimary));
        Typeface title_font = Typeface.createFromAsset(context.getAssets(), "RobotoSlab-Light.ttf");
        title.setTypeface(title_font);

        TextView msg = (TextView) alert.findViewById(android.R.id.message);
        msg.setTextColor(context.getResources().getColor(adhoc.app.applibrary.R.color.colorGreenDark));
        Typeface msg_font = Typeface.createFromAsset(context.getAssets(), "RobotoSlab-Light.ttf");
        msg.setTypeface(msg_font);*/
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
                        //   Objs.a.showToast(mCon,"BUSINESS_login_POST  "+"\n"+  JO_data );
                        Log.d("Response :", JO_data);
                        try {
                            if (response.getString(Params.status).equals(Params.ok)){
                                String otp_new =  response.getString(Params.otp);
                                //    Objs.a.showToast(mCon, otp_new);
                                Objs.a.showToast(mCon,"OTP will be sent to the mobile number");
                                Objs.ac.StartActivityPutExtra(mCon, SmsActivity2.class,Params.otp,otp_new,
                                        Params.mobile_no,Moblie);
                               /* Objs.ac.StartActivityPutExtra(mCon, SmsActivity2.class,
                                        Params.mobile_no,Moblie);*/
                                finish();
                            }
                            if (response.getString(Params.status).equals(Params.error)){
                                Objs.a.showToast(mCon,"Mobile number is not registered. Please register it");
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
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), RECEIVE_SMS);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return  result1 == PackageManager.PERMISSION_GRANTED
                && result2 == PackageManager.PERMISSION_GRANTED
                && result3 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA,RECEIVE_SMS,READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    //  boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean smsAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean externalAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && smsAccepted && externalAccepted)
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
                                                    requestPermissions(new String[]{ CAMERA,
                                                                    RECEIVE_SMS,READ_EXTERNAL_STORAGE},
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

        Objs.ac.StartActivity(mCon, Welcome_Page.class);
        finish();
        super.onBackPressed();

    }
}

