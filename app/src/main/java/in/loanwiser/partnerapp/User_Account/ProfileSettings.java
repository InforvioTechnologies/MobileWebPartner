package in.loanwiser.partnerapp.User_Account;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class ProfileSettings extends SimpleActivity {

    private Context mCon = this;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = ProfileSettings.class.getSimpleName();
    String[] SPINNERLIST,SPINNERLIST2;
    JSONArray ja= new JSONArray();
    String[] Locality;
    String State_Loc_uniqueID,City_loc_uniqueID;
    private android.app.AlertDialog progressDialog;

    private AppCompatEditText door,buliding,street,pincode,city,state,pan,bussiness_type;
    private AppCompatEditText editTextName,editTextMobile;
    private AppCompatEditText email,addess;
    private AwesomeValidation awesomeValidation;
    private AppCompatButton appCompatButtonGen_OTP;
    InputMethodManager imm;
    String[] STATE;
    String[] CITY;
    String PAN;
    String[] TYPE;
    private Spinner spinner_city;
    private Spinner spinner_state;
    private Spinner spinner_business_type;
    private String S_moblie,S_name,S_door,S_email,S_buliding,S_pan,S_street,S_pincode,S_city,
            S_city_name,S_state,S_business_type,S_business_type_ID,S_addess,S_business_type1;
    private String Sdatanew;
    ArrayAdapter<String> A_state,A_City,A_Type;
    private AppCompatImageView fab_edit_1,fab_edit_2;
    private LinearLayout Ly_spinner_edt,Ly_spinner,Ly_business_type,Ly_edit_option;
    private String searched_State,searched_City,searched_Type;
    private String get_state_id,state_name;
    private ImageButton edit_option;
    private String R_state,R_city;
    private static int SPLASH_TIME_OUT = 4000;
    private AppCompatTextView txt_bussiness,state_you,city_you,pan_note,Partner_Code;
    private FloatingActionButton fab,fab1;
    String state_id,get_state_name;
    String city_id,get_city_id,city_name,get_city_name;

    ArrayAdapter<String> state_profile;
    ArrayAdapter<String> type_business;
    ArrayAdapter<String> city_profile;
    Typeface font;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_profile_settings);
        initTools(R.string.profile);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        initCode();
    }


    private void initCode() {
        initUI();
        fonts();
        clicks();
        Account_Listings_Details();
        makeJsonObjReq();
        Select_Bussiness_Type();

        Partner_Code.setText("Your Partner Code :"+ Pref.getPART(mCon));
    }

    private void initUI() {
        txt_bussiness = (AppCompatTextView) findViewById(R.id.txt_bussiness);
        Partner_Code = (AppCompatTextView) findViewById(R.id.Partner_Code);
        state_you = (AppCompatTextView) findViewById(R.id.state_you);
        city_you = (AppCompatTextView) findViewById(R.id.city_you);
        pan_note = (AppCompatTextView) findViewById(R.id.pan_note);
        edit_option = (ImageButton) findViewById(R.id.edit_option);
       /* fab_edit_1 = (AppCompatImageView)findViewById(R.id.fab_edit_1);
        fab_edit_2 = (AppCompatImageView)findViewById(R.id.fab_edit_2);*/
        editTextName = (AppCompatEditText) findViewById(R.id.name);
        editTextMobile = (AppCompatEditText) findViewById(R.id.mob);
        addess = (AppCompatEditText) findViewById(R.id.addess);
        bussiness_type = (AppCompatEditText) findViewById(R.id.bussiness_type);
        // editTextMobile.setText(Pref.getMOB(mCon));
        email = (AppCompatEditText) findViewById(R.id.email);
        pan = (AppCompatEditText) findViewById(R.id.pan);
        appCompatButtonGen_OTP = (AppCompatButton) findViewById(R.id.appCompatButtonGen_OTP);
        state = (AppCompatEditText) findViewById(R.id.state);
        city = (AppCompatEditText) findViewById(R.id.city);
        spinner_city = (Spinner) findViewById(R.id.spinner_city);
        spinner_state = (Spinner) findViewById(R.id.spinner_state);
        pincode = (AppCompatEditText)findViewById(R.id.pincode);
        spinner_business_type =(Spinner) findViewById(R.id.spinner_business_type);
        Ly_spinner = (LinearLayout)findViewById(R.id.Ly_spinner);
        Ly_business_type = (LinearLayout)findViewById(R.id.Ly_business_type);
        Ly_spinner_edt = (LinearLayout)findViewById(R.id.Ly_spinner_edt);
        Ly_edit_option = (LinearLayout)findViewById(R.id.Ly_edit_option);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        addValidationToViews();
    }

    private void fonts() {
        Objs.a.OutfitEditTextStyle(mCon, editTextName);
        Objs.a.OutfitEditTextStyle(mCon, editTextMobile);
        Objs.a.OutfitEditTextStyle(mCon, addess);
        Objs.a.OutfitEditTextStyle(mCon, bussiness_type);
        Objs.a.OutfitEditTextStyle(mCon, email);
        Objs.a.OutfitEditTextStyle(mCon, state);
        Objs.a.OutfitEditTextStyle(mCon, city);
      //  Objs.a.EditTextStyle(mCon, pan);
         Objs.a.NormalFontStyle(mCon, txt_bussiness);
        Objs.a.NormalFontStyle(mCon, state_you);
        Objs.a.NormalFontStyle(mCon, city_you);
        Objs.a.NormalFontStyle(mCon, pan_note);
        Objs.a.NormalFontStyle(mCon, Partner_Code);
    }

    private void addValidationToViews() {
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        awesomeValidation.addValidation(this, R.id.mob, "^[0-9]{10}$", R.string.error_empty_mobile);
      //  awesomeValidation.addValidation(this, R.id.pan, "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", R.string.error_pan);
        awesomeValidation.addValidation(this, R.id.name, "^[0-9a-zA-Z\\s\\r\\n@!#\\$\\^%&*()+=\\-\\[\\]\\\\\\';,\\.\\/\\{\\}\\|\\\":<>\\?]+$", R.string.error_field_required);
        awesomeValidation.addValidation(this, R.id.addess, "^[0-9a-zA-Z\\s\\r\\n@!#\\$\\^%&*()+=\\-\\[\\]\\\\\\';,\\.\\/\\{\\}\\|\\\":<>\\?]+$", R.string.error_field_required);
    }

    private boolean validatePAN1() {
        PAN = pan.getText().toString().trim();
        Pattern pattern = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]{1}$");
        Matcher matcher = pattern .matcher(PAN);
        if (matcher.matches()) {
        }
        else
        {
            pan.setError(getText(R.string.error_pan));
            pan.requestFocus();
            return false;
        }

        return true;
    }
    private void clicks() {
        update();
        Ly_spinner_edt.setVisibility(View.VISIBLE);
        bussiness_type.setVisibility(View.VISIBLE);

        appCompatButtonGen_OTP.setVisibility(View.GONE);
        findViewById(R.id.fab).setVisibility(View.VISIBLE);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edite();
                appCompatButtonGen_OTP.setVisibility(View.VISIBLE);
                findViewById(R.id.fab).setVisibility(View.GONE);
                findViewById(R.id.fab1).setVisibility(View.VISIBLE);
                Ly_spinner_edt.setVisibility(View.GONE);
                bussiness_type.setVisibility(View.GONE);
                Ly_spinner.setVisibility(View.VISIBLE);
                Ly_edit_option.setVisibility(View.VISIBLE);
                Ly_business_type.setVisibility(View.VISIBLE);
                // makeJsonObjReq();


                if ( searched_State!="0" && searched_State != null &&
                        !searched_State.isEmpty() && !searched_State.equals("null")) {

                    int itemPosition_state = state_profile.getPosition(searched_State);
                    if (itemPosition_state == -1) {
                        String message = searched_State + " : Item not found.";
                    } else {
                        spinner_state.setSelection(itemPosition_state);
                        String message = searched_State + " : Item found and selected.";
                    }
                }

                if ( searched_Type!="0" && searched_Type != null ) {

                    int itemPosition_state1 = type_business.getPosition(searched_Type);
                    if (itemPosition_state1 == -1) {
                        String message = searched_Type + " : Item not found.";
                    } else {
                        spinner_business_type.setSelection(itemPosition_state1);
                        String message = searched_Type + " : Item found and selected.";
                    }
                }

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        if ( searched_City!="0" && searched_City != null && !searched_City.isEmpty() && !searched_City.equals("null")){
                            int itemPosition_city = city_profile.getPosition(searched_City);
                            if(itemPosition_city == -1)
                            {
                                String message1 = searched_City + " : Item not found.";
                            }
                            else
                            {
                                spinner_city.setSelection(itemPosition_city);
                                String message1 = searched_City + " : Item found and selected.";
                            }
                        }

                    }
                }, SPLASH_TIME_OUT);

            }
        });

        findViewById(R.id.fab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatButtonGen_OTP.setVisibility(View.GONE);
                findViewById(R.id.fab1).setVisibility(View.GONE);
                findViewById(R.id.fab).setVisibility(View.VISIBLE);
                update();
                Ly_spinner_edt.setVisibility(View.VISIBLE);
                bussiness_type.setVisibility(View.VISIBLE);
                Ly_spinner.setVisibility(View.GONE);
                Ly_edit_option.setVisibility(View.GONE);
                Ly_business_type.setVisibility(View.GONE);


            }
        });


        findViewById(R.id.appCompatButtonGen_OTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
        findViewById(R.id.edit_option).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitAlert(mCon);
            }
        });
    }

    public void ExitAlert(Context context) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setTitle("Are you sure you to change the number...!!");
        builder.setMessage(S_moblie);
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 //  S_moblie = editTextMobile.getText().toString();
                //Objs.a.showToast(mCon,S_moblie);
                Objs.ac.StartActivityPutExtra(mCon, MobileNoUpdated.class, Params.mobile_no,S_moblie);
                finish();

            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        Objs.a.DialogStyle(context, alert);
    }


    private void submitForm() {
        if (awesomeValidation.validate()) {

            S_name =  editTextName.getText().toString();
            S_email =  email.getText().toString();
            S_addess=  addess.getText().toString();
         //   S_pan =  pan.getText().toString();
                 /*  String all = S_moblie +"\n"+S_name +"\n"+S_door +"\n"+S_buliding +"\n"+S_street
                           +"\n"+ S_pincode +"\n"+ S_city +"\n"+ S_state +"\n"+ S_business_type; */
            S_state = State_Loc_uniqueID;
            S_city = City_loc_uniqueID;
            S_business_type_ID = S_business_type1;

            if(S_business_type!="0" && S_business_type != null &&
                    !S_business_type.isEmpty() && !S_business_type.equals("null")){
                if ( S_state!="0" && S_state != null && !S_state.isEmpty() && !S_state.equals("null")&&
                        S_city!="0" && S_city != null && !S_city.isEmpty() && !S_city.equals("null")){

              //      String all = S_name +"\n"+ S_email+"\n"+ S_addess+"\n"+S_business_type_ID +"\n"+  S_city
                 //           +"\n"+ S_state;

                     Updated_Profile(S_name,S_email,S_state,S_city,S_business_type_ID,S_addess);
                }else {
                    Toast.makeText(ProfileSettings.this, "Select your State and City", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(ProfileSettings.this, "Select your Business Type", Toast.LENGTH_SHORT).show();

            }

        }
    }


    private void update() {
        editTextName.setFocusable(false);
        editTextMobile.setFocusable(false);
        email.setFocusable(false);
        addess.setFocusable(false);
        bussiness_type.setFocusable(false);
        state.setFocusable(false);
        city.setFocusable(false);
        pan.setFocusable(false);
        addess.setFocusable(false);
        Objs.a.hideSoftKey(mCon);
    }

    private void edite() {
        editTextName.setFocusable(true);
        email.setFocusable(true);
      //  pan.setFocusable(true);
        addess.setFocusable(true);
        editTextName.setFocusableInTouchMode(true);
        email.setFocusableInTouchMode(true);
        addess.setFocusableInTouchMode(true);
    }

    private void makeJsonObjReq_State() {
        //  showProgressDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_STATE_POST, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            JSONArray  ja = object.getJSONArray(Params.state);

                            for(int i=0;i<ja.length();i++){
                                JSONObject J =  ja.getJSONObject(i);

                                state_name =  J.getString(Params.state_name);
                                get_state_id =  J.getString(Params.state_id);

                                if(get_state_id.equals(state_id)){

                                    state_name =  J.getString(Params.state_name);
                                    get_state_id =  J.getString(Params.state_id);

                                    state.setText(state_name);
                                    Select_City(state_id);
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



    private void makeJsonObjReq() {
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

        // Adding request to request queue
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
            //  SPINNERLIST[i] = J.getString("district_name");
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
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
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




    private void Updated_Profile(String name,String email,String si,String ci,String type,String address) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {

            /*   "contact_person": "Karthick",
       "mobile_no": "9003641618",
       "state_id": "28",
       "district_id": "39",
       "email_id":"sample@gmail.com",
       "office_address":"abc road",
       "business_type": "Partner / Others",
       "b2b_userid": "19131"*/
// Updated_Profile(S_name,S_email,S_state,S_city,S_business_type_ID,S_addess);
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(mCon));
            J.put(Params.contact_person, name);
            J.put(Params.email_id, email);
            J.put(Params.business_type, type);
            J.put(Params.office_address, address);
            J.put(Params.state_id, si);
            J.put(Params.district_id, ci);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PROFILE_UPDATED_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString(Params.status).equals(Params.success)) {

                                // String message = response.getString(Params.message);
                                Objs.a.showToast(mCon,"Successfully Updated");
                                finish();
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
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
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
                        S_business_type1 = ja.getJSONObject(position).getString("value");
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


    private void Account_Listings_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(mCon));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PROFILE_DETAILS_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString(Params.status).equals(Params.success)) {

                                JSONObject jobj = response.getJSONObject(Params.response);
                                Log.e("Profile Page" , String.valueOf(jobj));

                                searched_Type = jobj.getString(Params.business_typename);
                                R_state = jobj.getString(Params.state_id);
                                R_city = jobj.getString(Params.district_id);
                                editTextName.setText(jobj.getString(Params.contact_person));
                                editTextMobile.setText(jobj.getString(Params.mobile_no));
                                S_moblie = jobj.getString(Params.mobile_no);
                                email.setText(jobj.getString(Params.email_id));
                                addess.setText(jobj.getString(Params.office_address));

                                bussiness_type.setText(searched_Type);
                               // pan.setText(jobj.getString(Params.pan_no));

                                if ( R_state!="0" && R_state != null && !R_state.isEmpty() && !R_state.equals("null")){
                                 //   makeJsonObjReq_State();
                                    Select_City(R_state);
                                    searched_State = jobj.getString(Params.state_name);
                                    searched_City = jobj.getString(Params.district_name);

                                    state.setText(searched_State);
                                    city.setText(searched_City);
                                }else{
                                    Objs.a.showToast(mCon,"No Data");
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
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
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






}

