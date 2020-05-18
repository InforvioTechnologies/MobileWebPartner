package in.loanwiser.partnerapp.Infinite_Scrollview;

public class Lead_item {

    private String id;
    private String loan_typename;
    private String step_status;
    private String username;
    private String mobileno;
    private String transaction_id;
    private String field_status;

    //loan_amount,comp_step,status_disp,color_code
    private String loan_amount;
    private String comp_step;
    private String status_disp;
    private String color_code;



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

    public String getloan_amount() {
        return loan_amount;
    }

    public void setloan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getcomp_step() {
        return comp_step;
    }

    public void setcomp_step(String comp_step) {
        this.comp_step = comp_step;
    }
    public String getstatus_disp() {
        return status_disp;
    }

    public void setstatus_disp(String status_disp) {
        this.status_disp = status_disp;
    }

    public String getcolor_code() {
        return color_code;
    }

    public void setcolor_code(String color_code) {
        this.color_code = color_code;
    }

    public Lead_item(String id, String loan_typename, String step_status, String username, String mobileno,
                     String transaction_id, String loan_amount,String comp_step,String status_disp,String color_code ) {
        //
        this.id = id;
        this.loan_typename = loan_typename;
        this.step_status = step_status;
        this.username = username;
        this.mobileno = mobileno;
        this.transaction_id = transaction_id;
        this.field_status = field_status;


        this.loan_amount = loan_amount;
        this.comp_step = comp_step;
        this.status_disp = status_disp;
        this.color_code = color_code;


    }

}
