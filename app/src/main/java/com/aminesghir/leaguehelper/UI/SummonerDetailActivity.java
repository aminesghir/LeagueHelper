package com.aminesghir.leaguehelper.UI;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.Data.JsonParser;
import com.aminesghir.leaguehelper.R;
import com.aminesghir.leaguehelper.Data.Model.Summoner;

public class SummonerDetailActivity extends AppCompatActivity {

    private String summonerName;
    private Summoner summoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner_detail);

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

            displaySummonerInfo();
        }
    }

    private void displaySummonerInfo() {

        ((TextView)findViewById(R.id.idTextView)).setText(String.valueOf(summoner.getId()));
        ((TextView)findViewById(R.id.nameTextView)).setText(summoner.getName());
        ((TextView)findViewById(R.id.levelTextView)).setText(String.valueOf(summoner.getLevel()));
        ((TextView)findViewById(R.id.accountIdTextView)).setText(String.valueOf(summoner.getAccountId()));

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummonerDetailActivity.this, MatchListActivity.class);
                intent.putExtra("ACCOUNT_ID", summoner.getAccountId());
                startActivity(intent);
            }
        });
    }

    private void getSummonerNameFromIntent() {
        summonerName = getIntent().getStringExtra("SUMMONER_NAME");
    }
}
