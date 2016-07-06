/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.IzmenaApartmanaController;
import controller.MojiApartmaniController;
import entity.Apartman;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author vlada
 */
public class IzmenaApartmanaView {

    public void display(Apartman apartment, MojiApartmaniController showMyApartmentController) {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Izmeni Apartman");

            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/gui/IzmenaApartmana.fxml").openStream());
            IzmenaApartmanaController editController = (IzmenaApartmanaController) loader.getController();
            editController.displayApartment(apartment, showMyApartmentController);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(IzmenaApartmanaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
