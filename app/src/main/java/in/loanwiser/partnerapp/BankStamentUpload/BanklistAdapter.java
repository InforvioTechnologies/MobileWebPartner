package in.loanwiser.partnerapp.BankStamentUpload;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;

public class BanklistAdapter extends RecyclerView.Adapter<BanklistAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Bankitems> items;
    private ArrayList<Bankdetails_model> items1;
    private String lead_id;
    private AlertDialog progressDialog;
    private LayoutInflater mInflater;


    String Loan_amount,sub_categoryid,transaction_id1,subtask_id,loan_type_id,loan_type,
            payment,applicant_id1;

    public BanklistAdapter(Context context, ArrayList<Bankdetails_model> items1) {
        this.context = context;
        this.items1 = items1;
        notifyDataSetChanged();
    }

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.banklist_row, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String acno=items1.get(position).getAcc_number();
        final String entitynumber=items1.get(position).getEntity_id();
        final String bankname=items1.get(position).getBank_name();
        final String acchold_name=items1.get(position).getAcchold_name();
        final String statusint=items1.get(position).getStatus_int();

        if (statusint.equalsIgnoreCase("1")){
            holder.names.setText(entitynumber);
            holder.urls.setText(acno);
        }else{
            holder.names.setText(acchold_name);
            holder.urls.setText(acno);
        }

/*
        if (acchold_name.equals("0")&& acchold_name.equalsIgnoreCase("")){
            holder.names.setText(entitynumber);
            holder.urls.setText(acno);
        }else  {
            holder.names.setText(acchold_name);
            holder.urls.setText(acno);
        }*/


        holder.viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String acno=items1.get(position).getAcc_number();
                final String entitynumber=items1.get(position).getEntity_id();
                final String bankname=items1.get(position).getBank_name();
                final String acchold_name=items1.get(position).getAcchold_name();
                final String statusint=items1.get(position).getStatus_int();
                Bankdetails_model model=new Bankdetails_model(acno,entitynumber,bankname,acchold_name,statusint);
                String Account_no =acno;
                String statusintvalue=statusint;
                String entityvalue=entitynumber;
               /* Objs.ac.StartActivityPutExtra(context, BankAnalysis.class,
                        Params.document,path);*/
                Intent in=new Intent(context,BankAnalysis.class);
                in.putExtra("adapter","adapter");
                in.putExtra("Account_no",Account_no);
                in.putExtra("statusint",statusint);
                in.putExtra("entitynumber",entityvalue);
                context.startActivity(in);
                ((Activity)context).finish();

            }
        });

    }

    @Override
    public int getItemCount() {
        return items1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView names,urls;
        CardView cardView;
        AppCompatButton viewbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            names=(TextView)itemView.findViewById(R.id.banknames);
            urls=(TextView)itemView.findViewById(R.id.urlname);
            cardView=itemView.findViewById(R.id.cardView);
            viewbtn=itemView.findViewById(R.id.viewbtn);

        }
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
