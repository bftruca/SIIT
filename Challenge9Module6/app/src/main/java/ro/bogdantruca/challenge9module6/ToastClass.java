package ro.bogdantruca.challenge9module6;

import android.app.Activity;
import android.widget.Toast;

public class ToastClass {
    public static void show (Activity yourActivity, String message) {
        Toast.makeText(yourActivity, message, Toast.LENGTH_SHORT).show();
    }
}