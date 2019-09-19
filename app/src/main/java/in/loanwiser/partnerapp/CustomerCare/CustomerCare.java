package in.loanwiser.partnerapp.CustomerCare;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import android.view.View;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

@SuppressLint("Registered")
public class CustomerCare extends SimpleActivity {

private Context mCon = this;
private AppCompatTextView call_coimbatore,call_rajasthan,call_ap,call_telangana;
private CardView CD_call_coimbatore,CD_call_rajasthan,CD_call_ap,CD_call_telangana;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
   // setContentView(R.layout.activity_customer_care);

    setContentView(R.layout.activity_simple);
    Objs.a.setStubId(this, R.layout.activity_customer_care);
    initTools(R.string.app_call);

    initCode();

}

private void initCode() {
    initUI();
    fonts();
    clicks();
}

private void clicks() {
    findViewById(R.id.CD_call_coimbatore).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(ContextCompat.checkSelfPermission(
                    mCon,android.Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mCon, new
                        String[]{android.Manifest.permission.CALL_PHONE}, 0);
            } else {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "6369626669")));
            }
        }
    });

    findViewById(R.id.CD_call_rajasthan).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(ContextCompat.checkSelfPermission(
                    mCon,android.Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mCon, new
                        String[]{android.Manifest.permission.CALL_PHONE}, 0);
            } else {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0141-4284102")));
            }
        }
    });

    findViewById(R.id.CD_call_ap).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(ContextCompat.checkSelfPermission(
                    mCon,android.Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mCon, new
                        String[]{android.Manifest.permission.CALL_PHONE}, 0);
            } else {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "040 - 48501916")));
            }
        }
    });

    findViewById(R.id.CD_call_telangana).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(ContextCompat.checkSelfPermission(
                    mCon,android.Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mCon, new
                        String[]{android.Manifest.permission.CALL_PHONE}, 0);
            } else {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "040 - 48501916")));
            }
        }
    });
}

private void initUI() {
    //call_coimbatore,call_rajasthan,call_ap,call_telangana

    call_coimbatore = (AppCompatTextView)findViewById(R.id.call_coimbatore);
    call_rajasthan = (AppCompatTextView)findViewById(R.id.call_rajasthan);
    call_ap = (AppCompatTextView)findViewById(R.id.call_ap);
    call_telangana = (AppCompatTextView)findViewById(R.id.call_telangana);

    CD_call_coimbatore = (CardView)findViewById(R.id.CD_call_coimbatore);
    CD_call_rajasthan = (CardView)findViewById(R.id.CD_call_rajasthan);
    CD_call_ap = (CardView)findViewById(R.id.CD_call_ap);
    CD_call_telangana = (CardView)findViewById(R.id.CD_call_telangana);

}

private void fonts() {
    Objs.a.OutfitNormalFontStyle(mCon, R.id.call_coimbatore);
    Objs.a.OutfitNormalFontStyle(mCon, R.id.call_rajasthan);
    Objs.a.OutfitNormalFontStyle(mCon, R.id.call_ap);
    Objs.a.OutfitNormalFontStyle(mCon, R.id.call_telangana);
}


}
