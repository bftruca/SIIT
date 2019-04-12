package ro.bogdantruca.firebaseproject.Fragments;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import ro.bogdantruca.firebaseproject.AddOrEditActivity;
import ro.bogdantruca.firebaseproject.R;
import ro.bogdantruca.firebaseproject.TripDetailsActivity;
import ro.bogdantruca.firebaseproject.Utils.Constants;

public class TripAdapter extends RecyclerView.Adapter<TripsViewHolder> implements Constants {
    private List<Trip> mTrips;
    private FirebaseFirestore db;
    private String mUsernameMail;

    TripAdapter(List<Trip> mTrips) {
        this.mTrips = mTrips;
    }

    private void setUpFireBase() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        db = FirebaseFirestore.getInstance();

        if ( firebaseUser != null ) {
            mUsernameMail = firebaseUser.getEmail();
        }
    }

    @NonNull
    @Override
    public TripsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View tripView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_item, viewGroup, false);

        setUpFireBase();

        return new TripsViewHolder(tripView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TripsViewHolder tripsViewHolder, final int i) {
        Trip trip = mTrips.get(i);
        tripsViewHolder.mTextViewSeason.setText(trip.getTripName());
        tripsViewHolder.mTextViewLocation.setText(trip.getDestionation());
        tripsViewHolder.mTextViewPriceAndRating.setText(trip.getPrice() + " / " + trip.getRating());
        if(trip.isFavorite())
            tripsViewHolder.mImageButtonFavourite.setBackgroundResource(R.drawable.ic_bookmark_black_24dp);
        else
            tripsViewHolder.mImageButtonFavourite.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
        Picasso.get().load(trip.getImageURL()).into(tripsViewHolder.mImageView);

        tripsViewHolder.mImageButtonFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Icon position: " + i, Toast.LENGTH_SHORT).show();

                boolean value;
                if(mTrips.get(i).isFavorite()) {
                    value = false;
                } else {
                    value = true;
                }

                mTrips.get(i).setFavorite(value);

                db.collection(mUsernameMail).document(mTrips.get(i).getId()).update(
                        DB_FAVORITE, value
                );

                notifyDataSetChanged();
            }
        });

        tripsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TripDetailsActivity.class);

                //send user id via extras
                intent.putExtra(TRIP_KEY , mTrips.get(i).getId());
                v.getContext().startActivity(intent);
            }
        });

        tripsViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(v.getContext(), "Long clicked " + i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), AddOrEditActivity.class);
                intent.putExtra(ACTION_ID, ACTION_EDIT);
                intent.putExtra(TRIP_KEY , mTrips.get(i).getId());

                v.getContext().startActivity(intent);

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }
}
