package com.example.cocktailbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.cocktailbar.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity  {

    ActivityMain2Binding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMain2Binding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        replaceFragment(new homeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.home_button_NB:
                    replaceFragment(new homeFragment());
                    break;

                case R.id.favorite_button_NB:
                    replaceFragment(new favoriteFragment());

                    break;
                case R.id.profile_button_NB:
                    replaceFragment(new profileFragment());

                    break;


            }

            return true;



        });



    }

    public void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutContainer,fragment);
        fragmentTransaction.commit();
    }


    /*public void replaceFragment(Fragment fragment,Bundle bundle){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutContainer,fragment);
        fragmentTransaction.commit();
    }

     */
}