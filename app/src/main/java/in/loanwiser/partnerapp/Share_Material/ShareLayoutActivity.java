package in.loanwiser.partnerapp.Share_Material;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class ShareLayoutActivity  extends AppCompatActivity {
    TextView what_loan,content;
    ImageView loan_image;
    String getvalue,icon,doc_typename,loantype_id;
    ListView listview;
    Button whatsapp_button,other_network;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    final ArrayList<String> arrayList=new ArrayList<String>();
    final ArrayList<String> classarraylist=new ArrayList<String>();
    final ArrayList<String> itemlist=new ArrayList<String>();
    private String[] lv_arr = {};
    List<String> nameList = new ArrayList<>();
    String[] colourCodes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share__screen);
        loan_image=findViewById(R.id.loan_image);
        what_loan=findViewById(R.id.what_loan);
        content=findViewById(R.id.content);
        listview=findViewById(R.id.listview);
        whatsapp_button=findViewById(R.id.whatsapp_button);

      //  colourCodes = getResources().getStringArray(R.array.listValues);

        other_network=findViewById(R.id.other_network);


        Intent intent=getIntent();
        getvalue=getIntent().getStringExtra("key");
        icon=getIntent().getStringExtra("image");
        loantype_id=getIntent().getStringExtra("image");
        Log.d("TAG", "onCreate: " +getvalue );
        Log.d("TAG", "onCreateImage: " +icon );

        what_loan.setText(getvalue);

        Glide.with(this).load(icon)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(loan_image);

      //  Docmentchecklist();


        whatsapp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm=getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "YOUR TEXT HERE";

                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    //Check if package exists or not. If not then code
                    //in catch block will be called
                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, "Thanks for your interest in my Loan facilitation service." +
                            "Please find the documents required for applying Loan with any Bank."+"\n\n"+"KYC" +"\n"+
                            "-Aadhaar card(colored)"+"\n" + "-PAN card(colored)" +"\n\n" + "INCOME PROOF" +"\n"+
                            "- Last 3 months salary slip / salary certificate"+"\n"
                            + "- Last 6 months Salary's account bank statement"+"\n" +
                            "- Current office joining / appointment letter" +"\n\n" + "CURRENT RESIDENCE PROOF" +"\n"+
                            "- Aadhaar card"+"\n"
                            + "- Driving license"+"\n" +
                            "- Passport" + "- Postpaid phone bill"+"\n"
                            + "- Credit card bill"+"\n" +
                            "- Bank statement" +"- Electricity bill"+"\n"
                            + "- Rent agreement along with electricity bill"+"\n\n" +
                            "EXISTING LOANS (if any)"+"\n"+
                            "- Loan repayment schedule"+"\n"
                            + "- Sanction letter"+"\n\n"+"Please arrange these documents and share it with me.");

                   // waIntent.putExtra(Intent.EXTRA_TEXT, (String.valueOf(itemlist)));

                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(ShareLayoutActivity.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
        other_network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                if (intent != null) {
                    intent.putExtra(Intent.EXTRA_TEXT, "Thanks for your interest in my Loan facilitation service." +
                            "Please find the documents required for applying Loan with any Bank."+"\n\n"+"KYC" +"\n"+
                            "-Aadhaar card(colored)"+"\n" + "-PAN card(colored)" +"\n\n" + "INCOME PROOF" +"\n"+
                            "- Last 3 months salary slip / salary certificate"+"\n"
                            + "- Last 6 months Salary's account bank statement"+"\n" +
                            "- Current office joining / appointment letter" +"\n\n" + "CURRENT RESIDENCE PROOF" +"\n"+
                            "- Aadhaar card"+"\n"
                            + "- Driving license"+"\n" +
                            "- Passport" + "- Postpaid phone bill"+"\n"
                            + "- Credit card bill"+"\n" +
                            "- Bank statement" +"- Electricity bill"+"\n"
                            + "- Rent agreement along with electricity bill"+"\n\n" +
                            "EXISTING LOANS (if any)"+"\n"+
                            "- Loan repayment schedule"+"\n"
                            + "- Sanction letter"+"\n\n"+"Please arrange these documents and share it with me.");
                   // intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(itemlist));//
                    startActivity(Intent.createChooser(intent, "Share With"));
                } else {
                    Toast.makeText(ShareLayoutActivity.this, "App not found", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }


    private void Docmentchecklist() {
        final JSONObject jsonObject =new JSONObject();
        JSONObject J= null;

        try {
            J =new JSONObject();
            if(getvalue!=null){
                if (getvalue.equalsIgnoreCase("Personal")){
                    J.put("loan_type","21");
                }else if (getvalue.equalsIgnoreCase("Business")){
                    J.put("loan_type","20");
                }else if (getvalue.equalsIgnoreCase("Loan Against Property")){
                    J.put("loan_type","2");
                }
                else if (getvalue.equalsIgnoreCase("Home Loan")){
                    J.put("loan_type","1");
                }
            }
            Log.d("Document_Checklist", String.valueOf(J));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.DOC_CHECKLIST , J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(getvalue.equalsIgnoreCase("Business")) {
                                JSONArray jsonArray = response.getJSONArray("self_employed");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                                    //  Log.i("TAG", "jsonArray1 "+jsonArray1);
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                        String classname = jsonObject1.getString("class");
                                        itemlist.add(classname +"\n");

                                        JSONArray jsonArray2 = jsonObject1.getJSONArray("doc_typename");
                                        for (int z = 0; z < jsonArray2.length(); z++) {
                                            JSONObject jsonObject2 = jsonArray2.getJSONObject(z);
                                            doc_typename = jsonObject2.getString("doc_typename");
                                            Log.i("TAG", "onResponse:doc_typename " + doc_typename);
                                            itemlist.add(doc_typename +"\n");
                                        }
                                    }
                                }
                                listview.setAdapter(new ArrayAdapter<String>(ShareLayoutActivity.this,
                                        android.R.layout.simple_list_item_1, itemlist));
                            }
                            else if(getvalue.equalsIgnoreCase("Personal")){
                                JSONArray jsonArray = response.getJSONArray("salaried");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                                    //  Log.i("TAG", "jsonArray1 "+jsonArray1);
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                        String classname = jsonObject1.getString("class");
                                        itemlist.add(classname);

                                        JSONArray jsonArray2 = jsonObject1.getJSONArray("doc_typename");
                                        for (int z = 0; z < jsonArray2.length(); z++) {
                                            JSONObject jsonObject2 = jsonArray2.getJSONObject(z);
                                            doc_typename = jsonObject2.getString("doc_typename");
                                            Log.i("TAG", "onResponse:doc_typename " + doc_typename);
                                            itemlist.add(doc_typename);
                                        }
                                    }
                                }

                                listview.setAdapter(new ArrayAdapter<String>(ShareLayoutActivity.this,
                                        android.R.layout.simple_list_item_1, itemlist));
                            }
                            else if(getvalue.equalsIgnoreCase("Home Loan")){
                                JSONArray jsonArray = response.getJSONArray("salaried");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                                    //  Log.i("TAG", "jsonArray1 "+jsonArray1);
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                        String classname = jsonObject1.getString("class");
                                        itemlist.add(classname);

                                        JSONArray jsonArray2 = jsonObject1.getJSONArray("doc_typename");
                                        for (int z = 0; z < jsonArray2.length(); z++) {
                                            JSONObject jsonObject2 = jsonArray2.getJSONObject(z);
                                            doc_typename = jsonObject2.getString("doc_typename");
                                            Log.i("TAG", "onResponse:doc_typename " + doc_typename);
                                            itemlist.add(doc_typename);
                                        }
                                    }
                                }

                                JSONArray jsonArrays = response.getJSONArray("self_employed");
                                for (int i = 0; i < jsonArrays.length(); i++) {
                                    JSONArray jsonArray1 = jsonArrays.getJSONArray(i);
                                    //  Log.i("TAG", "jsonArray1 "+jsonArray1);
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                        String classname = jsonObject1.getString("class");
                                        itemlist.add(classname);

                                        JSONArray jsonArray2 = jsonObject1.getJSONArray("doc_typename");
                                        for (int z = 0; z < jsonArray2.length(); z++) {
                                            JSONObject jsonObject2 = jsonArray2.getJSONObject(z);
                                            doc_typename = jsonObject2.getString("doc_typename");
                                            Log.i("TAG", "onResponse:doc_typename " + doc_typename);
                                            itemlist.add(doc_typename);
                                        }
                                    }
                                }
                                JSONArray jsonArrayss = response.getJSONArray("property");
                                for (int i = 0; i < jsonArrayss.length(); i++) {
                                    JSONArray jsonArray1 = jsonArrayss.getJSONArray(i);
                                    //  Log.i("TAG", "jsonArray1 "+jsonArray1);
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                        String classname = jsonObject1.getString("class");
                                        itemlist.add(classname);

                                        JSONArray jsonArray2 = jsonObject1.getJSONArray("doc_typename");
                                        for (int z = 0; z < jsonArray2.length(); z++) {
                                            JSONObject jsonObject2 = jsonArray2.getJSONObject(z);
                                            doc_typename = jsonObject2.getString("doc_typename");
                                            Log.i("TAG", "onResponse:doc_typename " + doc_typename);
                                            itemlist.add(doc_typename);
                                        }
                                    }
                                }
                                listview.setAdapter(new ArrayAdapter<String>(ShareLayoutActivity.this,
                                        android.R.layout.simple_list_item_1, itemlist));
                            }
                            else if(getvalue.equalsIgnoreCase("Loan Against Property")){
                                JSONArray jsonArray = response.getJSONArray("salaried");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                                    //  Log.i("TAG", "jsonArray1 "+jsonArray1);
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                        String classname = jsonObject1.getString("class");
                                        itemlist.add(classname);

                                        JSONArray jsonArray2 = jsonObject1.getJSONArray("doc_typename");
                                        for (int z = 0; z < jsonArray2.length(); z++) {
                                            JSONObject jsonObject2 = jsonArray2.getJSONObject(z);
                                            doc_typename = jsonObject2.getString("doc_typename");
                                            Log.i("TAG", "onResponse:doc_typename " + doc_typename);
                                            itemlist.add(doc_typename);
                                        }
                                    }
                                }

                                JSONArray jsonArrays = response.getJSONArray("self_employed");
                                for (int i = 0; i < jsonArrays.length(); i++) {
                                    JSONArray jsonArray1 = jsonArrays.getJSONArray(i);
                                    //  Log.i("TAG", "jsonArray1 "+jsonArray1);
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                        String classname = jsonObject1.getString("class");
                                        itemlist.add(classname);

                                        JSONArray jsonArray2 = jsonObject1.getJSONArray("doc_typename");
                                        for (int z = 0; z < jsonArray2.length(); z++) {
                                            JSONObject jsonObject2 = jsonArray2.getJSONObject(z);
                                            doc_typename = jsonObject2.getString("doc_typename");
                                            Log.i("TAG", "onResponse:doc_typename " + doc_typename);
                                            itemlist.add(doc_typename);
                                        }
                                    }
                                }
                                JSONArray jsonArrayss = response.getJSONArray("property");
                                for (int i = 0; i < jsonArrayss.length(); i++) {
                                    JSONArray jsonArray1 = jsonArrayss.getJSONArray(i);
                                    //  Log.i("TAG", "jsonArray1 "+jsonArray1);
                                    for (int j = 0; j < jsonArray1.length(); j++) {
                                        JSONObject jsonObject1 = jsonArray1.getJSONObject(j);
                                        String classname = jsonObject1.getString("class");
                                        itemlist.add(classname);

                                        JSONArray jsonArray2 = jsonObject1.getJSONArray("doc_typename");
                                        for (int z = 0; z < jsonArray2.length(); z++) {
                                            JSONObject jsonObject2 = jsonArray2.getJSONObject(z);
                                            doc_typename = jsonObject2.getString("doc_typename");
                                            Log.i("TAG", "onResponse:doc_typename " + doc_typename);
                                            itemlist.add(doc_typename);
                                        }
                                    }
                                }
                                listview.setAdapter(new ArrayAdapter<String>(ShareLayoutActivity.this,
                                        android.R.layout.simple_list_item_1, itemlist));
                            }

                            else {
                                Toast.makeText(ShareLayoutActivity.this,"Error",Toast.LENGTH_SHORT).show();
                                // Objs.a.ShowHideNoItems(ShareLayoutActivity.this,true);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response",String.valueOf(error));
                Toast.makeText(ShareLayoutActivity.this,"Error",Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        Log.d("Response","Login Activity_Exitalert Login_POST33333333333333333333333333333333333");
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }



}