package com.aminesghir.leaguehelper.UI;

import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.Data.JsonParser;
import com.aminesghir.leaguehelper.Data.Model.Game;
import com.aminesghir.leaguehelper.R;

public class GameDetailActivity extends AppCompatActivity {

    private long gameId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        (new DisplayGameInfoTask()).execute();

    }

    private class DisplayGameInfoTask extends AsyncTask<Void, Void, Game>{

        @Override
        protected void onPreExecute() {
            getGameIdFromIntent();
        }

        @Override
        protected Game doInBackground(Void... params) {
            return JsonParser.jsonToGame(DataProvider.getMatchDetailById(gameId));
        }

        @Override
        protected void onPostExecute(Game game) {
            String s = "Winners :\n";
            for(int i = 0; i<game.getWinners().size(); i++){
                s = s + game.getWinners().get(i).getSummoner().getName() +" - " +
                        game.getWinners().get(i).getRole() + " - " +
                        game.getWinners().get(i).getLane() + "\n";
            }
            ((TextView)findViewById(R.id.winners)).setText(s);

            s = "Losers :\n";
            for(int i = 0; i<game.getWinners().size(); i++){
                s = s + game.getLosers().get(i).getSummoner().getName() +" " + game.getLosers().get(i).getRole() + " " + game.getLosers().get(i).getLane() + "\n";
            }
            ((TextView)findViewById(R.id.losers)).setText(s);
        }
    }

    public void getGameIdFromIntent(){
        gameId = getIntent().getLongExtra("GAME_ID", 0);
    }
}
