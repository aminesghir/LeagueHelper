package com.aminesghir.leaguehelper.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aminesghir.leaguehelper.Data.Model.Summoner;
import com.aminesghir.leaguehelper.R;

public class SummonerSpectateActivity extends AppCompatActivity {

    private Summoner summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner_spectate);


    }

    public void getSummonerFromIntent(){
        summoner = SummonerDetailActivity.getSummoner();
    }
}
