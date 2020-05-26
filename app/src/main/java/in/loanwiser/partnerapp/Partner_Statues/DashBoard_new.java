package in.loanwiser.partnerapp.Partner_Statues;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.Partner_Statues.ui.gallery.GalleryFragment;
import in.loanwiser.partnerapp.Partner_Statues.ui.home.HomeFragment;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Pay_Out_Screen;
import in.loanwiser.partnerapp.User_Account.BankDetails;
import in.loanwiser.partnerapp.User_Account.ProfileSettings;
import in.loanwiser.partnerapp.User_Account.Welcome_Page;

import static adhoc.app.applibrary.Config.AppUtils.Objs.a;

public class DashBoard_new extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private AppBarConfiguration mAppBarConfiguration;
    private View navHeader;
    private ImageView imageView_profile;
    BottomNavigationView navView_bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_new);

        navView_bottom =  findViewById(R.id.nav_view1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DashBoard_new.this,Applicant_Details_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navHeader = navigationView.getHeaderView(0);
        imageView_profile = (ImageView) navHeader.findViewById(R.id.imageProfile);
        String url = "http://cscapi.propwiser.com/mobile/images/loanwiser-app-logo.png";
        Glide.with(this).load(url)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView_profile);

       loadFragment(new ActivityFragment());

        navView_bottom.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.home:
                                selectedFragment = new ActivityFragment();
                                break;
                            case R.id.leads:
                                selectedFragment = new LeadeFragment();
                                break;
                            case R.id.earnings:
                                selectedFragment = new MyearningFragment();
                                break;
                            case R.id.share:
                                selectedFragment = new ShareFragment();
                                break;

                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_container, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.nav_payout
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(DashBoard_new.this,ProfileSettings.class);
            startActivity(intent);

        } else if (id == R.id.nav_bank) {
            Intent intent = new Intent(DashBoard_new.this,BankDetails.class);
            startActivity(intent);

        } else if (id == R.id.nav_payout) {

            Intent intent = new Intent(DashBoard_new.this,Pay_Out_Screen.class);
            startActivity(intent);

        }else if (id == R.id.nav_logout) {
            ExitAlert(DashBoard_new.this);

        }

        /*
        else if (id == R.id.nav_call) {
            Objs.ac.StartActivity(mCon, CustomerCare.class);
        } */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ExitAlert(Context context) {
        androidx.appcompat.app.AlertDialog.Builder builder = new  androidx.appcompat.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setMessage("Do you want to Logout..?");
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Pref.removeLogin(getApplicationContext());
                Pref.removeID(getApplicationContext());
                Pref.removeMOB(getApplicationContext());
                Pref.removeMobile(getApplicationContext());
                Intent i = new Intent(DashBoard_new.this, Welcome_Page.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                finish();
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        a.DialogStyle(context, alert);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board_new, menu);
        return true;
    }*/


}
