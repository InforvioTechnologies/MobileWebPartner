package in.loanwiser.partnerapp.BankStamentUpload;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.loanwiser.partnerapp.Documents.Bank_Availability_Check;
import in.loanwiser.partnerapp.R;

public class UploadmonthAdapter extends RecyclerView.Adapter<UploadmonthAdapter.ViewHolder> {

    List<studentData> studentDataList;
    ArrayList<String> requirelist=new ArrayList<>();
    int count = 0;
    private Context context;
    public UploadmonthAdapter(List<studentData> studentDataList) {
        this.studentDataList = studentDataList;
        this.requirelist=requirelist;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.upload_monthadapter, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String requiremonth=studentDataList.get(position).getUpload_detstatus();
        studentData data=studentDataList.get(position);
        if (requiremonth.equalsIgnoreCase("required")){
            count = 1;
            holder.yeartext.setText(data.name);
            holder.yeartext.setTextColor(Color.parseColor("#CE0000"));
            holder.rightcarnerlay.setBackgroundColor(Color.parseColor("#FFDBDB"));
            holder.leftcarnerlay.setBackgroundColor(Color.parseColor("#D34D53"));
        }else if (requiremonth.equalsIgnoreCase("uploaded")){
            holder.yeartext.setText(data.name);
            holder.yeartext.setTextColor(Color.parseColor("#0EBB9A"));
            holder.rightcarnerlay.setBackgroundColor(Color.parseColor("#CCF5ED"));
            holder.leftcarnerlay.setBackgroundColor(Color.parseColor("#0EBB9A"));
        }else if (requiremonth.equalsIgnoreCase("missing"))
        {
            holder.yeartext.setText(data.name);
            holder.yeartext.setTextColor(Color.parseColor("#FF9201"));
            holder.rightcarnerlay.setBackgroundColor(Color.parseColor("#FFE6CE"));
            holder.leftcarnerlay.setBackgroundColor(Color.parseColor("#FF9201"));
        }
        Log.i("TAG", "onBindViewHolder:requiremonth "+requiremonth);

       /* if(count>=1)
        {

        }else
        {
            Log.e("the count", String.valueOf(count));
            Intent intent = new Intent(context, Bank_Availability_Check.class);
            //  Intent in=new Intent(context,BankAnalysis.class);
            intent.putExtra("adapter","upload");
             context.startActivity(intent);

        }*/
    }

    @Override
    public int getItemCount() {
        return studentDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView yeartext;
        LinearLayout leftcarnerlay,rightcarnerlay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            yeartext=itemView.findViewById(R.id.yeartxt);
            leftcarnerlay=itemView.findViewById(R.id.leftcarnerlay);
            rightcarnerlay=itemView.findViewById(R.id.rigltlay);
        }
    }
}
