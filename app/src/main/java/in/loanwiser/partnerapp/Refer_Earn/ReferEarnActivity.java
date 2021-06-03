package in.loanwiser.partnerapp.Refer_Earn;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

import java.util.Calendar;
import java.util.HashMap;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class ReferEarnActivity extends SimpleActivity implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {

    TextView linkReceiveTextView,linkSendTextView;
    Button button,button1;
   // private static final String DEEP_LINK_URL = "https://example.com/deeplinks";
    private static final String DEEP_LINK_URL = "https://loanwiser.page.link/iGuj";
    String custid="59475";
    String propid="59475";
    private SliderLayout mDemoSlider;
    AppCompatImageView whatsappbtn;
    String b2b_user_id;
    SharedPreferences pref;
    public static final String b2b_user_id1 = "b2b_uer_id";
    AppCompatButton sharebuton;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private Context context = this;
    Uri shortLink;

    String shotlint;
    String image_url = "https://loanwiser.in/images/PartnerFacing.jpeg";

    private AlertDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_refer_earn);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_refer_earn);
        initTools(R.string.refereran);
        progressDialog = new SpotsDialog(context, R.style.Custom);
        //linkReceiveTextView=findViewById(R.id.linkViewReceive);
        linkSendTextView=findViewById(R.id.linkViewSend);
        button=findViewById(R.id.button);
        button1=findViewById(R.id.button1);
        whatsappbtn=findViewById(R.id.whatsappbtn);
        sharebuton=findViewById(R.id.sharebuton);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        HashMap<String,String> url_maps = new HashMap<String, String>();
        pref = this.getSharedPreferences("MyPref", 0);
        b2b_user_id =  pref.getString(b2b_user_id1, null);
        Log.i("TAG", "onCreate:b2b_userid "+b2b_user_id);


        url_maps.put("Home Loan", "https://cscapi.loanwiser.in/mobile/images/home_loan.png");
        url_maps.put("Loan Against Property", "https://cscapi.loanwiser.in/mobile/images/Loan_aganst_property.png");
        url_maps.put("Personal Loan", "https://cscapi.loanwiser.in/mobile/images/Personal_loan.png");
        url_maps.put("Business Loan", "https://cscapi.loanwiser.in/mobile/images/Busines_loan.png");
        url_maps.put("Vehicle Loan", "https://cscapi.loanwiser.in/mobile/images/vehicle_Loan.png");
        url_maps.put("Our Banks", "https://cscapi.loanwiser.in/mobile/images/Our_Banking_Partnersl.png");




        for(final String name : url_maps.keySet()){
            // TextSliderView textSliderView = new TextSliderView(this);
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    // .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
                    /*.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {


                                Intent intent = new Intent(Dashboard_Activity.this,View_More.class);
                                startActivity(intent);


                        }
                    });*/

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);


        //firebase();


        // Create a deep link and display it in the UI
        final Uri deepLink = buildDeepLink(Uri.parse(DEEP_LINK_URL), 0);
        linkSendTextView.setText(deepLink.toString());
        Log.i("TAG", "onCreate:linkSendTextView "+deepLink.toString());

        // [START get_deep_link]
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                        }


                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // [START_EXCLUDE]
                        // Display deep link in the UI
                        if (deepLink != null) {
                            Snackbar.make(findViewById(android.R.id.content),
                                    "Found deep link!", Snackbar.LENGTH_LONG).show();

                            linkReceiveTextView.setText(deepLink.toString());
                            Log.i("TAG", "deeplink:linkReceiveTextView: "+deepLink.toString());
                        } else {
                            Log.d("TAG", "getDynamicLink: no link found");
                        }
                        // [END_EXCLUDE]
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "getDynamicLink:onFailure", e);
                    }
                });
        // [END get_deep_link]

     /*   // Create a deep link and display it in the UI
        final Uri deepLink = buildDeepLink(Uri.parse(DEEP_LINK_URL), 0);
        linkSendTextView.setText(deepLink.toString());
        Log.i("TAG", "onCreate:linkSendTextView "+deepLink.toString());*/


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDeepLink(deepLink.toString());
            }
        });



        sharebuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                 /*   Intent intent = new Intent(Intent.ACTION_SEND);
                                    intent.setType("text/plain");
                                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    intent.putExtra(Intent.EXTRA_SUBJECT, "Firebase Deep Link");
                                    intent.putExtra(Intent.EXTRA_TEXT,shortLink.toString());
                                    intent.putExtra(Intent.EXTRA_TEXT, image_url);
                                    // Target whatsapp:
                                  //  intent.setPackage("com.whatsapp");
                                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    startActivity(intent);

*/
                String content="\n" +
                        "I recommend you Loanwiser Partner App to Manage your Loan services business at ease & Get to earn Rs25,000 and above per Month." +
                        "\n\nGet the most out of Loanwiser: " +
                        "\n1. Personalized Promotion Materials and tools helps us in Lead generation." +
                        "\n2. On spot Loan Viability Check and Instantaneous Credit Assessment helps you save time from running around to Banks with File." +
                        "\n3. Inbuilt Partner Training Module keeps us abreast about lending business" +
                        "\n4. Dedicated Relationship Manager and Transparent Process" +
                        "\n\n\n Click here & installs the App ";
                if (checkPermissionREAD_EXTERNAL_STORAGE(context)) {
                    Glide.with(getApplicationContext())
                            .load(image_url)
                            .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                      progressDialog.dismiss();
                                    // intent.setPackage("com.whatsapp");
                                    try {
                                        Intent intent = new Intent(Intent.ACTION_SEND);
                                        //  intent.putExtra(Intent.EXTRA_TEXT,"Refer a link");

                                        intent.putExtra(Intent.EXTRA_TEXT, content+"\n"+shortLink.toString());
                                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                        //intent.putExtra(Intent.EXTRA_TEXT,"Refer a link");

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

    @VisibleForTesting
    public Uri buildDeepLink(@NonNull Uri deepLink, int minVersion) {
        String uriPrefix = getString(R.string.dynamic_links_uri_prefix);

        // Set dynamic link parameters:
        //  * URI prefix (required)
        //  * Android Parameters (required)
        //  * Deep link
        // [START build_dynamic_link]
        DynamicLink.Builder builder = FirebaseDynamicLinks.getInstance()
                .createDynamicLink()
                .setDomainUriPrefix("https://loanwiser.page.link")
                //.setDomainUriPrefix("loanwiser.page.link")
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder()
                        .setMinimumVersion(minVersion)
                        .build())
                .setLink(deepLink);

        Log.i("TAG", "buildDeepLink: "+builder.toString());


        String sharelinktext  = "https://loanwiser.page.link/?"+
               // "link=http://www.blueappsoftware.com/"+
               // "link=https://loanwiser.in/"+
                "link=https://loanwiser.in/myrefer.php?custid="+b2b_user_id+"-"+b2b_user_id+
                "&apn="+ getPackageName()+
                "&st="+"My Refer Link"+
                "&sd="+"Reward Coins 50"+
               // "&si="+"https://www.blueappsoftware.com/logo-1.png";
                "&si="+"https://loanwiser.in/images/favicon.png";



        // Build the dynamic link
        DynamicLink link = builder.buildDynamicLink();
        // [END build_dynamic_link]
        Log.i("TAG", "buildDeepLink:uri "+link.getUri());
        Task<ShortDynamicLink> shortLinkTask = FirebaseDynamicLinks.getInstance().createDynamicLink()
               // .setLongLink(link.getUri())
                .setLongLink(Uri.parse(sharelinktext))
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if (task.isSuccessful()) {
                            // Short link created
                             shortLink = task.getResult().getShortLink();
                            Uri flowchartLink = task.getResult().getPreviewLink();
                            Log.e("TAG", "onComplete: " +shortLink.toString());






                        } else {
                            // Error
                            // ...
                            Log.e("TAG", "onComplete:issue " +task.getException());
                        }
                    }
                });
        // Return the dynamic link as a URI
        return link.getUri();
    }





    private void shareDeepLink(String deepLink) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Firebase Deep Link");
        intent.putExtra(Intent.EXTRA_TEXT,deepLink);

        startActivity(intent);
    }

    private void shortDeepLink(String deepLinks) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Firebase Deep Link");
        intent.putExtra(Intent.EXTRA_TEXT,deepLinks);

        startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

   /* private void firebase(){
        Log.i("TAG", "firebase: "+"Functioncall");
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            Log.i("TAG", "onSuccess: "+deepLink.toString());
                            String currentpage=deepLink.getQueryParameter("curPage");
                            int curpage=Integer.parseInt(currentpage);
                        }


                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "getDynamicLink:onFailure", e);
                    }
                });

    }
*/

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

}