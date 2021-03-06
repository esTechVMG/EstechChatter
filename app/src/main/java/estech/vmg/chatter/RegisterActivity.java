package estech.vmg.chatter;

import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    public TextInputEditText pass,verify,user,eMail;
    public TextInputLayout eMailLayout,passLayout;
    public Button register;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        mAuth=FirebaseAuth.getInstance();
        pass=findViewById(R.id.passRegisterInput);
        verify=findViewById(R.id.passVerifyRegisterInput);
        user=findViewById(R.id.userNameRegisterInput);
        eMail=findViewById(R.id.eMailRegisterInput);
        register=findViewById(R.id.regRegisterButton);
        eMailLayout=findViewById(R.id.eMailRegisterLayout);
        passLayout=findViewById(R.id.passRegisterLayout);
        register.setOnClickListener(v -> {
            String  registerEmail=CommonTools.getStringFromTextInputEditText(eMail),
                    registerUserName=CommonTools.getStringFromTextInputEditText(user),
                    registerPass=CommonTools.getStringFromTextInputEditText(pass),
                    registerVerify=CommonTools.getStringFromTextInputEditText(verify);
            if(registerEmail.isEmpty()|registerUserName.isEmpty()|registerPass.isEmpty()|registerVerify.isEmpty()){//Empty Fields
                CommonTools.basicToast(getBaseContext(),getString(R.string.field_not_filled));
            }else if(!CommonTools.isEmailValid(registerEmail)){ //Invalid email

            }else if(!registerPass.equals(registerVerify)){ //Passwords don t match

            }else{ //Firebase Login
                mAuth.createUserWithEmailAndPassword(registerEmail,registerPass).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){//Successful login
                        FirebaseUser user=mAuth.getCurrentUser();
                        UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
                        builder.setDisplayName(registerUserName);
                        UserProfileChangeRequest profileUpdates = builder.build();
                        assert user != null;
                        user.updateProfile(profileUpdates).addOnCompleteListener(task1 -> {
                            //Completed Auth. return to login
                            CommonTools.basicToast(getBaseContext(),user.getEmail() + user.getDisplayName());//debug
                            finish();
                        });

                    }else{//Fail
                        CommonTools.basicToast(getBaseContext(),"Register Failed");
                    }
                });
            }
        });
    }
}
