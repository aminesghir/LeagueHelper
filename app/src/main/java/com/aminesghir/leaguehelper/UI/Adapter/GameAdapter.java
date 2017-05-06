package com.aminesghir.leaguehelper.UI.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aminesghir.leaguehelper.Data.Model.Game;
import com.aminesghir.leaguehelper.R;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by Amine on 05/05/2017.
 */

public class GameAdapter extends BaseAdapter {

    private List<Game> datasource = Collections.EMPTY_LIST;

    public GameAdapter(List<Game> games){
        this.datasource = games;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);

        Game game = datasource.get(position);
        ((TextView)convertView.findViewById(R.id.gameId)).setText("id : " + game.getId());
        ((TextView)convertView.findViewById(R.id.region)).setText("region : " + game.getRegion());
        ((TextView)convertView.findViewById(R.id.queue)).setText("queue : " + game.getQueue());
        ((TextView)convertView.findViewById(R.id.season)).setText("season : " + game.getSeason());
        return convertView;
    }
}
