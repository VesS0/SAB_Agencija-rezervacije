/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
public class RezervacijaSobeView extends Pane {
    
    private RezervacijaSobeController controller;

    public void fillScene(Apartman myApartment) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent)loader.load(this.getClass().getResource("/gui/RezervacijaSobe.fxml").openStream());
            this.controller = (RezervacijaSobeController)loader.getController();
            this.controller.display(myApartment);
            this.getChildren().add(root);
        }
        catch (IOException ex) {
            Logger.getLogger(IzmenaLicnihPodatakaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RezervacijaSobeController getController() {
        return this.controller;
    }
}