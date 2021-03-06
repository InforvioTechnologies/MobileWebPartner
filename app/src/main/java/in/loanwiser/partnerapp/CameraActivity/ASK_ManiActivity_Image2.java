package in.loanwiser.partnerapp.CameraActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.material.snackbar.Snackbar;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.PhotoLoader;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import net.gotev.uploadservice.MultipartUploadRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.BankStamentUpload.FileUtils1;
import in.loanwiser.partnerapp.Documents.Document_Details;
import in.loanwiser.partnerapp.Documents.FilePath;
import in.loanwiser.partnerapp.Documents.MyCommand;
import in.loanwiser.partnerapp.Documents.SingleUploadBroadcastReceiver;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.Partner_Statues.Ask_Dashboard_Activity;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.Partner_Statues.Suggestion_item_freqent;
import in.loanwiser.partnerapp.R;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ASK_ManiActivity_Image2 extends SimpleActivity implements SingleUploadBroadcastReceiver.Delegate {

    //
    //private Bitmap bitmap;
    private static final int PICK_Camera_IMAGE = 2;

    //
    private int PICK_PDF_REQUEST = 1;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private Boolean isFabOpen = false;
//  private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    private Uri filePath;
    private final SingleUploadBroadcastReceiver uploadReceiver =
            new SingleUploadBroadcastReceiver();
    String id,doc_typename,docid,class_id,type,user_type,transaction_id;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    Uri imageUri;
    private ProgressDialog pDialog;
    GridView gridGallery;
    Handler handler;
    GalleryAdapter adapter;
    private Context mCon = this;
    ImageView imgSinglePick,imgSinglePick1;
    private static final String TAG = "AndroidUploadService";
    Button btnGalleryPick;
    Button btnGalleryPickMul,upload;
    private android.app.AlertDialog progressDialog;
    ArrayList<String[]> imageList = new ArrayList<String[]>();
    String action;
    ViewSwitcher viewSwitcher,viewSwitcher1;
    ImageLoader imageLoader;
    private String status;
    ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();
    String[] all_path;
    String all_path1;
    String app_id,msg,ask_id,doc_classname;
    AppCompatTextView pdf_name;

    int count=0;
    File orginalFile = null ;
    ///Camera image

    private static final String TAG1 = MainActivity_IMG_Property2.class.getSimpleName();
    // private static final int PERMISSION_REQUEST_CODE = 200;

    // Camera activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    //  String id,doc_typename,docid,class_id,type,user_type,transaction_id;
    private Uri fileUri;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton camera,gallery,pdf;
    private Bitmap bitmap,photo;
    AppCompatTextView pending_ask_List;
    String path;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.ask_main_activity);
        initTools(R.string.doc_upload);

        progressDialog = new SpotsDialog(ASK_ManiActivity_Image2.this, R.style.Custom);

        pDialog = new ProgressDialog(ASK_ManiActivity_Image2.this);
        pDialog.setMessage("Loading...");

        doc_typename =  Objs.a.getBundle(this, Params.doc_typename);
        doc_classname =  Objs.a.getBundle(this, Params.doc_classname);
        initTools1(doc_classname);

        pending_ask_List = (AppCompatTextView) findViewById(R.id.pending_ask_List);
        pending_ask_List.setText("Upload " +doc_typename);

        docid =  Objs.a.getBundle(this, Params.docid);

        transaction_id =  Objs.a.getBundle(this, Params.transaction_id);
        ask_id =  Objs.a.getBundle(this, Params.ask_id);


        Pref.putcamera_doc_typename(mCon,doc_typename);
        Pref.putcamera_docid(mCon,docid);
        Pref.putcamera_transaction_id(mCon,transaction_id);
        Pref.putask_id(mCon,ask_id);
       Log.d("doc_typename",doc_typename);
        Log.d("docid",docid);
        Log.d("transaction_id",transaction_id);
        Log.d("ask_id",ask_id);

      /*   Log.d("ID_values",user_type);
        Log.d("ID_values",transaction_id);*/

      /*  45
        Closure Proof of Loans Closed in Last 6 Months
        31
        15
        1
        6555*/

        Checking();
        initImageLoader();
        init();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

    }
    public void Checking(){
        if(checkPermission()== true) {
        }else{
            requestPermission();
        }
    }

    private boolean checkPermission() {

        // int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_SMS);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result4 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        return  result1 == PackageManager.PERMISSION_GRANTED
                && result2 == PackageManager.PERMISSION_GRANTED
                && result3 == PackageManager.PERMISSION_GRANTED &&
                result4 == PackageManager.PERMISSION_GRANTED;

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA,READ_SMS,READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    //  boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean smsAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean externalAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean externalAccepted1 = grantResults[3] == PackageManager.PERMISSION_GRANTED;

                    if (cameraAccepted && smsAccepted && externalAccepted && externalAccepted1)
                        Snackbar.make(findViewById(R.id.fab), "Permission Granted", Snackbar.LENGTH_LONG).show();
                    else {

                       // Snackbar.make(findViewById(R.id.fab), "Permission Denied, You cannot access SMS and camera.", Snackbar.LENGTH_LONG).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOKCancel("You need to allow access to all the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                                    requestPermissions(new String[]{ CAMERA,
                                                                    READ_SMS,READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }

                break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(mCon)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
  /*  //Fab
    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
              gallery.startAnimation(fab_close);
            camera.startAnimation(fab_close);
            pdf.startAnimation(fab_close);

              gallery.setClickable(false);
            camera.setClickable(false);
            pdf.setClickable(false);
            isFabOpen = false;
         //   Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
             gallery.startAnimation(fab_open);
            camera.startAnimation(fab_open);
            pdf.startAnimation(fab_open);
             gallery.setClickable(true);
            camera.setClickable(true);
            pdf.setClickable(true);
            isFabOpen = true;
          //  Log.d("Raj","open");

        }
    }*/



    ////////////////////////////////////////////////////////////////////////////////////



    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                this).defaultDisplayImageOptions(defaultOptions).memoryCache(
                new WeakMemoryCache());

        ImageLoaderConfiguration config = builder.build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);
    }

    private void init() {
        dataT = new ArrayList<CustomGallery>();
        handler = new Handler();
        gridGallery = (GridView) findViewById(R.id.gridGallery);
        gridGallery.setFastScrollEnabled(true);
        adapter = new GalleryAdapter(getApplicationContext(), imageLoader);
        adapter.setMultiplePick(false);
        gridGallery.setAdapter(adapter);

        viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        viewSwitcher.setDisplayedChild(1);

        viewSwitcher1 = (ViewSwitcher) findViewById(R.id.viewSwitcher1);
        viewSwitcher1.setDisplayedChild(1);

        imgSinglePick = (ImageView) findViewById(R.id.imgSinglePick);
        imgSinglePick1 = (ImageView) findViewById(R.id.imgSinglePick1);
        pdf_name = (AppCompatTextView) findViewById(R.id.pdf_name);
        upload = (Button) findViewById(R.id.upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                conformDialog();
            }
        });

