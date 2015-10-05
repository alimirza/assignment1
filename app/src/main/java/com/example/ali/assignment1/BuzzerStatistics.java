package com.example.ali.assignment1;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by ali on 15-10-04.
 */
public class BuzzerStatistics {

    private HashMap<String, HashMap<String, Integer>> playerCounts;
    private HashMap<String, Integer> twoPlayerMode;
    private HashMap<String, Integer> threePlayerMode;
    private HashMap<String, Integer> fourPlayerMode;
    private Context context;


    public BuzzerStatistics(Context context) {
        this.context = context;
        loadFromFile(context);


    }

    public void incrementPlayerCount(String PLAYER_SELECTION, String player) {
        playerCounts.get(PLAYER_SELECTION).put(player, playerCounts.get(PLAYER_SELECTION).get(player) + 1);
    }

    public HashMap<String, Integer> getPlayerMode(String PLAYER_SELECTION) {
        return playerCounts.get(Integer.valueOf(PLAYER_SELECTION));
    }

    public ArrayList<String> getResultsList() {
        ArrayList<String> returnValue = new ArrayList<String>();

        returnValue.add("2 Player Mode: Player 1 Buzzes = " + String.valueOf(playerCounts.get("2").get("Player1")));
        returnValue.add("2 Player Mode: Player 2 Buzzes = " + String.valueOf(playerCounts.get("2").get("Player2")));

        returnValue.add("3 Player Mode: Player 1 Buzzes = " + String.valueOf(playerCounts.get("3").get("Player1")));
        returnValue.add("3 Player Mode: Player 2 Buzzes = " + String.valueOf(playerCounts.get("3").get("Player2")));
        returnValue.add("3 Player Mode: Player 3 Buzzes = " + String.valueOf(playerCounts.get("3").get("Player3")));

        returnValue.add("4 Player Mode: Player 1 Buzzes = " + String.valueOf(playerCounts.get("4").get("Player1")));
        returnValue.add("4 Player Mode: Player 2 Buzzes = " + String.valueOf(playerCounts.get("4").get("Player2")));
        returnValue.add("4 Player Mode: Player 3 Buzzes = " + String.valueOf(playerCounts.get("4").get("Player3")));
        returnValue.add("4 Player Mode: Player 4 Buzzes = " + String.valueOf(playerCounts.get("4").get("Player4")));
        return returnValue;
    }

    private void loadFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput("file2.sav");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<HashMap<String,HashMap<String, Integer>>>() {}.getType();
            playerCounts = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            playerCounts = new HashMap<String,HashMap<String, Integer>>();
            twoPlayerMode = new HashMap<String, Integer>();
            threePlayerMode = new HashMap<String, Integer>();
            fourPlayerMode = new HashMap<String, Integer>();

            twoPlayerMode.put("Player1", 0);
            twoPlayerMode.put("Player2", 0);

            threePlayerMode.put("Player1", 0);
            threePlayerMode.put("Player2", 0);
            threePlayerMode.put("Player3", 0);

            fourPlayerMode.put("Player1", 0);
            fourPlayerMode.put("Player2", 0);
            fourPlayerMode.put("Player3", 0);
            fourPlayerMode.put("Player4", 0);

            playerCounts.put("2", twoPlayerMode);
            playerCounts.put("3", threePlayerMode);
            playerCounts.put("4", fourPlayerMode);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public void save(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput("file2.sav", 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(playerCounts, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    public void clear() {
        playerCounts = new HashMap<String,HashMap<String, Integer>>();
        twoPlayerMode = new HashMap<String, Integer>();
        threePlayerMode = new HashMap<String, Integer>();
        fourPlayerMode = new HashMap<String, Integer>();

        twoPlayerMode.put("Player1", 0);
        twoPlayerMode.put("Player2", 0);

        threePlayerMode.put("Player1", 0);
        threePlayerMode.put("Player2", 0);
        threePlayerMode.put("Player3", 0);

        fourPlayerMode.put("Player1", 0);
        fourPlayerMode.put("Player2", 0);
        fourPlayerMode.put("Player3", 0);
        fourPlayerMode.put("Player4", 0);

        playerCounts.put("2", twoPlayerMode);
        playerCounts.put("3", threePlayerMode);
        playerCounts.put("4", fourPlayerMode);

        save(this.context);


    }

}
