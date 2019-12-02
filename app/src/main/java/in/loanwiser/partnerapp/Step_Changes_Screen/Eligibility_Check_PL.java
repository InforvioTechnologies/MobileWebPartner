package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Eligibility_Check_PL extends SimpleActivity {

    AppCompatButton lead_viy_step2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_eligibility__check);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_eligibility__check);
        initTools(R.string.eligi_check);

        lead_viy_step2 = (AppCompatButton) findViewById(R.id.lead_viy_step2);

        lead_viy_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(Eligibility_Check_PL.this, Creadite_Report_Activity.class);
                startActivity(intent);*/
                Intent intent = new Intent(Eligibility_Check_PL.this, Credite_report_details.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Viability_Check_PL.class);
        finish();
        super.onBackPressed();
    }

}
