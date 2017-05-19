package com.aminesghir.leaguehelper.Data.Model;

/**
 * Created by Amine on 06/05/2017.
 */

public class Teammate {
    private int participantId;
    private Summoner Summoner;
    private String role;
    private String lane;
    private int championId;
    private boolean winner;
    private int teamId;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public boolean isWinner() {
        return winner;
    }

    public int getChampionId() {
        return championId;
    }

    public String getLane() {
        return lane;
    }

    public String getRole() {
        return role;
    }

    public Summoner getSummoner() {
        return Summoner;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSummoner(Summoner summoner) {
        Summoner = summoner;
    }

    public void setWinner(boolean win) {
        this.winner = win;
    }

}
