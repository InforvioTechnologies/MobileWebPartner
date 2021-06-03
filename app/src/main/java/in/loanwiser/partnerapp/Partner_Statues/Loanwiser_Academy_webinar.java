package in.loanwiser.partnerapp.Partner_Statues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklist_Fragment;
import in.loanwiser.partnerapp.Step_Changes_Screen.Pager1;

public class Loanwiser_Academy_webinar extends SimpleActivity implements TabLayout.OnTabSelectedListener{
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_document_checklist__fragment);
        // applicant_name =  Objs.a.getBundle(this, Params.applicant_name);
        String document= "Loanwiser Academy";
        //   Log.e("doc",document);
        initTools1(document);
        //  initTools1(applicant_name);

        viewPager = (ViewPager) findViewById(R.id.pager);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
       // viewPager = (ViewPager) findViewById(R.id.pager);
        // tabLayout.setupWithViewPager(viewPager);




        tabLayout.addTab(tabLayout.newTab().setText("COURSES"));
        tabLayout.addTab(tabLayout.newTab().setText("WEBINARS"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(Loanwiser_Academy_webinar.this, R.color.text_blue));

        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        tabLayout.getTabAt(position).select();
                    }
                });

        WebinarPager1 adapter = new WebinarPager1(getSupportFragmentManager(), tabLayout.getTabCount(),viewPager);
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(Loanwiser_Academy_webinar.this);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}