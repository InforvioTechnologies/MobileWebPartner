package in.loanwiser.partnerapp.Documents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import in.loanwiser.partnerapp.R;

public class ActivityStreamAdapter extends RecyclerView.Adapter<ActivityStreamAdapter.ViewHolder> {
    Context context;
    private ArrayList<StreamList> items;

    public ActivityStreamAdapter(Context context, ArrayList<StreamList> items) {
        this.context = context;
        this.items = items;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.stream_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String titletxt  = items.get(position).getTitle();
        String createdat  = items.get(position).getCreated_at();
        String messagetxt =items.get(position).getMessage();

        holder.titletxt.setText(titletxt);
        holder.createddate.setText(parseDateToddMMyyyy(createdat));
        holder.messagetxt.setText(messagetxt);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView titletxt,createddate,messagetxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titletxt=itemView.findViewById(R.id.titletxt);
            createddate=itemView.findViewById(R.id.createddate);
            messagetxt=itemView.findViewById(R.id.messagetxt);
        }
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        java.util.Date date = null;
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
