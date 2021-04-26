package in.loanwiser.partnerapp.Documents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.telephony.ClosedSubscriberGroupInfo;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;

import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class ActivityStreamActivity extends SimpleActivity {

    AppCompatTextView cus_name,cus_mobile,applicant_id, business_name, pending_with, ask_statustxt;
    RecyclerView notes_recycleview;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String cus_nametxt,cus_mobiletxt,applicant_idtxt, business_nametxt, pending_withtxt,duetxt, ask_statustxttxt;
    private android.app.AlertDialog progressDialog;

    ArrayList<StreamList> streamlistarray;
    ActivityStreamAdapter items3;
    LinearLayout activity_found,no_activity_list_found;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);


        progressDialog = new SpotsDialog(this, R.style.Custom);
        streamlistarray = new ArrayList<>();
        items3 = new ActivityStreamAdapter(this, streamlistarray);


        cus_name=findViewById(R.id.cus_name);
        cus_mobile=findViewById(R.id.cus_mobile);
        applicant_id=findViewById(R.id.applicant_id);
        business_name=findViewById(R.id.business_name);
        pending_with=findViewById(R.id.pending_with);
        ask_statustxt=findViewById(R.id.ask_statustxt);

        no_activity_list_found=findViewById(R.id.no_activity_list_found);
        activity_found=findViewById(R.id.activity_found);

        notes_recycleview=findViewById(R.id.notes_recycleview);
        notes_recycleview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        ActivityNotes();

    }

    private void ActivityNotes() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
          //  J.put("transaction_id","62592");
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data  = String.valueOf(J);
        Log.d("Request :", data);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.ACTIVITY_NOTES, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        String JO_data  = String.valueOf(response);
                        Log.d("Request :", JO_data.toString());
                        try {
                            if (response.getString(Params.status).equals(Params.success)){
                                no_activity_list_found.setVisibility(View.GONE);
                                activity_found.setVisibility(View.VISIBLE);
                                Log.i("TAG", "onResponse: "+"done");
                                JSONObject jsonObject1=response.getJSONObject("response");
                                JSONObject jsonObject2=jsonObject1.getJSONObject("customer_data");
                                JSONObject jsonObject3=jsonObject2.getJSONObject("status_arr");
                                cus_nametxt=jsonObject2.getString("username");
                                cus_mobiletxt=jsonObject2.getString("mobileno");
                                applicant_idtxt=jsonObject2.getString("applicant_id");
                                business_nametxt=jsonObject2.getString("company_name");
                                pending_withtxt=jsonObject3.getString("pending_with");
                                duetxt=jsonObject3.getString("due_since");
                                JSONArray jsonArray2=jsonObject2.getJSONArray("ask_arr");
                                if (jsonArray2.length()>0){
                                    for (int i=0;i<jsonArray2.length();i++){
                                        JSONObject j=jsonArray2.getJSONObject(i);
                                        ask_statustxttxt=j.getString("ask_status");
                                    }
                                    ask_statustxt.setText(ask_statustxttxt);


                                }


                                JSONArray jsonArray1=jsonObject1.getJSONArray("activity");
                                if (jsonArray1.length()>0){
                                    for(int i = 0;i<jsonArray1.length();i++){
                                        JSONObject J = jsonArray1.getJSONObject(i);
                                        streamlistarray.add(new StreamList( J.getString("title"), J.getString("created_at"),J.getString("message")));

                                        items3.notifyDataSetChanged();

                                    }
                                    notes_recycleview.setAdapter(items3);

                                }else{

                                }

                                cus_mobile.setText(cus_mobiletxt);
                                cus_name.setText(cus_nametxt);
                                applicant_id.setText(applicant_idtxt);
                                business_name.setText(business_nametxt);
                                pending_with.setText(pending_withtxt +" "+ "Due " +duetxt +" days");


                            }
                            else{

                                no_activity_list_found.setVisibility(View.VISIBLE);
                                activity_found.setVisibility(View.GONE);
                               // Toast.makeText(ActivityStreamActivity.this,"Bankwise Document Not Generated",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
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

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}