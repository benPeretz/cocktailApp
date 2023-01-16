package com.example.cocktailbar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class profileFragment extends Fragment {
    private MainActivity2 mainActivity2;
    private FirebaseAuth mAuth;
    boolean isLoggedIn = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mainActivity2 = (MainActivity2) getActivity();
        Button profileBtn = view.findViewById(R.id.profile_btn_profile);
        Button logoutBtn=view.findViewById(R.id.log_out_btn_profile);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            isLoggedIn = true;
            profileBtn.setText(email);
        }else{
            profileBtn.setText("Log-in");
        }

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLoggedIn){
                    ProfileSettingsFragment profileSettingsFragment=new ProfileSettingsFragment();
                    mainActivity2.replaceFragment(profileSettingsFragment);
                }else{
                    Intent intent=new Intent(requireContext(),MainActivity.class);
                    startActivity(intent);
                }

            }
        });


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity2.logout();
                Intent intent=new Intent(requireContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}