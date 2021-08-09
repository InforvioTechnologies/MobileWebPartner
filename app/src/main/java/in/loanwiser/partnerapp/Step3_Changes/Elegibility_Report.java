package in.loanwiser.partnerapp.Step3_Changes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import in.loanwiser.partnerapp.BankStamentUpload.BankAnalysis;
import in.loanwiser.partnerapp.BankStamentUpload.BankAnalysis1;
import in.loanwiser.partnerapp.Documents.Document_Availability_Check;
import in.loanwiser.partnerapp.PDF_Viewer.MainActivity;
import in.loanwiser.partnerapp.PartnerActivitys.Submitsuccess_Activity;
import in.loanwiser.partnerapp.Payment.Payment_Sucess_Screen;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.CRIF_Report_Activity_PDF_View;

public class Elegibility_Report extends SimpleActivity {

    CardView Bank_statement_eligible;
    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    AppCompatButton view_eligibility_report,banK_analysis,submit_update_status;
    String Eligibility_Report;
    AppCompatImageView crif_report_view_down,crid_view_up;
    LinearLayout Crif_Repoert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_elegibility_report);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_elegibility_report);
        initTools(R.string.elegibility);
        progressDialog = new SpotsDialog(mCon, R.style.Custom);
        Bank_statement_eligible = (CardView) findViewById(R.id.Bank_statement_eligible);
        view_eligibility_report = findViewById(R.id.view_eligibility_report);
        crif_report_view_down = findViewById(R.id.crif_report_view_down);
        submit_update_status = findViewById(R.id.submit_update_status);
        crid_view_up = findViewById(R.id.crid_view_up);
        Crif_Repoert = findViewById(R.id.Crif_Repoert);
        banK_analysis = findViewById(R.id.banK_analysis);

       /* ListItemAdapter adapter = new ListItemAdapter(mCon,null);
        Objs.a.getRecyleview(this).setAdapter(adapter);*/
      //  Document_Details();
        Document_Details();
        view_eligibility_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String report="Elegibility Report";
                Intent intent = new Intent(Elegibility_Report.this, MainActivity.class);
                intent.putExtra("viability_report_URL", Eligibility_Report);
                intent.putExtra("report", report);
                startActivity(intent);
            }
        });

        banK_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Elegibility_Report.this, BankAnalysis1.class);
                //  Intent in=new Intent(context,BankAnalysis.class);
                intent.putExtra("adapter","upload");
                startActivity(intent);
            }
        });

        submit_update_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_Status();

            }
        });


        crif_report_view_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Document_Details();
                crif_report_view_down.setVisibility(View.GONE);
                crid_view_up.setVisibility(View.VISIBLE);
                Crif_Repoert.setVisibility(View.VISIBLE);
            }
        });
        crid_view_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crif_report_view_down.setVisibility(View.VISIBLE);
                crid_view_up.setVisibility(View.GONE);
                Crif_Repoert.setVisibility(View.GONE);
            }
        });


    }

    private void Document_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();

            J.put("trans_id", Pref.getTRANSACTIONID(getApplicationContext()));
            //  J.put("trans_id","53259");
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            //  J.put("user_id","51647");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Report Request ",String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Report_Activity, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant_Doc",response.toString());
                        String data = String.valueOf(response);
                        //   Objs.a.showToast(mCon, data);

                        try {


                            Log.e("Report response",String.valueOf(response));
                            String report_statues = response.getString("status");
                             Eligibility_Report = response.getString("eligibility_report");
                            Log.e("Eligibility_Report",String.valueOf(Eligibility_Report));
                            if(report_statues.equals("success"))
                            {
                                JSONArray jsonObject1 = response.getJSONArray("crif_report");

                                if (jsonObject1.length()>0){
                                    // Objs.a.showToast(mCon, String.valueOf(object.getJSONArray(Params.products)));
                                    setAdapter(jsonObject1);

                                }
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
                VolleyLog.d("TAG", "Error: " + error.getMessage());
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

    private void upload_Status() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.transaction_id, Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("comp_status", "3");
            J.put("subcomp_status", "5");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("submit_loanwiser", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.status_update, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Documnet_upload_Status();
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
    private void upload_Status1() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.transaction_id, Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("comp_status", "4");
            J.put("subcomp_status", "0");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("submit_loanwiser", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.status_update, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Intent intent = new Intent(Elegibility_Report.this, Submitsuccess_Activity.class);
                        startActivity(intent);
                        finish();

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

    private void Documnet_upload_Status() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.transaction_id, Pref.getTRANSACTIONID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("submit_loanwiser", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.submit_loanwiser, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Documnet_upload_Status1", String.valueOf(response));
                        //{"request":{"transaction_id":"10194"},"response":true,"status":"success"}
                        try {

                            if(response.getString(Params.status).equals("success")){

                                upload_Status1();

                            }else {
                                Toast.makeText(getApplicationContext(),"Something went wrong, Please check!!!", Toast.LENGTH_SHORT).show();

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
                    .inflate(R.layout.ly_crif_view_activity_step3, parent, false);
            return new ListItemAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ListItemAdapter.ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);

                holder.class_name.setText(Objs.a.capitalize(J.getString("name")));
                Objs.a.NewNormalFontStyle(mCon,holder.class_name);
                holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.ic_green_tick));

                holder.REPORT_View.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);

                        try {

                            String viability_report_URL = J.getString("url");
                            String report="CRIF Report";
                            Log.e("CRIF Report",viability_report_URL);
                            Intent intent = new Intent(Elegibility_Report.this, MainActivity.class);
                            intent.putExtra("viability_report_URL", viability_report_URL);
                            intent.putExtra("report", report);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            } catch (NullPointerException e) {
               // Objs.a.showToast(mCon, e.toString());
                Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();

                Log.e("CRIF Report issu",e.toString());

            } catch (Exception e) {
                Objs.a.showToast(mCon, e.toString());
                Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
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
            AppCompatButton REPORT_View;

            public ViewHolder(View itemView) {
                super(itemView);

                class_name  = (AppCompatTextView) itemView.findViewById(R.id.In_Progress);
                image_doc  = (ImageView) itemView.findViewById(R.id.image_doc);
                uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);

                card_view_class_name  = (CardView) itemView.findViewById(R.id.card_view_class_name);
                REPORT_View =(AppCompatButton) itemView.findViewById(R.id.REPORT_View);

            }
        }
    }
}