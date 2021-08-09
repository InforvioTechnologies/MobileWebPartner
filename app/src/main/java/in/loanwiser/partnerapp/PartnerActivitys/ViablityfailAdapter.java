package in.loanwiser.partnerapp.PartnerActivitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Rulefail;
import in.loanwiser.partnerapp.Step_Changes_Screen.RulefailAdapter;
import in.loanwiser.partnerapp.Step_Changes_Screen.ViableBankActivity;

public class ViablityfailAdapter extends RecyclerView.Adapter<ViablityfailAdapter.RecyclerViewHolder> {

    private ArrayList<Viablityfaildata> productDataArrayList;
    private Context mcontext;

    public ViablityfailAdapter(ArrayList<Viablityfaildata> recyclerDataArrayList, Context mcontext) {
        this.productDataArrayList = recyclerDataArrayList;
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
            Viablityfaildata modal = productDataArrayList.get(rowPos-1);

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




















      /*  final String head_line  = productDataArrayList.get(position).getRule_desc();
        final String icon  = productDataArrayList.get(position).getRule_status();
        final String banklogo=productDataArrayList.get(position).getFail_message();
        holder.first.setText(head_line);
        holder.third.setText(banklogo);
        if (icon.equalsIgnoreCase("1")){
            holder.second.setText("Viablity Passed");
            holder.second.setTextColor(Color.parseColor("#25D366"));

        }else{
            holder.second.setText("Viablity Failed");
            holder.second.setTextColor(Color.parseColor("#D34D53"));


        }
*/



    }

    @Override
    public int getItemCount() {
        return productDataArrayList.size()+1;
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

