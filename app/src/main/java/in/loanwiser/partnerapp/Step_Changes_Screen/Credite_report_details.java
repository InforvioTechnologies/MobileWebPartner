package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Credite_report_details extends SimpleActivity {

    AppCompatButton cr_yes,cr_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_credite_report_details);
        initTools(R.string.credit_report1);

        cr_yes = (AppCompatButton) findViewById(R.id.cr_yes);
        cr_no = (AppCompatButton) findViewById(R.id.cr_no);

        cr_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(Credite_report_details.this, Creadite_Report_Activity.class);
                 startActivity(intent);
                 finish();
            }
        });

        cr_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Eligibility_Check_PL.class);
        finish();
        super.onBackPressed();
    }
}
