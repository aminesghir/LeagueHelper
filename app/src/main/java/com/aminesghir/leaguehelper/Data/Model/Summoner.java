package com.aminesghir.leaguehelper.Data.Model;

public class Summoner {

    private long id;
    private long accountId;
    private String name;
    private int level;

    public long getId(){ return this.id; }

    public void setId(long i){ this.id = i; }

    public long getAccountId(){ return this.accountId; }

    public void setAccountId(long i){ this.accountId = i; }

    public String getName(){ return this.name; }

    public void setName(String n){ this.name = n; }

    public int getLevel(){ return this.level; }

    public void setLevel(int l){ this.level = l; }
}
