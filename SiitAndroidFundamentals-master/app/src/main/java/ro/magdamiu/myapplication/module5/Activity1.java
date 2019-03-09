package ro.magdamiu.myapplication.module5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ro.magdamiu.myapplication.R;
import ro.magdamiu.myapplication.utils.Logging;

public class Activity1 extends AppCompatActivity {

    public static final String INPUT = "input";
    public static final String RESULT = "result";
    public static final int SEND_CODE = 27;

    private EditText mEditTextData;
    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        Logging.show(Activity1.this, getString(R.string.on_create));

        initView();
    }

    private void initView() {
        mEditTextData = findViewById(R.id.edit_text_data);
        mTextViewResult = findViewById(R.id.text_view_result);
    }

    public void startActivity2OnClick(View view) {
        Intent intent = new Intent(Activity1.this, Activity2.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logging.show(Activity1.this, getString(R.string.on_start));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logging.show(Activity1.this, getString(R.string.on_resume));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logging.show(Activity1.this, getString(R.string.on_pause));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logging.show(Activity1.this, getString(R.string.on_stop));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logging.show(Activity1.this, getString(R.string.on_restart));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logging.show(Activity1.this, getString(R.string.on_destroy));
    }

    public void callOnClick(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0777777777"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openUrlOnClick(View view) {
        String urlAndroidDeveloper = "https://developer.android.com";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlAndroidDeveloper));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void sendDataOnClick(View view) {
        Intent intent = new Intent(Activity1.this, Activity2.class);
        if (mEditTextData != null && mEditTextData.getText() != null) {
            String data = mEditTextData.getText().toString();
            if (data != null && !data.isEmpty()) {
                intent.putExtra(INPUT, data);
            }
        }
        startActivity(intent);
    }

    public void startActivityForResultOnClick(View view) {
        Intent intent = new Intent(this, Activity2.class);
        startActivityForResult(intent, SEND_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == SEND_CODE) {
            String result = data.getStringExtra(RESULT);
            if (result != null && !result.isEmpty()) {
                mTextViewResult.setText(result);
            }
        }
        if (resultCode == Activity.RESULT_CANCELED) {
        }
    }
}
