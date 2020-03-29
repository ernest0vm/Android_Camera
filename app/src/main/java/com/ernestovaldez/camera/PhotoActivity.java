package com.ernestovaldez.camera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST_CODE = 1;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 0;
    @BindView(R.id.imageView)
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //we are ok to open the camera
                openCamera();
            }else {
                //the permission was not granted
                Toast.makeText(this, R.string.camera_permission_message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == CAMERA_REQUEST_CODE){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageview.setImageBitmap(image);
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                Toast.makeText(this, R.string.take_picture_canceled, Toast.LENGTH_LONG).show();
            }
        }
    }

    @OnClick(R.id.btnTakePhoto)
    public void onBtnTakePhotoClicked() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            openCamera();

        } else {
            String[] permissionRequest = {Manifest.permission.CAMERA};

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissionRequest, CAMERA_PERMISSION_REQUEST_CODE);
            }
        }
    }

    private void openCamera(){
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }
}
