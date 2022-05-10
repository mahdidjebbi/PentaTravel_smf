/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.service.UserService;
import java.util.ArrayList;


/**
 *
 * @author PC
 */
public class ListeUserForm extends BaseForm{

private Resources theme;

    ListeUserForm(Resources res) {
        refreshTheme();
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List user");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        ArrayList<User> list =UserService.getInstance().getUser();
        for (User u : list ) {
            addItem(u,res);
        }

        
    }

    private void addItem(User u,Resources res) {
        
        Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label nom = new Label("nom : " + u.getName());
        Label prenom = new Label("prenom : " + u.getPrenom());
        Label age = new Label("age : " + u.getAge());
        Label email = new Label("email : " + u.getEmail());
        
        
        Button btnsupprimer = new Button ("supprimer");
        btnsupprimer.addActionListener(new ActionListener ()  {
           @Override
                    public void actionPerformed(ActionEvent evt){      
                        UserService.getInstance().deleteUser(u.getId());
} });
        Button btnupdate = new Button ("Update");
 btnupdate.addActionListener( new ActionListener ()  {
           @Override
                    public void actionPerformed(ActionEvent evt){
                UserService.getInstance().modifierUser(u);
                   
                    new UpdateUserForm(u,res).show();

                    } 
        });
        



        Label sep = new Label("------------------------------------------------------------------");

        C1.add(nom);
        C1.add(prenom);
        C1.add(age);
        C1.add(email);
        C1.add(btnsupprimer); 
        C1.add(btnupdate);
        C1.add(sep);
        C2.add(C1);
        add(C2);
    }
    

 
}
