package com.example.dheoclaveria.splash;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    static final int DATE_DIALOG = 0;
    static final int TIME_DIALOG = 1;
    ImageButton btnselect, btnselect1;
    Button btnproceed;
    DatePicker dtpicker;
    TextView lbldate, lbltime;
    EditText txtinput;
    RatingBar rtbar;
    TextToSpeech ttspeech;

    int Year, Month, Day, Hour, Minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnselect = (ImageButton) findViewById(R.id.btnselect);
        lbldate = (TextView) findViewById(R.id.lbldate);

        btnselect1 = (ImageButton) findViewById(R.id.btnselect1);
        lbltime = (TextView) findViewById(R.id.lbltime);

        txtinput = (EditText) findViewById(R.id.txtinput);
        btnproceed = (Button) findViewById(R.id.btnproceed);
        ttspeech = new TextToSpeech(this, this);

        rtbar = (RatingBar) findViewById(R.id.rtbar);

        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
                UpdateDisplay();
            }
        });

        btnselect1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG);
                UpdateDisplay();
            }
        });
        btnproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Speakout();
            }
        });

        rtbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(MainActivity.this, "THE RATING IS : " + rating, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            Year = year;
            Month = month;
            Day = dayOfMonth;
            UpdateDisplay();
        }
    };
    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Hour = hourOfDay;
            Minute = minute;
            UpdateDisplay();
        }
    };

    private static String pad(int c) {

        if (String.valueOf(c).length() > 2) {
            return String.valueOf(c);
        } else if (String.valueOf(c).length() == 1) {
            return "0" + String.valueOf(c);
        } else {
            return String.valueOf(c);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, dateListner, Year, Month, Day);
            case TIME_DIALOG:
                return new TimePickerDialog(this, timeSetListener, Hour, Minute, false);
        }
        return null;
    }

    private void UpdateDisplay() {
        lbldate.setText(new StringBuilder().append(Month + 1).append("-").append(Day).append("-").append(Year).append(" "));
        lbltime.setText(new StringBuilder().append(pad(Hour) + " : " + pad(Minute)));
    }

    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            int result = ttspeech.setLanguage(Locale.FRENCH);
            ttspeech.setPitch(1);
            // ttspeech.setSpeechRate(2);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(MainActivity.this, "THE LANGUAGE NOT TOTALLY FUCKING SUPPORTED", Toast.LENGTH_LONG).show();
            } else {
                Breakout();
            }
        }

    }

    public void Speakout() {

        String text = txtinput.getText().toString();
        ttspeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void Breakout() {

    }


//    public void Selectfunction(){
//
//        showDialog(DATE_DIALOG);
//
////        final Calendar c = getInstance();
////        Year = c.get(Calendar.YEAR);
////        Month = c.get(Calendar.MONTH);
////        Day  = c.get(Calendar.DAY_OF_MONTH);
//        UpdateDisplay();
//
//    }
//
//    private class ButtonEvent implements View.OnClickListener {
//
//        public void onClick(View v) {
//            if (btnselect == v) {}
//        }
//    }
}
