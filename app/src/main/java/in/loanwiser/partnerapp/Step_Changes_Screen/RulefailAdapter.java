package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import in.loanwiser.partnerapp.PartnerActivitys.ViablityfailAdapter;
import in.loanwiser.partnerapp.PartnerActivitys.Viablityfaildata;
import in.loanwiser.partnerapp.R;

public class RulefailAdapter extends RecyclerView.Adapter<RulefailAdapter.RecyclerViewHolder> {

    private ArrayList<Rulefail> rulefaildata;
    private Context mcontext;

    public RulefailAdapter(ArrayList<Rulefail> rulefaildata, Context mcontext) {
        this.rulefaildata = rulefaildata;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viablilityfail_adapter, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        RecyclerViewHolder rowViewHolder = (RecyclerViewHolder) holder;

        int rowPos = rowViewHolder.getAdapterPosition();

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            rowViewHolder.first.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.second.setBackgroundResource(R.drawable.table_header_cell_bg);
            rowViewHolder.third.setBackgroundResource(R.drawable.table_header_cell_bg);
           // rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_header_cell_bg);

            rowViewHolder.first.setText("Criteria");
            rowViewHolder.second.setText("Description");
            rowViewHolder.third.setText("Status");
            rowViewHolder.first.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.second.setTextColor(Color.parseColor("#FFFFFF"));
            rowViewHolder.third.setTextColor(Color.parseColor("#FFFFFF"));
          //  rowViewHolder.txtCost.setText("Budget (in Millions)");
        } else {
            Rulefail modal = rulefaildata.get(rowPos-1);

            // Content Cells. Content appear here
            rowViewHolder.first.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.second.setBackgroundResource(R.drawable.table_content_cell_bg);
            rowViewHolder.third.setBackgroundResource(R.drawable.table_content_cell_bg);
         //   rowViewHolder.txtCost.setBackgroundResource(R.drawable.table_content_cell_bg);

            /*final String head_line  = rulefaildata.get(position).getRule_desc();
            final String icon  = rulefaildata.get(position).getRule_status();
            final String banklogo=rulefaildata.get(position).getFail_message();*/

            rowViewHolder.first.setText(modal.getRule_desc()+"");
          //  rowViewHolder.second.setText(modal.getMovieName());
            rowViewHolder.third.setText(modal.fail_message+"");
            if (modal.getRule_status().equalsIgnoreCase("1")){
                holder.second.setText("Passed");
                holder.second.setTextColor(Color.parseColor("#25D366"));

            }else{
                holder.second.setText("Failed");
                holder.second.setTextColor(Color.parseColor("#D34D53"));


            }
          //  rowViewHolder.txtCost.setText(modal.getBudgetInMillions()+"");
        }

/*
        final String head_line  = rulefaildata.get(position).getRule_desc();
        final String icon  = rulefaildata.get(position).getRule_status();
        final String banklogo=rulefaildata.get(position).getFail_message();
        holder.first.setText(head_line);
        holder.third.setText(banklogo);
        if (icon.equalsIgnoreCase("1")){
            holder.second.setText("Viablity Passed");
            holder.second.setTextColor(Color.parseColor("#25D366"));

        }else{
            holder.second.setText("Viablity Failed");
            holder.second.setTextColor(Color.parseColor("#D34D53"));


        }*/

    }

    @Override
    public int getItemCount() {
        return rulefaildata.size()+1;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView first,second,third;
        private ImageView productimg;
        private MaterialCardView cardlay;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            second = itemView.findViewById(R.id.second);
            first = itemView.findViewById(R.id.first);
            third=itemView.findViewById(R.id.third);
        }
    }
}
