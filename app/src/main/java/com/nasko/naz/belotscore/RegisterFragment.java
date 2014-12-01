package com.nasko.naz.belotscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class RegisterFragment extends Fragment {

    EditText username, password, repeatPassword;
    Button register;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        username = (EditText) view.findViewById(R.id.reg_username_edit_text);
        password = (EditText) view.findViewById(R.id.reg_password_edit_text);
        repeatPassword = (EditText) view.findViewById(R.id.reg_repeat_password_edit_text);
        register = (Button) view.findViewById(R.id.register_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("reg_username", username.getText().toString().trim());
                intent.putExtra("reg_password", password.getText().toString().trim());
                intent.putExtra("reg_repeatPassword", repeatPassword.getText().toString().trim());
                startActivity(intent);

            }
        });
        return view;
    }
}
