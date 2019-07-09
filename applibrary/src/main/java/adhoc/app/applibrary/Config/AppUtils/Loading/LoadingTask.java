package adhoc.app.applibrary.Config.AppUtils.Loading;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import adhoc.app.applibrary.Config.AppUtils.Objs;


/**
 * Created by user on 9/8/2016.
 */

public class LoadingTask extends AsyncTask<Void, Void, String> {

    HashMap<String,String> map;
    Context  activity;
    String method;
    LoadingInter instance;
    int mType;


    public LoadingTask(Context activity,String methodName,HashMap<String,String> map,int mType){
        this.activity=activity;
        this.map=map;
        this.method = methodName;
        this.mType=mType;
        instance= (LoadingInter) activity;

    }

    @Override
    protected void onPreExecute() {
        Objs.a.showProgressBar(activity,"Loading");
    }

    @Override
    protected String doInBackground(Void... params) {

        return UpdateScanResultNow();
    }

    @Override
    protected void onPostExecute(final String response) {
        Objs.a.hideProgressBar(activity);
        try {
            if (response.startsWith("**")){
                Objs.a.setErrMsg(activity,response);
                return;
            }
            if (mType==Objs.POST)
                instance.onLoadingCompleted(new JSONObject(response));
            else
                instance.onGetResponse(new JSONArray(response));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private String UpdateScanResultNow() {
        if (mType==Objs.POST)
            return  Objs.as.getResponse(activity,map,method);
        else
            return  Objs.as.getResponse(activity,map,method);

    }
}

