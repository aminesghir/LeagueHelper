package com.aminesghir.leaguehelper.UI.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aminesghir.leaguehelper.Data.Model.Research;
import com.aminesghir.leaguehelper.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 12/05/2017.
 */

public class ResearchAdapter extends BaseAdapter {
    private List<Research> datasource = null;

    public ResearchAdapter(){}

    public ResearchAdapter(List<Research> list){
        datasource = list;
    }


    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        if(datasource == null)
            return 0;
        return datasource.size();
    }

    @Override
    public Research getItem(int i) {
        return datasource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_research, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.research)).setText(getItem(position).getText());
        return convertView;
    }

    public void show(List<Research> list){
        datasource = list;
        notifyDataSetChanged();
    }
}
