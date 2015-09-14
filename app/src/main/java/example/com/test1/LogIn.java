package example.com.test1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;


public class LogIn extends ActionBarActivity {

    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;
    private TextView mTxtWelcome;
    private Button mBtnEnjoy;
    private LoginButton mBtnLogIn;
    private CallbackManager mCallBackMsg;
    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d("Concept", "onSuccess");
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            mTxtWelcome.setText(welcomeMsg(profile));
            mBtnEnjoy.setVisibility(View.VISIBLE);
        }

        @Override
        public void onCancel() {
            Log.d("Concept", "onCancel");
        }

        @Override
        public void onError(FacebookException e) {
            Log.d("Concept", "onError" + e);
        }

    };

    private String welcomeMsg(Profile profile){
        StringBuffer stringBuffer = new StringBuffer();
        if (profile != null) {
            stringBuffer.append("Welcome " + profile.getName());
        }
        return stringBuffer.toString();

    }

    private void enjoy(){
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_log_in);

        mBtnEnjoy = (Button) findViewById(R.id.btnEnjoy);
        mTxtWelcome = (TextView) findViewById(R.id.txtWelcome);
        mBtnLogIn = (LoginButton) findViewById(R.id.login_button);
        mCallBackMsg = CallbackManager.Factory.create();
        mBtnLogIn.registerCallback(mCallBackMsg, mCallBack);

        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken currentToken) {
                Log.d("Concept", "" + currentToken);
            }
        };
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.d("Concept", "" + currentProfile);
                mTxtWelcome.setText(welcomeMsg(currentProfile));

            }
        };
        mTokenTracker.startTracking();
        mProfileTracker.startTracking();

        mBtnEnjoy.setVisibility(View.INVISIBLE);
        mBtnEnjoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enjoy();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        mTxtWelcome.setText(welcomeMsg(profile));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
        mBtnEnjoy.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallBackMsg.onActivityResult(requestCode,resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
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
