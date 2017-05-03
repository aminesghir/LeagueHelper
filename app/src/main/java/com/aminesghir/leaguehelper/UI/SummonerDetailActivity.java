package com.aminesghir.leaguehelper.UI;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.Data.JsonSummonerParser;
import com.aminesghir.leaguehelper.R;
import com.aminesghir.leaguehelper.Data.Model.Summoner;
import org.json.JSONException;
import org.json.JSONObject;

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
            summoner = JsonSummonerParser.jsonToSummoner(s);

            displaySummonerInfo();
        }
    }

    private void displaySummonerInfo() {

        ((TextView)findViewById(R.id.idTextView)).setText(String.valueOf(summoner.getId()));
        ((TextView)findViewById(R.id.nameTextView)).setText(summoner.getName());
        ((TextView)findViewById(R.id.levelTextView)).setText(String.valueOf(summoner.getLevel()));
        ((TextView)findViewById(R.id.accountIdTextView)).setText(String.valueOf(summoner.getAccountId()));
    }

    private void getSummonerNameFromIntent() {
        summonerName = getIntent().getStringExtra("SUMMONER_NAME");
    }
}
