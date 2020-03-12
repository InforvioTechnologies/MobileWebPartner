package in.loanwiser.partnerapp.Multi_select_checkbox;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import in.loanwiser.partnerapp.R;
import in.loanwiser.partnerapp.SimpleActivity;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_Check_PL;
import in.loanwiser.partnerapp.Step_Changes_Screen.Viability_check_HL;

public class Multi_Select_checkbox extends SimpleActivity {


    private ListView listView;
    String [] Salary_proof;
    private AppCompatButton button;
    List<String> result1;
    List<Integer> select_lid_id;
    List<Integer> myList_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi__select_checkbox);
      //  setContentView(R.layout.activity_simple);
       // Objs.a.setStubId(this,R.layout.activity_multi__select_checkbox);
       // initTools(R.string.viy_check1);
        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("jsonArray");
        listView = (ListView) findViewById(R.id.list);
        button = (AppCompatButton) findViewById(R.id.button);

        result1 = new ArrayList<>();
        select_lid_id = new ArrayList<Integer>();
        myList_values = (ArrayList<Integer>) getIntent().getSerializableExtra("select_lid_id");

        try {
            JSONArray array = new JSONArray(jsonArray);
            multi_spin(array);
            Log.e("the jsonvalue",array.toString());
         } catch (JSONException e) {
            e.printStackTrace();
        }

        set_selected_item();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> selected = getSelectedItems();
                String logString = "Selected items: " + TextUtils.join(", ", selected);
                Log.d("checked list", String.valueOf(selected));
             //   Log.d("MainActivity", logString);
                Log.d("check boc list", String.valueOf(result1));

                for(int i=0;i<result1.size(); ++i)
                {
                    for(int j=0; j<selected.size(); ++j)
                    {
                        String checked_value = result1.get(i);
                        String checked_value1 = selected.get(j);
                        if(checked_value.contains(checked_value1))
                        {
                            select_lid_id.add(i);
                            Log.e("the checked value", String.valueOf(i));
                        }
                    }
                }

              /*  if(selected.size()>0)
                {
                    Intent intent = new Intent(Multi_Select_checkbox.this, Viability_Check_PL.class);
                    intent.putExtra("select_lid_id", (Serializable) select_lid_id);
                    startActivity(intent);
                    finish();
                }else
                {
                    Toast.makeText(Multi_Select_checkbox.this, "Please select at least one", Toast.LENGTH_SHORT).show();
                }*/

               // Toast.makeText(this, logString, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void multi_spin( final  JSONArray salary_mulit)
    {
        Salary_proof = new String[salary_mulit.length()];
        for (int i=0;i<salary_mulit.length();i++) {
            JSONObject J = null;
            try {
                J = salary_mulit.getJSONObject(i);
                Salary_proof[i] = J.getString("value");
                result1.add(Salary_proof[i]);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            final List<String> loan_type_list = new ArrayList<>(Arrays.asList(Salary_proof));
            CustomAdapter adapter = new CustomAdapter(this, Salary_proof);

            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);
        }
    }

    private void set_selected_item()
    {
        if(myList_values != null)
        {
            for (int i = 0; i < myList_values.size(); ++i) {

                listView.setItemChecked(myList_values.get(i),true);
                Log.e("the list", String.valueOf(listView.getCount()));

            }

        }

    }

    private List<String> getSelectedItems() {
        List<String> result = new ArrayList<>();

        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        Log.e("the list", String.valueOf(checkedItems.size()));
        Log.e("the list", String.valueOf(checkedItems));

            if(checkedItems.size() > 0)
            {
                for (int i = 0; i < checkedItems.size(); i++) {

                    if (checkedItems.valueAt(i)) {

                        Log.e("the check id", String.valueOf(listView.getCount()));
                        result.add((String) listView.getItemAtPosition(checkedItems.keyAt(i)));
                        Log.e("the check id-1", String.valueOf(checkedItems.keyAt(i)));

                    }
                }
            }

        return result;
    }

}






