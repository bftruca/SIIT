package ro.bogdantruca.challenge1module3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTexEmail;
    private EditText mEditTextPhone;
    private EditText mEditTextInfo;
    private Button mSubmitButton;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView () {
        mEditTexEmail = findViewById(R.id.email_edit_text);
        mEditTextPhone = findViewById(R.id.phone_edit_text);
        mEditTextInfo = findViewById(R.id.edit_text_show_info);
        mSubmitButton = findViewById(R.id.button_submit);
        mCheckBox = findViewById(R.id.checkbox_item);
    }

    public boolean validMail(String mail) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(mail).matches();
    }

    public void validate_email_and_phone(View view) {
        String email = mEditTexEmail.getText().toString();
        String phone = mEditTextPhone.getText().toString();
        String info = mEditTextInfo.getText().toString();

        boolean isValidEmail = validMail(email);
        boolean isChecked = mCheckBox.isChecked();

        if (!info.isEmpty()) {
            mEditTextInfo.setText("");
        }

        if (email.isEmpty() || !isValidEmail) {
            mEditTexEmail.setError(getString(R.string.email_error));
            return;
        }

        if (phone.isEmpty()) {
            mEditTextPhone.setError(getString(R.string.phone_error));
            return;
        }

        if (!isChecked) {
            Toast.makeText(MainActivity.this, "You have to accept the Terms & Conditions", Toast.LENGTH_LONG).show();
            return;
        }

        if (isValidEmail && !phone.isEmpty() && isChecked) {
            String infos = "Email: " + email + "\n" + "Phone: " + phone;
            mEditTextInfo.setText(infos);
        }
    }
}
