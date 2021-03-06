package in.loanwiser.Old_Partner;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;

import in.loanwiser.partnerapp.BankStamentUpload.Bank_available_details_model;
import in.loanwiser.partnerapp.BankStamentUpload.Bank_available_listAdapter;
import in.loanwiser.partnerapp.BankStamentUpload.Bankdetails_model;
import in.loanwiser.partnerapp.BankStamentUpload.Bankitems;

import in.loanwiser.partnerapp.BankStamentUpload.Upload_Activity_Bank;
import in.loanwiser.partnerapp.BankStamentUpload.UploadmonthAdapter;
import in.loanwiser.partnerapp.BankStamentUpload.studentData;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklist_Fragment;

public class BankAnalysis_old extends SimpleActivity {
    public static final String TAG= BankAnalysis_old.class.getSimpleName();

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    RecyclerView recyclerView,uploadedmonth_recycleview,recycler_view_bank_available_;
    ArrayList<String> allNames = new ArrayList<String>();
    BanklistAdapter_old BanklistAdapter_old;
    Bank_available_listAdapter bank_available_listAdapter;

    UploadmonthAdapter uploadmonthAdapter;
    ArrayList<Bankitems> items;
    ArrayList<Bankdetails_model> items1;
    ArrayList<Bank_available_details_model> Bank_available_list;
    ProgressDialog dialog;
    TextView requiremonth,uploadmonth,missingerror;
    String documentget,adapter,statusintvalue,entitynumvalue;
    private AlertDialog progressDialog;
    private List studentDataList = new ArrayList<>();
    String document;
    TextView requiredatetxt,usernametxt,user_address,account_no,bank_name,account_typetxt;
    private List requirelist=new ArrayList<>();
    private String upload_detstatus,upload_det;
    private String year;
    private String month_details;
    TextView fromtodatetxt,salatxt ,abbtxt, approtxt,expen_ratiotxt ,baltxt ,expradio,missing_yeartxt;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList lineEntries;
    ArrayList barEntries;
    JSONArray labelarr;
    JSONObject rule_arr;
    LineChart lineChart;
    RadioGroup hour_radio_group;
    AppCompatButton upload_requirebtn;
    AppCompatButton proceed_button,proceed_button1;

    AVLoadingIndicatorView material_design_ball_scale_ripple_loader;
    LinearLayout grapiclay_parent,uploaded_monthtextlay,requiretxtlay,requirelay_detailslay,missingerror_lay,requiremonthbox_lay,
            uploadrequirebutton_lay,proceednext_lay,eligible_available,proceednext_lay1;

    RelativeLayout uploadedmonth_lay;


    String namevalue,addressvalue;
    ArrayList<String> glib_addresslist=new ArrayList<>();



    AppCompatButton pending_list,Proceed_to_next;
    float[] valOne = {10, 20, 30, 40, 50};
    float[] valTwo = {60, 50, 40, 30, 20};
    float[] valThree = {20, 10, 30, 60, 40};
    float[] valFour = {40, 10, 60, 30, 10};
    float[] valFive = {60, 50, 40, 30, 50};


    ArrayList<Entry> x,x1,x11;
    ArrayList<String> Date;
    //  private BarChart mChart;
    private LineChart mChart;
    ArrayList<BarEntry> visitors;
    ArrayList<ILineDataSet> dataSets;

    BarData barData;
    BarDataSet barDataSet;

    int count = 0;
    int count_but = 0;

