package estech.vmg.chatter;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChatListActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_list);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user= mAuth.getCurrentUser();
        if(user==null){
            Intent i=new Intent(this,LoginActivity.class);
            startActivity(i);
        }
    }
}
