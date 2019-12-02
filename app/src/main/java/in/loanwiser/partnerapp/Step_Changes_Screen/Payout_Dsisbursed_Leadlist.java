package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Params;
import in.loanwiser.partnerapp.PartnerActivitys.Dashboard_Activity;
import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;

import static adhoc.app.applibrary.Config.AppUtils.Objs.a;

public class Payout_Dsisbursed_Leadlist extends SimpleActivity {

    private Context mCon = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setContentView(R.layout.activity_payout__dsisbursed__leadlist);
        setContentView(R.layout.activity_simple);
        Objs.a.setStubId(this,R.layout.activity_payout__dsisbursed__leadlist);
        initTools(R.string.disbursed_lead_list);

        ListItemAdapter adapter = new ListItemAdapter(mCon,null);
        a.getRecyleview(this).setAdapter(adapter);
    }

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
           // return list.length();
            return 5;
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
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ly_track_status, parent, false);
            return new ListItemAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final ListItemAdapter.ViewHolder holder, final int position) {
            try {


            } catch (NullPointerException e) {
                a.showToast(mCon, e.toString());
            } catch (Exception e) {
                a.showToast(mCon, e.toString());
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

            AppCompatTextView type,doc_steps,doc_status,font1,font2,loantype;
            ImageView v_Image;
            ProgressBar progressBar;
            AppCompatButton appCompatButtonSelect;
            AppCompatImageView loan_type_image;
            CardView cardView;
            LinearLayout Over_all;
            View view;

            public ViewHolder(View itemView) {
                super(itemView);

                type  = (AppCompatTextView) itemView.findViewById(R.id.doc_typename_all);
                doc_steps  = (AppCompatTextView) itemView.findViewById(R.id.doc_steps);
                loantype  = (AppCompatTextView) itemView.findViewById(R.id.loantype);
                loan_type_image  = (AppCompatImageView) itemView.findViewById(R.id.loan_type_image);
                // font1  = (AppCompatTextView) itemView.findViewById(R.id.doc_typename_all);
                //  font2 = (AppCompatTextView) itemView.findViewById(R.id.image_Product);
                // progressBar = (ProgressBar) itemView.findViewById(R.id.progressBarMaterial);
                // cardView  = (CardView) itemView.findViewById(R.id.card_view);
                Over_all = (LinearLayout) itemView.findViewById(R.id.Over_all);

            }
        }
    }
}
