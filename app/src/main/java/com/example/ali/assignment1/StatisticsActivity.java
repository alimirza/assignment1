package com.example.ali.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    private ListView listView;
    private Button showBuzzerStatsButton;
    private ArrayList<String> reactionTimes = new ArrayList<String>();
    public ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        showBuzzerStatsButton = (Button) findViewById(R.id.showBuzzerStatsButton);
        listView = (ListView) findViewById(R.id.listView);

        ReactionTimeStatistics x = new ReactionTimeStatistics(getBaseContext());
        BuzzerStatistics y = new BuzzerStatistics(getBaseContext());

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                R.layout.stats_list_item,R.id.listItemValue, x.getResultsList());
        listView.setAdapter(adapter1);

        adapter2 = new ArrayAdapter<String>(this,
                R.layout.stats_list_item,R.id.listItemValue, y.getResultsList());


        showBuzzerStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(adapter2);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
