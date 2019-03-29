package ro.bogdantruca.challenge9module6;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ro.bogdantruca.challenge9module6.Fragments.Fragment1;
import ro.bogdantruca.challenge9module6.Fragments.Fragment2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container_layout_fragments, fragment);
        fragmentTransaction.commit();
    }

    public void onClickFragment1(View view) {
        Fragment1 fragment = new Fragment1();
        addFragment(fragment);
    }

    public void onClickFragment2(View view) {
        Fragment2 fragment = new Fragment2();
        addFragment(fragment);
    }
}
