package com.example.unlimitedaliengames.alienguesser;

/*
The Interface for Guesser game, accessed by problemHandler.
 */
public interface GuesserView {
    /*
    Change the game state between waiting for guess and waiting for problem.
     */
    void swapGameState();

    /*
    Write to the main window.
     */
    void updateProblemView(String message);

    /*
    Write to the main window, to be used for writing scores only.
     */
    void updateScoreView(String message);

    /*
    Erase the main window.
     */
    void clearProblemView();

    /*
    Finishes game and prepare to return.
     */
    void finishGuess();

    /*
    Return the String from resources with given name.
     */
    String fetchFromRes(String name);
}