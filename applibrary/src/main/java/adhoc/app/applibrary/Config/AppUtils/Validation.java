package adhoc.app.applibrary.Config.AppUtils;

import android.content.Context;
import android.graphics.PorterDuff;
import com.google.android.material.textfield.TextInputLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import android.text.TextUtils;

import adhoc.app.applibrary.R;


/**
 * Created by user on 3/14/2016.
 */
public class Validation {

    public final static boolean ValidateEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public static Boolean isVlaidEmail(AppCompatEditText appCompatEditText){
        if (ValidateEmail(appCompatEditText.getText().toString()) ){
            return true;
        }else {
            appCompatEditText.setError(AS.invalid_email);
            appCompatEditText.requestFocus();
        }
        return false;
    }
    public  Boolean doValidation(Context mCon,AppCompatEditText appCompatEditText,int msgSringid,int d){

        if (TextUtils.isEmpty(appCompatEditText.getText().toString().trim())){

            String msg = mCon.getResources().getString(R.string.error_field_required);
            appCompatEditText.setError(msg);
            appCompatEditText.requestFocus();
            return false;

        }else {
            switch (d){
                case 1:
                    if (appCompatEditText.length()<10){
                        String msg = mCon.getResources().getString(msgSringid);
                        appCompatEditText.setError(msg);
                        appCompatEditText.requestFocus();
                        return false;
                    }
                    break;
                case 2:
                    if (appCompatEditText.length()<2){
                        String msg = mCon.getResources().getString(msgSringid);
                        appCompatEditText.setError(msg);
                        appCompatEditText.requestFocus();
                        return false;
                    }
                    break;
            }
            return true;

        }

    }

    public static void RemoveError(Context mCon, int til_id) {

        TextInputLayout til = (TextInputLayout) ((AppCompatActivity)mCon).findViewById(til_id);
        til.setError(null);
    }

    public static Boolean validateMobileNumber(Context mCon,int til_id,AppCompatEditText appCompatEditText){
        if ((!TextUtils.isEmpty(appCompatEditText.getText().toString().trim())) && appCompatEditText.length()>9 ){
            return true;
        }else {
            appCompatEditText.setError(AS.invalid_contact);
            appCompatEditText.requestFocus();
        }
        return false;
    }

    public static void getFocus(Context mCon,int til_id,AppCompatEditText appCompatEditText,String msg) {
        appCompatEditText.requestFocus();
        TextInputLayout til = (TextInputLayout) ((AppCompatActivity)mCon).findViewById(til_id);
        til.setErrorEnabled(true);
        til.setError(msg);
    }
    public static Boolean validatePassword(Context mCon,int til_id,AppCompatEditText appCompatEditText){
        if ((!TextUtils.isEmpty(appCompatEditText.getText().toString().trim())) && appCompatEditText.length()>2){
            RemoveError(mCon,til_id);
            //appCompatEditText.getBackground().mutate().setColorFilter(mCon.getResources().getColor(R.color.colorBlue), PorterDuff.Mode.SRC_ATOP);
            return true;
        }else {
            // appCompatEditText.getBackground().mutate().setColorFilter(mCon.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_ATOP);
            getFocus(mCon,til_id,appCompatEditText,AS.invalid_pwd);
        }
        return false;
    }

    public void setError(Context mCon,int id,String msg,AppCompatEditText editText) {
        TextInputLayout til = (TextInputLayout)((AppCompatActivity) mCon).findViewById(id);
        til.setErrorEnabled(true);
        til.setError(msg);
        editText.getBackground().setColorFilter(mCon.getResources().getColor(R.color.colorRed), PorterDuff.Mode.SRC_ATOP);
        editText.requestFocus();
    }
    public void RemoveError(Context mCon,int id,AppCompatEditText editText) {
        TextInputLayout til = (TextInputLayout)((AppCompatActivity) mCon).findViewById(id);
        til.setErrorEnabled(false);
        til.setError(null);
        editText.getBackground().setColorFilter(mCon.getResources().getColor(R.color.colorGreen), PorterDuff.Mode.SRC_ATOP);
    }
}
