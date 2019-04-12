package ro.bogdantruca.firebaseproject.Fragments;

public class Trip {
    private String mId;
    private String mTripName;
    private String mDestionation;
    private String mPrice;
    private String mStartDate;
    private String mEndDate;
    private String mTripType;
    private String mImageURL;
    private String mFileReference;
    private double mRating;
    private boolean mFavorite;

    public Trip(String id, String tripName, String destionation, String price, String startDate,
                String endDate, String tripType, String imageURL, String fileReference,
                double rating, boolean favorite) {
        mId = id;
        mTripName = tripName;
        mDestionation = destionation;
        mPrice = price;
        mStartDate = startDate;
        mEndDate = endDate;
        mTripType = tripType;
        mImageURL = imageURL;
        mFileReference = fileReference;
        mRating = rating;
        mFavorite = favorite;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTripName() {
        return mTripName;
    }

    public void setTripName(String tripName) {
        mTripName = tripName;
    }

    public String getDestionation() {
        return mDestionation;
    }

    public void setDestionation(String destionation) {
        mDestionation = destionation;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public String getTripType() {
        return mTripType;
    }

    public void setTripType(String tripType) {
        mTripType = tripType;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String imageURL) {
        mImageURL = imageURL;
    }

    public String getFileReference() {
        return mFileReference;
    }

    public void setFileReference(String fileReference) {
        mFileReference = fileReference;
    }

    public double getRating() {
        return mRating;
    }

    public void setRating(double rating) {
        mRating = rating;
    }

    public boolean isFavorite() {
        return mFavorite;
    }

    public void setFavorite(boolean favorite) {
        mFavorite = favorite;
    }
}