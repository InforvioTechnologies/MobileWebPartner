package in.loanwiser.partnerapp.Infinite_Scrollview;

public class Lead_item {

    private String id;
    private String loan_typename;
    private String step_status;
    private String username;
    private String mobileno;
    private String transaction_id;
    private String field_status;



    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getloan_typename() {
        return loan_typename;
    }

    public void setloan_typename(String loan_typename) {
        this.loan_typename = loan_typename;
    }

    public String getstep_status() {
        return step_status;
    }

    public void setstep_status(String step_status) {
        this.step_status = step_status;
    }

    public String getmobileno() {
        return mobileno;
    }

    public void setmobileno(String type) {
        this.mobileno = mobileno;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String appointment_date) {
        this.username = appointment_date;
    }

    public String gettransaction_id() {
        return transaction_id;
    }

    public void settransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getfield_status() {
        return field_status;
    }

    public void setfield_status(String field_status) {
        this.field_status = field_status;
    }

    public Lead_item(String id, String loan_typename, String step_status, String username, String mobileno,
                     String transaction_id  ) {

        this.id = id;
        this.loan_typename = loan_typename;
        this.step_status = step_status;
        this.username = username;
        this.mobileno = mobileno;
        this.transaction_id = transaction_id;
        this.field_status = field_status;


    }

}
