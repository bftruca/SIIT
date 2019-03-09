package ro.magdamiu.myapplication.module6;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ro.magdamiu.myapplication.R;

public class DynamicFragmentActivity extends AppCompatActivity {

    public static final String EMAIL = "email";

    private EditText mEditTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);

        initView();

       // createDynamicFragment(new BlankFragment());
    }

    private void initView() {
        mEditTextEmail = findViewById(R.id.edit_text_mail);
    }

    private void createDynamicFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    public void SendEmailOnClick(View view) {
        Bundle bundle = new Bundle();
        if(mEditTextEmail != null) {
            String email = mEditTextEmail.getText().toString();

            if (email != null && !email.isEmpty()) {
                bundle.putString(EMAIL, email);
                BlankFragment blankFragment = new BlankFragment();
                blankFragment.setArguments(bundle);
                createDynamicFragment(blankFragment);
            }
        }
    }
}
