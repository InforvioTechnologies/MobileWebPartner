package in.loanwiser.partnerapp.Step_Changes_Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableRow;

import in.loanwiser.partnerapp.R;

public class Document_CheckList_Generation extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    int Array_Count=0;
  //  String[] Str_Array = {"a","b","c"};
    String[] Str_Array = {" -Select Salary Details- ","Salaried Individual","Self Employed Profesional","Self Employed Non Profesional","Home Maker","Retired"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document__check_list__generation);

        Array_Count=Str_Array.length;

        LinearLayout my_layout = (LinearLayout)findViewById(R.id.check_list);

        for (int i = 0; i < Array_Count; i++)
        {
            TableRow row =new TableRow(this);
            row.setId(i);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            CheckBox checkBox = new CheckBox(this);
            checkBox.setOnCheckedChangeListener(this);
            checkBox.setId(i);
            checkBox.setText(Str_Array[i]);
            row.addView(checkBox);
            my_layout.addView(row);
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
}
