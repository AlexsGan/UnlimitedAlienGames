package com.example.unlimitedaliengames.userdata;

import java.io.Serializable;

/*
Data packet for saving data of guesser game.
 */
public class GuesserData implements Serializable {

    public int numProblem;
    public int guesserScore;
    public String currProblem;
    public String correctAns;


    GuesserData() {
        this.numProblem = 0;
        this.guesserScore = 0;
        this.currProblem = null;
        this.correctAns = null;
    }

    public GuesserData(int num, int score, String curr, String correct) {
        this.numProblem = num;
        this.guesserScore = score;
        this.currProblem = curr;
        this.correctAns = correct;
    }
}
