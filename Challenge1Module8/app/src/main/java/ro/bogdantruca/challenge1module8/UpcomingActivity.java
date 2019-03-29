package ro.bogdantruca.challenge1module8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpcomingActivity extends AppCompatActivity {

    private Retrofit mRetrofit;
    private TheMovieDatabaseApi mTheMovieDatabaseApi;
    private TextView mTextViewDisplayUpcoming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        initView();
        getUpcoming();
    }

    private void initView() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mTextViewDisplayUpcoming = findViewById(R.id.text_view_display);
    }

    private void getUpcoming() {
        mTextViewDisplayUpcoming.setText("");

        mTheMovieDatabaseApi = mRetrofit.create(TheMovieDatabaseApi.class);

        Call<MovieModelList> call =
                mTheMovieDatabaseApi.getUpcoming(MainActivity.API_KEY,
                        MainActivity.LANGUAGE, MainActivity.PAGE);
        call.enqueue(new Callback<MovieModelList>() {
            @Override
            public void onResponse(Call<MovieModelList> call, Response<MovieModelList> response) {
                if (!response.isSuccessful()) {
                    mTextViewDisplayUpcoming.setText("Code: " + response.code());
                    return;
                }

                MovieModelList movieModelListTopRated = response.body();

                for (MovieModel movieModel : movieModelListTopRated.getMovieModelList()) {
                    String content = "";
                    content += movieModel.getTitle() + "\n";
                    mTextViewDisplayUpcoming.append(content);
                }
            }

            @Override
            public void onFailure(Call<MovieModelList> call, Throwable t) {
                mTextViewDisplayUpcoming.setText(t.getMessage());
            }
        });
    }
}
