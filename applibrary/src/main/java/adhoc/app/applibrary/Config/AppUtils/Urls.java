package adhoc.app.applibrary.Config.AppUtils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2/15/2016.
 */
public class Urls {


   // Live 24/06/2021
    public static String Urls_Old = "https://cscapi.loanwiser.in/mobile/mortgage_updated_test.php?call=";
    public static String Urls_step_Old = "https://cscapi.loanwiser.in/mobile/partner_loanapi_test.php?call=";
    public static String Urls_phamplet_Old = "https://cscapi.loanwiser.in/mobile/pamphlet_distributor_test.php?call=";

    public static String Urls_Bank_Old = "https://cscapi.loanwiser.in/integration/bank_statement.php?call=";
    public static String Urls_Bank1_Old = "https://apiuat.loanwiser.in/integration/bank_statement.php?call=";

    //Live 19/07/2021
     public static String Urls = "https://cscapi.loanwiser.in/mobile/mortgage_updated_test_new.php?call=";
    public static String Urls_step = "https://cscapi.loanwiser.in/mobile/partner_loanapi_test_new.php?call=";
    public static String Urls_phamplet = "https://cscapi.loanwiser.in/mobile/pamphlet_distributor_test_new.php?call=";

    public static String Urls_Bank = "https://cscapi.loanwiser.in/integration/bank_statement_new.php?call=";
    public static String Urls_Bank1 = "https://cscapi.loanwiser.in/integration/bank_statement.php?call=";

    public static String Urls_CRIF = "https://cscapi.loanwiser.in/integration/crif_integration.php?call=";
    public static String Urls_step1 = "https://cscapi.loanwiser.in/mobile/partner_loanapi_second.php?call=";

  /*  ///UAT 24/06/2021
   public static String Urls = "http://apiuat.loanwiser.in/uatapi/mobile/mortgage_updated_test_new.php?call=";
    public static String Urls_step = "http://apiuat.loanwiser.in/uatapi/mobile/partner_loanapi_test_new.php?call=";
    public static String Urls_phamplet = "http://apiuat.loanwiser.in/uatapi/mobile/pamphlet_distributor_test_new.php?call=";

    public static String Urls_Bank = "http://apiuat.loanwiser.in/uatapi/mobile/integration/bank_statement_new.php?call=";
    public static String Urls_Bank1 = "http://apiuat.loanwiser.in/uatapi/integration/bank_statement.php?call=";
    public static String Urls_CRIF = "http://apiuat.loanwiser.in/uatapi/integration/crif_integration.php?call=";
    public static String Urls_step1 = "http://apiuat.loanwiser.in/uatapi/partner_loanapi_second.php?call=";*/
    //// END ///

    public static String Get_Dropdown  = Urls + "get_dropdown";
    public static String get_banklist  = Urls + "get_banklist";
    public static String bank_statementlist_uat  = Urls_step + "bank_statementlist";
    ///

    public static  String Url_version = "https://cscapi.loanwiser.in/mobile/appversion.php?call=";


    public static String VERSION_CHECK_POST = Url_version + "version";
    public static String BUSINESS_reg_POST  = Urls + "register";
    public static String BUSINESS_reg_POST1  = Urls + "register";
    public static String BUSINESS_login_POST  = Urls + "login";
    public static String resendOtp  = Urls + "resendOtp";
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
    public static String relationship_mangement  = Urls_step + "relationship_mangement";
    public static String bank_statementlist  = Urls_step + "bank_statementlist";
    public static String bank_statementlist_old  = Urls_step_Old + "bank_statementlist";
    public static String status_update  = Urls_step + "status_update";
    public static String doc_uploadsubmit  = Urls_step + "doc_uploadsubmit";
    public static String get_selfemployvocation  = Urls + "get_selfemployvocation";
    public static String CITY_TYPE_POST  = Urls + "getDistrict";
    public static String GET_STATE_POST  = Urls + "getState";
    public static String GET_FIELDS_POST  = Urls + "GetFields";
    public static String GET_OCC_POST  = Urls + "getoccupation";
    public static String GET_PINCODE_POST  = Urls + "getpincode";
    public static String GET_TOCKEN  = Urls_phamplet + "get_Token";
    public static String REFERALCODE = Urls_step + "referal_save";
    public static String GET_AERA_POST  = Urls + "getarea";
    public static String BUSS_TYPE_POST  = Urls + "get_businesstype";
    public static String GET_CITY_POST  = Urls + "getcity&state_value=";
    public static String LOAN_TYPE_POST1  = Urls + "get_unsecuredloan";
    public static String LOAN_TYPE_POST2  = Urls + "get_vehicleloan";
    public static String ADD_LEAD_POST  = Urls + "createapplication";
    public static String UPDATE_ADD_LEAD_POST  = Urls + "update_basicdata";
    public static String LEAD_LIST_POST  = Urls + "getapplicationusers";
    public static  String ACTIVITY_NOTES= Urls_step+"Activity_Notes";
    public static String GET_FLAG_WALLET  = Urls_step + "getflag_wallet";
    public static String walletreg  = Urls_step + "walletreg";



