package com.example.cocktailbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.MyViewHolder> {

    private ArrayList<Cocktail>dataSet;


    public CocktailAdapter(ArrayList<Cocktail>dataSet){
        this.dataSet=dataSet;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textViewName;
        TextView textViewCategory;
        ImageView imageViewCocktail;

        public MyViewHolder(View itemView){
            super(itemView);

            cardView=itemView.findViewById(R.id.card_view);
            textViewName=itemView.findViewById(R.id.cocktail_name);
            textViewCategory=itemView.findViewById(R.id.cocktail_category);
            imageViewCocktail=itemView.findViewById(R.id.cocktail_image);

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(context).inflate(R.layout.card_layout, parent ,false);
        View view = LayoutInflater.from(parent.getContext() ).inflate(R.layout.card_home_fragment_layout, parent ,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView textViewName =holder.textViewName;
        TextView textViewNativeName=holder.textViewCategory;
        ImageView imageViewF=holder.imageViewCocktail;



        textViewName.setText(dataSet.get(position).getStrCategory());
        textViewNativeName.setText(dataSet.get(position).getStrDrink());

        Glide.with(holder.itemView.getContext()).load(dataSet.get(position).getPhoto()).error(R.drawable.ic_launcher_background).into(imageViewF);

    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }








}
