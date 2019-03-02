package ro.bogdantruca.challenge3module5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String name = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton1(View view) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra(name, "one");
        startActivity(intent);
    }

    public void onClickButton2(View view) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra(name, "two");
        startActivity(intent);
    }

    public void onClickButton3(View view) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra(name, "three");
        startActivity(intent);
    }
}
