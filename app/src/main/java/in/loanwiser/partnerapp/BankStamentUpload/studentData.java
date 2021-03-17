package in.loanwiser.partnerapp.BankStamentUpload;

public class studentData {
    String name;
    int age;
    String upload_detstatus;





    public studentData(String name,String upload_detstatus) {
        this.name = name;
        this.age = age;
        this.upload_detstatus=upload_detstatus;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUpload_detstatus() {
        return upload_detstatus;
    }

    public void setUpload_detstatus(String upload_detstatus) {
        this.upload_detstatus = upload_detstatus;
    }




}
