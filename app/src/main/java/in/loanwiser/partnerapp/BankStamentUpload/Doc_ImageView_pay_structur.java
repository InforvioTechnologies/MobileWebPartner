package in.loanwiser.partnerapp.BankStamentUpload;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;

import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class Doc_ImageView_pay_structur extends SimpleActivity {
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Doc_ImageView_pay_structur.class.getSimpleName();
    private AlertDialog progressDialog;
    String id,doc_typename,docid,class_id,user_type,transaction_id,doc_id;
    ImageView v_Image;
    ProgressBar progressBar;
    View view;
    WebView webview;
    ProgressBar progressbar;
    LinearLayout Ly_image_reader;
    RelativeLayout Rl_pdf_reader;
    String type,document,hash,filename,report;
    FloatingActionButton float_chat;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;
    PermissionUtils permissionUtils;
    private ProgressDialog pDialog;
    ImageView my_image;
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final int progress_bar_type = 0;
    TableLayout tabel_row;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_doc__image_view);
        setContentView(R.layout.activity_simple);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        Objs.a.setStubId(this, R.layout.activity_doc__image_view_new);
       // report =  Objs.a.getBundle(this, Params.report);
        initTools1("Payout Structure");
        Ly_image_reader = (LinearLayout)findViewById(R.id.Ly_image_reader);
        Rl_pdf_reader = (RelativeLayout)findViewById(R.id.Rl_pdf_reader);
        float_chat = (FloatingActionButton) findViewById(R.id.float_chat);

        tabel_row = (TableLayout) findViewById(R.id.tabel_row);
     //   document =  Objs.a.getBundle(this, Params.document);

        document =  "https://callcenter.loanwiser.in/includes/DETAILED-PAYOUT-STRUCTURE.pdf";
        permissionUtils = new PermissionUtils();
       // Log.e("pfd",document);
       // Log.e("type",type);
       // hash =  Objs.a.getBundle(this, Params.transaction_id);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        webview = (WebView)findViewById(R.id.webview);
            Rl_pdf_reader.setVisibility(View.VISIBLE);
            Ly_image_reader.setVisibility(View.GONE);
            webview.getSettings().setJavaScriptEnabled(true);
             filename =  document;
         //  webview.loadUrl("https://docs.google.com/gview?embedded=true&url=" + filename);


        payout_structure();




    }

    private void payout_structure()
    {
        JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        try {
            J = new JSONObject();

            J.put("partner_type", "individual_partner");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("bank state rule request", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Get_BankcomDetails, J,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @SuppressLint({"LongLogTag", "ResourceType"})
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("rule response", String.valueOf(response));
                        try {


                            TableRow tr_head = new TableRow(getApplicationContext());
                            tr_head.setId(10);
                            tr_head.setBackgroundColor(Color.GRAY);
                            tr_head.setLayoutParams(new ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.FILL_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT));

                            TextView label_date = new TextView(getApplicationContext());
                            label_date.setId(20);
                            label_date.setText("Bank Name" + "  ");
                            label_date.setTextColor(Color.WHITE);
                            label_date.setPadding(5, 5, 5, 5);
                            tr_head.addView(label_date);// add the column to the table row here

                            TextView label_weight_kg = new TextView(getApplicationContext());
                            label_weight_kg.setId(21);// define id that must be unique
                            label_weight_kg.setText("HL" + "  "); // set the text for the header
                            label_weight_kg.setTextColor(Color.WHITE); // set the color
                            label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
                            tr_head.addView(label_weight_kg); // add the column to the table row here

                            TextView Fail_Message = new TextView(getApplicationContext());
                            Fail_Message.setId(22);// define id that must be unique
                            Fail_Message.setText("LAP" + "  "); // set the text for the header
                            Fail_Message.setTextColor(Color.WHITE); // set the color
                            Fail_Message.setPadding(5, 5, 5, 5); // set the padding (if required)
                            tr_head.addView(Fail_Message); // add the column to the table row here

                            TextView PL_c = new TextView(getApplicationContext());
                            PL_c.setId(22);// define id that must be unique
                            PL_c.setText("PL" + "  "); // set the text for the header
                            PL_c.setTextColor(Color.WHITE); // set the color
                            PL_c.setPadding(5, 5, 5, 5); // set the padding (if required)
                            tr_head.addView(PL_c); // add the column to the table row here

                            TextView BL_c = new TextView(getApplicationContext());
                            BL_c.setId(23);// define id that must be unique
                            BL_c.setText("BL" + "  "); // set the text for the header
                            BL_c.setTextColor(Color.WHITE); // set the color
                            BL_c.setPadding(5, 5, 5, 5); // set the padding (if required)
                            tr_head.addView(BL_c); // add the column to the table row here

                            TextView VL_c = new TextView(getApplicationContext());
                            VL_c.setId(24);// define id that must be unique
                            VL_c.setText("VL" + "  "); // set the text for the header
                            VL_c.setTextColor(Color.WHITE); // set the color
                            VL_c.setPadding(5, 5, 5, 5); // set the padding (if required)
                            tr_head.addView(VL_c); // add the column to the table row here

                            TextView Cc_c = new TextView(getApplicationContext());
                            Cc_c.setId(25);// define id that must be unique
                            Cc_c.setText("CC" + "  "); // set the text for the header
                            Cc_c.setTextColor(Color.WHITE); // set the color
                            Cc_c.setPadding(5, 5, 5, 5); // set the padding (if required)
                            tr_head.addView(Cc_c); // add the column to the table row here

                            tabel_row.addView(tr_head, new TableLayout.LayoutParams(
                                    ViewGroup.LayoutParams.FILL_PARENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT));

                            JSONObject jsonObject1 = null;

                            JSONArray response1 = response.getJSONArray("payout_list");
                            for (int i = 0; i < response1.length(); i++) {

                                JSONObject J = null;
                                try {

                                    J = response1.getJSONObject(i);

                                    String bank_name = J.getString("bank_name");
                                    String hl_comission = J.getString("hl_comission");
                                    String lap_comission = J.getString("lap_comission");
                                    String pl_comission = J.getString("pl_comission");
                                    String bl_comission = J.getString("bl_comission");
                                    String vl_comission = J.getString("vl_comission");
                                    String cc_comission = J.getString("cc_comission");

                                    TableRow tr = new TableRow(getApplicationContext());
                                    tr.setId(100 + i);
                                    tr.setLayoutParams(new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.FILL_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT));

                                    TextView Bank_Name = new TextView(getApplicationContext());
                                    Bank_Name.setId(100 + i);
                                    Bank_Name.setText(bank_name);
                                    label_weight_kg.setPadding(5, 5, 5, 5);
                                    tr.addView(Bank_Name);

                                    double hl_comission1 = Double.parseDouble(hl_comission);
                                    if(hl_comission1==0)
                                    {
                                        TextView rule_description = new TextView(getApplicationContext());
                                        rule_description.setId(200 + i);
                                        rule_description.setText("-");
                                        label_weight_kg.setPadding(5, 5, 5, 5);
                                        tr.addView(rule_description);

                                    }else
                                    {
                                        if(hl_comission1>50)
                                        {
                                            TextView rule_description = new TextView(getApplicationContext());
                                            rule_description.setId(200 + i);
                                            rule_description.setText( getResources().getString(R.string.rs)+" " +hl_comission1+" ");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(rule_description);

                                        }else {
                                            TextView rule_description = new TextView(getApplicationContext());
                                            rule_description.setId(200 + i);
                                            rule_description.setText( +hl_comission1+"%");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(rule_description);
                                        }
                                    }


                                  //  int lap_comission1 = Integer.parseInt(lap_comission);
                                    double lap_comission1 = Double.parseDouble(lap_comission);
                                    if(lap_comission1==0)
                                    {

                                        TextView fail_message1 = new TextView(getApplicationContext());
                                        fail_message1.setId(300 + i);
                                        fail_message1.setText("-");
                                       // fail_message1.setTextColor(Color.parseColor("#33b48d"));
                                        label_weight_kg.setPadding(5, 5, 5, 5);
                                        tr.addView(fail_message1);

                                    }else
                                    {
                                        if(lap_comission1>50)
                                        {

                                            TextView fail_message1 = new TextView(getApplicationContext());
                                            fail_message1.setId(300 + i);
                                            fail_message1.setText(getResources().getString(R.string.rs)+" " +lap_comission1+" ");
                                          //  fail_message1.setTextColor(Color.parseColor("#33b48d"));
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(fail_message1);

                                        }else {

                                            TextView fail_message1 = new TextView(getApplicationContext());
                                            fail_message1.setId(300 + i);
                                            fail_message1.setText(lap_comission1+"%");
                                           // fail_message1.setTextColor(Color.parseColor("#33b48d"));
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(fail_message1);
                                        }
                                    }


                                    double pl_comission1 = Double.parseDouble(pl_comission);
                                    if(pl_comission1==0)
                                    {


                                        TextView PL_status1 = new TextView(getApplicationContext());
                                        PL_status1.setId(300 + i);
                                        PL_status1.setText("-");
                                        label_weight_kg.setPadding(5, 5, 5, 5);
                                        tr.addView(PL_status1);

                                    }else
                                    {
                                        if(pl_comission1>50)
                                        {

                                            TextView PL_status1 = new TextView(getApplicationContext());
                                            PL_status1.setId(300 + i);
                                            PL_status1.setText(getResources().getString(R.string.rs)+" " +pl_comission1+" ");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(PL_status1);

                                        }else {

                                            TextView PL_status1 = new TextView(getApplicationContext());
                                            PL_status1.setId(300 + i);
                                            PL_status1.setText(pl_comission1+"%");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(PL_status1);
                                        }
                                    }

                                    double bl_comission1 = Double.parseDouble(bl_comission);
                                    if(bl_comission1==0)
                                    {

                                        TextView BL_status1 = new TextView(getApplicationContext());
                                        BL_status1.setId(300 + i);
                                        BL_status1.setText(" -");
                                        label_weight_kg.setPadding(5, 5, 5, 5);
                                        tr.addView(BL_status1);

                                    }else
                                    {
                                        if(bl_comission1>50)
                                        {
                                            TextView BL_status1 = new TextView(getApplicationContext());
                                            BL_status1.setId(300 + i);
                                            BL_status1.setText(getResources().getString(R.string.rs)+" " +bl_comission1+" ");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(BL_status1);

                                        }else {


                                            TextView BL_status1 = new TextView(getApplicationContext());
                                            BL_status1.setId(300 + i);
                                            BL_status1.setText(bl_comission1+"%");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(BL_status1);
                                        }
                                    }



                                    double vl_comission1 = Double.parseDouble(vl_comission);
                                    if(vl_comission1==0)
                                    {

                                        TextView VL_status1 = new TextView(getApplicationContext());
                                        VL_status1.setId(300 + i);
                                        VL_status1.setText("-");
                                        label_weight_kg.setPadding(5, 5, 5, 5);
                                        tr.addView(VL_status1);

                                    }else
                                    {
                                        if(vl_comission1>50)
                                        {
                                            TextView VL_status1 = new TextView(getApplicationContext());
                                            VL_status1.setId(300 + i);
                                            VL_status1.setText(getResources().getString(R.string.rs)+" " +vl_comission1+" ");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(VL_status1);

                                        }else {

                                            TextView VL_status1 = new TextView(getApplicationContext());
                                            VL_status1.setId(300 + i);
                                            VL_status1.setText(vl_comission1+"%");

                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(VL_status1);
                                        }
                                    }


                                    double cc_comission1 = Double.parseDouble(cc_comission);
                                    if(cc_comission1==0)
                                    {

                                        TextView CC_status1 = new TextView(getApplicationContext());
                                        CC_status1.setId(300 + i);
                                        CC_status1.setText("-");
                                        label_weight_kg.setPadding(5, 5, 5, 5);
                                        tr.addView(CC_status1);

                                    }else
                                    {
                                        if(cc_comission1>50)
                                        {
                                            TextView CC_status1 = new TextView(getApplicationContext());
                                            CC_status1.setId(300 + i);
                                            CC_status1.setText(getResources().getString(R.string.rs)+" " +cc_comission1+" ");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(CC_status1);

                                        }else {

                                            TextView CC_status1 = new TextView(getApplicationContext());
                                            CC_status1.setId(300 + i);
                                            CC_status1.setText(cc_comission1+"%");
                                            label_weight_kg.setPadding(5, 5, 5, 5);
                                            tr.addView(CC_status1);

                                        }
                                    }



                                    tabel_row.addView(tr, new TableLayout.LayoutParams(
                                            ViewGroup.LayoutParams.FILL_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT));
                                } catch (JSONException e) {
                                    e.printStackTrace();
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
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
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



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }



}


