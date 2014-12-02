package com.nasko.naz.belotscore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends Activity {

    public static String saveFileName = "PlayerNamesSave";
    EditText team1_player1_editText, team1_player2_editText, team2_player1_editText, team2_player2_editText;
    Button saveButton;
    String team1_player1, team1_player2, team2_player1, team2_player2;
    SharedPreferences playerNamesSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        team1_player1_editText = (EditText) findViewById(R.id.team1_player1_editText);
        team1_player2_editText = (EditText) findViewById(R.id.team1_player2_editText);
        team2_player1_editText = (EditText) findViewById(R.id.team2_player1_editText);
        team2_player2_editText = (EditText) findViewById(R.id.team2_player2_editText);
        saveButton = (Button) findViewById(R.id.save_button);

        playerNamesSave = getSharedPreferences(saveFileName, 0);
        team1_player1_editText.setText(playerNamesSave.getString("team1_player1", ""));
        team1_player2_editText.setText(playerNamesSave.getString("team1_player2", ""));
        team2_player1_editText.setText(playerNamesSave.getString("team2_player1", ""));
        team2_player2_editText.setText(playerNamesSave.getString("team2_player2", ""));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team1_player1 = team1_player1_editText.getText().toString().trim();
                team1_player2 = team1_player2_editText.getText().toString().trim();
                team2_player1 = team2_player1_editText.getText().toString().trim();
                team2_player2 = team2_player2_editText.getText().toString().trim();

                SharedPreferences.Editor editor = playerNamesSave.edit();
                editor.putString("team1_player1", team1_player1);
                editor.putString("team1_player2", team1_player2);
                editor.putString("team2_player1", team2_player1);
                editor.putString("team2_player2", team2_player2);
                editor.apply();

                Intent returnToMainIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(returnToMainIntent);
            }
        });
    }
}
