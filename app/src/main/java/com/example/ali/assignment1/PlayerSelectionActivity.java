package com.example.ali.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PlayerSelectionActivity extends AppCompatActivity {

    private Button twoPlayersSelectionButton;
    private Button threePlayersSelectionButton;
    private Button fourPlayersSelectionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_selection);

        twoPlayersSelectionButton = (Button) findViewById(R.id.twoPlayersSelectionButton);
        threePlayersSelectionButton = (Button) findViewById(R.id.threePlayersSelectionButton);
        fourPlayersSelectionButton = (Button) findViewById(R.id.fourPlayersSelectionButton);

        twoPlayersSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameshowModeIntent = new Intent(PlayerSelectionActivity.this, GameshowModeActivity.class);
                gameshowModeIntent.putExtra("PLAYER_SELECTION", "2");
                PlayerSelectionActivity.this.startActivity(gameshowModeIntent);
            }
        });

        threePlayersSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameshowModeIntent = new Intent(PlayerSelectionActivity.this, GameshowModeActivity.class);
                gameshowModeIntent.putExtra("PLAYER_SELECTION", "3");
                PlayerSelectionActivity.this.startActivity(gameshowModeIntent);

            }
        });

        fourPlayersSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameshowModeIntent = new Intent(PlayerSelectionActivity.this, GameshowModeActivity.class);
                gameshowModeIntent.putExtra("PLAYER_SELECTION", "4");
                PlayerSelectionActivity.this.startActivity(gameshowModeIntent);

            }
        });
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
}
