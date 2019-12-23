package in.loanwiser.partnerapp.PartnerActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
import in.loanwiser.partnerapp.NumberTextWatcher;
import in.loanwiser.partnerapp.R;

public class Add_Home_loan extends Fragment {

    public static final String TITLE = "Basic Info";
    InputMethodManager imm;
    private AlertDialog progressDialog;

    ViewPager viewPager;
    private MaterialEditText email_id,name,mob,pincode,loanamount;
    private AppCompatTextView txt_bussiness,txt_bussiness1;
    private AppCompatTextView available_state1,available_state;
    private AppCompatButton appCompatButtonAdd_Leads,go_leads;
    private String TAG = Add_Home_loan.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String S_email_id,S_name,S_mob,S_address,S_city,S_pincode,S_loanamount,Name,Mobile;
    String user_id,applicant_id,sub_taskid,transaction_id,S_state;
    Typeface font;
    ArrayAdapter<String> state_profile;
    ArrayAdapter<String> city_profile;
    String result;
    public static final String PREFS_NAME = "MyApp_Settings";
    // public static final String TITLE = "Register Lead";
    String[] SPINNERLIST1;
    String[] SPINNERLIST;
    LinearLayout available,st1;

    Spinner SP_work_pincode_area;
    AutoCompleteTextView W_locationpincode;
    String[] Pincode,Area;
    ArrayAdapter<String>  A_Pincode,A_Area;
    String work_pincode_area;
    String work_pincode_district_id,work_pincode_state_id;

    public static Add_Home_loan newInstance() {

        return new Add_Home_loan();
    }

    Spinner address,address1;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_add__home_loan, container, false);

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        font = Typeface.createFromAsset(getActivity().getAssets(), "Lato-Regular.ttf");
        progressDialog = new SpotsDialog(getActivity(), R.style.Custom);
        viewPager = (ViewPager) getActivity().findViewById(R.id.pager);

        initCode(rootView);
        return rootView;
    }
    @SuppressLint("LongLogTag")
    private void initCode(View view){
        initUI(view);
        clicks(view);
        fonts();
        Select_State();
        edite();
        //   Mobile = Pref.getmobile(getContext());
        //  Log.d("OTP number11111111111111", Mobile);
        // Select_City(S_state);
    }

    private void Select_State() {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.GET_STATE_POST, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        //   Objs.a.showToast(getContext(), String.valueOf(object));
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
            SPINNERLIST1[i] = J.getString("state_name");

        final List<String> Profile_List = new ArrayList<>(Arrays.asList(SPINNERLIST1));
        //BUYER
        state_profile = new ArrayAdapter<String>(getActivity(), R.layout.view_spinner_item,Profile_List){
            public View getView(int position, View convertView, ViewGroup parent) {
                font = Typeface.createFromAsset(getActivity().getAssets(),"Lato-Regular.ttf");
                TextView v = (TextView) super.getView(position, convertView, parent);
                v.setTypeface(font);

                return v;
            }

            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView v = (TextView) super.getView(position, convertView, parent);
                v.setTypeface(font);
                return v;
            }
        };

        state_profile.setDropDownViewResource(R.layout.view_spinner_item);
        address.setAdapter(state_profile);
        address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    S_state = ja.getJSONObject(position).getString("state_id");
                    Select_City(S_state);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(pincode.getWindowToken(), 0);
                return false;
            }
        });
    }
