package com.aminesghir.leaguehelper.Data;

import android.util.Log;

import com.aminesghir.leaguehelper.Data.Model.Summoner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 03/05/2017.
 */

public class JsonSummonerParser {

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
}
