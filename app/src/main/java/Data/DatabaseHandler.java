package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Utils.util;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, util.DATABASE_NAME, null, util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + util.TABLE_NAME + "("
                + util.KEY_ID + "INTEGER PRIMARY KEY," + util.KEY_NAME + "TEXT,"
                + util.KEY_PHONE_NUMBER + "TEXT" + ")";

        db.execSQL(CREATE_CONTACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //dropping is deleting the table
        db.execSQL("DROP TABLE IF EXISTS " + util.TABLE_NAME);
        //create table again
        onCreate(db);

    }

       //add contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(util.KEY_NAME, contact.getName());
        values.put(util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        //insert a row
        db.insert(util.TABLE_NAME, null, values);
        db.close();
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(util.TABLE_NAME, new String[]{util.KEY_ID, util.KEY_NAME, util.KEY_PHONE_NUMBER}, util.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
            return contact;
        }

        public List<Contact> getAllContact () {

            SQLiteDatabase db = this.getReadableDatabase();
            List<Contact> contactList = new ArrayList<>();
            //selecting all contact
            String selectAll = "SELECT * FROM " + util.TABLE_NAME;

            Cursor cursor = db.rawQuery(selectAll, null);

            if (cursor.moveToFirst()) {
                do {
                    Contact contact = new Contact();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setPhoneNumber(cursor.getString(2));
                    //add contact object

                    contactList.add(contact);

                } while (cursor.moveToNext());
            }
            return contactList;

        }

    }