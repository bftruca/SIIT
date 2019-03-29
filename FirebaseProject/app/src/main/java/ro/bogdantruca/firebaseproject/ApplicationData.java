package ro.bogdantruca.firebaseproject;

import android.content.Context;
import android.content.SharedPreferences;

public class ApplicationData {

    private static final String APP_KEY = "android_course_key";

    public static final String USERNAME = "username";

    // save a String value by key
    public static void setStringValueInSharedPreferences(Context ctx, String key, String value) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ApplicationData.APP_KEY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    // get a String value by key
    public static String getStringValueFromSharedPreferences(Context ctx, String key) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(ApplicationData.APP_KEY,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
}
