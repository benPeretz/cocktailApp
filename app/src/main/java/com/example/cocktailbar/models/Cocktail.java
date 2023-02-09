package com.example.cocktailbar.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Cocktail implements Serializable {
    private String strDrink;
    private String idDrink;
    private String strCategory;
    private String photo;
    private String strAlcoholic;
    private String strGlass;
    private String strInstructions;
    private ArrayList<String> strIngredient;
    private ArrayList<String> strMeasure;

    public Cocktail(String strDrink, String strCategory, String photo, String strAlcoholic, String strGlass, String strInstructions, ArrayList<String> strIngredient, ArrayList<String> strMeasure,String idDrink) {
        this.strDrink = strDrink;
        this.strCategory = strCategory;
        this.photo = photo;
        this.strAlcoholic = strAlcoholic;
        this.strGlass = strGlass;
        this.strInstructions = strInstructions;
        this.strIngredient = strIngredient;
        this.strMeasure = strMeasure;
        this.idDrink=idDrink;
    }

    public Cocktail() {
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public String getStrAlcoholic() {
        return strAlcoholic;
    }

    public void setStrAlcoholic(String strAlcoholic) {
        this.strAlcoholic = strAlcoholic;
    }

    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public ArrayList<String> getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(ArrayList<String> strIngredient) {
        this.strIngredient = strIngredient;
    }

    public ArrayList<String> getStrMeasure() {
        return strMeasure;
    }

    public void setStrMeasure(ArrayList<String> strMeasure) {
        this.strMeasure = strMeasure;
    }

    public Cocktail(String strDrink, String strCategory , String photo,String idDrink) {

        this.idDrink=idDrink;
        this.strCategory=strCategory;
        this.strDrink=strDrink;
        this.photo=photo;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String flag) {
        this.photo = flag;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }
}



