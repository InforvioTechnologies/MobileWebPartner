package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import in.loanwiser.partnerapp.R;

public class Step_Completion_Screen extends AppCompatActivity {

    Button proceed_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step__completion__screen);

        proceed_button=(Button)findViewById(R.id.proceed_button);

    }
}
