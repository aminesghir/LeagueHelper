package com.aminesghir.leaguehelper.UI.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aminesghir.leaguehelper.Data.Model.GameSummoner;
import com.aminesghir.leaguehelper.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Amine on 05/05/2017.
 */

public class GameSummonerAdapter extends BaseAdapter {

    private List<GameSummoner> datasource = Collections.EMPTY_LIST;

    public GameSummonerAdapter(List<GameSummoner> gameSummoners){
        this.datasource = gameSummoners;
    }

    @Override
    public int getCount() {
        return datasource.size();
    }

    @Override
    public Object getItem(int position) {
        return datasource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return datasource.get(position).getGameId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);

        GameSummoner gameSummoner = datasource.get(position);
        ((TextView)convertView.findViewById(R.id.championId)).setText("champion : " + String.valueOf(gameSummoner.getChampionId()));
        ((TextView)convertView.findViewById(R.id.role)).setText(gameSummoner.getRole());
        ((TextView)convertView.findViewById(R.id.queue)).setText(gameSummoner.getQueue());
        ((TextView)convertView.findViewById(R.id.lane)).setText(gameSummoner.getLane());
        return convertView;
    }

    public long getGameId(int position){
        return datasource.get(position).getGameId();
    }
}
