package in.loanwiser.partnerapp.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Creadite_Report_Activity;

public class Payment_Sucess_Screen extends AppCompatActivity {

    Button payment_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment__sucess__screen);


        payment_button = findViewById(R.id.payment_button);

        payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Payment_Sucess_Screen.this, Creadite_Report_Activity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
