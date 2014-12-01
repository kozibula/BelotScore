package com.nasko.naz.belotscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginFragment extends Fragment {

    EditText usernameEditText, passwordEditText;
    Button loginButton;
    String username, password;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usernameEditText = (EditText) view.findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) view.findViewById(R.id.password_edit_text);
        loginButton = (Button) view.findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();

                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });

        return view;
    }
}

