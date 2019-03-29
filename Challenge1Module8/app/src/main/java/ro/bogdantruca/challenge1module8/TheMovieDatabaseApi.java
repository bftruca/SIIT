package ro.bogdantruca.challenge1module8;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDatabaseApi {

    @GET("top_rated")
    Call<MovieModelList> getTopRated(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") Integer page);

    @GET("upcoming")
    Call<MovieModelList> getUpcoming(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") Integer page);

    @GET("now_playing")
    Call<MovieModelList> getNowPlaying(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") Integer page);
}