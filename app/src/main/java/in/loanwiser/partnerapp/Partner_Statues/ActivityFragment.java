package in.loanwiser.partnerapp.Partner_Statues;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import com.google.android.material.navigation.NavigationView;


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
import in.loanwiser.partnerapp.Step_Changes_Screen.Pay_Out_Screen;
import in.loanwiser.partnerapp.User_Account.BankDetails;
import in.loanwiser.partnerapp.User_Account.ProfileSettings;
import in.loanwiser.partnerapp.User_Account.Welcome_Page;


public class ActivityFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    public ActivityFragment() {
        // Required empty public constructor
    }

    public static ActivityFragment newInstance(String param1, String param2) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    SharedPreferences pref; // 0 - for private mode

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private AppCompatButton navigate,navigate1,button_website,button_businessview;
    private SliderLayout mDemoSlider;
    private ActivityFragment mcon =this;
    private LinearLayout chat;
    private RecyclerView recycler_view,recycler_view_health_ass,
            recycler_view_document_,recycler_view_share,ask_recycler_view;
    ArrayList<Suggestion_item_freqent> items;
    ArrayList<Health_Assesment_item_freqent> items1;
    ArrayList<Document_item_freqent> items2;
    ArrayList<post_item_freqent> items3;
    ArrayList<Ask_item_freqent> items_ask;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    Resent_Lead_Statues adapter;
    Health_Assement_Adapter adapter1;
    Document_Adapter adapter2;
    Post_share_Statues adapter3;
    Ask_Lead_Statues adapter_ask;
    FrameLayout framelayout;

    AppCompatButton my_earnings,my_leads;
    DrawerLayout drawer;
    public static final String b2b_user_id1 = "b2b_uer_id";
    String b2b_user_id;
    private AppCompatTextView profile,recent_leads,Document_cheklist,fhas,share_material;

    LinearLayout firstlay,secondlay,thirdlay,Ly_allocate,ly_helth_assement_,ly_document_assement_,network_stat,
            view_all_ly, ask_Ly_allocate,asklay,view_all_ly1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_activity, container, false);

        mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);

        items = new ArrayList<>();
        items1 = new ArrayList<>();
        items2 = new ArrayList<>();
        items3 = new ArrayList<>();
        items_ask = new ArrayList<>();
        HashMap<String,String> url_maps = new HashMap<String, String>();

        pref = getActivity().getSharedPreferences("MyPref", 0);

     /*   url_maps.put("Home Loan", "http://cscapi.propwiser.com/mobile/images/home_loan.png");
        // url_maps.put("Loan Against Property", "http://www.expertsconsultancyservices.com/img/content/services/Loan%20against%20Property.jpg");
        url_maps.put("Personal Loan", "http://cscapi.propwiser.com/mobile/images/Loan_aganst_property.png");
        url_maps.put("Loan Against Property", "http://cscapi.propwiser.com/mobile/images/Busines_loan.png");*/

      /*  url_maps.put("Business Loan", "http://cscapi.propwiser.com/mobile/images/Our_Banking_Partnersl.png");
        url_maps.put("Vehicle Loan", "http://cscapi.propwiser.com/mobile/images/Our_Banking_Partnersl.png");
        url_maps.put("Vehicle Loan1", "http://cscapi.propwiser.com/mobile/images/Our_Banking_Partnersl.png");*/

        url_maps.put("Home Loan", "https://cscapi.loanwiser.in/mobile/images/home_loan.png");
        url_maps.put("Loan Against Property", "https://cscapi.loanwiser.in/mobile/images/Loan_aganst_property.png");
        url_maps.put("Personal Loan", "https://cscapi.loanwiser.in/mobile/images/Personal_loan.png");
        url_maps.put("Business Loan", "https://cscapi.loanwiser.in/mobile/images/Busines_loan.png");
        url_maps.put("Vehicle Loan", "https://cscapi.loanwiser.in/mobile/images/vehicle_Loan.png");
        url_maps.put("Our Banks", "https://cscapi.loanwiser.in/mobile/images/Our_Banking_Partnersl.png");
        // Ly_UI(view);
        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        ask_recycler_view = (RecyclerView)view.findViewById(R.id.ask_recycler_view);
        recycler_view_health_ass = (RecyclerView)view.findViewById(R.id.recycler_view_health_ass);
        recycler_view_document_ = (RecyclerView)view.findViewById(R.id.recycler_view_document_);
        recycler_view_share = (RecyclerView)view.findViewById(R.id.recycler_view_share);
        framelayout = (FrameLayout) view.findViewById(R.id.framelayout);



        firstlay=view.findViewById(R.id.firstlay);
        secondlay=view.findViewById(R.id.secondlay);
        thirdlay=view.findViewById(R.id.thirdlay);
        firstlay=view.findViewById(R.id.firstlay);
        Ly_allocate=view.findViewById(R.id.Ly_allocate);
        ly_helth_assement_=view.findViewById(R.id.ly_helth_assement_);
        ly_document_assement_=view.findViewById(R.id.ly_document_assement_);
        network_stat=view.findViewById(R.id.network_stat);
        share_material=view.findViewById(R.id.share_material);

        button_businessview=view.findViewById(R.id.button_businessview);
        button_website=view.findViewById(R.id.button_website);

        my_earnings = (AppCompatButton) view.findViewById(R.id.my_earnings);
        my_leads = (AppCompatButton) view.findViewById(R.id.my_leads);
         drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        view_all_ly = (LinearLayout) view.findViewById(R.id.view_all_ly);
        view_all_ly1 = (LinearLayout) view.findViewById(R.id.view_all_ly1);


        ask_Ly_allocate = (LinearLayout) view.findViewById(R.id.ask_Ly_allocate);
        asklay = (LinearLayout) view.findViewById(R.id.asklay);

        adapter = new Resent_Lead_Statues(getActivity(), items);
        adapter_ask = new Ask_Lead_Statues(getActivity(), items_ask);
        adapter1 = new Health_Assement_Adapter(getActivity(), items1);
        adapter2 = new Document_Adapter(getActivity(), items2);
        adapter3 = new Post_share_Statues(getActivity(), items3);

        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ask_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_view_health_ass.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recycler_view_document_.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recycler_view_share.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "segoe_ui.ttf");
        recent_leads=view.findViewById(R.id.recent_leads);
        Document_cheklist=view.findViewById(R.id.Document_cheklist);
        fhas=view.findViewById(R.id.fhas);
      //  recent_leads.setTypeface(font);
      //  Document_cheklist.setTypeface(font);
      //  fhas.setTypeface(font);

        button_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebsiteActivity.class);
                startActivity(intent);
            }
        });

        button_businessview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),BusinessCardActivity.class);
                startActivity(intent);
            }
        });



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

        view_all_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Ask_Dashboard_Activity.class);
                startActivity(intent);

            }
        });

        view_all_ly1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Dashboard_Activity.class);
                startActivity(intent);
            }
        });

         b2b_user_id =  pref.getString(b2b_user_id1, null);


        if(isConnected()==false){
            network_stat.setVisibility(View.VISIBLE);
            firstlay.setVisibility(View.GONE);
            secondlay.setVisibility(View.GONE);
            thirdlay.setVisibility(View.GONE);
            Ly_allocate.setVisibility(View.GONE);
            ly_helth_assement_.setVisibility(View.GONE);
            ly_document_assement_.setVisibility(View.GONE);
        }


        Get_Allocation_List(view);
        Health_Assement_List(view);
        Get_Ask_List(view);
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


    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
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
                               /* JSONObject ja1 = response.getJSONObject("balance_coins");
                                String credit_coin = ja1.getString("current_wallet_coins");

                                SharedPreferences pref1 = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref1.edit();
                                editor.putString("credit_coin", credit_coin);
                                editor.commit();*/
