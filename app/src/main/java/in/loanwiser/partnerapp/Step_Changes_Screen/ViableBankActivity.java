package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableRow;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.BankStamentUpload.Upload_Activity_Bank;
import in.loanwiser.partnerapp.PDF_Viewer.MainActivity;
import in.loanwiser.partnerapp.PartnerActivitys.BankimgData;
import in.loanwiser.partnerapp.PartnerActivitys.BankimgviewAdapter;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.PartnerActivitys.ViablityfailAdapter;
import in.loanwiser.partnerapp.PartnerActivitys.Viablityfaildata;
import in.loanwiser.partnerapp.PartnerActivitys.ViablityfailureActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class ViableBankActivity extends SimpleActivity {

    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    RecyclerView bankresycleview,bank_failresycleview;
    private ArrayList<BankimgData> recyclerDataArrayList;
    private ArrayList<Rulefail> ruledataarraylist;
    BankimgviewAdapter adapter;
    BankimgviewAdapter1 bankimgviewAdapter1;

    RulefailAdapter adapter1;
    RulefailAdapter1 rulefailAdapter1;
    PopupWindow popUp;
    boolean click = true;
    LinearLayout layout,viableanalyis;


    String bankid1;
    JSONObject jsonobjects,jsonobjectsindex;
    String bankid,status;
    String bankids1,status1;
    JSONObject response_doc, response_doc1,doc_ar,docarr_object;
    JSONArray response_iD_proof,doc_ype,response_iD_proof_comon,doc_ype_com;
    JSONArray key_value;
    ArrayList<String> list_key;
    LinearLayout check_list_name,dicreqcard,recyclelays,headlays;
    int checklistname_count= 0;
    AppCompatButton submitbtn,nextbtn;
    String doc_typename,Current_iD_Proof,PERMANT_iD_Proof,INCOME,WRK,PHOTO,checklist_name1,checked_statues;
    PopupWindow popupWindow;
    LinearLayout recyclelay;
    String viable_url;
    String firstvalue;
    boolean check=true;
    int number=1;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viable_bank);
        initTools(R.string.viablebank);
        bankresycleview=findViewById(R.id.bankresycleview);
        bank_failresycleview=findViewById(R.id.bank_failresycleview);
        check_list_name=findViewById(R.id.check_list_name);
        dicreqcard=findViewById(R.id.dicreqcard);
       // recyclelays=findViewById(R.id.recyclelays);
        headlays=findViewById(R.id.headlays);
        submitbtn=findViewById(R.id.submitbtn);
        nextbtn=findViewById(R.id.nextbtn);
        recyclelay=findViewById(R.id.recyclelay);
        viableanalyis=findViewById(R.id.viableanalyis);
        popUp = new PopupWindow(this);
         layout = new LinearLayout(this);



        progressDialog = new SpotsDialog(ViableBankActivity.this, R.style.Custom);
        recyclerDataArrayList=new ArrayList<>();
        ruledataarraylist=new ArrayList<>();


      //  adapter=new BankimgviewAdapter(recyclerDataArrayList,ViableBankActivity.this);
        bankimgviewAdapter1=new BankimgviewAdapter1(recyclerDataArrayList,ViableBankActivity.this);
       // adapter1=new RulefailAdapter(ruledataarraylist,ViableBankActivity.this);
        rulefailAdapter1=new RulefailAdapter1(ruledataarraylist,ViableBankActivity.this);

        bank_failresycleview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bankresycleview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

       // GridLayoutManager layoutManager=new GridLayoutManager(ViableBankActivity.this,2);
        // at last set adapter to recycler view.
       // bankresycleview.setLayoutManager(layoutManager);



        ViableBanks();





        Intent in=getIntent();
        bankid=in.getStringExtra("bankid");
        status=in.getStringExtra("statusvalue");
        Log.i("TAG", "onCreatebankid"+bankid);
        if (bankid!=null){
          //  check_list_name.removeAllViews();
            Docklistrules(bankid,status);
        }
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Status_Update();
                /*Intent intent = new Intent(ViableBankActivity.this, ViablityReportActivity.class);
                startActivity(intent);*/
            }
        });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViableBankActivity.this, Home.class);
                startActivity(intent);
            }
        });


    }

    private void ViableBanks(){
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));

            Log.i("TAG", "Viablebanks "+J.toString());
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.VIABLEBANKS, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        Log.e("response", object.toString());
                        Log.i("TAG", "onResponse:calledornot "+"called");
                        viableanalyis.setVisibility(View.VISIBLE);
                      //  adapter.notifyDataSetChanged();
                        try {
                            String statusvalue=object.getString("status");
                            if (statusvalue.equalsIgnoreCase("eligibility")){
                                JSONArray jsonArray = object.getJSONArray("bank_arr");
                                if (jsonArray.length()>0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                                         jsonobjectsindex = jsonArray.getJSONObject(0);
                                        String jsonObject1=jsonobject.getString("status");
                                        Log.i("TAG", "onResponse: "+jsonobject);
                                       JSONObject jsonObject=jsonobject.getJSONObject("bank_arr");
                                        JSONObject jsonObject2=jsonobjectsindex.getJSONObject("bank_arr");
                                     //   firstvalue = jsonobjectsindex.getString("id");

                                        //JSONObject firstjsonobject=jsonObjects.getJSONObject("bank_arr");
                                        bankid1=jsonObject2.getString("id");
                                        Log.i("TAG", "jsonObjectlength: "+jsonObject.length());
                                        Log.i("TAG", "jsonObjectlength: "+jsonObject2.length());
                                        recyclerDataArrayList.add(new BankimgData(jsonObject.getString("id"), jsonObject.getString("bank_logo_cc"),jsonObject.getString("bank_logo"),jsonObject1));
                                       // adapter.notifyDataSetChanged();
                                        bankimgviewAdapter1.notifyDataSetChanged();

                                    }
                                    bankresycleview.setAdapter(bankimgviewAdapter1);
                                   // bankresycleview.setAdapter(adapter);




                                }
                            }else{
                                recyclelay.setVisibility(View.GONE);

                                Intent intent=new Intent(ViableBankActivity.this, ViablityfailureActivity.class);
                                startActivity(intent);
                                finish();
                               /* LayoutInflater layoutInflater = (LayoutInflater) ViableBankActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                View customView = layoutInflater.inflate(R.layout.popup_rul_fail,null);
                                Button godashboard = (Button) customView.findViewById(R.id.godashboard);
                                TextView content_txt = (TextView) customView.findViewById(R.id.content_txt);


                                //  String list = Arrays.toString(rule_message.toArray()).replace("[", "").replace("]", "");
                                content_txt.setText("Bank Requirement Not Met");

                                //instantiate popup window
                                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                popupWindow.showAtLocation(godashboard, Gravity.CENTER, 0, 0);


                                Toast.makeText(ViableBankActivity.this,"Error",Toast.LENGTH_SHORT).show();*/
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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



    private void Docklistrules(String bankids1,String status1){
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            J.put("bank_id", bankids1);
            J.put("status", status1);
            Log.i("TAG", "Doclistreules "+J.toString());
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.DOCLISTRULES, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        check=false;
                       // Log.e("response", object.toString());
                     //   check_list_name.removeAllViews();
                        try {
                            Log.i("TAG", "onResponse: num" + number);
                            String bank_status = object.getString("bank_status");
                            if (bank_status.equalsIgnoreCase("1"))
                            {
                                bank_failresycleview.setVisibility(View.GONE);
                                docarr_object = object.getJSONObject("doc_arr");
                            response_doc = docarr_object.getJSONObject("response");
                           // dicreqcard.setVisibility(View.VISIBLE);
                            Log.i("TAG", "onResponse:key " + response_doc);
                            Log.e("the doc array", response_doc.toString());
                            //below few lines array key name
                            Iterator iterator = response_doc.keys();
                            while (iterator.hasNext()) {

                                String key = (String) iterator.next();
                                Log.e("value", key.toString());
                                list_key = new ArrayList<String>();


                                Log.e("the value", list_key.toString());
                                response_iD_proof_comon = response_doc.getJSONArray(key);
                                Log.i("TAG", "keyarraylength: " + response_iD_proof_comon);
                                for (int i = 0; i < response_iD_proof_comon.length(); i++) {
                                    JSONObject J = null;
                                    try {

                                        J = response_iD_proof_comon.getJSONObject(i);
                                        doc_ype_com = J.getJSONArray("document_arr");
                                        String document_req = J.getString("document_req");
                                        checklist_name(doc_ype_com, document_req, key);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }

                            }
                        }else{
                                bank_failresycleview.setVisibility(View.VISIBLE);
                                dicreqcard.setVisibility(View.GONE);
                              //  headlays.setVisibility(View.VISIBLE);
                              //  recyclelays.setVisibility(View.VISIBLE);
                                JSONArray jsonArray = object.getJSONArray("rule_arr");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                                    ruledataarraylist.add(new Rulefail(jsonobject.getString("rule_desc"), jsonobject.getString("rule_status"),jsonobject.getString("fail_message")));
                                   // adapter1.notifyDataSetChanged();
                                    rulefailAdapter1.notifyDataSetChanged();

                                }
                               // bank_failresycleview.setAdapter(adapter1);
                                bank_failresycleview.setAdapter(rulefailAdapter1);
                            }



                    } catch (JSONException e) {
                            e.printStackTrace();
                        }

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


    private void checklist_name(final JSONArray doc_ype_com,String document_req, String key) {


        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (!list_key.contains(key))
        {

            TextView tv =new TextView(this);
            TextView tv1 = new TextView(this);
            tv.setLayoutParams(lparams);
            tv1.setLayoutParams(lparams);
            list_key.add(key);
            if(document_req.equalsIgnoreCase("1")){
                tv.setText(key+ "**");
                Typeface font = Typeface.createFromAsset(this.getAssets(), "segoe_ui.ttf");
                tv.setTypeface(font,Typeface.BOLD);
                tv.setTextSize(18);
                tv1.setText("Please Upload any one of the following");
                tv1.setTypeface(font,Typeface.NORMAL);

                tv1.setTextSize(18);
                tv.setTextColor(Color.parseColor("#1592E6"));
                tv1.setTextColor(Color.parseColor("#ffcc95"));
                check_list_name.addView(tv);
                check_list_name.addView(tv1);
                popUp.setContentView(check_list_name);
            }else{
                tv.setText(key);
                tv.setTextSize(18);
                tv.setTextColor(Color.parseColor("#1592E6"));
                check_list_name.addView(tv);
                popUp.setContentView(check_list_name);

            }



        }else
        {

        }
        Log.e("the list_key value",list_key.toString());

        checklistname_count = doc_ype_com.length();
        for (int i = 0; i < checklistname_count; i++) {

            JSONObject rec5 = null;
            try {

                rec5 = doc_ype_com.getJSONObject(i);
                //  int id = rec.getInt("id");
                checklist_name1 = rec5.getString("doc_typename");
              //  checked_statues = rec5.getString("enable_status");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            LinearLayout.LayoutParams lparams1 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView tv1 = new TextView(this);
            tv1.setLayoutParams(lparams1);
            tv1.setText(checklist_name1);
            tv1.setText(checklist_name1);
            tv1.setTextSize(18);
            tv1.setTextColor(Color.parseColor("#B4B4B4"));
            check_list_name.addView(tv1);
            popUp.setContentView(check_list_name);



          /*  TableRow row =new TableRow(this);
            row.setId(i);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            final CheckBox checkBox = new CheckBox(this);
            TextView textView = new TextView(this);
           // checkBox.setOnCheckedChangeListener(this);
            checkBox.setId(i);
            checkBox.setText(checklist_name1);
            //  row.addView(checkBox);
            check_list_name.addView(checkBox);*/



                }


        }


    private void Viablityreport(){
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("transaction_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));
            Log.i("TAG", "Docverifyrule "+J.toString());
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.report_links, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        Log.e("respose docverifyrule", object.toString());
                        try {
                            viable_url=object.getString("viable_url");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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


    private void Status_Update(){
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("comp_status", "2");
            J.put("subcomp_status", "2");
            Log.i("TAG", "Statusupdate "+J.toString());
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.status_update, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        progressDialog.dismiss();
                        Eligibility_check_doc_checklist_generate();


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

    private void Eligibility_check_doc_checklist_generate() {

        JSONObject J = null;

        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id", Pref.getUSERID(getApplicationContext()));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("viability", String.valueOf(J));
        // progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.generate_doccklist, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("viability response", String.valueOf(response));
                        Intent intent=new Intent(ViableBankActivity.this, ViablityReportActivity.class);
                        startActivity(intent);
                        finish();


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d(TAG, error.getMessage());
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(mCon, "Network error, try after some time", Toast.LENGTH_SHORT).show();
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
    }

    public class BankimgviewAdapter1 extends RecyclerView.Adapter<BankimgviewAdapter1.RecyclerViewHolder>{
        private ArrayList<BankimgData> productDataArrayList;
        private Context mcontext;

        public BankimgviewAdapter1(ArrayList<BankimgData> recyclerDataArrayList, Context mcontext) {
            this.productDataArrayList = recyclerDataArrayList;
            this.mcontext = mcontext;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bankimgshow_layout, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            final String head_line  = productDataArrayList.get(position).getId();
            final String icon  = productDataArrayList.get(position).getBank_logo_cc();
            final String banklogo=productDataArrayList.get(position).getBank_logo();
            final String status=productDataArrayList.get(position).getStatus();
            Log.i("TAG", "onBindViewHolder:status "+status);


            Log.i("TAG", "onBindViewHolder: "+banklogo);
            Glide.with(mcontext).load("https://consumer.loanwiser.in/images/bank-logo/"+banklogo)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.productimg);
            if (status.equalsIgnoreCase("1")){
                holder.producttext.setText("Passed in Viability");
                holder.producttext.setTextColor(Color.parseColor("#25D366"));

            }else{
                holder.producttext.setText("Failed in Viability");
                holder.producttext.setTextColor(Color.parseColor("#D34D53"));

            }
            holder.arrowimgup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.checklistname.removeAllViews();
                    holder.arrowimgup.setVisibility(View.GONE);
                    holder.arrowimgup1.setVisibility(View.VISIBLE);
                    holder.checklistname.setVisibility(View.GONE);
                    holder.checklistname1.setVisibility(View.GONE);

                }
            });

            holder.arrowimgup1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    bankids1=productDataArrayList.get(position).getId();
                    status1=productDataArrayList.get(position).getStatus();
                    holder.checklistname.removeAllViews();
                    holder.checklistname.setVisibility(View.VISIBLE);
                    holder.arrowimgup.setVisibility(View.VISIBLE);
                    holder.arrowimgup1.setVisibility(View.GONE);
                   // holder.upload.setVisibility(View.GONE);
                        JSONObject J= null;
                        try {
                            J = new JSONObject();
                            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
                            J.put("user_id", Pref.getUSERID(getApplicationContext()));
                            J.put("bank_id", bankids1);
                            J.put("status", status1);
                            Log.i("TAG", "Doclistreules "+J.toString());
                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        progressDialog.show();
                        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.DOCLISTRULES, J,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject object) {
                                        progressDialog.dismiss();
                                        check=false;
                                        // Log.e("response", object.toString());
                                        //   check_list_name.removeAllViews();
                                        try {
                                           // Log.i("TAG", "onResponse: num" + number);
                                            String bank_status = object.getString("bank_status");
                                            if (bank_status.equalsIgnoreCase("1"))
                                            {
                                                holder.bank_failresycleview.setVisibility(View.GONE);
                                                docarr_object = object.getJSONObject("doc_arr");
                                                response_doc = docarr_object.getJSONObject("response");
                                                // dicreqcard.setVisibility(View.VISIBLE);
                                                Log.i("TAG", "onResponse:key " + response_doc);
                                                Log.e("the doc array", response_doc.toString());
                                                //below few lines array key name
                                                Iterator iterator = response_doc.keys();
                                                while (iterator.hasNext()) {

                                                    String key = (String) iterator.next();
                                                    Log.e("value", key.toString());
                                                    list_key = new ArrayList<String>();


                                                    Log.e("the value", list_key.toString());
                                                    response_iD_proof_comon = response_doc.getJSONArray(key);
                                                    Log.i("TAG", "keyarraylength: " + response_iD_proof_comon);
                                                    for (int i = 0; i < response_iD_proof_comon.length(); i++) {
                                                        JSONObject J = null;
                                                        try {

                                                            J = response_iD_proof_comon.getJSONObject(i);
                                                            doc_ype_com = J.getJSONArray("document_arr");
                                                            String document_req = J.getString("document_req");


                                                            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                                                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                                            if (!list_key.contains(key))
                                                            {

                                                                TextView tv =new TextView(ViableBankActivity.this);
                                                                TextView tv1 = new TextView(ViableBankActivity.this);
                                                                tv.setLayoutParams(lparams);
                                                                tv1.setLayoutParams(lparams);
                                                                list_key.add(key);
                                                                if(document_req.equalsIgnoreCase("1")){
                                                                    tv.setText(key+ "**");
                                                                    lparams.setMargins(10, 12, 10, 12);
                                                                    Typeface font = Typeface.createFromAsset(ViableBankActivity.this.getAssets(), "segoe_ui.ttf");
                                                                    tv.setTypeface(font,Typeface.BOLD);
                                                                    tv.setTextSize(18);
                                                                    tv1.setText("Please Upload any one of the following");
                                                                    tv1.setTypeface(font,Typeface.NORMAL);

                                                                    tv1.setTextSize(14);
                                                                    tv.setTextColor(Color.parseColor("#1592E6"));
                                                                    tv1.setTextColor(Color.parseColor("#ffcc95"));
                                                                    holder.checklistname.addView(tv);
                                                                    holder.checklistname.addView(tv1);
                                                                    //popUp.setContentView(check_list_name);
                                                                }else{
                                                                    tv.setText(key);
                                                                    tv.setTextSize(14);
                                                                    lparams.setMargins(10, 12, 10, 12);

                                                                    tv.setTextColor(Color.parseColor("#1592E6"));
                                                                    holder.checklistname.addView(tv);
                                                                   // popUp.setContentView(check_list_name);

                                                                }



                                                            }else
                                                            {

                                                            }

                                                            Log.e("the list_key value",list_key.toString());

                                                            checklistname_count = doc_ype_com.length();
                                                            for (int k = 0; k < checklistname_count; k++) {

                                                                JSONObject rec5 = null;
                                                                try {

                                                                    rec5 = doc_ype_com.getJSONObject(k);
                                                                    //  int id = rec.getInt("id");
                                                                    checklist_name1 = rec5.getString("doc_typename");
                                                                    //  checked_statues = rec5.getString("enable_status");
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }

                                                                LinearLayout.LayoutParams lparams1 = new LinearLayout.LayoutParams(
                                                                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                                                TextView tv1 = new TextView(ViableBankActivity.this);
                                                                lparams1.setMargins(10, 10, 10, 10);

                                                                Typeface font = Typeface.createFromAsset(ViableBankActivity.this.getAssets(), "segoe_ui.ttf");
                                                                tv1.setTypeface(font,Typeface.BOLD);
                                                                tv1.setLayoutParams(lparams1);
                                                                tv1.setText(checklist_name1);
                                                                tv1.setText(checklist_name1);
                                                                tv1.setTextSize(14);
                                                                tv1.setTextColor(Color.parseColor("#B4B4B4"));
                                                                holder.checklistname.addView(tv1);

                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }


                                                    }

                                                }
                                            }else{
                                                ruledataarraylist=new ArrayList<>();
                                                Log.i("TAG", "conditonmetornot:"+"check");

                                                holder.checklistname1.setVisibility(View.VISIBLE);
                                                holder.bank_failresycleview.setVisibility(View.VISIBLE);

                                                dicreqcard.setVisibility(View.GONE);
                                                JSONArray jsonArray = object.getJSONArray("rule_arr");
                                                Log.e("TAG", "jsonarraylength: "+jsonArray.length());
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject jsonobject = jsonArray.getJSONObject(i);
                                                    ruledataarraylist.add(new Rulefail(jsonobject.getString("rule_desc"), jsonobject.getString("rule_status"),jsonobject.getString("fail_message")));
                                                    // adapter1.notifyDataSetChanged();
                                                    rulefailAdapter1.notifyDataSetChanged();

                                                }
                                                rulefailAdapter1=new RulefailAdapter1(ruledataarraylist,ViableBankActivity.this);

                                                holder.bank_failresycleview.setLayoutManager(new LinearLayoutManager(ViableBankActivity.this, LinearLayoutManager.VERTICAL, false));

                                                // bank_failresycleview.setAdapter(adapter1);
                                               holder.bank_failresycleview.setAdapter(rulefailAdapter1);
                                            }



                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

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




            });
        }



        @Override
        public int getItemCount() {
            return productDataArrayList.size();
        }


        public class RecyclerViewHolder extends RecyclerView.ViewHolder {

            private AppCompatTextView producttext;
            private AppCompatImageView viewdoc,arrowimgup,arrowimgup1;
            private ImageView productimg;
            RecyclerView bank_failresycleview;
            LinearLayout checklistname,checklistname1;
            public RecyclerViewHolder(@NonNull View itemView) {
                super(itemView);
                productimg = itemView.findViewById(R.id.productimg);
                producttext = itemView.findViewById(R.id.producttext);
                viewdoc = itemView.findViewById(R.id.viewdoc);
                arrowimgup = itemView.findViewById(R.id.arrowimgup);
                arrowimgup1 = itemView.findViewById(R.id.arrowimgdown1);
                checklistname = itemView.findViewById(R.id.checklistname);
                checklistname1 = itemView.findViewById(R.id.checklistname1);
                bank_failresycleview=itemView.findViewById(R.id.bank_failresycleview);
            }
        }
    }

    public class RulefailAdapter1 extends RecyclerView.Adapter<RulefailAdapter1.RecyclerViewHolder> {

        private ArrayList<Rulefail> rulefaildata;
        private Context mcontext;

        public RulefailAdapter1(ArrayList<Rulefail> rulefaildata, Context mcontext) {
            this.rulefaildata = rulefaildata;
            this.mcontext = mcontext;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viablilityfail_adapter, parent, false);
            return new RecyclerViewHolder(view);
        }



        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            RecyclerViewHolder rowViewHolder = (RecyclerViewHolder) holder;

            int rowPos = rowViewHolder.getAdapterPosition();

            if (rowPos == 0) {
                // Header Cells. Main Headings appear here
                rowViewHolder.first.setBackgroundResource(R.drawable.table_header_cell_bg);
                rowViewHolder.second.setBackgroundResource(R.drawable.table_header_cell_bg);
                rowViewHolder.third.setBackgroundResource(R.drawable.table_header_cell_bg);
                // rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_header_cell_bg);

                rowViewHolder.first.setText("Criteria");
                rowViewHolder.second.setText("Status");
                rowViewHolder.third.setText("Description");
                rowViewHolder.first.setTextColor(Color.parseColor("#FFFFFF"));
                rowViewHolder.second.setTextColor(Color.parseColor("#FFFFFF"));
                rowViewHolder.third.setTextColor(Color.parseColor("#FFFFFF"));
                //  rowViewHolder.txtCost.setText("Budget (in Millions)");
            } else {
                Rulefail modal = rulefaildata.get(rowPos-1);

                // Content Cells. Content appear here
                rowViewHolder.first.setBackgroundResource(R.drawable.table_content_cell_bg);
                rowViewHolder.second.setBackgroundResource(R.drawable.table_content_cell_bg);
                rowViewHolder.third.setBackgroundResource(R.drawable.table_content_cell_bg);
                //   rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_content_cell_bg);

            /*final String head_line  = rulefaildata.get(position).getRule_desc();
            final String icon  = rulefaildata.get(position).getRule_status();
            final String banklogo=rulefaildata.get(position).getFail_message();*/

                rowViewHolder.first.setText(modal.getRule_desc()+"");
                //  rowViewHolder.second.setText(modal.getMovieName());
                rowViewHolder.third.setText(modal.fail_message+"");
                if (modal.getRule_status().equalsIgnoreCase("1")){
                    holder.second.setText("Passed");
                    holder.second.setTextColor(Color.parseColor("#25D366"));

                }else{
                    holder.second.setText("Failed");
                    holder.second.setTextColor(Color.parseColor("#D34D53"));


                }
                //  rowViewHolder.txtCost.setText(modal.getBudgetInMillions()+"");
            }

/*
        final String head_line  = rulefaildata.get(position).getRule_desc();
        final String icon  = rulefaildata.get(position).getRule_status();
        final String banklogo=rulefaildata.get(position).getFail_message();
        holder.first.setText(head_line);
        holder.third.setText(banklogo);
        if (icon.equalsIgnoreCase("1")){
            holder.second.setText("Viablity Passed");
            holder.second.setTextColor(Color.parseColor("#25D366"));

        }else{
            holder.second.setText("Viablity Failed");
            holder.second.setTextColor(Color.parseColor("#D34D53"));


        }*/

        }

        @Override
        public int getItemCount() {
            return rulefaildata.size()+1;
        }

        public class RecyclerViewHolder extends RecyclerView.ViewHolder {

            private AppCompatTextView first,second,third;
            private ImageView productimg;
            private MaterialCardView cardlay;
            public RecyclerViewHolder(@NonNull View itemView) {
                super(itemView);

                second = itemView.findViewById(R.id.second);
                first = itemView.findViewById(R.id.first);
                third=itemView.findViewById(R.id.third);
            }
        }
    }


    }



