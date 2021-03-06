package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Documents.Applicant_Doc_Details_revamp;
import in.loanwiser.partnerapp.R;


public class FragmentCoaplicant extends Fragment implements CompoundButton.OnCheckedChangeListener{

    String get_jsonArray,userstatus,transaction_id,user_type,emp_states,applicant_count,property_identify;
    AppCompatButton Docum_ch_step1;

    String checklist_name1,checked_statues,doc_typename6,target_id6;

    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    JSONObject response_doc,response_doc1,doc_ar;
    JSONArray response_iD_proof,doc_ype,response_iD_proof_comon,doc_ype_com;
    JSONArray key_value;
    ArrayList<String> list_key;
    LinearLayout my_layout,my_layout1,my_layout2,my_layout3,my_layout4,my_layout5,check_list_name;
    AppCompatTextView Identity_id_txt,Current_id_txt,Permanent_id_txt,Income_id_txt,Work_id_txt_id,Photo_id_txt;
    int checklistname_count= 0;
    JSONObject jsonObjectUid,jsonObjectUid1,jsonObjectUid2,jsonObjectUid3,jsonObjectUid4,jsonObjectUid5;
    List<String> mList5 = new ArrayList<String>();
    AppCompatTextView  Identity_Proof_txt,Current_Proof_txt,Permanent_Proof_txt,Income_Proof_txt,
            Work_Proof_txt_id,Photo_Proof_txt,Signature_id_txt;
    String documentreq,documentre_type;
    int keyarray = 0;
    String coapplicantstatus,description,enable_status;

    public FragmentCoaplicant() {
        // Required empty public constructor
    }

