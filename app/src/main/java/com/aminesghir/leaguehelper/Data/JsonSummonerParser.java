package com.aminesghir.leaguehelper.Data;

import com.aminesghir.leaguehelper.Data.Model.Summoner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 03/05/2017.
 */

public class JsonSummonerParser {

    public static Summoner jsonToSummoner(String summonerName, String json){
        Summoner summoner = new Summoner();

        try {
            JSONObject jo = new JSONObject(json);

            summoner.setId(jo.getJSONObject(summonerName.toLowerCase()).getInt("id"));
            summoner.setName(jo.getJSONObject(summonerName.toLowerCase()).getString("name"));
            summoner.setLevel(jo.getJSONObject(summonerName.toLowerCase()).getInt("summonerLevel"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return summoner;
    }
}
