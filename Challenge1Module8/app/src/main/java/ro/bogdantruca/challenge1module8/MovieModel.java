package ro.bogdantruca.challenge1module8;

import com.google.gson.annotations.SerializedName;

public class MovieModel {

    @SerializedName("title")
    private String mTitle;

    public String getTitle() {
        return mTitle;
    }
}