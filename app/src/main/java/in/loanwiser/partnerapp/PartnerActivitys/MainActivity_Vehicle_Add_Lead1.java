package in.loanwiser.partnerapp.PartnerActivitys;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.R;


public class MainActivity_Vehicle_Add_Lead1 extends AppCompatActivity {

    private Context mCon = this;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private View_Vehicle_PagerAdapter mViewPagerAdapter;
    private TabLayout mTabLayout;
    String transaction_id, JSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__vehicle__add__lead1);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Objs.ac.ApplyFont(mToolbar, mCon);
        Objs.ac.BackButton(MainActivity_Vehicle_Add_Lead1.this, mToolbar);
        getSupportActionBar().setTitle(R.string.addl);
        setViewPager();
    }

    private void setViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPagerAdapter = new View_Vehicle_PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public void onBackPressed() {
        Pref.removeCS1(mCon);
       // Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
        Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
        finish();
        super.onBackPressed();
    }
}

