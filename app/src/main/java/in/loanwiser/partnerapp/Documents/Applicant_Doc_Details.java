package in.loanwiser.partnerapp.Documents;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

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
    private String new_user_type;
    MenuItem chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_applicant__doc__details);
        applicant_name =  Objs.a.getBundle(this, Params.applicant_name);
        String document= applicant_name+" Document";
     //   Log.e("doc",document);
        initTools1(document);
      //  initTools1(applicant_name);
        progressDialog = new SpotsDialog(this, R.style.Custom);

        doc_id =  Objs.a.getBundle(this, Params.id);
        emp_state =  Objs.a.getBundle(this, Params.emp_state);
        type =  Objs.a.getBundle(this, Params.user_type);

        Pref.putAEID(mCon,type);
        Document_Details(doc_id,emp_state,type);



    }

    private void Document_Details(String id, String emp_state,String type) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.checklist_code, Params.EMITRA);
            J.put(Params.transaction_id, id);
            J.put(Params.usertype, type);
            J.put(Params.applicant_empstatus, emp_state);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Applicant_Doc", String.valueOf(J));

        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_DOCUMENT_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String data = String.valueOf(response);
                        //   Objs.a.showToast(mCon, data);


                        Log.e("response", data);
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
                        }
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

                holder.class_name.setText(Objs.a.capitalize(J.getString(Params.class_name)));
                Objs.a.NewNormalFontStyle(mCon,holder.class_name);

                if(J.getString(Params.class_name).contains("Signature Verification Document")){
                    holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.file));
                }else if(J.getString(Params.class_name).contains("Address Proof")){
                    holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.file));
                }else if(J.getString(Params.class_name).contains("Identify Proof")){
                    holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.file));
                }else if(J.getString(Params.class_name).contains("Income Proof")){
                    holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.file));
                }else if(J.getString(Params.class_name).contains("Existing Loan Documents")){
                    holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.file));
                }else if(J.getString(Params.class_name).contains("Account Statement")){
                    holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.file));
                }else if(J.getString(Params.class_name).contains("ITR Document")){
                    holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.file));
                }else{
                    holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.file));
                }

                if(J.getString(Params.uploadstatus).equals("1")){

                    holder.uploaded_yes.setImageDrawable(getResources().getDrawable(R.drawable.don));


                }/*else {
                    holder.uploaded_yes.setImageDrawable(getResources().getDrawable(R.drawable.notdone));
                }*/

                holder.card_view_class_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);

                        try {

                            String id = J.getString(Params.id);
                            String class_name1 = J.getString(Params.class_name);
                            String transaction_id = doc_id;
                            String applicant_empstatus = emp_state;


                            Pref.putDOC(mCon,id);
                            Pref.putTID(mCon,transaction_id);
                            Pref.putEID(mCon,applicant_empstatus);

                            String all = id + "," + class_name1 + "," + transaction_id + "," + applicant_empstatus
                                    + "," + new_user_type;


                            Log.e("card_view_class_name", all);

                            //    Objs.a.showToast(mCon, String.valueOf(J));

                            Objs.ac.StartActivityPutExtra(mCon,Document_Details.class,
                                    Params.user_type,new_user_type,
                                    Params.class_name,class_name1);
                            //Objs.ac.StartActivity(mCon,DemoActivity.class);

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
