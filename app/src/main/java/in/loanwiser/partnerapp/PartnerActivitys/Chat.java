package in.loanwiser.partnerapp.PartnerActivitys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import android.webkit.WebViewClient;
import adhoc.app.applibrary.Config.AppUtils.Objs;

import in.loanwiser.partnerapp.R;


public class Chat extends SimpleActivity {

    WebView chaturl;
    private ProgressDialog progDailog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_chat);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_chat);
        initTools(R.string.chat);
        progDailog = ProgressDialog.show(Chat.this, "Loading","Please wait...", true);
        progDailog.setCancelable(false);

        chaturl = (WebView) findViewById(R.id.chaturl);

        chaturl.getSettings().setJavaScriptEnabled(true);
        chaturl.getSettings().setLoadWithOverviewMode(true);
        chaturl.getSettings().setUseWideViewPort(true);
        chaturl.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);

                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }
        });

      //  chaturl.loadUrl("https://tawk.to/chat/5c85f76fc37db86fcfcd0bea/default");
        chaturl.loadUrl("https://tawk.to/chat/5c863c95c37db86fcfcd18bb/default");
        //  chaturl.loadUrl("https://hr.my/go/");

       // Crisp Chat SDK Integration
       /* initTools(R.string.chat);
        String mobile = Pref.getUID(mCon);
          String mobile = "9566592196";

        Crisp.User.setPhone(mobile);*/
    }

}
