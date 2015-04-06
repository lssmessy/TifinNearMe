package com.tifinnearme.priteshpatel.tifinnearme;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by pritesh.patel on 06-04-15.
 */

public class Drawer_Menu extends ActionBarActivity {

    // private String items[];//={"Home","Profile","Requests","Notifications","Search","Track My Tifin","Settings"};
    private DrawerLayout dl;
    private ListView listView;
    ActionBarDrawerToggle drawerlistener;
    //ArrayAdapter<String> adapter;
    private Myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_ex);

        dl=(DrawerLayout)findViewById(R.id.drawerOnleft);
        listView=(ListView) findViewById(R.id.list);
        //items=getResources().getStringArray(R.array.list_items);
        drawerlistener=new ActionBarDrawerToggle(this,dl,R.drawable.drawer_icon,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                //super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                //super.onDrawerClosed(drawerView);

            }
        };
        dl.setDrawerListener(drawerlistener);
        //Set adapter for this list
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter=new Myadapter(this);
        listView.setAdapter(adapter);
        //listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getSupportActionBar().setTitle(adapter.tifin_items[position]);
                listView.setItemChecked(position,true);
                dl.closeDrawers();
            }
        });

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerlistener.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerlistener.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerlistener.onConfigurationChanged(newConfig);
    }

}

class Myadapter extends BaseAdapter {
    private Context context;
    String tifin_items[];
    int icon[]={R.drawable.home,R.drawable.profile
            ,R.drawable.requests,R.drawable.notifications,R.drawable.search,R.drawable.tifins,R.drawable.settings};
    public Myadapter(Context context) {
        this.context=context;
        tifin_items=context.getResources().getStringArray(R.array.tifin_items);//Get the reference of social sites from strings.xml
    }
    public int getCount() {
        return tifin_items.length;
    }

    @Override
    public Object getItem(int position) {
        return tifin_items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=null;
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=(View)layoutInflater.inflate(R.layout.custom_row,parent,false);//Convert xml into java objects
        }
        else{
            row=convertView;
        }
        TextView titleText=(TextView)row.findViewById(R.id.textView);//Setting textview for the row
        ImageView i=(ImageView)row.findViewById(R.id.imageView);
        titleText.setText(tifin_items[position]);
        i.setImageResource(icon[position]);
        return row;
    }


}