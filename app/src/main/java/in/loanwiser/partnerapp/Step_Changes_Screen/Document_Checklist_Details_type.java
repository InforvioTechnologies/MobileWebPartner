package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
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
import in.loanwiser.partnerapp.Documents.Applicant_Doc_Details;
import in.loanwiser.partnerapp.Documents.Applicant_Doc_Details_Property;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class Document_Checklist_Details_type extends SimpleActivity {

    private Context mCon = this;
    String JSON, number, Jobject;
    private ProgressDialog pDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Document_Checklist_Details_type.class.getSimpleName();
    RelativeLayout lay_logout;
    AppCompatTextView applicant_from,dcomplete,notes;
    AppCompatButton logout, contact;
    String A_transaction_id, A_empstatue, A_type,A_Pr_type, contact_number;
    LinearLayout Ly_contact, Ly_chat,Ly_DOC_Status;
    String status, transaction_id, APP_id, application_form_,S_transaction_id;
    CardView card_view_doc;
    ImageView status_upload;
    String msg;
    private FloatingActionButton float_chat;
    private AlertDialog progressDialog;
    private AppCompatButton Completed;
    private CheckBox check_complete;
    String jsonStringObj;
    String jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_applicant__details__single);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_applicant__details_type);
        initTools(R.string.doc_check_list1);
        progressDialog = new SpotsDialog(this, R.style.Custom);

        applicant_from = (AppCompatTextView) findViewById(R.id.applicant_from);
        dcomplete = (AppCompatTextView) findViewById(R.id.dcomplete);
        notes = (AppCompatTextView) findViewById(R.id.notes);
        card_view_doc = (CardView) findViewById(R.id.card_view_doc);
        status_upload = (ImageView) findViewById(R.id.status_upload);
        Completed = (AppCompatButton) findViewById(R.id.Completed);
        check_complete = (CheckBox) findViewById(R.id.check_complete);
        Ly_DOC_Status = (LinearLayout) findViewById(R.id.Ly_DOC_Status);


        status_upload.setVisibility(View.GONE);

        Intent intent = getIntent();
         jsonArray = intent.getStringExtra("jsonArray");

        try {
            JSONArray ja = new JSONArray(jsonArray);
            if (ja.length() > 0) {
                setAdapter(ja);
            } else {
                Objs.a.ShowHideNoItems(mCon, true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }



        Completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitAlert(mCon);
                //Objs.a.showToast(mCon,"Completed");

            }
        });
        Completed.setEnabled(false);
        Completed.setBackgroundResource(R.color.gray);


        check_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    Completed.setEnabled(true);
                    Completed.setBackgroundResource(R.color.colorAccent);
                }else {
                    Completed.setEnabled(false);
                    Completed.setBackgroundResource(R.color.gray);
                }
            }
        });




    }


    public void ExitAlert(Context context) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setTitle("Are you sure have you uploaded all the documents?");
       // builder.setMessage(Moblie);
        builder.setNegativeButton("No", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Documnet_upload_Status(S_transaction_id);
            }
        });
        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        Objs.a.DialogStyle(context, alert);
    }

    private void setAdapter(JSONArray ja) {
        ListItemAdapter adapter = new ListItemAdapter(mCon,ja);
        Objs.a.getRecyleview(this).setAdapter(adapter);
    }

    public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {

        JSONArray list = new JSONArray();
        Context mCon;
        JSONObject J;


        public ListItemAdapter(Context mCon, JSONArray list) {
            this.list = list;
            this.mCon = mCon;
        }

        @Override
        public int getItemCount() {
            return list.length();
        }

        public JSONObject getItem(int i) {
            try {
                return list.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_applicant_details, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);

                if(J.getString(Params.applicant_name).equals("Property")){
                    holder.image_property.setImageDrawable(getResources().getDrawable(R.drawable.ic_house));
                }else if(J.getString(Params.applicant_name).equals("Vehicle")){
                    holder.image_property.setImageDrawable(getResources().getDrawable(R.drawable.car1));
                } else{
                    holder.image_property.setImageDrawable(getResources().getDrawable(R.drawable.ic_applicant));
                }

                holder.class_name.setText(J.getString(Params.applicant_name));
                Objs.a.NewNormalFontStyle(mCon,holder.class_name);

                holder.card_view_class_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);

                        try {
                            //A_transaction_id = J.getString(Params.transaction_id);
                            A_transaction_id = J.getString(Params.transaction_id);
                            A_empstatue = J.getString(Params.emp_states);
                            A_type = J.getString(Params.user_type);
                            String applicant_name = J.getString(Params.applicant_name);

                                    String all = A_transaction_id + "," + A_type + "," + A_Pr_type + "," + A_empstatue;
                                    Log.e("Applicant_DetailsSingle", all);
                                    Objs.ac.StartActivityPutExtra(mCon, Document_Check_List.class,
                                            Params.applicant_name,applicant_name,
                                            Params.transaction_id,A_transaction_id,
                                            Params.user_type,A_type,
                                            Params.emp_state,A_empstatue);
                                    //  Pref.putATID(mCon,A_id);
                                    // Pref.putAEID(mCon,A_empstatue);Params.user_type,A_type,
                                    //  Objs.ac.StartActivity(mCon, Applicant_Doc_Details.class);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            } catch (NullPointerException e) {
                Objs.a.showToast(mCon, e.toString());
            } catch (Exception e) {
                Objs.a.showToast(mCon, e.toString());
            }
        }

        private void update(JSONArray list1) {
            list = list1;
        }


        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            AppCompatTextView class_name;
            CardView card_view_class_name;
            ImageView image_property;
            View view;

            public ViewHolder(View itemView) {
                super(itemView);

                class_name  = (AppCompatTextView) itemView.findViewById(R.id.applicant_name);
                card_view_class_name  = (CardView) itemView.findViewById(R.id.card_view_doc_typename2);
                image_property  = (ImageView) itemView.findViewById(R.id.image_property);

            }
        }
    }


    private void Documnet_upload_Status(String id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.transaction_id, id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Documnet_upload_Status", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.UPDATE_STATUS_DOC, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Documnet_upload_Status", String.valueOf(response));
                        //{"request":{"transaction_id":"10194"},"response":true,"status":"success"}
                        try {

                          if(response.getString(Params.status).equals("success")){

                              if(response.getBoolean("doc_uploadstatus")){
                                 // Objs.a.showToast( mCon,"Document uploaded");
                                  Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                                  finish();
                              }else {

                                  Completed.setEnabled(false);
                                  Completed.setBackgroundResource(R.color.gray);
                                  if (check_complete.isChecked()) {
                                      check_complete.setChecked(false);
                                  }

                                  Objs.a.showToast(mCon,"You have not yet Uploaded any Document for Loan Process "+ "\n" + "Please Upload the Document");

                              }



                            /*  Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
                              finish();*/
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
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
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


}

