/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import session.MyMessage;
import entity.Korisnik;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.MojiApartmaniModel;
import session.MySession;

/**
 * FXML Controller class
 *
 * @author Vlada
 */
public class DodavanjeApartmanaController implements Initializable {

    @FXML
    private TextField NazivTextField;
    @FXML
    private TextField DrzavaTextField;
    @FXML
    private TextField GradTextField;
    @FXML
    private TextField UlicaTextField;
    @FXML
    private TextField BrojTextField;
    @FXML
    private TextArea OpisTextField;
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
    private void addApartment(ActionEvent event) {
        String name = NazivTextField.getText();
        String description = OpisTextField.getText();
        String state = DrzavaTextField.getText();
        String city = GradTextField.getText();
        String street = UlicaTextField.getText();
        String numberString = BrojTextField.getText();

        if (name == null || name.trim().equals("")
                || description == null || description.trim().equals("")
                || state == null || state.trim().equals("")
                || city == null || city.trim().equals("")
                || street == null || street.trim().equals("")
                || numberString == null || numberString.trim().equals("")) {
            errorLabel.setText("");
            MyMessage.setErrorLabel(errorLabel, "Neka polja su prazna!", true);
            return;
        }

        int number = 0;
        try {
            number = Integer.parseInt(numberString);
        } catch (NumberFormatException nfe) {
            errorLabel.setText("");
            MyMessage.setErrorLabel(errorLabel, "Broj nije dobro unesen!", true);
            return;
        }

        Korisnik mUser = MySession.getSessionInstance().getSessionUser();
        if (MojiApartmaniModel.insertNewApartment(mUser.getUserID(), name, description, state, city, street, numberString)) {
           errorLabel.setText("");
            MyMessage.setErrorLabel(errorLabel, "Uspesno dodat apartman!", false);
        }
        else {
            errorLabel.setText("");
            MyMessage.setErrorLabel(errorLabel, "Neuspesno dodavanje apartmana!", true);
        }
        removeText();
    }

    public void display() {
        MyMessage.removeErrorLabel(errorLabel);
        removeText();
        
    }
     public void removeText() {
         NazivTextField.setText("");
        DrzavaTextField.setText("");
        GradTextField.setText("");
        UlicaTextField.setText("");
        BrojTextField.setText("");
        OpisTextField.setText("");
        errorLabel.setText("");
    }
    
}
