package com.aminesghir.leaguehelper.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.Data.Database.DatabaseContract;
import com.aminesghir.leaguehelper.Data.Database.DatabaseHelper;
import com.aminesghir.leaguehelper.Data.Database.ResearchDao;
import com.aminesghir.leaguehelper.Data.Model.Research;
import com.aminesghir.leaguehelper.R;
import com.aminesghir.leaguehelper.UI.Adapter.ResearchAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    final ResearchAdapter researchAdapter = new ResearchAdapter();

    ResearchDao researchDao = new ResearchDao(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        researchDao.open();


        ((EditText)findViewById(R.id.summonerName)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchSummoner(researchDao);
                    return true;
                }
                return false;
            }
        });

        final View.OnClickListener searchSummoner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchSummoner(researchDao);
            }
        };

        ((Button)findViewById(R.id.button)).setOnClickListener(searchSummoner);

        ResearchAdapter researchAdapter = new ResearchAdapter(researchDao.getRecentResearches(5));
        ((ListView)findViewById(R.id.recentResearch)).setAdapter(researchAdapter);

        ((ListView)findViewById(R.id.recentResearch)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((EditText)findViewById(R.id.summonerName)).setText(((TextView)view.findViewById(R.id.research)).getText());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        researchAdapter.show(researchDao.getRecentResearches(5));
    }

    public void searchSummoner(ResearchDao researchDao){
        Intent intent = new Intent(MainActivity.this, SummonerDetailActivity.class);
        Research research = new Research(((EditText) findViewById(R.id.summonerName)).getText().toString());
        researchDao.insertResearch(research);
        intent.putExtra("SUMMONER_NAME", research.getText());
        startActivity(intent);
    }


}
