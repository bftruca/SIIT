package ro.bogdantruca.firebaseproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener {

    private static final String ANONYMOUS = "anonymus :D";

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;

    private String mUsername, mPhotoUrl;

    private TextView mTextViewUsername;
    private ImageView mImageViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFirebase();
        initGoogleClient();

        //useSharedPreferences("Bogdan");
    }

    private void useSharedPreferences(String username) {
        ApplicationData.setStringValueInSharedPreferences(MainActivity.this, ApplicationData.USERNAME, username);

        String usernameSharedPreferences = ApplicationData.getStringValueFromSharedPreferences(MainActivity.this, ApplicationData.USERNAME);

        Logging.show(MainActivity.this, usernameSharedPreferences);
        ToastClass.show(MainActivity.this, usernameSharedPreferences);
    }

    private void saveUsername() {
        ApplicationData.setStringValueInSharedPreferences(MainActivity.this, ApplicationData.USERNAME, mUsername);
    }

    private void initView() {
        mTextViewUsername = findViewById(R.id.text_view_username);
        mImageViewUser = findViewById(R.id.image_view_user);
    }

    private void initFirebase() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        if (mFirebaseUser == null) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            mUsername = mFirebaseUser.getDisplayName();
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
            displayUserDetails();
        }

        if (mUsername != null && !mUsername.isEmpty()) {
            saveUsername();
        }
    }

    private void initGoogleClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
    }

    private void displayUserDetails() {
        if (mUsername != null && !mUsername.isEmpty()) {
            mTextViewUsername.setText(mUsername);
        }
        if (mPhotoUrl != null && !mPhotoUrl.isEmpty()) {
            Glide.with(this).load(mPhotoUrl).into(mImageViewUser);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menu:
                mFirebaseAuth.signOut();
                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                mUsername = ANONYMOUS;
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mTextViewUsername.setText("Connection failed");
    }

    public void btnCloudFirestoreOnClick(View view) {
        startActivity(new Intent(MainActivity.this, CloudFirestoreActivity.class));
    }
}
