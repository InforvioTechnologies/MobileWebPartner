package in.loanwiser.partnerapp.PartnerActivitys;

import android.app.Activity;
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
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.ViableBankActivity;

public class BankimgviewAdapter extends RecyclerView.Adapter<BankimgviewAdapter.RecyclerViewHolder> {

    private ArrayList<BankimgData> productDataArrayList;
    private Context mcontext;

    public BankimgviewAdapter(ArrayList<BankimgData> recyclerDataArrayList, Context mcontext) {
        this.productDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bankimgshow_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final String head_line  = productDataArrayList.get(position).getId();
        final String icon  = productDataArrayList.get(position).getBank_logo_cc();
        final String banklogo=productDataArrayList.get(position).getBank_logo();
        final String status=productDataArrayList.get(position).getStatus();
        Log.i("TAG", "onBindViewHolder:status "+status);

        Log.i("TAG", "onBindViewHolder: "+banklogo);
        Glide.with(mcontext).load("https://consumer.loanwiser.in/images/bank-logo/"+banklogo)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.productimg);
        if (status.equalsIgnoreCase("1")){
            holder.producttext.setText("Passed in Viability");
            holder.producttext.setTextColor(Color.parseColor("#25D366"));

        }else{
            holder.producttext.setText("Failed in Viability");
            holder.producttext.setTextColor(Color.parseColor("#D34D53"));

        }

        holder.viewdoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcontext, ViableBankActivity.class);
                intent.putExtra("bankid",productDataArrayList.get(position).getId());
                intent.putExtra("statusvalue",productDataArrayList.get(position).getStatus());
                mcontext.startActivity(intent);
                ((Activity)mcontext).finish();


            }
        });
    }

    @Override
    public int getItemCount() {
        return productDataArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView producttext;
        private AppCompatImageView viewdoc;
        private ImageView productimg;
       // private MaterialCardView cardlay;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            productimg = itemView.findViewById(R.id.productimg);
            producttext = itemView.findViewById(R.id.producttext);
            viewdoc = itemView.findViewById(R.id.viewdoc);
         //   cardlay=itemView.findViewById(R.id.cardlay);
        }
    }

         public void clearData() {
             productDataArrayList.clear();
        // do something else here if you want.  Like some kind of visual notification to the user
        notifyDataSetChanged();
    }
}
