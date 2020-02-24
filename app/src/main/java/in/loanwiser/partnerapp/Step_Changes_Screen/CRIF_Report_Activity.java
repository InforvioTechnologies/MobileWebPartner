package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class CRIF_Report_Activity extends SimpleActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  setContentView(R.layout.activity_crif__report_);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_crif__report_);
        initTools(R.string.CRIF_REPORT);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        Rl_pdf_reader = (RelativeLayout)findViewById(R.id.Rl_pdf_reader);
        webview = (WebView)findViewById(R.id.webview);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        webview.getSettings().setJavaScriptEnabled(true);
        String filename =  "https://callcenter.propwiser.com/crif_mail_download.php?user_id=MUxURkNBKzFHSXJHMDZMMkZDaFByQT09&trans_id=MUxURkNBKzFHSXJHMDZMMkZDaFByQT09";
        String filename1 =  "http://www.tutorialspoint.com";
     //   webview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename);

        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webview.loadUrl(filename);
        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                progressbar.setVisibility(View.GONE);

            }
        });

      pdf_download = (AppCompatButton) findViewById(R.id.pdf_download);
      pdf_download.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

             Download_Rental_Agreement();

          }
      });

    }

    private void Download_Rental_Agreement() {

        String url = "http://maven.apache.org/archives/maven-1.x/maven.pdf";

        // declare the dialog as a member field of your activity
        // instantiate it within the onCreate method

        mProgressDialog = new ProgressDialog(CRIF_Report_Activity.this);
        mProgressDialog.setMessage("File Is Dowloading");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMax(100);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);

        // execute this when the downloader must be fired
        final DownloadTask downloadTask = new DownloadTask(CRIF_Report_Activity.this);
        downloadTask.execute(url);

        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

                downloadTask.cancel(true); //cancel the task
            }
        });
    }

    private class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;
        String path;
        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
               // File dir = new File("/sdcard/RentalDocument/");
                File dir = new File("/sdcard/CRIFReport/");

                try{
                    if(dir.mkdir()) {
                        Toast.makeText(context,"Rental Document Folder is Created: ", Toast.LENGTH_LONG).show();
                    } else {

                        // Toast.makeText(context,"Directory is not created: ", Toast.LENGTH_LONG).show();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                dir = new File(dir.getAbsolutePath());
                path = dir.getParent();
                Log.e("the path", String.valueOf(dir));

                input = connection.getInputStream();
                // output = new FileOutputStream("/sdcard/RentalDocument/Rental_Agreement1.docx");
                output = new FileOutputStream("/sdcard/CRIFReport/CRIFREPORTDETAILS.docx");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            mProgressDialog.dismiss();
            if (result != null) {
                Log.e("Files", String.valueOf(result));
                Toast.makeText(context, "Download error: " + result, Toast.LENGTH_LONG).show();
            }
            else {
                // Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT).show();
              //  Delete_Rental_Agreement();
                Inventory_Alert(mCon);
            }

        }

        public void Inventory_Alert(Context context) {
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
            builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
            builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
            // builder.setMessage("Do you like Update Your Inventory details for Rental Agreement Generation..!");
            builder.setMessage("File downloaded Sucessfuly In "+"\n"+" /sdcard/RentalDocument/Rental_Agreement1.docx");
            //  builder.setNegativeButton("No", null);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // open();
                }
            });
            androidx.appcompat.app.AlertDialog alert = builder.create();
            alert.show();
            Objs.a.DialogStyle(context, alert);
        }

        private void open()
        {
            String path = Environment.getExternalStorageDirectory().toString()+"/RentalDocument";
            Log.d("Files", "Path: " + path);
            File directory = new File(path);
            File[] files = directory.listFiles();
            Log.d("Files", "Size: "+ files.length);
            Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + "com.propwiser.tenancy.provider", directory);
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setAction(Intent.ACTION_VIEW);
            String type = "application/word";
            intent.setDataAndType(photoURI, type);
            startActivity(intent);

          /*  for (int i = 0; i < files.length-1; i++)
            {
                Log.d("Files", "FileName:"+path +"/"+ files[i].getName());

                File targetFile = new File(path +"/"+ files[i].getName());
                Uri targetUri = Uri.fromFile(targetFile);
                Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".my.package.name.provider", files);
               // Uri uri = (Uri) FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", directory);
               *//* Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(targetUri, "application/*");
                startActivityForResult(intent, DOC);*//*

                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                String type = "application/msword";
                intent.setDataAndType(targetUri, type);
                startActivity(intent);
            }*/

        }


    }
}
