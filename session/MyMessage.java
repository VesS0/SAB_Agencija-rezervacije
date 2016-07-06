/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javafx.scene.control.Label;

/**
 *
 * @author vlada
 */
public class MyMessage {

    public static void removeErrorLabel(Label errorLabel) {
        errorLabel.setText("");
        errorLabel.setVisible(false);
    }

    public static void setErrorLabel(Label errorLabel, String text, boolean error) {
        errorLabel.setText(text);
        errorLabel.setVisible(true);
        if (error) {
            errorLabel.setStyle("-fx-text-fill: red");
        } else {
            errorLabel.setStyle("-fx-text-fill: green");
        }
    }
}
