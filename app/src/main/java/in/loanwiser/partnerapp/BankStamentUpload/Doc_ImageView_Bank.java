package in.loanwiser.partnerapp.BankStamentUpload;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Downloader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.User_Account.LoginNew;
import in.loanwiser.partnerapp.User_Account.Registration;

public class Doc_ImageView_Bank extends SimpleActivity {
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Doc_ImageView_Bank.class.getSimpleName();
    private AlertDialog progressDialog;
    String id,doc_typename,docid,class_id,user_type,transaction_id,doc_id;
    ImageView v_Image;
    ProgressBar progressBar;
    View view;
    WebView webview;
    ProgressBar progressbar;
    LinearLayout Ly_image_reader;
    RelativeLayout Rl_pdf_reader;
    String type,document,hash,filename,report;
    FloatingActionButton float_chat;

    private ProgressDialog pDialog;
    ImageView my_image;
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_doc__image_view);
        setContentView(R.layout.activity_simple);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        Objs.a.setStubId(this, R.layout.activity_doc__image_view);
        report =  Objs.a.getBundle(this, Params.report);
        initTools1(report);
        Ly_image_reader = (LinearLayout)findViewById(R.id.Ly_image_reader);
        Rl_pdf_reader = (RelativeLayout)findViewById(R.id.Rl_pdf_reader);
        float_chat = (FloatingActionButton) findViewById(R.id.float_chat);
        document =  Objs.a.getBundle(this, Params.document);

      //  document =  "https://callcenter.loanwiser.in/viewdocuments.php?imp=87c2c2771fd18671e386b8a02f145102&id=761404";
        Log.e("pfd",document);
       // Log.e("type",type);
       // hash =  Objs.a.getBundle(this, Params.transaction_id);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        webview = (WebView)findViewById(R.id.webview);
            Rl_pdf_reader.setVisibility(View.VISIBLE);
            Ly_image_reader.setVisibility(View.GONE);
            webview.getSettings().setJavaScriptEnabled(true);
             filename =  document;
            webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename);
            webview.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    progressbar.setVisibility(View.GONE);

                }
            });
        webview.setWebViewClient(new HelloWebViewClient());
        // Enable Javascript
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webview.setWebViewClient(new WebViewClient());
       /* String extStorageDirectory = Environment.getExternalStorageDirectory()
                .toString();
        File folder = new File(extStorageDirectory, "Reports");
        folder.mkdir();
        File file = new File(folder, "Read.pdf");
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }*/
        float_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Downloader.DownloadFile(document, file);
                new DownloadFileFromURL().execute(document);
            }
        });



     //   showPdf();


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

            progressBar.setVisibility(view.GONE);
        }

        @SuppressLint("NewApi")
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed(); // Ignore SSL certificate errors
        }



    }

  /*  public static class Downloader {

        public static void DownloadFile(String fileURL, File directory) {
            try {

                FileOutputStream f = new FileOutputStream(directory);
                URL u = new URL(fileURL);
                HttpURLConnection c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.connect();

                InputStream in = c.getInputStream();

                byte[] buffer = new byte[1024];
                int len1 = 0;
                while ((len1 = in.read(buffer)) > 0) {
                    f.write(buffer, 0, len1);
                }
                f.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }*/

    /**
     * Showing Dialog
     * */


    /**
     * Background Async Task to download file
     * */

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // this will be useful so that you can show a tipical 0-100% progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream
                OutputStream output = new FileOutputStream("/sdcard/Reports/"+report+".pdf");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

            // Displaying downloaded image into image view
            // Reading image path from sdcard
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/report.jpg";
            // setting downloaded into image view
          //  my_image.setImageDrawable(Drawable.createFromPath(imagePath));
            ErrorStatus();
        }

    }

    private void ErrorStatus() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.download_popup);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView bankstatement_message=(AppCompatTextView) dialog.findViewById(R.id.bankstatement_message);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
}




