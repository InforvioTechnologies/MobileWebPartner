package in.loanwiser.partnerapp.Partner_Statues;

public class Suggestion_item_freqent {

    private String Appointment_id;
    private String user_id;
    private String name;
    private String loan_type;
    private String status_disp;
    private String loan_amount;





    public String getAppointment_id() {
        return Appointment_id;
    }

    public void setAppointment_id(String Appointment_id) {
        this.Appointment_id = Appointment_id;
    }

    public String getUser_ID() {
        return user_id;
    }

    public void setUser_ID(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getloan_type() {
        return loan_type;
    }

    public void setloan_type(String loan_type) {
        this.loan_type = loan_type;
    }

    public String getloan_amount() {
        return loan_amount;
    }

    public void setloan_amount(String loan_amount) {
        this.loan_amount = loan_amount;
    }

    public String getstatus_disp() {
        return status_disp;
    }

    public void setstatus_disp(String status_disp) {
        this.status_disp = status_disp;
    }


    public Suggestion_item_freqent(String Appointment_id, String user_id, String user_name, String loan_type, String loan_amount
    , String status_disp) {

        this.Appointment_id = Appointment_id;
        this.user_id = user_id;
        this.name = user_name;
        this.loan_type = loan_type;
        this.loan_amount = loan_amount;
        this.status_disp = status_disp;

    }

}
