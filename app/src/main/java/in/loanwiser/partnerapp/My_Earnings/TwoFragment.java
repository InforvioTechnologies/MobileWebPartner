package in.loanwiser.partnerapp.My_Earnings;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Infinite_Scrollview.CashfreeList_item;
import in.loanwiser.partnerapp.Infinite_Scrollview.Cashfree_ListAdapter;
import in.loanwiser.partnerapp.Infinite_Scrollview.InfiniteScrollProvider;
import in.loanwiser.partnerapp.Infinite_Scrollview.LeadListAdapter_Dashboard1;
import in.loanwiser.partnerapp.Infinite_Scrollview.Lead_item;
import in.loanwiser.partnerapp.Infinite_Scrollview.OnLoadMoreListener;
import in.loanwiser.partnerapp.Partner_Statues.Credite_Coin_Adapter;
import in.loanwiser.partnerapp.Partner_Statues.Credite_Coin_item_freqent;
import in.loanwiser.partnerapp.Partner_Statues.LeadeFragment;
import in.loanwiser.partnerapp.R;


public class TwoFragment extends Fragment implements OnLoadMoreListener {


    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public static TwoFragment newInstance(String param1, String param2) {
        TwoFragment fragment = new TwoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerView expanderRecyclerView;

    List<Creditearnings> creditearninglist;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    LinearLayout Ly_no_leads_data;

    public static final String b2b_user_id1 = "b2b_uer_id";
    private ProgressBar progressBar;
    private AlertDialog progressDialog;
    private int count12 = -1;
    String b2b_user_id;
    SharedPreferences pref; // 0 - for private mode

    List<CashfreeList_item> items;
    Cashfree_ListAdapter credite_coin_adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_two, container, false);
        expanderRecyclerView =v.findViewById(R.id.recyclerView);

        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        Ly_no_leads_data = (LinearLayout) v.findViewById(R.id.Ly_no_leads_data);
        progressDialog = new SpotsDialog(getActivity(), R.style.Custom);
        pref = getActivity().getSharedPreferences("MyPref", 0);

        b2b_user_id =  pref.getString(b2b_user_id1, null);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        items = new ArrayList<>();

       // showData();
      //  initRecyclerView();
        Account_Listings_Details(v);

        credite_coin_adapter = new Cashfree_ListAdapter(getActivity());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        recyclerView.setAdapter(credite_coin_adapter);

        InfiniteScrollProvider infiniteScrollProvider=new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView,this);
        return v;
    }

    private void initRecyclerView() {
        // MovieAdapter movieAdapter = new MovieAdapter(movieList);
        CreditearningAdapter creditearningAdapter=new CreditearningAdapter(creditearninglist);
        expanderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        expanderRecyclerView.setAdapter(creditearningAdapter);
    }

    private void showData(){
        creditearninglist=new ArrayList<>();

        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));
        creditearninglist.add(new Creditearnings("26th jan 2000","Gopal App1234 step3","500000 personal loan","Partner Benefit standard plan","40"));

    }

    private void Account_Listings_Details(View view) {
        items.clear();

        count12 = count12 +1;
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2buser_id", 24303);
            J.put("count", count12);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Coin request", String.valueOf(J));

        if(count12 == 0)
        {
            progressDialog.show();
        }else
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CREDIT_COINS, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Coin Respons", String.valueOf(response));
                        try {

                            String status = response.getString("status");
                            String wallet = response.getString("wallet");

                            Log.e("wallet",wallet);

                            if(status.equals("1")){
                                JSONArray ja = response.getJSONArray("data");
                                Log.e("ja",ja.toString());
                                if (ja.length()>0){

                                    for(int i = 0;i<ja.length();i++){
                                        JSONObject J = ja.getJSONObject(i);

                                        String date_disp = J.getString("date_disp");
                                        String user_name = J.getString("user_name");
                                        String app_id = J.getString("app_id");
                                        String username = J.getString("user_name");

                                        String loan_amount = J.getString("loan_amount");
                                        String loan_type = J.getString("loan_type");
                                        String name_of_source = J.getString("name_of_source");
                                        String transaction_mode_disp = J.getString("transaction_mode_disp");
                                        String coins_disp = J.getString("coins_disp");
                                        String current_step = J.getString("current_step");
                                        String payment_plan  = J.getString("payment_plan");


                                        Log.e("mobile no",date_disp);

                                        items.add(new CashfreeList_item(date_disp,user_name, app_id,
                                                loan_amount,loan_type,name_of_source,transaction_mode_disp,coins_disp,current_step,
                                                payment_plan));
                                        credite_coin_adapter.notifyDataSetChanged();
                                    }
                                    Log.e("leadListA", String.valueOf(credite_coin_adapter));
                                    credite_coin_adapter.addPosts(items);
                                    // items.clear();
                                    // items = null;


                                }else {
                                    Objs.a.ShowHideNoItems(getActivity(),true);
                                }

                            }else {



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
                    progressDialog.show();
                }else
                {
                    progressBar.setVisibility(View.GONE);
                }
                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_SHORT).show();
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
}
