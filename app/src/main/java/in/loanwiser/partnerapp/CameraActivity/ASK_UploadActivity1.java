package in.loanwiser.partnerapp.CameraActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Documents.Applicant_Doc_Details_revamp;
import in.loanwiser.partnerapp.Documents.SingleUploadBroadcastReceiver;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;

public class ASK_UploadActivity1 extends Activity implements SingleUploadBroadcastReceiver.Delegate {

    private static final String TAG = Camara_Activity.class.getSimpleName();

    //private Uri filePath;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private final SingleUploadBroadcastReceiver uploadReceiver =
            new SingleUploadBroadcastReceiver();
    private android.app.AlertDialog progressDialog;
    private Context mCon = this;
    private ProgressBar progressBar;
    private ProgressDialog pDialog;
    private String filePath = null;
    private TextView txtPercentage;
    private ImageView imgPreview;
    private VideoView vidPreview;
    private Button btnUpload,rotation;
    private Bitmap bitmap;
    long totalSize = 0;
    String id,doc_typename,docid,class_id,type,user_type,transaction_id,applicant_name,ask_id;
    private ExifInterface exifObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_activity_upload);
        //   txtPercentage = (TextView) findViewById(R.id.txtPercentage);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);
        vidPreview = (VideoView) findViewById(R.id.videoPreview);
        rotation = (Button) findViewById(R.id.rotation);



        progressDialog = new SpotsDialog(this, R.style.Custom);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");


       // id = Pref.getcameraID(mCon);
        doc_typename = Pref.getcamera_doc_typename(mCon);
        docid = Pref.getcamera_docid(mCon);
       // class_id = Pref.getcamera_class_id(mCon);
       // user_type = Pref.getcamera_user_type(mCon);
        transaction_id = Pref.getcamera_transaction_id(mCon);
        ask_id = Pref.getask_id(mCon);


        Log.e("doc_typename",doc_typename);
        Log.e("docid",docid);
        Log.e("transaction_id",transaction_id);
        Log.e("ask_id",ask_id);
        // Receiving the data from previous activity
        Intent i = getIntent();

        // image or video path that is captured in previous activity
        filePath = i.getStringExtra("filePath");

        // boolean flag to identify the media type, image or video
        final boolean isImage = i.getBooleanExtra("isImage", true);

        if (filePath != null) {
            // Displaying the image or video on the screen
            previewMedia(isImage);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry, file path is missing!", Toast.LENGTH_LONG).show();
        }

        btnUpload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                upload();
            }
        });

        rotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(imgPreview.getDrawable() != null){
                    try {
                        exifObject = new ExifInterface(filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int orientation = exifObject.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                    if(orientation == 0)
                    {

                        Toast.makeText(ASK_UploadActivity1.this, "This Mobile does not support Rotate View", Toast.LENGTH_LONG).show();
                        rotation.setVisibility(View.GONE);
                    }
                    else
                    {

                        Bitmap imageRotate = rotateBitmap(bitmap,orientation);
                        bitmap = imageRotate;
                        imgPreview.setImageBitmap(imageRotate);
                    }


                }else{
                    Toast.makeText(ASK_UploadActivity1.this, "Image photo is not yet set", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void previewMedia(boolean isImage) {
        // Checking whether captured media is image or video
        if (isImage) {
            imgPreview.setVisibility(View.VISIBLE);
            vidPreview.setVisibility(View.GONE);
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();
            // images
            options.inSampleSize = 8;

            int rotate = 0;
            int orientation = 0;
            try {
                //getContentResolver().notifyChange(imageUri, null);
                File imageFile = new File(filePath);
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
            bitmap = BitmapFactory.decodeFile(filePath, options);
            imgPreview.setImageBitmap(bitmap);
            //  imgPreview.setDisplayOrientation(90);

        } else {
            imgPreview.setVisibility(View.GONE);
            vidPreview.setVisibility(View.VISIBLE);
            vidPreview.setVideoPath(filePath);
            // start playing
            vidPreview.start();
        }
    }


    public void upload()
    {

        if(bitmap!=null)
        {
            final String singleimage = getStringImage(bitmap);
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.CAMERA_IMAGE_Upload,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject j = new JSONObject(response);
                                String a = j.getString(Params.status);
                                Objs.a.showToast(mCon, "successfully uploaded");
                              //  applicant_name = Pref.getapplicant_name(mCon);
                             //   Objs.ac.StartActivityPutExtra(mCon, Applicant_Doc_Details_revamp.class, Params.user_type,user_type, Params.applicant_name,applicant_name);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("legal_id", docid);

                    params.put(Params.doc_name, doc_typename);
                    params.put(Params.transaction_id,  transaction_id);
                    params.put(Params.is_mobileupload,  "4");
                    params.put(Params.img_url, singleimage);
                    return params;
                }
            };

            int socketTimeout = 0;
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

            stringRequest.setRetryPolicy(policy);
            AppController.getInstance().addToRequestQueue(stringRequest);
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


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
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


        if(response.equals("200")){
          //  progressDialog.dismiss();
          //  Objs.a.showToast(mCon, "Successfully uploaded");
            ASK_Submit_Lowiser();
            Pref.removecameraID(mCon);
            Pref.removecamera_doc_typename(mCon);
            Pref.removecamera_docid(mCon);
            Pref.removecamera_class_id(mCon);
            Pref.removecamera_user_type(mCon);
            Pref.removecamera_transaction_id(mCon);
            //
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
    /**
     * Uploading the file to server
     * */


    /**
     * Method to show alert dialog
     * */

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
                                Intent intent = new Intent(mCon, DashBoard_new.class);
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