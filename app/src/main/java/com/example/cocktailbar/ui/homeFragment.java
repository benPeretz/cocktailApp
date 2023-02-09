package com.example.cocktailbar.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cocktailbar.CocktailAdapter;
import com.example.cocktailbar.DataSevice;
import com.example.cocktailbar.MainActivity2;
import com.example.cocktailbar.R;
import com.example.cocktailbar.models.Cocktail;
import com.example.cocktailbar.recyclerViewInterface;

import java.util.ArrayList;


public class homeFragment extends Fragment implements recyclerViewInterface {
    View view;
    ArrayList<Cocktail> arr;
    MainActivity2 mainActivity2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_home, container, false);
         mainActivity2 = (MainActivity2) getActivity();
        DataSevice ds=new DataSevice();

        //getting the cocktail arraylist from the api
         arr= ds.getArrCocktail();


        //recycler view
        CocktailAdapter cocktailAdapter=new CocktailAdapter(arr,this);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cocktailAdapter);

        return view;

    }

    public void onItemClick(int position) {

        Bundle bundle=new Bundle();

        //passing the cocktail object that the user chosen
        bundle.putSerializable("cocktail",arr.get(position));
        cocktailDetailFragment cocktailDF=new cocktailDetailFragment();
        cocktailDF.setArguments(bundle);

        mainActivity2.replaceFragment(cocktailDF);



    }


}
/*

 */