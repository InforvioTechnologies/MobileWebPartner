package in.loanwiser.partnerapp.PartnerActivitys;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;

import static adhoc.app.applibrary.Config.AppUtils.Params.PMAY_DETAILS;
import static adhoc.app.applibrary.Config.AppUtils.Params.PROPERTY_DETAILS;

public class Customer_Info extends SimpleActivity {

    private Context mCon = this;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Customer_Info.class.getSimpleName();
    private AlertDialog progressDialog;
    String user_id;
    JSONArray pdetails;
    JSONArray pmay;
    private AppCompatTextView pmay_textview,pmay_textview1,pdetails_textview,pdetails_textview1;
    private AppCompatTextView papp_oj_textview;
    private LinearLayout pmay_linear,pdetails_linear,pdetails_linear1,pmay_linear1;
    private LinearLayout Ly_pmay,Ly_pdetails,Ly_papp_oj;
    private LinearLayout papp_app_User,papp_app_Current,papp_app_Off,papp_app_Employ,papp_app_income,papp_app_Exit;
    private LinearLayout papp_app_User1,papp_app_Current1,papp_app_Off1,papp_app_Employ1,papp_app_income1,papp_app_Exit1;
    AppCompatTextView pco1_oj_textview,pco1_oj_app_User,pco1_oj_app_Current,pco1_oj_app_Off,pco1_oj_app_EMP,pco1_oj_app_income,pco1_oj_app_exit;
    LinearLayout Ly_pco1_oj,pco1_app_User,pco1_app_User1,pco1_app_Current,pco1_app_Current1,
            pco1_app_Off,pco1_app_Off1, pco1_app_Employ,pco1_app_Employ1,pco1_app_income,
            pco1_app_income1, pco1_app_Exit,pco1_app_Exit1;

    AppCompatTextView pco2_oj_textview,pco2_oj_app_User,pco2_oj_app_Current,pco2_oj_app_Off,pco2_oj_app_EMP,pco2_oj_app_income,pco2_oj_app_exit;
    LinearLayout Ly_pco2_oj,pco2_app_User,pco2_app_User1,pco2_app_Current,pco2_app_Current1,
            pco2_app_Off,pco2_app_Off1,pco2_app_Employ,pco2_app_Employ1,pco2_app_income,pco2_app_income1,
            pco2_app_Exit,pco2_app_Exit1;


    AppCompatTextView pco3_oj_textview,pco3_oj_app_User,pco3_oj_app_Current,pco3_oj_app_Off,pco3_oj_app_EMP,pco3_oj_app_income,pco3_oj_app_exit;
    LinearLayout Ly_pco3_oj,pco3_app_User,pco3_app_User1,pco3_app_Current,pco3_app_Current1,
            pco3_app_Off,pco3_app_Off1,pco3_app_Employ,pco3_app_Employ1,pco3_app_income,pco3_app_income1,
            pco3_app_Exit,pco3_app_Exit1;
    Typeface custom_font;
    AppCompatTextView pco4_oj_textview,pco4_oj_app_User,pco4_oj_app_Current,pco4_oj_app_Off,pco4_oj_app_EMP,pco4_oj_app_income,pco4_oj_app_exit;
    LinearLayout Ly_pco4_oj,pco4_app_User,pco4_app_User1,pco4_app_Current,pco4_app_Current1,pco4_app_Off,pco4_app_Off1,pco4_app_Employ,pco4_app_Employ1,pco4_app_income,pco4_app_income1,
            pco4_app_Exit,pco4_app_Exit1;

