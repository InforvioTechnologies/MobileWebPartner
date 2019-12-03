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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Arrays;
import java.util.List;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;


public class Viability_Check_PL extends SimpleActivity {

    AppCompatButton lead_viy_step2;
    private Spinner spinner_residence_type,spinner_employe_id,spinn_salary_crt_mtd,
            spinner_salary_proof;
    LinearLayout residence_type,residence_live,pan_card_available,other_earning_avbl;
    RadioGroup has_pan_card,applicant_family_OEM;
    RadioButton yes_pan,no_pan,other__OEM_family_yes,other_OEM_family_no;
    AppCompatTextView age,age1,pan_number_txt,pan_number_txt1,Pan_number_txt,Pan_number1_txt,
                     occupation_txt,occupation_txt1,monthly_sal_txt,monthly_sal_txt1,emp_id,emp_id1,
                     salery_credite_method_txt,salery_credite_method_txt1,salary_proof_txt,salary_proof_txt1,Exp_in_current_txt,
                     Exp_in_current_txt1,total_workexperiecnce_txt,total_workexperiecnce_txt1,
                     cmp_pincode_txt,cmp_pincode_txt1,txt_residence_pincode,txt_residence_pincode1,txt_residence_type,
                    txt_residence_type1,Lives_in_current_txt,Lives_in_current_txt1,any_other_family_member_txt,
                      any_other_family_member_txt1,family_member_name_txt,family_member_name_txt1,family_member_income_txt,
            family_member_income_txt1,monthly_afr_emi_txt,monthly_afr_emi_txt1;

