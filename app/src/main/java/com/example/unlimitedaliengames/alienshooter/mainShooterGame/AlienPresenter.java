package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

import android.view.View;

import java.util.List;

/**
 * Presenter class that handles alien management tasks
 */
class AlienPresenter implements AlienShooterPresenterInterface {
    private AlienShooterManager rules;
    private AlienShooterView view;
    private AlienShooterData data;

    AlienPresenter(AlienShooterView view, AlienShooterData data) {
        this.view = view;
        this.rules = new AlienShooterManager(this);
        this.data = data;
    }

    /**
     * determines if the user clicked a correct alien or not and changes user statistics accordingly
     *
     * @param aliens the list of image buttons that correspond to aliens
     * @param v      the alien image button that was clicked
     */
    void clickedAlien(List<View> aliens, View v) {
        if (rules.checkAlien(v)) {
            data.setPoints(1);
            data.setCorrect();
        } else {
            data.setPoints(-2);
            data.setIncorrect();
        }
        rules.randomize(aliens);
    }

    /**
     * Randomizes the location of the evil alien
     *
     * @param aliens a list containing image buttons that represents all the aliens in this game
     */
    void randomizeAliens(List<View> aliens) {
        rules.randomize(aliens);
    }

    /**
     * Changes the image corresponding the the aliens in the aliens arraylist
     */
    @Override
    public void changeAlienImage() {
        view.changeAlienImage();
    }
}
