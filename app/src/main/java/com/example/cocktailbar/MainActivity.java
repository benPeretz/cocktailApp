package com.example.cocktailbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {

    private  FirebaseAuth mAuth;
    private FirebaseStorage storage=FirebaseStorage.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseApp.initializeApp(this);

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
            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {//if succeed

                        write(new Person(email,phon.getText().toString(),firstName.getText().toString(),lastName.getText().toString()));


                    } else {



                    }
                }
            });

}



    public void write(Person p ){



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(p.phon);//in this case we didnt take id from the user (we put it)

        myRef.setValue(p);

    }




}