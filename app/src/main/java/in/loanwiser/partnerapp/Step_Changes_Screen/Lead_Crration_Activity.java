package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Lead_Crration_Activity extends SimpleActivity {

    AppCompatButton lead_cr_step1;
    private Spinner spinner_loan_category,spinner_loan_type;
    private Toolbar toolbar;
    private AlertDialog progressDialog;
    private Context context = this;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    JSONArray ja= new JSONArray();
    JSONArray ja1= new JSONArray();
    Typeface font;
    String[] SPINNERLIST;
    String[] SPINNERLIST_CAT;
    ArrayAdapter<String> Loantype_cat,Loantype1;
    private String App,CAT_ID;
    String Lontypename,Lontype,Loan_Cat_id;
    InputMethodManager imm;
    AppCompatEditText loan_amount_txt,name_txt,mobile_no_txt,whats_app_no;
    AppCompatTextView txt_loan_category,txt_loan_category1,loan_type,loan_type1,
                        Loan_amount,Loan_amount1,name,name1,mobile,mobile1,wt_mobile,wt_mobile11,terms_and_condition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_lead__crration_);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_lead__crration_);
        initTools(R.string.lead_creation);

        spinner_loan_category =(Spinner) findViewById(R.id.spinner_loan_category);
        spinner_loan_type =(Spinner) findViewById(R.id.spinner_loan_type);

        lead_cr_step1 = (AppCompatButton) findViewById(R.id.lead_cr_step1);

        Lontype = Pref.getLoanType(getApplicationContext());
        Lontypename = Pref.getLoanTypename(context);
        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
        progressDialog = new SpotsDialog(context, R.style.Custom);
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);



        if(Lontype.equals("2"))
        {
            lead_cr_step1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_BL.class);
                    startActivity(intent);
                    finish();
                }
            });

        }else if(Lontype.equals("3"))
        {
            lead_cr_step1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_PL.class);
                    startActivity(intent);
                    finish();
                }
            });

        }else if(Lontype.equals("1"))
        {
            lead_cr_step1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_check_HL.class);
                    startActivity(intent);
                    finish();
                }
            });

        }else
        {
            lead_cr_step1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_check_HL.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

       /* MySpinnerAdapter spinner_loan_category_ad = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.loan_catogary)));
        spinner_loan_category.setAdapter(spinner_loan_category_ad);*/

       /* MySpinnerAdapter spinner_loan_type_ad = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.loan_type)));
        spinner_loan_type.setAdapter(spinner_loan_type_ad);*/

      //  makeJsonObjReq1();
        makeJsonObjReq_loancat();
        UI_FIELDS();
        fonts();

    }

    private void UI_FIELDS()
    {

        loan_amount_txt = (AppCompatEditText) findViewById(R.id.loan_amount_txt);
        name_txt = (AppCompatEditText) findViewById(R.id.name_txt);
        mobile_no_txt = (AppCompatEditText) findViewById(R.id.mobile_no_txt);
        whats_app_no = (AppCompatEditText) findViewById(R.id.whats_app_no);

        //TextView
        txt_loan_category = (AppCompatTextView) findViewById(R.id.txt_loan_category);
        txt_loan_category1 = (AppCompatTextView) findViewById(R.id.txt_loan_category1);
        loan_type = (AppCompatTextView) findViewById(R.id.loan_type);
        loan_type1 = (AppCompatTextView) findViewById(R.id.loan_type1);
        Loan_amount = (AppCompatTextView) findViewById(R.id.Loan_amount);
        Loan_amount1 = (AppCompatTextView) findViewById(R.id.Loan_amount1);
        name = (AppCompatTextView) findViewById(R.id.name);
        name1 = (AppCompatTextView) findViewById(R.id.name1);
        mobile = (AppCompatTextView) findViewById(R.id.mobile);
        mobile1 = (AppCompatTextView) findViewById(R.id.mobile1);
        wt_mobile = (AppCompatTextView) findViewById(R.id.wt_mobile);
        wt_mobile11 = (AppCompatTextView) findViewById(R.id.wt_mobile1);
        terms_and_condition = (AppCompatTextView) findViewById(R.id.terms_and_condition);

    }
    private void fonts() {

        font = Typeface.createFromAsset(getApplicationContext().getAssets(), "Lato-Regular.ttf");
        loan_amount_txt.setTypeface(font);
        name_txt.setTypeface(font);
        mobile_no_txt.setTypeface(font);
        whats_app_no.setTypeface(font);

        txt_loan_category.setTypeface(font);
        txt_loan_category1.setTypeface(font);
        loan_type.setTypeface(font);
        loan_type1.setTypeface(font);
        Loan_amount.setTypeface(font);
        Loan_amount1.setTypeface(font);
        name.setTypeface(font);
        name1.setTypeface(font);
        mobile.setTypeface(font);
        mobile1.setTypeface(font);
        wt_mobile.setTypeface(font);
        wt_mobile11.setTypeface(font);
        terms_and_condition.setTypeface(font);

    }

   /* private static class MySpinnerAdapter extends ArrayAdapter<String> {
        // Initialise custom font, for example:
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),
                "Lato-Regular.ttf");

        // (In reality I used a manager which caches the Typeface objects)
        // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

        private MySpinnerAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        // Affects default (closed) state of the spinner
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }

        // Affects opened state of the spinner
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }
    }*/

    private void makeJsonObjReq_loancat() {
        progressDialog.show();
        JSONObject J =new JSONObject();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LOAN_CATAGORY, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Loan catgory", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));
                        try {
                            ja1 = object.getJSONArray("loancatlist_arr");
                            setMainSpinner_loancat(ja1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {

            /**
             * Passing some request headers
             * */
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

    private void setMainSpinner_loancat( final JSONArray ja1) throws JSONException {

        //   SPINNERLIST = new String[ja.length()];
        SPINNERLIST_CAT = new String[ja1.length()];
        for (int i=0;i<ja1.length();i++){
            JSONObject J =  ja1.getJSONObject(i);
            SPINNERLIST_CAT[i] = J.getString("category_type");
          //  Log.e("catgory list",SPINNERLIST_CAT.toString());
            final List<String> Loan_cat_list = new ArrayList<>(Arrays.asList(SPINNERLIST_CAT));
            Loantype_cat = new ArrayAdapter<String>(context, R.layout.view_spinner_item, Loan_cat_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
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

            Loantype_cat.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_loan_category.setAdapter(Loantype_cat);
            spinner_loan_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        Loan_Cat_id = ja1.getJSONObject(position).getString("id");
                        makeJsonObjReq1(Loan_Cat_id);
                        Log.e("Selected_cat_id", String.valueOf(Loan_Cat_id));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_loan_category.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }


    }

    private void makeJsonObjReq1(String Loan_Cat_id) {
        progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("category_id", Loan_Cat_id);
            Log.e("category_id111", String.valueOf(J));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LOAN_TYPE_POST11, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("respose L_type", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        try {
                            ja = object.getJSONArray("loantypelist_arr");
                            setMainSpinner1(ja);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {

            /**
             * Passing some request headers
             * */
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

    private void setMainSpinner1(final JSONArray ja) throws JSONException {

        //   SPINNERLIST = new String[ja.length()];
        SPINNERLIST = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            SPINNERLIST[i] = J.getString("loan_type");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(SPINNERLIST));
            Loantype1 = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
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

            Loantype1.setDropDownViewResource(R.layout.view_spinner_item);
            spinner_loan_type.setAdapter(Loantype1);
            spinner_loan_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {
                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        App = ja.getJSONObject(position).getString("id");
                        CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Add Applicant Info", String.valueOf(App));
                        int a = Integer.parseInt(App);

                      /*  switch(a) {
                            case 1:
                                appl.setVisibility(View.VISIBLE);
                                P_Location_pin.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                appl.setVisibility(View.VISIBLE);
                                P_Location_pin.setVisibility(View.VISIBLE);
                                break;
                            case 4:
                                appl.setVisibility(View.VISIBLE);
                                P_Location_pin.setVisibility(View.VISIBLE);
                                break;
                            default:
                                appl.setVisibility(View.GONE);
                                P_Location_pin.setVisibility(View.VISIBLE);
                                return;
                        }*/

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_loan_type.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

        int loantypename1 = Loantype1.getPosition(Lontypename);
        if(loantypename1 == -1)
        {
            String message = Lontypename + " : Item not found.";
            //   Objs.a.showToast(getActivity(), message);
        }
        else
        {
            spinner_loan_type.setSelection(loantypename1);
            String message = Lontypename + " : Item found and selected.";
            //  Objs.a.showToast(getActivity(), message);
        }
        /// loadSubLocations(ja.getJSONObject(0).getString("countryid"));
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
        finish();
        super.onBackPressed();
    }

}
