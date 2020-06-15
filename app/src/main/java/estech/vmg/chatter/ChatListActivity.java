package estech.vmg.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChatListActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_list);
        //get firebase instance
        mAuth=FirebaseAuth.getInstance();
        //Firebase user check
        if(isUserLoggedIn()){
            //Load Chat List
            loadChatList();
        }else{
            //Open login
            openLogin();
        }
    }
    public void loadChatList(){
        db = FirebaseFirestore.getInstance();
        CollectionReference usersCollectionRef= db.collection("users");
        Log.d("ChatListTest",usersCollectionRef.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.chatlist_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings_menu_item:
                //Open settings menu
                Log.d("SettingsChatter","Opening settings");
                openSettings();
                break;
            case R.id.signout_menu_item:
                //Sign out user and open login again
                mAuth.signOut();
                openLogin();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isUserLoggedIn()){
            loadChatList();
        }else{
            finish();
        }
    }
    private void openLogin(){
        Intent i=new Intent(getBaseContext(),LoginActivity.class);
        startActivity(i);
    }
    private void openSettings(){
        Intent i=new Intent(getBaseContext(),SettingsActivity.class);
        startActivity(i);
    }
    private boolean isUserLoggedIn(){
        return mAuth.getCurrentUser()!=null;
    }
}
