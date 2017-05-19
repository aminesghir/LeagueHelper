package com.aminesghir.leaguehelper.Data.Model;

import com.aminesghir.leaguehelper.Data.Model.StaticData.Champion;

import java.util.List;

/**
 * Created by user on 19/05/2017.
 */

public class LiveGame extends Game {
    private List<Champion> bannedChampions;
    private long gameStart;
    private int gameLength;

    public List<Champion> getBannedChampions() {
        return bannedChampions;
    }

    public void setBannedChampions(List<Champion> bannedChampions) {
        this.bannedChampions = bannedChampions;
    }

    public long getGameStart() {
        return gameStart;
    }

    public void setGameStart(long gameStart) {
        this.gameStart = gameStart;
    }

    public int getGameLength() {
        return gameLength;
    }

    public void setGameLength(int gameLength) {
        this.gameLength = gameLength;
    }
}
