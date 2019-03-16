package ro.bogdantruca.challenge6module6;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Slide;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ro.bogdantruca.challenge6module6.Fragments.GalleryFragment;
import ro.bogdantruca.challenge6module6.Fragments.ImportFragment;
import ro.bogdantruca.challenge6module6.Fragments.SendFragment;
import ro.bogdantruca.challenge6module6.Fragments.ShareFragment;
import ro.bogdantruca.challenge6module6.Fragments.SlideshowFragment;
import ro.bogdantruca.challenge6module6.Fragments.ToolsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container_layout_fragments, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            ToastClass.show(MainActivity.this, "Import");
            ImportFragment importFragment = new ImportFragment();
            addFragment(importFragment);
        } else if (id == R.id.nav_gallery) {
            ToastClass.show(MainActivity.this, "Gallery");
            GalleryFragment galleryFragment = new GalleryFragment();
            addFragment(galleryFragment);
        } else if (id == R.id.nav_slideshow) {
            ToastClass.show(MainActivity.this, "Slideshow");
            SlideshowFragment slideshowFragment = new SlideshowFragment();
            addFragment(slideshowFragment);
        } else if (id == R.id.nav_manage) {
            ToastClass.show(MainActivity.this, "Tools");
            ToolsFragment toolsFragment = new ToolsFragment();
            addFragment(toolsFragment);
        } else if (id == R.id.nav_share) {
            ToastClass.show(MainActivity.this, "Share");
            ShareFragment shareFragment = new ShareFragment();
            addFragment(shareFragment);
        } else if (id == R.id.nav_send) {
            ToastClass.show(MainActivity.this, "Send");
            SendFragment sendFragment = new SendFragment();
            addFragment(sendFragment);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
