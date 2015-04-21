package com.example.hoang.myaudio;

/**
 * Created by hoang on 20/04/2015.
 */
import java.lang.Thread;
import java.util.HashMap;


import android.app.Activity;
import android.os.Bundle;


public class GuitarTunerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_ = new DrawableView(this);
        tv_.setText("Hello, Android");
        setContentView(tv_);
    }
    @Override
    public void onStart() {
        super.onStart();
        pitch_detector_thread_ = new Thread(new PitchDetector(this));
        pitch_detector_thread_.start();
    }
    @Override
    public void onStop() {
        super.onStop();
        pitch_detector_thread_.interrupt();
    }

    public void ShowPitchDetectionResult(String result, final HashMap<Double, Double> frequencies) {
        tv_.setText(result);
        tv_.setFrequencies(frequencies);
    }

    DrawableView tv_;
    Thread pitch_detector_thread_;
}