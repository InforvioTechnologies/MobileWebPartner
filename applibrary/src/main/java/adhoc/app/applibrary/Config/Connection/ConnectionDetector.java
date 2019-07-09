package adhoc.app.applibrary.Config.Connection;
 
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import adhoc.app.applibrary.R;


public class ConnectionDetector {


    private Context _context;
    public ConnectionDetector(){ }
    public ConnectionDetector(Context context){
        this._context = context;
    }
  
    /**
     * Checking for all possible internet providers
     * **/
    public boolean isConnectingToInternet(){
        try {
            ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null)
            {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        {
                            return true;
                        }

            }
        } catch (Exception e) {
            Objs.a.showToast(_context,e.toString());
        }
        return false;
    }

    public Boolean IsCon(Context context){
        this._context=context;
        if (!isConnectingToInternet()) {
            Objs.a.showToast(context, context.getString(R.string.no_network));
            Objs.a.HideAll(context);
            Objs.a.ShowHideNoNetwork(context,true);
            return false;
        }
        else
            Objs.a.ShowHideNoNetwork(context,false);
            return true;
    }
    public Boolean IsCon(Context context,int k){
        this._context=context;
        if (!isConnectingToInternet()) {
            // NoNetwork);
            return false;
        }
        else
            return true;
    }
}