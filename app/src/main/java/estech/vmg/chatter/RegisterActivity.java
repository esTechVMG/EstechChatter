package estech.vmg.chatter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    public TextInputEditText pass,verify,user,eMail;
    public Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        pass=findViewById(R.id.passRegisterInput);
        verify=findViewById(R.id.passVerifyRegisterInput);
        user=findViewById(R.id.userNameRegisterInput);
        eMail=findViewById(R.id.eMailRegisterInput);
        register=findViewById(R.id.regRegisterButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
