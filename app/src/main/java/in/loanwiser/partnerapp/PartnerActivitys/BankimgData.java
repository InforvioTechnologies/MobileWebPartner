package in.loanwiser.partnerapp.PartnerActivitys;

public class BankimgData {
    private String title;
    private int imgid;
    private String id;
    private String bank_logo_cc;
    private String bank_logo;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getBank_logo() {
        return bank_logo;
    }

    public void setBank_logo(String bank_logo) {
        this.bank_logo = bank_logo;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBank_logo_cc() {
        return bank_logo_cc;
    }

    public void setBank_logo_cc(String bank_logo_cc) {
        this.bank_logo_cc = bank_logo_cc;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public BankimgData(String id, String bank_logo_cc,String bank_logo,String status) {
        this.id = id;
        this.bank_logo_cc = bank_logo_cc;
        this.bank_logo=bank_logo;
        this.status=status;
    }
}
