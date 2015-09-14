package example.com.test1;

import android.app.ActionBar;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.test1.DataProvider;

/**
 * Created by user on 5/9/2015.
 */
public class ChatAdapter extends ArrayAdapter<DataProvider> {

    private List<DataProvider> chat_list = new ArrayList<DataProvider>();
    private TextView chat_txt;
    Context ctx;

    public ChatAdapter(Context context, int resource) {
        super(context, resource);
        ctx = context;
    }

    @Override
    public void add(DataProvider object) {
        chat_list.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return chat_list.size();
    }

    @Override
    public DataProvider getItem(int position) {
        return chat_list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_message_layout,parent,false);
        }
        chat_txt = (TextView) convertView.findViewById(R.id.singleMessage);
        String Message;
        boolean POSITION;
        DataProvider provider = getItem(position);
        Message = provider.message;
        POSITION = provider.position;
        chat_txt.setText(Message);
        chat_txt.setBackgroundResource(POSITION? R.drawable.bubble1 : R.drawable.bubble2);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        if (!POSITION){
            params.gravity = Gravity.RIGHT;
        }
        else{
            params.gravity = Gravity.LEFT;
        }

        chat_txt.setLayoutParams(params);

        return convertView;
    }
}
