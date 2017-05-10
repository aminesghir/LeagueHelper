package com.aminesghir.leaguehelper.Data.Model;

import com.aminesghir.leaguehelper.Data.Model.StaticData.Champion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 04/05/2017.
 */

public class GameSummoner {

    private Game game;
    private Summoner summoner;
    private long gameId;
    private String region;
    private int queueId;
    private int season;
    private long timestamp;
    private Champion champion;
    private String role;
    private String lane;
    private boolean win;

    public Summoner getSummoner() {
        return summoner;
    }

    public void setSummoner(Summoner summoner) {
        this.summoner = summoner;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    public long getGameId() {
        return gameId;
    }

    public String getLane() {
        return lane;
    }

    public String getRole() {
        return role;
    }


    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getQueueId() {
        return queueId;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public String getQueue(){
        switch (queueId){
            case 420 :
                return "Ranked 5x5";
            case 410:
                return "Normal 5x5";
            case 65:
                return "ARAM";
        }
        return "Unknown Queue";
    }

    public boolean isWin() {
        return this.win;
    }

    public void setWin(){
        if(this.getGame().getWinners() != null) {

            boolean valueSet = false;
            ArrayList<Teammate> winners = new ArrayList<>(this.getGame().getWinners());
            for (int i = 0; i < winners.size(); i++) {
                if (winners.get(i).getSummoner().getId() == this.getSummoner().getId()) {
                    this.win = true;
                    valueSet = true;
                }
            }
            if (!valueSet) {
                this.win = false;
            }
        }
    }



    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
