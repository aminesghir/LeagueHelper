package com.aminesghir.leaguehelper.Data;

import com.aminesghir.leaguehelper.Data.Model.Game;
import com.aminesghir.leaguehelper.Data.Model.GameSummoner;
import com.aminesghir.leaguehelper.Data.Model.Summoner;
import com.aminesghir.leaguehelper.Data.Model.Teammate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 03/05/2017.
 */

public class JsonParser {

    public static Summoner jsonToSummoner(String json) {
        try{
            Summoner summoner = new Summoner();


        JSONObject jo = new JSONObject(json);

        summoner.setId(jo.getLong("id"));
        summoner.setName(jo.getString("name"));
        summoner.setLevel(jo.getInt("summonerLevel"));
        summoner.setAccountId(jo.getLong("accountId"));


        return summoner;
        }catch (JSONException e){
            e.printStackTrace();
            return (new Summoner());
        }
    }

    public static List<GameSummoner> jsonToGameSummoners(String json) {

        try {
            JSONObject jo = new JSONObject(json);

        JSONArray ja = jo.getJSONArray("matches");

        ArrayList<GameSummoner> gameSummoners = new ArrayList<>(ja.length());

        for (int i=0; i< ja.length(); i++) {
            JSONObject gameJson = (JSONObject) ja.get(i);
            gameSummoners.add(jsonToGameSummoner(gameJson));
        }
        return gameSummoners;
        } catch (JSONException e) {
            e.printStackTrace();
            return (new ArrayList<>(0));
        }
    }

    private static GameSummoner jsonToGameSummoner(JSONObject gameJson) {
        try {
            GameSummoner gameSummoner = new GameSummoner();

            gameSummoner.setId(gameJson.getLong("gameId"));

        gameSummoner.setRegion(gameJson.getString("platformId"));
        gameSummoner.setQueue(gameJson.getInt("queue"));
        gameSummoner.setSeason(gameJson.getInt("season"));
        gameSummoner.setTimestamp(gameJson.getLong("timestamp"));

        return gameSummoner;} catch (JSONException e) {
            e.printStackTrace();
            return (new GameSummoner());
        }
    }

    public static Game jsonToGame(String json) {

        int TEAM_ID_WIN = 100;
        try {
            Game game = new Game();

            JSONObject jo = new JSONObject(json);

            game.setId(jo.getLong("gameId"));
            game.setRegion(jo.getString("platformId"));
            game.setCreationDate(jo.getLong("gameCreation"));
            game.setDuration(jo.getInt("gameDuration"));
            game.setQueue(jo.getInt("queueId"));
            game.setMapId(jo.getInt("mapId"));
            game.setSeason(jo.getInt("seasonId"));
            game.setPatch(jo.getString("gameVersion"));
            game.setMode(jo.getString("gameMode"));
            game.setType(jo.getString("gameType"));

            JSONObject team = jo.getJSONArray("teams").getJSONObject(0);
            TEAM_ID_WIN = team.getString("win").equals("Win")?
                    team.getInt("teamId"):
                    jo.getJSONArray("teams").getJSONObject(1).getInt("teamId");



            JSONArray participants = jo.getJSONArray("participants");
            JSONArray participantIdentities = jo.getJSONArray("participantIdentities");

            List<Teammate> teammates = new ArrayList<>(participants.length());

            JSONObject data;

            for (int i = 0; i < participants.length(); i++) {
                data = participants.getJSONObject(i);

                Teammate teammate = new Teammate();

                teammate.setParticipantId(data.getInt("participantId"));
                teammate.setChampionId(data.getInt("championId"));
                teammate.setWinner(data.getInt("teamId") == TEAM_ID_WIN);
                teammate.setLane(data.getJSONObject("timeline").getString("lane"));
                teammate.setRole(data.getJSONObject("timeline").getString("role"));

                data = participantIdentities.getJSONObject(i).getJSONObject("player");

                Summoner summoner = new Summoner();
                summoner.setId(data.getLong("summonerId"));
                summoner.setAccountId(data.getLong("accountId"));
                summoner.setName(data.getString("summonerName"));

                teammate.setSummoner(summoner);

                teammates.add(teammate);
            }

            game.setParticipants(teammates);

            return game;
        }catch (JSONException e){
            e.printStackTrace();
            return (new Game());
        }
    }
}
