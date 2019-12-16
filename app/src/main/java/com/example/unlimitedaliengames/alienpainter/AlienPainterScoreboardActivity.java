package com.example.unlimitedaliengames.alienpainter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.userdata.User;

/**
 * The Scoreboard Screen for alien painter. Displays the player's points, number of moves,
 * and the amount of time left when the player finished his game.
 * The displayed statistics retain the language choice the player has made.
 */
public class AlienPainterScoreboardActivity extends AppCompatActivity {


    //Used to pass current user
    public static final String PASS_USER = "passUser";

    /**
     * The constants used to receive the intents from AlienPainterActivity
     * Also used as part of the TextViews
     */
    static final String NUM_MOVES = "Number of Moves: ";
    static final String TIME_LEFT = "Time Remaining: ";
    static final String POINTS = "Points: ";
    static final String SCOREBOARD_STATUS = "You Have ";
    static final String STORY = "The Alien bows before your galactic intellect. " +
            "He has decided to withdraw his troops from Earth.";
    static final String WIN = "Won!";
    static final String LOSS = "Lost!";

    /**
     * The Chinese Constants used by the TextViews for what to display on the screen
     */
    static final String SCOREBOARD_STATUS_CHINESE = "你";
    static final String STORY_CHINESE = "外星人屈服于你强大的智力。他已命令他的部队撤离了。";
    static final String NUM_MOVES_CHINESE = "步数: ";
    static final String TIME_LEFT_CHINESE = "剩余时间: ";
    static final String POINTS_CHINESE = "分数:";
    static final String WIN_CHINESE = "赢了!";
    static final String LOSS_CHINESE = "输了!";

    /**
     * A constant used as a keyword to receive the isEnglish variable through intent
     */
    static final String LANGUAGE = "LANGUAGE";

    /**
     * Boolean variables holding whether the player won, and if the language chosen is in english
     * respectively
     */
    private boolean isVictorious, isEnglish;

    /**
     * Integer variables holding number of moves, amount of time left,
     * and points earned respectively
     */
    private int numMoves, timeLeft, points;

    /**
     * TextViews assigned to each of the TextView objects on the screen
     */
    private TextView pointsTextView;
    private TextView numMovesTextView;
    private TextView timeLeftTextView;
    private TextView gameOverStatus;
    private TextView storyTextView;

    /**
     * The button used to hold the scoreboardExitButton in the view
     */
    private Button exitButton;

    /**
     * Holds the current User
     */
    private User currUser;

    /**
     * This method is automatically called when the view switches to AlienPainterScoreboardActivity
     *
     * @param savedInstanceState the instanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_painter_scoreboard);

        //Grab all the values passed through the intent and assign them to variables
        Intent intent = getIntent();
        isVictorious = intent.getBooleanExtra(SCOREBOARD_STATUS, false);
        numMoves = intent.getIntExtra(NUM_MOVES, 0);
        timeLeft = intent.getIntExtra(TIME_LEFT, 0);
        points = intent.getIntExtra(POINTS, 0);
        isEnglish = intent.getBooleanExtra(LANGUAGE, true);
        currUser = (User) intent.getSerializableExtra(PASS_USER);

        //Setup the TextViews
        setupTextView();

        //Setup the button
        setupButtons();

        //Display the statistics
        displayStatistics();
    }

    /**
     * A helper function used to setup the TextViews in the activity.
     * Called in the onCreate method
     */
    private void setupTextView() {
        gameOverStatus = findViewById(R.id.gameOverStatusText);
        numMovesTextView = findViewById(R.id.numMovesTextView);
        timeLeftTextView = findViewById(R.id.timeLeftTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        storyTextView = findViewById(R.id.storyTextView);
    }

    /**
     * A helper function used to display the statistics onto the view
     * The function retains the language choice the player made in the game
     * Called in the onCreate method
     */
    private void displayStatistics() {
        if (isEnglish) {
            if (isVictorious) {
                gameOverStatus.setText(SCOREBOARD_STATUS + WIN);
            } else {
                gameOverStatus.setText(SCOREBOARD_STATUS + LOSS);
            }
            numMovesTextView.setText(NUM_MOVES + numMoves);
            timeLeftTextView.setText(TIME_LEFT + timeLeft);
            pointsTextView.setText(POINTS + points);
            storyTextView.setText(STORY);
        } else {
            if (isVictorious) {
                gameOverStatus.setText(SCOREBOARD_STATUS_CHINESE + WIN_CHINESE);
            } else {
                gameOverStatus.setText(SCOREBOARD_STATUS_CHINESE + LOSS_CHINESE);
            }
            numMovesTextView.setText(NUM_MOVES_CHINESE + numMoves);
            timeLeftTextView.setText(TIME_LEFT_CHINESE + timeLeft);
            pointsTextView.setText(POINTS_CHINESE + points);
            storyTextView.setText(STORY_CHINESE);
        }
    }

    /**
     * A helper function used to setup the buttons in the view
     */
    private void setupButtons() {
        exitButton = findViewById(R.id.scoreboardExitButton);


        if (isEnglish) {
            exitButton.setText(R.string.alien_painter_exit);
        } else {
            exitButton.setText(R.string.alien_painter_exit_chinese);
        }

        exitButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra(PASS_USER, currUser);
                startActivity(intent);
                finish();
            }
        });
    }
}
