package ro.bogdantruca.module9challenge1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextFirstName;
    private EditText mEditTextLastName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private CheckBox mCheckBoxAcceptTerms;
    private TextView mTextViewDisplayData;

    private Button mButtonSaveData;
    private Button mButtonClearData;
    private Button mButtonDisplayData;

    private Button mButtonSaveToFile;
    private Button mButtonDisplayFromFile;

    //for sharedPreferences
    public static final String SHARED_PREFS = "sharedPrefs";

    public static final String SP_FIRST_NAME = "spFirstName";
    public static final String SP_LAST_NAME = "spLastName";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_PASSWORD = "spPassword";
    public static final String SP_TERMS = "spTerms";

    private String spFirstName = "";
    private String spLastName = "";
    private String spEmail = "";
    private String spPassword = "";
    private boolean spTerms = false;

    //for file manager
    String filename = "myfile.txt";
    String fileContents = "";
    FileOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mButtonSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //save data to sharepref
                if (fieldsVerifications(v)) {
                    spFirstName = mEditTextFirstName.getText().toString();
                    spLastName = mEditTextLastName.getText().toString();
                    spEmail = mEditTextEmail.getText().toString();
                    spPassword = mEditTextPassword.getText().toString();
                    spTerms = mCheckBoxAcceptTerms.isChecked();
                    saveDataSharedPref();
                    Toast.makeText(MainActivity.this, getString(R.string.data_saved_to_shared_pref), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonClearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDataSharefPref();
                Toast.makeText(MainActivity.this, getString(R.string.data_cleared_from_shared_pref), Toast.LENGTH_SHORT).show();
            }
        });

        mButtonDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDataSharePref();
            }
        });

        mButtonSaveToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //save data to internal file
                if (fieldsVerifications(v)) {
                    spFirstName = mEditTextFirstName.getText().toString();
                    spLastName = mEditTextLastName.getText().toString();
                    spEmail = mEditTextEmail.getText().toString();
                    spPassword = mEditTextPassword.getText().toString();
                    spTerms = mCheckBoxAcceptTerms.isChecked();

                    saveDataToFile();
                    Toast.makeText(MainActivity.this, "Data saved to internal file", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonDisplayFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataFromFile();
            }
        });
    }

    private void initView() {
        mEditTextFirstName = findViewById(R.id.edit_text_first_name);
        mEditTextLastName = findViewById(R.id.edit_text_last_name);
        mEditTextEmail = findViewById(R.id.edit_text_email);
        mEditTextPassword = findViewById(R.id.edit_text_password);
        mCheckBoxAcceptTerms = findViewById(R.id.check_box_accept_terms);
        mTextViewDisplayData = findViewById(R.id.text_view_display_data);
        mTextViewDisplayData.setText("");
        mButtonSaveData = findViewById(R.id.button_save_data);
        mButtonClearData = findViewById(R.id.button_clear_data);
        mButtonDisplayData = findViewById(R.id.button_display_data);
        mButtonSaveToFile = findViewById(R.id.button_save_to_file);
        mButtonDisplayFromFile = findViewById(R.id.button_display_from_file);
    }

    private boolean fieldsVerifications(View v) {

        boolean response = false;

        String firstName = mEditTextFirstName.getText().toString();
        String lastName = mEditTextLastName.getText().toString();
        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();

        //for first name
        if (firstName.matches(".*[^a-zA-Z].*")) {
            mEditTextFirstName.requestFocus();
            mEditTextFirstName.setError(getString(R.string.first_name_invalid));
        }
        else if (firstName.isEmpty()) {
            mEditTextFirstName.requestFocus();
            mEditTextFirstName.setError(getString(R.string.this_field_required));
        }
        else if (firstName.length() < 3) {
            mEditTextFirstName.requestFocus();
            mEditTextFirstName.setError(getString(R.string.three_chars_minimum));
        }

        //for last name (space permitted)
        else if (lastName.matches(".*[^a-zA-Z\\s].*")) {
            mEditTextLastName.requestFocus();
            mEditTextLastName.setError(getString(R.string.last_name_invalid));
        }
        else if (lastName.isEmpty()) {
            mEditTextLastName.requestFocus();
            mEditTextLastName.setError(getString(R.string.this_field_required));
        }
        else if (lastName.length() < 3) {
            mEditTextLastName.requestFocus();
            mEditTextLastName.setError(getString(R.string.three_chars_minimum));
        }
        //for email
        else if (email.isEmpty()) {
            mEditTextEmail.requestFocus();
            mEditTextEmail.setError(getString(R.string.this_field_required));
        }

        else if (!validEmail(email)) {
            mEditTextEmail.requestFocus();
            mEditTextEmail.setError(getString(R.string.email_invalid));
        }
        //for password (any chars permitted)
        else if (password.isEmpty()) {
            mEditTextPassword.requestFocus();
            mEditTextPassword.setError(getString(R.string.this_field_required));
        }
        //for terms and conditions
        else if (!mCheckBoxAcceptTerms.isChecked()) {
            Toast.makeText(this, getString(R.string.you_must_accept_terms), Toast.LENGTH_SHORT).show();
        } else {
            response = true;
        }

        return response;
    }

    private boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private void saveDataSharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(SP_FIRST_NAME, spFirstName);
        editor.putString(SP_LAST_NAME, spLastName);
        editor.putString(SP_EMAIL, spEmail);
        editor.putString(SP_PASSWORD, spPassword);
        editor.putBoolean(SP_TERMS, spTerms);

        editor.apply();
    }

    private void loadDataSharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        spFirstName = sharedPreferences.getString(SP_FIRST_NAME, "");
        spLastName = sharedPreferences.getString(SP_LAST_NAME, "");
        spEmail = sharedPreferences.getString(SP_EMAIL, "");
        spPassword = sharedPreferences.getString(SP_PASSWORD, "");
        spTerms = sharedPreferences.getBoolean(SP_TERMS, false);
    }

    private void clearDataSharefPref() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }

    private void displayDataSharePref() {

        loadDataSharedPref();

        if (spFirstName.equals("")
                &&spLastName.equals("")
                &&spEmail.equals("")
                &&spPassword.equals("")
                &&!spTerms) {
            mTextViewDisplayData.setText(getString(R.string.empty));
        } else {
            String data = "Data from Shared Preferences" +
                    "\n\nFirst name: "  + spFirstName +
                    "\nLast name: " + spLastName +
                    "\nEmail: " + spEmail +
                    "\nPassword: " + spPassword +
                    "\nTerms and Conditions: " + spTerms;
            mTextViewDisplayData.setText(data);
        }
    }

    private void saveDataToFile() {

        fileContents = "Data from Internal File" +
                "\n\nFirst name: "  + spFirstName +
                "\nLast name: " + spLastName +
                "\nEmail: " + spEmail +
                "\nPassword: " + spPassword +
                "\nTerms and Conditions: " + spTerms;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile() {

        File file = getBaseContext().getFileStreamPath(filename);

        if(file.exists())
        {
            String[] loadText = Load(file);
            String finalString = "";

            for (int i = 0; i < loadText.length; i++)
            {
                finalString += loadText[i] + System.getProperty("line.separator");
            }
            mTextViewDisplayData.setText(finalString);
        }
        else
            Toast.makeText(getApplicationContext(), getString(R.string.file_not_found), Toast.LENGTH_SHORT).show();

    }

    //for reading file content
    public static String[] Load(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String test;
        int anzahl = 0;
        try {
            while ((test = br.readLine()) != null) {
                anzahl++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fis.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] array = new String[anzahl];

        String line;
        int i = 0;
        try {
            while ((line = br.readLine()) != null) {
                array[i] = line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

}
