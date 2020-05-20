package com.hfad.catchat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ComposeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onSend(View view){
        EditText mailId = findViewById(R.id.to_address);
        String email= mailId.getText().toString();
        EditText subject = findViewById(R.id.subject);
        String sub = subject.getText().toString();
        EditText message = findViewById(R.id.message);
        String body = message.getText().toString();



        String uriText = "mailto:"+email+"?subject=" + Uri.encode(sub)+"&body=" + Uri.encode(body);
        Uri uri = Uri.parse(uriText);
        Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
