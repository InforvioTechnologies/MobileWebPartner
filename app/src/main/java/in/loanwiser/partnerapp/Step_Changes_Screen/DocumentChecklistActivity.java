package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Documents.Applicant_Details_Single;
import in.loanwiser.partnerapp.PartnerActivitys.ViewPagerAdapter;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class DocumentChecklistActivity extends SimpleActivity implements CompoundButton.OnCheckedChangeListener {

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    String get_jsonArray,userstatus,transaction_id,user_type,emp_states,applicant_count,property_identify;
    String doc_typename,Current_iD_Proof,PERMANT_iD_Proof,INCOME,WRK,PHOTO,doc_typeque,checklist_name1,checked_statues,target_id6,doc_typename6;
    String documentreq,documentre_type;
   // FragmentApplicant fragmentApplicant;

    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    JSONObject response_doc,response_doc1,doc_ar,document_req;
    JSONArray response_iD_proof,doc_ype,response_iD_proof_comon,doc_ype_com;
    ArrayList<String> list_key;
    ArrayList<String> qoc_quelist;
    JSONArray key_value;
    LinearLayout my_layout,my_layout1,my_layout2,my_layout3,my_layout4,my_layout5,check_list_name,keylist;
    int checklistname_count= 0;
    int namelist= 0;
    int keyarray = 0;

    JSONObject jsonObjectUid,jsonObjectUid1,jsonObjectUid2,jsonObjectUid3,jsonObjectUid4,jsonObjectUid5;
    List<String> mList5 = new ArrayList<String>();
    AppCompatTextView Identity_id_txt,Current_id_txt,Permanent_id_txt,Income_id_txt,Work_id_txt_id,Photo_id_txt;
    AppCompatTextView  Identity_Proof_txt,Current_Proof_txt,Permanent_Proof_txt,Income_Proof_txt,
            Work_Proof_txt_id,Photo_Proof_txt;
    AppCompatButton Docum_ch_step1;
    String Applicant_what,co_app,Applicant_what1,Applicant_what2;

    CardView Applicant_ly,Co_Applicant_ly,Property;
    LinearLayout Applicant_ly1,Co_Applicant_ly1,Property1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_document_checklist);
        Log.i("TAG", "onCreate: "+"Documentchecklist Activity");
        progressDialog = new SpotsDialog(this, R.style.Custom);
        UIBINDING();
        checkcondition();
       /* co_app = Pref.getCoAPPAVAILABLE(getApplicationContext());
        if(co_app.equals("1"))
        {
            Applicant_ly1.setVisibility(View.VISIBLE);
            Co_Applicant_ly1.setVisibility(View.GONE);

        }else {
            Applicant_ly1.setVisibility(View.VISIBLE);
            Co_Applicant_ly1.setVisibility(View.VISIBLE);
        }*/
        Document_check_lsit();
        Applicant_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Document_check_lsit();
            }
        });

        Co_Applicant_ly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Document_check_lsit();
            }
        });

        Property1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Document_check_lsit();
            }
        });






        Docum_ch_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void UIBINDING()
    {
        Docum_ch_step1  = (AppCompatButton) findViewById(R.id.Docum_ch_step1);
        my_layout   = (LinearLayout)findViewById(R.id.check_list);
        my_layout1   = (LinearLayout)findViewById(R.id.check_list1);
        my_layout2   = (LinearLayout)findViewById(R.id.check_list2);
        my_layout3   = (LinearLayout)findViewById(R.id.check_list3);
        my_layout4   = (LinearLayout)findViewById(R.id.check_list4);
        my_layout5  = (LinearLayout)findViewById(R.id.check_list5);

        check_list_name  = (LinearLayout)findViewById(R.id.check_list_name);
       // keylist  = (LinearLayout)findViewById(R.id.keylist);


        Identity_id_txt  = (AppCompatTextView) findViewById(R.id.Identity_id_txt);
        Current_id_txt = (AppCompatTextView) findViewById(R.id.Current_id_txt);
        Permanent_id_txt = (AppCompatTextView) findViewById(R.id.Permanent_id_txt);
        Income_id_txt = (AppCompatTextView) findViewById(R.id.Income_id_txt);
        Work_id_txt_id = (AppCompatTextView) findViewById(R.id.Work_id_txt_id);
        Photo_id_txt = (AppCompatTextView) findViewById(R.id.Photo_id_txt);

        Identity_Proof_txt  = (AppCompatTextView) findViewById(R.id.Identity_Proof_txt);
        Current_Proof_txt  = (AppCompatTextView) findViewById(R.id.Current_Proof_txt);
        Permanent_Proof_txt  = (AppCompatTextView) findViewById(R.id.Permanent_Proof_txt);
        Income_Proof_txt  = (AppCompatTextView) findViewById(R.id.Income_Proof_txt);
        Work_Proof_txt_id  = (AppCompatTextView) findViewById(R.id.Work_Proof_txt_id);
        Photo_Proof_txt  = (AppCompatTextView) findViewById(R.id.Photo_Proof_txt);

        Applicant_ly = (CardView) findViewById(R.id.Applicant_ly);
        Co_Applicant_ly = (CardView) findViewById(R.id.Co_Applicant_ly);
        Property = (CardView) findViewById(R.id.Property);

        Applicant_ly1 = (LinearLayout) findViewById(R.id.Applicant_ly1);
        Co_Applicant_ly1 = (LinearLayout) findViewById(R.id.Co_Applicant_ly1);
        Property1 = (LinearLayout) findViewById(R.id.Property1);

    }

    private void checkcondition(){

        Intent intent=getIntent();
        get_jsonArray = intent.getStringExtra("jsonArray");
        applicant_count=intent.getStringExtra("applicantcount");
        property_identify=intent.getStringExtra("propertyidentify");
        Log.e("TAG", "Applicant_count_checkcondition: "+applicant_count);
        Log.e("TAG", "property_identify_checkcondition: "+property_identify);
        Log.e("TAG", "onCreate:json "+get_jsonArray);


        try {
            JSONArray ja = new JSONArray(get_jsonArray);
            if (ja.length()>0) {
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject explrObject = ja.getJSONObject(i);
                    userstatus = explrObject.getString("applicant_name");
                    transaction_id=explrObject.getString("transaction_id");
                    emp_states=explrObject.getString("emp_states");
                    user_type=explrObject.getString("user_type");
                    Log.i("TAG", "status: " + userstatus);
                }
            } else {
                Objs.a.ShowHideNoItems(this, true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        if(applicant_count.equals("1")) {
            Applicant_ly1.setVisibility(View.VISIBLE);
            Document_check_lsit();
            Co_Applicant_ly1.setVisibility(View.GONE);
            Property1.setVisibility(View.GONE);
        }else if (applicant_count.equals("2")){
            Applicant_ly1.setVisibility(View.VISIBLE);
            Co_Applicant_ly1.setVisibility(View.VISIBLE);
            Property1.setVisibility(View.GONE);
        } else if (property_identify.equals("0")){
            Property1.setVisibility(View.GONE);
        }



    }


    private void Document_check_lsit() {
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", transaction_id);
            J.put("employement_type", emp_states);
            J.put("applicant_type", user_type);
            J.put("type_req", 0);
            J.put("status_flag", 1);
            Log.i("TAG", "Document_check_lsit:request "+J.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Request ", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.DOCUMENT_CHECK_LIST, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Document_check_list", object.toString());
                        try
                        {
                            response_doc=object.getJSONObject("response");
                            key_value=response_doc.getJSONArray("key_arr");
                            Log.i("TAG", "Keyarray "+key_value);
                            Log.e("KEY_ARR_VALUE", key_value.toString());
                            doc_ar=response_doc.getJSONObject("document_arr");
                           // document_req=response_doc.getJSONObject("document_req");

                            //below few lines array key name
                            Iterator iterator = doc_ar.keys();
                            while (iterator.hasNext()) {

                                String key = (String)iterator.next();
                                Log.i("TAG", "keyvaluecheck: "+key);
                                Log.e("value", key.toString());
                                list_key = new ArrayList<String>();


                                Log.e("the value",list_key.toString());
                                response_iD_proof_comon = doc_ar.getJSONArray(key);
                              //  JSONObject req_type=doc_ar.getJSONObject("document_req");

                                for (int i=0;i<response_iD_proof_comon.length();i++) {
                                    JSONObject J = null;
                                    try {

                                        J = response_iD_proof_comon.getJSONObject(i);
                                        doc_ype_com = J.getJSONArray("doc_type_names");
                                        Log.e("DOCUMENTTYpe", String.valueOf(doc_ype_com));
                                        JSONObject js=doc_ype_com.getJSONObject(i);
                                        String test=js.getString("document_req");


                                        checklist_name(doc_ype_com,key,test);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }

                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
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

    @SuppressLint("ResourceAsColor")
    private void checklist_name(final JSONArray doc_ype_com,String key,String test) {


        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (!list_key.contains(key))
        {

            TextView tv = new TextView(this);
            tv.setLayoutParams(lparams);
            lparams.setMargins(10, 10, 10, 10);
            list_key.add(key);
            tv.setText(key);
            tv.setText(key);
            tv.setTextSize(18);
            Typeface font = Typeface.createFromAsset(this.getAssets(), "segoe_ui.ttf");
            tv.setTypeface(font);
            tv.setTextColor(Color.parseColor("#012B5D"));
            check_list_name.addView(tv);

            TextView tvs = new TextView(this);
            TextView tls=new TextView(this);
            tls.setLayoutParams(lparams);
            tvs.setLayoutParams(lparams);
            lparams.setMargins(10, 10, 10, 10);
            tvs.setTextSize(18);
            tls.setTextSize(18);
            Typeface fonts = Typeface.createFromAsset(this.getAssets(), "segoe_ui.ttf");
            tvs.setTypeface(fonts);
            tls.setTypeface(fonts);
            if (test.equals("1")){
                tvs.setText("(Mandatory Document)");
                tls.setText("Please Upload any one of the following");
                tls.setTextColor(Color.parseColor("#D34D53"));
                tvs.setTextColor(Color.parseColor("#25D366"));
               // tvs.setBackgroundColor(Color.GREEN);
                check_list_name.addView(tvs);
                check_list_name.addView(tls);
            }else{
                tvs.setText("Highly Increases Loan Approval");
                tvs.setTextColor(R.color.green);
                tls.setText("Please Upload any one of the following");
                tls.setTextColor(Color.parseColor("#D34D53"));
                tvs.setTextColor(Color.parseColor("#25D366"));
                check_list_name.addView(tvs);
                check_list_name.addView(tls);

            }





        }

            keyarray=doc_ype_com.length();
            for (int k=0;k<keyarray;k++){
                JSONObject rec5 = null;
                try {
                    rec5 = doc_ype_com.getJSONObject(k);
                    documentre_type=rec5.getString("document_req");
                    checklist_name1 = rec5.getString("doc_typename");
                    checked_statues = rec5.getString("enable_status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TableRow row =new TableRow(this);
                row.setId(k);
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                final CheckBox checkBox = new CheckBox(this);
                checkBox.setOnCheckedChangeListener(this);
                checkBox.setId(k);
                Typeface font = Typeface.createFromAsset(this.getAssets(), "segoe_ui.ttf");
                checkBox.setTypeface(font);
                checkBox.setPadding(10, 10, 10, 10);
                checkBox.setTextColor(R.color.black);
                checkBox.setText(checklist_name1);
                checkBox.setTextSize(14);
                checkBox.setGravity(Gravity.LEFT);
                check_list_name.addView(checkBox);

                if(checked_statues.equals("1"))
                {
                    checkBox.setChecked(true);
                }

                // my_layout.addView(row);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked) {
                            String string5 = checkBox.getText().toString();
                            Log.e("checkbox PHoto PF", string5);

                            for (int i = 0; i < doc_ype_com.length(); i++) {


                                try {
                                    jsonObjectUid5 = doc_ype_com.getJSONObject(i);

                                    doc_typename6 = jsonObjectUid5.getString("doc_typename");



                                    if (string5.equals(doc_typename6)) {
                                        target_id6 = jsonObjectUid5.getString("legal_docid");
                                        mList5.add(target_id6);
                                        Log.e("target_id6", String.valueOf(target_id6));
                                        String status = "1";
                                        document_format(target_id6,status);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            Log.e("the check id-6", String.valueOf(mList5));


                        }
                        else
                        {
                            String status = "0";
                            document_format(target_id6,status);
                        }


                    }
                });
            }

        /*checklistname_count = doc_ype_com.length();
        for (int i = 0; i < checklistname_count; i++) {

            JSONObject rec5 = null;
            try {

                rec5 = doc_ype_com.getJSONObject(i);
                //  int id = rec.getInt("id");
                checklist_name1 = rec5.getString("doc_typename");
                documentre_type=rec5.getString("document_req");

               // doc_typeque=rec5.getString("doc_type");

                checked_statues = rec5.getString("enable_status");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            TableRow row =new TableRow(this);
            row.setId(i);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            final CheckBox checkBox = new CheckBox(this);
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setId(i);
            Typeface font = Typeface.createFromAsset(this.getAssets(), "segoe_ui.ttf");
            checkBox.setTypeface(font);
            checkBox.setPadding(10, 10, 10, 10);
            checkBox.setTextColor(R.color.black);
            checkBox.setText(checklist_name1);
            checkBox.setTextSize(14);
            checkBox.setGravity(Gravity.LEFT);







            //  row.addView(checkBox);
            check_list_name.addView(checkBox);

            if(checked_statues.equals("1"))
            {
                checkBox.setChecked(true);
            }

            // my_layout.addView(row);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        String string5 = checkBox.getText().toString();
                        Log.e("checkbox PHoto PF", string5);

                        for (int i = 0; i < doc_ype_com.length(); i++) {


                            try {
                                jsonObjectUid5 = doc_ype_com.getJSONObject(i);

                                doc_typename6 = jsonObjectUid5.getString("doc_typename");

                                if (string5.equals(doc_typename6)) {
                                    target_id6 = jsonObjectUid5.getString("legal_docid");
                                    mList5.add(target_id6);
                                    Log.e("target_id6", String.valueOf(target_id6));
                                    String status = "1";
                                    document_format(target_id6,status);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        Log.e("the check id-6", String.valueOf(mList5));


                    }
                    else
                    {
                        String status = "0";
                        document_format(target_id6,status);
                    }


                }
            });
        }*/



    }

    private void document_format(String leagal_id, String status) {

        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("legal_docid", leagal_id);
            J.put("status", status);
            Log.i("TAG", "document_format:legal_docid "+leagal_id);
            Log.i("TAG", "document_format:status "+status);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Request cHECKBOX", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.UPDATED_ENABLE, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("RESPONSE cHECKBOXsTATUS", String.valueOf(object));

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
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


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    private void Account_Listings_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.user_id, Pref.getUSERID(getApplicationContext()));
            Log.i("TAG", "Account_Listings_Details: userid"+Pref.getUSERID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PARTNER_STATUES_IDs, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("the reponse",response.toString());
                        try {

                            JSONObject Response = response.getJSONObject("reponse");

                            JSONObject jsonObject1 = Response.getJSONObject(Params.application_form);
                            String user_id  = Response.getString(Params.user_id);
                            String jsonStringObj  = String.valueOf(jsonObject1);
                            JSONArray jsonArray = Response.getJSONArray(Params.emp_states);

                            if (jsonArray.length()>0){
                                String jsonString  = String.valueOf(jsonArray);
                                //   Log.e("ApplicantDetails_Single",jsonString);

                                Objs.ac.StartActivityPutExtra(mCon,
                                        Applicant_Details_Single.class,
                                        Params.JSON, jsonString,
                                        Params.id,user_id,
                                        Params.transaction_id,transaction_id,
                                        Params.JSONObj,jsonStringObj);

                                //finish();
                            }else {
                                Objs.a.ShowHideNoItems(mCon,true);
                            }

                            //   JSONArray ja = response.getJSONArray(Params.emp_states);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();

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