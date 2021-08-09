package in.loanwiser.partnerapp.Partner_Statues;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.AcadmyDetails;
import in.loanwiser.partnerapp.Step_Changes_Screen.AcadmyDetails_sales;

public class Sales_List_Adapter extends RecyclerView.Adapter<Sales_List_Adapter.CustomViewHolder> {

    private Context context;
    private ArrayList<AcadmyDetails_sales> items;
    private String lead_id;
    private AlertDialog progressDialog;

    String Loan_amount,sub_categoryid,transaction_id1,subtask_id,loan_type_id,loan_type,
            payment,applicant_id1,
            user_id1,app_id,name,request_by,applicant,applicant1,created_at,status_disp,doc_typename,status_disp1,
            Notes,transaction_id,doc_classname,doc_typeid,ask_id;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public Sales_List_Adapter(Context context, ArrayList<AcadmyDetails_sales> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.academychild_layout, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
      //  holder.applicant_id.setText("Testing Allocated Lead");

        /* Suggestion_item_freqent( J.getString(Params.id), J.getString(Params.user_id),J.getString(Params.name),
                                                J.getString(Params.mobileno),J.getString(Params.appointment_date),J.getString(Params.appointfrom_time)))*/

        String Title  = items.get(position).getTitle();
        final String description  = items.get(position).getDescription();

        holder.titletxt.setText(Title);
        holder.destxt.setText(description);

        String Logo  = items.get(position).getThumbnail();
        String pdfurl  = items.get(position).getPdf_url();
        String pdf_status  = items.get(position).getPdf_status();
        String video_status  = items.get(position).getVideo_status();
        String videourl  = items.get(position).getVideo_url();
        Picasso.with(context).load(Logo).into(holder.imageview);

        holder.viewvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String video_status  = items.get(position).getVideo_status();
                String videourl  = items.get(position).getVideo_url();
                if (video_status.equalsIgnoreCase("0")){
                    ErrorStatus();
                }else {
                    // watchYoutubeVideo(context,videourl);
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(videourl));
                    try {
                        context.startActivity(webIntent);
                    } catch (ActivityNotFoundException ex) {
                    }
                }
            }
        });

        holder.viewpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pdfurl  = items.get(position).getPdf_url();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdfurl));
                context.startActivity(browserIntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
      // return 3;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView titletxt,destxt;
        AppCompatImageView imageview;
        AppCompatButton viewvideo,viewpdf;
        public CustomViewHolder(View view) {
            super(view);
            titletxt=itemView.findViewById(R.id.titletxt);
            destxt=itemView.findViewById(R.id.destxt);
            imageview=itemView.findViewById(R.id.imageview);
            viewvideo=itemView.findViewById(R.id.viewvideo);
            viewpdf=itemView.findViewById(R.id.viewpdf);
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


    private void ErrorStatus() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.academic_soon);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView bankstatement_message=(AppCompatTextView) dialog.findViewById(R.id.bankstatement_message);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);
        Button submitbtn1 = (Button) dialog.findViewById(R.id.submitbtn1);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
}
