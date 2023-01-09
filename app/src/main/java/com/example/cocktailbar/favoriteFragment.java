package com.example.cocktailbar;

import static android.content.ContentValues.TAG;

import android.graphics.drawable.AnimationDrawable;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


public class favoriteFragment extends Fragment implements recyclerViewInterface{
    ArrayList<String>favorite;//favorite from firebase-list of drink id
    ArrayList<Cocktail>favoriteCocktail;//favorite from firebase-list of drink id
    View view;
    ArrayList<Cocktail> arr;
    MainActivity2 mainActivity2 ;
    CocktailAdapter cocktailAdapter;
    FirebaseFirestore userRef;



    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        /*
        mainActivity2 = (MainActivity2) getActivity();


        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);


        mAuth = FirebaseAuth.getInstance();

        //DataSevice ds=new DataSevice();
        //arr= ds.getArrCocktail();
        arr=new ArrayList<>();

        favoriteCocktail=new ArrayList<>();
        favorite=new ArrayList<>();





        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        userRef=FirebaseFirestore.getInstance();

        cocktailAdapter=new CocktailAdapter(arr,this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(cocktailAdapter);

        EventChangesListener();

       readFavoriteFromFireStore();
*/

        mainActivity2 = (MainActivity2) getActivity();
        mAuth = FirebaseAuth.getInstance();
        userRef= FirebaseFirestore.getInstance();

        DataSevice ds=new DataSevice();
        arr= ds.getArrCocktail();

        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);

        favoriteCocktail=new ArrayList<>();

        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        cocktailAdapter=new CocktailAdapter(favoriteCocktail,this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(cocktailAdapter);



        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading data...");
        progressDialog.setCancelable(false);

        readFavoriteFromFireStore(progressDialog);

        //readFavoriteFromFireStore();
        return view;
    }



    public void readFavoriteFromFireStore(ProgressDialog progressDialog){

        FirebaseUser user=mAuth.getCurrentUser();

        progressDialog.show();
        //if the user already login
        if(user!=null){
            String email =user.getEmail();
        DocumentReference docRef = userRef.collection("user").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    progressDialog.dismiss(); // dismiss progress dialog
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        favorite=(ArrayList<String>)document.get("favorite");
                        favoriteCocktail.clear();//clear the array first
                        for(int i=0;i<favorite.size();i++){
                            for(int j=0;j<arr.size();j++){
                                if(favorite.get(i).equals(arr.get(j).getIdDrink().replace("\"",""))){
                                    favoriteCocktail.add(arr.get(j));
                                }
                            }
                        }
                        cocktailAdapter.notifyDataSetChanged();

                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    progressDialog.dismiss(); // dismiss progress dialog
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }}

    private void EventChangesListener() {

        userRef.collection("userF")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error!=null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for(DocumentChange dc :value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                arr.add(dc.getDocument().toObject(Cocktail.class));
                            }
                            cocktailAdapter.notifyDataSetChanged();

                        }

                    }
                });
    }

    public void onItemClick(int position) {

        Toast.makeText(requireContext(),"stam"+position,Toast.LENGTH_LONG).show();

        Bundle bundle=new Bundle();
        bundle.putSerializable("cocktail",arr.get(position));

        cocktailDetailFragment cocktailDF=new cocktailDetailFragment();
        cocktailDF.setArguments(bundle);

        mainActivity2.replaceFragment(cocktailDF);



    }


}