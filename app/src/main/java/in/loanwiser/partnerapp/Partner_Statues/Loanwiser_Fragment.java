package in.loanwiser.partnerapp.Partner_Statues;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.AcademeChild_adapter;
import in.loanwiser.partnerapp.Step_Changes_Screen.AcademeParent_adapter;
import in.loanwiser.partnerapp.Step_Changes_Screen.AcadmyDetails;
import in.loanwiser.partnerapp.Step_Changes_Screen.Acadmytitle;

public class Loanwiser_Fragment extends Fragment {

    RecyclerView acadmy_recyclerview,childrecycleview;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private android.app.AlertDialog progressDialog;
    List<Acadmytitle> acadmeytitlelist;
    List<AcadmyDetails> acadmyDetails;
    AcademeParent_adapter academeParent_adapter;
    AcademeChild_adapter academeChild_adapter;
    String thumbnail,title,description,pdf_url,video_status,video_url;
    private ViewPager viewPager;

    public Loanwiser_Fragment(ViewPager viewPager) {
        this.viewPager=viewPager;
        // Required empty public constructor
    }

    public Loanwiser_Fragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_loanwiser_acadmy);

      //  Objs.a.setStubId(this, R.layout.activity_loanwiser_acadmy);
        View rootview= inflater.inflate(R.layout.activity_loanwiser_acadmy, container, false);
        progressDialog = new SpotsDialog(getContext(), R.style.Custom);
        acadmy_recyclerview= rootview.findViewById(R.id.acadmy_recyclerview);
        acadmeytitlelist=new ArrayList<>();
        acadmyDetails=new ArrayList<>();
        academeParent_adapter=new AcademeParent_adapter(getContext(),acadmeytitlelist);
        academeChild_adapter=new AcademeChild_adapter(getContext(),acadmyDetails);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        acadmy_recyclerview.setLayoutManager(layoutManager);

        LoanwiserAcadrmy();

        return rootview;
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
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
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