    public static  String GETBUSINESSCARD= Urls_step+"getuser_vscard";
    public static  String websitelink_stracking= Urls_step+"websitelink_stracking";
    public static  String UPDATEBSCARD= Urls_step+"updateuser_vscard";

    public static String LEAD_STATUES_LIST  = Urls + "get_status";
    public static String UPDATE_STATUS_DOC  = Urls + "update_uploadstatus";

    public static String submit_loanwiser  = Urls_step + "submit_loanwiser";
    public static String submit_loanwiser_old  = Urls_step_Old + "submit_loanwiser";

    public static String generate_docuverifyrule  = Urls_step + "generate_docuverifyrule";
    public static String generate_docuverifyrule_old  = Urls_step_Old + "generate_docuverifyrule";
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
    public static String ACADEMY_DETAILS  = Urls_step + "Loanwiser_Academy";
    public static String APPLICATION_UPLOAD_POST = Urls + "applicationform_upload";
    public static String OFFER_POST = Urls + "getloanofferresult";
    public static String TRACK_POST = Urls + "gettrack_status";
    public static String APPLICANT_INFO_POST = Urls + "getapplicationdetails";
    public static String APPLICANT_STATUS_POST = Urls + "get_applicantstatus";
  //  public static String BANK_GET = "http://cscapi.propwiser.com/mobile/rental.php?call=bank_list";
    public static String BANK_GET = Urls_step+"bank_list";

    //Step changes
    public static String Lead_Creation = Urls_step + "Lead_Creation";
    public static String lastname_update = Urls_step1 + "lastname_update";
    public static String VIABILITY_CHECK = Urls_step + "viability_check";
    public static String VIABILITY_CHECK_applicant = Urls_step + "viability_applicant";
    public static String VIABILITY_CHECK_applicant1 = Urls_step + "viability_fields_applicant";
    public static String VIABILITY_CHECK_co_applicant = Urls_step + "viability_coapplicant";
    public static String VIABILITY_CHECK_co_applicant1 = Urls_step + "viability_fields_coapplicant";

    public static String BANK_STATEMENT_LIST = Urls_step + "bank_statementlist";

    public static String AddLead_Web = "https://apiuat.loanwiser.in/uatapi/mobile/partner_loanapi_second.php?call=getdecrypt_data";
    public static String Applicant_User_Status_Web = "https://apiuat.loanwiser.in/uatapi/mobile/partner_loanapi_second.php?call=getdecrypt_existingdata";

   // https://cscapi.loanwiser.in/integration/bank_statement.php?call=get_bankstatement_det
    //"https://cscapi.loanwiser.in/integration/bank_statement.php?call=get_bankstatement_detnew"
    public static String BANK_STATEMENT_DETAILS = Urls_Bank + "get_bankstatement_det";
    public static String get_bankstatement_detnew = Urls_Bank + "get_bankstatement_detnew";

    public static String bankst_anaysisres = Urls_step + "bankst_anaysisres";
    public static String upload_bankstanalysis = Urls_step + "upload_bankstanalysis";
    public static String sift_statuscheck = Urls_Bank1 + "sift_statuscheck";

