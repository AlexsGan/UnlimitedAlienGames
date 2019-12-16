package com.example.unlimitedaliengames.alienpainter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.userdata.User;

/**
 * The top-level class for the alien painter game.
 */
public class AlienPainterActivity extends AppCompatActivity implements View.OnClickListener, AlienPainterViewable {

    //Used to pass current user
    public static final String PASS_USER = "passUser";

    /**
     * The English Constants used by the TextViews to display on the screen
     * Also used as keywords to pass values through an intent
     */
    static final String SCOREBOARD_STATUS = "You Have ";
    static final String NUM_MOVES = "Number of Moves: ";
    static final String TIME_LEFT = "Time Remaining: ";
    static final String POINTS = "Points: ";
    static final String WIN = "You Have Won!";
    static final String LOSS = "You Have Lost!";
    static final String REPLAY = "Instant Replaying...";

    /**
     * The Chinese Constants used by the TextViews to display on the screen
     */
    static final String NUM_MOVES_CHINESE = "步数: ";
    static final String TIME_LEFT_CHINESE = "剩余时间: ";
    static final String POINTS_CHINESE = "分数:";
    static final String WIN_CHINESE = "你赢了！";
    static final String LOSS_CHINESE = "你输了！";
    static final String REPLAY_CHINESE = "即时回放中...";

    /**
     * A constant used as a keyword to pass the isEnglish variable through intent
     */
    static final String LANGUAGE = "LANGUAGE";

    /**
     * The boolean variable used to check whether the player has turned on Chinese Language
     */
    private boolean isEnglish;

    /**
     * The 2D Array of imageButtons
     */
    private ImageButton[][] grid = new ImageButton[3][3];

    /**
     * Holds the textView for number of moves the player has made
     */
    private TextView painterTextViewMoves;

    /**
     * Holds the textView for amount of time that has passed
     */
    private TextView painterTextViewTime;

    /**
     * Holds the textView for the amount of points the user has earned
     */
    private TextView painterTextViewPoints;

    /**
     * Holds the textView for the instructions of the game
     */
    private TextView painterTextViewInstructions;

    /**
     * Holds the presenter of this game
     */
    private AlienPainterPresenter presenter;

    /**
     * Holds the button for exiting the game
     */
    private Button exitButton;

    /**
     * Holds the button for retrying the game
     */
    private Button retryButton;

    /**
     * Refers to the button in alien painter layout that switches the language of the game
     */
    private Button languageButton;

    /**
     * Holds the button that switches the view to the scoreboard.
     */
    private Button scoreboardButton;

    /**
     * Holds the button that when pressed, performs an instant replay of the game.
     * This button is invisible until the game has ended.
     */
    private Button replayButton;

    /**
     * Holds the current user of this game
     */
    private User currUser;


    /**
     * This method is automatically called when the app view switches to AlienPainterActivity.
     * The method extracts the User that logged in from the intent, and uses it to store player
     * statistics.
     * The method initializes AlienPainterTimer and AlienPainterPresenter.
     * The method calls the createGrid method that creations a 2D array of buttons
     * The method calls the setupButtons method that sets up the buttons other than the 2D array
     * The method sends a toast that informs the player of how to play the game.
     *
     * @param savedInstanceState the instanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alien_painter);

        Intent intent = getIntent();
        currUser = (User) intent.getSerializableExtra(PASS_USER);

        isEnglish = true;

        AlienPainterDataHandler dataHandler = new AlienPainterDataHandler();

        //Setup the TextViews
        setupTextView();

        //Setup the exitButton, languageButton, scoreboardButton and retryButton
        setupButtons();

        //Construct the 2D imageButton array
        createGrid();

        //Game Instructions
        if (isEnglish) {
            painterTextViewInstructions.setText(R.string.alien_painter_instructions);
        } else {
            painterTextViewInstructions.setText(R.string.alien_painter_instructions_chinese);
        }

        //Initialize the Presenter
        presenter = new AlienPainterPresenter(this,
                new AlienPainterFunctions(grid, this), this, dataHandler, currUser);
    }

    /**
     * A helper function used to setup the TextViews for the view
     */
    private void setupTextView() {
        painterTextViewMoves = findViewById(R.id.painterTextView1);
        painterTextViewTime = findViewById(R.id.painterTextView2);
        painterTextViewPoints = findViewById(R.id.painterTextView3);
        painterTextViewInstructions = findViewById(R.id.painterInstructionsView);
    }

