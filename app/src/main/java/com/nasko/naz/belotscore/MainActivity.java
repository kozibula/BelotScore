package com.nasko.naz.belotscore;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    Button newGameButton, loginButton, registerButton, settingsButton;
    String teamMember1, teamMember2, teamMember3, teamMember4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        fragmentTransaction.add(R.id.main_fragment, mainFragment);

        fragmentTransaction.commit();

    }
}


