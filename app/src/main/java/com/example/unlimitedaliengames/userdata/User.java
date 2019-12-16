package com.example.unlimitedaliengames.userdata;

import android.content.Context;

import java.io.Serializable;

/*
An User object.
 */
public class User implements Serializable {

    private UserManagerInterface userManagerInterface;
    /*
    User's information.
     */
    private String name;
    private String password;

    //3 common stats for user regardless of game/level
    private long timePlayed;
    private int gamesPlayed;
    private int totalPoints;

    /*
    Stats for the guesser game.
    */
    private GuesserData guesserData;

    /*
    Initialize a user with not game info.
     */
    User(String name, String password, UserManager manager) {
        this.name = name;
        this.password = password;
        this.guesserData = new GuesserData();
        this.userManagerInterface = manager;
        this.timePlayed = 0;
        this.totalPoints = 0;
        this.gamesPlayed = 0;
    }

    public GuesserData getGuesserData() {
        return guesserData;
    }

    public void writeToFile(Context context) {
        userManagerInterface.writeToFile(context);
    }

    /*
    Return the name of this user.
     */
    public String getName() {
        return name;
    }

    /*
    Return the password of the current user.
     */
    String getPassword() {
        return password;
    }

    public long getTimePlayed() {
        return timePlayed;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    void setTimePlayed(long tmp) {
        timePlayed = tmp;
    }

    void setGamesPlayed(int tmp) {
        gamesPlayed = tmp;
    }

    void setTotalPoints(int tmp) {
        totalPoints = tmp;
    }

    public void updateTimePlayed(long tmp) {
        timePlayed += tmp;
    }

    public void updateGamesPlayed(int tmp) {
        gamesPlayed += tmp;
    }

    public void updateTotalPoints(int tmp) {
        totalPoints += tmp;
    }
}
