
package in.loanwiser.partnerapp.BankStamentUpload.modelglib;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("MSG")
    @Expose
    private String mSG;
    @SerializedName("WORKORDER_ID")
    @Expose
    private String wORKORDERID;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("typecnt")
    @Expose
    private Integer typecnt;
    @SerializedName("status")
    @Expose
    private String status;

    public String getMSG() {
        return mSG;
    }

    public void setMSG(String mSG) {
        this.mSG = mSG;
    }

    public Response withMSG(String mSG) {
        this.mSG = mSG;
        return this;
    }

    public String getWORKORDERID() {
        return wORKORDERID;
    }

    public void setWORKORDERID(String wORKORDERID) {
        this.wORKORDERID = wORKORDERID;
    }

    public Response withWORKORDERID(String wORKORDERID) {
        this.wORKORDERID = wORKORDERID;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Response withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Integer getTypecnt() {
        return typecnt;
    }

    public void setTypecnt(Integer typecnt) {
        this.typecnt = typecnt;
    }

    public Response withTypecnt(Integer typecnt) {
        this.typecnt = typecnt;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Response withStatus(String status) {
        this.status = status;
        return this;
    }

}
