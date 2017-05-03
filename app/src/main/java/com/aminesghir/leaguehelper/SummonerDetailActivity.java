package com.aminesghir.leaguehelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SummonerDetailActivity extends AppCompatActivity {

    private String summonerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner_detail);

        getSummonerNameFromIntent();

        ((TextView)findViewById(R.id.title)).setText(summonerName);

        ((TextView)findViewById(R.id.idTextView)).setText(DataProvider.getSummonerByName(summonerName));

    }

    private void getSummonerNameFromIntent() {
        this.summonerName = getIntent().getStringExtra("SUMMONER_NAME");
    }
}
