package estech.vmg.chatter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import estech.vmg.chatter.ChatList.ChatListAdapter;
import estech.vmg.chatter.ChatList.ChatterUser;

public class ChatListActivity extends AppCompatActivity {
    FirebaseAuth mAuth=FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user;
    ListView chatList;
    ArrayList<ChatterUser> usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_list);
        chatList=findViewById(R.id.chatsListView);
        //get firebase instance
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
        chatList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Users Selected","Selected user" + usersList.get(position).getUsername());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("N","Nothing selected");
            }
        });
        db.collection("users").get().addOnSuccessListener(queryDocumentSnapshots -> {
            usersList=new ArrayList<>();
            DocumentSnapshot doc;
            ChatterUser dbUser;
            for(int a=0;a!=queryDocumentSnapshots.size();a++){
                doc = queryDocumentSnapshots.getDocuments().get(a);
                dbUser = new ChatterUser(
                        (String)doc.get("id"),
                        (String)doc.get("token"),
                        (String)doc.get("username"),
                        (String)doc.get("email"));
                if(!dbUser.getEmail().equals(user.getEmail())){
                    usersList.add(dbUser);
                }
            }
            ChatListAdapter chatListAdapter= new ChatListAdapter(usersList,this);
            chatList.setAdapter(chatListAdapter);
        });
        chatList.setOnItemClickListener((parent, view, position, id) -> {
            //Selected user
            //Log.d("Users Selected","Selected user" + usersList.get(position).getUsername());
            Intent i=new Intent(getBaseContext(),ChatActivity.class);
            i.putExtra("otherUser",usersList.get(position));
            startActivity(i);
        });
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
        user=mAuth.getCurrentUser();
        return user!=null;
    }
}