    /**
     * Setup for the exitButton and retryButton.
     * When exitButton is clicked, the game goes back to MainActivity.
     * When retryButton is clicked, the game resets its timer and generates a new board.
     * When languageButton is clicked, the game changes its UI language to Chinese.
     * When scoreboardButton is clicked, the view switches to the scoreboard.
     */
    private void setupButtons() {
        exitButton = findViewById(R.id.exitButton);
        retryButton = findViewById(R.id.retryButton);
        languageButton = findViewById(R.id.languageButton);
        scoreboardButton = findViewById(R.id.scoreBoardButton);
        replayButton = findViewById(R.id.replayButton);

        //Set retryButton scoreboardButton and replayButton to invisible until the game ends
        retryButton.setVisibility(View.INVISIBLE);
        scoreboardButton.setVisibility(View.INVISIBLE);
        replayButton.setVisibility(View.INVISIBLE);

        //Setup the exitButton
        exitButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {

                presenter.terminateTimer();
                presenter.recordStats();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra(PASS_USER, currUser);
                startActivity(intent);
                finish();
            }

        });

        //Setup the retryButton
        retryButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             * When this view is clicked, it calls the resetGame method in the presenter,
             * resets the timer, resets the statistics displayed on screen
             * resets gameEnded and isVictorious to false
             * resets replayButton, retryButton, and scoreboardButton's visibility to invisible
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if (presenter.getGameEnded() && !presenter.checkIfReplaying()) {
                    presenter.resetGame();
                    presenter.resetTimer();
                    updateStats();
                    replayButton.setVisibility(View.INVISIBLE);
                    retryButton.setVisibility(View.INVISIBLE);
                    scoreboardButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        //Setup the languageButton
        languageButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                isEnglish = !isEnglish;
                updateLanguage();
                updateStats();
            }
        });

        //Setup the scoreboardButton
        scoreboardButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                if (presenter.getGameEnded() && !presenter.checkIfReplaying()) {
                    //calls the scoreboard method
                    presenter.terminateTimer();
                    presenter.recordStats();
                    scoreboard();
                }
            }
        });

        //Setup the replayButton
        replayButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                presenter.instantReplay();
                replayButton.setVisibility(View.INVISIBLE);

                //Create a toast that tells the player it's INSTANT REPLAY TIME
                displayReplay();
            }
        });

    }

    /**
     * Constructs the 2D array for the imageButtons. Also sets onClickListener for each imageButton
     * inside. This 2D grid is pass into the presenter.
     */
    private void createGrid() {
        String imageButtonID;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                imageButtonID = "painterImageButton_" + i + j;

                //This step allows easy assignment of each imageButton with a nested loop
                int resID = this.getResources().getIdentifier(imageButtonID, "id", this.getPackageName());
                grid[i][j] = findViewById(resID);
                grid[i][j].setOnClickListener(this); //Response to each button click

                //Generate a random number to decide whether to put white or black here
                double x = Math.random() * 2;
                if (x < 1) {
                    grid[i][j].setImageResource(R.drawable.black_circle);
                    grid[i][j].setContentDescription(getString(R.string.alien_painter_black));
                } else if (x < 2) {
                    grid[i][j].setImageResource(R.drawable.white_circle);
                    grid[i][j].setContentDescription(getString(R.string.alien_painter_white));
                }

            }
        }

        //Make sure the grid isn't entirely black at the beginning
        grid[1][1].setImageResource(R.drawable.white_circle);
        grid[1][1].setContentDescription("white");
    }

    /**
     * The event in which any of the imageButtons are pressed.
     * Calls the onGridClick method in the presenter.
     *
     * @param v The view
     */
    @Override
    public void onClick(View v) {
        presenter.onGridClick(v);
    }

    /**
     * Updates the time displayed on screen.
     *
     * @param time the time to update painterTextViewTime with.
     */
    @Override
    public void updateTimer(int time) {
        if (isEnglish) {
            String temp = TIME_LEFT + time;
            painterTextViewTime.setText(temp);
        } else {
            String temp = TIME_LEFT_CHINESE + time;
            painterTextViewTime.setText(temp);
        }
    }

    /**
     * Calls this when the timer is done.
     */
    @Override
    public void timerExpired() {
        //Inform the player they have failed
        if (isEnglish) {
            Toast.makeText(this, LOSS, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, LOSS_CHINESE, Toast.LENGTH_SHORT).show();
        }

        //Set visibility for replay button to visible
        replayButton.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.VISIBLE);
        scoreboardButton.setVisibility(View.VISIBLE);
    }

    /**
     * A helper function used to update the language displayed on the view when the user
     * switches the language.
     */
    @Override
    public void updateLanguage() {
        if (isEnglish) {
            languageButton.setText(R.string.alien_painter_language_chinese);
            exitButton.setText(R.string.alien_painter_exit);
            retryButton.setText(R.string.alien_painter_retry);
            scoreboardButton.setText(R.string.alien_painter_scoreboard_button);
            painterTextViewInstructions.setText(R.string.alien_painter_instructions);
            replayButton.setText(R.string.alien_painter_replay);
        } else {
            languageButton.setText(R.string.alien_painter_language_english);
            exitButton.setText(R.string.alien_painter_exit_chinese);
            retryButton.setText(R.string.alien_painter_retry_chinese);
            scoreboardButton.setText(R.string.alien_painter_scoreboard_button_chinese);
            painterTextViewInstructions.setText(R.string.alien_painter_instructions_chinese);
            replayButton.setText(R.string.alien_painter_replay_chinese);
        }
    }

    /**
     * A helper function used to update the statistics displayed on the view to the user
     */
    @Override
    public void updateStats() {
        if (isEnglish) {
            String moves = NUM_MOVES + presenter.getNumMoves();
            String time = TIME_LEFT + presenter.getTimeLeft();
            String points = POINTS + presenter.getPoints();
            painterTextViewMoves.setText(moves);
            painterTextViewTime.setText(time);
            painterTextViewPoints.setText(points);
        } else {
            String moves = NUM_MOVES_CHINESE + presenter.getNumMoves();
            String time = TIME_LEFT_CHINESE + presenter.getTimeLeft();
            String points = POINTS_CHINESE + presenter.getPoints();
            painterTextViewTime.setText(time);
            painterTextViewMoves.setText(moves);
            painterTextViewPoints.setText(points);
        }
    }

    /**
     * Calls this method when the game ends
     * Creates an intent for AlienPainterScoreboardActivity and puts numMoves,
     * timeLeft, language choice, points, and victory status into the intent.
     */
    private void scoreboard() {
        Intent intent = new Intent(this, AlienPainterScoreboardActivity.class);
        intent.putExtra(SCOREBOARD_STATUS, presenter.getIsVictorious());
        intent.putExtra(POINTS, presenter.getPoints());
        intent.putExtra(NUM_MOVES, presenter.getNumMoves());
        intent.putExtra(TIME_LEFT, presenter.getTimeLeft());
        intent.putExtra(LANGUAGE, isEnglish);
        intent.putExtra(PASS_USER, currUser);
        startActivity(intent);
        finish();
    }

    /**
     * A helper function used to display a toast to let the player know that it is
     * INSTANT REPLAY TIME
     */
    private void displayReplay() {
        if (isEnglish)
            Toast.makeText(this, REPLAY, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, REPLAY_CHINESE, Toast.LENGTH_LONG).show();
    }

    /**
     * Displays the fact that the player has won
     */
    @Override
    public void showPlayerWon() {
        if (isEnglish) {
            Toast.makeText(this, WIN, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, WIN_CHINESE, Toast.LENGTH_SHORT).show();
        }


        //Set visibility for replayButton to visible
        replayButton.setVisibility(View.VISIBLE);
        retryButton.setVisibility(View.VISIBLE);
        scoreboardButton.setVisibility(View.VISIBLE);
    }
}