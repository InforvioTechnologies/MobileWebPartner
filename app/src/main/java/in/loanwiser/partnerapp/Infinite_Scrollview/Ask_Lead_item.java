package in.loanwiser.partnerapp.Infinite_Scrollview;

public class Ask_Lead_item {

    private String user_id;
    private String app_id;
    private String name;
    private String request_by;
    private String applicant;
    private String created_at;
    private String status_disp;
    private String doc_typename;
    private String notes;
    private String relationship_type;
    private String doc_classid;
    private String doc_typeid1;
    private String ask_id, close_date,partner_resolved_date_org;

    private String doc_classname,doc_typeid,transaction_id;

    public String getiuser_id() {
        return user_id;
    }

    public void setuser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getapp_id() {
        return app_id;
    }

    public void setapp_id(String app_id) {
        this.app_id = app_id;
    }



    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getrequest_by() {
        return request_by;
    }

    public void setrequest_by(String request_by) {
        this.request_by = request_by;
    }

    public String getapplicant() {
        return applicant;
    }

    public void setapplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getcreated_at() {
        return created_at;
    }

    public void setcreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getstatus_disp() {
        return status_disp;
    }

    public void setstatus_disp(String status_disp) {
        this.status_disp = status_disp;
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

    public String getclose_date() {
        return close_date;
    }

    public void setclose_date(String close_date) {
        this.close_date = close_date;
    }

    public String getpartner_resolved_date_org() {
        return partner_resolved_date_org;
    }

    public void setpartner_resolved_date_org(String partner_resolved_date_org) {
        this.partner_resolved_date_org = partner_resolved_date_org;
    }

    public String getrelationship_type() {
        return relationship_type;
    }

    public void setrelationship_type(String relationship_type) {
        this.relationship_type = relationship_type;
    }

    public String getdoc_classid() {
        return doc_classid;
    }

    public void setdoc_classid(String doc_classid) {
        this.doc_classid = doc_classid;
    }

    public String getdoc_typeid1() {
        return doc_typeid1;
    }

    public void setdoc_typeid1(String doc_typeid1) {
        this.doc_typeid1 = doc_typeid1;
    }
    public Ask_Lead_item(String user_id, String app_id, String name, String request_by, String applicant,
                         String created_at , String status_disp, String doc_typename, String notes,
                         String transaction_id,String doc_typeid, String ask_id ,String doc_classname,
                            String close_date, String partner_resolved_date_org, String relationship_type,
                         String doc_classid, String doc_typeid1) {
        //
        this.user_id = user_id;
        this.app_id = app_id;
        this.name = name;
        this.request_by = request_by;
        this.applicant = applicant;
        this.created_at = created_at;
        this.status_disp = status_disp;
        this.doc_typename = doc_typename;
        this.notes = notes;
        this.transaction_id = transaction_id;
        this.doc_typeid = doc_typeid;
        this.ask_id = ask_id;
        this.doc_classname = doc_classname;
        this.close_date = close_date;
        this.partner_resolved_date_org = partner_resolved_date_org;
        this.relationship_type = relationship_type;
        this.doc_classid = doc_classid;
        this.doc_typeid1 = doc_typeid1;

    }

}
