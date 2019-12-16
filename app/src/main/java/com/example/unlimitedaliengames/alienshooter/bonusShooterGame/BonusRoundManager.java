package com.example.unlimitedaliengames.alienshooter.bonusShooterGame;

class BonusRoundManager {
    private int points;
    private int numBullets = 5;

    /**
     * initialize the presenter and points
     */
    BonusRoundManager() {
        points = 0;
    }

    /**
     * getter for points
     */
    int getPoints() {
        return points;
    }

    /**
     * increase the points
     */
    void increasePoints() {
        points += 1;
    }

    /**
     * getter for numBullets
     */
    int getNumBullets() {
        return numBullets;
    }

    /**
     * decrease numBullets by 1
     */
    void decreaseNumBullets() {
        numBullets -= 1;
    }

    /**
     * check if the bullet hit the ufo
     *
     * @param bulletX the x coordinate for bullet
     * @param bulletY the y coordinate for bullet
     * @param ufoX    the x coordinate for ufo
     * @param ufoY    the y coordinate for ufo
     */
    boolean checkIfHit(float bulletX, float bulletY, float ufoX, float ufoY) {
        return (bulletX - 200 < ufoX && ufoX < bulletX + 200 &&
                bulletY - 200 < ufoY && ufoY < bulletY + 200);
    }
}
