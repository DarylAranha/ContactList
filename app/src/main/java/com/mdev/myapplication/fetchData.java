package com.mdev.myapplication;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class fetchData extends AsyncTask<Void,Void,Void> {

    String data="";
    String dataParsed = "";
    String singleParsed="";


    @Override
    protected Void doInBackground(Void... voids) {


        try{
            URL url = new URL("https://jsonkeeper.com/b/64JJ");
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();


            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });


            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line="";
            while (line!= null){
                line = bufferedReader.readLine();
                data = data +line;
            }

            JSONArray JA = new JSONArray(data);
            for (int i=0; i<JA.length(); i++){
                JSONObject JO = (JSONObject) JA.get(i);

                singleParsed = "ID: " +JO.get("id") + "\n" +
                        "Name: " +JO.get("name") + "\n" +
                        "Contact: " +JO.get("contact") + "\n\n" ;
                dataParsed = dataParsed + singleParsed;
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);
        FetchJSON.textView1.setText(this.dataParsed);
    }


}


