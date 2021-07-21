package com.deepak.alphametrica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText input_email= findViewById(R.id.login_page_email_input);
        EditText input_password= findViewById(R.id.login_page_password_input);
        Button do_login = findViewById(R.id.btn_login_page_login);
        TextView go_to_signup = findViewById(R.id.tv_signup_page_login);

        do_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_entered = input_email.getText().toString();
                String password_entered = input_password.getText().toString();


                if(email_entered.equals("test@luxpmsoft.com") && password_entered.equals("test1234!")){
                    Intent i = new Intent(LoginActivity.this, HomePageActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Email Id or Password Incorrect",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        go_to_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}