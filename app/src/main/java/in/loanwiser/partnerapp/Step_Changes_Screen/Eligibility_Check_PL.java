package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.os.Bundle;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Eligibility_Check_PL extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_eligibility__check);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_eligibility__check);
        initTools(R.string.eligi_check);
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Viability_Check_PL.class);
        finish();
        super.onBackPressed();

    }
}
