package com.hfad.catchat;


import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.zip.Inflater;


public class InboxFragment extends Fragment {

    private SQLiteDatabase db;
    private DbRecyclerAdapter adapter;

    public InboxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView inboxRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_inbox,container,false);

        final SQLiteOpenHelper dbHelper = new EmailDatabaseHelper(getActivity());
        try {
            db = dbHelper.getReadableDatabase();
            Cursor cursor = getAllItems();
            adapter = new DbRecyclerAdapter(getActivity(), cursor);
            inboxRecycler.setAdapter(adapter);
        }catch (SQLException e){
            Toast toast =Toast.makeText(getActivity(),"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        inboxRecycler.setLayoutManager(layoutManager);

       adapter.setListner(new DbRecyclerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(),ViewMailActivity.class);
                intent.putExtra(ViewMailActivity.EXTRA_MAIL_ID,position);
                intent.putExtra(ViewMailActivity.EXTRA_TABLE_NAME,"RECEIVEDMAIL");
                getActivity().startActivity(intent);

            }
        });


        return inboxRecycler;
    }

    private Cursor getAllItems() {
        return db.query("RECEIVEDMAIL", new String[]{"_id","EMAILID","SUBJECT","MESSAGE","TIME"}, null, null, null, null,"TIME DESC");
    }


}
