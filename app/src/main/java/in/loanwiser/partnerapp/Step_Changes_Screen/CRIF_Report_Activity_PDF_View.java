package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rajat.pdfviewer.PdfViewerActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.Old_Partner.Home_Old;
import in.loanwiser.partnerapp.BankStamentUpload.Doc_ImageView_Bank;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;

import in.loanwiser.partnerapp.PDF_Viewer.MainActivity;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class CRIF_Report_Activity_PDF_View extends SimpleActivity {

    private Context mCon = this;
    String doc_id,emp_state,type,applicant_name;
    private AppCompatTextView appl_id;
    private CardView app_id_card;
    private ProgressDialog pDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = CRIF_Report_Activity_PDF_View.class.getSimpleName();
    private AlertDialog progressDialog;
    private String usertype;
    private String new_user_type,transaction_id,applicant_id;
    MenuItem chat;
    JSONObject jsonobject_2;

    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    PermissionUtils permissionUtils;
    Toolbar toolbar;
    LinearLayout credit_card_app,co_applicant_;
    AppCompatTextView cr_app1,cr_app2;
    Typeface font;
    String[] SALARY_Method,Salary_Proof,Residence_Type_SA,Employe_ID_SA,PAN_ID_SA,Other_Earning_SA;
    ArrayAdapter<String> Other_Earning_Adapter;
    Spinner applicant_credite_card_spinner,co_applicant_Spinner;
    String  credit_issued_id,credit_issued_value,Report_ID,Order_ID;
    AppCompatButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_simple);

       /*  toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("CRIF Report Activity");
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // perform whatever you want on back arrow click
            }
        });*/
        Objs.a.setStubId(this, R.layout.activity_applicant__doc__details);
        initTools(R.string.crif_report);
        progressDialog = new SpotsDialog(mCon, R.style.Custom);
        permissionUtils = new PermissionUtils();

        credit_card_app = (LinearLayout) findViewById(R.id.credit_card_app);
        co_applicant_ = (LinearLayout) findViewById(R.id.co_applicant_);
        cr_app1 = (AppCompatTextView) findViewById(R.id.cr_app1);
        cr_app2 = (AppCompatTextView) findViewById(R.id.cr_app2);

        applicant_credite_card_spinner = (Spinner) findViewById(R.id.applicant_credite_card_spinner);
        co_applicant_Spinner = (Spinner) findViewById(R.id.co_applicant_Spinner);
        submit = (AppCompatButton) findViewById(R.id.submit);
        Intent intent = getIntent();
        applicant_id = intent.getStringExtra("user_id");
        Crif_Generation();

       /* applicant_credite_card_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CRIF_Question_Testing();
            }
        });*/

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CRIF_Question_Testing();
            }
        });

    }
    private void Crif_Generation() {

        JSONObject J =new JSONObject();
        try {
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("relationship_type",Pref.getCoAPPAVAILABLE(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Crif Generation", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CRIF_Generation_old, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment", String.valueOf(object));
                        try {

                            String Statues = object.getString("status");


                            if (Statues.equals("success")) {
                                credit_card_app.setVisibility(View.GONE);
                                Document_Details();
                              //  STEP2_COMPLETE();

                            }else if (Statues.equals("question"))
                            {
                                JSONObject data1 = object.getJSONObject("data");
                                Report_ID  = data1.getString("reportId");
                                Order_ID  = data1.getString("orderId");
                                String question = object.getString("question");
                                JSONArray qus_dropdown = object.getJSONArray("qus_dropdown");
                                cr_app1.setText(question);
                                credit_card_app.setVisibility(View.VISIBLE);
                                Other_Earning(qus_dropdown);

                               // co_applicant_.setVisibility(View.VISIBLE);
                            }else {
                                Toast.makeText(mCon, "error, Please Check",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
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
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void Other_Earning(final JSONArray other_earning_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Other_Earning_SA = new String[other_earning_ar.length()];
        for (int i=0;i<other_earning_ar.length();i++){
            JSONObject J =  other_earning_ar.getJSONObject(i);
            Other_Earning_SA[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Other_Earning_SA));
            Other_Earning_Adapter = new ArrayAdapter<String>(mCon, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            Other_Earning_Adapter.setDropDownViewResource(R.layout.view_spinner_item);
            applicant_credite_card_spinner.setAdapter(Other_Earning_Adapter);
            applicant_credite_card_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        credit_issued_id = other_earning_ar.getJSONObject(position).getString("id");
                        credit_issued_value = other_earning_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");
                        Log.d("Salary_id", credit_issued_id);
                        Log.d("Salary_Value", credit_issued_value);

                       // CRIF_Question_Testing();


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            applicant_credite_card_spinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void STEP2_COMPLETE() {
        progressDialog.show();

        JSONObject J =new JSONObject();

        try {

            J.put("trans_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("mode","view");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Step2 Complete request",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.step2_complete_old, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Step2 complete response",object.toString());
                        try {

                            String Staues_step2_complete = object.getString("status");

                            if(Staues_step2_complete.contains("success")) {
                                Document_Details();
                              //  Document_Details();
                            }else
                            {
                                Toast.makeText(mCon, "CRIF Statues Failed",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
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
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void CRIF_Question_Testing() {

        JSONObject J =new JSONObject();
        try {
            J.put("report_id",Report_ID);
            J.put("order_id",Order_ID);
            J.put("crif_question",credit_issued_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Crif Generation", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.submit_question_old, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment", String.valueOf(object));
                        try {

                            String Statues = object.getString("status");
                           /* JSONObject data1 = object.getJSONObject("data");
                            Report_ID  = data1.getString("reportId");
                            Order_ID  = data1.getString("orderId");*/


                            if (Statues.equals("success")) {


                                Document_Details();

                            }else if (Statues.equals("technical_error"))
                            {
                                Toast.makeText(mCon, " Technical Error, Please Contact Loanwiser",Toast.LENGTH_SHORT).show();
                                // co_applicant_.setVisibility(View.VISIBLE);
                            }else
                            {
                                Toast.makeText(mCon, " Error, Please Contact Loanwiser",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
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
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void Document_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();

            J.put("trans_id",Pref.getTRANSACTIONID(getApplicationContext()));
          //  J.put("trans_id","53259");
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
          //  J.put("user_id","51647");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Report Request ",String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Report_Activity_old, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant_Doc",response.toString());
                        String data = String.valueOf(response);
                        //   Objs.a.showToast(mCon, data);

                        try {


                            Log.e("Report response",String.valueOf(response));
                            String report_statues = response.getString("status");
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
                    .inflate(R.layout.ly_crif_view_activity, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);

                holder.class_name.setText(Objs.a.capitalize(J.getString("name")));
                Objs.a.NewNormalFontStyle(mCon,holder.class_name);
                holder.image_doc.setImageDrawable(getResources().getDrawable(R.drawable.ic_applicant));
                holder.card_view_class_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);

                        try {

                            String viability_report_URL = J.getString("url");
                            String report="CRIF Report";

                           // Toast.makeText(getApplicationContext(),"clicked", Toast.LENGTH_SHORT).show();

                          //  String URL_LOADER = "https:\\/\\/callcenter.loanwiser.in\\/trans_id\\/RTdqa0lmcDBkL2srTkpSN0dRKy9Gdz09\\/user_id\\/YVZUU2lDL0s5WUVRdEYyMkszV1F2UT09\\/Loan_Viability_Report.pdf";

                           /* if (permissionUtils.checkPermission(CRIF_Report_Activity_PDF_View.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                if (viability_report_URL.length() > 0) {
                                    try {
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL)));
                                    } catch (Exception e) {
                                      //  startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL)));
                                        e.getStackTrace();
                                    }
                                }

                            }*/
                           /* PdfViewerActivity.Companion.launchPdfFromUrl( CRIF_Report_Activity_PDF_View.this,s,
                                    "Title", "dir",true);*/
                            Intent intent = new Intent(CRIF_Report_Activity_PDF_View.this, MainActivity.class);
                            intent.putExtra("viability_report_URL", viability_report_URL);
                            intent.putExtra("report", report);
                            startActivity(intent);



                         /*   String report="CRIF Report";
                            Objs.ac.StartActivityPutExtra(CRIF_Report_Activity_PDF_View.this, Doc_ImageView_Bank.class,
                                    Params.document,viability_report_URL,Params.report,report);*/


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
        Objs.ac.StartActivity(mCon, Home_Old.class);
        finish();
        super.onBackPressed();
    }
}
