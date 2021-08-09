package in.loanwiser.partnerapp.BankStamentUpload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
public class UploadResponse {
    @SerializedName("status")
    String status;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public  class response{

    }


}
*/


class FileDeail {

    @SerializedName("file_url")
    @Expose
    private String fileUrl;
    @SerializedName("file_name")
    @Expose
    private String fileName;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public FileDeail withFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileDeail withFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

}

class Request {

    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("analysis_status")
    @Expose
    private String analysisStatus;
    @SerializedName("workorder_id")
    @Expose
    private String workorderId;
    @SerializedName("entity_id")
    @Expose
    private String entityId;
    @SerializedName("relationship_type")
    @Expose
    private String relationshipType;
    @SerializedName("pdf_password")
    @Expose
    private String pdfPassword;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Request withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getAnalysisStatus() {
        return analysisStatus;
    }

    public void setAnalysisStatus(String analysisStatus) {
        this.analysisStatus = analysisStatus;
    }

    public Request withAnalysisStatus(String analysisStatus) {
        this.analysisStatus = analysisStatus;
        return this;
    }

    public String getWorkorderId() {
        return workorderId;
    }

    public void setWorkorderId(String workorderId) {
        this.workorderId = workorderId;
    }

    public Request withWorkorderId(String workorderId) {
        this.workorderId = workorderId;
        return this;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Request withEntityId(String entityId) {
        this.entityId = entityId;
        return this;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Request withRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
        return this;
    }

    public String getPdfPassword() {
        return pdfPassword;
    }

    public void setPdfPassword(String pdfPassword) {
        this.pdfPassword = pdfPassword;
    }

    public Request withPdfPassword(String pdfPassword) {
        this.pdfPassword = pdfPassword;
        return this;
    }

}

class Response {

    @SerializedName("entity_id")
    @Expose
    private String entityId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("upload_status")
    @Expose
    private Integer uploadStatus;
    @SerializedName("file_deails")
    @Expose
    private List<FileDeail> fileDeails = null;
    @SerializedName("allready_accno")
    @Expose
    private Integer allreadyAccno;

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Response withEntityId(String entityId) {
        this.entityId = entityId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response withMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Response withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getUploadStatus() {
        return uploadStatus;

    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public Response withUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
        return this;
    }

    public List<FileDeail> getFileDeails() {
        return fileDeails;
    }

    public void setFileDeails(List<FileDeail> fileDeails) {
        this.fileDeails = fileDeails;
    }

    public Response withFileDeails(List<FileDeail> fileDeails) {
        this.fileDeails = fileDeails;
        return this;
    }

    public Integer getAllreadyAccno() {
        return allreadyAccno;
    }

    public void setAllreadyAccno(Integer allreadyAccno) {
        this.allreadyAccno = allreadyAccno;
    }

    public Response withAllreadyAccno(Integer allreadyAccno) {
        this.allreadyAccno = allreadyAccno;
        return this;
    }

}


public class UploadFileResponse {

    @SerializedName("request")
    @Expose
    private Request request;
    @SerializedName("response")
    @Expose
    private Response response;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public UploadFileResponse withRequest(Request request) {
        this.request = request;
        return this;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public UploadFileResponse withResponse(Response response) {
        this.response = response;
        return this;
    }

}