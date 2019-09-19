package adhoc.app.applibrary.GrantAccess;

import android.content.Context;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.R;

public class GetStarted extends AppCompatActivity {

    Context mCon;
    static GetStartedInter getStartedInter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_get_started);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mCon = this;

        findViewById(R.id.appCompatButtonGetStarted).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStartedInter.onGetSaratedCompleted("ok");
                finish();
            }
        });

        Objs.a.setTransition(this);

    }
    public static void setInterface(Context mCon){
        getStartedInter = (GetStartedInter) mCon;
    }


}
