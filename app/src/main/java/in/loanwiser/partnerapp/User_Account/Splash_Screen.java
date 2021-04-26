package in.loanwiser.partnerapp.User_Account;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import in.loanwiser.partnerapp.BuildConfig;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.Partner_Statues.Statues_Dashboard_Nav;
import in.loanwiser.partnerapp.R;

public class Splash_Screen extends AppCompatActivity {

    private Context mCon = this;
    private FloatingActionButton fab;
    private static int SPLASH_TIME_OUT = 3000;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Splash_Screen.class.getSimpleName();
    private ProgressDialog pDialog;
    public static String PACKAGE_NAME;
    String version,VersionUpdate;
    int verCode;
    private ProgressBar spinner;
    AppCompatTextView emp,textview1,textView2,emp2,emp3;
    AppCompatTextView earntxt,typeloan;
    Animation animFadein;
    private final String prefKey = "checkedInstallReferrer";

    private final Executor backgroundExecutor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysplash_layout);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        spinner=(ProgressBar)findViewById(R.id.ProgressBar);
        spinner.setVisibility(View.GONE);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        checkInstallReferrer();
        initCode();

        PACKAGE_NAME = getApplicationContext().getPackageName();
        if (getIntent().getBooleanExtra("EXIT", false)) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
        }
        if(checkConnection()==true){
            //main_function();
            Update_checker();
        }else{
        }
        try{
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
            verCode = pInfo.versionCode;
        }
        catch(Exception e){
        }

        Log.e("Package Name",PACKAGE_NAME);

    }
    private void initCode() {
        initUI();
        fonts();
        ballScaleRippleProgressLoaderExample();
    }

    void checkInstallReferrer() {
        if (getPreferences(MODE_PRIVATE).getBoolean(prefKey, false)) {
            return;
        }

        InstallReferrerClient referrerClient = InstallReferrerClient.newBuilder(this).build();
        backgroundExecutor.execute(() -> getInstallReferrerFromClient(referrerClient));
    }

    void getInstallReferrerFromClient(InstallReferrerClient referrerClient) {

        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        ReferrerDetails response = null;
                        try {
                            response = referrerClient.getInstallReferrer();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                            return;
                        }
                        final String referrerUrl = response.getInstallReferrer();


                        Log.e("the referat url ",referrerUrl);
                        // TODO: If you're using GTM, call trackInstallReferrerforGTM instead.
                      //  trackInstallReferrer(referrerUrl);
                      //  trackInstallReferrerforGTM(referrerUrl);

                        // Only check this once.
                        getPreferences(MODE_PRIVATE).edit().putBoolean(prefKey, true).commit();

                        // End the connection
                        referrerClient.endConnection();

                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        // API not available on the current Play Store app.
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        // Connection couldn't be established.
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {

            }
        });
    }

    // Tracker for Classic GA (call this if you are using Classic GA only)
    private void trackInstallReferrer(final String referrerUrl) {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                CampaignTrackingReceiver receiver = new CampaignTrackingReceiver();
                Intent intent = new Intent("com.android.vending.INSTALL_REFERRER");
                intent.putExtra("referrer", referrerUrl);
                receiver.onReceive(getApplicationContext(), intent);
            }
        });
    }

    // Tracker for GTM + Classic GA (call this if you are using GTM + Classic GA only)
    private void trackInstallReferrerforGTM(final String referrerUrl) {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                InstallReferrerReceiver receiver = new InstallReferrerReceiver();
                Intent intent = new Intent("com.android.vending.INSTALL_REFERRER");
                intent.putExtra("referrer", referrerUrl);
                receiver.onReceive(getApplicationContext(), intent);
            }
        });
    }
    void ballScaleRippleProgressLoaderExample() {
        findViewById(R.id.material_design_ball_scale_ripple_loader).setVisibility(View.VISIBLE);
    }
    private void initUI() {
     /*   emp = (AppCompatTextView) findViewById(R.id.emp);
        emp2 = (AppCompatTextView) findViewById(R.id.emp2);
        emp3 = (AppCompatTextView) findViewById(R.id.emp3);*/
        earntxt = (AppCompatTextView) findViewById(R.id.earntxt);
        typeloan = (AppCompatTextView) findViewById(R.id.typeloan);
        //  grow = (AppCompatTextView) findViewById(R.id.grow);
    }

    private void fonts() {
   /*     Objs.a.OutfitNormalFontStyle(mCon, R.id.emp);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.emp2);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.emp3);*/
        Objs.a.OutfitNormalFontStyle(mCon, R.id.earntxt);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.typeloan);
        // Objs.a.OutfitNormalFontStyle(mCon, R.id.grow);
    }
    private boolean checkConnection() {
        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
            showSnack(true);
            return true;
        }
        else
        {
            showSnack(false);
            return false;
        }

    }
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {

        } else {
            message = "Sorry! Not connected to internet";
            color = Color.WHITE;
            Snackbar snackbar = Snackbar.make(findViewById(R.id.fab), message, Snackbar.LENGTH_INDEFINITE).setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkConnection()==true){
                       // main_function();
                         Update_checker();
                    }
                    else{
                    }
                }
            });
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();
        }
    }

    public void main_function(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // if (Pref.getUSERNAME(mCon)==null && Pref.getPASSWORD(mCon)==null) {
                if (Pref.getMobile(mCon)==null) {
                    Intent i = new Intent(Splash_Screen.this, Welcome_Page.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(i);
                    finish();
                }else {
                    // String mobile = Pref.getUID(mCon);
                  //  Objs.ac.StartActivity(mCon, DashBoard_new.class);
                    Intent intent = new Intent(Splash_Screen.this,DashBoard_new.class);
                    startActivity(intent);
                    finish();

                }

            }
        }, SPLASH_TIME_OUT);
    }


    private void Update_checker() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.package_name,PACKAGE_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Req", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.VERSION_CHECK_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("Req", String.valueOf(response));
                            if(response.getString(Params.status).equals("Ok")){
                                JSONObject object = response.getJSONObject(Params.result);
                                VersionUpdate = object.getString(Params.version_name);
                                String VersionName = BuildConfig.VERSION_NAME;
                                if (VersionUpdate.equals(VersionName)){
                                    main_function();
                                     //     Objs.a.showToast(mCon, "No Updated Avaliable");
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Splash_Screen.this);
                                    builder.setTitle("Our App got Update");
                                    builder.setIcon(R.mipmap.ic_partner);
                                    builder.setCancelable(false);
                                    builder.setMessage("New version available, select update to update our app")
                                            .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    final String appName = getPackageName();

                                                    try {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appName)));
                                                    } catch (android.content.ActivityNotFoundException anfe) {
                                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appName)));
                                                    }
                                                    finish();
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                    Objs.a.DialogStyle(mCon, alert);


                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d(TAG, error.getMessage());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
               // Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                Log.e("Error", error.getMessage());

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsonObjReq.setRetryPolicy(policy);

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }
}
