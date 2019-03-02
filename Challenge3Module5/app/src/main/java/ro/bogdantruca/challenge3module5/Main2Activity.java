package ro.bogdantruca.challenge3module5;

import android.content.Intent;
import android.nfc.TagLostException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static ro.bogdantruca.challenge3module5.R.string.message1;
import static ro.bogdantruca.challenge3module5.R.string.message2;
import static ro.bogdantruca.challenge3module5.R.string.message3;

public class Main2Activity extends AppCompatActivity {

    private static final String name = "Name";
    private String mKey;

    private TextView mTextViewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getTheKey();

        init();
    }

    private void getTheKey() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            mKey = extras.getString(name);
        }
    }

    private void init() {
        mTextViewText = findViewById(R.id.textView);

        if (mKey.equals("one")) {
            mTextViewText.setText(message1);
        }

        if (mKey.equals("two")) {
            mTextViewText.setText(message2);
        }

        if (mKey.equals("three")) {
            mTextViewText.setText(message3);
        }
    }

    public void onClickButtonBack(View view) {
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        if (intent != null)
        {
            startActivity(intent);
        }
    }
}
