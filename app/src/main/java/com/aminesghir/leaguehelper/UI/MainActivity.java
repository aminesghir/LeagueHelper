package com.aminesghir.leaguehelper.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aminesghir.leaguehelper.Data.DataProvider;
import com.aminesghir.leaguehelper.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SummonerDetailActivity.class);
                intent.putExtra("SUMMONER_NAME", ((EditText)findViewById(R.id.summonerName)).getText().toString());
                startActivity(intent);
            }
        });
    }
}
