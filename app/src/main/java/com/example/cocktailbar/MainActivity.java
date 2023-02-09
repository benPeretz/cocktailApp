package com.example.cocktailbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cocktailbar.models.Person;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    private  FirebaseAuth mAuth;
    private FirebaseStorage storage=FirebaseStorage.getInstance();

    //for user collection
    FirebaseFirestore userRef= FirebaseFirestore.getInstance();


    FirebaseDatabase firebaseDatabase;

    // creating a variable for our Database
    // Reference for Firebase.
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FirebaseApp.initializeApp(this);

        firebaseDatabase = FirebaseDatabase.getInstance();


        mAuth = FirebaseAuth.getInstance();
        StorageReference storageRef = storage.getReference();


        //mAuth.signOut();
        FirebaseUser user=mAuth.getCurrentUser();

        //if the user already login
        /*
        if(user!=null){
            Toast.makeText(this,"log in",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,MainActivity2.class);
            startActivity(intent);
        }

         */



    }
    //if the user already login
    public boolean currentUser(){
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            Toast.makeText(this,"log in",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,MainActivity2.class);
            startActivity(intent);

            return true;
        }
        else return false;
    }



public  void funcRegister(EditText emailText, EditText passText,EditText firstName,EditText lastName,EditText phon, View view) {// save info in realTime Database (fireBase)
    String email =emailText.getText().toString().trim();
    String password=passText.getText().toString().trim();

    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {//if succeed

                        write(new Person(email,phon.getText().toString(),firstName.getText().toString(),lastName.getText().toString()),view);
                        Toast.makeText(view.getContext(), "new account created",Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_mainActivity2);

                    } else {
                        Toast.makeText(view.getContext(), "reg not ok",Toast.LENGTH_LONG).show();



                    }
                }
            });

}


    public void funcLogin(EditText emailText,EditText passText,View view,int flag) {

        String email =emailText.getText().toString().trim();
        String password=passText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                                Toast.makeText(MainActivity.this, "login successful",Toast.LENGTH_LONG).show();
                                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainActivity2);


                        } else {
                            Toast.makeText(MainActivity.this, "login failed",Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }


public void write(Person p,View view){
    userRef.collection("user")
            .document(p.email)
            .set(p)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(view.getContext(), "reg ok",Toast.LENGTH_LONG).show();

                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(view.getContext(), "reg not ok",Toast.LENGTH_LONG).show();

                }
            });

}



}