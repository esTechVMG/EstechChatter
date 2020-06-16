package estech.vmg.chatter.Bubbles;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import estech.vmg.chatter.R;

public class ChatBubbleHolder extends RecyclerView.ViewHolder {
    TextView text,date,user;

    public ChatBubbleHolder(@NonNull View itemView) {
        super(itemView);
        user=itemView.findViewById(R.id.chat_bubble_user);
        text=itemView.findViewById(R.id.chat_bubble_text);
        date=itemView.findViewById(R.id.chat_bubble_date);
    }
}
