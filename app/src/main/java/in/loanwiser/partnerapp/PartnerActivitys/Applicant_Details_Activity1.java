package in.loanwiser.partnerapp.PartnerActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
import in.loanwiser.partnerapp.R;

public class Applicant_Details_Activity1 extends SimpleActivity {

    private Context mCon = this;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    View drawerView;
    private FloatingActionButton float_chat;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Home.class.getSimpleName();
    private AlertDialog progressDialog;
    String email,username,mobileno,id,step_status,status;
    String applicant_id,sub_taskid,transaction_id;
    AppCompatButton logout,leads_float_chat;
    AppCompatTextView no_leads_data;
    LinearLayout Ly_no_leads_data;
    LinearLayout Ly_contact,Ly_chat,Ly_Profile;
    MenuItem chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_applicant__details_1);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_applicant__details_1);
        initTools(R.string.listapp);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        float_chat = (FloatingActionButton)findViewById(R.id.float_chat);
        Ly_no_leads_data = (LinearLayout)findViewById(R.id.Ly_no_leads_data);
        leads_float_chat = (AppCompatButton)findViewById(R.id.leads_float_chat);

        float_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objs.ac.StartActivity(mCon, MainActivity_Add_Lead1.class);
                finish();
            }
        });
        leads_float_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objs.ac.StartActivity(mCon, MainActivity_Add_Lead1.class);
                finish();
            }
        });

        Account_Listings_Details();

    }

    private void Account_Listings_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(mCon));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LEAD_LIST_POST, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response details", String.valueOf(response));
                        try {
                            if(response.getBoolean(Params.status)){
                                JSONArray ja = response.getJSONArray(Params.applicationusers_arr);
                                if (ja.length()>0){
                                    Ly_no_leads_data.setVisibility(View.GONE);
                                    float_chat.setVisibility(View.VISIBLE);
                                    setAdapter(ja);
                                }
                            }else {
                                float_chat.setVisibility(View.GONE);
                                Ly_no_leads_data.setVisibility(View.VISIBLE);

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

    private void setAdapter(JSONArray ja) {
        Applicant_Details_Activity1.ListItemAdapter adapter = new Applicant_Details_Activity1.ListItemAdapter(mCon,ja);
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
        public Applicant_Details_Activity1.ListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_track_status, parent, false);
            return new Applicant_Details_Activity1.ListItemAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final Applicant_Details_Activity1.ListItemAdapter.ViewHolder holder, final int position) {
            try {

                J = getItem(position);
                mobileno =   J.getString(Params.mobileno);
                username =   J.getString(Params.username);
                applicant_id =   J.getString(Params.applicant_id);
                status =   J.getString(Params.status);
                step_status =   J.getString(Params.step_status);

                holder.type.setText(Objs.a.capitalize(applicant_id +" "+ "( "+username+" "+ mobileno+" )"));
                //  holder.doc_status.setText(status);
                holder.doc_steps.setText(step_status);
                Objs.a.NormalFontStyle(mCon,holder.type);
                //   Objs.a.NormalFontStyle(mCon,holder.doc_status);
                Objs.a.NormalFontStyle(mCon,holder.doc_steps);

                holder.Over_all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);
                        try {

                            email = J.getString(Params.email);
                            username =   J.getString(Params.username);
                            mobileno =   J.getString(Params.mobileno);
                            id =   J.getString(Params.id);
                            applicant_id =   J.getString(Params.applicant_id);
                            transaction_id =   J.getString(Params.transaction_id);
                            sub_taskid =   J.getString(Params.sub_taskid);
                            // String all = id +"\n"+ applicant_id +"\n"+sub_taskid+"\n"+transaction_id +"\n"+ Pref.getID(mCon);
                            // Objs.a.showToast(mCon,all);
                            //  Objs.a.showToast(mCon, id);

                            Applicant_Status(id);


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

            AppCompatTextView type,doc_steps,doc_status;
            ImageView v_Image;
            ProgressBar progressBar;
            AppCompatButton appCompatButtonSelect;
            CardView cardView;
            LinearLayout Over_all;
            View view;

            public ViewHolder(View itemView) {
                super(itemView);

                type  = (AppCompatTextView) itemView.findViewById(R.id.doc_typename_all);
                doc_steps  = (AppCompatTextView) itemView.findViewById(R.id.doc_steps);
                // doc_status  = (AppCompatTextView) itemView.findViewById(R.id.doc_status);
                //  v_Image = (ImageView) itemView.findViewById(R.id.image_Product);
                // progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);
                // cardView  = (CardView) itemView.findViewById(R.id.card_view);
                Over_all = (LinearLayout) itemView.findViewById(R.id.Over_all);

            }
        }
    }

    private void Applicant_Status(final String id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.user_id, id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.APP_ID_OTP_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getBoolean(Params.status)){
                                Objs.ac.StartActivityPutExtra(mCon, Home.class,
                                        Params.mobileno,mobileno,
                                        Params.user_id,id,
                                        Params.transaction_id,transaction_id,
                                        Params.username,username,
                                        Params.email,email);
                            }else{
                                Objs.a.showToast(mCon, "Please Contact Call center to Update the Details");
                               /* Objs.a.showToast(mCon, "Update Your Applicant Details");
                                Objs.ac.StartActivityPutExtra(mCon, Applicant_Entry1.class,
                                        Params.user_id,id,
                                        Params.applicant_id,applicant_id,
                                        Params.transaction_id,transaction_id,
                                        Params.sub_taskid,sub_taskid);
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
}
