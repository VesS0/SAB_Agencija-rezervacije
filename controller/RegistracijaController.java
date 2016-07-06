/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import session.MyMessage;
import DB.DB;
import entity.Korisnik;
import entity.Kupac;
import entity.Prodavac;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.LoginModel;
import model.RegistracijaModel;
import view.LoginView;

/**
 * FXML Controller class
 *
 * @author Vlada
 */
public class RegistracijaController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField phone1TextField;
    @FXML
    private Label POSBrojLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private RadioButton KupacRadio;
    @FXML
    private RadioButton ProdavacRadio;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
    @FXML
    private void register(ActionEvent event) {
        String userName = usernameTextField.getText();
        String userPass = passwordTextField.getText();
        String email = emailTextField.getText();
        String phoneNo = phoneTextField.getText();
        String firstName = nameTextField.getText();
        String surrname = surnameTextField.getText();
        String sellerCustomer = phone1TextField.getText();

        if (!testEmptyFields(userName,userPass,email,phoneNo,firstName,surrname,sellerCustomer)) return;

        if (ProdavacRadio.isSelected()) {
            Prodavac prod = new Prodavac(userName,userPass,email,phoneNo,firstName,surrname,sellerCustomer);
            int prodID = RegistracijaModel.registerSeller(prod);
            if (!checkAndSetID(prodID,prod)) return;
        }else {
            Kupac kup = new Kupac(userName,userPass,email,phoneNo,firstName,surrname,sellerCustomer);
            int kupID = RegistracijaModel.registerCustomer(kup);
            if(!checkAndSetID(kupID,kup)) return;
        }
        
        removeText();
        MyMessage.setErrorLabel(errorLabel, "Uspesno ste se registrovali!", false);
    }

        private boolean testEmptyFields(String userName,String userPass,String email,String phoneNo,String firstName,String surrname, String sellerCustomer)
    {
        if (userName == null || userName.trim().equals("")
                || userPass == null || userPass.trim().equals("")
                || email == null || email.trim().equals("")
                || phoneNo == null || phoneNo.trim().equals("")
                || firstName == null || firstName.trim().equals("")
                || surrname == null || surrname.trim().equals("")
                || sellerCustomer == null || sellerCustomer.trim().equals("")) 
        {
            MyMessage.setErrorLabel(errorLabel, "Neka polja su prazna!", true);
            return false;
        }
        return true;
    }

    
     private boolean checkAndSetID(int korID, Korisnik kor){
     if (korID == -1) {
                MyMessage.setErrorLabel(errorLabel, "Neuspesna registracija", true);
                return false;
            } else {
                kor.setUserID(korID);
                return true;
            }
     }
     
     private void removeText() {
        usernameTextField.setText("");
        passwordTextField.setText("");
        nameTextField.setText("");
        surnameTextField.setText("");
        emailTextField.setText("");
        phoneTextField.setText("");
        phone1TextField.setText("");
    }
     
     @FXML
    private void loadLogin(ActionEvent event) {
        Stage previousStage = (Stage) usernameTextField.getScene().getWindow();
        new LoginView().display(previousStage);
    }

    public void display() {
        final ToggleGroup group = new ToggleGroup();
        KupacRadio.setToggleGroup(group);
        ProdavacRadio.setToggleGroup(group);

        if (KupacRadio.isSelected()) {
            POSBrojLabel.setText("POS broj");
        } else {
            POSBrojLabel.setText("Br Kreditne");
        }
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (group.getSelectedToggle() == ProdavacRadio) {
                    POSBrojLabel.setText("POS broj");
                } else {
                    POSBrojLabel.setText("Br Kreditne");
                }

            }
        });
        MyMessage.removeErrorLabel(errorLabel);
    }
    
}
