package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Creadite_Report_Activity extends SimpleActivity {


    AppCompatTextView name_txt,name_txt1,email_id_Txt,email_id_Txt1,mobiles_txt,mobiles_txt1,
            co_applicant_name_txt,co_applicant_name_txt1,Co_applicant_email_id,Co_applicant_email_id1,
            co_applicant_mobile,accept_terms_condition;
    AppCompatButton credit_det_cap_button;

    AppCompatEditText Pan_No_Edite_text,Email_Id_Edite_text,Co_App_Pan_No_Edite_text,Co_Email_Id_No_Edite_text,
            Co_Mobile_No_Edite_text,Mobile_No_Edite_text;
    AppCompatCheckBox check_complete;

    Typeface font;
    private Context context = this;
    InputMethodManager imm;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    private AlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_creadite__report_);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_creadite__report_);
        initTools(R.string.credit_report);



        progressDialog = new SpotsDialog(context, R.style.Custom);



        UISCREEN();
        Font();
        Click();

    }

    private void UISCREEN()
    {

        /*  AppCompatTextView name_txt,name_txt1,email_id_Txt,email_id_Txt1,mobiles_txt,mobiles_txt1,
            co_applicant_name_txt,co_applicant_name_txt1,Co_applicant_email_id,Co_applicant_email_id1,
            co_applicant_mobile,accept_terms_condition;*/

        name_txt = (AppCompatTextView) findViewById(R.id.name_txt);
        name_txt1 = (AppCompatTextView) findViewById(R.id.name_txt1);
        email_id_Txt = (AppCompatTextView) findViewById(R.id.email_id_Txt);
        email_id_Txt1 = (AppCompatTextView) findViewById(R.id.email_id_Txt1);
        mobiles_txt = (AppCompatTextView) findViewById(R.id.mobiles_txt);
        mobiles_txt1 = (AppCompatTextView) findViewById(R.id.mobiles_txt1);
        co_applicant_name_txt = (AppCompatTextView) findViewById(R.id.co_applicant_name_txt);
        co_applicant_name_txt1 = (AppCompatTextView) findViewById(R.id.co_applicant_name_txt1);
        Co_applicant_email_id = (AppCompatTextView) findViewById(R.id.Co_applicant_email_id);
        Co_applicant_email_id1 = (AppCompatTextView) findViewById(R.id.Co_applicant_email_id1);
        co_applicant_mobile = (AppCompatTextView) findViewById(R.id.co_applicant_mobile);
        accept_terms_condition = (AppCompatTextView) findViewById(R.id.accept_terms_condition);

        //Pan_No_Edite_text,Email_Id_Edite_text,Co_App_Pan_No_Edite_text;
        Pan_No_Edite_text = (AppCompatEditText) findViewById(R.id.Pan_No_Edite_text);
        Email_Id_Edite_text = (AppCompatEditText) findViewById(R.id.Email_Id_Edite_text);
        Co_App_Pan_No_Edite_text = (AppCompatEditText) findViewById(R.id.Co_App_Pan_No_Edite_text);
        Co_Email_Id_No_Edite_text = (AppCompatEditText) findViewById(R.id.Co_Email_Id_No_Edite_text);
        Co_Mobile_No_Edite_text = (AppCompatEditText) findViewById(R.id.Co_Mobile_No_Edite_text);
        Mobile_No_Edite_text = (AppCompatEditText) findViewById(R.id.Mobile_No_Edite_text);
        credit_det_cap_button = (AppCompatButton) findViewById(R.id.credit_det_cap_button);

    }


    private void Font() {

        font = Typeface.createFromAsset(context.getAssets(), "Lato-Regular.ttf");

        /*name_txt,name_txt1,email_id_Txt,email_id_Txt1,mobiles_txt,mobiles_txt1,
            co_applicant_name_txt,co_applicant_name_txt1,Co_applicant_email_id,Co_applicant_email_id1,
            co_applicant_mobile,accept_terms_condition*/

        name_txt.setTypeface(font);
        name_txt1.setTypeface(font);
        email_id_Txt.setTypeface(font);
        email_id_Txt1.setTypeface(font);
        mobiles_txt.setTypeface(font);
        mobiles_txt1.setTypeface(font);
        co_applicant_name_txt.setTypeface(font);
        co_applicant_name_txt1.setTypeface(font);
        Co_applicant_email_id.setTypeface(font);
        Co_applicant_email_id1.setTypeface(font);
        co_applicant_mobile.setTypeface(font);
        accept_terms_condition.setTypeface(font);
        Pan_No_Edite_text.setTypeface(font);
        Email_Id_Edite_text.setTypeface(font);
        Co_App_Pan_No_Edite_text.setTypeface(font);
        Co_Email_Id_No_Edite_text.setTypeface(font);
        Co_Mobile_No_Edite_text.setTypeface(font);
        Mobile_No_Edite_text.setTypeface(font);

    }

    private void Click() {

        credit_det_cap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Creadite_Report_Activity.this, Payment_Details_Activity.class);
                startActivity(intent);
                finish();
            }
        });


       /* credit_det_cap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!CR_PAn_Validation()) {
                    return;
                }
                if (!Email_ID_Validation()) {
                    return;
                }
                if (!Maobile_validation()) {
                    return;
                }

                if (!CO_CR_PAn_Validation()) {
                    return;
                }
                if (!CO_Email_ID_Validation()) {
                    return;
                }
                if (!CO_Mobile_validation()) {
                    return;
                }

                if(check_complete.isChecked())
                {
                    Payment_Option();

                }else
                {

                    Toast.makeText(context, "Please accept the Terms and condition", Toast.LENGTH_SHORT).show();

                }


            }
        });
*/

    }

    private boolean CR_PAn_Validation(){

        if (Pan_No_Edite_text.getText().toString().isEmpty()) {
            Pan_No_Edite_text.setError(getText(R.string.error_rise));
            Pan_No_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Email_ID_Validation(){

        if (Email_Id_Edite_text.getText().toString().isEmpty()) {
            Email_Id_Edite_text.setError(getText(R.string.error_rise));
            Email_Id_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean Maobile_validation(){

        if (Mobile_No_Edite_text.getText().toString().isEmpty()) {
            Mobile_No_Edite_text.setError(getText(R.string.error_rise));
            Mobile_No_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }


    private boolean CO_CR_PAn_Validation(){

        if (Co_App_Pan_No_Edite_text.getText().toString().isEmpty()) {
            Co_App_Pan_No_Edite_text.setError(getText(R.string.error_rise));
            Co_App_Pan_No_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean CO_Email_ID_Validation(){

        if (Co_Email_Id_No_Edite_text.getText().toString().isEmpty()) {
            Co_Email_Id_No_Edite_text.setError(getText(R.string.error_rise));
            Co_Email_Id_No_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

    private boolean CO_Mobile_validation(){

        if (Co_Mobile_No_Edite_text.getText().toString().isEmpty()) {
            Co_Mobile_No_Edite_text.setError(getText(R.string.error_rise));
            Co_Mobile_No_Edite_text.requestFocus();
            return false;
        } else {

            //inputLayoutLname.setErrorEnabled(false);
        }

        return true;
    }

        private void Payment_Option()
    {
        Intent intent = new Intent(Creadite_Report_Activity.this, Payment_Details_Activity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        Objs.ac.StartActivity(mCon, Credite_report_details.class);
        finish();
        super.onBackPressed();
    }
}