    TableLayout tabel_row;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_bank_analysis_old);
        initTools(R.string.bank_anlysis);

        recyclerView=(RecyclerView)findViewById(R.id.banklist_recycleview);
        BarChart barChart=findViewById(R.id.barchart);
        LineChart linechart=findViewById(R.id.linechart);

        requiremonth=findViewById(R.id.requiremonth);
        uploadmonth=findViewById(R.id.missingmonth);
        missingerror=findViewById(R.id.missing_error);
        hour_radio_group=findViewById(R.id.hour_radio_group);
        tabel_row=findViewById(R.id.tabel_row);

        requiredatetxt=findViewById(R.id.requiredatetxt);
        usernametxt=findViewById(R.id.usernametext);
        user_address=findViewById(R.id.chequen);
        account_no=findViewById(R.id.accnum);
        account_typetxt=findViewById(R.id.ac_typetxt);
        bank_name=findViewById(R.id.banknametxt);

        progressDialog = new SpotsDialog(this, R.style.Custom);

        fromtodatetxt=findViewById(R.id.fromtodatetxt);
        proceed_button=findViewById(R.id.proceed_button);
        proceed_button1=findViewById(R.id.proceed_button1);
        salatxt=findViewById(R.id.salatxt);
        abbtxt=findViewById(R.id.abbtxt);
        approtxt=findViewById(R.id.approtxt);
        expen_ratiotxt=findViewById(R.id.expen_ratiotxt);
        baltxt=findViewById(R.id.baltxt);
        expradio=findViewById(R.id.expradio);
        missing_yeartxt=findViewById(R.id.missing_yeartxt);
        grapiclay_parent=findViewById(R.id.grapiclay_parent);


        uploaded_monthtextlay=findViewById(R.id.uploaded_monthtextlay);
        eligible_available=findViewById(R.id.eligible_available);
        requiretxtlay=findViewById(R.id.requiretxtlay);
        uploadedmonth_lay=findViewById(R.id.uploadedmonth_lay);
        requirelay_detailslay=findViewById(R.id.requirelay_detailslay);
        missingerror_lay=findViewById(R.id.missingerror_lay);
        requiremonthbox_lay=findViewById(R.id.requiremonthbox_lay);
        uploadrequirebutton_lay=findViewById(R.id.uploadrequirebutton_lay);
        proceednext_lay=findViewById(R.id.proceednext_lay);
        proceednext_lay1=findViewById(R.id.proceednext_lay1);
        upload_requirebtn=findViewById(R.id.upload_requirebtn);
        material_design_ball_scale_ripple_loader=findViewById(R.id.material_design_ball_scale_ripple_loader);

        Loan_submit_statues();


        upload_requirebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BankAnalysis_old.this, Upload_Activity_Bank.class);
                startActivity(intent);
                finish();
            }
        });

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent=new Intent(BankAnalysis_old.this, DocumentChecklist_Fragment.class);
                startActivity(intent);
                finish();*/
              // Eligibility_check_doc_checklist_generate();
                Applicant_Status();
            }
        });

        proceed_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BankAnalysis_old.this, Home.class);
                startActivity(intent);
                finish();
              //  Eligibility_check_doc_checklist_generate();

            }
        });

        requiremonth.setText("\u25CF"+" "+"Required Months");
        requiremonth.setTextColor(Color.parseColor("#CE0000"));

        uploadmonth.setText("\u25CF"+" "+"Uploaded Months");
        uploadmonth.setTextColor(Color.parseColor("#0EBB9A"));

        missingerror.setText("\u25CF"+" "+"Missing/Reconcilliation Errors");
        missingerror.setTextColor(Color.parseColor("#FF922B"));

        progressDialog = new SpotsDialog(BankAnalysis_old.this, R.style.Custom);
        items=new ArrayList<>();
        items1=new ArrayList<>();
        Bank_available_list=new ArrayList<>();
     /*   dialog=new ProgressDialog(this);
        dialog.setTitle("Bank statement");*/

        recycler_view_bank_available_ = (RecyclerView) findViewById(R.id.recycler_view_bank_available_);
        uploadedmonth_recycleview = findViewById(R.id.uploadedmonth_recycleview);
        uploadmonthAdapter = new UploadmonthAdapter(studentDataList);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        uploadedmonth_recycleview.setLayoutManager(manager);
      //  uploadedmonth_recycleview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        uploadedmonth_recycleview.setAdapter(uploadmonthAdapter);
       // StudentDataPrepare();
     //   Bank_details_list();

        recyclerView.setLayoutManager(new LinearLayoutManager(BankAnalysis_old.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        BanklistAdapter_old = new BanklistAdapter_old(BankAnalysis_old.this, items1);

        recycler_view_bank_available_.setLayoutManager(new LinearLayoutManager(BankAnalysis_old.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        bank_available_listAdapter = new Bank_available_listAdapter(BankAnalysis_old.this,Bank_available_list);

        final Intent in=getIntent();
        adapter=in.getStringExtra("adapter");
        Log.e("adapter",adapter);
                if(adapter.equals("upload"))
                {
                    makeJsonObjReq1();
                }else
                {
                     count = 3;
                    makeJsonObjReq1();
                    documentget=in.getStringExtra("document");
                    statusintvalue=in.getStringExtra("statusint");
                    entitynumvalue=in.getStringExtra("entitynumber");

                    if (adapter!=null){
                        if (statusintvalue.equalsIgnoreCase("0")){
                            Log.i(TAG, "onCreate:documentget "+documentget);
                            FinboxAPICheck(documentget);
                        }else{
                            Log.i(TAG, "onCreate:entitynumber "+entitynumvalue);
                            GlibAPICheckdetnew(entitynumvalue);
                        }
                    }
                }

       // FinboxAPI();

        ArrayList<BarEntry> visitors=new ArrayList<>();
        visitors.add(new BarEntry(2016,240));
        visitors.add(new BarEntry(2017,620));
        visitors.add(new BarEntry(2018,540));
        visitors.add(new BarEntry(2019,440));
        visitors.add(new BarEntry(2020,380));
        visitors.add(new BarEntry(2021,550));
        BarDataSet barDataSet=new BarDataSet(visitors,"Analysis");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        BarData barData=new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.animateY(2000);

        x = new ArrayList<Entry>();
        x1 = new ArrayList<Entry>();
        x11 = new ArrayList<Entry>();
        Date  = new ArrayList<String>();
        dataSets = new ArrayList<>();
        mChart = (LineChart) findViewById(R.id.line);
        mChart.setDrawGridBackground(false);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        mChart.getXAxis().setTextSize(15f);
        mChart.getAxisLeft().setTextSize(15f);

        YAxis axisLeft = mChart.getAxisLeft();
        axisLeft.setGranularity(10f);
        axisLeft.setAxisMinimum(0);

        YAxis axisRight = mChart.getAxisRight();
        axisRight.setGranularity(10f);
        axisRight.setAxisMinimum(0);



        /// END OF THE CHArt


    }
    private void Loan_submit_statues() {
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            // J.put("transaction_id", 53277);

        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  progressDialog.show();
        Log.e("Request _statues ", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.bank_status_fetch, J,
                new Response.Listener<JSONObject>() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Loawiser_Submit", object.toString());


                        try
                        {
                            JSONObject result=object.getJSONObject("result");


                            String  submit_loanwiser = result.getString("submit_loanwiser");
                            String doc_verification = result.getString("doc_verification");
                            String apply_completion_status = result.getString("apply_completion_status");



                            if(submit_loanwiser.equals("1"))
                            {
                                proceed_button.setVisibility(View.GONE);
                                proceed_button1.setVisibility(View.VISIBLE);
                                eligible_available.setVisibility(View.VISIBLE);

                            }else
                            {
                                Bank_statues();
                            }


                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                      //  progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
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
    public void Bank_statues() {

        // final String step_status11 = step_status1;
        JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

       // progressDialog.show();
        Log.e("Applicant Entry request", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.update_bankstatementstatus, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant Entry", String.valueOf(response));
                        JSONObject jsonObject1 = new JSONObject();

                        try {

                            JSONObject jsonObject2 = response.getJSONObject("response");
                            String statues = response.getString("status");
                            String eligible_status = jsonObject2.getString("eligible_status");


                            if (statues.contains("success")) {
                                // Applicant_Status();

                                if(eligible_status.equals("0"))
                                {

                                    proceed_button.setVisibility(View.GONE);
                                    proceed_button1.setVisibility(View.VISIBLE);
                                    eligible_available.setVisibility(View.GONE);
                                   // progressDialog.dismiss();
                                    //   Toast.makeText(Upload_Activity_Bank.this, "Bank Statement Analysis Failed", Toast.LENGTH_SHORT).show();
                                }else
                                {
                                    eligible_available.setVisibility(View.VISIBLE);
                                    proceed_button.setVisibility(View.VISIBLE);
                                    proceed_button1.setVisibility(View.GONE);
                                }
                               // progressDialog.dismiss();
                                // finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                      //  progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
              //  progressDialog.dismiss();
                Log.e("Applicant Entry request", String.valueOf(error));
                Toast.makeText(BankAnalysis_old.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

   /* protected void Eligibility_check_doc_checklist_generate() {

        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("viability", String.valueOf(J));
         progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.generate_doccklist, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("viability response", String.valueOf(response));
                        String data = String.valueOf(response);
                        try {
                            String Generate_Document_check_list = response.getString("Generate_Document_check_list");
                            JSONObject jsonObject1 = response.getJSONObject("response");

                            if(response.getString("Generate_Document_check_list").equals("success"))
                            {
                                Applicant_Status();

                            }else if(response.getString("Generate_Document_check_list").equals("error"))
                            {


                            }
                            ///


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("Lead creation", String.valueOf(response));

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d(TAG, error.getMessage());
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
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

        // AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }*/

    protected void Applicant_Status(final String id) {
        tabel_row.removeAllViews();
        // final String step_status11 = step_status1;
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();

            J.put("bank_id", id);
          //  J.put("bank_id", id);
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("bank state rule request", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.get_bankstaterules, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint({"LongLogTag", "ResourceType"})
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("rule response", String.valueOf(response));
                        try {
                            String status = response.getString("status");

                            TableRow tr_head = new TableRow(getApplicationContext());
                            tr_head.setId(10);
                            tr_head.setBackgroundColor(Color.GRAY);
                            tr_head.setLayoutParams(new ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.FILL_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT));

                            TextView label_date = new TextView(getApplicationContext());
                            label_date.setId(20);
                            label_date.setText("Rule Description"+"  ");
                            label_date.setTextColor(Color.WHITE);
                            label_date.setPadding(5, 5, 5, 5);
                            tr_head.addView(label_date);// add the column to the table row here

                            TextView label_weight_kg = new TextView(getApplicationContext());
                            label_weight_kg.setId(21);// define id that must be unique
                            label_weight_kg.setText("Statues"+"  "); // set the text for the header
                            label_weight_kg.setTextColor(Color.WHITE); // set the color
                            label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
                            tr_head.addView(label_weight_kg); // add the column to the table row here

                            TextView Fail_Message = new TextView(getApplicationContext());
                            Fail_Message.setId(21);// define id that must be unique
                            Fail_Message.setText("Fail Message"+"  "); // set the text for the header
                            Fail_Message.setTextColor(Color.WHITE); // set the color
                            Fail_Message.setPadding(5, 5, 5, 5); // set the padding (if required)
                            tr_head.addView(Fail_Message); // add the column to the table row here


                            tabel_row.addView(tr_head, new TableLayout.LayoutParams(
                                    ViewGroup.LayoutParams.FILL_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT));

                            JSONObject jsonObject1=null;
                            if(status.equals("success"))
                            {
                                JSONArray response1 = response.getJSONArray("response");
                                for(int i = 0; i < response1.length(); i++){

                                    JSONObject J = null;
                                    try {

                                        J = response1.getJSONObject(i);

                                        String rule_type=J.getString("rule_desc");
                                        String fail_message=J.getString("fail_message");
                                        String rule_status=J.getString("rule_status");
                                        TableRow tr = new TableRow(getApplicationContext());
                                        tr.setId(100+i);
                                        tr.setLayoutParams(new ViewGroup.LayoutParams(
                                                ViewGroup.LayoutParams.FILL_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT));

                                        TextView rule_description = new TextView(getApplicationContext());
                                        rule_description.setId(200+i);
                                        rule_description.setText(rule_type+"  ");
                                        label_weight_kg.setPadding(5, 5, 5, 5);
                                        tr.addView(rule_description);

                                        TextView fail_message1 = new TextView(getApplicationContext());
                                        fail_message1.setId(300+i);
                                        if(rule_status.equals("1"))
                                        {
                                            fail_message1.setText("Pass"+"  ");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(fail_message1);

                                        }else {
                                            fail_message1.setText("Fail"+"  ");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(fail_message1);

                                        }


                                        TextView rule_status1 = new TextView(getApplicationContext());
                                        rule_status1.setId(300+i);
                                        rule_status1.setText(fail_message+"  ");
                                        label_weight_kg.setPadding(5, 5, 5, 5);
                                        tr.addView(rule_status1);

                                        tabel_row.addView(tr, new TableLayout.LayoutParams(
                                                ViewGroup.LayoutParams.FILL_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }



                                }
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JSONObject jsonObject1 = new JSONObject();


                        // TableLayout prices = (TableLayout) holder.findViewById(R.id.prices);


                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Applicant Entry request", String.valueOf(error));
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
    public void Applicant_Status() {

        // final String step_status11 = step_status1;
        JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("Applicant Entry request", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PARTNER_STATUES_IDs, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant Entry", String.valueOf(response));
                        JSONObject jsonObject1 = new JSONObject();

                        try {
                            String statues = response.getString("status");

                            if (statues.contains("success")) {
                                JSONObject jsonObject2 = response.getJSONObject("reponse");

                                JSONArray jsonArray = jsonObject2.getJSONArray("emp_states");

                                String user_id = jsonObject2.getString("user_id");
                                String Loan_amount = jsonObject2.getString("loan_amount");
                                String sub_categoryid = jsonObject2.getString("sub_categoryid");
                                String transaction_id1 = jsonObject2.getString("transaction_id");
                                String subtask_id = jsonObject2.getString("subtask_id");
                                String loan_type_id = jsonObject2.getString("loan_type_id");
                                String loan_type = jsonObject2.getString("loan_type");
                                String payment = jsonObject2.getString("payment");
                                String applicant_id1 = "APP-" + user_id;
                                // String statues2 = "3";
                                Pref.putUSERID(getApplicationContext(), user_id);
                                String _Emp_staus_jsonArray = jsonArray.toString();

                                                       /* Objs.ac.StartActivityPutExtra(context, Home.class,
                                                                Params.user_id,user_id,
                                                                Params.transaction_id,transaction_id1,
                                                                Params.applicant_id,applicant_id1,
                                                                Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                                                Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);*/
                                Intent intent = new Intent(BankAnalysis_old.this, DocumentChecklist_Fragment.class);
                                startActivity(intent);
                                finish();

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
                Log.e("Applicant Entry request", String.valueOf(error));
                Toast.makeText(BankAnalysis_old.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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



    private void makeJsonObjReq1() {
        Log.i(TAG, "makeJsonObjReq1: "+"Make");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        J = new JSONObject();
        material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("relationship_type",Pref.getCoAPPAVAILABLE(getApplicationContext()));
            Log.i(TAG, "makeJsonObjReq1:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.e("state_id", String.valueOf(J))
        progressDialog.show();

        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.BANK_STATEMENT_LIST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        JSONArray cast = null;
                        JSONArray ja = null;
                        JSONArray ja1=null;
                        try {
                            String s=object.getString("display_select");
                          //  String ban_statementlist=object.getString("ban_statementlist");
                          //  Log.i(TAG, "ban_statementlist: "+ban_statementlist);
                            Log.i(TAG, "onResponse: "+s);
                            ja = object.getJSONArray("ban_statementlist");
                            Log.i(TAG, "onResponse:Value "+ja);
                            Log.i(TAG, "onResponse: Array"+ja.length());
                            for(int i = 0;i<ja.length();i++){
                                JSONObject J = ja.getJSONObject(i);
                              //  String acc_number=J.getString("acc_number");
                               // String entity_id=J.getString("entity_id");
                                String bank_name=J.getString("bank_name");
                                String achold_name=J.getString("acchold_name");

                                items1.add(new Bankdetails_model(J.getString("acc_number"),J.getString("entity_id"),J.getString("bank_name"),J.getString("acchold_name"),J.getString("status_int")));
                                BanklistAdapter_old.notifyDataSetChanged();


                                if(count == 0)
                                {
                                    if(adapter.equals("upload"))
                                    {
                                        Log.e("count called ", String.valueOf(count));
                                        String status_int=J.getString("status_int");

                                        if (status_int.equalsIgnoreCase("0")){
                                            String acc_number=J.getString("acc_number");
                                            Log.i(TAG, "onCreate:documentget "+acc_number);
                                            FinboxAPICheck(acc_number);
                                        }else{
                                            String entity_id=J.getString("entity_id");
                                            Log.i(TAG, "onCreate:entitynumber "+entity_id);
                                            GlibAPICheckdetnew(entity_id);
                                        }
                                    }

                                }else
                                {
                                    Log.e("count called ", String.valueOf(count));
                                }

                                count = count+1;

                            }
                            recyclerView.setAdapter(BanklistAdapter_old);



                        } catch (JSONException ex) {
                            ex.printStackTrace();
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




    private void FinboxAPICheck(String documentget) {

        Log.i(TAG, "Which API: "+"Finbox");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        J = new JSONObject();
        material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("typecnt","0");
            J.put("bankaccno",documentget);
            Log.i(TAG, "FInbox:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  progressDialog.dismiss();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, "https://cscapi.loanwiser.in/integration/bank_statement.php?call=get_bankstatement_det", J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        JSONArray cast = null;
                        JSONArray ja = null;
                        JSONArray ja1=null;
                        try {
                            // String s=object.getString("display_select");
                            JSONObject bankarr=object.getJSONObject("bank_arr");

                           //  rule_arr=object.getJSONObject("rule_arr");

                            if (object.has("rule_arr")) {

                                JSONObject dataObject = object.optJSONObject("rule_arr");

                                if (dataObject != null) {

                                    rule_arr=object.getJSONObject("rule_arr");
                                    //Do things with object.
                                    Log.e("the works ",rule_arr.toString());

                                } else {
                                    rule_arr = null;
                                    JSONArray array = object.optJSONArray("rule_arr");

                                    //Do things with array
                                }
                            } else {
                                // Do nothing or throw exception if "data" is a mandatory field
                            }
                           // labelarr=bankarr.getJSONArray("label_arr");
                            JSONArray  label_arr=bankarr.getJSONArray("label_arr");
                            JSONArray  amount_arr=bankarr.getJSONArray("amount_arr");
                            JSONArray creditdet_arr=bankarr.getJSONArray("creditdet_arr");
                            JSONArray debitdet_arr=bankarr.getJSONArray("debitdet_arr");

                            JSONObject jsonObject1=object.getJSONObject("uploaded_month");
                            JSONObject jsonObject2=jsonObject1.getJSONObject("month_arr");
                            Log.i(TAG, "onResponse: "+jsonObject1);
                            String startdate=jsonObject1.getString("start_date");
                            String todate=jsonObject1.getString("end_date");

                            String username=bankarr.getString("acchold_name");
                            String user_addres=bankarr.getString("cust_address");
                            String accnumber=bankarr.getString("acc_number");
                            String bankname=bankarr.getString("bank_name");
                            String account_type=bankarr.getString("acc_type");
                            String fromtodate=bankarr.getString("from_date");
                            String tofromdate=bankarr.getString("to_date");
                            String salary=bankarr.getString("salary_amount");
                            String abb=bankarr.getString("avg_bankbalance");
                            String emi_amount=object.getString("emi_amount");
                            String bal_recol=object.getString("bal_reconcilation");
                            String chequeboun_penal=object.getString("chequeboun_penal");
                            String required_monthstr=object.getString("required_monthstr");
                            Log.i(TAG, "onResponse:required_monthstr "+required_monthstr);
                            Log.i(TAG, "startdate: "+startdate);
                            Log.i(TAG, "todate: "+todate);
                            Log.i(TAG, "acchold_name: "+username);
                            salatxt.setText(salary);
                            requiredatetxt.setText(startdate+" " +"to "+ todate);
                            usernametxt.setText(username);
                            user_address.setText(user_addres);
                            account_no.setText(accnumber);
                            bank_name.setText(bankname);
                            account_typetxt.setText(account_type);
                          //  fromtodatetxt.setText(fromtodate+" "+"to"+tofromdate);
                            fromtodatetxt.setText(parseDateToddMMyyyy(fromtodate)+" "+"to"+ " "+parseDateToddMMyyyy(tofromdate));

                            abbtxt.setText("\u20B9"+" "+abb);
                            approtxt.setText(emi_amount);
                            baltxt.setText(bal_recol);
                            expradio.setText(chequeboun_penal);
                            missing_yeartxt.setText("Please Upload Bank Statement For The Following Required Months -"+required_monthstr+" "+"in the above Bank Statement Upload Section.");
                            TextView fromtodatetxt,salatxt ,abbtxt, approtxt,expen_ratiotxt ,baltxt ,expradio;

                            if(rule_arr != null)
                            {
                            if(rule_arr.length()>0)
                            {
                                for(int i = 0;i<rule_arr.length();i++) {
                                    Iterator iterator = rule_arr.keys();
                                    while (iterator.hasNext()) {

                                        String key = (String) iterator.next();
                                        Log.i("TAG", "keyvaluecheck: " + key);
                                        Log.e("value", key.toString());

                                       JSONObject response_iD_proof_comon = rule_arr.getJSONObject(key);

                                       String status = response_iD_proof_comon.getString("status");
                                        if(status.equals("1"))
                                       {
                                           JSONObject response_iD_proof_comon1 = response_iD_proof_comon.getJSONObject("bank_data");
                                           String bank_logo_cc = response_iD_proof_comon1.getString("bank_logo_cc");
                                           String bank_categorystr = response_iD_proof_comon1.getString("category_name");
                                           String bank_category = response_iD_proof_comon1.getString("bus_category");
                                           String id = response_iD_proof_comon1.getString("id");


                                           Bank_available_list.add(new Bank_available_details_model(bank_logo_cc,bank_categorystr, bank_category,status,id));
                                           bank_available_listAdapter.notifyDataSetChanged();
                                        }else
                                        {
                                            JSONObject response_iD_proof_comon1 = response_iD_proof_comon.getJSONObject("bank_data");
                                            String bank_logo_cc = response_iD_proof_comon1.getString("bank_logo_cc");
                                            String bank_categorystr = response_iD_proof_comon1.getString("category_name");
                                            String bank_category = response_iD_proof_comon1.getString("bus_category");
                                            String id = response_iD_proof_comon1.getString("id");
                                            Bank_available_list.add(new Bank_available_details_model(bank_logo_cc,bank_categorystr, bank_category,status,id));
                                            bank_available_listAdapter.notifyDataSetChanged();
                                        }




                                    }
                                    //  String acc_number=J.getString("acc_number");
                                    // String entity_id=J.getString("entity_id");

                                }

                                recycler_view_bank_available_.setAdapter(bank_available_listAdapter);
                              //  progressDialog.dismiss();
                            }

                            }
                            for (int i = 0; i < amount_arr.length(); i++) {
                                // String value="12";
                                float value3= Float.parseFloat(amount_arr.getString(i));
                                // int value1 = Integer.parseInt(value3);
                                Log.e("json", i+"="+value3);

                                String value=label_arr.getString(i);
                                // String value="15-May-2019";
                                Date.add(String.valueOf(value));
                                x.add(new BarEntry(i, value3));
                                // x.add(value3);
                                // Log.e("json", i+"="+value3);
                            }
                            for (int i = 0; i < creditdet_arr.length(); i++) {
                                // String value="12";
                                if(creditdet_arr.getString(i).isEmpty())
                                {

                                }else
                                {
                                    float value3= Float.parseFloat(creditdet_arr.getString(i));
                                    // int value1 = Integer.parseInt(value3);
                                    Log.e("json", i+"="+value3);
                                    x1.add(new BarEntry(i, value3));
                                }


                                // x.add(value3);
                                // Log.e("json", i+"="+value3);
                            }
                            for (int i = 0; i < debitdet_arr.length(); i++) {
                                // String value="12";
                                if(debitdet_arr.getString(i).isEmpty())
                                {

                                }else
                                {
                                    float value3= Float.parseFloat(debitdet_arr.getString(i));
                                    // int value1 = Integer.parseInt(value3);
                                    Log.e("json", i+"="+value3);
                                    x11.add(new BarEntry(i, value3));
                                }


                                // x.add(value3);
                                // Log.e("json", i+"="+value3);
                            }
                            for (int i = 0; i < label_arr.length(); i++) {
                                String value=label_arr.getString(i);
                                // String value="15-May-2019";
                                Date.add(String.valueOf(value));
                                Log.e("json", i+"="+value);
                            }
                            //LineDataSet set1 = new LineDataSet(x, Token);

                            // BarDataSet set1 = new BarDataSet(x, "Analysis");
                            // BarDataSet set1 = new BarDataSet(x, "Analysis");
                            LineDataSet set3 = new LineDataSet(x, "Banking");
                            LineDataSet set2 = new LineDataSet(x1, "Credit");
                            LineDataSet set4 = new LineDataSet(x11, "Debit");
                            //   set1.setStackLabels(new String[] {"Balance", "Debit", "Credit"});
                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                            //  dataSets.add(set1);
                            dataSets.add(set3);
                            dataSets.add(set4);
                            dataSets.add(set2);
                            // LineData lineData = new LineData(dataSet);

                            // set1.setColors(ColorTemplate.COLORFUL_COLORS);
                            set4.setColors(Color.BLUE);
                            set3.setColors(Color.RED);
                            set2.setColors(Color.GREEN);
                            //  set1.setLineWidth(1.5f);
                            // set1.setCircleRadius(4f);
                            //  LineDataSet set12 = new LineDataSet(x, "# of Calls");

                            // LineData data = new LineData((ILineDataSet) y, set1);
                            // LineData data = new LineData(set1);

                            //  LineData data = new LineData(y, set1);

                            //BarData data = new BarData((IBarDataSet) Date ,set1);
                            LineData data = new LineData(dataSets);
                            //  BarData data = new BarData(set1);
                            //   barChart.setData(Data);
                               /* final XAxis xAxis = mChart.getXAxis();
                                xAxis.setLabelCount(Date.size());
                                xAxis.setValueFormatter(new ValueFormatter() {
                                    @Override
                                    public String getFormattedValue(float value, AxisBase axis) {
                                        return Date.get((int) value);

                                    }
                                });*/

                               /* xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                                xAxis.setDrawGridLines(false);
                                xAxis.setValueFormatter(new IndexAxisValueFormatter(){
                                    @Override
                                    public String getFormattedValue(float value, AxisBase axis) {
                                        return getXAxisValues().get((int) value);
                                    }
                                });*/


                            XAxis xAxis = mChart.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(getDate()));



                            // mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

                            mChart.setData(data);
                            mChart.setTouchEnabled(true);
                            mChart.setDragEnabled(true);
                            mChart.setScaleEnabled(true);
                            mChart.animateXY(0, 5000);
                            mChart.setHorizontalScrollBarEnabled(true);
                            mChart.setDoubleTapToZoomEnabled(true);
                            mChart.invalidate();

                            Iterator iterator = jsonObject2.keys();
                            while (iterator.hasNext()) {
                                String key = (String) iterator.next();
                                Log.e("value", key.toString());
                                ArrayList<String> list_key = new ArrayList<String>();
                                Log.e("the value",list_key.toString());

                                JSONArray jsonAray = jsonObject2.getJSONArray(key);
                                for (int i = 0; i < jsonAray.length(); i++) {
                                    JSONObject childrenObject = jsonAray.getJSONObject(i);
                                    Log.i(TAG, "onResponse:lops "+childrenObject);
                                    upload_detstatus=childrenObject.optString("upload_detstatus");
                                    upload_det=childrenObject.optString("upload_det");
                                    Log.i(TAG, "onResponse: upload_detstatus"+upload_detstatus);
                                    year=childrenObject.optString("year");
                                    month_details=childrenObject.getString("month_str");
                                    if (upload_detstatus.equalsIgnoreCase("required")){
                                        requirelist.add(year+month_details);
                                        Log.i(TAG, "onResponse:requirelist "+requirelist);
                                        if (upload_det.equals("required")){

                                            count_but = count_but+1;
                                        }else
                                        {

                                        }
                                        studentData data1 = new studentData(year+" "+month_details,upload_det);
                                        studentDataList.add(data1);


                                        uploadmonthAdapter = new UploadmonthAdapter(studentDataList);
                                        RecyclerView.LayoutManager manager = new GridLayoutManager(BankAnalysis_old.this, 3);
                                        uploadedmonth_recycleview.setLayoutManager(manager);
                                        //  uploadedmonth_recycleview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                                        uploadedmonth_recycleview.setAdapter(uploadmonthAdapter);

                                        uploadmonthAdapter.notifyDataSetChanged();
                                    }

                                }

                                if(count_but >1)
                                {
                                    upload_requirebtn.setVisibility(View.VISIBLE);
                                }else
                                {
                                    upload_requirebtn.setVisibility(View.GONE);
                                }

                            }

                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        material_design_ball_scale_ripple_loader.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
              //  progressDialog.dismiss();
                material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);
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

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        java.util.Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
/*    private void getEntries() {
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(2f, 0));
        lineEntries.add(new Entry(4f, 1));
        lineEntries.add(new Entry(6f, 1));
        lineEntries.add(new Entry(8f, 3));
        lineEntries.add(new Entry(7f, 4));
        lineEntries.add(new Entry(3f, 3));
    }*/

    private void GlibAPICheckdetnew(String entity) {
        Log.i(TAG, "Which API: "+"Glib");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);
        J = new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("typecnt","0");
            J.put("entity_id",entity);
            Log.i(TAG, "glib:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  progressDialog.dismiss();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, "https://cscapi.loanwiser.in/integration/bank_statement.php?call=get_bankstatement_detnew", J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        JSONArray cast = null;
                        JSONArray ja = null;
                        JSONArray ja1=null;
                        try {
                            JSONObject bankarr=object.getJSONObject("bank_arr");
                            rule_arr=object.getJSONObject("rule_arr");
                            JSONArray  label_arr=bankarr.getJSONArray("label_arr");
                            JSONArray  amount_arr=bankarr.getJSONArray("amount_arr");
                            JSONArray creditdet_arr=bankarr.getJSONArray("creditdet_arr");
                            JSONArray debitdet_arr=bankarr.getJSONArray("debitdet_arr");

                            JSONObject jsonObject1=object.getJSONObject("uploaded_month");
                            JSONObject jsonObject2=jsonObject1.getJSONObject("month_arr");
                            String startdate=jsonObject1.getString("start_date");
                            String todate=jsonObject1.getString("end_date");
                            String bankname=bankarr.getString("bank_name");
                            JSONObject jsonObject3=bankarr.getJSONObject("sift_statuscheck");
                            JSONObject result=jsonObject3.getJSONObject("results");
                            JSONObject accountno=result.getJSONObject("account_number");
                            JSONObject avgbal=result.getJSONObject("avg_balance");
                            JSONObject extracted_cust_details=result.getJSONObject("extracted_cust_details");
                            JSONArray jsonArray=extracted_cust_details.getJSONArray("extracted_name");
                            JSONArray jsonArray1=extracted_cust_details.getJSONArray("extracted_address");
                            String emi_amount=object.getString("emi_amount");
                            String salaryamt=bankarr.getString("salary_amount");
                            String bal_reconcilation=object.getString("bal_reconcilation");
                            String required_monthstr=object.getString("required_monthstr");
                            String acnum=accountno.getString("account_number");
                            String avgbankbal=avgbal.getString("avg_monthly_balance");



                            if(rule_arr.length()>0)
                            {
                                for(int i = 0;i<rule_arr.length();i++) {
                                    Iterator iterator = rule_arr.keys();
                                    while (iterator.hasNext()) {

                                        String key = (String) iterator.next();
                                        Log.i("TAG", "keyvaluecheck: " + key);
                                        Log.e("value", key.toString());

                                        JSONObject response_iD_proof_comon = rule_arr.getJSONObject(key);

                                        String status = response_iD_proof_comon.getString("status");
                                        if(status.equals("1"))
                                        {
                                            JSONObject response_iD_proof_comon1 = response_iD_proof_comon.getJSONObject("bank_data");
                                            String bank_logo_cc = response_iD_proof_comon1.getString("bank_logo_cc");
                                            String bank_categorystr = response_iD_proof_comon1.getString("category_name");
                                            String bank_category = response_iD_proof_comon1.getString("bus_category");
                                            String id = response_iD_proof_comon1.getString("id");

                                            Bank_available_list.add(new Bank_available_details_model(bank_logo_cc,bank_categorystr, bank_category,status,id));
                                            bank_available_listAdapter.notifyDataSetChanged();
                                        }else
                                        {
                                            JSONObject response_iD_proof_comon1 = response_iD_proof_comon.getJSONObject("bank_data");
                                            String bank_logo_cc = response_iD_proof_comon1.getString("bank_logo_cc");
                                            String bank_categorystr = response_iD_proof_comon1.getString("category_name");
                                            String bank_category = response_iD_proof_comon1.getString("bus_category");
                                            String id = response_iD_proof_comon1.getString("id");
                                            Bank_available_list.add(new Bank_available_details_model(bank_logo_cc,bank_categorystr, bank_category,status,id));
                                            bank_available_listAdapter.notifyDataSetChanged();
                                        }




                                    }
                                    //  String acc_number=J.getString("acc_number");
                                    // String entity_id=J.getString("entity_id");

                                }

                                recycler_view_bank_available_.setAdapter(bank_available_listAdapter);
                                progressDialog.dismiss();
                            }

                            for (int i = 0; i < amount_arr.length(); i++) {
                                // String value="12";
                                float value3= Float.parseFloat(amount_arr.getString(i));
                                // int value1 = Integer.parseInt(value3);
                                Log.e("json", i+"="+value3);

                                String value=label_arr.getString(i);
                                // String value="15-May-2019";
                                Date.add(String.valueOf(value));
                                x.add(new BarEntry(i, value3));
                                // x.add(value3);
                                // Log.e("json", i+"="+value3);
                            }
                            for (int i = 0; i < creditdet_arr.length(); i++) {
                                // String value="12";
                                if(creditdet_arr.getString(i).isEmpty())
                                {

                                }else
                                {
                                    float value3= Float.parseFloat(creditdet_arr.getString(i));
                                    // int value1 = Integer.parseInt(value3);
                                    Log.e("json", i+"="+value3);
                                    x1.add(new BarEntry(i, value3));
                                }


                                // x.add(value3);
                                // Log.e("json", i+"="+value3);
                            }
                            for (int i = 0; i < debitdet_arr.length(); i++) {
                                // String value="12";
                                if(debitdet_arr.getString(i).isEmpty())
                                {

                                }else
                                {
                                    float value3= Float.parseFloat(debitdet_arr.getString(i));
                                    // int value1 = Integer.parseInt(value3);
                                    Log.e("json", i+"="+value3);
                                    x11.add(new BarEntry(i, value3));
                                }


                                // x.add(value3);
                                // Log.e("json", i+"="+value3);
                            }
                            for (int i = 0; i < label_arr.length(); i++) {
                                String value=label_arr.getString(i);
                                // String value="15-May-2019";
                                Date.add(String.valueOf(value));
                                Log.e("json", i+"="+value);
                            }
                            //LineDataSet set1 = new LineDataSet(x, Token);

                            // BarDataSet set1 = new BarDataSet(x, "Analysis");
                            // BarDataSet set1 = new BarDataSet(x, "Analysis");
                            LineDataSet set3 = new LineDataSet(x, "Banking");
                            LineDataSet set2 = new LineDataSet(x1, "Credit");
                            LineDataSet set4 = new LineDataSet(x11, "Debit");
                            //   set1.setStackLabels(new String[] {"Balance", "Debit", "Credit"});
                            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                            //  dataSets.add(set1);
                            dataSets.add(set3);
                            dataSets.add(set4);
                            dataSets.add(set2);
                            // LineData lineData = new LineData(dataSet);

                            // set1.setColors(ColorTemplate.COLORFUL_COLORS);
                            set4.setColors(Color.BLUE);
                            set3.setColors(Color.RED);
                            set2.setColors(Color.GREEN);
                            //  set1.setLineWidth(1.5f);
                            // set1.setCircleRadius(4f);
                            //  LineDataSet set12 = new LineDataSet(x, "# of Calls");

                            // LineData data = new LineData((ILineDataSet) y, set1);
                            // LineData data = new LineData(set1);

                            //  LineData data = new LineData(y, set1);

                            //BarData data = new BarData((IBarDataSet) Date ,set1);
                            LineData data = new LineData(dataSets);
                            //  BarData data = new BarData(set1);
                            //   barChart.setData(Data);
                               /* final XAxis xAxis = mChart.getXAxis();
                                xAxis.setLabelCount(Date.size());
                                xAxis.setValueFormatter(new ValueFormatter() {
                                    @Override
                                    public String getFormattedValue(float value, AxisBase axis) {
                                        return Date.get((int) value);

                                    }
                                });*/

                               /* xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                                xAxis.setDrawGridLines(false);
                                xAxis.setValueFormatter(new IndexAxisValueFormatter(){
                                    @Override
                                    public String getFormattedValue(float value, AxisBase axis) {
                                        return getXAxisValues().get((int) value);
                                    }
                                });*/


                            XAxis xAxis = mChart.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setValueFormatter(new IndexAxisValueFormatter(getDate()));



                            // mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

                            mChart.setData(data);
                            mChart.setTouchEnabled(true);
                            mChart.setDragEnabled(true);
                            mChart.setScaleEnabled(true);
                            mChart.animateXY(0, 5000);
                            mChart.setHorizontalScrollBarEnabled(true);
                            mChart.setDoubleTapToZoomEnabled(true);
                            mChart.invalidate();

                            for (int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject6=jsonArray.getJSONObject(i);
                                namevalue=jsonObject6.getString("value");
                                Log.i(TAG, "onResponse: namevale"+namevalue);
                            }
                            for (int i=0;i<jsonArray1.length();i++){
                                JSONObject jsonObject7=jsonArray1.getJSONObject(i);
                                addressvalue=jsonObject7.getString("value");
                                glib_addresslist.add(addressvalue);
                                user_address.setText(glib_addresslist.get(0));
                                Log.i(TAG, "onResponse: addressvalue"+addressvalue);
                            }
                            JSONObject obj = result.getJSONObject("bank_identity");
                            JSONObject listPathObject = obj.getJSONObject("bank");
                            // JSONObject object149 = listPathObject.getJSONObject("149");
                            Iterator<String> iter = listPathObject.keys(); //This should be the iterator you want.
                            while(iter.hasNext()){
                                String key = iter.next();
                                bank_name.setText(key);
                            }
                            requiredatetxt.setText(startdate+" " +"to"+ todate);
                            usernametxt.setText(namevalue);
                            account_no.setText(acnum);
                          //  fromtodatetxt.setText(startdate+ "to"+ todate);
                            fromtodatetxt.setText(parseDateToddMMyyyy(startdate)+" "+"to"+ " "+parseDateToddMMyyyy(todate));
                           // salatxt.setText("NA");
                            salatxt.setText(salaryamt);
                            missing_yeartxt.setText("Please Upload Bank Statement For The Following Required Months -"+required_monthstr+" "+"in the above Bank Statement Upload Section.");
                            abbtxt.setText("\u20B9"+" "+avgbankbal);
                            approtxt.setText("NA");
                            expen_ratiotxt.setText("NA");
                            baltxt.setText(bal_reconcilation);
                            expradio.setText("NA");
                            Iterator iterator = jsonObject2.keys();
                            while (iterator.hasNext()) {
                                String key = (String) iterator.next();
                                Log.e("value", key.toString());
                                ArrayList<String> list_key = new ArrayList<String>();
                                Log.e("the value",list_key.toString());
                                JSONArray jsonAray = jsonObject2.getJSONArray(key);
                                for (int i = 0; i < jsonAray.length(); i++) {
                                    JSONObject childrenObject = jsonAray.getJSONObject(i);
                                    Log.i(TAG, "onResponse:lops "+childrenObject);
                                    upload_detstatus=childrenObject.optString("upload_detstatus");
                                    upload_det=childrenObject.optString("upload_det");
                                    Log.i(TAG, "onResponse: upload_detstatus"+upload_detstatus);
                                    year=childrenObject.optString("year");
                                    month_details=childrenObject.getString("month_str");
                                    if (upload_detstatus.equalsIgnoreCase("required")){
                                        requirelist.add(year+month_details);
                                        Log.i(TAG, "onResponse:requirelist "+requirelist);
                                        if (upload_det.equals("required")){

                                            count_but = count_but+1;
                                        }else
                                        {

                                        }
                                        studentData data1 = new studentData(year+" "+month_details,upload_det);
                                        studentDataList.add(data1);
                                        uploadmonthAdapter = new UploadmonthAdapter(studentDataList);
                                        RecyclerView.LayoutManager manager = new GridLayoutManager(BankAnalysis_old.this, 3);
                                        uploadedmonth_recycleview.setLayoutManager(manager);
                                        //  uploadedmonth_recycleview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                                        uploadedmonth_recycleview.setAdapter(uploadmonthAdapter);
                                        uploadmonthAdapter.notifyDataSetChanged();
                                    }
                                }
                                if(count_but >1)
                                {
                                    upload_requirebtn.setVisibility(View.VISIBLE);
                                }else
                                {
                                    upload_requirebtn.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);
               // progressDialog.dismiss();
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

    private List<String> getXAxisValues() {

        return Date;
    }

    public ArrayList<String> getDate() {

        ArrayList<String> label = new ArrayList<>();
        for (int i = 0; i < Date.size(); i++)
            label.add(Date.get(i));
        return label;
    }
    private void getEntries1() {
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(2f, 0));
        lineEntries.add(new Entry(4f, 1));
        lineEntries.add(new Entry(6f, 1));
        lineEntries.add(new Entry(8f, 3));
        lineEntries.add(new Entry(7f, 4));
        lineEntries.add(new Entry(3f, 3));
    }
    private void getEntries() {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(2f, 0));
        barEntries.add(new BarEntry(4f, 1));
        barEntries.add(new BarEntry(6f, 1));
        barEntries.add(new BarEntry(8f, 3));
        barEntries.add(new BarEntry(7f, 4));
        barEntries.add(new BarEntry(3f, 3));
    }

}