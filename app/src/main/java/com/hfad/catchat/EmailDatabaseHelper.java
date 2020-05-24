package com.hfad.catchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmailDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME ="email";
    private static final int DB_VERSION=1;

    EmailDatabaseHelper (Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db,0,DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        updateMyDatabase(db,oldVersion,newVersion);
    }

    public static void insertMail(SQLiteDatabase db,String emailId,String subject,String message){
        ContentValues mailValues = new ContentValues();
        mailValues.put("EMAILID",emailId);
        mailValues.put("SUBJECT",subject);
        mailValues.put("MESSAGE",message);
        db.insert("SENTMAIL",null,mailValues);
    }

    private void updateMyDatabase(SQLiteDatabase db,int oldVersion, int newVersion){

        if(oldVersion<1){
            db.execSQL("DROP TABLE IF EXISTS SENTMAIL");
            db.execSQL("CREATE TABLE SENTMAIL"+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,EMAILID TEXT,SUBJECT TEXT,MESSAGE TEXT,TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
        }
    }
}
