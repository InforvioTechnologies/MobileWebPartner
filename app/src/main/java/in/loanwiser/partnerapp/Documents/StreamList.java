package in.loanwiser.partnerapp.Documents;

public class StreamList {
    private String title;
    private String created_at;
    private String message;

    public StreamList(String title, String created_at, String message) {
        this.title = title;
        this.created_at = created_at;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
