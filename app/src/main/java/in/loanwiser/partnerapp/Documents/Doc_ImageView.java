package in.loanwiser.partnerapp.Documents;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class Doc_ImageView extends SimpleActivity {
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Doc_ImageView.class.getSimpleName();
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
    FloatingActionButton float_chat;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_doc__image_view);
        setContentView(R.layout.activity_simple);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        Objs.a.setStubId(this, R.layout.activity_doc__image_view);
        initTools(R.string.app_imge);
        Ly_image_reader = (LinearLayout)findViewById(R.id.Ly_image_reader);
        Rl_pdf_reader = (RelativeLayout)findViewById(R.id.Rl_pdf_reader);
        float_chat = (FloatingActionButton)findViewById(R.id.float_chat);
        float_chat.setVisibility(View.GONE);
        webview = (WebView)findViewById(R.id.webview);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        v_Image = (ImageView) findViewById(R.id.image_Product);
        progressBar = (ProgressBar) findViewById(R.id.progressBarMaterial);
        type =  Objs.a.getBundle(this, Params.type);
       document =  Objs.a.getBundle(this, Params.document);
      //  document =  "https://callcenter.loanwiser.in/viewdocuments.php?imp=87c2c2771fd18671e386b8a02f145102&id=761404";
        Log.e("pfd",document);
        Log.e("type",type);
        hash =  Objs.a.getBundle(this, Params.transaction_id);
        if(type.equals("pdf")){

            Rl_pdf_reader.setVisibility(View.VISIBLE);
            Ly_image_reader.setVisibility(View.GONE);
            webview.getSettings().setJavaScriptEnabled(true);
            String filename =  document;
            webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename);
            webview.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    progressbar.setVisibility(View.GONE);

                }
            });

        }else{

            Rl_pdf_reader.setVisibility(View.GONE);
            Ly_image_reader.setVisibility(View.VISIBLE);
            v_Image.setVisibility(View.VISIBLE);
            Objs.a.loadPicasso(mCon,document,v_Image,progressBar);
        }
    }

}

