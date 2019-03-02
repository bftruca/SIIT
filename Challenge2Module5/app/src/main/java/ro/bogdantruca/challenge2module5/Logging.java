package ro.bogdantruca.challenge2module5;

import android.util.Log;

import ro.bogdantruca.challenge2module5.BuildConfig;

public class Logging {
    public static  void show(Object obj, String message) {
        if (BuildConfig.DEBUG){
            Log.e(obj.getClass().getName(), message);
        }
    }
}
