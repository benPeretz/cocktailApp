package com.example.cocktailbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class loginFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);

        MainActivity mainActivity= (MainActivity) getActivity();

        EditText et_email=view.findViewById(R.id.editTextTextEmailAddress);
        EditText et_pass=view.findViewById(R.id.editTextTextPassword);
        Button btn_login =view.findViewById(R.id.button_login_FL);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.funcLogin(et_email,et_pass,view,0);
            }
        });


    return view;
    }
}