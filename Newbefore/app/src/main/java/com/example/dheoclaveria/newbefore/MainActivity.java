package com.example.dheoclaveria.newbefore;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgnbefore;
    Sensor sensor;
    SensorManager sensormanager;
    Float dim = 0.002f;
    Float bright = 4.00f;
    int proxyvalue = 0;
    WindowManager.LayoutParams winlayout;
    Vibrator vibrator;
    SensorEvents sensorevents  = new SensorEvents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgnbefore = (ImageView)findViewById(R.id.imgbefore);

        sensormanager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensormanager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        if(sensor !=null){

            sensormanager.registerListener(sensorevents,sensor,sensormanager.SENSOR_DELAY_NORMAL);
        }

        winlayout = getWindow().getAttributes();
        winlayout.screenBrightness = dim;
        getWindow().setAttributes(winlayout);

    }


    private class SensorEvents  implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorevent) {
            proxyvalue = (int) sensorevent.values[0];
            if(proxyvalue == 0)
            {
                winlayout.screenBrightness = bright;
                getWindow().setAttributes(winlayout);
                vibrator.vibrate(5000);
                imgnbefore.setImageResource(R.drawable.new_after);
            }
            else{
                vibrator.cancel();
                winlayout.screenBrightness = dim;
                getWindow().setAttributes(winlayout);
                imgnbefore.setImageResource(R.drawable.new_before);
            }


        }

        @Override
            public void onAccuracyChanged(Sensor sensor, int i) {


        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensormanager != null) {

            sensormanager.unregisterListener(sensorevents,sensor);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(sensor !=null){sensormanager.registerListener(sensorevents,sensor,sensormanager.SENSOR_DELAY_NORMAL);}

        winlayout = getWindow().getAttributes();
        winlayout.screenBrightness = dim;
        getWindow().setAttributes(winlayout);

    }
}
