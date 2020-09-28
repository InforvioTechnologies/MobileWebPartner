package in.loanwiser.partnerapp.PartnerActivitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Partner_Lead_OTP_Verification.Partner_Lead_OTP_Verification;
import in.loanwiser.partnerapp.Partner_Statues.DashBoard_new;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Lead_Crration_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Step_Completion_Activity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Step_Completion_Screen;

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

               /* String Loantype = "1";
                String sub_categoryid = "1";
                String Loantype_name = "Home Loan";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Pref.putLoanTypename(mCon,Loantype_name);
                //if(Loantype !="Applied") {
                Intent intent = new Intent(Applicant_Details_Activity.this, Partner_Lead_OTP_Verification.class);
                startActivity(intent);
                finish();*/

                String Loantype = "1";
                String sub_categoryid = "1";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                String Loantype_name = "Home Loan";
                Pref.putLoanTypename(mCon,Loantype_name);

                Intent intent = new Intent(Applicant_Details_Activity.this, Step_Completion_Screen.class);
                startActivity(intent);
                //finish();

            }
        });

        loanaganstpropert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* String Loantype = "1";
                String sub_categoryid = "2";
                String Loantype_name = "Loan Against Property";
                Pref.putLoanType(mCon,Loantype);

                String Loancat_name = "Secured Loan (HL/LAP)";
                Pref.putLoanCat_Name(mCon,Loancat_name);

                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Pref.putLoanTypename(mCon,Loantype_name);

                Intent intent = new Intent(Applicant_Details_Activity.this, Partner_Lead_OTP_Verification.class);
                startActivity(intent);
                finish();*/

                String Loantype = "1";
                Pref.putLoanType(mCon,Loantype);
                String Loantype_name = "Loan Against Property";
                Pref.putLoanTypename(mCon,Loantype_name);
                Intent intent = new Intent(Applicant_Details_Activity.this, Step_Completion_Screen.class);
                startActivity(intent);
               // finish();

            }
        });

        personal_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String Loantype = "2";
                String sub_categoryid = "3";
                String Loantype_name = "Personal Loan [Unsecured]";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypename(mCon,Loantype_name);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Intent intent = new Intent(Applicant_Details_Activity.this, Partner_Lead_OTP_Verification.class);
                startActivity(intent);
                finish();*/


                String sub_categoryid = "3";
                String Loantype_name = "Personal Loan [Unsecured]";
                Pref.putLoanTypename(mCon,Loantype_name);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                String Loantype = "2";
                Pref.putLoanType(mCon,Loantype);
                Intent intent = new Intent(Applicant_Details_Activity.this, Step_Completion_Screen.class);
                startActivity(intent);
               // finish();


            }
        });

        Businessloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String Loantype = "2";
                String sub_categoryid = "4";
                String Loantype_name = "Business Loan [Unsecured]";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypename(mCon,Loantype_name);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Intent intent = new Intent(Applicant_Details_Activity.this, Partner_Lead_OTP_Verification.class);
                startActivity(intent);
                finish();*/

                String Loantype = "2";
                Pref.putLoanType(mCon,Loantype);
                String Loantype_name = "Business Loan (MSME)";
                Pref.putLoanTypename(mCon,Loantype_name);

                Intent intent = new Intent(Applicant_Details_Activity.this, Step_Completion_Screen.class);
                startActivity(intent);
               // finish();
            }
        });



        vehicle_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mCon,"Vehicle Loan Under Process",Toast.LENGTH_SHORT).show();

                /*String Loancat_name = "Vehicle Loan";
                Pref.putLoanCat_Name(mCon,Loancat_name);
                String Loantype = "3";
                String sub_categoryid = "5";
                Pref.putLoanType(mCon,Loantype);
                Pref.putLoanTypeSub(mCon,sub_categoryid);
                Intent intent = new Intent(Applicant_Details_Activity.this, MainActivity_Vehicle_Add_Lead1.class);
                startActivity(intent);*/
                //finish();
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
        Objs.ac.StartActivity(mCon, DashBoard_new.class);
        finish();
        super.onBackPressed();
    }
}
