/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.service.UserService;

/**
 *
 * @author soon
 */
public class UpdateUserForm extends Form {
    Form current;
    public UpdateUserForm(User u,Resources res) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        
        
        ////     TABDIL
        
        
        TextField nom = new TextField(u.getName() , "Nom" , 20 , TextField.ANY);

        TextField prenom = new TextField(u.getPrenom() , "prenom" , 20 , TextField.ANY);

        TextField number = new TextField(u.getNumber() , "number" , 20 , TextField.ANY);

        TextField age = new TextField(String.valueOf(u.getAge()) , "age" , 20 , TextField.ANY);

        TextField email = new TextField(u.getEmail() , "email" , 20 , TextField.ANY);
     
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
       
        

        
        nom.setUIID("NewsTopLine");
        prenom.setUIID("NewsTopLine");
         number.setUIID("NewsTopLine");
        age.setUIID("NewsTopLine");
         email.setUIID("NewsTopLine");
        
        
        nom.setSingleLineTextArea(true);
        prenom.setSingleLineTextArea(true);
        number.setSingleLineTextArea(true);
        age.setSingleLineTextArea(true);
       email.setSingleLineTextArea(true);
       
       
       
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           
           u.setName(nom.getText());
           u.setPrenom(prenom.getText());
           u.setAge(age.getAsInt(BASELINE));
           u.setEmail(email.getText());
           u.setNumber(number.getText());
       //appel fonction modfier reclamation men service
                  System.out.println("user"+u);

       if(UserService.getInstance().modifierUser(u)) { // if true
           new ListeUserForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListeUserForm(res).show();
       });
       
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        /////   TABDIL
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(nom),
                new FloatingHint(prenom),
                new FloatingHint(age),
                new FloatingHint(email),
                new FloatingHint(number),
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
}

