package com.example.unlimitedaliengames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.unlimitedaliengames.alienguesser.AlienGuesser;
import com.example.unlimitedaliengames.alienpainter.AlienPainterActivity;
import com.example.unlimitedaliengames.alienshooter.instructionPages.ShooterInstructionsActivity;
import com.example.unlimitedaliengames.userdata.*;

/**
 * This class is created after the user passes the login activity
 * The XML file for this class contains four buttons that each lead to a level or the statistics
 * screen, passing the current user object along through the intent.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Holding the User object referencing the current user
     */
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        currUser = (User) intent.getSerializableExtra(LoginActivity.PASS_USER);
    }

    /**
     * Whne the button with onClick property set to this method is clicked, go to AlienGuesser
     * @param view the view clicked
     */
    public void goToAlienGuesser(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AlienGuesser.class);
        intent.putExtra(LoginActivity.PASS_USER, currUser);
        startActivity(intent);
        finish();
    }

    /**
     * Whne the button with onClick property set to this method is clicked, go to AlienPainter
     * @param view the view clicked
     */
    public void goToAlienPainter(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AlienPainterActivity.class);
        intent.putExtra(LoginActivity.PASS_USER, currUser);
        startActivity(intent);
        finish();
    }

    /**
     * Whne the button with onClick property set to this method is clicked, go to AlienShooter
     * @param view the view clicked
     */
    public void goToAlienShooter(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ShooterInstructionsActivity.class);
        intent.putExtra(LoginActivity.PASS_USER, currUser);
        startActivity(intent);
        finish();
    }

    /**
     * Whne the button with onClick property set to this method is clicked, go to StatisticsActivity
     * @param view the view clicked
     */
    public void goToAlienStatistics(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra(LoginActivity.PASS_USER, currUser);
        startActivity(intent);
        finish();
    }

}
