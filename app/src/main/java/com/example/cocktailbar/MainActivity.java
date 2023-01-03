package com.example.cocktailbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    private  FirebaseAuth mAuth;
    private FirebaseStorage storage=FirebaseStorage.getInstance();

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
    }

/*
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view_MA, fragment);
        fragmentTransaction.commit();
    }

 */
public  void funcRegister(EditText emailText, EditText passText,EditText firstName,EditText lastName,EditText phon, View view) {// save info in realTime Database (fireBase)
    String email =emailText.getText().toString().trim();
    String password=passText.getText().toString().trim();



    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {//if succeed

                        write(new Person(email,phon.getText().toString(),firstName.getText().toString(),lastName.getText().toString()),view);
                        Toast.makeText(view.getContext(), "login ok",Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(view.getContext(), "login not ok",Toast.LENGTH_LONG).show();



                    }
                }
            });

}


    public void funcLogin(EditText emailText,EditText passText,View view) {

        String email =emailText.getText().toString().trim();
        String password=passText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "login successful",Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mainActivity2);

                           // Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                            //startActivity(intent);



                        } else {
                            Toast.makeText(MainActivity.this, "login failed",Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }

    public void moveToSecActivity(){
        Toast.makeText(MainActivity.this, "Guest Mode",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);



    }

    public void write(Person p,View view ){
        String temp =p.email;
         temp =p.phon;
         temp =p.lastName;
         temp =p.firstName;
        databaseReference = firebaseDatabase.getReference("users").child(p.phon);
         databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 databaseReference.setValue(p);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 Toast.makeText(view.getContext(),"login not ok",Toast.LENGTH_LONG).show();
             }
         });
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("users").child(p.phon);//in this case we didnt take id from the user (we put it)
        //myRef.setValue(p);
    }




}