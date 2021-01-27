package in.loanwiser.partnerapp.CameraActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import in.loanwiser.partnerapp.R;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridviewHolder> {

    private Context mcontext;
    private List<Uri> mListphotos;

    public GridAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void setData(List<Uri> list){
        this.mListphotos=list;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public GridviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo,parent,false);
        return new GridviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridviewHolder holder, int position) {
        Uri uri=mListphotos.get(position);
        if (uri==null){
            return;
        }
        try {
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(mcontext.getContentResolver(),uri);
            if (bitmap!=null){
                holder.imgphoto.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (mListphotos!=null){
            return mListphotos.size();
        }
        return 0;
    }

    public class GridviewHolder extends RecyclerView.ViewHolder {
        private ImageView imgphoto;

        public GridviewHolder(@NonNull View itemView) {
            super(itemView);
            imgphoto=itemView.findViewById(R.id.img_photo);

        }
    }
}
