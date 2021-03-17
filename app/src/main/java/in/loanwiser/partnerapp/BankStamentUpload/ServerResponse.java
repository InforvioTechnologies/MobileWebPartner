package in.loanwiser.partnerapp.BankStamentUpload;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {

    @SerializedName("success")
    boolean success;


    @SerializedName("message")
    String message;
    @SerializedName("bank_name")
    String bank_name;



    @SerializedName("status")
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }


    String getMessage() {
        return message;
    }
    boolean getSuccess() {
        return success;
    }

    public class response{
        @SerializedName("status")
        String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
