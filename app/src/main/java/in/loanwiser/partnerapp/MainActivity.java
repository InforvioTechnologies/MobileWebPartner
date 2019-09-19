package in.loanwiser.partnerapp;

import android.os.Bundle;
import androidx.appcompat.widget.AppCompatTextView;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import in.loanwiser.partnerapp.PartnerActivitys.RemoveCommas;


public class MainActivity extends SimpleActivity {



    RemoveCommas removeClass;
    String array  = "{\n" +
            "  \"incomeprofcoapp\": [\n" +
            "    {\n" +
            "      \"id\": \"1\",\n" +
            "      \"name\": \"Pay Slip\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": \"3\",\n" +
            "      \"name\": \"Salary Certificate\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    String array1 = "{\n" +
            "  \"businessproo\": [\n" +
            "    \n" +
            "  ]\n" +
            "} ";

     AppCompatTextView txt_formate;

     String S_businessproo,R_businessproo;
     String S_co_businessproo,R_co_businessproo;
     String S_incomeprofcoapp,R_incomeprofcoapp;
     String S_incomeprofapp,R_incomeprofapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_main);

        txt_formate = (AppCompatTextView) findViewById(R.id.txt_formate);
        removeClass = new RemoveCommas();

        try {



            JSONObject app = new JSONObject(array1);
            JSONObject js = new JSONObject(array);


            //Applicant
            JSONArray businessproo = app.getJSONArray("businessproo");
            StringBuffer businessproo_result = new StringBuffer();

            //Validation of Array
            if(businessproo != null && businessproo.length() > 0 ){

            for (int i = 0; i < businessproo.length(); i++) {
                JSONObject object =  businessproo.getJSONObject(i);
                businessproo_result.append(object.getString("name")+ ",");
            }
            S_businessproo = businessproo_result.toString();
            R_businessproo  = removeClass.cleanUpCommas(S_businessproo);
            //set text value R_businessproo
            }else {
                Toast.makeText(mCon,"businessproo Its Empty",Toast.LENGTH_SHORT).show();
            }



            JSONObject co_applicant =js.getJSONObject("coapp");

            //Co-Applicant
            JSONArray co_businessproo = co_applicant.getJSONArray("businessproo");
            StringBuffer co_applicant_result = new StringBuffer();
            //Validation of Array
            if(co_businessproo != null && co_businessproo.length() > 0 ){

                for (int i = 0; i < co_businessproo.length(); i++) {
                    JSONObject object =  co_businessproo.getJSONObject(i);
                    co_applicant_result.append(object.getString("name")+ ",");
                }
                S_co_businessproo = co_applicant_result.toString();
                R_co_businessproo  = removeClass.cleanUpCommas(S_co_businessproo);
                //set text value R_co_businessproo
            }else {
                Toast.makeText(mCon,"co_businessproo Its Empty",Toast.LENGTH_SHORT).show();
            }



            //Income Proof for Applicant

            JSONArray incomeprofapp = js.getJSONArray("incomeprofapp");
            if(incomeprofapp != null && incomeprofapp.length() > 0 ) {
                StringBuffer incomeprofapp_result = new StringBuffer();
                for (int i = 0; i < incomeprofapp.length(); i++) {
                    JSONObject aa = incomeprofapp.getJSONObject(i);
                    incomeprofapp_result.append(aa.getString("name") + ",");
                }
                S_incomeprofapp= incomeprofapp_result.toString();
                R_incomeprofapp = removeClass.cleanUpCommas(S_incomeprofapp);
                //set text value R_incomeprofapp
            }


            //Income Proof for Co-Applicant
            JSONArray incomeprofcoapp = js.getJSONArray("incomeprofcoapp");
            if(incomeprofcoapp != null && incomeprofcoapp.length() > 0 ) {
                StringBuffer incomeprofcoapp_result = new StringBuffer();
                for (int i = 0; i < incomeprofcoapp.length(); i++) {
                    JSONObject aa = incomeprofcoapp.getJSONObject(i);
                    incomeprofcoapp_result.append(aa.getString("name") + ",");
                }
                S_incomeprofcoapp= incomeprofcoapp_result.toString();
                R_incomeprofcoapp = removeClass.cleanUpCommas(S_incomeprofcoapp);
                //set text value R_incomeprofcoapp
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
