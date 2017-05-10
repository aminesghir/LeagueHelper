package com.aminesghir.leaguehelper.UI;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        ((TextView)findViewById(R.id.accountIdTextView)).setText(String.valueOf(100*((float)summoner.getWins()/((float)summoner.getLoses()+(float)summoner.getWins())))+ " %");

        SummonerDetailActivity.setSummoner(summoner);
        SummonerDetailActivity.setAccountId(summoner.getAccountId());
        /*
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummonerStatsActivity.this, MatchListActivity.class);
                //intent.putExtra("ACCOUNT_ID", summoner.getAccountId());
                startActivity(intent);
            }
        });
        */
    }

    private void getSummonerNameFromIntent() {
        summonerName = getParent().getIntent().getStringExtra("SUMMONER_NAME");
    }
}
