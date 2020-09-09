package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
import in.loanwiser.partnerapp.Multi_select_checkbox.CustomAdapter;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Document_Check_List extends SimpleActivity implements CompoundButton.OnCheckedChangeListener {

    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    JSONObject response_doc,response_doc1,doc_ar;
    JSONArray response_iD_proof,doc_ype,response_iD_proof_comon,doc_ype_com;
    private Context context = this;
    int Array_Count=0;
    int Array_Count1=0;
    int Array_Count2=0;
    int Array_Count3=0;
    int Array_Count4=0;
    int Array_Count5=0;

    int checklistname_count= 0;

    ArrayList<String> list_key;
    //  String[] Str_Array = {"a","b","c"};
    //  String[] Str_Array = {" -Select Salary Details- ","Salaried Individual","Self Employed Profesional","Self Employed Non Profesional","Home Maker","Retired"};


    String doc_typename,Current_iD_Proof,PERMANT_iD_Proof,INCOME,WRK,PHOTO,checklist_name1,checked_statues;
    LinearLayout my_layout,my_layout1,my_layout2,my_layout3,my_layout4,my_layout5,check_list_name;
    JSONArray current_resdi_proof,current,permanent_addr,permant_add_proof,Income_pr,income_proof_arr,Wrk_Add_proof,wrk_prf__arr,
            Photo_proof,photo_prf__arr;
    JSONObject current_object,permaant_address_object,INcome_proof_object,wrk_proof_object,photo_proof_object;
    AppCompatButton Docum_ch_step1;

    ListView listView;
    List<Integer> myList_values;
    List<String> result1;
    List<Integer> select_lid_id;
    String [] js;
    int count;
    JSONObject jsonObjectUid,jsonObjectUid1,jsonObjectUid2,jsonObjectUid3,jsonObjectUid4,jsonObjectUid5;
    String target_id, doc_typename1,string,target_id2,doc_typename2,target_id3,doc_typename3,
            target_id4,doc_typename4,target_id5,doc_typename5,target_id6,doc_typename6;

    List<String> mList = new ArrayList<String>();
    List<String> mList1 = new ArrayList<String>();
    List<String> mList2 = new ArrayList<String>();
    List<String> mList3 = new ArrayList<String>();
    List<String> mList4 = new ArrayList<String>();
    List<String> mList5 = new ArrayList<String>();

    AppCompatTextView Identity_id_txt,Current_id_txt,Permanent_id_txt,Income_id_txt,Work_id_txt_id,Photo_id_txt;
    JSONArray key_value;
    JSONObject json_data;

    AppCompatTextView  Identity_Proof_txt,Current_Proof_txt,Permanent_Proof_txt,Income_Proof_txt,
            Work_Proof_txt_id,Photo_Proof_txt;


    String applicant_name,transaction_id,user_type,emp_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_document__check__list);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_document__check__list);
        initTools(R.string.doc_check_list);

        progressDialog = new SpotsDialog(context, R.style.Custom);
        UIBINDING();

        applicant_name =  Objs.a.getBundle(this, Params.applicant_name);
        transaction_id =  Objs.a.getBundle(this, Params.transaction_id);
        user_type =  Objs.a.getBundle(this, Params.user_type);
        emp_state =  Objs.a.getBundle(this, Params.emp_state);

        Document_check_lsit();

      //  Click();

        Docum_ch_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(Document_Check_List.this, Home.class);
                startActivity(intent);*/
                Account_Listings_Details();
            }
        });

    }

    private void Click()
    {
        Docum_ch_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Iterator iterator = doc_ar.keys();
                while (iterator.hasNext()) {

                    String key = (String)iterator.next();
                    Log.e("value", key.toString());

                    try {
                        response_iD_proof_comon = doc_ar.getJSONArray(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    for (int i=0;i<response_iD_proof_comon.length();i++) {
                        JSONObject J = null;
                        try {
                            J = response_iD_proof_comon.getJSONObject(i);

                            doc_ype_com = J.getJSONArray("doc_type_names");
                          //  checklist_name_validate(doc_ype_com,key);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                }
            }
        });

    }


    private void Account_Listings_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.user_id, Pref.getUSERID(getApplicationContext()));
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

    }
    private void document_format(String leagal_id, String status) {

        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("legal_docid", leagal_id);
            J.put("status", status);


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


    private void Document_check_lsit() {
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", transaction_id);
          //  J.put("transaction_id", "11465");
         //   J.put("applicant_type", "1");
         //   J.put("employement_type", "1");
           J.put("employement_type", emp_state);
           J.put("applicant_type", user_type);
            J.put("type_req", 0);
            J.put("status_flag", 1);

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
                            Log.e("KEY_ARR_VALUE", key_value.toString());
                            doc_ar=response_doc.getJSONObject("document_arr");
                            //below few lines array key name
                            Iterator iterator = doc_ar.keys();
                            while (iterator.hasNext()) {

                                String key = (String)iterator.next();
                                Log.e("value", key.toString());
                                 list_key = new ArrayList<String>();


                                Log.e("the value",list_key.toString());
                                response_iD_proof_comon = doc_ar.getJSONArray(key);
                                for (int i=0;i<response_iD_proof_comon.length();i++) {
                                    JSONObject J = null;
                                    try {

                                        J = response_iD_proof_comon.getJSONObject(i);
                                        doc_ype_com = J.getJSONArray("doc_type_names");

                                        checklist_name(doc_ype_com,key);

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

    private void checklist_name(final JSONArray doc_ype_com, String key) {


        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (!list_key.contains(key))
        {

            TextView tv = new TextView(this);
            tv.setLayoutParams(lparams);
            list_key.add(key);
            tv.setText(key);
            tv.setText(key);
            tv.setTextSize(18);
            tv.setTextColor(Color.BLACK);
            check_list_name.addView(tv);
        }else
        {

        }
       Log.e("the list_key value",list_key.toString());



        checklistname_count = doc_ype_com.length();
        for (int i = 0; i < checklistname_count; i++) {

            JSONObject rec5 = null;
            try {

                rec5 = doc_ype_com.getJSONObject(i);
                //  int id = rec.getInt("id");
                checklist_name1 = rec5.getString("doc_typename");
                checked_statues = rec5.getString("enable_status");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            TableRow row =new TableRow(this);
            row.setId(i);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            final CheckBox checkBox = new CheckBox(this);
            TextView textView = new TextView(this);
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setId(i);
            checkBox.setText(checklist_name1);
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
        }



    }
 /*   private void checklist_name_validate(final JSONArray doc_ype_com, String key) {

     *//*   LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(this);
        tv.setLayoutParams(lparams);
        tv.setText(key);
        tv.setTextSize(18);
        tv.setTextColor(Color.BLACK);
        check_list_name.addView(tv);*//*

        checklistname_count = doc_ype_com.length();
        for (int i = 0; i < checklistname_count; i++) {

            JSONObject rec5 = null;
            try {

                rec5 = doc_ype_com.getJSONObject(i);
                //  int id = rec.getInt("id");
                checklist_name1 = rec5.getString("doc_typename");



            } catch (JSONException e) {
                e.printStackTrace();
            }

        *//*    TableRow row =new TableRow(this);
            row.setId(i);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            checkBox = new CheckBox(this);
            TextView textView = new TextView(this);
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setId(i);
            checkBox.setText(checklist_name1);
            //  row.addView(checkBox);
            check_list_name.addView(checkBox);
            // my_layout.addView(row);*//*



        }

        if(checkBox.isChecked()){
            Log.e("sellect"," all from one");

        }else
        {
            Log.e("sellect","select all from one");
        }

    }*/

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

   /* @Override
    public void onBackPressed() {

        Intent intent = new Intent(Document_Check_List.this, Home.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }*/
}