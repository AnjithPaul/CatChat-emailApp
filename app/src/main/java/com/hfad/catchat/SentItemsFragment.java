package com.hfad.catchat;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SentItemsFragment extends Fragment {


    public SentItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView sentItemsRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_sent_items,container,false);

        SQLiteOpenHelper dbHelper = new EmailDatabaseHelper(getActivity());
        try {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query("SENTMAIL", new String[]{"_id","EMAILID","SUBJECT","MESSAGE","TIME"}, null, null, null, null,"TIME DESC");
            DbRecyclerAdapter adapter = new DbRecyclerAdapter(getActivity(), cursor);
            sentItemsRecycler.setAdapter(adapter);
            Log.v("try is clear ","OK");
        }catch (SQLException e){
            Toast toast =Toast.makeText(getActivity(),"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            sentItemsRecycler.setLayoutManager(layoutManager);

            return sentItemsRecycler;

    }

}
