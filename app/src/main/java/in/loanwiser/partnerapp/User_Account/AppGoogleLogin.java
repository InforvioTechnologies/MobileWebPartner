package in.loanwiser.partnerapp.User_Account;



import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import java.io.IOException;

import adhoc.app.applibrary.Config.AppUtils.Objs;
import dmax.dialog.SpotsDialog;
import in.loanwiser.partnerapp.R;


public class AppGoogleLogin extends AppCompatActivity implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener  {



    private static final String TAG = AppGoogleLogin.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;

    private Button btnSignOut, btnRevokeAccess;
    private LinearLayout llProfileLayout;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    Context mCon =this;
    Boolean alreadyLoggedIn=false;
    String PhotoUrl;
    String email;
    String personId;
    private AlertDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_google_login);
        progressDialog = new SpotsDialog(this, R.style.Custom);
        initCode();
    }

    private void initCode() {
        try {
            initUI();
            initSignIn();
            checkPreviousLogin();
            if (!alreadyLoggedIn)
                signIn();
        } catch (Exception e) {
            //  Objs.a.setErrMsg(mCon,e.getMessage());
        }
    }

    private void initUI() {
        btnSignOut = (Button) findViewById(R.id.btn_sign_out);
        btnRevokeAccess = (Button) findViewById(R.id.btn_revoke_access);
        llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);

        btnSignOut.setOnClickListener(this);
        btnRevokeAccess.setOnClickListener(this);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false, null);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false, null);
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            //  String idToken = acct.getIdToken();
            String personName = acct.getDisplayName();
            // String personPhotoUrl = acct.getPhotoUrl().toString();
            String personPhotoUrl = "0" ;

            //  PhotoUrl =  acct.getPhotoUrl().toString();
            PhotoUrl =  "0";
            personId = acct.getId();
            email = acct.getEmail();

            txtName.setText(personName);
            txtEmail.setText(email);

            Toast.makeText(AppGoogleLogin.this, "PersonId: " + personId +"\n" + "Name: " + personName +"\n" + "email: " + email +"\n"+ "Image: " + personPhotoUrl, Toast.LENGTH_SHORT).show();


            updateUI(true,acct);

        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false, null);
        }
    }

    private void initSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
      /*  mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Drive.API)
                .addScope(Drive.SCOPE_FILE)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();*/

       // mGoogleApiClient.connect();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }


    private void checkPreviousLogin() {
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
            alreadyLoggedIn=true;
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            progressDialog.show();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    progressDialog.dismiss();
                    handleSignInResult(googleSignInResult);
                    //Objs.a.showToast(mCon,"Not catched");
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }




    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }


    private void updateUI(boolean isSignedIn, GoogleSignInAccount acct) {
        if (isSignedIn) {
          //  btnSignOut.setVisibility(View.GONE);
          //  btnRevokeAccess.setVisibility(View.GONE);
         //   llProfileLayout.setVisibility(View.GONE);
            doSignUp(acct);
            //setToken(acct.getIdToken());
        } else {
          //  btnSignOut.setVisibility(View.GONE);
          //  btnRevokeAccess.setVisibility(View.GONE);
          //  llProfileLayout.setVisibility(View.GONE);
        }
    }


    private void doSignUp(GoogleSignInAccount acct) {
        try {
            new RetrieveTokenTask().execute(acct.getEmail(), acct.getDisplayName());
        } catch (Exception e) {
            //Objs.a.showToast(mCon, e.getMessage());
        }
    }

    private class RetrieveTokenTask extends AsyncTask<String, String, String> {

        String DisplayName,Email;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //   Objs.a.showProgressBar(mCon,"Authenticating account...");
        }

        @Override
        protected String doInBackground(String... params) {
            Email = params[0];
            DisplayName =params[1];
            String scopes = "oauth2:profile email";
            String token = null;
            try {
                //token = GoogleAuthUtil.getToken(mCon, DisplayName, "oauth2:https://mail.google.com/");
                token = GoogleAuthUtil.getToken(mCon, Email, scopes);
                Log.e("GMail Id" ,token +"\n"+  Email +"\n"+ DisplayName);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            } catch (UserRecoverableAuthException e) {
                //startActivityForResult(e.getIntent(), REQ_SIGN_IN_REQUIRED);
            } catch (GoogleAuthException e) {
            }catch (Exception e) {
                //   Objs.a.showToast(mCon,e.getMessage());
            }
            return token;
        }

        @Override
        protected void onPostExecute(String idToken) {
            super.onPostExecute(idToken);
            //   Objs.a.hideProgressBar(mCon);
            if (idToken!=null) {
                // setToken(idToken);
                //    Objs.a.showToast(mCon,idToken);
                Toast.makeText(getApplicationContext(),idToken,Toast.LENGTH_SHORT).show();
            }else {
                //   Objs.a.showToast(mCon,"unable get token!");
            }
        }
    }

   /* private void setToken(String idToken) {
        try {
            JSONObject jsonObject =new JSONObject();
            jsonObject.put(Params.loginType,Params.GPLUS);
            jsonObject.put(Params.trxSource,Params.a);
            jsonObject.put(Params.customerEmail, email);
            jsonObject.put(Params.googleUserId, personId);
            jsonObject.put(Params.googleImageUrl,PhotoUrl);
            jsonObject.put(Params.googleAccessToken,idToken);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            JSONObject J =new JSONObject();
            J.put(Params.customerDetails,jsonArray);

            Objs.a.loadData(mCon, Urls.CustomerLogin_POST, Request.Method.POST, J, new Alerts.AppLoadRequestListener<JSONObject>() {
                @Override
                public void onResult(JSONObject object) throws JSONException {

                    // Objs.a.showToast(mCon,object.toString());
                    JSONArray jsonArray = object.getJSONArray(Params.customerDetails);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject curr = jsonArray.getJSONObject(i);
                        Objs.a.showToast(mCon, curr.getString(Params.customerId));
                        Pref.putUID(mCon,curr.getString(Params.customerId));
                        Objs.a.showToast(mCon,getResources().getString(R.string.login_success));
                        finish();
                        Objs.ac.StartActivity(mCon, Home.class);

                    }
                    //Objs.a.showToast(mCon, String.valueOf(jsonArray));
                }
            });
        } catch (JSONException e) {
            Objs.a.showToast(mCon,e.getMessage());
        }
    }*/

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_sign_out:
                signOut();
                break;

            case R.id.btn_revoke_access:
                revokeAccess();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onBackPressed() {
        signOut();
        Objs.ac.StartActivity(mCon, Welcome_Page.class);
        finish();
        super.onBackPressed();
    }

}
