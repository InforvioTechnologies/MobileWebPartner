package adhoc.app.applibrary.Config.AppUtils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2/15/2016.
 */
public class Urls {

 /*UAT*/
 //  public static String Urls = "http://cscapitest.propwiser.com/mobile/mortgage_updated.php?call=";

    /*Live*/
   public static String Urls = "http://cscapi.propwiser.com/mobile/mortgage_updated.php?call=";


  /// public static String Urls = "http://cscapiuat.propwiser.com/mobile/mortgage_updated.php?call=";


    public static  String Url_version = "http://cscapi.propwiser.com/mobile/appversion.php?call=";

    public static String VERSION_CHECK_POST = Url_version + "version";
    public static String BUSINESS_reg_POST  = Urls + "register";
    public static String BUSINESS_reg_POST1  = Urls + "register";
    public static String BUSINESS_login_POST  = Urls + "login";
    public static String PARTNER_LEAD_MOBILE_NUMBER  = Urls + "check_mobilenumber";
    public static String PARTNER_LEAD_MOBILE_OTP  = Urls + "verifyotp_mobilenumber";
    public static String MOBILE_UPDATE_POST  = Urls + "partner_mobileupdate";
    public static String Confirm_LOGIN_OTP_POST  = Urls + "confirmOtp";
    public static String Confirm_Upadte_OTP_POST  = Urls + "partner_mobileverify";
    public static String Confirm_REG_OTP_POST  = Urls + "confirmOtp";
    public static String Confirm_REG_OTP_POST1  = Urls + "confirmOtp";
    public static String SESSION_POST  = Urls + "sessionmanage";
    public static String LOAN_TYPE_POST  = Urls + "getloantypelist";
    public static String LOAN_TYPE_POST11  = Urls + "getloantypelist1";
    public static String CITY_TYPE_POST  = Urls + "getDistrict";
    public static String GET_STATE_POST  = Urls + "getState";
    public static String GET_FIELDS_POST  = Urls + "GetFields";
    public static String GET_OCC_POST  = Urls + "getoccupation";
    public static String GET_PINCODE_POST  = Urls + "getpincode";
    public static String GET_AERA_POST  = Urls + "getarea";
    public static String BUSS_TYPE_POST  = Urls + "get_businesstype";
    public static String GET_CITY_POST  = Urls + "getcity&state_value=";
    public static String LOAN_TYPE_POST1  = Urls + "get_unsecuredloan";
    public static String LOAN_TYPE_POST2  = Urls + "get_vehicleloan";
    public static String ADD_LEAD_POST  = Urls + "createapplication";
    public static String UPDATE_ADD_LEAD_POST  = Urls + "update_basicdata";
    public static String LEAD_LIST_POST  = Urls + "getapplicationusers";
    public static String LEAD_STATUES_LIST  = Urls + "get_status";
    public static String UPDATE_STATUS_DOC  = Urls + "update_uploadstatus";
    public static String BANK_DETAILS_POST  = Urls + "partner_banksearch";
    public static String PROFILE_DETAILS_POST  = Urls + "partner_profilesearch";
    public static String PROFILE_UPDATED_POST  = Urls + "partner_profileupdate";
    public static String BANK_UPDATED_POST  = Urls + "partner_bankupdate";
    public static String APP_ID_OTP_POST = Urls + "appid_view";
    public static String WORKFLOW_POST = Urls + "get_workflowstatus";
    public static String APP_DOWNLOAD_POST = Urls + "application_download";
    public static String GET_DOCUMENT_POST = Urls + "listdocuments_view";
    public static String SINGLE_DOCUMENT_POST = Urls + "singledoc";
    public static String DOC_IMG_POST = Urls + "docu_view";
    public static String DELETE_IMG_POST = Urls + "deletesingledoc";
    public static final String FILE_UPLOAD_URL = Urls + "multi_doc_scan";
    public static String IMG_UPLOAD_DOCUMENT_POST = Urls + "multi_doc";
    public static String PDF_UPLOAD_DOCUMENT_POST = Urls + "multi_docpdf";
    public static String APPLICATION_UPLOAD_POST = Urls + "applicationform_upload";
    public static String OFFER_POST = Urls + "getloanofferresult";
    public static String TRACK_POST = Urls + "gettrack_status";
    public static String APPLICANT_INFO_POST = Urls + "getapplicationdetails";
    public static String APPLICANT_STATUS_POST = Urls + "get_applicantstatus";
    public static String BANK_GET = "http://cscapi.propwiser.com/mobile/rental.php?call=bank_list";

    /* public static  String = "" ;
    public static  String = "" ;
    public static  String = "" ;
    public static  String = "" ;
    public static  String = "" ;
    public static  String = "" ;
    public static  String = "" ;
    public static  String = "" ;
    public static  String = "" ;
    public static  String = "" ;
    public static  String = "" ;*/


    public static String CustomerDetaisl_GET  =  "Customer/CustomerDetails/";


    public static  String liveUrl = "http://192.168.0.27:8181/MassCart/MassThreeAPI/V1/MASS/1018/";

    public static String getPostQueryString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            }
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        String query=result.toString();

        return query;
    }


}
