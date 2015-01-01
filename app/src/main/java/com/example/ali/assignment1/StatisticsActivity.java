package com.example.ali.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class StatisticsActivity extends AppCompatActivity {

    private ListView listView;
    private Button showBuzzerStatsButton;
    private Button clearStatsButton;
    private Button emailButton;
    private ArrayAdapter<String> adapter2;
    private ReactionTimeStatistics x;
    private BuzzerStatistics y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        showBuzzerStatsButton = (Button) findViewById(R.id.showBuzzerStatsButton);
        clearStatsButton = (Button) findViewById(R.id.clearStatsButton);
        emailButton = (Button) findViewById(R.id.emailButton);
        listView = (ListView) findViewById(R.id.listView);

        x = new ReactionTimeStatistics(getBaseContext());
        y = new BuzzerStatistics(getBaseContext());

        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
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

        clearStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x.clear();
                y.clear();
                listView.invalidate();
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = "dummy@email.com";
                String subject = "ahmirza-reflex stats";
                String message = "";
                for (String item: x.getResultsList()){
                    message = message + item + "\n";
                }

                for (String item: y.getResultsList()){
                    message = message + item + "\n";
                }

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
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
