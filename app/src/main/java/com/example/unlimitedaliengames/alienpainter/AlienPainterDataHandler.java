package com.example.unlimitedaliengames.alienpainter;


import com.example.unlimitedaliengames.userdata.User;


/**
 * A class containing methods that handle recording of statistics for the current user
 */
class AlienPainterDataHandler {

    /**
     * records the statistics of currUser
     */
    void recordStats(int gamesPlayed, int totalTime, int points, User currUser) {
        currUser.updateGamesPlayed(gamesPlayed);
        currUser.updateTimePlayed(totalTime);
        currUser.updateTotalPoints(points);
    }
}
