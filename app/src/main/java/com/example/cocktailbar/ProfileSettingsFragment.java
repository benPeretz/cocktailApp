package com.example.cocktailbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ProfileSettingsFragment extends Fragment {
    MainActivity2 mainActivity2;
    MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_settings, container, false);
        EditText firstNameText = view.findViewById(R.id.first_name_text_profile_settings);
        EditText lastNameText = view.findViewById(R.id.last_name_text_profile_settings);
        EditText phoneNumberText = view.findViewById(R.id.phone_number_text_profile_settings);
        EditText emailText = view.findViewById(R.id.email_text_profile_settings);
        mainActivity2 = (MainActivity2) getActivity();
        mainActivity2.read(firstNameText,lastNameText,phoneNumberText,emailText,"125634");
        return view;
    }
}