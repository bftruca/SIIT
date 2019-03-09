package ro.magdamiu.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ro.magdamiu.myapplication.module2.RatingBarActivity;
import ro.magdamiu.myapplication.module5.Activity1;
import ro.magdamiu.myapplication.module5.Activity2;
import ro.magdamiu.myapplication.module5.SaveInstanceStateActivity;
import ro.magdamiu.myapplication.module6.DynamicFragmentActivity;
import ro.magdamiu.myapplication.module6.FragmentActivity;

public class AndroidFundamentalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_fundamentals);
    }

    public void ratingBarOnClick(View view) {
        startActivity(new Intent(AndroidFundamentalsActivity.this, RatingBarActivity.class));
    }

    public void activitiesOnClick(View view) {
        startActivity(new Intent(AndroidFundamentalsActivity.this, Activity1.class));
    }

    public void saveInstanceActivityOnClick(View view) {
        startActivity(new Intent(AndroidFundamentalsActivity.this, SaveInstanceStateActivity.class));
    }

    public void staticFragmentOnClick(View view) {
        startActivity(new Intent(AndroidFundamentalsActivity.this, DynamicFragmentActivity.class));
    }
}
