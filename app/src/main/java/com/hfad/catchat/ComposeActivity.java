package com.hfad.catchat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComposeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onSend(View view){
        new UpdateMailTask().execute();
    }

    private class UpdateMailTask extends AsyncTask<Void,Void,Boolean>{
        private ContentValues mailValues;
        private String email;
        private String sub;
        private String body;

        @Override
        protected void onPreExecute(){
            EditText mailId = findViewById(R.id.to_address);
            email= mailId.getText().toString();
            EditText subject = findViewById(R.id.subject);
            sub = subject.getText().toString();
            EditText message = findViewById(R.id.message);
            body = message.getText().toString();

            mailValues = new ContentValues();
            mailValues.put("EMAILID",email);
            mailValues.put("SUBJECT",sub);
            mailValues.put("MESSAGE",body);
        }

        @Override
        protected Boolean doInBackground(Void... params){
            SQLiteOpenHelper dbHelper = new EmailDatabaseHelper(ComposeActivity.this);
            try{
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.insert("SENTMAIL",null,mailValues);
                db.close();
                return true;
            }catch (SQLException e){
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success){
            if(!success){
                Toast toast = Toast.makeText(ComposeActivity.this,"Database unavailable",Toast.LENGTH_SHORT);
                toast.show();
            }
            String uriText = "mailto:"+email+"?subject=" + Uri.encode(sub)+"&body=" + Uri.encode(body);
            Uri uri = Uri.parse(uriText);
            Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
}
