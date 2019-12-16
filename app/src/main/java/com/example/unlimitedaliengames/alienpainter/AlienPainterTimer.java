package com.example.unlimitedaliengames.alienpainter;

import android.os.CountDownTimer;

/**
 * A class that inherits CountDownTimer and has a few more attributes used to create
 * a timer for the game. This class is used by AlienPainterPresenter
 */
public class AlienPainterTimer extends CountDownTimer {
    private static final long TIMELEFT = 30000;
    private boolean isActive = true;
    private AlienPainterPresenter presenter;

    /**
     * Constructs a new CountDownTimer
     * @param presenter The presenter
     */
    AlienPainterTimer(AlienPainterPresenter presenter) {
        super(TIMELEFT, 1000);
        this.presenter = presenter;
    }

    public void onTick(long seconds) {
        presenter.updateTimer(Math.toIntExact(seconds / 1000));
    }

    /**
     * Sets whether the timer is active or not
     * @param active whether the timer is active
     */
    void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Returns whether the timer is active
     * @return isActive
     */
    boolean getIsActive() {
        return isActive;
    }

    /**
     * Reset the timer
     */
    void reset () {
        this.cancel();
        this.start();
        this.setActive(true);
    }

    /**
     * Called when the CountDownTimer expires
     */
    public void onFinish() {
        isActive = false;
        presenter.timerExpired();
        cancel();
    }
}
