package com.example.contactdatabaseapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    ImageView contactImageView;
    TextView contactName, contactDOB, contactEmail;
    public ContactViewHolder(View itemView) {
        super(itemView);

        contactImageView = itemView.findViewById(R.id.contact_imageView);
        contactName = itemView.findViewById(R.id.contact_name_view);
        contactDOB = itemView.findViewById(R.id.contact_dob_view);
        contactEmail = itemView.findViewById(R.id.contact_email_view);
    }
}
