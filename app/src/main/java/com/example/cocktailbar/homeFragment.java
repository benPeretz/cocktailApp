package com.example.cocktailbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
         mainActivity2= (MainActivity2) getActivity();
        DataSevice ds=new DataSevice();
         arr= ds.getArrCocktail();

       // CocktailAdapter cocktailAdapter=new CocktailAdapter(arr);
        CocktailAdapter cocktailAdapter=new CocktailAdapter(arr,this);

        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager=new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(cocktailAdapter);

        return view;

    }

    public void onItemClick(int position) {

        Toast.makeText(requireContext(),"stam"+position,Toast.LENGTH_LONG).show();

        Bundle bundle=new Bundle();
        bundle.putString("id",arr.get(position).getIdDrink().toString());
        bundle.putString("pic",arr.get(position).getPhoto().toString());

        cocktailDetailFragment cocktailDF=new cocktailDetailFragment();
        cocktailDF.setArguments(bundle);

        mainActivity2.replaceFragment(cocktailDF);



    }


}