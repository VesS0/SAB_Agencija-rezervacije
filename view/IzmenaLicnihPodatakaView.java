/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  javafx.collections.ObservableList
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Parent
 *  javafx.scene.layout.Pane
 *  kontroler.LicneInformacijeController
 */
package view;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import controller.IzmenaLicnihPodatakaController;

public class IzmenaLicnihPodatakaView
extends Pane {
    private IzmenaLicnihPodatakaController controller;

    public void fillScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent)loader.load(this.getClass().getResource("/gui/IzmenaLicnihPodataka.fxml").openStream());
            this.controller = (IzmenaLicnihPodatakaController)loader.getController();
            this.controller.display();
            this.getChildren().add(root);
        }
        catch (IOException ex) {
            Logger.getLogger(IzmenaLicnihPodatakaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public IzmenaLicnihPodatakaController getController() {
        return this.controller;
    }
}
