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
   public static String Urls = "http://cscapitest.propwiser.com/mobile/mortgage_updated.php?call=";
   public static String Urls_step = "http://cscapitest.propwiser.com/mobile/partner_loanapi.php?call=";
   public static String Urls_phamplet = "http://cscapitest.propwiser.com/mobile/pamphlet_distributor.php?call=";


    /*Live*/
 //  public static String Urls = "http://cscapi.propwiser.com/mobile/mortgage_updated.php?call=";


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
    public static String LOAN_CATAGORY  = Urls + "loanlatlist";
    public static String LOAN_TYPE_POST11  = Urls + "getloantypelist1";
    public static String GET_DROPDOWN_LIST  = Urls + "get_dropdown";
    public static String CITY_TYPE_POST  = Urls + "getDistrict";
    public static String GET_STATE_POST  = Urls + "getState";
    public static String GET_FIELDS_POST  = Urls + "GetFields";
    public static String GET_OCC_POST  = Urls + "getoccupation";
    public static String GET_PINCODE_POST  = Urls + "getpincode";
    public static String GET_TOCKEN  = Urls_phamplet + "get_Token";



    public static String GET_AERA_POST  = Urls + "getarea";
    public static String BUSS_TYPE_POST  = Urls + "get_businesstype";
    public static String GET_CITY_POST  = Urls + "getcity&state_value=";
    public static String LOAN_TYPE_POST1  = Urls + "get_unsecuredloan";
    public static String LOAN_TYPE_POST2  = Urls + "get_vehicleloan";
    public static String ADD_LEAD_POST  = Urls + "createapplication";
    public static String UPDATE_ADD_LEAD_POST  = Urls + "update_basicdata";
    public static String LEAD_LIST_POST  = Urls + "getapplicationusers";

    public static String GET_FLAG_WALLET  = Urls_step + "getflag_wallet";
    public static String walletreg  = Urls_step + "walletreg";

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

    //Step changes
    public static String Lead_Creation = Urls_step + "Lead_Creation";
    public static String VIABILITY_CHECK = Urls_step + "viability_check";
    public static String Eligibility_Check = Urls_step + "eligibility_check";
    public static String NOCRIFREPORT = Urls_step + "crfi_check";
    public static String PAYMENT_CONFIRMATION = Urls_step + "payarr";
    public static String PAYMENT_SCHEDULE = Urls_step + "get_payschedule";
    public static String DOCUMENT_CHECK_LIST = Urls_step + "get_documentcklist";
    public static String APPLICANT_INFO = Urls_step + "applicant_info";
    public static String UPDATED_ENABLE = Urls_step + "update_enable";
    public static String PARTNER_STATUES = Urls_step + "part_status";
    public static String PARTNER_STATUES_IDs = Urls_step + "appid_view";
    public static String CAMERA_IMAGE_Upload = Urls_step + "multi_doc";
    public static String PDF_Document_Upload = Urls_step + "multi_docpdf";
    public static String DOC_IMAGE_VIEW = Urls_step + "docu_view";

    public static String Bank_Statement_Upload = Urls_step + "bank_statement_upload";

 public static String Payment_Initialize  = Urls_step + "payment_initialize";
 public static String Report_Activity  = Urls_step + "crif_mail_gen";
 public static String Lead_Details_statues  = Urls_step + "lead_details";

 public static String CRIF_Generation  = Urls_step + "crif_generation";
    public static String VIEW_VIABILITYDATA = Urls_step + "view_viabilitydata";
    public static String CRIF_DATA_Populate = Urls_step + "crifdata_view";
    public static String FIREBASE_TOKEN = Urls_step + "update_b2btoken";
    public static String UPDATE_PAYMENT_STATUES = Urls_step + "update_paymentstatus";


    public static String CRIF_SCORE_CHECK = Urls_step + "get_crif_score";
    public static String CRIF_Gerneration = Urls_step + "crif_reportgen";

    public static String SUBMIT_TO_LOANWIER = Urls_step + "submit_loanwiser";
    public static String COIN_Transaction = Urls_step + "coins_transaction";

    public static String Payable_Amount = Urls_step + "payable_amount";
    public static String customplan_msg = Urls_step + "customplan_msg";

    public static String GET_PAYLINK = Urls_step + "get_paylink";
    public static String send_paymentlink = Urls_step + "send_paymentlink";
    public static String Transaction_List = Urls_step + "transaction_list";
    public static String latest_lead_updates = Urls_step + "latest_lead_updates";
    public static String reference_board  = Urls_step + "reference_board ";
    public static String CREDIT_COINS  = Urls_step + "transaction_list";

    public static String PAYOUT_DISPLAY  = Urls_step + "payout_display";
    public static String NOTIFICATION_LIST  = Urls_step + "notification_list_partner";

    public static String Notification_Update  = Urls_step + "notification_update";
    public static String My_Earnings  = Urls_step + "my_earnings";
    public static String POST_SHARE  = Urls_step + "post_share";
   // public static String DOCUMENT_CHECK_LIST = Urls_document_check_list + "get_documentcklist";

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
