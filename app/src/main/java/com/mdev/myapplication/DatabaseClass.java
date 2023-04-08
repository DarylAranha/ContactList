package com.mdev.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact_database.db";
    private static final String TABLE_NAME = "CONTACT";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CONTACT = "contact";
    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+CONTACT+" TEXT NOT NULL);";


    public DatabaseClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    public void InsertContact(ContactModelClass contactModelClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseClass.NAME, contactModelClass.getName());
        contentValues.put(DatabaseClass.CONTACT, contactModelClass.getContact());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseClass.TABLE_NAME, null,contentValues);

    }

    public void updateContact(ContactModelClass contactModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseClass.NAME,contactModelClass.getName());
        contentValues.put(DatabaseClass.CONTACT,contactModelClass.getContact());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(contactModelClass.getId())});
    }

    public void deleteContact(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }


    public List<ContactModelClass> getContactList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<ContactModelClass> storeContact = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String contact = cursor.getString(2);
                storeContact.add(new ContactModelClass(id,name,contact));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeContact;
    }


}
