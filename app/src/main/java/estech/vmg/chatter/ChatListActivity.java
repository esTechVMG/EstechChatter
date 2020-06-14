package estech.vmg.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChatListActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_list);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user= mAuth.getCurrentUser();
        if(user==null){
            Intent i=new Intent(this,LoginActivity.class);
            startActivity(i);
        }else{
            loadChatList();
        }
    }
    public void loadChatList(){
        db = FirebaseFirestore.getInstance();
        CollectionReference usersCollectionRef= db.collection("users");
        Log.d("ChatListTest",usersCollectionRef.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        FirebaseUser user = mAuth.getCurrentUser();
        if(user==null){
            finish();
        }else{
            loadChatList();
        }*/
    }
}
