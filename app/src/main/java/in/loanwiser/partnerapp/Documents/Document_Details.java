package in.loanwiser.partnerapp.Documents;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import in.loanwiser.partnerapp.CameraActivity.DocGridView_List;
import in.loanwiser.partnerapp.CameraActivity.ManiActivity_Image2;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class Document_Details extends SimpleActivity {

    private Context mCon = this;
    String JSON;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private AppCompatTextView prop_name,prop_name_matatory,upload_any_one;
    private AppCompatButton submit_update_status;
    String doc_id,transaction_id,applicant_empstatus,classname1,type;
    private AlertDialog progressDialog;
    private String TAG = Document_Details.class.getSimpleName();
    MenuItem chat;
    String jsonArray,Proof_DOC_KEY_,document_req;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        Objs.a.setStubId(this, R.layout.activity_document__details);
        classname1 =  Objs.a.getBundle(Document_Details.this, Params.class_name);
        Pref.putapplicant_name(mCon,classname1);

        prop_name =(AppCompatTextView)findViewById(R.id.prop_name);
        prop_name_matatory =(AppCompatTextView)findViewById(R.id.prop_name_matatory);
        upload_any_one =(AppCompatTextView)findViewById(R.id.upload_any_one);
        submit_update_status =(AppCompatButton)findViewById(R.id.submit_update_status1);


        doc_id =  Pref.getDOC(mCon);
        transaction_id = Pref.getTID(mCon);
        applicant_empstatus =  Pref.getEID(mCon);
        Proof_DOC_KEY_ =  Pref.getDOCKEY(mCon);
        initTools1("Document Upload");

        prop_name.setText(Proof_DOC_KEY_);
        Intent intent = getIntent();
        jsonArray = intent.getStringExtra("jsonArray");
        document_req = intent.getStringExtra("document_req");

        if(document_req.equals("0"))
        {
            prop_name_matatory.setText("(Highly Increases Loan Approval)");
            prop_name_matatory.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));

        }else
        {
            prop_name_matatory.setText("(Mandatory Document)");
            prop_name_matatory.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.green));
        }
        upload_any_one.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.pending_color));
        type =  Pref.getAEID(mCon);

        submit_update_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("the value","y not working");
                Intent intent = new Intent(mCon, Applicant_Doc_Details_revamp.class);
                startActivity(intent);
                finish();
            }
        });

       /* try {
            JSONArray ja = new JSONArray(jsonArray);
            for (int i=0;i<ja.length();i++) {
                JSONObject J = null;
                try {

                    J = ja.getJSONObject(i);
                    JSONArray  doc_ype_com = J.getJSONArray("doc_type_names");

                    Log.e("doc_ype_com",doc_ype_com.toString());
                    if (doc_ype_com.length() > 0) {
                        setAdapter(doc_ype_com);
                    } else {
                        Objs.a.ShowHideNoItems(mCon, true);
                    }
                   // checklist_name(doc_ype_com,key);
                   // setAdapter(doc_ype_com);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
*/

        Document_Details();
      //  Document_Details(transaction_id,applicant_empstatus,doc_id);

    }

    private void Document_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            //  J.put(Params.checklist_code, Params.EMITRA);
          /*  J.put("transaction_id", "11465");
            J.put("applicant_type", "1");
            J.put("employement_type", "1");*/

              J.put("transaction_id", transaction_id);
            J.put("applicant_type", type);
             J.put("employement_type", applicant_empstatus);
            J.put("type_req", 0);
            J.put("status_flag", 1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Applicant_Doc_1", String.valueOf(J));

        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.DOCUMENT_CHECK_LIST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("response",response.toString());
                        String data = String.valueOf(response);
                        //   Objs.a.showToast(mCon, data);

                        try {
                            JSONObject jsonObject1 = response.getJSONObject("response");
                            JSONArray jsonArray = jsonObject1.getJSONArray("key_arr");
                            JSONObject jsonobject_2 = jsonObject1.getJSONObject("document_arr");

                            JSONArray Proof_Array = jsonobject_2.getJSONArray(Proof_DOC_KEY_);

                            for (int i=0;i<Proof_Array.length();i++) {
                                JSONObject J = null;
                                try {

                                    J = Proof_Array.getJSONObject(i);
                                    JSONArray  doc_ype_com = J.getJSONArray("doc_type_names");

                                    Log.e("doc_ype_com",doc_ype_com.toString());
                                    if (doc_ype_com.length() > 0) {
                                        setAdapter(doc_ype_com);
                                    } else {
                                        Objs.a.ShowHideNoItems(mCon, true);
                                    }
                                    // checklist_name(doc_ype_com,key);
                                    // setAdapter(doc_ype_com);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
                VolleyLog.d(TAG, "Error: " + error.getMessage());
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
        public ListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_application_doc_details_list, parent, false);
            return new ListItemAdapter.ViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(final ListItemAdapter.ViewHolder holder, final int position) {
            try {
                J = getItem(position);
                holder.Over_all.setVisibility(View.GONE);

               String enable_status = J.getString("enable_status");

               if(enable_status.equals("1"))
               {
                   holder.doc_typename.setText((J.getString("doc_typename")));
                   Objs.a.NewNormalFontStyle(mCon,holder.doc_typename);
                   holder.doc_typename_all.setText((J.getString("doc_typename")));
                   Objs.a.NewNormalFontStyle(mCon,holder.doc_typename_all);

                   holder.count__all.setText(J.getString("doc_count"));

                   if(J.getString("upload_status").equals("1")){
                       holder.Over_all.setVisibility(View.VISIBLE);
                       holder.Ly_first.setVisibility(View.GONE);
                   }else{
                       holder.Over_all.setVisibility(View.GONE);
                       holder.Ly_first.setVisibility(View.VISIBLE);

                   }
               }else
               {
                   holder.card_view_doc_typename.setVisibility(View.GONE);
               }



                holder.Over_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);
                        try {

                         //  String id =   J.getString(Params.id);
                            String doc_name =   J.getString(Params.doc_typename);
                            String doc_typename =   J.getString("doc_typename");
                            String docid =   J.getString("legal_docid");
                            String docid1 =   J.getString("docid");
                            String class_id =   J.getString(Params.class_id);
                            String user_type =    Pref.getAEID(mCon);
                            String transaction_id =   J.getString("transaction_id");

                            // Objs.a.showToast(mCon, id +"\n"+ transaction_id +"\n"+ doc_name
                            //         +"\n"+  docid +"\n"+class_id +"\n"+
                            //         user_type );
                            Log.e("doc_typename",doc_typename);
                            Log.e("legalid",docid);
                            Log.e("transaction_id",transaction_id);

                            Objs.ac.StartActivityPutExtra(mCon, DocGridView_List.class,
                                    Params.class_id,class_id,
                                    Params.user_type,user_type,
                                    Params.transaction_id,transaction_id,
                                    Params.doc_id,docid,Params.docid1,docid1,
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
                          //  String id =   J.getString(Params.id);
                            String doc_typename =   J.getString("doc_typename");
                            String docid =   J.getString("legal_docid");
                          //  String class_id =   J.getString(Params.class_id);
                          //  String user_type =  Pref.getAEID(mCon);
                            String transaction_id =   J.getString("transaction_id");

                            Log.e("doc_typename",doc_typename);
                            Log.e("legalid",docid);
                            Log.e("transaction_id",transaction_id);

                            Objs.ac.StartActivityPutExtra(mCon, ManiActivity_Image2.class, Params.doc_typename,doc_typename,
                                    Params.docid,docid,Params.transaction_id,transaction_id);
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
}