    public static String view_crifscore = Urls_step + "view_crifscore";
    public static String Get_BankcomDetails = Urls_step1 + "Get_BankcomDetails";
   // public static String VIABILITY_CHECK = Urls_step + "insert_family";
    public static String Eligibility_Check = Urls_step + "eligibility_check";
    public static String Eligibility_Check_applicant = Urls_step + "eligibility_applicant";
    public static String Eligibility_Check_co_applicant = Urls_step + "eligibility_coapplicant";
    public static String Eligibility_Check_eligibilitysavet = Urls_step + "eligibilitysave";
    public static String NOCRIFREPORT = Urls_step + "crfi_check";
    public static String PAYMENT_CONFIRMATION = Urls_step + "payarr";
    public static String PAYMENT_SCHEDULE = Urls_step + "get_payschedule";
    public static String DOCUMENT_CHECK_LIST = Urls_step + "get_documentcklist";
    public static String DOCUMENT_CHECK_LIST_old = Urls_step_Old + "get_documentcklist";

    public static String get_partbankup = Urls_step + "get_partbankup";
    public static String get_partbankup_old = Urls_step_Old + "get_partbankup";

    public static String Eligible_Banklist = Urls_step + "Eligible_Banklist";
    public static String Eligible_Banklist_Old = Urls_step_Old + "Eligible_Banklist";

    public static String Activity_Notes = Urls_step + "Activity_Notes";
    public static String bank_status_fetch = Urls_step + "bank_status_fetch";
    public static String bank_status_fetch_old = Urls_step_Old + "bank_status_fetch";
    public static String Get_DocumentcklistProp = Urls_step + "Get_DocumentcklistProp";
    public static String Get_DocumentcklistProp_old = Urls_step_Old + "Get_DocumentcklistProp";
    public static String APPLICANT_INFO = Urls_step + "applicant_info";
    public static String UPDATED_ENABLE = Urls_step + "update_enable";
    public static String PARTNER_STATUES = Urls_step + "part_status";
    public static String PARTNER_STATUES_old = Urls_step_Old + "part_status";
   // public static String PARTNER_STATUES = Urls_step + "Part_Status_new";
    public static String PARTNER_STATUES_IDs = Urls_step + "appid_view";
    public static String PARTNER_STATUES_IDs_old = Urls_step_Old + "appid_view";

    public static String webinar_register = Urls_step + "webinar_register";
    public static String webinar_join = Urls_step + "webinar_join";

    public static String GET_DROPDOWN_LISTVEHICLEBRAND  = Urls_step + "vh_brand";
    public static String GET_DROPDOWN_LISTVEHICLEMODEL  = Urls_step + "vh_modal";

    public static String get_bankstaterules = Urls_step + "get_bankstaterules";
    public static String doccklist_ruleres = Urls_step + "doccklist_ruleres";
    public static final String Bankstatement_URl=Urls_step+"bank_statement_upload";
    public static String ask_countdisp = Urls_step + "ask_countdisp";
    public static String update_bankstatementstatus = Urls_step + "update_bankstatementstatus";
    public static String get_bankdetils = Urls_Bank1 + "get_bankdetils";
    public static String update_bankstatementstatus_old = Urls_step_Old + "update_bankstatementstatus";
    public static String CAMERA_IMAGE_Upload = Urls_step + "multi_doc";
    public static String PDF_Document_Upload = Urls_step + "multi_docpdf";
    public static String DOC_IMAGE_VIEW = Urls_step + "docu_view";
    public static String validate_upldoc = Urls_step + "validate_upldoc";

    public static String Check_Uploadsubmit = Urls_step + "Check_Uploadsubmit";
    public static String Check_Uploadsubmit_old = Urls_step_Old + "Check_Uploadsubmit";

    public static String validate_checklist = Urls_step + "validate_checklist";
    public static String fetch_bankstatements = Urls_step + "fetch_bankstatements";

    public static String Bank_Statement_Upload = Urls_step + "bank_statement_upload";

 public static String Payment_Initialize  = Urls_step + "payment_initialize";
 public static String Report_Activity  = Urls_step + "crif_mail_gen";

