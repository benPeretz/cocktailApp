package com.example.cocktailbar;

import java.io.Serializable;

public class Cocktail implements Serializable {
    private String strDrink;
    private String strCategory;
    private String photo;

    public Cocktail(String strDrink,  String strCategory ,String photo) {

        this.strCategory=strCategory;
        this.strDrink=strDrink;
        this.photo=photo;
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

