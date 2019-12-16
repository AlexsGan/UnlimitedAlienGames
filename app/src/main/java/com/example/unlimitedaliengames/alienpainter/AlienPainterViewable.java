package com.example.unlimitedaliengames.alienpainter;

interface AlienPainterViewable {

    void updateTimer(int time);

    void timerExpired();

    void updateStats();

    void updateLanguage();

    void showPlayerWon();
}
