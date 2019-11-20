package in.loanwiser.partnerapp.User_Account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.Push_Notification.MainActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.app.Config;

public class Welcome_Page extends AppCompatActivity implements Animation.AnimationListener {


    private Context mCon = this;
    Animation animFadein;
    private AppCompatTextView welcome, tenacy, slogan, slogan2;
    AppCompatTextView textview1,emp2,emp3;
    AppCompatImageView imageView;
    private AppCompatButton Bt_Register, Bt_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__page);

        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        // set animation listener
        textview1 =(AppCompatTextView) findViewById(R.id.welcome);
        emp2 =(AppCompatTextView) findViewById(R.id.emp2);
        emp3 =(AppCompatTextView) findViewById(R.id.emp3);
        imageView =(AppCompatImageView) findViewById(R.id.logo);

        animFadein.setAnimationListener(this);

        textview1.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);

        // start the animation
        textview1.startAnimation(animFadein);
        imageView.startAnimation(animFadein);

        initCode();

      /*AppSignatureHelper signatureHelper = new AppSignatureHelper(Welcome_Page.this);

        String appSignatures = String.valueOf(signatureHelper.getAppSignatures());
        Log.e("AppSign", appSignatures);
        Toast.makeText(getApplicationContext(),appSignatures,Toast.LENGTH_LONG).show();*/
        displayFirebaseRegId();
    }

    private void initCode() {
        initUI();
        clicks();
        fonts();
        try {
            PackageInfo info = getPackageManager().getPackageInfo("in.loanwiser.partnerapp",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String sign = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("MY KEY HASH:", sign);
                //  Toast.makeText(getApplicationContext(),"From the FBlogim "+ sign, Toast.LENGTH_LONG).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    private void initUI() {

        welcome = (AppCompatTextView) findViewById(R.id.welcome);
        emp2 = (AppCompatTextView) findViewById(R.id.emp2);
        Bt_Register = (AppCompatButton) findViewById(R.id.Bt_Register);
        Bt_Login = (AppCompatButton) findViewById(R.id.Bt_Login);

    }

    private void fonts() {
        // Objs.a.OutfitEditTextStyle(mCon,editTextOtp);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.welcome);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.emp2);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.emp3);
        //Objs.a.ButtonFontStyle(mCon, R.id.Bt_Register);
      // Objs.a.ButtonFontStyle(mCon, R.id.Bt_Login);
    }

    private void clicks() {
        findViewById(R.id.Bt_Register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getApplicationContext(),"Register", Toast.LENGTH_LONG).show();
              // Intent i = new Intent(Welcome_Page.this, Registration_2.class);
               Intent i = new Intent(Welcome_Page.this, Registration.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                finish();
            }
        });

        findViewById(R.id.Bt_Login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   Toast.makeText(getApplicationContext(),"Login", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Welcome_Page.this, LoginNew.class);
              ///  Intent i = new Intent(Welcome_Page.this, Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void displayFirebaseRegId() {


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        // Log and toast
                        //  String msg = getString(R.string.msg_token_fmt, token);
                        //  Log.d(TAG, msg);
                       // txtRegId.setText("Firebase Reg Id: " + token);
                        Log.e("TAG", "Firebase reg id: " + token);
                       // Toast.makeText(Welcome_Page.this, "Firebase Reg Id: " + token, Toast.LENGTH_SHORT).show();
                    }
                });
        // [END retrieve_current_token]

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e("TAG", "Firebase reg id: " + regId);

    }
}
