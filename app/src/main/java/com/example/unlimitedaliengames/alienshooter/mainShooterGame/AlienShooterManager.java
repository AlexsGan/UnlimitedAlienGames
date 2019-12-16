package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

import android.view.View;

import java.util.List;
import java.util.Random;

/**
 * Model class that implements alien management tasks
 */
class AlienShooterManager {

    private static final int numOfAliens = 9;
    private AlienShooterPresenterInterface presenter;

    AlienShooterManager(AlienShooterPresenterInterface presenter) {
        this.presenter = presenter;
    }


    /**
     * randomizes the evil alien location in the list
     *
     * @param aliens list of aliens in this game
     */
    void randomize(List<View> aliens) {
        int random = randomNum();
        randomizeContentDescription(random, aliens);
        presenter.changeAlienImage();
    }

    /**
     * changes the alien image button content description based the type of alien each button is
     * randomly assigned to
     *
     * @param random integer that represents randomly generated location of evil alien in the list
     * @param aliens the list containing all the alien image buttons
     */
    private void randomizeContentDescription(int random, List<View> aliens) {
        for (int i = 0; i < numOfAliens; i++) {
            if (random == i) {
                String text = "red alien";
                aliens.get(i).setContentDescription(text);
            } else {
                String text = "normal alien";
                aliens.get(i).setContentDescription(text);
            }
        }
    }

    /**
     * generates a random number between 0 and 8
     *
     * @return randomly generated number
     */
    private int randomNum() {
        Random random = new Random();
        return random.nextInt(numOfAliens);
    }

    /**
     * checks if a specific alien image button in aliens is an evil or good alien
     *
     * @param v View that represents the alien image button
     * @return boolean that is true when the alien is evil and false when the alien is good
     */
    boolean checkAlien(View v) {
        return v.getContentDescription().equals("red alien");
    }
}
