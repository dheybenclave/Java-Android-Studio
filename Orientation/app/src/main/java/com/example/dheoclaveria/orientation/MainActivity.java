package com.example.dheoclaveria.orientation;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private int second = 0;
    private boolean running, isRunning;

    Button btnstart, btnstop, btnreset;
    TextView lbltimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){

            second = savedInstanceState.getInt("second");
                running = savedInstanceState.getBoolean("running");
                isRunning= savedInstanceState.getBoolean("isRunning");
        }
        btnstart = (Button) findViewById(R.id.btnstart);
        btnstop = (Button) findViewById(R.id.btnstop);
        btnreset = (Button) findViewById(R.id.btnreset);

        btnstart.setOnClickListener(new ButtonEvent());
        btnstop.setOnClickListener(new ButtonEvent());
        btnreset.setOnClickListener(new ButtonEvent());
        runTimer();



    }

    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnstart == v) {
                Start();
            } else if (btnstop == v) {
                Stop();
            } else if (btnreset == v) {
                Reset();
            }

        }
    }

    private void Start() {
        running = true;
    }

    private void Stop() {
        running = false;
    }

    private void Reset() {
        second = 0;
        running = false;
    }

    private void runTimer()
    {
        lbltimer = (TextView) findViewById(R.id.lbltimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int min = (second % 3600) / 60;
                int sec = second % 60;

                String time = String.format("%d:%02d:%02d",hours,min,sec);
                lbltimer.setText(time);
                if (running) {
                    second++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isRunning) {
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle s) {
        super.onSaveInstanceState(s);
        s.putInt("second", second);
        s.putBoolean("running", running);
        s.putBoolean("isRunning", isRunning);
    }


}

