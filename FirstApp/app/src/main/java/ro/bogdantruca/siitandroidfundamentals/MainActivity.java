package ro.bogdantruca.siitandroidfundamentals;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Utils.Logging;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewInfoText;
    private ImageView mImageViewProfilePicture;
    private Button mButtonChangeText;
    private EditText mEditTextPhoneNumber;
    private CheckBox mCheckBoxTerms;
    private RadioButton mRadioButtonYes;
    private RadioButton mRadioButtonNo;
    private RadioGroup mRadioGroupSelection;
    private RatingBar mRatingBarReview;
    private Switch mSwitchOnOff;
    private SeekBar mSeekbarMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List list = null;

        //TODO check null pointer exception

        Logging.show(MainActivity.this, "Hello From Android! :)" );

        initView();
    }

    private void initView () {
        mTextViewInfoText = findViewById(R.id.activity_main_textview_info_text);
        mTextViewInfoText.setText(getString(R.string.hello_from_java));
        String valueOfInfoText = mTextViewInfoText.getText().toString();

        if (valueOfInfoText != null) {
            Logging.show(MainActivity.this, valueOfInfoText);
        }

        mImageViewProfilePicture = findViewById(R.id.imageview_profile_picture);
        mImageViewProfilePicture.setImageResource(R.drawable.user);

        mButtonChangeText = findViewById(R.id.button_change_text);
        mButtonChangeText.setText(getString(R.string.change_text));

        mEditTextPhoneNumber = findViewById(R.id.edit_text_phone_number);

        mCheckBoxTerms = findViewById(R.id.checkbox_terms);

        mRadioGroupSelection = findViewById(R.id.radio_group_selection);
        mRadioButtonNo = findViewById(R.id.radio_button_no);
        mRadioButtonYes = findViewById(R.id.radio_button_yes);

        mRatingBarReview = findViewById(R.id.rating_bar);

        mSwitchOnOff = findViewById(R.id.switch_on_off);

        mSeekbarMoney = findViewById(R.id.seekbar_money);
    }

    public void changeTextViewValue(View view) {
        mButtonChangeText.setText(getString(R.string.new_text));

        if (mEditTextPhoneNumber != null) {
            String phoneNumber = mEditTextPhoneNumber.getText().toString();
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                mTextViewInfoText.setText(phoneNumber);
            } else {
                mEditTextPhoneNumber.setError(getString(R.string.required));
            }
        }

        if (mCheckBoxTerms != null) {
            boolean isChecked = mCheckBoxTerms.isChecked();
            Toast.makeText(MainActivity.this, "Checkbox status = " + isChecked, Toast.LENGTH_LONG).show();

            if (isChecked) {
                mRadioGroupSelection.setVisibility(View.VISIBLE);
                boolean yesIsChecked = mRadioButtonYes.isChecked();
                boolean noIsChecked = mRadioButtonNo.isChecked();
                Logging.show(MainActivity.this, "first = " + yesIsChecked + " " + "second = " + noIsChecked);
            } else {
                mRadioGroupSelection.setVisibility(View.GONE);
            }
        }

        float ratingValue = mRatingBarReview.getRating();
        Logging.show(MainActivity.this, "rating = " + ratingValue);

        boolean isSwitchedChecked = mSwitchOnOff.isChecked();
        String textOn = mSwitchOnOff.getTextOn().toString();
        String textOff = mSwitchOnOff.getTextOff().toString();
        Logging.show (MainActivity.this, isSwitchedChecked + " " + textOn  + " " + textOff);

        int money = mSeekbarMoney.getProgress();
        Logging.show(MainActivity.this, " value = " + money);
    }
}
