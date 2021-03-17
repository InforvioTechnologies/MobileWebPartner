
package in.loanwiser.partnerapp.BankStamentUpload.modelglib;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

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
