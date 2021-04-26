package in.loanwiser.partnerapp.PartnerActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;

public class Activity_Streem_list extends SimpleActivity {
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private AlertDialog progressDialog;
    AppCompatTextView Activity_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this,R.layout.activity__streem_list);
        initTools(R.string.viy_check);

        progressDialog = new SpotsDialog(mCon, R.style.Custom);

         Activity_details = (AppCompatTextView) findViewById(R.id.Activity_details);

      //  setContentView(R.layout.activity__streem_list);
        Bank_details_list();
    }

    private void Bank_details_list() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();

            //   J.put("transaction_id", transaction_id);
            J.put("transaction_id", "62592");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Applicant Document", String.valueOf(J));

        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Activity_Notes, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant_Doc Response",response.toString());
                        String data = String.valueOf(response);
                        //   Objs.a.showToast(mCon, data);


                        try {
                            String status = response.getString("status");

                            if(status.equals("success"))
                            {
                                JSONObject jsonObject1 = response.getJSONObject("response");

                                String fullNotes = jsonObject1.getString("fullNotes");

                                Activity_details.setText(fullNotes);
                                JSONArray activity = jsonObject1.getJSONArray("activity");
                                if(activity.length()>0)
                                {

                                  //  setAdapter(bank_eligiblearr);
                                }

                            }else
                            {
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
                //Log.d(TAG, error.getMessage());
                VolleyLog.e("TAG", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),"Network error, Please try Again ", Toast.LENGTH_SHORT).show();
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
}