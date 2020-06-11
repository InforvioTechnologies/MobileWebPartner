package in.loanwiser.partnerapp.Payment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import in.loanwiser.partnerapp.R;

public class Bank_Statement_Activity extends AppCompatActivity {

    LinearLayout ly_proceedbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank__statement_);

        ly_proceedbtn = (LinearLayout) findViewById(R.id.ly_proceedbtn);

        Buttondisableui();
    }
    @SuppressLint("ResourceAsColor")
    public void Buttondisableui(){
        ly_proceedbtn.setEnabled(false);
        ly_proceedbtn.setAlpha((float) 0.4);


        /*    proceedbtn.getBackground().setColorFilter(ContextCompat.getColor(this, android.R.color.darker_gray), PorterDuff.Mode.MULTIPLY);
            proceedbtn.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            proceedlay.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            submitloanlay.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            savelaterlay.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);*/

    }
}
