package adhoc.app.applibrary.Config.AppUtils;

import java.text.SimpleDateFormat;

/**
 * Created by user on 2/15/2016.
 */
public class AS {
    //App Strings
    public static final String Attention="Attention";
    public static final String NoNetwork="NO INTERNET ACCESS..";
    public static  String No_res="Server Not Responding..";
    public static  String No_data="No Items Found..";
    public static  String failed="A problem occurred on server connection!!";
    public static  String invalid_contact="Invalid Number";
    public static  String invalid_email="Invalid Email";
    public static  String invalid_pwd="Invalid Password";
    public static  String Rs="Rs. ";


    //Date Time formats
    public static final SimpleDateFormat DB_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat myformat = new SimpleDateFormat("dd/MM/yyyy h:mm:a");
    public static final SimpleDateFormat myCustomformat = new SimpleDateFormat("dd/MM/yyyy, h:m a");
    public static final String  OutPutFormatMyStr ="dd/MM/yyyy h:mm:a";
    public static final String InputFormatDBStr ="yyyy-MM-dd HH:mm:ss";

}
