package in.loanwiser.partnerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import in.loanwiser.partnerapp.User_Account.MobileNoUpdated;

public class IncomingSmsUpdate extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {

        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null)
            {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj .length; i++)
                {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[])                                                                                                    pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber ;
                    String message = currentMessage .getDisplayMessageBody();
                    try
                    {
                        if (senderNum .contains("OTPSMS"))
                        {
                            //Toast.makeText(context, "OTP Recevied please wait...", Toast.LENGTH_LONG).show();
                           // MobileNoUpdated Sms = new MobileNoUpdated();
                           // Sms.recivedSmsUpdate(message);
                        }
                    }

                    catch(Exception e){}

                }
            }

        } catch (Exception e)
        {

        }
    }

}