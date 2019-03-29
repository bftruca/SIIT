package ro.bogdantruca.module7.module8;

import android.util.Log;

import ro.bogdantruca.module7.BuildConfig;

public class Logging {
  public static void show(Object obj, String message) {
      if ( BuildConfig.DEBUG) {
          Log.e(obj.getClass().getName(), message);
      }
  }
}