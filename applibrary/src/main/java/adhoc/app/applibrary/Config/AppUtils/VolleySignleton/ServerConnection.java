package adhoc.app.applibrary.Config.AppUtils.VolleySignleton;

import android.content.Context;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import adhoc.app.applibrary.Config.AppUtils.Loading.LoadingInter;
import adhoc.app.applibrary.Config.AppUtils.Objs;

/**
 * Created by user on 11/4/2016.
 */

public class ServerConnection {


    //register interface here
    public static   LoadingInter loadingInterface;
    public static void setInterface(Context mCon){
        try {
            loadingInterface = (LoadingInter) mCon;
        } catch (ClassCastException e) {
            Objs.a.Attention(mCon,e.getMessage());
        }
    }


    public static String ShowError(Context mCon,VolleyError error) {
        NetworkResponse response = error.networkResponse;
        if(response != null && response.data != null){
            String a=null;
            switch(response.statusCode){

                case 500:
                     a = new String(response.data);
                    a = trimMessage(a, "message");
                    if(a != null) {
                        Objs.a.setErrMsg(mCon, a);
                        return a;
                    }
                    break;
                case 404:
                     a = new String(response.data);
                    a = trimMessage(a, "message");
                    if(a != null) {
                        Objs.a.setErrMsg(mCon, a);
                        return a;
                    }
                    break;
                case 400:
                    a = new String(response.data);
                    a = trimMessage(a, "message");
                    if(a != null) {
                        Objs.a.setErrMsg(mCon, a);
                        return a;
                    }
                    break;
                default:
                    Objs.a.setErrMsg(mCon, String.valueOf("data: "+new String(response.data)+"Code :"+response.statusCode));
                    return String.valueOf("data: "+new String(response.data)+"Code :"+response.statusCode);
            }
            //Additional cases
        }else {
            String message = "Server not responding!";
            if (error instanceof NetworkError) {
                message = "Cannot connect to Internet...Please check your connection!";
                Objs.a.ShowHideNoNetwork(mCon,true);
                return message;
            } else if (error instanceof ServerError) {
                message = "The server could not be found. Please try again after some time!!";
            } else if (error instanceof AuthFailureError) {
                message = "Cannot connect to Internet...Please check your connection!";
            } else if (error instanceof ParseError) {
                message = "Parsing error! Please try again after some time!!";
            } else if (error instanceof NoConnectionError) {
                message = "Cannot connect to Internet...Please check your connection!";
                Objs.a.ShowHideNoNetwork(mCon,true);
                return message;
            } else if (error instanceof TimeoutError) {
                message = "Connection TimeOut! Please check your internet connection.";
            }
            Objs.a.setErrMsg(mCon, "Response Error : * "+message);
            return message;
        }
        return "Server connection failed";

    }
    public static String trimMessage(String json, String key){
        String trimmedString = null;

        try{
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }
}
