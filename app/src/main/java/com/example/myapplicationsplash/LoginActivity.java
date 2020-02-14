package com.example.myapplicationsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


       //ejemplo de toast
      //  Toast.makeText(getApplicationContext(),"Hola Juan",Toast.LENGTH_SHORT).show();

        button = findViewById(R.id.buttonLogin);

        email= findViewById(R.id.emailText);
        //email.getText().toString();

        password = findViewById(R.id.passwordText);
        //password.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Email: "+ email.getText().toString() + "  Pasword "+password.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
