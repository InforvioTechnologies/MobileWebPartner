package in.loanwiser.partnerapp.BankStamentUpload;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.loanwiser.partnerapp.PDF_Viewer.MainActivity;
import in.loanwiser.partnerapp.R;

public class Statementlist extends RecyclerView.Adapter<Statementlist.ViewHolder> {

    private Context context;
    private ArrayList<Bankitems> items;


    public Statementlist(Context context, ArrayList<Bankitems> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.banklist_items, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String name  = items.get(position).getName();
        final String url  = items.get(position).getUrl();
        String mystring=new String("Hello.....");
        /*SpannableString content = new SpannableString(name);
        content.setSpan(new UnderlineSpan(), 0, name.length(), 0);*/


        holder.statementlist.setPaintFlags(holder.statementlist.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        holder.statementlist.setText("\u25CF"+" " +name);


        holder.image_doc_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String path =url;
                Log.e("the url",url);
               /* Objs.ac.StartActivityPutExtra(context, Doc_ImageView_Viability.class,
                        Params.document,path);*/
                String report="Bank Statement";
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("viability_report_URL", path);
                intent.putExtra("report", report);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView statementlist;
        ImageView image_doc_view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            statementlist=itemView.findViewById(R.id.uploaded_statement);
            image_doc_view=itemView.findViewById(R.id.image_doc_view);
        }
    }
}
