package com.example.ygraine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final Context context;

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
        this.context=context;
    }
    static String username,password,email,name;

    private static final String DATABASE_NAME = "MY_DATABASE";
    private static final int DATABASE_VERSION = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table user(username primary key, password text, name text, email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    //inserting in databse
    public boolean insert (String username,String password, String name, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user",null,contentValues);
        if (ins == -1) return false;
        else return true;
    }
    //checking if user name exist
    public Boolean checkUserName (String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=?", new String[]{username});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    //checking the username and password
    public Boolean UsernamePassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=? and password=?", new String[]{username,password});
        if(cursor.getCount()>0) return  true;
        else return false;
    }

    public boolean deleteAccount(String strUser){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("delete from user where username=?", new String[]{strUser});
        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }


//    public void deleteUser(String id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        // delete user record by id
//        long s = db.delete("user", username + " = ?",new String[]{id});
//        if(s<= 0){
//            Toast.makeText(context, "Deletion Unsuccessful", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Toast.makeText(context, "Deletion Successful", Toast.LENGTH_SHORT).show();
//        }
//        db.close();
//    }

}
