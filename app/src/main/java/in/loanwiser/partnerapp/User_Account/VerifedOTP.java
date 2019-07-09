package in.loanwiser.partnerapp.User_Account;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SMSRetrieverAPI.MySMSBroadcastReceiver;
import in.loanwiser.partnerapp.SMSRetrieverAPI.SmsListener;

public class VerifedOTP extends AppCompatActivity {

    public static final String OTP_REGEX = "[0-9]{1,5}";
    private Context mCon = this;
    private Button submitBtn;
    private Button cancelBtn;
    private EditText Ed_OTP;
    // private TextView test;
    String OTP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifed_otp);

        Ed_OTP = (EditText) findViewById(R.id.Ed_OTP1);
        // test = (TextView) findViewById(R.id.test1);


        submitBtn = (Button) findViewById(R.id.BT_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.equals(submitBtn)) {
                    String fieldText = Ed_OTP.getText().toString();
                    Log.e("fieldEDT",fieldText);
                }
            }
        });

        smsReceiver();

    }


    private void smsReceiver(){
        MySMSBroadcastReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                //From the received text string you may do string operations to get the required OTP
                //It depends on your SMS format
                Log.e("Message",messageText);
                // If your OTP is six digits number, you may use the below code
                Pattern pattern = Pattern.compile(OTP_REGEX);
                Matcher matcher = pattern.matcher(messageText);
                String otp = null;

                while (matcher.find()) {
                    otp = matcher.group();
                }

                if(otp != null && Ed_OTP != null) {
                    Ed_OTP.setText(otp);
                }
            }
        });
    }


}
