package com.example.nienie.myfavoriteplaces;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainLogin extends AppCompatActivity {
    Button btnLogin;
    EditText etUsername,etPassword;
    DBUtilities dbUtilities;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new ButtonEvent());
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        dbUtilities = new DBUtilities(getApplicationContext());
        etPassword.setOnFocusChangeListener(new Focus());
        etUsername.setOnFocusChangeListener(new Focus());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class ButtonEvent implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view == btnLogin) {
                if(etUsername.getText().toString().length()!= 0 &&
                        etPassword.getText().toString().length() != 0)
                        LoginAccount();
                else
                    Toast.makeText(MainLogin.this, "Enter Username and Password!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class Focus implements View.OnFocusChangeListener{
        @Override
        public void onFocusChange(View view, boolean b) {
            if(view == etUsername)
                if(b)
                    etUsername.setHint("");
                else
                    etUsername.setHint("Username");
            if(view == etPassword)
                if(b)
                    etPassword.setHint("");
                else
                    etPassword.setHint("Password");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainLogin.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void LoginAccount()
    {
        cursor = dbUtilities.getUsernamePassword(etUsername.getText().toString(),
                etPassword.getText().toString());
        if (cursor.moveToFirst()) {
            Intent intent = new Intent(MainLogin.this,MainHome.class);
            intent.putExtra("id",cursor.getInt(0));
            intent.putExtra("name",cursor.getString(1));
            startActivity(intent);
            finish();
        }else
            Toast.makeText(this, "Incorrect Username and/or Password!", Toast.LENGTH_SHORT).show();
    }
}
