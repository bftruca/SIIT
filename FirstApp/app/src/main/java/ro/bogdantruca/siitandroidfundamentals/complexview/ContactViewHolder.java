package ro.bogdantruca.siitandroidfundamentals.complexview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ro.bogdantruca.siitandroidfundamentals.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextViewFullName;
    private TextView mTextViewAddress;
    private TextView mTextViewGroup;
    private ImageView mImageViewProfilePicture;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewFullName = itemView.findViewById(R.id.text_view_full_name);
        mTextViewAddress = itemView.findViewById(R.id.text_view_address);
        mTextViewGroup = itemView.findViewById(R.id.text_view_group);
        mImageViewProfilePicture = itemView.findViewById(R.id.image_view_profile_picture_contact);
    }

    public TextView getTextViewFullName() {
        return mTextViewFullName;
    }

    public void setTextViewFullName(TextView textViewFullName) {
        mTextViewFullName = textViewFullName;
    }

    public TextView getTextViewAddress() {
        return mTextViewAddress;
    }

    public void setTextViewAddress(TextView textViewAddress) {
        mTextViewAddress = textViewAddress;
    }

    public TextView getTextViewGroup() {
        return mTextViewGroup;
    }

    public void setTextViewGroup(TextView textViewGroup) {
        mTextViewGroup = textViewGroup;
    }

    public ImageView getImageViewProfilePicture() {
        return mImageViewProfilePicture;
    }

    public void setImageViewProfilePicture(ImageView imageViewProfilePicture) {
        mImageViewProfilePicture = imageViewProfilePicture;
    }
}
