package in.loanwiser.partnerapp.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import in.loanwiser.partnerapp.R;

public class PDF_Download_Manager extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Button downloadbtn,submitloanbtn,proceedbtn;
    private static int REQUEST_CODE=1;
    AppCompatTextView savelater_textview;
    LinearLayout savelaterlay,proceedlay,submitloanlay;



    public static String baseUrl="http:\\/\\/uatcallcenter.propwiser.com\\/trans_id\\/bElWbC9jMTUwMXZhR2lwU0RHcFNUQT09\\/user_id\\/bE1IbkFFWEJ5OE9iQkJSY29YcUZGQT09\\/view_vible.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f__download__manager);
        //Button
        downloadbtn=findViewById(R.id.downloadbtn);
        proceedbtn=findViewById(R.id.proceedbtn);
        submitloanbtn=findViewById(R.id.submitloanbtn);

        //Textview
        savelater_textview=findViewById(R.id.savelater_textview);

        //Linearlayout
        savelaterlay=findViewById(R.id.savelaterlay);
        proceedlay=findViewById(R.id.proceedlay);
        submitloanlay=findViewById(R.id.submitloanlay);



        downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pdfdownload();
            }
        });

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, REQUEST_CODE);

        Buttondisableui();


   /*     if (context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Read granted");
        } else {
            Log.e(TAG, "Read refused");
        }*/
    }

    @SuppressLint("ResourceAsColor")
    public void Buttondisableui(){
        proceedbtn.setEnabled(false);
        proceedlay.setAlpha((float) 0.4);
        submitloanbtn.setEnabled(false);
        submitloanlay.setAlpha((float) 0.4);
        proceedlay.setAlpha((float) 0.4);
        savelater_textview.setEnabled(false);
        savelaterlay.setAlpha((float) 0.4);

        /*    proceedbtn.getBackground().setColorFilter(ContextCompat.getColor(this, android.R.color.darker_gray), PorterDuff.Mode.MULTIPLY);
            proceedbtn.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            proceedlay.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            submitloanlay.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            savelaterlay.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);*/

    }

    public boolean Pdfdownload(){
        boolean flag = true;
        boolean downloading =true;

        Uri Download_Uri = Uri.parse(baseUrl);
        try {
            DownloadManager downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setAllowedOverRoaming(false);
            request.setTitle("data" + ".pdf");
            request.setDescription("Downloading " + "data" + ".pdf");
            request.setVisibleInDownloadsUi(true);
            request.setMimeType("application/pdf");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, R.string.app_name  + "/" + "data" + ".pdf");

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
                        proceedbtn.setEnabled(true);
                        proceedlay.setAlpha((float) 1.0);
                        submitloanbtn.setEnabled(true);
                        submitloanlay.setAlpha((float) 1.0);
                        savelater_textview.setEnabled(true);
                        savelaterlay.setAlpha((float) 1.0);
                        downloadbtn.setText(getString(R.string.downloadcomplete));
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
}
