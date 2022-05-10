/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.service.UserService;

/**
 *
 * @author 
 */
public class changePasswordForm extends BaseForm {

    public changePasswordForm(User u,Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl
        ));

        TextField password = new TextField("sandeep", "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("password", password);

        TextField confirmpassword = new TextField("sandeep", "Password", 20, TextField.PASSWORD);
        confirmpassword.setUIID("TextFieldBlack");
        addStringValue("confirmpassword", confirmpassword);
        
        Button Change = new Button("Change");
        add(Change);
        Change.requestFocus();
         Change.addActionListener(e -> {
            
            try{
                    if(!(password.getText().equals(confirmpassword.getText())))
                    {
                 Dialog.show("veuillez verifier votre password ","","annuler","ok");
                    }   
                else
                {
                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    u.setId(SessionManager.getId());
                     u.setPassword(password.getText());
                    
                    System.out.println("data user "+u);
                    UserService.getInstance().modifierPassword(u);
                    iDialog.dispose();
                    refreshTheme();
                    new NewsfeedForm(res).show();
                    
                }
            }catch(Exception ex)
                        {
                         ex.printStackTrace();
                        }
        
        
    });
            
       
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
