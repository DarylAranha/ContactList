package com.mdev.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileOutputStream;
import java.io.InputStream;

public class AddContact extends AppCompatActivity {
    EditText txt_name,txt_contact;
    Button btn_add,btn_view,btn_data,btn_fetchedJSON;

    public boolean validatePhoneNumber(String phoneNumber) {
        String regex = "^(\\+\\d{1,2}\\s)?(\\d{10,12})$";
        return phoneNumber.matches(regex);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);

        txt_name = findViewById(R.id.txt_name);
        txt_contact = findViewById(R.id.txt_contact);
        btn_add = findViewById(R.id.btn_add);
        btn_view = findViewById(R.id.btn_view);
        btn_data = findViewById(R.id.btn_data);
        btn_fetchedJSON= findViewById(R.id.btn_fetchedJSON);


        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String stringName = txt_name.getText().toString();
                String stringContact = txt_contact.getText().toString();


                String phoneNumber = txt_contact.getText().toString();
                boolean isValid = validatePhoneNumber(phoneNumber);
                String result = isValid ? "Valid phone number" : "Invalid phone number";

                if(result == "Invalid phone number"){
                    Toast.makeText(AddContact.this, "Invalid phone number", Toast.LENGTH_SHORT).show();
                }
                else if (stringName.length() <= 0 || stringContact.length() <= 0) {
                    Toast.makeText(AddContact.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseClass databaseClass = new DatabaseClass(AddContact.this);
                    ContactModelClass contactModelClass = new ContactModelClass(stringName, stringContact);
                    databaseClass.InsertContact(contactModelClass);

                    Toast.makeText(AddContact.this, "Contact added Successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());

                    //post JSON data

                        String fileName = "contactdatabase.json";
                        String data = stringName + "," + stringContact;

                        try {
                            // Open the file for writing
                            FileOutputStream fos = openFileOutput(fileName, Context.MODE_APPEND);

                            // Write the data to the file
                            fos.write(data.getBytes());

                            // Close the file
                            fos.close();

                            Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error saving data", Toast.LENGTH_SHORT).show();
                        }


                    //JSONObject json = new JSONObject();
                    //try {
                    //    json.put("name", stringName);
                    //    json.put("contact", stringContact);
                    //    // Add any other data you want to include in the JSON object
                    //} catch (JSONException e) {
                    //    e.printStackTrace();
                    //}
                    //new PostJsonData().setInstance(json.toString()).execute();
                    //









                    //code added for web service//---- not working ----
                    // Define the URL to your server endpoint
                    //String url2 = "http://example.com/api/data";

                    // Create a RequestParams object to hold the data you want to post
                    //RequestParams params = new RequestParams();
                    //params.put("name", stringName);
                    //params.put("contact", stringContact);


                    //new AsyncHttpClient().get(url2, new AsyncHttpResponseHandler() {
                    //    @Override
                      //  public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            // Display a success message to the user
                        //    Toast.makeText(AddContact.this, "Data posted successfully", Toast.LENGTH_SHORT).show();
                        //}

                        //@Override
                        //public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            // Display an error message to the user
                          //  Toast.makeText(AddContact.this, "Failed to post data", Toast.LENGTH_SHORT).show();
                        //}

                    //});
                    //code end here//for web service//


                }
            }
        });


        btn_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddContact.this, ViewContactList.class);
                startActivity(intent);
            }
        });

        btn_data.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddContact.this, CallWebService.class);
                startActivity(intent);
            }
        });

        btn_fetchedJSON.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddContact.this, FetchJSON.class);
                startActivity(intent);
            }
        });
    }
}
