package com.nasko.naz.belotscore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SettingsActivity extends Activity {

    EditText team1_player1_editText, team1_player2_editText, team2_player1_editText, team2_player2_editText;
    Button saveButton;
    String team1_player1, team1_player2, team2_player1, team2_player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        team1_player1_editText = (EditText) findViewById(R.id.team1_player1_editText);
        team1_player2_editText = (EditText) findViewById(R.id.team1_player2_editText);
        team2_player1_editText = (EditText) findViewById(R.id.team2_player1_editText);
        team2_player2_editText = (EditText) findViewById(R.id.team2_player2_editText);
        saveButton = (Button) findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team1_player1 = team1_player1_editText.getText().toString().trim();
                team1_player2 = team1_player2_editText.getText().toString().trim();
                team2_player1 = team2_player1_editText.getText().toString().trim();
                team2_player2 = team2_player2_editText.getText().toString().trim();

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("team1_player1", team1_player1);
                intent.putExtra("team1_player2", team1_player2);
                intent.putExtra("team2_player1", team2_player1);
                intent.putExtra("team2_player2", team2_player2);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
