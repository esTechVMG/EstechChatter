package estech.vmg.chatter.Bubbles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import estech.vmg.chatter.R;

public class ChatBubbleAdapter extends RecyclerView.Adapter {
    ArrayList<Message> messages;
    Context context;
    public ChatBubbleAdapter(Context context, ArrayList<Message> messages){
        this.context=context;
        this.messages=messages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChatBubbleHolder holder;
        View v = LayoutInflater.from(context).inflate(R.layout.chat_bubble,
                parent, false);
        holder = new ChatBubbleHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatBubbleHolder myHolder=(ChatBubbleHolder) holder;
        Message message= messages.get(position);
        myHolder.user.setText(message.getUser());
        myHolder.text.setText(message.getText());
        myHolder.date.setText(message.getDate());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
