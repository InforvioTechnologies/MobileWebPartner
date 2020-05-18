package in.loanwiser.partnerapp.My_Earnings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.loanwiser.partnerapp.R;

public class CashearningAdapter extends RecyclerView.Adapter<CashearningAdapter.CashEn> {


    List<Cashearnings> cashearningsList;

    public CashearningAdapter(List<Cashearnings> cashearningsList) {
        this.cashearningsList = cashearningsList;
    }


    @NonNull
    @Override
    public CashEn onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cashen_row, parent, false);
        return new CashearningAdapter.CashEn(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CashEn holder, int position) {
        Cashearnings cashearnings = cashearningsList.get(position);
        holder.titleTextView.setText(cashearnings.getDate());
        holder.loandetails_text.setText(cashearnings.getLoandetail());
        holder.leaddetails_text.setText(cashearnings.getLeaddetail());
        holder.amount_text.setText(cashearnings.getAmount());
        holder.bankinfo.setText(cashearnings.getBankmax());
        holder.stepcomplete_text.setText(cashearnings.getStepcomplete());
        holder.amountdetail.setText(cashearnings.getBankamount());


        boolean isExpanded = cashearningsList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return cashearningsList.size();
    }

    class CashEn extends RecyclerView.ViewHolder {

        private static final String TAG = "earningVH";

        ConstraintLayout expandableLayout;
        TextView titleTextView, leaddetails_text, loandetails_text, amount_text,stepcomplete_text,amountdetail,bankinfo;
        ImageView arrow;

        public CashEn(@NonNull final View itemView) {
            super(itemView);

            expandableLayout=itemView.findViewById(R.id.expandableLayout);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            leaddetails_text = itemView.findViewById(R.id.leaddetails_text);
            loandetails_text=itemView.findViewById(R.id.loandetails_text);
            amount_text=itemView.findViewById(R.id.amount_text);
            stepcomplete_text=itemView.findViewById(R.id.stepcomplete_text);
            amountdetail=itemView.findViewById(R.id.amountdetail);
            bankinfo=itemView.findViewById(R.id.bankdetail);
            arrow=itemView.findViewById(R.id.arrow);


            arrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Cashearnings cashearnings = cashearningsList.get(getAdapterPosition());
                    cashearnings.setExpanded(!cashearnings.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
}
}