/*
        address.setItems(SPINNERLIST1);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Lato-Regular.ttf");
        address.setTypeface(font);
        address.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                try {
                    S_state = ja.getJSONObject(position).getString("state_id");

                    Log.d("Add_Home_loan1111", S_state);
                    Select_City(S_state);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                imm.hideSoftInputFromWindow(mob.getWindowToken(), 0);
                return false;
            }
        });*/





    }

    private void Select_City(String id) {

        Log.d("Add_Home_loan1111", id);
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
           // SPINNERLIST[0]="Select your City";
            SPINNERLIST[i] = J.getString("district_name");
          //  SPINNERLIST[i] = J.getString("district_name");
            final List<String> city_buyer_list = new ArrayList<>(Arrays.asList(SPINNERLIST));
            city_profile = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.view_spinner_item, city_buyer_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(getActivity().getAssets(),"Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            city_profile.setDropDownViewResource(R.layout.view_spinner_item);
            address1.setAdapter(city_profile);
            address1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                      //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        S_city = ja.getJSONObject(position).getString("district_id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            address1.setOnTouchListener(new View.OnTouchListener() {
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


    private void initUI(View view) {

        appCompatButtonAdd_Leads =(AppCompatButton)view.findViewById(R.id.appCompatButtonAdd_Leads);
        go_leads =(AppCompatButton)view.findViewById(R.id.go_leads);
        // email_id = (AppCompatEditText) view.findViewById(R.id.email);
        name = (MaterialEditText) view.findViewById(R.id.name);
        mob = (MaterialEditText) view.findViewById(R.id.mob);
        Mobile = Pref.getMobileLead(getContext());
        Name = Pref.getName(getContext());
        available = (LinearLayout) view.findViewById(R.id.available);
        st1 = (LinearLayout) view.findViewById(R.id.st1);
        name.setText(Name);
        // name.setFocusable(false);
        //name.setClickable(false);

        mob.setText(Mobile);
        mob.setFocusable(false);

        W_locationpincode =  (AutoCompleteTextView)view.findViewById(R.id.W_locationpincode);
        SP_work_pincode_area =  (Spinner) view.findViewById(R.id.SP_work_pincode_area);
        W_locationpincode.setTypeface(font);
        W_locationpincode.setTextSize(13);
        txt_bussiness = (AppCompatTextView) view.findViewById(R.id.txt_bussiness);
     //   txt_bussiness1 = (AppCompatTextView) view.findViewById(R.id.txt_bussiness1);
        available_state1 = (AppCompatTextView) view.findViewById(R.id.available_state1);
        available_state = (AppCompatTextView) view.findViewById(R.id.available_state);
        loanamount = (MaterialEditText) view.findViewById(R.id.loanamount);

        loanamount.addTextChangedListener(new NumberTextWatcher(loanamount));
        pincode = (MaterialEditText) view.findViewById(R.id.pincode);
        address = (Spinner) view.findViewById(R.id.address0);
        address1 = (Spinner) view.findViewById(R.id.address1);


        W_locationpincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String new_otp = W_locationpincode.getText().toString();
                if(new_otp.length()==2){
                    GET_Pincode(new_otp);
                }

                //  Objs.a.showToast(getContext(),new_otp);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
    private void fonts() {
       // Objs.a.EditTextStyle(getActivity(), email_id);
        Objs.a.OutfitREgEditTextStyle(getActivity(), name);
        Objs.a.OutfitREgEditTextStyle(getActivity(), mob);
        Objs.a.OutfitREgEditTextStyle(getActivity(), loanamount);
        Objs.a.OutfitREgEditTextStyle(getActivity(), pincode);
        Objs.a.NewNormalFontStyle(getActivity(),txt_bussiness);
      //  Objs.a.NewNormalFontStyle(getActivity(),txt_bussiness1);
        Objs.a.NewNormalFontStyle(getActivity(),available_state1);
        Objs.a.NewNormalFontStyle(getActivity(),available_state);
        ///  Objs.a.EditTextStyle(getActivity(), address);

    }

    private void GET_Pincode(String code) {
        // progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("pincode", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_PINCODE_POST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {
                            if (object.getString(Params.status).equals("success")) {
                                JSONArray response = object.getJSONArray("response");
                                //    Log.e("Pincode", String.valueOf(response));

                                setMain_Area(response);


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

    private void setMain_Area(final JSONArray ja) throws JSONException {


        Pincode = new String[ja.length()];


        /*for (int i =occupation.length() - 1;i >= 0; i--) {
                   occupation.remove(i);
               }*/
        for (int i=0;i<ja.length();i++) {


            JSONObject J = ja.getJSONObject(i);
            Pincode[i] = J.getString("pincode");
            final List<String> Pincode_list = new ArrayList<>(Arrays.asList(Pincode));
            HashSet<String> hashSet = new HashSet<String>();

            hashSet.addAll(Pincode_list);
            Pincode_list.clear();
            Pincode_list.addAll(hashSet);
            //ArrayList<Integer> newList = removeDuplicates(Pincode_list);
            A_Pincode = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    R.layout.view_spinner_item, Pincode_list) {
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(getActivity().getAssets(), "Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };
            W_locationpincode.setThreshold(4);
            W_locationpincode.setAdapter(A_Pincode);


        }

        W_locationpincode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String code = (String)adapterView.getItemAtPosition(i);
                //    Objs.a.showToast(getContext(),selection);
                if(code.length()==6){
                    GET_AERA_POST(code);
                }else {
                    Objs.a.showToast(getContext(),"Please Select Pin code");
                }

                 imm.hideSoftInputFromWindow(W_locationpincode.getWindowToken(), 0);
            }
        });
    }
    private void clicks(final View view1) {

        view1.findViewById(R.id.appCompatButtonAdd_Leads).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });

        view1.findViewById(R.id.go_leads).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                st1.setVisibility(View.VISIBLE);
                available.setVisibility(View.GONE);
            }
        });



    }

    private void GET_AERA_POST(String code) {
        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("pincode", code);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_AERA_POST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {

                            if (object.getString(Params.status).equals("success")) {
                                JSONArray response = object.getJSONArray("response");
                                //    Log.e("Pincode", String.valueOf(response));
                                setArea(response);
                            }

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


    private void setArea(final JSONArray ja) throws JSONException {

        Area = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            Area[i] = J.getString("area");
            final List<String> area_list = new ArrayList<>(Arrays.asList(Area));
            A_Area = new ArrayAdapter<String>(getActivity(), R.layout.view_spinner_item, area_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(getActivity().getAssets(),"Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            A_Area.setDropDownViewResource(R.layout.view_spinner_item);
            SP_work_pincode_area.setAdapter(A_Area);
            SP_work_pincode_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //   work_pincode_area = ja.getJSONObject(position).getString("id");
                        work_pincode_area = ja.getJSONObject(position).getString("id");
                        work_pincode_district_id = ja.getJSONObject(position).getString("district_id");
                        work_pincode_state_id = ja.getJSONObject(position).getString("state_id");
                        // Objs.a.showToast(getContext(),work_pincode_area);
                        String a = work_pincode_area +"   "+work_pincode_district_id +"   "+work_pincode_state_id;

                     //   Log.e("Drop Down",a);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            SP_work_pincode_area.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }
    public static boolean isValid(String s) {
        Pattern p = Pattern.compile("^([6-7-8-9]{1})([0-9]{9})");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
    private void submitForm() {

       /* if (!validateEmail()) {
            return;
        }*/
        if (!validateName()) {
            return;
        }
        /*if (!validateMobile()) {
            return;
        }*/

        if(loanamount == null)
        {
            loanamount.setError(getText(R.string.error_empty_mobile1));
            loanamount.requestFocus();
        }
        else {
            if (!validateLoanamount()) {
                return;
            }
        }
        if (!validateWork()) {
            return;
        }




        if(work_pincode_area == null)
        {
          //  Toast.makeText(getContext(), "Type pin code slowly and select pin code from Dropdown", Toast.LENGTH_SHORT).show();
       Objs.a.showToast(getContext(),"Type pin code slowly and select pin code from Dropdown");
        }
        else
        {
            registerUser();
        }

       /* // Objs.ac.StartActivity(getActivity(), DemoActivity.class);
            if ( S_state!="0" && S_state != null && !S_state.isEmpty() && !S_state.equals("null")&&
                    S_city != "0" && S_city != null && !S_city.isEmpty() && !S_city.equals("null")) {
                    registerUser();

            }else {
                Toast.makeText(getContext(), "Please select State and City", Toast.LENGTH_SHORT).show();
            }*/
    }

    private void registerUser() {

        //S_email_id,S_name,S_mob;;
        //   S_email_id = email_id.getText().toString();
        S_name = name.getText().toString();
        S_mob = mob.getText().toString();
        S_loanamount = loanamount.getText().toString();
        S_pincode = W_locationpincode.getText().toString();

        if (S_mob.length() < 10 || S_mob.length() > 10) {
            mob.setError(getText(R.string.error_empty_mobile));
            mob.requestFocus();
        }else {
            if (isValid(S_mob)) {
                Session_mobile(S_name,S_mob,S_pincode,S_loanamount);
               // Objs.a.showToast(getContext(), "Valid Number");
            } else {
                //  System.out.println("Invalid Number");
                Objs.a.showToast(getContext(), "Invalid Number");
            }

        }


        //   Session_mobile(S_email_id,S_name,S_mob);



    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

   /* private boolean validateEmail() {
        String email = email_id.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
           email_id.setError(getText(R.string.error_invalid_email));
            email_id.requestFocus();
            return false;
        } else {
          //  inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }*/

    private boolean validateMobile() {
        if (mob.length() < 10 || mob.length() > 10) {
            mob.setError(getText(R.string.error_empty_mobile));
            mob.requestFocus();
            return false;
        } else {
            //     inputLayoutNumber.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateLoanamount() {
        if (loanamount.length() < 6 || loanamount.length() > 12) {
            loanamount.setError(getText(R.string.error_empty_loan));
            loanamount.requestFocus();
            return false;
        } else {
            //     inputLayoutNumber.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validatepin() {
        if (pincode.length() < 6 ) {
            pincode.setError(getText(R.string.error_empty_mobile2));
            pincode.requestFocus();
            return false;
        } else {
            //     inputLayoutNumber.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateName(){
        if (name.getText().toString().trim().isEmpty() || name.length() < 3) {
            name.setError(getText(R.string.error_name));
            name.requestFocus();
            return false;
        } else {
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateWork() {
        if (W_locationpincode.getText().toString().trim().isEmpty()) {
            W_locationpincode.setError(getText(R.string.error_work));
            W_locationpincode.requestFocus();
            return false;
        } else {
            //     inputLayoutNumber.setErrorEnabled(false);
        }
        return true;
    }

    private void Session_mobile(String name , String mob,String s_pincode, String S_loanamount) {
        update();

        String stringNumber = S_loanamount;
        result = stringNumber.replace(",","");
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            //  J.put(Params.email_id,email);
            J.put(Params.user_name,name);
            J.put(Params.mobile_no,mob);
            J.put(Params.loan_amount,result);
            J.put(Params.state_id,work_pincode_state_id);
            J.put(Params.city_id,work_pincode_district_id);
            J.put(Params.d2c_homeloan,"0");
            J.put(Params.pincode_area, work_pincode_area);
            J.put(Params.work_location_pincode, s_pincode);
            J.put(Params.loaction_stateid, work_pincode_state_id);
            J.put(Params.loaction_districtid, work_pincode_district_id);
            J.put(Params.b2b_userid, Pref.getID(getContext()));
            J.put("loan_categoryid", Pref.getLoanType(getContext()));
            J.put("sub_categoryid", Pref.getLoanTypeSub(getContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("Add Home Laoan", String.valueOf(J));

        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.ADD_LEAD_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        Log.e("Add_Home_loan Partner", String.valueOf(response));
                        try {

                            if(response.getString(Params.status).equals("Ok")) {

                                if(response.getString(Params.state_status).equals("0")){

                                    user_id = response.getString(Params.user_id);
                                    sub_taskid = response.getString(Params.subtask_id);
                                    transaction_id = response.getString(Params.transaction_id);

                                    String all = user_id + "," + sub_taskid + "," + transaction_id + "," + result;

                                    ExitAlert(getContext(),all);
                                    Select_State();
                                    String s = "0";
                                    Select_City(s);

                                }


                               if(response.getString(Params.state_status).equals("1")) {
                                    //String user_id,applicant_id,sub_taskid,transaction_id;
                                    user_id = response.getString(Params.user_id);
                                    sub_taskid = response.getString(Params.subtask_id);
                                    transaction_id = response.getString(Params.transaction_id);

                                    String all = user_id + "," + sub_taskid + "," + transaction_id + "," + result;

                                    Log.d("all", String.valueOf(all));
                                    Pref.putALL(getActivity(), all);
                                    Pref.putCS1(getActivity(), "1");
                                    Pref.removeLoanType(getContext());
                                    //Pref.getALL(getActivity());
                                    update();
                                    viewPager.setCurrentItem(1);

                               }
                            }
                            if(response.getString(Params.status).equals("error")) {
                                Objs.a.showToast(getContext(), "Already Registered with Propwiser");
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


    public void ExitAlert(Context context, final String enter_all) {
        //http://android.support.v7.app/
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(R.string.attention));
        builder.setIcon(context.getResources().getDrawable(R.drawable.ic_info_outline_black_24dp));
        builder.setMessage("We are currently Not Operational in this State. We will contact you when we are Operational.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d("all", String.valueOf(enter_all));
                Pref.putALL(getActivity(), enter_all);
                Pref.putCS1(getActivity(), "1");
                Pref.removeLoanType(getContext());
                //Pref.getALL(getActivity());
                update();
                viewPager.setCurrentItem(1);
            }
        });
        //  builder.setPositiveButton("Yes", null);
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        Objs.a.DialogStyle(context, alert);
    }


    private void update() {
        name.setFocusable(false);
        mob.setFocusable(false);
        loanamount.setFocusable(false);
        W_locationpincode.setFocusable(false);
        SP_work_pincode_area.setEnabled(false);
        address.setFocusable(false);
        address1.setFocusable (false);
        appCompatButtonAdd_Leads.setBackgroundResource(R.drawable.capsul_button7);
        appCompatButtonAdd_Leads.setEnabled(false);
        appCompatButtonAdd_Leads.setText("Completed");
    }

    private void edite() {
        name.setFocusable(true);
        mob.setFocusable(true);
        loanamount.setFocusableInTouchMode(true);
        address.setEnabled(true);
        address1.setEnabled(true);
        appCompatButtonAdd_Leads.setEnabled(true);
    }
}
