package in.loanwiser.partnerapp.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Creadite_Report_Activity;

public class Payment_Sucess_Screen extends AppCompatActivity {

    Button payment_button;
    LinearLayout save_latter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment__sucess__screen);


        payment_button = findViewById(R.id.payment_button);

        save_latter = findViewById(R.id.save_latter);

        payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Payment_Sucess_Screen.this, Creadite_Report_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        save_latter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment_Sucess_Screen.this, Dashboard_Activity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
