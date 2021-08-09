package in.loanwiser.partnerapp.BankStamentUpload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;

import net.gotev.uploadservice.MultipartUploadRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import eu.inmite.android.lib.validations.form.iface.IFieldAdapter;
import in.loanwiser.partnerapp.BankStamentUpload.modelglib.GlibResponse;
import in.loanwiser.partnerapp.Documents.Applicant_Doc_Details_revamp;
import in.loanwiser.partnerapp.Documents.SingleUploadBroadcastReceiver;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.ExpandableListAdapter1;
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

import static adhoc.app.applibrary.Config.AppUtils.Urls.bank_statementlist_uat;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;
import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.getDataColumn;
import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.isDownloadsDocument;
import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.isExternalStorageDocument;
import static in.loanwiser.partnerapp.BankStamentUpload.FilePath.isMediaDocument;
import static in.loanwiser.partnerapp.BankStamentUpload.FileUtils1.isGooglePhotosUri;

public class Upload_Activity_Bank extends SimpleActivity implements  View.OnClickListener, CompoundButton.OnCheckedChangeListener, SingleUploadBroadcastReceiver.Delegate, PickiTCallbacks {

    private static final int PICK_PDF_REQUEST = 1;
    private static final String TAG =Upload_Activity_Bank.class.getSimpleName() ;
    Typeface font;
    RecyclerView recyclerView,bankstatement_recycleview;
    ListView listview;
    LinearLayout listviewlay;
    AppCompatButton upload, submit;
    Button view_analysisbut;
    private Uri filePath;
    private Uri fileUri;
    private String fileName;
    private List<String> fileNameList;
    private List<String> fileDoneList;
    ArrayList<Uri> uriarrayList = new ArrayList<>();
    ArrayList<File> uriarrayList_pic;
    List<Uri> uriarraylist1;
    ArrayList<String> pathlist;
    private android.app.AlertDialog progressDialog;
    FileAdapter fileAdapter;
    String[] stringarray;
    File orginalFile = null;
    File orginalFile_pic = null;
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

    JSONArray ja1,ja1_bank_list;
    LinearLayout Password_ly;

    Spinner spinn_uploading_for,account_type_spnr,password_protedted_spnr,banK_stm_type_spnr,
            Bank_name_spnr;


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
    //Permissions
    private static final int SELECT_VIDEO_REQUEST = 777;
    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 22;
    private static final int PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE = PERMISSION_REQ_ID_RECORD_AUDIO + 1;

    //Declare PickiT
    PickiT pickiT;

    String[] You_ur_uploading_for,BANK_list,Bank_statement_type,Bank_accounty_type,
    is_password_protected,Bank_Name;

    String  account_number_edit_txt_,Password_edit_txt_;
    ArrayAdapter<String> Your_Uploading_Bank,Bank_Name_dapter,Bank_statemt_type_adapter,
            Bank_accounty_type_adapter,Is_password_protected_adapter,Bank_Name_adapter;

    AutoCompleteTextView bank_name_auto_complete;

    AppCompatEditText account_number_edit_txt,Password_edit_txt;

    String your_uploading_for_ID,your_uploading_for_value,Bank_stm_type_account_ID,Bank_stm_type_account_value,
    Bank_accounty_type_id,Bank_accounty_type_value,Is_Password_protected_id,Is_Password_protected_value,
            Bank_Name_id,Bank_Name_value;
    String bank_accountno,bank_name,bank_status,bank_type,bank_pass;

