package in.loanwiser.partnerapp.BankStamentUpload;

import android.content.Context;

public class Bank_available_details_model {

    private String ban_url;
    private String cat_type;
    private String cat_type_id;
    private String bank_id;
    private Context context;
    private String status;



    public Bank_available_details_model(String ban_url, String cat_type, String cat_type_id, String status, String bank_id ) {
        this.ban_url = ban_url;
        this.cat_type = cat_type;
        this.cat_type_id = cat_type_id;
        this.status = status;
        this.bank_id = bank_id;

    }

    public String getbank_url() {
        return ban_url;
    }

    public void setbank_url(String ban_url) {
        this.ban_url = ban_url;
    }

    public String getcat_type() {
        return cat_type;
    }

    public void setcat_type(String cat_type) {
        this.cat_type = cat_type;
    }
    public String getcat_type_id() {
        return cat_type_id;
    }

    public void setcat_type_id(String cat_type_id) {
        this.cat_type_id = cat_type_id;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public String getbank_id() {
        return bank_id;
    }

    public void setbank_id(String bank_id) {
        this.bank_id = bank_id;
    }



    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
