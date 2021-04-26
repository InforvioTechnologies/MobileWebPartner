package in.loanwiser.partnerapp.BankStamentUpload;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
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

import java.util.ArrayList;
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

public class Bank_available_listAdapter extends RecyclerView.Adapter<Bank_available_listAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Bankitems> items;
    private ArrayList<Bank_available_details_model> items1;
    private String lead_id;
    private AlertDialog progressDialog;
    private LayoutInflater mInflater;


    String Loan_amount,sub_categoryid,transaction_id1,subtask_id,loan_type_id,loan_type,
            payment,applicant_id1;

    public Bank_available_listAdapter(Context context, ArrayList<Bank_available_details_model> items1) {
        this.context = context;
        this.items1 = items1;
        notifyDataSetChanged();
    }

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        progressDialog = new SpotsDialog(context, R.style.Custom);
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.ly_bank_list_elible_bank_analysis, parent, false));

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String Bank_url=items1.get(position).getbank_url();
        final String CAT_TYPE=items1.get(position).getcat_type();
        final String Cat_type_id=items1.get(position).getcat_type_id();
        final String statues=items1.get(position).getstatus();
        final String bank_id=items1.get(position).getbank_id();

        Objs.a.loadPicasso(context,Bank_url,holder.uploaded_yes,holder.progressBarMaterial);
        holder.cat_A.setText(CAT_TYPE);

        if(Cat_type_id.equals("1"))
        {
            holder.cat_A.setBackgroundResource(R.drawable.capsul_button41);
        }else if(Cat_type_id.equals("2"))
        {
            holder.cat_A.setBackgroundResource(R.drawable.but_shape_reject);
        } else
        {
            holder.cat_A.setBackgroundResource(R.drawable.capsul_button412);
        }

        if(statues.equals("1"))
        {
            holder.missing_document.setText("passed in Bank Statement");
            holder.missing_document.setTextColor(Color.parseColor("#3FC58F"));
            holder.done_tick.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_green_tick));
        }else
        {
            holder.done_tick.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_caution));
            holder.missing_document.setText("Missing Documents");
            holder.missing_document.setTextColor(Color.parseColor("#EC9022"));
        }

/*
        if (acchold_name.equals("0")&& acchold_name.equalsIgnoreCase("")){
            holder.names.setText(entitynumber);
            holder.urls.setText(acno);
        }else  {
            holder.names.setText(acchold_name);
            holder.urls.setText(acno);
        }*/

        holder.uploaded_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String bank_id=items1.get(position).getbank_id();

                if (context instanceof BankAnalysis) {
                    ((BankAnalysis)context).Applicant_Status(bank_id);
                }
              /* JSONObject jsonObject =new JSONObject();
                JSONObject J= null;
                try {
                    J =new JSONObject();

                    J.put("bank_id", bank_id);
                    J.put("transaction_id", Pref.getTRANSACTIONID(context));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                progressDialog.show();
                Log.e("bank state rule request", String.valueOf(J));

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.get_bankstaterules, J,
                        new Response.Listener<JSONObject>() {
                            @SuppressLint({"LongLogTag", "ResourceType"})
                            @Override
                            public void onResponse(JSONObject response) {

                                Log.e("rule response", String.valueOf(response));
                                try {
                                    String status = response.getString("status");

                                    TableRow tr_head = new TableRow(context);
                                    tr_head.setId(10);
                                    tr_head.setBackgroundColor(Color.GRAY);
                                    tr_head.setLayoutParams(new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.FILL_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT));

                                    TextView label_date = new TextView(context);
                                    label_date.setId(20);
                                    label_date.setText("Rule Description");
                                    label_date.setTextColor(Color.WHITE);
                                    label_date.setPadding(5, 5, 5, 5);
                                    tr_head.addView(label_date);// add the column to the table row here

                                    TextView label_weight_kg = new TextView(context);
                                    label_weight_kg.setId(21);// define id that must be unique
                                    label_weight_kg.setText("Statues"); // set the text for the header
                                    label_weight_kg.setTextColor(Color.WHITE); // set the color
                                    label_weight_kg.setPadding(5, 5, 5, 5); // set the padding (if required)
                                    tr_head.addView(label_weight_kg); // add the column to the table row here

                                    TextView Fail_Message = new TextView(context);
                                    Fail_Message.setId(21);// define id that must be unique
                                    Fail_Message.setText("Fail Message"); // set the text for the header
                                    Fail_Message.setTextColor(Color.WHITE); // set the color
                                    Fail_Message.setPadding(5, 5, 5, 5); // set the padding (if required)
                                    tr_head.addView(Fail_Message); // add the column to the table row here


                                    holder.tabel_row.addView(tr_head, new TableLayout.LayoutParams(
                                            ViewGroup.LayoutParams.FILL_PARENT,
                                            ViewGroup.LayoutParams.WRAP_CONTENT));

                                    JSONObject jsonObject1=null;
                                    if(status.equals("success"))
                                    {
                                        JSONArray response1 = response.getJSONArray("response");
                                        for(int i = 0; i < response1.length(); i++){

                                            JSONObject J = null;
                                            try {

                                                J = response1.getJSONObject(i);

                                                String rule_type=J.getString("rule_desc");
                                                String fail_message=J.getString("fail_message");
                                                String rule_status=J.getString("rule_status");
                                                TableRow tr = new TableRow(context);
                                                tr.setId(100+i);
                                                tr.setLayoutParams(new ViewGroup.LayoutParams(
                                                        ViewGroup.LayoutParams.FILL_PARENT,
                                                        ViewGroup.LayoutParams.WRAP_CONTENT));

                                                TextView rule_description = new TextView(context);
                                                rule_description.setId(200+i);
                                                rule_description.setText(rule_type);
                                                rule_description.setPadding(2, 0, 5, 0);
                                                tr.addView(rule_description);

                                                TextView fail_message1 = new TextView(context);
                                                fail_message1.setId(300+i);
                                                if(rule_status.equals("1"))
                                                {
                                                    fail_message1.setText("Pass");
                                                    fail_message1.setPadding(2, 0, 5, 0);
                                                    tr.addView(fail_message1);

                                                }else {
                                                    fail_message1.setText("Fail");
                                                    fail_message1.setPadding(2, 0, 5, 0);
                                                    tr.addView(fail_message1);

                                                }


                                                TextView rule_status1 = new TextView(context);
                                                rule_status1.setId(300+i);
                                                rule_status1.setText(fail_message);
                                                rule_status1.setPadding(2, 0, 5, 0);
                                                tr.addView(rule_status1);

                                                holder.tabel_row.addView(tr, new TableLayout.LayoutParams(
                                                        ViewGroup.LayoutParams.FILL_PARENT,
                                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }



                                        }
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                JSONObject jsonObject1 = new JSONObject();


                                // TableLayout prices = (TableLayout) holder.findViewById(R.id.prices);


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
                AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);*/



               // Applicant_Status(bank_id);
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
        AppCompatTextView cat_A,missing_document;
        ImageView uploaded_yes,done_tick;
        ProgressBar progressBarMaterial;
        TableLayout tabel_row,tabel_row1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_A  = (AppCompatTextView) itemView.findViewById(R.id.cat_A);
            uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);
            done_tick  = (ImageView) itemView.findViewById(R.id.done_tick);
            missing_document  = (AppCompatTextView) itemView.findViewById(R.id.missing_document);
            progressBarMaterial = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);
            tabel_row = (TableLayout) itemView.findViewById(R.id.tabel_row);
            tabel_row1 = (TableLayout) itemView.findViewById(R.id.tabel_row1);
        }
    }



}
