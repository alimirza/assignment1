package com.example.ali.assignment1;

/**
 * Created by ali on 15-10-02.
 */
public class StopWatch {
    private long timeA;
    private long timeB;

    public void startTimer(){
        this.timeA = System.currentTimeMillis();
    }

    public void stopTimer() {
        this.timeB = System.currentTimeMillis();
    }

    public String getDeltaTime(){
        return String.valueOf(this.timeB - this.timeA);
    }
}
