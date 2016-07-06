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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.TemplateModel;
import model.IzmenaLicnihPodatakaModel;
import session.MySession;

/**
 * FXML Controller class
 *
 * @author Vlada
 */
public class IzmenaLicnihPodatakaController implements Initializable {

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
    private TextField numberTextField;
    @FXML
    private PasswordField passwordTextField;
    
    private Korisnik myUser;
    
    @FXML
    private Label POSBrojLabel;
     
    @FXML
    private Label errorLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveMyInfo(ActionEvent event) {
        
        String name = nameTextField.getText();
        String surrname = surnameTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String username = usernameTextField.getText();
        String sellerCustomer = numberTextField.getText();
        String password = passwordTextField.getText();
        
        if ((name == null || name.trim().equals(""))
                && (surrname == null || surrname.trim().equals(""))
                && (username == null || username.trim().equals(""))
                && (email == null || email.trim().equals(""))
                && (phone == null || phone.trim().equals(""))
                && (password == null || password.trim().equals(""))
                && (sellerCustomer == null || sellerCustomer.trim().equals(""))
                && (password==null || password.trim().equals(""))
                ) {
            
          
            MyMessage.setErrorLabel(errorLabel, "Sva polja su prazna!", true);
            return;
        }

        if (myUser instanceof Prodavac) {
            IzmenaLicnihPodatakaModel.changeUserData(myUser.getUserID() + "", name, surrname, email, phone, sellerCustomer, username,password, true);
            myUser = TemplateModel.getUserSeller(myUser);
        } else { 
            IzmenaLicnihPodatakaModel.changeUserData(myUser.getUserID() + "", name, surrname, email, phone, sellerCustomer, username, password, false);
            myUser = TemplateModel.getUserCustomer(myUser);
        }
        MyMessage.setErrorLabel(errorLabel, "Uspesno ste izvrsili izmenu!", false);
        removeDataText();

    }

    public void display() {
      resolveSellerCustomer();
        //removePassText();
        removeDataText();
//        MyMessage.removeErrorLabel(errorLabel);
    }


        private void resolveSellerCustomer() {
        myUser = MySession.getSessionInstance().getSessionUser();
        if (myUser instanceof Prodavac) {
            POSBrojLabel.setText("POS");
            numberTextField.setText(((Prodavac) myUser).getPOS());
        } else {
           POSBrojLabel.setText("Kreditna kartica");
            numberTextField.setText(((Kupac) myUser).getCardNo());

        }

        nameTextField.setText(myUser.getName());
        surnameTextField.setText(myUser.getSurrname());
        emailTextField.setText(myUser.getEmail());
        phoneTextField.setText(myUser.getPhone());
        usernameTextField.setText(myUser.getUserName());
    }

    private void removeDataText() {
        nameTextField.setText("");
        surnameTextField.setText("");
        emailTextField.setText("");
        phoneTextField.setText("");

        numberTextField.setText("");

        nameTextField.setText(myUser.getName());
        surnameTextField.setText(myUser.getSurrname());
        emailTextField.setText(myUser.getEmail());
        phoneTextField.setText(myUser.getPhone());
        passwordTextField.setText(myUser.getUserPass());
        
        if (myUser instanceof Prodavac) {
            numberTextField.setText(((Prodavac) myUser).getPOS());
        } else {
            numberTextField.setText(((Kupac) myUser).getCardNo());
        }
    }
    
}
