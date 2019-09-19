package adhoc.app.applibrary.Config.ActivityFinisher;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;

import adhoc.app.applibrary.Config.AppUtils.Objs;


/**
 * Created by user on 1/19/2016.
 */
public class ActivityCloser {


    AppCompatActivity ActHome,MyProfile,KoMilKCoupon,NewOrder,LocateMe,ReqCoupon,MyOffers,ExceptionDate,ExceptionDays;

    public void setActHome(AppCompatActivity actHome) {
        ActHome = actHome;
    }

    public void setMyProfile(AppCompatActivity myProfile) {
        MyProfile = myProfile;
    }

    public void setKoMilKCoupon(AppCompatActivity koMilKCoupon) {
        KoMilKCoupon = koMilKCoupon;
    }

    public void setNewOrder(AppCompatActivity newOrder) {
        NewOrder = newOrder;
    }

    public void setLocateMe(AppCompatActivity locateMe) {
        LocateMe = locateMe;
    }

    public void setReqCoupon(AppCompatActivity reqCoupon) {
        ReqCoupon = reqCoupon;
    }

    public void setMyOffers(AppCompatActivity myOffers) {
        MyOffers = myOffers;
    }

    public void setExceptionDate(AppCompatActivity exceptionDate) {
        ExceptionDate = exceptionDate;
    }

    public void setExceptionDays(AppCompatActivity exceptionDays) {
        ExceptionDays = exceptionDays;
    }

    public void FinishAll(){
        if (ActHome!=null){
            ActHome.finish();
        }
        if (MyProfile!=null){
            MyProfile.finish();
        }
        if (KoMilKCoupon!=null){
            KoMilKCoupon.finish();
        }
        if (NewOrder!=null){
            NewOrder.finish();
        }
        if (LocateMe!=null){
            LocateMe.finish();
        }
        if (ReqCoupon!=null){
            ReqCoupon.finish();
        }
        if (MyOffers!=null){
            MyOffers.finish();
        }
        if (ExceptionDate!=null){
            ExceptionDate.finish();
        }
        if (ExceptionDays!=null){
            ExceptionDays.finish();
        }

    }

    public void FinishOthers(){
        if (MyProfile!=null){
            MyProfile.finish();
        }
        if (KoMilKCoupon!=null){
            KoMilKCoupon.finish();
        }
        if (NewOrder!=null){
            NewOrder.finish();
        }
        if (LocateMe!=null){
            LocateMe.finish();
        }
        if (ReqCoupon!=null){
            ReqCoupon.finish();
        }
        if (MyOffers!=null){
            MyOffers.finish();
        }
        if (ExceptionDate!=null){
            ExceptionDate.finish();
        }
        if (ExceptionDays!=null){
            ExceptionDays.finish();
        }
    }
    public  void StartActivity(Context activity,Class cls){
        try {
            Intent intent=new Intent(activity,cls);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            if (Build.VERSION.SDK_INT>=28){
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((AppCompatActivity)activity);
                ((AppCompatActivity)activity).startActivity(intent, options.toBundle());
            }else {
                activity.startActivity(intent);
                Objs.a.anim(activity);
            }
        } catch (Exception e) {
            Objs.a.setErrMsg(activity,e.getMessage());
        }


    }
    public  void StartActivityPutExtra(Context activity,Class cls,String ExtraKey1,String ExtraMsg1){
        try {
            Intent intent=new Intent(activity,cls);
            intent.putExtra(ExtraKey1,ExtraMsg1);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            activity.startActivity(intent);
            Objs.a.anim(activity);
        } catch (Exception e) {
            Objs.a.setErrMsg(activity,e.getMessage());
        }
    }

