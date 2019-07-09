package adhoc.app.applibrary.Config.AppUtils.Web;


import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.Config.AppUtils.Urls;
import adhoc.app.applibrary.R;


public class AppService {

    Context mCon;
    HashMap<String, String> postDataParams;
    String method;
    private boolean POST=false,GET=false;
    HttpURLConnection conn = null;
    String server_response;

    public AppService() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }



    public String getResponse(Context mCon, HashMap<String, String> postDataParams,String methodName) {
        try {

            this.mCon = mCon;
            this.postDataParams = postDataParams;
            this.method = methodName;
            return getJobj(true);

        } catch (Exception e) {
            Objs.a.showToast(mCon, e.getMessage());
        }
        return "";
    }
//    public JSONArray GETResponse(Context mCon, HashMap<String, String> postDataParams,String methodName) {
//        try {
//
//            this.mCon = mCon;
//            this.postDataParams = postDataParams;
//            this.method = methodName;
//            return getJobj(false);
//
//        } catch (Exception e) {
//            Objs.a.showToast(mCon, e.getMessage());
//        }
//        return "";
//    }


    public String getJobj(Boolean POST) {
        URL url;

        String response = "";
        int responseCode = 0;
        try {
            String string_url= Urls.liveUrl+method;
            url = new URL(string_url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);

            if (POST) {
                conn.setDoOutput(true);
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Content-Language", "en-US");
                conn.setRequestProperty("Accept-Encoding", "");
                conn.setUseCaches (false);
                conn.addRequestProperty("Content-Length", Integer.toString(ServiceUtil.getPostQueryString(postDataParams).length())); //add the content length of the post data
                conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //add the content type of the request, most post data is of this type
                conn.setRequestMethod("POST");
                conn.connect();
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream()); //we will write our request data here
                writer.write(ServiceUtil.getPostQueryString(postDataParams));
                writer.flush();
            }else if (GET){
                string_url= Urls.liveUrl+method+"?"+ServiceUtil.getPostQueryString(postDataParams);
                url = new URL(string_url);
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(30000);
                conn.setConnectTimeout(30000);
                conn.setRequestMethod("GET");
                conn.connect();
            }
             responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                response = ServiceUtil.readStream(mCon,conn.getInputStream());
            } else {
                response = "";
            }
        }catch (MalformedURLException e) {
            response=e.getMessage();
           // Objs.a.showToast(mCon, e.getMessage());
            Objs.a.setErrMsg(mCon, e.getMessage());
        }

        catch (IOException e) {
            response=e.getMessage();
           // Objs.a.showToast(mCon, e.getMessage());
            Objs.a.setErrMsg(mCon, e.getMessage());
        }
        catch (Exception e) {
            response=e.getMessage()!=null?e.getMessage():mCon.getResources().getString(R.string.server_host_connection_failed);
           // Objs.a.showToast(mCon, e.getMessage());
            Objs.a.setErrMsg(mCon, response);
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        if (responseCode == HttpsURLConnection.HTTP_OK && response.length()<=0 || response.startsWith("<!DOCTYPE"))
            response="** "+response;

        if (response.length()<=0)
            response="** "+"Empty response from server";

        if (response.length()>0 && response.startsWith("failed"))
            response="** "+response;

        return response;
    }


}

