package com.example.ali.assignment1;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by ali on 15-09-30.
 */
public class MainView extends View {
    private Button singleModeButton;
    private Button gameshowModeButton;
    private Button statsButton;

    public MainView(Context context) {
        super(context);
        this.singleModeButton = (Button) findViewById(R.id.singleModeButton);
        this.gameshowModeButton = (Button) findViewById(R.id.gameshowModeButton);
        this.statsButton = (Button) findViewById(R.id.statsButton);

        this.singleModeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent singleModeIntent = new Intent(context, SingleModeActivity.class);
                //context.startActivity(singleModeIntent);
            }
        });

        this.gameshowModeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.statsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
