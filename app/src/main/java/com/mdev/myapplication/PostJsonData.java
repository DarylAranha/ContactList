package com.mdev.myapplication;

import android.os.AsyncTask;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class PostJsonData extends AsyncTask<Void, Void, Boolean> {
    private String instance;

    public PostJsonData setInstance(String instance) {
        this.instance = instance;
        return this;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {

            URL url = new URL("https://jsonkeeper.com/b/64JJ");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            Log.d("MyApp", "Connection established: " + connection.getResponseCode());
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            outputStreamWriter.write(instance);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            // Data was successfully posted
        } else {
            // There was an error while posting the data
        }
    }
}
