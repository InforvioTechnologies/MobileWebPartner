package in.loanwiser.partnerapp.Documents;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
import in.loanwiser.partnerapp.CameraActivity.DocGridView_List1;
import in.loanwiser.partnerapp.CameraActivity.MainActivity_IMG_Property2;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class Applicant_Doc_Details_Property extends SimpleActivity {

    private Context mCon = this;
    String doc_id,emp_state,type,Pr_type;
    private AppCompatTextView appl_id;
    private CardView app_id_card;
    private ProgressDialog pDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Applicant_Doc_Details_Property.class.getSimpleName();
    private AlertDialog progressDialog;
    private String usertype,applicant_name,applicant_name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_applicant__doc__details__property);
        applicant_name =  Objs.a.getBundle(this, Params.applicant_name);
        Pref.putapplicant_name(mCon,applicant_name);
       // initTools1(applicant_name);
        String document= applicant_name+" Document";
        initTools1(document);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        // A_id + "," + A_type + "," + A_Pr_type + "," + A_empstatue;
        String all =  Pref.getPRO(mCon);
        String CurrentString = all;
        String[] separated = CurrentString.split(",");
        doc_id = separated[0];
        type = separated[1];
        Pr_type = separated[2];
        emp_state = separated[3];


        //  doc_id =  Objs.a.getBundle(this, Params.id);
        ///  emp_state =  Objs.a.getBundle(this, Params.emp_state);
        //  type =  Objs.a.getBundle(this, Params.user_type);
        //   Pr_type =  Objs.a.getBundle(this, Params.type);

        //  Pref.putAEID(mCon,type);
        //    Objs.a.showToast(mCon, doc_id+"\n" + emp_state+"\n" +type +"\n" +Pr_type);
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
            J.put(Params.type, Pr_type);
            J.put(Params.applicant_empstatus, emp_state);
            Log.d("the value of J", String.valueOf(J));

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
        public ListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_application_doc_details_list, parent, false);
            return new ListItemAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ListItemAdapter.ViewHolder holder, final int position) {
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
}
