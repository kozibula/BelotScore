package com.nasko.naz.belotscore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends FragmentActivity {

    Button newGameButton, continueButton, settingsButton;
    TextView applicationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LanguageChanger.loadLocale(getBaseContext());

        applicationName = (TextView) findViewById(R.id.app_name_text);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/troika.otf");
        applicationName.setTypeface(tf);

        tf = Typeface.createFromAsset(getAssets(), "fonts/inglobalb.ttf");
        newGameButton = (Button) findViewById(R.id.new_game_button);
        newGameButton.setText(R.string.new_game);
        newGameButton.setTypeface(tf);
        continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setText(R.string.continue_button);
        continueButton.setTypeface(tf);
        settingsButton = (Button) findViewById(R.id.settings_button_main);
        settingsButton.setText(R.string.settings);
        settingsButton.setTypeface(tf);


        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newGameIntent = new Intent(v.getContext(), GameActivity.class);
                newGameIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(newGameIntent);
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent continueIntent = new Intent(v.getContext(), GameActivity.class);
                continueIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                continueIntent.putExtra("newGame", false);
                startActivity(continueIntent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(v.getContext(), SettingsActivity.class);
                settingsIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(settingsIntent);
            }
        });
    }


}


