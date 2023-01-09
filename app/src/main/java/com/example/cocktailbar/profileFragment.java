package com.example.cocktailbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class profileFragment extends Fragment {
    MainActivity2 mainActivity2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mainActivity2 = (MainActivity2) getActivity();



        Button profileBtn = view.findViewById(R.id.profile_btn_profile);
        Button logoutBtn=view.findViewById(R.id.log_out_btn_profile);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileSettingsFragment profileSettingsFragment=new ProfileSettingsFragment();
                mainActivity2.replaceFragment(profileSettingsFragment);
            }
        });


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity2.logout();
            }
        });


        return view;
    }
}