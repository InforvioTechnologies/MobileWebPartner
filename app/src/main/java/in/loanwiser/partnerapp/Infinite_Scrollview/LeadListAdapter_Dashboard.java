package in.loanwiser.partnerapp.Infinite_Scrollview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;


import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.PartnerActivitys.Home;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Screen_revamp;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Screen_revamp_Pl_BL;


import static adhoc.app.applibrary.Config.AppUtils.Objs.a;

public class LeadListAdapter_Dashboard extends RecyclerView.Adapter<LeadListAdapter_Dashboard.CustomViewHolder> {

    private Context context;
    private List<Lead_item> posts = new ArrayList<>();
    JSONObject J;
    public LeadListAdapter_Dashboard(Context context){
        this.context = context;
    }

    //  private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    public void addPosts(List<Lead_item> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.leaddetails, parent, false));
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

        AppCompatTextView type,doc_steps,doc_status,font1,font2,loantype,assigned;
        AppCompatTextView Statues_update_dot,
                Lead_Name,loan_amount,app_id,loan_type,payment_plane,step_com,statues_new,status_Ask,Lead_crated_;
        ImageView v_Image;
        ProgressBar progressBar;
        AppCompatButton appCompatButtonSelect,add_notes,pipline,archive;
        AppCompatImageView loan_type_image;

        LinearLayout Over_all,ly_question,cobrand;
        View view;
        String loantype1,statues12,step_status,transaction_id,id,id1;
        AppCompatTextView paymentplanlabel,completeduptolabel,labellead,ask,loanfrom,loanfromtxt;
        AppCompatButton Statues_update_view,Statues_update_view1;
        public CustomViewHolder(View view) {
            super(view);
            Lead_Name  = (AppCompatTextView) itemView.findViewById(R.id.Lead_Name);
            loan_amount  = (AppCompatTextView) itemView.findViewById(R.id.loan_amount);
            app_id  = (AppCompatTextView) itemView.findViewById(R.id.app_id);
            loan_type  = (AppCompatTextView) itemView.findViewById(R.id.loan_type);
            payment_plane  = (AppCompatTextView) itemView.findViewById(R.id.payment_plane);
            step_com  = (AppCompatTextView) itemView.findViewById(R.id.step_com);
            Statues_update_dot  = (AppCompatTextView) itemView.findViewById(R.id.Statues_update_dot);
            statues_new  = (AppCompatTextView) itemView.findViewById(R.id.statues_new);
            Statues_update_view  = (AppCompatButton) itemView.findViewById(R.id.Statues_update_view);
            Statues_update_view1  = (AppCompatButton) itemView.findViewById(R.id.Statues_update_view1);
            status_Ask  = (AppCompatTextView) itemView.findViewById(R.id.status_Ask);

            Lead_crated_  = (AppCompatTextView) itemView.findViewById(R.id.Lead_crated_);

            paymentplanlabel  = (AppCompatTextView) itemView.findViewById(R.id.paymentplanlabel);
            completeduptolabel  = (AppCompatTextView) itemView.findViewById(R.id.completeduptolabel);
            labellead  = (AppCompatTextView) itemView.findViewById(R.id.labellead);
            ask  = (AppCompatTextView) itemView.findViewById(R.id.ask);
            loanfrom  = (AppCompatTextView) itemView.findViewById(R.id.loanfrom);
            loanfromtxt  = (AppCompatTextView) itemView.findViewById(R.id.loanfromtxt);

            Over_all = (LinearLayout) itemView.findViewById(R.id.Over_all);
            cobrand = (LinearLayout) itemView.findViewById(R.id.cobrand);

            Typeface font = Typeface.createFromAsset(context.getAssets(), "segoe_ui.ttf");
            paymentplanlabel.setTypeface(font);
            completeduptolabel.setTypeface(font);
            labellead.setTypeface(font);
            ask.setTypeface(font);
            loanfrom.setTypeface(font);
            loanfromtxt.setTypeface(font);
            Lead_Name.setTypeface(font);
            loan_amount.setTypeface(font);
            app_id.setTypeface(font);
            loan_type.setTypeface(font);
            payment_plane.setTypeface(font);
            step_com.setTypeface(font);
            Statues_update_dot.setTypeface(font);
            statues_new.setTypeface(font);
            status_Ask.setTypeface(font);
            Statues_update_view.setTypeface(font);
            Statues_update_view1.setTypeface(font);
            Lead_crated_.setTypeface(font);
        }

