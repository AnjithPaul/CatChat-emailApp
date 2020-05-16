package com.hfad.catchat;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class ViewMailActivity extends AppCompatActivity {

    public static final String EXTRA_MAIL_ID = "mailId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_mail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int mailId= (Integer)getIntent().getExtras().get(EXTRA_MAIL_ID);
        String name = email.emails[mailId].getName();
        String mail = email.emails[mailId].getMessage();
        TextView email = (TextView)findViewById(R.id.user_mail);
        TextView message = (TextView)findViewById(R.id.mail);
        email.setText(name);
        message.setText(mail);


    }
}
