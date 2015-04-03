package com.tifinnearme.priteshpatel.tifinnearme;

import android.annotation.TargetApi;
import android.os.Build;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by PRITESH on 03-04-2015.
 */
public class GetDataEx {


    public String getData() throws Exception{
        BufferedReader br=null;
        String data=null;
        try{
            HttpClient client=new DefaultHttpClient();
            URI website=new URI("http://www.twitter.com");
            HttpGet request=new HttpGet();
            request.setURI(website);
            HttpResponse response=client.execute(request);
            br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb=new StringBuffer("");//Save all response data as string buffer
            String line;
            String new_line=System.getProperty("line.separator");
            while ((line=br.readLine())!=null)//read whole line till null
            {
                sb.append(line+new_line); //Append data line by line
            }
            br.close();
            data=sb.toString();
            return  data;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                br.close();
                return  data;
            } catch (IOException e) {
                e.printStackTrace();
                return  data;
            }

        }

    }
}
