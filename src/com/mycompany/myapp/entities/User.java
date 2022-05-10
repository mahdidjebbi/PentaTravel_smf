/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author 
 */
public class User {
    
    //var
    private int id;
    private int age;
    private String name;
    private String prenom;
    private String email;
    private String number;
    private String role;
    private String Password;
    //constructor

    public User() {
    }

    public User(int age, String name, String prenom, String email, String number, String Password) {
        this.age = age;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.number = number;
        this.Password = Password;
    }
    

    public User(int age, String name, String prenom, String email, String number, String role, String password) {
        this.age = age;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.number = number;
        this.role = role;
        this.Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public User(int id, int age, String name, String prenom, String email, String number, String Password) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.number = number;
        this.Password = Password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", age=" + age + ", name=" + name + ", prenom=" + prenom + ", email=" + email + ", number=" + number + ", role=" + role + ", Password=" + Password + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

   

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   
    
}
