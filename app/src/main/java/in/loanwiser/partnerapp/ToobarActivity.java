package in.loanwiser.partnerapp;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import adhoc.app.applibrary.Config.AppUtils.Objs;

public class ToobarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initToolbar(int toolbarId)
    {
        Toolbar myToolbar = (Toolbar) findViewById(toolbarId);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        assert myToolbar != null;
        myToolbar.setTitle(R.string.app_name);
       // myToolbar.setBackgroundColor(R.drawable.greadient);
    }
    public Context mCon = this;

    public void initTools(int id) {
        Objs.a.iniToolbar(this,id);
    }

    public void initTools1(String id) {
        Objs.a.iniToolbar(this,id);
    }

}