    AppCompatEditText age_edite_txt,pan_number_edit_txt,occupation_edit_txt,monthly_net_sal_edit_txt,
                         experience_in_current_cmpy,total_experience_edit_txt,company_pincode_txt,
                         residence_pincode1_edit_txt,current_residence_edit_txt,family_member_name_edit_txt,
            family_member_income_edit_txt,monthly_afr_emi_amt_edit_txt;
    Typeface font;
    private Context context = this;

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
        Click();
        fonts();
    }

    private void UISCREEN() {

        spinner_residence_type = (Spinner) findViewById(R.id.spinner_residence_type);
        spinner_employe_id = (Spinner) findViewById(R.id.spinner_employe_id);
        spinn_salary_crt_mtd = (Spinner) findViewById(R.id.spinn_salary_crt_mtd);
        spinner_residence_type = (Spinner) findViewById(R.id.spinner_residence_type);
        spinner_salary_proof = (Spinner) findViewById(R.id.spinner_salary_proof);

        residence_type = (LinearLayout) findViewById(R.id.residence_type);
        residence_live = (LinearLayout) findViewById(R.id.residence_live);

        pan_card_available = (LinearLayout) findViewById(R.id.pan_card_available);
        other_earning_avbl = (LinearLayout) findViewById(R.id.other_earning_avbl);

        residence_type.setVisibility(View.VISIBLE);

        has_pan_card = (RadioGroup) findViewById(R.id.has_pan_card);
        applicant_family_OEM = (RadioGroup) findViewById(R.id.applicant_family_OEM);

        yes_pan = (RadioButton) findViewById(R.id.yes_pan);
        no_pan = (RadioButton) findViewById(R.id.no_pan);

        other__OEM_family_yes = (RadioButton) findViewById(R.id.other__OEM_family_yes);
        other_OEM_family_no = (RadioButton) findViewById(R.id.other_OEM_family_no);


      /*  age,age1,pan_number_txt,pan_number_txt1,
                occupation_txt,occupation_txt1,monthly_sal_txt,monthly_sal_txt1,emp_id,emp_id1,
                salery_credite_method_txt,salery_credite_method_txt1,salary_proof_txt,salary_proof_txt1,Exp_in_current_txt,
                Exp_in_current_txt1,total_workexperiecnce_txt,total_workexperiecnce_txt1,
                cmp_pincode_txt,cmp_pincode_txt1*/

        /*txt_residence_pincode,txt_residence_pincode1,txt_residence_type,
                    txt_residence_type1,Lives_in_current_txt,Lives_in_current_txt1,any_other_family_member_txt,
                      any_other_family_member_txt1,family_member_name_txt,family_member_name_txt1,family_member_income_txt,
            family_member_income_txt1,monthly_afr_emi_txt,monthly_afr_emi_txt1*/


        age = (AppCompatTextView) findViewById(R.id.age);
        age1 = (AppCompatTextView) findViewById(R.id.age1);
        pan_number_txt = (AppCompatTextView) findViewById(R.id.pan_number_txt);
        pan_number_txt1 = (AppCompatTextView) findViewById(R.id.pan_number_txt1);
        occupation_txt = (AppCompatTextView) findViewById(R.id.occupation_txt);
        occupation_txt1 = (AppCompatTextView) findViewById(R.id.occupation_txt1);
        monthly_sal_txt = (AppCompatTextView) findViewById(R.id.monthly_sal_txt);
        monthly_sal_txt1 = (AppCompatTextView) findViewById(R.id.monthly_sal_txt1);
        emp_id = (AppCompatTextView) findViewById(R.id.emp_id);
        emp_id1 = (AppCompatTextView) findViewById(R.id.emp_id1);
        salery_credite_method_txt = (AppCompatTextView) findViewById(R.id.salery_credite_method_txt);
        salery_credite_method_txt1 = (AppCompatTextView) findViewById(R.id.salery_credite_method_txt1);
        salary_proof_txt = (AppCompatTextView) findViewById(R.id.salary_proof_txt);
        salary_proof_txt1 = (AppCompatTextView) findViewById(R.id.salary_proof_txt1);
        Exp_in_current_txt = (AppCompatTextView) findViewById(R.id.Exp_in_current_txt);
        Exp_in_current_txt1 = (AppCompatTextView) findViewById(R.id.Exp_in_current_txt1);
        total_workexperiecnce_txt = (AppCompatTextView) findViewById(R.id.total_workexperiecnce_txt);
        total_workexperiecnce_txt1 = (AppCompatTextView) findViewById(R.id.total_workexperiecnce_txt1);
        cmp_pincode_txt = (AppCompatTextView) findViewById(R.id.cmp_pincode_txt);
        cmp_pincode_txt1 = (AppCompatTextView) findViewById(R.id.cmp_pincode_txt1);
        txt_residence_pincode = (AppCompatTextView) findViewById(R.id.txt_residence_pincode);
        txt_residence_pincode1 = (AppCompatTextView) findViewById(R.id.txt_residence_pincode1);
        txt_residence_type = (AppCompatTextView) findViewById(R.id.txt_residence_type);
        txt_residence_type1 = (AppCompatTextView) findViewById(R.id.txt_residence_type1);
        Lives_in_current_txt = (AppCompatTextView) findViewById(R.id.Lives_in_current_txt);
        Lives_in_current_txt1 = (AppCompatTextView) findViewById(R.id.Lives_in_current_txt1);
        any_other_family_member_txt = (AppCompatTextView) findViewById(R.id.any_other_family_member_txt);
        any_other_family_member_txt1 = (AppCompatTextView) findViewById(R.id.any_other_family_member_txt1);
        family_member_name_txt = (AppCompatTextView) findViewById(R.id.family_member_name_txt);
        family_member_name_txt1 = (AppCompatTextView) findViewById(R.id.family_member_name_txt1);
        family_member_income_txt = (AppCompatTextView) findViewById(R.id.family_member_income_txt);
        family_member_income_txt1 = (AppCompatTextView) findViewById(R.id.family_member_income_txt1);
        monthly_afr_emi_txt = (AppCompatTextView) findViewById(R.id.monthly_afr_emi_txt);
        monthly_afr_emi_txt1 = (AppCompatTextView) findViewById(R.id.monthly_afr_emi_txt1);



         /*age_edite_txt,pan_number_edit_txt,occupation_edit_txt,monthly_net_sal_edit_txt,
                         experience_in_current_cmpy,total_experience_edit_txt,company_pincode_txt;*/

         /*residence_pincode1_edit_txt,current_residence_edit_txt,family_member_name_edit_txt,
            family_member_income_edit_txt,monthly_afr_emi_amt_edit_txt*/

        age_edite_txt = (AppCompatEditText) findViewById(R.id.age_edite_txt);
        pan_number_edit_txt = (AppCompatEditText) findViewById(R.id.pan_number_edit_txt);
        occupation_edit_txt = (AppCompatEditText) findViewById(R.id.occupation_edit_txt);
        monthly_net_sal_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_net_sal_edit_txt);
        experience_in_current_cmpy = (AppCompatEditText) findViewById(R.id.experience_in_current_cmpy);
        total_experience_edit_txt = (AppCompatEditText) findViewById(R.id.total_experience_edit_txt);
        company_pincode_txt = (AppCompatEditText) findViewById(R.id.company_pincode_txt);

        residence_pincode1_edit_txt = (AppCompatEditText) findViewById(R.id.residence_pincode1_edit_txt);
        current_residence_edit_txt = (AppCompatEditText) findViewById(R.id.current_residence_edit_txt);
        family_member_name_edit_txt = (AppCompatEditText) findViewById(R.id.family_member_name_edit_txt);
        family_member_income_edit_txt = (AppCompatEditText) findViewById(R.id.family_member_income_edit_txt);
        monthly_afr_emi_amt_edit_txt = (AppCompatEditText) findViewById(R.id.monthly_afr_emi_amt_edit_txt);




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


    }

    private void fonts()
    {
        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");
          /*  age,age1,pan_number_txt,pan_number_txt1,
                occupation_txt,occupation_txt1,monthly_sal_txt,monthly_sal_txt1,emp_id,emp_id1,
                salery_credite_method_txt,salery_credite_method_txt1,salary_proof_txt,salary_proof_txt1,Exp_in_current_txt,
                Exp_in_current_txt1,total_workexperiecnce_txt,total_workexperiecnce_txt1,
                cmp_pincode_txt,cmp_pincode_txt1*/

        /*txt_residence_pincode,txt_residence_pincode1,txt_residence_type,
                    txt_residence_type1,Lives_in_current_txt,Lives_in_current_txt1,any_other_family_member_txt,
                      any_other_family_member_txt1,family_member_name_txt,family_member_name_txt1,family_member_income_txt,
            family_member_income_txt1,monthly_afr_emi_txt,monthly_afr_emi_txt1*/



        age.setTypeface(font);
        age1.setTypeface(font);
        pan_number_txt.setTypeface(font);
        pan_number_txt1.setTypeface(font);
        occupation_txt.setTypeface(font);
        occupation_txt1.setTypeface(font);
        monthly_sal_txt.setTypeface(font);
        monthly_sal_txt1.setTypeface(font);
        emp_id.setTypeface(font);
        salery_credite_method_txt.setTypeface(font);
        salery_credite_method_txt1.setTypeface(font);
        salary_proof_txt.setTypeface(font);
        salary_proof_txt1.setTypeface(font);
        Exp_in_current_txt.setTypeface(font);
        Exp_in_current_txt1.setTypeface(font);
        total_workexperiecnce_txt.setTypeface(font);
        total_workexperiecnce_txt1.setTypeface(font);
        cmp_pincode_txt.setTypeface(font);
        cmp_pincode_txt1.setTypeface(font);

        txt_residence_pincode.setTypeface(font);
        txt_residence_pincode1.setTypeface(font);
        txt_residence_type.setTypeface(font);
        txt_residence_type1.setTypeface(font);
        Lives_in_current_txt.setTypeface(font);
        Lives_in_current_txt1.setTypeface(font);
        any_other_family_member_txt.setTypeface(font);
        any_other_family_member_txt1.setTypeface(font);
        family_member_name_txt.setTypeface(font);
        family_member_name_txt1.setTypeface(font);
        family_member_income_txt.setTypeface(font);
        family_member_income_txt1.setTypeface(font);
        monthly_afr_emi_txt.setTypeface(font);
        monthly_afr_emi_txt1.setTypeface(font);


         /*age_edite_txt,pan_number_edit_txt,occupation_edit_txt,monthly_net_sal_edit_txt,
                         experience_in_current_cmpy,total_experience_edit_txt,company_pincode_txt;*/

         /*residence_pincode1_edit_txt,current_residence_edit_txt,family_member_name_edit_txt,
            family_member_income_edit_txt,monthly_afr_emi_amt_edit_txt*/
        age_edite_txt.setTypeface(font);
        pan_number_edit_txt.setTypeface(font);
        occupation_edit_txt.setTypeface(font);
        monthly_net_sal_edit_txt.setTypeface(font);
        experience_in_current_cmpy.setTypeface(font);
        total_experience_edit_txt.setTypeface(font);
        company_pincode_txt.setTypeface(font);
        residence_pincode1_edit_txt.setTypeface(font);
        current_residence_edit_txt.setTypeface(font);
        family_member_name_edit_txt.setTypeface(font);
        family_member_income_edit_txt.setTypeface(font);
        monthly_afr_emi_amt_edit_txt.setTypeface(font);

    }

    private void Click()
    {
        has_pan_card.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                View radioButton = radioGroup.findViewById(i);
                switch (i)
                {
                    case R.id.yes_pan:
                        pan_card_available.setVisibility(View.VISIBLE);
                        break;
                    case R.id.no_pan:
                        pan_card_available.setVisibility(View.GONE);
                        break;
                }

            }
        });

        applicant_family_OEM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                View radioButton = radioGroup.findViewById(i);
                switch (i)
                {
                    case R.id.other__OEM_family_yes:
                        other_earning_avbl.setVisibility(View.VISIBLE);
                        break;
                    case R.id.other_OEM_family_no:
                        other_earning_avbl.setVisibility(View.GONE);
                        break;
                }

            }
        });

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
