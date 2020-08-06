package in.loanwiser.partnerapp.Partner_Statues;

public class Document_item_freqent {

    private String head_line;
    private String icon;
    private String loantype_id;

    public String gethead_line() {
        return head_line;
    }

    public void sethead_line(String head_line) {
        this.head_line = head_line;
    }

    public String geticon() {
        return icon;
    }

    public void seticon(String icon) {
        this.icon = icon;
    }

    public String getloantype_id() {
        return loantype_id;
    }

    public void setloantype_id(String loantype_id) {
        this.loantype_id = loantype_id;
    }

    public Document_item_freqent(String head_line, String icon , String loantype_id) {

        this.head_line = head_line;

        this.icon = icon;
        this.loantype_id = loantype_id;

    }

}
