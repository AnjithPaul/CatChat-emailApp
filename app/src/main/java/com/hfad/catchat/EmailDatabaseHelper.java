package com.hfad.catchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EmailDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "email";
    private static final int DB_VERSION = 2;

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
        db.insert("RECEIVEDMAIL",null,mailValues);
    }

    private void updateMyDatabase(SQLiteDatabase db,int oldVersion, int newVersion){

        if(oldVersion<1){
            db.execSQL("DROP TABLE IF EXISTS SENTMAIL");
            db.execSQL("CREATE TABLE SENTMAIL"+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,EMAILID TEXT,SUBJECT TEXT,MESSAGE TEXT,TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
        }
        if(oldVersion<2){
            db.execSQL("DROP TABLE IF EXISTS RECEIVEDMAIL");
            db.execSQL("CREATE TABLE RECEIVEDMAIL"+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,EMAILID TEXT,SUBJECT TEXT,MESSAGE TEXT,TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
            insertMail(db,"stanleykubrick@gmail.com","Subject is relative","If it can be written, or thought, it can be filmed.");
            insertMail(db,"martinscorsese@gmail.com","Cinema","Cinema is a matter of what's in the frame and what's out.");
            insertMail(db,"alfredhitchcock@gmail.com","A secret","There is no terror in the bang, only in the anticipation of it.");

        }
    }
}
