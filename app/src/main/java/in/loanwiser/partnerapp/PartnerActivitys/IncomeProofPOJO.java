package in.loanwiser.partnerapp.PartnerActivitys;

public class IncomeProofPOJO {

    String IP_id = null;
    String IP_name = null;
    boolean IP_selected = false;

    public IncomeProofPOJO(String IP_id, String IP_name,boolean IP_selected) {
        super();
        this.IP_id = IP_id;
        this.IP_name = IP_name;
        this.IP_selected = IP_selected;
    }


    public String getIP_id() {
        return IP_id;
    }

    public void setIP_id(String IP_id) {
        this.IP_id = IP_id;
    }

    public String getIP_name() {
        return IP_name;
    }

    public void setIP_name(String IP_name) {
        this.IP_name = IP_name;
    }

    public boolean isIP_selected() {
        return IP_selected;
    }

    public void setIP_selected(boolean IP_selected) {
        this.IP_selected = IP_selected;
    }


}

