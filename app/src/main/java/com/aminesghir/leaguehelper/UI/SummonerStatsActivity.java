package com.aminesghir.leaguehelper.UI;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.Data.JsonParser;
import com.aminesghir.leaguehelper.R;
import com.aminesghir.leaguehelper.Data.Model.Summoner;

public class SummonerStatsActivity extends AppCompatActivity {

    private String summonerName;
    private Summoner summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner_stats);

        DisplayDataTask displayData = new DisplayDataTask();
        displayData.execute();



    }


    private class DisplayDataTask extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {
            getSummonerNameFromIntent();
            ((TextView)findViewById(R.id.title)).setText(summonerName);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return DataProvider.getSummonerByName(summonerName);
        }

        @Override
        protected void onPostExecute(String s) {
            summoner = JsonParser.jsonToSummoner(s);
            if (summoner != null) {
                DisplayLeagueDataTask displayLeagueDataTask = new DisplayLeagueDataTask();
                displayLeagueDataTask.execute();
            }else{
                Toast.makeText(getApplicationContext(), "Summoner not found", Toast.LENGTH_LONG).show();
                SummonerStatsActivity.this.onBackPressed();
            }
        }
    }

    private class DisplayLeagueDataTask extends AsyncTask<Void, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            return DataProvider.getLeagueData(summoner.getId());
        }

        @Override
        protected void onPostExecute(String s) {
            JsonParser.addLeagueData(summoner, s);
            displaySummonerInfo();
        }
    }

    private void displaySummonerInfo() {

        ((TextView)findViewById(R.id.idTextView)).setText(summoner.getName());
        ((TextView)findViewById(R.id.nameTextView)).setText(summoner.getTier()+ " - " +summoner.getRank());
        ((TextView)findViewById(R.id.levelTextView)).setText(String.valueOf(summoner.getLeaguePoints()));
        ((ProgressBar)findViewById(R.id.circularProgressbar)).setProgress((int)summoner.getWinrate());
        ((TextView)findViewById(R.id.accountIdTextView)).setText(String.valueOf(String.valueOf(summoner.getWinrate()).substring(0,2) + "%"));

        SummonerDetailActivity.setSummoner(summoner);
        SummonerDetailActivity.setAccountId(summoner.getAccountId());

    }

    private void getSummonerNameFromIntent() {
        summonerName = getParent().getIntent().getStringExtra("SUMMONER_NAME");
    }
}
