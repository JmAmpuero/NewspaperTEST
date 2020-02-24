package com.example.myapplicationsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplicationsplash.Services.UserService;
import com.example.myapplicationsplash.Models.User;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class CreateAccountActivity extends AppCompatActivity {

   private Button button;
   TextInputLayout emailTextInputLayout;
   TextInputLayout passwordTextInputLayout;
   EditText passwordCreateUserEditText;
   EditText emailCreateUserEditText;
   Toolbar toolbar;

   private EditText userName,userSurname,userRut,userEmail,userPassword;
           Realm realm;

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
        setContentView(R.layout.activity_create_account);
        realm = Realm.getDefaultInstance();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Crear cuenta");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        button = findViewById(R.id.buttonCreateUser);
        userName = findViewById(R.id.nameCreateUser);
        userSurname = findViewById(R.id.surnameCreateUser);
        userRut = findViewById(R.id.rutCreateUser);
        userEmail = findViewById(R.id.emailCreateUser);
        userPassword = findViewById(R.id.passwordCreateUser);

        emailTextInputLayout = findViewById(R.id.textInputLayoutUserEmail);
        passwordTextInputLayout =findViewById(R.id.textInputLayoutUserPassword);

        emailCreateUserEditText =findViewById(R.id.emailCreateUser);
        passwordCreateUserEditText=findViewById(R.id.passwordCreateUser);

        emailCreateUserEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateEmail();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordCreateUserEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validatePass();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData(){


       realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Number maxId = bgRealm.where(User.class).max("id");

                int newId = (maxId == null) ? 1 :maxId.intValue()+1;

             User user = bgRealm.createObject(User.class,newId);

                user.setName(userName.getText().toString());
                user.setSurname(userSurname.getText().toString());
                user.setRut(userRut.getText().toString());
                user.setEmail(userEmail.getText().toString());
                user.setPassword(userPassword.getText().toString());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

                Toast.makeText(CreateAccountActivity.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
               openSuccessfullCreateUserActivity();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("ERROR",error.getMessage().toString());
                Toast.makeText(CreateAccountActivity.this, "Usuario no creado", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void openSuccessfullCreateUserActivity () {

        Intent intent = new Intent(this, SuccessfullCreateUserActivity.class);
        startActivity(intent);
    }

    private void validateEmail(){

        String email= emailTextInputLayout.getEditText().getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            emailTextInputLayout.setError("Ingrese un correo válido");
            emailTextInputLayout.setErrorIconDrawable(null);

        }else{

            emailTextInputLayout.setErrorEnabled(false);
        }
    }

    private void validatePass(){

        String pass = passwordTextInputLayout.getEditText().getText().toString().trim();

        if (!Password_Pattern.matcher(pass).matches()){
            passwordTextInputLayout.setError("La contraseña es muy débil");
            passwordTextInputLayout.setErrorIconDrawable(null);
            button.setEnabled(false);

        }else{
            passwordTextInputLayout.setErrorEnabled(false);
            button.setEnabled(true);

        }

    }

    public void openMainActivity(){

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}

