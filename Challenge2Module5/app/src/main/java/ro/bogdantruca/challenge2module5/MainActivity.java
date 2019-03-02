package ro.bogdantruca.challenge2module5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logging.show(this, "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logging.show(this, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logging.show(this, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logging.show(this, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logging.show(this, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logging.show(this, "onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logging.show(this, "onRestart()");
    }
}