    int count,count1,prpceed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_coaplicant, container, false);

        Docum_ch_step1  = (AppCompatButton)rootview.findViewById(R.id.Docum_ch_step1);
        my_layout   = (LinearLayout)rootview.findViewById(R.id.check_list);
        my_layout1   = (LinearLayout)rootview.findViewById(R.id.check_list1);
        my_layout2   = (LinearLayout)rootview.findViewById(R.id.check_list2);
        my_layout3   = (LinearLayout)rootview.findViewById(R.id.check_list3);
        my_layout4   = (LinearLayout)rootview.findViewById(R.id.check_list4);
        my_layout5  = (LinearLayout)rootview.findViewById(R.id.check_list5);

        check_list_name  = (LinearLayout)rootview.findViewById(R.id.check_list_name);


        Identity_id_txt  = (AppCompatTextView)rootview.findViewById(R.id.Identity_id_txt);
        Current_id_txt = (AppCompatTextView) rootview.findViewById(R.id.Current_id_txt);
        Permanent_id_txt = (AppCompatTextView) rootview.findViewById(R.id.Permanent_id_txt);
        Income_id_txt = (AppCompatTextView)rootview.findViewById(R.id.Income_id_txt);
        Work_id_txt_id = (AppCompatTextView)rootview.findViewById(R.id.Work_id_txt_id);
        Photo_id_txt = (AppCompatTextView) rootview.findViewById(R.id.Photo_id_txt);

        Identity_Proof_txt  = (AppCompatTextView)rootview.findViewById(R.id.Identity_Proof_txt);
        Current_Proof_txt  = (AppCompatTextView)rootview.findViewById(R.id.Current_Proof_txt);
        Permanent_Proof_txt  = (AppCompatTextView)rootview.findViewById(R.id.Permanent_Proof_txt);
        Income_Proof_txt  = (AppCompatTextView)rootview.findViewById(R.id.Income_Proof_txt);
        Work_Proof_txt_id  = (AppCompatTextView)rootview.findViewById(R.id.Work_Proof_txt_id);
        Photo_Proof_txt  = (AppCompatTextView)rootview.findViewById(R.id.Photo_Proof_txt);
        Signature_id_txt=(AppCompatTextView)rootview.findViewById(R.id.Signature_id_txt);


        progressDialog = new SpotsDialog(getActivity(), R.style.Custom);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "segoe_ui.ttf");
        Signature_id_txt.setTypeface(font);
        Identity_Proof_txt.setTypeface(font);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        coapplicantstatus = preferences.getString("coapp_emp", "");
        Log.i("TAG", "onCreateView:coapplicant_statuscheck "+coapplicantstatus);
       checkcondition();
        Document_check_lsit();

        rootview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


       /* if (user_type.equals("2")){
            Document_check_lsit();
        }*/

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView tv = new TextView(getActivity());
        tv.setLayoutParams(lparams);
        lparams.setMargins(10, 10, 10, 10);
        tv.setText(R.string.notess);
        tv.setTextSize(14);
        tv.setTypeface(font,Typeface.BOLD);
       /* Typeface fonts = Typeface.createFromAsset(getActivity().getAssets(), "segoe_ui.ttf");
        tv.setTypeface(fonts);*/
        tv.setTextColor(Color.parseColor("#D44D53"));
        check_list_name.addView(tv);

        TextView tvs = new TextView(getActivity());
        tvs.setLayoutParams(lparams);
        lparams.setMargins(10, 10, 10, 10);

        String first = "*"+" "  ;
        String firsttxt="<font color='#4D4D4D'>Marked documents are</font>";
        String next = "<font color='#D44D53'><b>mandatory document</b></font>";
        tvs.setText(Html.fromHtml(first +firsttxt+ " "+next));
        // tvs.setText("*"+" "  +"Marked documents are mandatory document");
        tvs.setTextSize(14);
        Typeface fontss = Typeface.createFromAsset(getActivity().getAssets(), "segoe_ui.ttf");
        tvs.setTypeface(fontss);
        tvs.setTextColor(Color.parseColor("#D44D53"));
        check_list_name.addView(tvs);


        Docum_ch_step1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean oneChecked = false;
                View v = null;


                count1 =0;
                Iterator iterator = doc_ar.keys();
                while (iterator.hasNext()) {

                    String key = (String) iterator.next();
                    list_key = new ArrayList<String>();


                    Log.e("the value", list_key.toString());
                    try {
                        response_iD_proof_comon = doc_ar.getJSONArray(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //  JSONObject req_type=doc_ar.getJSONObject("document_req");

                    outerloop:
                    for (int h = 0; h < response_iD_proof_comon.length(); h++) {
                        JSONObject J = null;
                        try {

                            J = response_iD_proof_comon.getJSONObject(h);
                            doc_ype_com = J.getJSONArray("doc_type_names");
                            JSONObject js=doc_ype_com.getJSONObject(h);
                            description=J.getString("nameforhtml");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("length of", String.valueOf(doc_ype_com.length()));

                        count = 0;
                        for (int i=0;i<doc_ype_com.length();i++) {


                            JSONObject rec5 = null;
                            try {
                                rec5 = doc_ype_com.getJSONObject(i);
                                documentre_type=rec5.getString("document_req");
                                checklist_name1 = rec5.getString("doc_typename");
                                enable_status = rec5.getString("enable_status");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if(documentre_type.equals("1"))
                            {

                                if (enable_status.equals("1")) {
                                    count= count + 1;
                                    // Log.e("c", String.valueOf(count));
                                    break ;
                                }else
                                {
                                    continue;
                                }
                            }else
                            {
                                count= count + 2;
                            }
                        }
                        // Log.e("count", String.valueOf(count));
                        if(count>=1)
                        {


                        }else
                        {
                            count1= count1 + 1;
                            Log.e("count1", String.valueOf(count1));
                            Toast.makeText(getActivity(), "select any one from "+description,Toast.LENGTH_SHORT).show();
                            break outerloop;
                        }
                        // Log.e("count1", String.valueOf(count1));
                    }
                }
                Log.e("count", String.valueOf(count));
                Log.e("count1", String.valueOf(count1));


                if(count1 ==0) {

                    if (count != 0 && count != count1) {

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                               // Document_Statues();

                                Intent intent = new Intent(getActivity(), Applicant_Doc_Details_revamp.class);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        }, 200);
                    } else {

                    }
                }

                //
            }
        });

        return rootview;
    }


    private void checkcondition(){

   /*     Intent intent=getIntent();
        get_jsonArray = intent.getStringExtra("jsonArray");
        applicant_count=intent.getStringExtra("applicantcount");
        property_identify=intent.getStringExtra("propertyidentify");
        Log.i("TAG", "Applicant_count_checkcondition: "+applicant_count);
        Log.i("TAG", "property_identify_checkcondition: "+property_identify);
        Log.i("TAG", "onCreate:json "+get_jsonArray);*/

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String get_jsonArray = preferences.getString("get_jsonArray", "");
        String applicant_count = preferences.getString("applicant_count", "");
        String property_identify = preferences.getString("property_identify", "");
        Log.e("TAG", "Applicant_count_checkcondition: "+applicant_count);
        Log.i("TAG", "property_identify_checkcondition: "+property_identify);
        Log.i("TAG", "onCreate:json "+get_jsonArray);



        try {
            JSONArray ja = new JSONArray(get_jsonArray);
            if (ja.length()>0) {
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject explrObject = ja.getJSONObject(i);
                    userstatus = explrObject.getString("applicant_name");
                    transaction_id=explrObject.getString("transaction_id");
                    emp_states=explrObject.getString("emp_states");
                    user_type=explrObject.getString("user_type");

                   /* if (user_type.equals("2")){
                        Document_check_lsit();
                    }*/

                    Log.i("TAG", "usertype status: " + user_type);
                }
            } else {
                Objs.a.ShowHideNoItems(getActivity(), true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void Document_check_lsit() {
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", transaction_id);
            J.put("employement_type", coapplicantstatus);
            J.put("applicant_type", "2");
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
                                        String document_req =js.getString("document_req");


                                        checklist_name(doc_ype_com,key,document_req);

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
                Toast.makeText(getActivity(), "Network error, try after some time",Toast.LENGTH_SHORT).show();
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
    private void checklist_name(final JSONArray doc_ype_com,String key,String document_req) {


        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (!list_key.contains(key))
        {

            TextView tv = new TextView(getActivity());
            tv.setLayoutParams(lparams);
            lparams.setMargins(10, 12, 10, 12);
            String star="<font color='#D44D53'>*</font>";
            list_key.add(key);
            if(document_req.equals("1"))
            {
                tv.setText(Html.fromHtml(key + " "+star));
            }else {
                tv.setText(Html.fromHtml(key));
            }
            //list_key.add(key);
            //tv.setText(key);
           // tv.setText(key);
            tv.setTextSize(18);
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "segoe_ui.ttf");
            tv.setTypeface(font,Typeface.BOLD);
            tv.setTypeface(font);
            tv.setTextColor(Color.parseColor("#012B5D"));
            check_list_name.addView(tv);


            LinearLayout.LayoutParams lpara = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView tvs = new TextView(getActivity());
            TextView tls=new TextView(getActivity());
              tls.setLayoutParams(lparams);
            //tvs.setLayoutParams(lparams);
            lpara.setMargins(10, 10, 10, 10);
            tvs.setTextSize(12);
            //  tls.setTextSize(18);
            Typeface fonts = Typeface.createFromAsset(getActivity().getAssets(), "segoe_ui.ttf");
            tvs.setTypeface(fonts);
            // tls.setTypeface(fonts);
            tvs.setText("Please Upload any one of the following");
            //   tls.setTextColor(Color.parseColor("#D34D53"));
            tvs.setTextColor(Color.parseColor("#D44D53"));
            // tvs.setBackgroundColor(Color.GREEN);
            check_list_name.addView(tvs);

           /* TextView tvs = new TextView(getActivity());
            TextView tls=new TextView(getActivity());
            tls.setLayoutParams(lparams);
            tvs.setLayoutParams(lparams);
            lparams.setMargins(10, 10, 10, 10);
            tvs.setTextSize(18);
            tls.setTextSize(18);
            Typeface fonts = Typeface.createFromAsset(getActivity().getAssets(), "segoe_ui.ttf");
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

            }*/





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
            TableRow row =new TableRow(getActivity());
            row.setId(k);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            final CheckBox checkBox = new CheckBox(getActivity());
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setId(k);
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "segoe_ui.ttf");
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
                                    String status = "0";
                                    document_format(target_id6,status);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        /*String status = "0";
                        document_format(target_id6,status);*/
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
                        Document_check_lsit1();
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

    private void Document_check_lsit1() {
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", transaction_id);
            J.put("employement_type", coapplicantstatus);
            J.put("applicant_type", "1");
            J.put("type_req", 0);
            J.put("status_flag", 1);
            Log.i("TAG", "Document_check_lsit:request "+J.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Request Applicant ", String.valueOf(J));

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


                                        //  checklist_name(doc_ype_com,key);

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
                Toast.makeText(getActivity(), "Network error, try after some time",Toast.LENGTH_SHORT).show();
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
    private void Document_Statues() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            //  J.put(Params.user_id, id);
            J.put("transaction_id", transaction_id);
            J.put("employement_type", coapplicantstatus);
            J.put("applicant_type", "2");
            J.put("type_req", 0);
            J.put("status_flag", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("the Document",J.toString());
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.validate_checklist, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("the reponse",response.toString());
                        try {


                            String status  = response.getString("status");
                            String validate  = response.getString("validate");
                            if(validate.equals("1"))
                            {

                                progressDialog.dismiss();
                                Intent intent = new Intent(getActivity(), Applicant_Doc_Details_revamp.class);
                                startActivity(intent);
                                getActivity().finish();
                            }else
                            {

                                Toast.makeText(getActivity(),"Please Check the mandatory Documents", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
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
                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
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
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
}