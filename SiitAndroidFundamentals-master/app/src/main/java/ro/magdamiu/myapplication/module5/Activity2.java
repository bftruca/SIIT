package ro.magdamiu.myapplication.module5;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ro.magdamiu.myapplication.R;
import ro.magdamiu.myapplication.utils.Logging;

public class Activity2 extends AppCompatActivity {

    private TextView mTextViewDisplayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Logging.show(Activity2.this, getString(R.string.on_start));

        initView();

        if (getIntent() != null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                String valueSent = bundle.getString(Activity1.INPUT);
                mTextViewDisplayData.setText(valueSent);
            }
        }
    }

    private void initView() {
        mTextViewDisplayData = findViewById(R.id.text_view_get_data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logging.show(Activity2.this, getString(R.string.on_start));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logging.show(Activity2.this, getString(R.string.on_resume));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logging.show(Activity2.this, getString(R.string.on_pause));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logging.show(Activity2.this, getString(R.string.on_stop));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logging.show(Activity2.this, getString(R.string.on_restart));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logging.show(Activity2.this, getString(R.string.on_destroy));
    }

    public void sendDataBackOnClick(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Activity1.RESULT, "hello");
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
