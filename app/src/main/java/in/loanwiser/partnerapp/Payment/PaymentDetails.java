package in.loanwiser.partnerapp.Payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Payment_Details_Activity;

public class PaymentDetails extends SimpleActivity {

    Button payment_button,cust_payment_button,send_payment_link1;

    private PopupWindow mPopupWindow;
    PopupWindow popupWindow;
    LinearLayout linearLayout1;
    AppCompatTextView back_button;
    ImageView closebtn;
    AppCompatTextView proceedany,back;

    String payment_option;

    LinearLayout  standard,Custome,send_payment_link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_payment_details);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_payment_details);
        initTools(R.string.pay_ment);
        Intent intent = getIntent();
        payment_option = intent.getStringExtra("payment_option");


        payment_button=findViewById(R.id.payment_button);
        cust_payment_button=findViewById(R.id.cust_payment_button);
        send_payment_link1=findViewById(R.id.send_payment_link1);
        linearLayout1=findViewById(R.id.linearLayout1);
        back_button=findViewById(R.id.back_button);

        standard=findViewById(R.id.standard);
        Custome=findViewById(R.id.Custome);
        send_payment_link=findViewById(R.id.send_payment_link);

        if(payment_option.contains("2"))
        {
            standard.setVisibility(View.VISIBLE);
            Custome.setVisibility(View.GONE);
            send_payment_link.setVisibility(View.GONE);

        }else if(payment_option.contains("3"))
        {
            standard.setVisibility(View.GONE);
            Custome.setVisibility(View.VISIBLE);
            send_payment_link.setVisibility(View.GONE);
        }else  if(payment_option.contains("1"))
        {
            standard.setVisibility(View.GONE);
            Custome.setVisibility(View.GONE);
            send_payment_link.setVisibility(View.VISIBLE);
        }
        payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                startActivity(intent);
            }
               /* linearLayout1.setVisibility(View.GONE);
                LayoutInflater layoutInflater = (LayoutInflater) PaymentDetails.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup2,null);
                closebtn = (ImageView) customView.findViewById(R.id.closebtn);
                proceedany=(AppCompatTextView)customView.findViewById(R.id.proceedany);
                back=(AppCompatTextView)customView.findViewById(R.id.back);



                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //display the popup window
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        linearLayout1.setVisibility(View.VISIBLE);

                    }
                });

                proceedany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                        startActivity(intent);
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }*/
            //
        });

        cust_payment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                startActivity(intent);
            }
               /* linearLayout1.setVisibility(View.GONE);
                LayoutInflater layoutInflater = (LayoutInflater) PaymentDetails.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup2,null);
                closebtn = (ImageView) customView.findViewById(R.id.closebtn);
                proceedany=(AppCompatTextView)customView.findViewById(R.id.proceedany);
                back=(AppCompatTextView)customView.findViewById(R.id.back);



                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //display the popup window
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        linearLayout1.setVisibility(View.VISIBLE);

                    }
                });

                proceedany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                        startActivity(intent);
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }*/
            //
        });

        send_payment_link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                startActivity(intent);
            }
               /* linearLayout1.setVisibility(View.GONE);
                LayoutInflater layoutInflater = (LayoutInflater) PaymentDetails.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.popup2,null);
                closebtn = (ImageView) customView.findViewById(R.id.closebtn);
                proceedany=(AppCompatTextView)customView.findViewById(R.id.proceedany);
                back=(AppCompatTextView)customView.findViewById(R.id.back);



                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //display the popup window
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                closebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        linearLayout1.setVisibility(View.VISIBLE);

                    }
                });

                proceedany.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PaymentDetails.this, Payment_Details_Activity.class);
                        startActivity(intent);
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }*/
            //
        });

    }
}
