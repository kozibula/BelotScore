package com.nasko.naz.belotscore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends FragmentActivity {

    Button newGameButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LanguageChanger.loadLocale(getBaseContext());

        newGameButton = (Button) findViewById(R.id.new_game_button);
        newGameButton.setText(R.string.new_game);
        settingsButton = (Button) findViewById(R.id.settings_button_main);
        settingsButton.setText(R.string.settings);


        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GameActivity.class);
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