///FAB



      //  fab = (FloatingActionButton)findViewById(R.id.fab);
      //  pdf = (FloatingActionButton)findViewById(R.id.fab1);
      //  camera = (FloatingActionButton)findViewById(R.id.fab2);
      //  gallery = (FloatingActionButton)findViewById(R.id.fab3);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        pdf = (FloatingActionButton) findViewById(R.id.pdf);
        camera = (FloatingActionButton) findViewById(R.id.camera);
        gallery = (FloatingActionButton) findViewById(R.id.picture);

       // fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
       // fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
       // rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
       // rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);

       /* fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //  gallery.setVisibility(View.GONE);
                animateFAB();
            }
        });
*/
        upload.setEnabled(false);
        upload.setBackgroundResource(R.drawable.capsul_button_dis);
        gallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            filePath = null;
                Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
                startActivityForResult(i, 200);
            }
        });

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilePDF();
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

               /* Objs.ac.StartActivityPutExtra(mCon, Camara_Activity.class,
                        Params.id,id
                        ,Params.doc_typename,doc_typename,
                        Params.docid,docid
                        ,Params.class_id,class_id,
                        Params.user_type,user_type,
                        Params.transaction_id,transaction_id);*/

                Pref.putcameraID(mCon,id);
                Pref.putcamera_doc_typename(mCon,doc_typename);
                Pref.putcamera_docid(mCon,docid);
                Pref.putcamera_class_id(mCon,class_id);
                Pref.putcamera_user_type(mCon,user_type);
                Pref.putcamera_transaction_id(mCon,transaction_id);

                captureImage();
            }
        });

    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    public Uri getOutputMediaFileUri(int type) {

        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                Config.IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG1, "Oops! Failed create "
                        + Config.IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    private void launchUploadActivity(boolean isImage){

        Intent i = new Intent(ASK_ManiActivity_Image2.this, ASK_UploadActivity1.class);
        i.putExtra("filePath", fileUri.getPath());
        i.putExtra("isImage", isImage);
        startActivity(i);

    }


    //
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_Camera_IMAGE);
    }

    //
    private void showFilePDF() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_REQUEST);
    }

    private void conformDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.submit_conform);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        ImageView closebutton = (ImageView) dialog.findViewById(R.id.closebtn);
        Button submitbut = (Button) dialog.findViewById(R.id.yes_submit);
        Button noedit = (Button) dialog.findViewById(R.id.no_edit);
        dialog.show();
        submitbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });
        noedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImageUri = null;
        // filePath = null;
        materialDesignFAM.close(true);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            if(filePath != null)
            {
                upload.setEnabled(true);
                upload.setBackgroundResource(R.drawable.capsul_button_ask);

                viewSwitcher.setVisibility(View.GONE);
                viewSwitcher1.setVisibility(View.VISIBLE);
                viewSwitcher1.setDisplayedChild(1);

                imgSinglePick1.setVisibility(View.VISIBLE);
                imgSinglePick.setVisibility(View.GONE);

                String path = FilePath.getPath(this, filePath);

                String  fileName = getFileName(filePath);
                pdf_name.setText(fileName);

            }
            //  String path = FilePath.getPath(this, filePath);
            //  Toast.makeText(MainActivity_Document.this,"PDF " + path ,Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // successfully captured the image
                // launching upload activity
                launchUploadActivity(true);


            } else if (resultCode == RESULT_CANCELED) {
                upload.setEnabled(false);
                upload.setBackgroundResource(R.drawable.capsul_button_dis);
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();

            } else {
                upload.setEnabled(false);
                upload.setBackgroundResource(R.drawable.capsul_button_dis);
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }

        }
        else
        {
            upload.setEnabled(true);
            upload.setBackgroundResource(R.drawable.capsul_button_ask);

            viewSwitcher.setVisibility(View.VISIBLE);
            viewSwitcher1.setVisibility(View.GONE);
            // viewSwitcher1.setDisplayedChild(1);
            viewSwitcher.setDisplayedChild(1);

            imgSinglePick1.setVisibility(View.GONE);
            imgSinglePick.setVisibility(View.VISIBLE);
            ///

            if (requestCode == 200 && resultCode == Activity.RESULT_OK) {



                all_path = data.getStringArrayExtra("all_path");

                //  Log.d("all images path", String.valueOf(all_path));
                dataT = new ArrayList<CustomGallery>();

                for (String string : all_path) {
                    CustomGallery item = new CustomGallery();
                    item.sdcardPath = string;
                    // Log.d("all images path", String.valueOf(string));
                    dataT.add(item);

                    //  upload1(all_path);

                }

                viewSwitcher.setDisplayedChild(0);
                adapter.addAll(dataT);
            }

        }


    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
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
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    @SuppressLint("LongLogTag")
    public void upload()
    {

        //   Log.d("Camara image1", String.valueOf(all_path1));
        if(all_path == null && filePath == null )
        {
          //  upload.setEnabled(true);
            if(bitmap!=null)
            {
                final String singleimage = getStringImage(bitmap);
                //  Log.d("Camara image1", String.valueOf(singleimage));

                  progressDialog.show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.CAMERA_IMAGE_Upload,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                Log.d("Camara image", String.valueOf(response));
                                try {
                                    JSONObject j = new JSONObject(response);
                                    String a = j.getString(Params.status);
                                    Objs.a.showToast(mCon, "successfully uploaded");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Log.d(TAG, error.getMessage());
                        // VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        //  hideProgressDialog();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("legal_id", docid);
                        params.put(Params.doc_name, doc_typename);
                        params.put(Params.transaction_id, transaction_id);
                        params.put(Params.img_url, singleimage);
                        params.put(Params.is_mobileupload,"4");
                        Log.d("Camara image", String.valueOf(params));
                        return params;


                    }
                };

                int socketTimeout = 0;
                RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

                stringRequest.setRetryPolicy(policy);
                AppController.getInstance().addToRequestQueue(stringRequest);
                // Adding request to request queue
              //  AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);


            }
            else {
                Log.e("ComeON", "Upload multipart 3");
                Toast.makeText(getApplicationContext(), "Please Tack Images to upload!!!", Toast.LENGTH_SHORT).show();
            }

            ///
        }
        else
        {

            if(filePath != null){

                String path1= String.valueOf(filePath);
                String filename = path1.substring(path1.lastIndexOf("/")+1);
                String pdf1 = filename.substring(filename.lastIndexOf(".")+1);

                String a= "pdf";

                if(pdf1.equals(a))
                {
                    path = FilePath.getPath(this, filePath);
                    uploadMultipart_PDF();
                }
                else
                {
                    if (Build.VERSION.SDK_INT < 11) {
                        orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(ASK_ManiActivity_Image2.this, filePath));
                    }
                    // SDK >= 11 && SDK < 19
                    else if (Build.VERSION.SDK_INT < 19) {
                        orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(ASK_ManiActivity_Image2.this, filePath));
                    }
                    // SDK > 19 (Android 4.4) and up
                    else {
                        orginalFile = new File(FileUtils1.getRealPathFromURI_API19(ASK_ManiActivity_Image2.this, filePath));
                    }

                    path = String.valueOf(orginalFile);
                    Log.e("Upload_Activity_Ban", String.valueOf(orginalFile));

                    uploadMultipart_PDF();
                    //Toast.makeText(getApplicationContext(), "Please select the PDF file from the File Directory", Toast.LENGTH_SHORT).show();
                }

            }

            else
            {

                final MyCommand myCommand = new MyCommand(getApplicationContext());
                for(String imagePath : all_path){

                    count = count+1;
                 //   int l = imagePath.length();
                    Log.d("Galarel path length", String.valueOf(count));

                    try {
                        bitmap = PhotoLoader.init().from(imagePath).requestSize(512, 512).getBitmap();
                        final String encodedString = ImageBase64.encode(bitmap);
                        progressDialog.show();
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.CAMERA_IMAGE_Upload, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                try {
                                    Log.e("Galarel path length", String.valueOf(response));
                                    JSONObject j = new JSONObject(response);
                                    status = j.getString(Params.status);

                                    count = count-1;
                                    Log.d("Galarel path length", String.valueOf(count));

                                    Objs.a.showToast(mCon, "Successfully uploaded" +"\n"+ "Please wait for all Document upload");
                                    if(count == 0)
                                    {
                                        ASK_Submit_Lowiser();
                                    }
                                } catch (JSONException e) {

                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Error while uploading image", Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                               // params.put(Params.is_mobileupload, "4");
                                params.put("legal_id", docid);
                                params.put(Params.doc_name, doc_typename);
                                params.put(Params.transaction_id, transaction_id);
                                params.put(Params.img_url, encodedString);
                                params.put(Params.is_mobileupload,"4");
                               // Log.d("Camara image", String.valueOf(params));
                                Log.e("legal_id ", docid);
                                Log.e("doc_typename ", doc_typename);
                                Log.e("transaction_id ", transaction_id);
                                return params;

                            }
                        };

                        int socketTimeout = 0;
                        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

                        stringRequest.setRetryPolicy(policy);

                        myCommand.add(stringRequest);

                    } catch (FileNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "Error while loading image", Toast.LENGTH_SHORT).show();
                    }
                }
                myCommand.execute();

            }

        }
    }

    public void uploadMultipart_PDF() {
        //getting name for the pdf
        //  String name = editText.getText().toString().trim();
        Log.d("Pdf file333333", String.valueOf(filePath));
        //getting the actual path of the pdf
      //  String path = FilePath.getPath(this, filePath);
        Log.e("PDF File", String.valueOf(path));
       // File sourceFile = new File(path);

        if (path == null) {

            Toast.makeText(this, "Please move your .pdf file to internal storage and retry", Toast.LENGTH_LONG).show();
        } else {
            //Uploading code
            try {
                String uploadId = UUID.randomUUID().toString();
                uploadReceiver.setDelegate(this);
                uploadReceiver.setUploadID(uploadId);
                //Creating a multi part request
                progressDialog.show();
                new MultipartUploadRequest(this, uploadId, Urls.PDF_Document_Upload)
                      .addFileToUpload(path, Params.img_url) //Adding file
                        .addParameter("legal_id", docid) //Adding text parameter to the request
                        .addParameter(Params.doc_name, doc_typename)
                        .addParameter(Params.transaction_id, transaction_id)
                        .addParameter(Params.is_mobileupload, "4")
                       // .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
                Log.e("img_url", path);
                Log.e("relationship_type", "1");
                Log.e("entity_id", "no");
                Log.e("transaction_id", transaction_id);

            } catch (Exception exc) {
                Log.e("AndroidUploadService", exc.getMessage(), exc);
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        uploadReceiver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        uploadReceiver.unregister(this);
    }


    @Override
    public void onProgress(int progress) {
        Log.i(TAG, "The progress of the upload with ID " + " is: " + progress);
    }

    @Override
    public void onProgress(long uploadedBytes, long totalBytes) {
        Log.i(TAG, "The progress of the upload with ID "
                + uploadedBytes + " is: " + totalBytes);
    }

    @Override
    public void onError(Exception exception) {
        Log.e(TAG, "Error in upload with ID:"
                + exception.getLocalizedMessage(), exception);
    }

    @Override
    public void onCompleted(int serverResponseCode, byte[] serverResponseBody) {

        String response = String.valueOf(serverResponseCode);

        Log.e("the server Response", String.valueOf(serverResponseCode));
        Log.e("the server ", String.valueOf(serverResponseBody));

        if(response.equals("200")){

            //Objs.a.showToast(mCon, "Successfully uploaded");
            ASK_Submit_Lowiser();
           // Send_Reload(app_id);

            //   getContentResolver().delete(uri, null, null);
        }else if(response.equals("406")){
            progressDialog.dismiss();
            Objs.a.showToast(mCon, "Upload File" +"\n"+ "Maximum 5 MB upload file size");
        }
        //Upload with ID [B@19058a20 has been completed with HTTP 406. Response from server:
        //  Log.i(TAG, "Upload with ID " + serverResponseBody + " has been completed with HTTP " + serverResponseCode + ". Response from server: " );

    }

    @Override
    public void onCancelled() {

    }

    private void ASK_Submit_Lowiser() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("ask_id", ask_id);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("the ask_id",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.ask_submit_loanw, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("the ask_id",response.toString());

                        try {
                            if(response.getString("status").equals("success")){
                                progressDialog.dismiss();
                                Toast.makeText(mCon, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(mCon, Ask_Dashboard_Activity.class);
                                startActivity(intent);
                                finish();
                               // JSONArray ja = response.getJSONArray("data");
                            }else
                            {
                                Toast.makeText(mCon,"Something went Wrong please try again!!!",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response",String.valueOf(error));
                progressDialog.dismiss();
                Toast.makeText(mCon,"Network issues please try again!!!",Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        Log.d("Response","Login Activity_Exitalert Login_POST33333333333333333333333333333333333");
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

}

