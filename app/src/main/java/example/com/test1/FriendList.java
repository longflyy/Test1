package example.com.test1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class FriendList extends ActionBarActivity {

    ListView mListFd;
    EditText mEdtAddFd;
    Button mBtnAddFd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        mEdtAddFd = (EditText) findViewById(R.id.edtAddFd);
        mBtnAddFd = (Button) findViewById(R.id.btnAddFd);

        mListFd = (ListView) findViewById(R.id.listFriend);
        final String fds[] = {"Tom Cruise", "Brad Pitt", "George Clooney", "Johnny Depp", "Leonardo Dicarpio"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FriendList.this, android.R.layout.simple_list_item_1, fds);
        mListFd.setAdapter(adapter);

        mBtnAddFd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = mListFd.getCount();
                String fds2[] = new String[k + 1];
                for (int i = 0; i < k; i++)
                    fds2[i] = mListFd.getItemAtPosition(i).toString();
                fds2[k] = mEdtAddFd.getText().toString();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FriendList.this, android.R.layout.simple_list_item_1, fds2);
                mListFd.setAdapter(adapter);
                mEdtAddFd.setText("");
            }
        });

        mListFd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chat();
            }
        });

    }

    private void chat(){
        Intent intent = new Intent();
        intent.setClass(this, ChatRoom.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_list, menu);
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
