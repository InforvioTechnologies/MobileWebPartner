package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Step_Completion_Screen extends SimpleActivity {

    Button proceed_button;
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
        proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Step_Completion_Screen.this,Lead_Crration_Activity.class);
                startActivity(intent);
            }
        });

    }
}
