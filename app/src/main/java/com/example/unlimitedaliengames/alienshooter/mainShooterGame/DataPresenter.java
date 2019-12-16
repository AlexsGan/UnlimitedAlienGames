package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

/**
 * Presenter class that handles score/data related tasks
 */
class DataPresenter {
    private AlienShooterData data;
    private AlienShooterView view;

    DataPresenter(AlienShooterView view, AlienShooterData data) {
        this.data = data;
        this.view = view;
    }

    /**
     * getter for the points user has scored
     *
     * @return the current points the user has scored
     */
    int getPoints() {
        return data.get_point();
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
     * updates the labels of current game statistics
     */
    void updatePoints() {
        view.updatePoints(getPoints(), getCorrect(), getIncorrect());
    }
}
