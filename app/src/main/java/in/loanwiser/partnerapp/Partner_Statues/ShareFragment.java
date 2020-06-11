package in.loanwiser.partnerapp.Partner_Statues;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import in.loanwiser.partnerapp.BuildConfig;
import in.loanwiser.partnerapp.My_Earnings.My_Earnings;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.R;


public class ShareFragment extends Fragment{

    public ShareFragment() {
        // Required empty public constructor
    }

    public static ShareFragment newInstance(String param1, String param2) {
        ShareFragment fragment = new ShareFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private AppCompatTextView profile;
    private AppCompatButton navigate,navigate1;
    private SliderLayout mDemoSlider;
    private ShareFragment mcon =this;
    private LinearLayout chat;
    private RecyclerView recycler_view;
    ArrayList<Suggestion_item_freqent> items;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    Resent_Lead_Statues adapter;

    AppCompatButton my_earnings,my_leads;


  //  private ActivityMainBinding binding;

    String image_url = "https://www.paisabazaar.com/wp-content/uploads/2019/09/Personal-Loan-1.jpg";

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    ImageView imageView;

    String URL = "https://s3.amazonaws.com/uifaces/faces/twitter/mrmoiree/128.jpg";
    public static final int PERMISSION_WRITE = 0;
    String fileUri;

    Button button_share,imageShare,testShare;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share_fragment_activity, container, false);

     /*   binding = ActivityMainBinding.inflate(getLayoutInflater());
         view = binding.getRoot();
        setContentView(view);
*/
        button_share = (Button) view.findViewById(R.id.button_share);
        imageShare = (Button) view.findViewById(R.id.image_share);
        testShare = (Button) view.findViewById(R.id.test_share);

       // Share_images(view);

        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///internal storage/DCIM/Camera/test.jpg"));
                share.setPackage("com.whatsapp");//package name of the app
                startActivity(Intent.createChooser(share, "Share Image"));*/

                Bitmap bm = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.personal);

                File filesDir = getContext().getFilesDir();
                File imageFile = new File(filesDir, "ABeautifulFilename.png");

                OutputStream os;
                try {
                    os = new FileOutputStream(imageFile);
                    bm.compress(Bitmap.CompressFormat.PNG, 100, os); // 100% quality
                    os.flush();
                    os.close();
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                }

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);

// set flag to give temporary permission to external app to use your FileProvider
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

// generate URI, I defined authority as the application ID in the Manifest, the last param is file I want to open
                Uri uri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID, imageFile);
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.putExtra(Intent.EXTRA_TEXT,"Personal loan Available Here!!!!");

// Set type to only show apps that can open your PNG file
                intent.setType("image/png");

// start activity!
                startActivity(Intent.createChooser(intent, "send"));



            }
        });

        imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                //Target whatsapp:
                shareIntent.setPackage("com.whatsapp");
                //Add text and then Image URI
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Antony");
                shareIntent.putExtra(Intent.EXTRA_STREAM, getBitmapFromURL(image_url));
                shareIntent.setType("image/jpeg");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                try {
                    startActivity(shareIntent);
                } catch (android.content.ActivityNotFoundException ex) {

                    Toast.makeText(getActivity(),"Whatsapp not installed",Toast.LENGTH_SHORT).show();
                }

            }
        });


        testShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Glide.with(getContext())
                        .load(image_url)
                        .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.putExtra(Intent.EXTRA_TEXT, "Hey view/download this image");
                                String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), resource, "", null);
                                Log.i("quoteswahttodo", "is onresoursereddy" + path);
                                Uri screenshotUri = Uri.parse(path);
                                Log.i("quoteswahttodo", "is onresoursereddy" + screenshotUri);
                                intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                intent.setType("image/*");
                                // intent.setPackage("com.whatsapp");
                                try{
                                    startActivity(Intent.createChooser(intent, "Share image via..."));

                                } catch (Exception e) {
                                    Toast.makeText(getActivity(), "It seem like Whatsapp is not been installed", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                            }
                            @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                super.onLoadFailed(e, errorDrawable);
                            }

                            @Override public void onLoadStarted(Drawable placeholder) {
                                Toast.makeText(getActivity(), "Starting", Toast.LENGTH_SHORT).show();
                            }


                        });


            }
        });
        return view;


    }

    private void Share_images(View view)
    {
        Glide.with(getContext())
                .load(image_url)
                .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, "Hey view/download this image");
                        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), resource, "", null);
                        Log.i("quoteswahttodo", "is onresoursereddy" + path);
                        Uri screenshotUri = Uri.parse(path);
                        Log.i("quoteswahttodo", "is onresoursereddy" + screenshotUri);
                        intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                        intent.setType("image/*");
                        // intent.setPackage("com.whatsapp");
                        try{
                            startActivity(Intent.createChooser(intent, "Share image via..."));

                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "It seem like Whatsapp is not been installed", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                    @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        super.onLoadFailed(e, errorDrawable);
                    }

                    @Override public void onLoadStarted(Drawable placeholder) {
                        Toast.makeText(getActivity(), "Starting", Toast.LENGTH_SHORT).show();
                    }


                });
    }


    public static Bitmap getBitmapFromURL(String imgUrl) {
        try {
            java.net.URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }


}
