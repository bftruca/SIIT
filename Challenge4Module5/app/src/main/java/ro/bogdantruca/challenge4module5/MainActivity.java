package ro.bogdantruca.challenge4module5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String KEY = "THE_KEY";

    private TextView mTextViewFirst;
    private TextView mTextViewSecond;
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setMessage();
    }

    private void setMessage() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString(KEY);

            if (!value.isEmpty()) {
                mTextViewFirst.setVisibility(View.VISIBLE);
                mTextViewSecond.setVisibility(View.VISIBLE);
                mTextViewFirst.setText(value);
            }
        }
    }

    private void initView() {
        mTextViewFirst = findViewById(R.id.textView);
        mTextViewSecond = findViewById(R.id.textView2);
        mEditTextMessage = findViewById(R.id.edit_text_message);

        String message = mEditTextMessage.getText().toString();
        if (message.isEmpty()) {
           mTextViewFirst.setVisibility(View.GONE);
           mTextViewSecond.setVisibility(View.GONE);
        }
    }

    public void onClickButton(View view) {
        String message = mEditTextMessage.getText().toString();

        if (!message.isEmpty()) {
            Intent i = new Intent(MainActivity.this, Main2Activity.class);
            i.putExtra(KEY, message);
            startActivity(i);
        }
    }
}
