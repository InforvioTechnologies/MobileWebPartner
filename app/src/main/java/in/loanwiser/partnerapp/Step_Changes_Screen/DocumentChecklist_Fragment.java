package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import in.loanwiser.partnerapp.R;

public class DocumentChecklist_Fragment extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;
    String get_jsonArray,userstatus,transaction_id,user_type,emp_states,applicant_count,property_identify;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_checklist__fragment);


        Intent intent=getIntent();
        get_jsonArray = intent.getStringExtra("jsonArray");
        applicant_count=intent.getStringExtra("applicantcount");
        property_identify=intent.getStringExtra("propertyidentify");
        Log.i("TAG", "Applicant_count_checkcondition: "+applicant_count);
        Log.i("TAG", "property_identify_checkcondition: "+property_identify);
        Log.i("TAG", "onCreate:json "+get_jsonArray);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("get_jsonArray",get_jsonArray);
        editor.putString("applicant_count",applicant_count);
        editor.putString("property_identify",property_identify);
        editor.apply();



        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);


        //Adding the tabs using addTab() method

        if (applicant_count.equals("1")&& property_identify.equals("1")){
                tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
                tabLayout.addTab(tabLayout.newTab().setText("Property"));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

            }else if (applicant_count.equals("1")&& property_identify.equals("0")){
                tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            }else if (applicant_count.equals("2")&& property_identify.equals("1"))
            {
                tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
                tabLayout.addTab(tabLayout.newTab().setText("CO Applicant"));
                tabLayout.addTab(tabLayout.newTab().setText("Property"));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            } else if (applicant_count.equals("2")&& property_identify.equals("0"))
            {
                tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
                tabLayout.addTab(tabLayout.newTab().setText("CO Applicant"));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            }else if (applicant_count.equals("1")){
              tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
               tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            }else if (applicant_count.equals("2")){
            tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
            tabLayout.addTab(tabLayout.newTab().setText("CO Applicant"));
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        }


        if (applicant_count.equals("1")&& property_identify.equals("1")){

            viewPager = (ViewPager) findViewById(R.id.pager);

            //Creating our pager adapter
            Pager1 adapter = new Pager1(getSupportFragmentManager(), tabLayout.getTabCount());

            //Adding adapter to pager
            viewPager.setAdapter(adapter);
        }else
        {
            viewPager = (ViewPager) findViewById(R.id.pager);

            //Creating our pager adapter
            Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

            //Adding adapter to pager
            viewPager.setAdapter(adapter);
        }


       /* tabLayout.addTab(tabLayout.newTab().setText("Applicant"));
        tabLayout.addTab(tabLayout.newTab().setText("CO Applicant"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);*/


      /*  //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);*/

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
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