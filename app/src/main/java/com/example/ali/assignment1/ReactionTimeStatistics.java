package com.example.ali.assignment1;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by ali on 15-10-04.
 */
public class ReactionTimeStatistics {

    private static final String REACTION_TIME_STATS = "file.sav";
    private ArrayList<Integer> allTimesList;
    private Context context;

    public ArrayList<String> getResultsList() {
        return resultsList;
    }

    private ArrayList<String> resultsList;

    public ReactionTimeStatistics(Context context) {
        this.context = context;
        loadFromFile(context);
        updateResultsList();
    }

    public void updateResultsList() {
        resultsList = new ArrayList<String>();
        resultsList.add("Minimum time of all reaction times: NA");
        resultsList.add("Minimum time of last 10 reaction times: NA");
        resultsList.add("Minimum time of last 100 reaction times: NA");
        resultsList.add("Maximum time of all reaction times: NA");
        resultsList.add("Maximum time of last 10 reaction times: NA");
        resultsList.add("Maximum time of last 100 reaction times: NA");
        resultsList.add("Average time of all reaction times: NA");
        resultsList.add("Average time of last 10 reaction times: NA");
        resultsList.add("Average time of last 100 reaction times: NA");
        resultsList.add("Median time of all reaction times: NA");
        resultsList.add("Median time of last 10 reaction times: NA");
        resultsList.add("Median time of last 100 reaction times: NA");
        if (allTimesList.size() > 0) {
            resultsList.set(0,"Minimum time of all reaction times: " + String.valueOf(Collections.min(allTimesList)));
            resultsList.set(3,"Maximum time of all reaction times: " + String.valueOf(Collections.max(allTimesList)));
            resultsList.set(6,"Average time of all reaction times: " + String.valueOf(calculateAverage(allTimesList, allTimesList.size())));
            resultsList.set(9,"Median time of all reaction times: " + String.valueOf(calculateMedian(allTimesList, allTimesList.size())));
        }

        if (allTimesList.size() > 9){
            resultsList.set(1, "Minimum time of last 10 reaction times: " + String.valueOf(Collections.min(allTimesList.subList(allTimesList.size() - 10, allTimesList.size()))));
            resultsList.set(4, "Maximum time of last 10 reaction times: " + String.valueOf(Collections.max(allTimesList.subList(allTimesList.size() - 10, allTimesList.size()))));
            resultsList.set(7,"Average time of last 10 reaction times: " + String.valueOf(calculateAverage(allTimesList, 10)));
            resultsList.set(10,"Median time of last 10 reaction times" + String.valueOf(calculateMedian(allTimesList, 10)));
        }
        if (allTimesList.size() > 99){
            resultsList.set(2, "Minimum time of last 100 reaction times: " + String.valueOf(Collections.min(allTimesList.subList(allTimesList.size() - 100, allTimesList.size()))));
            resultsList.set(5, "Maximum time of last 100 reaction times: " + String.valueOf(Collections.max(allTimesList.subList(allTimesList.size() - 100, allTimesList.size()))));
            resultsList.set(8, "Average time of last 100 reaction times: " + String.valueOf(calculateAverage(allTimesList, 100)));
            resultsList.set(11,"Median time of last 100 reaction times: " + String.valueOf(calculateMedian(allTimesList, 100)));
        }
    }

    public void addTime(String time) {
        allTimesList.add(Integer.valueOf(time));


    }



    public void save(Context context) {
            try {
                FileOutputStream fos = context.openFileOutput(REACTION_TIME_STATS, 0);
                OutputStreamWriter writer = new OutputStreamWriter(fos);
                Gson gson = new Gson();
                gson.toJson(allTimesList, writer);
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

    private void loadFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput("file.sav");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
            allTimesList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            allTimesList = new ArrayList<Integer>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    private double calculateAverage(ArrayList<Integer> allTimesList, int size) {
        Integer sum = 0;
        if(!allTimesList.isEmpty()) {
            for (Integer time : allTimesList) {
                sum += time;
            }
            return sum.doubleValue() / size;
        }
        return sum;
    }

    public double calculateMedian(ArrayList<Integer> values, int size)
    {
        Collections.sort(values);

        if (values.size() % 2 == 1)
            return values.get((size+1)/2-1);
        else
        {
            double lower = values.get(size/2-1);
            double upper = values.get(size/2);

            return (lower + upper) / 2.0;
        }
    }

    public void clear() {
        allTimesList = new ArrayList<Integer>();
        resultsList = new ArrayList<String>();
        resultsList.add("empty");
        resultsList.add("empty");
        resultsList.add("empty");
        resultsList.add("empty");
        resultsList.add("empty");
        resultsList.add("empty");
        resultsList.add("empty");
        resultsList.add("empty");
        resultsList.add("empty");
        resultsList.add("empty");
        resultsList.add("empty");
        resultsList.add("empty");
        save(context);
    }


}
