package com.example.cocktailbar.repository;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class AuthRepository implements IAuthRepository{
    private FirebaseAuth mAuth=FirebaseAuth.getInstance();



    @Override
    public void currentUser() {

    }

    @Override
    public boolean login(String email, String pass) {
        return false;
    }

    @Override
    public void register(String email, String pass, String firstName, String lastName, String phone) {

    }
}
