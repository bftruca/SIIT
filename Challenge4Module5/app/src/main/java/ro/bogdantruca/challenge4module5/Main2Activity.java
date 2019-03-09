package ro.bogdantruca.challenge4module5;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView mTextViewFirst;
    private TextView mTextViewSecond;
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        setMessage();
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

    private void setMessage() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString(MainActivity.KEY);

            if (!value.isEmpty()) {
                mTextViewFirst.setVisibility(View.VISIBLE);
                mTextViewSecond.setVisibility(View.VISIBLE);
                mTextViewFirst.setText(value);
            }
        }
    }

    public void onClickButton2(View view) {
        String message = mEditTextMessage.getText().toString();

        if (!message.isEmpty()) {
            Intent i = new Intent(Main2Activity.this, MainActivity.class);
            i.putExtra(MainActivity.KEY, message);
            startActivity(i);
        }
    }
}
