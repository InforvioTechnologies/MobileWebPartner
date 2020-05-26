package in.loanwiser.partnerapp.Partner_Statues;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.loanwiser.partnerapp.R;

import static com.androidquery.util.AQUtility.getContext;

public class Health_Assement_Adapter extends RecyclerView.Adapter<Health_Assement_Adapter.CustomViewHolder> {

    private Context context;
    private ArrayList<Health_Assesment_item_freqent> items;
    private String lead_id;

    public Health_Assement_Adapter(Context context, ArrayList<Health_Assesment_item_freqent> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.finacial_health_assesment, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
      //  holder.applicant_id.setText("Testing Allocated Lead");

        /* Suggestion_item_freqent( J.getString(Params.id), J.getString(Params.user_id),J.getString(Params.name),
                                                J.getString(Params.mobileno),J.getString(Params.appointment_date),J.getString(Params.appointfrom_time)))*/

        String head_line  = items.get(position).gethead_line();
        String content  = items.get(position).getcontent();
        String icon  = items.get(position).geticon();
        String Color_icon  = items.get(position).getcolor_code();
        String url = "http://cscapi.propwiser.com/mobile/images/loanwiser-app-logo.png";
        Log.e("hello",icon);

        if(Color_icon.equals("1"))
        {
            holder.cardView.setBackgroundResource(R.drawable.gradient);
        }else
        {
            holder.cardView.setBackgroundResource(R.drawable.gradient1);
        }


      //  Picasso.with(getContext()).load(icon).fit().into(holder.helth_icons);

        Glide.with(context).load(icon)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.helth_icons);

        holder.head_line.setText(head_line);
        holder.su_head.setText(content);

     //  Log.e("The Appointment Date",name);
      //  String dt = parseDateToddMMyyyy(appointment_date);
       // holder.applicant_date.setText(dt);

      /* holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

                   String appoint_id  = items.get(position).getAppointment_id();
                   String applicant_id  = items.get(position).getUser_ID();
                   String lead_id = "1";
                   String SOD_EOD_ID = "11";


           }
       });

       holder.Bt_create_appointment.setOnClickListener(new View.OnClickListener() {
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

        private ImageView itemImage,helth_icons;
        private TextView head_line,su_head;
        private CardView cardView;
        private AppCompatButton Bt_create_appointment;

        public CustomViewHolder(View view) {
            super(view);
            head_line = view.findViewById(R.id.head_line);
            su_head = view.findViewById(R.id.su_head);
            cardView = view.findViewById(R.id.cardView);
            helth_icons = view.findViewById(R.id.helth_icons);

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
