package com.example.myapplicationsplash.Services;

import com.example.myapplicationsplash.Models.User;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserService {

    private Realm realm;
    public UserService(Realm realm){
        this.realm = realm;
    }

    //create user
    public void createUser(int id, String name,String surname,String rut,String email, String password){

        //para comenzar a trabajar con la bd
        realm.beginTransaction();

        User user = realm.createObject(User.class,id);
        user.setName(name);
        user.setSurname(surname);
        user.setRut(rut);
        user.setEmail(email);
        user.setPassword(password);

        //para terminar todas las operaciones
        realm.commitTransaction();

    }

    //get users
    public User[] getUsers(){

        RealmResults<User> results = realm.where(User.class).findAll();
        return results.toArray(new User[results.size()]);

    }

    //get user
    public User getUser(int id){

        User usuario = realm.where(User.class).equalTo("id",id).findFirst();

        return usuario;
    }

    public void updateUserName(User user, String name){

        realm.beginTransaction();

        user.setName(name);

        realm.commitTransaction();

    }

    public void deleteUser(int id){

        User user = getUser(id);

        realm.beginTransaction();
        user.deleteFromRealm();
        realm.commitTransaction();

    }


}
