package ro.bogdantruca.firebaseproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import ro.bogdantruca.firebaseproject.Fragments.RecyclerViewFragment;
import ro.bogdantruca.firebaseproject.Utils.Constants;

public class TripDetailsActivity extends AppCompatActivity implements Constants {

    FirebaseFirestore db;

    private SeekBar mSeekBarPrice;
    private EditText mEditTextDestination;
    private EditText mEditTextTripName;
    private TextView mTextViewPrice;
    private Button mButtonStartDate;
    private Button mButtonEndDate;
    private RatingBar mRatingBar;
    private ImageView mImageView;
    private TextView mTextViewType;

    private String mUsernameMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        initView();

        setUpFirebase();

        putDataInView();
    }

    private void putDataInView() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(RecyclerViewFragment.TRIP_KEY);
        if (message != null && !message.isEmpty()) {
            DocumentReference docRef = db.collection(mUsernameMail).document(message);
            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    mEditTextTripName.setText(documentSnapshot.getString(DB_TRIP_NAME));
                    mEditTextDestination.setText(documentSnapshot.getString(DB_DESTINATION));
                    Picasso.get().load(documentSnapshot.getString(DB_URL_IMAGE)).into(mImageView);
                    mTextViewType.setText(documentSnapshot.getString(DB_TRIP_TYPE));


                    mButtonStartDate.setText(documentSnapshot.getString(DB_START_DATE));
                    mButtonEndDate.setText(documentSnapshot.getString(DB_END_DATE));
                    mSeekBarPrice.setProgress(Integer.parseInt(documentSnapshot.getString(DB_PRICE_EURO)));
                    mTextViewPrice.setText(documentSnapshot.getString(DB_PRICE_EURO));
                    mRatingBar.setRating(documentSnapshot.get(DB_RATING , float.class));
                }
            });
        }
    }

    private void setUpFirebase() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        db = FirebaseFirestore.getInstance();

        if ( firebaseUser != null ) {
            mUsernameMail = firebaseUser.getEmail();
        }
    }

    private void initView() {
        mSeekBarPrice = findViewById(R.id.seekbar_readonly);
        mTextViewPrice = findViewById(R.id.textview_price);
        mButtonEndDate = findViewById(R.id.button_enddate_readonly);
        mButtonStartDate = findViewById(R.id.button_startdate_readonly);
        mRatingBar = findViewById(R.id.ratingbar_readonly);
        mEditTextDestination = findViewById(R.id.edittext_destination_readonly);
        mEditTextTripName = findViewById(R.id.edittext_tripname_readonly);
        mImageView = findViewById(R.id.imageView_readonly);
        mTextViewType = findViewById(R.id.textview_triptypeshow);
        mRatingBar = findViewById(R.id.ratingbar_readonly);

        mSeekBarPrice.setEnabled(false);
        mEditTextTripName.setEnabled(false);
        mEditTextDestination.setEnabled(false);
        mButtonStartDate.setEnabled(false);
        mButtonEndDate.setEnabled(false);
    }
}
