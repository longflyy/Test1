package example.com.test1;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import static example.com.test1.R.id.chat_list;


public class ChatRoom extends ActionBarActivity {
    private ListView listview;
    private EditText chat_text;
    private Button SEND;
    private boolean position = false;
    private ChatAdapter adapter;
    private Context ctx = this;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sp = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = sp.edit();

        InItViews();
        InItViewsValueAndListener();

    }

    private void InItViews(){
        listview = (ListView) findViewById(R.id.chat_list);
        chat_text = (EditText) findViewById(R.id.txtChat);
        SEND = (Button) findViewById(R.id.btnSend);
    }

    private void InItViewsValueAndListener(){
        chat_text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String text = chat_text.getText().toString();
                editor.putString("input", text);
                editor.commit();

                    if (keyCode == KeyEvent.KEYCODE_ENTER){
                        if (event.getAction()==KeyEvent.ACTION_DOWN){
                            send();
                        }
                        return true;
                    }

                return false;
            }
        });

        adapter = new ChatAdapter(ctx, R.layout.single_message_layout);
        listview.setAdapter(adapter);
        listview.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listview.setSelection(adapter.getCount() - 1);
            }
        });

        SEND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

    }

    private void send(){
        adapter.add(new DataProvider(position, chat_text.getText().toString()));
        position = !position;
        chat_text.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat_room, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
