package com.mdev.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapterClass extends RecyclerView.Adapter<ContactAdapterClass.ViewHolder> {

    List<ContactModelClass> contact;
    Context context;
    DatabaseClass databaseClass;

    public ContactAdapterClass(List<ContactModelClass> contact, Context context) {
        this.contact = contact;
        this.context = context;
        databaseClass = new DatabaseClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_updatedelete_contact,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final ContactModelClass contactModelClass = contact.get(position);

        holder.textViewID.setText(Integer.toString(contactModelClass.getId()));
        holder.editText_Name.setText(contactModelClass.getName());
        holder.editText_Contact.setText(contactModelClass.getContact());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.editText_Name.getText().toString();
                String stringContact = holder.editText_Contact.getText().toString();

                databaseClass.updateContact(new ContactModelClass(contactModelClass.getId(),stringName,stringContact));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseClass.deleteContact(contactModelClass.getId());
                contact.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_Name;
        EditText editText_Contact;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_Contact = itemView.findViewById(R.id.edittext_contact);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}
