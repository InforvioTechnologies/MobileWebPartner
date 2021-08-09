package in.loanwiser.partnerapp.PartnerActivitys;

public class Viablityfaildata {

    String rule_desc;
    String rule_status;
    String fail_message;

    public Viablityfaildata(String rule_desc, String rule_status, String fail_message) {
        this.rule_desc = rule_desc;
        this.rule_status = rule_status;
        this.fail_message = fail_message;
    }

    public String getRule_desc() {
        return rule_desc;
    }

    public void setRule_desc(String rule_desc) {
        this.rule_desc = rule_desc;
    }

    public String getRule_status() {
        return rule_status;
    }

    public void setRule_status(String rule_status) {
        this.rule_status = rule_status;
    }

    public String getFail_message() {
        return fail_message;
    }

    public void setFail_message(String fail_message) {
        this.fail_message = fail_message;
    }
}
