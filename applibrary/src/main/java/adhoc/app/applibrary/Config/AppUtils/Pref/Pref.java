package adhoc.app.applibrary.Config.AppUtils.Pref;

import android.content.Context;
import android.content.SharedPreferences;

import adhoc.app.applibrary.R;


public final class Pref {

    public static String UID = "UID";
    public static String PID = "PID";
    public static String Count = "Count";
    public static String ID = "ID";
    public static String PART = "PART";
    public static String TID = "TID";
    public static String EID = "EID";
    public static String AEID = "AEID";
    public static String ATID = "ATID";
    public static String MOB = "MOB";
    public static String ALL = "ALL";
    public static String LAM = "LAM";
    public static String LOANTYPESUB = "LOANTYPESUB";
    public static String DOC = "DOC";
    public static String PRO = "PRO";
    public static String UserID = "UserID";
    public static String UserCode = "UserCode";
    public static String CID = "CID";
    public static String CM1 = "CM1";
    public static String CDOCTYPENAME = "CDOCTYPENAME";
    public static String DOCID = "DOCID";
    public static String CLASSID = "CLASSID";
    public static String USERTYPE = "USERTYPE";
    public static String TRANSACTIONID = "TRANSACTIONID";

    public static String APPLICANTNAME = "APPLICANTNAME";

    public static String SALARYTYPE = "SALARYTYPE";
    public static String COSALARYTYPE = "COSALARYTYPE";

    public static String LOANTYPE = "LOANTYPE";
    public static String LOANTYPE1 = "LOANTYPE1";
    public static String NAME = "NAME";
    public static String Residence_ID = "Residence_ID";

    public static String MOBILE = "MOBILE";
    public static String LEADMOBILE = "LEADMOBILE";
    public static String LOANTYPENAME = "LOANTYPENAME";

    public static String LOANCATNAME = "LOANCATNAME";

    public static String DOC_Status = "DOC_Status";
    public static String IDENTIFIED = "IDENTIFIED";
    public static String CoAPPAVAILABLE = "CoAPPAVAILABLE";

    public static SharedPreferences get(final Context context) {
        return context.getSharedPreferences(context.getResources().getString(R.string.app_name),Context.MODE_PRIVATE);
    }

    public static String getPref(final Context context, String pref,	String def) {
        SharedPreferences prefs = Pref.get(context);
        String val = prefs.getString(pref, def);

        if (val == null || val.equals("") || val.equals("null"))
            return def;
        else
            return val;
    }

    public static void putPref(final Context context, String pref,String val) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(pref, val);
        editor.commit();
    }
    public static Boolean getBooleanPref(final Context context, String pref,	Boolean def) {
        SharedPreferences prefs = Pref.get(context);
        Boolean val = prefs.getBoolean(pref, def);

        return val;
    }

    public static void putBooleanPref(final Context context, String pref,Boolean val) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(pref, val);
        editor.commit();
    }


    public static void putCS1(final Context context, String aUserId) {
        Pref.putPref(context, CM1, aUserId);
    }

    public static String getCS1(final Context context) {
        return Pref.getPref(context, CM1, null);
    }

    public static void removeCS1(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(CM1);
        editor.commit();
    }

    ////CAmera Image Upload
