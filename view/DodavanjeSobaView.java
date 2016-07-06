/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DodavanjeSobaController;
import controller.RezervacijaSobeController;
import entity.Apartman;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Vlada
 */
public class DodavanjeSobaView extends Pane {
    
    private DodavanjeSobaController controller;

    public void fillScene(Apartman myApartment) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent)loader.load(this.getClass().getResource("/gui/DodavanjeSoba.fxml").openStream());
            this.controller = (DodavanjeSobaController)loader.getController();
            this.controller.display(myApartment);
            this.getChildren().add(root);
        }
        catch (IOException ex) {
            Logger.getLogger(IzmenaLicnihPodatakaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DodavanjeSobaController getController() {
        return this.controller;
    }
}