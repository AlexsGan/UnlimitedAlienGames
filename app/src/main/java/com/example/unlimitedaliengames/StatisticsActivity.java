package com.example.unlimitedaliengames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unlimitedaliengames.userdata.User;

import static com.example.unlimitedaliengames.LoginActivity.PASS_USER;

/**
 * This is the class that represents the Statistics screen.
 * The XML file for this class contains TextViews that display the current user's total statistics
 * Two buttons are available for the user to click, the save button allows the user to manually save
 * the statistics. The exit button exits back into MainActivity.
 */
public class StatisticsActivity extends AppCompatActivity {
    private Button exit, save;

    /**
     * Holds the object that references the current user
     */
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Intent intent = getIntent();
        currUser = (User) intent.getSerializableExtra(PASS_USER);

        int totalTime = (int) currUser.getTimePlayed();
        int totalGamesPlayed = currUser.getGamesPlayed();
        int totalPoints = currUser.getTotalPoints();

        TextView game = findViewById(R.id.Game);
        TextView points = findViewById(R.id.Points);
        TextView time = findViewById(R.id.Time);
        exit = findViewById(R.id.BackToMenu);
        save = findViewById(R.id.statisticsSaveButton);

        String gameString = "Total Number of Games Played: " + totalGamesPlayed;
        String pointsString = "Total Number of Points: " + totalPoints;
        String timeString = "Total Number Time in Seconds: " + totalTime;
        game.setText(gameString);
        points.setText(pointsString);
        time.setText(timeString);
        SetupButtonListeners();

    }

    /**
     * A helper method used to set up the buttons available for clicking
     */
    private void SetupButtonListeners() {
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitScoreBoard();
                finish();

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                currUser.writeToFile(getApplicationContext());
            }
        });
    }

    /**
     * A helper method used to exit back into MainActivity and pass the current user
     * along through intent.
     */
    private void exitScoreBoard() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(PASS_USER, currUser);
        startActivity(intent);
        finish();
    }
}
