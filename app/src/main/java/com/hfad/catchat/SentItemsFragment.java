package com.hfad.catchat;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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

    private SQLiteDatabase db;
    private DbRecyclerAdapter adapter;

    public SentItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView sentItemsRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_sent_items,container,false);

        final SQLiteOpenHelper dbHelper = new EmailDatabaseHelper(getActivity());
        try {
            db = dbHelper.getReadableDatabase();
            Cursor cursor = getAllItems();
            adapter = new DbRecyclerAdapter(getActivity(), cursor);
            sentItemsRecycler.setAdapter(adapter);
            Log.v("try is clear ","OK");
        }catch (SQLException e){
            Toast toast =Toast.makeText(getActivity(),"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            sentItemsRecycler.setLayoutManager(layoutManager);

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    removeItem((long) viewHolder.itemView.getTag());
                }
            }).attachToRecyclerView(sentItemsRecycler);
            return sentItemsRecycler;

    }

    private void removeItem(long id){
        db.delete("SENTMAIL","_id =?",new String[]{Long.toString(id)});
        adapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems() {
        return db.query("SENTMAIL", new String[]{"_id","EMAILID","SUBJECT","MESSAGE","TIME"}, null, null, null, null,"TIME DESC");
    }



}
