
package in.loanwiser.partnerapp.BankStamentUpload.modelglib;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlibResponse {

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

    public GlibResponse withRequest(Request request) {
        this.request = request;
        return this;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public GlibResponse withResponse(Response response) {
        this.response = response;
        return this;
    }

}
