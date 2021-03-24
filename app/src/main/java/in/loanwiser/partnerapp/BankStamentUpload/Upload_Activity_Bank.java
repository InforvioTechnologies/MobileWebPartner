package in.loanwiser.partnerapp.BankStamentUpload;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import net.gotev.uploadservice.MultipartUploadRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import eu.inmite.android.lib.validations.form.iface.IFieldAdapter;
import in.loanwiser.partnerapp.BankStamentUpload.modelglib.GlibResponse;
import in.loanwiser.partnerapp.Documents.SingleUploadBroadcastReceiver;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklistActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.DocumentChecklist_Fragment;
import in.loanwiser.partnerapp.Step_Changes_Screen.Document_Checklist_Details_type;
import in.loanwiser.partnerapp.User_Account.Welcome_Page;
import lecho.lib.hellocharts.view.LineChartView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Part;

import static adhoc.app.applibrary.Config.AppUtils.Objs.a;
import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.getDataColumn;
import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.isDownloadsDocument;
import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.isExternalStorageDocument;
import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.isMediaDocument;
import static in.loanwiser.partnerapp.BankStamentUpload.FileUtils1.isGooglePhotosUri;

public class Upload_Activity_Bank extends SimpleActivity implements  View.OnClickListener, CompoundButton.OnCheckedChangeListener, SingleUploadBroadcastReceiver.Delegate  {

    private static final int PICK_PDF_REQUEST = 1;
    private static final String TAG =Upload_Activity_Bank.class.getSimpleName() ;

    RecyclerView recyclerView,bankstatement_recycleview;
    ListView listview;
    Button upload, submit;
    Button view_analysisbut;
    private Uri filePath;
    private Uri fileUri;
    private String fileName;
    private List<String> fileNameList;
    private List<String> fileDoneList;
    ArrayList<Uri> uriarrayList = new ArrayList<>();
    List<Uri> uriarraylist1;
    ArrayList<String> pathlist;
    private android.app.AlertDialog progressDialog;
    FileAdapter fileAdapter;
    String[] stringarray;
    File orginalFile = null;
    String fileget;
    Context context = this;
    MultipartUploadRequest uploadRequest;
    AppCompatTextView skip_payment;
    ImageView imageview;
    Statementlist statementlist;
    ArrayList<Bankitems> items;
    LinearLayout recycleview_lay,submitbanktxt_lay;
    String checkradiobutton_value;
    BanklistAdapter banklistAdapter;
    ArrayList<Bankdetails_model> items1;
    String bankstatement_msg,entity_id;
    TextView sub_banktextlay;
    Button upload_requirebtn;

    JSONArray ja1;



    private final SingleUploadBroadcastReceiver uploadReceiver =
            new SingleUploadBroadcastReceiver();

