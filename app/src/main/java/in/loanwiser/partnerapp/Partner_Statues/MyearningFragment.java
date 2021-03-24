package in.loanwiser.partnerapp.Partner_Statues;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import in.loanwiser.partnerapp.BankStamentUpload.Doc_ImageView_pay_structur;
import in.loanwiser.partnerapp.My_Earnings.My_Earnings;
import in.loanwiser.partnerapp.My_Earnings.OneFragment;
import in.loanwiser.partnerapp.My_Earnings.TwoFragment;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Pay_Out_Screen;
import in.loanwiser.partnerapp.Step_Changes_Screen.Step_Completion_Screen;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class MyearningFragment extends  Fragment {


    public MyearningFragment() {
        // Required empty public constructor
    }

    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private AlertDialog progressDialog;

    AppCompatTextView total_earnings,potential_earnings,disbursal_of_leads,my_earning;
    TabLayout tabLayout;
    ViewPager viewPager;
    PermissionUtils permissionUtils;
    AppCompatTextView tot_ear,pot_ear,dis_lead,totl_cred,earn_des;

    CardView linearLayout,linearLayout2;
    LinearLayout network_stat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_myearning, container, false);
        progressDialog = new SpotsDialog(getActivity(), R.style.Custom);

        total_earnings = (AppCompatTextView) v.findViewById(R.id.total_earnings);
        potential_earnings = (AppCompatTextView) v.findViewById(R.id.potential_earnings);
        disbursal_of_leads = (AppCompatTextView) v.findViewById(R.id.disbursal_of_leads);

        my_earning = (AppCompatTextView) v.findViewById(R.id.my_earning);

        linearLayout = v.findViewById(R.id.linearLayout);
        linearLayout2 = v.findViewById(R.id.linearLayout2);
        network_stat = v.findViewById(R.id.network_stat);
        permissionUtils = new PermissionUtils();

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "segoe_ui.ttf");
        tot_ear=v.findViewById(R.id.tot_ear);
        pot_ear=v.findViewById(R.id.pot_ear);
        dis_lead=v.findViewById(R.id.dis_lead);
        totl_cred=v.findViewById(R.id.totl_cred);
        earn_des=v.findViewById(R.id.earn_des);
        tot_ear.setTypeface(font);
        pot_ear.setTypeface(font);
        dis_lead.setTypeface(font);
        totl_cred.setTypeface(font);
        earn_des.setTypeface(font);

        Account_Listings_Details1();

        tabLayout = v.findViewById(R.id.tabs);
        viewPager = v.findViewById(R.id.viewpager);

        if(isConnected()==false){
            network_stat.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.GONE);
            linearLayout.setVisibility(View.INVISIBLE);
            linearLayout2.setVisibility(View.INVISIBLE);
        }


        my_earning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), Doc_ImageView_pay_structur.class);
                startActivity(intent);
              /*  String viability_report_URL1 = "https://callcenter.loanwiser.in/includes/DETAILED-PAYOUT-STRUCTURE.pdf";

                if (permissionUtils.checkPermission(getActivity(), STORAGE_PERMISSION_REQUEST_CODE, view)) {
                    if (viability_report_URL1.length() > 0) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL1)));
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }

                }*/
            }
        });

        return v;

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