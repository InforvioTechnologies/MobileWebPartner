package in.loanwiser.partnerapp.BankStamentUpload;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.HashMap;
import java.util.Map;

import in.loanwiser.partnerapp.BankStamentUpload.modelglib.GlibResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

public interface ApiConfig {
    @Multipart
    @POST("retrofit_example/upload_multiple_files.php")
    Call< ServerResponse > uploadMulFile(@Part MultipartBody.Part file1, @Part MultipartBody.Part file2);


    @Multipart
    @POST("partner_loanapi_test.php?call=bank_statement_upload")
    Call<UploadFileResponse> submitNew(@Header("Authorization") String authHeader,
                            /*   @Part("first-parameter") RequestBody firstParameter,
                               @Part("second-parameter") RequestBody secondParameter,*/
                                       @Part MultipartBody.Part[] img_url,
                                       @Part("transaction_id") RequestBody transaction_id,
                                       @Part("analysis_status") RequestBody analysis_status,
                                       @Part("workorder_id") RequestBody workorder_id,
                                       @Part("entity_id") RequestBody entity_id,
                                       @Part("relationship_type") RequestBody relationship_type,
                                       @Part("pdf_password") RequestBody pdf_password);

    @Multipart
    @POST("partner_loanapi_test.php?call=bank_statement_upload")
    Call<GlibResponse> submitNews(@Header("Authorization") String authHeader,
                            /*   @Part("first-parameter") RequestBody firstParameter,
                               @Part("second-parameter") RequestBody secondParameter,*/
                                  @Part MultipartBody.Part[] img_url,
                                  @Part("transaction_id") RequestBody transaction_id,
                                  @Part("analysis_status") RequestBody analysis_status,
                                  @Part("workorder_id") RequestBody workorder_id,
                                  @Part("entity_id") RequestBody entity_id,
                                  @Part("relationship_type") RequestBody relationship_type,
                                  @Part("pdf_password") RequestBody pdf_password);

    @Multipart
    @POST("partner_loanapi_test.php?call=issue_report")
    Call<IssueUpload> imageupload(@Header("Authorization") String authHeader,
                            /*   @Part("first-parameter") RequestBody firstParameter,
                               @Part("second-parameter") RequestBody secondParameter,*/
                                  @Part MultipartBody.Part[] img_url,
                                  @Part("app_id") RequestBody app_id,
                                  @Part("b2buser_id") RequestBody b2buser_id,
                                  @Part("title_issue") RequestBody title_issue,
                                  @Part("description_issue") RequestBody description_issue);

}
