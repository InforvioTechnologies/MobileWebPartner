package in.loanwiser.partnerapp.PartnerActivitys;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import adhoc.app.applibrary.Config.AppUtils.Objs;


public class SimpleActivity extends AppCompatActivity {

    public Context mCon = this;

    public void initTools(int id) {
        Objs.a.iniToolbar(this,id);
    }

    public void initTools1(String id) {
        Objs.a.iniToolbar(this,id);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
