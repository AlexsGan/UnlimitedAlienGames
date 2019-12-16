package com.example.unlimitedaliengames.alienshooter.mainShooterGame;

/**
 * Interface implemented by the AlienShooter class to maintain dependency inversion
 */
interface AlienShooterView {

    void updateTimer(String text);

    void changeAlienImage();

    void updatePoints(int points, int correct, int incorrect);

    void finishGame();
}
