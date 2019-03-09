package ro.bogdantruca.challenge7module5;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextViewDate;
    private TextView mTextViewLocation;
    private ImageView mImageViewLocationPicture;

    public LocationViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewDate = itemView.findViewById(R.id.text_view_date);
        mTextViewLocation = itemView.findViewById(R.id.text_view_location);
        mImageViewLocationPicture = itemView.findViewById(R.id.image_view_picture);
    }

    public TextView getTextViewDate() {
        return mTextViewDate;
    }

    public void setTextViewDate(TextView textViewDate) {
        mTextViewDate = textViewDate;
    }

    public TextView getTextViewLocation() {
        return mTextViewLocation;
    }

    public void setTextViewLocation(TextView textViewLocation) {
        mTextViewLocation = textViewLocation;
    }

    public ImageView getImageViewLocationPicture() {
        return mImageViewLocationPicture;
    }

    public void setImageViewLocationPicture(ImageView imageViewLocationPicture) {
        mImageViewLocationPicture = imageViewLocationPicture;
    }
}