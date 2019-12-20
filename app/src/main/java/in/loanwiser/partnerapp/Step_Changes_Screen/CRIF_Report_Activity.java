package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class CRIF_Report_Activity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crif__report_);

      /*  setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_eligibility__bl);
        initTools(R.string.eligi_check);*/
    }
}
