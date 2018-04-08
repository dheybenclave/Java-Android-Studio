package com.example.dheoclaveria.assignment_3;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Locale;


public class Main_Form extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextView lbldate, lbltimer, lbltime;
    EditText txtrestoname, txtservername, txtaddtionalremarks;
    Button btnreset, btndate, btntime, btnsave;
    ToggleButton tbtnonoff;
    RatingBar rtbar;
    TextToSpeech ttspeech;

    private int second = 0;
    private boolean running, isRunning;

    static final int DATE_DIALOG = 0;
    static final int TIME_DIALOG = 1;


    private int Year, Month, Day, Hour, Minute;

    private final int REQ_CODE_SPEECH_INPUT = 100;

    String dateval = "DATA PICKER";
    String timeval = "TIME PICKER";
    Float ratingval = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);

        if (savedInstanceState != null) {

            second = savedInstanceState.getInt("second");
            running = savedInstanceState.getBoolean("running");
            isRunning = savedInstanceState.getBoolean("isRunning");
            dateval = savedInstanceState.getString("dateval");
            timeval = savedInstanceState.getString("timeval");
            ratingval = savedInstanceState.getFloat("ratingval");
        }

        txtrestoname = (EditText) findViewById(R.id.txtrestoname);
        txtservername = (EditText) findViewById(R.id.txtservername);
        fortextchanged();

        lbltime = (TextView) findViewById(R.id.lbltimer);

        tbtnonoff = (ToggleButton) findViewById(R.id.tbtnonoff);
        tbtnonoff.setOnClickListener(new ButtonEvent());

        btnreset = (Button) findViewById(R.id.btnreset);
        btnreset.setOnClickListener(new ButtonEvent());

        rtbar = (RatingBar) findViewById(R.id.rtbar);
        forratingbar();

        btndate = (Button) findViewById(R.id.btndate);
        btndate.setOnClickListener(new ButtonEvent());

        lbldate = (TextView) findViewById(R.id.lbldate);

        btntime = (Button) findViewById(R.id.btntime);
        btntime.setOnClickListener(new ButtonEvent());

        lbltime = (TextView) findViewById(R.id.lbltime);

        btnsave = (Button) findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new ButtonEvent());

        ttspeech = new TextToSpeech(this, this);
        runTimer();

        Speakout("Hello to Food.inc");
    }

    private void runTimer() {
        lbltimer = (TextView) findViewById(R.id.lbltimer);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int min = (second % 3600) / 60;
                int sec = second % 60;

                lbldate.setText(dateval);
                lbltime.setText(timeval);

                String time = String.format("%d:%02d:%02d", hours, min, sec);
                lbltimer.setText(time);
                if (running) {
                    second++;
                }
                handler.postDelayed(this, 1000);
            }
        });
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

    public void fortextchanged() {

        txtrestoname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    Toast.makeText(Main_Form.this, "Your are typing..", Toast.LENGTH_SHORT).show();
                } else {
                    if (txtrestoname.getText().toString().trim().length() != 0) {
                        Speakout("Your Restaurant Name is " + txtrestoname.getText().toString());
                        Toast.makeText(Main_Form.this, "Your Restaurant Name is " + txtrestoname.getText().toString(), Toast.LENGTH_SHORT).show();
                    } else {
                    }
                }
            }
        });

        txtservername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    Toast.makeText(Main_Form.this, "Your are typing..", Toast.LENGTH_SHORT).show();
                } else {
                    if (txtservername.getText().toString().trim().length() != 0) {
                        Speakout("Your Server Name is " + txtservername.getText().toString());
                        Toast.makeText(Main_Form.this, "Your Server Name is " + txtservername.getText().toString(), Toast.LENGTH_SHORT).show();
                    } else {
                    }
                }
            }
        });
    }

    public void fortogglebutton() {

        if (tbtnonoff.isChecked()) {
            Start();
            Toast.makeText(Main_Form.this, "Timer is Running", Toast.LENGTH_LONG).show();
            Speakout("Timer is Running");
        } else {
            Stop();
            Toast.makeText(Main_Form.this, "Timer is Stop", Toast.LENGTH_LONG).show();
            Speakout("Timer is Stop");
        }
    }

    public void forratingbar() {

        rtbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingval = rating;
                Toast.makeText(Main_Form.this, "THE RATING IS : " + ratingval, Toast.LENGTH_SHORT).show();
                Speakout("You rate " + ratingval + " stars");
            }
        });
    }
    public void forsaving(){

    }
    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtrestoname.setText(result.get(0));
                }
                break;
            }

        }
    }

    private DatePickerDialog.OnDateSetListener dateListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            view.updateDate(year, month, dayOfMonth);
            Year = year;
            Month = month;
            Day = dayOfMonth;
            UpdateDisplay();
            Speakout("Our date for today is" + dateval);
        }
    };
    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Hour = hourOfDay;
            Minute = minute;
            UpdateDisplay();
            Speakout("Our time is" + timeval);
        }
    };

    private void UpdateDisplay() {
        dateval = String.valueOf(Month + 1) + "-" + Day + "-" + Year + " ";
        timeval = String.valueOf(pad(Hour)) + " : " + String.valueOf(pad(Minute));
        lbldate.setText(new StringBuilder().append(dateval));
        lbltime.setText(new StringBuilder().append(timeval));

    }


    private static String pad(int c) {

        if (String.valueOf(c).length() > 2) {
            return String.valueOf(c);
        } else if (String.valueOf(c).length() == 1) {
            return "0" + String.valueOf(c);
        } else {
            return String.valueOf(c);
        }
    }


    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (tbtnonoff == v) {
                fortogglebutton();
            } else if (btnreset == v) {
                Reset();
                Speakout("Timer is Reset");
            } else if (btndate == v) {
                showDialog(DATE_DIALOG);
                UpdateDisplay();
            } else if (btntime == v) {
                showDialog(TIME_DIALOG);
                UpdateDisplay();

            } else if (btnsave == v) {
               Speakout("unfortunately, We don't have a function for this Feature, Thank you for your patients");
            //askSpeechInput();
            }

        }
    }


    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            int result = ttspeech.setLanguage(Locale.getDefault());
            ttspeech.setPitch(-10);
            // ttspeech.setSpeechRate(2);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(Main_Form.this, "THE LANGUAGE NOT TOTALLY FUCKING SUPPORTED", Toast.LENGTH_LONG).show();
            } else {
                Breakout();
            }
        }

    }

    public void Speakout(String data) {

        ttspeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void Breakout() {

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

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRunning) {
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle s) {
        super.onSaveInstanceState(s);
        s.putInt("second", second);
        s.putBoolean("running", running);
        s.putBoolean("isRunning", isRunning);
        s.putString("dateval", dateval);
        s.putString("timeval", timeval);
        s.putFloat("ratingval", ratingval);
    }


}
