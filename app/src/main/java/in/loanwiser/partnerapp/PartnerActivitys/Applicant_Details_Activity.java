package in.loanwiser.partnerapp.PartnerActivitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;

public class Applicant_Details_Activity extends SimpleActivity {


    private Context mCon = this;
    Toolbar toolbar1;
    DrawerLayout drawerLayout;
    View drawerView;
    private CardView homeloan,loanaganstpropert,personal_loan,Businessloan,vehicle_loan;
    private FloatingActionButton float_chat;

    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Applicant_Details_Activity.class.getSimpleName();
    private AlertDialog progressDialog;
    String email,username,mobileno,id,step_status,status;
    String applicant_id,sub_taskid,transaction_id;
    AppCompatButton logout,leads_float_chat;
    AppCompatTextView no_leads_data;
    LinearLayout Ly_no_leads_data;
    LinearLayout Ly_contact,Ly_chat,Ly_Profile;
    MenuItem chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_applicant__details_);
        setContentView(R.layout.activity_simple);
        //Loantype = getIntent().getStringExtra("EXTRA_SESSION_ID");

       // Log.d("The extration data",Loantype);
        Objs.a.setStubId(this, R.layout.activity_applicant__details_);
        initTools(R.string.listapp1);

        progressDialog = new SpotsDialog(this, R.style.Custom);
        homeloan = findViewById(R.id.hl);
        loanaganstpropert = findViewById(R.id.lap);
        personal_loan = findViewById(R.id.PL);
        Businessloan = findViewById(R.id.Bl);
        vehicle_loan = findViewById(R.id.vl);


        homeloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Loantype = "1";
                String sub_categoryid = "1";
                String Loantype_name = "Home Loan";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Pref.putLoanTypename(mCon,Loantype_name);
                //if(Loantype !="Applied") {
                Intent intent = new Intent(Applicant_Details_Activity.this, MainActivity_Add_Lead.class);
                startActivity(intent);
                finish();

            }
        });

        loanaganstpropert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Loantype = "1";
                String sub_categoryid = "2";
                String Loantype_name = "Loan Against Property";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Pref.putLoanTypename(mCon,Loantype_name);

                Intent intent = new Intent(Applicant_Details_Activity.this, MainActivity_Add_Lead.class);
                startActivity(intent);
                finish();
            }
        });
        personal_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Loantype = "2";
                String sub_categoryid = "3";
                String Loantype_name = "Personal Loan [Unsecured]";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypename(mCon,Loantype_name);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Intent intent = new Intent(Applicant_Details_Activity.this, MainActivity_Add_Lead1.class);
                startActivity(intent);
                finish();
            }
        });

        Businessloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Loantype = "2";
                String sub_categoryid = "4";
                String Loantype_name = "Business Loan [Unsecured]";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypename(mCon,Loantype_name);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Intent intent = new Intent(Applicant_Details_Activity.this, MainActivity_Add_Lead1.class);
                startActivity(intent);
                finish();
            }
        });



        vehicle_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Loantype = "3";
                String sub_categoryid = "5";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Intent intent = new Intent(Applicant_Details_Activity.this, MainActivity_Vehicle_Add_Lead1.class);
                startActivity(intent);
                finish();
            }
        });

        font();

    }

    private void font()
    {
        Objs.a.OutfitNormalFontStyle(mCon, R.id.hli);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.lapi);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.pli);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.bli);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.vli);
    }
    @Override
    public void onBackPressed() {
        Objs.ac.StartActivity(mCon, Dashboard_Activity.class);
        finish();
        super.onBackPressed();
    }
}
