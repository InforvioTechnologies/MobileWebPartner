package in.loanwiser.partnerapp.Slider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.PartnerActivitys.MainActivity_Add_Lead;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class View_More extends SimpleActivity {

    private AppCompatButton apply_now1;
    private AppCompatTextView title,sub_id,sub_id1,sub_id3,sub_id4,sub_id5,sub_id6,sub_id7,sub_id8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_view__more);

        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_view__more);
        initTools(R.string.homeLoan);

        //title,sub_id,sub_id1,sub_id3,sub_id4,sub_id5,sub_id6,sub_id7,sub_id8;
        title = (AppCompatTextView)findViewById(R.id.title);
        sub_id = (AppCompatTextView)findViewById(R.id.sub_id);
        sub_id1 = (AppCompatTextView)findViewById(R.id.sub_id1);
        sub_id3 = (AppCompatTextView)findViewById(R.id.sub_id3);
        sub_id4 = (AppCompatTextView)findViewById(R.id.sub_id4);
        sub_id5 = (AppCompatTextView)findViewById(R.id.sub_id5);
        sub_id6 = (AppCompatTextView)findViewById(R.id.sub_id6);
        sub_id7 = (AppCompatTextView)findViewById(R.id.sub_id7);
        sub_id8 = (AppCompatTextView)findViewById(R.id.sub_id8);

        Objs.a.NormalFontStyle(mCon, R.id.title);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id1);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id3);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id4);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id5);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id6);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id7);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id8);

        apply_now1 = (AppCompatButton) findViewById(R.id.apply_now1);

        apply_now1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Loantype = "1";
                String Loantype_name = "Home Loan";
                String sub_categoryid = "1";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Pref.putLoanTypename(mCon,Loantype_name);

                Intent intent = new Intent(View_More.this, MainActivity_Add_Lead.class);
                startActivity(intent);
            }
        });

    }
}
