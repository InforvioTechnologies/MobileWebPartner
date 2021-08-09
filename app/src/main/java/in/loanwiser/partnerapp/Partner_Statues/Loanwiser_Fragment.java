package in.loanwiser.partnerapp.Partner_Statues;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
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
import in.loanwiser.partnerapp.Step_Changes_Screen.AcadmyDetails_Lead_Process;
import in.loanwiser.partnerapp.Step_Changes_Screen.AcadmyDetails_Loan_Process;
import in.loanwiser.partnerapp.Step_Changes_Screen.AcadmyDetails_sales;
import in.loanwiser.partnerapp.Step_Changes_Screen.Acadmytitle;

public class Loanwiser_Fragment extends Fragment {

    RecyclerView acadmy_recyclerview,acadmy_recyclerview_sales,acadmy_recyclerview_leead_process,
            acadmy_recyclerview_loan_Product;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private android.app.AlertDialog progressDialog;
    List<Acadmytitle> acadmeytitlelist;
    List<AcadmyDetails> acadmyDetails;
    List<AcadmyDetails_Lead_Process> acadmyDetails_lead;
    List<AcadmyDetails_Loan_Process> acadmyDetails_loan;
    AcademeParent_adapter academeParent_adapter;
    AcademeChild_adapter academeChild_adapter;
    String thumbnail,title,description,pdf_url,video_status,video_url;
    private ViewPager viewPager;
    Intro_List_Adapter adapter1;
    Sales_List_Adapter adapter_sales;
    leead_Process_List_Adapter adapter_Lead_process;
    Loan_Product_List_Adapter adapter_Loan_process;
    AppCompatTextView titleTextView,txt_sale_and_mark,txt_leead_process,txt_loan_Product;

    ArrayList<AcadmyDetails> items1;
    ArrayList<AcadmyDetails_sales> items2;
    ArrayList<AcadmyDetails_Lead_Process> items3;
    ArrayList<AcadmyDetails_Loan_Process> items4;
    AppCompatImageView arrowimgup,arrowimgdown1,arrowimgup_2,arrowimgdown1_2,
            arrowimgup_3,arrowimgdown1_3,arrowimgup_4,arrowimgdown1_4;

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
        arrowimgup= rootview.findViewById(R.id.arrowimgup);
        arrowimgdown1= rootview.findViewById(R.id.arrowimgdown1);
        acadmy_recyclerview.setVisibility(View.GONE);

        //2 sales

        acadmy_recyclerview_sales= rootview.findViewById(R.id.acadmy_recyclerview_sales);
        txt_sale_and_mark= rootview.findViewById(R.id.txt_sale_and_mark);
        arrowimgup_2= rootview.findViewById(R.id.arrowimgup_2);
        arrowimgdown1_2= rootview.findViewById(R.id.arrowimgdown1_2);
        acadmy_recyclerview_sales.setVisibility(View.GONE);

        acadmy_recyclerview_leead_process= rootview.findViewById(R.id.acadmy_recyclerview_leead_process);
        txt_leead_process= rootview.findViewById(R.id.txt_leead_process);

        arrowimgup_3= rootview.findViewById(R.id.arrowimgup_3);
        arrowimgdown1_3= rootview.findViewById(R.id.arrowimgdown1_3);
        acadmy_recyclerview_leead_process.setVisibility(View.GONE);

        acadmy_recyclerview_loan_Product= rootview.findViewById(R.id.acadmy_recyclerview_loan_Product);
        txt_loan_Product= rootview.findViewById(R.id.txt_loan_Product);
        arrowimgup_4= rootview.findViewById(R.id.arrowimgup_4);
        arrowimgdown1_4= rootview.findViewById(R.id.arrowimgdown1_4);
        acadmy_recyclerview_loan_Product.setVisibility(View.GONE);

