package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Eligibility_check_LAP extends SimpleActivity {

    LinearLayout self,former,individual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_viability_check__lap);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viability_check__lap);
        initTools(R.string.eligi_check);


        String Lontype = Pref.getEMPLOYMENT(getApplicationContext());

        self =(LinearLayout) findViewById(R.id.self);
        former =(LinearLayout) findViewById(R.id.former);
        individual =(LinearLayout) findViewById(R.id.individual);

        if(Lontype.equals("2"))
        {
            self.setVisibility(View.VISIBLE);
            former.setVisibility(View.GONE);
            individual.setVisibility(View.GONE);
        }else
        {
            self.setVisibility(View.GONE);
            individual.setVisibility(View.GONE);
        }
    }


    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Viability_check_HL.class);
        finish();
        super.onBackPressed();

    }
}
