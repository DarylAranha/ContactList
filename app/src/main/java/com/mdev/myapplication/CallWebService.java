package com.mdev.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
public class CallWebService extends AppCompatActivity {

    TextView textView;
    Button Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webservice);

        textView = findViewById(R.id.textView);
        Button = findViewById(R.id.Button);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //call webservice here
                String url = "https://jsonplaceholder.typicode.com/posts/1"; //URL USED
                new AsyncHttpClient().get(url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String str= new String(responseBody);
                        textView.setText(str);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        textView.setText("Error in calling api");
                    }
                });
            }
        });
    }


}
