package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Eligibility_HL extends SimpleActivity {

    LinearLayout self,former,salaried;
    String salary_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_eligibility__hl);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_eligibility__hl);
        initTools(R.string.eligi_check);

        salary_type = Pref.getSALARYTYPE(getApplicationContext());

        self =(LinearLayout) findViewById(R.id.self);
        former =(LinearLayout) findViewById(R.id.former);
        salaried =(LinearLayout) findViewById(R.id.salaried);

        if(salary_type.equals("2"))
        {
            self.setVisibility(View.VISIBLE);
            former.setVisibility(View.GONE);
            salaried.setVisibility(View.GONE);

        }else if(salary_type.equals("1"))
        {
            salaried.setVisibility(View.VISIBLE);
            self.setVisibility(View.GONE);
            former.setVisibility(View.GONE);

        }else
        {
            self.setVisibility(View.GONE);
            salaried.setVisibility(View.VISIBLE);
            former.setVisibility(View.GONE);
            salaried.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Viability_check_HL.class);
        finish();
        super.onBackPressed();

    }
}
