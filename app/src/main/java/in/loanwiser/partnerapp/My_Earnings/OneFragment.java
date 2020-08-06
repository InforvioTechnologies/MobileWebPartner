package in.loanwiser.partnerapp.My_Earnings;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
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
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Infinite_Scrollview.CashfreeList_item;
import in.loanwiser.partnerapp.Infinite_Scrollview.Cashfree_ListAdapter;
import in.loanwiser.partnerapp.Infinite_Scrollview.InfiniteScrollProvider;
import in.loanwiser.partnerapp.Infinite_Scrollview.OnLoadMoreListener;
import in.loanwiser.partnerapp.Infinite_Scrollview.PayoutList_item;
import in.loanwiser.partnerapp.Infinite_Scrollview.Payout_ListAdapter;
import in.loanwiser.partnerapp.R;


public class OneFragment extends Fragment implements OnLoadMoreListener {

    RecyclerView expanderRecyclerView;
    List<Cashearnings> cashearningsList;
    private ProgressBar progressBar;
    private AlertDialog progressDialog;
    public static final String b2b_user_id1 = "b2b_uer_id";

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    String b2b_user_id;
    SharedPreferences pref; // 0 - for private mode
    private int count12 = -1;
    List<PayoutList_item> items;
    Payout_ListAdapter credite_coin_adapter1;
    RecyclerView recyclerView;
    LinearLayout no_item;
    AppCompatTextView trans_his,date,amount,lead_detail,loan_detail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_one, container, false);

       /* expanderRecyclerView =v.findViewById(R.id.recyclerView);
        expanderRecyclerView.setNestedScrollingEnabled(true);*/

        progressDialog = new SpotsDialog(getActivity(), R.style.Custom);
        pref = getActivity().getSharedPreferences("MyPref", 0);
        progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        b2b_user_id =  pref.getString(b2b_user_id1, null);
        no_item = (LinearLayout) v.findViewById(R.id.no_item);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "segoe_ui.ttf");
        trans_his=v.findViewById(R.id.trans_his);
        date=v.findViewById(R.id.date);
        amount=v.findViewById(R.id.amount);
        lead_detail=v.findViewById(R.id.lead_detail);
        loan_detail=v.findViewById(R.id.loan_detail);
        trans_his.setTypeface(font);
        date.setTypeface(font);
        amount.setTypeface(font);
        lead_detail.setTypeface(font);
        loan_detail.setTypeface(font);

      //  showData();
       // initRecyclerView();

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView1);
        items = new ArrayList<>();
        Account_Listings_Details1(v);
        credite_coin_adapter1 = new Payout_ListAdapter(getActivity());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(credite_coin_adapter1);

        InfiniteScrollProvider infiniteScrollProvider=new InfiniteScrollProvider();
        infiniteScrollProvider.attach(recyclerView,this);

        return v;
    }
    private void initRecyclerView() {
        CashearningAdapter cashearningAdapter=new CashearningAdapter(cashearningsList);
        expanderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        expanderRecyclerView.setAdapter(cashearningAdapter);
    }

    private void showData(){
        cashearningsList=new ArrayList<>();
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));
        cashearningsList.add(new Cashearnings("26 jan 2000","Gopal App1234","800000 Personal loan","5000","Bank of baroda  loan amount","Completed up to step 2","3450"));


    }

    private void Account_Listings_Details1(View view) {
        items.clear();

        count12 = count12 +1;
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2buser_id", b2b_user_id);
           // J.put("b2buser_id", 329);
            J.put("count", count12);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Payout request", String.valueOf(J));

        if(count12 == 0)
        {
            progressDialog.show();
        }else
        {
            progressBar.setVisibility(View.VISIBLE);
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PAYOUT_DISPLAY, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Payout Respons", String.valueOf(response));
                        try {

                            String status = response.getString("status");

                            if(status.contains("success")){
                                no_item.setVisibility(View.GONE);
                                JSONArray ja = response.getJSONArray("data");
                                Log.e("ja",ja.toString());
                                if (ja.length()>0){

                                    for(int i = 0;i<ja.length();i++){
                                        JSONObject J = ja.getJSONObject(i);

                                        String date1 = J.getString("date");
                                        String name = J.getString("name");
                                        String app_id = J.getString("app_id");
                                        String loan_amount = J.getString("loan_amount");
                                        String loan_type = J.getString("loan_type");
                                        String commision = J.getString("commision");



                                        Log.e("the values", date1.toString());

                                        items.add(new PayoutList_item(date1,name, app_id,
                                                loan_amount,loan_type,commision));
                                        credite_coin_adapter1.notifyDataSetChanged();

                                    }
                                    Log.e("leadListA", String.valueOf(credite_coin_adapter1));
                                    credite_coin_adapter1.addPosts(items);
                                    // items.clear();
                                    // items = null;


                                }else {
                                    Objs.a.ShowHideNoItems(getActivity(),true);
                                }

                            }else {

                                no_item.setVisibility(View.VISIBLE);
                                if(count12 == 0)
                                {
                                    progressDialog.dismiss();
                                }else
                                {
                                    progressBar.setVisibility(View.GONE);
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
                Account_Listings_Details1(getView());

            }
        },1500);
    }

}
