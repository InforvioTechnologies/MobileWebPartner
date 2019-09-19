package in.loanwiser.partnerapp.Slider;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import android.view.View;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.PartnerActivitys.MainActivity_Add_Lead1;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Business_Loan extends SimpleActivity {

    private AppCompatButton apply_now3;
    private AppCompatTextView title,sub_id,sub_id1,sub_id3,sub_id4,sub_id5,sub_id6,sub_id7,sub_id8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_business__loan);

        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_business__loan);
        initTools(R.string.bl);
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

        apply_now3 = (AppCompatButton) findViewById(R.id.apply_now3);

        apply_now3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Loantype = "2";
                String sub_categoryid = "4";
                String Loantype_name = "Business Loan [Unsecured]";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypename(mCon,Loantype_name);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Intent intent = new Intent(Business_Loan.this, MainActivity_Add_Lead1.class);
                startActivity(intent);
            }
        });

    }
}
