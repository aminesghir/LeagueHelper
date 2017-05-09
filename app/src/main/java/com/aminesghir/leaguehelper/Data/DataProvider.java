package com.aminesghir.leaguehelper.Data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class DataProvider {

    private static final String API_KEY = "RGAPI-c9cf9b74-18c1-4c73-902d-eddcefd156c4";
    private static final int STATUS_OK = 200;

    public static String getSummonerByName(String summonerName){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String summoner = null;

        try {
            URL url = new URL(
                    "https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/"+
                            summonerName+
                            "?api_key="+
                            API_KEY);

            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() != STATUS_OK){
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            summoner = buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return summoner;
    }

    public static String getMatchListByAccountId(Long accountId){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String response = null;

        try {
            URL url = new URL(
                    "https://euw1.api.riotgames.com/lol/match/v3/matchlists/by-account/"+
                            String.valueOf(accountId)+
                            "/recent?api_key="+
                            API_KEY);

            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() != STATUS_OK){
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            response = buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

    public static String getMatchDetailById(Long matchId){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String response = null;

        try {
            URL url = new URL(
                    "https://euw1.api.riotgames.com/lol/match/v3/matches/"+
                            String.valueOf(matchId)+
                            "?api_key="+
                            API_KEY);

            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() != STATUS_OK){
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            response = buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

    public static String getChampionsStaticData(){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String response = null;

        try {
            URL url = new URL(
                    "https://euw1.api.riotgames.com/lol/static-data/v3/champions"+
                            "?api_key=" + API_KEY);

            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() != STATUS_OK){
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream == null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            response = buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

    public static Bitmap getChampionImage(String key){

        try {
            URL url = new URL(
                    "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/"+
                            key + ".png");

            return BitmapFactory.decodeStream(url.openConnection().getInputStream());


        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}