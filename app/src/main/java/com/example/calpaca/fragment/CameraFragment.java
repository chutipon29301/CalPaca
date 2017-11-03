package com.example.calpaca.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.calpaca.R;
import com.varunest.sparkbutton.SparkButton;
import com.varunest.sparkbutton.SparkButtonBuilder;
import com.varunest.sparkbutton.SparkEventListener;

import java.util.ArrayList;


/**
 * Created by admin on 30/10/2017 AD.
 */

public class CameraFragment extends Fragment {

    private static CameraFragment instance;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_IMAGE = 2;
    private static SparkButton btnSelectPhoto;
    private static SparkButton btnTakePhoto;

    private ImageView mImageView;

    public static CameraFragment newInstance() {
        if (instance == null) {
            instance = new CameraFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment(savedInstanceState);
    }


    private void initFragment(Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_camera, container, false);
        initInstance(rootView, savedInstanceState);
        return rootView;
    }

    private void initInstance(View rootView, Bundle savedInstanceState) {

        btnTakePhoto = (SparkButton) rootView.findViewById(R.id.btnTakePhoto);
        btnSelectPhoto = (SparkButton) rootView.findViewById(R.id.btnSelectPhoto);
        btnTakePhoto.setEventListener(new SetSpark(btnTakePhoto));
        btnSelectPhoto.setEventListener(new SetSpark(btnSelectPhoto));


        final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bouncing);


        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        btnTakePhoto.startAnimation(myAnim);
        btnSelectPhoto.startAnimation(myAnim);



    }


    class SetSpark implements SparkEventListener{
        private SparkButton btn;
        SetSpark(SparkButton btn){
            this.btn= btn;

        }
        @Override
        public void onEvent(ImageView button, boolean buttonState) {
            if(btn == btnTakePhoto){
                Log.d("SparkButton", "In listener");
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, 1);

            }
            if(btn == btnSelectPhoto){
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        }

        @Override
        public void onEventAnimationEnd(ImageView button, boolean buttonState) {

        }

        @Override
        public void onEventAnimationStart(ImageView button, boolean buttonState) {

        }
    }


    View.OnClickListener selectPhotoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnSelectPhoto) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("CameraDebug", "resultCode: " + resultCode);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == -1) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
        if (requestCode == PICK_IMAGE && resultCode == -1) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            mImageView.setImageBitmap(imageBitmap);
        }
    }
    class MyBounceInterpolator implements android.view.animation.Interpolator {
        private double mAmplitude = 0.1;
        private double mFrequency = 10;

        MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float)(-1 * Math.pow(Math.E, -time/ mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }
    }
}
