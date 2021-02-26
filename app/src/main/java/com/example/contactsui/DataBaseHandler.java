package com.example.contactsui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 11;
    private static final String DATABASE_NAME = "contactManager";

    public DataBaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE contacts (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone_Number TEXT, image BLOB)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("phone_Number", contact.getPhoneNumber());
         values.put("image",contact.getImage());
        db.insert("contacts", null, values);
        db.close();
    }
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("contacts","id = ?",new String[] { String.valueOf(contact.getId())});
        db.close();
    }
public void selectContact(Contact contact)
{
    SQLiteDatabase db = this.getWritableDatabase();
    db.close();
}

    public int updateContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",contact.getName());
        values.put("phone_number",contact.getPhoneNumber());
       //values.put("image",contact.getImage());
        return db.update("contacts", values, "id=?", new String[] {String.valueOf(contact.getId())});
    }
    public List<Contact> getAllContact()
    {
        List<Contact> contactList  = new ArrayList<Contact>();
        String selectQuery = "SELECT * FROM contacts";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null );
        if(cursor.moveToFirst())
        {
            do {
                Contact   contact = new Contact();
                contact.setId(cursor.getInt(0));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                if(cursor.getBlob(3)!=null)
                {
                    contact.setImage(cursor.getBlob(3));
                }
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
      return contactList;
    }
Contact getContact(int id)
{
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query("contacts",new String[] {"id","name","phone_number","image"},"id=?",new String[] {String.valueOf(id)},null ,null,null,null);
    if(cursor != null)
        cursor.moveToFirst();
    Contact contact = new Contact();
    contact.setId(cursor.getInt(0));
    contact.setName(cursor.getString(1));
    contact.setPhoneNumber(cursor.getString(2));
    //contact.setImage(cursor.getBlob(3));
    return contact;
}
    public  int getContactsCount() {
        String countQuery = "SELECT * FROM contacts";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        int nb = cursor.getCount();
        cursor.close();
        return nb;

    }
// fin de programme
}
