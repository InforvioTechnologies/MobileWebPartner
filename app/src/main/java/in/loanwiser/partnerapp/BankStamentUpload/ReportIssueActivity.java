package in.loanwiser.partnerapp.BankStamentUpload;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.isDownloadsDocument;
import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.isExternalStorageDocument;
import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.isMediaDocument;
import static in.loanwiser.partnerapp.BankStamentUpload.FileUtils1.isGooglePhotosUri;

public class ReportIssueActivity extends SimpleActivity implements View.OnClickListener {

    RadioButton yes_radio,no_radio;
    TextInputEditText appidtxt,titletxt,desc_txt;
    AppCompatButton uploadbutton,sendbtn;
    ListView listView;
    public static final int PICK_IMAGE = 1;
    private Uri fileUri;
    String fileget;
    private String fileName;
    private List<String> fileNameList;
    private List<String> fileDoneList;
    ArrayList<Uri> uriarrayList = new ArrayList<>();
    private android.app.AlertDialog progressDialog;
    FileAdapter fileAdapter;
    String title_txts,desc_txts,appid;
    Context context = this;
    File orginalFile = null;
  ///  RadioButton yes_radio,no_radio;
    RadioGroup issueradio;
    TextInputLayout notextinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_report_issue);
       initTools(R.string.report_issue);

        appidtxt=findViewById(R.id.appidtxt);

        yes_radio=findViewById(R.id.yes_radio);
        no_radio=findViewById(R.id.no_radio);
        issueradio=findViewById(R.id.issueradio);

        titletxt=findViewById(R.id.titletxt);
        desc_txt=findViewById(R.id.desc_txt);
        notextinput=findViewById(R.id.notextinput);

        uploadbutton=findViewById(R.id.uploadbutton);
        sendbtn=findViewById(R.id.sendbtn);

        listView=findViewById(R.id.listview);

        fileNameList = new ArrayList<>();
        fileDoneList = new ArrayList<>();
        progressDialog = new SpotsDialog(this, R.style.Custom);

        uploadbutton.setOnClickListener(this);
        sendbtn.setOnClickListener(this);


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        fileAdapter = new FileAdapter(fileNameList, fileDoneList);
        listView.setAdapter(fileAdapter);

        title_txts=titletxt.getText().toString().trim();
        desc_txts=desc_txt.getText().toString().trim();
        appid = appidtxt.getText().toString().trim();

        issueradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=(RadioButton)findViewById(checkedId);
                //  Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();
                if (rb.getText().toString().equalsIgnoreCase("NO")){
                    appidtxt.setVisibility(View.GONE);
                    notextinput.setVisibility(View.GONE);
                }else{
                    notextinput.setVisibility(View.VISIBLE);
                    appidtxt.setVisibility(View.VISIBLE);
                }
                // Log.i("TAG", "onCheckedChanged: "+rb.getText());
                //  Log.i("TAG", "onCheckedChanged_id: "+idx);
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.uploadbutton:
                Gallerychooser();
                break;
            case R.id.sendbtn:
                if (appidtxt.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(this,"Please select APP ID",Toast.LENGTH_SHORT).show();
                }else if (titletxt.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(this, "Please select Title", Toast.LENGTH_SHORT).show();
                }else if (desc_txt.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(this,"Please select Description",Toast.LENGTH_SHORT).show();
                }else {
                    MultifileUploadRetrofit();
                }
                break;

        }

    }

    private void Gallerychooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            listView.setVisibility(View.VISIBLE);
            if (data.getClipData() != null) {
                int totalItemsSelected = data.getClipData().getItemCount();
                Log.i("totalItemsSelected", "onActivityResult: " + totalItemsSelected);
                for (int i = 0; i < totalItemsSelected; i++) {
                    fileUri = data.getClipData().getItemAt(i).getUri();
                    String path = fileUri.getPath(); // "/mnt/sdcard/FileName.mp3"
                    Log.i("TAG", "onActivityResult:Stringpath " + path);
                    fileget = String.valueOf(data.getClipData());
                    fileName = getFileName(fileUri);
                    fileNameList.add(fileName);
                    uriarrayList.add(fileUri);
                    //getmultiplelist.add(fileUri);
                    fileAdapter.notifyDataSetChanged();


                    // fileDoneList.add("uploading");
                    Log.i("TAG", "onActivityResult:uriarrayList " + uriarrayList);
                    Log.i("Upload_Activity_Bank", "onActivityResult_fileuri: " + fileUri);
                    Log.i("Upload_Activity_Bank", "onActivityResult_path: " + fileget);
                    Log.i("Upload_Activity_Bank", "ActivityResult_filename: " + fileName);
                }
            } else {

                fileUri = data.getData();
                fileget = String.valueOf(data.getData());
                fileName = getFileName(data.getData());
                fileNameList.add(fileName);
                uriarrayList.add(fileUri);
                String sav = fileUri.getPath();
                fileAdapter.notifyDataSetChanged();
            }

        }

    }

    private void Submit_upload_sucess() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.report_dialog);
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
                Intent intent=new Intent(ReportIssueActivity.this, DashBoard_new.class);
                startActivity(intent);
                // Bank_statues();
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

    private void MultifileUploadRetrofit() {
        ApiConfig getResponse = Appconfig.getRetrofit().create(ApiConfig.class);
        Log.i("TAG", "MultifileUploadRetrofit: " + "check");
        progressDialog.show();
        // List<Uri> uriList = null; //These are the uris for the files to be uploaded
        MediaType mediaType = MediaType.parse("application/pdf");//Based on the Postman logs,it's not specifying Content-Type, this is why I've made this empty content/mediaType
        MultipartBody.Part[] fileParts = new MultipartBody.Part[uriarrayList.size()];
        for (int i = 0; i < uriarrayList.size(); i++) {
            String filePath = getRealPathFromUri(uriarrayList.get(i));
            //File file = new File(uriarrayList.get(i).getPath());
            String uri_test = uriarrayList.get(i).toString();

            String filename = uri_test.substring(uri_test.lastIndexOf("/") + 1);
            String pdf1 = filename.substring(filename.lastIndexOf(".") + 1);

            if (Build.VERSION.SDK_INT < 11) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(ReportIssueActivity.this, uriarrayList.get(i)));
            }
            // SDK >= 11 && SDK < 19
            else if (Build.VERSION.SDK_INT < 19) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(ReportIssueActivity.this, uriarrayList.get(i)));
            }
            // SDK > 19 (Android 4.4) and up
            else {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API19(ReportIssueActivity.this, uriarrayList.get(i)));
            }
            File file = new File(filePath);
            RequestBody fileBody = RequestBody.create(mediaType, orginalFile);
            //Setting the file name as an empty string here causes the same issue, which is sending the request successfully without saving the files in the backend, so don't neglect the file name parameter.
            fileParts[i] = MultipartBody.Part.createFormData(String.format(Locale.ENGLISH, "img_url[%d]", i), file.getName(), fileBody);
            // fileParts[i] = MultipartBody.Part.createFormData("img_url", file.getName(), fileBody);
        }
        RequestBody b2b_userid = RequestBody.create(MediaType.parse("multipart/form-data"), Pref.getID(getApplicationContext()));
        RequestBody appid = RequestBody.create(MediaType.parse("multipart/form-data"),appidtxt.getText().toString().trim() );
        RequestBody titleissue = RequestBody.create(MediaType.parse("multipart/form-data"), titletxt.getText().toString().trim());
        RequestBody descissue = RequestBody.create(MediaType.parse("multipart/form-data"), desc_txt.getText().toString().trim());
        Call<IssueUpload> call = getResponse.imageupload("Auth Token", fileParts, appid, b2b_userid, titleissue, descissue);

        call.enqueue(new Callback<IssueUpload>() {
            @Override
            public void onResponse(Call<IssueUpload> call, retrofit2.Response<IssueUpload> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(ReportIssueActivity.this, "Submitted Successfully",Toast.LENGTH_SHORT).show();
                    Submit_upload_sucess();

                }
            }

            @Override
            public void onFailure(Call<IssueUpload> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ReportIssueActivity.this, "Network issue", Toast.LENGTH_SHORT).show();
                Log.i("TAG", "onFailure: " + t.getMessage());
            }
        });


    }


    public String getRealPathFromUri(Uri uri) { // function for file path from uri,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(this, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn( contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn( contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn( uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public String getDataColumn(Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

}