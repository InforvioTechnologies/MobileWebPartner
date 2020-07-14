package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Step_Completion_Screen extends SimpleActivity {

    Button proceed_button;
    TextView noteid;
    LinearLayout earning_details_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_step_completion);
        setContentView(R.layout.activity_simple);
        //Loantype = getIntent().getStringExtra("EXTRA_SESSION_ID");
        // Log.d("The extration data",Loantype);
        Objs.a.setStubId(this, R.layout.activity_step_completion);
        initTools(R.string.listapp2);
        proceed_button=(Button)findViewById(R.id.proceed_button);

        noteid=(TextView) findViewById(R.id.noteid);

        earning_details_id = (LinearLayout) findViewById(R.id.earning_details_id);

        earning_details_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Step_Completion_Screen.this, Pay_Out_Screen.class);
                startActivity(intent);
            }
        });
        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Step_Completion_Screen.this,Lead_Crration_Activity.class);
                startActivity(intent);
            }
        });

    }
}
