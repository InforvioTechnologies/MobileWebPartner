package in.loanwiser.partnerapp.Slider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.PartnerActivitys.MainActivity_Vehicle_Add_Lead1;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Vehicle_Loan extends SimpleActivity {

    private AppCompatButton apply_now5;
    private AppCompatTextView title,sub_id,sub_id1,sub_id3,sub_id4,sub_id5,sub_id6,sub_id7,sub_id8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      // setContentView(R.layout.activity_vehicle__loan);

        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_vehicle__loan);
        initTools(R.string.vehicleloan);
        title = (AppCompatTextView)findViewById(R.id.title);
        sub_id = (AppCompatTextView)findViewById(R.id.sub_id);
        sub_id1 = (AppCompatTextView)findViewById(R.id.sub_id1);
        sub_id3 = (AppCompatTextView)findViewById(R.id.sub_id3);
        sub_id4 = (AppCompatTextView)findViewById(R.id.sub_id4);
        sub_id5 = (AppCompatTextView)findViewById(R.id.sub_id5);
        sub_id6 = (AppCompatTextView)findViewById(R.id.sub_id6);
        //   sub_id7 = (AppCompatTextView)findViewById(R.id.sub_id7);
        //   sub_id8 = (AppCompatTextView)findViewById(R.id.sub_id8);

        Objs.a.NormalFontStyle(mCon, R.id.title);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id1);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id3);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id4);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id5);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id6);

        apply_now5 = (AppCompatButton) findViewById(R.id.apply_now5);

        apply_now5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Loantype = "3";
                String sub_categoryid = "5";
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Pref.putLoanType(mCon,Loantype);
                Intent intent = new Intent(Vehicle_Loan.this, MainActivity_Vehicle_Add_Lead1.class);
                startActivity(intent);
            }
        });

    }
}
