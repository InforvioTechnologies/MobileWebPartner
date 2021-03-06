package in.loanwiser.Old_Partner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
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
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
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
import com.android.volley.toolbox.StringRequest;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.hbisoft.pickit.PickiT;
import com.hbisoft.pickit.PickiTCallbacks;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.PhotoLoader;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import net.gotev.uploadservice.MultipartUploadRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import in.loanwiser.partnerapp.CameraActivity.Action;
import in.loanwiser.partnerapp.CameraActivity.Config;
import in.loanwiser.partnerapp.CameraActivity.CustomGallery;
import in.loanwiser.partnerapp.CameraActivity.GalleryAdapter;
import in.loanwiser.partnerapp.CameraActivity.MainActivity_IMG_Property2;

import in.loanwiser.partnerapp.Documents.MyCommand;
import in.loanwiser.partnerapp.Documents.SingleUploadBroadcastReceiver;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ManiActivity_Image2_old extends SimpleActivity implements SingleUploadBroadcastReceiver.Delegate, PickiTCallbacks {

    //
    //private Bitmap bitmap;
    private static final int PICK_Camera_IMAGE = 2;

    //
   // GalleryAdapter galleryAdapter;

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
    String[] all_path,strArr;
    String all_path1;
    String app_id,msg,filePath_camera;
    AppCompatTextView pdf_name;
    PopupWindow popupWindow;
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

    private ImageView imgPreview;
    private VideoView vidPreview;
    private Button btnUpload,rotation;
    private ExifInterface exifObject;
    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 22;
    private static final int PERMISSION_REQ_ID_WRITE_EXTERNAL_STORAGE = PERMISSION_REQ_ID_RECORD_AUDIO + 1;
    String path;
    File orginalFile_pic = null;
    LinearLayout pdf_gallery,camera_ly_upload;
    PickiT pickiT;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.main_activity);
        initTools(R.string.doc_upload);

        progressDialog = new SpotsDialog(ManiActivity_Image2_old.this, R.style.Custom);

        pDialog = new ProgressDialog(ManiActivity_Image2_old.this);
        pDialog.setMessage("Loading...");

        doc_typename = Pref.getcamera_doc_typename(mCon);
        docid = Pref.getcamera_docid(mCon);

        transaction_id = Pref.getcamera_transaction_id(mCon);
        pickiT = new PickiT(this, this, this);
       // doc_typename =  Objs.a.getBundle(this, Params.doc_typename);
        initTools1(doc_typename);
       // docid =  Objs.a.getBundle(this, Params.docid);

       // transaction_id =  Objs.a.getBundle(this, Params.transaction_id);

       /* Log.d("ID_values",id);
        Log.d("ID_values",doc_typename);
        Log.d("ID_values",docid);
        Log.d("ID_values",class_id);
        Log.d("ID_values",user_type);
        Log.d("ID_values",transaction_id);*/

      /*  45
        Closure Proof of Loans Closed in Last 6 Months
        31
        15
        1
        6555*/


        Intent intent = getIntent();
        String action_type = intent.getStringExtra("action");

        if(action_type.equals("1"))
        {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Pref.putcameraID(mCon,id);
                    Pref.putcamera_class_id(mCon,class_id);
                    Pref.putcamera_user_type(mCon,user_type);

                    Pref.putcamera_doc_typename(mCon,doc_typename);
                    Pref.putcamera_docid(mCon,docid);
                    Pref.putcamera_transaction_id(mCon,transaction_id);

                    captureImage();
                }
            }, 200);
            //camera

        }else if(action_type.equals("2"))
        {
            //galaery
            filePath = null;
            Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
            startActivityForResult(i, 200);
        }else
        {
            //pdf
            showFilePDF();
        }

        pdf_gallery = (LinearLayout) findViewById(R.id.pdf_gallery);
        camera_ly_upload = (LinearLayout) findViewById(R.id.camera_ly_upload);

       /* */

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



        //camera
        btnUpload = (Button) findViewById(R.id.btnUpload);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);
        vidPreview = (VideoView) findViewById(R.id.videoPreview);
        rotation = (Button) findViewById(R.id.rotation);
        progressDialog = new SpotsDialog(this, R.style.Custom);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        ///
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pref.removeGalary_Path(mCon);
                upload();

            }
        });

