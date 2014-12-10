package com.nasko.naz.belotscore;

import android.content.Context;
import android.content.SharedPreferences;

public class SavingData {
    public static String saveFileName = "PlayerHistorySave";
    private static String scoreHistoryP1 = "", scoreHistoryP2 = "";
    private static int winsP1 = 0, winsP2 = 0, scoreP1 = 0, scoreP2 = 0;
    private static SharedPreferences playerHistorySave;

    public static void setScoreP1(int newScoreP1) {
        scoreP1 = newScoreP1;
    }

    public static int getScoreP1() {
        return scoreP1;
    }

    public static void setScoreP2(int newScoreP2) {
        scoreP2 = newScoreP2;
    }

    public static int getScoreP2() {
        return scoreP2;
    }

    public static String getScoreHistoryP1() {
        return scoreHistoryP1;
    }

    public static void setScoreHistoryP1(String newScoreHistoryP1) {
        scoreHistoryP1 = newScoreHistoryP1;
    }

    public static String getScoreHistoryP2() {
        return scoreHistoryP2;
    }

    public static void setScoreHistoryP2(String newScoreHistoryP2) {
        scoreHistoryP2 = newScoreHistoryP2;
    }

    public static int getWinsP1() {
        return winsP1;
    }

    public static void setWinsP1(int newWinsP1) {
        winsP1 = newWinsP1;
    }

    public static int getWinsP2() {
        return winsP2;
    }

    public static void setWinsP2(int newWinsP2) {
        winsP2 = newWinsP2;
    }

    public static void saveData(Context context, String scoreHistoryP1, String scoreHistoryP2, int winsP1, int winsP2, int scoreP1, int scoreP2) {
        playerHistorySave = context.getSharedPreferences(saveFileName, 0);
        SharedPreferences.Editor editor = playerHistorySave.edit();
        editor.putString("scoreHistoryP1", scoreHistoryP1);
        editor.putString("scoreHistoryP2", scoreHistoryP2);
        editor.putInt("scoreP1", scoreP1);
        editor.putInt("scoreP2", scoreP2);
        editor.putInt("winsP1", winsP1);
        editor.putInt("winsP2", winsP2);
        editor.apply();
    }

    public static void loadData(Context context) {
        playerHistorySave = context.getSharedPreferences(saveFileName, 0);
        setScoreHistoryP1(playerHistorySave.getString("scoreHistoryP1", ""));
        setScoreHistoryP2(playerHistorySave.getString("scoreHistoryP2", ""));
        setScoreP1(playerHistorySave.getInt("scoreP1", 0));
        setScoreP2(playerHistorySave.getInt("scoreP2", 0));
        setWinsP1(playerHistorySave.getInt("winsP1", 0));
        setWinsP2(playerHistorySave.getInt("winsP2", 0));
    }
}