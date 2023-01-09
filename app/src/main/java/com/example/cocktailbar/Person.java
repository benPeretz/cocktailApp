package com.example.cocktailbar;

import java.util.ArrayList;

public class Person {

    public String email;
    public String phon;
    public String firstName;
    public String lastName;
    public ArrayList<String>favorite;

    public Person(String email, String phon, String firstName, String lastName) {
        this.email = email;
        this.phon = phon;
        this.firstName = firstName;
        this.lastName = lastName;
        this.favorite=new ArrayList<>();
    }
    public Person(){

    }
}
