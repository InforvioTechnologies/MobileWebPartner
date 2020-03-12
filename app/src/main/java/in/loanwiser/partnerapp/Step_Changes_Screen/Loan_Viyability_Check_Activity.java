package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.PartnerActivitys.Applicant_Details_Activity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Loan_Viyability_Check_Activity extends SimpleActivity {


    String lv,vs,tc,applicant_reason,comments;
    JSONObject obj1,Jason1,jsonObject2,applicant_json_object2,co_applicant_json_object;
    AppCompatTextView loan_viability,applicant_profile,Type_a,Type_b,Type_c,Type_d,co_applicant_profile,viability_reson,
            ove_all;
    JSONArray array,rule_message_Applicant,rule_message_Co_Applicant;
    List<String> result1;
    AppCompatButton viability_Done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_loan__viyability__check_);
        result1 = new ArrayList<>();

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_loan__viyability__check_);
        initTools(R.string.viy_report);


        UIScreen();

        Intent intent = getIntent();
        String JSon_object = intent.getStringExtra("viability_jsonArray");

        try {
            JSONObject array = new JSONObject(JSon_object);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        lv = intent.getStringExtra("viability_jsonArray");
        Log.e("status_arr",String.valueOf(lv));
        vs = intent.getStringExtra("viablity_status");
      //  loan_viability.setText(vs);
           try
        {
            Jason1 = new JSONObject(lv);
            Log.e("jsoobject",String.valueOf(lv));
            obj1 = Jason1.getJSONObject("message");
            jsonObject2 = obj1.getJSONObject("type_message");
            // JSONArray a = jsonObject2.getJSONArray("applicant");
            JSONObject a = jsonObject2.getJSONObject("applicant");
            Log.e("Type", a.toString());
            String Type_A= a.getString("Type A");
            String Type_B= a.getString("Type B");
            String Type_C= a.getString("Type C");
            String Type_D= a.getString("Type D");
            Type_a.setText(Type_A);
            Type_b.setText(Type_B);
            Type_c.setText(Type_C);
            Type_d.setText(Type_D);
            obj1 = Jason1.getJSONObject("message");
            applicant_json_object2=obj1.getJSONObject("rule_message");

            rule_message_Applicant = applicant_json_object2.getJSONArray("applicant");

            String date = applicant_json_object2.getString("type_cat");
            Log.e("string_name",String.valueOf(date));
            applicant_profile.setText(date);
            ove_all.setText(date);

            for(int i = 0;i<rule_message_Applicant.length() ;i++)
            {
                JSONObject jsonObject = rule_message_Applicant.getJSONObject(i);
                applicant_reason = jsonObject.getString("reason");
                comments = jsonObject.getString("comments");
                result1.add(applicant_reason +"\n" +comments);

            }
            viability_reson.setText(String.valueOf(result1));


          /*  co_applicant_json_object=obj1.getJSONObject("category_arr");
            String date1 = co_applicant_json_object.getString("co-applicant");
          //  co_applicant_profile.setText(date1);*/
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        viability_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Loan_Viyability_Check_Activity.this, Applicant_Details_Activity.class);
                startActivity(intent1);
            }
        });
    }

    private void UIScreen()
    {
        loan_viability=(AppCompatTextView)findViewById(R.id.loan_viability);
        applicant_profile=(AppCompatTextView)findViewById(R.id.applicant_profile);
        Type_a =(AppCompatTextView)findViewById(R.id.salaried);
        Type_b=(AppCompatTextView)findViewById(R.id.bank_cr);
        Type_c=(AppCompatTextView)findViewById(R.id.own_house);
        Type_d=(AppCompatTextView)findViewById(R.id.rent);
        viability_reson =(AppCompatTextView)findViewById(R.id.viability_reson);

        ove_all =(AppCompatTextView)findViewById(R.id.ove_all);

        viability_Done =(AppCompatButton)findViewById(R.id.viability_Done);
    }

}
