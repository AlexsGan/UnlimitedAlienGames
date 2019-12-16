package com.example.unlimitedaliengames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.unlimitedaliengames.userdata.*;

import java.io.IOException;

/*
An Login menu for the game.
 */
public class LoginActivity extends AppCompatActivity {

    //Used to pass current user
    public static final String PASS_USER = "passUser";

    /*
    Components in the layout.
     */
    private EditText username;
    private EditText password;
    private TextView display;

    /*
    ArrayList of users.
     */
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userManager = new UserManager();

        userManager.readFromFile(getApplicationContext()); //get past users' data from file

        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        display = findViewById(R.id.messageDisplay);


        //handling clicking for login
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin(username.getText().toString(), password.getText().toString());
            }
        });

        //handling clicking for register.
        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    attemptRegister();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*
    Attempt to put a user into this database, if duplicated, change the password.
     */
    private void attemptRegister() throws IOException {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (userManager.attemptRegister(user, pass)) {
            display.setText(getString(R.string.register_ok));

            userManager.writeToFile(getApplicationContext()); //save the new user
            // userManager.readFromFile(getApplicationContext()); //get past users' data from file
        } else {
            display.setText(getString(R.string.register_fail));
        }
    }

    /*
    Attempt to login based on given user name and password.
     */
    void attemptLogin(String user, String pass) {
        if (userManager.validateCredentials(user, pass)) {
            onSuccess(userManager.extractUser());
        } else {
            display.setText(getString(R.string.login_fail));
        }
    }

    /*
    Go to main menu if successfully logged in.
    */
    void onSuccess(User currUser) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(PASS_USER, currUser);
        startActivity(intent);
        finish();
    }
}