package com.example.cocktailbar;

import android.os.StrictMode;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataSevice {
    private ArrayList<Cocktail> arrCocktail=new ArrayList<>();
    private Cocktail cocktailDetail;

    public ArrayList<Cocktail> getArrCocktail(){

        String sURL="https://www.thecocktaildb.com/api/json/v1/1/search.php?s";
        URL url=null;

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {//if url is not good
            url=new URL(sURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {// io acception like 404

            HttpURLConnection request=(HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser js = new JsonParser();
            JsonElement root = js.parse(new InputStreamReader((InputStream) request.getContent()));//get from json String element
            JsonElement rootobj =root.getAsJsonObject();

            JsonObject obj=rootobj.getAsJsonObject();

            JsonElement drinks =obj.get("drinks");
            if(drinks!=null){

                JsonArray drinksArray=drinks.getAsJsonArray();
                //JsonElement d=drinksArray.get(1).getAsJsonObject().get("strDrink");
                for (JsonElement j:drinksArray){
                    JsonObject drink=j.getAsJsonObject();
                    JsonElement strDrink = drink.get("strDrink");
                    JsonElement strCategory = drink.get("strCategory");
                    JsonElement strDrinkThumb =drink.get("strDrinkThumb");
                    JsonElement idDrink =drink.get("idDrink");

                    String strDrinkThumbUrl=strDrinkThumb.getAsString();
                    //JsonElement strDrink =obj.get("strDrink");
                    //JsonElement strCategory =obj.get("strCategory");


                    arrCocktail.add(new Cocktail(strDrink.toString(),strCategory.toString(),strDrinkThumbUrl,idDrink.toString()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrCocktail;

    }
    public Cocktail getCocktailDetail(String strId){

        String sURL="https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i="+strId;
        URL url=null;


        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {//if url is not good
            url=new URL(sURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {// io acception like 404

            HttpURLConnection request=(HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser js = new JsonParser();
            JsonElement root = js.parse(new InputStreamReader((InputStream) request.getContent()));//get from json String element
            JsonElement rootobj =root.getAsJsonObject();

            JsonObject obj=rootobj.getAsJsonObject();

            JsonElement drinks =obj.get("drinks");
            if(drinks!=null){

                JsonArray drinksArray=drinks.getAsJsonArray();
                //JsonElement d=drinksArray.get(1).getAsJsonObject().get("strDrink");
                for (JsonElement j:drinksArray){
                    JsonObject drink=j.getAsJsonObject();
                    JsonElement strDrink = drink.get("strDrink");
                    JsonElement strCategory = drink.get("strCategory");
                    JsonElement strDrinkThumb =drink.get("strDrinkThumb");
                    JsonElement idDrink =drink.get("idDrink");

                    String strDrinkThumbUrl=strDrinkThumb.getAsString();
                    //JsonElement strDrink =obj.get("strDrink");
                    //JsonElement strCategory =obj.get("strCategory");


                    cocktailDetail=new Cocktail(strDrink.toString(),strCategory.toString(),strDrinkThumbUrl,idDrink.toString());
                }
            }







        } catch (IOException e) {
            e.printStackTrace();
        }

        return cocktailDetail;
    }
}
