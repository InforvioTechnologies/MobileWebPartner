package in.loanwiser.partnerapp.PartnerActivitys;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;

public class Offers_list extends SimpleActivity {

    private Context mCon = this;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Offers_list.class.getSimpleName();
    private AlertDialog progressDialog;
    String transaction_id,offer_id_new,transaction_id_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_offers_list);
        initTools(R.string.offer);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        transaction_id =  Objs.a.getBundle(this, Params.transaction_id);
        Applicant_Status(transaction_id);
    }

    private void Applicant_Status(final String id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.transaction_id, id);
            //  J.put(Params.transaction_id, "2620");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.OFFER_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.getBoolean("status")) {

                                JSONArray ja = response.getJSONArray("offer_result");

                                if (ja.length()>0){
                                    //  Objs.a.showToast(mCon, String.valueOf(ja));
                                    // Log.d("Offer result", String.valueOf(ja));
                                    setAdapter(ja);

                                }else {
                                    Objs.a.ShowHideNoItems(mCon,true);
                                }

                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Falls data",Toast.LENGTH_SHORT).show();
                            }


                        }catch (JSONException e) {
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

    private void setAdapter(JSONArray ja) {

        ListItemAdapter adapter = new ListItemAdapter(mCon,ja);
        Objs.a.getRecyleview(this).setAdapter(adapter);
    }


    //Offer Adapted class.....
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
                    .inflate(R.layout.ly_offer_list, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                String rupee = getResources().getString(R.string.Rs);
                J = getItem(position);



                Objs.a.loadPicasso(mCon,J.getString("bank_logo"),holder.banklogo,holder.progressBarMaterial);


                holder.skeem_name.setText(J.getString("scheme_name"));
                Objs.a.NewNormalFontStyle(mCon,holder.skeem_name);

                holder.pmay_loan.setText(Objs.a.capitalize( rupee + J.getString("pmay_loan")));
                Objs.a.NewNormalFontStyle(mCon,holder.pmay_loan);

                holder.pmay_emi.setText(Objs.a.capitalize(rupee + J.getString("pmay_emi")));
                Objs.a.NewNormalFontStyle(mCon,holder.pmay_emi);

                holder.maxloan.setText(Objs.a.capitalize(rupee + J.getString("max_loan")));
                Objs.a.NewNormalFontStyle(mCon,holder.maxloan);

                holder.ispmayeligible.setText(Objs.a.capitalize( J.getString("is_pmayeligible")));
                Objs.a.NewNormalFontStyle(mCon,holder.ispmayeligible);

                holder.interestrate.setText(Objs.a.capitalize( J.getString("interest_rate")));
                Objs.a.NewNormalFontStyle(mCon,holder.interestrate);

                holder.oc_required.setText(Objs.a.capitalize( rupee +  J.getString("oc_required")));
                Objs.a.NewNormalFontStyle(mCon,holder.oc_required);

                holder.affordable_price.setText(Objs.a.capitalize(rupee + J.getString("affordable_price")));
                Objs.a.NewNormalFontStyle(mCon,holder.affordable_price);

                if(J.getString("loan_status").equals("1")) {
                    // holder.apply_status.setText(Objs.a.capitalize(J.getString("loan_status")));
                    // Objs.a.NewNormalFontStyle(mCon, holder.apply_status);
                    holder.apply_status.setText("Track Status");
                    holder.apply_status.setTextColor(Color.parseColor("#ffffff"));
                    Objs.a.NewNormalFontStyle(mCon, holder.apply_status);
                    holder.apply_status.setEnabled(true);
                }
                else
                {
                    holder.apply_status.setText("Inprocess");
                    Objs.a.NewNormalFontStyle(mCon, holder.apply_status);
                    holder.apply_status.setEnabled(false);
                }

                holder.apply_status.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);
                        try {
                            offer_id_new = J.getString(Params.id);
                            transaction_id_new = J.getString(Params.transaction_id);
                            //  Objs.a.showToast(mCon, offer_id_new +"\n"+ transaction_id);
                            Objs.ac.StartActivityPutExtra(mCon, Track_Status.class,
                                    Params.transaction_id,transaction_id_new, Params.offer_id,offer_id_new);
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

            AppCompatTextView skeem_name,pmay_loan,pmay_emi,maxloan,ispmayeligible,interestrate,oc_required,
                    affordable_price,apply_status;
            CardView card_view;
            View view;
            ImageView banklogo;
            ProgressBar progressBarMaterial;



            public ViewHolder(View itemView) {
                super(itemView);

                card_view =(CardView) itemView.findViewById(R.id.card_view);
                skeem_name =(AppCompatTextView) itemView.findViewById(R.id.S_name);
                banklogo = (ImageView) itemView.findViewById(R.id.bank_logo);
                pmay_loan =(AppCompatTextView) itemView.findViewById(R.id.Pmay_loan);
                pmay_emi =(AppCompatTextView)itemView.findViewById(R.id.S_emi);
                maxloan  =(AppCompatTextView)itemView.findViewById(R.id.S_eli);
                ispmayeligible = (AppCompatTextView)itemView.findViewById(R.id.is_Pmayeligible);
                interestrate = (AppCompatTextView)itemView.findViewById(R.id.S_interest);
                oc_required = (AppCompatTextView)itemView.findViewById(R.id.Oc_Required);
                affordable_price =(AppCompatTextView)itemView.findViewById(R.id.Affordable_price);
                apply_status = (AppCompatTextView)itemView.findViewById(R.id.Apply_statues);

                progressBarMaterial = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);




                /*class_name  = (AppCompatTextView) itemView.findViewById(R.id.class_name);
                image_doc  = (ImageView) itemView.findViewById(R.id.image_doc);
                uploaded_yes  = (ImageView) itemView.findViewById(R.id.uploaded_yes);

                card_view_class_name  = (CardView) itemView.findViewById(R.id.card_view_class_name);
*/
            }
        }
    }
}