/*
                               String credit_coin1 =  pref1.getString("credit_coin", null);
                                Log.e("credit_coin1",credit_coin1);*/

                                if (ja.length()>0){

                                    view_all_ly1.setVisibility(View.VISIBLE);
                                    Ly_allocate.setVisibility(View.VISIBLE);
                                    firstlay.setVisibility(View.VISIBLE);
                                    for(int i = 0;i<ja.length();i++){

                                        if( i < 3)
                                        {
                                            JSONObject J = ja.getJSONObject(i);
                                            items.add(new Suggestion_item_freqent( J.getString("user_id"), J.getString("user_id"),J.getString("username"),
                                                    J.getString("loan_type"),J.getString("loan_amount"),J.getString("status_disp")
                                            ));
                                            adapter.notifyDataSetChanged();

                                            view_all_ly1.setVisibility(View.VISIBLE);
                                        }
                                    }
                                    recycler_view.setAdapter(adapter);

                                }else {
                                    view_all_ly1.setVisibility(View.GONE);
                                    Ly_allocate.setVisibility(View.GONE);
                                    firstlay.setVisibility(View.GONE);

                                    Objs.a.ShowHideNoItems(getActivity(),true);
                                }
                            }else
                            {
                                view_all_ly1.setVisibility(View.GONE);
                                Ly_allocate.setVisibility(View.GONE);
                                firstlay.setVisibility(View.GONE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response",String.valueOf(error));
               // Toast.makeText(getActivity(),String.valueOf(error),Toast.LENGTH_SHORT).show();

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
    private void Get_Ask_List(View view) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("b2b_id", Pref.getID(getActivity()));
            J.put("type", "1");
            //J.put("b2b_id", "49529");


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("the b2b_id",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.getAsklist, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("the ask_recent",response.toString());

                        try {
                            if(response.getString("status").equals("success")){

                                JSONArray ja = response.getJSONArray("data");
                               /* JSONObject ja1 = response.getJSONObject("balance_coins");
                                String credit_coin = ja1.getString("current_wallet_coins");

                                SharedPreferences pref1 = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref1.edit();
                                editor.putString("credit_coin", credit_coin);
                                editor.commit();*/
/*
                               String credit_coin1 =  pref1.getString("credit_coin", null);
                                Log.e("credit_coin1",credit_coin1);*/

                                if (ja.length()>0){
                                    for(int i = 0;i<ja.length();i++){

                                        if( i < 3)
                                        {
                                            JSONObject J = ja.getJSONObject(i);
                                            items_ask.add(new Ask_item_freqent( J.getString("name"), J.getString("applicant"),J.getString("status_disp"),
                                                    J.getString("created_at"),J.getString("user_id"),J.getString("updated_at"),
                                                    J.getString("app_id"),J.getString("request_by"),J.getString("doc_typename"),J.getString("notes"),
                                                    J.getString("transaction_id"),J.getString("doc_classname"),J.getString("legaldoc_id"),J.getString("id")));
                                            adapter_ask.notifyDataSetChanged();
                                            view_all_ly.setVisibility(View.VISIBLE);
                                        }

                                    }
                                    ask_recycler_view.setAdapter(adapter_ask);

                                }else {
                                    ask_recycler_view.setVisibility(View.GONE);

                                    asklay.setVisibility(View.GONE);
                                    ask_Ly_allocate.setVisibility(View.GONE);
                                    Objs.a.ShowHideNoItems(getActivity(),true);
                                }
                            }else
                            {
                                ask_recycler_view.setVisibility(View.GONE);
                                view_all_ly.setVisibility(View.GONE);
                                asklay.setVisibility(View.GONE);
                                ask_Ly_allocate.setVisibility(View.GONE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response",String.valueOf(error));
                // Toast.makeText(getActivity(),String.valueOf(error),Toast.LENGTH_SHORT).show();

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

    private void Health_Assement_List(View view) {
        final JSONObject jsonObject =new JSONObject();
        JSONObject J= null;

        try {
            J =new JSONObject();

            J.put("b2buser_id", Pref.getID(getActivity()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("the helth request",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.reference_board , J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("the helth Assement",response.toString());

                        try {



                                JSONArray ja = response.getJSONArray("finance");
                                JSONArray ja1 = response.getJSONArray("checklist");
                                JSONObject ja_post =response.getJSONObject("post_share");
                                JSONArray ja2 = ja_post.getJSONArray("data");

                                Log.e("the post share",ja2.toString());
                                if (ja.length()>0){
                                    for(int i = 0;i<ja.length();i++){

                                        JSONObject J = ja.getJSONObject(i);
                                        items1.add(new Health_Assesment_item_freqent( J.getString("heading"), J.getString("content"),J.getString("icon"),J.getString("color_code")));
                                        adapter1.notifyDataSetChanged();

                                    }
                                    recycler_view_health_ass.setAdapter(adapter1);

                                }else {
                                    Objs.a.ShowHideNoItems(getActivity(),true);
                                }

                            if (ja1.length()>0){
                                for(int i = 0;i<ja1.length();i++){

                                    JSONObject J = ja1.getJSONObject(i);
                                    items2.add(new Document_item_freqent( J.getString("name"), J.getString("icon"),J.getString("loantype_id")));
                                    adapter2.notifyDataSetChanged();

                                }
                                recycler_view_document_.setAdapter(adapter2);

                            }else {
                                Objs.a.ShowHideNoItems(getActivity(),true);
                            }

                            if (ja2.length()>0){
                                for(int i = 0;i<ja2.length();i++){

                                    JSONObject J = ja2.getJSONObject(i);
                                    items3.add(new post_item_freqent( J.getString("title"), J.getString("post_url"),J.getString("content")));
                                    adapter3.notifyDataSetChanged();

                                }
                                recycler_view_share.setAdapter(adapter3);

                            }else {
                                Objs.a.ShowHideNoItems(getActivity(),true);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response",String.valueOf(error));
               // Toast.makeText(getActivity(),String.valueOf(error),Toast.LENGTH_SHORT).show();

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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.nav_payout
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Objs.ac.StartActivity(getActivity(), ProfileSettings.class);
        } else if (id == R.id.nav_bank) {
            Objs.ac.StartActivity(getActivity(), BankDetails.class);
        } else if (id == R.id.nav_payout) {
            Objs.ac.StartActivity(getActivity(), Pay_Out_Screen.class);
        }else if (id == R.id.nav_logout) {
          //  ExitAlert(getActivity());
        }

        /*
        else if (id == R.id.nav_call) {
            Objs.ac.StartActivity(mCon, CustomerCare.class);
        } */


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




 


}
