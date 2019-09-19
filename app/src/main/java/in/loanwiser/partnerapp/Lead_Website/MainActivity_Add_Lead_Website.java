package in.loanwiser.partnerapp.Lead_Website;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.R;

public class MainActivity_Add_Lead_Website  extends AppCompatActivity {

    private Context mCon = this;
    private ViewPager mViewPager;
    private Toolbar mToolbar;
    private ViewPagerAdapter_Lead_Website mViewPagerAdapter;
    private TabLayout mTabLayout;
    String transaction_id, JSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__add__lead);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Objs.ac.ApplyFont(mToolbar, mCon);
        Objs.ac.BackButton(MainActivity_Add_Lead_Website.this, mToolbar);
        getSupportActionBar().setTitle(R.string.update_lead);

        setViewPager();
    }

    private void setViewPager() {

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPagerAdapter = new ViewPagerAdapter_Lead_Website(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public void onBackPressed() {
        Pref.removeCS1(mCon);
        Pref.removeALL(mCon);
        Pref.removeLAM(mCon);
        Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
        finish();
        super.onBackPressed();
    }
}

