package com.hfad.catchat;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolder> {

    private String[] names;
    private String[] messages;
    private int[] imageIds;

    public EmailAdapter( String [] names, String[] messages, int[] imageIds){
        this.names = names;
        this.messages = messages;
        this.imageIds = imageIds;
    }

    @Override
    public int getItemCount(){
        return names.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cv;

        public ViewHolder(CardView v){
            super(v);
            cv=v;
        }
    }

    @Override
    public EmailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){

        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_email,parent,false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        CardView cardView = holder.cv;
        ImageView imageView = cardView.findViewById(R.id.user_dp);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(),imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(names[position]);
        TextView title = cardView.findViewById(R.id.card_title);
        title.setText(names[position]);
        TextView content = cardView.findViewById(R.id.card_content);
        content.setText(messages[position]);
    }
}
