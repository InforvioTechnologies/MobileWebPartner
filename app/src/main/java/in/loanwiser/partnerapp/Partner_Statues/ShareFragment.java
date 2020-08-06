package in.loanwiser.partnerapp.Partner_Statues;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
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
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.BuildConfig;
import in.loanwiser.partnerapp.CameraActivity.DocGridView_List;
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
    private AlertDialog progressDialog;
    RecyclerView recyclerView;

    LinearLayout network_stat,mainlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share_fragment_activity, container, false);

     /*   binding = ActivityMainBinding.inflate(getLayoutInflater());
         view = binding.getRoot();
        setContentView(view);
*/

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        button_share = (Button) view.findViewById(R.id.button_share);
        imageShare = (Button) view.findViewById(R.id.image_share);
        testShare = (Button) view.findViewById(R.id.test_share);
        network_stat =  view.findViewById(R.id.network_stat);
        mainlay =  view.findViewById(R.id.mainlay);

        if(isConnected()==false){
            network_stat.setVisibility(View.VISIBLE);
            mainlay.setVisibility(View.GONE);
        }


        progressDialog = new SpotsDialog(getContext(), R.style.Custom);
       // Share_images(view);
        Document_Details();
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
                                progressDialog.dismiss();
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
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                super.onLoadFailed(e, errorDrawable);
                            }

                            @Override public void onLoadStarted(Drawable placeholder) {
                                progressDialog.show();
                                Toast.makeText(getActivity(), "Please wait", Toast.LENGTH_SHORT).show();
                            }


                        });


            }
        });
        return view;


    }


    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
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
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        super.onLoadFailed(e, errorDrawable);
                    }

                    @Override public void onLoadStarted(Drawable placeholder) {

                        Toast.makeText(getActivity(), "Starting", Toast.LENGTH_SHORT).show();
                    }


                });
    }

    private void Document_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;


        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.POST_SHARE, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("Share Response", String.valueOf(response));

                           // JSONObject jsonObject1 = response.getJSONObject("response");
                            JSONArray ja = response.getJSONArray("data");
                            if (ja.length()>0){
                                setAdapter(ja);
                            }else {
                                Objs.a.ShowHideNoItems(getActivity(),true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
               // Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
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
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void setAdapter(JSONArray ja) {
        ShareFragment.ListItemAdapter adapter = new ShareFragment.ListItemAdapter(getActivity(),ja);
        // Objs.a.getRecyleview_horizontal(this).setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new ShareFragment.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    //Adapter Class list
    public class ListItemAdapter extends RecyclerView.Adapter<ShareFragment.ListItemAdapter.ViewHolder> {

        JSONArray list = new JSONArray();
        Context mCon;
        JSONObject J;
        public ListItemAdapter(Context mCon, JSONArray list) {
            this.list = list;
            this.mCon = mCon;
        }
        @Override
        public int getItemCount() {
            return list.length();
        }
        public JSONObject getItem(int i) {
            try {
                return list.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public ShareFragment.ListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_share_image_list, parent, false);
            return new ShareFragment.ListItemAdapter.ViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(final ShareFragment.ListItemAdapter.ViewHolder holder, final int position) {
            try {
                J = getItem(position);

             /*   holder.updated_.setText(Objs.a.capitalize(J.getString(Params.update_at)));
                Objs.a.NewNormalFontStyle(mCon,holder.updated_);*/

                final String img_url1 = J.getString("post_url");
                final String content = J.getString("content");

                holder.Title.setText(J.getString("title"));
                Log.e("img_url",img_url1);

               /* Glide.with(getContext()).load(img_url)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.image_Pdf);*/
               Objs.a.loadPicasso(mCon,img_url1,holder.image_Pdf,holder.progressBarMaterial_pdf);

               /* if(J.getString(Params.type).equals("pdf")){
                    holder.Ly_Rl_pdf_reader.setVisibility(View.VISIBLE);
                    holder.Ly_image_reader.setVisibility(View.GONE);
                    String image = "http://cscapi.propwiser.com/mobile/images/pdf.jpg";
                    Objs.a.loadPicasso(mCon,image,holder.image_Pdf,holder.progressBarMaterial_pdf);
                }else{

                    holder.Ly_Rl_pdf_reader.setVisibility(View.GONE);
                    holder.Ly_image_reader.setVisibility(View.VISIBLE);

                    String path =J.getString(Params.document);
                    // Bitmap d = new BitmapDrawable(ctx.getResources() , w.photo.getAbsolutePath()).getBitmap();
                   *//* Bitmap d = new BitmapDrawable(mCon.getResources() , path).getBitmap();
                    int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
                    Bitmap scaled = Bitmap.createScaledBitmap(d, 512, nh, true);
                    holder.v_Image.setImageBitmap(scaled);*//*

                    Objs.a.loadPicasso(mCon,J.getString(Params.document),holder.v_Image,holder.progressBar);

                }*/

                holder.share_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Glide.with(getContext())
                                .load(img_url1)
                                .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(Intent.ACTION_SEND);
                                        intent.putExtra(Intent.EXTRA_TEXT, content);
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
                                        progressDialog.dismiss();
                                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                        super.onLoadFailed(e, errorDrawable);
                                    }

                                    @Override public void onLoadStarted(Drawable placeholder) {
                                        progressDialog.show();
                                       // Toast.makeText(getActivity(), "Starting", Toast.LENGTH_SHORT).show();
                                    }


                                });
                    }
                });

                holder.whats_app_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Glide.with(getContext())
                                .load(img_url1)
                                .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(Intent.ACTION_SEND);
                                        intent.putExtra(Intent.EXTRA_TEXT, content);
                                        String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), resource, "", null);
                                        Log.i("quoteswahttodo", "is onresoursereddy" + path);
                                        Uri screenshotUri = Uri.parse(path);
                                        Log.i("quoteswahttodo", "is onresoursereddy" + screenshotUri);
                                        intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                        intent.setType("image/*");
                                        intent.setPackage("com.whatsapp");
                                        // intent.setPackage("com.whatsapp");
                                        try{
                                            startActivity(Intent.createChooser(intent, "Share image via..."));

                                        } catch (Exception e) {
                                            Toast.makeText(getActivity(), "It seem like Whatsapp is not been installed", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }

                                    }
                                    @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                                        super.onLoadFailed(e, errorDrawable);
                                    }

                                    @Override public void onLoadStarted(Drawable placeholder) {
                                        progressDialog.show();
                                       // Toast.makeText(getActivity(), "Please wait it is loading!!!", Toast.LENGTH_SHORT).show();
                                    }

                                });



                    }
                });



            } catch (NullPointerException e) {
                Objs.a.showToast(mCon, e.toString());
            } catch (Exception e) {
                Objs.a.showToast(mCon, e.toString());
            }
        }
        private void update(JSONArray list1) {
            list = list1;
        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView v_Image,image_Pdf;
            AppCompatImageView share_image,whats_app_share;
            ProgressBar progressBar,progressBarMaterial_pdf;
            View view;
            WebView webview;
            AppCompatTextView Title;
            ProgressBar progressbar;
            LinearLayout Ly_image_reader,Ly_Rl_pdf_reader,Ly_item;

            public ViewHolder(View itemView) {
                super(itemView);
                Ly_image_reader = (LinearLayout)itemView.findViewById(R.id.Ly_image_reader);
                Ly_Rl_pdf_reader = (LinearLayout)itemView.findViewById(R.id.Ly_Rl_pdf_reader);
                Ly_item = (LinearLayout)itemView.findViewById(R.id.Ly_item);
                //  progressbar = (ProgressBar) itemView.findViewById(R.id.progressbar);
                v_Image = (ImageView) itemView.findViewById(R.id.image_Product);
                image_Pdf = (ImageView) itemView.findViewById(R.id.image_Pdf);

                share_image = (AppCompatImageView) itemView.findViewById(R.id.share_image);
                whats_app_share = (AppCompatImageView) itemView.findViewById(R.id.whats_app_share);

                Title = (AppCompatTextView) itemView.findViewById(R.id.Title);
                progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);
                progressBarMaterial_pdf = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial_pdf);

            }
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;
        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
