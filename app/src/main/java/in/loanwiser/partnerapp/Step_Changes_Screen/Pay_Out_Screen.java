package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Pay_Out_Screen extends SimpleActivity {

    CardView disbursed_lead;
    private Context mCon = this;
    RelativeLayout filter,close;
    LinearLayout dis_detail,dis_detail1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_pay__out__screen);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_pay__out__screen);
        initTools(R.string.pay_out_details1);

        filter = (RelativeLayout) findViewById(R.id.filter);
        close = (RelativeLayout) findViewById(R.id.close);
        dis_detail = (LinearLayout) findViewById(R.id.dis_detail);
        dis_detail1 = (LinearLayout) findViewById(R.id.dis_detail1);
        disbursed_lead = (CardView) findViewById(R.id.disbursed_lead);
        disbursed_lead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mCon,Payout_Dsisbursed_Leadlist.class);
                startActivity(intent);
            }
        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis_detail.setVisibility(View.GONE);
                dis_detail1.setVisibility(View.VISIBLE);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis_detail.setVisibility(View.VISIBLE);
                dis_detail1.setVisibility(View.GONE);
            }
        });

    }
}
