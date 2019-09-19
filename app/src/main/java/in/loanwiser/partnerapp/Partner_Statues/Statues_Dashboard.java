package in.loanwiser.partnerapp.Partner_Statues;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
import in.loanwiser.partnerapp.User_Account.BankDetails;
import in.loanwiser.partnerapp.User_Account.ProfileSettings;
import in.loanwiser.partnerapp.User_Account.Welcome_Page;

public class Statues_Dashboard extends AppCompatActivity implements
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener  {


    private AppCompatTextView welcom,pls_enter,Lead_details;
    private AppCompatButton addlead,go_leads;
    private SliderLayout mDemoSlider;
    private LinearLayout dash_board;
    private Context mCon = this;
    Toolbar toolbar;
    MenuItem chat1;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    DrawerLayout drawerLayout;
    private AlertDialog progressDialog;
    LinearLayout Get_call_back,check_eligibility,quick_apply,chat,contact_person,logout,Ly_profile,Ly_bank;
    private AppCompatTextView  Closed_count,Declined_count,Disbursed_count,In_Progress_count,document_statues,
            total_Lead_details,
    Doc_status,In_Progress,Disbursed,Declined,Closed,Approved,Approved_count;
    private String  Closed_count1,Declined_count1,Disbursed_count1,In_Progress_count1,document_statues1,Approved1,total_lead_count;
    View drawerView;
    AppCompatTextView no_leads_data,txt_bank,txt_profile,txt_get_callback,txt_code,code;
   // FloatingActionMenu materialDesignFAM;
    FloatingActionButton float_add;
    //FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    private CardView CD_declined,CD_disbursed,CD_approved,CD_in_progress,CD_document;
    private ImageView  imageView_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statues__dashboard);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.dashboards);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_hamburger);
        Objs.ac.ApplyFont(toolbar, mCon);
       // Closed_count,Declined_count,Disbursed_count,In_Progress_count,document_statues;
        addlead = (AppCompatButton) findViewById(R.id.addlead);



        addlead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
                finish();
            }
        });

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        float_add = (FloatingActionButton)findViewById(R.id.float_add);
        Get_call_back= (LinearLayout) findViewById(R.id.get_callback);
        check_eligibility= (LinearLayout) findViewById(R.id.check_e);
        quick_apply= (LinearLayout) findViewById(R.id.quick_apply);
        chat= (LinearLayout) findViewById(R.id.Ly_chat);
        Ly_bank= (LinearLayout) findViewById(R.id.Ly_bank);
        Ly_profile= (LinearLayout) findViewById(R.id.Ly_profile);
        contact_person= (LinearLayout) findViewById(R.id.Ly_contact);
        logout= (LinearLayout) findViewById(R.id.logout1);
        no_leads_data= (AppCompatTextView) findViewById(R.id.no_leads_data);
        txt_get_callback= (AppCompatTextView) findViewById(R.id.txt_get_callback);
        txt_bank= (AppCompatTextView) findViewById(R.id.txt_bank);
        txt_profile= (AppCompatTextView) findViewById(R.id.txt_profile);
        txt_code= (AppCompatTextView) findViewById(R.id.txt_code);
        code= (AppCompatTextView) findViewById(R.id.code);

        //CD_declined,CD_disbursed,CD_approved,CD_in_progress,CD_document
        CD_document= (CardView) findViewById(R.id.CD_document);
        CD_in_progress= (CardView) findViewById(R.id.CD_in_progress);
        CD_approved= (CardView) findViewById(R.id.CD_approved);
        CD_disbursed= (CardView) findViewById(R.id.CD_disbursed);
        CD_declined= (CardView) findViewById(R.id.CD_declined);

     //   materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
     //   floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
     //   floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
     //   floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);




        drawerView = (View)findViewById(R.id.drawer);


        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                drawerLayout.openDrawer(drawerView);
            }});
        drawerLayout.setDrawerListener(myDrawerListener);


        Get_call_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
              /*  if(ContextCompat.checkSelfPermission(
                        mCon,android.Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mCon, new
                            String[]{android.Manifest.permission.CALL_PHONE}, 0);
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "6376090176")));
                }*/
            }
        });

        contact_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawerLayout.closeDrawer(drawerView);
                Objs.ac.StartActivity(mCon, CustomerCare.class);

           /*     if(ContextCompat.checkSelfPermission(
                        mCon,android.Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mCon, new
                            String[]{android.Manifest.permission.CALL_PHONE}, 0);
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "7395825992")));
                }*/
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
                ExitAlert(mCon);
            }
        });

        Ly_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
                Objs.ac.StartActivity(mCon, ProfileSettings.class);
                // finish();
            }
        });

        Ly_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(drawerView);
                Objs.ac.StartActivity(mCon, BankDetails.class);
                // finish();

            }
        });


        progressDialog = new SpotsDialog(this, R.style.Custom);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        HashMap<String,String> url_maps = new HashMap<String, String>();

        url_maps.put("Home Loan", "http://cscapi.propwiser.com/mobile/images/home_loan.png");
        url_maps.put("Loan Against Property", "http://cscapi.propwiser.com/mobile/images/Loan_aganst_property.png");
        url_maps.put("Personal Loan", "http://cscapi.propwiser.com/mobile/images/Personal_loan.png");
        url_maps.put("Business Loan", "http://cscapi.propwiser.com/mobile/images/Busines_loan.png");
        url_maps.put("Vehicle Loan", "http://cscapi.propwiser.com/mobile/images/vehicle_Loan.png");
        url_maps.put("Our Banks", "http://cscapi.propwiser.com/mobile/images/Our_Banking_Partnersl.png");

       /* HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.drawable.hannibal);
        file_maps.put("Big Bang Theory",R.drawable.bigbang);
        file_maps.put("House of Cards",R.drawable.house);
        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);*/

        for(final String name : url_maps.keySet()){
           // TextSliderView textSliderView = new TextSliderView(this);
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                   // .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
                    /*.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {


                                Intent intent = new Intent(Dashboard_Activity.this,View_More.class);
                                startActivity(intent);


                        }
                    });
*/
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3500);
        mDemoSlider.addOnPageChangeListener(this);

        initCode();
        dash_board = (LinearLayout) findViewById(R.id.dash_board);
        go_leads = (AppCompatButton) findViewById(R.id.go_leads);
      /*  dash_board.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                    //   Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"0");
                       Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                       Pref.putStatus_id(mCon,"0");
                       finish();
                                          }
                                      }
        );*/

        go_leads.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              //   Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"0");
                                              Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                                              Pref.putStatus_id(mCon,"0");
                                              Pref.putStatus_Count(mCon,total_lead_count);
                                              finish();
                                              System.exit(0);
                                          }
                                      }
        );

        Account_Listings_Details();


     /*   floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
                finish();
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"0");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"0");
                finish();
            }
        });*/
        float_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
                finish();

            }
        });
        clicks();


       /*  imageView_profile = (ImageView) findViewById(adhoc.app.applibrary.R.id.imageProfile);
         imageView_profile.setImageDrawable(mCon.getResources().getDrawable(adhoc.app.applibrary.R.drawable.default_placeholder));
        String url ="http://cscapi.propwiser.com/mobile/images/loanwiser-app-logo.png";
        Objs.a.loadPicasso(mCon,url, imageView_profile,(ProgressBar)findViewById(adhoc.app.applibrary.R.id.progressBarMaterial));
        final AppCompatTextView tvUserName = (AppCompatTextView)findViewById(R.id.tvUserName);
        final AppCompatTextView tvUserEmail = (AppCompatTextView) findViewById(adhoc.app.applibrary.R.id.tvUserEmail);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(),  "Lato-Regular.ttf");
        tvUserName.setText(mCon.getResources().getString(R.string.app_name));
        tvUserEmail.setText(mCon.getResources().getString(R.string.app_web));
        tvUserEmail.setTypeface(font);
        tvUserName.setTypeface(font);
*/
        //String filename =  "https://ak6.picdn.net/shutterstock/videos/1007116486/thumb/12.jpg";
       // Objs.a.loadPicasso(mCon,filename,imageView_profile,(ProgressBar)findViewById(R.id.progressBarMaterial));


     //   Objs.a.showToast(mCon,Pref.getPART(mCon));



        PrintHashKey();

    }


    @Override
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
    }


    private void clicks() {

        //CD_declined,CD_disbursed,CD_approved,CD_in_progress,CD_document
        findViewById(R.id.CD_document).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"1");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"1");
                Pref.putStatus_Count(mCon,document_statues1);
                finish();
                System.exit(0);
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
                System.exit(0);
            }
        });
        findViewById(R.id.CD_declined).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"3");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"3");
                Pref.putStatus_Count(mCon,Declined_count1);
                finish();

            }
        });
        findViewById(R.id.CD_approved).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Objs.ac.StartActivityPutExtra(mCon, Dashboard_Activity.class,Params.status_id,"4");
                Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                Pref.putStatus_id(mCon,"4");
                Pref.putStatus_Count(mCon,Approved1);
                finish();
                System.exit(0);
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
                System.exit(0);
            }
        });



    }

    private void PrintHashKey() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo("in.loanwiser.partnerapp", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

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
                Intent intent = new Intent(Statues_Dashboard.this, View_More.class);
                startActivity(intent);

            }
            else if(a=="Loan Against Property")
            {
                Intent intent = new Intent(Statues_Dashboard.this, View_More1.class);
                startActivity(intent);

            }else if(a == "Personal Loan")
            {
                Intent intent = new Intent(Statues_Dashboard.this, Personal_Loan_info.class);
                startActivity(intent);



            }else if(a == "Business Loan")
            {


                Intent intent = new Intent(Statues_Dashboard.this, Business_Loan.class);
                startActivity(intent);

            }else if(a == "Vehicle Loan")
            {

                Intent intent = new Intent(Statues_Dashboard.this, Vehicle_Loan.class);
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

    private void initCode() {
        initUI();
        fonts();

       // clicks();

        code.setText(Pref.getPART(mCon));

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
                Intent i = new Intent(Statues_Dashboard.this, Welcome_Page.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                finish();
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        Objs.a.DialogStyle(context, alert);
    }
 /*   public void DialogStyle(Context context, AlertDialog alert) {
        Button BUTTON_NEGATIVE = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        BUTTON_NEGATIVE.setTextColor(context.getResources().getColor(adhoc.app.applibrary.R.color.colorAccent));
        BUTTON_NEGATIVE.setTransformationMethod(null);
        Typeface cancel_font = Typeface.createFromAsset(context.getAssets(), "RobotoSlab-Light.ttf");
        BUTTON_NEGATIVE.setTypeface(cancel_font);

        Button BUTTON_POSITIVE = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        BUTTON_POSITIVE.setTextColor(context.getResources().getColor(adhoc.app.applibrary.R.color.colorPrimaryDark));
        BUTTON_POSITIVE.setTransformationMethod(null);
        Typeface ok_font = Typeface.createFromAsset(context.getAssets(), "RobotoSlab-Light.ttf");
        BUTTON_POSITIVE.setTypeface(ok_font);
    }
*/
    DrawerLayout.DrawerListener myDrawerListener = new DrawerLayout.DrawerListener(){

        @Override
        public void onDrawerClosed(View drawerView) {
        }

        @Override
        public void onDrawerOpened(View drawerView) {
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            String state;
            switch(newState){
                case DrawerLayout.STATE_IDLE:
                    state = "STATE_IDLE";
                    break;
                case DrawerLayout.STATE_DRAGGING:
                    state = "STATE_DRAGGING";
                    break;
                case DrawerLayout.STATE_SETTLING:
                    state = "STATE_SETTLING";
                    break;
                default:
                    state = "unknown!";
            }
        }};
    private void initUI() {
        // welcom,pls_enter,Lead_details
      //  welcom = (AppCompatTextView) findViewById(R.id.welcom1);
      //  pls_enter = (AppCompatTextView) findViewById(R.id.pls_enter);
       Lead_details = (AppCompatTextView) findViewById(R.id.Lead_details);


        total_Lead_details = (AppCompatTextView) findViewById(R.id.total_Lead_details);
        Closed_count = (AppCompatTextView) findViewById(R.id.Closed_count);
        Declined_count = (AppCompatTextView) findViewById(R.id.Declined_count);
        Disbursed_count = (AppCompatTextView) findViewById(R.id.Disbursed_count);
        In_Progress_count = (AppCompatTextView) findViewById(R.id.In_Progress_count);
        document_statues = (AppCompatTextView) findViewById(R.id.document_statues);

        Doc_status = (AppCompatTextView) findViewById(R.id.Doc_status);
        In_Progress = (AppCompatTextView) findViewById(R.id.In_Progress);
        Disbursed = (AppCompatTextView) findViewById(R.id.Disbursed);
        Declined = (AppCompatTextView) findViewById(R.id.Declined);
        Closed = (AppCompatTextView) findViewById(R.id.Closed);
        Approved = (AppCompatTextView) findViewById(R.id.Approved);
        Approved_count = (AppCompatTextView) findViewById(R.id.Approved_count);

       // city_you = (AppCompatTextView) findViewById(R.id.city_you);


    }

    private void fonts() {
        //  Objs.a.EditTextStyle(mCon, editTextName);
        //  Objs.a.EditTextStyle(mCon, editTextMobile);
        //  Objs.a.EditTextStyle(mCon, pincode);
        // txt_bussiness,state_you,city_you;
        // Objs.a.OutfitNormalFontStyle(mCon,R.id.verif);
       // Objs.a.OutfitNormalFontStyle(mCon, R.id.welcom1);
       // Objs.a.OutfitNormalFontStyle(mCon, R.id.pls_enter);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Lead_details);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.txt_bank);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.txt_profile);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.total_Lead_details);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Closed_count);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Declined_count);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Disbursed_count);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.In_Progress_count);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.document_statues);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Doc_status);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.In_Progress);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Disbursed);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Declined);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Approved);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.Approved_count);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.txt_code);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.code);

        Objs.a.OutfitNormalFontStyle(mCon, R.id.txt_get_callback);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.help_and_support);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.logout2);

    }

    private void Account_Listings_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(mCon));
            Log.d("response b2b_userid", String.valueOf(J));

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

                            total_Lead_details.setText(total_lead_count);

                            document_statues.setText(document_statues1);
                            In_Progress_count.setText(In_Progress_count1);
                            Disbursed_count.setText(Disbursed_count1);
                            Declined_count.setText(Declined_count1);
                            Closed_count.setText(Closed_count1);
                            Approved_count.setText(Approved1);

                            if(!document_statues1.equals("0")){
                                CD_document.setEnabled(true);
                            }else{
                                CD_document.setEnabled(false);
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


                            if(!Declined_count1.equals("0")){
                                CD_declined.setEnabled(true);
                            }else{
                                CD_declined.setEnabled(false);
                            }

                            if(!Approved1.equals("0")){
                                CD_approved.setEnabled(true);
                            }else{
                                CD_approved.setEnabled(false);
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
