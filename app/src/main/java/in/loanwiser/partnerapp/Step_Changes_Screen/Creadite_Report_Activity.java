package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Creadite_Report_Activity extends SimpleActivity {


    AppCompatButton credit_det_cap_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_creadite__report_);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_creadite__report_);
        initTools(R.string.credit_report);

        credit_det_cap_button = (AppCompatButton) findViewById(R.id.credit_det_cap_button);

    }
    private void Payment_Option()
    {
        Intent intent = new Intent(Creadite_Report_Activity.this, Payment_Details_Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Credite_report_details.class);
        finish();
        super.onBackPressed();
    }
}
