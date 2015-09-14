package example.com.test1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class SearchYourAss extends ActionBarActivity {

    ImageView mImageView;
    Button mBtnPopFood, mBtnPopHuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_your_ass);

        mBtnPopFood = (Button) findViewById(R.id.btnPopFood);
        mBtnPopHuman = (Button) findViewById(R.id.btnPopHuman);

        mBtnPopFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFood();
            }
        });

        mBtnPopHuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchHuman();
            }
        });

        mImageView = (ImageView) findViewById(R.id.imageView);

    }

    private void searchFood(){
        int i = (int) (Math.random()*3 + 1);
        if (i==1)  mImageView.setImageResource(R.drawable.img01);
        else if (i==2) mImageView.setImageResource(R.drawable.img02);
        else mImageView.setImageResource(R.drawable.img03);
    }

    private void searchHuman(){
        int i = (int) (Math.random()*3 + 1);
        if (i==1)  mImageView.setImageResource(R.drawable.img04);
        else if (i==2) mImageView.setImageResource(R.drawable.img05);
        else mImageView.setImageResource(R.drawable.img06);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_your_ass, menu);
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