        public void bindPost(final Lead_item post) {

         /*   type.setText(a.capitalize(post.getid()+" "+ "( "+post.getusername()+" "+ post.getmobileno()+" )"));
            loantype.setText(post.getloan_typename()); */

            //doc_steps.setText(post.getstep_status());

            String statues = post.getstatus_disp();

          //  String from_cobrand = post.getfrom_cobrand();
            String loan_type_id = post.getstatus_disp();
            Lead_Name.setText(post.getusername());
           String loan_amount_name = post.getloan_typename();
            String from_cobrand1 = post.getfrom_cobrand();
            if(loan_amount_name.equals("null"))
            {
                loan_amount.setText("Co-Branded");
                loan_type.setText("Please Complete from your side");

            }else
            {
                loan_amount.setText("\u20B9"+post.getloan_amount());
                loan_type.setText(post.getloan_typename());
            }
            if(from_cobrand1.equals("1"))
            {
                cobrand.setVisibility(View.VISIBLE);
            }else
            {
                cobrand.setVisibility(View.GONE);
            }
           // loan_amount.setText("\u20B9"+post.getloan_amount());

            String createdat = post.getcreated_at();
            app_id.setText(post.getid());
            Lead_crated_.setText(parseDateToddMMyyyy(createdat));
           // loan_type.setText(post.getloan_typename());
            step_com.setText(post.getcomp_step());
            statues_new.setText(post.getstatus_disp());
            payment_plane.setText(post.getpayment_plan());
          String  pending_asks_count = post.getpending_asks_count();
            //
            //  Statues_update_dot.setText(post.getcolor_code());
            //  payment_plane.setText(post.getloan_typename());

            String color_code = post.getcolor_code();
            // loantype1 = post.getloan_typename();
            //  field_status = post.getfield_status();
            step_status = post.getstep_status();

            step_status = post.getstep_status();
            id = post.getid();
            id1 = post.getid1();
            transaction_id = post.gettransaction_id();
            status_Ask.setText("Pending Ask"+"("+pending_asks_count+")");
/*
            if(loantype1.contains("null")) {
                loantype.setVisibility(View.GONE);
            }*/

/*
            if(color_code.equals("1"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#FF9200"));
                Statues_update_view.setVisibility(View.VISIBLE);
                Statues_update_view1.setVisibility(View.VISIBLE);

            } else if(color_code.equals("2"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#F9F338"));
            }else if(color_code.equals("3"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#1592E6"));
            }else if(color_code.equals("4"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#15CE00"));
            }else if(color_code.equals("5"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#012B5D"));
            }else
            {
                Statues_update_dot.setTextColor(Color.parseColor("#E3434A"));
            }*/
            if(statues.equals("Pending under you"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#FF9200"));
                statues_new.setTextColor(Color.parseColor("#FF9200"));

                Statues_update_view.setVisibility(View.VISIBLE);
                Statues_update_view.setText("Complete Now");
                Statues_update_view1.setVisibility(View.GONE);

            } else if(statues.equals("Submitted to Loanwiser"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#1592E6"));
                statues_new.setTextColor(Color.parseColor("#1592E6"));
                Statues_update_view.setVisibility(View.GONE);
                Statues_update_view1.setText("View");
                Statues_update_view1.setVisibility(View.VISIBLE);

            }else if(statues.equals("Sanctioned"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#15CE00"));
                statues_new.setTextColor(Color.parseColor("#15CE00"));
                Statues_update_view.setVisibility(View.GONE);
                Statues_update_view1.setText("View");
                Statues_update_view1.setVisibility(View.VISIBLE);
            }else if(statues.equals("Disbursed"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#D05AE9"));
                statues_new.setTextColor(Color.parseColor("#D05AE9"));
                Statues_update_view.setVisibility(View.GONE);
                Statues_update_view1.setText("View");
                Statues_update_view1.setVisibility(View.VISIBLE);

            }else if(statues.equals("Sent to bank"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#00D1D1"));
                statues_new.setTextColor(Color.parseColor("#00D1D1"));
                Statues_update_view.setVisibility(View.GONE);
                Statues_update_view1.setText("View");
                Statues_update_view1.setVisibility(View.VISIBLE);

            }else
            {
                Statues_update_dot.setTextColor(Color.parseColor("#E3434A"));
                statues_new.setTextColor(Color.parseColor("#E3434A"));
                Statues_update_view.setVisibility(View.GONE);
                Statues_update_view1.setText("View");
                Statues_update_view1.setVisibility(View.VISIBLE);
            }
            String from_cobrand = post.getfrom_cobrand();

          /*  if(from_cobrand.equals("1"))
            {
                Statues_update_dot.setTextColor(Color.parseColor("#FF9200"));
                statues_new.setTextColor(Color.parseColor("#FF9200"));
                statues_new.setText("post.getstatus_disp()");
                holder.Statues_update.setText("Cobranded website");
            }*/
            Statues_update_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    id1 = post.getid1();
                    String from_cobrand = post.getfrom_cobrand();
                    String mobile_cobrank = post.getcobrand_mobile();
                    if(step_status.contains("Rejected")|| step_status.contains("Auto Rejected"))
                    {
                        Log.e("the lead List","intiated ");
                      //  Objs.a.showToast(context, "This Lead is Rejected");

                        if (context instanceof Dashboard_Activity) {
                            ((Dashboard_Activity)context).Applicant_Status(id1,step_status);
                        }
                       /* Toast.makeText(context,"This Lead is Rejected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, Home.class);
                        context.startActivity(intent);
                        Toast.makeText(context,"This Lead is Rejected", Toast.LENGTH_SHORT).show();*/

                    }
                    else
                    {

                        if(from_cobrand.equals("1"))
                        {
                            if(mobile_cobrank.equals("no"))
                            {

                              //  String loan_type  = items.get(position).getloan_type();
                                String loan_type  = post.getloan_typename();
                                String loan_type_id = post.getloan_type();
                                Pref.putLoanType(context,loan_type_id);
                                if(loan_type.equals("Personal Loan (Salaried)")||loan_type.equals("Business Loan (Self Employed)"))
                                {
                                    Intent intent = new Intent(context, Viability_Screen_revamp_Pl_BL.class);
                                    context.startActivity(intent);
                                }else
                                {
                                    Intent intent = new Intent(context, Viability_Screen_revamp.class);
                                    context.startActivity(intent);

                                }

                            }else
                            {
                                Intent intent = new Intent(context, Applicant_Details_Activity.class);
                                context.startActivity(intent);
                            }
                        }else
                        {
                            if (context instanceof Dashboard_Activity) {
                                ((Dashboard_Activity)context).Applicant_Status(id1,step_status);
                            }
                        }


                        // Applicant_Status(id);
                    }


                }
            });
            Statues_update_view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    id1 = post.getid1();
                    String from_cobrand = post.getfrom_cobrand();
                    String mobile_cobrank = post.getcobrand_mobile();
                    if(step_status.contains("Rejected")|| step_status.contains("Auto Rejected"))
                    {
                        Log.e("the lead List","intiated ");
                        //  Objs.a.showToast(context, "This Lead is Rejected");

                        if (context instanceof Dashboard_Activity) {
                            ((Dashboard_Activity)context).Applicant_Status(id1,step_status);
                        }
                       /* Toast.makeText(context,"This Lead is Rejected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, Home.class);
                        context.startActivity(intent);
                        Toast.makeText(context,"This Lead is Rejected", Toast.LENGTH_SHORT).show();*/

                    }
                    else
                    {

                        if(from_cobrand.equals("1"))
                        {
                            if(mobile_cobrank.equals("no"))
                            {

                                //  String loan_type  = items.get(position).getloan_type();
                                String loan_type  = post.getloan_typename();
                                String loan_type_id = post.getloan_type();
                                Pref.putLoanType(context,loan_type_id);
                                if(loan_type.equals("Personal Loan (Salaried)")||loan_type.equals("Business Loan (Self Employed)"))
                                {
                                    Intent intent = new Intent(context, Viability_Screen_revamp_Pl_BL.class);
                                    context.startActivity(intent);
                                }else
                                {
                                    Intent intent = new Intent(context, Viability_Screen_revamp.class);
                                    context.startActivity(intent);

                                }

                            }else
                            {
                                Intent intent = new Intent(context, Applicant_Details_Activity.class);
                                context.startActivity(intent);
                            }
                        }else
                        {
                            if (context instanceof Dashboard_Activity) {
                                ((Dashboard_Activity)context).Applicant_Status(id1,step_status);
                            }
                        }


                        // Applicant_Status(id);
                    }


                }
            });

            // Objs.a.OutfitNormalFontStyle(mCon, R.id.doc_typename_all);
            // Objs.a.OutfitNormalFontStyle(mCon, R.id.doc_steps);
            //    a.NewNormalFontStyle(context,type);
            //   a.NewNormalFontStyle(context,doc_steps);
            //   a.NewNormalFontStyle(context,loantype);
            //  a.NewNormalFontStyle(context,assigned);

        }



        public String parseDateToddMMyyyy(String time) {
            String inputPattern = "yyyy-MM-dd";
            String outputPattern = "dd-MM-yyyy";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
            java.util.Date date = null;
            String str = null;
            try {
                date = inputFormat.parse(time);
                str = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}
