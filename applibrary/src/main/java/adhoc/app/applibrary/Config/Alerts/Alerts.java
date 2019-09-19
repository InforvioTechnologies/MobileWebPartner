package adhoc.app.applibrary.Config.Alerts;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Build;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.AppBaseActivity;
import adhoc.app.applibrary.Config.AppUtils.CustomTypefaceSpan;
import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.ServerConnection;
import adhoc.app.applibrary.R;


public class Alerts {
    public  JSONObject res = null;
    public static SimpleDateFormat DBformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static SimpleDateFormat DBformatDate = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat myformat = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat visibleformat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    public static SimpleDateFormat datetimevisibleformat = new SimpleDateFormat("ddMMyyyyhhmmssa");
    public static SimpleDateFormat UIdate = new SimpleDateFormat("MMM dd, yyyy");
    public static SimpleDateFormat UIday = new SimpleDateFormat("EEEE");
    public static SimpleDateFormat UITime = new SimpleDateFormat("hh:mm aa");
    public static SimpleDateFormat UIDateUITime = new SimpleDateFormat("MMM dd, yyyy hh:mm aa");
    public static String DBformatDateString = "yyyy-MM-dd";

    public static Boolean connectionStatus=null;

    //On Exit!!
    @SuppressWarnings("unchecked")
    public void ExitAlert(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(R.string.attention));
        builder.setIcon(context.getResources().getDrawable(R.drawable.ic_info_outline_black_24dp));
        builder.setMessage("Do you want to Exit..?");
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Objs.ac.FinishAll();
                ((Activity) context).finish();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        DialogStyle(context, alert);
    }
    @SuppressWarnings("unchecked")
    public void AttentionFinish(final Context context, final String msg) {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                builder.setTitle(context.getResources().getString(R.string.attention));
                builder.setIcon(context.getResources().getDrawable(R.drawable.ic_info_outline_black_24dp));
                builder.setMessage(msg);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ((Activity) context).finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                DialogStyle(context, alert);
            }
        });
    }
    @SuppressWarnings("unchecked")
    public void DialogStyle(Context context, AlertDialog alert) {
        Button BUTTON_NEGATIVE = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        BUTTON_NEGATIVE.setTextColor(context.getResources().getColor(R.color.colorAccent));
        BUTTON_NEGATIVE.setTransformationMethod(null);
        Typeface cancel_font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
        BUTTON_NEGATIVE.setTypeface(cancel_font);

        Button BUTTON_POSITIVE = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        BUTTON_POSITIVE.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        BUTTON_POSITIVE.setTransformationMethod(null);
        Typeface ok_font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
        BUTTON_POSITIVE.setTypeface(ok_font);

        TextView title = (TextView) alert.findViewById(R.id.alertTitle);
        title.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        Typeface title_font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
        title.setTypeface(title_font);

        TextView msg = (TextView) alert.findViewById(android.R.id.message);
        msg.setTextColor(context.getResources().getColor(R.color.colorGreenDark));
        Typeface msg_font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
        msg.setTypeface(msg_font);
    }

    //On Logoff!!

    @SuppressWarnings("unchecked")
    public void Attention(final Context context, final String msg) {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
                builder.setTitle(context.getResources().getString(R.string.attention));
                builder.setIcon(context.getResources().getDrawable(R.drawable.ic_info_outline_black_24dp));
                builder.setMessage(msg);
                builder.setPositiveButton("Ok", null);
                AlertDialog alert = builder.create();
                alert.show();
                DialogStyle(context, alert);
            }
        });
    }




    @SuppressWarnings("unchecked")
    public void showToast(final Context context, final String msg) {
        ((Activity) context).runOnUiThread(new Runnable() {
            public void run() {

                Toast toastObj = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                TextView v = (TextView) toastObj.getView().findViewById(android.R.id.message);
                if (v != null) {
                    v.setGravity(Gravity.CENTER);
                    v.setPadding(10, 5, 10, 5);
                    v.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    v.setTextSize(13);
                    Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "RobotoSlab-Light.ttf");
                    v.setTypeface(custom_font);
                }
                View view = toastObj.getView();
                // view.setBackgroundColor(context.getResources().getColor(R.color.appcolor));
                toastObj.show();
            }
        });
    }
    @SuppressWarnings("unchecked")
    public void showToastSimple(final Context context, final String msg) {
        Toast toastObj = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        TextView v = (TextView) toastObj.getView().findViewById(android.R.id.message);
        if (v != null) {
            v.setGravity(Gravity.CENTER);
            v.setPadding(10, 5, 10, 5);
            v.setTextColor(context.getResources().getColor(R.color.colorWhite));
            v.setTextSize(13);
            Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "RobotoSlab-Light.ttf");
            v.setTypeface(custom_font);
        }
        View view = toastObj.getView();
        // view.setBackgroundColor(context.getResources().getColor(R.color.appcolor));
        toastObj.show();
    }

    @SuppressWarnings("unchecked")
    public static String formattedDateFromString(SimpleDateFormat inputFormat, SimpleDateFormat outputFormat, String inputDate) {
//        if(inputFormat.equals("")){ // if inputFormat = "", set a default input format.
//            inputFormat = "yyyy-MM-dd hh:mm:ss";
//        }
//        if(outputFormat.equals("")){
//            outputFormat = "EEEE d 'de' MMMM 'del' yyyy"; // if inputFormat = "", set a default output format.
//        }
        Date parsed = null;
        String outputDate = "";

//        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
//        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(parsed);
        } catch (Exception e) {
            Log.e("formattedDateFromString", "Exception in formateDateFromstring(): " + e.getMessage());
        }
        return outputDate;

    }
    @SuppressWarnings("unchecked")
    public void anim(Context mCon) {
       // ((Activity) mCon).overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

    }
    @SuppressWarnings("unchecked")
    public void GoAnim(Context mCon) {
   //  ((Activity) mCon).overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @SuppressWarnings("unchecked")
    public void hideSoftKey(Context mCon) {
        // Check if no view has focus:
        View view = ((Activity) mCon).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mCon.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @SuppressWarnings("unchecked")
    private void requestFocus(Context mCon, View view) {
        if (view.requestFocus()) {
            ((Activity) mCon).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    @SuppressWarnings("unchecked")
    public void SnakBar(View view, Context mCon, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    @SuppressWarnings("unchecked")
    public  JSONArray remove(final int idx, final JSONArray from) {
        final List<JSONObject> objs = asList(from);
        objs.remove(idx);

        final JSONArray ja = new JSONArray();
        for (final JSONObject obj : objs) {
            ja.put(obj);
        }

        return ja;
    }
    @SuppressWarnings("unchecked")
    public static List<JSONObject> asList(final JSONArray ja) {
        final int len = ja.length();
        final ArrayList<JSONObject> result = new ArrayList<JSONObject>(len);
        for (int i = 0; i < len; i++) {
            final JSONObject obj = ja.optJSONObject(i);
            if (obj != null) {
                result.add(obj);
            }
        }
        return result;
    }

//    public SwipeRefreshLayout getRefreshLayout(AppCompatActivity activity) {
//        SwipeRefreshLayout  swipeRefreshLayout = (SwipeRefreshLayout) activity.findViewById(R.id.swipe_refresh_layout);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorGreenDark);
//        return swipeRefreshLayout;
//    }
@SuppressWarnings("unchecked")
    public void iniToolbar(final AppCompatActivity activity, int id) {
        String title = activity.getResources().getString(id);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
       // toolbar.setLogo(R.drawable.ic_menu_camera);
        activity.getSupportActionBar().setTitle(title);
        Objs.ac.ApplyFont(toolbar, activity);
        Objs.ac.BackButton(activity, toolbar);
        //activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setTransition(activity);

    }

    @SuppressWarnings("unchecked")
    public void iniToolbar(final AppCompatActivity activity, String title) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        Objs.ac.ApplyFont(toolbar, activity);
        Objs.ac.BackButton(activity, toolbar);
        //activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setTransition(activity);

    }
    @SuppressWarnings("unchecked")
    public void setTransition(AppCompatActivity activity){
        if (Build.VERSION.SDK_INT>=28){
            Transition exitTrans = new Explode();
            activity.getWindow().setExitTransition(exitTrans);

            Transition reenterTrans = new Slide();
            activity.getWindow().setReenterTransition(reenterTrans);
        }
    }
    @SuppressWarnings("unchecked")
    public void fontChange(Context mCon, NavigationView navigationView) {
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    Objs.a.applyFontToMenuItem(mCon, subMenuItem);
                }
            }
            Objs.a.applyFontToMenuItem(mCon, mi);
        }


    }
    @SuppressWarnings("unchecked")
    public void applyFontToMenuItem(Context mCon,MenuItem mi) {
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Roboto-Regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);

    }

    @SuppressWarnings("unchecked")
    public void iniToolbarSimple(final AppCompatActivity activity, int id) {
        String title = activity.getResources().getString(id);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        Objs.ac.ApplyFont(toolbar, activity);
        Objs.ac.BackButton(activity, toolbar);
        Objs.a.StatusBarColor(activity);
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }



    @SuppressWarnings("unchecked")
    public void StatusBarColor(Context mCon) {
        if (Build.VERSION.SDK_INT >= 28) {
            ((Activity) mCon).getWindow().setStatusBarColor(((Activity) mCon).getResources().getColor(R.color.transparent));
            //((Activity) mCon).getWindow().findViewById(R.id.app_bar).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
    @SuppressWarnings("unchecked")
    public void initNavView(AppCompatActivity activity) {
        Context mCon = activity;
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) activity);
        navigationView.getMenu().getItem(0).setChecked(true);
        fontChange(mCon,navigationView);
        View hView = navigationView.getHeaderView(0);
        setUpUserDetail(mCon,hView);
    }
    @SuppressWarnings("unchecked")
    public void setUpUserDetail(Context mCon, View hView) {
/*
        try {
            getUserData(mCon,hView);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        if (Pref.getUID(mCon)!=null){
            try {
                getUserData(mCon,hView);
            } catch (JSONException e) {
                Objs.a.setErrMsg(mCon,e.getMessage());
            } catch (UnsupportedEncodingException e) {
                Objs.a.setErrMsg(mCon,e.getMessage());
            }
        }else {
            final ImageView imageView_profile = (ImageView) hView.findViewById(R.id.imageProfile);
            imageView_profile.setImageDrawable(mCon.getResources().getDrawable(R.drawable.default_placeholder));
          //  final ProgressBar progressBar = (ProgressBar) hView.findViewById(R.id.progressBarMaterial);
            String url ="https://www.outfitkart.com/wp-content/uploads/2017/07/Outfit-Logo.jpg";
           // String url ="https://hdwallsource.com/img/2014/6/wallpapers-hd-8000-8331-hd-wallpapers.jpg";
            Objs.a.loadPicasso(mCon,url, imageView_profile,(ProgressBar)hView.findViewById(R.id.progressBarMaterial));
            final AppCompatTextView tvUserName = (AppCompatTextView) hView.findViewById(R.id.tvUserName);
            final AppCompatTextView tvUserEmail = (AppCompatTextView) hView.findViewById(R.id.tvUserEmail);
            tvUserName.setText(mCon.getResources().getString(R.string.app_name));
            tvUserEmail.setText(mCon.getResources().getString(R.string.app_web));
        }
    }

    /* private void initUI() {
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        appBarImage = (ImageView) findViewById(R.id.image_Product);
        String url ="https://shoppingdmart.files.wordpress.com/2011/10/banner1.jpg";
        Objs.a.loadPicasso(mCon,url, appBarImage,(ProgressBar)findViewById(R.id.progressBarMaterial));
        //layout_segment = (LinearLayout)findViewById(R.id.layout_segment);
    }
*/
    @SuppressWarnings("unchecked")
    private void getUserData(final Context mCon, final View hView) throws JSONException, UnsupportedEncodingException {

        final HashMap<String,String> map =new HashMap<>();

        map.put(Params.customerId ,Pref.getUID(mCon));

        Objs.a.loadUserDataOnly(mCon, Urls.CustomerDetaisl_GET+"mass?"+Urls.getPostQueryString(map), Request.Method.GET, null, new Alerts.AppLoadRequestListener<JSONObject>() {
            @Override
            public void onResult(JSONObject object) {
                try {
                    setUserData(mCon,object,hView);
                } catch (JSONException e) {
                    Objs.a.showToast(mCon,e.getMessage());
                }
                catch (NullPointerException e) {
                    Objs.a.showToast(mCon,e.getMessage());
                }
            }
        });
    }
    @SuppressWarnings("unchecked")
    private void setUserData(Context mCon,JSONObject J, View hView) throws JSONException,NullPointerException {

        try {
            JSONArray ja = J.getJSONArray(Params.customerDetails);
            for(int i=0;i<ja.length();i++){
                JSONObject jsonObject = ja.getJSONObject(i);
                final AppCompatTextView tvUserName = (AppCompatTextView) hView.findViewById(R.id.tvUserName);
                String a = jsonObject.getString(Params.firstName);
              //  String b = jsonObject.getString(Params.lastName);
             //   String c = a +" "+b;
             //   tvUserName.setText(c);

                final ImageView imageView_profile = (ImageView) hView.findViewById(R.id.imageProfile);
                final ProgressBar progressBar = (ProgressBar) hView.findViewById(R.id.progressBarMaterial);
                final AppCompatTextView tvUserEmail = (AppCompatTextView) hView.findViewById(R.id.tvUserEmail);
                tvUserEmail.setText(jsonObject.getString(Params.customerEmail));
              //  String  profile_url = jsonObject.getString(Params.fbImageUrl);
                // id = J.getString(Params.id);
              //  Objs.a.loadPicasso(mCon,profile_url,imageView_profile,progressBar);
            }
        } catch (JSONException e) {
            Objs.a.setErrMsg(mCon,e.getMessage());
        }
        /*J = new JSONObject(J.getString(Params.user_data));
        String profile_url = J.getString(Params.userimage);
        final ImageView imageView_profile = (ImageView) hView.findViewById(R.id.imageProfile);
        final ProgressBar progressBar = (ProgressBar) hView.findViewById(R.id.progressBarMaterial);
        final AppCompatTextView tvUserName = (AppCompatTextView) hView.findViewById(R.id.tvUserName);
        final AppCompatTextView tvUserEmail = (AppCompatTextView) hView.findViewById(R.id.tvUserEmail);
        tvUserName.setText(J.getString(Params.username));
        tvUserEmail.setText(J.getString(Params.email_id));
        Objs.a.loadPicasso(mCon,profile_url,imageView_profile,progressBar);*/
    }

    @SuppressWarnings("unchecked")
    public void onBackPressed(AppCompatActivity activity) {
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            activity.finish();
        }
    }
    @SuppressWarnings("unchecked")
    public void hideProgressBar(final Context mCon) {
        ((AppCompatActivity) mCon).findViewById(R.id.LyContainer).setVisibility(View.GONE);
        ((AppCompatActivity) mCon).findViewById(R.id.LyProcessing).setVisibility(View.GONE);
    }

    @SuppressWarnings("unchecked")
    public void showProgressBar(final Context mCon, String msg) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        ((AppCompatActivity) mCon).findViewById(R.id.LyError).setVisibility(View.GONE);
        ((AppCompatActivity) mCon).findViewById(R.id.LyNoNetwork).setVisibility(View.GONE);
        ((AppCompatActivity) mCon).findViewById(R.id.LyNoItems).setVisibility(View.GONE);
        ((AppCompatActivity) mCon).findViewById(R.id.LyProcessing).setVisibility(View.VISIBLE);

        ((AppCompatActivity) mCon).findViewById(R.id.LyContainer).setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT>=28){
            ((Activity)mCon).findViewById(R.id.progressBarMaterial).setVisibility(View.VISIBLE);
            ((Activity)mCon).findViewById(R.id.progressBarPreLollipop).setVisibility(View.GONE);
        }
        AppCompatTextView appCompatTextView = (AppCompatTextView) activity.findViewById(R.id.text_processing);
        appCompatTextView.setText(msg);
        HeaderFontStyle(mCon, appCompatTextView);
        hideSoftKey(mCon);
    }
    @SuppressWarnings("unchecked")
    public void ShowHideNoNetwork(Context mCon, Boolean show) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        ((AppCompatActivity) mCon).findViewById(R.id.LyContainer).setVisibility(show ? View.VISIBLE : View.GONE);
        ((Activity) mCon).findViewById(R.id.LyNoNetwork).setVisibility(show ? View.VISIBLE : View.GONE);
        AppCompatTextView appCompatTextView = (AppCompatTextView) activity.findViewById(R.id.NoNetwork_text);
        HeaderFontStyle(mCon, appCompatTextView);
        hideSoftKey(mCon);
    }
    @SuppressWarnings("unchecked")
    public void hideProgressBarFragment(final View view) {
        view.findViewById(R.id.LyContainer).setVisibility(View.GONE);
        view.findViewById(R.id.LyProcessing).setVisibility(View.GONE);
    }
    @SuppressWarnings("unchecked")
    public void showProgressBarFragment(View view,final Context mCon, String msg) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        view.findViewById(R.id.LyProcessing).setVisibility(View.VISIBLE);
        view.findViewById(R.id.LyContainer).setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT>=21){
            view.findViewById(R.id.progressBarMaterial).setVisibility(View.VISIBLE);
            view.findViewById(R.id.progressBarPreLollipop).setVisibility(View.GONE);
        }
        AppCompatTextView appCompatTextView = (AppCompatTextView) view.findViewById(R.id.text_processing);
        appCompatTextView.setText(msg);

        hideSoftKey(mCon);
    }

    @SuppressWarnings("unchecked")
    public void HideAll(Context mCon) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        ((Activity) mCon).findViewById(R.id.LyContainer).setVisibility(View.GONE);
        ((Activity) mCon).findViewById(R.id.LyNoNetwork).setVisibility(View.GONE);
        ((Activity) mCon).findViewById(R.id.LyProcessing).setVisibility(View.GONE);
        ((Activity) mCon).findViewById(R.id.LyNoItems).setVisibility(View.GONE);

        hideSoftKey(mCon);
    }
    @SuppressWarnings("unchecked")
    public void ShowHideNoItems(Context mCon, Boolean show) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        ((Activity) mCon).findViewById(R.id.LyContainer).setVisibility(View.VISIBLE);
        ((Activity) mCon).findViewById(R.id.LyProcessing).setVisibility( View.GONE);
        ((Activity) mCon).findViewById(R.id.LyNoNetwork).setVisibility( View.GONE);
        ((Activity) mCon).findViewById(R.id.LyError).setVisibility( View.GONE);
        ((Activity) mCon).findViewById(R.id.LyNoItems).setVisibility(show ? View.VISIBLE : View.GONE);
        AppCompatTextView appCompatTextView = (AppCompatTextView) activity.findViewById(R.id.txtNoItems);
        HeaderFontStyle(mCon, appCompatTextView);
        hideSoftKey(mCon);
    }
    @SuppressWarnings("unchecked")
    public void HeaderFontStyle(Context mCon, TextView tv) {
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "NotoSerif-Regular.ttf");
        tv.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void HelveticaFontStyle(Context mCon, TextView tv) {
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "helvetica.ttf");
        tv.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void HeaderFontStyle(Context mCon, int id) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        AppCompatTextView tv = (AppCompatTextView) activity.findViewById(id);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "OpenSans-Light.ttf");
        tv.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void NormalFontStyle(Context mCon, TextView tv) {
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
        tv.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void NewNormalFontStyle(Context mCon, TextView tv) {
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
        tv.setTypeface(font);
    }
   /* public void NewNormalFontStyle(Context mCon, TextView tv) {
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Marguaritas.ttf");
        tv.setTypeface(font);
    }*/
   @SuppressWarnings("unchecked")
    public void NewFontStyle(Context mCon, int id) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        AppCompatTextView tv = (AppCompatTextView) activity.findViewById(id);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Marguaritas.ttf");
        tv.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void NormalFontStyle(Context mCon, int id) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        AppCompatTextView tv = (AppCompatTextView) activity.findViewById(id);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
        tv.setTypeface(font);
    }

    @SuppressWarnings("unchecked")
    public void OutfitNormalFontStyle(Context mCon, int id) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        AppCompatTextView tv = (AppCompatTextView) activity.findViewById(id);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
        tv.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void SmallFontStyle(Context mCon, TextView tv) {
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");
        tv.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void SmallFontStyle(Context mCon, int id) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        AppCompatTextView tv = (AppCompatTextView) activity.findViewById(id);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "NotoSerif-Regular.ttf");
        tv.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void EditTextStyle(Context mCon, int id) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        AppCompatEditText tv = (AppCompatEditText) activity.findViewById(id);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "RobotoSlab-Light.ttf");
        tv.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void EditTextStyle(Context mCon, AppCompatEditText et) {
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "RobotoSlab-Light.ttf");
        et.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void OutfitEditTextStyle(Context mCon, AppCompatEditText et) {
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
        et.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public void OutfitREgEditTextStyle(Context mCon, AppCompatEditText et) {
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
        et.setTypeface(font);
    }

    @SuppressWarnings("unchecked")
    public void ButtonFontStyle(Context mCon, int btn_id) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        Button btn = (Button) activity.findViewById(btn_id);
        Typeface font = Typeface.createFromAsset(mCon.getAssets(), "RobotoSlab-Light.ttf");
        btn.setTypeface(font);
    }
    @SuppressWarnings("unchecked")
    public AppCompatEditText initEditText(AppCompatActivity activity, int id) {
        AppCompatEditText appCompatEditText = (AppCompatEditText) activity.findViewById(id);
        return appCompatEditText;
    }
    @SuppressWarnings("unchecked")
    public RecyclerView getRecyleview(AppCompatActivity activity) {
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        return recyclerView;
    }

    public RecyclerView getRecyleview_horizontal(AppCompatActivity activity) {
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);

        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        return recyclerView;
    }



    public boolean Connect(AppCompatActivity activity) {
        if (Objs.cd.IsCon(activity)) {
            Objs.a.showProgressBar(activity, "loading..");
            Objs.a.ShowHideNoNetwork(activity, false);
            return true;
        } else {
            Objs.a.hideProgressBar(activity);
            Objs.a.ShowHideNoNetwork(activity, true);
            return false;
        }

    }

    public String getBundle(Context mCon, String param) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        return activity.getIntent().getExtras().getString(param);
    }
    public Boolean getBundleBoolean(Context mCon, String param) {
        AppCompatActivity activity = (AppCompatActivity) mCon;
        Boolean aBoolean =false;
        try {
             aBoolean = activity.getIntent().getExtras().getBoolean(param);
        } catch (NullPointerException e) {
            return false;
        }
        catch (Exception e) {
            return false;
        }
        return aBoolean;
    }

   /* public void Notify(Context mCon, String msg, String info, int id) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(mCon);

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(msg)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(false)
                .setContentTitle(mCon.getString(R.string.app_name))
                .setContentText(msg)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                //.setContentIntent(contentIntent)
                .setContentInfo(info);
        NotificationManager notificationManager = (NotificationManager) mCon.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, b.build());

    }
    public void NotifyPendinActivity(Context mCon, String msg, String info, int id) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(mCon);
        Intent notificationIntent = new Intent(mCon, AppBaseActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(mCon,
                1, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(msg)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(false)
                .setContentTitle(mCon.getString(R.string.app_name))
                .setContentText(msg)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent)
                .setContentInfo(info);
        NotificationManager notificationManager = (NotificationManager) mCon.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, b.build());

    }

*/




    public Boolean onConnectionChanged(Boolean aBoolean) {
        connectionStatus=aBoolean;
        return aBoolean;
    }

    public void setRecycerView(AppCompatActivity activity, Object o) {
        RecyclerView recyclerView = Objs.a.getRecyleview(activity);
        recyclerView.setAdapter((RecyclerView.Adapter) o);
        activity.findViewById(R.id.LyContainer).setVisibility(View.GONE);
    }

    /*public void vibrate(AppCompatActivity activity) {
        Vibrator v = (Vibrator)activity.getSystemService(Context.VIBRATOR_SERVICE);
        // pass the number of millseconds fro which you want to vibrate the phone here we
        // have passed 2000 so phone will vibrate for 2 seconds.
        v.vibrate(300);
    }
*/
    public static String batteryLevel(Context context) {
        Intent intent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        int percent = (level * 100) / scale;
        return String.valueOf(percent) + "%";
    }

    public void setErrMsg(Context mCon, final String msg) {
        final AppCompatActivity activity = (AppCompatActivity) mCon;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.findViewById(R.id.LyContainer).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.LyError).setVisibility(View.VISIBLE);
                AppCompatTextView errText = (AppCompatTextView) activity.findViewById(R.id.err_msg);
                errText.setText(msg);
            }
        });
    }

    public String getTodayMyFormat() {
        Date date = Calendar.getInstance().getTime();
        String today = myformat.format(date);
        return today;
    }
    public String getDateTime() {
        Date date = Calendar.getInstance().getTime();
        String today = datetimevisibleformat.format(date);
        return today;
    }

    public String getDateDBFormat() {
        Date date = Calendar.getInstance().getTime();
        String today = DBformatDate.format(date);
        return today;
    }
    public String getDateUIFormat(String created) {
        try {
            Date date = Objs.a.DBformatDate.parse(created);
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            if (isToday(calendar.getTimeInMillis())){
                return "Today!";
            }
            if (isTomorrow(calendar.getTimeInMillis())){
                return "Tomorrow!";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date date = Calendar.getInstance().getTime();
        String today = UIdate.format(date);
        return today;
    }
    public String getDateUIDay(String created) {
        Calendar calendar=Calendar.getInstance();
        try {
             calendar.setTime(Objs.a.DBformatDate.parse(created));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return UIday.format(calendar.getTime());
    }
    public String getUIDate(String created) {
        Calendar calendar=Calendar.getInstance();
        try {
            calendar.setTime(Objs.a.DBformatDate.parse(created));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return UIdate.format(calendar.getTime());
    }
    public String getTodayUIFormat() {
        Date date = Calendar.getInstance().getTime();
        String today = UIdate.format(date);
        return today;
    }
    public String getFormattedDate(Context context, String created) {
        Calendar now = Calendar.getInstance();
        Calendar targetCalender = Calendar.getInstance();
        try {
            Date date = Objs.a.DBformatDate.parse(created);
            targetCalender.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (now.get(Calendar.YEAR) == targetCalender.get(Calendar.YEAR)
                && now.get(Calendar.DAY_OF_YEAR) == targetCalender.get(Calendar.DAY_OF_YEAR) ) {
            return "Today ";
        } else if (now.get(Calendar.YEAR) == targetCalender.get(Calendar.YEAR)
                && now.get(Calendar.DAY_OF_YEAR) - targetCalender.get(Calendar.DAY_OF_YEAR) == -1  ){
            return "Tomorrow ";
        } else {
            return UIdate.format(targetCalender.getTime());//DateFormat.format("MMMM dd, yyyy", targetCalender).toString();
        }
    }
    public static boolean isToday(long timestamp) {
        Calendar now = Calendar.getInstance();
        Calendar timeToCheck = Calendar.getInstance();
        timeToCheck.setTimeInMillis(timestamp);
        return (now.get(Calendar.YEAR) == timeToCheck.get(Calendar.YEAR)
                && now.get(Calendar.DAY_OF_YEAR) == timeToCheck.get(Calendar.DAY_OF_YEAR));
    }
    public static boolean isTomorrow(long timestamp) {
        Calendar now = Calendar.getInstance();
        Calendar timeToCheck = Calendar.getInstance();

        timeToCheck.setTimeInMillis(timestamp);
        timeToCheck.add(Calendar.DAY_OF_YEAR,-1);
        return (now.get(Calendar.YEAR) == timeToCheck.get(Calendar.YEAR)
                && now.get(Calendar.DAY_OF_YEAR) == timeToCheck.get(Calendar.DAY_OF_YEAR));
    }



    public void setStubId(AppCompatActivity activity, int id) {
        ViewStub stub = (ViewStub) activity.findViewById(R.id.layout_stub);
        stub.setLayoutResource(id);
        View inflated = stub.inflate();
    }
    public String capitalize(final String line) {
        if (line.length() > 0) {
            String str = line.toLowerCase();
            //  return  Character.toString(line.charAt(0)).toUpperCase()+str.substring(1);
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        } else {
            return line;
        }
    }

//    public FloatingActionButton getFab(AppCompatActivity activity, int id) {
//        ViewStub stub = (ViewStub) activity.findViewById(R.id.layout_stub_fab);
//        stub.setLayoutResource(id);
//        View inflated = stub.inflate();
//        FloatingActionButton floatingActionButton = (FloatingActionButton)inflated.findViewById(R.id.floatingActionButton);
//        return floatingActionButton;
//    }

  /*  public FloatingActionButton initFab(AppCompatActivity activity) {

        FloatingActionButton floatingActionButton = (FloatingActionButton)activity.findViewById(R.id.floatingActionButton);
        floatingActionButton.setVisibility(View.VISIBLE);
        return floatingActionButton;
    }*/

    public void HomeMenu(Context mCon) {
        Objs.ac.FinishOthers();
        Objs.ac.StartActivity(mCon,AppBaseActivity.class);
    }

    public Boolean getRes(Context mCon,String res) {
        try {
            JSONObject J= new JSONObject(res);
            Boolean result=J.getBoolean(Params.res);
            if (result)
                return result ;
            else {
                Objs.a.Attention(mCon, J.getString(Params.msg));
                return false;
            }
        } catch (JSONException e) {
            Objs.a.AttentionFinish(mCon,e.getMessage());
            return false;
        }
    }
    public String getParam(String param,String res) {
        try {
            JSONObject J= new JSONObject(res);
            return  J.getString(param);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void strickText(Context mCon,TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }




    public void loadPicasso(Context mCon, String url, final ImageView imageView, final ProgressBar progressBar){
        try {
            Picasso.with(mCon)
                    .load(url)
                    //.resize(600,600)
                    .placeholder(R.drawable.default_placeholder)
                    .error(R.drawable.failed)
                    .into(imageView,  new Callback() {
                        @Override
                        public void onSuccess() {
                            imageView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            imageView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    });
        } catch (IllegalArgumentException e) {
           Objs.a.setErrMsg(mCon,e.getMessage());
        }
    }

    public void loadDataArray(final Context mCon, String url, int method, JSONArray j, final AppLoadRequestListener appLoadRequestListener){
        Objs.a.showProgressBar(mCon,"Loading Please wait..");
        JsonArrayRequest req = new JsonArrayRequest(method,
                url,j,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Objs.a.hideProgressBar(mCon);
                        try {
                            appLoadRequestListener.onResult(response);
                        } catch (JSONException e) {
                            Objs.a.setErrMsg(mCon,e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Objs.a.hideProgressBar(mCon);
                ServerConnection.ShowError(mCon,error);

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };
        req.setRetryPolicy(new DefaultRetryPolicy(9000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, Objs.tag_json_arry);
    }

    public void loadData(final Context mCon, String url, int method, JSONObject j, final AppLoadRequestListener appLoadRequestListener){
        Objs.a.showProgressBar(mCon,"Loading Please wait..");
        JsonObjectRequest req = new JsonObjectRequest(method,
                url,j,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Objs.a.hideProgressBar(mCon);
                        try {
                            appLoadRequestListener.onResult(response);
                        } catch (JSONException e) {
                            Objs.a.setErrMsg(mCon,e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Objs.a.hideProgressBar(mCon);
                ServerConnection.ShowError(mCon,error);

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };
        req.setRetryPolicy(new DefaultRetryPolicy(9000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, Objs.tag_json_arry);
    }



    public interface AppLoadRequestListener<T>{
        public void onResult(T object) throws JSONException;

    }
    public void loadUserDataOnly(final Context mCon, String url, int method, JSONObject j, final AppLoadRequestListener appLoadRequestListener){
        //Objs.a.showProgressBar(mCon,"Connecting to server..");
        JsonObjectRequest req = new JsonObjectRequest(method,
                url,j,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Objs.a.hideProgressBar(mCon);
                        try {
                            appLoadRequestListener.onResult(response);
                        } catch (JSONException e) {
                            Objs.a.setErrMsg(mCon,e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Objs.a.hideProgressBar(mCon);
                Objs.a.showToast(mCon, "Error : cant get user details");

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };
        req.setRetryPolicy(new DefaultRetryPolicy(9000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, Objs.tag_json_arry);
    }


}
