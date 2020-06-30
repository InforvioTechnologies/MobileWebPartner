package in.loanwiser.partnerapp.Partner_Statues;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.My_Earnings.My_Earnings;
import in.loanwiser.partnerapp.My_Earnings.OneFragment;
import in.loanwiser.partnerapp.My_Earnings.TwoFragment;
import in.loanwiser.partnerapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyearningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyearningFragment extends  Fragment {


    public MyearningFragment() {
        // Required empty public constructor
    }



    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private AlertDialog progressDialog;

    AppCompatTextView total_earnings,potential_earnings,disbursal_of_leads;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_myearning, container, false);
        progressDialog = new SpotsDialog(getActivity(), R.style.Custom);

        total_earnings = (AppCompatTextView) v.findViewById(R.id.total_earnings);
        potential_earnings = (AppCompatTextView) v.findViewById(R.id.potential_earnings);
        disbursal_of_leads = (AppCompatTextView) v.findViewById(R.id.disbursal_of_leads);

        Account_Listings_Details1();

        tabLayout = v.findViewById(R.id.tabs);
        viewPager = v.findViewById(R.id.viewpager);



        return v;

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new OneFragment(), "CASH EARNINGS");
        adapter.addFragment(new TwoFragment(), "CREDIT COINS");
        viewPager.setAdapter(adapter);
    }



    static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();
        ViewPagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }
        @Override
        public Fragment getItem(int i) {
            return mList.get(i);
        }
        @Override
        public int getCount() {
            return mList.size();
        }
        public void addFragment(Fragment fragment, String title) {
            mList.add(fragment);
            mTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }

    private void Account_Listings_Details1() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2buser_id", Pref.getID(getActivity()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Earning Page" , String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.My_Earnings, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("earning reponse Page" , String.valueOf(response));
                        try {

                            String commission_earned = response.getString("commission_earned");
                            String potential_earning = response.getString("potential_earning");
                            String wallet_coins = response.getString("wallet_coins");

                            total_earnings.setText(commission_earned);
                            potential_earnings.setText(potential_earning);
                            disbursal_of_leads.setText(wallet_coins);

                            setupViewPager(viewPager);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Profile Page" , String.valueOf(error));
                // Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
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