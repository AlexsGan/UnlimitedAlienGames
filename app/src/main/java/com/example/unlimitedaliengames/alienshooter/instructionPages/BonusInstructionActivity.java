package com.example.unlimitedaliengames.alienshooter.instructionPages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.alienshooter.bonusShooterGame.BonusRound;
import com.example.unlimitedaliengames.alienshooter.mainShooterGame.AlienShooter;
import com.example.unlimitedaliengames.userdata.User;

public class BonusInstructionActivity extends AppCompatActivity {
    public static final String PASS_USER = "passUser";
    private User user;
    private Button startButton;
    public final static String POINTS = "pass points";
    public final static String CORRECT = "pass friendly";
    public final static String INCORRECT = "pass evil";
    public final static String TIME = "pass time";
    public final static String FRIENDLY = "pass friendly custom";
    public final static String EVIL = "pass evil custom";
    private String time, friendly, evil;
    private int points, correct, incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus_instruction);
        startButton = findViewById(R.id.BonusStartButton);
        setBonusStartListener();

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra(PASS_USER);
        time = intent.getStringExtra(AlienShooter.TIME);
        friendly = intent.getStringExtra(AlienShooter.FRIENDLY);
        evil = intent.getStringExtra(AlienShooter.EVIL);
        points = intent.getIntExtra(AlienShooter.POINTS, 0);
        correct = intent.getIntExtra(AlienShooter.CORRECT, 0);
        incorrect = intent.getIntExtra(AlienShooter.INCORRECT, 0);
    }

    private void setBonusStartListener() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBonusRound();
                finish();
            }
        });
    }

    private void startBonusRound() {
        Intent intent = new Intent(this, BonusRound.class);
        intent.putExtra(POINTS, points);
        intent.putExtra(CORRECT, correct);
        intent.putExtra(INCORRECT, incorrect);
        intent.putExtra(TIME, time);
        intent.putExtra(EVIL, evil);
        intent.putExtra(PASS_USER, user);
        intent.putExtra(FRIENDLY, friendly);
        startActivity(intent);
    }
}
