package com.example.cocktailbar.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.cocktailbar.MainActivity2;
import com.example.cocktailbar.R;
import com.example.cocktailbar.models.Cocktail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.SecureRandom;
import java.util.ArrayList;


public class myCocktailsFragment extends Fragment {

    private FirebaseAuth mAuth;
    MainActivity2 mainActivity2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_cocktails, container, false);

        mainActivity2=(MainActivity2) getActivity();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();


        EditText et_drinkName=view.findViewById(R.id.et_drinkName);
        EditText et_drinkCategory=view.findViewById(R.id.et_drinkCategory);
        EditText et_drinkAlcoholic=view.findViewById(R.id.et_drinkAlcoholic);
        EditText et_drinkGlass=view.findViewById(R.id.et_drinkGlass);
        EditText et_drinkInstructions=view.findViewById(R.id.et_drinkInstructions);
        EditText et_ingredient1=view.findViewById(R.id.et_ingredient1);
        EditText et_ingredient2=view.findViewById(R.id.et_ingredient2);
        EditText et_ingredient3=view.findViewById(R.id.et_ingredient3);
        EditText et_ingredient4=view.findViewById(R.id.et_ingredient4);
        EditText et_ingredient5=view.findViewById(R.id.et_ingredient5);
        EditText et_measure1=view.findViewById(R.id.et_measure1);
        EditText et_measure2=view.findViewById(R.id.et_measure2);
        EditText et_measure3=view.findViewById(R.id.et_measure3);
        EditText et_measure4=view.findViewById(R.id.et_measure4);
        EditText et_measure5=view.findViewById(R.id.et_measure5);

        Button btn_finish=view.findViewById(R.id.button_finishCocktail);

        LinearLayout accountScreen=view.findViewById(R.id.accountScreen);
        LinearLayout guestScreen=view.findViewById(R.id.guestScreen);


        if(user==null){
            guestScreen.setVisibility(View.VISIBLE);
            accountScreen.setVisibility(View.GONE);
        }else {
            accountScreen.setVisibility(View.VISIBLE);
            guestScreen.setVisibility(View.GONE);
        }

        ArrayList<String>ingredientArr=new ArrayList<>();
        ArrayList<String>measureArr=new ArrayList<>();




        //giving random id to our cocktail
        int min = 1;
        int max = 999999;
        int drinkId = min + new SecureRandom().nextInt((max - min) + 1);



        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ingredientArr.add(et_ingredient1.getText().toString());
                ingredientArr.add(et_ingredient2.getText().toString());
                ingredientArr.add(et_ingredient3.getText().toString());
                ingredientArr.add(et_ingredient4.getText().toString());
                ingredientArr.add(et_ingredient5.getText().toString());


                measureArr.add(et_measure1.getText().toString());
                measureArr.add(et_measure2.getText().toString());
                measureArr.add(et_measure3.getText().toString());
                measureArr.add(et_measure4.getText().toString());
                measureArr.add(et_measure5.getText().toString());



                Cocktail cocktail=new Cocktail(et_drinkName.getText().toString(),et_drinkCategory.getText().toString(),
                        null,et_drinkAlcoholic.getText().toString(),et_drinkGlass.getText().toString(),et_drinkInstructions.getText().toString(),
                        ingredientArr,measureArr,String.valueOf(drinkId));


                Toast.makeText(requireContext(),"Added to favorite",Toast.LENGTH_LONG).show();
                mainActivity2.writeToCollectionMyCocktail(cocktail,view);
                homeFragment homeFragment=new homeFragment();
                mainActivity2.replaceFragment(homeFragment);
            }
        });



        return view;
    }
}