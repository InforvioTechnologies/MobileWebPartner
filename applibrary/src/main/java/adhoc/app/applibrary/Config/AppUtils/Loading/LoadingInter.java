package adhoc.app.applibrary.Config.AppUtils.Loading;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by user on 9/8/2016.
 */
public interface LoadingInter {
    public void onLoadingCompleted(JSONObject res);
    public void onGetResponse(JSONArray res);
}
