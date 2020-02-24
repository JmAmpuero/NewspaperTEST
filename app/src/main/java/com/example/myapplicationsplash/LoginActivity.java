package com.example.myapplicationsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText email;
    EditText password;
    TextInputLayout textInputLayoutPassword;
    TextInputLayout textInputLayoutEmail;

    Toolbar toolbar;

    private static final Pattern Password_Pattern =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //al menos un número
                    "(?=.*[a-z])" +         //al menos 1 minúscula
                    "(?=.*[A-Z])" +         //al menos 1 mayúscula
                    "(?=\\S+$)" +           //sin espacios
                    ".{6,}" +               //al menos 6 caracteres
                    "$");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ingresar");

       toolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               openMainActivity();
           }
       });

        textInputLayoutPassword = findViewById(R.id.textInputLayout3);
        textInputLayoutEmail = findViewById(R.id.textInputLayout1);

        button = findViewById(R.id.buttonLogin);

        email = findViewById(R.id.emailText);

        password = findViewById(R.id.passwordText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Email: " + email.getText().toString() + "  Pasword " + password.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //validateEmailAdress(email);
                validateEmail();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //validatePassword(password);
                validatePass();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
        private boolean validateEmailAdress(EditText email){
            String emailInput = email.getText().toString();

            if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){

                return true;

            }else{
                email.setError("Ingrese un correo válido");
                return false;
            }

        }

        private void validateEmail(){

        String email= textInputLayoutEmail.getEditText().getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            textInputLayoutEmail.setError("Ingrese un correo válido");
            textInputLayoutEmail.setErrorIconDrawable(null);

        }else{

            textInputLayoutEmail.setErrorEnabled(false);
        }
        }

        private boolean validatePassword(EditText password){

        String passwordInput = password.getText().toString().trim();

        if (!Password_Pattern.matcher(passwordInput).matches()) {
                password.setError("La contraseña es muy débil");
                return false;
            } else {
            button.setEnabled(true);
            return true;
            }
        }

        private void validatePass(){

        String pass = textInputLayoutPassword.getEditText().getText().toString().trim();

        if (!Password_Pattern.matcher(pass).matches()){
            textInputLayoutPassword.setError("La contraseña es muy débil");
            textInputLayoutPassword.setErrorIconDrawable(null);
            button.setEnabled(false);

        }else{
           textInputLayoutPassword.setErrorEnabled(false);
            button.setEnabled(true);

        }

        }

    public void openMainActivity(){

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
