/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Korisnik;
import entity.Prodavac;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import session.MySession;
import view.DodavanjeApartmanaView;
import view.IzmenaLicnihPodatakaView;
import view.LoginView;
import view.MojiApartmaniView;
import view.PretragaApartmanaView;
import view.RezervacijeView;

/**
 * FXML Controller class
 *
 * @author Vlada
 */

public class TemplateController implements Initializable {

    @FXML
    private Label infoLabel;
    @FXML
    private Pane dynamicPane;
    @FXML
    private Button MojiApartmani;
    @FXML
    private Button DodajApartman;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadMojeInformacije(ActionEvent event) {
        IzmenaLicnihPodatakaView personalWindow = new IzmenaLicnihPodatakaView();
        dynamicPane.getChildren().setAll(personalWindow);
        infoLabel.setText("Moje Informacije");
        personalWindow.fillScene();
    }

    @FXML
    private void loadMojiApartmani(ActionEvent event) {
        MojiApartmaniView personalWindow = new MojiApartmaniView();
        dynamicPane.getChildren().setAll(personalWindow);
        infoLabel.setText("Moji Apartmani");
        personalWindow.fillScene();
    }

    @FXML
    private void loadDodajApartman(ActionEvent event) {
        DodavanjeApartmanaView personalWindow = new DodavanjeApartmanaView();
        dynamicPane.getChildren().setAll(personalWindow);
        infoLabel.setText("Dodavanje Apartmana");
        personalWindow.fillScene();
    }

    @FXML
    private void loadPretragaApartmana(ActionEvent event) {
        PretragaApartmanaView personalWindow = new PretragaApartmanaView();
        dynamicPane.getChildren().setAll(personalWindow);
        infoLabel.setText("Pretraga Apartmana");
        personalWindow.fillScene();
    }

    @FXML
    private void loadRezervacije(ActionEvent event) {
        RezervacijeView personalWindow = new RezervacijeView();
        dynamicPane.getChildren().setAll(personalWindow);
        infoLabel.setText("Rezervacije");
        personalWindow.fillScene();
    }

    @FXML
    private void logout(ActionEvent event) {
        MySession.getSessionInstance().setSessionUser(null);
        Stage previousStage = (Stage) infoLabel.getScene().getWindow();
        new LoginView().display(previousStage);
    }

    public void display() {
        Korisnik myUser = MySession.getSessionInstance().getSessionUser();
        //userNameLabel.setText(myUser.getUserName());
        IzmenaLicnihPodatakaView personalWindow = new IzmenaLicnihPodatakaView();
        dynamicPane.getChildren().setAll(personalWindow);   
        infoLabel.setText("Moje Informacije");
        Korisnik usr=MySession.getSessionInstance().getSessionUser();
        if (usr instanceof Prodavac) {
            MojiApartmani.setVisible(true);
            DodajApartman.setVisible(true);
        }
        else 
        {
            MojiApartmani.setVisible(false);
            DodajApartman.setVisible(false);
        }
        personalWindow.fillScene();   
    }
}
