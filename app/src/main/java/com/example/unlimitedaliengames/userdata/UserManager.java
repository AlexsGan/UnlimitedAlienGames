package com.example.unlimitedaliengames.userdata;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/*
User database class, stores an array of users.
 */
public class UserManager implements Serializable, UserManagerInterface {

    /*
    ArrayList of users.
     */
    private ArrayList<User> users;
    /*
    Users to extract, always after matching password.
     */
    private int toExtract;

    public UserManager() {
        users = new ArrayList<>();
    }

    /*
    Attempt to put a user into this database, if duplicated, change the password.
     */
    public boolean attemptRegister(String name, String pass) {
        if (name.length() <= 0)
            return false;
        for (int i = 0; i < users.size(); i++) {
            User currUser = users.get(i);

            if (currUser.getName().equals(name)) {
                return false;
            }
        }
        addUser(name, pass);
        return true;
    }

    /*
    Add the user to the database.
     */
    private void addUser(String name, String pass) {
        users.add(new User(name, pass, this));
    }

    /*
    Return true if there is a user with given name and password.
     */
    public boolean validateCredentials(String name, String pass) {
        for (int i = 0; i < users.size(); i++) {
            User currUser = users.get(i);

            if (currUser.getName().equals(name) && currUser.getPassword().equals(pass)) {
                toExtract = i;
                return true;
            }
        }
        return false;
    }

    /*
    Extract the user found by validateCredentials.
     */
    public User extractUser() {
        return users.get(toExtract);
    }

    /*
    Write (save) user data onto a file
     */
    public void writeToFile(Context context) {

        //String path = context.getFilesDir().getAbsolutePath();
        File file = new File(context.getFilesDir(), "savedUserData.json");

        try {
            FileWriter output = new FileWriter(file);

            JsonArray userList = new JsonArray();

            for (int i = 0; i < users.size(); i++) {
                User currUser = users.get(i);
                JsonObject json1 = new JsonObject();
                json1.addProperty("userId", currUser.getName());
                json1.addProperty("password", currUser.getPassword());
                json1.addProperty("timePlayed", currUser.getTimePlayed());
                json1.addProperty("gamesPlayed", currUser.getGamesPlayed());
                json1.addProperty("totalPoints", currUser.getTotalPoints());

                userList.add(json1);
            }
            output.write(userList.toString());
            output.flush();

            Log.e("tag1", "writeToFile successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Read saved user data and add them to users
    */
    public void readFromFile(Context context) {
        JsonParser parser = new JsonParser();

        File file = context.getFileStreamPath("savedUserData.json");
        if (!file.exists()) {
            writeToFile(context);
        }

        //File file = new File(context.getFilesDir(), "savedUserData.json");

        try {
            FileReader reader = new FileReader(file);

            JsonArray userList = (JsonArray) parser.parse(reader);
            // System.out.println(userList);
            Log.e("tag2", String.valueOf(userList.size()));

            for (int i = 0; i < userList.size(); i++) {

                JsonObject jsonUser = (JsonObject) userList.get(i);

                String userId = jsonUser.get("userId").getAsString();
                String password = jsonUser.get("password").getAsString();
                User currUser = new User(userId, password, this);
                currUser.setTimePlayed(Long.valueOf(jsonUser.get("timePlayed").getAsString()));
                currUser.setGamesPlayed(Integer.valueOf(jsonUser.get("gamesPlayed").getAsString()));
                currUser.setTotalPoints(Integer.valueOf(jsonUser.get("totalPoints").getAsString()));

                users.add(currUser);

                Log.e("Name: ", userId);
                Log.e("Password: ", password);
                Log.e("TimePlayed: ", String.valueOf(currUser.getTimePlayed()));
            }
            Log.e("tag1", "readFromFile successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
