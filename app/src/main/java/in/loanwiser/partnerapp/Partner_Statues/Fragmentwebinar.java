package in.loanwiser.partnerapp.Partner_Statues;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.Documents.Applicant_Doc_Details_revamp;
import in.loanwiser.partnerapp.R;


public class Fragmentwebinar extends Fragment implements CompoundButton.OnCheckedChangeListener{

    private AlertDialog progressDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private RecyclerView ask_recycler_view,recycler_view,live_recycler_view;
    Resent_webinars adapter;
    Resent_webinars_completed adapter1;
    Resent_webinars_live adapterlive;
    private ViewPager viewPager;
    ArrayList<Webinar_item_freqent> items;
    ArrayList<Webinar_item_freqent_completed> items_completed;
    ArrayList<Webinar_item_freqent_live> items_live;
    LinearLayout no_webinars,no_past_webinars,no_live_webinars,Live_webinar,upcomming_webinar,past_webinar_ly;
 //   ArrayList<> items;
    public Fragmentwebinar(ViewPager viewPager) {
        this.viewPager=viewPager;
        // Required empty public constructor
    }

    public Fragmentwebinar() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview= inflater.inflate(R.layout.fragment_webinar, container, false);

        items = new ArrayList<>();
        items_completed = new ArrayList<>();
        items_live = new ArrayList<>();
        live_recycler_view = (RecyclerView)rootview.findViewById(R.id.live_recycler_view);
        ask_recycler_view = (RecyclerView)rootview.findViewById(R.id.ask_recycler_view);
        recycler_view = (RecyclerView)rootview.findViewById(R.id.recycler_view);

        no_webinars = (LinearLayout) rootview.findViewById(R.id.no_webinars);
        no_past_webinars = (LinearLayout) rootview.findViewById(R.id.no_past_webinars);
        no_live_webinars = (LinearLayout) rootview.findViewById(R.id.no_live_webinars);

        Live_webinar = (LinearLayout) rootview.findViewById(R.id.Live_webinar);
        upcomming_webinar = (LinearLayout) rootview.findViewById(R.id.upcomming_webinar);
        past_webinar_ly = (LinearLayout) rootview.findViewById(R.id.past_webinar_ly);



        adapter = new Resent_webinars(getActivity(), items);
        adapter1 = new Resent_webinars_completed(getActivity(), items_completed);
        adapterlive = new Resent_webinars_live(getActivity(), items_live);

        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ask_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        live_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        Get_Allocation_List(rootview);

      //  adapter.notifyDataSetChanged();
      //  recycler_view.setAdapter(adapter);
      //  ask_recycler_view.setAdapter(adapter);

        return rootview;



    }


    private void Get_Allocation_List(View view) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("is_mobile", "1");
            J.put("b2b_userid", Pref.getID(getActivity()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("the request",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.getwebinars_b2b, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.e("the recent",response.toString());
                       // Log.e("the b2b_id",Pref.getID(getActivity()));

                        try {
                            if(response.getString("status").equals("success")){

                                JSONObject upcoming = response.getJSONObject("upcoming");
                                JSONArray ja = upcoming.getJSONArray("data");
                                String statues_is = upcoming.getString("status");

                                JSONObject live = response.getJSONObject("live");

                                JSONArray ja1_live = live.getJSONArray("data");
                                String statues_is_live = live.getString("status");

                                JSONObject completed = response.getJSONObject("completed");
                                JSONArray ja1 = completed.getJSONArray("data");
                                String statues_is1 = completed.getString("status");
                               /* JSONObject ja1 = response.getJSONObject("balance_coins");
                                String credit_coin = ja1.getString("current_wallet_coins");

                                SharedPreferences pref1 = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
                                SharedPreferences.Editor editor = pref1.edit();
                                editor.putString("credit_coin", credit_coin);
                                editor.commit();*/
/*
                               String credit_coin1 =  pref1.getString("credit_coin", null);
                                Log.e("credit_coin1",credit_coin1);*/

                                if(statues_is.equals("success"))
                                {
                                    if (ja.length()>0){
                                        no_webinars.setVisibility(View.GONE);
                                        ask_recycler_view.setVisibility(View.VISIBLE);
                                        for(int i = 0;i<ja.length();i++){

                                            JSONObject J = ja.getJSONObject(i);
                                            items_completed.add(new Webinar_item_freqent_completed( J.getString("title"), J.getString("description"),J.getString("scheduled_time"),
                                                    J.getString("url"),J.getString("thumbnail_url"),J.getString("is_registered"),Pref.getID(getActivity()),J.getString("id"),J.getString("session_end")));
                                            adapter1.notifyDataSetChanged();
                                            //  Log.e("the recent",items.toString());
                                        }
                                        ask_recycler_view.setAdapter(adapter1);
                                    }else {
                                        no_webinars.setVisibility(View.VISIBLE);
                                        ask_recycler_view.setVisibility(View.GONE);
                                    }

                                }else {
                                    no_webinars.setVisibility(View.VISIBLE);
                                    ask_recycler_view.setVisibility(View.GONE);
                                    upcomming_webinar.setVisibility(View.GONE);
                                  //  Objs.a.ShowHideNoItems(getActivity(),true);
                                }

                                if(statues_is1.equals("success"))
                                {
                                    if (ja1.length()>0){
                                        no_past_webinars.setVisibility(View.GONE);
                                        recycler_view.setVisibility(View.VISIBLE);
                                        for(int i = 0;i<ja1.length();i++){

                                            JSONObject J = ja1.getJSONObject(i);
                                            items.add(new Webinar_item_freqent( J.getString("title"), J.getString("description"),J.getString("scheduled_time"),
                                                    J.getString("url"),J.getString("thumbnail_url"),J.getString("is_registered"), Pref.getID(getActivity()),J.getString("id"),J.getString("session_end")));
                                            adapter.notifyDataSetChanged();
                                            //  Log.e("the recent",items.toString());
                                        }
                                        recycler_view.setAdapter(adapter);
                                    }else {
                                        no_past_webinars.setVisibility(View.VISIBLE);
                                        recycler_view.setVisibility(View.GONE);
                                    }

                                }else {
                                    no_past_webinars.setVisibility(View.VISIBLE);
                                    recycler_view.setVisibility(View.GONE);
                                   // Objs.a.ShowHideNoItems(getActivity(),true);
                                }

                                if(statues_is_live.equals("success"))
                                {
                                    if (ja1_live.length()>0){
                                        no_live_webinars.setVisibility(View.GONE);
                                        live_recycler_view.setVisibility(View.VISIBLE);
                                        for(int i = 0;i<ja1_live.length();i++){

                                            JSONObject J = ja1_live.getJSONObject(i);
                                            items_live.add(new Webinar_item_freqent_live( J.getString("title"), J.getString("description"),J.getString("scheduled_time"),
                                                    J.getString("url"),J.getString("thumbnail_url"),J.getString("is_registered"),Pref.getID(getActivity()),
                                                    J.getString("id"),J.getString("session_end")));
                                            adapterlive.notifyDataSetChanged();
                                            //  Log.e("the recent",items.toString());
                                        }
                                        live_recycler_view.setAdapter(adapterlive);
                                    }else {
                                        no_live_webinars.setVisibility(View.VISIBLE);
                                        live_recycler_view.setVisibility(View.GONE);
                                    }

                                }else {
                                    no_live_webinars.setVisibility(View.VISIBLE);
                                    live_recycler_view.setVisibility(View.GONE);
                                    Live_webinar.setVisibility(View.GONE);
                                    // Objs.a.ShowHideNoItems(getActivity(),true);
                                }


                            }else
                            {

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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
}