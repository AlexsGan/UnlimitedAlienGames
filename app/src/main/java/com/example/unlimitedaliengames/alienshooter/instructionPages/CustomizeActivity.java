package com.example.unlimitedaliengames.alienshooter.instructionPages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.alienshooter.mainShooterGame.AlienShooter;
import com.example.unlimitedaliengames.userdata.User;

public class CustomizeActivity extends AppCompatActivity {
    public static final String PASS_USER = "passUser";
    private User user;
    public final static String PASS_TIME = "pass time";
    public final static String PASS_FRIENDLY = "pass friendly";
    public final static String PASS_EVIL = "pass evil";
    private View fifteen, thirty, red, black, blue, yellow, proceed;
    private String time, friendly, evil;
    private boolean choseEvil, choseFriendly, choseTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        fifteen = findViewById(R.id.FIFTEEN);
        setListenerFifteen();
        thirty = findViewById(R.id.THIRTY);
        setListenerThirty();
        red = findViewById(R.id.RED);
        setListenerRed();
        black = findViewById(R.id.BLACK);
        setListenerBlack();
        blue = findViewById(R.id.BLUE);
        setListenerBlue();
        yellow = findViewById(R.id.YELLOW);
        setListenerYellow();
        proceed = findViewById(R.id.proceed);
        setListenerProceed();
        choseEvil = false;
        choseFriendly = false;
        choseTime = false;
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra(PASS_USER);
    }

    /**
     * proceed the game if the user choose all the customization
     */
    private void setListenerProceed() {
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choseEvil && choseFriendly && choseTime) {
                    proceedToGame();
                }
            }
        });
    }

    /**
     * pass in the information about the customization to AlienShooter
     */
    private void proceedToGame() {
        Intent intent = new Intent(this, AlienShooter.class);
        intent.putExtra(PASS_TIME, time);
        intent.putExtra(PASS_FRIENDLY, friendly);
        intent.putExtra(PASS_USER, user);
        intent.putExtra(PASS_EVIL, evil);
        startActivity(intent);
        finish();
    }

    /**
     * set the time to 15 seconds when the button is clicked
     */
    private void setListenerFifteen() {
        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseTime) {
                    time = fifteen.getContentDescription().toString();
                    thirty.setVisibility(View.INVISIBLE);
                    choseTime = true;
                }
            }
        });
    }

    /**
     * set the time to 30 seconds when the button is clicked
     */
    private void setListenerThirty() {
        thirty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseTime) {
                    time = thirty.getContentDescription().toString();
                    fifteen.setVisibility(View.INVISIBLE);
                    choseTime = true;
                }
            }
        });
    }

    /**
     * set the friendly alien to be blue
     */
    private void setListenerBlue() {
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseFriendly) {
                    friendly = blue.getContentDescription().toString();
                    yellow.setVisibility(View.INVISIBLE);
                    choseFriendly = true;
                }
            }
        });
    }

    /**
     * set the friendly alien to be yellow
     */
    private void setListenerYellow() {
        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseFriendly) {
                    friendly = yellow.getContentDescription().toString();
                    blue.setVisibility(View.INVISIBLE);
                    choseFriendly = true;
                }
            }
        });
    }

    /**
     * set evil alien to be red
     */
    private void setListenerRed() {
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseEvil) {
                    evil = red.getContentDescription().toString();
                    black.setVisibility(View.INVISIBLE);
                    choseEvil = true;
                }
            }
        });
    }

    /**
     * set the evil alien to be black
     */
    private void setListenerBlack() {
        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!choseEvil) {
                    evil = black.getContentDescription().toString();
                    red.setVisibility(View.INVISIBLE);
                    choseEvil = true;
                }
            }
        });
    }

}
