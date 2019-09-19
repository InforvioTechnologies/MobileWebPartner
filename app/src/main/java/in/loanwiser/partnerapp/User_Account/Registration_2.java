package in.loanwiser.partnerapp.User_Account;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import com.google.android.material.snackbar.Snackbar;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.jaredrummler.materialspinner.MaterialSpinner;

import in.loanwiser.partnerapp.OTP.SmsActivity;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.RECEIVE_SMS;

public class Registration_2 extends SimpleActivity {

    private Context mCon = this;
    private AppCompatEditText door,buliding,street,pincode,city,state;
    private AppCompatEditText editTextName,editTextMobile;
    private AwesomeValidation awesomeValidation;
    private AppCompatButton appCompatButtonGen_OTP;
    InputMethodManager imm;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Registration_2.class.getSimpleName();
    String[] SPINNERLIST;
    String[] SPINNERLIST1;
    String[] TYPE;
    @NotEmpty(messageId = R.string.city_type)
    private MaterialSpinner spinner_city;
    @NotEmpty(messageId = R.string.state_type)
    private MaterialSpinner spinner_state;
    private AppCompatTextView txt_bussiness,state_you,city_you;
    private MaterialSpinner spinner_business_type;
    private String S_moblie,S_name,S_door,S_buliding,S_street,S_pincode,S_city,S_city_name,S_state,S_business_type;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private android.app.AlertDialog progressDialog;
    private String Sdatanew;
    private int offSet = 1;
    MySpinnerAdapter property;
    MaterialEditText name,mob,pincode1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_registration);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_registration);
        initTools(R.string.app_sign);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        initCode();
       // Checking();
        Select_Bussiness_Type();
        Select_State();

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
        // txt_bussiness,state_you,city_you;
        txt_bussiness = (AppCompatTextView) findViewById(R.id.txt_bussiness);
        state_you = (AppCompatTextView) findViewById(R.id.state_you);
        city_you = (AppCompatTextView) findViewById(R.id.city_you);

        city_you = (AppCompatTextView) findViewById(R.id.city_you);

        name = (MaterialEditText) findViewById(R.id.name);
        mob = (MaterialEditText) findViewById(R.id.mob);
        pincode1 = (MaterialEditText) findViewById(R.id.pincode);

        editTextName = (AppCompatEditText) findViewById(R.id.name);
        editTextMobile = (AppCompatEditText) findViewById(R.id.mob);

        spinner_city = (MaterialSpinner) findViewById(R.id.spinner_city);
        spinner_state = (MaterialSpinner) findViewById(R.id.spinner_state);
        pincode = (AppCompatEditText)findViewById(R.id.pincode);
        spinner_business_type =(MaterialSpinner) findViewById(R.id.spinner_business_type);
        addValidationToViews();
    }

    private void fonts() {
        //  Objs.a.EditTextStyle(mCon, editTextName);
        //  Objs.a.EditTextStyle(mCon, editTextMobile);
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
        awesomeValidation.addValidation(this, R.id.mob, "^[0-9]{10}$", R.string.error_empty_mobile);
        awesomeValidation.addValidation(this, R.id.name, "^[0-9a-zA-Z\\s\\r\\n@!#\\$\\^%&*()+=\\-\\[\\]\\\\\\';,\\.\\/\\{\\}\\|\\\":<>\\?]+$", R.string.error_field_required);
    }

    /*private void Adapter_function(){

         property = new MySpinnerAdapter(this, R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.Type)));
        spinner_business_type.setAdapter(property);

        spinner_business_type.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(editTextMobile.getWindowToken(), 0);
                return false;
            }
        });
    }*/

    private void clicks() {
        findViewById(R.id.appCompatButtonGen_OTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitForm();
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
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            //  SPINNERLIST1[0]="Select your City";
            TYPE[i] = J.getString("value");
        }


        spinner_business_type.setItems(TYPE);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
        spinner_business_type.setTypeface(font);
        spinner_business_type.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                try {
                    S_business_type = ja.getJSONObject(position).getString("value");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        spinner_business_type.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(editTextMobile.getWindowToken(), 0);
                return false;
            }
        });
    }


    private void Select_State() {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_STATE_POST, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            JSONArray ja = object.getJSONArray("state");
                            setState_Spinner(ja);
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

    private void setState_Spinner(final JSONArray ja) throws JSONException {
        SPINNERLIST1 = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            //  SPINNERLIST1[0]="Select your City";
            SPINNERLIST1[i] = J.getString("state_name");
        }


        spinner_state.setItems(SPINNERLIST1);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
        spinner_state.setTypeface(font);
        spinner_state.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                try {
                    S_state = ja.getJSONObject(position).getString("state_id");
                    Select_City(S_state);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        spinner_state.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(editTextMobile.getWindowToken(), 0);
                return false;
            }
        });
    }




    private void Select_City(String id) {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_CITY_POST+id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        try {


                            JSONObject jobj = object.getJSONObject(Params.city);

                            JSONArray ja = jobj.getJSONArray("district_arr");
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

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void setMainSpinner(final JSONArray ja) throws JSONException {
        SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[0]="Select your City";
            SPINNERLIST[i] = J.getString("district_name");
        }


        spinner_city.setItems(SPINNERLIST);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
        spinner_city.setTypeface(font);
        spinner_city.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

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
        spinner_city.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(editTextMobile.getWindowToken(), 0);
                return false;
            }
        });
    }


    private void submitForm() {
        if (awesomeValidation.validate()) {

            //  if (S_business_type.equals("Select Business Type")) {
            if (S_business_type == null) {
                Toast.makeText(Registration_2.this, "Select your Business Type", Toast.LENGTH_SHORT).show();
            }else {

                S_name =  name.getText().toString();
                S_moblie =  mob.getText().toString();
                //   S_pincode =  pincode1.getText().toString();

                 /*  String all = S_moblie +"\n"+S_name +"\n"+S_door +"\n"+S_buliding +"\n"+S_street
                           +"\n"+ S_pincode +"\n"+ S_city +"\n"+ S_state +"\n"+ S_business_type; */

                if ( S_state!="0" && S_state != null && !S_state.isEmpty() && !S_state.equals("null")&&
                        S_city!="0" && S_city != null && !S_city.isEmpty() && !S_city.equals("null")){

                    String all = S_moblie +"\n"+S_name +"\n"+ S_pincode +"\n"+ S_city
                            +"\n"+ S_business_type +"\n"+ S_state;
                    // Objs.a.showToast(mCon, all );

                    //   Log.d("Regis button", all);

                    reg_POST();
                    //ExitAlert(mCon);
                }else {
                    Toast.makeText(Registration_2.this, "Select your State and City", Toast.LENGTH_SHORT).show();
                }
                //

            }
        }
    }

    public void ExitAlert(Context context) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setTitle("'Are you sure this number is valid ?");
        builder.setMessage(S_moblie);
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //  Objs.a.showToast(mCon, "Sending request for server" );
                Log.d("Regis button", "ExitAlert");

                // main_function();
                reg_POST();
            }
        });
        android.app.AlertDialog alert = builder.create();
        alert.show();
        DialogStyle(context, alert);
    }

    public void main_function(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                reg_POST();
            }
        }, 3000);
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


    private void reg_POST() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.contact_person, S_name);
            J.put(Params.mobile_no,S_moblie);
            J.put(Params.comp_building_no, "");
            J.put(Params.comp_building_name, "");
            J.put(Params.comp_building_street, "");
            J.put(Params.state_id, S_state);
            J.put(Params.district_id, S_city);
            //   J.put(Params.pincode, S_pincode);
            J.put(Params.business_type, S_business_type);
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

                        try {
                            if(response.getString(Params.status).equals(Params.ok)){

                                //  Objs.a.showToast(mCon,"Registered");
                                String otp_new = response.getString(Params.otp);

                                Objs.a.showToast(mCon,"OTP will be sent to the mobile number");
                                JSONObject jsonObject1 = response.getJSONObject(Params.inputs);
                                String JSON = String.valueOf(jsonObject1);
                                Objs.ac.StartActivityPutExtra(mCon, SmsActivity.class,Params.otp,otp_new
                                        ,Params.mobile_no,S_moblie,Params.JSON,JSON);
                                finish();
                            }

                            if(response.getString(Params.status).equals(Params.error)){
                                Objs.a.showToast(mCon,"Mobile number is already registered. Please login");
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

        Objs.ac.StartActivity(mCon,Welcome_Page.class);
        finish();
        super.onBackPressed();
    }
}

