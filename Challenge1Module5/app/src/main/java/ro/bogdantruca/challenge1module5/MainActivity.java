package ro.bogdantruca.challenge1module5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final Random RANDOM = new Random();

    private String mTypeOfCoin;

    private ImageView mImageViewCoin;
    private Button mButtonHead;
    private Button mButtonTail;
    private TextView mTextViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mImageViewCoin = findViewById(R.id.coin);
        mButtonHead = findViewById(R.id.head);
        mButtonTail = findViewById(R.id.tail);
        mTextViewMessage = findViewById(R.id.message_winner_or_loser);
    }

    public void onClickHead(View view) {
        flipCoin("Head");
    }

    public void onClickTail(View view) {
        flipCoin("Tail");
    }

    private void flipCoin(String type) {
        mTypeOfCoin = type;

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fadeIn(mTypeOfCoin);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mImageViewCoin.startAnimation(fadeOut);
    }

    private void fadeIn(String type) {
        float number = RANDOM.nextFloat();
        mImageViewCoin.setImageResource((number > 0.5f) ? R.drawable.tail : R.drawable.head);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(3000);
        fadeIn.setFillAfter(true);

        mImageViewCoin.startAnimation(fadeIn);
        winnerOrLooser(type, number);
    }

    private void winnerOrLooser (String type, float number) {
        if( ( type.equals("Tail") && number > 0.5f ) || ( type.equals("Head") && number <= 0.5f ) ) {
            mTextViewMessage.setText(getString(R.string.message_winner));
            return;
        }

        mTextViewMessage.setText(getString(R.string.messge_loser));
    }
}
