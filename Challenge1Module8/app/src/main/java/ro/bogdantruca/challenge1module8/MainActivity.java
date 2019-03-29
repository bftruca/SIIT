package ro.bogdantruca.challenge1module8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "5e145d7e312482968e35b63033cbbc94";
    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String LANGUAGE = "en-US";
    public static int PAGE = 1;

    private Button mButtonTopRatedMovies;
    private Button mButtonUpcomingMovies;
    private Button mButtonNowPlayingMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mButtonTopRatedMovies = findViewById(R.id.button_top_rated_movies);
        mButtonUpcomingMovies = findViewById(R.id.button_upcoming_movies);
        mButtonNowPlayingMovies = findViewById(R.id.button_now_playing_movies);
    }

    public void onClickButtonTopRatedMovies(View view) {
        Intent intent = new Intent(MainActivity.this, TopRatedActivity.class);
        startActivity(intent);
    }

    public void onClickButtonUpcomingMovies(View view) {
        Intent intent = new Intent(MainActivity.this, UpcomingActivity.class);
        startActivity(intent);
    }

    public void onClickButtonNowPlayingMovies(View view) {
        Intent intent = new Intent(MainActivity.this, NowPlayingActivity.class);
        startActivity(intent);
    }
}
