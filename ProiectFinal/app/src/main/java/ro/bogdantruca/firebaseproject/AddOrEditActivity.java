package ro.bogdantruca.firebaseproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import ro.bogdantruca.firebaseproject.Fragments.RecyclerViewFragment;
import ro.bogdantruca.firebaseproject.Fragments.Trip;
import ro.bogdantruca.firebaseproject.Fragments.TripAdapter;
import ro.bogdantruca.firebaseproject.Utils.Constants;
import ro.bogdantruca.firebaseproject.Utils.CustomDatePickerFragment;

public class AddOrEditActivity extends AppCompatActivity implements Constants, DatePickerDialog.OnDateSetListener {
    byte[] dataByteImage;
    private Bitmap mBitmapImage;

    private CustomDatePickerFragment mDialogFragmentDatePicker;

    private Button mButtonGallery;
    private Button mButtonSave;
    private Button mButtonCamera;
    private Button mButtonStartDate;
    private Button mButtonEndDate;

    private RadioButton mRadioButtonCityBreak;
    private RadioButton mRadioButtonSeaSide;
    private RadioButton mRadioButtonMountains;

    private SeekBar mSeekBarPrice;
    private TextView mTextViewPrice;

    private RatingBar mRatingBarRating;

    private EditText mEditTextTripName;
    private EditText mEditTextDestination;

    private FirebaseStorage mStorage;
    FirebaseFirestore db;

    private String mUsernameMail;
    private String mCurrentPhotoPath;

    Map<String, Object> mUserDatas;

    String tripID;
    String action;

