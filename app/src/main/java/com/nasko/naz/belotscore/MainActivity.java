package com.nasko.naz.belotscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    Button newGameButton, loginButton, registerButton, settingsButton;
    String team1_player1, team1_player2, team2_player1, team2_player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent settingsIntent = getIntent();
        team1_player1 = settingsIntent.getStringExtra("team1_player1");
        team1_player2 = settingsIntent.getStringExtra("team1_player2");
        team2_player1 = settingsIntent.getStringExtra("team2_player1");
        team2_player2 = settingsIntent.getStringExtra("team2_player2");

        newGameButton = (Button) findViewById(R.id.new_game_button);
        loginButton = (Button) findViewById(R.id.login_button_main);
        registerButton = (Button) findViewById(R.id.register_button_main);
        settingsButton = (Button) findViewById(R.id.settings_button_main);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GameActivity.class);
                intent.putExtra("team1_player1", team1_player1);
                intent.putExtra("team1_player2", team1_player2);
                intent.putExtra("team2_player1", team2_player1);
                intent.putExtra("team2_player2", team2_player2);
                startActivity(intent);

            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}


