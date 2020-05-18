package in.loanwiser.partnerapp.My_Earnings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.loanwiser.partnerapp.R;

public class CreditearningAdapter extends RecyclerView.Adapter<CreditearningAdapter.CreditEn> {

    List<Creditearnings> creditearningsList;


    public CreditearningAdapter(List<Creditearnings> creditearnings) {
        this.creditearningsList = creditearnings;
    }


    @NonNull
    @Override
    public CreditEn onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.crediten_row, parent, false);
        return new CreditearningAdapter.CreditEn(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditEn holder, int position) {
        Creditearnings creditearnings = creditearningsList.get(position);
        holder.datetext.setText(creditearnings.getDate());
        holder.loandetails_text.setText(creditearnings.getLoandetail());
        holder.leaddetails_text.setText(creditearnings.getLeaddetail());
        holder.purpose.setText(creditearnings.getPurpose());
        holder.coins.setText(creditearnings.getCoins());


    }

    @Override
    public int getItemCount() {
        return creditearningsList.size();
    }

    class CreditEn extends RecyclerView.ViewHolder {

        private static final String TAG = "earningVH";

        TextView datetext, leaddetails_text, loandetails_text, purpose,coins;


        public CreditEn(@NonNull final View itemView) {
            super(itemView);
            datetext=itemView.findViewById(R.id.datetext);
            leaddetails_text=itemView.findViewById(R.id.leaddetails_text);
            loandetails_text=itemView.findViewById(R.id.loandetails_text);
            purpose=itemView.findViewById(R.id.purpose);
            coins=itemView.findViewById(R.id.coins);







        }
    }
}
