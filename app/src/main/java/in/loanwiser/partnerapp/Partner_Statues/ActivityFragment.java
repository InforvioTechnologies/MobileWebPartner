package in.loanwiser.partnerapp.Partner_Statues;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import in.loanwiser.partnerapp.My_Earnings.My_Earnings;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.Payment.PaymentActivity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.Step_Changes_Screen.Lead_Crration_Activity;


public class ActivityFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    public ActivityFragment() {
        // Required empty public constructor
    }

    public static ActivityFragment newInstance(String param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private AppCompatTextView profile;
    private AppCompatButton navigate,navigate1;
    private SliderLayout mDemoSlider;
    private ActivityFragment mcon =this;
    private LinearLayout chat;
    private RecyclerView recycler_view;
    ArrayList<Suggestion_item_freqent> items;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    Resent_Lead_Statues adapter;

    AppCompatButton my_earnings,my_leads;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);

        items = new ArrayList<>();
        HashMap<String,String> url_maps = new HashMap<String, String>();

     /*   url_maps.put("Home Loan", "http://cscapi.propwiser.com/mobile/images/home_loan.png");
        // url_maps.put("Loan Against Property", "http://www.expertsconsultancyservices.com/img/content/services/Loan%20against%20Property.jpg");
        url_maps.put("Personal Loan", "http://cscapi.propwiser.com/mobile/images/Loan_aganst_property.png");
        url_maps.put("Loan Against Property", "http://cscapi.propwiser.com/mobile/images/Busines_loan.png");*/

        url_maps.put("Business Loan", "http://cscapi.propwiser.com/mobile/images/Our_Banking_Partnersl.png");
        url_maps.put("Vehicle Loan", "http://cscapi.propwiser.com/mobile/images/Our_Banking_Partnersl.png");
        url_maps.put("Vehicle Loan1", "http://cscapi.propwiser.com/mobile/images/Our_Banking_Partnersl.png");

       // Ly_UI(view);
        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);

        my_earnings = (AppCompatButton) view.findViewById(R.id.my_earnings);
        my_leads = (AppCompatButton) view.findViewById(R.id.my_leads);

        adapter = new Resent_Lead_Statues(getActivity(), items);
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        my_earnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), My_Earnings.class);
                startActivity(intent);
            }
        });

        my_leads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Dashboard_Activity.class);
                startActivity(intent);
            }
        });


        Get_Allocation_List(view);
        for(final String name : url_maps.keySet()){
            // TextSliderView textSliderView = new TextSliderView(this);
            DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    // .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
                    /*.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {


                                Intent intent = new Intent(Dashboard_Activity.this,View_More.class);
                                startActivity(intent);


                        }
                    });*/

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        return view;


    }

    private void Get_Allocation_List(View view) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2buser_id", Pref.getID(getActivity()));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.latest_lead_updates, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("the recent",response.toString());

                        try {
                            if(response.getString("status").equals("success")){


                                JSONArray ja = response.getJSONArray("data");
                                if (ja.length()>0){
                                    for(int i = 0;i<ja.length();i++){

                                        JSONObject J = ja.getJSONObject(i);
                                        items.add(new Suggestion_item_freqent( J.getString("user_id"), J.getString("user_id"),J.getString("username"),
                                                J.getString("loan_type"),J.getString("loan_amount"),J.getString("status_disp")));
                                        adapter.notifyDataSetChanged();

                                    }
                                    recycler_view.setAdapter(adapter);

                                }else {
                                    Objs.a.ShowHideNoItems(getActivity(),true);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response",String.valueOf(error));
                Toast.makeText(getActivity(),String.valueOf(error),Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("content-type", "application/json");
                return headers;
            }
        };

        Log.d("Response","Login Activity_Exitalert Login_POST33333333333333333333333333333333333");
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
