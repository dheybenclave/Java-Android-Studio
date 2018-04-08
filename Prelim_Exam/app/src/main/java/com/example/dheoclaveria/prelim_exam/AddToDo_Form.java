package com.example.dheoclaveria.prelim_exam;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.dheoclaveria.prelim_exam.Main_Form.update_id;
import static com.example.dheoclaveria.prelim_exam.R.id.lblstatustop;
//dheo claveria and noel atazar prelim examaniation

public class AddToDo_Form extends AppCompatActivity {

    EditText txttitle, txtdetails;
    Button btnfrmdate, btnfrmtime, btntodate, btntotime, btnsave, btncancel;
    TextView lblprioritystatus;
    RelativeLayout pnlheader;
    SeekBar sbpriority;

    DBTools dbTools;
    Cursor cursor;
    ArrayAdapter<String> adapter1;
    ArrayList list;

    Calendar c;

    static final int FROM_DATE_DIALOG = 0;
    static final int FROM_TIME_DIALOG = 1;
    static final int TO_DATE_DIALOG = 3;
    static final int TO_TIME_DIALOG = 4;
    private int frmYear, frmMonth, frmDay, frmHour, frmMinute;
    private int toYear, toMonth, toDay, toHour, toMinute;
    String frmdate, todate = "DATE";
    String frmtime, totime = " TIME";
    String title, details;
    int pass_updateid, prioritylevel = 0;

