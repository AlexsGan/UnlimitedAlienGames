package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

/**
 * Contains the data of the current game that user is playing
 */
class AlienShooterData {

    private int points;
    private int correct;
    private int incorrect;


    AlienShooterData() {
        points = 0;
        correct = 0;
        incorrect = 0;
    }

    /**
     * getter for instance variable incorrect
     *
     * @return the variable incorrect that contains the data of how many aliens the user incorrectly
     * clicked
     */
    int getIncorrect() {
        return incorrect;
    }

    /**
     * increases the incorrect aliens clicked by one
     */
    void setIncorrect() {
        this.incorrect += 1;
    }

    /**
     * getter for instance variable correct
     *
     * @return the variable incorrect that contains the data of how many aliens the user correctly
     * clicked
     */
    int getCorrect() {
        return correct;
    }

    /**
     * increases the aliens the user has correctly clicked by one
     */
    void setCorrect() {
        this.correct += 1;
    }

    /**
     * getter for the instance variable points
     *
     * @return instance variable points that represents the current points the user has scored
     */
    int get_point() {
        return points;
    }

    /**
     * adds points to the user's score
     *
     * @param p the number of points to be added to the user's score
     */
    void setPoints(int p) {
        points += p;
    }
}

