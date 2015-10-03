package com.example.ali.assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class SingleModeActivity extends AppCompatActivity {

    private Button singleModeClickerButton;
    private TextView clickNowText;
    private StopWatch stopWatch;
    private Integer randomTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_mode);


        singleModeClickerButton = (Button) findViewById(R.id.singleModeClickerButton);
        clickNowText = (TextView) findViewById(R.id.clickNowText);
        stopWatch = new StopWatch();

        singleModeClickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clickNowText.getVisibility() == View.VISIBLE) {
                    stopWatch.stopTimer();
                    showDialog("Reaction Time", stopWatch.getDeltaTime());
                }
                else if (clickNowText.getVisibility() == View.INVISIBLE) {
                    showDialog("You responded too quickly", "Click ok to try again");
                }

            }
        });

        showDialog("How to play", "wait for Click text to show. then click");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_mode, menu);
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

    private void showClickerAfterDelay(int milliseconds){
        clickNowText.setVisibility(View.INVISIBLE);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        clickNowText.setVisibility(View.VISIBLE);
                        stopWatch.startTimer();
                    }
                });
            }
        }, milliseconds);

    }

    private AlertDialog showDialog(String title, String message) {
        return new AlertDialog.Builder(SingleModeActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        randomTime = randomInt(10, 2000);
                        showClickerAfterDelay(randomTime);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private int randomInt(int min, int max) {
        Random rand = new Random();
        int randomInt = rand.nextInt((max - min) + 1) + min;
        return randomInt;
    }


}
