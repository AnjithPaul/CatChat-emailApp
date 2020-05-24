package com.hfad.catchat;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class DbRecyclerAdapter extends RecyclerView.Adapter<DbRecyclerAdapter.ViewHolder> {

    private Context context;
    private Cursor cursor;

    public DbRecyclerAdapter(Context context ,Cursor cursor){
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public DbRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){

        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_email,parent,false);
        return new ViewHolder(cardView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cv;

        public ViewHolder(CardView v){
            super(v);
            cv=v;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position){
        if(!cursor.moveToPosition(position)){
            return;
        }

        String name = cursor.getString(cursor.getColumnIndex("EMAILID"));
       // String subject = cursor.getString(cursor.getColumnIndex("SUBJECT"));
        String message = cursor.getString(cursor.getColumnIndex("MESSAGE"));


        CardView cardView = holder.cv;
        TextView title = cardView.findViewById(R.id.card_title);
        TextView content = cardView.findViewById(R.id.card_content);
        ImageView imageView =cardView.findViewById(R.id.user_dp);


        title.setText(name);
        content.setText(message);
        imageView.setImageResource(R.drawable.default_dp);

    }

    @Override
    public int getItemCount(){
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(cursor!=null){
            cursor.close();
        }
        cursor = newCursor;

        if(newCursor!=null){
            notifyDataSetChanged();
        }
    }

}
