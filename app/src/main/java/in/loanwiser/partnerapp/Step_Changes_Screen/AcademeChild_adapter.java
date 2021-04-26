package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.loanwiser.partnerapp.R;

public class AcademeChild_adapter extends RecyclerView.Adapter<AcademeChild_adapter.ViewHolder> {
    Context context;
    List<AcadmyDetails> acadmyDetailsList;

    public AcademeChild_adapter(Context context, List<AcadmyDetails> acadmyDetailsList) {
        this.context = context;
        this.acadmyDetailsList = acadmyDetailsList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.academychild_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AcadmyDetails acadmyDetails=acadmyDetailsList.get(position);
        holder.titletxt.setText(acadmyDetails.getTitle());
        holder.destxt.setText(acadmyDetails.getDescription());
        String logo  = acadmyDetailsList.get(position).getThumbnail();
        String pdf=acadmyDetailsList.get(position).getPdf_url();
        Log.i("TAG", "onBindViewHolder: logo"+logo);

        Picasso.with(context).load(logo).into(holder.imageview);
        holder.viewpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.viewvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/embed/vlhvLhQwE8w"));
                try {
                    context.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return acadmyDetailsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView titletxt,destxt;
        AppCompatImageView imageview;
        AppCompatButton viewpdf,viewvideo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titletxt=itemView.findViewById(R.id.titletxt);
            destxt=itemView.findViewById(R.id.destxt);
            imageview=itemView.findViewById(R.id.imageview);
            viewpdf=itemView.findViewById(R.id.viewpdf);
            viewvideo=itemView.findViewById(R.id.viewvideo);
        }
    }
}