////Activity Title
public static void putapplicant_name(final Context context, String aUserId) {
    Pref.putPref(context, APPLICANTNAME, aUserId);
}

    public static String getapplicant_name(final Context context) {
        return Pref.getPref(context, APPLICANTNAME, null);
    }

    public static void removeapplicant_name(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(APPLICANTNAME);
        editor.commit();
    }

    ///User Login


    public static void putUSERNAME(final Context context, String aUserId) {
        Pref.putPref(context, ID, aUserId);
    }

    public static String getUSERNAME(final Context context) {
        return Pref.getPref(context, ID, null);
    }

    public static void removeUSERNAME(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(ID);
        editor.commit();
    }

    public static void putPROPERTYIDENTIFIED(final Context context, String aUserId) {
        Pref.putPref(context, IDENTIFIED, aUserId);
    }

    public static String getPROPERTYIDENTIFIED(final Context context) {
        return Pref.getPref(context, IDENTIFIED, null);
    }

    public static void removePROPERTYIDENTIFIED(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(IDENTIFIED);
        editor.commit();
    }

    //Is Co applicant available

    public static void putCoAPPAVAILABLE(final Context context, String aUserId) {
        Pref.putPref(context, CoAPPAVAILABLE, aUserId);
    }

    public static String getCoAPPAVAILABLE(final Context context) {
        return Pref.getPref(context, CoAPPAVAILABLE, null);
    }

    public static void removeCoAPPAVAILABLE(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(CoAPPAVAILABLE);
        editor.commit();
    }

    //Salary type

    public static void putSALARYTYPE(final Context context, String aUserId) {
        Pref.putPref(context, SALARYTYPE, aUserId);
    }

    public static String getSALARYTYPE(final Context context) {
        return Pref.getPref(context, SALARYTYPE, null);
    }

    public static void removeSALARYTYPE(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(SALARYTYPE);
        editor.commit();
    }



    //Co Salary Type
    public static void putCOSALARYTYPE(final Context context, String aUserId) {
        Pref.putPref(context, COSALARYTYPE, aUserId);
    }

    public static String getCOSALARYTYPE(final Context context) {
        return Pref.getPref(context, COSALARYTYPE, null);
    }

    public static void removeCOSALARYTYPE(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(COSALARYTYPE);
        editor.commit();
    }

    public static void putStatus_id(final Context context, String aUserId) {
        Pref.putPref(context, PID, aUserId);
    }

    public static String getStatus_id(final Context context) {
        return Pref.getPref(context, PID, null);
    }

    public static void removeStatus_id(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(PID);
        editor.commit();
    }


    public static void putStatus_Count(final Context context, String aUserId) {
        Pref.putPref(context, Count, aUserId);
    }

    public static String getStatus_Count(final Context context) {
        return Pref.getPref(context, Count, null);
    }

    public static void removeStatus_Count(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(Count);
        editor.commit();
    }
    //////////////////
    public static void putcameraID(final Context context, String aUserId) {
        Pref.putPref(context, CID, aUserId);
    }

    public static String getcameraID(final Context context) {
        return Pref.getPref(context, CID, null);
    }

    public static void removecameraID(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(CID);
        editor.commit();
    }


    public static void putcamera_doc_typename(final Context context, String aUserId) {
        Pref.putPref(context, CDOCTYPENAME, aUserId);
    }

    public static String getcamera_doc_typename(final Context context) {
        return Pref.getPref(context, CDOCTYPENAME, null);
    }

    public static void removecamera_doc_typename(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(CDOCTYPENAME);
        editor.commit();
    }


    public static void putcamera_docid(final Context context, String aUserId) {
        Pref.putPref(context, DOCID, aUserId);
    }

    public static String getcamera_docid(final Context context) {
        return Pref.getPref(context, DOCID, null);
    }

    public static void removecamera_docid(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(DOCID);
        editor.commit();
    }

    public static void putcamera_class_id(final Context context, String aUserId) {
        Pref.putPref(context, CLASSID, aUserId);
    }

    public static String getcamera_class_id(final Context context) {
        return Pref.getPref(context, CLASSID, null);
    }

    public static void removecamera_class_id(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(CLASSID);
        editor.commit();
    }

    public static void putcamera_user_type(final Context context, String aUserId) {
        Pref.putPref(context, USERTYPE, aUserId);
    }

    public static String getcamera_user_type(final Context context) {
        return Pref.getPref(context, USERTYPE, null);
    }

    public static void removecamera_user_type(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(USERTYPE);
        editor.commit();
    }

    public static void putcamera_transaction_id(final Context context, String aUserId) {
        Pref.putPref(context, TRANSACTIONID, aUserId);
    }

    public static String getcamera_transaction_id(final Context context) {
        return Pref.getPref(context, TRANSACTIONID, null);
    }

    public static void removecamera_transaction_id(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(TRANSACTIONID);
        editor.commit();
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    ///Loan type
    public static void putLoanType(final Context context, String aUserId) {
        Pref.putPref(context, LOANTYPE, aUserId);
    }

    public static String getLoanType(final Context context) {
        return Pref.getPref(context, LOANTYPE, null);
    }

    public static void removeLoanType(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(LOANTYPE);
        editor.commit();
    }

    ////
    //Loan Type name

    public static void putLoanTypename(final Context context, String aUserId) {
        Pref.putPref(context, LOANTYPENAME, aUserId);
    }

    public static String getLoanTypename(final Context context) {
        return Pref.getPref(context, LOANTYPENAME, null);
    }

    public static void removeLoanTypename(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(LOANTYPENAME);
        editor.commit();
    }

    public static void putLoanCat_Name(final Context context, String aUserId) {
        Pref.putPref(context, LOANCATNAME, aUserId);
    }

    public static String getLoanCat_Name(final Context context) {
        return Pref.getPref(context, LOANCATNAME, null);
    }

    public static void removeLoanCat_Name(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(LOANCATNAME);
        editor.commit();
    }


    public static void putUID(final Context context, String aUserId) {
        Pref.putPref(context, UID, aUserId);
    }

    public static String getUID(final Context context) {
        return Pref.getPref(context, UID, null);
    }

    public static void removeLogin(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(UID);
        editor.commit();
    }

    public static void putALL(final Context context, String aUserId) {
        Pref.putPref(context, ALL, aUserId);
    }

    public static String getALL(final Context context) {
        return Pref.getPref(context, ALL, null);
    }

    public static void removeALL(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(ALL);
        editor.commit();
    }

    public static void putLAM(final Context context, String aUserId) {
        Pref.putPref(context, LAM, aUserId);
    }

    public static String getLAM(final Context context) {
        return Pref.getPref(context, LAM, null);
    }

    public static void removeLAM(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(LAM);
        editor.commit();
    }



    //Loan type sub
    public static void putLoanTypeSub(final Context context, String aUserId) {
        Pref.putPref(context, LOANTYPESUB, aUserId);
    }

    public static String getLoanTypeSub(final Context context) {
        return Pref.getPref(context, LOANTYPESUB, null);
    }

    public static void removeLoanTypeSub(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(LOANTYPESUB);
        editor.commit();
    }

    public static void putPRO(final Context context, String aUserId) {
        Pref.putPref(context, PRO, aUserId);
    }

    public static String getPRO(final Context context) {
        return Pref.getPref(context, PRO, null);
    }

    public static void removePRO(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(PRO);
        editor.commit();
    }


    public static void putMOB(final Context context, String aUserId) {
        Pref.putPref(context, MOB, aUserId);
    }

    public static String getMOB(final Context context) {
        return Pref.getPref(context, MOB, null);
    }

    public static void removeMOB(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(MOB);
        editor.commit();
    }
///

    public static void putMobile(final Context context, String aUserId) {
        Pref.putPref(context, MOBILE, aUserId);
    }

    public static String getMobile(final Context context) {
        return Pref.getPref(context, MOBILE, null);
    }

    public static void removeMobile(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(MOBILE);
        editor.commit();
    }

    public static void putMobileLead(final Context context, String aUserId) {
        Pref.putPref(context, LEADMOBILE, aUserId);
    }

    public static String getMobileLead(final Context context) {
        return Pref.getPref(context, LEADMOBILE, null);
    }

    public static void removeMobileLead(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(LEADMOBILE);
        editor.commit();
    }
////
public static void putName(final Context context, String aUserId) {
    Pref.putPref(context, NAME, aUserId);
}

    public static String getName(final Context context) {
        return Pref.getPref(context, NAME, null);
    }

    public static void removeName(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(NAME);
        editor.commit();
    }

    /*////*/
    public static void putID(final Context context, String aUserId) {
        Pref.putPref(context, ID, aUserId);
    }

    public static String getID(final Context context) {
        return Pref.getPref(context, ID, null);
    }

    public static void removeID(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(ID);
        editor.commit();
    }

    public static void put_Residence_ID(final Context context, String aUserId) {
        Pref.putPref(context, Residence_ID, aUserId);
    }

    public static String get_Residence_ID(final Context context) {
        return Pref.getPref(context, Residence_ID, null);
    }

    public static void remove_Residence_ID(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(Residence_ID);
        editor.commit();
    }



    public static void putPART(final Context context, String aUserId) {
        Pref.putPref(context, PART, aUserId);
    }

    public static String getPART(final Context context) {
        return Pref.getPref(context, PART, null);
    }

    public static void removePART(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(PART);
        editor.commit();
    }

    public static void putDOC_Status(final Context context, String aUserId) {
        Pref.putPref(context, DOC_Status, aUserId);
    }

    public static String getDOC_Status(final Context context) {
        return Pref.getPref(context, DOC_Status, null);
    }

    public static void removeDOC_Status(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(DOC_Status);
        editor.commit();
    }

    public static void putDOC(final Context context, String aUserId) {
        Pref.putPref(context, DOC, aUserId);
    }

    public static String getDOC(final Context context) {
        return Pref.getPref(context, DOC, null);
    }

    public static void removeDOC(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(DOC);
        editor.commit();
    }



    public static void putTID(final Context context, String aUserId) {
        Pref.putPref(context, TID, aUserId);
    }

    public static String getTID(final Context context) {
        return Pref.getPref(context, TID, null);
    }

    public static void removeTID(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(TID);
        editor.commit();
    }

    public static void putEID(final Context context, String aUserId) {
        Pref.putPref(context, EID, aUserId);
    }

    public static String getEID(final Context context) {
        return Pref.getPref(context, EID, null);
    }

    public static void removeEID(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(EID);
        editor.commit();
    }

    public static void putAEID(final Context context, String aUserId) {
        Pref.putPref(context, AEID, aUserId);
    }

    public static String getAEID(final Context context) {
        return Pref.getPref(context, AEID, null);
    }

    public static void removeAEID(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(AEID);
        editor.commit();
    }

    public static void putATID(final Context context, String aUserId) {
        Pref.putPref(context, ATID, aUserId);
    }

    public static String getATID(final Context context) {
        return Pref.getPref(context, ATID, null);
    }

    public static void removeATID(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(ATID);
        editor.commit();
    }

}
