package com.nasko.naz.belotscore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends Activity {

    Button addScoreButton;
    EditText handP1EditText;
    EditText handP2EditText;
    TextView historyP1TextView;
    TextView historyP2TextView;

    protected int totalScoreP1 = 0, totalScoreP2 = 0;
    protected int undoTotalScoreP1, undoTotalScoreP2;
    protected String scoreHistoryP1 = "";
    protected String scoreHistoryP2 = "";
    protected String undoScoreHistoryP1 = "", undoScoreHistoryP2 = "";
    protected String username, password, regUsername, regPassword, regRepeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        regUsername = intent.getStringExtra("reg_username");
        regPassword = intent.getStringExtra("reg_password");
        regRepeatPassword = intent.getStringExtra("reg_repeatPassword");


        addScoreButton = (Button) findViewById(R.id.add_score_button);
        handP1EditText = (EditText) findViewById(R.id.hand_player_one);
        handP2EditText = (EditText) findViewById(R.id.hand_player_two);
        historyP1TextView = (TextView) findViewById(R.id.history_player_one);
        historyP2TextView = (TextView) findViewById(R.id.history_player_two);

        historyP1TextView.setText(scoreHistoryP1);
        historyP2TextView.setText(scoreHistoryP2);

        addScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (handP1EditText.getText().toString().matches("") || handP2EditText.getText().toString().matches("")) {
                    Toast emptyField = Toast.makeText(getApplicationContext(), "You have to enter both scores", Toast.LENGTH_SHORT);
                    emptyField.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
                    emptyField.show();
                } else {
                    addScoreToHistory();
                }

                if (checkForWinner(totalScoreP1, totalScoreP2)) {
                    showWinnerDialog();
                }
            }
        });
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

        StringBuffer historyP1 = new StringBuffer();
        StringBuffer historyP2 = new StringBuffer();

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
        if ((totalScoreP1 >= 151 || totalScoreP2 >= 151) && totalScoreP1 != totalScoreP2)
            return true;
        else return false;
    }

    private void showWinnerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        TextView winnerMessage = new TextView(this);
        winnerMessage.setText(R.string.winner_message);
        winnerMessage.setGravity(Gravity.CENTER);
        winnerMessage.setTextSize(18);
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
                        closeKeyboard();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected int calculateTotalScore(int hand, int currentTotal) {
        int newTotal = hand + currentTotal;
        return newTotal;
    }

    private void startNewGame() {
        addScoreButton.setEnabled(true);
        totalScoreP1 = 0;
        totalScoreP2 = 0;
        scoreHistoryP1 = "0 + ";
        scoreHistoryP2 = "0 + ";
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
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
        if (id == R.id.action_login_info) {
            Toast infoToast = Toast.makeText(this, username + " " + password, Toast.LENGTH_LONG);
            infoToast.show();
        }
        if (id == R.id.action_register_info) {
            Toast infoToast = Toast.makeText(this, regUsername + " " + regPassword + " " + regRepeatPassword, Toast.LENGTH_LONG);
            infoToast.show();
        }


        return super.onOptionsItemSelected(item);
    }


}
