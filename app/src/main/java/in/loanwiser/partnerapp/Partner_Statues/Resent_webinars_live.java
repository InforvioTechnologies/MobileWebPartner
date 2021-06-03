package in.loanwiser.partnerapp.Partner_Statues;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;

public class Resent_webinars_live extends RecyclerView.Adapter<Resent_webinars_live.CustomViewHolder> {

    private Context context;
    private ArrayList<Webinar_item_freqent_live> items;
    private String lead_id;
    private AlertDialog progressDialog;

    String Loan_amount,sub_categoryid,transaction_id1,subtask_id,loan_type_id,loan_type,
            payment,applicant_id1;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public Resent_webinars_live(Context context, ArrayList<Webinar_item_freqent_live> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.resent_webinar_statues, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
      //  holder.applicant_id.setText("Testing Allocated Lead");

        /* Suggestion_item_freqent( J.getString(Params.id), J.getString(Params.user_id),J.getString(Params.name),
                                                J.getString(Params.mobileno),J.getString(Params.appointment_date),J.getString(Params.appointfrom_time)))*/
      //  holder.bindPost(posts.get(position));
       // String appointment_id  = "Webinar Topic";
        final String applicant_id  = "mathayan";

        String title  = items.get(position).gettitle();
        final String description  = items.get(position).getdescription();
        String url  = items.get(position).geturl();
        String scheduled_time  = items.get(position).getscheduled_time();
        String thumbnail_url  = items.get(position).getthumbnail_url();
        String is_registered  = items.get(position).getis_registered();
        String session_end  = items.get(position).getsession_end();


        holder.webinar_topic.setText(title);
        holder.webinar_topi_des.setText(description);
        holder.webinar_time.setText(scheduled_time+" - "+session_end);
        // holder.loan_amount.setText(loan_amount);
       Objs.a.loadPicasso(context,thumbnail_url,holder.thumb_nile_image,holder.progressBarMaterial_pdf);
      //  holder.thumb_nile_image.setImageDrawable(context.getResources().getDrawable(R.drawable.training));
        if(is_registered.equals("0"))
        {
            holder.webinar_register.setVisibility(View.GONE);
            holder.watch_webinar.setVisibility(View.GONE);
        }else
        {
            holder.webinar_register.setVisibility(View.GONE);
            holder.webinar_time1.setText("Already Registered");
            holder.webinar_time.setText(scheduled_time+" - "+session_end);
            holder.webinar_time1.setTextColor(Color.GREEN);
            holder.watch_webinar.setVisibility(View.VISIBLE);
        }
        holder.watch_webinar.setVisibility(View.VISIBLE);
        holder.watch_webinar.setText("Join");
        holder.webinar_time1.setVisibility(View.GONE);
        holder.watch_webinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b2b_id  = items.get(position).getb2b_id();
                String webinar_id  = items.get(position).getwebinar_id();
                String url  = items.get(position).geturl();
                Register_Webinar(b2b_id,webinar_id,url);

                // watchYoutubeVideo(context,videourl);


            }
        });

        holder.webinar_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b2b_id  = items.get(position).getb2b_id();
                String webinar_id  = items.get(position).getwebinar_id();
                holder.webinar_time1.setText("Aleready Registered");
                holder.webinar_time1.setTextColor(Color.GREEN);
                holder.webinar_register.setVisibility(View.GONE);
               // Register_Webinar(b2b_id,webinar_id);
            }
        });

         }

    @Override
    public int getItemCount() {
        return items.size();
       // return 3;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumb_nile_image;
        private AppCompatTextView webinar_topic,webinar_topi_des,webinar_time,watch_webinar,webinar_register,webinar_time1;
        private CardView cardView;
        private AppCompatButton Bt_create_appointment;
        private AppCompatTextView Statues_update_view,Statues_update_view1;
        ProgressBar progressBar,progressBarMaterial_pdf;

        public CustomViewHolder(View view) {
            super(view);
            webinar_topic = view.findViewById(R.id.webinar_topic);
            webinar_topi_des = view.findViewById(R.id.webinar_topi_des);
            webinar_time = view.findViewById(R.id.webinar_time);
            webinar_time1 = view.findViewById(R.id.webinar_time1);
            watch_webinar = view.findViewById(R.id.watch_webinar);

            webinar_register = view.findViewById(R.id.webinar_register);
            thumb_nile_image = view.findViewById(R.id.thumb_nile_image);
            progressBarMaterial_pdf = view.findViewById(R.id.progressBarMaterial_pdf);

            progressDialog = new SpotsDialog(context, R.style.Custom);
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


    public void Register_Webinar(String b2b_id,String webinar_id, String url1) {

        // final String step_status11 = step_status1;
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            // J.put(Params.user_id, id);
            J.put("b2b_id", b2b_id);
            J.put("webinar_id", webinar_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("Applicant Entry request", String.valueOf(J));
       // Log.e("Applicant Entry request", Pref.getID(context));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.webinar_join, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant Entry", String.valueOf(response));

                        try {
                            String status = response.getString("status");
                            if(status.equals("success"))
                            {
                                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse(url1));
                                try {
                                    context.startActivity(webIntent);
                                } catch (ActivityNotFoundException ex) {
                                }


                            }else
                            {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Applicant Entry request", String.valueOf(error));
                Toast.makeText(context,error.getMessage(), Toast.LENGTH_SHORT).show();
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
}
