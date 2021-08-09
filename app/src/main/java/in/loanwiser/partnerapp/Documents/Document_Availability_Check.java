package in.loanwiser.partnerapp.Documents;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.BankStamentUpload.Upload_Activity_Bank;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.PartnerActivitys.Submitsuccess_Activity;
import in.loanwiser.partnerapp.Payment.PaymentActivity;
import in.loanwiser.partnerapp.Payment.Payment_Sucess_Screen;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklist_Fragment;

public class Document_Availability_Check extends SimpleActivity {

    private Context mCon = this;
    String doc_id,emp_state,type,applicant_name;
    private AppCompatTextView appl_id,cat_c,cat_c1;
    private CardView app_id_card;
    private ProgressDialog pDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Document_Availability_Check.class.getSimpleName();
    private AlertDialog progressDialog;
    private String usertype,property_identified;
    private String new_user_type,transaction_id,Applicant_type;
    MenuItem chat;
    JSONObject jsonobject_2;
    String emp_states;

    CardView Applicant_ly,Co_Applicant_ly,Property;
    LinearLayout Applicant_ly1,Co_Applicant_ly1,Property1,Document_,
            bank_eligible_not_met,bank_eligible_met,bank_statement_met_ly,bank_statement_not_met_ly,
            bank_eligible_not_met1;

    String Applicant_what,co_app,Applicant_what1,Applicant_what2,applicant_count,property_identify,loan_categoryid;
    AppCompatTextView In_Progress_txt,co_applicant_txt,applicant_txt,sucess_msg;
    RecyclerView imagelist1,imagelist;
    RecyclerView recycler_view_bank_eligible,recycler_view_not_bank_eligible,
            recycler_view_not_bank_eligible1;

    AppCompatButton submit_update_status;
    PopupWindow popupWindow;
    ArrayList<String> message_list;

