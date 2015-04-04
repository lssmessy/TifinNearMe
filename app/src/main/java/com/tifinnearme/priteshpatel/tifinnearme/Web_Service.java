package com.tifinnearme.priteshpatel.tifinnearme;

import android.app.Activity;
import android.app.ProgressDialog;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by PRITESH on 03-04-2015.
 */
public class Web_Service extends Activity {
    TextView tv,msg,contry_text,msg_text;
    HttpClient client;
    JSONObject json;
    String place,result,message;
    //ArrayList<String> alist;
    //int flag=0;
    static final String URL="http://api.openweathermap.org/data/2.5/weather?q=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loaddata);
        Bundle b=getIntent().getExtras();
        place=b.getString("textfield_data");
        this.place=place;

        tv=(TextView)findViewById(R.id.loadData);
        msg=(TextView)findViewById(R.id.msgView);
        contry_text=(TextView)findViewById(R.id.countryTeext);
        msg_text=(TextView)findViewById(R.id.messageText);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().detectAll().build());

        client=new DefaultHttpClient();

        String a[]={"country","message"};
            new ReadData().execute(a);

       /* new ReadData().execute("message");
        msg.setText(message);*/





        /*GetDataEx getDataEx=new GetDataEx();
        try {
            String returned_data=getDataEx.getData();
            tv.setText(returned_data);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

    }
    /*public Web_Service(String place)
    {

        this.place=place;
        new ReadData().execute("country");

    }*/
    public JSONObject getPlaceCountry(String place) throws ClientProtocolException,IOException,JSONException{
        //this.place=place;

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
   public class ReadData extends AsyncTask<String, Integer, String> {
       ProgressDialog progressDialog = new ProgressDialog(Web_Service.this);
       ArrayList<String> alist = new ArrayList<String>(4);
        String country,message;

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           progressDialog.setTitle("Looking");
           progressDialog.setMessage("Getting data...");
           progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
           progressDialog.show();
       }

       @Override
       protected String doInBackground(String... params) {

           try {
               json = getPlaceCountry(place);

               /*for(int i=0; i<params.length;i++)
               {
                   alist.add(i, json.getString(params[i])+System.getProperty("line.separator"));

               }*/
               this.country=json.getString(params[0]);
               this.message=json.getString(params[1]);


               return "hello";


           } catch (JSONException e1) {
               e1.printStackTrace();
           } catch (ClientProtocolException e1) {
               e1.printStackTrace();
           } catch (IOException e1) {
               e1.printStackTrace();
           }

           return null;
       }


       protected void onPostExecute(String s) {
           /*for(int i=0; i<alist.size(); i++)
           s1 += s+"\n";*/
           tv.setText(country);
           msg.setText(message);
           progressDialog.dismiss();



       }

       }




   }

