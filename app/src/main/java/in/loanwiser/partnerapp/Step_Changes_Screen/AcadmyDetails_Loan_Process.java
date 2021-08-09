package in.loanwiser.partnerapp.Step_Changes_Screen;

public class AcadmyDetails_Loan_Process {

    private String thumbnail;
    private String title;
    private String description;
    private String pdf_url;
    private String pdf_status;
    private String video_status;

    public String getVideo_status() {
        return video_status;
    }

    public void setVideo_status(String video_status) {
        this.video_status = video_status;
    }

    public String getPdf_status() {
        return pdf_status;
    }

    public void setPdf_status(String pdf_status) {
        this.pdf_status = pdf_status;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    private String video_url;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public AcadmyDetails_Loan_Process(String thumbnail, String title, String description, String pdf_url, String video_status, String video_url) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
        this.pdf_url = pdf_url;
        this.video_status=video_status;
        this.video_url=video_url;
    }


}
