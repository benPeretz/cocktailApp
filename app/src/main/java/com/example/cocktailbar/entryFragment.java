package com.example.cocktailbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class entryFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_entry, container, false);

        MainActivity mainActivity= (MainActivity) getActivity();
        Button btn_to_Login_page=view.findViewById(R.id.button_signIn_EF);
        Button btn_to_signup_page=view.findViewById(R.id.button_signUp_EF);
        Button btn_to_app_guestMode=view.findViewById(R.id.button_cocktail_EF);



        btn_to_Login_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_entryFragment_to_loginFragment);
            }
        });

        btn_to_signup_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_entryFragment_to_registerFragment);

            }
        });

        btn_to_app_guestMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mainActivity.moveToSecActivity();
                Navigation.findNavController(view).navigate(R.id.action_entryFragment_to_mainActivity2);
            }
        });


    return view;
    }
}//


/*
    JsonObject obj=rootobj.getAsJsonObject();

    JsonElement drinks =obj.get("drinks");
            if(drinks!=null){

                    JsonArray drinksArray=drinks.getAsJsonArray();
                    //JsonElement d=drinksArray.get(1).getAsJsonObject().get("strDrink");
                    for (JsonElement j:drinksArray){
                    JsonObject drink=j.getAsJsonObject();
                    JsonElement strDrink = drink.get("strDrink");
                    JsonElement strCategory = drink.get("strCategory");

 */