///FAB

        btnUpload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                upload1();
            }
        });


        rotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(imgPreview.getDrawable() != null){
                    try {
                        exifObject = new ExifInterface(filePath_camera);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int orientation = exifObject.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                    if(orientation == 0)
                    {

                        Toast.makeText(ManiActivity_Image2_old.this, "This Mobile does not support Rotate View", Toast.LENGTH_LONG).show();
                        rotation.setVisibility(View.GONE);
                    }
                    else
                    {

                        Bitmap imageRotate = rotateBitmap(bitmap,orientation);
                        bitmap = imageRotate;
                        imgPreview.setImageBitmap(imageRotate);
                    }


                }else{
                    Toast.makeText(ManiActivity_Image2_old.this, "Image photo is not yet set", Toast.LENGTH_LONG).show();
                }
            }
        });


      //  fab = (FloatingActionButton)findViewById(R.id.fab);
      //  pdf = (FloatingActionButton)findViewById(R.id.fab1);
      //  camera = (FloatingActionButton)findViewById(R.id.fab2);
      //  gallery = (FloatingActionButton)findViewById(R.id.fab3);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        materialDesignFAM.setVisibility(View.GONE);
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
               // (new Handler()).postDelayed(this::yourMethod, 5000);

               /* Objs.ac.StartActivityPutExtra(mCon, Camara_Activity.class,
                        Params.id,id
                        ,Params.doc_typename,doc_typename,
                        Params.docid,docid
                        ,Params.class_id,class_id,
                        Params.user_type,user_type,
                        Params.transaction_id,transaction_id);*/

                Pref.putcameraID(mCon,id);
                Pref.putcamera_class_id(mCon,class_id);
                Pref.putcamera_user_type(mCon,user_type);

                Pref.putcamera_doc_typename(mCon,doc_typename);
                Pref.putcamera_docid(mCon,docid);
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
/*
        Intent i = new Intent(ManiActivity_Image2.this, UploadActivity.class);
        i.putExtra("filePath", fileUri.getPath());
        i.putExtra("isImage", isImage);
        startActivity(i);*/



         filePath_camera = fileUri.getPath();
        if (filePath_camera != null) {
            // Displaying the image or video on the screen
            previewMedia(isImage);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry, file path is missing!", Toast.LENGTH_LONG).show();
        }

    }


    //
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_Camera_IMAGE);
    }
    private void previewMedia(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            imgPreview.setVisibility(View.VISIBLE);
            vidPreview.setVisibility(View.GONE);
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;

            int rotate = 0;
            int orientation = 0;
            try {
                //getContentResolver().notifyChange(imageUri, null);
                File imageFile = new File(filePath_camera);
                ExifInterface exif = new ExifInterface(
                        imageFile.getAbsolutePath());
                orientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotate = 270;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotate = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotate = 90;
                        break;
                }
                //	Log.v(Common.TAG, "Exif orientation: " + orientation);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /****** Image rotation ****/
            Matrix matrix = new Matrix();
            Log.d("Croped ", String.valueOf(orientation));
            matrix.postRotate(orientation);
            bitmap = BitmapFactory.decodeFile(filePath_camera, options);


            ///Bitmap cropped = Bitmap.createBitmap(bitmap, 100, 200, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            //  Bitmap cropped = Bitmap.createBitmap(bitmap, 60, 0, 480, 260);


            //  Log.d("Croped ", String.valueOf(cropped));
            Log.d("bimap ", String.valueOf(bitmap));

            imgPreview.setImageBitmap(bitmap);
            //  imgPreview.setDisplayOrientation(90);

        } else {
            imgPreview.setVisibility(View.GONE);
            vidPreview.setVisibility(View.VISIBLE);
            vidPreview.setVideoPath(filePath_camera);
            // start playing
            vidPreview.start();
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();
        Log.e("roration", String.valueOf(orientation));
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                Log.e("roration","0");
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                Log.e("roration","111");
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                Log.e("roration","2");
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                Log.e("roration","3");
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                Log.e("roration","4");
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                Log.e("roration","5");
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                Log.e("roration","5");
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                Log.e("roration","6");
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            // bitmap.recycle();
            bitmap = bmRotated;
            Log.e("roration", String.valueOf(bitmap));
            Log.e("roration", String.valueOf(bmRotated));
            return bitmap;
        }
        catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
    //
    private void showFilePDF() {
        if (checkSelfPermission()) {


        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PICK_PDF_REQUEST);
        }
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri selectedImageUri = null;
        // filePath = null;

        materialDesignFAM.close(true);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            pdf_gallery.setVisibility(View.VISIBLE);
            camera_ly_upload.setVisibility(View.GONE);
            filePath = data.getData();

            if(filePath != null)
            {
                upload.setEnabled(true);
                upload.setBackgroundResource(R.drawable.capsul_button4);

                viewSwitcher.setVisibility(View.GONE);
                viewSwitcher1.setVisibility(View.VISIBLE);
                viewSwitcher1.setDisplayedChild(1);

                imgSinglePick1.setVisibility(View.VISIBLE);
                imgSinglePick.setVisibility(View.GONE);

               // String path = FilePath.getPath(this, filePath);
                pickiT.getPath(data.getData(), Build.VERSION.SDK_INT);
                String  fileName = getFileName(filePath);
                pdf_name.setText(fileName);

            }
            //  String path = FilePath.getPath(this, filePath);
            //  Toast.makeText(MainActivity_Document.this,"PDF " + path ,Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {

            camera_ly_upload.setVisibility(View.VISIBLE);
            pdf_gallery.setVisibility(View.GONE);
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
            pdf_gallery.setVisibility(View.VISIBLE);
            camera_ly_upload.setVisibility(View.GONE);
            upload.setEnabled(true);
            upload.setBackgroundResource(R.drawable.capsul_button4);

            viewSwitcher.setVisibility(View.VISIBLE);
            viewSwitcher1.setVisibility(View.GONE);
            // viewSwitcher1.setDisplayedChild(1);
            viewSwitcher.setDisplayedChild(1);

            imgSinglePick1.setVisibility(View.GONE);
            imgSinglePick.setVisibility(View.VISIBLE);
            ///

            if (requestCode == 200 && resultCode == Activity.RESULT_OK) {

                viewSwitcher.setVisibility(View.GONE);
                viewSwitcher1.setVisibility(View.GONE);
                all_path = data.getStringArrayExtra("all_path");
                upload();
                if(all_path != null)
                {

                }else
                {
                    Toast.makeText(getApplicationContext(), "Please Select Images to upload!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ManiActivity_Image2_old.this, Applicant_Doc_Details_revamp_old.class);
                    startActivity(intent);
                    finish();
                }

               /*
                all_path = data.getStringArrayExtra("all_path");
                Log.e(TAG, "the galary list"+all_path);
                //  Log.d("all images path", String.valueOf(all_path));
                dataT = new ArrayList<CustomGallery>();

                for (String string : all_path) {
                    CustomGallery item = new CustomGallery();
                    item.sdcardPath = string;
                    // Log.d("all images path", String.valueOf(string));
                    dataT.add(item);

                    Log.e("the galary list", String.valueOf(dataT));
                    //  upload1(all_path);

                }

                viewSwitcher.setDisplayedChild(0);
                adapter.addAll(dataT);*/
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
                                  //  Objs.a.showToast(mCon, "successfully uploaded");
                                    Toast.makeText(ManiActivity_Image2_old.this, "Successfully uploaded", Toast.LENGTH_LONG).show();

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
                  /* //  path = FilePath.getPath(this, filePath);

                    if (Build.VERSION.SDK_INT < 11) {
                        orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(ManiActivity_Image2.this, filePath));
                    }
                    // SDK >= 11 && SDK < 19
                    else if (Build.VERSION.SDK_INT < 19) {
                        orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(ManiActivity_Image2.this, filePath));
                    }
                    // SDK > 19 (Android 4.4) and up
                    else {
                        orginalFile = new File(FileUtils1.getRealPathFromURI_API19(ManiActivity_Image2.this, filePath));
                    }

                    path = String.valueOf(orginalFile);*/
                    uploadMultipart_PDF();
                }
                else
                {
                   /* if (Build.VERSION.SDK_INT < 11) {
                        orginalFile = new File(FileUtils1.getRealPathFromURI_BelowAPI11(ManiActivity_Image2.this, filePath));
                    }
                    // SDK >= 11 && SDK < 19
                    else if (Build.VERSION.SDK_INT < 19) {
                        orginalFile = new File(FileUtils1.getRealPathFromURI_API11to18(ManiActivity_Image2.this, filePath));
                    }
                    // SDK > 19 (Android 4.4) and up
                    else {
                        orginalFile = new File(FileUtils1.getRealPathFromURI_API19(ManiActivity_Image2.this, filePath));
                    }

                     path = String.valueOf(orginalFile);
                    Log.e("Upload_Activity_Ban", String.valueOf(orginalFile));*/

                    uploadMultipart_PDF();
                   // Toast.makeText(getApplicationContext(), "Please select the PDF file from the File Directory", Toast.LENGTH_SHORT).show();
                }

            }

            else
            {

                LayoutInflater layoutInflater = (LayoutInflater) ManiActivity_Image2_old.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup_loading1,null);


                //instantiate popup window
                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                //display the popup window
                popupWindow.showAtLocation(upload, Gravity.CENTER, 0, 0);

                final MyCommand myCommand = new MyCommand(getApplicationContext());
                for(String imagePath : all_path){

                    count = count+1;
                 //   int l = imagePath.length();
                    Log.d("Galarel path length", String.valueOf(count));

                    try {
                        bitmap = PhotoLoader.init().from(imagePath).requestSize(512, 512).getBitmap();
                        final String encodedString = ImageBase64.encode(bitmap);
                       // final String encodedString1 = Base64.encodeToString(bitmap, Base64.DEFAULT);
                       // progressDialog.show();
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.CAMERA_IMAGE_Upload, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject j = new JSONObject(response);
                                    Log.d("Galarel path length", String.valueOf(j));
                                    status = j.getString(Params.status);

                                    count = count-1;
                                    Log.d("Galarel path length", String.valueOf(count));
                                    Toast.makeText(ManiActivity_Image2_old.this, "Successfully uploaded", Toast.LENGTH_LONG).show();

                                 //   Objs.a.showToast(mCon, "Successfully uploaded" +"\n"+ "Please wait for all Document upload");
                                    if(count == 0)
                                    {
                                      //  popupWindow.dismiss();
                                     //   progressDialog.dismiss();
                                       // Objs.ac.StartActivityPutExtra(mCon,Document_Details.class, Params.user_type,user_type);
                                        Intent intent = new Intent(ManiActivity_Image2_old.this, Applicant_Doc_Details_revamp_old.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } catch (JSONException e) {

                                    e.printStackTrace();

                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                               // progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Error while uploading image", Toast.LENGTH_SHORT).show();
                               // popupWindow.dismiss();
                                //   progressDialog.dismiss();
                                // Objs.ac.StartActivityPutExtra(mCon,Document_Details.class, Params.user_type,user_type);
                                Intent intent = new Intent(ManiActivity_Image2_old.this, Applicant_Doc_Details_revamp_old.class);
                                startActivity(intent);
                                finish();
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
                      //  popupWindow.dismiss();

                        //   progressDialog.dismiss();
                        // Objs.ac.StartActivityPutExtra(mCon,Document_Details.class, Params.user_type,user_type);
                        Intent intent = new Intent(ManiActivity_Image2_old.this, Applicant_Doc_Details_revamp_old.class);
                        startActivity(intent);
                        finish();
                    }
                }
                myCommand.execute();

            }

        }
    }

    public void upload1()
    {

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
                            Log.d("Camara image1", String.valueOf(response));
                            //  Objs.a.showToast(mCon, String.valueOf(response));
                            // progressDialog.dismiss();

                            try {
                                JSONObject j = new JSONObject(response);
                                String a = j.getString(Params.status);

                               // Objs.a.showToast(mCon, "successfully uploaded");
                                Toast.makeText(ManiActivity_Image2_old.this, "Successfully uploaded", Toast.LENGTH_LONG).show();

                                // class_name1 = Pref.getapplicant_name(mCon);
                                //   Objs.ac.StartActivityPutExtra(mCon,Document_Details.class, Params.user_type,user_type, Params.class_name,class_name1);
                                // Objs.ac.StartActivityPutExtra(mCon,Applicant_Doc_Details_Property.class, Params.user_type,user_type, Params.applicant_name,applicant_name);
                                Intent intent = new Intent(ManiActivity_Image2_old.this, Applicant_Doc_Details_revamp_old.class);
                                startActivity(intent);
                                finish();
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
                    params.put(Params.transaction_id,  transaction_id);
                    params.put(Params.img_url, singleimage);
                    params.put(Params.is_mobileupload,"4");

                    Log.e("doc_typename",doc_typename);
                    Log.e("transaction_id",transaction_id);
                    Log.e("legal_id",docid);

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
    }
    public void uploadMultipart_PDF() {
        //getting name for the pdf
        //  String name = editText.getText().toString().trim();
      //  Log.d("Pdf file333333", String.valueOf(filePath));
        //getting the actual path of the pdf

      //  Log.e("PDF File", String.valueOf(path));
       // File sourceFile = new File(path);


            //Uploading code
            try {
                String uploadId = UUID.randomUUID().toString();
                uploadReceiver.setDelegate(this);
                uploadReceiver.setUploadID(uploadId);
                //Creating a multi part request
                progressDialog.show();
                new MultipartUploadRequest(this, uploadId, Urls.PDF_Document_Upload)
                      .addFileToUpload(orginalFile_pic.toString(), Params.img_url) //Adding file
                        .addParameter("legal_id", docid) //Adding text parameter to the request
                        .addParameter(Params.doc_name, doc_typename)
                        .addParameter(Params.transaction_id, transaction_id)
                        .addParameter(Params.is_mobileupload, "4")
                       // .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(2)
                        .startUpload(); //Starting the upload
              //  Log.e("img_url", path);
                Log.e("relationship_type", "1");
                Log.e("entity_id", "no");
                Log.e("transaction_id", transaction_id);

            } catch (Exception exc) {
                Log.e("AndroidUploadService", exc.getMessage(), exc);
                Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
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
            progressDialog.dismiss();
            //Objs.a.showToast(mCon, "Successfully uploaded");
            Toast.makeText(this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
           // Objs.ac.StartActivityPutExtra(mCon,Document_Details.class, Params.user_type,user_type);
            Intent intent = new Intent(ManiActivity_Image2_old.this, Applicant_Doc_Details_revamp_old.class);
            startActivity(intent);
            finish();
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

    @Override
    public void onBackPressed() {
        pickiT.deleteTemporaryFile(this);

        Intent intent = new Intent(mCon, DashBoard_new.class);
        startActivity(intent);
        // Objs.ac.StartActivity(mCon, LeadeFragment.class);
        finish();
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
                showFilePDF();
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
          //  uriarrayList_pic.add(new File(path));
            orginalFile_pic = new File(path);
            Log.e("the Path selected PIC",orginalFile_pic.toString());

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

