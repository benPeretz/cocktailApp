package com.example.cocktailbar;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cocktailbar.models.Cocktail;

import java.util.ArrayList;

public class CocktailAdapter extends RecyclerView.Adapter<CocktailAdapter.MyViewHolder> {

    private ArrayList<Cocktail>dataSet;

    private  recyclerViewInterface recyclerVI;

    private OnCocktailsFetchedListener onCocktailsFetchedListener;
    public CocktailAdapter(ArrayList<Cocktail>dataSet,recyclerViewInterface recyclerVI){

        this.dataSet=dataSet;
        this.recyclerVI=recyclerVI;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewName;
        TextView textViewCategory;
        ImageView imageViewCocktail;

        public MyViewHolder(View itemView,recyclerViewInterface recyclerVI){
        //public MyViewHolder(View itemView){
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
        View view = LayoutInflater.from(parent.getContext() ).inflate(R.layout.card_home_fragment_layout, parent ,false);

        MyViewHolder myViewHolder = new MyViewHolder(view,recyclerVI);

        return myViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        TextView textViewName =holder.textViewName;
        TextView textViewNativeName=holder.textViewCategory;
        ImageView imageViewF=holder.imageViewCocktail;




        textViewName.setText(dataSet.get(position).getStrDrink().replace("\"",""));
        textViewNativeName.setText(dataSet.get(position).getStrCategory().replace("\"",""));
        Glide.with(holder.itemView.getContext()).load(dataSet.get(position).getPhoto()).circleCrop().error(R.drawable.def_pic).into(imageViewF);
          
        
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerVI.onItemClick(position);
            }
        });  

    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }








}
