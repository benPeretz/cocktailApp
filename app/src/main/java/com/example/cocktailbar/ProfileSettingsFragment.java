package com.example.cocktailbar;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ProfileSettingsFragment extends Fragment {
    MainActivity2 mainActivity2;
    private FirebaseAuth mAuth;
    FirebaseFirestore userRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_settings, container, false);
        mAuth = FirebaseAuth.getInstance();
        readCurrentUserFromFireStore(view);

//        EditText firstNameText = view.findViewById(R.id.first_name_text_profile_settings);
//        EditText lastNameText = view.findViewById(R.id.last_name_text_profile_settings);
//        EditText phoneNumberText = view.findViewById(R.id.phone_number_text_profile_settings);
//        EditText emailText = view.findViewById(R.id.email_text_profile_settings);


        return view;
    }

    private void readCurrentUserFromFireStore(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        userRef= FirebaseFirestore.getInstance();
        EditText firstNameText = view.findViewById(R.id.first_name_text_profile_settings);
        EditText lastNameText = view.findViewById(R.id.last_name_text_profile_settings);
        EditText phoneNumberText = view.findViewById(R.id.phone_number_text_profile_settings);
        EditText emailText = view.findViewById(R.id.email_text_profile_settings);
        if (user != null){
            String email =user.getEmail();
            mainActivity2 = (MainActivity2) getActivity();
            mainActivity2.read(firstNameText,lastNameText,phoneNumberText,emailText,user.getEmail());
        }
    }
}