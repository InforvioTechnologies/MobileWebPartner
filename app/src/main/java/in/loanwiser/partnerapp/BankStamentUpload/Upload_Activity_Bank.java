package in.loanwiser.partnerapp.BankStamentUpload;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Documents.Document_Details;
import in.loanwiser.partnerapp.Documents.SingleUploadBroadcastReceiver;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Lead_Crration_Activity;

public class Upload_Activity_Bank extends SimpleActivity implements View.OnClickListener, SingleUploadBroadcastReceiver.Delegate {

private static final int PICK_PDF_REQUEST =1 ;
        RecyclerView recyclerView;
        ListView listview;

        Button upload,submit;






private Uri filePath;
private Uri fileUri;

private String fileName;

private List<String> fileNameList;
private List<String> fileDoneList;

        ArrayList<Uri> uriarrayList = new ArrayList<>();


private android.app.AlertDialog progressDialog;



public static final String Bankstatement_URl="http://cscapitest.propwiser.com/mobile/partner_loanapi.php?call=bank_statement_upload";


        FileAdapter fileAdapter;

        String[] stringarray;

        String fileget;

        MultipartUploadRequest uploadRequest;
        AppCompatTextView skip_payment;

        private final SingleUploadBroadcastReceiver uploadReceiver =
                new SingleUploadBroadcastReceiver();

        EditText docpass_edt_txt;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setContentView(R.layout.activity_upload___bank);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_upload___bank);
        initTools(R.string.bank_statment);
        upload=findViewById(R.id.upload);
        listview=findViewById(R.id.listview);
        submit=findViewById(R.id.submit);
        skip_payment=findViewById(R.id.skip_payment);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        fileNameList = new ArrayList<>();
        fileDoneList=new ArrayList<>();

        upload.setOnClickListener(this);
        submit.setOnClickListener(this);

        docpass_edt_txt = findViewById(R.id.docpass_edt_txt);


        skip_payment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        Intent intent = new Intent(Upload_Activity_Bank.this, Dashboard_Activity.class);
                        startActivity(intent);
                }
        });

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        fileAdapter=new FileAdapter(fileNameList,fileDoneList);
        listview.setAdapter(fileAdapter);





        }

public void onRadioButtonClicked(View v) {
        //require to import the RadioButton class
        RadioButton rb1 = (RadioButton) findViewById(R.id.downpdf_btn);
        RadioButton rb2 = (RadioButton) findViewById(R.id.scanpdf_btn);
        boolean  checked = ((RadioButton) v).isChecked();
        switch (v.getId()){
        case R.id.downpdf_btn:
        if (checked)
        Toast.makeText(Upload_Activity_Bank.this,"Downloaded PDf",Toast.LENGTH_SHORT).show();
        break;
        case R.id.scanpdf_btn:
        if(checked)
        Toast.makeText(Upload_Activity_Bank.this,"Scaned PDf",Toast.LENGTH_SHORT).show();
        break;
        }

        }




//method to show file chooser
private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_REQUEST);
        }

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK) {



          /* ArrayList<File> Files = (ArrayList<File>) data.getSerializableExtra(FILES_TO_UPLOAD); //file array list
           String [] files_paths; //string array
           int i = 0;*/

        /*   for(File file : Files){
               //String fileName = file.getName();
               String uri = file.getAbsolutePath();
               files_paths[i] = uri.toString(); //storing the selected file's paths to string array files_paths
               i++;
           }*/


        listview.setVisibility(View.VISIBLE);

        if (data.getClipData() != null) {

        int totalItemsSelected = data.getClipData().getItemCount();

        Log.i("totalItemsSelected", "onActivityResult: "+totalItemsSelected);

        for(int i = 0; i < totalItemsSelected; i++) {

        fileUri = data.getClipData().getItemAt(i).getUri();
        String path = fileUri.getPath(); // "/mnt/sdcard/FileName.mp3"
        Log.i("TAG", "onActivityResult:Stringpath "+path);
        fileget= String.valueOf(data.getClipData());
        fileName = getFileName(fileUri);
        fileNameList.add(fileName);
        uriarrayList.add(fileUri);
        fileAdapter.notifyDataSetChanged();


        // fileDoneList.add("uploading");
        Log.i("TAG", "onActivityResult:uriarrayList "+uriarrayList);
        Log.i("Upload_Activity_Bank", "onActivityResult_fileuri: "+fileUri);
        Log.i("Upload_Activity_Bank", "onActivityResult_path: "+fileget);
        Log.i("Upload_Activity_Bank", "ActivityResult_filename: "+fileName);
        }
        }
        else{

        fileUri = data.getData();
        fileget= String.valueOf(data.getData());
        fileName=getFileName(data.getData());
        fileNameList.add(fileName);
        uriarrayList.add(fileUri);
        fileAdapter.notifyDataSetChanged();
        }

        }



        }

