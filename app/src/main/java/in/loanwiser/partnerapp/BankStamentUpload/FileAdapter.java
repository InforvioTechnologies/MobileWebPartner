package in.loanwiser.partnerapp.BankStamentUpload;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import in.loanwiser.partnerapp.R;

public class FileAdapter extends BaseAdapter {

    private Context context;
    public List<String> fileNameList;
    public List<String> fileDoneList;
    private TextView serialNum, name, contactNum,test;
    private ImageView pdfview, pdfdelete;


    public FileAdapter(List<String> fileNameList, List<String> fileDoneList) {
        this.context = context;
        this.fileNameList = fileNameList;
        this.fileDoneList = fileDoneList;
    }

    @Override
    public int getCount() {
        return fileNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upload_file, parent, false);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        convertView.setBackgroundColor(Color.parseColor("#89cff0"));
        test=convertView.findViewById(R.id.content);
        pdfdelete=convertView.findViewById(R.id.pdfdelete);
        final String fileName = fileNameList.get(position);
         final String files="android_tutorial.pdf";
        Log.i("Getview", "getView: "+fileName);
        test.setText(fileName);

        pdfdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileNameList.remove(position);
                notifyDataSetChanged();
            }
        });


   /*     pdfview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ fileName);
                Intent target = new Intent(Intent.ACTION_VIEW);
                target.setDataAndType(Uri.fromFile(file),"application/pdf");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    v.getContext().startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }

            }
        });*/
        return convertView;
    }
}
