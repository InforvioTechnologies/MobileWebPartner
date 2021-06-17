package in.loanwiser.partnerapp.Partner_Statues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

import static in.loanwiser.partnerapp.Partner_Statues.Post_share_Statues.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;

public class ShareActivity extends SimpleActivity {

    String content,imgurl,title,app_content;
    AppCompatImageView shareimage;
    AppCompatTextView contenttxt,contenttxt1;
    AppCompatButton whatsappshare,othernetworkshare;
    private AlertDialog progressDialog;

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    ProgressBar progressBarMaterial_pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_share);
        initTools(R.string.sharepromo);
        shareimage=findViewById(R.id.shareimage);
        contenttxt=findViewById(R.id.contenttxt);
        contenttxt1=findViewById(R.id.contenttxt1);
        progressBarMaterial_pdf=findViewById(R.id.progressBarMaterial_pdf);
        whatsappshare=findViewById(R.id.whatsappshare);
        contenttxt=findViewById(R.id.contenttxt);
        othernetworkshare=findViewById(R.id.othernetwork);
        progressDialog = new SpotsDialog(this, R.style.Custom);

        Intent intent=getIntent();
        content=intent.getStringExtra("content");
        app_content=intent.getStringExtra("app_content");
        imgurl=intent.getStringExtra("imgurl");
        title=intent.getStringExtra("title");

        Log.i("TAG", "onCreate:imgurl "+imgurl);
        Log.e("the value",imgurl);
        contenttxt.setText(title);
        if(!app_content.equals("null") && !app_content.isEmpty())
        {
            contenttxt1.setText(app_content);
        }
       // contenttxt1.setText(app_content);
      /*  Glide.with(this)
                .load(imgurl)
                .into(shareimage);
        contenttxt.setText(content);*/

        Objs.a.loadPicasso(getApplicationContext(),imgurl,shareimage,progressBarMaterial_pdf);

     /*   whatsappshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,content);
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(imgurl));
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "Share image via:"));
            }
        });*/


        whatsappshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (checkPermissionREAD_EXTERNAL_STORAGE(getApplicationContext())) {
                    Glide.with(getApplicationContext())
                            .load(imgurl)
                            .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)

                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    progressDialog.dismiss();

                                    // intent.setPackage("com.whatsapp");
                                    try{
                                        Intent intent = new Intent(Intent.ACTION_SEND);
                                        intent.putExtra(Intent.EXTRA_TEXT, content);
                                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);



                                      //  String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), resource, "", null);
                                        String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), resource, "IMG_" + Calendar.getInstance().getTime(), null);

                                        Log.i("quoteswahttodo", "is onresoursereddy" + path);
                                        Uri screenshotUri = Uri.parse(path);


                                        if(screenshotUri!=null){
                                            Log.i("quoteswahttodo", "is onresoursereddy" + screenshotUri);
                                            intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                            intent.setType("image/*");
                                            intent.setPackage("com.whatsapp");
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(Intent.createChooser(intent, "Share image via..."));
                                        }else
                                        {
                                            Toast.makeText(getApplicationContext(), "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();

                                        }


                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                        e.printStackTrace();
                                        Log.e("the error,", String.valueOf(e));
                                    }

                                }
                                @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                                    super.onLoadFailed(e, errorDrawable);
                                }

                                @Override public void onLoadStarted(Drawable placeholder) {
                                    progressDialog.show();
                                    // Toast.makeText(getActivity(), "Please wait it is loading!!!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }



            }
        });

        othernetworkshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkPermissionREAD_EXTERNAL_STORAGE(getApplicationContext())) {
                    Glide.with(getApplicationContext())
                            .load(imgurl)
                            .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    progressDialog.dismiss();
                                    // intent.setPackage("com.whatsapp");
                                    try {
                                        Intent intent = new Intent(Intent.ACTION_SEND);
                                        intent.putExtra(Intent.EXTRA_TEXT, content);
                                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                                        String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), resource, "IMG_S" + Calendar.getInstance().getTime(), null);

                                       // String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), resource, "", null);
                                        Log.i("quoteswahttodo", "is onresoursereddy" + path);
                                        Uri screenshotUri = Uri.parse(path);
                                        if(screenshotUri!=null){
                                            Log.i("quoteswahttodo", "is onresoursereddy" + screenshotUri);
                                            intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                            intent.setType("image/*");
                                            startActivity(Intent.createChooser(intent, "Share image via..."));
                                        }else
                                        {
                                            Toast.makeText(getApplicationContext(), "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();

                                        }


                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                        e.printStackTrace();
                                        Log.e("the error,", String.valueOf(e));
                                    }

                                }

                                @Override
                                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Something went wrong, Try Again", Toast.LENGTH_SHORT).show();
                                    super.onLoadFailed(e, errorDrawable);
                                }

                                @Override
                                public void onLoadStarted(Drawable placeholder) {
                                    progressDialog.show();
                                    // Toast.makeText(getActivity(), "Starting", Toast.LENGTH_SHORT).show();
                                }


                            });
                }

            }

        });

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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff

                } else {
                    Toast.makeText(getApplicationContext(), "GET_ACCOUNTS Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }
    
}