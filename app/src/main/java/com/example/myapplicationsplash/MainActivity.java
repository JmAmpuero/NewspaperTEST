package com.example.myapplicationsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplicationsplash.Services.UserService;
import com.example.myapplicationsplash.Models.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
//Se inicia realm, se estructura la BD, se setea la configuracion con el objeto realmConfiguration, su nombre y su schema
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("TestDB1").schemaVersion(1).build();
        Realm.setDefaultConfiguration(realmConfiguration);

       UserService userService = new UserService(Realm.getDefaultInstance());
      //  userService.createUser(1,"Juan","juan@juan.cl","A1qwert");

        User[] usuarios = userService.getUsers();

        for( int i =0; i< usuarios.length;i++){

            Log.d("RESULTADOS",usuarios[i].getName());
        }*/
/*
        User user = userService.getUser(1);
        Log.d("Un usuario",user.getName());

        userService.updateUserName(user,"John");
        Log.d("Nuevo nombre",user.getName());

        userService.deleteUser(user.getId());

        */


        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

       button = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateAccountActivity();
            }
        });
    }

    public void openLoginActivity(){

        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void openCreateAccountActivity(){

        Intent intent = new Intent(this,CreateAccountActivity.class);
        startActivity(intent);
    }



}
