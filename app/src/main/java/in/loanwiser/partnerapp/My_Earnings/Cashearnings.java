package in.loanwiser.partnerapp.My_Earnings;

public class Cashearnings {

    private String date;
    private String leaddetail;
    private String loandetail;
    private String amount;
    private String bankmax;
    private String stepcomplete;
    private String bankamount;
    private boolean expanded;

    public Cashearnings(String date, String leaddetail, String loandetail, String amount, String bankmax, String stepcomplete, String bankamount) {
        this.date = date;
        this.leaddetail = leaddetail;
        this.loandetail = loandetail;
        this.amount = amount;
        this.bankmax = bankmax;
        this.stepcomplete = stepcomplete;
        this.bankamount = bankamount;
        this.expanded = false;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLeaddetail() {
        return leaddetail;
    }

    public void setLeaddetail(String leaddetail) {
        this.leaddetail = leaddetail;
    }

    public String getLoandetail() {
        return loandetail;
    }

    public void setLoandetail(String loandetail) {
        this.loandetail = loandetail;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBankmax() {
        return bankmax;
    }

    public void setBankmax(String bankmax) {
        this.bankmax = bankmax;
    }

    public String getStepcomplete() {
        return stepcomplete;
    }

    public void setStepcomplete(String stepcomplete) {
        this.stepcomplete = stepcomplete;
    }

    public String getBankamount() {
        return bankamount;
    }

    public void setBankamount(String bankamount) {
        this.bankamount = bankamount;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
