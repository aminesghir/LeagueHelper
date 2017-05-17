package com.aminesghir.leaguehelper.Data.Model;

import java.text.DecimalFormat;

public class Summoner {

    private long id;
    private int iconId;
    private long accountId;
    private String name;
    private int level;

    private LeagueInfo soloQData;
    private LeagueInfo flexSRData;
    private LeagueInfo flexTTData;

    public Summoner(){
        this.level = 30;
    }

    public LeagueInfo getSoloQData() {
        return soloQData;
    }

    public void setSoloQData(LeagueInfo soloQData) {
        this.soloQData = soloQData;
    }

    public LeagueInfo getFlexSRData() {
        return flexSRData;
    }

    public void setFlexSRData(LeagueInfo flexSRData) {
        this.flexSRData = flexSRData;
    }

    public LeagueInfo getFlexTTData() {
        return flexTTData;
    }

    public void setFlexTTData(LeagueInfo flexTTData) {
        this.flexTTData = flexTTData;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public long getId(){ return this.id; }

    public void setId(long i){ this.id = i; }

    public long getAccountId(){ return this.accountId; }

    public void setAccountId(long i){ this.accountId = i; }

    public String getName(){ return this.name; }

    public void setName(String n){ this.name = n; }

    public int getLevel(){ return this.level; }

    public void setLevel(int l){ this.level = l; }

    public boolean equals(Summoner s){
        return (this.id == s.getId());
    }

    public float getWinrate(){
        if(this.getSoloQData() != null) {
            float p = (this.getSoloQData().getWins() * 100.0f) / (this.getSoloQData().getLosses() + this.getSoloQData().getWins());
            return p;
        }
        return -1;
    }

    public String getPrintableElo(){
        if (this.soloQData != null) {
            return this.getSoloQData().getTier() +
                    ((this.getSoloQData().getTier().equals("MASTER") || this.getSoloQData().getTier().equals("CHALLENGER"))?"":" " +this.getSoloQData().getRank()) +
                    " - "
                    + String.valueOf(this.getSoloQData().getLeaguePoints());
        }else{
            return "Level " + String.valueOf(this.getLevel());
        }
    }
}
