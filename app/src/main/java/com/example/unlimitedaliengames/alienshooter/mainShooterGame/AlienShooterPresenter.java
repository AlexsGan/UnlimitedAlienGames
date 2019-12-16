package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

import android.view.View;

import java.util.List;

/**
 * Facade Class acting as the presenter of this game
 */
class AlienShooterPresenter {
    private DataPresenter data;
    private AlienPresenter alien;


    AlienShooterPresenter(AlienShooterView view) {
        AlienShooterData gameData = new AlienShooterData();
        this.data = new DataPresenter(view, gameData);
        this.alien = new AlienPresenter(view, gameData);
    }

    /**
     * getter for the points user has scored
     *
     * @return the current points the user has scored
     */
    int getPoints() {
        return data.getPoints();
    }

    /**
     * getter for the number of evil aliens user has shot
     *
     * @return the current number of evil aliens shot
     */
    int getCorrect() {
        return data.getCorrect();
    }

    /**
     * getter for the number of good aliens user has shot
     *
     * @return the current number of good aliens shot
     */
    int getIncorrect() {
        return data.getIncorrect();
    }

    /**
     * Randomizes the location of the evil alien
     *
     * @param aliens a list containing image buttons that represents all the aliens in this game
     */
    void randomizeAliens(List<View> aliens) {
        alien.randomizeAliens(aliens);
    }

    /**
     * determines if the user clicked a correct alien or not and changes user statistics accordingly
     *
     * @param aliens the list of image buttons that correspond to aliens
     * @param v      the alien image button that was clicked
     */
    void clickedAlien(List<View> aliens, View v) {
        alien.clickedAlien(aliens, v);
        data.updatePoints();
    }

    /**
     * called when activity/game is closed
     */
    void destroy() {
        data = null;
        alien = null;
    }

}
