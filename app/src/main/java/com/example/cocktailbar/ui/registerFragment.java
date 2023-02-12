package com.example.cocktailbar.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cocktailbar.MainActivity;
import com.example.cocktailbar.R;


public class registerFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);

        MainActivity mainActivity= (MainActivity) getActivity();

        EditText et_email=view.findViewById(R.id.editTextTextEmailAddress);
        EditText et_pass=view.findViewById(R.id.editTextTextPassword);
        EditText et_lastName=view.findViewById(R.id.editTextTextPersonLastName);
        EditText et_firstName=view.findViewById(R.id.editTextTextPersonName);
        EditText et_phon=view.findViewById(R.id.editTextPhone);



        Button btn_signup=view.findViewById(R.id.button_singup_FR);



        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.funcRegister(et_email,et_pass,et_firstName,et_lastName,et_phon,view);
            }
        });




    return view;
    }
}