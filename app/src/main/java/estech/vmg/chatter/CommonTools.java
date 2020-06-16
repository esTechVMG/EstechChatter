package estech.vmg.chatter;

import android.content.Context;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonTools {
    public static void basicToast(Context c,String message){
        Toast. makeText(c,message,Toast. LENGTH_SHORT).show();
    }
    public static boolean isEmailValid(String email) {//This method checks with a regular expression if a input string has a valid email format
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static String getStringFromTextInputEditText(TextInputEditText t){
        return  Objects.requireNonNull(t.getText()).toString();
    }
}