 public static String Report_Activity_old  = Urls_step_Old + "crif_mail_gen";
 public static String Lead_Details_statues  = Urls_step + "lead_details";
 public static String Lead_Details_statues_Old  = Urls_step_Old + "lead_details";

    public static String CRIF_Generation  = Urls_step + "crif_generation";
    public static String CRIF_Generation_old  = Urls_step_Old + "crif_generation";
    public static String payment_schedule  = Urls_step + "payment_schedule";
    public static String submit_question  = Urls_step + "submit_question";
    public static String submit_question_old  = Urls_step_Old + "submit_question";
    public static String report_links  = Urls_step + "report_links";
    public static String VIEW_VIABILITYDATA = Urls_step + "view_viabilitydata";
    public static String CRIF_DATA_Populate = Urls_step + "crifdata_view";
    public static String FIREBASE_TOKEN = Urls_step + "update_b2btoken";
    public static String UPDATE_PAYMENT_STATUES = Urls_step + "update_paymentstatus";
    public static String paymentlink_check = Urls_step + "paymentlink_check";
    public static String URL_Viability_Detail_Show = Urls_step + "view_viability_revamp";


    public static String CRIF_SCORE_CHECK = Urls_step + "get_crif_score";
    public static String CRIF_Gerneration = Urls_step + "crif_reportgen";
    public static String step2_complete  = Urls_step + "step2_complete";
    public static String InvoiceReport  = Urls_step1 + "InvoiceReport";
    public static String step2_complete_old  = Urls_step_Old + "step2_complete";
    public static String creditrep_anaysisres  = Urls_step + "creditrep_anaysisres";


    public static String cibil_report1  = Urls_step + "cibil_report1";

    public static String SUBMIT_TO_LOANWIER = Urls_step + "submit_loanwiser";
    public static String viabilitysave = Urls_step + "viabilitysave";
    public static String viable_rule_check = Urls_step + "viable_rule_check";
    public static String viability_eligibilitycheck = Urls_step + "viability_eligibilitycheck";
    public static String COIN_Transaction = Urls_step + "coins_transaction";
    public static String balance_wallentcoins = Urls_step + "balance_wallentcoins";

    public static String Payable_Amount = Urls_step + "payable_amount";
    public static String customplan_msg = Urls_step + "customplan_msg";

    public static String Payment_Plan_Show = Urls_step + "payment_details";

    public static String GET_PAYLINK = Urls_step + "get_paylink";
    public static String send_paymentlink = Urls_step + "send_paymentlink";
    public static String Transaction_List = Urls_step + "transaction_list";
    public static String latest_lead_updates = Urls_step + "latest_lead_updates";
    public static String getwebinars_b2b = Urls_step + "getwebinars_b2b";
    public static String ask_submit_loanw = Urls_step + "ask_submit_loanw";
    public static String getAsklist = Urls_step + "getAsklist";
    public static String reference_board  = Urls_step + "reference_board ";
    public static String CREDIT_COINS  = Urls_step + "transaction_list";

    public static String PAYOUT_DISPLAY  = Urls_step + "payout_display";
    public static String NOTIFICATION_LIST  = Urls_step + "notification_list_partner";

    public static String Notification_Update  = Urls_step + "notification_update";
    public static String My_Earnings  = Urls_step + "my_earnings";
    public static String POST_SHARE  = Urls_step + "post_share";
    public static String DOC_CHECKLIST  = Urls_step + "Get_allDocumentcklist";
    public static String generate_doccklist  = Urls_step + "generate_doccklist";
    public static String CHECKLIST  = Urls_step + "fetch_loanchecklist";



    ///STEP3 Api////

    public static String GET_BANKDROPDOWN_LIST  = Urls + "get_banklist_step2";
    public static String GENEARTEDOCVERIFYRULE  = Urls_step + "generate_docuverifyrule";
    public static String VIABLEBANKS  = Urls_step + "Viable_Banks";
    public static String DOCLISTRULES  = Urls_step + "doccklist_ruleres";

    public static String COMPANYLIST = Urls_step + "getCompanyList";

   // public static String DOCUMENT_CHECK  = Urls_step + "Get_allDocumentcklist";

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
