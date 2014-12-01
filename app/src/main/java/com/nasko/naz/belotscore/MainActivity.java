package com.nasko.naz.belotscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
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

        /*newGameButton = (Button) findViewById(R.id.new_game_button);
        loginButton = (Button) findViewById(R.id.login_button_main);
        registerButton = (Button) findViewById(R.id.register_button_main);
        settingsButton = (Button) findViewById(R.id.settings_button_main);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("tm1", teamMember1);
                intent.putExtra("tm2", teamMember2);
                intent.putExtra("tm3", teamMember3);
                intent.putExtra("tm4", teamMember4);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LoginFragment loginFragment = new LoginFragment();
                fragmentTransaction.replace(R.id.login_fragment, loginFragment);

                fragmentTransaction.commit();

            }
        });*/


}


