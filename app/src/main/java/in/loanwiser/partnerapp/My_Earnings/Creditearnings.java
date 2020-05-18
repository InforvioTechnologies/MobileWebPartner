package in.loanwiser.partnerapp.My_Earnings;

public class Creditearnings {
    private String date;
    private String leaddetail;
    private String loandetail;
    private String purpose;
    private String coins;

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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public Creditearnings(String date, String leaddetail, String loandetail, String purpose, String coins) {
        this.date = date;
        this.leaddetail = leaddetail;
        this.loandetail = loandetail;
        this.purpose = purpose;
        this.coins = coins;
    }



}