        arrowimgup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrowimgdown1.setVisibility(View.VISIBLE);
                arrowimgup.setVisibility(View.GONE);
                acadmy_recyclerview.setVisibility(View.GONE);
            }
        });
        arrowimgdown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrowimgdown1.setVisibility(View.GONE);
                arrowimgup.setVisibility(View.VISIBLE);
                acadmy_recyclerview.setVisibility(View.VISIBLE);
            }
        });

        arrowimgup_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrowimgdown1_2.setVisibility(View.VISIBLE);
                arrowimgup_2.setVisibility(View.GONE);
                acadmy_recyclerview_sales.setVisibility(View.GONE);
            }
        });
        arrowimgdown1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrowimgdown1_2.setVisibility(View.GONE);
                arrowimgup_2.setVisibility(View.VISIBLE);
                acadmy_recyclerview_sales.setVisibility(View.VISIBLE);
            }
        });


        arrowimgup_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrowimgdown1_3.setVisibility(View.VISIBLE);
                arrowimgup_3.setVisibility(View.GONE);
                acadmy_recyclerview_leead_process.setVisibility(View.GONE);
            }
        });
        arrowimgdown1_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrowimgdown1_3.setVisibility(View.GONE);
                arrowimgup_3.setVisibility(View.VISIBLE);
                acadmy_recyclerview_leead_process.setVisibility(View.VISIBLE);
            }
        });

        arrowimgup_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrowimgdown1_4.setVisibility(View.VISIBLE);
                arrowimgup_4.setVisibility(View.GONE);
                acadmy_recyclerview_loan_Product.setVisibility(View.GONE);
            }
        });
        arrowimgdown1_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrowimgdown1_4.setVisibility(View.GONE);
                arrowimgup_4.setVisibility(View.VISIBLE);
                acadmy_recyclerview_loan_Product.setVisibility(View.VISIBLE);
            }
        });

        titleTextView= rootview.findViewById(R.id.titleTextView);
        items1 = new ArrayList<>();
        items2 = new ArrayList<>();
        items3 = new ArrayList<>();
        items4 = new ArrayList<>();

        acadmy_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        acadmy_recyclerview_sales.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        acadmy_recyclerview_leead_process.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        acadmy_recyclerview_loan_Product.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        adapter1 = new Intro_List_Adapter(getActivity(), items1);
        adapter_sales = new Sales_List_Adapter(getActivity(), items2);
        adapter_Lead_process = new leead_Process_List_Adapter(getActivity(), items3);
        adapter_Loan_process = new Loan_Product_List_Adapter(getActivity(), items4);

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

                        Log.e("the accademy",response.toString());
                        progressDialog.dismiss();
                      try {

                          JSONObject ja_post = response.getJSONObject("intro_loan");
                          String name = ja_post.getString("name");
                          titleTextView.setText(name);
                          JSONArray ja2 = ja_post.getJSONArray("data");

                          JSONObject ja_post_sales = response.getJSONObject("sales_market");
                          String name_sales = ja_post_sales.getString("name");
                          txt_sale_and_mark.setText(name_sales);
                          JSONArray ja2_sales = ja_post_sales.getJSONArray("data");

                          JSONObject ja_post_leead = response.getJSONObject("lead_process");
                          String name_lead = ja_post_leead.getString("name");
                          txt_leead_process.setText(name_sales);
                          JSONArray ja3_leead = ja_post_leead.getJSONArray("data");

                          JSONObject ja_post_Loan = response.getJSONObject("deepdive");
                          String name_loan = ja_post_Loan.getString("name");
                          txt_loan_Product.setText(name_loan);
                          JSONArray ja4_leead = ja_post_Loan.getJSONArray("data");

                          if (ja2.length()>0){
                              for(int i = 0;i<ja2.length();i++){

                                  JSONObject J = ja2.getJSONObject(i);
                                  title = J.getString("title");
                                  description = J.getString("description");
                                  pdf_url = J.getString("pdf_url");
                                  thumbnail = J.getString("thumbnail");
                                  video_status=J.getString("video_status");
                                  video_url=J.getString("video_url");

                                  items1.add(new AcadmyDetails(thumbnail, title, description, pdf_url,video_status,video_url));
                                  adapter1.notifyDataSetChanged();
                              }
                              acadmy_recyclerview.setAdapter(adapter1);

                          }else {
                              Objs.a.ShowHideNoItems(getActivity(),true);
                          }

                          if (ja2_sales.length()>0){
                              for(int i = 0;i<ja2_sales.length();i++){

                                  JSONObject J = ja2_sales.getJSONObject(i);
                                  title = J.getString("title");
                                  description = J.getString("description");
                                  pdf_url = J.getString("pdf_url");
                                  thumbnail = J.getString("thumbnail");
                                  video_status=J.getString("video_status");
                                  video_url=J.getString("video_url");

                                  items2.add(new AcadmyDetails_sales(thumbnail, title, description, pdf_url,video_status,video_url));
                                  adapter_sales.notifyDataSetChanged();
                              }
                              acadmy_recyclerview_sales.setAdapter(adapter_sales);

                          }else {
                              Objs.a.ShowHideNoItems(getActivity(),true);
                          }

                          if (ja3_leead.length()>0){
                              for(int i = 0;i<ja3_leead.length();i++){

                                  JSONObject J = ja3_leead.getJSONObject(i);
                                  title = J.getString("title");
                                  description = J.getString("description");
                                  pdf_url = J.getString("pdf_url");
                                  thumbnail = J.getString("thumbnail");
                                  video_status=J.getString("video_status");
                                  video_url=J.getString("video_url");

                                  items3.add(new AcadmyDetails_Lead_Process(thumbnail, title, description, pdf_url,video_status,video_url));
                                  adapter_Lead_process.notifyDataSetChanged();
                              }
                              acadmy_recyclerview_leead_process.setAdapter(adapter_Lead_process);

                          }else {
                              Objs.a.ShowHideNoItems(getActivity(),true);
                          }

                          if (ja4_leead.length()>0){
                              for(int i = 0;i<ja4_leead.length();i++){

                                  JSONObject J = ja4_leead.getJSONObject(i);
                                  title = J.getString("title");
                                  description = J.getString("description");
                                  pdf_url = J.getString("pdf_url");
                                  thumbnail = J.getString("thumbnail");
                                  video_status=J.getString("video_status");
                                  video_url=J.getString("video_url");

                                  items4.add(new AcadmyDetails_Loan_Process(thumbnail, title, description, pdf_url,video_status,video_url));
                                  adapter_Loan_process.notifyDataSetChanged();
                              }
                              acadmy_recyclerview_loan_Product.setAdapter(adapter_Loan_process);

                          }else {
                              Objs.a.ShowHideNoItems(getActivity(),true);
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