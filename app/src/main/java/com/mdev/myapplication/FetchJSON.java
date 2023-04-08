package com.mdev.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class FetchJSON  extends AppCompatActivity {

    Button button1;
    public static TextView textView1;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsondata);

        button1 = (Button)findViewById(R.id.button);
        textView1 = (TextView)findViewById(R.id.fetchedData);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData process = new fetchData();
                process.execute();

                //--- by ead local file saved in res ---
                //try {
                //    // Open the file
                //    InputStream inputStream = getResources().openRawResource(R.raw.contactdatabase);
                //    // Read the file into a string
                //    byte[] buffer = new byte[inputStream.available()];
                //    inputStream.read(buffer);
                //    String jsonString = new String(buffer, "UTF-8");
                //    // Set the string in a TextView
                //    TextView textView = findViewById(R.id.fetchedData);
                //    textView.setText(jsonString);
                //   // Close the file
                //    inputStream.close();
                //} catch (IOException e) {
                //    e.printStackTrace();
                //}
            }
        });

    }
}
