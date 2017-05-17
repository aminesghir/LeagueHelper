package com.aminesghir.leaguehelper.Data;

import com.aminesghir.leaguehelper.Data.Model.Game;
import com.aminesghir.leaguehelper.Data.Model.GameSummoner;
import com.aminesghir.leaguehelper.Data.Model.LeagueInfo;
import com.aminesghir.leaguehelper.Data.Model.StaticData.Champion;
import com.aminesghir.leaguehelper.Data.Model.Summoner;
import com.aminesghir.leaguehelper.Data.Model.Teammate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
            summoner.setIconId(jo.getInt("profileIconId"));


            return summoner;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public static List<GameSummoner> jsonToGameSummoners(String matches, String champions, Summoner summoner) {

        try {
            JSONObject jsonMatches = new JSONObject(matches);

            JSONArray ja = jsonMatches.getJSONArray("matches");

            ArrayList<GameSummoner> gameSummoners = new ArrayList<>(ja.length());

            for (int i=0; i< ja.length(); i++) {
                JSONObject gameJson = (JSONObject) ja.get(i);
                gameSummoners.add(jsonToGameSummoner(gameJson, champions, summoner));
            }
            return gameSummoners;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    private static GameSummoner jsonToGameSummoner(JSONObject gameJson, String champions, Summoner summoner) {
        GameSummoner gameSummoner = new GameSummoner();
        try {

            gameSummoner.setSummoner(summoner);

            gameSummoner.setGameId(gameJson.getLong("gameId"));
            gameSummoner.setRegion(gameJson.getString("platformId"));
            gameSummoner.setQueueId(gameJson.getInt("queue"));
            gameSummoner.setSeason(gameJson.getInt("season"));
            gameSummoner.setTimestamp(gameJson.getLong("timestamp"));
            gameSummoner.setChampion(getChampionFromJsonById(champions, gameJson.getLong("champion")));
            gameSummoner.setRole(gameJson.getString("role"));
            gameSummoner.setLane(gameJson.getString("lane"));

            return gameSummoner;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
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

            if(jo.getInt("queueId") != 420){
                return game;
            }

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
            return null;
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public static Champion jsonToChampion(JSONObject jo){
        try {
            Champion champion = new Champion();
            champion.setId(jo.getLong("id"));
            champion.setName(jo.getString("name"));
            champion.setKey(jo.getString("key"));
            champion.setTitle(jo.getString("title"));

            return champion;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public  static Champion getChampionFromJsonById(String json, long id){
        try {
            JSONObject jo = (new JSONObject(json)).getJSONObject("data");

            Iterator<String> keys = jo.keys();

            while (keys.hasNext()){
                String keyValue = keys.next();
                if( jo.getJSONObject(keyValue).getLong("id") == id){
                    return jsonToChampion(jo.getJSONObject(keyValue));
                }
            }
            return null;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void addLeagueData(Summoner summoner, String s){
        try {
            JSONArray ja = new JSONArray(s);

            for(int i = 0; i<ja.length(); i++){
                JSONObject jo = ja.getJSONObject(i);

                LeagueInfo leagueInfo = new LeagueInfo();
                leagueInfo.setLeagueName(jo.getString("leagueName"));
                leagueInfo.setTier(jo.getString("tier"));
                leagueInfo.setRank(jo.getString("rank"));
                leagueInfo.setLeaguePoints(jo.getInt("leaguePoints"));
                leagueInfo.setWins(jo.getInt("wins"));
                leagueInfo.setLosses(jo.getInt("losses"));

                List<String> status = new ArrayList<>();

                if(jo.getBoolean("veteran"))
                {
                    status.add("veteran");
                }

                if(jo.getBoolean("inactive"))
                {
                    status.add("inactive");
                }

                if(jo.getBoolean("freshBlood"))
                {
                    status.add("freshBlood");
                }

                if(jo.getBoolean("hotStreak"))
                {
                    status.add("hotStreak");
                }

                leagueInfo.setStatus(status);

                if(jo.getString("queueType").equals("RANKED_SOLO_5x5")){
                    summoner.setSoloQData(leagueInfo);
                }
                else if(jo.getString("queueType").equals("RANKED_FLEX_SR")){
                    summoner.setFlexSRData(leagueInfo);
                }
                else if(jo.getString("queueType").equals("RANKED_FLEX_TT")){
                    summoner.setFlexTTData(leagueInfo);

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
