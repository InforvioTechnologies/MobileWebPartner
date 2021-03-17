package in.loanwiser.partnerapp.BankStamentUpload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class BankStatement extends SimpleActivity {

    Button view_analysisbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_bank_statement);
        initTools(R.string.bank_statment);

        view_analysisbut=findViewById(R.id.view_analysisbut);

        view_analysisbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i= new Intent(BankStatement.this,BankAnalysis.class);
              startActivity(i);
            }
        });


    }
}