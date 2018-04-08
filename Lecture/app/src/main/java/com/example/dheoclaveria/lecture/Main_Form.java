package com.example.dheoclaveria.lecture;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Main_Form extends AppCompatActivity {

    private boolean running, isRunning;
    TextView txt1;
    SensorManager sensorManager ;
    Sensor sensor;
    List<Sensor> lstsensor;
    float dy = 0;
    float dx = 0 ;
    float dz = 0 ;

    long lastupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        txt1 = (TextView) findViewById(R.id.txt1);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        lstsensor  = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s : lstsensor){

          //  txt1.append("\n \n  Sensor name " + s.getName() + " \n  Sensor Type " + s.getType());
        }
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor != null){
            sensorManager.registerListener(accelerate,sensor,sensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Toast.makeText(getApplicationContext(), "No accelerometerfound",Toast.LENGTH_SHORT ).show();
        }
    }

    public SensorEventListener accelerate = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            dx = event.values[0];
            dy = event.values[1];
            dz = event.values[2];
            txt1.setText("dx = " + dx + " dy = " + dy + " dz = " + dz);
            long  currtime = System.currentTimeMillis();
            if(currtime - lastupdate > 500){

                if(((int)dx) > 5){
                    Toast.makeText(Main_Form.this,"Right Direction", Toast.LENGTH_SHORT).show();
                }else if (((int)dx) < -5){
                    Toast.makeText(Main_Form.this,"Left Direction", Toast.LENGTH_SHORT).show();
                }
                else{}
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
         sensor = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(accelerate,sensor,sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sensorManager.registerListener(accelerate,sensor,sensorManager.SENSOR_DELAY_NORMAL);

    }
}
