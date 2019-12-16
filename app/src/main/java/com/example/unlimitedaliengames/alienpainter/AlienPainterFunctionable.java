package com.example.unlimitedaliengames.alienpainter;

/**
 * An interface implemented by AlienPainterFuntions
 * This interface is used for the purpose of dependency inversion
 */
interface AlienPainterFunctionable {

    void recordButtonPress();

    void updateNumMoves();

    void flipButton();

    void calculatePoints();

    boolean checkWinCondition();

    void setTimeLeft(int timeLeft);

    int getTimeLeft();

    int getNumMoves();

    void setPoints(int points);

    int getPoints();

    void setGamesPlayed(int gamesPlayed);

    int getGamesPlayed();

    void checkBonus();

    void instantReplay();

    void resetGame();

    int getTotalTime();

    void setTotalTime(int time);

    boolean getIsReplaying();

}
