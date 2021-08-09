package in.loanwiser.partnerapp.PartnerActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.User_Account.BankDetails;
import in.loanwiser.partnerapp.User_Account.ProfileSettings;
import in.loanwiser.partnerapp.User_Account.Welcome_Page;

import static adhoc.app.applibrary.Config.AppUtils.Objs.a;

public class profile extends SimpleActivity {
    
    AppCompatTextView partnername,partnercode,personal_text,bank_text,myearn_text,logouttct;
    private android.app.AlertDialog progressDialog;
    private Context mCon = this;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    MaterialCardView personalcard,bankcard;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.profilescreen);
        initTools(R.string.profile);
        progressDialog = new SpotsDialog(this, R.style.Custom);


        partnername=findViewById(R.id.partnername);
        partnercode=findViewById(R.id.partnercode);
        personal_text=findViewById(R.id.personal_text);
        bank_text=findViewById(R.id.bank_text);
        myearn_text=findViewById(R.id.myearn_text);
        logouttct=findViewById(R.id.logouttct);

        personalcard=findViewById(R.id.personalcard);
        bankcard=findViewById(R.id.bankcard);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        partnercode.setText(Pref.getPART(mCon));


        Typeface font = Typeface.createFromAsset(this.getAssets(), "segoe_ui.ttf");
        partnername.setTypeface(font);
        partnercode.setTypeface(font);
        personal_text.setTypeface(font);
        bank_text.setTypeface(font);
        myearn_text.setTypeface(font);
        logouttct.setTypeface(font);

        logouttct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExitAlert(mCon);
            }
        });

        personalcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(profile.this, ProfileSettings.class);
                startActivity(intent);
                finish();
            }
        });

        bankcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(profile.this, BankDetails.class);
                startActivity(intent);
                finish();
            }
        });

        Account_Listings_Details();

    }


    private void Account_Listings_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.b2b_userid, Pref.getID(mCon));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("Profile Request" , String.valueOf(J));
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PROFILE_DETAILS_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString(Params.status).equals(Params.success)) {
                                JSONObject jobj = response.getJSONObject(Params.response);
                                Log.e("Profile Page" , String.valueOf(jobj));
                                partnername.setText(jobj.getString(Params.contact_person));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    public void ExitAlert(Context context) {
        androidx.appcompat.app.AlertDialog.Builder builder = new  androidx.appcompat.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setMessage("Do you want to Logout..?");
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Pref.removeLogin(getApplicationContext());
                Pref.removeID(getApplicationContext());
                Pref.removeMOB(getApplicationContext());
                Pref.removeMobile(getApplicationContext());
                editor.remove("b2b_uer_id");
                Intent i = new Intent(profile.this, Welcome_Page.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
                finish();
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        a.DialogStyle(context, alert);
    }
}