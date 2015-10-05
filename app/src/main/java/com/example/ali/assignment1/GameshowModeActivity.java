package com.example.ali.assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GameshowModeActivity extends AppCompatActivity {

    private String PLAYER_SELECTION;
    private Button playerOneButton;
    private Button playerTwoButton;
    private Button playerThreeButton;
    private Button playerFourButton;
    private BuzzerStatistics buzzerStatistics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            PLAYER_SELECTION = extras.getString("PLAYER_SELECTION");
            switch (PLAYER_SELECTION) {
                case "2":
                    setContentView(R.layout.activity_gameshow_mode_2);
                    break;
                case "3":
                    setContentView(R.layout.activity_gameshow_mode_3);
                    break;
                case "4":
                    setContentView(R.layout.activity_gameshow_mode_4);
                    break;

            }
        }
        playerOneButton = (Button) findViewById(R.id.playerOneButton);
        playerTwoButton = (Button) findViewById(R.id.playerTwoButton);
        playerThreeButton = (Button) findViewById(R.id.playerThreeButton);
        playerFourButton = (Button) findViewById(R.id.playerFourButton);
        buzzerStatistics = new BuzzerStatistics(getBaseContext());

        playerOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buzzerStatistics.incrementPlayerCount(PLAYER_SELECTION, "Player1");
                buzzerStatistics.save(getBaseContext());
                showDialog("Player 1 Clicked", "Press OK to restart");
            }
        });
        playerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buzzerStatistics.incrementPlayerCount(PLAYER_SELECTION, "Player2");
                buzzerStatistics.save(getBaseContext());
                showDialog("Player 2 Clicked", "Press OK to restart");
            }
        });

        if (playerThreeButton != null){
            playerThreeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buzzerStatistics.incrementPlayerCount(PLAYER_SELECTION, "Player3");
                    buzzerStatistics.save(getBaseContext());
                    showDialog("Player 3 Clicked", "Press OK to restart");
                }
            });
        }

        if (playerFourButton != null) {
            playerFourButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buzzerStatistics.incrementPlayerCount(PLAYER_SELECTION, "Player4");
                    buzzerStatistics.save(getBaseContext());
                    showDialog("Player 4 Clicked", "Press OK to restart");
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gameshow_mode, menu);
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

    private AlertDialog showDialog(String title, String message) {
        return new AlertDialog.Builder(GameshowModeActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