public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
        String[] proj = { MediaStore.Images.Media.DATA };
        cursor = context.getContentResolver().query(contentUri, proj, null,
        null, null);
        int column_index = cursor
        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
        } finally {
        if (cursor != null) {
        cursor.close();
        }
        }
        }

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
@Override
public void onClick(View v) {
        switch (v.getId()){
        case R.id.upload:
        showFileChooser();
        break;
        case R.id.submit:
        uploadMultipart();
        break;


        }
        }

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public String getPDFPath(Uri uri){
final String id = DocumentsContract.getDocumentId(uri);
final Uri contentUri = ContentUris.withAppendedId(
        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
        }




public String getPath(Uri uri)
        {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index =             cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(column_index);
        cursor.close();
        return s;
        }







@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public void uploadMultipart() {
        //getting name for the image
        //  String name = editText.getText().toString().trim();

        //getting the actual path of the image
        ArrayList<String> templist = new ArrayList<String>();

        String pdf_password = docpass_edt_txt.getText().toString();

      /*  for (int n=0;n<uriarrayList.size();n++){
            Log.i("TAG", "uploadMultipart:loop "+uriarrayList.get(n));
            String paths= String.valueOf(uriarrayList.get(n));
        }*/
        ArrayList<String> pathlist = new ArrayList<>();
        for (int i = 0; i < uriarrayList.size(); i++) {
        String path = FilePath.getPath(this, uriarrayList.get(i));
        pathlist.add(path);

        }

        Log.i("TAG", "String_path: " + pathlist);
        if (pathlist == null) {

        Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
        }
        else {
        //Uploading code
        try {
        String uploadId = UUID.randomUUID().toString();
        uploadReceiver.setDelegate(this);
        uploadReceiver.setUploadID(uploadId);
        Log.i("Uploadpdf", "uploadMultipart:" + uploadId);
        Log.i("Uploadpdf", "Path: " + pathlist.get(0));
        Log.i("Uploadpdf", "uploadMultipart_filename: " + fileName);
        Log.i("filenamelist", "fileNameList: " + String.valueOf(fileNameList));
        String s = String.valueOf(fileNameList);
        Log.i("tag", "namelist: " + s);
        String uris = String.valueOf(uriarrayList);
        Log.i("TAG", "urilist: " + uris);
        //Creating a multi part request

        for (int i=0;i<pathlist.size();i++){
        String finalpath= pathlist.get(i);
        progressDialog.show();
        new MultipartUploadRequest(this, uploadId, Bankstatement_URl)
        .addFileToUpload(finalpath, "img_url") //Adding file
        .addParameter("doc_name", String.valueOf(fileNameList.get(i))) //Adding text parameter to the request
        //Adding file
        //Adding text parameter to the request
        .addParameter("pdf_password", "")
        .addParameter("relationship_type", pdf_password)
        .addParameter("is_mobileupload ", "4")
        .addParameter("transaction_id  ", Pref.getTRANSACTIONID(getApplicationContext()))
       // .setNotificationConfig(new UploadNotificationConfig())
        .setMaxRetries(2)
        .startUpload(); //Starting the upload

        }



        } catch (Exception exc) {
        Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }

        }

        }





        @Override
        protected void onPause() {
                super.onPause();

                uploadReceiver.unregister(this);
        }


        @Override
        public void onProgress(int progress) {
                Log.i("TAG", "The progress of the upload with ID " + " is: " + progress);
        }

        @Override
        public void onProgress(long uploadedBytes, long totalBytes) {
                Log.i("TAG", "The progress of the upload with ID "
                        + uploadedBytes + " is: " + totalBytes);
        }

        @Override
        public void onError(Exception exception) {
                Log.e("TAG", "Error in upload with ID:"
                        + exception.getLocalizedMessage(), exception);
        }

        @Override
        public void onCompleted(int serverResponseCode, byte[] serverResponseBody) {

                String response = String.valueOf(serverResponseCode);

                Log.e("the server Response", String.valueOf(serverResponseCode));
                Log.e("the server ", String.valueOf(serverResponseBody));

                if(response.equals("200")){
                        progressDialog.dismiss();

                       Toast.makeText(getApplicationContext(),"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                      /* Intent intent = new Intent(Upload_Activity_Bank.this, Lead_Crration_Activity.class);
                       startActivity(intent);
                        finish();*/
                        // Send_Reload(app_id);

                        //   getContentResolver().delete(uri, null, null);
                }else if(response.equals("406")){
                        progressDialog.dismiss();

                }
                //Upload with ID [B@19058a20 has been completed with HTTP 406. Response from server:
                //  Log.i(TAG, "Upload with ID " + serverResponseBody + " has been completed with HTTP " + serverResponseCode + ". Response from server: " );

        }

        @Override
        public void onCancelled() {

        }


    /*    String uripath=String.valueOf(uriarrayList);
        Log.i("TAG", "uripath: "+uripath);*/








public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        try {
        if (cursor != null && cursor.moveToFirst()) {
        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        }
        } finally {
        cursor.close();
        }
        }
        if (result == null) {
        result = uri.getPath();
        int cut = result.lastIndexOf('/');
        if (cut != -1) {
        result = result.substring(cut + 1);
        }
        }
        return result;
        }

@Override
protected void onResume() {
        super.onResume();
        fileAdapter.notifyDataSetChanged();
        uploadReceiver.register(this);
        }
        }