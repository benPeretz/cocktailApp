package com.example.cocktailbar;

import android.os.StrictMode;
import android.util.Log;

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





                String strIngredient="strIngredient";
                String strMeasure="strMeasure";
                int temp=1;


                for (JsonElement j:drinksArray){
                    JsonObject drink=j.getAsJsonObject();
                    JsonElement strDrink = drink.get("strDrink");
                    JsonElement strCategory = drink.get("strCategory");
                    JsonElement strDrinkThumb =drink.get("strDrinkThumb");
                    JsonElement idDrink =drink.get("idDrink");
                    JsonElement strGlass =drink.get("strGlass");
                    JsonElement strAlcoholic =drink.get("strAlcoholic");
                    JsonElement strInstructions =drink.get("strInstructions");
                    String strDrinkThumbUrl=strDrinkThumb.getAsString();
                    temp=1;

                    ArrayList<String>ingredientArr=new ArrayList<String>();
                    ArrayList<String>measureArr=new ArrayList<String>();
                    while(temp<6){

                        strMeasure=strMeasure+String.valueOf(temp);
                        strIngredient=strIngredient+String.valueOf(temp);
                        JsonElement Ingredient =drink.get(strIngredient);
                        JsonElement Measure =drink.get(strMeasure);
                        String STRing=Ingredient.toString();

                        if(STRing.equals("null")){
                            strMeasure=strMeasure.substring(0,strMeasure.length()-1);
                            strIngredient=strIngredient.substring(0,strIngredient.length()-1);
                            break;
                        }
                        ingredientArr.add(Ingredient.toString());
                        measureArr.add(Measure.toString());
                        temp++;
                        strMeasure=strMeasure.substring(0,strMeasure.length()-1);
                        strIngredient=strIngredient.substring(0,strIngredient.length()-1);




                    }


                    //ArrayList<String>a=ingredientArr;
                    //int size =ingredientArr.size();

                    cocktailDetail=new Cocktail(strDrink.toString(),strCategory.toString(),strDrinkThumbUrl.toString(),strAlcoholic.toString()
                            ,strGlass.toString(),strInstructions.toString(),ingredientArr,measureArr,idDrink.toString());
                    arrCocktail.add(cocktailDetail);
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrCocktail;

    }
    /*
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

                ArrayList<String>ingredientArr=new ArrayList<String>();
                ArrayList<String>measureArr=new ArrayList<String>();

                String strIngredient="strIngredient";
                String strMeasure="strMeasure";
                int temp=1;

                strMeasure=strMeasure+String.valueOf(temp);
                strIngredient=strIngredient+String.valueOf(temp);

                for (JsonElement j:drinksArray){
                    JsonObject drink=j.getAsJsonObject();
                    JsonElement strDrink = drink.get("strDrink");
                    JsonElement strCategory = drink.get("strCategory");
                    JsonElement strDrinkThumb =drink.get("strDrinkThumb");
                    JsonElement idDrink =drink.get("idDrink");
                    JsonElement strGlass =drink.get("strGlass");
                    JsonElement strAlcoholic =drink.get("strAlcoholic");
                    JsonElement strInstructions =drink.get("strInstructions");
                    String strDrinkThumbUrl=strDrinkThumb.getAsString();

                    while(temp<12){

                        JsonElement Ingredient =drink.get(strIngredient);
                        JsonElement Measure =drink.get(strMeasure);
                        String STRing=Ingredient.toString();

                        if(STRing.equals("null")){
                            break;
                        }
                        ingredientArr.add(Ingredient.toString());
                        measureArr.add(Measure.toString());
                        temp++;
                        strMeasure=strMeasure.substring(0,strMeasure.length()-1);
                        strIngredient=strIngredient.substring(0,strIngredient.length()-1);

                        strMeasure=strMeasure+String.valueOf(temp);
                        strIngredient=strIngredient+String.valueOf(temp);


                    }


                    ArrayList<String>a=ingredientArr;
                    int size =ingredientArr.size();

                    cocktailDetail=new Cocktail(strDrink.toString(),strCategory.toString(),strDrinkThumbUrl.toString(),strAlcoholic.toString()
                    ,strGlass.toString(),strInstructions.toString(),ingredientArr,measureArr,idDrink.toString());
                }
            }







        } catch (IOException e) {
            e.printStackTrace();
        }

        return cocktailDetail;
    }

     */
}
