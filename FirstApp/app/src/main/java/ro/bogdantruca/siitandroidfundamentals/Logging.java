package ro.bogdantruca.siitandroidfundamentals;

import android.util.Log;

import ro.bogdantruca.siitandroidfundamentals.BuildConfig;

public class Logging {
    public static  void show(Object obj, String message) {
        if (BuildConfig.DEBUG){
            Log.e(obj.getClass().getName(), message);
        }
    }

}
