package com.example.cocktailbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class homeFragment extends Fragment {
    View view;
    ArrayList<Cocktail> arr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_home, container, false);

        DataSevice ds=new DataSevice();
         arr= ds.getArrCocktail();

        CocktailAdapter cocktailAdapter=new CocktailAdapter(arr);
        //CocktailAdapter cocktailAdapter=new CocktailAdapter(arr, (recyclerViewInterface) this);

        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(cocktailAdapter);

        return view;

    }
    /*
    public void onItemClick(int position) {

        Bundle bundle =new Bundle();
        bundle.putString("in",arr.get(position).getStrDrink());
        MainActivity2 mainActivity2= (MainActivity2) getActivity();
        mainActivity2.replaceFragment(new cocktailDetailFragment());



    }

     */
}