package com.example.myapplicationsplash;

import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText email;
    EditText password;

    private static final Pattern Password_Pattern =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //al menos un número
                    "(?=.*[a-z])" +         //al menos 1 minúscula
                    "(?=.*[A-Z])" +         //al menos 1 mayúscula
                    "(?=.*[a-zA-Z])" +      //cualquier letra
                    //"(?=.*[@#$%^&+=])" +    //al menos un caracter especial
                    "(?=\\S+$)" +           //sin espacios
                    ".{6,}" +               //al menos 6 caracteres
                    "$");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //ejemplo de toast
        //  Toast.makeText(getApplicationContext(),"Hola Juan",Toast.LENGTH_SHORT).show();

        button = findViewById(R.id.buttonLogin);

        email = findViewById(R.id.emailText);
        //email.getText().toString();

        password = findViewById(R.id.passwordText);
        //password.getText().toString();

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
                validateEmailAdress(email);
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

                validatePassword(password);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
        private boolean validateEmailAdress(EditText email){
            String emailInput = email.getText().toString();

            if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){

               //Toast.makeText(getApplicationContext(), "Email correcto ", Toast.LENGTH_SHORT).show();
                return true;

            }else{
                email.setError("Ingrese un correo válido");
                return false;
            }

        }

        private boolean validatePassword(EditText password){

        String passwordInput = password.getText().toString().trim();

        if (!Password_Pattern.matcher(passwordInput).matches()) {
                password.setError("La contraseña es muy débil");
                return false;
            } else {
            //Toast.makeText(getApplicationContext(), "Password segura", Toast.LENGTH_SHORT).show();
            button.setEnabled(true);
            return true;
            }
        }


}
