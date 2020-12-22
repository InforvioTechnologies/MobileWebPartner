package in.loanwiser.partnerapp.Documents;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.BankStamentUpload.Upload_Activity_Bank;
import in.loanwiser.partnerapp.CameraActivity.DocGridView_List1;
import in.loanwiser.partnerapp.CameraActivity.MainActivity_IMG_Property2;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class Applicant_Doc_Details_revamp extends SimpleActivity {

    private Context mCon = this;
    String doc_id,emp_state,type,applicant_name;
    private AppCompatTextView appl_id;
    private CardView app_id_card;
    private ProgressDialog pDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Applicant_Doc_Details_revamp.class.getSimpleName();
    private AlertDialog progressDialog;
    private String usertype,property_identified;
    private String new_user_type,transaction_id,Applicant_type;
    MenuItem chat;
    JSONObject jsonobject_2;
    String emp_states;

    CardView Applicant_ly,Co_Applicant_ly,Property;
    LinearLayout Applicant_ly1,Co_Applicant_ly1,Property1;

    String Applicant_what,co_app,Applicant_what1,Applicant_what2;
    AppCompatTextView In_Progress_txt,co_applicant_txt,applicant_txt;
    RecyclerView imagelist1,imagelist;

    AppCompatButton submit_update_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_applicant__doc__details_revamp);
       // applicant_name =  Objs.a.getBundle(this, Params.applicant_name);
        String document= "Document Upload";
     //   Log.e("doc",document);
        initTools1(document);
      //  initTools1(applicant_name);
        progressDialog = new SpotsDialog(mCon, R.style.Custom);

        Applicant_ly = (CardView) findViewById(R.id.Applicant_ly);
        Co_Applicant_ly = (CardView) findViewById(R.id.Co_Applicant_ly);
        Property = (CardView) findViewById(R.id.Property);

        Applicant_ly1 = (LinearLayout) findViewById(R.id.Applicant_ly1);
        Co_Applicant_ly1 = (LinearLayout) findViewById(R.id.Co_Applicant_ly1);
        Property1 = (LinearLayout) findViewById(R.id.Property1);


        In_Progress_txt = (AppCompatTextView) findViewById(R.id.In_Progress_txt);
        co_applicant_txt = (AppCompatTextView) findViewById(R.id.co_applicant_txt);
        applicant_txt = (AppCompatTextView) findViewById(R.id.applicant_txt);

        submit_update_status = (AppCompatButton) findViewById(R.id.submit_update_status);

        imagelist1 = (RecyclerView) findViewById(R.id.recycler_view_property);
        imagelist = (RecyclerView) findViewById(R.id.recycler_view);

        co_app = Pref.getCoAPPAVAILABLE(getApplicationContext());
        property_identified = Pref.getProperty_id(getApplicationContext());
        Log.i(TAG, "onCreate:property_identified "+property_identified);
        applicant_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
        co_applicant_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
        In_Progress_txt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
        Log.e(TAG, "onCreate:property_identified "+property_identified);
        if(co_app.equals("1"))
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

        if (property_identified != null && property_identified.equals("1")) {
            Property1.setVisibility(View.VISIBLE);

        }else {
            Property1.setVisibility(View.GONE);

        }


        submit_update_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Document_Statues();
            }
        });



        Applicant_what ="Applicant";
        Applicant_what1 ="";
        Applicant_what2 ="";
       Account_Listings_Details();

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

                        Log.e("the reponse",response.toString());
                        try {

                            JSONObject Response = response.getJSONObject("reponse");
                            JSONObject jsonObject1 = Response.getJSONObject(Params.application_form);
                            String user_id  = Response.getString(Params.user_id);
                            String jsonStringObj  = String.valueOf(jsonObject1);
                            JSONArray jsonArray = Response.getJSONArray(Params.emp_states);

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
                                            Document_Details();
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
                                            Document_Details();

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
                                            Document_Details1();

                                        }



                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }

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

    private void Document_Statues() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            //  J.put(Params.user_id, id);
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("the Document",J.toString());
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.validate_upldoc, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("the reponse",response.toString());
                        try {


                            String status  = response.getString("status");
                           if(status.equals("success"))
                           {
                               progressDialog.dismiss();
                               Documnet_upload_Status();
                           }else
                           {
                               submit_update_status.setEnabled(false);
                               Toast.makeText(getApplicationContext(),"Please Upload the mandatory Documents", Toast.LENGTH_SHORT).show();
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

    private void Documnet_upload_Status() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.transaction_id, Pref.getTRANSACTIONID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Documnet_upload_Status", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.UPDATE_STATUS_DOC, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Documnet_upload_Status1", String.valueOf(response));
                        //{"request":{"transaction_id":"10194"},"response":true,"status":"success"}
                        try {

                            if(response.getString(Params.status).equals("success")){

                                if(response.getBoolean("doc_uploadstatus")){
                                    // Objs.a.showToast( mCon,"Document uploaded");
                                  //  Objs.ac.StartActivity(mCon, Home.class);
                                    Intent intent = new Intent(Applicant_Doc_Details_revamp.this, Home.class);
                                    startActivity(intent);

                                    finish();
                                }else {

                                    // Objs.a.showToast(mCon,"You have not yet Uploaded any Document for Loan Process "+ "\n" + "Please Upload the Document");
                                    Toast.makeText(getApplicationContext(),"You have not yet Uploaded any Document for Loan Process", Toast.LENGTH_SHORT).show();

                                }

                            /*  Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                              finish();*/
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

    private void Document_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();

            J.put("transaction_id", transaction_id);
            J.put("applicant_type", Applicant_type);
            J.put("employement_type", emp_states);
            J.put("type_request", 0);
            J.put("status_flag", 1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Applicant Document", String.valueOf(J));

        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.DOCUMENT_CHECK_LIST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant_Doc Response",response.toString());
                        String data = String.valueOf(response);
                        //   Objs.a.showToast(mCon, data);

                        try {
                            JSONObject jsonObject1 = response.getJSONObject("response");
                            JSONArray jsonArray = jsonObject1.getJSONArray("key_arr");
                            jsonobject_2 = jsonObject1.getJSONObject("document_arr");
                            Log.e("KEY_ARRar",jsonArray.toString());
                            Log.e("jsonobject_2",jsonobject_2.toString());
                            if (jsonArray.length()>0){
                                // Objs.a.showToast(mCon, String.valueOf(object.getJSONArray(Params.products)));

                                setAdapter(jsonArray);
                                progressDialog.dismiss();
                            }else {
                                Objs.a.ShowHideNoItems(mCon,true);
                                progressDialog.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                       // progressDialog.dismiss();

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

    private void Document_Details1() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.checklist_code, Params.EMITRA);
            J.put(Params.transaction_id, transaction_id);
            J.put(Params.usertype, "0");
            J.put(Params.type, "1");
            J.put(Params.applicant_empstatus, "11");
            Log.e("the value of J", String.valueOf(J));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_DOCUMENT_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        //   Objs.a.showToast(mCon, data);

                        try {
                            JSONArray ja = response.getJSONArray(Params.displayname);

                            Log.d("the value of ja", String.valueOf(ja));
                            if (ja.length()>0){
                                //     Objs.a.showToast(mCon, String.valueOf(ja));
                                setAdapter1(ja);
                            }else {
                                Objs.a.ShowHideNoItems(mCon,true);
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
        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    private void setAdapter(JSONArray ja) {
        ListItemAdapter adapter = new ListItemAdapter(mCon,ja);
        imagelist1.setVisibility(View.GONE);
        imagelist.setVisibility(View.VISIBLE);




       /// RecyclerView recyclerView = (RecyclerView) findViewById(adhoc.app.applibrary.R.id.recycler_view);
       // imagelist.setLayoutManager(llm);
        imagelist.setHasFixedSize(true);
        imagelist.setNestedScrollingEnabled(false);
        imagelist.setLayoutManager(new LinearLayoutManager(this));
        imagelist.setAdapter(adapter);
         //Objs.a.getRecyleview(this).setAdapter(adapter);
    }

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
                    .inflate(R.layout.ly_application_document_details, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);

                holder.class_name.setText(Objs.a.capitalize(J.getString("key")));
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

                            Pref.putDOCKEY(mCon,key);
                            String document_req = J.getString("document_req");
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

            AppCompatTextView class_name,mandatory_do;
            CardView card_view_class_name;
            View view;
            ImageView image_doc,uploaded_yes;
            Button uploadbtn;

            public ViewHolder(View itemView) {
                super(itemView);

                class_name  = (AppCompatTextView) itemView.findViewById(R.id.class_name);
                mandatory_do  = (AppCompatTextView) itemView.findViewById(R.id.mandatory_do);
                image_doc  = (ImageView) itemView.findViewById(R.id.image_doc);
                uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);
                uploadbtn=(Button)itemView.findViewById(R.id.uploadbtn);

                card_view_class_name  = (CardView) itemView.findViewById(R.id.card_view_class_name);

            }
        }
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
                holder.doc_typename.setText((J.getString(Params.doc_typename)));
                Objs.a.NewNormalFontStyle(mCon,holder.doc_typename);
                holder.doc_typename_all.setText((J.getString(Params.doc_typename)));
                Objs.a.NewNormalFontStyle(mCon,holder.doc_typename_all);
                holder.count__all.setText(J.getString(Params.upload_count));

                if(J.getString(Params.upload_status).equals("1")){
                    holder.Over_all.setVisibility(View.VISIBLE);
                    holder.Ly_first.setVisibility(View.GONE);
                }else{
                    holder.Over_all.setVisibility(View.GONE);
                    holder.Ly_first.setVisibility(View.VISIBLE);
                }

                holder.Over_all.setOnClickListener(new View.OnClickListener() {
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

                           /*  Objs.a.showToast(mCon, "Gridview  " +id +"\n"+ transaction_id +"\n"+ doc_name
                                    +"\n"+  docid +"\n"+class_id +"\n"+
                                    user_type );*/


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


                            /*Objs.a.showToast(mCon, "Upload  " +id +"\n"+ transaction_id +"\n"+ doc_typename
                                    +"\n"+  docid +"\n"+class_id +"\n"+
                                   user_type );
*/
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

            AppCompatTextView doc_typename,count;
            AppCompatTextView doc_typename_all,count__all;
            CardView card_view_doc_typename;
            LinearLayout lay_image_view,Ly_first,Over_all;
            View view;

            public ViewHolder(View itemView) {
                super(itemView);

                doc_typename  = (AppCompatTextView) itemView.findViewById(R.id.doc_typename);
                doc_typename_all  = (AppCompatTextView) itemView.findViewById(R.id.doc_typename_all);
                count__all  = (AppCompatTextView) itemView.findViewById(R.id.count__all);
                card_view_doc_typename  = (CardView) itemView.findViewById(R.id.card_view_doc_typename);
                Ly_first  = (LinearLayout) itemView.findViewById(R.id.Ly_first);
                Over_all  = (LinearLayout) itemView.findViewById(R.id.Over_all);

            }
        }
    }

    @Override
    public void onBackPressed() {

        Pref.removeDOC(mCon);
        Pref.removeTID(mCon);
        Pref.removeEID(mCon);
        Pref.removeAEID(mCon);
        super.onBackPressed();

    }
}
