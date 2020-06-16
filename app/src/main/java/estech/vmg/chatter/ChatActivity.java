package estech.vmg.chatter;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import estech.vmg.chatter.Bubbles.ChatBubbleAdapter;
import estech.vmg.chatter.Bubbles.Message;
import estech.vmg.chatter.ChatList.ChatterUser;

public class ChatActivity extends AppCompatActivity {
    public Button sendButton;
    public EditText messageInput;
    public RecyclerView messages;
    public FirebaseUser user;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    public String document;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_view);
        //Firebase instances
        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        db=FirebaseFirestore.getInstance();
        //Find views
        messages=findViewById(R.id.chatMessages);
        messageInput=findViewById(R.id.messageSendBox);
        sendButton=findViewById(R.id.sendButton);
        //Adapter configuration
        ArrayList<Message> messagesList=new ArrayList<Message>();
        LinearLayoutManager llm = new LinearLayoutManager(this,
                RecyclerView.VERTICAL, false);

        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);

        messages.setLayoutManager(llm);
        ChatBubbleAdapter adapter = new ChatBubbleAdapter(this,messagesList);
        messages.setAdapter(adapter);
        //messagesList.add(new Message());//Test Message
        ChatterUser otherUser =getIntent().getParcelableExtra("otherUser");
        //The name of documents is the two emails joint in a string
        assert otherUser != null;
        List<String> mails= Arrays.asList(user.getEmail(),otherUser.getEmail());
        //Sorts alphabetically the emails. needed for document request
        mails=mails
                .stream()
                .sorted(Comparator.comparing(String::toString))
                .collect(Collectors.toList());
        document=mails.get(0)+mails.get(1);
        Map<String, Object> data = new HashMap<>();
        data.put("exist",true);//Needs at least one value to create document
        db.collection("messages").document(document).set(data, SetOptions.merge());
        db.collection("messages").document(document).collection("messageList").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("DBMessages","Getting Messages");
                List<DocumentSnapshot> docs =queryDocumentSnapshots.getDocuments();
                Log.d("BDMessages","Number of messages:" + docs.size());
                for (int a=0;a!=docs.size();a++){
                    String user= String.valueOf(docs.get(a).get("user")),text=String.valueOf(docs.get(a).get("text")),date=String.valueOf(docs.get(a).get("date"));
                    Log.d("DBMessages:","Message from:" + user + "/text: " + text + " /at date:" + date);
                    messagesList.add(new Message(user,text,date));
                    adapter.notifyDataSetChanged();
                }
            }
        });
        sendButton.setOnClickListener(v -> {
            String textToSend=messageInput.getText().toString();
            messageInput.setText("");
            if(!textToSend.isEmpty()){
                Message send=new Message(user.getEmail(),textToSend,"Test Date");
                messagesList.add(send);
                db.collection("messages").document(document).collection("messageList").add(send);
            }
        });
    }
    private QuerySnapshot getChatPath(){
        return null;
    }

}
