package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

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
import in.loanwiser.partnerapp.BankStamentUpload.Doc_ImageView_pay_structur;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;

public class AcademeParent_adapter extends RecyclerView.Adapter<AcademeParent_adapter.ViewHolder> {


    Context context;
    List<Acadmytitle> acadmytitleList;
    List<AcadmyDetails> acadmyDetails;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req",video_status,video_url;
    private android.app.AlertDialog progressDialog;
    String thumbnail,title,description,pdf_url;
    AcademeParent_adapter academeParent_adapter;
    AcademeChild_adapter academeChild_adapter;
    RecyclerView childrecycleview;
    LinearLayoutManager layoutManager
            = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);



    public AcademeParent_adapter(Context context, List<Acadmytitle> acadmytitleList) {
        this.context = context;
        this.acadmytitleList = acadmytitleList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.academy_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Acadmytitle acadmytitle=acadmytitleList.get(position);
        holder.titleTextView.setText(acadmytitle.getName());
        boolean isExpanded = acadmytitleList.get(position).isExpanded();
        holder.expandableLayoutlay.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

        holder.arrowimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isExpanded = acadmytitleList.get(position).isExpanded();

                Log.e("the position", String.valueOf(position));
                holder.expandableLayoutlay.setVisibility(isExpanded ? View.VISIBLE : View.GONE);


                Acadmytitle acadmytitle1 = acadmytitleList.get(position);
                acadmytitle1.setExpanded(!acadmytitle1.isExpanded());
                notifyItemChanged(position);
                holder.expandableLayoutlay.setVisibility(View.VISIBLE);
                String title_name =  acadmytitle1.getName();

                LoanwiserAcadrmys(title_name);

            }


        });

        holder.arrowimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isExpanded = acadmytitleList.get(position).isExpanded();

                Log.e("the position", String.valueOf(position));
                holder.expandableLayoutlay.setVisibility(isExpanded ? View.VISIBLE : View.GONE);


                Acadmytitle acadmytitle1 = acadmytitleList.get(position);
                acadmytitle1.setExpanded(!acadmytitle1.isExpanded());
                notifyItemChanged(position);
                holder.expandableLayoutlay.setVisibility(View.VISIBLE);
                String title_name =  acadmytitle1.getName();

                LoanwiserAcadrmys(title_name);

            }


        });


    }

    @Override
    public int getItemCount() {
        return acadmytitleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView titleTextView;
        AppCompatImageView arrowimg,arrowimg1;
        LinearLayout expandableLayoutlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView=itemView.findViewById(R.id.titleTextView);
            arrowimg=itemView.findViewById(R.id.arrowimg);
            arrowimg1=itemView.findViewById(R.id.arrowimg1);
            expandableLayoutlay=itemView.findViewById(R.id.expandableLayoutlay);
            childrecycleview=itemView.findViewById(R.id.childrecycleview);



        }


    }


    private void LoanwiserAcadrmys(final String title_name) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;

        String data  = String.valueOf(J);
        Log.d("Request :", data);
        progressDialog = new SpotsDialog(context, R.style.Custom);

        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.ACADEMY_DETAILS, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        acadmyDetails=new ArrayList<>();
                        AcademeChild_adapter academeChild_adapter=new AcademeChild_adapter(context,acadmyDetails);

                        try{
                            Iterator<String> keys= response.keys();
                            while (keys.hasNext())
                            {
                                String keyValue = (String)keys.next();
                                Log.i("TAG", "onResponse:keyvalue "+keyValue);
                                String valueString = response.getString(keyValue);
                                Log.i("TAG", "onResponse:keys "+valueString);
                                JSONObject innerJObject = response.getJSONObject(keyValue);
                                Log.i("TAG", "onResponse: length"+innerJObject.length());
                                JSONArray jsonArray=innerJObject.getJSONArray("data");
                                String name = innerJObject.getString("name");
                                // acadmeytitlelist.add(new Acadmytitle(name));
                                //academeParent_adapter.notifyDataSetChanged();
                                if(title_name.equals(name)) {

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                        title = jsonObject1.getString("title");
                                        description = jsonObject1.getString("description");
                                        pdf_url = jsonObject1.getString("pdf_url");
                                        thumbnail = jsonObject1.getString("thumbnail");
                                        video_status=jsonObject1.getString("video_status");
                                        video_url=jsonObject1.getString("video_url");

                                        Log.i("TAG", "onResponse:title " + title);
                                        Log.i("TAG", "onResponse:description " + description);
                                        Log.i("TAG", "onResponse:pdf_url " + pdf_url);
                                        acadmyDetails.add(new AcadmyDetails(thumbnail, title, description, pdf_url,video_status,video_url));
                                        Log.i("TAG", "onResponse: detder" + acadmyDetails);
                                        Log.i("TAG", "onResponse:det " + acadmyDetails);


                                    }
                                    academeChild_adapter = new AcademeChild_adapter(context, acadmyDetails);
                                    childrecycleview.setLayoutManager(layoutManager);
                                    childrecycleview.setAdapter(academeChild_adapter);
                                }

                                           /* Log.i("TAG", "onResponse:det "+acadmyDetails);
                                           academeChild_adapter=new AcademeChild_adapter(context,acadmyDetails);
                                            childrecycleview.setLayoutManager(layoutManager);
                                            childrecycleview.setAdapter(academeParent_adapter);
                                            Log.i("TAG", "onResponse:name "+name);*/
                            }

                        }catch (JSONException e){
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();
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

    class AcademeChild_adapter extends RecyclerView.Adapter<in.loanwiser.partnerapp.Step_Changes_Screen.AcademeChild_adapter.ViewHolder> {
        Context context;
        List<AcadmyDetails> acadmyDetailsList;

        public AcademeChild_adapter(Context context, List<AcadmyDetails> acadmyDetailsList) {
            this.context = context;
            this.acadmyDetailsList = acadmyDetailsList;
        }



        @NonNull
        @Override
        public in.loanwiser.partnerapp.Step_Changes_Screen.AcademeChild_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.academychild_layout, parent, false);
            return new in.loanwiser.partnerapp.Step_Changes_Screen.AcademeChild_adapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull in.loanwiser.partnerapp.Step_Changes_Screen.AcademeChild_adapter.ViewHolder holder, int position) {

            AcadmyDetails acadmyDetails=acadmyDetailsList.get(position);
            holder.titletxt.setText(acadmyDetails.getTitle());
            holder.destxt.setText(acadmyDetails.getDescription());

            String logo  = acadmyDetailsList.get(position).getThumbnail();
            final String pdfurl=acadmyDetailsList.get(position).getPdf_url();
            String pdf_status=acadmyDetailsList.get(position).getPdf_status();
            video_status=acadmyDetailsList.get(position).getVideo_status();
            final String videourl=acadmyDetailsList.get(position).getVideo_url();
            Log.i("TAG", "onBindViewHolder: logo"+logo);
            Log.i("TAG", "onBindViewHolder:videourl"+videourl);
            Log.i("TAG", "onBindViewHolder:videostatus"+video_status);
            Picasso.with(context).load(logo).into(holder.imageview);

            holder.viewvideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (video_status.equalsIgnoreCase("0")){
                        ErrorStatus();
                    }else {
                        // watchYoutubeVideo(context,videourl);
                        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(videourl));
                        try {
                            context.startActivity(webIntent);
                        } catch (ActivityNotFoundException ex) {
                        }
                    }
                }
            });

            holder.viewpdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                             /*   Intent intent=new Intent(context,Doc_ImageView_pay_structur.class);
                                intent.putExtra("key",pdfurl);
                                intent.putExtra("keymodel","check");
                                context.startActivity(intent);*/

                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
                    context.startActivity(browserIntent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return acadmyDetailsList.size();
        }

        class ViewHolders extends RecyclerView.ViewHolder {
            AppCompatTextView titletxt,destxt;
            AppCompatImageView imageview;
            AppCompatButton viewvideo,viewpdf;
            public ViewHolders(@NonNull View itemView) {
                super(itemView);
                titletxt=itemView.findViewById(R.id.titletxt);
                destxt=itemView.findViewById(R.id.destxt);
                imageview=itemView.findViewById(R.id.imageview);
                viewvideo=itemView.findViewById(R.id.viewvideo);
                viewpdf=itemView.findViewById(R.id.viewpdf);


            }
        }
    }

    public static void watchYoutubeVideo(Context context, String video_url){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video_url));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + video_url));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    private void ErrorStatus() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.academic_soon);
        //  dialog.getWindow().setLayout(display.getWidth() * 90 / 100, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        AppCompatTextView bankstatement_message=(AppCompatTextView) dialog.findViewById(R.id.bankstatement_message);
        Button cancelbtn = (Button) dialog.findViewById(R.id.cancelbtn);
        Button submitbtn = (Button) dialog.findViewById(R.id.submitbtn);
        Button submitbtn1 = (Button) dialog.findViewById(R.id.submitbtn1);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        if (!dialog.isShowing()) {
            dialog.show();
        }
    }



}


