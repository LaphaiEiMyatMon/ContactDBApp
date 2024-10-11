package com.example.contactdatabaseapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    Context context;
    ArrayList<Contact> contactArrayList;

    public ContactRecyclerViewAdapter(Context context, ArrayList<Contact> contactArrayList) {

        this.contactArrayList = contactArrayList;
        this.context = context;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_layout, parent, false);
        ContactViewHolder viewHolder = new ContactViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        Contact contact = contactArrayList.get(position);
        holder.contactImageView.setImageResource(contact.getImageID());
        holder.contactName.setText(contact.getName());
        holder.contactDOB.setText(contact.getDob());
        holder.contactEmail.setText(contact.getEmail());
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }
}

