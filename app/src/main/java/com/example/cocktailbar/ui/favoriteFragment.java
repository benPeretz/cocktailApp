package com.example.cocktailbar.ui;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cocktailbar.CocktailAdapter;
import com.example.cocktailbar.DataSevice;
import com.example.cocktailbar.MainActivity2;
import com.example.cocktailbar.R;
import com.example.cocktailbar.models.Cocktail;
import com.example.cocktailbar.recyclerViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;


public class favoriteFragment extends Fragment implements recyclerViewInterface {
    ArrayList<String>favorite;//favorite from firebase-list of drink id
    ArrayList<Cocktail>myCocktail;
    ArrayList<Cocktail>favoriteCocktail;//favorite from firebase-list of drink id
    ArrayList<Cocktail> arr;
    MainActivity2 mainActivity2 ;
    CocktailAdapter cocktailAdapter;
    FirebaseFirestore userRef;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favorite, container, false);


        mainActivity2 = (MainActivity2) getActivity();
        mAuth = FirebaseAuth.getInstance();//auth
        userRef= FirebaseFirestore.getInstance();

        DataSevice ds=new DataSevice();
        arr= ds.getArrCocktail();//getting the cocktail array from the api


        favoriteCocktail=new ArrayList<>();


        LinearLayout accountScreen=view.findViewById(R.id.accountScreen);
        LinearLayout guestScreen=view.findViewById(R.id.guestScreen);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);


        //recycler view
        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        cocktailAdapter=new CocktailAdapter(favoriteCocktail,this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cocktailAdapter);

        //getting the current user
        user=mAuth.getCurrentUser();
        if(user!=null){
            accountScreen.setVisibility(View.VISIBLE);
            guestScreen.setVisibility(View.GONE);
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading data...");
            progressDialog.setCancelable(false);

            //calling the function to get the favorite cocktail from the user
            readFavoriteFromFireStore(progressDialog);
        }
        else {
            guestScreen.setVisibility(View.VISIBLE);
            accountScreen.setVisibility(View.GONE);

        }
        return view;
    }



    public void readFavoriteFromFireStore(ProgressDialog progressDialog){


        progressDialog.show();
        //if the user already login
        if(user!=null){
            String email =user.getEmail();
            //getting the reference to the person
        DocumentReference docRef = userRef.collection("user").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    progressDialog.dismiss(); // dismiss progress dialog
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        //getting my cocktail favorite
                        ArrayList<HashMap<String, Object>> myCocktailMap = (ArrayList<HashMap<String, Object>>) document.get("cocktails");



                        myCocktail=new ArrayList<>();

                        String temp;
                        for (HashMap<String, Object> map : myCocktailMap) {
                            Cocktail cocktail = new Cocktail();
                            cocktail.setStrDrink((String) map.get("strDrink"));
                            temp=cocktail.getStrDrink();
                            cocktail.setIdDrink((String) map.get("idDrink"));
                            cocktail.setStrCategory((String) map.get("strCategory"));
                            cocktail.setPhoto((String) map.get("photo"));
                            cocktail.setStrAlcoholic((String) map.get("strAlcoholic"));
                            cocktail.setStrGlass((String) map.get("strGlass"));
                            cocktail.setStrInstructions((String) map.get("strInstructions"));
                            cocktail.setStrIngredient((ArrayList<String>) map.get("strIngredient"));
                            cocktail.setStrMeasure((ArrayList<String>) map.get("strMeasure"));

                            // set other properties of Cocktail object in a similar way
                            myCocktail.add(cocktail);
                        }

                        //getting the favorite id
                        favorite=(ArrayList<String>)document.get("favorite");


                        favoriteCocktail.clear();//clear the array first

                        for(int i=0;i<favorite.size();i++){
                            for(int j=0;j<arr.size();j++){
                                if(favorite.get(i).equals(arr.get(j).getIdDrink().replace("\"",""))){
                                    favoriteCocktail.add(arr.get(j));
                                }
                            }
                        }


                        //merging all the favorite and putting them inside one array
                        favoriteCocktail.addAll(myCocktail);

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

    public void onItemClick(int position) {

        Bundle bundle=new Bundle();
        bundle.putSerializable("cocktail",favoriteCocktail.get(position));

        cocktailDetailFragment cocktailDF=new cocktailDetailFragment();
        cocktailDF.setArguments(bundle);

        mainActivity2.replaceFragment(cocktailDF);



    }


}