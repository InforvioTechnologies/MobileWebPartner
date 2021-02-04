package in.loanwiser.partnerapp.BankStamentUpload;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import adhoc.app.applibrary.Config.AppUtils.Pref.Pref;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.Config.AppUtils.VolleySignleton.AppController;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.CameraActivity.ASK_ManiActivity_Image2;
import in.loanwiser.partnerapp.Documents.Doc_ImageView;
import in.loanwiser.partnerapp.PartnerActivitys.Doc_ImageView_Viability;
import in.loanwiser.partnerapp.PartnerActivitys.SimpleActivity;
import in.loanwiser.partnerapp.R;

public class Bank_DocGridView_List extends SimpleActivity {

    Context mCon;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = Bank_DocGridView_List.class.getSimpleName();
    private AlertDialog progressDialog;
    String id,doc_name,docid,class_id,user_type,transaction_id,doc_id,doc_typename;
    String _class_id,_transaction_id,_user_type,_doc_id,type,docid1;
    private RecyclerView recyclerView;
    private FloatingActionButton fab_add_more;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        Objs.a.setStubId(this, R.layout.activity_doc_grid_view__list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        initTools1("Bank Statement View");



        fab_add_more = (FloatingActionButton) findViewById(R.id.fab_add_more);
        fab_add_more.setVisibility(View.GONE);
        Document_Details();

    }

    private void Document_Details() {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put("transaction_id", Pref.getTRANSACTIONID(getApplicationContext()));
            J.put("relationship_type", Pref.getCoAPPAVAILABLE(getApplicationContext()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("Request ", String.valueOf(J));
        progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.fetch_bankstatements, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("Image Response", String.valueOf(response));

                           String statues = response.getString("response");
                           JSONArray jsonObject1 = response.getJSONArray("data");


                            if (jsonObject1.length()>0){
                                setAdapter(jsonObject1);
                            }else {
                                Objs.a.ShowHideNoItems(mCon,true);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
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

    private void setAdapter(JSONArray ja) {
        ListItemAdapter adapter = new ListItemAdapter(Bank_DocGridView_List.this,ja);
        // Objs.a.getRecyleview_horizontal(this).setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Bank_DocGridView_List.this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    //Adapter Class list
    public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {

        JSONArray list = new JSONArray();
        Context mCon;
        JSONObject J;
        public ListItemAdapter(Context mCon, JSONArray list) {
            this.list = list;
            this.mCon = mCon;
        }
        @Override
        public int getItemCount() {
            return list.length();
        }
        public JSONObject getItem(int i) {
            try {
                return list.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        public ListItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ly_image_list, parent, false);
            return new ListItemAdapter.ViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            try {
                J = getItem(position);

                String name = J.getString("name");
                Log.e("the Value",name);

                    holder.Ly_Rl_pdf_reader.setVisibility(View.VISIBLE);
                    holder.Ly_image_reader.setVisibility(View.GONE);
                    String image = "http://cscapi.propwiser.com/mobile/images/pdf.jpg";
                    Objs.a.loadPicasso(mCon,image,holder.image_Pdf,holder.progressBarMaterial_pdf);
                holder.updated_.setText(name);
                holder.Ly_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        J = getItem(position);
                        try {
                            String path =J.getString("url");
                            Objs.ac.StartActivityPutExtra(Bank_DocGridView_List.this, Doc_ImageView_Viability.class,
                                    Params.document,path);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            } catch (NullPointerException e) {
                Objs.a.showToast(mCon, e.toString());
            } catch (Exception e) {
                Objs.a.showToast(mCon, e.toString());
            }
        }
        private void update(JSONArray list1) {
            list = list1;
        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView v_Image,image_Pdf;
            ProgressBar progressBar,progressBarMaterial_pdf;
            View view;
            WebView webview;
            AppCompatTextView updated_;
            ProgressBar progressbar;
            LinearLayout Ly_image_reader,Ly_Rl_pdf_reader,Ly_item;

            public ViewHolder(View itemView) {
                super(itemView);
                Ly_image_reader = (LinearLayout)itemView.findViewById(R.id.Ly_image_reader);
                Ly_Rl_pdf_reader = (LinearLayout)itemView.findViewById(R.id.Ly_Rl_pdf_reader);
                Ly_item = (LinearLayout)itemView.findViewById(R.id.Ly_item);
                //  progressbar = (ProgressBar) itemView.findViewById(R.id.progressbar);
                v_Image = (ImageView) itemView.findViewById(R.id.image_Product);
                image_Pdf = (ImageView) itemView.findViewById(R.id.image_Pdf);
                updated_ = (AppCompatTextView) itemView.findViewById(R.id.updated_);
                progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);
                progressBarMaterial_pdf = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial_pdf);
            }
        }
    }

    public void ExitAlert(final Context context, final String type, final String doc, final String hash) {
        androidx.appcompat.app.AlertDialog.Builder builder =
                new androidx.appcompat.app.AlertDialog.Builder(context, adhoc.app.applibrary.R.style.MyAlertDialogStyle);
        builder.setTitle(context.getResources().getString(adhoc.app.applibrary.R.string.attention));
        builder.setIcon(context.getResources().getDrawable(adhoc.app.applibrary.R.drawable.ic_info_outline_black_24dp));
        builder.setMessage("Do you want to View Document....?");
        builder.setNegativeButton("No", null);
        builder.setNeutralButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d("Response11111111111",type);
                Log.d("Response11111111112",doc);
                Log.d("Response11111111113",hash);

            }
        });
       /* builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Delete_Image(hash);
            }
        });*/

        androidx.appcompat.app.AlertDialog alert = builder.create();
        alert.show();
        Objs.a.DialogStyle(context, alert);
    }



    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;
        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    private void Delete_Image(String hash) {
        JSONObject jsonObject =new JSONObject();
        JSONObject J= null;
        try {
            J =new JSONObject();
            J.put(Params.hash, hash);
            J.put(Params.class_id, _class_id);
            J.put(Params.transaction_id, _transaction_id);
            J.put(Params.user_type, _user_type);
            J.put(Params.doc_id, _doc_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        progressDialog.show();

        Log.e("delete",J.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Urls.DELETE_IMG_POST, J,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.dismiss();
                        Log.e("delete",response.toString());
                        try {
                            if(response.getBoolean(Params.status)){

                                Toast.makeText(getApplication(),"Succussfully deleted the Document...",Toast.LENGTH_SHORT).show();

                              //  Objs.a.showToast(DocGridView_List.this, "Succussfully deleted the Document...");
                              /*  Objs.ac.StartActivityPutExtra(Ask_DocGridView_List.this, Document_Details.class,
                                        Params.user_type,user_type);

                                Intent intent = new Intent(Ask_DocGridView_List.this, Document_Details.class);
                                startActivity(intent);*/

                                finish();
                                /// Document_Details(user_type,class_id,transaction_id,doc_id);
                            }else{
                              ///  Objs.a.showToast(mCon, "Something went wrong ");
                                Toast.makeText(mCon,"Something went wrong",Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
             //   Objs.a.showToast(mCon, error.getMessage());
                Log.e("delete",error.toString());
                progressDialog.dismiss();
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
}
