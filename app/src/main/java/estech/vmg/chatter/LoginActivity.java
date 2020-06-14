package estech.vmg.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Objects;

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
            //Store fields in strings
            String  authEmail=CommonTools.getStringFromTextInputEditText(eMail),
                    authPass=CommonTools.getStringFromTextInputEditText(pass);
            //Reset Errors in TextInputLayouts
            eMailLayout.setErrorEnabled(false);
            passLayout.setErrorEnabled(false);

            if(authEmail.isEmpty()|authPass.isEmpty()){//Check for empty fields
                CommonTools.basicToast(getBaseContext(),getString(R.string.field_not_filled));
            }else if(!CommonTools.isEmailValid(authEmail)){//Check for invalid email
                eMailLayout.setError("Invalid email");
            }else{//Firebase Login
                mAuth.signInWithEmailAndPassword(authEmail,authPass).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){//Successful login
                        FirebaseUser user=mAuth.getCurrentUser();
                        assert user != null;
                        if(!user.isEmailVerified()){//Check for verified email
                            //AlertDialog asking for email resend
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("E-mail not verified")
                                    .setMessage("Do you want to resend the verification E-mail?")
                                    .setPositiveButton("Yes", (dialog, which) -> {
                                        user.sendEmailVerification();
                                        CommonTools.basicToast(getBaseContext(),"Verification E-Mail Resent");
                                    })
                                    .setNegativeButton("No",null)
                                    .setIcon(R.drawable.ic_baseline_warning_24)
                                    .setCancelable(false)
                                    .show();
                            mAuth.signOut();
                        }else{//Email is verified
                            //Add to collection in FireStore
                            FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful()){
                                    String token= Objects.requireNonNull(task1.getResult()).getToken();
                                    ChatterUser chatterUser = new ChatterUser(token,user.getUid());
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    CollectionReference users = db.collection("users");
                                    users.document(Objects.requireNonNull(user.getEmail())).set(chatterUser);
                                }
                            });
                            finish();//return to main activity
                        }

                    }else{//Firebase Failed sign in
                        CommonTools.basicToast(getBaseContext(),"Login Failed");
                        eMailLayout.setError("Invalid credentials");
                    }
                });
            }
        });

        register.setOnClickListener(v -> {
            Intent i=new Intent(getBaseContext(),RegisterActivity.class);
            startActivity(i);
        });
    }
}