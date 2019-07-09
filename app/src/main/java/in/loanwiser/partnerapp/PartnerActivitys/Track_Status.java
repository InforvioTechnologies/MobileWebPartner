package in.loanwiser.partnerapp.PartnerActivitys;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;

public class Track_Status extends SimpleActivity {

    private Context mCon = this;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Offers_list.class.getSimpleName();
    private AlertDialog progressDialog;
    String jsonstr,Sname;
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView exp_leaseoffer;
    String nes_id,new_type;
    String all;
    private String offer_id,transaction_id;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    private String id,stage,status,is_rejected_option,seq_no,display_status,completion_date,completion_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_track__status);

        setContentView(R.layout.activity_simple);

        Objs.a.setStubId(this, R.layout.activity_track__status);
        initTools(R.string.app_offer_new);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        exp_leaseoffer = (ExpandableListView) findViewById(R.id.lvExp);

        offer_id =  Objs.a.getBundle(this, Params.offer_id);
        transaction_id =  Objs.a.getBundle(this, Params.transaction_id);
        Track_Status(offer_id,transaction_id);
        addDatas();
    }

    private void Track_Status(String O_id,String T_id) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.offer_id, O_id);
            J.put(Params.transaction_id, T_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.TRACK_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        listDataHeader = new ArrayList<String>();
                        listDataChild = new HashMap<String, List<String>>();

                        try {
                            JSONArray ja = response.getJSONArray(Params.stages);
                            for(int i=0;i<ja.length();i++){
                                JSONObject jsonObject = ja.getJSONObject(i);
                                listDataHeader.add(jsonObject.getString(Params.stage_name));
                                List<String> lease_offer = new ArrayList<String>();
                                JSONArray jsonArray = jsonObject.getJSONArray(Params.child);
                                for(int j = 0;j<jsonArray.length();j++){
                                    JSONObject jj = jsonArray.getJSONObject(j);
                                    //id,stage,status,is_rejected_option,seq_no,display_status
                                    // completion_date,completion_status;
                                    //  Objs.a.showToast(mCon, String.valueOf(jj));
                                    id = jj.getString(Params.id);
                                    stage = jj.getString(Params.stage);
                                    status = jj.getString(Params.status);
                                    is_rejected_option = jj.getString(Params.is_rejected_option);
                                    seq_no = jj.getString(Params.seq_no);
                                    display_status = jj.getString(Params.display_status);
                                    completion_date = jj.getString(Params.completion_date);
                                    completion_status = jj.getString(Params.completion_status);

                                    //  Objs.a.showToast(mCon, completion_date);
                                    all = status+","+ completion_date  +","+ completion_status +","+ completion_date+"," +display_status;
                                    //  String all = status+","+ completion_date  +","+ completion_status;
                                 /*   String all = stage+","+ status +","+ is_rejected_option+","+ seq_no
                                            +","+display_status +","+ completion_date+","+ completion_status;*/
                                    lease_offer.add(all);
                                    listDataChild.put(listDataHeader.get(i),lease_offer);

                                }
                            }
                            exp_leaseoffer.setFocusable(false);
                            expandableListAdapter = new ExpandableListAdapter(mCon,listDataHeader, listDataChild);
                            exp_leaseoffer.setAdapter(expandableListAdapter);
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

    public int GetPixelFromDips(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
    public void addDatas(){

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;


        //    exp_leaseoffer.setIndicatorBounds(width - GetPixelFromDips(10), width - GetPixelFromDips(10));
        // Listview Group click listener
        exp_leaseoffer.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        exp_leaseoffer.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
              /*  Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();*/
            }
        });

        // Listview Group collasped listener
        exp_leaseoffer.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
               /* Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
*/
            }
        });

        // Listview on child click listener
        exp_leaseoffer.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
             //   Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " : " +
            //            listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
