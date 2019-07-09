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

public class View_More1 extends SimpleActivity {

    AppCompatTextView title,sub_id,sub_id1,sub_id2,sub_id3,sub_id4,sub_id5;
    private AppCompatButton apply_now2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_view__more1);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_view__more1);
        initTools(R.string.lap);
        title = (AppCompatTextView) findViewById(R.id.title);
        sub_id = (AppCompatTextView) findViewById(R.id.sub_id);
        sub_id1 = (AppCompatTextView) findViewById(R.id.sub_id1);
        sub_id3 = (AppCompatTextView) findViewById(R.id.sub_id3);
        sub_id4 = (AppCompatTextView) findViewById(R.id.sub_id4);
        sub_id5 = (AppCompatTextView) findViewById(R.id.sub_id5);

        apply_now2 = (AppCompatButton) findViewById(R.id.apply_now2);

        apply_now2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Loantype = "1";
                String Loantype_name = "Loan Against Property";
                String sub_categoryid = "2";
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypename(mCon,Loantype_name);
                Intent intent = new Intent(View_More1.this, MainActivity_Add_Lead.class);
                startActivity(intent);
            }
        });

        fonts();
    }

    private void fonts() {
        Objs.a.NormalFontStyle(mCon, R.id.title);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id1);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id3);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id4);
        Objs.a.NormalFontStyle(mCon, R.id.sub_id5);

    }
}
