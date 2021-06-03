package in.loanwiser.partnerapp.BankStamentUpload;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build;
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
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.CameraActivity.MainActivity;
import in.loanwiser.partnerapp.PDF_Dounloader.PermissionUtils;
import in.loanwiser.partnerapp.PartnerActivitys.FileDownloader;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class Doc_ImageView_Bank1 extends SimpleActivity {
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Doc_ImageView_Bank1.class.getSimpleName();
    private AlertDialog progressDialog;
    String id,doc_typename,docid,class_id,user_type,transaction_id,doc_id;
    ImageView v_Image;
    ProgressBar progressBar;
    View view;
    WebView webview;
    ProgressBar progressbar;
    LinearLayout Ly_image_reader;
    RelativeLayout Rl_pdf_reader;
    String type,document,hash,filename,report,viable_url_view,filename1;
    FloatingActionButton float_chat;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 1;
    PermissionUtils permissionUtils;
    private ProgressDialog pDialog;
    ImageView my_image;
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_doc__image_view);
        setContentView(R.layout.activity_simple);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        Objs.a.setStubId(this, R.layout.activity_doc__image_view_no_preview);
        report =  Objs.a.getBundle(this, Params.report);
        initTools1(report);
        Ly_image_reader = (LinearLayout)findViewById(R.id.Ly_image_reader);
        Rl_pdf_reader = (RelativeLayout)findViewById(R.id.Rl_pdf_reader);
        float_chat = (FloatingActionButton) findViewById(R.id.float_chat);
        document =  Objs.a.getBundle(this, Params.document);
        viable_url_view =  Objs.a.getBundle(this, Params.viable_url_view);

      //  document =  "https://callcenter.loanwiser.in/viewdocuments.php?imp=87c2c2771fd18671e386b8a02f145102&id=761404";
        Log.e("pfd",document);
      //  Log.e("viable_url_view",viable_url_view);
       // Log.e("type",type);
       // hash =  Objs.a.getBundle(this, Params.transaction_id);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
     /*   webview = (WebView)findViewById(R.id.webview);
            Rl_pdf_reader.setVisibility(View.VISIBLE);
            Ly_image_reader.setVisibility(View.GONE);
            webview.getSettings().setJavaScriptEnabled(true);
             filename =  document;

        try {
            filename=URLEncoder.encode(document,"UTF-8"); //Url Convert to UTF-8 It important.
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            webview.loadUrl("https://docs.google.com/gview?embedded=true&url=" + URLEncoder.encode(filename, "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //  webview.loadUrl("https://stackoverflow.com/questions/18838779/how-to-compare-two-edittext-fields-in-android/29399267");
          //  webview.loadUrl(filename);

        webview.setWebViewClient(new HelloWebViewClient());
        // Enable Javascript
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccessFromFileURLs(true);
        webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webview.getSettings().setBuiltInZoomControls(true);

        // Force links and redirects to open in the WebView instead of in a browser
       // webview.setWebViewClient(new WebViewClient());
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
        permissionUtils = new PermissionUtils();
       *//* String extStorageDirectory = Environment.getExternalStorageDirectory()
                .toString();
        File folder = new File(extStorageDirectory, "Reports");
        folder.mkdir();
        File file = new File(folder, "Read.pdf");
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }*//*
        float_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Downloader.DownloadFile(document, file);
              //  new DownloadFileFromURL().execute(document);
                Toast.makeText(mCon, "your file is downloading, Please wait...",Toast.LENGTH_SHORT).show();

                haveStoragePermission();

            }
        });*/



     //   showPdf();


    }
    public void download(View v)
    {
        new DownloadFile().execute(filename, "maven.pdf");
    }

    public void view(View v)
    {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/testthreepdf/" + "maven.pdf");  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try{
            startActivity(pdfIntent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(Doc_ImageView_Bank1.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadFile extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "testthreepdf");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //you have the permission now.
            Pdfdownload();
        }
    }
    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.O_MR1) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }


    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
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




    public boolean Pdfdownload(){

        boolean flag = true;
        boolean downloading =true;
        Uri Download_Uri = Uri.parse(document);
        try {
            DownloadManager downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setAllowedOverRoaming(false);
            request.setTitle(report + ".pdf");
            request.setDescription("Downloading " + report + ".pdf");
            request.setVisibleInDownloadsUi(true);
            request.setMimeType("application/pdf");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,     report + ".pdf");
            //  downloadManager.enqueue(request);
            long idDownLoad=downloadManager.enqueue(request);
            DownloadManager.Query query = null;
            query = new DownloadManager.Query();
            Cursor c = null;
            if(query!=null) {
                query.setFilterByStatus(DownloadManager.STATUS_FAILED|DownloadManager.STATUS_PAUSED|DownloadManager.STATUS_SUCCESSFUL|DownloadManager.STATUS_RUNNING|DownloadManager.STATUS_PENDING);
            } else {
                return flag;
            }
            while (downloading) {
                c = downloadManager.query(query);

                if(c.moveToFirst()) {
                    Log.i ("FLAG","Downloading");
                    int status =c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    if (status==DownloadManager.STATUS_SUCCESSFUL) {
                        Log.i ("FLAG","done");
                        downloading = false;
                        flag=true;
                        ErrorStatus();
                        break;
                    }
                    if (status==DownloadManager.STATUS_FAILED) {
                        Log.i ("FLAG","Fail");
                        downloading = false;
                        flag=false;
                        break;
                    }
                }
            }
        } catch (NullPointerException e) {
            Log.d("DownloadError", e.getMessage());
        }
        return flag;
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
        AppCompatButton submitbtn = (AppCompatButton) dialog.findViewById(R.id.submitbtn);
        AppCompatButton submitbtn1 = (AppCompatButton) dialog.findViewById(R.id.submitbtn1);
        submitbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (permissionUtils.checkPermission(Doc_ImageView_Bank1.this, STORAGE_PERMISSION_REQUEST_CODE, view)) {
                    if (filename.length() > 0) {
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(filename)));
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }

                }

            }

        });
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public  boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error","You have permission");
                Pdfdownload();
                return true;
            } else {
                Toast.makeText(this,"You need a Storage Permission",Toast.LENGTH_SHORT).show();
                Log.e("Permission error","You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");
            return true;
        }
    }
}




