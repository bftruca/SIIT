package ro.bogdantruca.challenge2module5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String message = "onCreate()";

        Logging.show(this, message);
        ToastClass.show(MainActivity.this, message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String message = "onStart()";

        Logging.show(this, message);
        ToastClass.show(MainActivity.this, message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String message = "onResume()";

        Logging.show(this, message);
        ToastClass.show(MainActivity.this, message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String message = "onPause()";

        Logging.show(this, message);
        ToastClass.show(MainActivity.this, message);
    }

    @Override
    protected void onStop() {
        super.onStop();
        String message = "onStop()";

        Logging.show(this, message);
        ToastClass.show(MainActivity.this, message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String message = "onDestroy()";

        Logging.show(this, message);
        ToastClass.show(MainActivity.this, message);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String message = "onRestart()";

        Logging.show(this, message);
        ToastClass.show(MainActivity.this, message);
    }

    public void onClickButton(View view) {
        Intent i = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(i);
    }
}
