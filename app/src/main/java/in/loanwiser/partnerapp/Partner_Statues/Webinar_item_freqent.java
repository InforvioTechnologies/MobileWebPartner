package in.loanwiser.partnerapp.Partner_Statues;

public class Webinar_item_freqent {

    private String title;
    private String description;
    private String url;
    private String scheduled_time;
    private String thumbnail_url;
    private String is_registered;
    private String b2b_id;
    private String webinar_id;
    private String session_end;





    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String geturl() {
        return url;
    }

    public void seturl(String url) {
        this.url = url;
    }

    public String getscheduled_time() {
        return scheduled_time;
    }

    public void setscheduled_time(String scheduled_time) {
        this.scheduled_time = scheduled_time;
    }

    public String getthumbnail_url() {
        return thumbnail_url;
    }

    public void setthumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getis_registered() {
        return is_registered;
    }

    public void setis_registered(String is_registered) {
        this.is_registered = is_registered;
    }

    public String getb2b_id() {
        return b2b_id;
    }

    public void setb2b_id(String b2b_id) {
        this.b2b_id = b2b_id;
    }

    public String getwebinar_id() {
        return webinar_id;
    }

    public void setwebinar_id(String b2b_id) {
        this.webinar_id = webinar_id;
    }

    public String getsession_end() {
        return session_end;
    }

    public void setsession_end(String session_end) {
        this.session_end = session_end;
    }
    public Webinar_item_freqent(String title, String description, String scheduled_time, String url, String thumbnail_url
    , String is_registered, String b2b_id, String webinar_id, String session_end) {

        this.title = title;
        this.description = description;
        this.scheduled_time = scheduled_time;
        this.url = url;
        this.thumbnail_url = thumbnail_url;
        this.is_registered = is_registered;
        this.b2b_id = b2b_id;
        this.webinar_id = webinar_id;
        this.session_end = session_end;

    }

}
