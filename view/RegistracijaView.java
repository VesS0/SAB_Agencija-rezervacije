/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.LoginController;
import controller.RegistracijaController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Vlada
 */
public class RegistracijaView extends Pane {
    
    private RegistracijaController controller;

    public void display(Stage previousStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/gui/Registracija.fxml").openStream());
            RegistracijaController controller = (RegistracijaController) loader.getController();
            controller.display();
            Scene scene = new Scene(root);
            previousStage.setScene(scene);
            previousStage.setTitle("Registracija");
            previousStage.setResizable(false);
            previousStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


