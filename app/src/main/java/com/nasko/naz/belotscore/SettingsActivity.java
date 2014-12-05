package com.nasko.naz.belotscore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends FragmentActivity {

    public static String saveFileName = "PlayerNamesSave";
    private EditText team1_player1_editText, team1_player2_editText, team2_player1_editText, team2_player2_editText;
    private String team1_player1, team1_player2, team2_player1, team2_player2, chosenLanguage;
    private Spinner languageSpinner;
    private Button saveButton;
    private SharedPreferences playerNamesSave;
    private TextView settingsDescription, weText, youText, languageDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeViews();

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosenLanguage = languageSpinner.getSelectedItem().toString();
                if (chosenLanguage.matches("Български"))
                    LanguageChanger.setLanguage("bg");
                else
                    LanguageChanger.setLanguage("en");

                LanguageChanger.changeLang(LanguageChanger.getLanguage(), getBaseContext());
                int langPos = languageSpinner.getSelectedItemPosition();
                SharedPreferences.Editor editor = playerNamesSave.edit();
                editor.putInt("langPos", langPos);
                editor.apply();

                saveButton.setText(R.string.save_button);
                settingsDescription.setText(R.string.settings_description);
                weText.setText(R.string.nie_text_view);
                youText.setText(R.string.vie_text_view);
                languageDescription.setText(R.string.choose_language);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

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

    private void initializeViews() {
        team1_player1_editText = (EditText) findViewById(R.id.team1_player1_editText);
        team1_player2_editText = (EditText) findViewById(R.id.team1_player2_editText);
        team2_player1_editText = (EditText) findViewById(R.id.team2_player1_editText);
        team2_player2_editText = (EditText) findViewById(R.id.team2_player2_editText);
        saveButton = (Button) findViewById(R.id.save_button);
        languageSpinner = (Spinner) findViewById(R.id.language_spinner);
        settingsDescription = (TextView) findViewById(R.id.description_textview);
        weText = (TextView) findViewById(R.id.we_textview);
        youText = (TextView) findViewById(R.id.you_textview);
        languageDescription = (TextView) findViewById(R.id.choose_language_textview);

        playerNamesSave = getSharedPreferences(saveFileName, 0);
        team1_player1_editText.setText(playerNamesSave.getString("team1_player1", ""));
        team1_player2_editText.setText(playerNamesSave.getString("team1_player2", ""));
        team2_player1_editText.setText(playerNamesSave.getString("team2_player1", ""));
        team2_player2_editText.setText(playerNamesSave.getString("team2_player2", ""));
        languageSpinner.setSelection(playerNamesSave.getInt("langPos", 0));
    }
}
