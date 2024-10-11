package com.example.contactdatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "DBContact";
    private static final String TABLE_NAME = "tb_contact";
    public static final String CONTACT_ID = "_id"; //cursor adapter//primary key
    public static final String CONTACT_NAME = "name";
    public static final String CONTACT_DOB = "dob";
    public static final String CONTACT_EMAIL = "email";
    public static final String IMAGE_ID = "imageID";

    private SQLiteDatabase database;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCreate = "create table " + TABLE_NAME + "(" + CONTACT_ID + " integer primary key autoincrement,"
                + CONTACT_NAME + " text, " + CONTACT_DOB + " text," + CONTACT_EMAIL + " text, " +IMAGE_ID + " integer)";
        db.execSQL(tableCreate);


    }//onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "drop table if exists " + TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }//onUpgrade

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;"); //foreign key
    }

    public long saveContact(Contact contact) {

        ContentValues values = new ContentValues();
        values.put(CONTACT_NAME, contact.getName());
        values.put(CONTACT_DOB, contact.getDob());
        values.put(CONTACT_EMAIL, contact.getEmail());
        values.put(IMAGE_ID, contact.getImageID());

        long contact_id = database.insertOrThrow(TABLE_NAME, null, values);

        return contact_id;
    }//end of saveContact


    public ArrayList<Contact> getAllContacts() {
        database = getReadableDatabase();

        Cursor results = database.query(TABLE_NAME, new String[]{CONTACT_ID, CONTACT_NAME, CONTACT_DOB, CONTACT_EMAIL, IMAGE_ID},
                null, null, null, null, null);

        //int no_of_rows = results.getCount();

        ArrayList<Contact> contactArrayList = new ArrayList<>();

        results.moveToFirst();

        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);
            String dob = results.getString(2);
            String email = results.getString(3);
            int imageID = results.getInt(4);

            Contact contact = new Contact(id, name, dob, email, imageID);
            contactArrayList.add(contact);
            results.moveToNext();//next row
        } //end of the loop

        return contactArrayList;
    }//end of getAllContacts

}