    String bank_available,document_available,Bank_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_document_availability_check);
       // applicant_name =  Objs.a.getBundle(this, Params.applicant_name);
        String document= "Bank Eligibility Check";
     //   Log.e("doc",document);
        initTools1(document);
      //  initTools1(applicant_name);
        progressDialog = new SpotsDialog(mCon, R.style.Custom);

        Applicant_ly = (CardView) findViewById(R.id.Applicant_ly);
        Co_Applicant_ly = (CardView) findViewById(R.id.Co_Applicant_ly);
        Property = (CardView) findViewById(R.id.Property);

        Document_ = (LinearLayout) findViewById(R.id.Document_);
        Applicant_ly1 = (LinearLayout) findViewById(R.id.Applicant_ly1);
        Co_Applicant_ly1 = (LinearLayout) findViewById(R.id.Co_Applicant_ly1);
        Property1 = (LinearLayout) findViewById(R.id.Property1);

        bank_statement_met_ly = (LinearLayout) findViewById(R.id.bank_statement_met_ly);
        bank_statement_not_met_ly = (LinearLayout) findViewById(R.id.bank_statement_not_met_ly);
        bank_eligible_not_met1 = (LinearLayout) findViewById(R.id.bank_eligible_not_met1);
        bank_eligible_not_met1.setVisibility(View.GONE);
        cat_c = (AppCompatTextView) findViewById(R.id.cat_c);
        sucess_msg = (AppCompatTextView) findViewById(R.id.sucess_msg);
        cat_c1 = (AppCompatTextView) findViewById(R.id.cat_c1);


        In_Progress_txt = (AppCompatTextView) findViewById(R.id.In_Progress_txt);
        co_applicant_txt = (AppCompatTextView) findViewById(R.id.co_applicant_txt);
        applicant_txt = (AppCompatTextView) findViewById(R.id.applicant_txt);

        submit_update_status = (AppCompatButton) findViewById(R.id.submit_update_status);

        recycler_view_not_bank_eligible = (RecyclerView) findViewById(R.id.recycler_view_not_bank_eligible);
        recycler_view_bank_eligible = (RecyclerView) findViewById(R.id.recycler_view_bank_eligible);
        recycler_view_not_bank_eligible1 = (RecyclerView) findViewById(R.id.recycler_view_not_bank_eligible1);
        recycler_view_not_bank_eligible1.setVisibility(View.GONE);
       // co_app = Pref.getCoAPPAVAILABLE(getApplicationContext());
      //  property_identified = Pref.getProperty_id(getApplicationContext());
        Log.i(TAG, "onCreate:property_identified "+property_identified);
        applicant_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
        co_applicant_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
        In_Progress_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
        Log.e(TAG, "onCreate:property_identified "+property_identified);
        Log.e(TAG, "onCreate:property_identified "+property_identified);
        Log.e(TAG, "onCreate:property_identified "+property_identified);

        message_list  = new ArrayList<String>();

        Document_.setVisibility(View.GONE);
        submit_update_status.setVisibility(View.GONE);
        Account_Listings_Details();



        submit_update_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bank_Eligibility_Check();

            }
        });



        Applicant_what ="Applicant";
        Applicant_what1 ="";
        Applicant_what2 ="";


        Applicant_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Applicant_what ="Applicant";
                Applicant_what1 ="no";
                Applicant_what2 ="no";
                Log.e("Applicant_what Tabs", Applicant_what+"Works");
              //  applicant_txt.setTextColor(Color.parseColor("#bdbdbd"));
                applicant_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                co_applicant_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
                In_Progress_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
                Account_Listings_Details();
            }
        });
        Co_Applicant_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Applicant_what ="no";
                Applicant_what1 ="Co Applicant1";
                Applicant_what2 ="no";
                Log.e("Applicant_what Tabs", Applicant_what+"Works");
                applicant_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
                co_applicant_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                In_Progress_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
                Account_Listings_Details();
            }
        });
        Property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Applicant_what ="no";
                Applicant_what1 ="no";
                Applicant_what2 ="Property";
                Log.e("Applicant_what Tabs", Applicant_what+"Works");
                applicant_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
                co_applicant_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
                In_Progress_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
                Account_Listings_Details();
            }
        });
    }

    private void Account_Listings_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
          //  J.put(Params.user_id, id);
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("the Document",J.toString());
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PARTNER_STATUES_IDs, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("the appid response",response.toString());
                        try {

                            JSONObject Response = response.getJSONObject("reponse");
                            JSONObject jsonObject1 = Response.getJSONObject(Params.application_form);
                            String user_id  = Response.getString(Params.user_id);
                            String jsonStringObj  = String.valueOf(jsonObject1);
                           // JSONArray jsonArray = Response.getJSONArray(Params.emp_states);
                            JSONArray jsonArray = Response.getJSONArray("emp_states");
                            String Loan_amount = Response.getString("loan_amount");
                            transaction_id =  Response.getString("transaction_id");
                            applicant_count =  Response.getString("applicant_count");
                            property_identify =  Response.getString("property_identify");
                            loan_categoryid =  Response.getString("loan_categoryid");
                            Document_.setVisibility(View.VISIBLE);
                            submit_update_status.setVisibility(View.VISIBLE);
                            if(applicant_count.equals("1"))
                            {
                                Applicant_ly1.setVisibility(View.VISIBLE);
                                Co_Applicant_ly1.setVisibility(View.GONE);

                            }else {
                                Applicant_ly1.setVisibility(View.VISIBLE);
                                Co_Applicant_ly1.setVisibility(View.VISIBLE);
                            }
       /* if (property_identified.equalsIgnoreCase("null")){
            Property1.setVisibility(View.GONE);
        }*/

                            if (property_identify != null && property_identify.equals("1")) {

                                Property1.setVisibility(View.GONE);
                            }else {

                                Property1.setVisibility(View.GONE);
                            }
                            if (jsonArray.length()>0){
                                String jsonString  = String.valueOf(jsonArray);
                                 //  Log.e("ApplicantDetails_Single",jsonString);

                                for (int i=0;i<jsonArray.length();i++) {
                                    JSONObject J = null;
                                    try {
                                        J = jsonArray.getJSONObject(i);
                                      //  Log.e("Length Tabs", String.valueOf(jsonArray.length()));

                                        applicant_name = J.getString("applicant_name");

                                        if(Applicant_what.equals(applicant_name))
                                        {
                                         //   Log.e("Applicant_what Tabs", Applicant_what+"Works");
                                            emp_states = J.getString("emp_states");
                                            Applicant_type = J.getString("user_type");
                                            transaction_id = J.getString("transaction_id");
                                            doc_id = J.getString("transaction_id");
                                            //  checklist_name_validate(doc_ype_com,key);

                                            Pref.putAEID(mCon,Applicant_type);
                                            Pref.putDOC(mCon,doc_id);
                                            Pref.putTID(mCon,transaction_id);
                                            Pref.putEID(mCon,emp_states);
                                            Bank_details_list();
                                        }else if(Applicant_what1.equals(applicant_name))
                                        {
                                         //   Log.e("Applicant_what Tabs", Applicant_what1+"Works");
                                            emp_states = J.getString("emp_states");
                                            Applicant_type = J.getString("user_type");
                                            transaction_id = J.getString("transaction_id");
                                            doc_id = J.getString("transaction_id");
                                            //  checklist_name_validate(doc_ype_com,key);

                                            Pref.putAEID(mCon,Applicant_type);
                                            Pref.putDOC(mCon,doc_id);
                                            Pref.putTID(mCon,transaction_id);
                                            Pref.putEID(mCon,emp_states);
                                            Bank_details_list();

                                        }else if(Applicant_what2.equals(applicant_name))
                                        {
                                         //   Log.e("Applicant_what Tabs", Applicant_what2+"Works");
                                            emp_states = J.getString("emp_states");
                                            Applicant_type = J.getString("user_type");
                                            transaction_id = J.getString("transaction_id");
                                            doc_id = J.getString("transaction_id");
                                            //  checklist_name_validate(doc_ype_com,key);

                                            Pref.putAEID(mCon,Applicant_type);
                                            Pref.putDOC(mCon,doc_id);
                                            Pref.putTID(mCon,transaction_id);
                                            Pref.putEID(mCon,emp_states);
                                           // Document_Details1();

                                        }



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                   // progressDialog.dismiss();
                                }

                                //finish();
                            }else {
                                Objs.a.ShowHideNoItems(mCon,true);
                            }

                            //   JSONArray ja = response.getJSONArray(Params.emp_states);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
               progressDialog.dismiss();

                Log.e("the error",error.toString());
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void Bank_Eligibility_Check() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("b2b_userid", Pref.getID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("submit_loanwiser", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.doc_uploadsubmit, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Bank_eligible", String.valueOf(response));
                        progressDialog.dismiss();
                        try {
                            String eligible_status = response.getString("eligible_status");
                            if(eligible_status.equals("1"))
                            {
                                upload_Status();
                            }else
                            {
                                Bank_analysis_ErrorStatus();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
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
    private void Bank_analysis_ErrorStatus() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.doc_availability_analysis_error);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView bankstatement_message=(AppCompatTextView) dialog.findViewById(R.id.bankstatement_message);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();


            }
        });



        if (!dialog.isShowing()) {
            dialog.show();
        }

    }
    private void upload_Status() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.transaction_id, Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("comp_status", "3");
            J.put("subcomp_status", "2");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("submit_loanwiser", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.status_update, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Documnet_upload_Status1", String.valueOf(response));
                        //{"request":{"transaction_id":"10194"},"response":true,"status":"success"}
                        Intent intent = new Intent(Document_Availability_Check.this, PaymentActivity.class);
                        startActivity(intent);
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void Bank_details_list() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Applicant Document", String.valueOf(J));

        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Eligible_Banklist, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant_Doc Response",response.toString());
                        String data = String.valueOf(response);
                        try {
                            String status = response.getString("status");

                            if(status.equals("success"))
                            {
                                JSONObject jsonObject1 = response.getJSONObject("response");
                                String bankeligible_sts = jsonObject1.getString("bankeligible_sts");
                                String eligible_bank_cnt = jsonObject1.optString("eligible_bank_cnt");
                                String not_eligible_bank_cnt = jsonObject1.optString("not_eligible_bank_cnt");
                                String banknoteligible_sts = jsonObject1.getString("banknoteligible_sts");
                                String bankrulenoteligible_sts = jsonObject1.getString("bankrulenoteligible_sts");

                                if(bankeligible_sts.equals("0"))
                                {

                                    bank_statement_met_ly.setVisibility(View.GONE);
                                    if(banknoteligible_sts.equals("0") )
                                    {
                                        bank_statement_not_met_ly.setVisibility(View.GONE);
                                        if(bankrulenoteligible_sts.equals("1"))
                                        {
                                            bank_statement_met_ly.setVisibility(View.GONE);
                                            bank_statement_not_met_ly.setVisibility(View.GONE);
                                            bank_eligible_not_met1.setVisibility(View.VISIBLE);
                                        }

                                    }else
                                    {

                                        bank_statement_not_met_ly.setVisibility(View.VISIBLE);
                                        bank_eligible_not_met1.setVisibility(View.GONE);
                                    }
                                }else
                                {
                                    String count = "4";
                                    sucess_msg.setText("● Congrats You have  Successfully met Eligible and Doc Requirement for"+" "+eligible_bank_cnt+" "+"Lenders and Eligible and Docs need are"+" "+ not_eligible_bank_cnt +" "+"lenders.");
                                    bank_statement_met_ly.setVisibility(View.VISIBLE);
                                    bank_statement_not_met_ly.setVisibility(View.GONE);
                                    bank_eligible_not_met1.setVisibility(View.GONE);
                                }

                                if(bankeligible_sts.equals("1"))
                                {
                                    JSONArray bank_eligiblearr = jsonObject1.getJSONArray("bank_eligiblearr");
                                    if(bank_eligiblearr.length()>0)
                                    {
                                        recycler_view_bank_eligible.setVisibility(View.VISIBLE);

                                        setAdapter(bank_eligiblearr);
                                    }
                                }else{
                                    cat_c.setText("Sorry....!\n" +
                                            "You have not met the document requirement for any of the eligible and available bank near you.");
                                }
                                if(banknoteligible_sts.equals("1"))
                                {
                                    JSONArray bank_nteligiblearr = jsonObject1.getJSONArray("bank_nteligiblearr");
                                    if(bank_nteligiblearr.length()>0)
                                    {
                                        recycler_view_not_bank_eligible.setVisibility(View.VISIBLE);
                                        if(bankeligible_sts.equals("1")) {
                                            JSONArray bank_eligiblearr = jsonObject1.getJSONArray("bank_eligiblearr");

                                        }

                                        setAdapter_not_eligible(bank_nteligiblearr);

                                    }

                                }else{

                                    cat_c.setText("Sorry....!\n" +
                                            "You have not met the document requirement for any of the eligible and available bank near you.");
                                }

                                if(bankrulenoteligible_sts.equals("1"))
                                {

                                    Log.e("the value",bankrulenoteligible_sts);
                                    JSONArray bankneligible_arr = jsonObject1.getJSONArray("bankneligible_arr");
                                    Log.e("the value",bankneligible_arr.toString());
                                    recycler_view_not_bank_eligible1.setVisibility(View.VISIBLE);


                                    setAdapter_not_eligible1(bankneligible_arr);
                                }else{
                                    recycler_view_not_bank_eligible1.setVisibility(View.GONE);


                                    cat_c1.setText("Sorry....!\n" +
                                            "Available Banks - For Which \\n Eligibility Requirements Not Met");
                                }

                            }else
                            {
                                Toast.makeText(getApplicationContext(),"Something went wrong, Please check!!!", Toast.LENGTH_SHORT).show();


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
                VolleyLog.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),"Network error, Please try Again ", Toast.LENGTH_SHORT).show();
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
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }



    private void setAdapter(JSONArray ja) {
        ListItemAdapter adapter = new ListItemAdapter(mCon,ja);
        recycler_view_bank_eligible.setHasFixedSize(true);
        recycler_view_bank_eligible.setNestedScrollingEnabled(false);
        recycler_view_bank_eligible.setLayoutManager(new LinearLayoutManager(this));
        recycler_view_bank_eligible.setAdapter(adapter);
         //Objs.a.getRecyleview(this).setAdapter(adapter);
    }
    private void setAdapter_not_eligible(JSONArray ja) {
        ListItemAdapter_not adapter = new ListItemAdapter_not(mCon,ja);
        recycler_view_not_bank_eligible.setHasFixedSize(true);
        recycler_view_not_bank_eligible.setNestedScrollingEnabled(false);
        recycler_view_not_bank_eligible.setLayoutManager(new LinearLayoutManager(this));
        recycler_view_not_bank_eligible.setAdapter(adapter);
        //Objs.a.getRecyleview(this).setAdapter(adapter);
    }
    private void setAdapter_not_eligible1(JSONArray ja) {
        ListItemAdapter_not_ele adapter = new ListItemAdapter_not_ele(mCon,ja);
        recycler_view_not_bank_eligible1.setHasFixedSize(true);
        recycler_view_not_bank_eligible1.setNestedScrollingEnabled(false);
        recycler_view_not_bank_eligible1.setLayoutManager(new LinearLayoutManager(this));
        recycler_view_not_bank_eligible1.setAdapter(adapter);
        //Objs.a.getRecyleview(this).setAdapter(adapter);
    }
   /* private void setAdapter_chile(JSONArray ja) {
              ListItemAdapter_chiled adapter = new ListItemAdapter_chiled(mCon,ja);
        imagelist1.setVisibility(View.GONE);
        imagelist.setVisibility(View.VISIBLE);




        /// RecyclerView recyclerView = (RecyclerView) findViewById(adhoc.app.applibrary.R.id.recycler_view);
        // imagelist.setLayoutManager(llm);
        imagelist.setHasFixedSize(true);
        imagelist.setNestedScrollingEnabled(false);
        imagelist.setLayoutManager(new LinearLayoutManager(this));
        imagelist.setAdapter(adapter);
        //Objs.a.getRecyleview(this).setAdapter(adapter);
    }*/

    private void setAdapter1(JSONArray ja) {
        ListItemAdapter1 adapter1 = new ListItemAdapter1(mCon,ja);
        imagelist.setVisibility(View.GONE);
        imagelist1.setVisibility(View.VISIBLE);
        imagelist1.setHasFixedSize(true);
        imagelist1.setNestedScrollingEnabled(false);
        imagelist1.setLayoutManager(new LinearLayoutManager(this));
        imagelist1.setAdapter(adapter1);
        //  Objs.a.getRecyleview1(this).setAdapter(adapter1);
    }
    //Adapter Class list
    public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {

        JSONArray list = new JSONArray();
        Context mCon;
        JSONObject J;

        public ListItemAdapter(Context mCon, JSONArray list) {
            this.list = list;
            this.mCon = mCon;
        }

        @Override
        public int getItemCount() {
            return list.length();
        }

        public JSONObject getItem(int i) {
            try {
                return list.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_bank_list_elible, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);

               // holder.class_name.setText(Objs.a.capitalize(J.getString("key")));
               // Objs.a.NewNormalFontStyle(mCon,holder.class_name);

                String id= J.getString("id");
                String bank_name= J.getString("bank_name");
                String bank_logo= J.getString("bank_logo");
                String bank_logo_cc= J.getString("bank_logo_cc");
                String bank_url= J.getString("bank_url");
                String bank_category= J.getString("bank_category");
                String bank_categorystr= J.getString("bank_categorystr");
                Log.e("bank_logo",bank_logo_cc);
                Objs.a.loadPicasso(mCon,bank_url,holder.uploaded_yes,holder.progressBarMaterial);
                holder.cat_A.setText(bank_categorystr);

                if(bank_category.equals("1"))
                {
                    holder.cat_A.setBackgroundResource(R.drawable.capsul_button41);
                }else if(bank_category.equals("2"))
                {
                    holder.cat_A.setBackgroundResource(R.drawable.but_shape_reject);
                } else
                {
                    holder.cat_A.setBackgroundResource(R.drawable.capsul_button412);
                }

                holder.missing.setVisibility(View.GONE);


                holder.arrowimgup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.arrowimgup.setVisibility(View.GONE);
                        holder.arrowimgdown1.setVisibility(View.VISIBLE);
                        holder.eligible_available.setVisibility(View.GONE);
                    }
                });


                holder.arrowimgdown1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // holder.check_list_name.removeAllViews();
                        holder.arrowimgup.setVisibility(View.VISIBLE);
                        holder.arrowimgdown1.setVisibility(View.GONE);
                        holder.eligible_available.setVisibility(View.VISIBLE);
                        String typecnt1;

                        final String relationship_type=Pref.getCoAPPAVAILABLE(getApplicationContext());

                        if(relationship_type.equals("1"))
                        {
                            typecnt1="0";
                        }else
                        {
                            typecnt1="1";
                        }
                        try {
                            Bank_ID= J.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        holder.tabel_row.removeAllViews();
                        // final String step_status11 = step_status1;
                        JSONObject jsonObject = new JSONObject();
                        JSONObject J = null;
                        try {
                            J = new JSONObject();

                            J.put("bank_id", Bank_ID);
                            //  J.put("bank_id", id);
                            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
                            J.put("user_id",Pref.getUSERID(getApplicationContext()));
                            J.put("automated_underwriting","1");
                            J.put("type_cnt",typecnt1);

                           /* J.put("bank_id", 23);
                            //  J.put("bank_id", id);
                            J.put("transaction_id", "58815");
                            J.put("user_id","57205");
                            J.put("automated_underwriting","1");
                            J.put("type_cnt","0");
*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.show();
                        Log.e("bank state rule request", String.valueOf(J));

                        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.doccklist_ruleres, J,
                                new Response.Listener<JSONObject>() {
                                    @SuppressLint({"LongLogTag", "ResourceType"})
                                    @Override
                                    public void onResponse(JSONObject response) {




                                            TableRow tr_head = new TableRow(getApplicationContext());
                                            tr_head.setId(10);
                                            tr_head.setBackgroundColor(Color.GRAY);
                                            tr_head.setLayoutParams(new ViewGroup.LayoutParams(
                                                    ViewGroup.LayoutParams.FILL_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));

                                            TextView label_date = new TextView(getApplicationContext());
                                            label_date.setId(20);
                                            label_date.setText("Criteria" + "  ");
                                            label_date.setTextColor(Color.WHITE);
                                            label_date.setPadding(5, 5, 5, 5);
                                            tr_head.addView(label_date);// add the column to the table row here

                                            TextView label_weight_kg = new TextView(getApplicationContext());
                                            label_weight_kg.setId(21);// define id that must be unique
                                            label_weight_kg.setText("Status" + "  "); // set the text for the header
                                            label_weight_kg.setTextColor(Color.WHITE); // set the color
                                            label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
                                            tr_head.addView(label_weight_kg); // add the column to the table row here

                                            holder.tabel_row.addView(tr_head, new TableLayout.LayoutParams(
                                                    ViewGroup.LayoutParams.FILL_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));


                                        for(int i=0;i<2;i++)
                                        {
                                        TableRow tr = new TableRow(getApplicationContext());
                                        tr.setId(100 + 10);
                                        tr.setLayoutParams(new ViewGroup.LayoutParams(
                                                ViewGroup.LayoutParams.FILL_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT));

                                        if(i==0)
                                        {
                                            TextView rule_description = new TextView(getApplicationContext());
                                            rule_description.setId(200 + 10);
                                            rule_description.setText("CRIF-Eligibility"+ "  ");
                                            rule_description.setTextColor(Color.parseColor("#33b48d"));
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(rule_description);

                                            TextView fail_message1 = new TextView(getApplicationContext());
                                            fail_message1.setId(300 + 10);
                                            fail_message1.setText("Pass" + "  ");
                                            fail_message1.setTextColor(Color.parseColor("#33b48d"));
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(fail_message1);

                                            holder.tabel_row.addView(tr, new TableLayout.LayoutParams(
                                                    ViewGroup.LayoutParams.FILL_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));
                                        }else if(i==1)
                                        {
                                            TextView rule_description = new TextView(getApplicationContext());
                                            rule_description.setId(200 + 10);
                                            rule_description.setText("Bank-Statement Eligibility"+ "  ");
                                            rule_description.setTextColor(Color.parseColor("#33b48d"));
                                            label_weight_kg.setPadding(20, 5, 20, 5);

                                            tr.addView(rule_description);

                                            TextView fail_message1 = new TextView(getApplicationContext());
                                            fail_message1.setId(300 + 10);
                                            fail_message1.setText("Pass" + "  ");
                                            fail_message1.setTextColor(Color.parseColor("#33b48d"));
                                            label_weight_kg.setPadding(20, 5, 20, 5);
                                            tr.addView(fail_message1);

                                            holder.tabel_row.addView(tr, new TableLayout.LayoutParams(
                                                    ViewGroup.LayoutParams.FILL_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));
                                        }


                                        }

                                        // TableLayout prices = (TableLayout) holder.findViewById(R.id.prices);


                                        progressDialog.dismiss();
                                    }
                                }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Log.e("Applicant Entry request", String.valueOf(error));
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                });

               // holder.uploaded_yes.setText(bank_categorystr);

            } catch (NullPointerException e) {
                Objs.a.showToast(mCon, e.toString());
            } catch (Exception e) {
                Objs.a.showToast(mCon, e.toString());
            }
        }

        private void update(JSONArray list1) {
            list = list1;
        }


        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            AppCompatTextView cat_A,missing;
            CardView card_view_class_name;
            View view;
            ImageView image_doc,uploaded_yes;
            Button uploadbtn;
            RecyclerView recycler_view_chiled;
            ProgressBar progressBarMaterial;
            AppCompatImageView arrowimgup,arrowimgdown1;
            LinearLayout eligible_ly,eligible_available;
            TableLayout tabel_row;
            public ViewHolder(View itemView) {
                super(itemView);

                cat_A  = (AppCompatTextView) itemView.findViewById(R.id.cat_A);
                missing  = (AppCompatTextView) itemView.findViewById(R.id.missing);
                uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);
               // uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);

                card_view_class_name  = (CardView) itemView.findViewById(R.id.card_view_class_name);
                progressBarMaterial = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);
                arrowimgup = (AppCompatImageView) itemView.findViewById(R.id.arrowimgup);
                arrowimgdown1 = (AppCompatImageView) itemView.findViewById(R.id.arrowimgdown1);
                eligible_ly = (LinearLayout) itemView.findViewById(R.id.eligible_ly);
                eligible_available = (LinearLayout) itemView.findViewById(R.id.eligible_available);
                tabel_row  = (TableLayout) itemView.findViewById(R.id.tabel_row);

            }
        }
    }

    public class ListItemAdapter_not extends RecyclerView.Adapter<ListItemAdapter_not.ViewHolder> {

        JSONArray list = new JSONArray();
        Context mCon;
        JSONObject J;

        public ListItemAdapter_not(Context mCon, JSONArray list) {
            this.list = list;
            this.mCon = mCon;
        }

        @Override
        public int getItemCount() {
            return list.length();
        }

        public JSONObject getItem(int i) {
            try {
                return list.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_bank_list_elible, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);

                // holder.class_name.setText(Objs.a.capitalize(J.getString("key")));
                // Objs.a.NewNormalFontStyle(mCon,holder.class_name);

                 Bank_ID= J.getString("id");
                String bank_name= J.getString("bank_name");
                String bank_logo= J.getString("bank_logo");
                String bank_logo_cc= J.getString("bank_logo_cc");
              //  String bank_logo_cc= "https://consumer.loanwiser.in/images/paysense.png";
                Log.e("bank_logo_cc",bank_logo_cc);
                String bank_url= J.getString("bank_url");
                String bank_category= J.getString("bank_category");
                String bank_categorystr= J.getString("bank_categorystr");
                holder.done_tick.setImageDrawable(getResources().getDrawable(R.drawable.ic_caution));
                holder.cat_A.setText(bank_categorystr);


               // Objs.a.loadPicasso(mCon,bank_logo_cc,holder.uploaded_yes,holder.progressBarMaterial);
                Glide.with(mCon).load(bank_url).into(holder.uploaded_yes);


                if(bank_category.equals("1"))
                {
                    holder.cat_A.setBackgroundResource(R.drawable.capsul_button41);
                }else if(bank_category.equals("2"))
                {
                    holder.cat_A.setBackgroundResource(R.drawable.but_shape_reject);
                } else
                {
                    holder.cat_A.setBackgroundResource(R.drawable.capsul_button412);
                }
                holder.missing_document.setText("Passed in Eligibility and doc need");
                holder.missing_document.setTextColor(Color.parseColor("#EC9022"));
                holder.missing.setVisibility(View.VISIBLE);

                holder.upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Document_Availability_Check.this, Applicant_Doc_Details_revamp.class);
                        startActivity(intent);

                    }
                });

                holder.arrowimgup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.arrowimgup.setVisibility(View.GONE);
                        holder.arrowimgdown1.setVisibility(View.VISIBLE);
                        holder.check_list_name.removeAllViews();
                        holder.upload.setVisibility(View.GONE);
                    }
                });

                holder.arrowimgdown1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.arrowimgup.setVisibility(View.VISIBLE);
                        holder.arrowimgdown1.setVisibility(View.GONE);
                        holder.check_list_name.removeAllViews();
                        J = getItem(position);

                        // holder.class_name.setText(Objs.a.capitalize(J.getString("key")));
                        // Objs.a.NewNormalFontStyle(mCon,holder.class_name);

                        try {
                            Bank_ID= J.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject jsonObject =new JSONObject();
                        JSONObject J= null;
                        try {
                            J =new JSONObject();
                            // J.put("transaction_id", transaction_id);
                            //  J.put("transaction_id", "61359");
                            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
                            J.put("applicant_type", applicant_count);
                            J.put("bank_id", Bank_ID);
                            J.put("loan_category", loan_categoryid);
                            J.put("step3_uploaddet", "1");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("the value of Property", String.valueOf(J));
                        progressDialog.show();
                        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.get_partbankup, J,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        String data = String.valueOf(response);
                                        //   Objs.a.showToast(mCon, data);
                                        Log.e("the value of Property", String.valueOf(data));
                                        try {
                                            // JSONArray ja = response.getJSONArray(Params.displayname);
                                            String status = response.getString("status");
                                            if(status.equals("success"))
                                            {
                                                holder.upload.setVisibility(View.VISIBLE);
                                                JSONObject jsonObject1 = response.getJSONObject("response");

                                                //jsonobject_2 = jsonObject1.getJSONObject("document_arr");
                                                // JSONArray Property_Document = jsonobject_2.getJSONArray("Property Document");

                                                Iterator iterator = jsonObject1.keys();
                                                while (iterator.hasNext()) {

                                                    String key = (String)iterator.next();
                                                    Log.i("TAG", "keyvaluecheck: "+key);
                                                    Log.e("value", key.toString());

                                                    JSONArray  response_iD_proof_comon = jsonObject1.getJSONArray(key);
                                                    //  JSONObject req_type=doc_ar.getJSONObject("document_req");

                                                    for (int i=0;i<response_iD_proof_comon.length();i++) {
                                                        JSONObject J = null;
                                                        try {

                                                            J = response_iD_proof_comon.getJSONObject(i);
                                                            JSONArray document_arr = J.getJSONArray("document_arr");
                                                            String document_req = J.getString("document_req");

                                                            //
                                                            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                                                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                                            TextView tv = new TextView(getApplicationContext());
                                                            tv.setLayoutParams(lparams);
                                                            tv.setText(Html.fromHtml(key));
                                                            lparams.setMargins(10, 12, 10, 12);
                                                            Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "segoe_ui.ttf");
                                                            tv.setTypeface(font,Typeface.BOLD);
                                                            tv.setTextSize(14);
                                                            tv.setTextColor(Color.parseColor("#002B5D"));
                                                            holder.check_list_name.addView(tv);

                                                            String star="<font color='#D44D53'>*</font>";
                                                            LinearLayout.LayoutParams lpara = new LinearLayout.LayoutParams(
                                                                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                                            TextView tvs1 = new TextView(getApplicationContext());
                                                            tvs1.setLayoutParams(lparams);
                                                            lpara.setMargins(10, 10, 10, 10);
                                                            tvs1.setLayoutParams(lparams);
                                                            tvs1.setTextSize(14);
                                                            Typeface fonts = Typeface.createFromAsset(getApplicationContext().getAssets(), "segoe_ui.ttf");
                                                            tvs1.setTypeface(fonts);
                                                            tvs1.setText("(Mandatory)");
                                                            tvs1.setTextColor(Color.parseColor("#0CB400"));
                                                            holder.check_list_name.addView(tvs1);

                                                            TextView tvs = new TextView(getApplicationContext());
                                                            tvs.setLayoutParams(lparams);
                                                            lpara.setMargins(10, 10, 10, 10);
                                                            tvs.setLayoutParams(lparams);
                                                            tvs.setTextSize(14);
                                                            Typeface fonts1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "segoe_ui.ttf");
                                                            tvs.setTypeface(fonts1);
                                                            tvs.setText("Please Upload any one of the following");
                                                            tvs.setTextColor(Color.parseColor("#E17E29"));
                                                            holder.check_list_name.addView(tvs);
                                                            String doc_typename = null;
                                                            for (int k=0;k<document_arr.length();k++) {
                                                                JSONObject rec5 = null;
                                                                try {
                                                                    rec5 = document_arr.getJSONObject(k);
                                                                    doc_typename = rec5.getString("doc_typename");

                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                TableRow row = new TableRow(getApplicationContext());
                                                                row.setId(k);
                                                                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));


                                                                AppCompatTextView checkBox = new AppCompatTextView(getApplicationContext());
                                                                checkBox.setId(k);
                                                                Typeface fonts3 = Typeface.createFromAsset(getApplicationContext().getAssets(), "segoe_ui.ttf");
                                                                checkBox.setTypeface(fonts3);
                                                                //checkBox.setLayoutParams(lparams);
                                                                checkBox.setPadding(10, 10, 10, 10);
                                                                checkBox.setTextColor(Color.parseColor("#ACACAC"));
                                                                checkBox.setGravity(Gravity.LEFT);
                                                                checkBox.setText(doc_typename);
                                                                checkBox.setTextSize(14);
                                                                holder.check_list_name.addView(checkBox);
                                                            }
                                                            //
                                                            checklist_name(document_arr,key,document_req);

                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }


                                                    }

                                                }
                                            }else
                                            {

                                                Toast.makeText(getApplicationContext(),"Something went wrong, Please check!!!", Toast.LENGTH_SHORT).show();

                                            }
                                            progressDialog.dismiss();
                                            //  JSONArray jsonArray  = Property_Document.getJSONArray("doc_type_names");
                           /* if (Property_Document.length()>0){
                                //     Objs.a.showToast(mCon, String.valueOf(ja));


                            }else {
                                Objs.a.ShowHideNoItems(mCon,true);
                            }*/

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Log.d(TAG, error.getMessage());
                                VolleyLog.e(TAG, "Error: " + error.getMessage());
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
                        // Adding request to request queue
                        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
                    }
                });

                // holder.uploaded_yes.setText(bank_categorystr);

            } catch (NullPointerException e) {
                Objs.a.showToast(mCon, e.toString());
            } catch (Exception e) {
                Objs.a.showToast(mCon, e.toString());
            }
        }

        private void update(JSONArray list1) {
            list = list1;
        }


        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            AppCompatTextView cat_A,missing_document,missing;
            CardView card_view_class_name;
            View view;
            ImageView image_doc,uploaded_yes,done_tick;
            Button uploadbtn;
            RecyclerView recycler_view_chiled;
            ProgressBar progressBarMaterial;
            LinearLayout check_list_name;
            AppCompatImageView arrowimgup,arrowimgdown1;
            AppCompatButton upload;
            public ViewHolder(View itemView) {
                super(itemView);
                missing  = (AppCompatTextView) itemView.findViewById(R.id.missing);

                cat_A  = (AppCompatTextView) itemView.findViewById(R.id.cat_A);
                missing_document  = (AppCompatTextView) itemView.findViewById(R.id.missing_document);
                uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);
                done_tick  = (ImageView) itemView.findViewById(R.id.done_tick);
                // uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);
                progressBarMaterial = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);
                card_view_class_name  = (CardView) itemView.findViewById(R.id.card_view_class_name);
                check_list_name  = (LinearLayout)itemView.findViewById(R.id.check_list_name);
                upload  = (AppCompatButton) itemView.findViewById(R.id.upload);
                arrowimgup  = (AppCompatImageView) itemView.findViewById(R.id.arrowimgup);
                arrowimgdown1  = (AppCompatImageView) itemView.findViewById(R.id.arrowimgdown1);

            }
        }
    }
    public class ListItemAdapter_not_ele extends RecyclerView.Adapter<ListItemAdapter_not_ele.ViewHolder> {

        JSONArray list = new JSONArray();
        Context mCon;
        JSONObject J;

        public ListItemAdapter_not_ele(Context mCon, JSONArray list) {
            this.list = list;
            this.mCon = mCon;
        }

        @Override
        public int getItemCount() {
            return list.length();
        }

        public JSONObject getItem(int i) {
            try {
                return list.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public ListItemAdapter_not_ele.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_bank_list_elible_rule, parent, false);
            return new ListItemAdapter_not_ele.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ListItemAdapter_not_ele.ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);

                // holder.class_name.setText(Objs.a.capitalize(J.getString("key")));
                // Objs.a.NewNormalFontStyle(mCon,holder.class_name);

                Bank_ID= J.getString("id");
                String bank_name= J.getString("bank_name");
                String bank_logo= J.getString("bank_logo");
                //   String bank_logo_cc= J.getString("bank_logo_cc");
                String bank_url= J.getString("bank_url");
                //  String bank_logo_cc= "https://consumer.loanwiser.in/images/paysense.png";
                // Log.e("bank_logo_cc",bank_logo_cc);
                String bank_category= J.getString("bank_category");
                String bank_categorystr= J.getString("bank_categorystr");
                holder.done_tick.setImageDrawable(getResources().getDrawable(R.drawable.ic_caution));
                holder.cat_A.setText(bank_categorystr);


                // Objs.a.loadPicasso(mCon,bank_logo_cc,holder.uploaded_yes,holder.progressBarMaterial);
                Glide.with(mCon).load(bank_url).into(holder.uploaded_yes);


                if(bank_category.equals("1"))
                {
                    holder.cat_A.setBackgroundResource(R.drawable.capsul_button41);
                }else if(bank_category.equals("2"))
                {
                    holder.cat_A.setBackgroundResource(R.drawable.but_shape_reject);
                } else
                {
                    holder.cat_A.setBackgroundResource(R.drawable.capsul_button412);
                }
                holder.missing_document.setText("Failed in Eligibility");
                holder.missing_document.setTextColor(Color.parseColor("#D34D53"));

                holder.upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(Document_Availability_Check.this, Applicant_Doc_Details_revamp.class);
                        startActivity(intent);

                    }
                });
                holder.arrowimgup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.arrowimgup.setVisibility(View.GONE);
                        holder.arrowimgdown1.setVisibility(View.VISIBLE);
                        holder.eligible_available.setVisibility(View.GONE);
                    }
                });
                holder.arrowimgdown1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // holder.check_list_name.removeAllViews();
                        holder.arrowimgup.setVisibility(View.VISIBLE);
                        holder.arrowimgdown1.setVisibility(View.GONE);
                        holder.eligible_available.setVisibility(View.VISIBLE);
                        String typecnt1;

                        final String relationship_type=Pref.getCoAPPAVAILABLE(getApplicationContext());

                        if(relationship_type.equals("1"))
                        {
                            typecnt1="0";
                        }else
                        {
                            typecnt1="1";
                        }
                        try {
                            Bank_ID= J.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        holder.tabel_row.removeAllViews();
                        // final String step_status11 = step_status1;
                        JSONObject jsonObject = new JSONObject();
                        JSONObject J = null;
                        try {
                            J = new JSONObject();

                            J.put("bank_id", Bank_ID);
                            //  J.put("bank_id", id);
                            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
                            J.put("user_id",Pref.getUSERID(getApplicationContext()));
                            J.put("automated_underwriting","1");
                            J.put("type_cnt",typecnt1);

                           /* J.put("bank_id", 23);
                            //  J.put("bank_id", id);
                            J.put("transaction_id", "58815");
                            J.put("user_id","57205");
                            J.put("automated_underwriting","1");
                            J.put("type_cnt","0");
*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.show();
                        Log.e("bank state rule request", String.valueOf(J));

                        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.doccklist_ruleres, J,
                                new Response.Listener<JSONObject>() {
                                    @SuppressLint({"LongLogTag", "ResourceType"})
                                    @Override
                                    public void onResponse(JSONObject response) {

                                        Log.e("rule response", String.valueOf(response));
                                        try {


                                            TableRow tr_head = new TableRow(getApplicationContext());
                                            tr_head.setId(10);
                                            tr_head.setBackgroundColor(Color.GRAY);
                                            tr_head.setLayoutParams(new ViewGroup.LayoutParams(
                                                    ViewGroup.LayoutParams.FILL_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));

                                            TextView label_date = new TextView(getApplicationContext());
                                            label_date.setId(20);
                                            label_date.setText("Rule Description" + "  ");
                                            label_date.setTextColor(Color.WHITE);
                                            label_date.setPadding(5, 5, 5, 5);
                                            tr_head.addView(label_date);// add the column to the table row here

                                            TextView label_weight_kg = new TextView(getApplicationContext());
                                            label_weight_kg.setId(21);// define id that must be unique
                                            label_weight_kg.setText("Status" + "  "); // set the text for the header
                                            label_weight_kg.setTextColor(Color.WHITE); // set the color
                                            label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
                                            tr_head.addView(label_weight_kg); // add the column to the table row here

                                            TextView Fail_Message = new TextView(getApplicationContext());
                                            Fail_Message.setId(21);// define id that must be unique
                                            Fail_Message.setText("Fail Message" + "  "); // set the text for the header
                                            Fail_Message.setTextColor(Color.WHITE); // set the color
                                            Fail_Message.setPadding(5, 5, 5, 5); // set the padding (if required)
                                            tr_head.addView(Fail_Message); // add the column to the table row here


                                            holder.tabel_row.addView(tr_head, new TableLayout.LayoutParams(
                                                    ViewGroup.LayoutParams.FILL_PARENT,
                                                    ViewGroup.LayoutParams.WRAP_CONTENT));

                                            JSONObject jsonObject1 = null;

                                            JSONArray response1 = response.getJSONArray("rule_arr");
                                            for (int i = 0; i < response1.length(); i++) {

                                                JSONObject J = null;
                                                try {

                                                    J = response1.getJSONObject(i);

                                                    String rule_type = J.getString("rule_desc");
                                                    String fail_message = J.getString("fail_message");
                                                    String rule_status = J.getString("rule_status");
                                                    TableRow tr = new TableRow(getApplicationContext());
                                                    tr.setId(100 + i);
                                                    tr.setLayoutParams(new ViewGroup.LayoutParams(
                                                            ViewGroup.LayoutParams.FILL_PARENT,
                                                            ViewGroup.LayoutParams.WRAP_CONTENT));

                                                    TextView rule_description = new TextView(getApplicationContext());
                                                    rule_description.setId(200 + i);
                                                    rule_description.setText(rule_type + "  ");
                                                    label_weight_kg.setPadding(5, 5, 5, 5);
                                                    tr.addView(rule_description);

                                                    TextView fail_message1 = new TextView(getApplicationContext());
                                                    fail_message1.setId(300 + i);
                                                    if (rule_status.equals("2")) {
                                                        fail_message1.setText("Pass" + "  ");
                                                        fail_message1.setTextColor(Color.parseColor("#33b48d"));
                                                        label_weight_kg.setPadding(5, 5, 5, 5);
                                                        tr.addView(fail_message1);

                                                    } else {
                                                        fail_message1.setText("Fail" + "  ");
                                                        fail_message1.setTextColor(Color.parseColor("#c82128"));

                                                        label_weight_kg.setPadding(5, 5, 5, 5);
                                                        tr.addView(fail_message1);

                                                    }


                                                    TextView rule_status1 = new TextView(getApplicationContext());
                                                    rule_status1.setId(300 + i);
                                                    rule_status1.setText(fail_message + "  ");
                                                    label_weight_kg.setPadding(5, 5, 5, 5);
                                                    tr.addView(rule_status1);

                                                    holder.tabel_row.addView(tr, new TableLayout.LayoutParams(
                                                            ViewGroup.LayoutParams.FILL_PARENT,
                                                            ViewGroup.LayoutParams.WRAP_CONTENT));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }


                                            }



                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                        JSONObject jsonObject1 = new JSONObject();


                                        // TableLayout prices = (TableLayout) holder.findViewById(R.id.prices);


                                        progressDialog.dismiss();
                                    }
                                }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Log.e("Applicant Entry request", String.valueOf(error));
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                });

                // holder.uploaded_yes.setText(bank_categorystr);

            } catch (NullPointerException e) {
                Objs.a.showToast(mCon, e.toString());
            } catch (Exception e) {
                Objs.a.showToast(mCon, e.toString());
            }
        }

        private void update(JSONArray list1) {
            list = list1;
        }


        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            AppCompatTextView cat_A,missing_document;
            CardView card_view_class_name;
            View view;
            ImageView image_doc,uploaded_yes,done_tick;
            Button uploadbtn;
            RecyclerView recycler_view_chiled;
            ProgressBar progressBarMaterial;
            LinearLayout check_list_name,eligible_available;
            AppCompatButton upload;
            TableLayout tabel_row;
            AppCompatImageView arrowimgup,arrowimgdown1;
            public ViewHolder(View itemView) {
                super(itemView);

                cat_A  = (AppCompatTextView) itemView.findViewById(R.id.cat_A);
                tabel_row  = (TableLayout) itemView.findViewById(R.id.tabel_row);
                missing_document  = (AppCompatTextView) itemView.findViewById(R.id.missing_document);
                uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);
                done_tick  = (ImageView) itemView.findViewById(R.id.done_tick);
                // uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);
                progressBarMaterial = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);
                card_view_class_name  = (CardView) itemView.findViewById(R.id.card_view_class_name);
                check_list_name  = (LinearLayout)itemView.findViewById(R.id.check_list_name);
                upload  = (AppCompatButton) itemView.findViewById(R.id.upload);
                arrowimgup  = (AppCompatImageView) itemView.findViewById(R.id.arrowimgup);
                arrowimgdown1  = (AppCompatImageView) itemView.findViewById(R.id.arrowimgdown1);
                eligible_available  = (LinearLayout) itemView.findViewById(R.id.eligible_available);

            }
        }
    }
    public class ListItemAdapter_chiled extends RecyclerView.Adapter<ListItemAdapter_chiled.ViewHolder> {

        JSONArray list = new JSONArray();
        Context mCon;
        JSONObject J;

        public ListItemAdapter_chiled(Context mCon, JSONArray list) {
            this.list = list;
            this.mCon = mCon;
        }

        @Override
        public int getItemCount() {
            return list.length();
          //  return 2;
        }

        public JSONObject getItem(int i) {
            try {
                return list.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_application_document_details_child, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);

                String enable_status = J.getString("enable_status");
                String upload_status = J.getString("upload_status");
               // holder.class_name.setText(Objs.a.capitalize(J.getString("doc_typename")));

                if(enable_status.equals("1") || upload_status.equals("1") )
                {
                    holder.class_name.setText(Objs.a.capitalize(J.getString("doc_typename")));

                   // String upload_status = J.getString("upload_status");
                    String submit_loanwiser = J.getString("submit_loanwiser");
                    if(upload_status.equals("1"))
                    {
                        if(submit_loanwiser.equals("1"))
                        {
                            holder.uploadbtn.setVisibility(View.GONE);
                        }else
                        {
                            holder.uploadbtn.setVisibility(View.VISIBLE);
                        }

                       // holder.uploadbtn.setVisibility(View.GONE);
                        JSONArray file_array = J.getJSONArray("file_array");
                        Log.e("file array",file_array.toString());
                        ListItemAdapter_sub_chiled adapter_sub_chile = new ListItemAdapter_sub_chiled(mCon,file_array);
                        imagelist1.setVisibility(View.GONE);
                        imagelist.setVisibility(View.VISIBLE);
                        /// RecyclerView recyclerView = (RecyclerView) findViewById(adhoc.app.applibrary.R.id.recycler_view);
                        // imagelist.setLayoutManager(llm);
                        holder.recycler_view_sub_chiled.setHasFixedSize(true);
                        holder.recycler_view_sub_chiled.setNestedScrollingEnabled(false);
                        holder.recycler_view_sub_chiled.setLayoutManager(new LinearLayoutManager(mCon));
                        holder.recycler_view_sub_chiled.setAdapter(adapter_sub_chile);
                    }else
                    {
                        imagelist1.setVisibility(View.GONE);
                        imagelist.setVisibility(View.VISIBLE);
                    }


                }else
                {
                    holder.card_view_class_name_child.setVisibility(View.GONE);
                }


                holder.uploadbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);
                        try {
                            //  String id =   J.getString(Params.id);
                            String doc_typename =   J.getString("doc_typename");
                            String docid =   J.getString("legal_docid");
                            //  String class_id =   J.getString(Params.class_id);
                            //  String user_type =  Pref.getAEID(mCon);
                            String transaction_id =   J.getString("transaction_id");

                            Log.e("doc_typename",doc_typename);
                            Log.e("legalid",docid);
                            Log.e("transaction_id",transaction_id);

                            Pref.putcamera_doc_typename(mCon,doc_typename);
                            Pref.putcamera_docid(mCon,docid);
                            Pref.putcamera_transaction_id(mCon,transaction_id);

                            showBottomSheetDialogFragment();
                           /* Objs.ac.StartActivityPutExtra(mCon, ManiActivity_Image2.class, Params.doc_typename,doc_typename,
                                    Params.docid,docid,Params.transaction_id,transaction_id);*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (NullPointerException e) {
                Objs.a.showToast(mCon, e.toString());
            } catch (Exception e) {
                Objs.a.showToast(mCon, e.toString());
            }
        }

        private void update(JSONArray list1) {
            list = list1;
        }


        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            AppCompatTextView class_name,mandatory_do,missing;
            CardView card_view_class_name_child;
            View view;
            ImageView image_doc,uploaded_yes;
            Button uploadbtn;
            RecyclerView recycler_view_sub_chiled;

            public ViewHolder(View itemView) {
                super(itemView);

               /*
                mandatory_do  = (AppCompatTextView) itemView.findViewById(R.id.mandatory_do);
                image_doc  = (ImageView) itemView.findViewById(R.id.image_doc);
                uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);
                uploadbtn=(Button)itemView.findViewById(R.id.uploadbtn);

                card_view_class_name  = (CardView) itemView.findViewById(R.id.card_view_class_name);
                recycler_view_sub_chiled  = (RecyclerView) itemView.findViewById(R.id.recycler_view_sub_chiled);*/
                uploadbtn=(Button)itemView.findViewById(R.id.uploadbtn);
                card_view_class_name_child  = (CardView) itemView.findViewById(R.id.card_view_class_name_child);
                class_name  = (AppCompatTextView) itemView.findViewById(R.id.class_name);
                recycler_view_sub_chiled  = (RecyclerView) itemView.findViewById(R.id.recycler_view_sub_chiled);
            }
        }
    }
    public void showBottomSheetDialogFragment() {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();

        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
    public class ListItemAdapter_sub_chiled extends RecyclerView.Adapter<ListItemAdapter_sub_chiled.ViewHolder> {

        JSONArray list = new JSONArray();
        Context mCon;
        JSONObject J;

        public ListItemAdapter_sub_chiled(Context mCon, JSONArray list) {
            this.list = list;
            this.mCon = mCon;
        }

        @Override
        public int getItemCount() {
             return list.length();
          //  return 2;
        }

        public JSONObject getItem(int i) {
            try {
                return list.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_application_document_details_sub_child, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);

                holder.image_doc1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String type = null;
                        String hash = null;
                        String document = null;
                        String transaction_id = null;
                        String docid = null;
                        String class_id = null;
                        String user_type = null;
                        String file_name = null;
                        String file_name_withhash = null;
                        try {
                            type = J.getString("type");
                            hash = J.getString("hash");
                            document = J.getString("document");
                            transaction_id = J.getString("transaction_id");
                            docid = J.getString("docid");
                            file_name_withhash= J.getString("file_name_withhash");
                            class_id = J.getString("class_id");
                            user_type = J.getString("user_type");
                            file_name = J.getString("file_name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Submit_Delete(hash,type,document,transaction_id,docid,class_id,user_type,file_name,file_name_withhash);
                    }
                });

                holder.image_doc_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String type = null;
                        String hash = null;
                        String document = null;
                        try {
                            type = J.getString("type");
                             hash = J.getString("hash");
                             document = J.getString("document");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Objs.ac.StartActivityPutExtra(mCon, Doc_ImageView.class, Params.type,type,
                                Params.document,document,
                                Params.hash,hash);
                    }
                });





                String upload_status= J.getString("upload_status");
                String submit_loanwiser= J.getString("submit_loanwiser");

                if(submit_loanwiser.equals("1"))
                {
                    holder.image_doc1.setVisibility(View.GONE);
                }else {
                    holder.image_doc1.setVisibility(View.VISIBLE);
                }

                if(upload_status.equals("0"))
                {
                    holder.uploded_img.setVisibility(View.GONE);
                }else
                {
                    String mandatory_do= J.getString("document");
                    String file_name= J.getString("file_name_withhash");
                    String hash= J.getString("hash");
                    holder.uploded_img.setVisibility(View.VISIBLE);
                    holder.mandatory_do.setText(file_name);
                }


              /*  holder.class_name.setText(Objs.a.capitalize(J.getString("key")));
                // Objs.a.NewNormalFontStyle(mCon,holder.class_name);

                String docuemt_upload= J.getString("docuemt_upload");

                String key = J.getString("key");
                String document_req = J.getString("document_req");
                String star="<font color='#D44D53'>*</font>";

                holder.class_name.setText(Html.fromHtml(key + " "+star));
                Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "segoe_ui.ttf");
                holder.class_name.setTypeface(font,Typeface.BOLD);
                holder.class_name.setTextSize(16);
                if(document_req.equals("0"))
                {
                    holder.mandatory_do.setText("(Highly Increases Loan Approval)");
                    holder. mandatory_do.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.but_red));
                }else
                {
                    holder.mandatory_do.setText("(Mandatory Document)");
                    holder. mandatory_do.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.but_red));
                }

                JSONArray Proof_Array12 = jsonobject_2.getJSONArray(key);

                if(docuemt_upload.equals("1")){

                    holder.uploaded_yes.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));

                }else {
                    holder.uploaded_yes.setImageDrawable(getResources().getDrawable(R.drawable.ic_grey_tick));
                }

                //  holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.file));

                holder.uploadbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);

                        try {

                            String key = J.getString("key");
                            String document_req = J.getString("document_req");
                            Pref.putDOCKEY(mCon,key);
                            Pref.putdocument_req(mCon,document_req);
                            // String document_req = J.getString("document_req");
                            JSONArray Proof_Array = jsonobject_2.getJSONArray(key);
                            String proof_String_JSonarray  = String.valueOf(Proof_Array);
                            Log.e("the proof_",Proof_Array.toString());

                            Intent intent = new Intent(Applicant_Doc_Details_revamp.this, Document_Details.class);
                            intent.putExtra("jsonArray", proof_String_JSonarray.toString());
                            intent.putExtra("document_req", document_req);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });*/
            } catch (NullPointerException e) {
                Objs.a.showToast(mCon, e.toString());
            } catch (Exception e) {
                Objs.a.showToast(mCon, e.toString());
            }
        }

        private void update(JSONArray list1) {
            list = list1;
        }


        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            AppCompatTextView class_name,mandatory_do;
            CardView card_view_class_name;
            View view;
            ImageView image_doc1,image_doc_view;
            Button uploadbtn;
            RecyclerView recycler_view_chiled;
            LinearLayout uploded_img;

            public ViewHolder(View itemView) {
                super(itemView);

                image_doc1  = (ImageView) itemView.findViewById(R.id.image_doc1);
                image_doc_view  = (ImageView) itemView.findViewById(R.id.image_doc_view);
                mandatory_do  = (AppCompatTextView) itemView.findViewById(R.id.mandatory_do);
                uploded_img  = (LinearLayout) itemView.findViewById(R.id.uploded_img);
              /*  class_name  = (AppCompatTextView) itemView.findViewById(R.id.class_name);
                mandatory_do  = (AppCompatTextView) itemView.findViewById(R.id.mandatory_do);
                image_doc  = (ImageView) itemView.findViewById(R.id.image_doc);
                uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);
                uploadbtn=(Button)itemView.findViewById(R.id.uploadbtn);

                card_view_class_name  = (CardView) itemView.findViewById(R.id.card_view_class_name);
                recycler_view_chiled  = (RecyclerView) itemView.findViewById(R.id.recycler_view_chiled);*/

            }
        }
    }
    private void Submit_Delete(String hash, String type, String document,String transaction_id, String docid,
                                String class_id,String user_type,String file_name, String file_name_withhash){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.delete_dialog_document);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Button delete = (Button) dialog.findViewById(R.id.delete);
        Button no=(Button)dialog.findViewById(R.id.no);
        AppCompatTextView doument_name=(AppCompatTextView)dialog.findViewById(R.id.doument_name);
        AppCompatTextView document_url=(AppCompatTextView)dialog.findViewById(R.id.document_url);
        doument_name.setText(file_name);
        document_url.setText(file_name_withhash);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Delete_Image(hash,type,document,transaction_id,docid,class_id,user_type);

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        if (!dialog.isShowing()) {
            dialog.show();
        }

    }

    private void Submit_Co_Applicant(){
         Dialog dialog = new Dialog(mCon);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.co_app_sub_dialog_document);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView delete = (AppCompatTextView) dialog.findViewById(R.id.delete);
        AppCompatButton upload = (AppCompatButton) dialog.findViewById(R.id.upload);
        Button no=(Button)dialog.findViewById(R.id.no);
        AppCompatTextView bank_statement=(AppCompatTextView)dialog.findViewById(R.id.bank_statement);
        AppCompatTextView document_url=(AppCompatTextView)dialog.findViewById(R.id.document_url);


        if(document_available.equals("yes"))
        {
            if(bank_available.equals("yes"))
            {
                upload.setVisibility(View.VISIBLE);
            }else
            {
                upload.setVisibility(View.GONE);
            }
        }else
        {
            if(bank_available.equals("yes"))
            {
                upload.setVisibility(View.VISIBLE);
            }else {
                upload.setVisibility(View.GONE);
            }
        }
        String formattedString = message_list.toString()
                .replace("[", "")  //remove the right bracket
                .replace("]", "")
                .replace(",", "")
                .replaceAll("\"", "")
                .trim();

        bank_statement.setText(formattedString);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                message_list.clear();
               // Delete_Image(hash,type,document,transaction_id,docid,class_id,user_type);

            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Document_Availability_Check.this, Upload_Activity_Bank.class);
                startActivity(intent);
            }
        });

        if (!dialog.isShowing()) {
            dialog.show();
        }


    }

    private void Delete_Image(String hash, String type, String document,String transaction_id, String docid,
                              String class_id,String user_type) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.hash, hash);
            J.put(Params.class_id, class_id);
            J.put(Params.transaction_id, transaction_id);
            J.put(Params.user_type, user_type);
            J.put(Params.doc_id, docid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();

        Log.e("delete",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.DELETE_IMG_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.e("delete",response.toString());
                        try {
                            if(response.getBoolean(Params.status)){

                                Toast.makeText(getApplication(),"Succussfully deleted the Document...",Toast.LENGTH_SHORT).show();

                                //  Objs.a.showToast(DocGridView_List.this, "Succussfully deleted the Document...");
                              /*  Objs.ac.StartActivityPutExtra(DocGridView_List.this, Document_Details.class,
                                        Params.user_type,user_type);*/

                                Intent intent = new Intent(Document_Availability_Check.this, Document_Availability_Check.class);
                                startActivity(intent);

                                finish();
                                /// Document_Details(user_type,class_id,transaction_id,doc_id);
                            }else{
                                ///  Objs.a.showToast(mCon, "Something went wrong ");
                                Toast.makeText(mCon,"Something went wrong",Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //   Objs.a.showToast(mCon, error.getMessage());
                Log.e("delete",error.toString());
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
    public class ListItemAdapter1 extends RecyclerView.Adapter<ListItemAdapter1.ViewHolder> {

        JSONArray list = new JSONArray();
        Context mCon;
        JSONObject J;

        public ListItemAdapter1(Context mCon, JSONArray list) {
            this.list = list;
            this.mCon = mCon;
        }

        @Override
        public int getItemCount() {
            return list.length();
        }

        public JSONObject getItem(int i) {
            try {
                return list.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_application_doc_details_list, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);



                holder.Over_all.setVisibility(View.GONE);
                holder.doc_typename.setText((J.getString("doc_typename")));
                Objs.a.NewNormalFontStyle(mCon,holder.doc_typename);
                holder.doc_typename_all.setText((J.getString("doc_typename")));
                Objs.a.NewNormalFontStyle(mCon,holder.doc_typename_all);
              //  holder.count__all.setText(J.getString(Params.upload_count));


                if(J.getString("upload_status").equals("1")){

                    String submit_loanwiser = J.getString("submit_loanwiser");
                    if(submit_loanwiser.equals("1"))
                    {
                        holder.uploadbtn.setVisibility(View.GONE);
                    }else
                    {
                        holder.uploadbtn.setVisibility(View.VISIBLE);
                    }
                    JSONArray file_array = J.getJSONArray("file_array");
                    ListItemAdapter_sub_chiled adapter_sub_chile = new ListItemAdapter_sub_chiled(mCon,file_array);
                    /// RecyclerView recyclerView = (RecyclerView) findViewById(adhoc.app.applibrary.R.id.recycler_view);
                    // imagelist.setLayoutManager(llm);
                    holder.recycler_view_sub_chiled.setHasFixedSize(true);
                    holder.recycler_view_sub_chiled.setNestedScrollingEnabled(false);
                    holder.recycler_view_sub_chiled.setLayoutManager(new LinearLayoutManager(mCon));
                    holder.recycler_view_sub_chiled.setAdapter(adapter_sub_chile);
                    holder.Over_all.setVisibility(View.VISIBLE);
                    holder.Ly_first.setVisibility(View.GONE);
                }else{
                    holder.Over_all.setVisibility(View.GONE);
                    holder.Ly_first.setVisibility(View.VISIBLE);
                }

                holder.uploadbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);
                        try {
                            //  String id =   J.getString(Params.id);
                            String doc_typename =   J.getString("doc_typename");
                            String docid =   J.getString("legal_docid");
                            //  String class_id =   J.getString(Params.class_id);
                            //  String user_type =  Pref.getAEID(mCon);
                            String transaction_id =   J.getString("transaction_id");

                            Log.e("doc_typename",doc_typename);
                            Log.e("legalid",docid);
                            Log.e("transaction_id",transaction_id);

                            Pref.putcamera_doc_typename(mCon,doc_typename);
                            Pref.putcamera_docid(mCon,docid);
                            Pref.putcamera_transaction_id(mCon,transaction_id);

                            showBottomSheetDialogFragment();
                           /* Objs.ac.StartActivityPutExtra(mCon, ManiActivity_Image2.class, Params.doc_typename,doc_typename,
                                    Params.docid,docid,Params.transaction_id,transaction_id);*/
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

               /* holder.Over_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);
                        try {

                            String id =   J.getString(Params.id);
                            String doc_name =   J.getString(Params.doc_typename);
                            String docid =   J.getString(Params.doc_id);
                            String class_id =   J.getString(Params.class_id);
                            String doc_typename =   J.getString(Params.doc_typename);
                            String user_type =    J.getString(Params.user_type);
                            String transaction_id =   J.getString(Params.transaction_id);

                           *//*  Objs.a.showToast(mCon, "Gridview  " +id +"\n"+ transaction_id +"\n"+ doc_name
                                    +"\n"+  docid +"\n"+class_id +"\n"+
                                    user_type );*//*


                            Objs.ac.StartActivityPutExtra(mCon, DocGridView_List1.class,
                                    Params.class_id,class_id,
                                    Params.user_type,user_type,
                                    Params.transaction_id,transaction_id,
                                    Params.doc_id,docid,
                                    Params.id,id,
                                    Params.doc_typename,doc_typename);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                holder.Ly_first.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);
                        try {
                            String id =   J.getString(Params.id);
                            String doc_typename =   J.getString(Params.doc_typename);
                            String docid =   J.getString(Params.doc_id);
                            String class_id =   J.getString(Params.class_id);
                            String user_type =    J.getString(Params.user_type);
                            String transaction_id =   J.getString(Params.transaction_id);


                            *//*Objs.a.showToast(mCon, "Upload  " +id +"\n"+ transaction_id +"\n"+ doc_typename
                                    +"\n"+  docid +"\n"+class_id +"\n"+
                                   user_type );
*//*
                            Objs.ac.StartActivityPutExtra(mCon, MainActivity_IMG_Property2.class,
                                    Params.id,id
                                    , Params.doc_typename,doc_typename,
                                    Params.docid,docid
                                    , Params.class_id,class_id,
                                    Params.user_type,user_type,
                                    Params.transaction_id,transaction_id);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });*/



            } catch (NullPointerException e) {
                Objs.a.showToast(mCon, e.toString());
            } catch (Exception e) {
                Objs.a.showToast(mCon, e.toString());
            }
        }

        private void update(JSONArray list1) {
            list = list1;
        }


        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            AppCompatTextView doc_typename,count;
            AppCompatTextView doc_typename_all,count__all;
            CardView card_view_doc_typename;
            LinearLayout lay_image_view,Ly_first,Over_all;
            View view;
            RecyclerView recycler_view_sub_chiled;
            Button uploadbtn;

            public ViewHolder(View itemView) {
                super(itemView);

                doc_typename  = (AppCompatTextView) itemView.findViewById(R.id.doc_typename);
                doc_typename_all  = (AppCompatTextView) itemView.findViewById(R.id.doc_typename_all);
                count__all  = (AppCompatTextView) itemView.findViewById(R.id.count__all);
                card_view_doc_typename  = (CardView) itemView.findViewById(R.id.card_view_doc_typename);
                Ly_first  = (LinearLayout) itemView.findViewById(R.id.Ly_first);
                Over_all  = (LinearLayout) itemView.findViewById(R.id.Over_all);
                recycler_view_sub_chiled  = (RecyclerView) itemView.findViewById(R.id.recycler_view_sub_chiled);
                uploadbtn  = (Button) itemView.findViewById(R.id.uploadbtn);

            }
        }
    }


    private void checklist_name(final JSONArray doc_ype_com,String key, String document_req) {



    }

  /*  @Override
    public void onBackPressed() {

        Pref.removeDOC(mCon);
        Pref.removeTID(mCon);
        Pref.removeEID(mCon);
        Pref.removeAEID(mCon);
            Intent intent = new Intent(Document_Availability_Check.this,Home.class);
            startActivity(intent);
            finish();
            super.onBackPressed();

    }*/
}
