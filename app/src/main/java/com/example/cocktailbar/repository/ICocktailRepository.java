package com.example.cocktailbar.repository;

import com.example.cocktailbar.models.Person;

public interface ICocktailRepository {
    void write(Person person);
    void read(String firstName,String lastName,String phone,String email);

}
