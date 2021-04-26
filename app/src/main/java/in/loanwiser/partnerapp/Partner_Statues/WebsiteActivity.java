package in.loanwiser.partnerapp.Partner_Statues;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class WebsiteActivity extends SimpleActivity {

    AppCompatTextView websiteurltxt,urlopentxt;
    private android.app.AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    public static final String b2b_user_id1 = "b2b_uer_id";
    String b2b_user_id;
    SharedPreferences pref; // 0 - for private mode
    String email,mobilenumber,contactperson,location,url;
    AppCompatButton whatsappbtn,othernetworkbtn,copyurlbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_website);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_website);
        initTools(R.string.mywebsite);
        progressDialog = new SpotsDialog(this, R.style.Custom);


        websiteurltxt=findViewById(R.id.websiteurltxt);
        whatsappbtn=findViewById(R.id.whatsappbtn);
        othernetworkbtn=findViewById(R.id.othernetworkbtn);
        copyurlbutton=findViewById(R.id.copyurlbutton);
        urlopentxt=findViewById(R.id.urlopentxt);

        pref = this.getSharedPreferences("MyPref", 0);
        b2b_user_id =  pref.getString(b2b_user_id1, null);
        Log.i("TAG", "b2b_userid_onCreate: "+b2b_user_id);

        Businesscarddetails();

        whatsappbtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                shareViaWhatsApp();
            }
        });

        othernetworkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Othernetwork();
            }
        });

        urlopentxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        copyurlbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(websiteurltxt.getText());
                Toast.makeText(WebsiteActivity.this, "Copied Website URL", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void Businesscarddetails() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2b_userid", b2b_user_id);
            Log.i("TAG", "Request "+J.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data  = String.valueOf(J);
        Log.d("Request :", data);
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.GETBUSINESSCARD, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        String JO_data  = String.valueOf(response);
                        Log.d("Request :", JO_data.toString());
                        try {
                            url=response.getString("url");
                            websiteurltxt.setText(url);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void shareViaWhatsApp() {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, url);
        whatsappIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            Objects.requireNonNull(getApplicationContext()).startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
        }
    }

    public void Othernetwork(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "Share"));
    }

}