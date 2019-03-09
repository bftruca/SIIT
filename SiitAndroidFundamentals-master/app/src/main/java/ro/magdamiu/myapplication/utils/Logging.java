package ro.magdamiu.myapplication.utils;

import android.util.Log;

import ro.magdamiu.myapplication.BuildConfig;

public class Logging {
  public static void show(Object obj, String message) {
      if (BuildConfig.DEBUG) {
          Log.e(obj.getClass().getName(), message);
      }
  }
}
