package in.loanwiser.partnerapp.Partner_Statues;

public class Health_Assesment_item_freqent {

    private String head_line;
    private String content;
    private String icon;
    private String color_code;






    public String gethead_line() {
        return head_line;
    }

    public void sethead_line(String head_line) {
        this.head_line = head_line;
    }

    public String getcontent() {
        return content;
    }

    public void setcontent(String content) {
        this.content = content;
    }

    public String geticon() {
        return icon;
    }

    public void seticon(String icon) {
        this.icon = icon;
    }

    public String getcolor_code() {
        return color_code;
    }

    public void setcolor_code(String color_code) {
        this.color_code = color_code;
    }


    public Health_Assesment_item_freqent(String head_line, String content, String icon,String color_code) {

        this.head_line = head_line;
        this.content = content;
        this.icon = icon;
        this.color_code = color_code;


    }

}
