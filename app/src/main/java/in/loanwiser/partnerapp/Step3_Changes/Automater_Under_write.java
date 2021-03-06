package in.loanwiser.partnerapp.Step3_Changes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;

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
import in.loanwiser.partnerapp.BankStamentUpload.BankAnalysis;
import in.loanwiser.partnerapp.BankStamentUpload.Upload_Activity_Bank;
import in.loanwiser.partnerapp.Documents.Bank_Availability_Check;
import in.loanwiser.partnerapp.Documents.Document_Availability_Check;
import in.loanwiser.partnerapp.PDF_Viewer.MainActivity;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.Payment.PaymentDetails;
import in.loanwiser.partnerapp.Payment.Payment_Sucess_Screen;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.CRIF_Report_Activity_PDF_View;

public class Automater_Under_write extends SimpleActivity {


    CardView Bank_statement_analysisi,Bank_statement_eligible,CRIF_report;

    private AlertDialog progressDialog;
    String Statues_vability,viable_url,app_crif_score,app_crif_url,coapp_crif_score,coapp_crif_url,viable_statues,
            overall_status,crif_fail,app_crif_url_view,coapp_crif_url_view,viable_url_view;
    String  credit_issued_id,credit_issued_value,Report_ID,Order_ID,viability_report_URL,
            trans_id,user_id;
    String[] Other_Earning_SA;
    ArrayAdapter<String> Other_Earning_Adapter;
    String applicantcount;
    Typeface font;
    PopupWindow popupWindow,popupWindow1,pop_up_loader;
    private Context mCon = this;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    AppCompatSpinner questions_spinner;
    AppCompatButton submit_update_status;
    AppCompatTextView step_1detail;
    ImageView step1_image_completed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_automater_under_write);
        initTools(R.string.au_Un_Wr);
        Bank_statement_analysisi= findViewById(R.id.Bank_statement_analysisi);
        Bank_statement_eligible= findViewById(R.id.Bank_statement_eligible);
        submit_update_status= findViewById(R.id.submit_update_status);
        step_1detail= findViewById(R.id.step_1detail);
        step1_image_completed= findViewById(R.id.step1_image_completed);

        CRIF_report= findViewById(R.id.CRIF_report);
        progressDialog = new SpotsDialog(mCon, R.style.Custom);
        applicantcount =  Pref.getCoAPPAVAILABLE(getApplicationContext());

        Automater_unserwrite_call();

        CRIF_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater1 = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView1 = layoutInflater1.inflate(R.layout.popup_loading,null);
                pop_up_loader = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                //display the popup window
                pop_up_loader.showAtLocation(CRIF_report, Gravity.CENTER, 0, 0);
                Crif_Generation();
            }
        });

        Bank_statement_analysisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Automater_Under_write.this, BankAnalysis.class);
                //  Intent in=new Intent(context,BankAnalysis.class);
                intent.putExtra("adapter","upload");
                startActivity(intent);
            }
        });

      /*  Bank_statement_eligible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Automater_Under_write.this, Bank_Availability_Check.class);
                //  Intent in=new Intent(context,BankAnalysis.class);
                intent.putExtra("adapter","upload");
                startActivity(intent);
            }
        });*/

        submit_update_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_Status();
            }
        });

     //   setContentView(R.layout.activity_automater_under_write);

    }



    private void upload_Status() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.transaction_id, Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("comp_status", "3");
            J.put("subcomp_status", "4");

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
                        Log.e("Documnet_upload_Status1", String.valueOf(response));
                        //{"request":{"transaction_id":"10194"},"response":true,"status":"success"}
                        Intent intent = new Intent(Automater_Under_write.this, Elegibility_Report.class);
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

    private void Crif_Generation() {

        JSONObject J =new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("relationship_type",applicantcount);
            //  J.put("relationship_type","2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  progressDialog.show();
        Log.e("Crif Generation", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CRIF_Generation, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment", String.valueOf(object));
                        try {

                            String Statues = object.getString("status");


                            if (Statues.equals("success")) {


                                Automater_unserwrite_call();
                                JSONObject data = object.getJSONObject("data");
                                overall_status = data.getString("overall_status");
                              /*  JSONObject data = object.getJSONObject("data");
                                overall_status = data.getString("overall_status");
                                if (applicantcount.equals("1")) {

                                    if(overall_status.equals("0"))
                                    {
                                        Viability_CRIF_report_Functions();
                                    }else
                                    {
                                        Viability_CRIF_report_Functions();
                                    }

                                } else {
                                    Co_applicant_Crif_Generation();
                                }*/


                            } else if (Statues.equals("question")) {

                              /*  LayoutInflater layoutInflater1 = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View customView1 = layoutInflater1.inflate(R.layout.popup_loading,null);
                                pop_up_loader = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                //display the popup window
                                pop_up_loader.showAtLocation(CRIF_report, Gravity.CENTER, 0, 0);*/
                                // standard.setVisibility(View.GONE);
                                JSONObject data1 = object.getJSONObject("data");
                                Report_ID = data1.getString("reportId");
                                Order_ID = data1.getString("orderId");
                                String question = object.getString("question");
                                String name = object.getString("name");
                                final JSONArray qus_dropdown = object.getJSONArray("qus_dropdown");
                                //  applicant_question.setVisibility(View.VISIBLE);

                                //  cr_app1.setText(question);
                                //  credit_card_app.setVisibility(View.VISIBLE);
                                // Other_Earning(qus_dropdown);
                             //   progressDialog.dismiss();
                                final Dialog dialog = new Dialog(Automater_Under_write.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dialog.setContentView(R.layout.question_layout);
                                //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
                                dialog.setCancelable(true);
                                dialog.setCanceledOnTouchOutside(false);
                                final AppCompatButton submit_buton = (AppCompatButton) dialog.findViewById(R.id.submit_buton);
                                AppCompatTextView applicant = (AppCompatTextView) dialog.findViewById(R.id.applicant);
                                questions_spinner = (AppCompatSpinner) dialog.findViewById(R.id.question_spinner);
                                AppCompatTextView question_tag = (AppCompatTextView) dialog.findViewById(R.id.question_tag);
                                AppCompatTextView question_content = (AppCompatTextView) dialog.findViewById(R.id.question_content);
                                AppCompatTextView applicant_type = (AppCompatTextView) dialog.findViewById(R.id.applicant_type);
                                dialog.show();

                                question_tag.setText("Dear" + " " + name + " " + "Please answer this Question for verification ");
                                question_content.setText(question + "?");
                                //Other_Earning(qus_dropdown);
                                applicant.setText("Applicant");
                                Other_Earning_SA = new String[qus_dropdown.length()];
                                for (int i = 0; i < qus_dropdown.length(); i++) {
                                    JSONObject J = qus_dropdown.getJSONObject(i);
                                    Other_Earning_SA[i] = J.getString("value");
                                    final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Other_Earning_SA));
                                    Other_Earning_Adapter = new ArrayAdapter<String>(mCon, R.layout.view_spinner_item, loan_type_list) {
                                        public View getView(int position, View convertView, ViewGroup parent) {
                                            font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
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
                                    questions_spinner.setAdapter(Other_Earning_Adapter);
                                    questions_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                            try {

                                                credit_issued_id = qus_dropdown.getJSONObject(position).getString("id");
                                                credit_issued_value = qus_dropdown.getJSONObject(position).getString("value");
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
                                   /* applicant_credite_card_spinner.setOnTouchListener(new View.OnTouchListener() {
                                        @Override
                                        public boolean onTouch(View view, MotionEvent motionEvent) {
                                            // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                                            return false;
                                        }
                                    });*/
                                }

                                submit_buton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        if (applicantcount.equals("1")) {
                                            dialog.dismiss();
                                            LayoutInflater layoutInflater1 = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                            View customView1 = layoutInflater1.inflate(R.layout.popup_loading, null);
                                            popupWindow1 = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                            //display the popup window
                                            popupWindow1.showAtLocation(submit_buton, Gravity.CENTER, 0, 0);
                                            CRIF_Question_Testing();
                                        } else {
                                            dialog.dismiss();
                                            CRIF_Question_Testing();
                                        }


                                    }
                                });

                                // co_applicant_.setVisibility(View.VISIBLE);
                            }else
                            {
                             //   pop_up_loader.dismiss();
                                step_1detail.setText("CRIF Faild");
                                step1_image_completed.setVisibility(View.VISIBLE);
                                step1_image_completed.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning));
                                Toast.makeText(mCon, "CRIF Statues Failed",Toast.LENGTH_SHORT).show();
                                Bank_statement_analysisi.setEnabled(false);
                                Bank_statement_eligible.setEnabled(false);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                      //  progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(mCon, "error, somthing went wrong!!!",Toast.LENGTH_SHORT).show();
             //   progressDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

        // AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void Viability_CRIF_report_Functions() {

        JSONObject J =new JSONObject();
        try {
          /*  J.put("transaction_id","58440");
            J.put("user_id","56830");*/
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //  progressDialog.show();
        Log.e("Crif Generation_submit", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.report_links, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment", String.valueOf(object));
                        try {

                            String Statues = object.getString("status");
                            JSONObject jsonObject = object.getJSONObject("viable");
                            viable_statues = jsonObject.getString("applicant_status");

                          /*  JSONObject data1 = object.getJSONObject("data");
                            Report_ID  = data1.getString("reportId");
                            Order_ID  = data1.getString("orderId");*/
                            pop_up_loader.dismiss();
                            LayoutInflater layoutInflater = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View customView = layoutInflater.inflate(R.layout.popup_loading,null);
                            popupWindow1 = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                            //display the popup window
                            popupWindow1.dismiss();
                            if (Statues.equals("success")) {

                                Log.e("applicantcount", String.valueOf(applicantcount));
                                if (applicantcount.equals("1")) {
                                    if (overall_status.equals("0")) {

                                        viable_url = object.getString("viable_url");
                                        app_crif_score = object.getString("app_crif_score");
                                        app_crif_url = object.getString("app_crif_url");
                                        app_crif_url_view = object.getString("app_crif_url_view");
                                        viable_url_view = object.getString("viable_url_view");
                                        trans_id = object.getString("trans_id");
                                        user_id = object.getString("user_id");
                                        CRIF_Fails();

                                    } else if(overall_status.equals("1")){


                                        viable_url = object.getString("viable_url");
                                        app_crif_score = object.getString("app_crif_score");
                                        app_crif_url = object.getString("app_crif_url");
                                        app_crif_url_view = object.getString("app_crif_url_view");
                                        viable_url_view = object.getString("viable_url_view");



                                        LayoutInflater layoutInflater1 = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        View customView1 = layoutInflater1.inflate(R.layout.activity_viablity_sucess_, null);

                                       //
                                        AppCompatButton nextahed = (AppCompatButton) customView1.findViewById(R.id.nextahed);
                                        LinearLayout background = (LinearLayout) customView1.findViewById(R.id.background);
                                        LinearLayout co_applicant = (LinearLayout) customView1.findViewById(R.id.co_applicant);
                                        LinearLayout co_Applicant_ly = (LinearLayout) customView1.findViewById(R.id.co_Applicant_ly);
                                        AppCompatTextView crifscore = (AppCompatTextView) customView1.findViewById(R.id.crifscore);
                                        AppCompatButton view_viablity_report = (AppCompatButton) customView1.findViewById(R.id.view_viablity_report);
                                        AppCompatButton view_CRIF_report = (AppCompatButton) customView1.findViewById(R.id.view_CRIF_report);

                                        TextView score = (TextView) customView1.findViewById(R.id.score);
                                        background.setBackground(getResources().getDrawable(R.drawable.capsul_button_rect_viability));
                                        co_applicant.setVisibility(View.GONE);
                                        co_Applicant_ly.setVisibility(View.GONE);
                                        crifscore.setText(app_crif_score);
                                        Log.e("crif_Score", app_crif_score);
                                        //instantiate popup window
                                        popupWindow1 = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                        //display the popup window
                                        popupWindow1.showAtLocation(nextahed, Gravity.CENTER, 0, 0);

                                        ImageView close = customView1.findViewById(R.id.close);
                                        //close the popup window on button click
                                        close.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                popupWindow1.dismiss();

                                            }
                                        });
                                        nextahed.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                              //  STEP2_COMPLETE();
                                                popupWindow1.dismiss();
                                            }
                                        });

                                        view_viablity_report.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                           /* if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                                if (viable_url.length() > 0) {
                                                    try {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viable_url)));
                                                    } catch (Exception e) {
                                                        e.getStackTrace();
                                                        Toast.makeText(mCon, "Please Try again",Toast.LENGTH_SHORT).show();

                                                    }
                                                }

                                            }*/

                                                String report="Viability Report";
                                                Intent intent = new Intent(Automater_Under_write.this, MainActivity.class);
                                                intent.putExtra("viability_report_URL", viable_url);
                                                intent.putExtra("report", report);
                                                startActivity(intent);
                                         /*   String report = "Viability Report";
                                            Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                    Params.document, viable_url, Params.report, report,
                                                    Params.viable_url_view, viable_url_view);
*/
                                            }

                                        });
                                        view_CRIF_report.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                           /* if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                                if (app_crif_url.length() > 0) {
                                                    try {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(app_crif_url)));
                                                    } catch (Exception e) {
                                                        e.getStackTrace();
                                                    }
                                                }

                                            }*/

                                                String report="CRIF Report";
                                                Intent intent = new Intent(Automater_Under_write.this, MainActivity.class);
                                                intent.putExtra("viability_report_URL", app_crif_url);
                                                intent.putExtra("report", report);
                                                startActivity(intent);

                                           /* String report = "CRIF Report";
                                            Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                    Params.document, app_crif_url, Params.report, report,
                                                    Params.viable_url_view, app_crif_url_view
                                                    );*/
                                           /* Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                    Params.document,app_crif_url);*/

                                            }

                                        });
                                        //  viability_check_pass();

                                    }
                                }else {
                                    if (overall_status.equals("0")) {


                                        Log.e("applicantcount 111", String.valueOf(applicantcount));
                                        viable_url = object.getString("viable_url");
                                        app_crif_score = object.getString("app_crif_score");
                                        app_crif_url = object.getString("app_crif_url");
                                        coapp_crif_score = object.getString("coapp_crif_score");
                                        coapp_crif_url = object.getString("coapp_crif_url");
                                        app_crif_url_view = object.getString("app_crif_url_view");
                                        coapp_crif_url_view = object.getString("app_crif_url_view");
                                        viable_url_view = object.getString("viable_url_view");

                                        CRIF_Fails();

                                    } else  if(overall_status.equals("1")){

                                        Log.e("applicantcount 111", String.valueOf(applicantcount));
                                        viable_url = object.getString("viable_url");
                                        app_crif_score = object.getString("app_crif_score");
                                        app_crif_url = object.getString("app_crif_url");
                                        coapp_crif_score = object.getString("coapp_crif_score");
                                        coapp_crif_url = object.getString("coapp_crif_url");
                                        app_crif_url_view = object.getString("app_crif_url_view");
                                        coapp_crif_url_view = object.getString("app_crif_url_view");
                                        viable_url_view = object.getString("viable_url_view");

                                        LayoutInflater layoutInflater1 = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        View customView1 = layoutInflater1.inflate(R.layout.activity_viablity_sucess_, null);
//
                                        AppCompatButton nextahed = (AppCompatButton) customView1.findViewById(R.id.nextahed);
                                        AppCompatButton view_viablity_report = (AppCompatButton) customView1.findViewById(R.id.view_viablity_report);
                                        AppCompatButton view_CRIF_report = (AppCompatButton) customView1.findViewById(R.id.view_CRIF_report);
                                        AppCompatButton view_co_App_CRIF_report = (AppCompatButton) customView1.findViewById(R.id.view_co_App_CRIF_report);
                                        LinearLayout co_applicant = (LinearLayout) customView1.findViewById(R.id.co_applicant);
                                        LinearLayout co_Applicant_ly = (LinearLayout) customView1.findViewById(R.id.co_Applicant_ly);
                                        AppCompatTextView crifscore = (AppCompatTextView) customView1.findViewById(R.id.crifscore);
                                        LinearLayout background = (LinearLayout) customView1.findViewById(R.id.background);
                                        AppCompatTextView co_appcrifscore = (AppCompatTextView) customView1.findViewById(R.id.co_appcrifscore);
                                        background.setBackground(getResources().getDrawable(R.drawable.capsul_button_rect_viability));

                                        //  TextView score= (TextView) customView1.findViewById(R.id.score);
                                        co_applicant.setVisibility(View.VISIBLE);
                                        co_Applicant_ly.setVisibility(View.VISIBLE);
                                        crifscore.setText(app_crif_score);
                                        co_appcrifscore.setText(coapp_crif_score);
                                        Log.e("crif_Score", app_crif_score);

                                        //instantiate popup window
                                        popupWindow1 = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                        //display the popup window
                                        popupWindow1.showAtLocation(nextahed, Gravity.CENTER, 0, 0);


                                        ImageView close = customView1.findViewById(R.id.close);
                                        //close the popup window on button click
                                        close.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                popupWindow1.dismiss();

                                            }
                                        });
                                        //close the popup window on button click
                                        nextahed.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                popupWindow1.dismiss();
                                               // STEP2_COMPLETE();
                                            }
                                        });

                                        view_viablity_report.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                  /*  if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                                        if (viable_url.length() > 0) {
                                                            try {
                                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viable_url)));
                                                            } catch (Exception e) {
                                                                e.getStackTrace();
                                                            }
                                                        }

                                                    }*/

                                                String report="Viability Report";
                                                Intent intent = new Intent(Automater_Under_write.this, MainActivity.class);
                                                intent.putExtra("viability_report_URL", viable_url);
                                                intent.putExtra("report", report);
                                                startActivity(intent);

                                              /*  String report = "Viability Report";
                                                Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                        Params.document, viable_url,Params.report, report,Params.viable_url_view, viable_url_view);
*/
                                            }

                                        });
                                        view_CRIF_report.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                           /* if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                                if (app_crif_url.length() > 0) {
                                                    try {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(app_crif_url)));
                                                    } catch (Exception e) {
                                                        e.getStackTrace();
                                                    }
                                                }

                                            }*/

                                                String report="CRIF Report";
                                                Intent intent = new Intent(Automater_Under_write.this, MainActivity.class);
                                                intent.putExtra("viability_report_URL", app_crif_url);
                                                intent.putExtra("report", report);
                                                startActivity(intent);

                                               /* String report = "CRIF Report";
                                                Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                        Params.document, app_crif_url,
                                                        Params.viable_url_view,Params.report, report, app_crif_url_view);*/

                                            }

                                        });

                                        view_co_App_CRIF_report.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                           /* if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                                if (coapp_crif_url.length() > 0) {
                                                    try {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(coapp_crif_url)));
                                                    } catch (Exception e) {
                                                        e.getStackTrace();
                                                    }
                                                }

                                            }*/

                                                String report="CRIF Report";
                                                Intent intent = new Intent(Automater_Under_write.this, MainActivity.class);
                                                intent.putExtra("viability_report_URL", coapp_crif_url);
                                                intent.putExtra("report", report);
                                                startActivity(intent);

                                               /* String report = "CRIF Report";
                                                Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                        Params.document, coapp_crif_url,Params.report, report,
                                                        Params.viable_url_view, coapp_crif_url_view);*/

                                            }

                                        });

                                        // viability_check_pass();
                                    }

                                }
                            } else{
                                Toast.makeText(mCon, " Error, Please Contact Loanwiser",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        // progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                //  progressDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        //AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

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
        // progressDialog.show();
        Log.e("Crif Generation_submit", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.submit_question, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment", String.valueOf(object));
                        try {

                            String Statues = object.getString("status");
                          /*  JSONObject data1 = object.getJSONObject("data");
                            Report_ID  = data1.getString("reportId");
                            Order_ID  = data1.getString("orderId");*/

                            if (Statues.equals("success")) {

                               /* JSONObject data = object.getJSONObject("data");
                                overall_status = data.getString("overall_status");
                                if (applicantcount.equals("1"))
                                {
                                    Viability_CRIF_report_Functions();
                                }else
                                {
                                    Co_applicant_Crif_Generation();
                                }*/
                                Automater_unserwrite_call();

                                // viability_CRIF_Score();
                            } else if (Statues.equals("technical_error"))
                            {
                                Toast.makeText(mCon, " Technical Error, Please Contact Loanwiser",Toast.LENGTH_SHORT).show();
                                // co_applicant_.setVisibility(View.VISIBLE);
                            }else
                            {
                                if (applicantcount.equals("1"))
                                {
                                    Viability_CRIF_report_Functions();
                                }else
                                {
                                    Co_applicant_Crif_Generation();
                                }
                                Toast.makeText(mCon, " Error, Please Contact Loanwiser",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        // progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                //  progressDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        //   AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void Co_applicant_Crif_Generation() {

        JSONObject J =new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            // J.put("relationship_type",Pref.getCoAPPAVAILABLE(getApplicationContext()));
            J.put("relationship_type","2");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Crif Generation", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CRIF_Generation, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment", String.valueOf(object));
                        try {

                            String Statues = null;
                            try {
                                Statues = object.getString("status");
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }

                            if (Statues.equals("success")) {
                                JSONObject data = object.getJSONObject("data");
                                overall_status = data.getString("overall_status");
                                Viability_CRIF_report_Functions();
                                Toast.makeText(Automater_Under_write.this,"Sucess",Toast.LENGTH_SHORT);

                            }else if (Statues.equals("question"))
                            {
                                // standard.setVisibility(View.GONE);
                                JSONObject data1 = object.getJSONObject("data");
                                Report_ID  = data1.getString("reportId");
                                Order_ID  = data1.getString("orderId");
                                String question = object.getString("question");
                                String name = object.getString("name");
                                final JSONArray qus_dropdown = object.getJSONArray("qus_dropdown");
                                //  applicant_question.setVisibility(View.VISIBLE);

                                //  cr_app1.setText(question);
                                //  credit_card_app.setVisibility(View.VISIBLE);
                                // Other_Earning(qus_dropdown);
                                progressDialog.dismiss();


                                final Dialog dialog = new Dialog(Automater_Under_write.this);
                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dialog.setContentView(R.layout.question_layout);
                                //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
                                dialog.setCancelable(true);
                                dialog.setCanceledOnTouchOutside(false);
                                final AppCompatButton submit_buton=(AppCompatButton) dialog.findViewById(R.id.submit_buton);
                                questions_spinner=(AppCompatSpinner)dialog.findViewById(R.id.question_spinner);
                                AppCompatTextView question_tag=(AppCompatTextView)dialog.findViewById(R.id.question_tag);
                                AppCompatTextView question_content=(AppCompatTextView)dialog.findViewById(R.id.question_content);
                                AppCompatTextView applicant_type=(AppCompatTextView)dialog.findViewById(R.id.applicant_type);
                                AppCompatTextView applicant =(AppCompatTextView) dialog.findViewById(R.id.applicant);
                                dialog.show();


                                // question_tag.setText("Dear" +" "+name+" "+question);
                                //Other_Earning(qus_dropdown);
                                question_tag.setText("Dear" +" "+name+" "+"Please answer this Question for verification ");
                                question_content.setText(question + "?");
                                applicant.setText("Co Applicant");
                                Other_Earning_SA = new String[qus_dropdown.length()];
                                for (int i=0;i<qus_dropdown.length();i++){
                                    JSONObject J =  qus_dropdown.getJSONObject(i);
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
                                    questions_spinner.setAdapter(Other_Earning_Adapter);
                                    questions_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                            try {

                                                credit_issued_id = qus_dropdown.getJSONObject(position).getString("id");
                                                credit_issued_value = qus_dropdown.getJSONObject(position).getString("value");
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
                                   /* applicant_credite_card_spinner.setOnTouchListener(new View.OnTouchListener() {
                                        @Override
                                        public boolean onTouch(View view, MotionEvent motionEvent) {
                                            // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                                            return false;
                                        }
                                    });*/
                                }

                                submit_buton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        LayoutInflater layoutInflater1 = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        View customView1 = layoutInflater1.inflate(R.layout.popup_loading,null);
                                        pop_up_loader = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                        //display the popup window
                                        pop_up_loader.showAtLocation(submit_buton, Gravity.CENTER, 0, 0);
                                        CRIF_Question_Testing();
                                    }
                                });

                                // co_applicant_.setVisibility(View.VISIBLE);
                            }else
                            {
                                Toast.makeText(mCon, "Please Provide Valid PAN Details!!!",Toast.LENGTH_SHORT).show();
                                overall_status = "3";
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
                Toast.makeText(mCon, "error, somthing went wrong!!!",Toast.LENGTH_SHORT).show();
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

        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        // AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }
    private void CRIF_Fails() {
        //    progressDialog.show();
        JSONObject J =new JSONObject();
        try {

            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("relationship_type",applicantcount);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Step2 Complete request",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.cibil_report1, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Step2 complete response",object.toString());
                        try {

                            String Staues_step2_complete = object.getString("status");
                            JSONObject jsonObject1 = object.getJSONObject("response");
                            if(Staues_step2_complete.contains("success")) {

                                JSONObject jsonObject = jsonObject1.getJSONObject("ovrl_loandet");

                                Log.e("the over_all",jsonObject.toString());
                                crif_fail = jsonObject.getString("crif_fail");

                                if (applicantcount.equals("1")) {
                                    LayoutInflater layoutInflater1 = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View customView1 = layoutInflater1.inflate(R.layout.activity_viablity_fail1_, null);

                                    AppCompatButton nextahed1 = (AppCompatButton) customView1.findViewById(R.id.nextahed1);
                                    AppCompatButton GO_Back_To_Dashboard = (AppCompatButton) customView1.findViewById(R.id.GO_Back_To_Dashboard);
                                    AppCompatTextView crif_fail_reason = (AppCompatTextView) customView1.findViewById(R.id.crif_fail_reason);
                                    LinearLayout background = (LinearLayout) customView1.findViewById(R.id.background);
                                    LinearLayout co_applicant = (LinearLayout) customView1.findViewById(R.id.co_applicant);
                                    LinearLayout co_Applicant_ly = (LinearLayout) customView1.findViewById(R.id.co_Applicant_ly);
                                    AppCompatTextView crifscore = (AppCompatTextView) customView1.findViewById(R.id.crifscore);
                                    AppCompatButton view_viablity_report = (AppCompatButton) customView1.findViewById(R.id.view_viablity_report);
                                    AppCompatButton view_CRIF_report = (AppCompatButton) customView1.findViewById(R.id.view_CRIF_report);

                                    TextView score = (TextView) customView1.findViewById(R.id.score);
                                    ImageView close = (ImageView) customView1.findViewById(R.id.close);
                                    background.setBackground(getResources().getDrawable(R.drawable.capsul_button_rect_viability1));
                                    co_applicant.setVisibility(View.GONE);
                                    co_Applicant_ly.setVisibility(View.GONE);
                                    crifscore.setText(app_crif_score);
                                    crif_fail_reason.setText("Reason: "+crif_fail);
                                    Log.e("crif_Score", app_crif_score);
                                    //instantiate popup window
                                    popupWindow1 = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                    //display the popup window
                                    popupWindow1.showAtLocation(GO_Back_To_Dashboard, Gravity.CENTER, 0, 0);


                                    close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            popupWindow1.dismiss();
                                        }
                                    });
                                    //close the popup window on button click
                                    nextahed1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            popupWindow1.dismiss();
                                        }
                                    });
                                    GO_Back_To_Dashboard.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            popupWindow1.dismiss();
                                        }
                                    });

                                    view_viablity_report.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                           /* if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                                if (viable_url.length() > 0) {
                                                    try {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viable_url)));
                                                    } catch (Exception e) {
                                                        e.getStackTrace();
                                                        Toast.makeText(mCon, "Please Try again",Toast.LENGTH_SHORT).show();

                                                    }
                                                }

                                            }*/

                                            String report="Viability Report";
                                            Intent intent = new Intent(Automater_Under_write.this, MainActivity.class);
                                            intent.putExtra("viability_report_URL", viable_url);
                                            intent.putExtra("report", report);
                                            startActivity(intent);

                                          /* String report = "Viability Report";
                                            Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                    Params.document, viable_url, Params.report, report);*/


                                        }

                                    });
                                    view_CRIF_report.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                           /* if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                                if (app_crif_url.length() > 0) {
                                                    try {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(app_crif_url)));
                                                    } catch (Exception e) {
                                                        e.getStackTrace();
                                                    }
                                                }

                                            }*/

                                            String report="CRIF Report";
                                            Intent intent = new Intent(Automater_Under_write.this, MainActivity.class);
                                            intent.putExtra("viability_report_URL", app_crif_url);
                                            intent.putExtra("report", report);
                                            startActivity(intent);
                                          /*  String report = "CRIF Report";
                                            Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                    Params.document, app_crif_url, Params.report, report);*/
                                           /* Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                    Params.document,app_crif_url);*/

                                        }

                                    });
                                    //   viability_check_pass();
                                }else
                                {
                                    LayoutInflater layoutInflater1 = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    View customView1 = layoutInflater1.inflate(R.layout.activity_viablity_fail1_, null);

                                    AppCompatButton nextahed1 = (AppCompatButton) customView1.findViewById(R.id.nextahed1);
                                    AppCompatTextView crif_fail_reason = (AppCompatTextView) customView1.findViewById(R.id.crif_fail_reason);
                                    AppCompatButton GO_Back_To_Dashboard = (AppCompatButton) customView1.findViewById(R.id.GO_Back_To_Dashboard);
                                    AppCompatButton view_viablity_report = (AppCompatButton) customView1.findViewById(R.id.view_viablity_report);
                                    AppCompatButton view_CRIF_report = (AppCompatButton) customView1.findViewById(R.id.view_CRIF_report);
                                    AppCompatButton view_co_App_CRIF_report = (AppCompatButton) customView1.findViewById(R.id.view_co_App_CRIF_report);
                                    LinearLayout co_applicant = (LinearLayout) customView1.findViewById(R.id.co_applicant);
                                    LinearLayout co_Applicant_ly = (LinearLayout) customView1.findViewById(R.id.co_Applicant_ly);
                                    AppCompatTextView crifscore = (AppCompatTextView) customView1.findViewById(R.id.crifscore);
                                    LinearLayout background = (LinearLayout) customView1.findViewById(R.id.background);
                                    AppCompatTextView co_appcrifscore = (AppCompatTextView) customView1.findViewById(R.id.co_appcrifscore);
                                    background.setBackground(getResources().getDrawable(R.drawable.capsul_button_rect_viability1));

                                    ImageView close = (ImageView) findViewById(R.id.close);

                                    //  TextView score= (TextView) customView1.findViewById(R.id.score);
                                    co_applicant.setVisibility(View.VISIBLE);
                                    co_Applicant_ly.setVisibility(View.VISIBLE);
                                    crifscore.setText(app_crif_score);
                                    co_appcrifscore.setText(coapp_crif_score);
                                    Log.e("crif_Score", app_crif_score);
                                    crif_fail_reason.setText("Reason: "+crif_fail);
                                    //instantiate popup window
                                    popupWindow1 = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                    //display the popup window
                                    popupWindow1.showAtLocation(nextahed1, Gravity.CENTER, 0, 0);

                                    close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            popupWindow1.dismiss();
                                        }
                                    });

                                    //close the popup window on button click
                                    nextahed1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                               /* Intent intent = new Intent(Payment_Sucess_Screen.this, Upload_Activity_Bank.class);
                                                startActivity(intent);
                                                finish();*/
                                        }
                                    });
                                    GO_Back_To_Dashboard.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Intent intent = new Intent(Automater_Under_write.this, Dashboard_Activity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

                                    view_viablity_report.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                                 /*   if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                                        if (viable_url.length() > 0) {
                                                            try {
                                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viable_url)));
                                                            } catch (Exception e) {
                                                                e.getStackTrace();
                                                            }
                                                        }

                                                    }*/

                                            String report="Viability Report";
                                            Intent intent = new Intent(Automater_Under_write.this, MainActivity.class);
                                            intent.putExtra("viability_report_URL", viable_url);
                                            intent.putExtra("report", report);
                                            startActivity(intent);
                                          /*  Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                    Params.document, viable_url);*/

                                        }

                                    });
                                    view_CRIF_report.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                           /* if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                                if (app_crif_url.length() > 0) {
                                                    try {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(app_crif_url)));
                                                    } catch (Exception e) {
                                                        e.getStackTrace();
                                                    }
                                                }

                                            }*/

                                            String report="CRIF Report";
                                            Intent intent = new Intent(Automater_Under_write.this, MainActivity.class);
                                            intent.putExtra("viability_report_URL", app_crif_url);
                                            intent.putExtra("report", report);
                                            startActivity(intent);
                                           /* Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                    Params.document, app_crif_url);*/

                                        }

                                    });

                                    view_co_App_CRIF_report.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                           /* if (permissionUtils.checkPermission(Payment_Sucess_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                                                if (coapp_crif_url.length() > 0) {
                                                    try {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(coapp_crif_url)));
                                                    } catch (Exception e) {
                                                        e.getStackTrace();
                                                    }
                                                }

                                            }*/

                                            String report="CRIF Report";
                                            Intent intent = new Intent(Automater_Under_write.this, MainActivity.class);
                                            intent.putExtra("viability_report_URL", coapp_crif_url);
                                            intent.putExtra("report", report);
                                            startActivity(intent);

                                          /*  Objs.ac.StartActivityPutExtra(Payment_Sucess_Screen.this, Doc_ImageView_Bank.class,
                                                    Params.document, coapp_crif_url);*/

                                        }

                                    });

                                    // viability_check_pass();
                                }
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

        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }
    private void Automater_unserwrite_call() {



        JSONObject J =new JSONObject();

        try {

            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Step2 Complete request",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.creditrep_anaysisres, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Step2 complete response",object.toString());
                        try {

                            String all_status = object.getString("all_status");

                            if(all_status.equals("success")) {

                                Toast.makeText(mCon, "CRIF Successfully Generated",Toast.LENGTH_SHORT).show();
                                step_1detail.setText("CRIF Completed");
                                step1_image_completed.setVisibility(View.VISIBLE);
                                step1_image_completed.setImageDrawable(getResources().getDrawable(R.drawable.ic_check));

                                Intent intent = new Intent(Automater_Under_write.this, BankAnalysis.class);
                                //  Intent in=new Intent(context,BankAnalysis.class);
                                intent.putExtra("adapter","upload");
                                startActivity(intent);
                            } else
                            {
                                JSONArray status_arr = object.getJSONArray("status_arr");

                                for (int i=0;i<status_arr.length();i++) {
                                    JSONObject J = null;
                                    J = status_arr.getJSONObject(i);
                                   String status = J.getString("status");
                                   if(status.equals("not_initiated"))
                                   {
                                       Crif_Generation();
                                   }else if(status.equals("question")) {

                                        LayoutInflater layoutInflater1 = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        View customView1 = layoutInflater1.inflate(R.layout.popup_loading, null);
                                        pop_up_loader = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                        //display the popup window
                                        pop_up_loader.showAtLocation(CRIF_report, Gravity.CENTER, 0, 0);
                                        // standard.setVisibility(View.GONE);
                                        JSONObject data1 = object.getJSONObject("data");
                                        Report_ID = data1.getString("reportId");
                                        Order_ID = data1.getString("orderId");
                                        String question = object.getString("question");
                                        String name = object.getString("name");
                                        final JSONArray qus_dropdown = object.getJSONArray("qus_dropdown");
                                        //  applicant_question.setVisibility(View.VISIBLE);

                                        //  cr_app1.setText(question);
                                        //  credit_card_app.setVisibility(View.VISIBLE);
                                        // Other_Earning(qus_dropdown);
                                        //   progressDialog.dismiss();
                                        final Dialog dialog = new Dialog(Automater_Under_write.this);
                                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                        dialog.setContentView(R.layout.question_layout);
                                        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
                                        dialog.setCancelable(true);
                                        dialog.setCanceledOnTouchOutside(false);
                                        final AppCompatButton submit_buton = (AppCompatButton) dialog.findViewById(R.id.submit_buton);
                                        AppCompatTextView applicant = (AppCompatTextView) dialog.findViewById(R.id.applicant);
                                        questions_spinner = (AppCompatSpinner) dialog.findViewById(R.id.question_spinner);
                                        AppCompatTextView question_tag = (AppCompatTextView) dialog.findViewById(R.id.question_tag);
                                        AppCompatTextView question_content = (AppCompatTextView) dialog.findViewById(R.id.question_content);
                                        AppCompatTextView applicant_type = (AppCompatTextView) dialog.findViewById(R.id.applicant_type);
                                        dialog.show();

                                        question_tag.setText("Dear" + " " + name + " " + "Please answer this Question for verification ");
                                        question_content.setText(question + "?");
                                        //Other_Earning(qus_dropdown);
                                        applicant.setText("Applicant");
                                        Other_Earning_SA = new String[qus_dropdown.length()];
                                        for (int i1 = 0; i1 < qus_dropdown.length(); i1++) {
                                            JSONObject J1 = qus_dropdown.getJSONObject(i1);
                                            Other_Earning_SA[i1] = J1.getString("value");
                                            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Other_Earning_SA));
                                            Other_Earning_Adapter = new ArrayAdapter<String>(mCon, R.layout.view_spinner_item, loan_type_list) {
                                                public View getView(int position, View convertView, ViewGroup parent) {
                                                    font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
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
                                            questions_spinner.setAdapter(Other_Earning_Adapter);
                                            questions_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                    try {

                                                        credit_issued_id = qus_dropdown.getJSONObject(position).getString("id");
                                                        credit_issued_value = qus_dropdown.getJSONObject(position).getString("value");
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
                                   /* applicant_credite_card_spinner.setOnTouchListener(new View.OnTouchListener() {
                                        @Override
                                        public boolean onTouch(View view, MotionEvent motionEvent) {
                                            // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                                            return false;
                                        }
                                    });*/
                                        }
                                       submit_buton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

                                               if (applicantcount.equals("1")) {
                                                   dialog.dismiss();
                                                   LayoutInflater layoutInflater1 = (LayoutInflater) Automater_Under_write.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                                   View customView1 = layoutInflater1.inflate(R.layout.popup_loading, null);
                                                   popupWindow1 = new PopupWindow(customView1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                                   //display the popup window
                                                   popupWindow1.showAtLocation(submit_buton, Gravity.CENTER, 0, 0);
                                                   CRIF_Question_Testing();
                                               } else {
                                                   dialog.dismiss();
                                                   CRIF_Question_Testing();
                                               }


                                           }
                                       });

                                    }else
                                   {
                                       Intent intent = new Intent(Automater_Under_write.this, BankAnalysis.class);
                                       //  Intent in=new Intent(context,BankAnalysis.class);
                                       intent.putExtra("adapter","upload");
                                       startActivity(intent);
                                   }
                                }
                               // Toast.makeText(mCon, "CRIF Statues Failed",Toast.LENGTH_SHORT).show();
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

}