    Intent intentdata;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do__form);


        pnlheader = (RelativeLayout) findViewById(R.id.pnlheader);
        lblprioritystatus = (TextView) findViewById(lblstatustop);

        txttitle = (EditText) findViewById(R.id.txttitle);
        txtdetails = (EditText) findViewById(R.id.txtdetails);

        btnfrmdate = (Button) findViewById(R.id.btnfrmdate);
        btnfrmdate.setOnClickListener(new ButtonEvent());

        btnfrmtime = (Button) findViewById(R.id.btnfrmtime);
        btnfrmtime.setOnClickListener(new ButtonEvent());

        btntodate = (Button) findViewById(R.id.btntodate);
        btntodate.setOnClickListener(new ButtonEvent());

        btntotime = (Button) findViewById(R.id.btntotime);
        btntotime.setOnClickListener(new ButtonEvent());

        sbpriority = (SeekBar) findViewById(R.id.sbpriority);
        sbpriority.setOnSeekBarChangeListener(new SeekbarHandler());

        btnsave = (Button) findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new ButtonEvent());

        btncancel = (Button) findViewById(R.id.btncancel);
        btncancel.setOnClickListener(new ButtonEvent());

        dbTools = new DBTools(getApplicationContext());
        list = new ArrayList<String>();
        pass_updateid = update_id;
        if (pass_updateid != 0) {
            loadforupdate();
        }
        else {
        }

        c = Calendar.getInstance();
        frmYear = toYear = c.get(Calendar.YEAR);
        frmMonth = toMonth = c.get(Calendar.MONTH);
        frmDay = toDay = c.get(Calendar.DAY_OF_MONTH);

    }

    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnsave == v) {
                forsaveandupdate();
            } else if (btncancel == v) {
                forcancel();
            } else if (btnfrmdate == v) {
                showDialog(FROM_DATE_DIALOG);
                updatefromDateTime();
            } else if (btnfrmtime == v) {
                showDialog(FROM_TIME_DIALOG);
                updatefromDateTime();
            } else if (btntodate == v) {
                showDialog(TO_DATE_DIALOG);
                updatetoDateTime();
            } else if (btntotime == v) {
                showDialog(TO_TIME_DIALOG);
                updatetoDateTime();
            }
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case FROM_DATE_DIALOG:

                return new DatePickerDialog(this, frmdateListner, frmYear, frmMonth, frmDay);
            case FROM_TIME_DIALOG:
                return new TimePickerDialog(this, frmtimeSetListener, frmHour, frmMinute, false);
            case TO_DATE_DIALOG:
                return new DatePickerDialog(this, todateListner, toYear, toMonth, toDay);
            case TO_TIME_DIALOG:
                return new TimePickerDialog(this, totimeSetListener, toHour, toMinute, false);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener frmdateListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            frmYear = year;
            frmMonth = month;
            frmDay = dayOfMonth;
            updatefromDateTime();
        }
    };
    private TimePickerDialog.OnTimeSetListener frmtimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            frmHour = hourOfDay;
            frmMinute = minute;
            updatetoDateTime();
        }
    };

    private DatePickerDialog.OnDateSetListener todateListner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            toYear = year;
            toMonth = month;
            toDay = dayOfMonth;
            updatetoDateTime();
        }
    };
    private TimePickerDialog.OnTimeSetListener totimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            toHour = hourOfDay;
            toMinute = minute;
            updatetoDateTime();

        }
    };


    private void updatefromDateTime() {
        frmdate = String.valueOf(frmMonth + 1) + "-" + frmDay + "-" + frmYear + " ";
        frmtime = String.valueOf(pad(frmHour)) + " : " + String.valueOf(pad(frmMinute));

        btnfrmdate.setText(new StringBuilder().append(frmdate));
        btnfrmtime.setText(new StringBuilder().append(frmtime));

    }

    private void updatetoDateTime() {
        todate = String.valueOf(toMonth + 1) + "-" + toDay + "-" + toYear + " ";
        totime = String.valueOf(pad(toHour)) + " : " + String.valueOf(pad(toMinute));

        btntodate.setText(new StringBuilder().append(todate));
        btntotime.setText(new StringBuilder().append(totime));

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

    public void prioritylevelcolor() {

        if (sbpriority.getProgress() == 0) {
            //low
            lblprioritystatus.setText("PRIORITY STATUS : LOW");
            pnlheader.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2dcc70")));
        } else if (sbpriority.getProgress() == 1) {
            //meduim
            lblprioritystatus.setText("PRIORITY STATUS : MEDUIM");
            pnlheader.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffae00")));
        } else if (sbpriority.getProgress() == 2) {
            //high
            lblprioritystatus.setText("PRIORITY STATUS : HIGH");
            pnlheader.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#c23a2c")));
        }

    }

    private class SeekbarHandler implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            // pnlheader.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFF")));
            prioritylevelcolor();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }


    public void forsaveandupdate() {

        if (txttitle.toString().trim().length() != 0 && btnfrmdate.toString() != "DATE" && btntodate.toString() != "DATE") {
            intentdata = getIntent();
            title = txttitle.getText().toString().trim();
            details = txtdetails.getText().toString().trim();
            frmdate = btnfrmdate.getText().toString().trim();
            frmtime = btnfrmtime.getText().toString().trim();
            todate = btntodate.getText().toString().trim();
            totime = btntotime.getText().toString().trim();
            prioritylevel = Integer.valueOf(sbpriority.getProgress());
            if (btnsave.getText().toString().equals("SAVE"))
                dbTools.insertData(title, details, frmdate, frmtime, todate, totime, prioritylevel);
            else {
                dbTools.updateData(update_id, title, details, frmdate, frmtime, todate, totime, prioritylevel);
            }

            setResult(1, intentdata);
            finish();
            pass_updateid = 0;
        } else {
            Toast.makeText(getApplicationContext(), "Please fill important details!.", Toast.LENGTH_SHORT).show();
        }
    }

    public void forcancel() {
        setResult(1, getIntent());
        finish();
    }

    public void loadforupdate() {



        String query = "SELECT * FROM " + dbTools.tbl_name + " WHERE " + dbTools.fld_id + " = " + pass_updateid;
        cursor = dbTools.executeQuery(query);
        if (cursor.moveToFirst()) {
            do {
                txttitle.setText(cursor.getString(1));
                txtdetails.setText(cursor.getString(2));
                btnfrmdate.setText(cursor.getString(3));
                btnfrmtime.setText(cursor.getString(4));
                btntodate.setText(cursor.getString(5));
                btntotime.setText(cursor.getString(6));
                sbpriority.setProgress(Integer.valueOf(cursor.getString(7)));
                prioritylevelcolor();

            }
            while (cursor.moveToNext());

            btnsave.setText("UPDATE");
            btnsave.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8d44ad")));
        }
        pass_updateid = 0;
    }
}
