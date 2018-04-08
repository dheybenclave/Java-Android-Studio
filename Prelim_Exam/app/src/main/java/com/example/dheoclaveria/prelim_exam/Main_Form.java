package com.example.dheoclaveria.prelim_exam;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
//dheo claveria and noel atazar prelim examaniation
public class Main_Form extends AppCompatActivity {

    Button btnadd;
    ListView lstcustomview;
    TextView lbldate,lblsubdate;
    MultiAutoCompleteTextView txtsearch;

    private List<Todo_Data> todo_data = new ArrayList<Todo_Data>();
    private CustomListAdapter adapter;

    DBTools dbTools;
    Cursor cursor;
    Calendar c;

    String frmdate, todate = "DATE";
    String frmtime, totime = " TIME";
    String title, details, subdate, searchval;
    int prioritylevel = 0;
    boolean  islongpress = false;

    List<Integer> sortedid = new ArrayList<Integer>();
    List<String> lstoffrmdate = new ArrayList<String>();

    ArrayAdapter adapterlst ;
    DialogInterface.OnClickListener dialoglistener;

    public static int update_id = 0;
    public int listviewposition = 0;

    private Intent mNotificationIntent;
    private PendingIntent mContentIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__form);
        this.mNotificationIntent = new Intent(this.getApplicationContext(), NotificationSubActivity.class);
        this.mContentIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, this.mNotificationIntent, 268435456);


        dbTools = new DBTools(getApplicationContext());
        adapter = new CustomListAdapter(this, todo_data);

        lbldate = (TextView) findViewById(R.id.lbldate);
        lblsubdate = (TextView) findViewById(R.id.lblsubdate);

        btnadd = (Button) findViewById(R.id.btnadd);
        btnadd.setOnClickListener(new ButtonEvent());

        lstcustomview = (ListView) findViewById(R.id.lstcustomview);
        lstcustomview.setAdapter(adapter);
        lstcustomview.setOnItemClickListener(new ListViewItemClick());
        lstcustomview.setOnItemLongClickListener(new ListViewItemLongClick());

        txtsearch = (MultiAutoCompleteTextView) findViewById(R.id.txtsearch);
        adapterlst  =  new ArrayAdapter(this, android.R.layout.simple_list_item_1,lstoffrmdate);
        txtsearch.setAdapter(adapterlst);
        txtsearch.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        txtsearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchval = txtsearch.getText().toString().split(":")[0].toString();


                Toast.makeText(getApplicationContext(),searchval,Toast.LENGTH_LONG).show();
                completionsearch();
                }
        });

        dialoglistener();
        loadData();

    }


    private class ButtonEvent implements View.OnClickListener {

        public void onClick(View v) {
            if (btnadd == v) {
                foradd();
            }
        }
    }

    public class ListViewItemLongClick implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            //update_id = Integer.valueOf(todo_data.get(i).getId().toString());
            update_id = Integer.valueOf(sortedid.get(i).toString());
            AlertDialog.Builder builder = new AlertDialog.Builder(Main_Form.this);
            builder.setMessage("Do you want to delete this item? ").setPositiveButton("Yes", dialoglistener).setNegativeButton("No", dialoglistener).show();
            islongpress  = true;
            return false;
        }
    }

    public class ListViewItemClick implements AdapterView.OnItemClickListener, View.OnClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // update_id = Integer.valueOf(todo_data.get(i).getId().toString());

            if (islongpress == false) {
                update_id = Integer.valueOf(sortedid.get(i).toString());
                forupdate();
            } else {}
         islongpress = false;
        }

        @Override
        public void onClick(View view) {
            view.cancelLongPress();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 1) {
            if (requestCode == 1) {
                loadData();
            }
        }
    }



    private void loadData() {

        todo_data.clear();
        sortedid.clear();
        adapter = new CustomListAdapter(this, todo_data);
        try {
            //cursor = dbTools.getAllData();
            String query = "SELECT * FROM " + dbTools.tbl_name + " ORDER BY " + dbTools.fld_prioritylevel + " DESC";
            cursor = dbTools.executeQuery(query);
            if (cursor.moveToFirst()) {

                do {
                    int getid = cursor.getInt(0);
                    title = cursor.getString(1);
                    details = cursor.getString(2);
                    frmdate = cursor.getString(3);
                    frmtime = cursor.getString(4);
                    todate = cursor.getString(5);
                    totime = cursor.getString(6);
                    prioritylevel = cursor.getInt(7);
                    todo_data.add(new Todo_Data(String.valueOf(getid), title, details, frmdate, frmtime, todate, totime, prioritylevel));
                    sortedid.add(cursor.getInt(0));
                    lstoffrmdate.add(cursor.getString(3) + " : " + title);

                }
                while (cursor.moveToNext());
                lstcustomview.setAdapter(adapter);

            }
        } catch (Exception ex) {
            todo_data.clear();
            sortedid.clear();
        }
    }

    public void foradd() {
        update_id = 0;
        Intent intent = new Intent(Main_Form.this, AddToDo_Form.class);
        startActivityForResult(intent, 1);
        fornotif();
    }

    public void fornotif(){

        Bitmap icon1 = BitmapFactory.decodeResource(getResources(),R.drawable.final1);
        NotificationCompat.BigTextStyle bigtext = new NotificationCompat.BigTextStyle();
        bigtext.bigText("try ko lang kung gagana talga kung hinid shet kana talga \n so yung nga no mga kapaa  \n ahhahahhaha sfdsjkahisdafhaekfjhsadkfjahseifusadhfkjsaefes \n" +
                         "  asdjfhskdjfhakfsjdfhaksdfjdsahfkadsjfhsadkfjahsdfkajdshfsadkfjhsadfkdsjfh \n hello po ate ");
        bigtext.setBigContentTitle("Sample TITLE Notifivcation");
        bigtext.setSummaryText("By the semnder daw to para ganun daw");


        NotificationCompat.Builder mybuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.final1)
                .setContentTitle("Big Notif Sample Title")
                .setContentText("Test for  my big text notiffation!")
                .setLargeIcon(icon1)
                .setStyle(bigtext);

        NotificationManager notifmanager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifmanager.notify(0,mybuilder.build());

    }


    public void forupdate() {
        Intent intent = new Intent(Main_Form.this, AddToDo_Form.class);
        startActivityForResult(intent, 1);
    }

    public void fordelete() {

        dbTools.deleteData(update_id);
        loadData();
        Toast.makeText(getApplicationContext(), "Delete Success", Toast.LENGTH_SHORT).show();

    }

    public void completionsearch() {

        todo_data.clear();
        sortedid.clear();
        adapter = new CustomListAdapter(this, todo_data);
        try {
            //cursor = dbTools.getAllData();
            String query = "SELECT * FROM " + dbTools.tbl_name + " WHERE " + dbTools.fld_frmdate + " LIKE '%" + searchval.replace(",","") + "%'";
            cursor = dbTools.executeQuery(query);
            if (cursor.moveToFirst()) {

                do {
                    int getid = cursor.getInt(0);
                    title = cursor.getString(1);
                    details = cursor.getString(2);
                    frmdate = cursor.getString(3);
                    frmtime = cursor.getString(4);
                    todate = cursor.getString(5);
                    totime = cursor.getString(6);
                    prioritylevel = cursor.getInt(7);
                    todo_data.add(new Todo_Data(String.valueOf(getid), title, details, frmdate, frmtime, todate, totime, prioritylevel));
                    sortedid.add(cursor.getInt(0));
                    lstoffrmdate.add(cursor.getString(3));

                }
                while (cursor.moveToNext());
                lstcustomview.setAdapter(adapter);

            }
        } catch (Exception ex) {
            todo_data.clear();
            sortedid.clear();
        }
    }

    public void dialoglistener() {
        dialoglistener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        fordelete();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

    }

}
