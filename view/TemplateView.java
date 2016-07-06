/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.TemplateController;
import entity.Prodavac;
import entity.Kupac;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import session.MySession;

/**
 *
 * @author vlada
 */
public class TemplateView {

    public void display(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/gui/Template.fxml").openStream());
            TemplateController controller = (TemplateController) loader.getController();

            controller.display();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            if (MySession.getSessionInstance().getSessionUser() instanceof Prodavac) {
                stage.setTitle("Prodavac");
            } else { 
                stage.setTitle("Kupac");
            }
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
