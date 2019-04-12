package ro.bogdantruca.firebaseproject.Fragments;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import ro.bogdantruca.firebaseproject.R;

public class TripsViewHolder extends RecyclerView.ViewHolder {
    public ImageView mImageView;
    public TextView mTextViewSeason;
    public TextView mTextViewLocation;
    public TextView mTextViewPriceAndRating;
    public ImageButton mImageButtonFavourite;

    public TripsViewHolder(@NonNull final View itemView) {
        super(itemView);

        mImageView = itemView.findViewById(R.id.imageview_trip);
        mTextViewSeason = itemView.findViewById(R.id.textview_season);
        mTextViewLocation = itemView.findViewById(R.id.textview_location);
        mTextViewPriceAndRating = itemView.findViewById(R.id.textview_price_rating);
        mImageButtonFavourite = itemView.findViewById(R.id.button_favourite);
    }
}