package in.loanwiser.partnerapp.Partner_Statues;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

import org.json.JSONArray;
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
import in.loanwiser.partnerapp.Infinite_Scrollview.Lead_item;
import in.loanwiser.partnerapp.My_Earnings.My_Earnings;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.Partner_Statues.ui.gallery.GalleryFragment;
import in.loanwiser.partnerapp.Partner_Statues.ui.home.HomeFragment;
import in.loanwiser.partnerapp.Push_Notification.Push_Notification_List;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Pay_Out_Screen;
import in.loanwiser.partnerapp.Step_Changes_Screen.Step_Completion_Screen;
import in.loanwiser.partnerapp.User_Account.BankDetails;
import in.loanwiser.partnerapp.User_Account.ProfileSettings;
import in.loanwiser.partnerapp.User_Account.Welcome_Page;

import static adhoc.app.applibrary.Config.AppUtils.Objs.a;

public class DashBoard_new extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener,
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private AppBarConfiguration mAppBarConfiguration;
    private View navHeader;
    private ImageView imageView_profile,nav_header_imageView;
    BottomNavigationView navView_bottom;
    private AlertDialog progressDialog;
    SharedPreferences pref;
    RelativeLayout relative_layout_multiple;

    TextView textview,nav_header_textView,nav_header_mobile_no;

    SharedPreferences.Editor editor;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    PermissionUtils permissionUtils;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_new);

        progressDialog = new SpotsDialog(this, R.style.Custom);

        navView_bottom =  findViewById(R.id.nav_view1);
        Toolbar toolbar = findViewById(R.id.toolbar);

         textview = findViewById(R.id.hotlist_hot);
        permissionUtils = new PermissionUtils();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nav_header_textView = headerView.findViewById(R.id.nav_header_textView);
        nav_header_mobile_no = headerView.findViewById(R.id.nav_header_mobile_no);

        setSupportActionBar(toolbar);


        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
            // do your stuff..
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DashBoard_new.this,Applicant_Details_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        Account_Listings_Details1();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
      //  NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navHeader = navigationView.getHeaderView(0);
        imageView_profile = (ImageView) navHeader.findViewById(R.id.imageProfile);

        relative_layout_multiple = (RelativeLayout) findViewById(R.id.relative_layout_multiple);
        relative_layout_multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DashBoard_new.this, Push_Notification_List.class);
                startActivity(intent);
            }
        });

        nav_header_imageView = (ImageView) navHeader.findViewById(R.id.nav_header_imageView);
        String url = "https://cdn5.vectorstock.com/i/1000x1000/77/14/businessman-flat-style-icon-vector-7497714.jpg";
        String url1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcT2h3JvD2aa9HobUrXyYEWUdQEZG6A-VMVHWvEDL838HyFVN3sE&usqp=CAU";
     /*   Glide.with(this).load(url)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView_profile);*/

       loadFragment(new ActivityFragment());

        Glide.with(this).load(url)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(nav_header_imageView);

      /*  Glide.with(this).load(R.drawable.ic_person)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(nav_header_imageView);*/

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

        Account_Listings_Details();


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

        }else if (id == R.id.nav_logout) {
            ExitAlert(DashBoard_new.this);

        } else if (id == R.id.my_lead) {

            Intent intent = new Intent(DashBoard_new.this,Dashboard_Activity.class);
            startActivity(intent);
        }else if (id == R.id.my_earnings) {

            Intent intent = new Intent(DashBoard_new.this, My_Earnings.class);
            startActivity(intent);
            //Pay_Out_Screen
        }else if (id == R.id.pay_structure) {

            String viability_report_URL1 = "https://callcenter.loanwiser.in/includes/DETAILED-PAYOUT-STRUCTURE.pdf";

            View view = null;
            if (permissionUtils.checkPermission(DashBoard_new.this, STORAGE_PERMISSION_REQUEST_CODE,  view)) {
                if (viability_report_URL1.length() > 0) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL1)));
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
            }
            //Pay_Out_Screen
        } else if(id == R.id.nav_item_six){
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            String mobileno="6369626669";
            callIntent.setData(Uri.parse("tel:" +mobileno));
            try {
                startActivity(callIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(DashBoard_new.this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
            }
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
                editor.remove("b2b_uer_id");
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
   public boolean checkPermissionREAD_EXTERNAL_STORAGE(
           final Context context) {
       int currentAPIVersion = Build.VERSION.SDK_INT;
       if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
           if (ContextCompat.checkSelfPermission(context,
                   Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
               if (ActivityCompat.shouldShowRequestPermissionRationale(
                       (Activity) context,
                       Manifest.permission.READ_EXTERNAL_STORAGE)) {
                   showDialog("External storage", context,
                           Manifest.permission.READ_EXTERNAL_STORAGE);

               } else {
                   ActivityCompat
                           .requestPermissions(
                                   (Activity) context,
                                   new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                   MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
               }
               return false;
           } else {
               return true;
           }

       } else {
           return true;
       }
   }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                   /* binding.testShare.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Glide.with(getApplicationContext())
                                    .load(image_url)
                                    .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                                            Intent intent = new Intent(Intent.ACTION_SEND);
                                            intent.putExtra(Intent.EXTRA_TEXT, "Hey view/download this image");
                                            String path = MediaStore.Images.Media.insertImage(getContentResolver(), resource, "", null);
                                            Log.i("quoteswahttodo", "is onresoursereddy" + path);
                                            Uri screenshotUri = Uri.parse(path);
                                            Log.i("quoteswahttodo", "is onresoursereddy" + screenshotUri);
                                            intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                            intent.setType("image/*");
                                            // intent.setPackage("com.whatsapp");
                                            try{
                                                startActivity(Intent.createChooser(intent, "Share image via..."));

                                            } catch (Exception e) {
                                                Toast.makeText(DashBoard_new.this, "It seem like Whatsapp is not been installed", Toast.LENGTH_SHORT).show();
                                                e.printStackTrace();
                                            }

                                        }
                                        @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                            Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                            super.onLoadFailed(e, errorDrawable);
                                        }

                                        @Override public void onLoadStarted(Drawable placeholder) {
                                            Toast.makeText(getApplicationContext(), "Starting", Toast.LENGTH_SHORT).show();
                                        }


                                    });


                        }
                    });*/



                } else {
                    Toast.makeText(DashBoard_new.this, "GET_ACCOUNTS Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }
    private void Account_Listings_Details() {

        final JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(getApplicationContext()));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("coins request flag", String.valueOf(J));

            progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GET_FLAG_WALLET, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("coins response flag", String.valueOf(response));

                        try {
                            String string = response.getString("status");

                            if(string.contains("success"))
                            {
                                progressDialog.dismiss();
                               JSONObject jsonObject1 = response.getJSONObject("data");

                               String initiate_coins_transact = jsonObject1.getString("initiate_coins_transact");
                               if(initiate_coins_transact.equals("0"))
                               {
                                   Intiat_Coin_Transaction();
                               }else
                               {

                               }

                            }else
                            {
                                progressDialog.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"getFlag wallet", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        int x=2;// retry count
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                x, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void Account_Listings_Details1() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Profile Page" , String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PROFILE_DETAILS_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Profile Page" , String.valueOf(response));
                        try {
                            if(response.getString("notify_status").equals("success")) {

                                JSONObject jobj = response.getJSONObject(Params.response);
                                Log.e("Profile Page" , String.valueOf(jobj));

                                String notification_count = jobj.getString("notification_count");
                                String contact_person = jobj.getString("contact_person");
                                String mobile_no = jobj.getString("mobile_no");


                                 textview.setText(notification_count);
                                nav_header_textView.setText(contact_person);
                                nav_header_mobile_no.setText(mobile_no);
                                // pan.setText(jobj.getString(Params.pan_no));
                            }else {
                                textview.setVisibility(View.GONE);
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

    private void Intiat_Coin_Transaction() {

        final JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(getApplicationContext()));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("coins request", String.valueOf(J));

        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.walletreg, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("coin Api Call", String.valueOf(response));

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),"coin request", Toast.LENGTH_SHORT).show();
              Log.e("the Error,",error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        int x=2;// retry count
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 48,
                x, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}


