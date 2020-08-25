package in.loanwiser.partnerapp.Partner_Statues;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;

public class Post_share_Statues extends RecyclerView.Adapter<Post_share_Statues.CustomViewHolder> {

    private Context context;
    private ArrayList<post_item_freqent> items;
    private String lead_id;
    private AlertDialog progressDialog;

    String Loan_amount,sub_categoryid,transaction_id1,subtask_id,loan_type_id,loan_type,
            payment,applicant_id1;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public Post_share_Statues(Context context, ArrayList<post_item_freqent> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.poster_share, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
      //  holder.applicant_id.setText("Testing Allocated Lead");

        /* Suggestion_item_freqent( J.getString(Params.id), J.getString(Params.user_id),J.getString(Params.name),
                                                J.getString(Params.mobileno),J.getString(Params.appointment_date),J.getString(Params.appointfrom_time)))*/

        String title  = items.get(position).gettitle();
       final String content  = items.get(position).getcontent();
        final String post_url  = items.get(position).getpost_url();
        progressDialog = new SpotsDialog(context, R.style.Custom);


        holder.Title.setText(title);
       // holder.loan_amount.setText(loan_amount);
        Objs.a.loadPicasso(context,post_url,holder.image_Pdf,holder.progressBarMaterial_pdf);


        holder.share_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(context)
                        .load(post_url)
                        .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.putExtra(Intent.EXTRA_TEXT, content);
                                String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), resource, "", null);
                                Log.i("quoteswahttodo", "is onresoursereddy" + path);
                                Uri screenshotUri = Uri.parse(path);
                                Log.i("quoteswahttodo", "is onresoursereddy" + screenshotUri);
                                intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                intent.setType("image/*");

                                // intent.setPackage("com.whatsapp");
                                try{
                                    context.startActivity(Intent.createChooser(intent, "Share image via..."));

                                } catch (Exception e) {
                                    Toast.makeText(context, "It seem like Whatsapp is not been installed", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                            }
                            @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                progressDialog.dismiss();
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
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

                Glide.with(context)
                        .load(post_url)
                        .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                progressDialog.dismiss();
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.putExtra(Intent.EXTRA_TEXT, content);
                                String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), resource, "", null);
                                Log.i("quoteswahttodo", "is onresoursereddy" + path);
                                Uri screenshotUri = Uri.parse(path);
                                Log.i("quoteswahttodo", "is onresoursereddy" + screenshotUri);
                                intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                intent.setType("image/*");
                                intent.setPackage("com.whatsapp");
                                // intent.setPackage("com.whatsapp");
                                try{
                                    context.startActivity(Intent.createChooser(intent, "Share image via..."));

                                } catch (Exception e) {
                                    Toast.makeText(context, "It seem like Whatsapp is not been installed", Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }

                            }
                            @Override public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                progressDialog.dismiss();
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                                super.onLoadFailed(e, errorDrawable);
                            }

                            @Override public void onLoadStarted(Drawable placeholder) {
                                progressDialog.show();
                                // Toast.makeText(getActivity(), "Please wait it is loading!!!", Toast.LENGTH_SHORT).show();
                            }

                        });



            }
        });





        //  Log.e("The Appointment Date",name);
      //  String dt = parseDateToddMMyyyy(appointment_date);
       // holder.applicant_date.setText(dt);


     /*  holder.Bt_create_appointment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String appoint_id  = items.get(position).getAppointment_id();
               String applicant_id  = items.get(position).getUser_ID();
               String lead_id = "1";
               String SOD_EOD_ID = "11";

           }
       });*/
    }

    @Override
    public int getItemCount() {
        return items.size();
       // return 6;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView lead_name,loan_amount,loan_type,Statues_update_dot,Statues_update;
        private CardView cardView;
        ImageView image_Pdf;
        private AppCompatButton Bt_create_appointment;
        AppCompatTextView Title;
        ProgressBar progressBarMaterial_pdf;
        AppCompatImageView share_image,whats_app_share;

        public CustomViewHolder(View view) {
            super(view);
            Title = view.findViewById(R.id.Title);
            image_Pdf = view.findViewById(R.id.image_Pdf);
            progressBarMaterial_pdf = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial_pdf);
            share_image = (AppCompatImageView) itemView.findViewById(R.id.share_image);
            whats_app_share = (AppCompatImageView) itemView.findViewById(R.id.whats_app_share);
        }
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        //  String inputPattern = "yyyy-MM-dd HH:mm:ss";
        // String outputPattern = "dd-MMM-yyyy h:mm a";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }



}
