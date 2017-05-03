package com.aminesghir.leaguehelper;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class SummonerDetailActivity extends AppCompatActivity {

    private String summonerName;

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
            try {
                JSONObject json = new JSONObject(s);
                ((TextView)findViewById(R.id.idTextView)).setText(json.getJSONObject(summonerName.toLowerCase()).getString("id"));
                ((TextView)findViewById(R.id.nameTextView)).setText(json.getJSONObject(summonerName.toLowerCase()).getString("name"));
                ((TextView)findViewById(R.id.levelTextView)).setText(json.getJSONObject(summonerName.toLowerCase()).getString("summonerLevel"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void getSummonerNameFromIntent() {
        this.summonerName = getIntent().getStringExtra("SUMMONER_NAME");
    }
}