    private Trip auxTrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit);

        initFirebase();
        initView();
        setPrice();

        checkEditAction();
    }

    private void initFirebase() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        mStorage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();

        if ( firebaseUser == null ) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
        } else {
            mUsernameMail = firebaseUser.getEmail();
        }
    }

    private void initView() {
        mButtonGallery = findViewById(R.id.button_gallery);
        mButtonSave = findViewById(R.id.button_save);
        mButtonCamera = findViewById(R.id.button_camera);

        mButtonStartDate = findViewById(R.id.start_date_button);
        mButtonEndDate = findViewById(R.id.end_date_button);

        mSeekBarPrice = findViewById(R.id.seek_bar_price);
        mTextViewPrice = findViewById(R.id.text_view_price);

        mEditTextDestination = findViewById(R.id.edit_text_destinations);
        mEditTextTripName = findViewById(R.id.edit_text_trip_name);

        mDialogFragmentDatePicker = new CustomDatePickerFragment();

        mRadioButtonCityBreak = findViewById(R.id.radio_button_city_break);
        mRadioButtonSeaSide = findViewById(R.id.radio_button_sea_side);
        mRadioButtonMountains = findViewById(R.id.radio_button_mountains);

        mRatingBarRating = findViewById(R.id.rating_bar_rating);
    }

    private void setPrice() {
        mSeekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTextViewPrice.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void checkEditAction() {
        Intent intent = getIntent();
        action = intent.getStringExtra(ACTION_ID);

        if (action != null && !action.isEmpty()) {
                    if (action.equals(ACTION_EDIT)) {
                tripID = intent.getStringExtra(TRIP_KEY);
                if (tripID != null && !tripID.isEmpty()) {
                    putDataInView(tripID);
                }
            }
        }
    }

    private void putDataInView(String tripID) {
        DocumentReference docRef = db.collection(mUsernameMail).document(tripID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                mEditTextTripName.setText(documentSnapshot.getString(DB_TRIP_NAME));
                mEditTextDestination.setText(documentSnapshot.getString(DB_DESTINATION));

                if(documentSnapshot.getString(DB_TRIP_TYPE).equals(CITY_BREAK)) {
                    mRadioButtonCityBreak.setChecked(true);
                }
                else if(documentSnapshot.getString(DB_TRIP_TYPE).equals(SEA_SIDE)) {
                    mRadioButtonSeaSide.setChecked(true);
                }
                else if(documentSnapshot.getString(DB_TRIP_TYPE).equals(MOUNTAINS)) {
                    mRadioButtonMountains.setChecked(true);
                }

                mButtonStartDate.setText(documentSnapshot.getString(DB_START_DATE));
                mButtonEndDate.setText(documentSnapshot.getString(DB_END_DATE));
                mSeekBarPrice.setProgress(Integer.parseInt(documentSnapshot.getString(DB_PRICE_EURO)));
                mTextViewPrice.setText(documentSnapshot.getString(DB_PRICE_EURO));
                mRatingBarRating.setRating(documentSnapshot.get(DB_RATING , float.class));

                auxTrip = new Trip(documentSnapshot.getId()
                        , documentSnapshot.getString(DB_TRIP_NAME)
                        , documentSnapshot.getString(DB_DESTINATION)
                        , documentSnapshot.getString(DB_PRICE_EURO)
                        , documentSnapshot.getString(DB_START_DATE)
                        , documentSnapshot.getString(DB_END_DATE)
                        , documentSnapshot.getString(DB_TRIP_TYPE)
                        , documentSnapshot.getString(DB_URL_IMAGE)
                        , documentSnapshot.getString(DB_FILE_REFERENCE)
                        , documentSnapshot.getDouble(DB_RATING)
                        , documentSnapshot.get(DB_FAVORITE , boolean.class));

                setDateForChecking(documentSnapshot.getString(DB_END_DATE), END_DATE);
                setDateForChecking(documentSnapshot.getString(DB_START_DATE), START_DATE);
            }
        });


        //setDateForChecking(auxTrip.getStartDate(), START_DATE);
    }

    private void setDateForChecking(String str, int flag) {
        String[] a = str.split("/");
        mDialogFragmentDatePicker.setFlag(flag);
        mDialogFragmentDatePicker.setDate(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]));
        //Toast.makeText(this, a[0] + " " + a[1] + " " + a[2], Toast.LENGTH_SHORT).show();
    }

    public void onClickButtonGallery(View view) {
        if ( ContextCompat.checkSelfPermission(AddOrEditActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED ) {
            //open gallery
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, GALLERY_CODE);
        } else {
            //open specific fragment to ask for permission
            ActivityCompat.requestPermissions(AddOrEditActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, GALLERY_CODE);
        }
    }

    public void onClickButtonCamera(View view) {
        if ( ContextCompat.checkSelfPermission(AddOrEditActivity.this,
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED ) {
            //open camera
            dispatchTakePictureIntent();

        } else {
            //open specific fragment to ask for permission
            ActivityCompat.requestPermissions(AddOrEditActivity.this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);
        }
    }

    public void onClickStartDate(View view) {
        mDialogFragmentDatePicker.setFlag(START_DATE);
        mDialogFragmentDatePicker.show(getSupportFragmentManager(), "DatePicker");
    }

    public void onClickEndDate(View view) {
        mDialogFragmentDatePicker.setFlag(END_DATE);
        mDialogFragmentDatePicker.show(getSupportFragmentManager(), "DatePicker");
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if ( takePictureIntent.resolveActivity(getPackageManager()) != null ) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e("TAG:", "Error occurred while creating the File: " + ex.getMessage());
            }
            // Continue only if the File was successfully created
            if ( photoFile != null ) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public void onClickButtonSave(View view) {
        String tripName = mEditTextTripName.getText().toString().trim();

        if (tripName.isEmpty()) {
            mEditTextTripName.requestFocus();
            mEditTextTripName.setError("Trip name is missing");
            return;
        }

        String destination = mEditTextDestination.getText().toString().trim();

        if (destination.isEmpty()) {
            mEditTextDestination.requestFocus();
            mEditTextDestination.setError("Destination is missing");
            return;
        }

        if (!mRadioButtonCityBreak.isChecked() && !mRadioButtonSeaSide.isChecked() && !mRadioButtonMountains.isChecked()) {
            Toast.makeText(AddOrEditActivity.this, "Trip type is missing", Toast.LENGTH_SHORT).show();
            return;
        }

        String price = mTextViewPrice.getText().toString().trim();

        if (price.equals("0")) {
            Toast.makeText(AddOrEditActivity.this, "Price is missing", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!mDialogFragmentDatePicker.checkDate()) {
            Toast.makeText(AddOrEditActivity.this, "Error Date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mRatingBarRating.getRating() <= 0)
        {
            Toast.makeText(AddOrEditActivity.this, "Add Rating", Toast.LENGTH_SHORT).show();
            return;
        }

        if (action != null && !action.isEmpty()) {
            if ( action.equals(ACTION_ADD) ) {
                if ( mBitmapImage == null) {
                    Toast.makeText(AddOrEditActivity.this, "Select an image", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

        saveToFirestore();
        finish();
    }

    private void saveToFirestore() {
        //initialize object
        mUserDatas = new HashMap<>();

        mUserDatas.put(DB_TRIP_NAME, mEditTextTripName.getText().toString().trim());
        mUserDatas.put(DB_DESTINATION, mEditTextDestination.getText().toString().trim());
        mUserDatas.put(DB_PRICE_EURO, mTextViewPrice.getText().toString().trim());
        mUserDatas.put(DB_START_DATE, mButtonStartDate.getText().toString().trim());
        mUserDatas.put(DB_END_DATE, mButtonEndDate.getText().toString().trim());
        mUserDatas.put(DB_FAVORITE, false);

        if (mRadioButtonCityBreak.isChecked()) {
            mUserDatas.put(DB_TRIP_TYPE, CITY_BREAK);
        } else if (mRadioButtonSeaSide.isChecked()) {
            mUserDatas.put(DB_TRIP_TYPE, SEA_SIDE);
        } else if (mRadioButtonMountains.isChecked()) {
            mUserDatas.put(DB_TRIP_TYPE, MOUNTAINS);
        }

        mUserDatas.put(DB_RATING, mRatingBarRating.getRating());

        if ( mBitmapImage == null) {
            if (action != null && !action.isEmpty()) {
                if (action.equals(ACTION_EDIT)) {
                    mUserDatas.put(DB_URL_IMAGE, auxTrip.getImageURL());
                    mUserDatas.put(DB_FILE_REFERENCE, auxTrip.getFileReference());

                    addToDatabase();
                }
            }
        } else if ( mBitmapImage != null ) {
            uploadImage();
        }
    }

    private void uploadImage() {

        if ( mBitmapImage != null ) {
            //scaling the gallery image
            customScaleImage();

            //convert my image to jpeg
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            mBitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            dataByteImage = baos.toByteArray();

            //for upload file (storage reference)
            String path = mUsernameMail + "/" + UUID.randomUUID() + ".jpeg";
            final StorageReference storageReference = mStorage.getReference(path);

            //uploading
            //dataByte = my image
            UploadTask uploadTask = storageReference.putBytes(dataByteImage);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl();
                    firebaseUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            if (action != null && !action.isEmpty()) {
                                if (action.equals(ACTION_EDIT)) {
                                    deleteOldPhoto();
                                }
                            }

                            mUserDatas.put(DB_URL_IMAGE, uri.toString());
                            mUserDatas.put(DB_FILE_REFERENCE, storageReference.getName());

                            addToDatabase();
                        }
                    });
                }
            });
        }
    }

    private void deleteOldPhoto() {
        StorageReference photoRef = mStorage.getReferenceFromUrl(auxTrip.getImageURL());

        photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // File deleted successfully
                Log.d("TAG", "onSuccess: deleted file");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Uh-oh, an error occurred!
                Log.d("TAG", "onFailure: did not delete file");
            }
        });
    }

    private void customScaleImage() {
        final double OPTIMUM_MAX_SIZE = 900.0;

        if ( mBitmapImage != null ) {
            int myDstWidth = mBitmapImage.getWidth();
            int myDstHeight = mBitmapImage.getHeight();
            double temp;

            int width = mBitmapImage.getWidth();
            int height = mBitmapImage.getHeight();

            if ( height > width && height > OPTIMUM_MAX_SIZE ) {
                myDstHeight = (int) OPTIMUM_MAX_SIZE;

                temp = height / OPTIMUM_MAX_SIZE;
                double calc = width / temp;
                width = (int) calc;
                myDstWidth = width;

            } else if ( width > height && width > OPTIMUM_MAX_SIZE ) {

                myDstWidth = (int) OPTIMUM_MAX_SIZE;

                temp = width / OPTIMUM_MAX_SIZE;
                double calc = height / temp;
                height = (int) calc;
                myDstHeight = height;
            }

            mBitmapImage = Bitmap.createScaledBitmap(mBitmapImage, myDstWidth, myDstHeight, true);
        }
    }

    private void addToDatabase() {
        if(action.equals(ACTION_ADD)) {
            db.collection(mUsernameMail)
                    .add(mUserDatas)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddOrEditActivity.this, "Trip stored successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddOrEditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else if(action.equals(ACTION_EDIT)) {
            DocumentReference docRef = db.collection(mUsernameMail).document(tripID);
            docRef.update(mUserDatas);
        }
    }

    public static int getImageOrientation(String imagePath) {
        int rotate = 0;
        try {
            ExifInterface exif = new ExifInterface(imagePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rotate;
    }

    private Bitmap checkRotationFromCamera(Bitmap bitmap, int rotate) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return rotatedBitmap;
    }

    private void setPictureFromCamera() {
        /* Take the picture from gallery */
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);

        /* Get the dimensions of the bitmap */
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath);

        /* Decode the image file into a Bitmap sized to fill the View */
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inPurgeable = true;

        if ( mBitmapImage != null )
            mBitmapImage = null;

        final int rotation = getImageOrientation(mCurrentPhotoPath);
        mBitmapImage = BitmapFactory.decodeFile(mCurrentPhotoPath);
        mBitmapImage = checkRotationFromCamera(mBitmapImage, rotation);
    }

    public String getImagePath(Uri uri){
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == RESULT_CANCELED )
            return;

        if ( requestCode == REQUEST_TAKE_PHOTO ) {
            setPictureFromCamera();
        }

        if ( requestCode == GALLERY_CODE ) {
            if ( data != null ) {
                Uri contentURI = data.getData();

                mCurrentPhotoPath = getImagePath(contentURI);

                try {
                    //image selected is added to mBitmapGallery

                    if ( mBitmapImage != null )
                        mBitmapImage = null;

                    final int rotation = getImageOrientation(mCurrentPhotoPath);
                    mBitmapImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                            contentURI);
                    mBitmapImage = checkRotationFromCamera(mBitmapImage, rotation);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddOrEditActivity.this,
                            getString(R.string.failed_to_load_image) + " " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Toast.makeText(AddOrEditActivity.this, "The selected date is " + view.getYear() +
                " / " + (view.getMonth() + 1) +
                " / " + view.getDayOfMonth(), Toast.LENGTH_SHORT).show();

        mDialogFragmentDatePicker.setDate(dayOfMonth, month, year);

        String currentDate = dayOfMonth + "/" + month + "/" + year;
        switch(mDialogFragmentDatePicker.getFlag()) {
            case START_DATE:
                mButtonStartDate.setText(currentDate);
                break;
            case END_DATE:
                mButtonEndDate.setText(currentDate);
                break;
        }
    }
}