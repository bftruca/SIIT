package ro.bogdantruca.challenge6module7;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private FrameLayout mFrameLayout;
    private ShowCamera mShowCamera;
    private ImageView mImageView;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFrameLayout = findViewById(R.id.frameLayout);
        mImageView = findViewById(R.id.image_camera);

        if (!checkPermission()) {
            requestPermission();
        } else {
            openAndDisplay();
        }
    }

    private void openAndDisplay() {
        mCamera = Camera.open();
        mShowCamera = new ShowCamera(this, mCamera);
        mFrameLayout.addView(mShowCamera);
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                MY_PERMISSIONS_REQUEST_CAMERA);

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAndDisplay();
                }
            }
        }
    }


    public void onClickCamera(View view) {
        mCamera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Bitmap b = BitmapFactory.decodeByteArray(data, 0, data.length);
                mImageView.setImageBitmap(Bitmap.createScaledBitmap(rotate(b,90), 1024, 1280, false));

                mCamera.stopPreview();
                mImageView.setVisibility(View.VISIBLE);
                mFrameLayout.setVisibility(View.INVISIBLE);

            }
        });
    }

    public static Bitmap rotate(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public void onClickReset(View view) {
        mCamera.startPreview();
        mImageView.setVisibility(View.INVISIBLE);
        mFrameLayout.setVisibility(View.VISIBLE);
    }
}
