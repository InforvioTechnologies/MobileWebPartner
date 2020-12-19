package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;

public class DocumentChecklist_Fragment extends SimpleActivity implements TabLayout.OnTabSelectedListener {

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    String get_jsonArray,userstatus,transaction_id,user_type,emp_states,applicant_count,property_identify;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private AlertDialog progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_document_checklist__fragment);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_document_checklist__fragment);
        // applicant_name =  Objs.a.getBundle(this, Params.applicant_name);
        String document= "Document Checklist";
        //   Log.e("doc",document);
        initTools1(document);
        //  initTools1(applicant_name);

     /*   Intent intent=getIntent();
        get_jsonArray = intent.getStringExtra("jsonArray");
        applicant_count=intent.getStringExtra("applicantcount");
        property_identify=intent.getStringExtra("propertyidentify");
        Log.i("TAG", "Applicant_count_checkcondition: "+applicant_count);
        Log.i("TAG", "property_identify_checkcondition: "+property_identify);
        Log.i("TAG", "onCreate:json "+get_jsonArray);*/



        progressDialog = new SpotsDialog(this, R.style.Custom);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        Applicant_Status();
        //Adding the tabs using addTab() method




       /* tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
        tabLayout.addTab(tabLayout.newTab().setText("CO Applicant"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);*/


      /*  //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);*/

        //Adding onTabSelectedListener to swipe views

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
    public void Applicant_Status() {

        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("user_id",Pref.getUSERID(getApplicationContext()));

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

                            if(statues.equals("success"))
                            {
                                JSONObject jsonObject2 = response.getJSONObject("reponse");

                                JSONArray jsonArray = jsonObject2.getJSONArray("emp_states");

                                String user_id = jsonObject2.getString("user_id");
                                String Loan_amount = jsonObject2.getString("loan_amount");
                                transaction_id =  jsonObject2.getString("transaction_id");
                                applicant_count =  jsonObject2.getString("applicant_count");
                                property_identify =  jsonObject2.getString("property_identify");

                                //  applicant_id =  "APP-"+user_id;

                              String  applicant_empstates=response.getString("app_emp");
                                String coapplicant_empstates=response.getString("coapp_emp");
                                String property_empstates=response.getString("prop_emp");
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(DocumentChecklist_Fragment.this);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("app_emp", applicant_empstates);
                                editor.putString("coapp_emp", coapplicant_empstates);
                                editor.putString("prop_emp",property_empstates);
                                editor.apply();

                                editor.putString("get_jsonArray",jsonArray.toString());
                                editor.putString("applicant_count",applicant_count);
                                editor.putString("property_identify",property_identify);
                                editor.apply();
                                // String statues2 = "3";
                                //  Pref.putUSERID(mCon,user_id);
                                String _Emp_staus_jsonArray = jsonArray.toString();
                                Pref.putTRANSACTIONID(mCon,transaction_id);
                                Pref.putUSERID(mCon,user_id);

                                if (applicant_count.equals("1")&& property_identify.equals("1")){
                                    tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
                                    tabLayout.addTab(tabLayout.newTab().setText("Property"));
                                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                                }else if (applicant_count.equals("1")&& property_identify.equals("0")){
                                    tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
                                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                                }else if (applicant_count.equals("2")&& property_identify.equals("1"))
                                {
                                    tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
                                    tabLayout.addTab(tabLayout.newTab().setText("CO Applicant"));
                                    tabLayout.addTab(tabLayout.newTab().setText("Property"));
                                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                                } else if (applicant_count.equals("2")&& property_identify.equals("0"))
                                {
                                    tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
                                    tabLayout.addTab(tabLayout.newTab().setText("CO Applicant"));
                                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                                }else if (applicant_count.equals("1")){
                                    tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
                                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                                }else if (applicant_count.equals("2")){
                                    tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
                                    tabLayout.addTab(tabLayout.newTab().setText("CO Applicant"));
                                    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
                                }


                                if (applicant_count.equals("1")&& property_identify.equals("1")){

                                    viewPager = (ViewPager) findViewById(R.id.pager);

                                    //Creating our pager adapter
                                    Pager1 adapter = new Pager1(getSupportFragmentManager(), tabLayout.getTabCount());

                                    //Adding adapter to pager
                                    viewPager.setAdapter(adapter);
                                }else
                                {
                                    viewPager = (ViewPager) findViewById(R.id.pager);

                                    //Creating our pager adapter
                                    Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

                                    //Adding adapter to pager
                                    viewPager.setAdapter(adapter);
                                }
                                tabLayout.setOnTabSelectedListener(DocumentChecklist_Fragment.this);
                                progressDialog.dismiss();
                            }else
                            {
                                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Applicant Entry request", String.valueOf(error));
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
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