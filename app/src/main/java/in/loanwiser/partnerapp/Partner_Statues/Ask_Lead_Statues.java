package in.loanwiser.partnerapp.Partner_Statues;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.CameraActivity.ASK_ManiActivity_Image2;
import in.loanwiser.partnerapp.CameraActivity.ManiActivity_Image2;
import in.loanwiser.partnerapp.PartnerActivitys.Viability_Data_revamp;
import in.loanwiser.partnerapp.R;

public class Ask_Lead_Statues extends RecyclerView.Adapter<Ask_Lead_Statues.CustomViewHolder> {

    private Context context;
    private ArrayList<Ask_item_freqent> items;
    private String lead_id;
    private AlertDialog progressDialog;

    String Loan_amount,sub_categoryid,transaction_id1,subtask_id,loan_type_id,loan_type,
            payment,applicant_id1,
            user_id1,app_id,name,request_by,applicant,applicant1,created_at,status_disp,doc_typename,status_disp1,
            Notes,transaction_id,doc_classname,doc_typeid,ask_id;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public Ask_Lead_Statues(Context context, ArrayList<Ask_item_freqent> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ask_lead_statues, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
      //  holder.applicant_id.setText("Testing Allocated Lead");

        /* Suggestion_item_freqent( J.getString(Params.id), J.getString(Params.user_id),J.getString(Params.name),
                                                J.getString(Params.mobileno),J.getString(Params.appointment_date),J.getString(Params.appointfrom_time)))*/

        String Name  = items.get(position).getName();
        final String applicant  = items.get(position).getapplicant();
        String raised_date  = items.get(position).getcreated_at();
        String status_disp  = items.get(position).getstatus_disp();
        String user_id  = items.get(position).getuser_id();
        String updated_at  = items.get(position).getupdated_at();
        String doc_classname1  = items.get(position).getdoc_classname();


        holder.lead_name.setText(Name +"("+applicant +")");
       // holder.loan_amount.setText(loan_amount);
       // holder.loan_amount.setText("\u20B9"+loan_amount);
        holder.status_pending_ask.setText(status_disp);
        holder.raised_date.setText(raised_date);

       /* if(status_disp.contains("Pending under you"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#FF9200"));
        } else if(status_disp.contains("Submitted to Loanwiser"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#F9F338"));
        }else if(status_disp.contains("Sanctioned"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#1592E6"));
        }else if(status_disp.contains("Disbursed"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#15CE00"));
        }else if(status_disp.contains("Sent to bank"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#012B5D"));
        }else
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#E3434A"));
        }*/




      //  Log.e("The Appointment Date",name);
      //  String dt = parseDateToddMMyyyy(appointment_date);
       // holder.applicant_date.setText(dt);

      holder.Statues_update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String applicant_id  = items.get(position).getuser_id();

               user_id1 = items.get(position).getuser_id();
               app_id = items.get(position).getapp_id();
               name = items.get(position).getName();
               request_by = items.get(position).getrequest_by();
               applicant1 = items.get(position).getapplicant();
               created_at = items.get(position).getcreated_at();
               status_disp1 = items.get(position).getstatus_disp();
               doc_typename = items.get(position).getdoc_typename();
               Notes = items.get(position).getnotes();

               transaction_id = items.get(position).gettransaction_id();
               doc_classname = items.get(position).getdoc_classname();
               doc_typeid = items.get(position).getdoc_typeid();
               ask_id = items.get(position).getask_id();

               Submit_popup();
              // Applicant_Status(applicant_id);
             /*  Intent intent = new Intent(context, Ask_Lead_Dashboard_Activity.class);
               intent.putExtra("user_id", applicant_id);
               context.startActivity(intent);*/
           }
       });

     /*  holder.Bt_create_appointment.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String appoint_id  = items.get(position).getAppointment_id();
               String applicant_id  = items.get(position).getUser_ID();
               String lead_id = "1";
               String SOD_EOD_ID = "11";

           }
       });*/
    }

    @Override
    public int getItemCount() {
        return items.size();
       // return 6;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView lead_name,status_pending_ask,raised_date,Statues_update_dot;
        private CardView cardView;
        private AppCompatButton Statues_update;

        public CustomViewHolder(View view) {
            super(view);
            lead_name = view.findViewById(R.id.lead_name);
            status_pending_ask = view.findViewById(R.id.status_pending_ask);
            raised_date = view.findViewById(R.id.raised_date);

            cardView = view.findViewById(R.id.cardView);
            Statues_update = view.findViewById(R.id.Statues_update);

            progressDialog = new SpotsDialog(context, R.style.Custom);
        }
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd";
        //  String inputPattern = "yyyy-MM-dd HH:mm:ss";
        // String outputPattern = "dd-MMM-yyyy h:mm a";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public void Applicant_Status(final String user_id) {

       // final String step_status11 = step_status1;
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("user_id", user_id);
           // J.put("user_id", "51647");
           // {"user_id":"51647"}
        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("Ask user request", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.getAsklist, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("askDashboard response", String.valueOf(response));
                        try {
                            if(response.getString("status").equals("success")){

                                JSONArray ja = response.getJSONArray("data");

                                if (ja.length()>0){

                                    for(int i = 0;i<ja.length();i++){
                                        JSONObject J = ja.getJSONObject(i);



                                         user_id1 = J.getString("user_id");
                                         app_id = J.getString("app_id");
                                         name = J.getString("name");
                                         request_by = J.getString("request_by");
                                         applicant = J.getString("applicant");
                                         created_at = J.getString("created_at");
                                         status_disp = J.getString("status_disp");
                                         doc_typename = J.getString("doc_typename");
                                        progressDialog.dismiss();
                                        Submit_popup();

                                    }



                                }else {

                                }
                            }else {

                                Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("Applicant Entry request", String.valueOf(error));
                Toast.makeText(context,error.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void Submit_popup(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.ly_ask_new_lead_status1);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn=(Button)dialog.findViewById(R.id.submitbtn);
        AppCompatImageView close_image=(AppCompatImageView)dialog.findViewById(R.id.close_image);

        AppCompatTextView App_id,name_applicant,name_callcenter,status_Ask,ask_crated_at,documnet_name,notes,
                Lead_Name,requester,Staus,raised_on,details,notes1;
        AppCompatButton ask_Tack_Action;

        Typeface font = Typeface.createFromAsset(context.getAssets(), "segoe_ui.ttf");

        App_id  = (AppCompatTextView) dialog.findViewById(R.id.App_id);
        name_applicant  = (AppCompatTextView) dialog.findViewById(R.id.name_applicant);
        name_callcenter  = (AppCompatTextView) dialog.findViewById(R.id.name_callcenter);
        status_Ask  = (AppCompatTextView) dialog.findViewById(R.id.status_Ask);
        ask_crated_at  = (AppCompatTextView) dialog.findViewById(R.id.ask_crated_at);
        documnet_name  = (AppCompatTextView) dialog.findViewById(R.id.documnet_name);
        notes  = (AppCompatTextView) dialog.findViewById(R.id.notes);
        ask_Tack_Action  = (AppCompatButton) dialog.findViewById(R.id.ask_Tack_Action);


        Lead_Name  = (AppCompatTextView) dialog.findViewById(R.id.Lead_Name);
        requester  = (AppCompatTextView) dialog.findViewById(R.id.requester);
        Staus  = (AppCompatTextView) dialog.findViewById(R.id.Staus);
        raised_on  = (AppCompatTextView) dialog.findViewById(R.id.raised_on);
        details  = (AppCompatTextView) dialog.findViewById(R.id.details);
        notes1  = (AppCompatTextView) dialog.findViewById(R.id.notes1);

        App_id.setText(app_id);
        //   name_applicant.setText("\u20B9"+post.getloan_amount());
        name_applicant.setText(name);
        name_callcenter.setText(request_by);
        status_Ask.setText(status_disp1);
        ask_crated_at.setText(created_at);
        documnet_name.setText(applicant1+","+ " "+ "Need"+ " "+ doc_typename);
        notes.setText(Notes);


        App_id.setTypeface(font);
        name_applicant.setTypeface(font);
        name_callcenter.setTypeface(font);
        status_Ask.setTypeface(font);
        ask_crated_at.setTypeface(font);
        documnet_name.setTypeface(font);
        notes.setTypeface(font);
        Lead_Name.setTypeface(font);
        requester.setTypeface(font);
        Staus.setTypeface(font);
        raised_on.setTypeface(font);
        details.setTypeface(font);
        notes1.setTypeface(font);

        ask_Tack_Action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
              //  transaction_id,doc_classname,doc_typeid

                Objs.ac.StartActivityPutExtra(context, ASK_ManiActivity_Image2.class, Params.doc_typename,doc_typename,
                        Params.docid,doc_typeid,Params.transaction_id,transaction_id,Params.ask_id,ask_id,Params.doc_classname,doc_classname);
              /*  Intent intent = new Intent(context, ASK_ManiActivity_Image2.class);
                context.startActivity(intent);*/
            }
        });

        close_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        if (!dialog.isShowing()) {
            dialog.show();
        }

    }
}
