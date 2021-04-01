package in.loanwiser.partnerapp.PartnerActivitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.BankStamentUpload.Doc_ImageView_Bank;
import in.loanwiser.partnerapp.R;

public class Doc_ImageView_Viability extends SimpleActivity {
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Doc_ImageView_Viability.class.getSimpleName();
    private AlertDialog progressDialog;
    String id,doc_typename,docid,class_id,user_type,transaction_id,doc_id;
    ImageView v_Image;
    ProgressBar progressBar;
    View view;
    WebView webview;
    ProgressBar progressbar;
    LinearLayout Ly_image_reader;
    RelativeLayout Rl_pdf_reader;
    String type,document,hash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_doc__image_view);
        setContentView(R.layout.activity_simple);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        Objs.a.setStubId(this, R.layout.activity_doc__image_view1);
        initTools1("Bank Statement View");

        webview = (WebView)findViewById(R.id.webview);
        progressBar = findViewById(R.id.progressBar);
        document =  Objs.a.getBundle(this, Params.document);
        //webView = (WebView) findViewById(R.id.webView1);
        webview.getSettings().setAppCacheEnabled(false);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setInitialScale(1);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.setVisibility(View.GONE);
        webview.setWebViewClient(new HelloWebViewClient() {

            public void onPageFinished(WebView view, String url) {
                if (view.getTitle().equals(""))
                {
                    view.reload();
                }else
                {
                    progressbar.setVisibility(View.GONE);
                }


            }
        });

        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
       // setContentView(webview);
       // webview.loadUrl(document);
        webview.loadUrl("http://docs.google.com/gview?embedded=true&url="+document);
        Log.e("pfd",document);

    }


    private class HelloWebViewClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            if (view.getTitle().equals(""))
                view.reload();
            progressBar.setVisibility(view.GONE);


        }

        @SuppressLint("NewApi")
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
            Toast.makeText(mCon, "Please try again",Toast.LENGTH_SHORT).show();

        }

    }
}

