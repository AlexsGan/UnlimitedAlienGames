package com.example.unlimitedaliengames.alienshooter.instructionPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.unlimitedaliengames.MainActivity;
import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.alienshooter.bonusShooterGame.BonusRound;
import com.example.unlimitedaliengames.alienshooter.mainShooterGame.AlienShooter;
import com.example.unlimitedaliengames.userdata.User;

public class GameOverActivity extends AppCompatActivity {
    public static final String PASS_USER = "passUser";
    private User user;
    private View restart, customize, backToMenu;
    private TextView textPoints, friendly, evil;
    private int points, correct, incorrect;
    private String time, friendlyCustom, evilCustom;
    public final static String PASS_TIME = "pass time";
    public final static String PASS_FRIENDLY = "pass friendly";
    public final static String PASS_EVIL = "pass evil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        customize = findViewById(R.id.Customize)
        ;
        setCustomizeListener();
        backToMenu = findViewById(R.id.BackToMenu);
        setReturnListener();
        restart = findViewById(R.id.Restart);
        setListenerRestart();

        retrieveData();

        textPoints = findViewById(R.id.Points);
        setPoints();
        friendly = findViewById(R.id.FriendlyAlien);
        setFriendly();
        evil = findViewById(R.id.EvilAlien);
        setEvil();

        saveData();

    }

    private void retrieveData() {
        Intent intent = getIntent();
        if (intent.getStringExtra("from").equals("notBonus")) {
            points = intent.getIntExtra(AlienShooter.POINTS, 0);
            user = (User) intent.getSerializableExtra(PASS_USER);
            time = intent.getStringExtra(AlienShooter.TIME);
            friendlyCustom = intent.getStringExtra(AlienShooter.FRIENDLY);
            evilCustom = intent.getStringExtra(AlienShooter.EVIL);
            correct = intent.getIntExtra(AlienShooter.CORRECT, 0);
            incorrect = intent.getIntExtra(AlienShooter.INCORRECT, 0);
        } else {
            points = intent.getIntExtra(BonusRound.POINTS, 0);
            user = (User) intent.getSerializableExtra(BonusRound.PASS_USER);
            time = intent.getStringExtra(BonusRound.TIME);
            friendlyCustom = intent.getStringExtra(BonusRound.FRIENDLY);
            evilCustom = intent.getStringExtra(BonusRound.EVIL);
            correct = intent.getIntExtra(BonusRound.CORRECT, 0);
            incorrect = intent.getIntExtra(BonusRound.INCORRECT, 0);
        }

    }

    private void saveData() {
        user.updateTotalPoints(points);
        user.updateGamesPlayed(1);
        if (time.equals("15 seconds")) {
            user.updateTimePlayed(15);
        } else {
            user.updateTimePlayed(30);
        }
    }

    /**
     * set the total points
     */
    private void setPoints() {
        String text = "Total Points: " + points;
        textPoints.setText(text);
    }

    /**
     * set the number of friendly aliens clicked
     */
    private void setFriendly() {
        String text = "Friendly Aliens Shot: " + incorrect;
        friendly.setText(text);
    }

    /**
     * set the number of evil alien clicked
     */
    private void setEvil() {
        String text = "Evil Aliens Shot: " + correct;
        evil.setText(text);
    }

    /**
     * restart the game with all the same customization
     */
    private void setListenerRestart() {
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartActivity();
                finish();
            }
        });
    }

    /**
     * reset the customization before starting the game
     */
    private void setCustomizeListener() {
        customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customizeActivity();
                finish();
            }
        });
    }

    /**
     * return to menu
     */
    private void setReturnListener() {
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
                finish();
            }
        });
    }

    /**
     * start activity with the new intent
     */
    private void customizeActivity() {
        Intent intent = new Intent(this, CustomizeActivity.class);
        intent.putExtra(PASS_USER, user);
        startActivity(intent);
    }

    /**
     * create an intent to pass the information of customization to AlienShooter class
     */
    private void restartActivity() {
        Intent intent = new Intent(this, AlienShooter.class);
        intent.putExtra(PASS_EVIL, evilCustom);
        intent.putExtra(PASS_TIME, time);
        intent.putExtra(PASS_FRIENDLY, friendlyCustom);
        intent.putExtra(PASS_USER, user);
        startActivity(intent);
    }

    /**
     * start activity with the new intent
     */
    private void menu() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(PASS_USER, user);
        startActivity(intent);
    }
}
