package in.loanwiser.partnerapp.User_Account;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.text.InputFilter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

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
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class BankDetails extends SimpleActivity {


    private Context mCon = this;
    private String TAG = BankDetails.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private AppCompatEditText ifsc,b_holder_name,b_ac_no,branch;
    private AppCompatTextView b_ac_type,pay_mode,bank_name,pan_note,provide;
    private AwesomeValidation awesomeValidation;
    private AppCompatEditText txt_bank_name,txt_bank_account,txt_bank_mode,pan;
    String bank_id,Search_bank_name;
    String B_radio_Ac_Type,B_radio_Pay,Bank_Id,B_Bank_Id;
    String S_radio_Ac_Type,S_radio_Pay;
    String B_beneficiary_name,B_acc_no,B_bank_name,B_ifsc,B_pan_no,B_bank_branch;
    String[] bank_spinner;
    @NotEmpty(messageId = R.string.validation_bank)
    private Spinner spinner_bank ;
    private RadioGroup radio_account_type;
    private RadioGroup radio_pay_mode;
    private RadioButton acc_savings,acc_current,radio_internet,radio_mbank;
    private AlertDialog progressDialog;
    ArrayAdapter<String> bank_profile;
    private AppCompatButton next;
    private FloatingActionButton fab,fab1;
    private LinearLayout Ly_txt_type_mode,Ly_edit_type_mode,spinner_bank_name;
    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_bank_details);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_bank_details);
        initTools(R.string.app_bank);
        progressDialog = new SpotsDialog(mCon, R.style.Custom);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        initCode();
    }

    private void initCode() {
        initUI();
        clicks();
        fonts();
        Account_Listings_Details();
        Banks_Names();
    }

    private void initUI() {
        // ifsc,b_holder_name,b_ac_no,bank_name,branch;

        spinner_bank = (Spinner)findViewById(R.id.spinner_bank);
        pan = (AppCompatEditText) findViewById(R.id.pan);
        ifsc = (AppCompatEditText) findViewById(R.id.ifsc);
        ifsc.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        b_holder_name = (AppCompatEditText) findViewById(R.id.b_holder_name);
        b_ac_no = (AppCompatEditText) findViewById(R.id.b_ac_no);
        bank_name = (AppCompatTextView) findViewById(R.id.bank_name);
        provide = (AppCompatTextView) findViewById(R.id.provide);
        branch = (AppCompatEditText) findViewById(R.id.branch);
        next = (AppCompatButton)findViewById(R.id.next);

        //txt_bank_name,txt_bank_account,txt_bank_mode;
        txt_bank_name = (AppCompatEditText) findViewById(R.id.txt_bank_name);
        txt_bank_account = (AppCompatEditText) findViewById(R.id.txt_bank_account);
        txt_bank_mode = (AppCompatEditText) findViewById(R.id.txt_bank_mode);

        b_ac_type = (AppCompatTextView) findViewById(R.id.b_ac_type);
        pay_mode = (AppCompatTextView) findViewById(R.id.pay_mode);

        Ly_edit_type_mode = (LinearLayout)findViewById(R.id.Ly_edit_type_mode);

        Ly_txt_type_mode = (LinearLayout)findViewById(R.id.Ly_txt_type_mode);
        spinner_bank_name = (LinearLayout)findViewById(R.id.spinner_bank_name);


        radio_account_type = (RadioGroup) findViewById(R.id.radio_account_type);
        radio_pay_mode = (RadioGroup) findViewById(R.id.radio_pay_mode);
        acc_savings = (RadioButton)findViewById(R.id.acc_savings);
        acc_current = (RadioButton)findViewById(R.id.acc_current);
        radio_internet = (RadioButton)findViewById(R.id.radio_internet);
        radio_mbank = (RadioButton)findViewById(R.id.radio_mbank);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(),  "Lato-Regular.ttf");
        acc_savings.setTypeface(font);
        acc_current.setTypeface(font);
        radio_internet.setTypeface(font);
        radio_mbank.setTypeface(font);
        provide.setTypeface(font);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);

        addValidationToViews();
    }
    private void fonts() {
        //txt_bank_name,txt_bank_account,txt_bank_mode;
        Objs.a.OutfitEditTextStyle(mCon, ifsc);
        Objs.a.OutfitEditTextStyle(mCon, b_holder_name);
        Objs.a.OutfitEditTextStyle(mCon, b_ac_no);
        Objs.a.OutfitEditTextStyle(mCon, txt_bank_name);
        Objs.a.OutfitEditTextStyle(mCon, txt_bank_account);
        Objs.a.OutfitEditTextStyle(mCon, txt_bank_mode);
        Objs.a.OutfitEditTextStyle(mCon, branch);
        Objs.a.OutfitEditTextStyle(mCon, pan);

        Objs.a.NormalFontStyle(mCon, R.id.b_ac_type);
        Objs.a.NormalFontStyle(mCon, R.id.bank_name);
        Objs.a.NormalFontStyle(mCon, R.id.pay_mode);
        Objs.a.NormalFontStyle(mCon, R.id.pan_note);
    }
    private void addValidationToViews() {
        //  awesomeValidation.addValidation(this, R.id.ifsc, "^^[A-Za-z]{4}[0-9]{6,7}$", R.string.invalid_phone);
      //  awesomeValidation.addValidation(this, R.id.ifsc, "/^[A-Za-z]{4}\\d{7}$/", R.string.invalid_ifcse);
        awesomeValidation.addValidation( this, R.id.b_holder_name, RegexTemplate.NOT_EMPTY, R.string.error_name);
      //  awesomeValidation.addValidation( this, R.id.b_ac_no, RegexTemplate.NOT_EMPTY, R.string.error_account);
        //  awesomeValidation.addValidation( this, R.id.bank_name, RegexTemplate.NOT_EMPTY, R.string.error_postal);
     //   awesomeValidation.addValidation( this, R.id.branch, RegexTemplate.NOT_EMPTY, R.string.error_branch);
      //  awesomeValidation.addValidation(this, R.id.pan, "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", R.string.error_pan);

    }

    public static boolean isValid_Account(String s) {
        Pattern p = Pattern.compile("^[0-9]{6,18}$");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }



    private void Account_Listings_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
           J.put(Params.b2b_userid, Pref.getID(mCon));
          //  J.put(Params.b2b_userid, "19264");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.BANK_DETAILS_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            Log.e("Bank Details Response" , String.valueOf(response));
                            if(response.getString(Params.status).equals(Params.success)) {

                                JSONObject jobj = response.getJSONObject(Params.response);
                                Log.d("response" , String.valueOf(jobj));

                                b_holder_name.setText(jobj.getString(Params.beneficiary_name));
                                b_ac_no.setText(jobj.getString(Params.bank_acc_no));
                               // branch.setText(jobj.getString(Params.bank_brand));
                                ifsc.setText(jobj.getString(Params.ifsc_code));
                              //  pan.setText(jobj.getString(Params.pan_no));
                                txt_bank_name.setText(jobj.getString(Params.bank_name));
                                bank_id = jobj.getString(Params.bank_id);

                                if(jobj.getString(Params.bank_name).equals("null")){
                                    edite();
                                    next.setVisibility(View.VISIBLE);
                                    findViewById(R.id.fab).setVisibility(View.GONE);
                                    findViewById(R.id.fab1).setVisibility(View.VISIBLE);
                                    txt_bank_name.setVisibility(View.GONE);
                                    spinner_bank_name.setVisibility(View.VISIBLE);
                                    if ( Search_bank_name!="0" && Search_bank_name != null &&
                                            !Search_bank_name.isEmpty() && !Search_bank_name.equals("null")) {
                                        int itemPosition_state = bank_profile.getPosition(Search_bank_name);
                                        if (itemPosition_state == -1) {
                                            String message = Search_bank_name + " : Item not found.";
                                        } else {
                                            spinner_bank.setSelection(itemPosition_state);
                                            String message = spinner_bank + " : Item found and selected.";
                                        }
                                    }

                                }else {

                                    next.setVisibility(View.GONE);

                                    findViewById(R.id.fab1).setVisibility(View.GONE);
                                    findViewById(R.id.fab).setVisibility(View.VISIBLE);
                                    update();
                                    txt_bank_name.setVisibility(View.VISIBLE);
                                    spinner_bank_name.setVisibility(View.GONE);

                                    if ( bank_id!="0" && bank_id != null && !bank_id.isEmpty()
                                            && !bank_id.equals("null")){
                                        Search_bank_name = jobj.getString(Params.bank_name);
                                    }else{

                                    }
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
    private void clicks() {

        update();
       // Ly_edit_type_mode.setVisibility(View.GONE);
       // Ly_txt_type_mode.setVisibility(View.VISIBLE);
        txt_bank_name.setVisibility(View.VISIBLE);
        next.setVisibility(View.GONE);
        spinner_bank_name.setVisibility(View.GONE);
        findViewById(R.id.fab).setVisibility(View.VISIBLE);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edite();
                next.setVisibility(View.VISIBLE);
                findViewById(R.id.fab).setVisibility(View.GONE);
                findViewById(R.id.fab1).setVisibility(View.VISIBLE);
             //   Ly_txt_type_mode.setVisibility(View.GONE);
                txt_bank_name.setVisibility(View.GONE);
               /// Ly_edit_type_mode.setVisibility(View.VISIBLE);
                spinner_bank_name.setVisibility(View.VISIBLE);
                if ( Search_bank_name!="0" && Search_bank_name != null &&
                        !Search_bank_name.isEmpty() && !Search_bank_name.equals("null")) {
                    int itemPosition_state = bank_profile.getPosition(Search_bank_name);
                    if (itemPosition_state == -1) {
                        String message = Search_bank_name + " : Item not found.";
                    } else {
                        spinner_bank.setSelection(itemPosition_state);
                        String message = spinner_bank + " : Item found and selected.";
                    }
                }
            }
        });


        findViewById(R.id.fab1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setVisibility(View.GONE);
                findViewById(R.id.fab1).setVisibility(View.GONE);
                findViewById(R.id.fab).setVisibility(View.VISIBLE);
                update();
               /// Ly_txt_type_mode.setVisibility(View.VISIBLE);
              //  Ly_edit_type_mode.setVisibility(View.GONE);
                txt_bank_name.setVisibility(View.VISIBLE);
                spinner_bank_name.setVisibility(View.GONE);

            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });


    }

    private void update() {
        b_holder_name.setFocusable(false);

        b_ac_no.setFocusable(false);
    //    pan.setFocusable(false);
        txt_bank_name.setFocusable(false);
     //   branch.setFocusable(false);
        ifsc.setFocusable(false);
        txt_bank_account.setFocusable(false);
      //  txt_bank_mode.setFocusable(false);
        Objs.a.hideSoftKey(mCon);
    }
    private void edite() {
        b_holder_name.setFocusable(true);
        b_ac_no.setFocusable(true);
     //   pan.setFocusable(true);
     //   branch.setFocusable(true);
        ifsc.setFocusable(true);
        b_holder_name.setFocusableInTouchMode(true);
        b_ac_no.setFocusableInTouchMode(true);
     //   branch.setFocusableInTouchMode(true);
        ifsc.setFocusableInTouchMode(true);
     //   pan.setFocusableInTouchMode(true);
    }

    private void submitForm() {

        if (awesomeValidation.validate()) {

            final boolean isValid = FormValidator.validate(this, new SimpleErrorPopupCallback
                    (mCon, true));
            if(isValid) {


                B_acc_no = b_ac_no.getText().toString();

                if (isValid_Account(B_acc_no)) {


                    B_beneficiary_name = b_holder_name.getText().toString();
                    B_ifsc = ifsc.getText().toString();
                    B_Bank_Id = Bank_Id;

                    if (B_Bank_Id != "0" && B_Bank_Id != null && !B_Bank_Id.isEmpty() && !B_Bank_Id.equals("null")) {
                        String all = B_beneficiary_name + "\n" + B_acc_no + "\n" + B_bank_branch + "\n" + B_ifsc + "\n" +
                                B_radio_Ac_Type + "\n" + B_radio_Pay + "\n" + B_Bank_Id;
                        Log.e("Banks Details", all);
                        Updated_Profile();

                    } else {
                        Objs.a.showToast(mCon, "Select the Bank Name");

                    }
                } else {
                    //  System.out.println("Invalid Number");
                    b_ac_no.setError(getText(R.string.error_account));
                    b_ac_no.requestFocus();
                   // Objs.a.showToast(mCon, "Invalid Account Number");
                }




            }


        }



    }


    private void Updated_Profile() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(mCon));
            J.put(Params.beneficiary_name, B_beneficiary_name);
           // J.put(Params.acc_type, B_radio_Ac_Type);
            J.put(Params.bank_acc_no, B_acc_no);
          //  J.put(Params.bank_brand, B_bank_branch);
            J.put(Params.ifsc_code, B_ifsc);
         //   J.put(Params.pan_no, B_pan_no);
         //   J.put(Params.pay_mode, B_radio_Pay);
            J.put(Params.bank_name, B_Bank_Id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.BANK_UPDATED_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString(Params.status).equals(Params.success)) {

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


    private void Banks_Names() {
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, Urls.BANK_GET, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.d("Banks", object.toString());
                        try {
                            JSONArray  ja = object.getJSONArray("bank_data");
                            setMainSpinner(ja);
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
    private void setMainSpinner(final JSONArray ja) throws JSONException {
        bank_spinner = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            /// SPINNERLIST[0]="";
            bank_spinner[i] = J.getString("bank_name");
            final List<String> Profile_List = new ArrayList<>(Arrays.asList(bank_spinner));
            //BUYER
            bank_profile = new ArrayAdapter<String>(mCon, R.layout.view_spinner_item,Profile_List){
                public View getView(int position, View convertView, android.view.ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"AlegreyaSans-Regular.ttf");
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

            bank_profile.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_bank.setAdapter(bank_profile);
            spinner_bank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        Bank_Id = ja.getJSONObject(position).getString("id");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner_bank.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    // imm.hideSoftInputFromWindow(pincode.getWindowToken(), 0);
                    return false;
                }
            });
        }
    }
}

