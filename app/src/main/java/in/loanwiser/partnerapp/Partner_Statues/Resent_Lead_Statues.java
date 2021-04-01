package in.loanwiser.partnerapp.Partner_Statues;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
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
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;

public class Resent_Lead_Statues extends RecyclerView.Adapter<Resent_Lead_Statues.CustomViewHolder> {

    private Context context;
    private ArrayList<Suggestion_item_freqent> items;
    private String lead_id;
    private AlertDialog progressDialog;

    String Loan_amount,sub_categoryid,transaction_id1,subtask_id,loan_type_id,loan_type,
            payment,applicant_id1;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public Resent_Lead_Statues(Context context, ArrayList<Suggestion_item_freqent> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.resent_lead_statues, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
      //  holder.applicant_id.setText("Testing Allocated Lead");

        /* Suggestion_item_freqent( J.getString(Params.id), J.getString(Params.user_id),J.getString(Params.name),
                                                J.getString(Params.mobileno),J.getString(Params.appointment_date),J.getString(Params.appointfrom_time)))*/

        String appointment_id  = items.get(position).getAppointment_id();
        final String applicant_id  = items.get(position).getUser_ID();
        String name  = items.get(position).getName();
        String loan_type  = items.get(position).getloan_type();
        String loan_amount  = items.get(position).getloan_amount();
        String status_disp  = items.get(position).getstatus_disp();


      //  String input= "sentence";
        String output = name.substring(0, 1).toUpperCase() + name.substring(1);
       // textview.setText(output);


        holder.lead_name.setText(output);
       // holder.loan_amount.setText(loan_amount);
        holder.loan_amount.setText("\u20B9"+loan_amount);
        holder.loan_type.setText(loan_type);
        holder.Statues_update.setText(status_disp);

        if(status_disp.equals("Pending under you"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#FF9200"));
            holder.Statues_update.setTextColor(Color.parseColor("#FF9200"));

            holder.Statues_update_view.setVisibility(View.VISIBLE);
            holder.Statues_update_view.setText("Complete Now");
            holder.Statues_update_view1.setVisibility(View.GONE);

        } else if(status_disp.equals("Submitted to Loanwiser"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#1592E6"));
            holder.Statues_update.setTextColor(Color.parseColor("#1592E6"));


            holder.Statues_update_view.setVisibility(View.GONE);
            holder.Statues_update_view1.setText("View");
            holder.Statues_update_view1.setVisibility(View.VISIBLE);

        }else if(status_disp.equals("Sanctioned"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#15CE00"));
            holder.Statues_update.setTextColor(Color.parseColor("#15CE00"));
            holder.Statues_update_view.setVisibility(View.GONE);
            holder.Statues_update_view1.setText("View");
            holder.Statues_update_view1.setVisibility(View.VISIBLE);
        }else if(status_disp.equals("Disbursed"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#D05AE9"));
            holder.Statues_update.setTextColor(Color.parseColor("#D05AE9"));
            holder.Statues_update_view.setVisibility(View.GONE);
            holder.Statues_update_view1.setText("View");
            holder.Statues_update_view1.setVisibility(View.VISIBLE);

        }else if(status_disp.equals("Sent to bank"))
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#00D1D1"));
            holder.Statues_update.setTextColor(Color.parseColor("#00D1D1"));
            holder.Statues_update_view.setVisibility(View.GONE);
            holder.Statues_update_view1.setText("View");
            holder.Statues_update_view1.setVisibility(View.VISIBLE);

        }else
        {
            holder.Statues_update_dot.setTextColor(Color.parseColor("#E3434A"));
            holder.Statues_update.setTextColor(Color.parseColor("#E3434A"));
            holder.Statues_update_view.setVisibility(View.GONE);
            holder.Statues_update_view1.setText("View");
            holder.Statues_update_view1.setVisibility(View.VISIBLE);
        }




      //  Log.e("The Appointment Date",name);
      //  String dt = parseDateToddMMyyyy(appointment_date);
       // holder.applicant_date.setText(dt);

    /*  holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final String applicant_id  = items.get(position).getUser_ID();

               Applicant_Status(applicant_id);

           }
       });*/

        holder.Statues_update_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String applicant_id  = items.get(position).getUser_ID();
                Applicant_Status(applicant_id);
            }
        });
        holder.Statues_update_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String applicant_id  = items.get(position).getUser_ID();
                Applicant_Status(applicant_id);
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
      //  return 3;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;
        private TextView lead_name,loan_amount,loan_type,Statues_update_dot,Statues_update;
        private CardView cardView;
        private AppCompatButton Bt_create_appointment;
        private AppCompatTextView Statues_update_view,Statues_update_view1;

        public CustomViewHolder(View view) {
            super(view);
            lead_name = view.findViewById(R.id.lead_name);
            loan_amount = view.findViewById(R.id.loan_amount);
            loan_type = view.findViewById(R.id.loan_type);
            Statues_update = view.findViewById(R.id.Statues_update);
            cardView = view.findViewById(R.id.cardView);
            Bt_create_appointment = view.findViewById(R.id.Bt_create_appointment);
            Statues_update_dot = view.findViewById(R.id.Statues_update_dot);
            Statues_update_view = view.findViewById(R.id.Statues_update_view);
            Statues_update_view1 = view.findViewById(R.id.Statues_update_view1);
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


    public void Applicant_Status(final String id) {

       // final String step_status11 = step_status1;
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.user_id, id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("Applicant Entry request", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.PARTNER_STATUES_IDs, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Applicant Entry", String.valueOf(response));
                        JSONObject jsonObject1 = new JSONObject();

                        try {
                            String statues = response.getString("status");

                            if(statues.contains("success"))
                            {
                                JSONObject jsonObject2 = response.getJSONObject("reponse");

                                JSONArray jsonArray = jsonObject2.getJSONArray("emp_states");

                                String user_id = jsonObject2.getString("user_id");
                                Loan_amount = jsonObject2.getString("loan_amount");
                                sub_categoryid =   jsonObject2.getString("sub_categoryid");
                                transaction_id1 =  jsonObject2.getString("transaction_id");
                                subtask_id =  jsonObject2.getString("subtask_id");
                                loan_type_id =  jsonObject2.getString("loan_type_id");
                                loan_type =  jsonObject2.getString("loan_type");
                                payment =  jsonObject2.getString("payment");
                                applicant_id1 =  "APP-"+user_id;


                                // String statues2 = "3";
                                Pref.putUSERID(context,user_id);
                                String _Emp_staus_jsonArray = jsonArray.toString();

                                Objs.ac.StartActivityPutExtra(context, Home.class,
                                        Params.user_id,user_id,
                                        Params.transaction_id,transaction_id1,
                                        Params.applicant_id,applicant_id1,
                                        Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                        Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);

                              /*  if(payment.equals("error"))
                                {
                                    Intent intent = new Intent(Dashboard_Activity.this, Payment_Details_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }else
                                {
                                    Log.d("applicant_id1",loan_type);
                                    Objs.ac.StartActivityPutExtra(mCon, Home.class,
                                            Params.user_id,user_id,
                                            Params.transaction_id,transaction_id1,
                                            Params.applicant_id,applicant_id1,
                                            Params.sub_taskid,subtask_id, Params.Applicant_status,_Emp_staus_jsonArray,
                                            Params.loan_type_id,loan_type_id,Params.loan_type,loan_type);
                                    finish();

                                }*/


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
}
