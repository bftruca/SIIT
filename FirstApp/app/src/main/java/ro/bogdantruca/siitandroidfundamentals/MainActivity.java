package ro.bogdantruca.siitandroidfundamentals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List list = null;

        //TODO check null pointer exception

        Logging.show(MainActivity.this, "Hello From Android! :)" );
    }
}
