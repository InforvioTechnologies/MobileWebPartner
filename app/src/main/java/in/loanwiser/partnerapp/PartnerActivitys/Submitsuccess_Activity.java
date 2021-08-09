package in.loanwiser.partnerapp.PartnerActivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.loanwiser.Old_Partner.Home_Old;
import in.loanwiser.partnerapp.Documents.Applicant_Doc_Details_revamp;
import in.loanwiser.partnerapp.R;

public class Submitsuccess_Activity extends AppCompatActivity {


    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitsuccess_);
        done=findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Submitsuccess_Activity.this, Home_Old.class);
                startActivity(intent);
                finish();

            }
        });

    }
}