    EditText docpass_edt_txt;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    boolean checkradio_value;
    RadioButton scanpdf_btns,downpdf_btns;
    List<Uri> getmultiplelist;
    RadioGroup Bank_Statement_radio_list;
    String selectedtext;
    LinearLayout loader,bankhorizontallist,banklisttxt_lay;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.activity_upload___bank);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_bank_statement);
        initTools(R.string.bank_statment);
        upload = findViewById(R.id.uploads);
        listview = findViewById(R.id.listview);
        submit = findViewById(R.id.submit);
        bankstatement_recycleview=findViewById(R.id.bankstatement_recycleview);
        Bank_Statement_radio_list = (RadioGroup) findViewById(R.id.hour_radio_group);
        imageview=findViewById(R.id.imageview);
        skip_payment = findViewById(R.id.skip_payment);
        docpass_edt_txt = findViewById(R.id.docpass_edt_txt);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        fileNameList = new ArrayList<>();
        fileDoneList = new ArrayList<>();
        downpdf_btns = (RadioButton) findViewById(R.id.downpdf_btns);
        scanpdf_btns = (RadioButton) findViewById(R.id.scanpdf_btns);
        recycleview_lay=findViewById(R.id.recycleview_lay);
        bankhorizontallist=findViewById(R.id.bankhorizontallist);
        banklisttxt_lay=findViewById(R.id.banklisttxt_lay);
        sub_banktextlay=findViewById(R.id.sub_banktextlay);
        //LineChartView lineChartView = findViewById(R.id.helolinechart);

        submitbanktxt_lay=findViewById(R.id.submitbanktxt_lay);
        recyclerView=(RecyclerView)findViewById(R.id.banklist_recycleview);
        entity_id="";
        items1=new ArrayList<>();

        loader=findViewById(R.id.loader);

        downpdf_btns.setOnCheckedChangeListener(this);
        scanpdf_btns.setOnCheckedChangeListener(this);
        downpdf_btns.setChecked(true);
        checkradio_value=true;
        checkradiobutton_value="1";
        upload.setOnClickListener(this);
        submit.setOnClickListener(this);

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recycleview_lay.getVisibility()==View.VISIBLE)
                    recycleview_lay.setVisibility(View.GONE);
                else{ recycleview_lay.setVisibility(View.GONE);
                }
            }
        });

        items=new ArrayList<>();

        statementlist = new Statementlist(Upload_Activity_Bank.this, items);
        bankstatement_recycleview.setLayoutManager(new LinearLayoutManager(Upload_Activity_Bank.this, LinearLayoutManager.VERTICAL, false));
        bankstatement_recycleview.setHasFixedSize(true);



        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileNameList.clear();
                uriarrayList.clear();
                showFileChooser();
            }
        });
        view_analysisbut = findViewById(R.id.view_analysisbut);

        view_analysisbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Upload_Activity_Bank.this, BankAnalysis.class);
              //  Intent in=new Intent(context,BankAnalysis.class);
                i.putExtra("adapter","upload");
                startActivity(i);
            }
        });

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        fileAdapter = new FileAdapter(fileNameList, fileDoneList);
        listview.setAdapter(fileAdapter);




        BankverticalList();
      //  BankItemhorizontallist();

                /*Log.i("getTRANSACTIONID", Pref.getTRANSACTIONID(getApplicationContext()));
                Log.i("relation", Pref.getCoAPPAVAILABLE(getApplicationContext()));*/

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK) {
            listview.setVisibility(View.VISIBLE);
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


    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
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
        switch (v.getId()) {
            case R.id.submit:
                if (checkradiobutton_value!=null) {
                    if (uriarrayList.isEmpty()) {
                        Toast.makeText(Upload_Activity_Bank.this, "Please Select Bank statement", Toast.LENGTH_SHORT).show();
                    } else if (uriarrayList.size() == 0) {
                        Toast.makeText(Upload_Activity_Bank.this, "Please upload document", Toast.LENGTH_SHORT).show();
                    } else {
                        //    uploadMultipart();
                        pathlist = new ArrayList<>();
                        for (int i = 0; i < uriarrayList.size(); i++) {
                            Log.e("the Upload issues", uriarrayList.get(i).toString());
                            String uri_test = uriarrayList.get(i).toString();
                            Log.e("the Upload issues", uri_test);
                            String filename = uri_test.substring(uri_test.lastIndexOf("/") + 1);
                            String pdf1 = filename.substring(filename.lastIndexOf(".") + 1);
                            //  uploadMultipart();
                            String a = "pdf";

                            if (pdf1.equals(a)) {
                                // String path = getPath(this, uriarrayList.get(i));
                                String path = getPDFPath(uriarrayList.get(i));
                                pathlist.add(path);
                                // uploadMultipart();
                                MultifileUploadRetrofit();

                            } else {
                                if (Build.VERSION.SDK_INT < 11) {
                                    orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(Upload_Activity_Bank.this, uriarrayList.get(i)));
                                }
                                // SDK >= 11 && SDK < 19
                                else if (Build.VERSION.SDK_INT < 19) {
                                    orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(Upload_Activity_Bank.this, uriarrayList.get(i)));
                                }
                                // SDK > 19 (Android 4.4) and up
                                else {
                                    orginalFile = new File(FileUtils1.getRealPathFromURI_API19(Upload_Activity_Bank.this, uriarrayList.get(i)));
                                }
                                String path = String.valueOf(orginalFile);
                                pathlist.add(path);
                                // uploadMultipart();
                                CheckUploadcondition();
                                //  MultifileUploadRetrofit();

                                Log.e("path", path);
                                Log.e("Upload_Activity_Ban", String.valueOf(orginalFile));

                            }


                        }
                    }
                }
                else{
                    Toast.makeText(this,"Select bank Statement Type",Toast.LENGTH_SHORT).show();
                }

                break;


        }
    }

    private void CheckUploadcondition() {
        if (checkradiobutton_value.equalsIgnoreCase("1")){
            MultifileUploadRetrofit();
        }else{
            MultifileUploadRetrofitGlib();
        }
    }




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getPDFPath1(Uri uri) {
        final String id = DocumentsContract.getDocumentId(uri);
        final Uri contentUri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String getPDFPath(Uri uri) {
        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                String documentId = DocumentsContract.getDocumentId(uri);
                String[] split = documentId.split(":");
                String type = split[0];
                return Environment.getExternalStorageDirectory().toString() + "/" + split[1];

            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                String decodedURI = Uri.decode(uri.toString());

                if (decodedURI.contains("raw:")) {
                    return decodedURI.substring(decodedURI.indexOf("raw:") + 4);
                }

                String id = DocumentsContract.getDocumentId(Uri.parse(decodedURI));

                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                /// Android.Net.Uri uri = Android.Net.Uri.Parse(path);
                return getDataColumn(contentUri, null, null);
            }
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

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        return s;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void uploadMultipart() {

        ArrayList<String> templist = new ArrayList<String>();
        String pdf_password = docpass_edt_txt.getText().toString();
        Log.i("TAG", "String_path: " + pathlist);
        if (pathlist == null) {

            Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
        } else {
            //Uploading code
            try {
                String uploadId = UUID.randomUUID().toString();
                uploadReceiver.setDelegate(this);
                uploadReceiver.setUploadID(uploadId);
                Log.i("Uploadpdf", "uploadMultipart:" + uploadId);
                Log.i("Uploadpdf", "Path: " + pathlist.get(0));
                Log.i("Uploadpdf", "uploadMultipart_filename: " + fileName);
                Log.i("filenamelist", "fileNameList: " + fileNameList);
                String s = String.valueOf(fileNameList);
                Log.i("tag", "namelist: " + s);
                String uris = String.valueOf(uriarrayList);
                Log.i("TAG", "urilist: " + uris);
                //Creating a multi part request
                               /* Log.i("getTRANSACTIONID", Pref.getTRANSACTIONID(getApplicationContext()));
                                Log.i("relation", Pref.getCoAPPAVAILABLE(getApplicationContext()));
*/
                             /*  for (int i = 0; i < pathlist.size(); i++) {
                                        String finalpath = pathlist.get(i);*/
                Log.i(TAG, "uploadMultipart: arraystring"+ pathlist);
                progressDialog.show();
                new MultipartUploadRequest(this, uploadId, Urls.Bankstatement_URl)
                        .addFileToUpload(String.valueOf(pathlist), "img_url") //Adding file
                        // .addParameter("doc_name", String.valueOf(fileNameList.get(i))) //Adding text parameter to the request
                        //.addParameter("doc_name", String.valueOf(fileNameList) //Adding text parameter to the request
                        //Adding file
                        //Adding text parameter to the request
                        .addParameter("pdf_password", pdf_password)
                        //.addParameter("relationship_type", Pref.getCoAPPAVAILABLE(getApplicationContext()))
                        .addParameter("relationship_type", "1")
                        .addParameter("is_mobileupload", "4")
                        //.addParameter("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()))
                        .addParameter("transaction_id", "60775")
                        .addParameter("analysis_status", "1")
                        .addParameter("entity_id", "")
                        .addParameter("workorder_id", "")
                        // .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
                                      /*  Log.i("doc_name", String.valueOf(fileNameList.get(i)));
                                        Log.i("finalpath", String.valueOf(finalpath));*/

                // }
            } catch (Exception exc) {
                Log.e(TAG, "uploadMultipart: error"+exc.getMessage() );
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
        Log.i("TAG", "onCompletedserver: "+response);

        Log.e("the server Response", String.valueOf(serverResponseCode));
        Log.e("the server ", String.valueOf(serverResponseBody));

        if (response.equals("200")) {
            progressDialog.dismiss();

            Submit_upload_sucess();

            // Send_Reload(app_id);

            //   getContentResolver().delete(uri, null, null);
        } else if (response.equals("406")) {
            progressDialog.dismiss();

        }
        //Upload with ID [B@19058a20 has been completed with HTTP 406. Response from server:
        //  Log.i(TAG, "Upload with ID " + serverResponseBody + " has been completed with HTTP " + serverResponseCode + ". Response from server: " );

    }

    @Override
    public void onCancelled() {

    }


    public void Bank_statues() {

        // final String step_status11 = step_status1;
        JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("Applicant Entry request", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.update_bankstatementstatus, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant Entry", String.valueOf(response));
                        JSONObject jsonObject1 = new JSONObject();

                        try {

                            JSONObject jsonObject2 = response.getJSONObject("response");
                            String statues = jsonObject2.getString("status");

                            if (statues.contains("success")) {
                               // Applicant_Status();
                                Intent intent = new Intent(Upload_Activity_Bank.this, BankAnalysis.class);
                                //  Intent in=new Intent(context,BankAnalysis.class);
                                intent.putExtra("adapter","upload");
                                startActivity(intent);
                               // finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Applicant Entry request", String.valueOf(error));
                Toast.makeText(Upload_Activity_Bank.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
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

    @Override
    protected void onResume() {
        super.onResume();
        fileAdapter.notifyDataSetChanged();
        uploadReceiver.register(this);

    }


    public void ExitAlert(Context context) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setMessage("Unable get correct pdf format. Please select the correct PDF file from File Directory/Internal Storage.");
        builder.setNegativeButton("Okay", null);

        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        a.DialogStyle(context, alert);
    }

    public void ExitAlert1(Context context) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(R.drawable.ic_check));
        builder.setMessage("Bank Statement Uploaded Sucessfully.");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        a.DialogStyle(context, alert);
    }

    private void Submit_upload_sucess() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.bank_statement_sucess);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView bankstatement_message=(AppCompatTextView) dialog.findViewById(R.id.bankstatement_message);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);

        //bankstatement_message.setText(bankstatement_msg);



        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Bank_statues();

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

    private void Submit_upload_filePath() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.bank_statement_file);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);

        AppCompatTextView Pan_No_Show, dob_Show, father_name, marital_status;
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Bank_statues();

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

    @SuppressLint("LongLogTag")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (buttonView.getId() == R.id.downpdf_btns) {
                scanpdf_btns.setChecked(false);
                checkradio_value=true;
                checkradiobutton_value="1";
                Log.i(TAG, "Radiobuttonvalue: "+checkradiobutton_value);
                Log.i("TAG", "onCheckedChanged: "+"Download finbox");
            } if (buttonView.getId()==R.id.scanpdf_btns){
                downpdf_btns.setChecked(false);
                checkradio_value=false;
                checkradiobutton_value="2";
                Log.i(TAG, "Radiobuttonvalue: "+checkradiobutton_value);
                Log.i("TAG", "onCheckedChanged: "+"Scaned GLib");

            }
        }
    }


    //Bank Statement List

    private void BankverticalList() {
        Log.i(TAG, "makeJsonObjReq1: "+"Make");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;

        J = new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
          //  J.put("relationship_type","0");
            J.put("relationship_type",Pref.getCoAPPAVAILABLE(getApplicationContext()));
            Log.i(TAG, "Banklist:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, "https://cscapi.loanwiser.in/mobile/partner_loanapi_test.php?call=bank_statementlist", J,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @SuppressLint("ResourceType")
                    @Override
                    public void onResponse(JSONObject object) {
                        JSONArray cast = null;
                        JSONArray ja = null;

                        try {

                            String s=object.getString("display_select");
                            Log.i(TAG, "onResponse: "+s);
                            ja = object.getJSONArray("bank_statementarr");
                            ja1 = object.getJSONArray("ban_statementlist");
                            Log.i(TAG, "onResponse:Value "+ja);
                            Log.i(TAG, "onResponse: Array"+ja.length());
                            if (ja.length()==0 && ja!=null){
                                recycleview_lay.setVisibility(View.GONE);
                                submitbanktxt_lay.setVisibility(View.GONE);
                                sub_banktextlay.setVisibility(View.GONE);
                                banklisttxt_lay.setVisibility(View.GONE);

                            }
                            for(int i = 0;i<ja.length();i++){
                                recycleview_lay.setVisibility(View.VISIBLE);
                                submitbanktxt_lay.setVisibility(View.VISIBLE);
                                JSONObject J = ja.getJSONObject(i);
                                String name=J.getString("name");
                                String url=J.getString("url");
                                items.add(new Bankitems(J.getString("name"),J.getString("url")));
                                statementlist.notifyDataSetChanged();
                                bankstatement_recycleview.setAdapter(statementlist);
                            }
                            Log.i(TAG, "onResponse: ja 1length"+ja1.length());
                            RadioButton button = new RadioButton(Upload_Activity_Bank.this);
                            button.setId(View.generateViewId());
                            button.setText("new");
                            Bank_Statement_radio_list.addView(button);

                            if (ja1!=null){
                                for (int i=0;i<ja1.length();i++){
                                    JSONObject J = ja1.getJSONObject(i);
                                   // String entity_id1 = J.getString("entity_id");
                                    String acc_number = J.getString("acc_number");
                                                  /*  Log.i(TAG, "onResponse:entity_id "+entity_id);
                                                    RadioButton rdbtn = new RadioButton(Upload_Activity_Bank.this);
                                                    rdbtn.setId(View.generateViewId());
                                                    rdbtn.setText(entity_id + rdbtn.getId());
                                                    //rdbtn.setOnClickListener(this);
                                                    Bank_Statement_radio_list.addView(rdbtn);*/
                                    RadioButton button1 = new RadioButton(Upload_Activity_Bank.this);
                                    button1.setId(View.generateViewId());
                                    button1.setText(acc_number);
                                    button1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            String selectedtext = button1.getText().toString();
                                            selected(selectedtext);


                                        }
                                    });
                                    // button1.setChecked(i == entity_ID_selected); // Only select button with same index as currently selected number of hours
                                    /// button.setBackgroundResource(R.drawable.ca); // This is a custom button drawable, defined in XML
                                    Bank_Statement_radio_list.addView(button1);
                                    view_analysisbut.setVisibility(View.VISIBLE);
                                }


                            }



                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String selectedtext = button.getText().toString();
                                    Log.e("the selected value is",selectedtext);
                                    entity_id="";
                                }
                            });

                            bankstatement_recycleview.setAdapter(statementlist);


                        } catch (JSONException ex) {
                            ex.printStackTrace();
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

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


    private void selected (String selectedtext)
    {
        Log.e("the selected value is",ja1.toString());
        for (int j=0;j<ja1.length();j++){
            try {
                JSONObject J = ja1.getJSONObject(j);
                String acc_number = J.getString("acc_number");
                if(selectedtext.equals(acc_number)){
                    String entity_id1 = J.getString("entity_id");
                    entity_id=entity_id1;

                    Log.e("the selected value is",entity_id);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    private void MultifileUploadRetrofit() {
        ApiConfig getResponse = Appconfig.getRetrofit().create(ApiConfig.class);
        Log.i(TAG, "MultifileUploadRetrofit: "+"check");
        progressDialog.show();
        // List<Uri> uriList = null; //These are the uris for the files to be uploaded
        MediaType mediaType = MediaType.parse("application/pdf");//Based on the Postman logs,it's not specifying Content-Type, this is why I've made this empty content/mediaType
        MultipartBody.Part[] fileParts = new MultipartBody.Part[uriarrayList.size()];
        for (int i = 0; i < uriarrayList.size(); i++) {



            String uri_test = uriarrayList.get(i).toString();
            Log.e("the Upload issues", uri_test);
            String filename = uri_test.substring(uri_test.lastIndexOf("/") + 1);
            String pdf1 = filename.substring(filename.lastIndexOf(".") + 1);
            //  uploadMultipart();
            String a = "pdf";

            if (Build.VERSION.SDK_INT < 11) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK >= 11 && SDK < 19
            else if (Build.VERSION.SDK_INT < 19) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK > 19 (Android 4.4) and up
            else {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API19(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // String path = String.valueOf(orginalFile);
            // pathlist.add(path);
            // uploadMultipart();
            //  MultifileUploadRetrofit();

            Log.e("path", String.valueOf(orginalFile));
            Log.e("Upload_Activity_Ban", String.valueOf(orginalFile));


            //File file = new File(uriarrayList.get(i).getPath());
            // File file = new File(orginalFile);
            RequestBody fileBody = RequestBody.create(mediaType, orginalFile);
            //Setting the file name as an empty string here causes the same issue, which is sending the request successfully without saving the files in the backend, so don't neglect the file name parameter.
            fileParts[i] = MultipartBody.Part.createFormData(String.format(Locale.ENGLISH, "img_url[%d]", i), orginalFile.getName(), fileBody);
            // fileParts[i] = MultipartBody.Part.createFormData("img_url", file.getName(), fileBody);
        }
        String transcation_id="60775";
        String analysis_status="1";
        String workorder_id="";

        String pdf_password=docpass_edt_txt.getText().toString().trim();
        final String relationship_type="1";
        RequestBody transaction_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), Pref.getTRANSACTIONID(getApplicationContext()));
        RequestBody analysis_status1 = RequestBody.create(MediaType.parse("multipart/form-data"), checkradiobutton_value);
        RequestBody workorder_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), entity_id);
        RequestBody entity_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), entity_id);
        RequestBody pdf_password1 = RequestBody.create(MediaType.parse("multipart/form-data"), pdf_password);
        RequestBody relationship_type1 = RequestBody.create(MediaType.parse("multipart/form-data"), Pref.getCoAPPAVAILABLE(getApplicationContext()));

        Call<UploadFileResponse> call =getResponse.submitNew("Auth Token", fileParts,transaction_id1,analysis_status1,workorder_id1,entity_id1,relationship_type1,pdf_password1);
        call.enqueue(new Callback<UploadFileResponse>() {
            @Override
            public void onResponse(Call<UploadFileResponse> call, retrofit2.Response<UploadFileResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    UploadFileResponse response1=(UploadFileResponse) response.body();
                    int status=response1.getResponse().getStatus();
                    bankstatement_msg=response1.getResponse().getMessage();
                    Log.i(TAG, "onResponse:status "+status);
                    if (status==1){
                        Submit_upload_sucess();
                    }else if (status==4){
                        bankstatement_msg=response1.getResponse().getMessage();
                        ErrorStatus();
                    }else if (status==5){
                        bankstatement_msg=response1.getResponse().getMessage();
                        ErrorStatus();
                    }else if (status==6){
                        bankstatement_msg=response1.getResponse().getMessage();
                        ErrorStatus();
                    }else if (status==7){
                        ErrorStatus();
                        bankstatement_msg=response1.getResponse().getMessage();
                    }
                }



            }
            @Override
            public void onFailure(Call<UploadFileResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Upload_Activity_Bank.this,"Network issue",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: "+t.getMessage());
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


    private void Progressview(){
        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());
        loader.addView(loader);
    }


    private void BankItemhorizontallist() {
        Log.i(TAG, "makeJsonObjReq1: "+"Make");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        J = new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            //J.put("transaction_id","60775");
           // J.put("relationship_type","0");
            J.put("relationship_type",Pref.getCoAPPAVAILABLE(getApplicationContext()));
            Log.i(TAG, "Bankitems:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, "https://cscapi.loanwiser.in/mobile/partner_loanapi_test.php?call=bank_statementlist", J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        JSONArray cast = null;
                        JSONArray ja = null;
                        JSONArray ja1=null;
                        try {
                            String s=object.getString("display_select");
                            Log.i(TAG, "onResponse: "+s);
                            ja = object.getJSONArray("ban_statementlist");
                            if (ja!=null && ja.length() > 0){
                                banklisttxt_lay.setVisibility(View.VISIBLE);
                                bankhorizontallist.setVisibility(View.VISIBLE);
                                ja = object.getJSONArray("ban_statementlist");
                                for(int i = 0;i<ja.length();i++){
                                    JSONObject J = ja.getJSONObject(i);
                                    String acc_number=J.getString("acc_number");
                                    String entity_id=J.getString("entity_id");
                                    String bank_name=J.getString("bank_name");
                                    String achold_name=J.getString("acchold_name");
                                    String status_int=J.getString("status_int");
                                    items1.add(new Bankdetails_model(J.getString("acc_number"),J.getString("entity_id"),J.getString("bank_name"),J.getString("acchold_name"),J.getString("status_int")));
                                    recyclerView.setLayoutManager(new LinearLayoutManager(Upload_Activity_Bank.this, LinearLayoutManager.HORIZONTAL, false));
                                    recyclerView.setHasFixedSize(true);
                                    banklistAdapter = new BanklistAdapter(Upload_Activity_Bank.this, items1);
                                    recyclerView.setAdapter(banklistAdapter);
                                    banklistAdapter.notifyDataSetChanged();

                                }
                            }
                            else{
                                Log.i(TAG, "arraynull: "+"arraynull");
                                bankhorizontallist.setVisibility(View.GONE);
                                banklisttxt_lay.setVisibility(View.GONE);
                                submitbanktxt_lay.setVisibility(View.GONE);
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
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

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }


/*    private void BankverticalList() {
        Log.i(TAG, "makeJsonObjReq1: "+"Make");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        JSONArray ja1 = null;
        J = new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("relationship_type","0");
            Log.i(TAG, "Banklist:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, "https://cscapi.loanwiser.in/mobile/partner_loanapi_test.php?call=bank_statementlist", J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        JSONArray cast = null;
                        JSONArray ja = null;
                        JSONArray ja1 = null;
                        try {
                            String s=object.getString("display_select");
                            Log.i(TAG, "onResponse: "+s);
                            ja = object.getJSONArray("bank_statementarr");
                            ja1 = object.getJSONArray("ban_statementlist");
                            Log.i(TAG, "onResponse:Value "+ja);
                            Log.i(TAG, "onResponse: Array"+ja.length());
                            if (ja.length()==0){
                                recycleview_lay.setVisibility(View.GONE);
                                submitbanktxt_lay.setVisibility(View.GONE);
                            }
                            for(int i = 0;i<ja.length();i++){
                                recycleview_lay.setVisibility(View.VISIBLE);
                                submitbanktxt_lay.setVisibility(View.VISIBLE);
                                JSONObject J = ja.getJSONObject(i);
                                String name=J.getString("name");
                                String url=J.getString("url");
                                items.add(new Bankitems(J.getString("name"),J.getString("url")));
                                bankstatement_recycleview.setAdapter(statementlist);
                                statementlist.notifyDataSetChanged();
                            }
                            if(ja1.length()==0)
                            {
                                Log.e("BankverticalList"," executed");
                                Log.e("ja",ja.toString());
                                final RadioButton button = new RadioButton(Upload_Activity_Bank.this);
                                button.setId(0);
                                button.setText("new");
                                Log.i(TAG, "onResponse: beforeloop"+"value come");
                                Bank_Statement_radio_list.addView(button);
                                for (int i = 0; i < ja1.length(); i++) {
                                    Log.i(TAG, "onResponse: afterloop"+"value not come");
                                    JSONObject J = ja1.getJSONObject(i);
                                    String entity_id=J.getString("entity_id");
                                    Log.i(TAG, "onResponse:entity_id "+entity_id);
                                    final RadioButton button1 = new RadioButton(Upload_Activity_Bank.this);
                                    button1.setId(i);
                                    button1.setText(entity_id);
                                    // button1.setChecked(i == entity_ID_selected); // Only select button with same index as currently selected number of hours
                                    /// button.setBackgroundResource(R.drawable.ca); // This is a custom button drawable, defined in XML
                                    Bank_Statement_radio_list.addView(button1);
                                    button1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            selectedtext = button1.getText().toString();
                                            Log.e("the selected value is",selectedtext);
                                        }
                                    });
                                }
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        selectedtext = button.getText().toString();
                                        Log.e("the selected value is",selectedtext);
                                    }
                                });


                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
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
            *//**
     * Passing some request headers
     * *//*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void BankItemhorizontallist() {
        Log.i(TAG, "makeJsonObjReq1: "+"Make");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        J = new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            //J.put("transaction_id","60775");
            J.put("relationship_type","0");
            Log.i(TAG, "Bankitems:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, "https://cscapi.loanwiser.in/mobile/partner_loanapi_test.php?call=bank_statementlist", J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        JSONArray cast = null;
                        JSONArray ja = null;
                        JSONArray ja1=null;
                        try {
                            String s=object.getString("display_select");
                            Log.i(TAG, "onResponse: "+s);
                            ja = object.getJSONArray("ban_statementlist");
                            if (ja!=null){
                                ja = object.getJSONArray("ban_statementlist");
                                for(int i = 0;i<ja.length();i++){
                                    JSONObject J = ja.getJSONObject(i);
                                    String acc_number=J.getString("acc_number");
                                    String entity_id=J.getString("entity_id");
                                    String bank_name=J.getString("bank_name");
                                    String achold_name=J.getString("acchold_name");
                                    items1.add(new Bankdetails_model(J.getString("acc_number"),J.getString("entity_id"),J.getString("bank_name"),J.getString("acchold_name")));
                                    recyclerView.setLayoutManager(new LinearLayoutManager(Upload_Activity_Bank.this, LinearLayoutManager.HORIZONTAL, false));
                                    recyclerView.setHasFixedSize(true);
                                    banklistAdapter = new BanklistAdapter(Upload_Activity_Bank.this, items1);
                                    recyclerView.setAdapter(banklistAdapter);
                                    banklistAdapter.notifyDataSetChanged();
                                }
                            }
                            else{
                                Log.i(TAG, "arraynull: "+"arraynull");
                                bankhorizontallist.setVisibility(View.GONE);
                                submitbanktxt_lay.setVisibility(View.GONE);
                            }
                            if(ja.length()<0)
                            {
                                Log.e("BankItemhorizontallist"," executed");
                                Log.e("ja",ja.toString());
                                final RadioButton button = new RadioButton(Upload_Activity_Bank.this);
                                button.setId(0);
                                button.setText("New");
                                Bank_Statement_radio_list.addView(button);
                                button.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        selectedtext="new";
                                    }
                                });
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
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
            *//**
     * Passing some request headers
     * *//*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }*/


    private void ErrorStatus() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.bankstatement_error);
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

    private void GlibErrorStatus() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.glibstatement_error);
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



  /*  private void MultifileUploadRetrofitGlib(){
        final ApiConfig getResponse = Appconfig.getRetrofit().create(ApiConfig.class);
        Log.e(TAG, "MultifileUploadRetrofit: "+"check");
        progressDialog.show();
        // List<Uri> uriList = null; //These are the uris for the files to be uploaded
        MediaType mediaType = MediaType.parse("application/pdf");//Based on the Postman logs,it's not specifying Content-Type, this is why I've made this empty content/mediaType
        MultipartBody.Part[] fileParts = new MultipartBody.Part[uriarrayList.size()];
        for (int i = 0; i < uriarrayList.size(); i++) {
            //  String filePath =getRealPathFromUri(uriarrayList.get(i));
            String uri_test = uriarrayList.get(i).toString();
            Log.e("the Upload issues", uri_test);
            String filename = uri_test.substring(uri_test.lastIndexOf("/") + 1);
            String pdf1 = filename.substring(filename.lastIndexOf(".") + 1);
            //  uploadMultipart();
            String a = "pdf";
            if (Build.VERSION.SDK_INT < 11) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK >= 11 && SDK < 19
            else if (Build.VERSION.SDK_INT < 19) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK > 19 (Android 4.4) and up
            else {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API19(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // String path = String.valueOf(orginalFile);
            // pathlist.add(path);
            // uploadMultipart();
            //  MultifileUploadRetrofit();
            //  Log.e("path", String.valueOf(orginalFile));
            //  Log.e("Upload_Activity_Ban", String.valueOf(orginalFile));
            //File file = new File(uriarrayList.get(i).getPath());
            //  File file = new File(filePath);
            RequestBody fileBody = RequestBody.create(mediaType, orginalFile);
            //Setting the file name as an empty string here causes the same issue, which is sending the request successfully without saving the files in the backend, so don't neglect the file name parameter.
            fileParts[i] = MultipartBody.Part.createFormData(String.format(Locale.ENGLISH, "img_url[%d]", i), orginalFile.getName(), fileBody);
            // fileParts[i] = MultipartBody.Part.createFormData("img_url", file.getName(), fileBody);
        }
        String transcation_id="60775";
        String analysis_status="1";
        String workorder_id="";
        String entity_id="";
        String pdf_password=docpass_edt_txt.getText().toString().trim();
        final String relationship_type="1";
        RequestBody transaction_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), Pref.getTRANSACTIONID(getApplicationContext()));
        RequestBody analysis_status1 = RequestBody.create(MediaType.parse("multipart/form-data"), checkradiobutton_value);
        RequestBody workorder_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), workorder_id);
        RequestBody entity_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), entity_id);
        RequestBody pdf_password1 = RequestBody.create(MediaType.parse("multipart/form-data"), pdf_password);
        RequestBody relationship_type1 = RequestBody.create(MediaType.parse("multipart/form-data"), relationship_type);
        Call<GlibResponse> call =getResponse.submitNews("Auth Token", fileParts,transaction_id1,analysis_status1,workorder_id1,entity_id1,relationship_type1,pdf_password1);
        call.enqueue(new Callback<GlibResponse>() {
            @Override
            public void onResponse(Call<GlibResponse> call, retrofit2.Response<GlibResponse> response) {
                progressDialog.dismiss();
                Log.i(TAG, "onResponse:Glib "+"Check Glib status");
                if (response.isSuccessful()){
                    GlibResponse response2=(GlibResponse) response.body();
                    String status=response2.getResponse().getStatus();
                    if (status!=null && status!=""){
                        if (status.equalsIgnoreCase("success")){
                            String workorder=response2.getResponse().getWORKORDERID();
                            Log.i(TAG, "onResponse: workorder"+workorder);
                            String msg=response2.getResponse().getMSG();
                            if (msg.equalsIgnoreCase("Workorder Created")){
                                GlibAPICheck(workorder);
                            }
                        }
                        else if (status.equalsIgnoreCase("invalid_bankstatement")){
                            GlibErrorStatus();
                        }
                    }else{
                        ErrorStatus();
                    }
                }
            }
            @Override
            public void onFailure(Call<GlibResponse> call, Throwable t) {
                Toast.makeText(Upload_Activity_Bank.this,"Network issue",Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }*/
/*  private void MultifileUploadRetrofitGlib(){
      final ApiConfig getResponse = Appconfig.getRetrofit().create(ApiConfig.class);
      Log.i(TAG, "MultifileUploadRetrofit: "+"check");
      progressDialog.show();
      // List<Uri> uriList = null; //These are the uris for the files to be uploaded
      MediaType mediaType = MediaType.parse("application/pdf");//Based on the Postman logs,it's not specifying Content-Type, this is why I've made this empty content/mediaType
      MultipartBody.Part[] fileParts = new MultipartBody.Part[uriarrayList.size()];
      for (int i = 0; i < uriarrayList.size(); i++) {
          String filePath =getRealPathFromUri(uriarrayList.get(i));
          //File file = new File(uriarrayList.get(i).getPath());
          File file = new File(filePath);
          RequestBody fileBody = RequestBody.create(mediaType, file);
          //Setting the file name as an empty string here causes the same issue, which is sending the request successfully without saving the files in the backend, so don't neglect the file name parameter.
          fileParts[i] = MultipartBody.Part.createFormData(String.format(Locale.ENGLISH, "img_url[%d]", i), file.getName(), fileBody);
          // fileParts[i] = MultipartBody.Part.createFormData("img_url", file.getName(), fileBody);
      }
      String transcation_id="60775";
      String analysis_status="1";
      String workorder_id="";
       entity_id="";
      String pdf_password=docpass_edt_txt.getText().toString().trim();
      final String relationship_type="1";
      RequestBody transaction_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), Pref.getTRANSACTIONID(getApplicationContext()));
      RequestBody analysis_status1 = RequestBody.create(MediaType.parse("multipart/form-data"), checkradiobutton_value);
      RequestBody workorder_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), entity_id);
      RequestBody entity_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), entity_id);
      RequestBody pdf_password1 = RequestBody.create(MediaType.parse("multipart/form-data"), pdf_password);
      RequestBody relationship_type1 = RequestBody.create(MediaType.parse("multipart/form-data"), relationship_type);
      Call<GlibResponse> call =getResponse.submitNews("Auth Token", fileParts,transaction_id1,analysis_status1,workorder_id1,entity_id1,relationship_type1,pdf_password1);
      call.enqueue(new Callback<GlibResponse>() {
          @Override
          public void onResponse(Call<GlibResponse> call, retrofit2.Response<GlibResponse> response) {
              progressDialog.dismiss();
              Log.i(TAG, "onResponse:Glib "+"Check Glib status");
              if (response.isSuccessful()){
                  GlibResponse response2=(GlibResponse) response.body();
                  String status=response2.getResponse().getStatus();
                  if (status!=null && status!=""){
                      if (status.equalsIgnoreCase("success")){
                          String workorder=response2.getResponse().getWORKORDERID();
                          Log.i(TAG, "onResponse: workorder"+workorder);
                          String msg=response2.getResponse().getMSG();
                          if (msg.equalsIgnoreCase("Workorder Created")){
                              GlibAPICheck(workorder);
                          }
                      }
                      else if (status.equalsIgnoreCase("invalid_bankstatement")){
                          GlibErrorStatus();
                      }
                  }else{
                      ErrorStatus();
                  }
              }
          }
          @Override
          public void onFailure(Call<GlibResponse> call, Throwable t) {
              Toast.makeText(Upload_Activity_Bank.this,"Network issue",Toast.LENGTH_SHORT).show();
              Log.i(TAG, "onFailure: "+t.getMessage());
          }
      });
  }*/

    private void MultifileUploadRetrofitGlib(){
        final ApiConfig getResponse = Appconfig.getRetrofit().create(ApiConfig.class);
        Log.i(TAG, "MultifileUploadRetrofit: "+"check");
        progressDialog.show();
        // List<Uri> uriList = null; //These are the uris for the files to be uploaded
        MediaType mediaType = MediaType.parse("application/pdf");//Based on the Postman logs,it's not specifying Content-Type, this is why I've made this empty content/mediaType
        MultipartBody.Part[] fileParts = new MultipartBody.Part[uriarrayList.size()];
        for (int i = 0; i < uriarrayList.size(); i++) {
            //  String filePath =getRealPathFromUri(uriarrayList.get(i));
            String uri_test = uriarrayList.get(i).toString();
            Log.e("the Upload issues", uri_test);
            String filename = uri_test.substring(uri_test.lastIndexOf("/") + 1);
            String pdf1 = filename.substring(filename.lastIndexOf(".") + 1);
            //  uploadMultipart();
            String a = "pdf";
            if (Build.VERSION.SDK_INT < 11) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK >= 11 && SDK < 19
            else if (Build.VERSION.SDK_INT < 19) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK > 19 (Android 4.4) and up
            else {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API19(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // String path = String.valueOf(orginalFile);
            // pathlist.add(path);
            // uploadMultipart();
            //  MultifileUploadRetrofit();
            //  Log.e("path", String.valueOf(orginalFile));
            //  Log.e("Upload_Activity_Ban", String.valueOf(orginalFile));
            //File file = new File(uriarrayList.get(i).getPath());
            //  File file = new File(filePath);
            RequestBody fileBody = RequestBody.create(mediaType, orginalFile);
            //Setting the file name as an empty string here causes the same issue, which is sending the request successfully without saving the files in the backend, so don't neglect the file name parameter.
            fileParts[i] = MultipartBody.Part.createFormData(String.format(Locale.ENGLISH, "img_url[%d]", i), orginalFile.getName(), fileBody);
            // fileParts[i] = MultipartBody.Part.createFormData("img_url", file.getName(), fileBody);
        }
        String transcation_id="60775";
        String analysis_status="1";
        String workorder_id="";

        String pdf_password=docpass_edt_txt.getText().toString().trim();
        final String relationship_type="1";
        RequestBody transaction_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), Pref.getTRANSACTIONID(getApplicationContext()));
        RequestBody analysis_status1 = RequestBody.create(MediaType.parse("multipart/form-data"), checkradiobutton_value);
        RequestBody workorder_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), entity_id);
        RequestBody entity_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), entity_id);
        RequestBody pdf_password1 = RequestBody.create(MediaType.parse("multipart/form-data"), pdf_password);
        RequestBody relationship_type1 = RequestBody.create(MediaType.parse("multipart/form-data"), Pref.getCoAPPAVAILABLE(getApplicationContext()));
        Call<GlibResponse> call =getResponse.submitNews("Auth Token", fileParts,transaction_id1,analysis_status1,workorder_id1,entity_id1,relationship_type1,pdf_password1);
        call.enqueue(new Callback<GlibResponse>() {
            @Override
            public void onResponse(Call<GlibResponse> call, retrofit2.Response<GlibResponse> response) {
                progressDialog.dismiss();
                Log.i(TAG, "onResponse:Glib "+"Check Glib status");
                Log.e("the GLIP Passes", String.valueOf(response));
                if (response.isSuccessful()){
                    GlibResponse response2=(GlibResponse) response.body();
                    String status=response2.getResponse().getStatus();
                    if (status!=null && status!=""){
                        if (status.equalsIgnoreCase("success")){
                            String workorder=response2.getResponse().getWORKORDERID();
                            Log.i(TAG, "onResponse: workorder"+workorder);
                            String msg=response2.getResponse().getMSG();
                            if (msg.equalsIgnoreCase("Workorder Created")){
                                GlibAPICheck(workorder);
                            }
                        }
                        else if (status.equalsIgnoreCase("invalid_bankstatement")){
                            GlibErrorStatus();
                        }
                    }else{
                        ErrorStatus();
                    }
                }
            }
            @Override
            public void onFailure(Call<GlibResponse> call, Throwable t) {
                Toast.makeText(Upload_Activity_Bank.this,"Network issue",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: "+t.getMessage());
                Log.e("the GLIP failur",t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void GlibAPICheck(final String entity) {
        Log.i(TAG, "Which API: "+"Glib");
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        J = new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            //  J.put("transaction_id", "61854");
            J.put("typecnt","0");
            //   J.put("entity_id","20210223141934HULZD8CB");
            J.put("entity_id",entity);
            Log.i(TAG, "glib:Request "+J.toString());
            progressDialog.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, "https://cscapi.loanwiser.in/integration/bank_statement.php?call=sift_statuscheck", J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject object) {
                        JSONArray cast = null;
                        JSONArray ja = null;
                        JSONArray ja1=null;
                        try {
                            int status=object.getInt("status");
                            Log.i(TAG, "statusvalue: "+status);
                            Log.i(TAG, "GlibonResponse: "+status);
                            if (status==3){
                                progressDialog.dismiss();
                                Log.i(TAG, "GlibonResponse:1"+status);
                                GlibErrorStatus();
                            }else if (status==5){
                                progressDialog.dismiss();
                                Toast.makeText(Upload_Activity_Bank.this,"Success",Toast.LENGTH_SHORT).show();
                                Bank_statues();
                        /*    Intent intent=new Intent(Upload_Activity_Bank.this,BankAnalysis.class);
                            intent.putExtra("fromglib","fromglib");
                            intent.putExtra("glibentityid",entity);
                            startActivity(intent);*/
                            }else{
                                progressDialog.show();
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Do something after 20 seconds
                                        GlibAPICheck(entity);
                                        // handler.postDelayed(this, 10000);
                                    }
                                }, 20000);
                            }
                            //progressDialog.dismiss();
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                        // progressDialog.dismiss();
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
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(mCon, DashBoard_new.class);
        startActivity(intent);
        // Objs.ac.StartActivity(mCon, LeadeFragment.class);
        finish();
        super.onBackPressed();

    }
}

