package com.deepak.alphametrica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText entered_email = findViewById(R.id.signup_page_email_input);
        EditText entered_password = findViewById(R.id.signup_page_password_input);
        EditText re_entered_password = findViewById(R.id.signup_page_confirm_password_input);
        CheckBox terms_check = findViewById(R.id.check_terms);
        Button signupBtn= findViewById(R.id.btn_login_page_login);
        TextView signup_to_login = findViewById(R.id.tv_signup_page_login);

        entered_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                //Do nothing
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pass = s.toString();
                System.out.println(Password_Validation(pass));
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(terms_check.isChecked() &&
                        Email_Validation(entered_email.getText().toString()) &&
                        Password_Validation(entered_password.getText().toString()) &&
                        Confirm_Password(entered_password.getText().toString(), re_entered_password.getText().toString())){
                    System.out.println("success");
                    entered_email.setText("");
                    entered_password.setText("");
                    re_entered_password.setText("");
                    terms_check.setChecked(false);
                    Toast toast = Toast.makeText(getApplicationContext(),"Registered successfully!\nLogin",Toast.LENGTH_LONG);
                    toast.show();
                }else if(!Email_Validation(entered_email.getText().toString())){
                    Toast toast = Toast.makeText(getApplicationContext(),"Please provide the right email format",Toast.LENGTH_LONG);
                    toast.show();
                }else if(!Confirm_Password(entered_password.getText().toString(), re_entered_password.getText().toString())){
                    Toast toast = Toast.makeText(getApplicationContext(),"Confirm password to Sign Up",Toast.LENGTH_LONG);
                    toast.show();
                }else if(!terms_check.isChecked()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Check Terms and Condition to proceed",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        signup_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    public boolean Email_Validation(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean Password_Validation(String password) {
        System.out.println("p =>> "+password);
        TextView check_8_char = findViewById(R.id.check_8_char);
        TextView check_uppercase = findViewById(R.id.check_uppercase);
        TextView check_lowercase = findViewById(R.id.check_lowercase);
        TextView check_number = findViewById(R.id.check_number);
        TextView check_special_char = findViewById(R.id.check_special_char);

        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        boolean isAtLeast8   = password.length() >= 8;
        boolean hasSpecial   = !password.matches("[A-Za-z0-9 ]*");//Checks at least one char is not alpha numeric
        boolean hasNumber    = password.matches(".*\\d.*");

        if(hasUppercase){
            check_uppercase.setTextColor(Color.parseColor("#71EFA3"));
        }else{
            check_uppercase.setTextColor(Color.parseColor("#FF616D"));
        }

        if(hasLowercase){
            check_lowercase.setTextColor(Color.parseColor("#71EFA3"));
        }else{
            check_lowercase.setTextColor(Color.parseColor("#FF616D"));
        }

        if(hasNumber){
            check_number.setTextColor(Color.parseColor("#71EFA3"));
        }else{
            check_number.setTextColor(Color.parseColor("#FF616D"));
        }

        if(hasSpecial){
            check_special_char.setTextColor(Color.parseColor("#71EFA3"));
        }else{
            check_special_char.setTextColor(Color.parseColor("#FF616D"));
        }

        if(isAtLeast8){
            check_8_char.setTextColor(Color.parseColor("#71EFA3"));
        }else{
            check_8_char.setTextColor(Color.parseColor("#FF616D"));
        }

        return hasUppercase && hasLowercase && hasNumber && hasSpecial && isAtLeast8;

    }

    public boolean Confirm_Password(String pass, String conf_pass){
        return pass.equals(conf_pass);
    }

}