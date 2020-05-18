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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.loanwiser.partnerapp.R;

public class Resent_Lead_Statues extends RecyclerView.Adapter<Resent_Lead_Statues.CustomViewHolder> {

    private Context context;
    private ArrayList<Suggestion_item_freqent> items;
    private String lead_id;

    public Resent_Lead_Statues(Context context, ArrayList<Suggestion_item_freqent> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.resent_lead_statues, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
      //  holder.applicant_id.setText("Testing Allocated Lead");

        /* Suggestion_item_freqent( J.getString(Params.id), J.getString(Params.user_id),J.getString(Params.name),
                                                J.getString(Params.mobileno),J.getString(Params.appointment_date),J.getString(Params.appointfrom_time)))*/

        String appointment_id  = items.get(position).getAppointment_id();
        String applicant_id  = items.get(position).getUser_ID();
        String name  = items.get(position).getName();
        String loan_type  = items.get(position).getloan_type();
        String loan_amount  = items.get(position).getloan_amount();
        String status_disp  = items.get(position).getstatus_disp();


        holder.lead_name.setText(name);
        holder.loan_amount.setText(loan_amount);
        holder.loan_type.setText(loan_type);
        holder.Statues_update.setText(status_disp);

        if(status_disp.contains("Pending under you"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#FF9200"));
        } else if(status_disp.contains("Submitted to Loanwiser"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#F9F338"));
        }else if(status_disp.contains("Sanctioned"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#1592E6"));
        }else if(status_disp.contains("Disbursed"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#15CE00"));
        }else if(status_disp.contains("Sent to bank"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#012B5D"));
        }else
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#E3434A"));
        }




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

        private ImageView itemImage;
        private TextView lead_name,loan_amount,loan_type,Statues_update_dot,Statues_update;
        private CardView cardView;
        private AppCompatButton Bt_create_appointment;

        public CustomViewHolder(View view) {
            super(view);
            lead_name = view.findViewById(R.id.lead_name);
            loan_amount = view.findViewById(R.id.loan_amount);
            loan_type = view.findViewById(R.id.loan_type);
            Statues_update = view.findViewById(R.id.Statues_update);
            cardView = view.findViewById(R.id.cardView);
            Bt_create_appointment = view.findViewById(R.id.Bt_create_appointment);
            Statues_update_dot = view.findViewById(R.id.Statues_update_dot);
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
