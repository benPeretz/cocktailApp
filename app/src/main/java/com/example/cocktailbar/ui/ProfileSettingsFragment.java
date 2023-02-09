package com.example.cocktailbar.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cocktailbar.MainActivity2;
import com.example.cocktailbar.databinding.FragmentProfileSettingsBinding;
import com.example.cocktailbar.models.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileSettingsFragment extends Fragment {
    MainActivity2 mainActivity2;
    private FirebaseAuth mAuth;
    FirebaseFirestore userRef;
    private FragmentProfileSettingsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileSettingsBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();
        Button saveButton = binding.buttonSaveProfileSettings;
        EditText firstNameText = binding.firstNameTextProfileSettings;
        EditText lastNameText = binding.lastNameTextProfileSettings;
        EditText phoneNumberText = binding.phoneNumberTextProfileSettings;
        EditText emailText = binding.emailTextProfileSettings;
        readCurrentUserFromFireStore();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference docRef = userRef.collection("user").document(mAuth.getCurrentUser().getEmail());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Person person = new Person(emailText.getText().toString(), phoneNumberText.getText().toString(), firstNameText.getText().toString(), lastNameText.getText().toString());
                                docRef.set(person);

                            } else {
                                // The person document does not exist
                            }
                        } else {
                            // An error occurred while retrieving the document
                        }
                    }
                });
                profileFragment profileFragment=new profileFragment();
                mainActivity2.replaceFragment(profileFragment);

            }
        });
        return binding.getRoot();
    }

    private void readCurrentUserFromFireStore() {
        FirebaseUser user = mAuth.getCurrentUser();
        userRef= FirebaseFirestore.getInstance();
        Boolean isFirstNameChanged, Boolean;
        if (user != null){
            String email =user.getEmail();
            mainActivity2 = (MainActivity2) getActivity();
            mainActivity2.read(binding.firstNameTextProfileSettings,binding.lastNameTextProfileSettings,binding.phoneNumberTextProfileSettings,binding.emailTextProfileSettings,user.getEmail(), binding.getRoot());
        }
    }

}