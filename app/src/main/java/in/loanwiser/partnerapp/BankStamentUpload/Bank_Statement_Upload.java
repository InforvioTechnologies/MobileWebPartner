package in.loanwiser.partnerapp.BankStamentUpload;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import in.loanwiser.partnerapp.R;

public class Bank_Statement_Upload extends  AppCompatActivity {

    Button proceedbtn,add,viewpdf,singlepdf;
    ListView listView;
    String[] ListElements = new String[] {
            "Android",
            "PHP",
            "Python",
    };
    String animalList[] = {"Lion","Tiger","Monkey","Elephant","Dog","Cat","Camel"};








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank__statement__upload);
        proceedbtn=findViewById(R.id.proceedbtn);
        add=findViewById(R.id.add);
        viewpdf=findViewById(R.id.viewpdf);
        listView=findViewById(R.id.listview);
        singlepdf=findViewById(R.id.singlepdf);
    /*    arrayList.add(new MyData(1, " Mashu","987576443"));
        arrayList.add(new MyData(2, " Azhar","8787576768"));
        arrayList.add(new MyData(3, " Niyaz","65757657657"));
        adapter = new MyAdapter(this, arrayList);
        listView.setAdapter(adapter);*/


      /*  proceedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Bank_Statement_Upload.this,UploadPdf.class);
                startActivity(intent);
            }
        });
*/
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Bank_Statement_Upload.this,Upload_Activity_Bank.class);
                startActivity(i);
            }
        });

        viewpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ "android_tutorial.pdf");
                Intent target = new Intent(Intent.ACTION_VIEW);
                target.setDataAndType(Uri.fromFile(file),"application/pdf");
                target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                Intent intent = Intent.createChooser(target, "Open File");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }
            }
        });

       /* singlepdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bank_Statement_Upload.this,SinglepdfActivity.class));
            }
        });*/



    }
}