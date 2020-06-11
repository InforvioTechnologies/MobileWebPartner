package in.loanwiser.partnerapp.Infinite_Scrollview;

public class CashfreeList_item {

    private String date_disp;
    private String user_name;
    private String app_id;
    private String loan_amount;
    private String loan_type;
    private String name_of_source;

    //loan_amount,comp_step,status_disp,color_code
    private String transaction_mode_disp;
    private String coins_disp;
    private String status_disp;
    private String payment_plan;
    private String current_step;


    public String getdate_disp() {
        return date_disp;
    }

    public void setdate_disp(String date_disp) {
        this.date_disp = date_disp;
    }

    public String getcurrent_step() {
        return current_step;
    }

    public void setcurrent_step(String current_step) {
        this.current_step = current_step;
    }


    public String getuser_name() {
        return user_name;
    }

    public void setuser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getapp_id() {
        return app_id;
    }

    public void setapp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getloan_amount() {
        return loan_amount;
    }

    public void setloan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getloan_type() {
        return loan_type;
    }

    public void setloan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getname_of_source() {
        return name_of_source;
    }

    public void setname_of_source(String name_of_source) {
        this.name_of_source = name_of_source;
    }

    public String gettransaction_mode_disp() {
        return transaction_mode_disp;
    }

    public void settransaction_mode_disp(String transaction_mode_disp) {
        this.transaction_mode_disp = transaction_mode_disp;
    }

    public String getcoins_disp() {
        return coins_disp;
    }

    public void setcoins_disp(String coins_disp) {
        this.coins_disp = coins_disp;
    }

    public String getstatus_disp() {
        return status_disp;
    }

    public void setstatus_disp(String status_disp) {
        this.status_disp = status_disp;
    }


    public String getpayment_plan() {
        return payment_plan;
    }

    public void setpayment_plan(String payment_plan) {
        this.payment_plan = payment_plan;
    }


    public CashfreeList_item(String date_disp, String user_name, String app_id, String loan_amount, String loan_type,
                             String name_of_source, String transaction_mode_disp, String coins_disp, String current_step, String payment_plan ) {
        //
        this.date_disp = date_disp;
        this.user_name = user_name;
        this.app_id = app_id;
        this.loan_amount = loan_amount;
        this.loan_type = loan_type;
        this.name_of_source = name_of_source;
        this.transaction_mode_disp = transaction_mode_disp;
        this.coins_disp = coins_disp;
        this.status_disp = status_disp;
        this.payment_plan = payment_plan;
        this.current_step = current_step;

    }

}