    private ImageView img_drop,pdetails_img_drop,pco1_img_drop,pco2_img_drop,pco3_img_drop,pco4_img_drop,pay_img_drop;
    private LinearLayout Ly_pco1_oj_textview,Ly_pco2_oj_textview,Ly_pco3_oj_textview,
            Ly_pco4_oj_textview,Ly_papp_oj_textview;
    private LinearLayout Ly_pmay_textview,Ly_pdetails_textview;
    private CardView card_view_doc_typename,card_view_doc_typename1,card_view_doc_typename4,card_view_doc_typename3,
            card_view_doc_typename2,card_view_doc_typename11,card_view_doc_typename12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_customer__info);
        initTools(R.string.cinterview);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        user_id =  Objs.a.getBundle(this, Params.id);

        initCode();


        //  pmay_textview1.setVisibility(View.GONE);
        //    pdetails_textview1.setVisibility(View.GONE);
        Ly_pmay.setVisibility(View.GONE);
        Ly_pdetails.setVisibility(View.GONE);

        Applicant_Info(user_id);
    }

    private void initCode() {
        initUI();
        custom_font  = Typeface.createFromAsset(mCon.getAssets(), "Lato-Regular.ttf");
        fonts();
        //  clicks();
    }


    private void initUI() {

        pmay_textview = (AppCompatTextView) findViewById(R.id.pmay_textview);
        papp_oj_textview = (AppCompatTextView) findViewById(R.id.papp_oj_textview);
        //    pmay_textview1 = (AppCompatTextView) findViewById(R.id.pmay_textview1);
        pco1_oj_textview = (AppCompatTextView) findViewById(R.id.pco1_oj_textview);
        pco2_oj_textview = (AppCompatTextView) findViewById(R.id.pco2_oj_textview);
        pco3_oj_textview = (AppCompatTextView) findViewById(R.id.pco3_oj_textview);
        pco4_oj_textview = (AppCompatTextView) findViewById(R.id.pco4_oj_textview);
        pdetails_textview = (AppCompatTextView) findViewById(R.id.pdetails_textview);

        card_view_doc_typename = (CardView) findViewById(R.id.card_view_doc_typename);
        card_view_doc_typename1 = (CardView) findViewById(R.id.card_view_doc_typename1);
        //    pdetails_textview1 = (AppCompatTextView) findViewById(R.id.pdetails_textview1);
        card_view_doc_typename11 = (CardView) findViewById(R.id.card_view_doc_typename11);
        card_view_doc_typename12 = (CardView) findViewById(R.id.card_view_doc_typename12);
        card_view_doc_typename2 = (CardView) findViewById(R.id.card_view_doc_typename2);
        card_view_doc_typename3 = (CardView) findViewById(R.id.card_view_doc_typename3);
        card_view_doc_typename4 = (CardView) findViewById(R.id.card_view_doc_typename4);

        pmay_textview.setText("PMAY Details");

        pdetails_textview.setText("Loan Requirement Details");

        pmay_linear = (LinearLayout)findViewById(R.id.pmay_linear);
        pmay_linear1 = (LinearLayout)findViewById(R.id.pmay_linear1);
        pdetails_linear= (LinearLayout)findViewById(R.id.pdetails_linear);
        pdetails_linear1 = (LinearLayout)findViewById(R.id.pdetails_linear1);
        papp_app_User = (LinearLayout)findViewById(R.id.papp_app_User);
        papp_app_User1 = (LinearLayout)findViewById(R.id.papp_app_User1);
        papp_app_Current = (LinearLayout)findViewById(R.id.papp_app_Current);
        papp_app_Current1= (LinearLayout)findViewById(R.id.papp_app_Current1);
        papp_app_Off= (LinearLayout)findViewById(R.id.papp_app_Off);
        papp_app_Off1= (LinearLayout)findViewById(R.id.papp_app_Off1);
        papp_app_Employ= (LinearLayout)findViewById(R.id.papp_app_Employ);
        papp_app_Employ1= (LinearLayout)findViewById(R.id.papp_app_Employ1);
        papp_app_income= (LinearLayout)findViewById(R.id.papp_app_income);
        papp_app_income1= (LinearLayout)findViewById(R.id.papp_app_income1);
        papp_app_Exit= (LinearLayout)findViewById(R.id.papp_app_Exit);
        papp_app_Exit1= (LinearLayout)findViewById(R.id.papp_app_Exit1);

        pco1_app_User= (LinearLayout)findViewById(R.id.pco1_app_User);
        pco1_app_User1= (LinearLayout)findViewById(R.id.pco1_app_User1);
        pco1_app_Current= (LinearLayout)findViewById(R.id.pco1_app_Current);
        pco1_app_Current1= (LinearLayout)findViewById(R.id.pco1_app_Current1);
        pco1_app_Off= (LinearLayout)findViewById(R.id.pco1_app_Off);
        pco1_app_Off1= (LinearLayout)findViewById(R.id.pco1_app_Off1);
        pco1_app_Employ= (LinearLayout)findViewById(R.id.pco1_app_Employ);
        pco1_app_Employ1= (LinearLayout)findViewById(R.id.pco1_app_Employ1);
        pco1_app_income= (LinearLayout)findViewById(R.id.pco1_app_income);
        pco1_app_income1= (LinearLayout)findViewById(R.id.pco1_app_income1);
        pco1_app_Exit= (LinearLayout)findViewById(R.id.pco1_app_Exit);
        pco1_app_Exit1= (LinearLayout)findViewById(R.id.pco1_app_Exit1);

        pco2_app_User= (LinearLayout)findViewById(R.id.pco2_app_User);
        pco2_app_User1= (LinearLayout)findViewById(R.id.pco2_app_User1);
        pco2_app_Current= (LinearLayout)findViewById(R.id.pco2_app_Current);
        pco2_app_Current1= (LinearLayout)findViewById(R.id.pco2_app_Current1);
        pco2_app_Off= (LinearLayout)findViewById(R.id.pco2_app_Off);
        pco2_app_Off1= (LinearLayout)findViewById(R.id.pco2_app_Off1);
        pco2_app_Employ= (LinearLayout)findViewById(R.id.pco2_app_Employ);
        pco2_app_Employ1= (LinearLayout)findViewById(R.id.pco2_app_Employ1);
        pco2_app_income= (LinearLayout)findViewById(R.id.pco2_app_income);
        pco2_app_income1= (LinearLayout)findViewById(R.id.pco2_app_income1);
        pco2_app_Exit= (LinearLayout)findViewById(R.id.pco2_app_Exit);
        pco2_app_Exit1= (LinearLayout)findViewById(R.id.pco2_app_Exit1);

        pco3_app_User= (LinearLayout)findViewById(R.id.pco3_app_User);
        pco3_app_User1= (LinearLayout)findViewById(R.id.pco3_app_User1);
        pco3_app_Current= (LinearLayout)findViewById(R.id.pco3_app_Current);
        pco3_app_Current1= (LinearLayout)findViewById(R.id.pco3_app_Current1);
        pco3_app_Off= (LinearLayout)findViewById(R.id.pco3_app_Off);
        pco3_app_Off1= (LinearLayout)findViewById(R.id.pco3_app_Off1);
        pco3_app_Employ= (LinearLayout)findViewById(R.id.pco3_app_Employ);
        pco3_app_Employ1= (LinearLayout)findViewById(R.id.pco3_app_Employ1);
        pco3_app_income= (LinearLayout)findViewById(R.id.pco3_app_income);
        pco3_app_income1= (LinearLayout)findViewById(R.id.pco3_app_income1);
        pco3_app_Exit= (LinearLayout)findViewById(R.id.pco3_app_Exit);
        pco3_app_Exit1= (LinearLayout)findViewById(R.id.pco3_app_Exit1);

        pco4_app_User= (LinearLayout)findViewById(R.id.pco4_app_User);
        pco4_app_User1= (LinearLayout)findViewById(R.id.pco4_app_User1);
        pco4_app_Current= (LinearLayout)findViewById(R.id.pco4_app_Current);
        pco4_app_Current1= (LinearLayout)findViewById(R.id.pco4_app_Current1);
        pco4_app_Off= (LinearLayout)findViewById(R.id.pco4_app_Off);
        pco4_app_Off1= (LinearLayout)findViewById(R.id.pco4_app_Off1);
        pco4_app_Employ= (LinearLayout)findViewById(R.id.pco4_app_Employ);
        pco4_app_Employ1= (LinearLayout)findViewById(R.id.pco4_app_Employ1);
        pco4_app_income= (LinearLayout)findViewById(R.id.pco3_app_income);
        pco4_app_income1= (LinearLayout)findViewById(R.id.pco4_app_income1);
        pco4_app_Exit= (LinearLayout)findViewById(R.id.pco4_app_Exit);
        pco4_app_Exit1= (LinearLayout)findViewById(R.id.pco4_app_Exit1);

        Ly_pmay= (LinearLayout)findViewById(R.id.Ly_pmay);
        Ly_pdetails= (LinearLayout)findViewById(R.id.Ly_pdetails);
        Ly_papp_oj= (LinearLayout)findViewById(R.id.Ly_papp_oj);
        Ly_pco1_oj= (LinearLayout)findViewById(R.id.Ly_pco1_oj);
        Ly_pco2_oj= (LinearLayout)findViewById(R.id.Ly_pco2_oj);
        Ly_pco3_oj= (LinearLayout)findViewById(R.id.Ly_pco3_oj);
        Ly_pco4_oj= (LinearLayout)findViewById(R.id.Ly_pco4_oj);

        /*Ly_pco1_oj_textview,Ly_pco2_oj_textview,Ly_pco3_oj_textview,
            Ly_pco4_oj_textview,Ly_papp_oj_textview;*/
        Ly_pco1_oj_textview= (LinearLayout)findViewById(R.id.Ly_pco1_oj_textview);
        Ly_pco2_oj_textview= (LinearLayout)findViewById(R.id.Ly_pco2_oj_textview);
        Ly_pco3_oj_textview= (LinearLayout)findViewById(R.id.Ly_pco3_oj_textview);
        Ly_pco4_oj_textview= (LinearLayout)findViewById(R.id.Ly_pco4_oj_textview);
        Ly_papp_oj_textview= (LinearLayout)findViewById(R.id.Ly_papp_oj_textview);
        Ly_pmay_textview= (LinearLayout)findViewById(R.id.Ly_pmay_textview);
        Ly_pdetails_textview= (LinearLayout)findViewById(R.id.Ly_pdetails_textview);

        img_drop= (ImageView)findViewById(R.id.img_drop);
        pco1_img_drop= (ImageView)findViewById(R.id.pco1_img_drop);
        pco2_img_drop= (ImageView)findViewById(R.id.pco2_img_drop);
        pco3_img_drop= (ImageView)findViewById(R.id.pco3_img_drop);
        pco4_img_drop= (ImageView)findViewById(R.id.pco4_img_drop);
        pay_img_drop= (ImageView)findViewById(R.id.pay_img_drop);
        pdetails_img_drop= (ImageView)findViewById(R.id.pdetails_img_drop);

        img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        pco1_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        pco2_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        pco3_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        pco4_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        pay_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        pdetails_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
    }




    private void fonts() {

        Objs.a.OutfitNormalFontStyle(mCon, R.id.pmay_textview);
        //     Objs.a.OutfitNormalFontStyle(mCon, R.id.pmay_textview1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.pdetails_textview);
        //      Objs.a.OutfitNormalFontStyle(mCon, R.id.pdetails_textview1);
        Objs.a.OutfitNormalFontStyle(mCon, R.id.papp_oj_textview);

    }

    private void Applicant_Info(final String id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.user_id, id);
            //J.put(Params.user_id, "1647");
            Log.d("The Customer info", String.valueOf(J));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.APPLICANT_INFO_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d("The Customer info", String.valueOf(response));
                        //  Objs.a.showToast(mCon, String.valueOf(response));
                        String rupee = getResources().getString(R.string.Rs);
                        try {
                            if(response.getBoolean(Params.status)){

                                // JSONArray pmay = response.getJSONArray(Params.PMAY_DETAILS);

                                if(response.has(PMAY_DETAILS)){

                                    pmay = response.getJSONArray(PMAY_DETAILS);
                                }
                                else
                                {
                                    card_view_doc_typename.setVisibility(View.GONE);
                                    pmay = new JSONArray();
                                }
                                if(response.has(PROPERTY_DETAILS)){

                                    pdetails = response.getJSONArray(PROPERTY_DETAILS);
                                }
                                else
                                {
                                    card_view_doc_typename1.setVisibility(View.GONE);
                                    pdetails = new JSONArray();
                                }
                                //   JSONArray pdetails = response.getJSONArray(PROPERTY_DETAILS);

                                for(int i=0; i<pmay.length();i++){

                                    JSONObject pmay_oj = pmay.getJSONObject(i);
                                    String a  = pmay_oj.getString(Params.fieldname);
                                    String b  = pmay_oj.getString(Params.fieldvalue);



                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                    params.setMargins(0, 10, 0, 0);

                                    TextView pmay_fieldname = new TextView(mCon);
                                    pmay_fieldname.setLayoutParams(params);
                                    pmay_fieldname.setTypeface(custom_font);
                                    pmay_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                    pmay_fieldname.setText(a);
                                    pmay_fieldname.setTextSize(16);
                                    pmay_linear.addView(pmay_fieldname);

                                    TextView pmay_fieldvaluee= new TextView(mCon);
                                    pmay_fieldvaluee.setLayoutParams(params);
                                    pmay_fieldvaluee.setTypeface(custom_font);
                                    pmay_fieldvaluee.setText(b);
                                    pmay_fieldvaluee.setTextSize(16);
                                    pmay_linear1.addView(pmay_fieldvaluee);

                                }

                                for(int j=0; j<pdetails.length();j++){

                                    JSONObject pdetails_oj = pdetails.getJSONObject(j);
                                    String a  = pdetails_oj.getString(Params.fieldname);
                                    String b  = pdetails_oj.getString(Params.fieldvalue);



                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    params.setMargins(0, 10, 0, 0);


                                    if(a == "Required Loan Amount")
                                    {
                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pdetails_linear.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(rupee+b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pdetails_linear1.addView(pdetails_fieldvaluee);
                                    }
                                    else
                                    {
                                        Log.e("Required loan",a);
                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pdetails_linear.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pdetails_linear1.addView(pdetails_fieldvaluee);
                                    }

                                }


                                JSONObject papp_oj = response.getJSONObject(Params.Applicant);
                                JSONObject pco1_oj = response.getJSONObject(Params.C01);
                                JSONObject pco2_oj = response.getJSONObject(Params.C02);
                                JSONObject pco3_oj = response.getJSONObject(Params.C03);
                                JSONObject pco4_oj = response.getJSONObject(Params.C04);


                                if(papp_oj.getBoolean(Params.status)){

                                    Ly_papp_oj_textview.setVisibility(View.VISIBLE);
                                    card_view_doc_typename11.setVisibility(View.VISIBLE);
                                    //   Ly_papp_oj.setVisibility(View.VISIBLE);


                                    JSONArray app_User = papp_oj.getJSONArray(Params.user_info);
                                    JSONArray app_Current = papp_oj.getJSONArray(Params.Current_Address);
                                    JSONArray app_Off = papp_oj.getJSONArray(Params.Office_Address);
                                    JSONArray app_Employ = papp_oj.getJSONArray(Params.Employment_Details);
                                    JSONArray app_income = papp_oj.getJSONArray(Params.Income);
                                    JSONArray app_Exit = papp_oj.getJSONArray(Params.Existing_Loan);

                                    for(int i=0;i<app_User.length();i++){

                                        JSONObject app_User_oj = app_User.getJSONObject(i);
                                        String a  = app_User_oj.getString(Params.fieldname);
                                        String b  = app_User_oj.getString(Params.fieldvalue);

                                     //   Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        papp_app_User.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        papp_app_User1.addView(pdetails_fieldvaluee);

                                    }

                                    for(int i=0;i<app_Current.length();i++){

                                        JSONObject app_Current_oj = app_Current.getJSONObject(i);
                                        String a  =app_Current_oj.getString(Params.fieldname);
                                        String b  = app_Current_oj.getString(Params.fieldvalue);

                                    //    Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        papp_app_Current.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        papp_app_Current1.addView(pdetails_fieldvaluee);

                                    }
                                    for(int i=0;i<app_Off.length();i++){

                                        JSONObject app_Off_oj = app_Off.getJSONObject(i);
                                        String a  =app_Off_oj.getString(Params.fieldname);
                                        String b  = app_Off_oj.getString(Params.fieldvalue);

                                       /// Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        papp_app_Off.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        papp_app_Off1.addView(pdetails_fieldvaluee);

                                    }

                                    for(int i=0;i<app_Employ.length();i++){

                                        JSONObject app_Employ_oj = app_Employ.getJSONObject(i);
                                        String a  =app_Employ_oj.getString(Params.fieldname);
                                        String b  = app_Employ_oj.getString(Params.fieldvalue);

                                      //  Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        papp_app_Employ.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        papp_app_Employ1.addView(pdetails_fieldvaluee);

                                    }

                                    /* for(int i=0;i<app_income.length();i++){

                                         JSONArray ja = app_income.getJSONArray(i);

                                         for(int k=0;k<ja.length();k++){
                                             JSONObject app_income_oj = ja.getJSONObject(i);
                                             String a  =app_income_oj.getString(Params.fieldname);
                                             String b  = app_income_oj.getString(Params.fieldvalue);

                                             Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                     ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                             params.setMargins(0, 10, 0, 0);

                                             TextView pdetails_fieldname = new TextView(mCon);
                                             pdetails_fieldname.setLayoutParams(params);
                                             pdetails_fieldname.setTypeface(custom_font);
                                             pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.primary));
                                             pdetails_fieldname.setText(a);
                                             pdetails_fieldname.setTextSize(16);
                                             papp_app_income.addView(pdetails_fieldname);

                                             TextView pdetails_fieldvaluee= new TextView(mCon);
                                             pdetails_fieldvaluee.setLayoutParams(params);
                                             pdetails_fieldvaluee.setTypeface(custom_font);
                                             pdetails_fieldvaluee.setText(b);
                                             pdetails_fieldvaluee.setTextSize(16);
                                             papp_app_income1.addView(pdetails_fieldvaluee);

                                         }

                                     }
*/
                               /*      for(int i=0;i<app_Exit.length();i++){

                                         JSONArray ja = app_Exit.getJSONArray(i);

                                         for(int k=0;k<ja.length();k++){
                                             JSONObject app_Exit_oj = ja.getJSONObject(i);
                                             String a  =app_Exit_oj.getString(Params.fieldname);
                                             String b  = app_Exit_oj.getString(Params.fieldvalue);

                                             Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                     ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                             params.setMargins(0, 10, 0, 0);

                                             TextView pdetails_fieldname = new TextView(mCon);
                                             pdetails_fieldname.setLayoutParams(params);
                                             pdetails_fieldname.setTypeface(custom_font);
                                             pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.primary));
                                             pdetails_fieldname.setText(a);
                                             pdetails_fieldname.setTextSize(16);
                                             papp_app_Exit.addView(pdetails_fieldname);

                                             TextView pdetails_fieldvaluee= new TextView(mCon);
                                             pdetails_fieldvaluee.setLayoutParams(params);
                                             pdetails_fieldvaluee.setTypeface(custom_font);
                                             pdetails_fieldvaluee.setText(b);
                                             pdetails_fieldvaluee.setTextSize(16);
                                             papp_app_Exit1.addView(pdetails_fieldvaluee);

                                         }

                                     }*/


                                }else{

                                }

                                if(pco1_oj.getBoolean(Params.status)){

                                    Ly_pco1_oj_textview.setVisibility(View.VISIBLE);
                                    card_view_doc_typename12.setVisibility(View.VISIBLE);
                                    Objs.a.OutfitNormalFontStyle(mCon, R.id.pco1_oj_textview);
                                    JSONArray app_User = pco1_oj.getJSONArray(Params.user_info);
                                    JSONArray app_Current = pco1_oj.getJSONArray(Params.Current_Address);
                                    JSONArray app_Off = pco1_oj.getJSONArray(Params.Office_Address);
                                    JSONArray app_Employ = pco1_oj.getJSONArray(Params.Employment_Details);
                                    JSONArray app_income = pco1_oj.getJSONArray(Params.Income);
                                    JSONArray app_Exit = pco1_oj.getJSONArray(Params.Existing_Loan);


                                    for(int i=0;i<app_User.length();i++){

                                        JSONObject app_User_oj = app_User.getJSONObject(i);
                                        String a  =app_User_oj.getString(Params.fieldname);
                                        String b  = app_User_oj.getString(Params.fieldvalue);

                                       // Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco1_app_User.addView(pdetails_fieldname);
                                        // pco1_app_User.setOrientation(LinearLayout.HORIZONTAL);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco1_app_User1.addView(pdetails_fieldvaluee);
                                        //    pco1_app_User.setOrientation(LinearLayout.HORIZONTAL);

                                    }

                                    for(int i=0;i<app_Current.length();i++){

                                        JSONObject app_Current_oj = app_Current.getJSONObject(i);
                                        String a  =app_Current_oj.getString(Params.fieldname);
                                        String b  = app_Current_oj.getString(Params.fieldvalue);

                                       // Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco1_app_Current.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco1_app_Current1.addView(pdetails_fieldvaluee);

                                    }
                                    for(int i=0;i<app_Off.length();i++){

                                        JSONObject app_Off_oj = app_Off.getJSONObject(i);
                                        String a  =app_Off_oj.getString(Params.fieldname);
                                        String b  = app_Off_oj.getString(Params.fieldvalue);

                                        //Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco1_app_Off.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco1_app_Off1.addView(pdetails_fieldvaluee);

                                    }

                                    for(int i=0;i<app_Employ.length();i++){

                                        JSONObject app_Employ_oj = app_Employ.getJSONObject(i);
                                        String a  =app_Employ_oj.getString(Params.fieldname);
                                        String b  = app_Employ_oj.getString(Params.fieldvalue);

                                     //   Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco1_app_Employ.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco1_app_Employ1.addView(pdetails_fieldvaluee);

                                    }

                                    /* for(int i=0;i<app_income.length();i++){

                                         JSONArray ja = app_income.getJSONArray(i);

                                         for(int k=0;k<ja.length();k++){
                                             JSONObject app_income_oj = ja.getJSONObject(i);
                                             String a  =app_income_oj.getString(Params.fieldname);
                                             String b  = app_income_oj.getString(Params.fieldvalue);

                                             Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                     ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                             params.setMargins(0, 10, 0, 0);

                                             TextView pdetails_fieldname = new TextView(mCon);
                                             pdetails_fieldname.setLayoutParams(params);
                                             pdetails_fieldname.setTypeface(custom_font);
                                             pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.primary));
                                             pdetails_fieldname.setText(a);
                                             pdetails_fieldname.setTextSize(16);
                                             papp_app_income.addView(pdetails_fieldname);

                                             TextView pdetails_fieldvaluee= new TextView(mCon);
                                             pdetails_fieldvaluee.setLayoutParams(params);
                                             pdetails_fieldvaluee.setTypeface(custom_font);
                                             pdetails_fieldvaluee.setText(b);
                                             pdetails_fieldvaluee.setTextSize(16);
                                             papp_app_income1.addView(pdetails_fieldvaluee);

                                         }

                                     }
*/
                               /*      for(int i=0;i<app_Exit.length();i++){

                                         JSONArray ja = app_Exit.getJSONArray(i);

                                         for(int k=0;k<ja.length();k++){
                                             JSONObject app_Exit_oj = ja.getJSONObject(i);
                                             String a  =app_Exit_oj.getString(Params.fieldname);
                                             String b  = app_Exit_oj.getString(Params.fieldvalue);

                                             Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                     ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                             params.setMargins(0, 10, 0, 0);

                                             TextView pdetails_fieldname = new TextView(mCon);
                                             pdetails_fieldname.setLayoutParams(params);
                                             pdetails_fieldname.setTypeface(custom_font);
                                             pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.primary));
                                             pdetails_fieldname.setText(a);
                                             pdetails_fieldname.setTextSize(16);
                                             papp_app_Exit.addView(pdetails_fieldname);

                                             TextView pdetails_fieldvaluee= new TextView(mCon);
                                             pdetails_fieldvaluee.setLayoutParams(params);
                                             pdetails_fieldvaluee.setTypeface(custom_font);
                                             pdetails_fieldvaluee.setText(b);
                                             pdetails_fieldvaluee.setTextSize(16);
                                             papp_app_Exit1.addView(pdetails_fieldvaluee);

                                         }

                                     }*/

                                }else{

                                }

                                if(pco2_oj.getBoolean(Params.status)){

                                    Ly_pco2_oj_textview.setVisibility(View.VISIBLE);
                                    card_view_doc_typename2.setVisibility(View.VISIBLE);
                                    Objs.a.OutfitNormalFontStyle(mCon, R.id.pco2_oj_textview);

                                    JSONArray app_User = pco2_oj.getJSONArray(Params.user_info);
                                    JSONArray app_Current = pco2_oj.getJSONArray(Params.Current_Address);
                                    JSONArray app_Off = pco2_oj.getJSONArray(Params.Office_Address);
                                    JSONArray app_Employ = pco2_oj.getJSONArray(Params.Employment_Details);
                                    JSONArray app_income = pco2_oj.getJSONArray(Params.Income);
                                    JSONArray app_Exit = pco2_oj.getJSONArray(Params.Existing_Loan);






                                    for(int i=0;i<app_User.length();i++){

                                        JSONObject app_User_oj = app_User.getJSONObject(i);
                                        String a  =app_User_oj.getString(Params.fieldname);
                                        String b  = app_User_oj.getString(Params.fieldvalue);

                                      //  Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco2_app_User.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco2_app_User1.addView(pdetails_fieldvaluee);

                                    }

                                    for(int i=0;i<app_Current.length();i++){

                                        JSONObject app_Current_oj = app_Current.getJSONObject(i);
                                        String a  =app_Current_oj.getString(Params.fieldname);
                                        String b  = app_Current_oj.getString(Params.fieldvalue);

                                    //    Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco2_app_Current.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco2_app_Current1.addView(pdetails_fieldvaluee);

                                    }
                                    for(int i=0;i<app_Off.length();i++){

                                        JSONObject app_Off_oj = app_Off.getJSONObject(i);
                                        String a  =app_Off_oj.getString(Params.fieldname);
                                        String b  = app_Off_oj.getString(Params.fieldvalue);

                                      //  Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco2_app_Off.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco2_app_Off1.addView(pdetails_fieldvaluee);

                                    }

                                    for(int i=0;i<app_Employ.length();i++){

                                        JSONObject app_Employ_oj = app_Employ.getJSONObject(i);
                                        String a  =app_Employ_oj.getString(Params.fieldname);
                                        String b  = app_Employ_oj.getString(Params.fieldvalue);

                                     //   Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco2_app_Employ.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco2_app_Employ1.addView(pdetails_fieldvaluee);

                                    }

                                    /* for(int i=0;i<app_income.length();i++){

                                         JSONArray ja = app_income.getJSONArray(i);

                                         for(int k=0;k<ja.length();k++){
                                             JSONObject app_income_oj = ja.getJSONObject(i);
                                             String a  =app_income_oj.getString(Params.fieldname);
                                             String b  = app_income_oj.getString(Params.fieldvalue);

                                             Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                     ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                             params.setMargins(0, 10, 0, 0);

                                             TextView pdetails_fieldname = new TextView(mCon);
                                             pdetails_fieldname.setLayoutParams(params);
                                             pdetails_fieldname.setTypeface(custom_font);
                                             pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.primary));
                                             pdetails_fieldname.setText(a);
                                             pdetails_fieldname.setTextSize(16);
                                             papp_app_income.addView(pdetails_fieldname);

                                             TextView pdetails_fieldvaluee= new TextView(mCon);
                                             pdetails_fieldvaluee.setLayoutParams(params);
                                             pdetails_fieldvaluee.setTypeface(custom_font);
                                             pdetails_fieldvaluee.setText(b);
                                             pdetails_fieldvaluee.setTextSize(16);
                                             papp_app_income1.addView(pdetails_fieldvaluee);

                                         }

                                     }
*/
                               /*      for(int i=0;i<app_Exit.length();i++){

                                         JSONArray ja = app_Exit.getJSONArray(i);

                                         for(int k=0;k<ja.length();k++){
                                             JSONObject app_Exit_oj = ja.getJSONObject(i);
                                             String a  =app_Exit_oj.getString(Params.fieldname);
                                             String b  = app_Exit_oj.getString(Params.fieldvalue);

                                             Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                     ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                             params.setMargins(0, 10, 0, 0);

                                             TextView pdetails_fieldname = new TextView(mCon);
                                             pdetails_fieldname.setLayoutParams(params);
                                             pdetails_fieldname.setTypeface(custom_font);
                                             pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.primary));
                                             pdetails_fieldname.setText(a);
                                             pdetails_fieldname.setTextSize(16);
                                             papp_app_Exit.addView(pdetails_fieldname);

                                             TextView pdetails_fieldvaluee= new TextView(mCon);
                                             pdetails_fieldvaluee.setLayoutParams(params);
                                             pdetails_fieldvaluee.setTypeface(custom_font);
                                             pdetails_fieldvaluee.setText(b);
                                             pdetails_fieldvaluee.setTextSize(16);
                                             papp_app_Exit1.addView(pdetails_fieldvaluee);

                                         }

                                     }*/
                                }else{

                                }if(pco3_oj.getBoolean(Params.status)){

                                    Ly_pco3_oj_textview.setVisibility(View.VISIBLE);
                                    card_view_doc_typename.setVisibility(View.VISIBLE);
                                    Objs.a.OutfitNormalFontStyle(mCon, R.id.pco3_oj_textview);
                                    JSONArray app_User = pco3_oj.getJSONArray(Params.user_info);
                                    JSONArray app_Current = pco3_oj.getJSONArray(Params.Current_Address);
                                    JSONArray app_Off = pco3_oj.getJSONArray(Params.Office_Address);
                                    JSONArray app_Employ = pco3_oj.getJSONArray(Params.Employment_Details);
                                    JSONArray app_income = pco3_oj.getJSONArray(Params.Income);
                                    JSONArray app_Exit = pco3_oj.getJSONArray(Params.Existing_Loan);

                                    for(int i=0;i<app_User.length();i++){

                                        JSONObject app_User_oj = app_User.getJSONObject(i);
                                        String a  =app_User_oj.getString(Params.fieldname);
                                        String b  = app_User_oj.getString(Params.fieldvalue);

                                      //  Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco3_app_User.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco3_app_User1.addView(pdetails_fieldvaluee);

                                    }

                                    for(int i=0;i<app_Current.length();i++){

                                        JSONObject app_Current_oj = app_Current.getJSONObject(i);
                                        String a  =app_Current_oj.getString(Params.fieldname);
                                        String b  = app_Current_oj.getString(Params.fieldvalue);

                                     //   Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco3_app_Current.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco3_app_Current1.addView(pdetails_fieldvaluee);

                                    }
                                    for(int i=0;i<app_Off.length();i++){

                                        JSONObject app_Off_oj = app_Off.getJSONObject(i);
                                        String a  =app_Off_oj.getString(Params.fieldname);
                                        String b  = app_Off_oj.getString(Params.fieldvalue);

                                    //    Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco3_app_Off.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco3_app_Off1.addView(pdetails_fieldvaluee);

                                    }

                                    for(int i=0;i<app_Employ.length();i++){

                                        JSONObject app_Employ_oj = app_Employ.getJSONObject(i);
                                        String a  =app_Employ_oj.getString(Params.fieldname);
                                        String b  = app_Employ_oj.getString(Params.fieldvalue);

                                     //   Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco3_app_Employ.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco3_app_Employ1.addView(pdetails_fieldvaluee);

                                    }

                                    /* for(int i=0;i<app_income.length();i++){

                                         JSONArray ja = app_income.getJSONArray(i);

                                         for(int k=0;k<ja.length();k++){
                                             JSONObject app_income_oj = ja.getJSONObject(i);
                                             String a  =app_income_oj.getString(Params.fieldname);
                                             String b  = app_income_oj.getString(Params.fieldvalue);

                                             Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                     ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                             params.setMargins(0, 10, 0, 0);

                                             TextView pdetails_fieldname = new TextView(mCon);
                                             pdetails_fieldname.setLayoutParams(params);
                                             pdetails_fieldname.setTypeface(custom_font);
                                             pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.primary));
                                             pdetails_fieldname.setText(a);
                                             pdetails_fieldname.setTextSize(16);
                                             papp_app_income.addView(pdetails_fieldname);

                                             TextView pdetails_fieldvaluee= new TextView(mCon);
                                             pdetails_fieldvaluee.setLayoutParams(params);
                                             pdetails_fieldvaluee.setTypeface(custom_font);
                                             pdetails_fieldvaluee.setText(b);
                                             pdetails_fieldvaluee.setTextSize(16);
                                             papp_app_income1.addView(pdetails_fieldvaluee);

                                         }

                                     }
*/
                               /*      for(int i=0;i<app_Exit.length();i++){

                                         JSONArray ja = app_Exit.getJSONArray(i);

                                         for(int k=0;k<ja.length();k++){
                                             JSONObject app_Exit_oj = ja.getJSONObject(i);
                                             String a  =app_Exit_oj.getString(Params.fieldname);
                                             String b  = app_Exit_oj.getString(Params.fieldvalue);

                                             Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                     ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                             params.setMargins(0, 10, 0, 0);

                                             TextView pdetails_fieldname = new TextView(mCon);
                                             pdetails_fieldname.setLayoutParams(params);
                                             pdetails_fieldname.setTypeface(custom_font);
                                             pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.primary));
                                             pdetails_fieldname.setText(a);
                                             pdetails_fieldname.setTextSize(16);
                                             papp_app_Exit.addView(pdetails_fieldname);

                                             TextView pdetails_fieldvaluee= new TextView(mCon);
                                             pdetails_fieldvaluee.setLayoutParams(params);
                                             pdetails_fieldvaluee.setTypeface(custom_font);
                                             pdetails_fieldvaluee.setText(b);
                                             pdetails_fieldvaluee.setTextSize(16);
                                             papp_app_Exit1.addView(pdetails_fieldvaluee);

                                         }

                                     }*/

                                }else{

                                }

                                if(pco4_oj.getBoolean(Params.status)){

                                    Ly_pco4_oj_textview.setVisibility(View.VISIBLE);
                                    card_view_doc_typename4.setVisibility(View.VISIBLE);
                                    Objs.a.OutfitNormalFontStyle(mCon, R.id.pco4_oj_textview);

                                    JSONArray app_User = pco4_oj.getJSONArray(Params.user_info);
                                    JSONArray app_Current = pco4_oj.getJSONArray(Params.Current_Address);
                                    JSONArray app_Off = pco4_oj.getJSONArray(Params.Office_Address);
                                    JSONArray app_Employ = pco4_oj.getJSONArray(Params.Employment_Details);
                                    JSONArray app_income = pco4_oj.getJSONArray(Params.Income);
                                    JSONArray app_Exit = pco4_oj.getJSONArray(Params.Existing_Loan);

                                    for(int i=0;i<app_User.length();i++){

                                        JSONObject app_User_oj = app_User.getJSONObject(i);
                                        String a  =app_User_oj.getString(Params.fieldname);
                                        String b  = app_User_oj.getString(Params.fieldvalue);

                                     //   Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco4_app_User.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco4_app_User1.addView(pdetails_fieldvaluee);

                                    }

                                    for(int i=0;i<app_Current.length();i++){

                                        JSONObject app_Current_oj = app_Current.getJSONObject(i);
                                        String a  =app_Current_oj.getString(Params.fieldname);
                                        String b  = app_Current_oj.getString(Params.fieldvalue);

                                      //  Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco4_app_Current.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco4_app_Current1.addView(pdetails_fieldvaluee);

                                    }
                                    for(int i=0;i<app_Off.length();i++){

                                        JSONObject app_Off_oj = app_Off.getJSONObject(i);
                                        String a  =app_Off_oj.getString(Params.fieldname);
                                        String b  = app_Off_oj.getString(Params.fieldvalue);

                                      //  Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco4_app_Off.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco4_app_Off1.addView(pdetails_fieldvaluee);

                                    }

                                    for(int i=0;i<app_Employ.length();i++){

                                        JSONObject app_Employ_oj = app_Employ.getJSONObject(i);
                                        String a  =app_Employ_oj.getString(Params.fieldname);
                                        String b  = app_Employ_oj.getString(Params.fieldvalue);

                                    //    Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                        params.setMargins(0, 10, 0, 0);

                                        TextView pdetails_fieldname = new TextView(mCon);
                                        pdetails_fieldname.setLayoutParams(params);
                                        pdetails_fieldname.setTypeface(custom_font);
                                        pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.colorPrimary));
                                        pdetails_fieldname.setText(a);
                                        pdetails_fieldname.setTextSize(16);
                                        pco4_app_Employ.addView(pdetails_fieldname);

                                        TextView pdetails_fieldvaluee= new TextView(mCon);
                                        pdetails_fieldvaluee.setLayoutParams(params);
                                        pdetails_fieldvaluee.setTypeface(custom_font);
                                        pdetails_fieldvaluee.setText(b);
                                        pdetails_fieldvaluee.setTextSize(16);
                                        pco4_app_Employ1.addView(pdetails_fieldvaluee);

                                    }

                                    /* for(int i=0;i<app_income.length();i++){

                                         JSONArray ja = app_income.getJSONArray(i);

                                         for(int k=0;k<ja.length();k++){
                                             JSONObject app_income_oj = ja.getJSONObject(i);
                                             String a  =app_income_oj.getString(Params.fieldname);
                                             String b  = app_income_oj.getString(Params.fieldvalue);

                                             Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                     ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                             params.setMargins(0, 10, 0, 0);

                                             TextView pdetails_fieldname = new TextView(mCon);
                                             pdetails_fieldname.setLayoutParams(params);
                                             pdetails_fieldname.setTypeface(custom_font);
                                             pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.primary));
                                             pdetails_fieldname.setText(a);
                                             pdetails_fieldname.setTextSize(16);
                                             papp_app_income.addView(pdetails_fieldname);

                                             TextView pdetails_fieldvaluee= new TextView(mCon);
                                             pdetails_fieldvaluee.setLayoutParams(params);
                                             pdetails_fieldvaluee.setTypeface(custom_font);
                                             pdetails_fieldvaluee.setText(b);
                                             pdetails_fieldvaluee.setTextSize(16);
                                             papp_app_income1.addView(pdetails_fieldvaluee);

                                         }

                                     }
*/
                               /*      for(int i=0;i<app_Exit.length();i++){

                                         JSONArray ja = app_Exit.getJSONArray(i);

                                         for(int k=0;k<ja.length();k++){
                                             JSONObject app_Exit_oj = ja.getJSONObject(i);
                                             String a  =app_Exit_oj.getString(Params.fieldname);
                                             String b  = app_Exit_oj.getString(Params.fieldvalue);

                                             Typeface custom_font = Typeface.createFromAsset(mCon.getAssets(), "AlegreyaSans-Regular.ttf");

                                             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                     ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                             params.setMargins(0, 10, 0, 0);

                                             TextView pdetails_fieldname = new TextView(mCon);
                                             pdetails_fieldname.setLayoutParams(params);
                                             pdetails_fieldname.setTypeface(custom_font);
                                             pdetails_fieldname.setTextColor(ContextCompat.getColor(mCon, R.color.primary));
                                             pdetails_fieldname.setText(a);
                                             pdetails_fieldname.setTextSize(16);
                                             papp_app_Exit.addView(pdetails_fieldname);

                                             TextView pdetails_fieldvaluee= new TextView(mCon);
                                             pdetails_fieldvaluee.setLayoutParams(params);
                                             pdetails_fieldvaluee.setTypeface(custom_font);
                                             pdetails_fieldvaluee.setText(b);
                                             pdetails_fieldvaluee.setTextSize(16);
                                             papp_app_Exit1.addView(pdetails_fieldvaluee);

                                         }

                                     }*/

                                }else{

                                }




                            }else{

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    public void hide(View view) {
        Ly_papp_oj= (LinearLayout)findViewById(R.id.Ly_papp_oj);
        img_drop= (ImageView)findViewById(R.id.img_drop);
        if (Ly_papp_oj .getVisibility() == View.VISIBLE){
            Ly_papp_oj.setVisibility(View.GONE);
            img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        } else {
            Ly_papp_oj.setVisibility(View.VISIBLE);
            img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_minus));
        }
    }

    public void hide1(View view) {
        Ly_pco1_oj= (LinearLayout)findViewById(R.id.Ly_pco1_oj);
        pco1_img_drop= (ImageView)findViewById(R.id.pco1_img_drop);
        if (Ly_pco1_oj .getVisibility() == View.VISIBLE) {
            Ly_pco1_oj.setVisibility(View.GONE);
            pco1_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        } else {
            Ly_pco1_oj.setVisibility(View.VISIBLE);
            pco1_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_minus));
        }
    }
    public void hide2(View view) {
        Ly_pco2_oj= (LinearLayout)findViewById(R.id.Ly_pco2_oj);
        pco2_img_drop= (ImageView)findViewById(R.id.pco2_img_drop);
        if (Ly_pco2_oj .getVisibility() == View.VISIBLE) {
            Ly_pco2_oj.setVisibility(View.GONE);
            pco2_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        } else {
            Ly_pco2_oj.setVisibility(View.VISIBLE);
            pco2_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_minus));
        }
    }
    public void hide3(View view) {
        Ly_pco3_oj= (LinearLayout)findViewById(R.id.Ly_pco3_oj);
        pco3_img_drop= (ImageView)findViewById(R.id.pco3_img_drop);
        if (Ly_pco3_oj .getVisibility() == View.VISIBLE) {
            Ly_pco3_oj.setVisibility(View.GONE);
            pco3_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        } else {
            Ly_pco3_oj.setVisibility(View.VISIBLE);
            pco3_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_minus));
        }
    }
    public void hide4(View view) {
        Ly_pco4_oj = (LinearLayout) findViewById(R.id.Ly_pco4_oj);
        pco4_img_drop = (ImageView) findViewById(R.id.pco4_img_drop);
        if (Ly_pco4_oj.getVisibility() == View.VISIBLE) {
            Ly_pco4_oj.setVisibility(View.GONE);
            pco4_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        } else {
            Ly_pco4_oj.setVisibility(View.VISIBLE);
            pco4_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_minus));
        }

    }

    public void hide_new(View view) {
        Ly_pmay = (LinearLayout) findViewById(R.id.Ly_pmay);
        pay_img_drop = (ImageView) findViewById(R.id.pay_img_drop);
        if (Ly_pmay.getVisibility() == View.VISIBLE) {
            Ly_pmay.setVisibility(View.GONE);
            pay_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        } else {
            Ly_pmay.setVisibility(View.VISIBLE);
            pay_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_minus));
        }

    }
    public void hide_new_(View view) {
        Ly_pdetails = (LinearLayout) findViewById(R.id.Ly_pdetails);
        pdetails_img_drop = (ImageView) findViewById(R.id.pdetails_img_drop);
        if (Ly_pdetails.getVisibility() == View.VISIBLE) {
            Ly_pdetails.setVisibility(View.GONE);
            pdetails_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_plus));
        } else {
            Ly_pdetails.setVisibility(View.VISIBLE);
            pdetails_img_drop.setImageDrawable(getResources().getDrawable(R.drawable.ic_minus));
        }

    }
}

