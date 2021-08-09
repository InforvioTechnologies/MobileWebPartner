package in.loanwiser.partnerapp.BankStamentUpload;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Documents.Bank_Availability_Check;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.PartnerActivitys.Submitsuccess_Activity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.Step3_Changes.Elegibility_Report;
import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklist_Fragment;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class BankAnalysis1 extends SimpleActivity implements View.OnClickListener,PickiTCallbacks {
    public static final String TAG= BankAnalysis1.class.getSimpleName();

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    RecyclerView recyclerView,uploadedmonth_recycleview,recycler_view_bank_available_;
    ArrayList<String> allNames = new ArrayList<String>();
    BanklistAdapter1 banklistAdapter;
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
    String document,Account_no;
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

    private static final int PICK_PDF_REQUEST = 1;
    private Uri fileUri;
    private String fileName;
    private List<String> fileNameList;
    private List<String> fileDoneList;
    ArrayList<Uri> uriarrayList = new ArrayList<>();
    ArrayList<File> uriarrayList_pic;
    List<Uri> uriarraylist1;
    ArrayList<String> pathlist;

    AVLoadingIndicatorView material_design_ball_scale_ripple_loader;
    LinearLayout grapiclay_parent,uploaded_monthtextlay,requiretxtlay,requirelay_detailslay,missingerror_lay,requiremonthbox_lay,
            uploadrequirebutton_lay,proceednext_lay,eligible_available,proceednext_lay1;

    RelativeLayout uploadedmonth_lay;


    String namevalue,addressvalue,entity_id1,acc_number1;
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
    PickiT pickiT;
    ListView listview;
    AppCompatButton upload,submit;

    private static final int SELECT_VIDEO_REQUEST = 777;
    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 22;
    private static final int PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE = PERMISSION_REQ_ID_RECORD_AUDIO + 1;
    Statementlist statementlist;
    File orginalFile_pic = null;
    String fileget;
    Context context = this;
    FileAdapter fileAdapter;
    TableLayout tabel_row;

    AppCompatEditText Password_edit_txt;

    RecyclerView bankstatement_recycleview;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_bank_analysis1);
        initTools(R.string.bank_anlysis);

        recyclerView=(RecyclerView)findViewById(R.id.banklist_recycleview);
        BarChart barChart=findViewById(R.id.barchart);
        LineChart linechart=findViewById(R.id.linechart);

        pickiT = new PickiT(this, this, this);

        requiremonth=findViewById(R.id.requiremonth);
        uploadmonth=findViewById(R.id.missingmonth);
        missingerror=findViewById(R.id.missing_error);
        hour_radio_group=findViewById(R.id.hour_radio_group);
        tabel_row=findViewById(R.id.tabel_row);
        uriarrayList_pic = new ArrayList<>();
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
        Password_edit_txt=findViewById(R.id.Password_edit_txt);

        upload = findViewById(R.id.uploads);
        listview = findViewById(R.id.listview);
        submit = findViewById(R.id.submit);

        fileNameList = new ArrayList<>();
        fileDoneList = new ArrayList<>();

        material_design_ball_scale_ripple_loader=findViewById(R.id.material_design_ball_scale_ripple_loader);

        Loan_submit_statues();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("entity_id1_upload",entity_id1);
                bank_statement_upload();
            }
        });
        upload_requirebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BankAnalysis1.this,Upload_Activity_Bank.class);
                startActivity(intent);
                finish();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileNameList.clear();
                uriarrayList.clear();
                uriarrayList_pic.clear();
                showFileChooser();
            }
        });

        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                upload_Status();

              // Eligibility_check_doc_checklist_generate();
             //   Applicant_Status();
            }
        });

        proceed_button1.setVisibility(View.GONE);
        proceed_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BankAnalysis1.this, Home.class);
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

        progressDialog = new SpotsDialog(BankAnalysis1.this, R.style.Custom);
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

        recyclerView.setLayoutManager(new LinearLayoutManager(BankAnalysis1.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        banklistAdapter = new BanklistAdapter1(BankAnalysis1.this, items1);

        recycler_view_bank_available_.setLayoutManager(new LinearLayoutManager(BankAnalysis1.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        bank_available_listAdapter = new Bank_available_listAdapter(BankAnalysis1.this,Bank_available_list);



        final Intent in=getIntent();
        adapter=in.getStringExtra("adapter");
        Log.e("adapter",adapter);


                if(adapter.equals("upload"))
                {
                    step123_Bank_analysis_check();
                }else
                {
                     count = 3;
                  //  makeJsonObjReq1();
                    Account_no=in.getStringExtra("Account_no");
                    statusintvalue=in.getStringExtra("statusint");
                    entitynumvalue=in.getStringExtra("entitynumber");
                    step123_Bank_analysis_check();

                    if (adapter!=null){
                        if (statusintvalue.equalsIgnoreCase("0")){
                            Log.i(TAG, "onCreate:documentget "+documentget);
                            FinboxAPICheck(Account_no,entitynumvalue);
                        }else{
                            Log.i(TAG, "onCreate:entitynumber "+entitynumvalue);
                           // GlibAPICheckdetnew(entitynumvalue);
                            GLIP_sift_status_check(entitynumvalue);
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
        fileAdapter = new FileAdapter(fileNameList, fileDoneList);
        listview.setAdapter(fileAdapter);


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
                                eligible_available.setVisibility(View.GONE);

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
                                    eligible_available.setVisibility(View.GONE);
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
                Toast.makeText(BankAnalysis1.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                                Intent intent = new Intent(BankAnalysis1.this, DocumentChecklist_Fragment.class);
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
                Toast.makeText(BankAnalysis1.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Intent intent = new Intent(BankAnalysis1.this, Submitsuccess_Activity.class);
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

    private void step123_Bank_analysis_check() {
        Log.i(TAG, "makeJsonObjReq1: "+"Make");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        J = new JSONObject();
        material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("relationship_type",Pref.getCoAPPAVAILABLE(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            Log.i(TAG, "makeJsonObjReq1:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.e("state_id", String.valueOf(J))
        progressDialog.show();

        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.bankst_anaysisres, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e(TAG, "bankst_anaysisres: " + object.toString());
                        progressDialog.dismiss();

                        try {
                            String status = object.getString("status");
                            Log.e(TAG, "onResponse: " + status);
                            if (status.equals("0")) {
                                Log.e(TAG, "onResponse: " + status);
                                Bank_analysis_run();
                            } else {

                            JSONArray ja = object.getJSONArray("bank_statementarr");


                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject J = ja.getJSONObject(i);
                                String acc_number = J.getString("acc_number");
                                String entity_id = J.getString("entity_id");
                               /* String bank_name=J.getString("bnk_name");
                                String achold_name=J.getString("bnk_name");*/
                                if(acc_number.equals("0"))
                                {

                                }else
                                {
                                items1.add(new Bankdetails_model(J.getString("acc_number"), J.getString("entity_id"), J.getString("bank_name"), J.getString("acchold_name"), J.getString("status_int")));
                                banklistAdapter.notifyDataSetChanged();

                                }

                                if (count == 0) {
                                    if (adapter.equals("upload")) {
                                        Log.e("count called ", String.valueOf(count));
                                        String status_int = J.getString("status_int");

                                        if (status_int.equalsIgnoreCase("0")) {
                                             acc_number1 = J.getString("acc_number");
                                              entity_id1 = J.getString("entity_id");

                                            Log.e("acc_number1",acc_number1);
                                            Log.e("entity_id1",entity_id1);
                                            FinboxAPICheck(acc_number1,entity_id1);
                                        } else {
                                           //  acc_number1 = J.getString("acc_number");
                                             entity_id1 = J.getString("entity_id");
                                            Log.i(TAG, "onCreate:entitynumber " + entity_id);

                                            GLIP_sift_status_check(entity_id1);

                                        }
                                    }

                                } else {
                                    Log.e("count called ", String.valueOf(count));
                                }

                                count = count + 1;

                            }
                            recyclerView.setAdapter(banklistAdapter);

                        }

                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Errorin new: " + error.getMessage());
                Log.e("the issues ","issues new analysis");
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

    private void Bank_analysis_run() {
        Log.i(TAG, "makeJsonObjReq1: "+"Make");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        J = new JSONObject();
      //  material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("relationship_type",Pref.getCoAPPAVAILABLE(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            Log.i(TAG, "makeJsonObjReq1:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.e("state_id", String.valueOf(J))
        progressDialog.show();

        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.upload_bankstanalysis, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        Log.e("upload_bankstanalysis", object.toString());
                            count =0;
                            step123_Bank_analysis_check();


                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Errorin new: " + error.getMessage());
                Log.e("the issues ","issues new analysis");
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

    private void GLIP_sift_status_check(String entity_id1_shift) {
        Log.i(TAG, "sift_status: "+"sift_status");

        String typecnt;
        String applicant_c=Pref.getCoAPPAVAILABLE(getApplicationContext());

        final String relationship_type="1";

        if(applicant_c.equals("1"))
        {
            typecnt = "0";
        }else
        {
            typecnt = "1";
        }

        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        J = new JSONObject();
        //  material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("typecnt",typecnt);
            J.put("entity_id",entity_id1_shift);
            Log.i(TAG, "sift_status:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.e("state_id", String.valueOf(J))
        progressDialog.show();

        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.sift_statuscheck, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        Log.e("sift_status", object.toString());

                        try {
                            String status = object.getString("status");
                           if(status.equals("5"))
                           {
                               GlibAPICheckdetnew(entity_id1_shift);
                           }else
                           {
                               GLIP_sift_status_check(entity_id1_shift);
                           }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        step123_Bank_analysis_check();


                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Errorin new: " + error.getMessage());
                Log.e("the issues ","issues new analysis");
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

    public void bank_statement_upload()
    {

        if (uriarrayList.isEmpty()) {
            Toast.makeText(BankAnalysis1.this, "Please Select Bank statement", Toast.LENGTH_SHORT).show();
        } else if (uriarrayList.size() == 0) {
            Toast.makeText(BankAnalysis1.this, "Please upload document", Toast.LENGTH_SHORT).show();
        } else {
            //    uploadMultipart();
            pathlist = new ArrayList<>();
            for (int i = 0; i < uriarrayList.size(); i++) {
                Log.e("the Upload issues", uriarrayList.get(i).toString());
                String uri_test = uriarrayList.get(i).toString();
                Log.e("the Upload issues", uri_test);
                String filename = uri_test.substring(uri_test.lastIndexOf("/") + 1);
                String pdf1 = filename.substring(filename.lastIndexOf(".") + 1);
                //  uploadMultipart();
                String a = "pdf";

                if (pdf1.equals(a)) {

                    for(i=0;i<uriarrayList_pic.size();i++)
                    {
                        orginalFile_pic = uriarrayList_pic.get(i);


                        String path = String.valueOf(orginalFile_pic);

                        pathlist.add(path);

                        long fileSizeInBytes = orginalFile_pic.length();
                        long fileSizeInKB = fileSizeInBytes / 1024;
                        long fileSizeInMB = fileSizeInKB / 1024;
                        Log.e("the Size is", String.valueOf(fileSizeInMB));
                        if (fileSizeInMB < 3) {
                            CheckUploadcondition();
                        }else
                        {
                            pdf_error();
                            // Toast.makeText(this,"Please upload file less than 3MB.",Toast.LENGTH_SHORT).show();

                        }

                        Log.e("Activity_Ban path1", String.valueOf(orginalFile_pic));
                    }
                                               /* if (SDK_INT < 11) {
                                                    orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(Upload_Activity_Bank.this, uriarrayList.get(i)));
                                                }
                                                // SDK >= 11 && SDK < 19
                                                else if (SDK_INT < 19) {
                                                    orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(Upload_Activity_Bank.this, uriarrayList.get(i)));
                                                }
                                                // SDK > 19 (Android 4.4) and up
                                                else {
                                                    Log.e("path", "api 19 called");
                                                    orginalFile = new File(FileUtils1.getRealPathFromURI_API19(Upload_Activity_Bank.this, uriarrayList.get(i)));

                                                }
                                                String path = String.valueOf(orginalFile);

                                                pathlist.add(path);

                                                long fileSizeInBytes = orginalFile.length();
                                                long fileSizeInKB = fileSizeInBytes / 1024;
                                                long fileSizeInMB = fileSizeInKB / 1024;
                                                Log.e("the Size is", String.valueOf(fileSizeInMB));
                                                if (fileSizeInMB < 3) {

                                                    CheckUploadcondition();
                                                }else
                                                {
                                                    pdf_error();

                                                    // Toast.makeText(this,"Please upload file less than 3MB.",Toast.LENGTH_SHORT).show();

                                                }
                                                // uploadMultipart();

                                                //  MultifileUploadRetrofit();

                                                Log.e("path", path);
                                                Log.e("Upload_Activity_Ban", String.valueOf(orginalFile));*/

                } else {


                    for(i=0;i<uriarrayList_pic.size();i++)
                    {
                        orginalFile_pic = uriarrayList_pic.get(i);


                        String path = String.valueOf(orginalFile_pic);

                        pathlist.add(path);

                        long fileSizeInBytes = orginalFile_pic.length();
                        long fileSizeInKB = fileSizeInBytes / 1024;
                        long fileSizeInMB = fileSizeInKB / 1024;
                        Log.e("the Size is", String.valueOf(fileSizeInMB));
                        if (fileSizeInMB < 3) {
                            CheckUploadcondition();
                        }else
                        {
                            pdf_error();
                            // Toast.makeText(this,"Please upload file less than 3MB.",Toast.LENGTH_SHORT).show();

                        }
                        // uploadMultipart();

                        //  MultifileUploadRetrofit();

                        //  Log.e("path", orginalFile_pic);
                        Log.e("Activity_Ban path", String.valueOf(orginalFile_pic));
                    }
                }


            }
        }

    }


    private void CheckUploadcondition() {


        MultifileUploadRetrofit();
       /* if (checkradiobutton_value.equalsIgnoreCase("1")){
            MultifileUploadRetrofit();
        }else{
            MultifileUploadRetrofitGlib();
        }*/
    }

    private void MultifileUploadRetrofit() {
        ApiConfig getResponse = Appconfig.getRetrofit().create(ApiConfig.class);
        Log.i(TAG, "MultifileUploadRetrofit: "+"check");
        progressDialog.show();
        // List<Uri> uriList = null; //These are the uris for the files to be uploaded
        MediaType mediaType = MediaType.parse("application/pdf");//Based on the Postman logs,it's not specifying Content-Type, this is why I've made this empty content/mediaType
        MultipartBody.Part[] fileParts = new MultipartBody.Part[uriarrayList.size()];
        for (int i = 0; i < uriarrayList.size(); i++) {



            String uri_test = uriarrayList.get(i).toString();
            Log.e("the Upload issues", uri_test);
            String filename = uri_test.substring(uri_test.lastIndexOf("/") + 1);
            String pdf1 = filename.substring(filename.lastIndexOf(".") + 1);
            //  uploadMultipart();
            String a = "pdf";

          /*  if (SDK_INT < 11) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK >= 11 && SDK < 19
            else if (SDK_INT < 19) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK > 19 (Android 4.4) and up
            else {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API19(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }*/
            orginalFile_pic = uriarrayList_pic.get(i);

            // String path = String.valueOf(orginalFile);
            // pathlist.add(path);
            // uploadMultipart();
            //  MultifileUploadRetrofit();

            Log.e("path", String.valueOf(orginalFile_pic));
            Log.e("Upload_Activity_Ban", String.valueOf(orginalFile_pic));


            //File file = new File(uriarrayList.get(i).getPath());
            // File file = new File(orginalFile);
            RequestBody fileBody = RequestBody.create(mediaType, orginalFile_pic);
            //Setting the file name as an empty string here causes the same issue, which is sending the request successfully without saving the files in the backend, so don't neglect the file name parameter.
            fileParts[i] = MultipartBody.Part.createFormData(String.format(Locale.ENGLISH, "img_url[%d]", i), orginalFile_pic.getName(), fileBody);
            // fileParts[i] = MultipartBody.Part.createFormData("img_url", file.getName(), fileBody);
        }
        String transcation_id="60775";
        String analysis_status="1";
        String workorder_id="";
        String typecnt1;
        String Password_edit_txt_=Password_edit_txt.getText().toString();

        final String relationship_type=Pref.getCoAPPAVAILABLE(getApplicationContext());

        if(relationship_type.equals("1"))
        {
             typecnt1="0";
        }else
        {
             typecnt1="1";
        }

        RequestBody transaction_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), Pref.getTRANSACTIONID(getApplicationContext()));

        RequestBody typecnt = RequestBody.create(MediaType.parse("multipart/form-data"), typecnt1);
        RequestBody Password_edt = RequestBody.create(MediaType.parse("multipart/form-data"), Password_edit_txt_);
        RequestBody Enitity_id = RequestBody.create(MediaType.parse("multipart/form-data"), entity_id1);

        Call<UploadFileResponse> call =getResponse.submitNew1("Auth Token", fileParts,transaction_id1,typecnt
                ,Password_edt,Enitity_id);
        call.enqueue(new Callback<UploadFileResponse>() {
            @Override
            public void onResponse(Call<UploadFileResponse> call, retrofit2.Response<UploadFileResponse> response) {
                progressDialog.dismiss();
                Toast.makeText(BankAnalysis1.this, "Submitted Successfully",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "response file uploded"+response);
                if (response.isSuccessful()){
                    UploadFileResponse response1=(UploadFileResponse) response.body();
                    int status=response1.getResponse().getStatus();
                    Log.i(TAG, "onResponse: "+status);
                    if(status==1){
                        fileNameList.clear();
                        uriarrayList.clear();
                        uriarrayList_pic.clear();
                        Submit_upload_sucess();
                    }
                    //    Toast.makeText(Upload_Activity_Bank.this, "Submitted Successfully",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UploadFileResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, "response fail uploded"+t.toString());
                Toast.makeText(BankAnalysis1.this,"Network issue",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void Submit_upload_sucess() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.bank_statement_sucess);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView bankstatement_message=(AppCompatTextView) dialog.findViewById(R.id.bankstatement_message);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);

        //bankstatement_message.setText(bankstatement_msg);



        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
               // step123_Bank_analysis_check();
              Intent intent = new Intent(BankAnalysis1.this, BankAnalysis1.class);
                intent.putExtra("adapter","upload");
              startActivity(intent);
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        if (!dialog.isShowing()) {
            dialog.show();
        }

    }
    private void pdf_error() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.pdf_error);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView bankstatement_message=(AppCompatTextView) dialog.findViewById(R.id.bankstatement_message);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        if (!dialog.isShowing()) {
            dialog.show();
        }

    }

    private void showFileChooser() {
        if (checkSelfPermission()) {
            Intent intent = new Intent();
            intent.setType("application/pdf");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_REQUEST);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK) {
            listview.setVisibility(View.VISIBLE);
            if (data.getClipData() != null) {
                int totalItemsSelected = data.getClipData().getItemCount();
                Log.i("totalItemsSelected", "onActivityResult: " + totalItemsSelected);
                for (int i = 0; i < totalItemsSelected; i++) {
                    fileUri = data.getClipData().getItemAt(i).getUri();
                    String path = fileUri.getPath(); // "/mnt/sdcard/FileName.mp3"
                    Log.i("TAG", "onActivityResult:Stringpath " + path);
                    fileget = String.valueOf(data.getClipData());
                    fileName = getFileName(fileUri);
                    fileNameList.add(fileName);
                    uriarrayList.add(fileUri);
                    //getmultiplelist.add(fileUri);
                    fileAdapter.notifyDataSetChanged();

                    pickiT.getPath(data.getClipData().getItemAt(i).getUri(), Build.VERSION.SDK_INT);
                    // fileDoneList.add("uploading");

                    Log.e("the URI SIZE", String.valueOf(uriarrayList.size()));
                    Log.i("TAG", "onActivityResult:uriarrayList " + uriarrayList);
                    Log.i("Upload_Activity_Bank", "onActivityResult_fileuri: " + fileUri);
                    Log.i("Upload_Activity_Bank", "onActivityResult_path: " + fileget);
                    Log.i("Upload_Activity_Bank", "ActivityResult_filename: " + fileName);
                }
            } else {

                fileUri = data.getData();
                fileget = String.valueOf(data.getData());
                fileName = getFileName(data.getData());
                fileNameList.add(fileName);
                uriarrayList.add(fileUri);
                pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);
                String sav = fileUri.getPath();
                fileAdapter.notifyDataSetChanged();
            }

        }

    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fileAdapter.notifyDataSetChanged();
       // uploadReceiver.register(this);

    }
    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void FinboxAPICheck(String banK_accountNo,String entity_id1_) {

        Log.e(TAG, "Which API: "+"Finbox");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        J = new JSONObject();
        material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);

        String typecnt;
        String applicant_c=Pref.getCoAPPAVAILABLE(getApplicationContext());

        final String relationship_type="1";

        if(applicant_c.equals("1"))
        {
            typecnt = "0";
        }else
        {
            typecnt = "1";
        }
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("typecnt",typecnt);
            J.put("bankaccno",banK_accountNo);
            J.put("entity_id",entity_id1_);
            Log.i(TAG, "FInbox:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  progressDialog.dismiss();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.BANK_STATEMENT_DETAILS, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        JSONArray cast = null;
                        JSONArray ja = null;
                        JSONArray ja1=null;
                        try {
                            // String s=object.getString("display_select");
                            JSONObject bankarr = object.getJSONObject("bank_arr");

                            String metrics_datastatus = bankarr.getString("metrics_datastatus");
                            String alltransaction_datastatus = bankarr.getString("alltransaction_datastatus");
                            String transaction_datastatus = bankarr.getString("transaction_datastatus");
                            String salary_datastatus = bankarr.getString("salary_datastatus");
                            String lender_datastatus = bankarr.getString("lender_datastatus");

                            Log.e("metrics_datastatus",metrics_datastatus);
                            Log.e("alltran_datastatus",alltransaction_datastatus);
                            Log.e("transaction_datastatus",transaction_datastatus);
                            Log.e("salary_datastatus",salary_datastatus);
                            Log.e("lender_datastatus",lender_datastatus);

                            if ((!metrics_datastatus.equals("completed")  || !alltransaction_datastatus.equals("completed")||
                                    !transaction_datastatus.equals("completed") || !salary_datastatus.equals("completed") ||
                                    !lender_datastatus.equals("completed") && !metrics_datastatus.equals("failed"))) {

                                FinboxAPICheck(banK_accountNo, entity_id1_);

                            } else {



                            if (object.has("rule_arr")) {

                                JSONObject dataObject = object.optJSONObject("rule_arr");

                                if (dataObject != null) {

                                    rule_arr = object.getJSONObject("rule_arr");
                                    //Do things with object.
                                    Log.e("the works ", rule_arr.toString());

                                } else {
                                    rule_arr = null;
                                    JSONArray array = object.optJSONArray("rule_arr");

                                    //Do things with array
                                }
                            } else {
                                // Do nothing or throw exception if "data" is a mandatory field
                            }
                            // labelarr=bankarr.getJSONArray("label_arr");
                            JSONArray label_arr = bankarr.getJSONArray("label_arr");
                            JSONArray amount_arr = bankarr.getJSONArray("amount_arr");
                            JSONArray creditdet_arr = bankarr.getJSONArray("creditdet_arr");
                            JSONArray debitdet_arr = bankarr.getJSONArray("debitdet_arr");

                            JSONObject jsonObject1 = object.getJSONObject("uploaded_month");
                            JSONObject jsonObject2 = jsonObject1.getJSONObject("month_arr");
                            Log.i(TAG, "onResponse: " + jsonObject1);
                            String startdate = jsonObject1.getString("start_date");
                            String todate = jsonObject1.getString("end_date");

                            String username = bankarr.getString("acchold_name");
                            String user_addres = bankarr.getString("cust_address");
                            String accnumber = bankarr.getString("acc_number");
                            String bankname = bankarr.getString("bank_name");
                            String account_type = bankarr.getString("acc_type");
                            String fromtodate = bankarr.getString("from_date");
                            String tofromdate = bankarr.getString("to_date");
                            String salary = bankarr.getString("salary_amount");
                            String abb = bankarr.getString("avg_bankbalance");
                            String emi_amount = object.getString("emi_amount");
                            String bal_recol = object.getString("bal_reconcilation");
                            String chequeboun_penal = object.getString("chequeboun_penal");
                            String required_monthstr = object.getString("required_monthstr");
                            Log.i(TAG, "onResponse:required_monthstr " + required_monthstr);
                            Log.i(TAG, "startdate: " + startdate);
                            Log.i(TAG, "todate: " + todate);
                            Log.i(TAG, "acchold_name: " + username);
                            salatxt.setText(salary);
                            requiredatetxt.setText(startdate + " " + "to " + todate);
                            usernametxt.setText(username);
                            user_address.setText(user_addres);
                            account_no.setText(accnumber);
                            bank_name.setText(bankname);
                            account_typetxt.setText(account_type);
                            //  fromtodatetxt.setText(fromtodate+" "+"to"+tofromdate);
                            fromtodatetxt.setText(parseDateToddMMyyyy(fromtodate) + " " + "to" + " " + parseDateToddMMyyyy(tofromdate));

                            abbtxt.setText("\u20B9" + " " + abb);
                            approtxt.setText(emi_amount);
                            baltxt.setText(bal_recol);
                            expradio.setText(chequeboun_penal);
                            missing_yeartxt.setText("Please Upload Bank Statement For The Following Required Months -" + required_monthstr + " " + "in the above Bank Statement Upload Section.");
                            TextView fromtodatetxt, salatxt, abbtxt, approtxt, expen_ratiotxt, baltxt, expradio;

                            if (rule_arr != null) {
                                if (rule_arr.length() > 0) {
                                    for (int i = 0; i < rule_arr.length(); i++) {
                                        Iterator iterator = rule_arr.keys();
                                        while (iterator.hasNext()) {

                                            String key = (String) iterator.next();
                                            Log.i("TAG", "keyvaluecheck: " + key);
                                            Log.e("value", key.toString());

                                            JSONObject response_iD_proof_comon = rule_arr.getJSONObject(key);

                                            String status = response_iD_proof_comon.getString("status");
                                            if (status.equals("1")) {
                                                JSONObject response_iD_proof_comon1 = response_iD_proof_comon.getJSONObject("bank_data");
                                                String bank_logo_cc = response_iD_proof_comon1.getString("bank_logo_cc");
                                                String bank_categorystr = response_iD_proof_comon1.getString("category_name");
                                                String bank_category = response_iD_proof_comon1.getString("bus_category");
                                                String id = response_iD_proof_comon1.getString("id");


                                                Bank_available_list.add(new Bank_available_details_model(bank_logo_cc, bank_categorystr, bank_category, status, id));
                                                bank_available_listAdapter.notifyDataSetChanged();
                                            } else {
                                                JSONObject response_iD_proof_comon1 = response_iD_proof_comon.getJSONObject("bank_data");
                                                String bank_logo_cc = response_iD_proof_comon1.getString("bank_logo_cc");
                                                Log.e("the,bank logo", bank_logo_cc);
                                                String bank_categorystr = response_iD_proof_comon1.getString("category_name");
                                                String bank_category = response_iD_proof_comon1.getString("bus_category");
                                                String id = response_iD_proof_comon1.getString("id");
                                                Bank_available_list.add(new Bank_available_details_model(bank_logo_cc, bank_categorystr, bank_category, status, id));
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
                                float value3 = Float.parseFloat(amount_arr.getString(i));
                                // int value1 = Integer.parseInt(value3);
                                Log.e("json", i + "=" + value3);

                                String value = label_arr.getString(i);
                                // String value="15-May-2019";
                                Date.add(String.valueOf(value));
                                x.add(new BarEntry(i, value3));
                                // x.add(value3);
                                // Log.e("json", i+"="+value3);
                            }
                            for (int i = 0; i < creditdet_arr.length(); i++) {
                                // String value="12";
                                if (creditdet_arr.getString(i).isEmpty()) {

                                } else {
                                    float value3 = Float.parseFloat(creditdet_arr.getString(i));
                                    // int value1 = Integer.parseInt(value3);
                                    Log.e("json", i + "=" + value3);
                                    x1.add(new BarEntry(i, value3));
                                }


                                // x.add(value3);
                                // Log.e("json", i+"="+value3);
                            }
                            for (int i = 0; i < debitdet_arr.length(); i++) {
                                // String value="12";
                                if (debitdet_arr.getString(i).isEmpty()) {

                                } else {
                                    float value3 = Float.parseFloat(debitdet_arr.getString(i));
                                    // int value1 = Integer.parseInt(value3);
                                    Log.e("json", i + "=" + value3);
                                    x11.add(new BarEntry(i, value3));
                                }


                                // x.add(value3);
                                // Log.e("json", i+"="+value3);
                            }
                            for (int i = 0; i < label_arr.length(); i++) {
                                String value = label_arr.getString(i);
                                // String value="15-May-2019";
                                Date.add(String.valueOf(value));
                                Log.e("json", i + "=" + value);
                            }

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
                                Log.e("the value", list_key.toString());

                                JSONArray jsonAray = jsonObject2.getJSONArray(key);
                                for (int i = 0; i < jsonAray.length(); i++) {
                                    JSONObject childrenObject = jsonAray.getJSONObject(i);
                                    Log.i(TAG, "onResponse:lops " + childrenObject);
                                    upload_detstatus = childrenObject.optString("upload_detstatus");
                                    upload_det = childrenObject.optString("upload_det");
                                    Log.i(TAG, "onResponse: upload_detstatus" + upload_detstatus);
                                    year = childrenObject.optString("year");
                                    month_details = childrenObject.getString("month_str");
                                    if (upload_detstatus.equalsIgnoreCase("required")) {
                                        requirelist.add(year + month_details);
                                        Log.i(TAG, "onResponse:requirelist " + requirelist);
                                        if(upload_det.equalsIgnoreCase("required")) {

                                            count_but = count_but + 1;
                                        } else {

                                        }
                                        studentData data1 = new studentData(year + " " + month_details, upload_det);
                                        studentDataList.add(data1);


                                        uploadmonthAdapter = new UploadmonthAdapter(studentDataList);
                                        RecyclerView.LayoutManager manager = new GridLayoutManager(BankAnalysis1.this, 3);
                                        uploadedmonth_recycleview.setLayoutManager(manager);
                                        //  uploadedmonth_recycleview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                                        uploadedmonth_recycleview.setAdapter(uploadmonthAdapter);

                                        uploadmonthAdapter.notifyDataSetChanged();
                                    }

                                }

                                if(count_but >1)
                                {
                                    upload_requirebtn.setVisibility(View.GONE);
                                    uploadrequirebutton_lay.setVisibility(View.VISIBLE);
                                }else
                                {
                                  /*  Intent intent = new Intent(BankAnalysis1.this, Bank_Availability_Check.class);
                                    //  Intent in=new Intent(context,BankAnalysis.class);
                                    intent.putExtra("adapter","upload");
                                    startActivity(intent);*/
                                    upload_requirebtn.setVisibility(View.GONE);
                                    uploadrequirebutton_lay.setVisibility(View.GONE);
                                }

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

        String typecnt;
        String applicant_c=Pref.getCoAPPAVAILABLE(getApplicationContext());

        final String relationship_type="1";

        if(applicant_c.equals("1"))
        {
            typecnt = "0";
        }else
        {
            typecnt = "1";
        }
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        material_design_ball_scale_ripple_loader.setVisibility(View.VISIBLE);
        J = new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("typecnt",typecnt);
            J.put("entity_id",entity);
            Log.i(TAG, "glib:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  progressDialog.dismiss();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.get_bankstatement_detnew, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        JSONArray cast = null;
                        JSONArray ja = null;
                        JSONArray ja1=null;
                        try {
                            JSONObject bankarr=object.getJSONObject("bank_arr");


                           // rule_arr=object.getJSONObject("rule_arr");
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



                           /* if(rule_arr.length()>0)
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
                            }*/

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
                                        RecyclerView.LayoutManager manager = new GridLayoutManager(BankAnalysis1.this, 3);
                                        uploadedmonth_recycleview.setLayoutManager(manager);
                                        //  uploadedmonth_recycleview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                                        uploadedmonth_recycleview.setAdapter(uploadmonthAdapter);
                                        uploadmonthAdapter.notifyDataSetChanged();
                                    }
                                }
                                if(count_but >1)
                                {
                                    upload_requirebtn.setVisibility(View.GONE);
                                    uploadrequirebutton_lay.setVisibility(View.VISIBLE);
                                }else
                                {
                                  /*  Intent intent = new Intent(BankAnalysis1.this, Bank_Availability_Check.class);
                                    //  Intent in=new Intent(context,BankAnalysis.class);
                                    intent.putExtra("adapter","upload");
                                    startActivity(intent);*/

                                    upload_requirebtn.setVisibility(View.GONE);
                                    uploadrequirebutton_lay.setVisibility(View.GONE);
                                }
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        material_design_ball_scale_ripple_loader.setVisibility(View.GONE);
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


    //  Check if permissions was granted
    private boolean checkSelfPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE);
            return false;
        }
        return true;
    }

    //  Handle permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //  Permissions was granted, open the gallery
                showFileChooser();
            }
            //  Permissions was not granted
            else {
                showLongToast("No permission for " + Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        }
    }

    //Show Toast
    private void showLongToast(final String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
    ProgressBar mProgressBar;
    TextView percentText;
    private androidx.appcompat.app.AlertDialog mdialog;
    ProgressDialog progressBar;

    @Override
    public void PickiTonUriReturned() {
        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Waiting to receive file...");
        progressBar.setCancelable(false);
        progressBar.show();
    }

    @Override
    public void PickiTonStartListener() {
        if (progressBar.isShowing()){
            progressBar.cancel();
        }
        final androidx.appcompat.app.AlertDialog.Builder mPro = new androidx.appcompat.app.AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
        @SuppressLint("InflateParams") final View mPView = LayoutInflater.from(this).inflate(R.layout.dailog_layout, null);
        percentText = mPView.findViewById(R.id.percentText);

        percentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickiT.cancelTask();
                if (mdialog != null && mdialog.isShowing()) {
                    mdialog.cancel();
                }
            }
        });

        mProgressBar = mPView.findViewById(R.id.mProgressBar);
        mProgressBar.setMax(100);
        mPro.setView(mPView);
        mdialog = mPro.create();
        mdialog.show();

    }

    @Override
    public void PickiTonProgressUpdate(int progress) {
        String progressPlusPercent = progress + "%";
        percentText.setText(progressPlusPercent);
        mProgressBar.setProgress(progress);
    }

    @Override
    public void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String reason) {

        if (mdialog != null && mdialog.isShowing()) {
            mdialog.cancel();
        }

        //  Check if it was a Drive/local/unknown provider file and display a Toast
        if (wasDriveFile){
            //   showLongToast("Drive file was selected");
        }else if (wasUnknownProvider){
            //  showLongToast("File was selected from unknown provider");
        }else {
            //   showLongToast("Local file was selected");
        }

        //  Chick if it was successful
        if (wasSuccessful) {
            //  Set returned path to TextView
            uriarrayList_pic.add(new File(path));

            Log.e("the Path selected PIC",uriarrayList_pic.toString());
            Log.e("the URI SIZE", String.valueOf(uriarrayList.size()));
            Log.e("the URI SIZE1", String.valueOf(uriarrayList_pic.size()));
        }else {
            showLongToast("Error, please see the log..");

        }
    }


    //
    //  Lifecycle methods
    //

    //  Deleting the temporary file if it exists
    //  As we know, this might not even be called if the system kills the application before onDestroy is called
    //  So, it is best to call pickiT.deleteTemporaryFile(); as soon as you are done with the file
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!isChangingConfigurations()) {
            pickiT.deleteTemporaryFile(this);
        }
    }

    @Override
    public void onClick(View view) {

    }


}