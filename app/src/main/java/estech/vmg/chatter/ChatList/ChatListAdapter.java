package estech.vmg.chatter.ChatList;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import estech.vmg.chatter.R;

public class ChatListAdapter extends BaseAdapter {
    private ArrayList<ChatterUser> list;
    private Context context;
    public ChatListAdapter(ArrayList<ChatterUser> list, Context context){
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatListHolder holder;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.chat_list_item,null);
            holder=new ChatListHolder();
            holder.tName=convertView.findViewById(R.id.item_name);
            holder.tEmail=convertView.findViewById(R.id.item_email);
            convertView.setTag(holder);
        }else{
            holder=(ChatListHolder) convertView.getTag();
        }
        holder.tName.setText(list.get(position).getUsername());
        holder.tEmail.setText(list.get(position).getEmail());
        return convertView;
    }
}
