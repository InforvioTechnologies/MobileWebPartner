package in.loanwiser.partnerapp.Partner_Statues;

public class post_item_freqent {

    private String title;
    private String post_url;
    private String content;
    private String id;

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getpost_url() {
        return post_url;
    }

    public void setpost_url(String post_url) {
        this.post_url = post_url;
    }

    public String getcontent() {
        return content;
    }

    public void setcontent(String content) {
        this.content = content;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public post_item_freqent(String title, String post_url,String content, String id) {

        this.title = title;
        this.post_url = post_url;
        this.content = content;
        this.id = id;

    }

}
