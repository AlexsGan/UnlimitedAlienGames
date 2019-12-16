package com.example.unlimitedaliengames.alienshooter.bonusShooterGame;

import android.os.Handler;

public class BonusRoundPresenter {

    private BonusRoundView view;
    private BonusRoundManager rules;

    /**
     * initialize BonusRoundPresenter
     *
     * @param view the instance of BonusRoundView
     */
    BonusRoundPresenter(BonusRoundView view) {
        this.view = view;
        this.rules = new BonusRoundManager();

    }

    /**
     * return true when the bullet hit the ufo and false otherwise
     *
     * @param bulletX the x coordinate for bullet
     * @param bulletY the y coordinate for bullet
     * @param ufoX    the x coordinate for ufo
     * @param ufoY    the y coordinate for ufo
     */
    boolean checkOnHit(float bulletX, float bulletY, float ufoX, float ufoY) {
        return rules.checkIfHit(bulletX, bulletY, ufoX, ufoY);
    }

    /**
     * number of bullet remaining after decreasing it by one
     */
    int getBullet() {
        rules.decreaseNumBullets();
        return rules.getNumBullets();
    }

    /**
     * check if the bullet is the last bullet
     */
    void checkLastBullet() {
        if (!canShoot()) {
            endBonusRound();
        }
    }

    /**
     * ready to end after 1.5 seconds delay
     */
    private void endBonusRound() {
        String text = "Out of Ammo";
        view.setShoot(text);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.endingDescription();
                view.setExitButton();
                view.changeDescriptionInvisibility();
            }
        }, 1500);
    }

    /**
     * return true when the number of bullets is greater than 0 and false otherwise
     */
    boolean canShoot() {
        return rules.getNumBullets() > 0;
    }

    /**
     * increase points by one
     */
    void increasePoint() {
        rules.increasePoints();
    }

    /**
     * update points
     */
    void updatePoints() {
        view.updatePointText(rules.getPoints());
    }

    /**
     * getter for the points in rules
     */
    public int getPoints() {
        return rules.getPoints();
    }
}