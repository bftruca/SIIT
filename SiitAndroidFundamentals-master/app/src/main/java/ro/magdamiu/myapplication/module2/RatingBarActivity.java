package ro.magdamiu.myapplication.module2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import ro.magdamiu.myapplication.R;

public class RatingBarActivity extends AppCompatActivity {

    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);

        initView();
    }

    private void initView() {
        mRatingBar = findViewById(R.id.rating_bar_basic);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(RatingBarActivity.this,
                        getString(R.string.current_rating) + " " + ratingBar.getRating(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
