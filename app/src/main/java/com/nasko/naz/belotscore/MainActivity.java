package com.nasko.naz.belotscore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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


public class MainActivity extends Activity {

    Button addScoreButton;
    EditText handP1EditText;
    EditText handP2EditText;
    TextView historyP1TextView;
    TextView historyP2TextView;

    protected int totalScoreP1 = 0;
    protected int totalScoreP2 = 0;
    protected String scoreHistoryP1 = "0 + ";
    protected String scoreHistoryP2 = "0 + ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    private void addScoreToHistory() {
        int handP1 = Integer.parseInt(handP1EditText.getText().toString());
        int handP2 = Integer.parseInt(handP2EditText.getText().toString());
        int newScoreP1 = calculateTotalScore(handP1, totalScoreP1);
        int newScoreP2 = calculateTotalScore(handP2, totalScoreP2);

        StringBuffer historyP1 = new StringBuffer().append(scoreHistoryP1).append(handP1).append("\n").append(String.valueOf(newScoreP1)).append(" + ");
        StringBuffer historyP2 = new StringBuffer().append(scoreHistoryP2).append(handP2).append("\n").append(String.valueOf(newScoreP2)).append(" + ");

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
        builder.setMessage(R.string.winner_message);
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
        historyP1TextView.setText("0 + ");
        historyP2TextView.setText("0 + ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }
        if (id == R.id.action_about) {
            Toast aboutToast = Toast.makeText(this, getString(R.string.about_text), Toast.LENGTH_LONG);
            aboutToast.show();
        }


        return super.onOptionsItemSelected(item);
    }
}
