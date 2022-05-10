/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author soon
 */
public class SessionManager {
    

    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String nom ;
    private static String prenom ; 
    private static String email; 
    private static String passowrd ;
    private static int age;
    private static String number;
    private static String role;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getNom() {
        return pref.get("nom",nom);
    }

    public static void setNom(String nom) {
         pref.set("nom",nom);
    }
    public static String getPreom() {
        return pref.get("prenom",prenom);
    }

    public static void setPrenom(String prenom) {
         pref.set("prenom",prenom);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }
     public static String getNumber() {
        return pref.get("number",number);
    }

    public static void setNumber(String number) {
         pref.set("number",number);
    }
public static int getAge() {
        return pref.get("age",age);
    }

    public static void setAge(int age) {
         pref.set("age",age);
    }
   
    
    
    
    
    
    
}
