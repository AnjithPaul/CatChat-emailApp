package com.hfad.catchat;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.zip.Inflater;


public class InboxFragment extends Fragment {


    public InboxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView inboxRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_inbox,container,false);

        String[] emailNames = new String[email.emails.length];
        for(int i=0;i<email.emails.length;i++){
            emailNames[i]=email.emails[i].getName();
        }

        String[] emailContent = new String[email.emails.length];
        for(int i=0;i<email.emails.length;i++){
            emailContent[i]=email.emails[i].getMessage();
        }

        int[] userImages = new int[email.emails.length];
        for(int i=0;i<email.emails.length;i++){
            userImages[i] = email.emails[i].getImageResourceId();
        }

        EmailAdapter adapter = new EmailAdapter(emailNames,emailContent,userImages);
        inboxRecycler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        inboxRecycler.setLayoutManager(layoutManager);

        adapter.setListner(new EmailAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(),ViewMailActivity.class);
                intent.putExtra(ViewMailActivity.EXTRA_MAIL_ID,position);
                getActivity().startActivity(intent);

            }
        });
        return inboxRecycler;
    }

}
