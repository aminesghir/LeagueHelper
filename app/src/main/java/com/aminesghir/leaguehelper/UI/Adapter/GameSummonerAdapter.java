package com.aminesghir.leaguehelper.UI.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.Data.JsonParser;
import com.aminesghir.leaguehelper.Data.Model.Game;
import com.aminesghir.leaguehelper.Data.Model.GameSummoner;
import com.aminesghir.leaguehelper.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Amine on 05/05/2017.
 */

public class GameSummonerAdapter extends BaseAdapter {

    private List<GameSummoner> datasource = Collections.EMPTY_LIST;

    private ArrayList<Boolean> loadData;

    public GameSummonerAdapter(List<GameSummoner> gameSummoners){
        this.datasource = gameSummoners;
        this.loadData = new ArrayList<>(Collections.nCopies(gameSummoners.size()+1, true));
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return datasource.size();
    }

    @Override
    public GameSummoner getItem(int position) {
        return datasource.get(position);
    }

    @Override
    public long getItemId(int position) {
            return datasource.get(position).getGameId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        }

        GameSummoner gameSummoner = datasource.get(position);
        ((TextView)convertView.findViewById(R.id.championId)).setText(gameSummoner.getChampion().getName());
        ((TextView)convertView.findViewById(R.id.role)).setText(gameSummoner.getRole());
        ((TextView)convertView.findViewById(R.id.queue)).setText(gameSummoner.getQueue());
        ((TextView)convertView.findViewById(R.id.lane)).setText(gameSummoner.getLane());

        ImageView imageView = (ImageView)convertView.findViewById(R.id.championImage);

        if(imageView.getDrawable() == null) {
            String key = (gameSummoner.getChampion().getKey().equals("Fiddlesticks"))?"FiddleSticks":gameSummoner.getChampion().getKey();
            new DownloadImageTask(imageView).execute("https://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/" + key + ".png");
        }
        if(loadData.get(position)) {
            (new AddGameDataTask(convertView)).execute(getItem(position));
            loadData.add(position, false);
        }

        return convertView;
    }



    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


    private class AddGameDataTask extends  AsyncTask<GameSummoner, Void, GameSummoner>{

        View view;

        public AddGameDataTask(View v){
            view = v;
        }

        @Override
        protected GameSummoner doInBackground(GameSummoner... gameSummoners) {
            GameSummoner gs = gameSummoners[0];
                Game game = JsonParser.jsonToGame(DataProvider.getMatchDetailById(gs.getGameId()));
                gs.setGame(game);
                gs.setWin();
                return gs;
        }

        @Override
        protected void onPostExecute(GameSummoner gs) {
            if(gs.getGame().getQueue() == 420) {
                if (gs.isWin()) {
                    view.findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#C0F9F9"));
                } else {
                    view.findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#FFC6C6"));
                }
            }else{
                view.findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#FFFFCC"));
            }
        }
    }



}
