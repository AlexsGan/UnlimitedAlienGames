package com.example.unlimitedaliengames.alienshooter.bonusShooterGame;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unlimitedaliengames.R;
import com.example.unlimitedaliengames.alienshooter.instructionPages.BonusInstructionActivity;
import com.example.unlimitedaliengames.alienshooter.instructionPages.GameOverActivity;
import com.example.unlimitedaliengames.userdata.User;

import java.util.ArrayList;
import java.util.List;

public class BonusRound extends AppCompatActivity implements BonusRoundView {
    public static final String PASS_USER = "passUser";
    private User user;
    public final static String POINTS = "pass points";
    public final static String CORRECT = "pass friendly";
    public final static String INCORRECT = "pass evil";
    public final static String TIME = "pass time";
    public final static String FRIENDLY = "pass friendly custom";
    public final static String EVIL = "pass evil custom";
    private String time, friendly, evil;
    private int points, correct, incorrect;
    private TextView text, endMessage;
    private ImageView canon, ufo;
    private Button shoot, endButton;
    private BonusRoundPresenter presenter;
    private List<ImageView> bullets;
    private boolean clickable = false;

    /**
     * This method is automatically called when the User scores more then 5 points.
     *
     * @param savedInstanceState the instanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus_round);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra(PASS_USER);
        time = intent.getStringExtra(BonusInstructionActivity.TIME);
        friendly = intent.getStringExtra(BonusInstructionActivity.FRIENDLY);
        evil = intent.getStringExtra(BonusInstructionActivity.EVIL);
        points = intent.getIntExtra(BonusInstructionActivity.POINTS, 0);
        correct = intent.getIntExtra(BonusInstructionActivity.CORRECT, 0);
        incorrect = intent.getIntExtra(BonusInstructionActivity.INCORRECT, 0);


        bullets = new ArrayList<>();


        //add 5 bullets
        ImageView bullet1 = findViewById(R.id.Bullet1);
        ImageView bullet2 = findViewById(R.id.Bullet2);
        ImageView bullet3 = findViewById(R.id.Bullet3);
        ImageView bullet4 = findViewById(R.id.Bullet4);
        ImageView bullet5 = findViewById(R.id.Bullet5);

        bullets.add(bullet1);
        bullets.add(bullet2);
        bullets.add(bullet3);
        bullets.add(bullet4);
        bullets.add(bullet5);

        canon = findViewById(R.id.Canon);

        text = findViewById(R.id.Points);
        endMessage = findViewById(R.id.BonusResult);
        endMessage.setVisibility(View.INVISIBLE);

        ufo = findViewById(R.id.Ufo);
        presenter = new BonusRoundPresenter(this);
        endButton = findViewById(R.id.ExitButton);
        EndButtonListener();

        endButton.setVisibility(View.INVISIBLE);

//        intent = getIntent();

        shoot = findViewById(R.id.Shoot);
        ShootListener();

        canonMotion();
        ufoMotion();

    }

    /**
     * set the text for button
     *
     * @param text the string text
     */
    public void setShoot(String text) {
        shoot.setText(text);
    }

    /**
     * end button listener that finishes the Bonus Game
     */
    private void EndButtonListener() {
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickable) {
                    Intent intent = createIntent();
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    /**
     * shoots the bullets when the shoot button is clicked
     */
    private void ShootListener() {
        shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.canShoot()) {
                    shoot(presenter.getBullet());
                    presenter.checkLastBullet();
                }

            }
        });
    }

    /**
     * shoot the bullet by setting it's x and y value
     *
     * @param bulletLeft the number of bullets left
     */
    public void shoot(int bulletLeft) {
        float xValue = canon.getX();
        float yValue = canon.getY();
        bullets.get(bulletLeft).setVisibility(View.VISIBLE);
        shootingMotion(bullets.get(bulletLeft), xValue, yValue);

    }

    /**
     * Move ufo sideways so that it takes 1 second
     */
    private void ufoMotion() {
        leftToRightAnimation(ufo, 500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!clickable)
                    ufoMotion();
            }
        }, 1000);
    }

    /**
     * Move the canon sideways so that it takes 2 second
     */
    private void canonMotion() {
        leftToRightAnimation(canon, 1000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!clickable)
                    canonMotion();
            }
        }, 2000);
    }

    /**
     * Animation that moves the view left to right and right to left
     *
     * @param v the view that moves
     * @param t the number of bullets left
     */
    private void leftToRightAnimation(View v, int t) {
        ObjectAnimator canonMoveLeft = ObjectAnimator.ofFloat(v, "translationX", 0f);
        canonMoveLeft.setDuration(t);
        ObjectAnimator canonMoveRight = ObjectAnimator.ofFloat(v, "translationX", 870f);
        canonMoveRight.setDuration(t);
        AnimatorSet moveCanon = new AnimatorSet();
        moveCanon.play(canonMoveRight).before(canonMoveLeft);
        moveCanon.start();
    }

    /**
     * shooting Animation that sends bullet upward
     *
     * @param v the view of the object moving
     * @param x the X coordinate
     * @param y the y coordinate
     */
    private void shootingMotion(final View v, final float x, final float y) {
        setBulletLocation(v, x, y);
        ObjectAnimator animation = ObjectAnimator.ofFloat(v, "translationY", -300f);
        animation.setDuration(2000);
        animation.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkOnHit(v);
            }
        }, 1300);
    }

    /**
     * Sets the bullet X and Y value
     *
     * @param v the view of the object moving
     * @param x the X coordinate
     * @param y the y coordinate
     */
    private void setBulletLocation(View v, float x, float y) {
        v.setX(x);
        v.setY(y);
    }

    /**
     * check if the bullet is conflicted with ufo
     *
     * @param v the view that is conflicted with the ufo
     */
    private void checkOnHit(View v) {
        float bulletX = v.getX();
        float bulletY = v.getY();
        float ufoX = ufo.getX();
        float ufoY = ufo.getY();

        if (presenter.checkOnHit(bulletX, bulletY, ufoX, ufoY)) {
            presenter.increasePoint();
            presenter.updatePoints();
            v.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * update user's total point
     *
     * @param point the view that is conflicted with the ufo
     */
    public void updatePointText(int point) {
        String textPoint = "points: " + point;
        text.setText(textPoint);
    }

    /**
     * Describes how you did in Bonus game
     */
    public void endingDescription() {
        String endDescription = "Congratulation! you earned an extra " + presenter.getPoints()
                + " point(s) and have increased your total from " + points + " points to " +
                (points + presenter.getPoints()) + " points.";
        endMessage.setText(endDescription);
    }

    /**
     * set the exit button to be visible
     */
    public void setExitButton() {
        clickable = true;
        endButton.setVisibility(View.VISIBLE);
    }

    public void changeDescriptionInvisibility() {
        endMessage.setVisibility(View.VISIBLE);
    }

    /**
     * create intent to pass on the information to GameOverActivity
     */
    private Intent createIntent() {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra(POINTS, presenter.getPoints() + points);
        intent.putExtra(PASS_USER, user);
        intent.putExtra(CORRECT, correct);
        intent.putExtra(INCORRECT, incorrect);
        intent.putExtra(TIME, time);
        intent.putExtra(EVIL, evil);
        intent.putExtra(FRIENDLY, friendly);
        intent.putExtra("from", "bonus");
        return intent;
    }
}


