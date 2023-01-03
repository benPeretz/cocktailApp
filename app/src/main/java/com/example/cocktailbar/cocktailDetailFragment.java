package com.example.cocktailbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class cocktailDetailFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cocktail_detail, container, false);

        DataSevice ds=new DataSevice();


        TextView tv_name=view.findViewById(R.id.tv_name);
        ImageView iv_image=view.findViewById(R.id.iv_image);
        TextView tv_category_result=view.findViewById(R.id.tv_category_result);
        TextView tv_alcoholic_result=view.findViewById(R.id.tv_alcoholic_result);
        TextView tv_Glass_result=view.findViewById(R.id.tv_Glass_result);
        TextView tv_ingredient1=view.findViewById(R.id.tv_ingredient1);
        TextView tv_measure1=view.findViewById(R.id.tv_measure1);
        TextView tv_ingredient2=view.findViewById(R.id.tv_ingredient2);
        TextView tv_ingredient3=view.findViewById(R.id.tv_ingredient3);
        TextView tv_measure2=view.findViewById(R.id.tv_measure2);
        TextView tv_measure3=view.findViewById(R.id.tv_measure3);
        TextView tv_ingredient4=view.findViewById(R.id.tv_ingredient4);
        TextView tv_measure4=view.findViewById(R.id.tv_measure4);
        TextView tv_ingredient5=view.findViewById(R.id.tv_ingredient5);
        TextView tv_measure5=view.findViewById(R.id.tv_measure5);
        TextView tv_ingredient6=view.findViewById(R.id.tv_ingredient6);
        TextView tv_measure6=view.findViewById(R.id.tv_measure6);
        TextView tv_instructions_result=view.findViewById(R.id.tv_instructions_result);




        String id =getArguments().getString("id").replace("\"","");

        Cocktail cocktail=ds.getCocktailDetail(id);
        tv_category_result.setText(cocktail.getStrCategory().replace("\"",""));
        tv_alcoholic_result.setText(cocktail.getStrAlcoholic().replace("\"",""));
        tv_Glass_result.setText(cocktail.getStrGlass().replace("\"",""));
        tv_name.setText(cocktail.getStrDrink().replace("\"",""));
        tv_instructions_result.setText(cocktail.getStrInstructions().replace("\"",""));
        Glide.with(this).load(cocktail.getPhoto()).error(R.drawable.ic_launcher_background).into(iv_image);

        ArrayList<String>ingredient=cocktail.getStrIngredient();
        ArrayList<String>measure=cocktail.getStrMeasure();

        int size=ingredient.size();
        switch (size){
            case 1:
                tv_ingredient1.setText(ingredient.get(0).replace("\"",""));
                tv_measure1.setText(measure.get(0).replace("\"",""));
                break;
            case 2:
                tv_ingredient1.setText(ingredient.get(0).replace("\"",""));
                tv_ingredient2.setText(ingredient.get(1).replace("\"",""));
                tv_measure1.setText(measure.get(0).replace("\"",""));
                tv_measure2.setText(measure.get(1).replace("\"",""));

                break;
            case 3:
                tv_ingredient1.setText(ingredient.get(0).replace("\"",""));
                tv_ingredient2.setText(ingredient.get(1).replace("\"",""));
                tv_ingredient3.setText(ingredient.get(2).replace("\"",""));
                tv_measure1.setText(measure.get(0).replace("\"",""));
                tv_measure2.setText(measure.get(1).replace("\"",""));
                tv_measure3.setText(measure.get(2).replace("\"",""));

                break;
            case 4:
                tv_ingredient1.setText(ingredient.get(0).replace("\"",""));
                tv_ingredient2.setText(ingredient.get(1).replace("\"",""));
                tv_ingredient3.setText(ingredient.get(2).replace("\"",""));
                tv_ingredient4.setText(ingredient.get(3).replace("\"",""));
                tv_measure1.setText(measure.get(0).replace("\"",""));
                tv_measure2.setText(measure.get(1).replace("\"",""));
                tv_measure3.setText(measure.get(2).replace("\"",""));
                tv_measure4.setText(measure.get(3).replace("\"",""));

                break;
            case 5:
                tv_ingredient1.setText(ingredient.get(0).replace("\"",""));
                tv_ingredient2.setText(ingredient.get(1).replace("\"",""));
                tv_ingredient3.setText(ingredient.get(2).replace("\"",""));
                tv_ingredient4.setText(ingredient.get(3).replace("\"",""));
                tv_ingredient5.setText(ingredient.get(4).replace("\"",""));
                tv_measure1.setText(measure.get(0).replace("\"",""));
                tv_measure2.setText(measure.get(1).replace("\"",""));
                tv_measure3.setText(measure.get(2).replace("\"",""));
                tv_measure4.setText(measure.get(3).replace("\"",""));
                tv_measure5.setText(measure.get(4).replace("\"",""));

                break;

            default:
                tv_ingredient1.setText(ingredient.get(0).replace("\"",""));
                tv_ingredient2.setText(ingredient.get(1).replace("\"",""));
                tv_ingredient3.setText(ingredient.get(2).replace("\"",""));
                tv_ingredient4.setText(ingredient.get(3).replace("\"",""));
                tv_ingredient5.setText(ingredient.get(4).replace("\"",""));
                tv_ingredient6.setText(ingredient.get(5).replace("\"",""));
                tv_measure1.setText(measure.get(0).replace("\"",""));
                tv_measure2.setText(measure.get(1).replace("\"",""));
                tv_measure3.setText(measure.get(2).replace("\"",""));
                tv_measure4.setText(measure.get(3).replace("\"",""));
                tv_measure5.setText(measure.get(4).replace("\"",""));
                tv_measure6.setText(measure.get(5).replace("\"",""));
                break;


        }





    return view;
    }
}