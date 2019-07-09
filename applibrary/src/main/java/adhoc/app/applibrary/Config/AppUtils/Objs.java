package adhoc.app.applibrary.Config.AppUtils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import adhoc.app.applibrary.Config.ActivityFinisher.ActivityCloser;
import adhoc.app.applibrary.Config.Alerts.Alerts;
import adhoc.app.applibrary.Config.AppUtils.Web.AppService;
import adhoc.app.applibrary.Config.Connection.ConnectionDetector;


/**
 * Created by user on 2/15/2016.
 */
public class Objs {
    //Json
    public static JSONObject json=new JSONObject();
    public static JSONArray ja=new JSONArray();
    public static JSONObject J=new JSONObject();
    //Objects
    public static Alerts a=new Alerts();
    public static ConnectionDetector cd=new ConnectionDetector();
    public static ActivityCloser ac=new ActivityCloser();
    public static Context mCon;
    public static AppService as=new AppService();
    public static Validation v=new Validation();

    public static int POST = 0;
    public static int GET = 1;
    public static String Rs = "Rs. ";
    public static String tag_json_arry = "json_array_req";
}
