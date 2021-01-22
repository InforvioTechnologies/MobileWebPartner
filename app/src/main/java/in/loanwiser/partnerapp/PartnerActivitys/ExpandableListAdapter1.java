package in.loanwiser.partnerapp.PartnerActivitys;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import in.loanwiser.partnerapp.R;

public class ExpandableListAdapter1 extends BaseExpandableListAdapter {

    private Context _context = null;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;
    private String id,stage,status,is_rejected_option,seq_no,display_status,completion_date,completion_status;
    private String date_status;

    public ExpandableListAdapter1(Context context, List<String> listDataHeader,
                                  HashMap<String, List<String>> listDataChild) {

        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listDataChild;

    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView doc_status,doc_date,doc_status11;
        ImageView doc_status_img;

        final String childText = (String) getChild(groupPosition, childPosition);

        /*//id,stage,status,is_rejected_option,seq_no,display_status
                                    // completion_date,completion_status;*/
        String CurrentString = childText;
        String[] separated = CurrentString.split(",");
        status = separated[0];
      String date =  separated[1];
        /*completion_date = separated[1];
        completion_status = separated[2];
        date_status = separated[3];
        display_status = separated[4];*/

       /*  id = separated[0];
         stage = separated[1];
         status = separated[2];
         is_rejected_option = separated[3];
         seq_no = separated[4];
         display_status = separated[5];
         completion_date = separated[6];
       //  completion_status = separated[7];*/

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ly_list_exp_item1, null);
        }
        doc_status = (TextView) convertView.findViewById(R.id.doc_status);
        doc_status11 = (TextView) convertView.findViewById(R.id.doc_status11);
        doc_date = (TextView) convertView.findViewById(R.id.doc_date);
        doc_status_img = (ImageView) convertView.findViewById(R.id.doc_status_img);
       /* Typeface custom_font = Typeface.createFromAsset(_context.getAssets(), "AlegreyaSans-Regular.ttf");
        doc_status.setTypeface(custom_font);*/
        doc_status11.setText("\u25CF");
        doc_status.setText(status+"("+date+")");
        doc_status11.setTextColor(Color.parseColor("#00ceb4"));
        //doc_date.setText(completion_date);


      /*  if(date_status.isEmpty()||date_status==null){
            doc_date.setTextColor(_context.getResources().getColor(R.color.colorHover));
        }else{
            doc_date.setTextColor(_context.getResources().getColor(R.color.colorPrimary1));
        }
*/

       /* if(completion_status.equals("1")){

            doc_status_img.setImageDrawable(_context.getResources().getDrawable(R.drawable.don));
        }else{
            doc_status_img.setImageDrawable(_context.getResources().getDrawable(R.drawable.notdon));
        }
*/

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ly_list_exp_group1, null);
        }
        TextView listTitle = (TextView) convertView.findViewById(R.id.lblListHeader);

       /* Typeface custom_font = Typeface.createFromAsset(_context.getAssets(), "AlegreyaSans-Regular.ttf");
        listTitle.setTypeface(custom_font);*/
        listTitle.setText(headerTitle);

      /*  app_info_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app_info_img.setVisibility(View.GONE);
                app_info_img1.setVisibility(View.VISIBLE);
            }
        });
        app_info_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app_info_img1.setVisibility(View.GONE);
                app_info_img.setVisibility(View.VISIBLE);
            }
        });*/
               // app_info_img.setImageResource(R.drawable.ic_uparrow_icon);


       // app_info_img.setImageResource(R.drawable.ic_uparrow_icon);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
