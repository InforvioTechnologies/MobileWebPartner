package in.loanwiser.partnerapp.Partner_Statues;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.R.layout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.User_Account.Login;

public class ChecklistShare extends AppCompatActivity {

    private ImageView loanimgview;
    private TextView loantypetxt;
    private Button whatsapp_button, other_network,whatsapp_button_salaried,other_network_salaried;
    ListView listview, listView1;
    String getvalue, icon, doc_typename, loantype_id;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private static final String TAG = "Checklist_Share";
    //SharetextAdapter sharetextAdapter;
    final ArrayList<String> itemlist = new ArrayList<String>();
    final ArrayList<String> shalist = new ArrayList<String>();
    final ArrayList<String> selflist = new ArrayList<String>();
    final ArrayList<String> contentlist = new ArrayList<String>();
    private android.app.AlertDialog progressDialog;
    private JSONObject jsonObject1, propertyObject,Jsonobject2;
    private LinearLayout selfemploylay;
    private TextView salaried_textview,selfemp_textview;
    private String loan_type;
    LinearLayout viewlayout,twolay;
    TextView mandatory_doc,anyone_doc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_share);
        loanimgview = findViewById(R.id.loanimgview);
        loantypetxt = findViewById(R.id.loantypetxt);
        whatsapp_button = findViewById(R.id.whatsapp_button);
        other_network = findViewById(R.id.other_network);
        whatsapp_button_salaried = findViewById(R.id.whatsapp_button_salaried);
        other_network_salaried = findViewById(R.id.other_network_salaried);
        listview = findViewById(R.id.listview);
        listView1 = findViewById(R.id.listview1);
        selfemploylay = findViewById(R.id.selfemploylay);
        salaried_textview=findViewById(R.id.salaried_textview);
        selfemp_textview=findViewById(R.id.selfemp_textview);
        mandatory_doc=findViewById(R.id.mandatory_doc);
        anyone_doc=findViewById(R.id.anyone_doc);
        twolay=findViewById(R.id.twolay);
      //  viewlayout=findViewById(R.id.viewlayout);
        progressDialog = new SpotsDialog(this, R.style.Custom);


        Intent intent = getIntent();
        getvalue = getIntent().getStringExtra("key");
        icon = getIntent().getStringExtra("image");
        loantype_id = getIntent().getStringExtra("image");
        Log.d("TAG", "onCreate: " + getvalue);
        Log.d("TAG", "onCreateImage: " + icon);

        if (getvalue.equalsIgnoreCase("Home Loan")) {
            selfemploylay.setVisibility(View.VISIBLE);
            salaried_textview.setVisibility(View.VISIBLE);
            selfemp_textview.setVisibility(View.VISIBLE);
            salaried_textview.setText("Home Loan (Salaried Type)");
            selfemp_textview.setText("Home Loan (Self Employed)");
            twolay.setVisibility(View.VISIBLE);

            whatsapp_button_salaried.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Home Salaried",Toast.LENGTH_SHORT).show();
                    Whatsappshare();

                }
            });

            other_network_salaried.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Home Salaried",Toast.LENGTH_SHORT).show();
                    Othernetworkshare();
                }
            });


            whatsapp_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Home Self employed",Toast.LENGTH_SHORT).show();
                    Whatsappshare2();


                }
            });

            other_network.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Home Self employed",Toast.LENGTH_SHORT).show();
                    OtherNetwork2();
                }
            });

        }
        else if (getvalue.equalsIgnoreCase("Loan Against Property")){
            selfemploylay.setVisibility(View.VISIBLE);
            salaried_textview.setVisibility(View.VISIBLE);
            selfemp_textview.setVisibility(View.VISIBLE);
            salaried_textview.setText("LAP (Salaried Type)");
            selfemp_textview.setText("LAP (Self Employed)");
            twolay.setVisibility(View.VISIBLE);

            whatsapp_button_salaried.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Home Salaried",Toast.LENGTH_SHORT).show();
                    Whatsappshare();

                }
            });

            other_network_salaried.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Home Salaried",Toast.LENGTH_SHORT).show();
                    Othernetworkshare();
                }
            });


            whatsapp_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Home Self employed",Toast.LENGTH_SHORT).show();
                    Whatsappshare2();


                }
            });

            other_network.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Home Self employed",Toast.LENGTH_SHORT).show();
                    OtherNetwork2();
                }
            });
        }else if (getvalue.equalsIgnoreCase("Business Loan")){
            twolay.setVisibility(View.GONE);
            whatsapp_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Business loan",Toast.LENGTH_SHORT).show();
                    Whatsappshare();


                }
            });

            other_network.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Business loan",Toast.LENGTH_SHORT).show();
                    Othernetworkshare();
                }
            });
        }
        else if (getvalue.equalsIgnoreCase("Personal Loan")){
            twolay.setVisibility(View.GONE);
            whatsapp_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Personal loan",Toast.LENGTH_SHORT).show();
                    Whatsappshare();


                }
            });

            other_network.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ChecklistShare.this,"Personal loan",Toast.LENGTH_SHORT).show();
                    Othernetworkshare();
                }
            });

        }


        loantypetxt.setText(getvalue);

        Glide.with(this).load(icon)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(loanimgview);

        Checklistshare();

     /*   whatsapp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChecklistShare.this,"Check something change",Toast.LENGTH_SHORT).show();
                Whatsappshare();
            }
        });

        other_network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Othernetworkshare();

            }
        });
*/
    }

    private void Othernetworkshare() {

        StringBuilder str = new StringBuilder();
        String[] arrStr = new String[itemlist.size()];
        itemlist.toArray(arrStr);

        for (int i = 0; i < arrStr.length; i++) {
            str.append(arrStr[i]);
            str.append("\n");
            str.append("\n");
        }

     /*   StringBuilder str1=new StringBuilder();
        String[] arrStr1 = new String[selflist.size()];
        selflist.toArray(arrStr1);
        for (int i = 0; i < arrStr1.length; i++) {
            str1.append(arrStr1[i]);
            str1.append("\n");
            str1.append("\n");
        }*/
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "*" +loan_type+ "\n"+ "\n"  +str.toString());
        startActivity(intent);

    }

    private void Whatsappshare() {
        StringBuilder str = new StringBuilder();
        String[] arrStr = new String[itemlist.size()];
        itemlist.toArray(arrStr);

        for (int i = 0; i < arrStr.length; i++) {
            str.append(arrStr[i]);
            str.append("\n");
            str.append("\n");
        }

      /*  StringBuilder str1=new StringBuilder();
        String[] arrStr1 = new String[selflist.size()];
        selflist.toArray(arrStr1);
        for (int i = 0; i < arrStr1.length; i++) {
            str1.append(arrStr1[i]);
            str1.append("\n");
            str1.append("\n");
        }*/


        // String smsNumber = "9212197079"; //without '+'
        try {
            Intent sendIntent = new Intent();//"android.intent.action.MAIN");
            //sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            String shareMessage =  "*"+loan_type+"*";
            String mandatory =  "*"+ " Star Marked Documents are Mandatory documents"+"*";
            String ss="*"+mandatory+"*";
            String anyone =  "*"+"(Please upload any one of the documents under each section)"+"*";
            String anyone1 =  "* Marked Documents are Mandatory Documents";



            //sendIntent.putExtra(Intent.EXTRA_TEXT, str.toString()+"\n"+str1.toString());
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage+"\n"+"\n" +anyone1+"\n"+"\n"+anyone+"\n"+ "\n"+str.toString());



            // sendIntent.putStringArrayListExtra(Intent.EXTRA_TEXT,finalList);

            // sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    private void Checklistshare() {
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;

        try {
            J = new JSONObject();
            if (getvalue != null) {
                if (getvalue.equalsIgnoreCase("Personal Loan")) {
                    J.put("loan_type", "21");
                    J.put("loan_category", "2");
                    J.put("employement_type", "1");
                } else if (getvalue.equalsIgnoreCase("Business Loan")) {
                    J.put("loan_type", "20");
                    J.put("loan_category", "2");
                    J.put("employement_type", "3");
                } else if (getvalue.equalsIgnoreCase("Loan Against Property")) {
                    J.put("loan_type", "2");
                    J.put("loan_category", "1");
                    J.put("employement_type", "1");
                } else if (getvalue.equalsIgnoreCase("Home Loan")) {
                    J.put("loan_type", "1");
                    J.put("loan_category", "1");
                    J.put("employement_type", "1");
                }
            }
            Log.d("Document_Checklist", String.valueOf(J));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CHECKLIST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response);


                        if (getvalue.equalsIgnoreCase("Business Loan")) {
                            listview.invalidateViews();

                            try {
                                jsonObject1 = response.getJSONObject("Applicant");
                                Jsonobject2=response.getJSONObject("loan_typearr");
                                loan_type=Jsonobject2.getString("loan_type");

                                // first Array
                                JSONArray jsonArray = jsonObject1.getJSONArray("Identity Proof");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    String docreq=jsonObject2.getString("doc_req");
                                    String main_value = "<b>"+classname +":</b> ";
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");

                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                       // shalist.add("===================================");
                                        // itemlist.addAll(contentlist);
                                    }

                                   // viewlayout.setVisibility(View.VISIBLE);

                                }
                                listview.setDividerHeight(1);
                                listview.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1, itemlist) {
                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        View row = super.getView(position, convertView, parent);
                                        Log.i(TAG, "getView: "+getItem(position));
                                        TextView textView = (TextView) super.getView(position, convertView, parent);

                                        switch (getItem(position)){
                                            case "Identity Proof *":
                                            case "Address Proof (Current Residence) Rented*":
                                            case "Address Proof (Current Residence) Owned*":
                                            case "Signature Verification Document *":
                                            case "Bank Statement - Last 12 Months *":
                                            case "Business Vintage Proof *":
                                            case "Income Proof ":
                                            case "Residence/Property Ownership Proof ":
                                            case "Photo Proof ":
                                                textView.setTextColor(Color.parseColor("#012B5D"));
                                                break;
                                            default:
                                                textView.setTextColor(Color.parseColor("#8A8A8A"));
                                                break;

                                        }

                                     /*   if (getItem(position).equalsIgnoreCase("Identity Proof *")){
                                            textView.setTextColor(Color.parseColor("#2196F3"));

                                        }else{
                                            textView.setTextColor(Color.parseColor("#000000"));
                                        }*/
                                        return row;
                                    }
                                });


                                //second array
                                JSONArray jsonArrays = jsonObject1.getJSONArray("Address Proof (Current Residence) Rented");
                                for (int i = 0; i < jsonArrays.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }
                          /*      listview.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1, itemlist) {
                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {

                                        TextView textView = (TextView) super.getView(position, convertView, parent);
                                        textView.setTextColor(Color.parseColor("#B4B4B4"));
                                        return textView;
                                    }
                                });*/

                                //third array
                                JSONArray jsonArrays3 = jsonObject1.getJSONArray("Address Proof (Current Residence) Owned");
                                for (int i = 0; i < jsonArrays3.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays3.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //forth array

                                JSONArray jsonArrays4 = jsonObject1.getJSONArray("Signature Verification Document");
                                for (int i = 0; i < jsonArrays4.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays4.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //fifth array

                                JSONArray jsonArrays5 = jsonObject1.getJSONArray("Bank Statement - Last 12 Months");
                                for (int i = 0; i < jsonArrays5.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays5.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //sixth array

                                JSONArray jsonArrays6 = jsonObject1.getJSONArray("Business Vintage Proof");
                                for (int i = 0; i < jsonArrays6.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays6.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //seventh array
                                JSONArray jsonArrays7 = jsonObject1.getJSONArray("Income Proof");
                                for (int i = 0; i < jsonArrays7.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays7.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //eight array

                                JSONArray jsonArrays8 = jsonObject1.getJSONArray("Residence/Property Ownership Proof");
                                for (int i = 0; i < jsonArrays8.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays8.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //ninth array

                                JSONArray jsonArrays9 = jsonObject1.getJSONArray("Photo Proof");
                                for (int i = 0; i < jsonArrays9.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays9.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }


                            /*    listview.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1, itemlist) {
                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        TextView textView = (TextView) super.getView(position, convertView, parent);
                                        textView.setTextColor(Color.parseColor("#B4B4B4"));
                                        return textView;
                                    }
                                });*/


                                Log.i(TAG, "onResponse:jsonArray " + jsonArray);
                                Log.i(TAG, "Response: on" + jsonObject1);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();
                        }


                        //Personal loan type
                        else if (getvalue.equalsIgnoreCase("Personal Loan")) {
                            listview.invalidateViews();

                            try {
                                jsonObject1 = response.getJSONObject("Applicant");
                                Jsonobject2=response.getJSONObject("loan_typearr");
                                loan_type=Jsonobject2.getString("loan_type");

                                // first Array
                                JSONArray jsonArray = jsonObject1.getJSONArray("Identity Proof");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                 /*   SpannableStringBuilder builder = new SpannableStringBuilder();
                                    String red = "(Mandatory document)";
                                    SpannableString redSpannable= new SpannableString(red);
                                    redSpannable.setSpan(new ForegroundColorSpan(Color.RED), 0, red.length(), 0);
                                    builder.append(redSpannable);*/
                                   // itemlist.add(classname + "*" +builder);

                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname +"*");

                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }
                             /*   listview.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1,itemlist) {
                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        TextView textView = (TextView) super.getView(position, convertView, parent);
                                        textView.setTextColor(Color.parseColor("#B4B4B4"));
                                        return textView;
                                    }
                                });*/
                                //second array
                                JSONArray jsonArrays = jsonObject1.getJSONArray("Address Proof (Current Residence) Rented");
                                for (int i = 0; i < jsonArrays.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                   // itemlist.add(classname);
                                    itemlist.add(classname +"*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                listview.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1, itemlist) {
                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        View row = super.getView(position, convertView, parent);
                                        Log.i(TAG, "getView: "+getItem(position));
                                        TextView textView = (TextView) super.getView(position, convertView, parent);

                                        switch (getItem(position)){
                                            case "Identity Proof *":
                                            case "Address Proof (Current Residence) Rented*":
                                            case "Address Proof (Current Residence) Owned*":
                                            case "Signature Verification Document *":
                                            case "Bank Statement - Last 12 Months *":
                                            case "Business Vintage Proof *":
                                            case "Income Proof ":
                                            case "Residence/Property Ownership Proof ":
                                            case "Photo Proof ":
                                                textView.setTextColor(Color.parseColor("#012B5D"));
                                                break;
                                            default:
                                                textView.setTextColor(Color.parseColor("#8A8A8A"));
                                                break;

                                        }
                                        return row;
                                    }
                                });

                                //third array
                                JSONArray jsonArrays3 = jsonObject1.getJSONArray("Address Proof (Current Residence) Owned");
                                for (int i = 0; i < jsonArrays3.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays3.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                   // itemlist.add(classname);
                                    itemlist.add(classname +"*");

                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //forth array

                                JSONArray jsonArrays4 = jsonObject1.getJSONArray("Signature Verification Document");
                                for (int i = 0; i < jsonArrays4.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays4.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                   // itemlist.add(classname);
                                    itemlist.add(classname +"*");

                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //fifth array

                                JSONArray jsonArrays5 = jsonObject1.getJSONArray("Bank Statement - Last 6 Months");
                                for (int i = 0; i < jsonArrays5.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays5.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                  //  itemlist.add(classname);
                                    itemlist.add(classname +"*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //sixth array

                                JSONArray jsonArrays6 = jsonObject1.getJSONArray("Salary Proof");
                                for (int i = 0; i < jsonArrays6.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays6.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    //itemlist.add(classname);
                                    itemlist.add(classname +"*");

                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //seventh array
                                JSONArray jsonArrays7 = jsonObject1.getJSONArray("Income Proof");
                                for (int i = 0; i < jsonArrays7.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays7.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);
                                    // itemlist.add(classname);
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //eight array

                                JSONArray jsonArrays8 = jsonObject1.getJSONArray("Residence/Property Ownership Proof");
                                for (int i = 0; i < jsonArrays8.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays8.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }


                                progressDialog.dismiss();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                        //Home loan salaried

                        else if (getvalue.equalsIgnoreCase("Home Loan")) {
                            try {
                                jsonObject1 = response.getJSONObject("Applicant");
                                propertyObject = response.getJSONObject("Property");
                                Jsonobject2=response.getJSONObject("loan_typearr");
                                loan_type=Jsonobject2.getString("loan_type");

                                // first Array
                                JSONArray jsonArray = jsonObject1.getJSONArray("Identity Proof");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");

                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }
                             /*   listview.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1,itemlist) {
                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        TextView textView = (TextView) super.getView(position, convertView, parent);
                                        textView.setTextColor(Color.parseColor("#B4B4B4"));
                                        return textView;
                                    }
                                });*/
                                //second array
                                JSONArray jsonArrays = jsonObject1.getJSONArray("Address Proof (Current Residence) Rented");
                                for (int i = 0; i < jsonArrays.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //third array
                                JSONArray jsonArrays3 = jsonObject1.getJSONArray("Address Proof (Current Residence) Owned");
                                for (int i = 0; i < jsonArrays3.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays3.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //forth array

                                JSONArray jsonArrays4 = jsonObject1.getJSONArray("Signature Verification Document");
                                for (int i = 0; i < jsonArrays4.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays4.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //fifth array

                                JSONArray jsonArrays5 = jsonObject1.getJSONArray("Bank Statement - Last 6 Months");
                                for (int i = 0; i < jsonArrays5.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays5.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //sixth array

                                JSONArray jsonArrays6 = jsonObject1.getJSONArray("Salary Proof");
                                for (int i = 0; i < jsonArrays6.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays6.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname+ "*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //seventh array
                                JSONArray jsonArrays7 = jsonObject1.getJSONArray("Income Proof");
                                for (int i = 0; i < jsonArrays7.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays7.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //eight array

                                JSONArray jsonArrays8 = jsonObject1.getJSONArray("Residence/Property Ownership Proof");
                                for (int i = 0; i < jsonArrays8.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays8.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //property array

                                JSONArray jsonArrayp = propertyObject.getJSONArray("Property Document");
                                for (int i = 0; i < jsonArrayp.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrayp.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);

                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                listview.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1, itemlist) {
                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        View row = super.getView(position, convertView, parent);
                                        Log.i(TAG, "getView: "+getItem(position));
                                        TextView textView = (TextView) super.getView(position, convertView, parent);

                                        switch (getItem(position)){
                                            case "Identity Proof *":
                                            case "Address Proof (Current Residence) Rented*":
                                            case "Address Proof (Current Residence) Owned*":
                                            case "Signature Verification Document *":
                                            case "Bank Statement - Last 6 Months *":
                                            case "Salary Proof *":
                                            case "Income Proof ":
                                            case "Residence/Property Ownership Proof ":
                                            case "Property Document ":
                                                textView.setTextColor(Color.parseColor("#012B5D"));
                                                break;
                                            default:
                                                textView.setTextColor(Color.parseColor("#8A8A8A"));
                                                break;

                                        }
                                        return row;
                                    }
                                });
                                progressDialog.dismiss();
                                Homeloanselfemploy();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();


                        }


                        //loan against property salaried

                        else if (getvalue.equalsIgnoreCase("Loan Against Property")) {
                            try {
                                jsonObject1 = response.getJSONObject("Applicant");
                                propertyObject = response.getJSONObject("Property");
                                Jsonobject2=response.getJSONObject("loan_typearr");
                                loan_type=Jsonobject2.getString("loan_type");

                                // first Array
                                JSONArray jsonArray = jsonObject1.getJSONArray("Identity Proof");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname +"*");

                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }
                             /*   listview.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1,itemlist) {
                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        TextView textView = (TextView) super.getView(position, convertView, parent);
                                        textView.setTextColor(Color.parseColor("#B4B4B4"));
                                        return textView;
                                    }
                                });*/
                                //second array
                                JSONArray jsonArrays = jsonObject1.getJSONArray("Address Proof (Current Residence) Rented");
                                for (int i = 0; i < jsonArrays.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname +"*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //third array
                                JSONArray jsonArrays3 = jsonObject1.getJSONArray("Address Proof (Current Residence) Owned");
                                for (int i = 0; i < jsonArrays3.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays3.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname +"*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //forth array

                                JSONArray jsonArrays4 = jsonObject1.getJSONArray("Signature Verification Document");
                                for (int i = 0; i < jsonArrays4.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays4.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname +"*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //fifth array

                                JSONArray jsonArrays5 = jsonObject1.getJSONArray("Bank Statement - Last 6 Months");
                                for (int i = 0; i < jsonArrays5.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays5.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname +"*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //sixth array

                                JSONArray jsonArrays6 = jsonObject1.getJSONArray("Salary Proof");
                                for (int i = 0; i < jsonArrays6.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays6.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname +"*");
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //seventh array
                                JSONArray jsonArrays7 = jsonObject1.getJSONArray("Income Proof");
                                for (int i = 0; i < jsonArrays7.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays7.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //eight array

                                JSONArray jsonArrays8 = jsonObject1.getJSONArray("Residence/Property Ownership Proof");
                                for (int i = 0; i < jsonArrays8.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrays8.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);
                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }

                                //property array

                                JSONArray jsonArrayp = propertyObject.getJSONArray("Property Document");
                                for (int i = 0; i < jsonArrayp.length(); i++) {
                                    JSONObject jsonObject2 = jsonArrayp.getJSONObject(i);
                                    String classname = jsonObject2.getString("class_name");
                                    Log.i(TAG, "classname: " + classname);
                                    itemlist.add(classname);

                                    JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                    for (int j = 0; j < moreDetails.length(); j++) {
                                        JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                        String doctypename = jsonObject3.getString("document_name");
                                        itemlist.add(doctypename);
                                        // itemlist.addAll(contentlist);
                                    }
                                }
/*
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChecklistShare.this,
                                        android.R.layout.simple_list_item_1, android.R.id.text1, itemlist);
*/

                                listview.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1, itemlist) {
                                    @Override
                                    public View getView(int position, View convertView, ViewGroup parent) {
                                        View row = super.getView(position, convertView, parent);
                                        Log.i(TAG, "getView: "+getItem(position));
                                        TextView textView = (TextView) super.getView(position, convertView, parent);

                                        switch (getItem(position)){
                                            case "Identity Proof *":
                                            case "Address Proof (Current Residence) Rented*":
                                            case "Address Proof (Current Residence) Owned*":
                                            case "Signature Verification Document *":
                                            case "Bank Statement - Last 6 Months *":
                                            case "Salary Proof *":
                                            case "Income Proof ":
                                            case "Residence/Property Ownership Proof ":
                                            case "Property Document ":
                                                textView.setTextColor(Color.parseColor("#012B5D"));
                                                break;
                                            default:
                                                textView.setTextColor(Color.parseColor("#8A8A8A"));
                                                break;

                                        }
                                        return row;
                                    }
                                });

                             /*  listview.setAdapter(adapter);
                               adapter.notifyDataSetChanged();*/

                                progressDialog.dismiss();
                                LapSelfemploy();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            progressDialog.dismiss();

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", String.valueOf(error));
                Toast.makeText(ChecklistShare.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        Log.d("Response", "responsesss");
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }



    private void Homeloanselfemploy() {

        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;

        try {
            J = new JSONObject();
            J.put("loan_type", "1");
            J.put("loan_category", "1");
            J.put("employement_type", "3");

            Log.d("Document_Checklist", String.valueOf(J));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CHECKLIST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response);


                        try {
                            jsonObject1 = response.getJSONObject("Applicant");
                            Jsonobject2=response.getJSONObject("loan_typearr");

                            loan_type=Jsonobject2.getString("loan_type");

                            // first Array
                            JSONArray jsonArray = jsonObject1.getJSONArray("Identity Proof");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");

                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }

                            }


                            //second array
                            JSONArray jsonArrays = jsonObject1.getJSONArray("Address Proof (Current Residence) Rented");
                            for (int i = 0; i < jsonArrays.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //third array
                            JSONArray jsonArrays3 = jsonObject1.getJSONArray("Address Proof (Current Residence) Owned");
                            for (int i = 0; i < jsonArrays3.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays3.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //forth array

                            JSONArray jsonArrays4 = jsonObject1.getJSONArray("Signature Verification Document");
                            for (int i = 0; i < jsonArrays4.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays4.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //fifth array

                            JSONArray jsonArrays5 = jsonObject1.getJSONArray("Bank Statement - Last 12 Months");
                            for (int i = 0; i < jsonArrays5.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays5.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //sixth array

                            JSONArray jsonArrays6 = jsonObject1.getJSONArray("Business Vintage Proof");
                            for (int i = 0; i < jsonArrays6.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays6.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //seventh array
                            JSONArray jsonArrays7 = jsonObject1.getJSONArray("Income Proof");
                            for (int i = 0; i < jsonArrays7.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays7.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname);
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //eight array

                            JSONArray jsonArrays8 = jsonObject1.getJSONArray("Residence/Property Ownership Proof");
                            for (int i = 0; i < jsonArrays8.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays8.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname);
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //ninth array

                            JSONArray jsonArrays9 = jsonObject1.getJSONArray("Photo Proof");
                            for (int i = 0; i < jsonArrays9.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays9.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname);
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //property array

                            JSONArray jsonArrayp = propertyObject.getJSONArray("Property Document");
                            for (int i = 0; i < jsonArrayp.length(); i++) {
                                JSONObject jsonObject2 = jsonArrayp.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname);

                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            listView1.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1, selflist) {
                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View row = super.getView(position, convertView, parent);
                                    Log.i(TAG, "getView: "+getItem(position));
                                    TextView textView = (TextView) super.getView(position, convertView, parent);

                                    switch (getItem(position)){
                                        case "Identity Proof *":
                                        case "Address Proof (Current Residence) Rented*":
                                        case "Address Proof (Current Residence) Owned*":
                                        case "Signature Verification Document *":
                                        case "Bank Statement - Last 12 Months *":
                                        case "Business Vintage Proof *":
                                        case "Income Proof ":
                                        case "Residence/Property Ownership Proof ":
                                        case "Photo Proof ":
                                        case "Property Document ":
                                            textView.setTextColor(Color.parseColor("#012B5D"));
                                            break;
                                        default:
                                            textView.setTextColor(Color.parseColor("#8A8A8A"));
                                            break;

                                    }
                                    return row;
                                }
                            });
                            Log.i(TAG, "onResponse:jsonArray " + jsonArray);
                            Log.i(TAG, "Response: on" + jsonObject1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", String.valueOf(error));
                Toast.makeText(ChecklistShare.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        Log.d("Response", "responsesss");
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void LapSelfemploy(){
        final JSONObject jsonObject = new JSONObject();
        JSONObject J = null;

        try {
            J = new JSONObject();
            J.put("loan_type", "2");
            J.put("loan_category", "1");
            J.put("employement_type", "3");

            Log.d("Document_Checklist", String.valueOf(J));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.CHECKLIST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onResponse: " + response);


                        try {
                            jsonObject1 = response.getJSONObject("Applicant");
                            Jsonobject2=response.getJSONObject("loan_typearr");

                            loan_type=Jsonobject2.getString("loan_type");

                            // first Array
                            JSONArray jsonArray = jsonObject1.getJSONArray("Identity Proof");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");

                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }

                            }


                            //second array
                            JSONArray jsonArrays = jsonObject1.getJSONArray("Address Proof (Current Residence) Rented");
                            for (int i = 0; i < jsonArrays.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //third array
                            JSONArray jsonArrays3 = jsonObject1.getJSONArray("Address Proof (Current Residence) Owned");
                            for (int i = 0; i < jsonArrays3.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays3.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //forth array

                            JSONArray jsonArrays4 = jsonObject1.getJSONArray("Signature Verification Document");
                            for (int i = 0; i < jsonArrays4.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays4.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //fifth array

                            JSONArray jsonArrays5 = jsonObject1.getJSONArray("Bank Statement - Last 12 Months");
                            for (int i = 0; i < jsonArrays5.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays5.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //sixth array

                            JSONArray jsonArrays6 = jsonObject1.getJSONArray("Business Vintage Proof");
                            for (int i = 0; i < jsonArrays6.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays6.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname+ "*");
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //seventh array
                            JSONArray jsonArrays7 = jsonObject1.getJSONArray("Income Proof");
                            for (int i = 0; i < jsonArrays7.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays7.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname);
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //eight array

                            JSONArray jsonArrays8 = jsonObject1.getJSONArray("Residence/Property Ownership Proof");
                            for (int i = 0; i < jsonArrays8.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays8.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname);
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //ninth array

                            JSONArray jsonArrays9 = jsonObject1.getJSONArray("Photo Proof");
                            for (int i = 0; i < jsonArrays9.length(); i++) {
                                JSONObject jsonObject2 = jsonArrays9.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname);
                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }

                            //property array

                            JSONArray jsonArrayp = propertyObject.getJSONArray("Property Document");
                            for (int i = 0; i < jsonArrayp.length(); i++) {
                                JSONObject jsonObject2 = jsonArrayp.getJSONObject(i);
                                String classname = jsonObject2.getString("class_name");
                                Log.i(TAG, "classname: " + classname);
                                selflist.add(classname);

                                JSONArray moreDetails = jsonObject2.getJSONArray("doc_type_names");
                                for (int j = 0; j < moreDetails.length(); j++) {
                                    JSONObject jsonObject3 = moreDetails.getJSONObject(j);
                                    String doctypename = jsonObject3.getString("document_name");
                                    selflist.add(doctypename);
                                    // itemlist.addAll(contentlist);
                                }
                            }


                            listView1.setAdapter(new ArrayAdapter<String>(ChecklistShare.this, layout.simple_list_item_1, selflist) {
                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View row = super.getView(position, convertView, parent);
                                    Log.i(TAG, "getView: "+getItem(position));
                                    TextView textView = (TextView) super.getView(position, convertView, parent);

                                    switch (getItem(position)){
                                        case "Identity Proof *":
                                        case "Address Proof (Current Residence) Rented*":
                                        case "Address Proof (Current Residence) Owned*":
                                        case "Signature Verification Document *":
                                        case "Bank Statement - Last 12 Months *":
                                        case "Photo Proof":
                                        case "Income Proof ":
                                        case "Residence/Property Ownership Proof ":
                                        case "Business Vintage Proof *":
                                            case "Property Document ":
                                            textView.setTextColor(Color.parseColor("#012B5D"));
                                            break;
                                        default:
                                            textView.setTextColor(Color.parseColor("#8A8A8A"));
                                            break;

                                    }
                                    return row;
                                }
                            });
                            Log.i(TAG, "onResponse:jsonArray " + jsonArray);
                            Log.i(TAG, "Response: on" + jsonObject1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", String.valueOf(error));
                Toast.makeText(ChecklistShare.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        Log.d("Response", "responsesss");
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }

    private Spannable boldText(String mText, int mStart, int mEnd) {

        Spannable WordtoSpan = new SpannableString(mText);
        WordtoSpan.setSpan(new StyleSpan(Typeface.BOLD), mStart, mEnd,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return WordtoSpan;
    }

    private void Whatsappshare2() {

        StringBuilder str1=new StringBuilder();
        String[] arrStr1 = new String[selflist.size()];
        selflist.toArray(arrStr1);
        for (int i = 0; i < arrStr1.length; i++) {
            str1.append(arrStr1[i]);
            str1.append("\n");
            str1.append("\n");
        }


        // String smsNumber = "9212197079"; //without '+'
        try {
            Intent sendIntent = new Intent();//"android.intent.action.MAIN");
            //sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            String shareMessage =  "*"+loan_type+"*";
            String mandatory =  "*"+ " Star Marked Documents are Mandatory documents"+"*";
            String ss="*"+mandatory+"*";
            String anyone =  "*"+"(Please upload any one of the documents under each section)"+"*";
            String anyone1 =  "* Marked Documents are Mandatory Documents";



            //sendIntent.putExtra(Intent.EXTRA_TEXT, str.toString()+"\n"+str1.toString());
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage+"\n"+"\n" +anyone1+"\n"+"\n"+anyone+"\n"+ "\n"+str1.toString());



            // sendIntent.putStringArrayListExtra(Intent.EXTRA_TEXT,finalList);

            // sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void OtherNetwork2() {

        StringBuilder str1=new StringBuilder();
        String[] arrStr1 = new String[selflist.size()];
        selflist.toArray(arrStr1);
        for (int i = 0; i < arrStr1.length; i++) {
            str1.append(arrStr1[i]);
            str1.append("\n");
            str1.append("\n");
        }
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "*" +loan_type+ "\n"+ "\n"  +str1.toString());
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }




}