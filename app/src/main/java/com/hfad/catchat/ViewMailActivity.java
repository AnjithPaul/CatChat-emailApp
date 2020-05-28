package com.hfad.catchat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.Random;

public class ViewMailActivity extends AppCompatActivity {

    public static final String EXTRA_MAIL_ID = "mailId";
    public static final String EXTRA_TABLE_NAME ="tableName";
    public static Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        //int n = email.images.length;
        int p = random.nextInt(10);

        int mailId= (Integer)getIntent().getExtras().get(EXTRA_MAIL_ID);
        String tableName = (String)getIntent().getExtras().get(EXTRA_TABLE_NAME);

        SQLiteOpenHelper dbHelper = new EmailDatabaseHelper(this);
        try{
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(tableName,new String[]{"_id","EMAILID","SUBJECT","MESSAGE"},"_id=?",new String[]{Integer.toString(mailId)},null,null,null);

            if(cursor.moveToFirst()){
                String mail =cursor.getString(cursor.getColumnIndex("EMAILID"));
                String sub = cursor.getString(cursor.getColumnIndex("SUBJECT"));
                String mess = cursor.getString(cursor.getColumnIndex("MESSAGE"));

                TextView emailId =(TextView)findViewById(R.id.user_mail);
                TextView subject = (TextView)findViewById(R.id.mail_subject);
                TextView message = (TextView)findViewById(R.id.mail);
                ImageView dp =(ImageView)findViewById(R.id.imageView);


                emailId.setText(mail);
                subject.setText(sub);
                message.setText(mess);
                dp.setImageResource(email.images[p]);
            }
            cursor.close();
            db.close();
        }catch (SQLException e){
            Toast toast = Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }

    }

}
