package estech.vmg.chatter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class LoginActivity extends AppCompatActivity {

    public Button register,login;
    public TextInputEditText pass,eMail;
    public FirebaseAuth mAuth;
    public TextInputLayout passLayout,eMailLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth=FirebaseAuth.getInstance();
        eMailLayout=findViewById(R.id.loginEmailLayout);
        passLayout=findViewById(R.id.loginPassLayout);
        eMail=findViewById(R.id.loginEMail);
        pass=findViewById(R.id.loginPass);
        login=findViewById(R.id.loginButton);
        register=findViewById(R.id.registerLoginButton);
        login.setOnClickListener(v -> {
            String  authEmail=CommonTools.getStringFromTextInputEditText(eMail),
                    authPass=CommonTools.getStringFromTextInputEditText(pass);
            eMailLayout.setErrorEnabled(false);
            passLayout.setErrorEnabled(false);
            if(authEmail.isEmpty()|authPass.isEmpty()){//Check for empty fields
                CommonTools.basicToast(getBaseContext(),getString(R.string.field_not_filled));
            }else if(!CommonTools.isEmailValid(authEmail)){//Check for invalid email
                eMailLayout.setError("Invalid email");
            }else{
                mAuth.signInWithEmailAndPassword(authEmail,authPass).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){//Successful login
                        finish();//return to main activity
                    }else{//Fail
                        CommonTools.basicToast(getBaseContext(),"Login Failed");
                        eMailLayout.setError("Invalid credentials");
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}