/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MojiApartmaniController;
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
public class MojiApartmaniView extends Pane {
    
    private MojiApartmaniController controller;

    public void fillScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent)loader.load(this.getClass().getResource("/gui/MojiApartmani.fxml").openStream());
            this.controller = (MojiApartmaniController)loader.getController();
            this.controller.display();
            this.getChildren().add(root);
        }
        catch (IOException ex) {
            Logger.getLogger(IzmenaLicnihPodatakaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MojiApartmaniController getController() {
        return this.controller;
    }
}
