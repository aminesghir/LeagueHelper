package com.aminesghir.leaguehelper.UI;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.Data.JsonParser;
import com.aminesghir.leaguehelper.Data.Model.Game;
import com.aminesghir.leaguehelper.R;
import com.aminesghir.leaguehelper.UI.Adapter.GameAdapter;

import java.util.ArrayList;
import java.util.List;

public class MatchListActivity extends AppCompatActivity {

    private long accountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        (new DisplayHistoryTask()).execute();
    }

    private class DisplayHistoryTask extends AsyncTask<Void, Void, List<Game>>{

        @Override
        protected void onPreExecute() {
            getAccountIdFromIntent();
        }

        @Override
        protected List<Game> doInBackground(Void... params) {
            return JsonParser.jsonToGames(DataProvider.getMatchListByAccountId(accountId));
        }

        @Override
        protected void onPostExecute(List<Game> games) {
            GameAdapter adapter = new GameAdapter(games);
            ((ListView)findViewById(R.id.historyListView)).setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    public void getAccountIdFromIntent(){
        accountId = getIntent().getLongExtra("ACCOUNT_ID", 0);
    }
}
