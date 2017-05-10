package com.aminesghir.leaguehelper.UI;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.aminesghir.leaguehelper.Data.Model.Summoner;

public class SummonerDetailActivity extends TabActivity {

    private static long accountId;
    private static Summoner summoner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);

        TabHost mTabHost = getTabHost();

        mTabHost.addTab(mTabHost.newTabSpec("stats").setIndicator("Stats").setContent(new Intent(this  ,SummonerStatsActivity.class )));
        mTabHost.addTab(mTabHost.newTabSpec("history").setIndicator("History").setContent(new Intent(this , MatchListActivity.class )));
        mTabHost.setCurrentTab(0);


    }

    public static Summoner getSummoner() {
        return summoner;
    }

    public static void setSummoner(Summoner summoner) {
        SummonerDetailActivity.summoner = summoner;
    }


    public static void setAccountId(long id){
        accountId = id;
    }
}