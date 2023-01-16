package com.example.cocktailbar;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cocktailbar.databinding.ActivityMain2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity   {
    private FirebaseAuth mAuth;
    private FirebaseStorage storage=FirebaseStorage.getInstance();
    FirebaseDatabase firebaseDatabase;

    public Person getCurrentUser() {
        return CurrentUser;
    }

    private Person CurrentUser;




    //for favorite
    FirebaseFirestore userRef= FirebaseFirestore.getInstance();


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
        mAuth = FirebaseAuth.getInstance();


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

                case R.id.myCocktail_button_NB:
                    replaceFragment(new myCocktailsFragment());

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
    public void read(EditText firstNameText, EditText lastNameText, EditText phoneNumberText, EditText emailText, String currentUserEmail, View view){
        // Read from the database
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DocumentReference docRef = userRef.collection("user").document(currentUserEmail);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    CurrentUser = new Person();
                    DocumentSnapshot document = task.getResult();
                    CurrentUser.firstName = document.get("firstName").toString();
                    CurrentUser.lastName = document.get("lastName").toString();
                    CurrentUser.phon = document.get("phon").toString();
                    CurrentUser.email = currentUserEmail;
                    firstNameText.setText(CurrentUser.firstName);
                    lastNameText.setText(CurrentUser.lastName);
                    phoneNumberText.setText(CurrentUser.phon);
                    emailText.setText(currentUserEmail);
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    addTextChangeToAllEditText(firstNameText, lastNameText, phoneNumberText, emailText, view);
                } else {
                    progressDialog.dismiss();
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }

    Boolean isFirstNameChanged = false, isLastNameChanged = false, isPhoneNumberChange = false, isEmailChanged = false;
    private void addTextChangeToAllEditText(EditText firstNameText, EditText lastNameText, EditText phoneNumberText, EditText emailText, View view){
        String initFirstName = getCurrentUser().firstName;
        String initLastName = getCurrentUser().lastName;
        String initPhoneNumber = getCurrentUser().phon;
        String initEmail = getCurrentUser().email;
        firstNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(initFirstName)){
                    isFirstNameChanged = true;
                }
                else{
                    isFirstNameChanged = false;
                }
                checkifEditTextChanged(view);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        lastNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(initLastName)){
                    isLastNameChanged = true;
                }
                else{
                    isLastNameChanged = false;
                }
                checkifEditTextChanged(view);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        phoneNumberText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(initPhoneNumber)){
                    isPhoneNumberChange = true;
                }
                else{
                    isPhoneNumberChange = false;
                }
                checkifEditTextChanged(view);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(initEmail)){
                    isEmailChanged = true;
                }
                else{
                    isEmailChanged = false;
                }
                checkifEditTextChanged(view);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    private void checkifEditTextChanged(View view){
        Button confirmButton = view.findViewById(R.id.button_save_profile_settings);
        confirmButton.setEnabled(isEmailChanged || isFirstNameChanged || isLastNameChanged || isPhoneNumberChange);
    }


/*
    public void writeToCollection(Cocktail cocktail, View view){


        userRef.collection("userF")
                .document(cocktail.getIdDrink()).set(cocktail)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(view.getContext(),"add",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(view.getContext(),"not added",Toast.LENGTH_LONG).show();
                    }
                });


    }

 */


    public void writeToCollection(Cocktail cocktail, View view){

        FirebaseUser user=mAuth.getCurrentUser();


        //if the user already login
        if(user!=null){
            String email =user.getEmail();
            DocumentReference docRef = userRef.collection("user").document(email);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Person person = document.toObject(Person.class);
                            String favoriteString = cocktail.getIdDrink().replace("\"","");
                            person.favorite.add(favoriteString);
                            docRef.set(person);
                        } else {
                            // The person document does not exist
                        }
                    } else {
                        // An error occurred while retrieving the document
                    }
                }
            });
        }



    }


/*
    public ArrayList<Cocktail> readFromCollection(){

        ArrayList<Cocktail>arr=new ArrayList<Cocktail>();

        userRef.collection("userF")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                            Cocktail cocktail=documentSnapshot.toObject(Cocktail.class);
                            arr.add(cocktail);
                        }




                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        return arr;
    }

 */

    public void readFromCollection(OnCocktailsFetchedListener listener) {
         ArrayList<Cocktail> arr = new ArrayList<Cocktail>();

        userRef.collection("userF")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Cocktail cocktail = documentSnapshot.toObject(Cocktail.class);
                            arr.add(cocktail);
                        }
                        listener.onCocktailsFetched(arr);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFetchFailed(e);
                    }
                });
    }

    public void readFromCollections(OnCocktailsFetchedListener listener) {
        ArrayList<Cocktail> arr = new ArrayList<Cocktail>();

        userRef.collection("userF")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Cocktail cocktail = documentSnapshot.toObject(Cocktail.class);
                            arr.add(cocktail);
                        }
                        listener.onCocktailsFetched(arr);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFetchFailed(e);
                    }
                });
    }


    public void logout(){
        mAuth.signOut();
        Toast.makeText(this,"logout",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,MainActivity.class);
        //intent.putExtra("userLogout",1);
        startActivity(intent);
    }
}