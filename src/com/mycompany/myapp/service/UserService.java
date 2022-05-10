 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.NewsfeedForm;
import com.mycompany.gui.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Static;

/**
 *
 * @author   n 
 */
public class UserService {
    
    //var
    boolean resultOK;
    ConnectionRequest req;
    static UserService instance;
    ArrayList<User> Users = new ArrayList<>();
    
    
    //constructor
    private UserService() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static UserService getInstance(){
        
        if (instance == null) {
            instance = new UserService();
        }
        
        return instance;
    }
    
    
    
    
    //
    public boolean addUserAction(User u){
        
        String url = Static.BASE_URL + "/AjouterUser?name="+u.getName()+"&prenom="+u.getPrenom()+"&age="+u.getAge()+"&email="+u.getEmail()+"&number="+u.getNumber()+"&Password="+u.getPassword();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            String str = new String(req.getResponseData());
            System.out.println("data=="+str);   
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    //PARSE user JSON : convert JSON to java objects
    public ArrayList<User> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> UsersListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> UsersList = (ArrayList<Map<String,Object>>) UsersListJson.get("root");
            
            for (Map<String, Object> obj : UsersList) {
                
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                u.setName(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setAge((int) Float.parseFloat(obj.get("age").toString()));
                u.setEmail(obj.get("email").toString());
                u.setPassword(obj.get("password").toString());
                
                u.setNumber(obj.get("number").toString());
                
                Users.add(u);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return Users;  
    }



    //GET user
    public ArrayList<User> getUser(){
        
         String url = Static.BASE_URL+"/display";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             Users = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return Users;
    }
      public boolean deleteUser(int t) {
        String url = Static.BASE_URL + "/Deleteuser?id=" + t;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      public boolean modifierUser(User u) {
        String url = Static.BASE_URL +"/updateUser?id="+u.getId()+"&name="+u.getName()+"&prenom="+u.getPrenom()+"&age="+u.getAge()+"&email="+u.getEmail()+"&number="+u.getNumber();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }  
      
      public boolean modifierPassword(User u) {
        String url = Static.BASE_URL +"/updatePassword?id="+u.getId()+"&password="+u.getPassword();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }  
 public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Static.BASE_URL+"/loginmobile?email="+email.getText().toString()+"&Password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                float age = Float.parseFloat(user.get("age").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                SessionManager.setAge((int)age);//jibt id ta3 user ly3ml login w sajltha fi session ta3i

                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setPassowrd(user.get("number").toString());
                
                SessionManager.setNom(user.get("nom").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                
                
                
                if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                   new NewsfeedForm(rs).show();
                    
                    }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    

}
