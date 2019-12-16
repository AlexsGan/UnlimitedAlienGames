package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

import android.os.CountDownTimer;


/**
 * Countdown timer class for the alien shooter game
 */
class Timer extends CountDownTimer {
    private boolean isActive = false;
    private AlienShooterView view;
    private long timeLeft;

    Timer(AlienShooterView view, long time) {
        super(time, 1000);
        this.view = view;
    }

    /**
     * Every second, this method is called to update the timer
     * @param seconds the time remaining on the countdwon timer in milliseconds
     */
    public void onTick(long seconds) {
        String text = "Time: " + seconds / 1000;
        timeLeft = seconds;
        view.updateTimer(text);
    }

    /**
     * Setting the Boolean indicating if the timer is currently counting down
     */
    void setActive() {
        isActive = true;
    }

    /**
     * Retrieves data on if the timer is active or not
     * @return the boolean indicating if the timer is currently counting down
     */
    boolean getIsActive() {
        return isActive;
    }

    /**
     * Executes when timer is over to carry out tasks to end the game
     */
    public void onFinish() {
        isActive = false;
        cancel();
        view.finishGame();
    }

    /**
     * Getter for the time remaining on the timer
     * @return the timer remaining on the timer
     */
    long getTimeLeft(){
        return timeLeft;
    }

}
