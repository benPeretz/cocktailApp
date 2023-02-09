package com.example.cocktailbar.repository;

public interface IAuthRepository {

    void currentUser();

     boolean login(String email,String pass);

    void register(String email,String pass,String firstName,String lastName,String phone );

}
