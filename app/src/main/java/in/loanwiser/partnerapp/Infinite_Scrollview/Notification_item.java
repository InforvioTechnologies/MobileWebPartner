package in.loanwiser.partnerapp.Infinite_Scrollview;

public class Notification_item {

    private String title;
    private String message;
    private String created_at;
    private String user_id;
    private String btn_invoke;
    private String notification_id;
    private String status1;




    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }



    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    public String getcreated_at() {
        return created_at;
    }

    public void setcreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getuser_id() {
        return user_id;
    }

    public void setuser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getbtn_invoke() {
        return btn_invoke;
    }

    public void setbtn_invoke(String btn_invoke) {
        this.btn_invoke = btn_invoke;
    }

    public String getnotification_id() {
        return notification_id;
    }

    public void setnotification_id(String notification_id) {
        this.notification_id = notification_id;
    }

    public String getstatus() {
        return status1;
    }

    public void setstatus(String status1) {
        this.status1 = status1;
    }


    public Notification_item(String title, String message, String created_at, String user_id, String btn_invoke,String notification_id,
                             String status1) {
        //
        this.title = title;
        this.message = message;
        this.created_at = created_at;
        this.user_id = user_id;
        this.btn_invoke = btn_invoke;
        this.notification_id = notification_id;
        this.status1 = status1;



    }

}
