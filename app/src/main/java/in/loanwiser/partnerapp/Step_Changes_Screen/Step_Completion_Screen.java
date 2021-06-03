package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thekhaeng.pushdownanim.PushDownAnim;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.My_Earnings.My_Earnings;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_STATIC_DP;

public class Step_Completion_Screen extends SimpleActivity {

    Button proceed_button;
    TextView noteid;
    LinearLayout earning_details_id;

    PermissionUtils permissionUtils;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

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
        permissionUtils = new PermissionUtils();
        earning_details_id = (LinearLayout) findViewById(R.id.earning_details_id);

        earning_details_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String viability_report_URL1 = "http://callcenter.propwiser.com/includes/DETAILED-PAYOUT-STRUCTURE.pdf";

                if (permissionUtils.checkPermission(Step_Completion_Screen.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                    if (viability_report_URL1.length() > 0) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(viability_report_URL1)));
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }

                }
            }
        });
       /* proceed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Step_Completion_Screen.this,Lead_Crration_Activity.class);
                startActivity(intent);
            }
        });*/


        PushDownAnim.setPushDownAnimTo( proceed_button )
                .setDurationPush( PushDownAnim.DEFAULT_PUSH_DURATION )
                // .setScale(MODE_SCALE,0.89f)
                .setScale(MODE_STATIC_DP,8)
                .setDurationRelease( PushDownAnim.DEFAULT_RELEASE_DURATION )
                .setInterpolatorPush( PushDownAnim.DEFAULT_INTERPOLATOR )
                .setInterpolatorRelease( PushDownAnim.DEFAULT_INTERPOLATOR )
                .setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick( View view ){
                        Intent intent=new Intent(Step_Completion_Screen.this,Lead_Crration_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                } );

    }
}
