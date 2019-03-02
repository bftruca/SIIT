package ro.bogdantruca.challenge5module5;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextWebsite;
    private EditText mEditTextLocation;
    private EditText mEditTextShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mEditTextLocation = findViewById(R.id.editText_location);
        mEditTextWebsite = findViewById(R.id.editText_website);
        mEditTextShare = findViewById(R.id.editText_share_text);
    }

    public void onClickOpenWebsite(View view) {
        String url = mEditTextWebsite.getText().toString();
        Uri website = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, website);

        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onClikcOpenLocation(View view) {
        String location = mEditTextLocation.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + location);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onClickOpenShareText(View view) {
        String textToShare = mEditTextShare.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle(R.string.share_text_with).setText(textToShare);
    }
}
