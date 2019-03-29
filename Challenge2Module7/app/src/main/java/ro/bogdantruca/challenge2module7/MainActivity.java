package ro.bogdantruca.challenge2module7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static ro.bogdantruca.challenge2module7.R.drawable.level1;
import static ro.bogdantruca.challenge2module7.R.drawable.level2;
import static ro.bogdantruca.challenge2module7.R.drawable.level3;
import static ro.bogdantruca.challenge2module7.R.drawable.level4;
import static ro.bogdantruca.challenge2module7.R.drawable.level5;
import static ro.bogdantruca.challenge2module7.R.drawable.level6;
import static ro.bogdantruca.challenge2module7.R.drawable.level7;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageViewBattery;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mImageViewBattery = findViewById(R.id.imageView);
        level = 1;
    }

    public void onClickMinus(View view) {
        level--;
        switchImages();
    }

    public void onClickPlus(View view) {
        level++;
        switchImages();
    }

    private void switchImages() {
        switch (level) {
            case 0:
                level = 1;
                break;
            case 1:
                mImageViewBattery.setImageResource(level1);
                break;
            case 2:
                mImageViewBattery.setImageResource(level2);
                break;
            case 3:
                mImageViewBattery.setImageResource(level3);
                break;
            case 4:
                mImageViewBattery.setImageResource(level4);
                break;
            case 5:
                mImageViewBattery.setImageResource(level5);
                break;
            case 6:
                mImageViewBattery.setImageResource(level6);
                break;
            case 7:
                mImageViewBattery.setImageResource(level7);
                break;
            case 8:
                level = 7;
                break;
        }
    }
}
