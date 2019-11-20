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
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Viability_Check_PL extends SimpleActivity {

    AppCompatButton lead_viy_step2;
    private Spinner spinner_residence_type,spinner_employe_id,spinn_salary_crt_mtd,
            spinner_salary_proof;
    LinearLayout residence_type,residence_live;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_viability__check);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_viability__check);
        initTools(R.string.viy_check);

        lead_viy_step2 = (AppCompatButton) findViewById(R.id.lead_viy_step2);

        lead_viy_step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Viability_Check_PL.this, Eligibility_Check_PL.class);
                startActivity(intent);
                finish();
            }
        });

        UISCREEN();
    }

    private void UISCREEN() {

        spinner_residence_type = (Spinner) findViewById(R.id.spinner_residence_type);
        spinner_employe_id = (Spinner) findViewById(R.id.spinner_employe_id);
        spinn_salary_crt_mtd = (Spinner) findViewById(R.id.spinn_salary_crt_mtd);
        spinner_residence_type = (Spinner) findViewById(R.id.spinner_residence_type);
        spinner_salary_proof = (Spinner) findViewById(R.id.spinner_salary_proof);

        residence_type = (LinearLayout) findViewById(R.id.residence_type);
        residence_live = (LinearLayout) findViewById(R.id.residence_live);
        residence_type.setVisibility(View.VISIBLE);

        MySpinnerAdapter residence = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.ressidence_typ)));
        spinner_residence_type.setAdapter(residence);


        MySpinnerAdapter emp_id = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.emp_id)));
        spinner_employe_id.setAdapter(emp_id);

        MySpinnerAdapter spinn_salary_crt_mtd_ad = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.salary_ctd_method)));
        spinn_salary_crt_mtd.setAdapter(spinn_salary_crt_mtd_ad);

        MySpinnerAdapter salary_proof = new MySpinnerAdapter(getApplicationContext(), R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.salary_proof)));
        spinner_salary_proof.setAdapter(salary_proof);

        spinner_residence_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String  emp = String.valueOf(spinner_residence_type.getSelectedItemPosition());
                Log.e("the Employe select is",emp);
                int b = Integer.parseInt(emp);
                if(emp.equals("1") )
                {
                    residence_live.setVisibility(View.VISIBLE);

                }else
                {
                    residence_live.setVisibility(View.GONE);
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
