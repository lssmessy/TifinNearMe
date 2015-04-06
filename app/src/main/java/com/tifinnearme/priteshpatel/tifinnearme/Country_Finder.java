package com.tifinnearme.priteshpatel.tifinnearme;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by PRITESH on 04-04-2015.
 */
public class Country_Finder extends Activity {
    EditText et;
    ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_find);
        et=(EditText)findViewById(R.id.editText);

    }
    public void onFindClick(View view){

        if(isInternetAvailable()) {
            Intent i = new Intent(this, Web_Service.class);
            i.putExtra("textfield_data", et.getText().toString());
            //b.putString("text",et.getText().toString());
            startActivity(i);
        }


    }

    private boolean isInternetAvailable() {
        connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()== NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED)
        {
            return true;
        }
        else
        {
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
            alertDialog.setTitle("No Internet");
            alertDialog.setMessage("No Internet available. Please connect to a network in order to access further!");
            alertDialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
            return false;
        }

    }
}
