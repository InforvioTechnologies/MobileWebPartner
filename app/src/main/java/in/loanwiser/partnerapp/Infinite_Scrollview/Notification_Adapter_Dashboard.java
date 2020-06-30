package in.loanwiser.partnerapp.Infinite_Scrollview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
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
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.Push_Notification.Push_Notification_List;
import in.loanwiser.partnerapp.Push_Notification.Timeago;
import in.loanwiser.partnerapp.R;

import static java.sql.Types.TIMESTAMP;

public class Notification_Adapter_Dashboard extends RecyclerView.Adapter<Notification_Adapter_Dashboard.CustomViewHolder> {

    private Context context;
    private List<Notification_item> posts = new ArrayList<>();
    JSONObject J;
    public Notification_Adapter_Dashboard(Context context){
        this.context = context;
    }
    String user_id,btn_invoke,step_status,Notification_id,
            status;

    Timeago timeAgo2 = new Timeago();
    private ProgressBar progressBar;
    private AlertDialog progressDialog;
  //  private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public void addPosts(List<Notification_item> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.ly_notification_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

         holder.bindPost(posts.get(position));

    }

    @Override
    public int getItemCount() {
        return posts.size();
       // return 6;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView title,message,time_ago,font1,font2,loantype,assigned;
        AppCompatTextView Statues_update_dot,
                Lead_Name,loan_amount,app_id,loan_type,payment_plane,step_com,statues_new;
        ImageView v_Image;
        AppCompatImageView  read_msg;
        LinearLayout Over_all;


        public CustomViewHolder(View view) {
            super(view);
            title  = (AppCompatTextView) itemView.findViewById(R.id.title);
            message  = (AppCompatTextView) itemView.findViewById(R.id.message);
            time_ago  = (AppCompatTextView) itemView.findViewById(R.id.time_ago);
            Over_all  = (LinearLayout) itemView.findViewById(R.id.Over_all);
            read_msg  = (AppCompatImageView) itemView.findViewById(R.id.read_msg);
            progressDialog = new SpotsDialog(context, R.style.Custom);
        }

        public void bindPost(Notification_item post) {


            title.setText(post.gettitle());
            message.setText(post.getmessage());

            user_id = post.getuser_id();
            btn_invoke = post.getbtn_invoke();
            status = post.getstatus();
            Log.e("status",status);

            Notification_id = post.getnotification_id();
           // String date = "2011-10-06T12:00:00-08:00";
            String MyFinalValue = timeAgo2.covertTimeToText(post.getcreated_at());
          //  String MyFinalValue = timeAgo2.covertTimeToText(date);
            time_ago.setText(MyFinalValue);
           // long timeInMillis = System.currentTimeMillis();

            if (status.equals("1"))
            {
                read_msg.setImageResource(R.drawable.ic_right_done);
            }else
            {
                read_msg.setImageResource(R.drawable.ic_right_notdone);
            }

            Over_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.e("this is working fine","works fine");
                  /* if( btn_invoke.equals("1"))
                    {
                        if (context instanceof Dashboard_Activity) {
                            ((Dashboard_Activity)context).Applicant_Status(user_id,step_status);
                        }
                    }*/

                    Applicant_Status();
                }
            });

        }




    }

    public void Applicant_Status() {


        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("notification_id", Notification_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();
        Log.e("Notification_id", String.valueOf(J));

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.Notification_Update, J,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("Notification_id", String.valueOf(response));
                        try {

                            String status = response.getString("status");

                            if(status.contains("success"))
                            {
                                if (context instanceof Push_Notification_List) {
                                    ((Push_Notification_List)context).Applicant_Status(user_id,step_status);
                                }
                            }else {

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
