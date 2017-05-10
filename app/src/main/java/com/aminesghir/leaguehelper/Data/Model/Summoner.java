package com.aminesghir.leaguehelper.Data.Model;

public class Summoner {

    private long id;
    private long accountId;
    private String name;
    private int level;
    private String tier;
    private String rank;
    private int leaguePoints;
    private int wins;
    private int loses;

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

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public Summoner(){
        this.level = 30;
    }

    public long getId(){ return this.id; }

    public void setId(long i){ this.id = i; }

    public long getAccountId(){ return this.accountId; }

    public void setAccountId(long i){ this.accountId = i; }

    public String getName(){ return this.name; }

    public void setName(String n){ this.name = n; }

    public int getLevel(){ return this.level; }

    public void setLevel(int l){ this.level = l; }

    private boolean equals(Summoner s){
        return (this.id == s.getId());
    }
}
