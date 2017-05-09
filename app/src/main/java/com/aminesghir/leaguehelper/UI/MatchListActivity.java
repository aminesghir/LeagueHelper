package com.aminesghir.leaguehelper.UI;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.Data.JsonParser;
import com.aminesghir.leaguehelper.Data.Model.GameSummoner;
import com.aminesghir.leaguehelper.R;
import com.aminesghir.leaguehelper.UI.Adapter.GameSummonerAdapter;

import java.util.List;

public class MatchListActivity extends AppCompatActivity {

    private long accountId;
    private SwipeRefreshLayout swiperefresh;
    private GameSummonerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        swiperefresh = (SwipeRefreshLayout)findViewById(R.id.swiperefresh);

        refreshHistory();

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshHistory();
            }
        });
    }

    private void refreshHistory() {
        (new DisplayHistoryTask()).execute();
    }

    private class DisplayHistoryTask extends AsyncTask<Void, Void, List<GameSummoner>>{

        @Override
        protected void onPreExecute() {
            swiperefresh.setRefreshing(true);
            getAccountIdFromIntent();
        }

        @Override
        protected List<GameSummoner> doInBackground(Void... params) {
            return JsonParser.jsonToGameSummoners(DataProvider.getMatchListByAccountId(accountId));
        }

        @Override
        protected void onPostExecute(final List<GameSummoner> gameSummoners) {
            adapter = new GameSummonerAdapter(gameSummoners);
            ListView history =(ListView)findViewById(R.id.historyListView);
            history.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MatchListActivity.this, GameDetailActivity.class);
                    intent.putExtra("GAME_ID", id);
                    startActivity(intent);
                }
            });

            swiperefresh.setRefreshing(false);
        }
    }

    public void getAccountIdFromIntent(){
        accountId = getIntent().getLongExtra("ACCOUNT_ID", 0);
    }
}
