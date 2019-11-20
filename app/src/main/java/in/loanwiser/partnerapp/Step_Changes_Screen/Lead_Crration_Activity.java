package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import java.util.Arrays;
import java.util.List;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Lead_Crration_Activity extends SimpleActivity {

    AppCompatButton lead_cr_step1;
    private Spinner spinner_loan_category,spinner_loan_type;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_lead__crration_);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_lead__crration_);
        initTools(R.string.lead_creation);

        spinner_loan_category =(Spinner) findViewById(R.id.spinner_loan_category);
        spinner_loan_type =(Spinner) findViewById(R.id.spinner_loan_type);

        lead_cr_step1 = (AppCompatButton) findViewById(R.id.lead_cr_step1);

        String Lontype = Pref.getLoanType(getApplicationContext());

        if(Lontype.equals("2"))
        {
            lead_cr_step1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_BL.class);
                    startActivity(intent);
                    finish();
                }
            });

        }else if(Lontype.equals("3"))
        {
            lead_cr_step1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_Check_PL.class);
                    startActivity(intent);
                    finish();
                }
            });

        }else if(Lontype.equals("1"))
        {
            lead_cr_step1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_check_HL.class);
                    startActivity(intent);
                    finish();
                }
            });

        }else
        {
            lead_cr_step1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Lead_Crration_Activity.this, Viability_check_HL.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        MySpinnerAdapter spinner_loan_category_ad = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.loan_catogary)));
        spinner_loan_category.setAdapter(spinner_loan_category_ad);

        MySpinnerAdapter spinner_loan_type_ad = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.loan_type)));
        spinner_loan_type.setAdapter(spinner_loan_type_ad);

    }

    private static class MySpinnerAdapter extends ArrayAdapter<String> {
        // Initialise custom font, for example:
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),
                "Lato-Regular.ttf");

        // (In reality I used a manager which caches the Typeface objects)
        // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

        private MySpinnerAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        // Affects default (closed) state of the spinner
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }

        // Affects opened state of the spinner
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Applicant_Details_Activity.class);
        finish();
        super.onBackPressed();

    }

}
