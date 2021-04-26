package in.loanwiser.partnerapp.User_Account;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.github.mikephil.charting.utils.Utils;

class InstallReferrerReceiver extends BroadcastReceiver {

    public static final String KEY_UTM_SOURCE = "utm_source";
    public static final String KEY_UTM_CONTENT = "utm_content";
    public static final String KEY_UTM_CAMPAIGN = "utm_campaign";
    @Override
    public void onReceive(Context context, Intent intent) {
        /*String referrer = intent.getStringExtra("referrer");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (referrer != null) {

            preferences.edit().putString("referral_code", referrer).apply();
        }*/
       // Utils.Log("Referral Received");
        try {
            String referrer = intent.getStringExtra("referrer");
            if (referrer != null && !referrer.equals("")) {
               // Utils.log("Referral Received - " + referrer);
                String[] referrerParts = referrer.split("&");
                String utmSource = getData(KEY_UTM_SOURCE, referrerParts);
                String utmContent = getData(KEY_UTM_CONTENT, referrerParts);
                String utmCampaign = getData(KEY_UTM_CAMPAIGN, referrerParts);
                if (utmSource != null && utmSource.equals("google")) {
                  //  sendLogToMobisocServer(context, utmContent);

                    Log.e("the utm",utmContent);
                } else if (utmSource != null && utmSource.equals("app_share")) {
                   // RawStorageProvider.getInstance(context).dumpDataToStorage(RaghuKakaConstants.REFFERAL_FOR, utmContent);
                }
               // updateRKServerForReferral(context, utmSource, utmCampaign, utmContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getData(String key, String[] allData) {
        for (String selected : allData)
            if (selected.contains(key)) {
                return selected.split("=")[1];
            }
        return "";
    }
}
