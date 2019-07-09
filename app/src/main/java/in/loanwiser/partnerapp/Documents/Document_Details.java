package in.loanwiser.partnerapp.Documents;

import android.app.AlertDialog;
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
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class Document_Details extends SimpleActivity {

    private Context mCon = this;
    String JSON;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private AppCompatTextView choose_one;
    String doc_id,transaction_id,applicant_empstatus,classname1,type;
    private AlertDialog progressDialog;
    private String TAG = Document_Details.class.getSimpleName();
    MenuItem chat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        Objs.a.setStubId(this, R.layout.activity_document__details);

        classname1 =  Objs.a.getBundle(Document_Details.this, Params.class_name);
        Pref.putapplicant_name(mCon,classname1);

        initTools1(classname1);
        choose_one =(AppCompatTextView)findViewById(R.id.choose_one);
        Objs.a.NormalFontStyle(mCon,choose_one);

        doc_id =  Pref.getDOC(mCon);
        transaction_id = Pref.getTID(mCon);
        applicant_empstatus =  Pref.getEID(mCon);
        type =  Pref.getAEID(mCon);

        Document_Details(transaction_id,applicant_empstatus,doc_id);

    }

    private void Document_Details(String transaction_id, String applicant_empstatus, String spec_id) {
        final JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.transaction_id, transaction_id);
            J.put(Params.applicant_empstatus, applicant_empstatus);
            J.put(Params.spec_id, spec_id);
            J.put(Params.user_type, type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Document_Details", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.SINGLE_DOCUMENT_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String data = String.valueOf(response);
                        try {
                            JSONObject object = response.getJSONObject(Params.displayname);
                            JSONArray ja = object.getJSONArray(Params.singlelist);
                            if (ja.length()>0){
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
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
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
                            String doc_typename =   J.getString(Params.doc_typename);
                            String docid =   J.getString(Params.doc_id);
                            String class_id =   J.getString(Params.class_id);
                            String user_type =    Pref.getAEID(mCon);
                            String transaction_id =   J.getString(Params.transaction_id);

                            // Objs.a.showToast(mCon, id +"\n"+ transaction_id +"\n"+ doc_name
                            //         +"\n"+  docid +"\n"+class_id +"\n"+
                            //         user_type );


                            Objs.ac.StartActivityPutExtra(mCon, DocGridView_List.class,
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
                            String user_type =  Pref.getAEID(mCon);
                            String transaction_id =   J.getString(Params.transaction_id);


                           /* Objs.a.showToast(mCon, id +"\n"+ transaction_id +"\n"+ doc_typename
                                    +"\n"+  docid +"\n"+class_id +"\n"+
                                   user_type );*/
                           String all =  id +"\n"+ transaction_id +"\n"+ doc_typename
                                    +"\n"+  docid +"\n"+class_id +"\n"+
                                    user_type;

                            Log.e("Document_Details", all);

                            Objs.ac.StartActivityPutExtra(mCon, ManiActivity_Image2.class, Params.id,id
                                    , Params.doc_typename,doc_typename, Params.docid,docid
                                    , Params.class_id,class_id, Params.user_type,user_type,
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
}
