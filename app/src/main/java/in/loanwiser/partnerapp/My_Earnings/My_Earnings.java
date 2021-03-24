package in.loanwiser.partnerapp.My_Earnings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

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

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.BankStamentUpload.Doc_ImageView_pay_structur;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Pay_Out_Screen;

public class My_Earnings extends SimpleActivity {

    private ScrollView scrollView;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private AlertDialog progressDialog;

    AppCompatTextView total_earnings,potential_earnings,disbursal_of_leads,my_earning1;
    PermissionUtils permissionUtils;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    private Context context = this;
    TabLayout tabLayout;
    ViewPager viewPager;
    AppCompatTextView tot_ear,pot_ear,dis_lead,totl_cred,earn_des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.sample);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.sample);
        initTools(R.string.my_earning);
        progressDialog = new SpotsDialog(context, R.style.Custom);
        Account_Listings_Details1();

         tabLayout = findViewById(R.id.tabs);
         viewPager = findViewById(R.id.viewpager);
        permissionUtils = new PermissionUtils();
        total_earnings = (AppCompatTextView) findViewById(R.id.total_earnings);
        potential_earnings = (AppCompatTextView) findViewById(R.id.potential_earnings);
        disbursal_of_leads = (AppCompatTextView) findViewById(R.id.disbursal_of_leads);
        my_earning1 = (AppCompatTextView) findViewById(R.id.my_earning);

        Typeface font = Typeface.createFromAsset(My_Earnings.this.getAssets(), "segoe_ui.ttf");
        tot_ear=findViewById(R.id.tot_ear);
        pot_ear=findViewById(R.id.pot_ear);
        dis_lead=findViewById(R.id.dis_lead);
        totl_cred=findViewById(R.id.totl_cred);
        earn_des=findViewById(R.id.earn_des);
        tot_ear.setTypeface(font);
        pot_ear.setTypeface(font);
        dis_lead.setTypeface(font);
        totl_cred.setTypeface(font);
        earn_des.setTypeface(font);
       // scrollView=findViewById(R.id.scroll);

        my_earning1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String viability_report_URL1 = "http://callcenter.propwiser.com/includes/DETAILED-PAYOUT-STRUCTURE.pdf";

                if (permissionUtils.checkPermission(My_Earnings.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                    if (viability_report_URL1.length() > 0) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL1)));
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }

                }*/
                Intent intent = new Intent(My_Earnings.this, Doc_ImageView_pay_structur.class);
                startActivity(intent);
            }
        });

     /*   viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                viewPager.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               // viewPager.getParent().requestDisallowInterceptTouchEvent(true);
                scrollView.requestDisallowInterceptTouchEvent(true);
            }
        });*/


    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
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

    public class CustomViewPager extends ViewPager {
        public CustomViewPager(@NonNull Context context) {
            super(context);
        }

        public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }


        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            try {
                int currentPagePosition = 0;
                View child = getChildAt(currentPagePosition);
                if (child != null) {
                    child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                    int h = child.getMeasuredHeight();
                    heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


    private void Account_Listings_Details1() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2buser_id", Pref.getID(getApplicationContext()));

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

                            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                            adapter.addFragment(new OneFragment(), "CASH EARNINGS");
                            adapter.addFragment(new TwoFragment(), "CREDIT COINS");
                            viewPager.setAdapter(adapter);
                            tabLayout.setupWithViewPager(viewPager);


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
