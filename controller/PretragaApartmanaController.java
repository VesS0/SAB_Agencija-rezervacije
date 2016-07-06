/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Apartman;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MojiApartmaniModel;
import view.IzmenaApartmanaView;

/**
 * FXML Controller class
 *
 * @author Vlada
 */
public class PretragaApartmanaController implements Initializable {

    @FXML
    private TableView PretragaApartmanaTable;
    @FXML
    private TextField gradTextField;
    @FXML
    private TextField DrzavaTextField;
    
    private List<Apartman> allApartments;
    private ObservableList<Apartman> tableApartments;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    public void display() {
        fillApartments();
       
    }
    
     private void fillApartments() {
         gradTextField.setText("");
         DrzavaTextField.setText("");
         
        allApartments = MojiApartmaniModel.getAllApartments();
        tableApartments = FXCollections.observableArrayList(allApartments);
        
        TableColumn<Apartman, String> nameColumn = new TableColumn<>("Naziv");
        nameColumn.setMinWidth(220);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn stateColumn = new TableColumn("Drzava");
        stateColumn.setMinWidth(140);
        stateColumn.setCellValueFactory(new PropertyValueFactory<Apartman, String>("state"));
        
        TableColumn cityColumn = new TableColumn("Grad");
        cityColumn.setMinWidth(140);
        cityColumn.setCellValueFactory(new PropertyValueFactory<Apartman, String>("city"));
        
        TableColumn streetColumn = new TableColumn("Ulica");
        streetColumn.setMinWidth(260);
        streetColumn.setCellValueFactory(new PropertyValueFactory<Apartman, String>("street"));
        
        TableColumn numberColumn = new TableColumn("Broj");
        numberColumn.setMinWidth(140);
        numberColumn.setCellValueFactory(new PropertyValueFactory<Apartman, String>("number"));
        
        PretragaApartmanaTable.getColumns().addAll(nameColumn, stateColumn, cityColumn, streetColumn, numberColumn);
        PretragaApartmanaTable.setItems(tableApartments);
    }

    @FXML
    private void Pretrazi(ActionEvent event) {
        String city = gradTextField.getText();
        String state = DrzavaTextField.getText();
       
        allApartments = MojiApartmaniModel.filterStateCityApartments(state, city);
        tableApartments = FXCollections.observableArrayList(allApartments);
        PretragaApartmanaTable.setItems(tableApartments);
    }

    @FXML
    private void Detalji(ActionEvent event) {
        Apartman apartment=(Apartman) PretragaApartmanaTable.getSelectionModel().getSelectedItem();
        if (apartment==null) return;
        new IzmenaApartmanaView().display(apartment, null);
    }
    
}


    
   