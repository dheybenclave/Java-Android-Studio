package com.example.nienie.myfavoriteplaces;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainCreateAccount extends AppCompatActivity {
    Button btnCreateAccount;
    TextView tvHaveAccount;
    EditText etName, etUsername, etPassword, etCPassword;
    DBUtilities dbUtilities;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create_account);
        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etCPassword = (EditText) findViewById(R.id.etCPassword);
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);
        btnCreateAccount.setOnClickListener(new ButtonEvent());
        tvHaveAccount = (TextView) findViewById(R.id.tvHaveAccount);
        tvHaveAccount.setOnClickListener(new ButtonEvent());
        dbUtilities = new DBUtilities(getApplicationContext());
        etName.setOnFocusChangeListener(new Focus());
        etUsername.setOnFocusChangeListener(new Focus());
        etPassword.setOnFocusChangeListener(new Focus());
        etCPassword.setOnFocusChangeListener(new Focus());
    }


    private class Focus implements View.OnFocusChangeListener{
        @Override
        public void onFocusChange(View view, boolean b) {
            if(view == etName)
                if(b)
                    etName.setHint("");
                else
                    etName.setHint("Name");
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
            if(view == etCPassword)
                if(b)
                    etCPassword.setHint("");
                else
                    etCPassword.setHint("Confirm Password");
        }
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
            if (view == tvHaveAccount) {
                Intent intent = new Intent(MainCreateAccount.this, MainLogin.class);
                startActivity(intent);
                finish();
            }
            if (view == btnCreateAccount) {
                if (etName.getText().toString().trim().length() != 0 &&
                        etUsername.getText().toString().trim().length() != 0 &&
                        etPassword.getText().toString().trim().length() != 0 &&
                        etCPassword.getText().toString().trim().length() != 0) {
                    if (etPassword.getText().toString().equals(etCPassword.getText().toString())) {
                        CreateAccount();
                    } else {
                        Toast.makeText(MainCreateAccount.this, "Password and Current Password did not match!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainCreateAccount.this, "Fill all the Text field to proceed!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainCreateAccount.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void CreateAccount() {
        cursor = dbUtilities.getUsername(etUsername.getText().toString());
        if (cursor.moveToFirst()) {
            Toast.makeText(this, "Username already exist!", Toast.LENGTH_SHORT).show();
        } else {
            dbUtilities.insertUser(
                    etName.getText().toString(),
                    etUsername.getText().toString(),
                    etPassword.getText().toString()
            );
            Intent intent = new Intent(MainCreateAccount.this, MainLogin.class);
            startActivity(intent);
            finish();
        }
    }
}