package ro.bogdantruca.firebaseproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ro.bogdantruca.firebaseproject.Fragments.RecyclerViewFavouritesFragment;
import ro.bogdantruca.firebaseproject.Fragments.RecyclerViewFragment;
import ro.bogdantruca.firebaseproject.Utils.Constants;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener, Constants {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;

    private String mUsername, mPhotoUrl, mEmail;

    private TextView mTextViewUsername;
    private TextView mTextViewEmail;
    private ImageView mImageViewUser;
    private NavigationView navigationView;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddOrEditActivity.class);
                intent.putExtra(ACTION_ID, ACTION_ADD);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
        initFirebase();
        initGoogleClient();

        MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, RecyclerViewFragment.newInstance()).commit();
    }

    private void initView() {
        navigationView =  findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);

        mTextViewUsername = headerView.findViewById(R.id.username);
        mImageViewUser = headerView.findViewById(R.id.imageView);
        mTextViewEmail = headerView.findViewById(R.id.email);
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
            mEmail = mFirebaseUser.getEmail();
            if (mFirebaseUser.getPhotoUrl() != null) {
                mPhotoUrl = mFirebaseUser.getPhotoUrl().toString();
            }
            displayUserDetails();
        }
    }

    private void displayUserDetails() {
        if (mUsername != null && !mUsername.isEmpty()) {
            mTextViewUsername.setText(mUsername);
        }
        if (mEmail != null && !mEmail.isEmpty()) {
            mTextViewEmail.setText(mEmail);
        }
        if (mPhotoUrl != null && !mPhotoUrl.isEmpty()) {
            Glide.with(this).load(mPhotoUrl).into(mImageViewUser);
        }
    }


    private void initGoogleClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if ( drawer.isDrawerOpen(GravityCompat.START) ) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings ) {
            mFirebaseAuth.signOut();
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if ( id == R.id.nav_home ) {
            // Handle the camera action
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, RecyclerViewFragment.newInstance()).commit();
        } else if ( id == R.id.nav_favorite ) {
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, RecyclerViewFavouritesFragment.newInstance()).commit();
        } else if ( id == R.id.nav_about_us ) {

        } else if ( id == R.id.nav_contact ) {

        } else if ( id == R.id.nav_share ) {

        } else if ( id == R.id.nav_send ) {

        } else if (id == R.id.nav_sign_out) {
            mFirebaseAuth.signOut();
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mTextViewUsername.setText("Connection failed");
    }
}
