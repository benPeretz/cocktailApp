package com.example.cocktailbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cocktailbar.databinding.ActivityMain2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity2 extends AppCompatActivity  {
    private FirebaseAuth mAuth;
    private FirebaseStorage storage=FirebaseStorage.getInstance();
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;
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
    public void read(EditText firstName, EditText lastName, EditText phoneNumber, EditText email, String currentUserPhoneNumber){
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(currentUserPhoneNumber);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("check", dataSnapshot.getValue(Person.class).toString());
                Person value = dataSnapshot.getValue(Person.class);
                if(value == null){
                    Toast.makeText(MainActivity2.this, "No such id", Toast.LENGTH_SHORT).show();
                }else{
                    firstName.setText(value.firstName);
                    lastName.setText(value.lastName);
                    phoneNumber.setText(value.phon);
                    email.setText(value.email);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity2.this, "No such id", Toast.LENGTH_SHORT).show();
            }
        });
    }
}