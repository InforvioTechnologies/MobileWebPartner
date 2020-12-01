package in.loanwiser.partnerapp.Documents;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Document_Checklist_Details_type;

public class Applicant_Doc_Details extends SimpleActivity {

    private Context mCon = this;
    String doc_id,emp_state,type,applicant_name;
    private AppCompatTextView appl_id;
    private CardView app_id_card;
    private ProgressDialog pDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Applicant_Doc_Details.class.getSimpleName();
    private AlertDialog progressDialog;
    private String usertype;
    private String new_user_type,transaction_id,Applicant_type;
    MenuItem chat;
    JSONObject jsonobject_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_applicant__doc__details);
        applicant_name =  Objs.a.getBundle(this, Params.applicant_name);
        String document= applicant_name+"Document";
     //   Log.e("doc",document);
        initTools1(document);
      //  initTools1(applicant_name);
        progressDialog = new SpotsDialog(mCon, R.style.Custom);

        doc_id =  Objs.a.getBundle(this, Params.id);
        emp_state =  Objs.a.getBundle(this, Params.emp_state);
        Applicant_type =  Objs.a.getBundle(this, Params.user_type);
        transaction_id =  Objs.a.getBundle(this, Params.transaction_id);

        Pref.putAEID(mCon,Applicant_type);

        Pref.putDOC(mCon,doc_id);
        Pref.putTID(mCon,transaction_id);
        Pref.putEID(mCon,emp_state);
     //   Account_Listings_Details();
        Document_Details();


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

    private void Document_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();

            J.put("transaction_id", transaction_id);
            J.put("applicant_type", Applicant_type);
            J.put("employement_type", emp_state);
            J.put("type_request", 0);
            J.put("status_flag", 1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Applicant_Doc", String.valueOf(J));

        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.DOCUMENT_CHECK_LIST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant_Doc",response.toString());
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

                            }else {
                                Objs.a.ShowHideNoItems(mCon,true);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                      /*  Log.e("response", data);
                        try {
                            JSONArray ja = response.getJSONArray(Params.displayname);
                            new_user_type = response.getString(Params.user_type);
                            if (ja.length()>0){
                                // Objs.a.showToast(mCon, String.valueOf(object.getJSONArray(Params.products)));

                                setAdapter(ja);

                            }else {
                                Objs.a.ShowHideNoItems(mCon,true);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
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
        Objs.a.getRecyleview(this).setAdapter(adapter);
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
                Objs.a.NewNormalFontStyle(mCon,holder.class_name);

                String docuemt_upload= J.getString("docuemt_upload");

                String key = J.getString("key");
                holder.class_name.setText(key);

                JSONArray Proof_Array12 = jsonobject_2.getJSONArray(key);

                if(docuemt_upload.equals("1")){

                    holder.uploaded_yes.setImageDrawable(getResources().getDrawable(R.drawable.ic_right_done));

                }else {
                    holder.uploaded_yes.setImageDrawable(getResources().getDrawable(R.drawable.notdon));
                }

              /*  for (int i=0;i<Proof_Array12.length();i++) {
                    JSONObject J = null;
                    try {

                        J = Proof_Array12.getJSONObject(i);
                        JSONArray  doc_ype_com = J.getJSONArray("doc_type_names");

                        Log.e("doc_ype_com",doc_ype_com.toString());
                        if (doc_ype_com.length() > 0) {

                            for (int i1=0;i<Proof_Array12.length();i++) {
                                JSONObject J1 = null;
                                try {

                                    J1 = Proof_Array12.getJSONObject(i);

                                      if(J1.getString(Params.uploadstatus).equals("1")){

                                      holder.uploaded_yes.setImageDrawable(getResources().getDrawable(R.drawable.don));

                                      }else {
                                      holder.uploaded_yes.setImageDrawable(getResources().getDrawable(R.drawable.notdon));
                                      }

                                }
                             catch (JSONException e) {
                                e.printStackTrace();
                            }
                            }

                        } else {
                            Objs.a.ShowHideNoItems(mCon, true);
                        }
                        // checklist_name(doc_ype_com,key);
                        // setAdapter(doc_ype_com);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }*/

                holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.file));



                holder.card_view_class_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);

                        try {

                            String key = J.getString("key");

                            Pref.putDOCKEY(mCon,key);

                            JSONArray Proof_Array = jsonobject_2.getJSONArray(key);
                            String proof_String_JSonarray  = String.valueOf(Proof_Array);
                            Log.e("the proof_",Proof_Array.toString());

                            Intent intent = new Intent(Applicant_Doc_Details.this, Document_Details.class);
                            intent.putExtra("jsonArray", proof_String_JSonarray.toString());
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

            AppCompatTextView class_name;
            CardView card_view_class_name;
            View view;
            ImageView image_doc,uploaded_yes;

            public ViewHolder(View itemView) {
                super(itemView);

                class_name  = (AppCompatTextView) itemView.findViewById(R.id.class_name);
                image_doc  = (ImageView) itemView.findViewById(R.id.image_doc);
                uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);

                card_view_class_name  = (CardView) itemView.findViewById(R.id.card_view_class_name);

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
