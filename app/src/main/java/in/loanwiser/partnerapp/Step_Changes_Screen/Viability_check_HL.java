package in.loanwiser.partnerapp.Step_Changes_Screen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import java.util.Arrays;
import java.util.List;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Viability_check_HL extends SimpleActivity {

    Spinner spnr_type_of_empmnt;
    LinearLayout salaried,self_employed;

    private Spinner spinner1,spi_vocation_type_,spinner_residence_type,
            spi_vocation_forming,spinner_busines_type;
    private LinearLayout individual,self_business,formin_dairy,Driver_C_owner,
            res_rented,forming,dairy,poultry,
            Retail_wholesale_business,service_business,manufacturing;

    AppCompatButton lead_viy_step2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_viability_check__hl);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viability_check__hl);
        initTools(R.string.viy_check);

        lead_viy_step2 = (AppCompatButton) findViewById(R.id.lead_viy_step2);
        String Lontype = Pref.getLoanType(getApplicationContext());

        if(Lontype.equals("1"))
        {
            lead_viy_step2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Viability_check_HL.this, Eligibility_HL.class);
                    startActivity(intent);
                    finish();
                }
            });
        }else if(Lontype.equals("4"))
        {
            lead_viy_step2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Viability_check_HL.this, Eligibility_check_LAP.class);
                    startActivity(intent);
                    finish();
                }
            });

        }


        UISCREEN();

    }

    private void UISCREEN() {

        //salaried
        salaried = (LinearLayout) findViewById(R.id.salaried);
        self_employed = (LinearLayout) findViewById(R.id.self_employed);
        spnr_type_of_empmnt = (Spinner) findViewById(R.id.spnr_type_of_empmnt);


        // Self_Employed

        spinner1 =(Spinner) findViewById(R.id.emp_type);
        spi_vocation_type_ =(Spinner) findViewById(R.id.spi_vocation_type_);
        spinner_residence_type =(Spinner) findViewById(R.id.spinner_residence_type);
        spi_vocation_forming =(Spinner) findViewById(R.id.spi_vocation_forming);
        spinner_busines_type =(Spinner) findViewById(R.id.spinner_busines_type_own_business);

        individual = (LinearLayout) findViewById(R.id.individual);
        formin_dairy = (LinearLayout) findViewById(R.id.formin_dairy);
        self_business = (LinearLayout) findViewById(R.id.self_business);
        Driver_C_owner = (LinearLayout) findViewById(R.id.Driver_C_owner);
        res_rented = (LinearLayout) findViewById(R.id.res_rented);
        forming = (LinearLayout) findViewById(R.id.forming);
        dairy = (LinearLayout) findViewById(R.id.dairy);
        poultry = (LinearLayout) findViewById(R.id.poultry);


        Retail_wholesale_business = (LinearLayout) findViewById(R.id.Retail_wholesale_business);
        service_business = (LinearLayout) findViewById(R.id.service_business);
        manufacturing = (LinearLayout) findViewById(R.id.manufacturing);


        MySpinnerAdapter employement_type = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.emp_typ)));
        spnr_type_of_empmnt.setAdapter(employement_type);


        spnr_type_of_empmnt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String  emp = String.valueOf(spnr_type_of_empmnt.getSelectedItemPosition());
                Log.e("the Employe select is",emp);
                int b = Integer.parseInt(emp);
                if(emp.equals("0"))
                {
                    salaried.setVisibility(View.GONE);
                    self_employed.setVisibility(View.GONE);

                }else  if(emp.equals("1"))
                {
                    salaried.setVisibility(View.VISIBLE);
                    self_employed.setVisibility(View.GONE);
                }else
                {
                    salaried.setVisibility(View.GONE);
                    self_employed.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    // Self_Employeed

        MySpinnerAdapter employee_type = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.employee_type)));
        spinner1.setAdapter(employee_type);

        MySpinnerAdapter spi_vocation_type = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.vocation_type)));
        spi_vocation_type_.setAdapter(spi_vocation_type);

        MySpinnerAdapter residence = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.ressidence_typ)));
        spinner_residence_type.setAdapter(residence);

        MySpinnerAdapter vocation_forming = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.vocation_forming)));
        spi_vocation_forming.setAdapter(vocation_forming);

        MySpinnerAdapter business_type = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.business_type)));
        spinner_busines_type.setAdapter(business_type);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String  emp = String.valueOf(spinner1.getSelectedItemPosition());
                Log.e("the Employe select is",emp);
                int b = Integer.parseInt(emp);
                Log.e("propert value",String.valueOf(emp));
                switch(b) {
                    case 0:
                        individual.setVisibility(View.VISIBLE);
                        formin_dairy.setVisibility(View.GONE);
                        self_business.setVisibility(View.GONE);
                        break;
                    case 1:
                        individual.setVisibility(View.GONE);
                        formin_dairy.setVisibility(View.VISIBLE);
                        self_business.setVisibility(View.GONE);
                        break;
                    case 2:
                        String Loantype = "2";
                        Pref.putEMPLOYMENT(mCon,Loantype);

                        String Lontype = Pref.getEMPLOYMENT(getApplicationContext());
                        Log.e("Lontype",Lontype);

                        individual.setVisibility(View.GONE);
                        formin_dairy.setVisibility(View.GONE);
                        self_business.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spi_vocation_type_.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String  emp = String.valueOf(spi_vocation_type_.getSelectedItemPosition());
                Log.e("the Employe select is",emp);
                int b = Integer.parseInt(emp);

                if(emp.equals("6"))
                {
                    Driver_C_owner.setVisibility(View.VISIBLE);

                }else
                {
                    Driver_C_owner.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_residence_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String  emp = String.valueOf(spinner_residence_type.getSelectedItemPosition());
                Log.e("the Employe select is",emp);
                int b = Integer.parseInt(emp);

                if(emp.equals("1"))
                {
                    res_rented.setVisibility(View.VISIBLE);

                }else
                {
                    res_rented.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spi_vocation_forming.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String  emp = String.valueOf(spi_vocation_forming.getSelectedItemPosition());
                Log.e("the Employe select is",emp);
                int b = Integer.parseInt(emp);

                if(emp.equals("0"))
                {
                    forming.setVisibility(View.VISIBLE);
                    dairy.setVisibility(View.GONE);
                    poultry.setVisibility(View.GONE);

                }else if(emp.equals("1"))
                {
                    forming.setVisibility(View.GONE);
                    dairy.setVisibility(View.VISIBLE);
                    poultry.setVisibility(View.GONE);
                }else
                {
                    forming.setVisibility(View.GONE);
                    dairy.setVisibility(View.GONE);
                    poultry.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_busines_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String  emp = String.valueOf(spinner_busines_type.getSelectedItemPosition());
                Log.e("the Employe select is",emp);
                int b = Integer.parseInt(emp);

                if(emp.equals("0"))
                {
                    Retail_wholesale_business.setVisibility(View.VISIBLE);
                    service_business.setVisibility(View.GONE);
                    manufacturing.setVisibility(View.GONE);

                }else if(emp.equals("1"))
                {
                    Retail_wholesale_business.setVisibility(View.GONE);
                    service_business.setVisibility(View.VISIBLE);
                    manufacturing.setVisibility(View.GONE);
                }else
                {
                    Retail_wholesale_business.setVisibility(View.GONE);
                    service_business.setVisibility(View.GONE);
                    manufacturing.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

        Objs.ac.StartActivity(mCon, Lead_Crration_Activity.class);
        finish();
        super.onBackPressed();

    }
}
