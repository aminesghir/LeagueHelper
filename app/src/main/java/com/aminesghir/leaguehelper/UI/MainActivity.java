package com.aminesghir.leaguehelper.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.Data.Database.DatabaseContract;
import com.aminesghir.leaguehelper.Data.Database.DatabaseHelper;
import com.aminesghir.leaguehelper.Data.Database.ResearchDao;
import com.aminesghir.leaguehelper.Data.Model.Research;
import com.aminesghir.leaguehelper.R;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper = new DatabaseHelper(this);

    ResearchDao researchDao = new ResearchDao(dbHelper);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SummonerDetailActivity.class);
                String research = ((EditText)findViewById(R.id.summonerName)).getText().toString();

                researchDao.save(research);

                Log.i("LOOOOOOOL", "lkjlkjlh");
                System.out.println(researchDao.isDataAlreadyInDBorNot(DatabaseContract.ResearchTable.TABLE_NAME,DatabaseContract.ResearchTable.KEYWORD_COLUMN,research));
                intent.putExtra("SUMMONER_NAME", research);
                startActivity(intent);
            }
        });
    }
}
