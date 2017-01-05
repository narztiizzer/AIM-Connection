package group.aim.framework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import group.aim.framework.connection.RequestHttpURL;
import group.aim.framework.connection.interfaces.RequestURLListener;

public class MainActivity extends AppCompatActivity implements RequestURLListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestHttpURL.to("https://dl.dropboxusercontent.com/u/83667991/Mockup/kcalsync-mockup-food-list.json")
                .addParameters("one" , 1)
                .addParameters("two" , 2 + "")
                .addParameters("three" , 3.00 + "")
                .setRequestURLListener(this)
                .start();

    }

    @Override
    public void onRequestURLSuccess(int code, String tag, String responseString) {
        Log.d("REQUEST" , "Success");
    }

    @Override
    public void onRequestURLFailed(int code, String tag, String exception) {
        Log.d("REQUEST" , "Failed");
    }
}
