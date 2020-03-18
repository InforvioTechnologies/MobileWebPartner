package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Viability_Report_Activity_New extends SimpleActivity {

    AppCompatButton pdf_download;
    private ProgressDialog mProgressDialog;
    private Context nCom = this;
    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    WebView webview;
    ProgressBar progressbar;
    RelativeLayout Rl_pdf_reader;
    String dest_file_path = "test.pdf";
    int downloadedSize = 0, totalsize;
    String download_file_url = "http://ilabs.uw.edu/sites/default/files/sample_0.pdf";
    float per = 0;
    String applicant_id,filename,filename1;

    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;

    PermissionUtils permissionUtils;

    TextInputLayout urlTextInputLayout;
    EditText urlEditText;

    String url = "http://callcenter.propwiser.com/trans_id/bEhsWHZzV1hJbjFoQVM0LzJKZmcrZz09/user_id/ZTMxZW04ZTkwWFlTbGt2SmIrN2NGdz09/rel_type/1/view_crif.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  setContentView(R.layout.activity_crif__report_);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_crif__report_);
        initTools(R.string.VIABILITY);

        Intent intent = getIntent();
        applicant_id = intent.getStringExtra("applicant_id");

        progressDialog = new SpotsDialog(this, R.style.Custom);
        Rl_pdf_reader = (RelativeLayout)findViewById(R.id.Rl_pdf_reader);
        webview = (WebView)findViewById(R.id.webview);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        webview.getSettings().setJavaScriptEnabled(true);

      //  filename =  "https://callcenter.propwiser.com/crif_mail_download.php?user_id=MUxURkNBKzFHSXJHMDZMMkZDaFByQT09&trans_id=MUxURkNBKzFHSXJHMDZMMkZDaFByQT09";
     //   String filename1 =  "https://www.loanwiser.in/";
        filename1 =  "http://callcenter.propwiser.com/trans_id/bEhsWHZzV1hJbjFoQVM0LzJKZmcrZz09/user_id/ZTMxZW04ZTkwWFlTbGt2SmIrN2NGdz09/rel_type/1/view_crif.pdf";
     //   webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename);

        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
       // webview.loadDataWithBaseURL("",filename1,"text/html", "UTF-8","");
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
      //  webview.loadUrl(filename1);
        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename1);

        WebViewClient webClient = new WebViewClient() {
            // Override page so it's load on my view only
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {

              progressbar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                progressbar.setVisibility(View.GONE);
            }
        };
        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                progressbar.setVisibility(View.GONE);

            }
        });


      pdf_download = (AppCompatButton) findViewById(R.id.pdf_download);
      pdf_download.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            // Download_Rental_Agreement();
              if (permissionUtils.checkPermission(Viability_Report_Activity_New.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                  if (url.length() > 0) {
                      try {
                          startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                      } catch (Exception e) {
                          e.getStackTrace();
                      }
                  }

              }

          }
      });

     //   Crif_Generation();

    }



    private void Download_Rental_Agreement() {

        String url = filename1;



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(urlTextInputLayout, "Permission Granted, Now you can write storage.", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(urlTextInputLayout, "Permission Denied, You cannot access storage.", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }


    private void Crif_Generation() {

        JSONObject J =new JSONObject();
        try {

            J.put("trans_id",Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("user_id",Pref.getUSERID(getApplicationContext()));
            J.put("rel_type",applicant_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("Crif Generation", String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CRIF_Generation, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Payment", String.valueOf(object));
                        try {

                            String Statues = object.getString("status");
                            if (Statues.equals("success")) {
                                filename = object.getString("download");

                                Log.e("faild", String.valueOf(object));
                                Webview_(filename);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private void Webview_(String filename){
        Log.e("filename", String.valueOf(filename));
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.loadUrl(filename);
        webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename);
        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                progressbar.setVisibility(View.GONE);

            }
        });
    }


}
