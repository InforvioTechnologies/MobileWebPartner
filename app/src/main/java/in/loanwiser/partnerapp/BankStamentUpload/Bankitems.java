package in.loanwiser.partnerapp.BankStamentUpload;

import java.util.ArrayList;

public class Bankitems {

    public Bankitems(String name, String url) {
        this.name = name;
        this.url = url;
    }

    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;
}