    public  void StartActivityPutExtra(Context activity,Class cls,String ExtraKey1,String ExtraMsg1,String ExtraKey2,String ExtraMsg2){
        Intent intent=new Intent(activity,cls);
        intent.putExtra(ExtraKey1,ExtraMsg1);
        intent.putExtra(ExtraKey2,ExtraMsg2);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
        Objs.a.anim(activity);
    }
    public  void StartActivityPutExtra(Context activity,Class cls,String ExtraKey1,String ExtraMsg1,String ExtraKey2,String ExtraMsg2,String ExtraKey3,String ExtraMsg3){
        Intent intent=new Intent(activity,cls);
        intent.putExtra(ExtraKey1,ExtraMsg1);
        intent.putExtra(ExtraKey2,ExtraMsg2);
        intent.putExtra(ExtraKey3,ExtraMsg3);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
        Objs.a.anim(activity);
    }
    public  void StartActivityPutExtra(Context activity,Class cls,String ExtraKey1,String ExtraMsg1,String ExtraKey2,String ExtraMsg2,String ExtraKey3,String ExtraMsg3,String ExtraKey4,String ExtraMsg4){
        Intent intent=new Intent(activity,cls);
        intent.putExtra(ExtraKey1,ExtraMsg1);
        intent.putExtra(ExtraKey2,ExtraMsg2);
        intent.putExtra(ExtraKey3,ExtraMsg3);
        intent.putExtra(ExtraKey4,ExtraMsg4);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
        Objs.a.anim(activity);
    }
    public  void StartActivityPutExtra(Context activity,Class cls,String ExtraKey1,String ExtraMsg1,String ExtraKey2,String ExtraMsg2,String ExtraKey3,String ExtraMsg3,String ExtraKey4,String ExtraMsg4,String ExtraKey5,String ExtraMsg5){
        Intent intent=new Intent(activity,cls);
        intent.putExtra(ExtraKey1,ExtraMsg1);
        intent.putExtra(ExtraKey2,ExtraMsg2);
        intent.putExtra(ExtraKey3,ExtraMsg3);
        intent.putExtra(ExtraKey4,ExtraMsg4);
        intent.putExtra(ExtraKey5,ExtraMsg5);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
        Objs.a.anim(activity);
    }
    public  void StartActivityPutExtra(Context activity,Class cls,String ExtraKey1,String ExtraMsg1,String ExtraKey2,String ExtraMsg2,String ExtraKey3,String ExtraMsg3,String ExtraKey4,String ExtraMsg4,String ExtraKey5,String ExtraMsg5,String ExtraKey6,String ExtraMsg6){
        Intent intent=new Intent(activity,cls);
        intent.putExtra(ExtraKey1,ExtraMsg1);
        intent.putExtra(ExtraKey2,ExtraMsg2);
        intent.putExtra(ExtraKey3,ExtraMsg3);
        intent.putExtra(ExtraKey4,ExtraMsg4);
        intent.putExtra(ExtraKey5,ExtraMsg5);
        intent.putExtra(ExtraKey6,ExtraMsg6);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
        Objs.a.anim(activity);
    }
    public  void StartActivityPutExtra(Context activity,Class cls,String ExtraKey1,String ExtraMsg1,String ExtraKey2,String ExtraMsg2,String ExtraKey3,String ExtraMsg3,String ExtraKey4,String ExtraMsg4,String ExtraKey5,String ExtraMsg5,String ExtraKey6,String ExtraMsg6,String ExtraKey7,String ExtraMsg7){
        Intent intent=new Intent(activity,cls);
        intent.putExtra(ExtraKey1,ExtraMsg1);
        intent.putExtra(ExtraKey2,ExtraMsg2);
        intent.putExtra(ExtraKey3,ExtraMsg3);
        intent.putExtra(ExtraKey4,ExtraMsg4);
        intent.putExtra(ExtraKey5,ExtraMsg5);
        intent.putExtra(ExtraKey6,ExtraMsg6);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivity(intent);
        Objs.a.anim(activity);
    }


    public  void StartActivityPutBooleanExtra(Context activity,Class cls,String ExtraKey1,Boolean aBoolean){
      try {
            Intent intent=new Intent(activity,cls);
            intent.putExtra(ExtraKey1,aBoolean);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            if (Build.VERSION.SDK_INT>=28){
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((AppCompatActivity)activity);
                ((AppCompatActivity)activity).startActivity(intent, options.toBundle());
            }else {
                activity.startActivity(intent);
                Objs.a.anim(activity);
            }
        } catch (Exception e) {
            Objs.a.setErrMsg(activity,e.getMessage());
        }
    }
    public  void StartActivityPutBooleanExtraString(Context activity,Class cls,String ExtraKey1,Boolean aBoolean,String ExtraKey2,String value){
        try {
            Intent intent=new Intent(activity,cls);
            intent.putExtra(ExtraKey1,aBoolean);
            intent.putExtra(ExtraKey2,value);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            if (Build.VERSION.SDK_INT>=21){
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((AppCompatActivity)activity);
                ((AppCompatActivity)activity).startActivity(intent, options.toBundle());
            }else {
                activity.startActivity(intent);
                Objs.a.anim(activity);
            }
        } catch (Exception e) {
            Objs.a.setErrMsg(activity,e.getMessage());
        }
    }


    public  void FinishActivity(Context mCon){
        ((Activity) mCon).finish();
        Objs.a.GoAnim(mCon);
    }

    public void BackButton(final Activity a,Toolbar toolbar) {

        ((AppCompatActivity)a).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)a). getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity)a).onBackPressed();
            }
        });


    }



    public static void applyFontForToolbarTitle(Activity context,Toolbar toolbar,String title){
        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);
            if(view instanceof TextView){
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.
                        createFromAsset(context.getAssets(),  "Fonts/DroidSerif-Regular.ttf");
                if(tv.getText().equals(context.getTitle())){
                    tv.setText(title);
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }
    }
    public static void setToolBarFont(Toolbar mToolBar, Context mCon, String title) {
        TextView titleTextView = null;
        try {
            Field f = mToolBar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(mToolBar);

            Typeface font = Typeface.createFromAsset(mCon.getAssets(),"Fonts/DroidSerif-Regular.ttf");
            titleTextView.setTypeface(font);
            titleTextView.setText(title);

        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }

    public static void ApplyFont(Toolbar mToolBar, Context mCon) {
        TextView titleTextView = null;
        try {
            Field f = mToolBar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(mToolBar);

            Typeface font = Typeface.createFromAsset(mCon.getAssets(),"Lato-Regular.ttf");
            titleTextView.setTypeface(font);


        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }


/*public static void ApplyFont(Toolbar mToolBar, Context mCon) {
        TextView titleTextView = null;
        try {
            Field f = mToolBar.getClass().getDeclaredField("mTitleTextView");
            f.setAccessible(true);
            titleTextView = (TextView) f.get(mToolBar);

            Typeface font = Typeface.createFromAsset(mCon.getAssets(),"RobotoSlab-Light.ttf");
            titleTextView.setTypeface(font);


        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }*/

}
