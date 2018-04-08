package com.example.nienie.myfavoriteplaces;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    TextView tvCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new ButtonEvent());
        tvCreateAccount = (TextView) findViewById(R.id.tvCreateAccount);
        tvCreateAccount.setOnClickListener(new ButtonEvent());
    }

    public class ButtonEvent implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view == btnLogin)
            {
                Intent intent = new Intent(MainActivity.this,MainLogin.class);
                startActivity(intent);
                finish();
            }
            if(view == tvCreateAccount)
            {
                Intent intent = new Intent(MainActivity.this,MainCreateAccount.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
