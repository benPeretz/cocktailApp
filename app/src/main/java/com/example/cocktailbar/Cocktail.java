package com.example.cocktailbar;

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

/*
    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:cardCornerRadius="25dp"
        android:background="@android:color/transparent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="10dp"
                    android:layout_weight="1"
                    android:text="Cocktail Name : "
                    android:textSize="24sp"


                    />

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Cocktail Name : "
                    android:textSize="24sp"
                    android:id="@+id/textName"


                    />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="10dp"
                    android:layout_weight="1"
                    android:text="Cocktail Category : "
                    android:textSize="24sp"


                    />

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Cocktail Category : "
                    android:textSize="24sp"


                    />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="10dp"
                    android:layout_weight="1"
                    android:text="Alcoholic : "
                    android:textSize="24sp"


                    />

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Alcoholic"
                    android:textSize="24sp"


                    />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="10dp"
                    android:layout_weight="1"
                    android:text="Glass : "
                    android:textSize="24sp"


                    />

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Glass"
                    android:textSize="24sp"


                    />

            </LinearLayout>
        </LinearLayout>
    </androidx.cardview.widget.CardView>
    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        app:cardCornerRadius="25dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="10dp"
                    android:layout_weight="1"
                    android:text="Cocktail Name : "
                    android:textSize="24sp"


                    />

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Cocktail Name : "
                    android:textSize="24sp"


                    />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="10dp"
                    android:layout_weight="1"
                    android:text="Cocktail Category : "
                    android:textSize="24sp"


                    />

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Cocktail Category : "
                    android:textSize="24sp"


                    />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="10dp"
                    android:layout_weight="1"
                    android:text="Alcoholic : "
                    android:textSize="24sp"


                    />

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Alcoholic"
                    android:textSize="24sp"


                    />

            </LinearLayout>

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal">

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_marginRight="10dp"
                    android:layout_weight="1"
                    android:text="Glass : "
                    android:textSize="24sp"


                    />

                <TextView
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="Glass"
                    android:textSize="24sp"


                    />

            </LinearLayout>
        </LinearLayout>
    </androidx.cardview.widget.CardView>


 */

