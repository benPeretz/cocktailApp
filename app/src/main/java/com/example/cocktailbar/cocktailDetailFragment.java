package com.example.cocktailbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class cocktailDetailFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cocktail_detail, container, false);

        DataSevice ds=new DataSevice();

        TextView textView=view.findViewById(R.id.textName);
        ImageView imageView=view.findViewById(R.id.imageView);

        String id =getArguments().getString("id").replace("\"","");


        String pic =getArguments().getString("pic");

        Cocktail cocktail=ds.getCocktailDetail(id);

        textView.setText(cocktail.getStrDrink());



        //Glide.with(this).load(pic).error(R.drawable.ic_launcher_background).into(imageView);
        //textView.setText(name);
    return view;
    }
}