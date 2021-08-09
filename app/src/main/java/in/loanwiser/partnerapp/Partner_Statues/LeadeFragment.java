package in.loanwiser.partnerapp.Partner_Statues;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.daimajia.slider.library.SliderLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;

import in.loanwiser.partnerapp.Infinite_Scrollview.InfiniteScrollProvider;
import in.loanwiser.partnerapp.Infinite_Scrollview.LeadListAdapter_Dashboard1;
import in.loanwiser.partnerapp.Infinite_Scrollview.Lead_item;
import in.loanwiser.partnerapp.Infinite_Scrollview.OnLoadMoreListener;
import in.loanwiser.partnerapp.R;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class LeadeFragment extends Fragment implements OnLoadMoreListener {

    public LeadeFragment() {
        // Required empty public constructor
    }

    public static LeadeFragment newInstance(String param1, String param2) {
        LeadeFragment fragment = new LeadeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private int count12 = -1;
    List<Lead_item> items;
    LeadListAdapter_Dashboard1 leadListAdapter_dashboard;
    RecyclerView recyclerView;

    private ProgressBar progressBar;
    private AlertDialog progressDialog;
    String applicant_id;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    LinearLayout Ly_no_leads_data,network_stat,mainlay;

    public static final String b2b_user_id1 = "b2b_uer_id";
    String b2b_user_id;
    SharedPreferences pref; // 0 - for private mode



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lead_fragment_activity, container, false);

     /*   binding = ActivityMainBinding.inflate(getLayoutInflater());
         view = binding.getRoot();
        setContentView(view);
*/

        pref = getActivity().getSharedPreferences("MyPref", 0);

        b2b_user_id =  pref.getString(b2b_user_id1, null);

        items = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        Ly_no_leads_data = (LinearLayout) view.findViewById(R.id.Ly_no_leads_data);
        network_stat = (LinearLayout) view.findViewById(R.id.network_stat);
        mainlay = (LinearLayout) view.findViewById(R.id.mainlay);
        progressDialog = new SpotsDialog(getContext(), R.style.Custom);

        if (isConnected()==false){

            progressDialog.dismiss();
            network_stat.setVisibility(View.VISIBLE);
            mainlay.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.GONE);

        }


        Account_Listings_Details(view);

        leadListAdapter_dashboard = new LeadListAdapter_Dashboard1(getContext());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        recyclerView.setAdapter(leadListAdapter_dashboard);

        InfiniteScrollProvider infiniteScrollProvider=new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView,this);

        return view;


    }


    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }


    private void Account_Listings_Details(View view) {
        items.clear();

        count12 = count12 +1;
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2b_userid", b2b_user_id);
            J.put(Params.status_id, "0");
            J.put("count", count12);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("count12 request", String.valueOf(J));

        if(count12 == 0)
        {
            progressDialog.show();
        }else
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LEAD_LIST_POST, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Dashboard response", String.valueOf(response));
                        try {
                            if(response.getBoolean(Params.status)){
                                JSONArray ja = response.getJSONArray(Params.applicationusers_arr);

                                if (ja.length()>0){

                                    for(int i = 0;i<ja.length();i++){

                                        JSONObject J = ja.getJSONObject(i);
                                        String id = J.getString("id");
                                        String loan_typename = J.getString("loan_typename");
                                        String cobrand_mobile = J.getString("cobrand_mobile");
                                        String step_status = J.getString("step_status");
                                        String username = J.getString("username");
                                        String mobileno = J.getString("mobileno");
                                        String transaction_id = J.getString("transaction_id");
                                        String from_cobrand = J.getString("from_cobrand");
                                        String loan_type = J.getString("loan_type");
                                        String loan_amount = J.getString("loan_amount");
                                        String comp_step = J.getString("comp_step");
                                        String status_disp = J.getString("status_disp");
                                        String color_code = J.getString("color_code");
                                        String payment_plan = J.getString("payment_plan");
                                        String created_at = J.getString("created_at");
                                        applicant_id =   J.getString(Params.applicant_id);
                                        //  String id1 =   J.getString("id");
                                        // String field_status = J.getString("field_status");
                                        String pending_asks_count = J.getString("pending_asks_count");
                                        Log.e("mobile no",mobileno);

                                        items.add(new Lead_item(applicant_id,loan_typename, step_status,username,
                                                mobileno,transaction_id,loan_amount,comp_step,status_disp,color_code,payment_plan,id,pending_asks_count,from_cobrand,
                                                loan_type,cobrand_mobile,created_at));
                                        leadListAdapter_dashboard.notifyDataSetChanged();
                                    }
                                    Log.e("leadListA", String.valueOf(leadListAdapter_dashboard));
                                    leadListAdapter_dashboard.addPosts(items);
                                    // items.clear();
                                    // items = null;


                                }else {
                                    Objs.a.ShowHideNoItems(getActivity(),true);
                                }

                               /* if (ja.length()>0){
                                   // Log.e("Length", String.valueOf(ja.length()));
                                    Ly_no_leads_data.setVisibility(View.GONE);
                                    float_chat.setVisibility(View.VISIBLE);
                                    label_status.setVisibility(View.VISIBLE);
                                    setAdapter(ja);

                                        if (Pref.getStatus_Count(mCon) != null && !Pref.getStatus_Count(mCon).isEmpty() && !Pref.getStatus_Count(mCon).equals("null")){

                                            label_status.setText("No. of Leads in this Queue: " + Pref.getStatus_Count(mCon));
                                         }else {
                                            label_status.setText("Total No. of Leads: " + String.valueOf(ja.length()));
                                        }

                                }*/


                            }else {

                                if(count12 == 0)
                                {

                                    Ly_no_leads_data.setVisibility(View.VISIBLE);
                                }


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(count12 == 0)
                        {
                            progressDialog.dismiss();
                        }else
                        {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if(count12 == 0)
                {
                    progressDialog.dismiss();
                }else
                {
                    progressBar.setVisibility(View.GONE);


                }
                Log.e("errror",error.toString());
              //  Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        int x=2;// retry count
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                x, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    @Override
    public void onLoadMore() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                items.clear();
                Account_Listings_Details(getView());

            }
        },1500);
    }
    public static Bitmap getBitmapFromURL(String imgUrl) {
        try {
            java.net.URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


}