    LinearLayout your_uploadin_ly;
    LinearLayout Bank_Name_sp,bank_Name_edt,ac_typ_spin,ac_type_edt,
            bank_state_type_sp,bank_state_type_edt,pass_word_sp,pass_word_edt,
            upload_section;
    JSONArray bank_list, bankstacc_type,bankststatement_type;
    AppCompatEditText bank_name_edt,account_type_edit_txt,bank_state_type_edt_txt,
            pass_word_edt_txt;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.activity_upload___bank);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_bank_statement);
        initTools(R.string.bank_statment);

        upload = findViewById(R.id.uploads);
        bank_state_type_sp = findViewById(R.id.bank_state_type_sp);
        bank_state_type_edt = findViewById(R.id.bank_state_type_edt);
        bank_Name_edt = findViewById(R.id.bank_Name_edt);
        bank_name_edt = findViewById(R.id.bank_name_edt);
        bank_state_type_edt_txt = findViewById(R.id.bank_state_type_edt_txt);
        pass_word_edt_txt = findViewById(R.id.pass_word_edt_txt);
        pass_word_edt = findViewById(R.id.pass_word_edt);
        pass_word_sp = findViewById(R.id.pass_word_sp);
        upload_section = findViewById(R.id.upload_section);
        Bank_Name_sp = findViewById(R.id.Bank_Name_sp);

        ac_typ_spin = findViewById(R.id.ac_typ_spin);
        ac_type_edt = findViewById(R.id.ac_type_edt);
        account_type_edit_txt = findViewById(R.id.account_type_edit_txt);
        listview = findViewById(R.id.listview);
        listviewlay = findViewById(R.id.listviewlay);
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
        spinn_uploading_for=findViewById(R.id.spinn_uploading_for);
        account_type_spnr=findViewById(R.id.account_type_spnr);
        banK_stm_type_spnr=findViewById(R.id.banK_stm_type_spnr);
        password_protedted_spnr=findViewById(R.id.password_protedted_spnr);
        Bank_name_spnr=findViewById(R.id.Bank_name_spnr);
        Password_ly=findViewById(R.id.Password_ly);
        account_number_edit_txt=findViewById(R.id.account_number_edit_txt);
        Password_edit_txt=findViewById(R.id.Password_edit_txt);
        your_uploadin_ly=findViewById(R.id.your_uploadin_ly);
        //LineChartView lineChartView = findViewById(R.id.helolinechart);

        bank_name_auto_complete = (AppCompatAutoCompleteTextView) findViewById(R.id.bank_name_auto_complete);
        makeJsonObjReq1();

        Bank_Name_Searc();
        bank_name_auto_complete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String workpincode = bank_name_auto_complete.getText().toString();

                if(workpincode.length()> 2){
                 //   Bank_Name_Searc(workpincode);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("hi","hi11");

                // imm.hideSoftInputFromWindow(company_pincode_txt.getWindowToken(), 0);
            }
        });

        submitbanktxt_lay=findViewById(R.id.submitbanktxt_lay);
        recyclerView=(RecyclerView)findViewById(R.id.banklist_recycleview);
        entity_id="";
        items1=new ArrayList<>();
        pickiT = new PickiT(this, this, this);
        loader=findViewById(R.id.loader);
        uriarrayList_pic = new ArrayList<>();
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
                uriarrayList_pic.clear();
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


        Loan_submit_statues();

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
        if (checkSelfPermission()) {
            Intent intent = new Intent();
            intent.setType("application/pdf");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_REQUEST);
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK) {
            listviewlay.setVisibility(View.VISIBLE);
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

                    pickiT.getPath(data.getClipData().getItemAt(i).getUri(), Build.VERSION.SDK_INT);
                    // fileDoneList.add("uploading");

                    Log.e("the URI SIZE", String.valueOf(uriarrayList.size()));
                    Log.i("TAG", "onActivityResult:uriarrayList " + uriarrayList);
                    Log.i("Upload_Activity_Bank", "onActivityResult_fileuri: " + fileUri);
                    Log.i("Upload_Activity_Bank", "onActivityResult_path: " + fileget);
                    Log.i("Upload_Activity_Bank", "ActivityResult_filename: " + fileName);
                }
            } else {
                listviewlay.setVisibility(View.VISIBLE);
                fileUri = data.getData();
                fileget = String.valueOf(data.getData());
                fileName = getFileName(data.getData());
                fileNameList.add(fileName);
                uriarrayList.add(fileUri);
                pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);
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

                if(ja1_bank_list.length()==0)
                {
                    your_uploading_for_ID ="New";
                    bank_new();
                }else
                {
                    if(your_uploading_for_ID.equals("New"))
                    {
                        bank_new();
                    }else
                    {
                        bank_statement_upload();
                    }

                }



                //
                break;


        }
    }

    public void bank_new()
    {
                    if (Bank_Name_id.equals("0")) {
                        Toast.makeText(this, "Select Bank Name", Toast.LENGTH_SHORT).show();

                    } else {
                        if (!validate_account_NO()) {
                            return;
                        }
                        if (Bank_accounty_type_id.equals("0")) {
                            Toast.makeText(this, "Select bank Account Type", Toast.LENGTH_SHORT).show();

                        } else {
                            if (Bank_stm_type_account_ID.equals("0")) {
                                Toast.makeText(this, "Select bank Statement Type", Toast.LENGTH_SHORT).show();

                            } else {

                                if (Is_Password_protected_id.equals("0")) {
                                    Toast.makeText(this, "Select Password protected?", Toast.LENGTH_SHORT).show();

                                } else if (Is_Password_protected_id.equals("1")) {
                                    if (!validate_password()) {
                                        return;
                                    }
                                    bank_statement_upload();
                                } else if (Is_Password_protected_id.equals("2")) {
                                    bank_statement_upload();
                                }


                            }
                        }

                    //
                }
    }
    public void bank_statement_upload()
    {

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

                        for(i=0;i<uriarrayList_pic.size();i++)
                        {
                            orginalFile_pic = uriarrayList_pic.get(i);


                            String path = String.valueOf(orginalFile_pic);

                            pathlist.add(path);

                            long fileSizeInBytes = orginalFile_pic.length();
                            long fileSizeInKB = fileSizeInBytes / 1024;
                            long fileSizeInMB = fileSizeInKB / 1024;
                            Log.e("the Size is", String.valueOf(fileSizeInMB));
                            if (fileSizeInMB < 3) {
                                CheckUploadcondition();
                            }else
                            {
                                pdf_error();
                                // Toast.makeText(this,"Please upload file less than 3MB.",Toast.LENGTH_SHORT).show();

                            }

                            Log.e("Activity_Ban path1", String.valueOf(orginalFile_pic));
                        }
                                               /* if (SDK_INT < 11) {
                                                    orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(Upload_Activity_Bank.this, uriarrayList.get(i)));
                                                }
                                                // SDK >= 11 && SDK < 19
                                                else if (SDK_INT < 19) {
                                                    orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(Upload_Activity_Bank.this, uriarrayList.get(i)));
                                                }
                                                // SDK > 19 (Android 4.4) and up
                                                else {
                                                    Log.e("path", "api 19 called");
                                                    orginalFile = new File(FileUtils1.getRealPathFromURI_API19(Upload_Activity_Bank.this, uriarrayList.get(i)));

                                                }
                                                String path = String.valueOf(orginalFile);

                                                pathlist.add(path);

                                                long fileSizeInBytes = orginalFile.length();
                                                long fileSizeInKB = fileSizeInBytes / 1024;
                                                long fileSizeInMB = fileSizeInKB / 1024;
                                                Log.e("the Size is", String.valueOf(fileSizeInMB));
                                                if (fileSizeInMB < 3) {

                                                    CheckUploadcondition();
                                                }else
                                                {
                                                    pdf_error();

                                                    // Toast.makeText(this,"Please upload file less than 3MB.",Toast.LENGTH_SHORT).show();

                                                }
                                                // uploadMultipart();

                                                //  MultifileUploadRetrofit();

                                                Log.e("path", path);
                                                Log.e("Upload_Activity_Ban", String.valueOf(orginalFile));*/

                    } else {


                        for(i=0;i<uriarrayList_pic.size();i++)
                        {
                            orginalFile_pic = uriarrayList_pic.get(i);


                            String path = String.valueOf(orginalFile_pic);

                            pathlist.add(path);

                            long fileSizeInBytes = orginalFile_pic.length();
                            long fileSizeInKB = fileSizeInBytes / 1024;
                            long fileSizeInMB = fileSizeInKB / 1024;
                            Log.e("the Size is", String.valueOf(fileSizeInMB));
                            if (fileSizeInMB < 3) {
                                CheckUploadcondition();
                            }else
                            {
                                pdf_error();
                                // Toast.makeText(this,"Please upload file less than 3MB.",Toast.LENGTH_SHORT).show();

                            }
                            // uploadMultipart();

                            //  MultifileUploadRetrofit();

                            //  Log.e("path", orginalFile_pic);
                            Log.e("Activity_Ban path", String.valueOf(orginalFile_pic));
                        }
                    }


                }
            }

    }


    private void CheckUploadcondition() {



          if(your_uploading_for_ID.equals("New"))
           {
               Log.e("uploading for ",your_uploading_for_ID);
               Log.e("Bank_Name",Bank_Name_id);

               account_number_edit_txt_ = account_number_edit_txt.getText().toString();
               Password_edit_txt_ = Password_edit_txt.getText().toString();
               Log.e("account_numbe",account_number_edit_txt_);
               Log.e("accounty_type",Bank_accounty_type_id);
               Log.e("bank stam type",Bank_stm_type_account_ID);
               Log.e("Is_Password_prot",Is_Password_protected_id);
               Log.e("Password_edit_txt_",Password_edit_txt_);
               MultifileUploadRetrofit();
           }else
          {

              account_number_edit_txt_ = bank_accountno;
              Password_edit_txt_ = bank_pass;
              Bank_Name_id = bank_name;
              Bank_stm_type_account_ID =bank_type;
              MultifileUploadRetrofit();

          }


       /* if (checkradiobutton_value.equalsIgnoreCase("1")){
            MultifileUploadRetrofit();
        }else{
            MultifileUploadRetrofitGlib();
        }*/
    }



    private void makeJsonObjReq1() {
        JSONObject J= null;
        try {
            J = new JSONObject();
            J.put("name","name");

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        Log.e("state_id", String.valueOf(J));

        progressDialog.show();
        Log.e("Request Dreopdown", "called");
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Get_Dropdown, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {

                        progressDialog.dismiss();

                        Log.e("respose Dreopdown", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));
                        try {

                            bankstacc_type =object.getJSONArray("bankstacc_type");
                            bankststatement_type =object.getJSONArray("bankststatement_type");
                           JSONArray is_passprotect =object.getJSONArray("is_passprotect");
                            Bank_statement_type(bankststatement_type);
                            Bank_account_type(bankstacc_type);
                            IS_password_protected(is_passprotect);
                          //  Log.e("Business_type_own_busin",String.valueOf(Business_type_own_business));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {

            /**
             * Passing some request headers
             * */
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

    private void Your_uploading_for(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        You_ur_uploading_for = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            You_ur_uploading_for[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(You_ur_uploading_for));
            Your_Uploading_Bank = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            Your_Uploading_Bank.setDropDownViewResource(R.layout.view_spinner_item);
            spinn_uploading_for.setAdapter(Your_Uploading_Bank);
            spinn_uploading_for.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        your_uploading_for_ID = has_pancard_ar.getJSONObject(position).getString("id");
                        your_uploading_for_value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

                        if(your_uploading_for_ID.equals("New"))
                        {
                            Bank_Name_sp.setVisibility(View.VISIBLE);
                            bank_Name_edt.setVisibility(View.GONE);

                            ac_typ_spin.setVisibility(View.VISIBLE);
                             ac_type_edt.setVisibility(View.GONE);

                            bank_state_type_sp.setVisibility(View.VISIBLE);
                            bank_state_type_edt.setVisibility(View.GONE);

                            pass_word_sp.setVisibility(View.VISIBLE);
                            pass_word_edt.setVisibility(View.GONE);
                            upload_section.setVisibility(View.VISIBLE);
                        }else
                        {
                            upload_section.setVisibility(View.VISIBLE);
                            Bank_Name_sp.setVisibility(View.GONE);
                            bank_Name_edt.setVisibility(View.VISIBLE);
                            bank_name_edt.setEnabled(false);
                            account_number_edit_txt.setEnabled(false);

                            ac_typ_spin.setVisibility(View.GONE);
                            ac_type_edt.setVisibility(View.VISIBLE);
                            account_type_edit_txt.setEnabled(false);

                            bank_state_type_sp.setVisibility(View.GONE);
                            bank_state_type_edt.setVisibility(View.VISIBLE);
                            bank_state_type_edt_txt.setEnabled(false);

                            pass_word_sp.setVisibility(View.GONE);
                            pass_word_edt.setVisibility(View.VISIBLE);
                            pass_word_edt_txt.setEnabled(false);

                            Bank_statement_Vale(your_uploading_for_value);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinn_uploading_for.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Bank_statement_type(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Bank_statement_type = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            Bank_statement_type[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Bank_statement_type));
            Bank_statemt_type_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            Bank_statemt_type_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            banK_stm_type_spnr.setAdapter(Bank_statemt_type_adapter);
            banK_stm_type_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Bank_stm_type_account_ID = has_pancard_ar.getJSONObject(position).getString("id");
                        Bank_stm_type_account_value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            banK_stm_type_spnr.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Bank_account_type(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Bank_accounty_type = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            Bank_accounty_type[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Bank_accounty_type));
            Bank_accounty_type_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            Bank_accounty_type_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            account_type_spnr.setAdapter(Bank_accounty_type_adapter);
            account_type_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Bank_accounty_type_id = has_pancard_ar.getJSONObject(position).getString("id");
                        Bank_accounty_type_value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            account_type_spnr.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void IS_password_protected(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        is_password_protected = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            is_password_protected[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(is_password_protected));
            Is_password_protected_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){
                public View getView(int position, View convertView, ViewGroup parent) {
                    font = Typeface.createFromAsset(context.getAssets(),"Lato-Regular.ttf");
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            Is_password_protected_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            password_protedted_spnr.setAdapter(Is_password_protected_adapter);
            password_protedted_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Is_Password_protected_id = has_pancard_ar.getJSONObject(position).getString("id");
                        Is_Password_protected_value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");

                            if(Is_Password_protected_id.equals("1"))
                            {
                                Password_ly.setVisibility(View.VISIBLE);
                            }else{
                                Password_ly.setVisibility(View.GONE);
                            }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            password_protedted_spnr.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    //
    private boolean validate_account_NO(){
        if (account_number_edit_txt.getText().toString().isEmpty()) {
            account_number_edit_txt.setError(getText(R.string.error_rise));
            account_number_edit_txt.requestFocus();
            return false;
        } else {
            account_number_edit_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validate_password(){
        if (Password_edit_txt.getText().toString().isEmpty()) {
            Password_edit_txt.setError(getText(R.string.error_rise));
            Password_edit_txt.requestFocus();
            return false;
        } else {
            Password_edit_txt.setError(null);
            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private void BANK_Name_list(final JSONArray has_pancard_ar) throws JSONException {
        //   SPINNERLIST = new String[ja.length()];
        Bank_Name = new String[has_pancard_ar.length()];
        for (int i=0;i<has_pancard_ar.length();i++){
            JSONObject J =  has_pancard_ar.getJSONObject(i);
            Bank_Name[i] = J.getString("value");
            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Bank_Name));
            Bank_Name_adapter = new ArrayAdapter<String>(context, R.layout.view_spinner_item, loan_type_list){

                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    TextView v = (TextView) super.getView(position, convertView, parent);
                    v.setTypeface(font);
                    return v;
                }
            };

            Bank_Name_adapter.setDropDownViewResource(R.layout.view_spinner_item);
            Bank_name_spnr.setAdapter(Bank_Name_adapter);
            Bank_name_spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    try {

                        //  City_loc_uniqueID = ja.getJSONObject(position).getString("city_id");
                        Bank_Name_id = has_pancard_ar.getJSONObject(position).getString("id");
                        Bank_Name_value = has_pancard_ar.getJSONObject(position).getString("value");
                        //CAT_ID = ja.getJSONObject(position).getString("category_id");



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Bank_name_spnr.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    // imm.hideSoftInputFromWindow(edt_buyer_address.getWindowToken(), 0);
                    return false;
                }
            });
        }

    }

    private void Bank_Name_Searc() {
      //  progressDialog.show();
        JSONObject J =new JSONObject();
        try {
            J.put("name", "name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.get_banklist, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {
                        try {

                                 bank_list = object.getJSONArray("bank_list");
                                Log.e("Pincode", String.valueOf(bank_list));

                                if(bank_list.length()>0)
                                {
                                    BANK_Name_list(bank_list);
                                }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();
                       // progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
              //  progressDialog.dismiss();
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
    private void set_Bank_Name(final JSONArray ja) throws JSONException {

        BANK_list = new String[ja.length()];
        for (int i=0;i<ja.length();i++){
            JSONObject J =  ja.getJSONObject(i);
            BANK_list[i] = J.getString("value");
            final List<String> area_list = new ArrayList<>(Arrays.asList(BANK_list));
            Bank_Name_dapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.view_spinner_item, area_list){};
            String workpincode = bank_name_auto_complete.getText().toString();
            //  String workpincode1 = residence_pincode1_edit_txt.getText().toString();
        }

        bank_name_auto_complete.setAdapter(Bank_Name_dapter);
        Bank_Name_dapter.notifyDataSetChanged();
        if(ja.length() < 40) bank_name_auto_complete.setThreshold(1);
        else bank_name_auto_complete.setThreshold(2);
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

    private void Loan_submit_statues() {
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            // J.put("transaction_id", 53277);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Request _statues ", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.bank_status_fetch, J,
                new Response.Listener<JSONObject>() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(JSONObject object) {
                        Log.e("Loawiser_Submit", object.toString());


                        try
                        {
                            JSONObject result=object.getJSONObject("result");


                            String  submit_loanwiser = result.getString("submit_loanwiser");
                            String doc_verification = result.getString("doc_verification");
                            String apply_completion_status = result.getString("apply_completion_status");



                            if(submit_loanwiser.equals("1"))
                            {

                                upload.setVisibility(View.GONE);
                                submit.setVisibility(View.GONE);
                            }else
                            {
                                upload.setVisibility(View.VISIBLE);
                                submit.setVisibility(View.VISIBLE);
                            }


                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(mCon, "Network error, try after some time",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {

            /**
             * Passing some request headers
             * */
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
    public void Bank_statues() {

        // final String step_status11 = step_status1;
        JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("from_partstatus", "2");
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
                            String statues = response.getString("status");
                            String eligible_status = jsonObject2.getString("eligible_status");


                            if (statues.contains("success")) {
                               // Applicant_Status();

                                if(eligible_status.equals("0"))
                                {
                                    Bank_analysis_ErrorStatus();
                                 //   Toast.makeText(Upload_Activity_Bank.this, "Bank Statement Analysis Failed", Toast.LENGTH_SHORT).show();
                                }else
                                {
                                   /* Intent intent = new Intent(Upload_Activity_Bank.this, BankAnalysis.class);
                                    //  Intent in=new Intent(context,BankAnalysis.class);
                                    intent.putExtra("adapter","upload");
                                    startActivity(intent);*/
                                    Intent intent = new Intent(Upload_Activity_Bank.this, Applicant_Doc_Details_revamp.class);
                                    startActivity(intent);
                                    finish();
                                }

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

    public void Bank_statement_Vale(String your_uploading_for_value) {

        // final String step_status11 = step_status1;
        String typecnt;
        String applicant_c =Pref.getCoAPPAVAILABLE(getApplicationContext());

        if(applicant_c.equals("1"))
        {
            typecnt = "0";
        }else
        {
            typecnt = "1";
        }
        JSONObject jsonObject = new JSONObject();
        JSONObject J = null;
        try {
            J = new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("albankstacc_no", your_uploading_for_value);
            J.put("type_cnt", typecnt);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("Applicant Entry request", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.get_bankdetils, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Bank_data", String.valueOf(response));

                        try {
                             bank_accountno= response.getString("bank_accountno");
                            account_number_edit_txt.setText(bank_accountno);
                             bank_name= response.getString("bank_name");

                           // String bank_name
                           // Log.e("loancat_name1", String.valueOf(bank_name1));


                            for (int k=0;k<bank_list.length();k++){

                                JSONObject object2 = bank_list.getJSONObject(k);

                                String id_type1 = object2.getString("id");

                                Log.e("Bank Name", String.valueOf(bank_name));
                                if(bank_name.equals(id_type1)){

                                    String loan_type1 = object2.getString("value");
                                    bank_name_edt.setText(loan_type1);
                                    // Objs.a.showToast(mCom, loan_type);
                                    Log.e("FindBank", String.valueOf(loan_type1));
                                   // Bank_name_spnr.setSelection(Integer.parseInt(loan_type1));
                                }
                            }

                             bank_status= response.getString("bank_status");
                            for (int k=0;k<bankstacc_type.length();k++){

                                JSONObject object2 = bankstacc_type.getJSONObject(k);

                                String id_type1 = object2.getString("id");

                                Log.e("accountType", String.valueOf(bank_status));
                                Log.e("bank_sta", String.valueOf(bank_status));
                                if(bank_status.equals(id_type1)){

                                    String loan_type1 = object2.getString("value");
                                    account_type_edit_txt.setText(loan_type1);
                                    // Objs.a.showToast(mCom, loan_type);
                                    Log.e("FindBank", String.valueOf(loan_type1));
                                    // Bank_name_spnr.setSelection(Integer.parseInt(loan_type1));
                                }
                            }
                             bank_type= response.getString("bank_type");
                            for (int k=0;k<bankststatement_type.length();k++){

                                JSONObject object2 = bankststatement_type.getJSONObject(k);

                                String id_type1 = object2.getString("id");

                                Log.e("bank_status", String.valueOf(bank_type));
                                if(bank_type.equals(id_type1)){

                                    String loan_type1 = object2.getString("value");
                                    bank_state_type_edt_txt.setText(loan_type1);
                                    // Objs.a.showToast(mCom, loan_type);
                                    Log.e("FindBank", String.valueOf(loan_type1));
                                    // Bank_name_spnr.setSelection(Integer.parseInt(loan_type1));
                                }
                            }

                             bank_pass= response.getString("bank_pass");
                            pass_word_edt_txt.setText(bank_pass);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setMessage("Unable get correct pdf format. Please select the correct PDF file from File Directory/Internal Storage.");
        builder.setNegativeButton("Okay", null);

        AlertDialog alert = builder.create();
        alert.show();
        a.DialogStyle(context, alert);
    }

    public void ExitAlert1(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(R.drawable.ic_check));
        builder.setMessage("Bank Statement Uploaded Sucessfully.");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
        a.DialogStyle(context, alert);
    }

    private void Submit_upload_sucess() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
                Intent intent = new Intent(Upload_Activity_Bank.this, Applicant_Doc_Details_revamp.class);
                startActivity(intent);
                finish();
              //  Bank_statues();

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
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
        String typecnt;
        String applicant_c =Pref.getCoAPPAVAILABLE(getApplicationContext());

        if(applicant_c.equals("1"))
        {
             typecnt = "0";
        }else
        {
             typecnt = "1";
        }

        J = new JSONObject();
        try {
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
          //  J.put("transaction_id","66431");
           //J.put("relationship_type","1");
           J.put("typecnt",typecnt);
            Log.i(TAG, "Banklist:Request "+J.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();
        Log.e("Request Dreopdown", J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.bank_statementlist, J,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @SuppressLint("ResourceType")
                    @Override
                    public void onResponse(JSONObject object) {
                        JSONArray cast = null;
                        JSONArray ja = null;
                        Log.e("response Dreopdown", object.toString());
                        try {

                            String s=object.getString("display_select");
                            Log.i(TAG, "onResponse: "+s);
                            ja = object.getJSONArray("bank_statementarr");
                            ja1_bank_list = object.getJSONArray("inputs_array");


                            if(ja1_bank_list.length()>0)
                            {
                                Your_uploading_for(ja1_bank_list);
                                your_uploadin_ly.setVisibility(View.VISIBLE);
                            }else
                            {
                                your_uploadin_ly.setVisibility(View.GONE);
                            }


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
                          /*  ja1 = object.getJSONArray("ban_statementlist");
                            Log.i(TAG, "onResponse: ja 1length"+ja1.length());
                            RadioButton button = new RadioButton(Upload_Activity_Bank.this);
                            button.setId(View.generateViewId());
                            button.setText("new");
                            Bank_Statement_radio_list.addView(button);*/

                           /* if (ja1!=null){
                                for (int i=0;i<ja1.length();i++){
                                    JSONObject J = ja1.getJSONObject(i);
                                   // String entity_id1 = J.getString("entity_id");
                                    String acc_number = J.getString("acc_number");
                                                  *//*  Log.i(TAG, "onResponse:entity_id "+entity_id);
                                                    RadioButton rdbtn = new RadioButton(Upload_Activity_Bank.this);
                                                    rdbtn.setId(View.generateViewId());
                                                    rdbtn.setText(entity_id + rdbtn.getId());
                                                    //rdbtn.setOnClickListener(this);
                                                    Bank_Statement_radio_list.addView(rdbtn);*//*
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
                            });*/

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

          /*  if (SDK_INT < 11) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK >= 11 && SDK < 19
            else if (SDK_INT < 19) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK > 19 (Android 4.4) and up
            else {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API19(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }*/
            orginalFile_pic = uriarrayList_pic.get(i);

            // String path = String.valueOf(orginalFile);
            // pathlist.add(path);
            // uploadMultipart();
            //  MultifileUploadRetrofit();

            Log.e("path", String.valueOf(orginalFile_pic));
            Log.e("Upload_Activity_Ban", String.valueOf(orginalFile_pic));


            //File file = new File(uriarrayList.get(i).getPath());
            // File file = new File(orginalFile);
            RequestBody fileBody = RequestBody.create(mediaType, orginalFile_pic);
            //Setting the file name as an empty string here causes the same issue, which is sending the request successfully without saving the files in the backend, so don't neglect the file name parameter.
            fileParts[i] = MultipartBody.Part.createFormData(String.format(Locale.ENGLISH, "img_url[%d]", i), orginalFile_pic.getName(), fileBody);
            // fileParts[i] = MultipartBody.Part.createFormData("img_url", file.getName(), fileBody);
        }
        String transcation_id="60775";
        String analysis_status="1";
        String workorder_id="";
        String typecnt;
        String applicant_c=Pref.getCoAPPAVAILABLE(getApplicationContext());

        String pdf_password=docpass_edt_txt.getText().toString().trim();
        final String relationship_type="1";

        if(applicant_c.equals("1"))
        {
            typecnt = "0";
        }else
        {
            typecnt = "1";
        }
        RequestBody transaction_id1 = RequestBody.create(MediaType.parse("multipart/form-data"), Pref.getTRANSACTIONID(getApplicationContext()));

        RequestBody Bank_Name = RequestBody.create(MediaType.parse("multipart/form-data"), Bank_Name_id);
        RequestBody account_number = RequestBody.create(MediaType.parse("multipart/form-data"), account_number_edit_txt_);
        RequestBody Bank_stm_type_ = RequestBody.create(MediaType.parse("multipart/form-data"), Bank_stm_type_account_ID);
        RequestBody Password_edt = RequestBody.create(MediaType.parse("multipart/form-data"), Password_edit_txt_);

        RequestBody relationship_type1 = RequestBody.create(MediaType.parse("multipart/form-data"), typecnt);

        Call<UploadFileResponse> call =getResponse.submitNew("Auth Token", fileParts,transaction_id1,Bank_Name
                ,account_number,relationship_type1,Bank_stm_type_,Password_edt);
        call.enqueue(new Callback<UploadFileResponse>() {
            @Override
            public void onResponse(Call<UploadFileResponse> call, retrofit2.Response<UploadFileResponse> response) {
                progressDialog.dismiss();
                Toast.makeText(Upload_Activity_Bank.this, "Submitted Successfully",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "response file uploded"+response);
                if (response.isSuccessful()){
                    UploadFileResponse response1=(UploadFileResponse) response.body();
                    int status=response1.getResponse().getStatus();
                    Log.i(TAG, "onResponse: "+status);
                    if(status==1){
                        Submit_upload_sucess();
                    }
                    //    Toast.makeText(Upload_Activity_Bank.this, "Submitted Successfully",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UploadFileResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e(TAG, "response fail uploded"+t.toString());
                Toast.makeText(Upload_Activity_Bank.this,"Network issue",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onFailure: "+t.getMessage());
            }
        });
    }


    public String getRealPathFromUri(Uri uri) { // function for file path from uri,
        if (SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(this, uri)) {
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








    private void ErrorStatus() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
    private void ErrorStatus1() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.bankstatement_error1);
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
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
    private void Bank_analysis_ErrorStatus() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.bank_statement_analysis_error);
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

                Intent intent = new Intent(Upload_Activity_Bank.this,Home.class);
                startActivity(intent);
                finish();
            }
        });



        if (!dialog.isShowing()) {
            dialog.show();
        }

    }

    private void pdf_error() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.pdf_error);
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
          /*  if (SDK_INT < 11) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK >= 11 && SDK < 19
            else if (SDK_INT < 19) {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }
            // SDK > 19 (Android 4.4) and up
            else {
                orginalFile = new File(FileUtils1.getRealPathFromURI_API19(Upload_Activity_Bank.this, uriarrayList.get(i)));
            }*/
            orginalFile_pic = uriarrayList_pic.get(i);
            // String path = String.valueOf(orginalFile);
            // pathlist.add(path);
            // uploadMultipart();
            //  MultifileUploadRetrofit();
            //  Log.e("path", String.valueOf(orginalFile));
            //  Log.e("Upload_Activity_Ban", String.valueOf(orginalFile));
            //File file = new File(uriarrayList.get(i).getPath());
            //  File file = new File(filePath);
            RequestBody fileBody = RequestBody.create(mediaType, orginalFile_pic);
            //Setting the file name as an empty string here causes the same issue, which is sending the request successfully without saving the files in the backend, so don't neglect the file name parameter.
            fileParts[i] = MultipartBody.Part.createFormData(String.format(Locale.ENGLISH, "img_url[%d]", i), orginalFile_pic.getName(), fileBody);
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
                                Submit_upload_sucess();
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
        pickiT.deleteTemporaryFile(this);

      /*  Intent intent = new Intent(mCon, DashBoard_new.class);
        startActivity(intent);
        // Objs.ac.StartActivity(mCon, LeadeFragment.class);
        finish();*/
        super.onBackPressed();

    }


    //  Check if permissions was granted
    private boolean checkSelfPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE);
            return false;
        }
        return true;
    }

    //  Handle permissions
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //  Permissions was granted, open the gallery
                showFileChooser();
            }
            //  Permissions was not granted
            else {
                showLongToast("No permission for " + Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
        }
    }

    //Show Toast
    private void showLongToast(final String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
    ProgressBar mProgressBar;
    TextView percentText;
    private AlertDialog mdialog;
    ProgressDialog progressBar;

    @Override
    public void PickiTonUriReturned() {
        progressBar = new ProgressDialog(this);
        progressBar.setMessage("Waiting to receive file...");
        progressBar.setCancelable(false);
        progressBar.show();
    }

    @Override
    public void PickiTonStartListener() {
        if (progressBar.isShowing()){
            progressBar.cancel();
        }
        final AlertDialog.Builder mPro = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
        @SuppressLint("InflateParams") final View mPView = LayoutInflater.from(this).inflate(R.layout.dailog_layout, null);
        percentText = mPView.findViewById(R.id.percentText);

        percentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickiT.cancelTask();
                if (mdialog != null && mdialog.isShowing()) {
                    mdialog.cancel();
                }
            }
        });

        mProgressBar = mPView.findViewById(R.id.mProgressBar);
        mProgressBar.setMax(100);
        mPro.setView(mPView);
        mdialog = mPro.create();
        mdialog.show();

    }

    @Override
    public void PickiTonProgressUpdate(int progress) {
        String progressPlusPercent = progress + "%";
        percentText.setText(progressPlusPercent);
        mProgressBar.setProgress(progress);
    }

    @Override
    public void PickiTonCompleteListener(String path, boolean wasDriveFile, boolean wasUnknownProvider, boolean wasSuccessful, String reason) {

        if (mdialog != null && mdialog.isShowing()) {
            mdialog.cancel();
        }

        //  Check if it was a Drive/local/unknown provider file and display a Toast
        if (wasDriveFile){
         //   showLongToast("Drive file was selected");
        }else if (wasUnknownProvider){
          //  showLongToast("File was selected from unknown provider");
        }else {
         //   showLongToast("Local file was selected");
        }

        //  Chick if it was successful
        if (wasSuccessful) {
            //  Set returned path to TextView
            uriarrayList_pic.add(new File(path));

            Log.e("the Path selected PIC",uriarrayList_pic.toString());
            Log.e("the URI SIZE", String.valueOf(uriarrayList.size()));
            Log.e("the URI SIZE1", String.valueOf(uriarrayList_pic.size()));
        }else {
            showLongToast("Error, please see the log..");

        }
    }


    //
    //  Lifecycle methods
    //

    //  Deleting the temporary file if it exists
    //  As we know, this might not even be called if the system kills the application before onDestroy is called
    //  So, it is best to call pickiT.deleteTemporaryFile(); as soon as you are done with the file
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!isChangingConfigurations()) {
            pickiT.deleteTemporaryFile(this);
        }
    }

}

