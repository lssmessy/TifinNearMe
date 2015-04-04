package com.tifinnearme.priteshpatel.tifinnearme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by PRITESH on 04-04-2015.
 */
public class Country_Finder extends Activity {
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_find);
        et=(EditText)findViewById(R.id.editText);

    }
    public void onFindClick(View view){

        Intent i=new Intent(this,Web_Service.class);
        i.putExtra("textfield_data",et.getText().toString());
        //b.putString("text",et.getText().toString());
        startActivity(i);







    }
}
