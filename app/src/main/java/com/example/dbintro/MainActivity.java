package com.example.dbintro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Date;
import java.util.List;

import Data.DatabaseHandler;
import Model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        Log.d("Insert:","Inserting...");
        db.addContact(new Contact("Paul","4586544454"));
        db.addContact(new Contact("rose","414151425"));
        db.addContact(new Contact("pop","9971033135"));
        db.addContact(new Contact("dsz","8755496568"));


        Log.d("reading","reading all contact");
        List<Contact> contactList = db.getAllContacts();

        for(Contact c: contactList){
            String log = ", ID"+ c.getId() + ", Name"+ c.getName() + ", PhoneNumber"+ c.getPhoneNumber();
            Log.d("Name", log);

        }
    }
}