package com.tifinnearme.priteshpatel.tifinnearme;

import android.app.Activity;
import android.media.AsyncPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by PRITESH on 03-04-2015.
 */
public class Web_Service extends Activity {
    TextView tv;
    HttpClient client;
    JSONObject json;
    String place;
    static final String URL="http://api.openweathermap.org/data/2.5/weather?q=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loaddata);
        tv=(TextView)findViewById(R.id.loadData);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().detectAll().build());




        /*GetDataEx getDataEx=new GetDataEx();
        try {
            String returned_data=getDataEx.getData();
            tv.setText(returned_data);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

    }
    public Web_Service(String place){

        this.place=place;
        try {
            getPlaceCountry(place);
            new ReadData().execute("country");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public JSONObject getPlaceCountry(String place) throws ClientProtocolException,IOException,JSONException{
        //this.place=place;
        client=new DefaultHttpClient();
        StringBuilder url=new StringBuilder(URL);//Build string for JSON code
        url.append(place);
        HttpGet request=new HttpGet(url.toString());//set a request for following url
        HttpResponse response=client.execute(request);
        int status=response.getStatusLine().getStatusCode();//Get status for response 1
        if(status==200)
        {
            HttpEntity entity=response.getEntity();
            String data= EntityUtils.toString(entity);
            JSONObject job=new JSONObject(data);
            JSONObject first_ob=job.getJSONObject("sys");
            return  first_ob;


        }
        else return null;

    }
   public class ReadData extends AsyncTask<String,Integer,String>{
       @Override
       protected String doInBackground(String... params) {
           try {
               json=getPlaceCountry(place);
               //String con=json.getString(params[0]);
               return json.getString(params[0]);
           } catch (IOException e) {
               e.printStackTrace();
           } catch (JSONException e) {
               e.printStackTrace();
           }
           return "nothing found";
       }

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
       }

       @Override
       protected void onPostExecute(String s) {
           tv.setText(s);
       }
   }
}
