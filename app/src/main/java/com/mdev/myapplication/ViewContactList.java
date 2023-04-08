package com.mdev.myapplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class ViewContactList extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewcontactlist);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseClass databaseClass = new DatabaseClass(this);
        List<ContactModelClass> contactModelClasses = databaseClass.getContactList();

        if (contactModelClasses.size() > 0){
            ContactAdapterClass contactadapterclass = new ContactAdapterClass(contactModelClasses,ViewContactList.this);
            recyclerView.setAdapter(contactadapterclass);
        }else {
            Toast.makeText(this, "There is no contact in the contact list", Toast.LENGTH_SHORT).show();
        }

    }
}
