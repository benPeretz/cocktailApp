package com.example.cocktailbar;

import android.os.StrictMode;

import com.example.cocktailbar.models.Cocktail;
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
            url=new URL(sURL);//initializing the URL object with the URL string
        } catch (MalformedURLException e) {//catching the exception if the URL is not properly formatted
            e.printStackTrace();
        }

        try {// io acception like 404

            HttpURLConnection request=(HttpURLConnection) url.openConnection(); //creating an HTTP URL connection to the URL
            request.connect();//connecting to the URL


            JsonParser js = new JsonParser(); //creating a JsonParser object
            JsonElement root = js.parse(new InputStreamReader((InputStream) request.getContent())); //parsing the data received from the API in Json format
            JsonElement rootobj =root.getAsJsonObject();//getting the Json data as a Json object
            JsonObject obj=rootobj.getAsJsonObject();//casting the root Json element to a Json object

            JsonElement drinks =obj.get("drinks"); //getting the "drinks" element from the Json object

            if(drinks!=null){

                //if its not null convert in a json array
                JsonArray drinksArray=drinks.getAsJsonArray();


                String strIngredient="strIngredient";
                String strMeasure="strMeasure";
                int temp=1;


                for (JsonElement j:drinksArray){//looping through each element in the drinks array
                    JsonObject drink=j.getAsJsonObject();//casting the current Json element to a Json object
                    JsonElement strDrink = drink.get("strDrink");//getting the "strDrink" element from the Json object
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

}
