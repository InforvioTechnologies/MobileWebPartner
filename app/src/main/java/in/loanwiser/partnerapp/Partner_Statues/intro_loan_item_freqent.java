package in.loanwiser.partnerapp.Partner_Statues;

public class intro_loan_item_freqent {

    private String applicant;
    private String status_disp;
    private String name;
    private String created_at;
    private String user_id;
    private String updated_at;

    private String app_id;
    private String request_by;
    private String doc_typename;
    private String notes;
    private String ask_id;

    private String doc_classname,doc_typeid,transaction_id;





    public String getstatus_disp() {
        return status_disp;
    }

    public void setstatus_disp(String status_disp) {
        this.status_disp = status_disp;
    }

    public String getapplicant() {
        return applicant;
    }

    public void setapplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcreated_at() {
        return created_at;
    }

    public void setcreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getuser_id() {
        return user_id;
    }

    public void setuser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getupdated_at() {
        return updated_at;
    }

    public void setupdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getapp_id() {
        return app_id;
    }

    public void setapp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getrequest_by() {
        return request_by;
    }

    public void setrequest_by(String request_by) {
        this.request_by = request_by;
    }

    public String getdoc_typename() {
        return doc_typename;
    }

    public void setdoc_typename(String doc_typename) {
        this.doc_typename = doc_typename;
    }

    public String getnotes() {
        return notes;
    }

    public void setnotes(String notes) {
        this.notes = notes;
    }

    public String gettransaction_id() {
        return transaction_id;
    }

    public void settransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    public String getdoc_classname() {
        return doc_classname;
    }

    public void setdoc_classname(String doc_classname) {
        this.doc_classname = doc_classname;
    }

    public String getdoc_typeid() {
        return doc_typeid;
    }

    public void setdoc_typeid(String doc_typeid) {
        this.doc_typeid = doc_typeid;
    }

    public String getask_id() {
        return ask_id;
    }

    public void setask_id(String ask_id) {
        this.ask_id = ask_id;
    }


    public intro_loan_item_freqent(String name, String applicant, String status_disp, String created_at, String user_id
    , String updated_at, String app_id, String request_by, String doc_typename, String notes,
                                   String transaction_id, String doc_classname, String doc_typeid, String ask_id ) {

        this.name = name;
        this.applicant = applicant;

        this.status_disp = status_disp;
        this.created_at = created_at;
        this.user_id = user_id;
        this.updated_at = updated_at;

        this.app_id = app_id;
        this.request_by = request_by;
        this.doc_typename = doc_typename;
        this.notes = notes;
        this.transaction_id = transaction_id;
        this.doc_classname = doc_classname;
        this.doc_typeid = doc_typeid;
        this.ask_id = ask_id;

    }

}
