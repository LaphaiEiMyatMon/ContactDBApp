package com.example.contactdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        recyclerView = findViewById((R.id.contact_recycler_view));
        databaseHelper = new DatabaseHelper(this);

        ArrayList<Contact> contactArrayList = databaseHelper.getAllContacts();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();
        ContactRecyclerViewAdapter adapter = new ContactRecyclerViewAdapter(this,contactArrayList);

        recyclerView.setAdapter(adapter);

    }
}