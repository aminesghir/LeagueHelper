package com.aminesghir.leaguehelper.Data.Model;

import java.util.List;

/**
 * Created by user on 17/05/2017.
 */

public class LeagueInfo {

    private String leagueName;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int losses;
    private String queue;
    private List<String> status;

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String name) {
        this.leagueName = name;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }
}
