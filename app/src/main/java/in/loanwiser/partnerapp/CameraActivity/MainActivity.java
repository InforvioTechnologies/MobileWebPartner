package in.loanwiser.partnerapp.CameraActivity;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.PhotoLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Documents.MyCommand;
import in.loanwiser.partnerapp.R;

public class MainActivity extends AppCompatActivity {

    Button button;
    private static final int SELECT_SINGLE_PICTURE = 101;

    private static final int SELECT_MULTIPLE_PICTURE = 201;

    public static final String IMAGE_TYPE = "image/*";
    private ImageView selectedImagePreview;
    int count=0;
    private Bitmap bitmap,photo;
    String[] all_path,strNames;
    String id,doc_typename,docid,class_id,type,user_type,transaction_id;
    private Context mCon = this;
    ImageSwitcher imageIs;
    Button prev,next,pickimage;
    private ArrayList<Uri> imageUrls;
    String path;
    private static final int PICK_IMAGE_CODE = 0;
    int position=0;
    GridView gridView;
    GridAdapter gridAdapter;
    RecyclerView recyclerView;
    private android.app.AlertDialog progressDialog;
    private String status;
    Button upload;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_galary);
  /*      imageIs=findViewById(R.id.imageIs);
        prev=findViewById(R.id.prev);
        next=findViewById(R.id.next);*/
        pickimage=findViewById(R.id.pickimage);
        upload=findViewById(R.id.upload);


        imageUrls=new ArrayList<>();
        gridAdapter=new GridAdapter(this);
        recyclerView=findViewById(R.id.recycleview);
        progressDialog = new SpotsDialog(this, R.style.Custom);


        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(gridAdapter);

        doc_typename = Pref.getcamera_doc_typename(mCon);
        docid = Pref.getcamera_docid(mCon);
        transaction_id = Pref.getcamera_transaction_id(mCon);

        Log.i(TAG, "onCreate:doc_typename "+doc_typename);
        Log.i(TAG, "onCreate:docid "+docid);
        Log.i(TAG, "onCreate:transaction_id "+transaction_id);

      /*  imageIs.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                return imageView;
            }
        });*/

        pickimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickimage();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

       /* next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position<imageUrls.size()-1){
                    position++;
                    imageIs.setImageURI(imageUrls.get(position));
                }
                else{
                    Toast.makeText(MainActivity.this,"No Next",Toast.LENGTH_SHORT).show();

                }

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position>0){
                    position--;
                    imageIs.setImageURI(imageUrls.get(position));
                }
                else{
                    Toast.makeText(MainActivity.this,"No previw",Toast.LENGTH_SHORT).show();
                }

            }
        });
*/

    }

    private void pickimage(){
        Intent intent = new Intent();
        intent.setType(IMAGE_TYPE);
        // this line is different here !!
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Selected Image"),PICK_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==PICK_IMAGE_CODE){
            if (resultCode== Activity.RESULT_OK){

                if (data.getClipData()!=null){
                    int cout=data.getClipData().getItemCount();
                    for(int i=0;i<cout;i++){
                        //  getimageuri
                        Uri imageuri=data.getClipData().getItemAt(i).getUri();
                        imageUrls.add(imageuri);
                        String path=imageuri.getPath();
                        String fileget= String.valueOf(data.getClipData());
                        Log.i(TAG, "fileget: "+fileget);
                        Log.i("TAG", "imageUrlsResult: "+imageUrls);
                        Log.i("TAG", "onActivityImagepath "+path);
                        Log.i("TAG", "onActivitygetpath "+getImgPath(imageuri));
                        strNames = new String[imageUrls.size()];
                        for(int j=0; j < strNames.length; j++){
                            strNames[j] = String.valueOf(imageUrls.get(j));
                            //   Log.d("String : {} ", strNames[i]);
                        }

                        gridAdapter.setData(imageUrls);



                    }
                   /* imageIs.setImageURI(imageUrls.get(0));
                    position=0;*/
                }
                else{
                    Uri imageuri=data.getData();
                    imageUrls.add(imageuri);
                    gridAdapter.setData(imageUrls);

                    Log.i("TAG", "onActivityResult:singleimage "+imageUrls.add(imageuri));
                  /*  imageIs.setImageURI(imageUrls.get(0));
                    position=0;*/
                }

            }
        }

    }
    public String getImageFilePath(Uri uri) {

        File file = new File(uri.getPath());
        String[] filePath = file.getPath().split(":");
        String image_id = filePath[filePath.length - 1];

        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + " = ? ", new String[]{image_id}, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

            cursor.close();
            return imagePath;
        }
        return null;
    }


    private void upload(){
        final MyCommand myCommand = new MyCommand(getApplicationContext());
        Log.i("TAG", "upload:function "+"upload");
        Log.i(TAG, "upload:array "+strNames);
       for(String imagePath :strNames ) {
           count = count + 1;
           try {
               bitmap = PhotoLoader.init().from(imagePath).requestSize(512, 512).getBitmap();
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }
           final String encodedString = ImageBase64.encode(bitmap);
           progressDialog.show();
           StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.CAMERA_IMAGE_Upload, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {

                   try {
                       JSONObject j = new JSONObject(response);
                       Log.d("Galarel path length", String.valueOf(j));
                       status = j.getString("status");

                       count = count - 1;
                       Log.d("Galarel path length", String.valueOf(count));

                       Toast.makeText(MainActivity.this, "Successfully uploaded" + "\n" + "Please wait for all Document upload", Toast.LENGTH_SHORT).show();
                       if (count == 0) {
                           progressDialog.dismiss();
                           // Objs.ac.StartActivityPutExtra(mCon,Document_Details.class, Params.user_type,user_type);
                      /*  Intent intent = new Intent(ManiActivity_Image2.this, Applicant_Doc_Details_revamp.class);
                        startActivity(intent);*/
                           Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
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
           }) {
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String, String> params = new HashMap<>();
                   // params.put(Params.is_mobileupload, "4");
                   params.put("legal_id", docid);
                   params.put("doc_name", doc_typename);
                   params.put("transaction_id", transaction_id);
                   params.put("img_url", encodedString);
                   params.put("is_mobileupload", "4");
                   Log.d("Camara image", String.valueOf(params));
        /*        Log.e("legal_id ", docid);
                Log.e("doc_typename ", doc_typename);
                Log.e("ImageUrl",encodedString);
                Log.e("transaction_id ", transaction_id);*/
                   return params;

               }

           };


           int socketTimeout = 0;
           RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                   DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                   DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

           stringRequest.setRetryPolicy(policy);


           myCommand.add(stringRequest);

           myCommand.execute();
       }


    }


    public String getImgPath(Uri uri) {
        String[] largeFileProjection = { MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DATA };
        String largeFileSort = MediaStore.Images.ImageColumns._ID + " DESC";
        Cursor myCursor = this.managedQuery(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                largeFileProjection, null, null, largeFileSort);
        String largeImagePath = "";
        try {
            myCursor.moveToFirst();
            largeImagePath = myCursor
                    .getString(myCursor
                            .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
        } finally {
            myCursor.close();
        }
        return largeImagePath;
    }



    }




