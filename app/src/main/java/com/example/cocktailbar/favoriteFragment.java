package com.example.cocktailbar;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class favoriteFragment extends Fragment implements recyclerViewInterface{

    View view;
    ArrayList<Cocktail> arr;
    MainActivity2 mainActivity2 ;
    CocktailAdapter cocktailAdapter;
    FirebaseFirestore userRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favorite, container, false);
        mainActivity2 = (MainActivity2) getActivity();


        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);






        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        userRef=FirebaseFirestore.getInstance();
        arr=new ArrayList<>();

        cocktailAdapter=new CocktailAdapter(arr,this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(cocktailAdapter);

        EventChangesListener();


        return view;
    }

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