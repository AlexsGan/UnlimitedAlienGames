package com.example.unlimitedaliengames.alienshooter.bonusShooterGame;

public interface BonusRoundView {
    void updatePointText(int points);

    void shoot(int bulletLeft);

    void setShoot(String text);

    void endingDescription();

    void setExitButton();

    void changeDescriptionInvisibility();
}