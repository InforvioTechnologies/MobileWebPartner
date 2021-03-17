package in.loanwiser.partnerapp.BankStamentUpload;

import android.content.Context;

public class Bankdetails_model {

    private String acc_number;
    private String entity_id;
    private String bank_name;
    private String acchold_name;
    private Context context;
    private String status_int;

    public String getStatus_int() {
        return status_int;
    }

    public void setStatus_int(String status_int) {
        this.status_int = status_int;
    }



    public String getAcchold_name() {
        return acchold_name;
    }

    public void setAcchold_name(String acchold_name) {
        this.acchold_name = acchold_name;
    }

    public Bankdetails_model(String acc_number, String entity_id, String bank_name, String acchold_name,String status_int) {
        this.acc_number = acc_number;
        this.entity_id = entity_id;
        this.bank_name = bank_name;
        this.acchold_name=acchold_name;
        this.status_int=status_int;
    }

    public String getAcc_number() {
        return acc_number;
    }

    public void setAcc_number(String acc_number) {
        this.acc_number = acc_number;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
