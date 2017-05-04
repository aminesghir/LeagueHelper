package com.aminesghir.leaguehelper.Data;

import com.aminesghir.leaguehelper.Data.Model.Game;
import com.aminesghir.leaguehelper.Data.Model.Summoner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 03/05/2017.
 */

public class JsonParser {

    public static Summoner jsonToSummoner(String json){
        Summoner summoner = new Summoner();

        try {
            JSONObject jo = new JSONObject(json);

            summoner.setId(jo.getLong("id"));
            summoner.setName(jo.getString("name"));
            summoner.setLevel(jo.getInt("summonerLevel"));
            summoner.setAccountId(jo.getLong("accountId"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return summoner;
    }

    public static List<Game> jsonToGames(String json){
        try {
            JSONObject jo = new JSONObject(json);
            JSONArray ja = jo.getJSONArray("matches");

            ArrayList<Game> games = new ArrayList<>(ja.length());

            for (int i=0; i< ja.length(); i++) {
                JSONObject gameJson = (JSONObject) ja.get(i);
                games.add(jsonToGame(gameJson));
            }
            return games;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Game jsonToGame(JSONObject gameJson) throws JSONException {
        Game game = new Game();
        game.setId(gameJson.getLong("gameId"));
        game.setRegion(gameJson.getString("platformId"));
        game.setQueue(gameJson.getInt("queue"));
        game.setSeason(gameJson.getInt("season"));
        game.setTimestamp(gameJson.getLong("timestamp"));

        return game;
    }
}
