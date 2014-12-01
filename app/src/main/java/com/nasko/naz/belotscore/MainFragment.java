package com.nasko.naz.belotscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainFragment extends Fragment {

    Button newGameButton, loginButton, registerButton, settingsButton;
    String teamMember1, teamMember2, teamMember3, teamMember4;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        newGameButton = (Button) view.findViewById(R.id.new_game_button);
        loginButton = (Button) view.findViewById(R.id.login_button_main);
        registerButton = (Button) view.findViewById(R.id.register_button_main);
        settingsButton = (Button) view.findViewById(R.id.settings_button_main);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), GameActivity.class);
                intent.putExtra("tm1", teamMember1);
                intent.putExtra("tm2", teamMember2);
                intent.putExtra("tm3", teamMember3);
                intent.putExtra("tm4", teamMember4);
                startActivity(intent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                LoginFragment loginFragment = new LoginFragment();
                fragmentTransaction.replace(R.id.login_fragment_parent, loginFragment);

                fragmentTransaction.commit();
            }
        });

        return view;
    }

}

