/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import session.MyMessage;
import entity.Korisnik;
import entity.Kupac;
import entity.Prodavac;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.LoginModel;
import session.MySession;
import view.RegistracijaView;
import view.TemplateView;

/**
 * FXML Controller class
 *
 * @author Vlada
 */
public class LoginController implements Initializable {

    @FXML
    private TextField KorImeTextField;
    @FXML
    private TextField LozinkaTextField;
    
    @FXML
    private Label loginGreska;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        
                //login
        String userName = KorImeTextField.getText();
        String userPass = LozinkaTextField.getText();

        if (userName.trim().equals("") || userName == null) {
            MyMessage.setErrorLabel(loginGreska, "Prazno korisnicko ime!", true);
        } else {
            MyMessage.removeErrorLabel(loginGreska);
        }

        if (userPass.trim().equals("") || userPass == null) {
            MyMessage.setErrorLabel(loginGreska, "Prazna sifra!", true);
            return;
        } else {
            MyMessage.removeErrorLabel(loginGreska);
        }

        Korisnik user = null;

        
            Kupac customer = LoginModel.getUserCustomer(userName, userPass);
            if(customer!=null){ user = customer;

        } else {
            Prodavac seller = LoginModel.getUserSeller(userName, userPass);
            user = seller;
        }

        if (user == null) {
            MyMessage.setErrorLabel(loginGreska, "Lose unesena sifra/korisnicko ime", true);
           // MyMessage.removeErrorLabel(loginGreska);

        } else {
            MySession.getSessionInstance().setSessionUser(user);
            Stage previousStage = (Stage) KorImeTextField.getScene().getWindow();
            new TemplateView().display(previousStage);
//            //MySession.getSessionInstance().setSessionUser(user);
//            //Stage previousStage = (Stage) KorImeTextField.getScene().getWindow();
//            display();
        }
    }

    @FXML
    private void loadRegistration(ActionEvent event) {
        Stage previousStage = (Stage) KorImeTextField.getScene().getWindow();
        new RegistracijaView().display(previousStage);
    }
    
    
     public void display() {
         MyMessage.removeErrorLabel(loginGreska);
    }

    
}
