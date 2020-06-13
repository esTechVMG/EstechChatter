package estech.vmg.chatter.Bubbles;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import estech.vmg.chatter.R;

public class ChatBubbleAdapter extends BaseAdapter {
    ChatBubbleAdapter(Context context, ArrayList<Message> messages){
        this.context=context;
        this.messages=messages;
    }
    ArrayList<Message> messages;
    private final Context context;
    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatBubbleHolder holder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.chat_bubble,null);
            holder=new ChatBubbleHolder();
            holder.text=convertView.findViewById(R.id.message);
            holder.date=convertView.findViewById(R.id.date);
            convertView.setTag(holder);
        }else{
            holder=(ChatBubbleHolder)convertView.getTag();
        }
        return convertView;
    }
}
