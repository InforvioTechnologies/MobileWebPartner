package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.Partner_Statues.Suggestion_item_freqent;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class RelationshipContact extends SimpleActivity {

    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    AppCompatTextView accnum,ac_typetxt,mobile_1;
    String mobile_no;

    LinearLayout rm_not_available,rm_available;
    AppCompatImageView call_1,call_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_relationship_contact);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this, R.layout.activity_relationship_contact);
        initTools(R.string.relationmanagar);
        progressDialog = new SpotsDialog(RelationshipContact.this, R.style.Custom);
        accnum = (AppCompatTextView) findViewById(R.id.accnum);
        ac_typetxt = (AppCompatTextView) findViewById(R.id.ac_typetxt);
        mobile_1 = (AppCompatTextView) findViewById(R.id.mobile_1);


        rm_not_available = (LinearLayout) findViewById(R.id.rm_not_available);
        rm_available = (LinearLayout) findViewById(R.id.rm_available);
        call_1 = (AppCompatImageView) findViewById(R.id.call_1);
        call_2 = (AppCompatImageView) findViewById(R.id.call_2);

        makeJsonObjReq1();

        call_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String mobileno="8883565000";
                callIntent.setData(Uri.parse("tel:" +mobileno));
                try {
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RelationshipContact.this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        call_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String mobileno=mobile_no;
                callIntent.setData(Uri.parse("tel:" +mobileno));
                try {
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RelationshipContact.this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void makeJsonObjReq1() {
        JSONObject J= null;
        try {
            J = new JSONObject();
            J =new JSONObject();
            J.put("b2b_userid", Pref.getID(getApplicationContext()));

        }catch (JSONException e)
        {
            e.printStackTrace();
        }



        progressDialog.show();
        Log.e("Request Dreopdown", J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.relationship_mangement, J,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject object) {

                        progressDialog.dismiss();
                        Log.e("Response Dreopdown", object.toString());
                        try {
                            String response = object.getString("status");
                            if(response.equals("success"))
                            {

                                JSONObject jsonObject = object.getJSONObject("data");

                                String name = jsonObject.getString("name");
                                String email = jsonObject.getString("email");
                                 mobile_no = jsonObject.getString("mobile_no");
                                accnum.setText(name);
                                ac_typetxt.setText(email);
                                mobile_1.setText(mobile_no);
                                rm_not_available.setVisibility(View.GONE);
                                rm_available.setVisibility(View.VISIBLE);
                               // ErrorStatus();
                            }else if(response.equals("no_lead")){
                              //  Toast.makeText(getApplicationContext(),"no lead found",Toast.LENGTH_SHORT).show();
                                ErrorStatus1();
                                rm_not_available.setVisibility(View.VISIBLE);
                                rm_available.setVisibility(View.GONE);

                            }else
                            {
                                rm_not_available.setVisibility(View.VISIBLE);
                                rm_available.setVisibility(View.GONE);
                                ErrorStatus();
                               // Toast.makeText(getApplicationContext(),"Not Assigning Partner",Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("respose Dreopdown", object.toString());
                        /// msgResponse.setText(response.toString());
                        //  Objs.a.showToast(getContext(), String.valueOf(object));

                        // Toast.makeText(mCon, response.toString(),Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

    }
    private void ErrorStatus() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.rm_popup);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView bankstatement_message=(AppCompatTextView) dialog.findViewById(R.id.bankstatement_message);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);
        Button submitbtn1 = (Button) dialog.findViewById(R.id.submitbtn1);
        AppCompatTextView phonenum=(AppCompatTextView)dialog.findViewById(R.id.phonenumber);
        AppCompatImageView imageView=(AppCompatImageView)dialog.findViewById(R.id.imgphn);
        //submitbtn.setVisibility(View.GONE);
        phonenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String mobileno="8999023498";
                callIntent.setData(Uri.parse("tel:" +mobileno));
                try {
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RelationshipContact.this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String mobileno="8999023498";
                callIntent.setData(Uri.parse("tel:" +mobileno));
                try {
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RelationshipContact.this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        submitbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(RelationshipContact.this,HelpSupportActivity.class);
                startActivity(intent);
                finish();

            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
    private void ErrorStatus1() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.rm_popup1);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView bankstatement_message=(AppCompatTextView) dialog.findViewById(R.id.bankstatement_message);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);
        Button submitbtn1 = (Button) dialog.findViewById(R.id.submitbtn1);
        AppCompatTextView phonenum=(AppCompatTextView)dialog.findViewById(R.id.phonenumber);
        AppCompatImageView imageView=(AppCompatImageView)dialog.findViewById(R.id.imgphn);
        //submitbtn.setVisibility(View.GONE);
        phonenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String mobileno="8999023498";
                callIntent.setData(Uri.parse("tel:" +mobileno));
                try {
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RelationshipContact.this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String mobileno="8999023498";
                callIntent.setData(Uri.parse("tel:" +mobileno));
                try {
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(RelationshipContact.this, "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        submitbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(RelationshipContact.this,HelpSupportActivity.class);
                startActivity(intent);
                finish();

            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
}