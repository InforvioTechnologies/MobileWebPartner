package in.loanwiser.partnerapp.Partner_Statues;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.CustomerCare.CustomerCare;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Chat;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Slider.Business_Loan;
import in.loanwiser.partnerapp.Slider.Personal_Loan_info;
import in.loanwiser.partnerapp.Slider.Vehicle_Loan;
import in.loanwiser.partnerapp.Slider.View_More;
import in.loanwiser.partnerapp.Slider.View_More1;
import in.loanwiser.partnerapp.Step_Changes_Screen.Pay_Out_Screen;
import in.loanwiser.partnerapp.User_Account.BankDetails;
import in.loanwiser.partnerapp.User_Account.ProfileSettings;
import in.loanwiser.partnerapp.User_Account.Welcome_Page;

public class Statues_Dashboard_Nav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private AppCompatTextView welcom,pls_enter,Lead_details;
    private AppCompatButton addlead,go_leads;
    private SliderLayout mDemoSlider;
    private LinearLayout dash_board;
    private Context mCon = this;
    Toolbar toolbar;
    MenuItem chat1;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private AlertDialog progressDialog;
    LinearLayout Get_call_back,check_eligibility,quick_apply,chat,contact_person,logout,Ly_profile,Ly_bank;
    private AppCompatTextView  Closed_count,Declined_count,Disbursed_count,In_Progress_count,document_statues,
            total_Lead_details,
            Doc_status,In_Progress,Disbursed,Declined,Closed,Approved,Approved_count,Pending_with_you,
            in_progress,rejected,sanctioned,disbursed;

    private String  Closed_count1,Declined_count1,Disbursed_count1,In_Progress_count1,
            document_statues1,Approved1,total_lead_count,Rejected;
    View drawerView;
    AppCompatTextView no_leads_data,txt_bank,txt_profile,txt_get_callback,txt_code,code;

    FloatingActionButton float_add;
    private CardView CD_declined,CD_disbursed,CD_approved,CD_in_progress,CD_document,CD_Sanctioned,
            Pending_withyoy,CD_Rejected;
    private ImageView imageView_profile;
    private View navHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statues__dashboard__nav);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.dashboards);
        setSupportActionBar(toolbar);
        Objs.ac.ApplyFont(toolbar, mCon);

        progressDialog = new SpotsDialog(this, R.style.Custom);

        float_add = (FloatingActionButton) findViewById(R.id.float_add);
        float_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Objs.ac.StartActivity(mCon, DashBoard_new.class);
                finish();

               /* Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
                finish();*/
            }
        });

      //  float_add.setContent("Start Chat");


        initCode();

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        HashMap<String, String> url_maps = new HashMap<String, String>();

        url_maps.put("Home Loan", "http://cscapi.propwiser.com/mobile/images/home_loan.png");
        url_maps.put("Loan Against Property", "http://cscapi.propwiser.com/mobile/images/Loan_aganst_property.png");
        url_maps.put("Personal Loan", "http://cscapi.propwiser.com/mobile/images/Personal_loan.png");
        url_maps.put("Business Loan", "http://cscapi.propwiser.com/mobile/images/Busines_loan.png");
        url_maps.put("Vehicle Loan", "http://cscapi.propwiser.com/mobile/images/vehicle_Loan.png");
        url_maps.put("Our Banks", "http://cscapi.propwiser.com/mobile/images/Our_Banking_Partnersl.png");

        for (final String name : url_maps.keySet()) {
            // TextSliderView textSliderView = new TextSliderView(this);
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    // .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3500);
        mDemoSlider.addOnPageChangeListener(this);


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


    private void initCode() {
        initUI();
        clicks();
        fonts();
        Account_Listings_Details();
    }

    private void initUI(){
        float_add = (FloatingActionButton)findViewById(R.id.float_add);
        Get_call_back= (LinearLayout) findViewById(R.id.get_callback);
        check_eligibility= (LinearLayout) findViewById(R.id.check_e);
        quick_apply= (LinearLayout) findViewById(R.id.quick_apply);
        chat= (LinearLayout) findViewById(R.id.Ly_chat);
       // Ly_bank= (LinearLayout) findViewById(R.id.Ly_bank);
       // Ly_profile= (LinearLayout) findViewById(R.id.Ly_profile);
     //   contact_person= (LinearLayout) findViewById(R.id.Ly_contact);
       // logout= (LinearLayout) findViewById(R.id.logout1);
        no_leads_data= (AppCompatTextView) findViewById(R.id.no_leads_data);

        //txt_get_callback= (AppCompatTextView) findViewById(R.id.txt_get_callback);
       // txt_bank= (AppCompatTextView) findViewById(R.id.txt_bank);
       // txt_profile= (AppCompatTextView) findViewById(R.id.txt_profile);

        txt_code= (AppCompatTextView) findViewById(R.id.txt_code);
        code= (AppCompatTextView) findViewById(R.id.code);
        code.setText(Pref.getPART(mCon));

        Lead_details = (AppCompatTextView) findViewById(R.id.Lead_details);
        total_Lead_details = (AppCompatTextView) findViewById(R.id.total_Lead_details);
       // Closed_count = (AppCompatTextView) findViewById(R.id.Closed_count);
      //  Declined_count = (AppCompatTextView) findViewById(R.id.Declined_count);
      //  Disbursed_count = (AppCompatTextView) findViewById(R.id.Disbursed_count);
    //    In_Progress_count = (AppCompatTextView) findViewById(R.id.In_Progress_count);
        document_statues = (AppCompatTextView) findViewById(R.id.document_statues);
        Pending_with_you = (AppCompatTextView) findViewById(R.id.Pending_with_you);
        in_progress = (AppCompatTextView) findViewById(R.id.in_progress);
        Doc_status = (AppCompatTextView) findViewById(R.id.Doc_status);
        In_Progress = (AppCompatTextView) findViewById(R.id.In_Progress);
        rejected = (AppCompatTextView) findViewById(R.id.rejected);
        sanctioned = (AppCompatTextView) findViewById(R.id.sanctioned);
        disbursed = (AppCompatTextView) findViewById(R.id.disbursed);
        Disbursed = (AppCompatTextView) findViewById(R.id.Disbursed);
        Declined = (AppCompatTextView) findViewById(R.id.Declined);
        Closed = (AppCompatTextView) findViewById(R.id.Closed);
        Approved = (AppCompatTextView) findViewById(R.id.Approved);
     //   Approved_count = (AppCompatTextView) findViewById(R.id.Approved_count);
        //CD_declined,CD_disbursed,CD_approved,CD_in_progress,CD_document
        CD_document= (CardView) findViewById(R.id.CD_document);
        CD_in_progress= (CardView) findViewById(R.id.CD_in_progress);
        Pending_withyoy= (CardView) findViewById(R.id.Pending_withyoy);
        CD_Rejected = (CardView) findViewById(R.id.CD_Rejected);
      //  CD_approved= (CardView) findViewById(R.id.CD_approved);
        CD_disbursed= (CardView) findViewById(R.id.CD_disbursed);
        CD_Sanctioned= (CardView) findViewById(R.id.CD_Sanctioned);
      //  CD_closed= (CardView) findViewById(R.id.CD_closed);
        dash_board = (LinearLayout) findViewById(R.id.dash_board);
        go_leads = (AppCompatButton) findViewById(R.id.go_leads);
    }

    private void clicks(){
        //CD_declined,CD_disbursed,CD_approved,CD_in_progress,CD_document
        findViewById(R.id.CD_document).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"1");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"1");
                Pref.putStatus_Count(mCon,total_lead_count);
                finish();

            }
        });

        findViewById(R.id.Pending_withyoy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"1");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"1");
                Pref.putStatus_Count(mCon,document_statues1);
                finish();

            }
        });

        findViewById(R.id.CD_Rejected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"1");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"7");
                Pref.putStatus_Count(mCon,Rejected);
                finish();

            }
        });

        findViewById(R.id.CD_in_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"2");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"2");
                Pref.putStatus_Count(mCon,In_Progress_count1);
                finish();

            }
        });
     /*   findViewById(R.id.CD_Sanctioned).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"3");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"3");
                Pref.putStatus_Count(mCon,Declined_count1);
                finish();

            }
        });*/
        findViewById(R.id.CD_Sanctioned).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"4");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"4");
                Pref.putStatus_Count(mCon,Approved1);
                finish();

            }
        });

        findViewById(R.id.CD_disbursed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"5");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"5");
                Pref.putStatus_Count(mCon,Disbursed_count1);
                finish();

            }
        });

       /* findViewById(R.id.CD_closed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"5");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"6");
                Pref.putStatus_Count(mCon,Disbursed_count1);
                finish();

            }
        });*/

        findViewById(R.id.go_leads).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"0");
                Pref.putStatus_Count(mCon,total_lead_count);
                finish();
            }
        });

    }


    private void fonts(){

        Objs.a.OutfitNormalFontStyle(mCon, R.id.Lead_details);

        Objs.a.OutfitNormalFontStyle(mCon, R.id.total_Lead_details);

      //  Objs.a.OutfitNormalFontStyle(mCon, R.id.In_Progress_count);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.document_statues);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Doc_status);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.In_Progress);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Disbursed);
       // Objs.a.OutfitNormalFontStyle(mCon, R.id.Declined);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Approved);
       // Objs.a.OutfitNormalFontStyle(mCon, R.id.Approved_count);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.txt_code);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.code);

    }



   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat, menu);
        chat1 = menu.findItem(R.id.action_chat);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_chat) {
            Objs.ac.StartActivity(mCon, Chat.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.nav_payout
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Objs.ac.StartActivity(mCon, ProfileSettings.class);
        } else if (id == R.id.nav_bank) {
            Objs.ac.StartActivity(mCon, BankDetails.class);
        } else if (id == R.id.nav_payout) {
            Objs.ac.StartActivity(mCon, Pay_Out_Screen.class);
        }else if (id == R.id.nav_logout) {
            ExitAlert(mCon);
        }

        /*
        else if (id == R.id.nav_call) {
            Objs.ac.StartActivity(mCon, CustomerCare.class);
        } */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        String a = String.valueOf(slider.getBundle().get("extra"));
        Log.e("Slider message22", a);
       /* url_maps.put("Home Loan", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Loan Against Property", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("Personal Loan", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Business Loan", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        url_maps.put("Vehicle Loan", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");*/

        if(a=="Home Loan")
        {
            Intent intent = new Intent(Statues_Dashboard_Nav.this, View_More.class);
            startActivity(intent);

        }
        else if(a=="Loan Against Property")
        {
            Intent intent = new Intent(Statues_Dashboard_Nav.this, View_More1.class);
            startActivity(intent);

        }else if(a == "Personal Loan")
        {
            Intent intent = new Intent(Statues_Dashboard_Nav.this, Personal_Loan_info.class);
            startActivity(intent);



        }else if(a == "Business Loan")
        {


            Intent intent = new Intent(Statues_Dashboard_Nav.this, Business_Loan.class);
            startActivity(intent);

        }else if(a == "Vehicle Loan")
        {

            Intent intent = new Intent(Statues_Dashboard_Nav.this, Vehicle_Loan.class);
            startActivity(intent);

        }
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

    public void ExitAlert(Context context) {

        //android.app.AlertDialog;
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setMessage("Do you want to Logout..?");
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Pref.removeLogin(mCon);
                Pref.removeID(mCon);
                Pref.removeMOB(mCon);
                Pref.removeMobile(mCon);
                Pref.removePART(mCon);
                Intent i = new Intent(Statues_Dashboard_Nav.this, Welcome_Page.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                finish();
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        Objs.a.DialogStyle(context, alert);
    }


    private void Account_Listings_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(mCon));
          //  J.put(Params.b2b_userid, "20407");
            Log.d("response b2b_userid ", String.valueOf(J));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.LEAD_STATUES_LIST, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response details", String.valueOf(response));
                        try {
                            JSONObject jsonObject1 = response.getJSONObject("0");
                            Log.e("the response jsonobject",String.valueOf(jsonObject1));
                            //  Closed_count,Declined_count,Disbursed_count,In_Progress_count,document_statues,total_Lead_details;
                            total_lead_count = response.getString("total_lead_logged_in");

                            document_statues1 = jsonObject1.getString("document_pending");
                            In_Progress_count1 = jsonObject1.getString("in_progress");
                            Approved1 = jsonObject1.getString("approved");
                            Disbursed_count1 = jsonObject1.getString("disbursed");
                            Declined_count1 = jsonObject1.getString("declined");
                            Closed_count1 = jsonObject1.getString("closed");
                            Rejected = jsonObject1.getString("rejected");

                          /*  total_Lead_details.setText(total_lead_count);
                            document_statues.setText(document_statues1);
                            In_Progress_count.setText(In_Progress_count1);
                            Disbursed_count.setText(Disbursed_count1);
                            Declined_count.setText(Declined_count1);
                            Closed_count.setText(Closed_count1);
                            Approved_count.setText(Approved1);*/
                            total_Lead_details.setText(total_lead_count);
                            document_statues.setText(total_lead_count);
                            Pending_with_you.setText(document_statues1);
                            in_progress.setText(In_Progress_count1);
                            rejected.setText(Rejected);
                            sanctioned.setText(Approved1);
                            disbursed.setText(Disbursed_count1);

                            if(!document_statues.equals("0")){
                                CD_document.setEnabled(true);
                            }else{
                                CD_document.setEnabled(false);
                            }

                            if(!document_statues1.equals("0")){
                                Pending_withyoy.setEnabled(true);
                            }else{
                                Pending_withyoy.setEnabled(false);
                            }

                            if(!Rejected.equals("0")){
                                CD_Rejected.setEnabled(true);
                            }else{
                                CD_Rejected.setEnabled(false);
                            }

                            if(!In_Progress_count1.equals("0")){
                                CD_in_progress.setEnabled(true);
                            }else{
                                CD_in_progress.setEnabled(false);
                            }

                            if(!Disbursed_count1.equals("0")){
                                CD_disbursed.setEnabled(true);
                            }else{
                                CD_disbursed.setEnabled(false);
                            }

                            if(!Approved1.equals("0")){
                                CD_Sanctioned.setEnabled(true);
                            }else{
                                CD_Sanctioned.setEnabled(false);
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
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
