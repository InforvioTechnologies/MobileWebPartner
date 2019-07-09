package in.loanwiser.partnerapp.SMSRetrieverAPI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

import java.util.Objects;

public class MySMSBroadcastReceiver extends BroadcastReceiver {

    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent . getExtras ();
            Status status =(Status) extras . get (SmsRetriever.EXTRA_STATUS);
            switch(status.getStatusCode()) {
                case CommonStatusCodes . SUCCESS :
                    // Get SMS message contents
                    String message =(String)extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    // Extract one-time code from the message and complete verification
                    // by sending the code back to your server for SMS authenticity.
                    Log.d("MySMSBroadcastReceiver", "Retrieved sms code: " + message);
                    String str = message;
                    String a=    str.replace("<#>", "");
                    String a1=    a.replace("63Tec26epnY", "");
                    String a2 = a1.replaceAll("[^\\d.]", "");
                    String OTP =   a2.replace("\"", "");
                    //  VerifedOTP Sms = new VerifedOTP();
                    //   Sms.recivedSms(OTP);
                    if (mListener != null ) {
                    mListener.messageReceived(OTP);
                }
                    break;
                case CommonStatusCodes. TIMEOUT :
                    // Waiting for SMS timed out (5 minutes)
                    // Handle the error ...
                    break;
            }
        }
    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}

