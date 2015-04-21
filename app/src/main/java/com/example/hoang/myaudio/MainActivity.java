package com.example.hoang.myaudio;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {

    FFT1 fft;
    MediaPlayer mPlayer;
    int mFile = R.drawable.borntodie;
    Byte[] mByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayer = MediaPlayer.create(this, mFile);
        mPlayer.start();

        Log.e("MPLAYER", mPlayer.toString());
    }
}
