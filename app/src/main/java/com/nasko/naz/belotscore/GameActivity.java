package com.nasko.naz.belotscore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

    Button addScoreButton;
    EditText handP1EditText, handP2EditText;
    TextView historyP1TextView, historyP2TextView, team1WinsTextView, team2WinsTextView;
    ScrollView scoreScrollView;

    protected int totalScoreP1 = 0, totalScoreP2 = 0, undoTotalScoreP1, undoTotalScoreP2, team1Wins = 0, team2Wins = 0;
    protected String scoreHistoryP1 = "", scoreHistoryP2 = "", undoScoreHistoryP1 = "", undoScoreHistoryP2 = "";
    protected String team1_player1, team1_player2, team2_player1, team2_player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSharedPreferences();

        addScoreButton = (Button) findViewById(R.id.add_score_button);
        handP1EditText = (EditText) findViewById(R.id.hand_player_one);
        handP2EditText = (EditText) findViewById(R.id.hand_player_two);
        historyP1TextView = (TextView) findViewById(R.id.history_player_one);
        historyP2TextView = (TextView) findViewById(R.id.history_player_two);
        team1WinsTextView = (TextView) findViewById(R.id.team1_wins_textview);
        team2WinsTextView = (TextView) findViewById(R.id.team2_wins_textview);
        scoreScrollView = (ScrollView) findViewById(R.id.scroll_scrollview);

        addScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (handP1EditText.getText().toString().matches("") || handP2EditText.getText().toString().matches("")) {
                    Toast emptyField = Toast.makeText(getApplicationContext(), "You have to enter both scores", Toast.LENGTH_SHORT);
                    emptyField.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    emptyField.show();
                } else {
                    addScoreToHistory();
                    scoreScrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scoreScrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }
                if (checkForWinner(totalScoreP1, totalScoreP2)) {
                    showWinnerDialog();
                    team1WinsTextView.setText(String.valueOf(team1Wins));
                    team2WinsTextView.setText(String.valueOf(team2Wins));
                }
            }
        });

        handP2EditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    addScoreButton.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    private void getSharedPreferences() {
        SharedPreferences playerNamesSave = getSharedPreferences(SettingsActivity.saveFileName, 0);
        team1_player1 = (playerNamesSave.getString("team1_player1", ""));
        team1_player2 = (playerNamesSave.getString("team1_player2", ""));
        team2_player1 = (playerNamesSave.getString("team2_player1", ""));
        team2_player2 = (playerNamesSave.getString("team2_player2", ""));
    }

    private void saveBackUp() {
        undoScoreHistoryP1 = scoreHistoryP1;
        undoScoreHistoryP2 = scoreHistoryP2;
        undoTotalScoreP1 = totalScoreP1;
        undoTotalScoreP2 = totalScoreP2;
    }

    private void addScoreToHistory() {
        int handP1 = Integer.parseInt(handP1EditText.getText().toString());
        int handP2 = Integer.parseInt(handP2EditText.getText().toString());
        int newScoreP1 = calculateTotalScore(handP1, totalScoreP1);
        int newScoreP2 = calculateTotalScore(handP2, totalScoreP2);

        saveBackUp();

        StringBuffer historyP1 = new StringBuffer(), historyP2 = new StringBuffer();

        if (scoreHistoryP1.matches("") && scoreHistoryP2.matches("")) {
            historyP1.append(String.valueOf(newScoreP1)).append(" + ");
            historyP2.append(String.valueOf(newScoreP2)).append(" + ");
        } else {
            historyP1.append(scoreHistoryP1).append(handP1).append("\n").append(String.valueOf(newScoreP1)).append(" + ");
            historyP2.append(scoreHistoryP2).append(handP2).append("\n").append(String.valueOf(newScoreP2)).append(" + ");
        }

        scoreHistoryP1 = String.valueOf(historyP1);
        scoreHistoryP2 = String.valueOf(historyP2);

        historyP1TextView.setText(scoreHistoryP1);
        historyP2TextView.setText(scoreHistoryP2);

        totalScoreP1 = newScoreP1;
        totalScoreP2 = newScoreP2;
        handP1EditText.setText("");
        handP1EditText.requestFocus();
        handP2EditText.setText("");
    }

    private boolean checkForWinner(int totalScoreP1, int totalScoreP2) {
        if ((totalScoreP1 >= 151 || totalScoreP2 >= 151) && totalScoreP1 != totalScoreP2) {
            if (totalScoreP1 > totalScoreP2) {
                team1Wins++;
                return true;
            } else {
                team2Wins++;
                return true;
            }
        } else return false;
    }

    private void showWinnerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView winnerMessage = buildWinnerMessage();
        builder.setView(winnerMessage);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.button_new_game,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startNewGame();
                    }
                });
        builder.setNegativeButton(R.string.button_cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        addScoreButton.setEnabled(false);
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        closeKeyboard();
    }

    private TextView buildWinnerMessage() {
        TextView winnerMessage = new TextView(this);
        if (team1_player1 == null || team1_player1.matches("") || team1_player2 == null || team1_player2.matches("")
                || team2_player1 == null || team2_player1.matches("") || team2_player2 == null || team2_player2.matches("")) {
            winnerMessage.setText(R.string.winner_message);
        } else {
            if (totalScoreP1 > totalScoreP2) {
                winnerMessage.setText(team1_player1 + " and " + team1_player2 + " are winners!");
            } else {
                winnerMessage.setText(team2_player1 + " and " + team2_player2 + " are winners!");
            }
        }
        winnerMessage.setGravity(Gravity.CENTER);
        winnerMessage.setHeight(150);
        winnerMessage.setTextSize(16);
        return winnerMessage;
    }

    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected int calculateTotalScore(int hand, int currentTotal) {
        currentTotal += hand;
        return currentTotal;
    }

    private void startNewGame() {
        addScoreButton.setEnabled(true);
        totalScoreP1 = 0;
        totalScoreP2 = 0;
        scoreHistoryP1 = "";
        scoreHistoryP2 = "";
        historyP1TextView.setText("");
        historyP2TextView.setText("");
    }

    private void undoMove() {
        scoreHistoryP1 = undoScoreHistoryP1;
        scoreHistoryP2 = undoScoreHistoryP2;
        historyP1TextView.setText(scoreHistoryP1);
        historyP2TextView.setText(scoreHistoryP2);
        totalScoreP1 = undoTotalScoreP1;
        totalScoreP2 = undoTotalScoreP2;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_new_game) {
            startNewGame();
        }
        if (id == R.id.action_undo) {
            undoMove();
        }
        if (id == R.id.action_about) {
            Toast aboutToast = Toast.makeText(this, getString(R.string.about_text), Toast.LENGTH_LONG);
            aboutToast.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
