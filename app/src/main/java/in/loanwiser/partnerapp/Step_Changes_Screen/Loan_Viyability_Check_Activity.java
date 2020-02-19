package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

public class Loan_Viyability_Check_Activity extends SimpleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_loan__viyability__check_);

        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_loan__viyability__check_);
        initTools(R.string.viy_report);

        Intent intent = getIntent();
        String JSon_object = intent.getStringExtra("viability_jsonArray");


        try {
            JSONObject array = new JSONObject(JSon_object);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
