/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import session.MyMessage;
import entity.Apartman;
import entity.Korisnik;
import entity.Kupac;
import entity.Prodavac;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.MojiApartmaniModel;
import session.MySession;
import view.DodavanjeSobaView;
import view.RezervacijaSobeView;

/**
 * FXML Controller class
 *
 * @author Vlada
 */
public class IzmenaApartmanaController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField country;
    @FXML
    private TextField city;
    @FXML
    private TextField street;
    @FXML
    private TextField num;
    @FXML
    private TextArea desc;
    
    
    private MojiApartmaniController showMyApartmentController;
    @FXML
    private CheckBox zakljucan;
    @FXML
    private Label errorLabelAp;
    @FXML
    private Label errorLabel;
    @FXML
    private Button save;
    
    @FXML
    private Pane innerRoomSelector;
    
    private Apartman myApartment;
//    private TextField roomNoTextField;
//    private TextField roomCapacityTextField;
//    private TextField roomDetailTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sacuvajIzmene(ActionEvent event) {
        String name = this.name.getText();
        String description = desc.getText();
        boolean isChecked = zakljucan.isSelected();

        if (name == null || name.trim().equals("")
                || description == null || description.trim().equals("")) {
            MyMessage.setErrorLabel(errorLabelAp, "Neka polja su prazna!", true);
            return;
        }

        myApartment.setName(name);
        myApartment.setDescription(description);
        myApartment.setIsLocked(isChecked);
        if (MojiApartmaniModel.updateApartment(myApartment)) {
            MyMessage.setErrorLabel(errorLabelAp, "Uspesno izmenjen apartman!", false);
        } else {
            MyMessage.setErrorLabel(errorLabelAp, "Neuspesna izmena!", true);
        }
        
        showMyApartmentController.display();

    }
    
   
    public void displayApartment(Apartman apartment, MojiApartmaniController showMyApartmentController) {
        this.myApartment = apartment;
        this.showMyApartmentController = showMyApartmentController;
        setApartmentDetails();
    }

    private void setApartmentDetails() {
        name.setText(myApartment.getName());
        desc.setText(myApartment.getDescription());
        country.setText(myApartment.getState());
        city.setText(myApartment.getCity());
        street.setText(myApartment.getStreet());
        num.setText(myApartment.getNumber());
        zakljucan.setSelected(myApartment.isIsLocked());
        
        name.setEditable(showMyApartmentController!=null);
        desc.setEditable(showMyApartmentController!=null);
        country.setEditable(showMyApartmentController!=null);
        city.setEditable(showMyApartmentController!=null);
        street.setEditable(showMyApartmentController!=null);
        num.setEditable(showMyApartmentController!=null);
        save.setDisable(showMyApartmentController==null);
        //add.setDisable(showMyApartmentController==null); 
        
        Korisnik myUser = MySession.getSessionInstance().getSessionUser();
        if (myUser instanceof Kupac){
            RezervacijaSobeView personalWindow = new RezervacijaSobeView();
            innerRoomSelector.getChildren().setAll(personalWindow);
            personalWindow.fillScene(myApartment);
        }
        else
        {
            DodavanjeSobaView personalWindow = new DodavanjeSobaView();
            innerRoomSelector.getChildren().setAll(personalWindow);
            personalWindow.fillScene(myApartment);
        }
        
    }

    
    
}
