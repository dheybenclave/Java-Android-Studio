package com.example.dheoclaveria.projectos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import static com.example.dheoclaveria.projectos.Imageitem_Form.getidimage;
import static com.example.dheoclaveria.projectos.Main_Form.adapter_subject;
import static com.example.dheoclaveria.projectos.Main_Form.getid;

public class ImageDetails_Form extends AppCompatActivity {

    TextView txtsapl;

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


    ImageButton imgbtndate, imgbtntime;
    EditText txttitle, txtsubjectname, txtkeyword, txtdescription;
    TextView lbldate, lbltime;

    Resource res = new Resource();

    DBTools dbTools;
    Cursor cursor;
    private Intent intentdata;

    String image, background, passgetid, passgetcolor, passgetimage, passgettitle = "";
    Uri passsubimage;
    ColorDrawable passsubcolor;
    Toolbar toolbar;
    private DialogInterface.OnClickListener dialoglistener;
    private int getids;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details__form);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        c = Calendar.getInstance();
        frmYear = toYear = c.get(Calendar.YEAR);
        frmMonth = toMonth = c.get(Calendar.MONTH);
        frmDay = toDay = c.get(Calendar.DAY_OF_MONTH);

        dbTools = new DBTools(getApplication());

        txttitle = (EditText) findViewById(R.id.txttitle);
        txttitle.addTextChangedListener(new fortext());
        txtsubjectname = (EditText) findViewById(R.id.txtsubjectname);
        txtkeyword = (EditText) findViewById(R.id.txtkeyword);
        txtdescription = (EditText) findViewById(R.id.txtdescription);

        lbldate = (TextView) findViewById(R.id.lbldate);
        lbldate.setOnClickListener(new ClickEvent());
        lbltime = (TextView) findViewById(R.id.lbltime);
        lbltime.setOnClickListener(new ClickEvent());

        imgbtndate = (ImageButton) findViewById(R.id.imgbtndate);
        imgbtndate.setOnClickListener(new ClickEvent());
        imgbtntime = (ImageButton) findViewById(R.id.imgbtntime);
        imgbtntime.setOnClickListener(new ClickEvent());


//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();

        load();
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case FROM_DATE_DIALOG:

                return new DatePickerDialog(this, frmdateListner, frmYear, frmMonth, frmDay);
            case FROM_TIME_DIALOG:
                return new TimePickerDialog(this, frmtimeSetListener, frmHour, frmMinute, false);
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

        }

    };


    private void updatefromDateTime() {
        frmdate = String.valueOf(frmMonth + 1) + "-" + frmDay + "-" + frmYear + " ";
        frmtime = String.valueOf(pad(frmHour)) + " : " + String.valueOf(pad(frmMinute));

        lbldate.setText(new StringBuilder().append(frmdate));
        lbltime.setText(new StringBuilder().append(frmtime));

    }

    private void updatetoDateTime() {
        todate = String.valueOf(toMonth + 1) + "-" + toDay + "-" + toYear + " ";
        totime = String.valueOf(pad(toHour)) + " : " + String.valueOf(pad(toMinute));

        lbldate.setText(new StringBuilder().append(todate));
        lbltime.setText(new StringBuilder().append(totime));

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

    private class ClickEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (lbldate == v) {
                showDialog(FROM_DATE_DIALOG);
                updatefromDateTime();
            } else if (lbltime == v) {
                showDialog(FROM_TIME_DIALOG);
                updatefromDateTime();
            } else if (imgbtndate == v) {
                showDialog(FROM_DATE_DIALOG);
                updatefromDateTime();
            } else if (imgbtntime == v) {
                showDialog(FROM_TIME_DIALOG);
                updatefromDateTime();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        forsaving();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        AlertDialog.Builder builder = new AlertDialog.Builder(ImageDetails_Form.this);
//        builder.setMessage("Do you want to save ").setPositiveButton("Yes", dialoglistener).setNegativeButton("No", dialoglistener).show();
//     }

    private void forsaving() {

        String title = txttitle.getText().toString();
        String keyword = txtkeyword.getText().toString();
        String description = txtdescription.getText().toString();
        String date = lbldate.getText().toString();
        String time = lbltime.getText().toString();
        if (title.trim().length() != 0 &&
                txtsubjectname.getText().toString().trim().length() != 0) {

            String s = "UPDATE " + dbTools.tbl_name + " SET " + dbTools.fld_title + "='" + title +
                    "', " + dbTools.fld_keyword + "='" + keyword + "', " + dbTools.fld_description + "='" + description + "', " +
                    dbTools.fld_date + "='" + date + "', " + dbTools.fld_time + "='" + time + "' WHERE " + dbTools.fld_id + "=" + getidimage + " ;";
            dbTools.db.execSQL(s);
            Toast.makeText(this, "Updating Details Success", Toast.LENGTH_SHORT).show();
        }
    }

    private void load() {
        String query = "SELECT * FROM " + dbTools.tbl_name + " WHERE " + dbTools.fld_id + "=" + getidimage + " ;";
        //cursor = dbTools.getsingleDatasub(gettitle);

        cursor = dbTools.executeQuery(query);
        if (cursor.moveToFirst()) {

            do {
                //  int id, String title, String subjectname, String descripton, String keyword, String date, String time , String image) {
                getids = cursor.getInt(0);
                txttitle.setText(cursor.getString(1));
                toolbar.setTitle(cursor.getString(1));
                txtdescription.setText(cursor.getString(3));
                txtsubjectname.setText(cursor.getString(2));
                txtkeyword.setText(cursor.getString(4));
                lbldate.setText(cursor.getString(5));
                lbltime.setText(cursor.getString(6));

            } while (cursor.moveToNext());
        }
    }

    private class fortext implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            toolbar.setTitle(txttitle.getText());

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            toolbar.setTitle(txttitle.getText());

        }

        @Override
        public void afterTextChanged(Editable editable) {
            toolbar.setTitle(txttitle.getText());

        }
    }

    public void dialoglistener() {
        dialoglistener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        forsaving();

                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        adapter_subject.notifyDataSetChanged();
    }

    public void fordelete() {

        dbTools.deleteData(dbTools.tbl_subjectname, Integer.valueOf(getid));
        // DELETE FROM `db_scheduler_system`.`tbl_schedule` WHERE `schedule_id`='0';
        String s = "DELETE FROM " + dbTools.tbl_name + "  WHERE " + dbTools.fld_id + "='" + getids + "'";
        dbTools.db.execSQL(s);
        Toast.makeText(getApplicationContext(), "Delete Success", Toast.LENGTH_SHORT).show();
        adapter_subject.notifyDataSetChanged();

    }


}