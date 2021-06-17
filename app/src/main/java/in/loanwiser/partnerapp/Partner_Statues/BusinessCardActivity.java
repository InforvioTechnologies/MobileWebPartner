package in.loanwiser.partnerapp.Partner_Statues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class BusinessCardActivity extends SimpleActivity implements SwipeRefreshLayout.OnRefreshListener {

    AppCompatButton whatsappbutton,othernetworkbutton;
    AppCompatTextView editbtn;
    AppCompatImageView imageview;
    private AlertDialog progressDialog;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    public static final String b2b_user_id1 = "b2b_uer_id";
    String b2b_user_id;
    SharedPreferences pref; // 0 - for private mode
    String email,mobilenumber,contactperson,location,url,visitincard_url;
    ProgressBar progressBarMaterial_pdf;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    AppCompatTextView nametxt,emailtxt,webtxt,phntxt,locationtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //setContentView(R.layout.activity_business_card);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_business_card);
        initTools(R.string.businesscard);


        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swife);
        mSwipeRefreshLayout.setOnRefreshListener(BusinessCardActivity.this);
        super.onCreate(savedInstanceState);
        whatsappbutton=findViewById(R.id.whatsappbutton);
        othernetworkbutton=findViewById(R.id.othernetworkbutton);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        imageview=findViewById(R.id.imageview);
        progressBarMaterial_pdf=findViewById(R.id.progressBarMaterial_pdf);
        editbtn=findViewById(R.id.editbtn);

        nametxt=findViewById(R.id.nametxt);
        emailtxt=findViewById(R.id.emailtxt);
        webtxt=findViewById(R.id.webtxt);
        phntxt=findViewById(R.id.phntxt);
        locationtxt=findViewById(R.id.locationtxt);

        pref = this.getSharedPreferences("MyPref", 0);
        b2b_user_id =  pref.getString(b2b_user_id1, null);
        Log.i("TAG", "b2b_userid_onCreate: "+b2b_user_id);

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BusinessCardActivity.this,BusinesscardEditActivity.class);
                startActivity(intent);
            }
        });



        Businesscarddetails();

        whatsappbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPermissionREAD_EXTERNAL_STORAGE(BusinessCardActivity.this)) {
                    Glide.with(BusinessCardActivity.this)
                            .load(visitincard_url)
                            .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)

                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    progressDialog.dismiss();

                                    // intent.setPackage("com.whatsapp");
                                    try{
                                        Intent intent = new Intent(Intent.ACTION_SEND);
                                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), resource, "IMG_Url" + Calendar.getInstance().getTime(), null);
                                        Log.i("quoteswahttodo", "is onresoursereddy" + path);
                                        Uri screenshotUri = Uri.parse(path);
                                        String content="\n" +
                                                "Glad to be your Financial Consultant. Contact me at";
                                        String content3="" +"to assist you with your Loan needs." +
                                                "\n\nYou can also access my services through my website ";
                                        String content1= "I would be happy to assist you.";

                                        if(screenshotUri!=null){
                                            Log.i("quoteswahttodo", "is onresoursereddy" + screenshotUri);
                                            intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                            intent.setType("image/*");
                                            intent.setPackage("com.whatsapp");
                                            intent.putExtra(Intent.EXTRA_TEXT, content+" " +mobilenumber+" "+content3+url+". "+content1);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(Intent.createChooser(intent, "Share image via..."));
                                        }else
                                        {
                                            Toast.makeText(getApplicationContext(), "Something went wrong, Try Again1", Toast.LENGTH_SHORT).show();

                                        }


                                    } catch (Exception e) {
                                        Toast.makeText(getApplicationContext(), "Something went wrong, Try Again2", Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();
                                        e.printStackTrace();
                                        Log.e("the error,", String.valueOf(e));
                                    }

                                }
                                @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Something went wrong, Try Again3", Toast.LENGTH_SHORT).show();
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


        othernetworkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermissionREAD_EXTERNAL_STORAGE(BusinessCardActivity.this)) {
                    Glide.with(BusinessCardActivity.this)
                            .load(visitincard_url)
                            .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    progressDialog.dismiss();
                                    // intent.setPackage("com.whatsapp");
                                    try {
                                        Intent intent = new Intent(Intent.ACTION_SEND);
                                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                                        String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), resource, "IMG_s" + Calendar.getInstance().getTime(), null);

                                        // String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), resource, "", null);
                                        Log.i("quoteswahttodo", "is onresoursereddy" + path);
                                        String content="\n" +
                                                "Glad to be your Financial Consultant. Contact me at";
                                        String content3="" +"to assist you with your Loan needs." +
                                                "\n\nYou can also access my services through my website ";
                                        String content1= "I would be happy to assist you.";
                                        Uri screenshotUri = Uri.parse(path);
                                        if(screenshotUri!=null){
                                            Log.i("quoteswahttodo", "is onresoursereddy" + screenshotUri);
                                            intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                            intent.putExtra(Intent.EXTRA_TEXT, content+" " +mobilenumber+" "+content3+url+". "+content1);
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

    private void Businesscarddetails() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2b_userid", b2b_user_id);
            Log.i("TAG", "Request "+J.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data  = String.valueOf(J);
        Log.d("Request :", data);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GETBUSINESSCARD, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        String JO_data  = String.valueOf(response);
                        Log.d("Request :", JO_data.toString());
                        try {
                            email=response.getString("email_id");
                            mobilenumber=response.getString("mobile_no");
                            contactperson=response.getString("contact_person");
                            location=response.getString("location");
                            url=response.getString("url");

                            nametxt.setText(contactperson);
                            emailtxt.setText(email);
                            phntxt.setText(mobilenumber);
                            locationtxt.setText(location);
                            webtxt.setText(url);
                            visitincard_url=response.getString("visit_card");
                            Log.i("TAG", "onResponse:url "+visitincard_url);
                      //      Objs.a.loadPicasso(getApplicationContext(),visitincard_url,imageview,progressBarMaterial_pdf);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }



    @Override
    public void onRefresh() {
       // Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Businesscarddetails();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}