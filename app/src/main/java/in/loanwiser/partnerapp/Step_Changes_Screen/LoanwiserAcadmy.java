package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class LoanwiserAcadmy extends SimpleActivity {

    RecyclerView acadmy_recyclerview,childrecycleview;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private android.app.AlertDialog progressDialog;
    List<Acadmytitle> acadmeytitlelist;
    List<AcadmyDetails> acadmyDetails;
    AcademeParent_adapter academeParent_adapter;
    AcademeChild_adapter academeChild_adapter;
    String thumbnail,title,description,pdf_url,video_status,video_url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_loanwiser_acadmy);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_loanwiser_acadmy);
        initTools(R.string.loan_acddmy);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        acadmy_recyclerview=findViewById(R.id.acadmy_recyclerview);
        acadmeytitlelist=new ArrayList<>();
        acadmyDetails=new ArrayList<>();
        academeParent_adapter=new AcademeParent_adapter(this,acadmeytitlelist);
        academeChild_adapter=new AcademeChild_adapter(this,acadmyDetails);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        acadmy_recyclerview.setLayoutManager(layoutManager);

        LoanwiserAcadrmy();
    }




    private void LoanwiserAcadrmy() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;

        String data  = String.valueOf(J);
        Log.d("Request :", data);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.ACADEMY_DETAILS, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                      try{
                          Iterator<String> keys= response.keys();
                          while (keys.hasNext())
                          {
                              String keyValue = (String)keys.next();
                              String valueString = response.getString(keyValue);
                              Log.i("TAG", "onResponse:keys "+valueString);
                              JSONObject innerJObject = response.getJSONObject(keyValue);
                              JSONArray jsonArray=innerJObject.getJSONArray("data");
                              String name = innerJObject.getString("name");
                              acadmeytitlelist.add(new Acadmytitle(name));
                              Log.i("TAG", "onResponse: loan"+acadmeytitlelist);
                              academeParent_adapter.notifyDataSetChanged();
                              for (int i=0;i<jsonArray.length();i++){
                                  JSONObject jsonObject1 =jsonArray.getJSONObject(i);
                                  title=jsonObject1.getString("title");
                                  description=jsonObject1.getString("description");
                                   pdf_url=jsonObject1.getString("pdf_url");
                                   thumbnail=jsonObject1.getString("thumbnail");
                                  video_status=jsonObject1.getString("video_status");
                                  video_url=jsonObject1.getString("video_url");


                                  Log.i("TAG", "onResponse:title "+title);
                                  Log.i("TAG", "onResponse:description "+description);
                                  Log.i("TAG", "onResponse:pdf_url "+pdf_url);

                              }
                              acadmyDetails.add(new AcadmyDetails(thumbnail,title,description,pdf_url,video_status,video_url));
                              Log.i("TAG", "onResponse:det "+acadmyDetails);

                              acadmy_recyclerview.setAdapter(academeParent_adapter);
                              Log.i("TAG", "onResponse:name "+name);
                          }

                      }catch (JSONException e){
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