package com.example.java2.swipe;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SeekBar sb;
    private Thread t;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sb = (SeekBar) findViewById(R.id.myseek);
        text= (TextView) findViewById(R.id.text_tv);
        text.setText("Slide to check balance");
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {
                if (seekBar.getProgress() > 95) {

                } else {
                   sfd(seekBar);
                }


                seekBar.setThumb(getResources().getDrawable(R.drawable.circle));


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                colorChange(seekBar,text);


            }
        });
    }

    private void sfd(final SeekBar seekBar){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = seekBar.getProgress(); i >= 0; i--) {
                    try {
                        t.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seekBar.setProgress(i);
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
    private void colorChange(final SeekBar seekBar, final TextView text){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                final int alpha=seekBar.getProgress();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        float temp=5;
                        if(temp>=0){
                            text.setText("Slide to check balance");
                        }
                        temp-=seekBar.getProgress();
                        temp/=10;
                        text.setAlpha(temp);
                        if(temp<0){
                            text.setAlpha(Math.abs(temp));
                            text.setText("500 RON");
                        }
                    }
                });


            }
        };
        Thread t = new Thread(r);
        t.start();
    }
}
